package com.fank243.study.gateway.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.gateway.constants.FilterOrderConstant;
import com.fank243.study.gateway.constants.GatewayContext;
import com.fank243.study.gateway.utils.LogUtils;

import brave.Tracer;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Request Params Filter
 * 
 * @author FanWeiJie
 * @since 2021-07-24 15:45:46
 */
@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {

    @Resource
    private Tracer tracer;
    /**
     * default HttpMessageReader
     */
    private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();

    private static final ResolvableType MULTIPART_DATA_TYPE =
        ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, Part.class);

    private static final Mono<MultiValueMap<String, Part>> EMPTY_MULTIPART_DATA =
        Mono.just(CollectionUtils.unmodifiableMultiValueMap(new LinkedMultiValueMap<String, Part>(0))).cache();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String method = request.getMethodValue();

        // HTTP GET
        if (HttpMethod.GET.matches(method)) {
            return handleGetMethod(exchange, chain, request);
        }
        // HTTP POST
        else if (HttpMethod.POST.matches(method)) {
            return handlePostMethod(exchange, chain, request);
        }

        return chain.filter(exchange);
    }

    /**
     * GET方法处理逻辑
     * 
     * @param exchange ServerWebExchange
     * @param chain GatewayFilterChain
     * @param request ServerHttpRequest
     * @return Mono
     */
    private Mono<Void> handleGetMethod(ServerWebExchange exchange, GatewayFilterChain chain,
        ServerHttpRequest request) {
        String queryParams = "";
        Map<String, String> params = request.getQueryParams().toSingleValueMap();
        if (MapUtil.isNotEmpty(params)) {
            queryParams = JSONUtil.toJsonStr(params);
        }

        // 打印日志
        LogUtils.printLog(request, queryParams);

        return chain.filter(exchange);
    }

    /**
     * POST方法处理逻辑
     *
     * @param exchange ServerWebExchange
     * @param chain GatewayFilterChain
     * @param request ServerHttpRequest
     * @return Mono
     */
    private Mono<Void> handlePostMethod(ServerWebExchange exchange, GatewayFilterChain chain,
        ServerHttpRequest request) {
        GatewayContext gatewayContext = new GatewayContext();
        gatewayContext.setPath(request.getPath().pathWithinApplication().value());
        // save context
        exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);

        MediaType contentType = request.getHeaders().getContentType();

        // ContentType：application/json
        if (MediaType.APPLICATION_JSON.equals(contentType)) {
            return readJsonBody(exchange, chain, gatewayContext);
        }

        // ContentType： multipart/form-data
        if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)) {
            return readFormData(exchange, chain, gatewayContext);
        }
        return chain.filter(exchange);
    }

    /**
     * POST方法重新构造请求体
     * 
     * @param exchange ServerWebExchange
     * @param chain GatewayFilterChain
     * @param gatewayContext gatewayContext
     * @return Mono
     */
    private Mono<Void> readJsonBody(ServerWebExchange exchange, GatewayFilterChain chain,
        GatewayContext gatewayContext) {
        ServerHttpRequest request = exchange.getRequest();

        String traceId = tracer.currentSpan().context().traceIdString();
        String spanId = tracer.currentSpan().context().spanIdString();

        return DataBufferUtils.join(request.getBody()).flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            gatewayContext.setCacheBody(new String(bytes, StandardCharsets.UTF_8));

            DataBufferUtils.release(dataBuffer);

            Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                DataBufferUtils.retain(buffer);
                return Mono.just(buffer);
            });

            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return cachedFlux;
                }
            };

            // 打印日志
            LogUtils.printLog(request, gatewayContext.getCacheBody(), traceId, spanId);

            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

            // save body into gatewayContext
            return ServerRequest.create(mutatedExchange, MESSAGE_READERS).bodyToMono(String.class)
                .doOnNext(gatewayContext::setCacheBody).then(chain.filter(mutatedExchange));
        });
    }

    private Mono<Void> readFormData(ServerWebExchange exchange, GatewayFilterChain chain,
        GatewayContext gatewayContext) {
        ServerHttpRequest request = exchange.getRequest();
        String traceId = tracer.currentSpan().context().traceIdString();
        String spanId = tracer.currentSpan().context().spanIdString();

        return request.getBody().collectList().flatMap(dataBuffers -> {
            final byte[] totalBytes = dataBuffers.stream().map(dataBuffer -> {
                try {
                    return IOUtils.toByteArray(dataBuffer.asInputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).reduce(this::addBytes).get();
            final ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return Flux.just(buffer(totalBytes));
                }
            };
            final ServerCodecConfigurer configurer = ServerCodecConfigurer.create();
            final Mono<MultiValueMap<String, Part>> multiValueMapMono = repackageMultipartData(decorator, configurer);
            return multiValueMapMono.publishOn(Schedulers.boundedElastic()).flatMap(part -> {
                for (String key : part.keySet()) {
                    // 如果为文件时 则进入下一次循环
                    if ("file".equals(key)) {
                        continue;
                    }
                    Objects.requireNonNull(part.getFirst(key)).content().subscribe(buffer -> {
                        final byte[] bytes = new byte[buffer.readableByteCount()];
                        buffer.read(bytes);
                        DataBufferUtils.release(buffer);
                        final String bodyString = new String(bytes, StandardCharsets.UTF_8);
                        gatewayContext.setCacheBody(bodyString);
                    });
                }
                // 打印日志
                LogUtils.printLog(request, gatewayContext.getCacheBody(), traceId, spanId);

                return chain.filter(exchange.mutate().request(decorator).build());
            });
        });
    }

    @SuppressWarnings("unchecked")
    private static Mono<MultiValueMap<String, Part>> repackageMultipartData(ServerHttpRequest request,
        ServerCodecConfigurer configurer) {
        try {
            final MediaType contentType = request.getHeaders().getContentType();
            if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)) {
                return ((HttpMessageReader<MultiValueMap<String, Part>>)configurer.getReaders().stream()
                    .filter(reader -> reader.canRead(MULTIPART_DATA_TYPE, MediaType.MULTIPART_FORM_DATA)).findFirst()
                    .orElseThrow(() -> new IllegalStateException("No multipart HttpMessageReader.")))
                        .readMono(MULTIPART_DATA_TYPE, request, Collections.emptyMap())
                        .switchIfEmpty(EMPTY_MULTIPART_DATA).cache();
            }
        } catch (InvalidMediaTypeException ex) {
            // Ignore
        }
        return EMPTY_MULTIPART_DATA;
    }

    /**
     * addBytes.
     * 
     * @param first first
     * @param second second
     * @return byte
     */
    public byte[] addBytes(byte[] first, byte[] second) {
        final byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private DataBuffer buffer(byte[] bytes) {
        final NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        final DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }
}
