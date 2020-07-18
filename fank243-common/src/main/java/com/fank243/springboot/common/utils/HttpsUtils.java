package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP HTTPS 请求接口
 * 
 * @author FanWeiJie
 * @date 2017-09-22 11:43:08
 **/
@Slf4j
public class HttpsUtils {

    /** 等待连接池分配的超时时，10s **/
    private static final int REQ_TIME_OUT = 10 * 1000;
    /** 建立网络连接的超时时间(请求)，20s **/
    private static final int CONN_TIME_OUT = 20 * 1000;
    /** 通讯过程中的超时时间(响应)，30s **/
    private static final int SOCKET_TIME_OUT = 30 * 1000;

    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;

    static {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, (TrustStrategy)(x509Certificates, s) -> true);
            sslsf = new SSLConnectionSocketFactory(builder.build(),
                new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
            cm = new PoolingHttpClientConnectionManager(registry);
            // 设置默认通讯超时时间
            cm.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(SOCKET_TIME_OUT).build());
            // max connection
            cm.setMaxTotal(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).setConnectionManagerShared(true)
            .build();
    }

    private static RequestConfig config() {
        return RequestConfig.custom()
            // 等待连接池分配的超时时间
            .setConnectionRequestTimeout(REQ_TIME_OUT)
            // 建立网络连接的超时时间
            .setConnectTimeout(CONN_TIME_OUT)
            // 通讯过程中的超时时间
            .setSocketTimeout(SOCKET_TIME_OUT).build();
    }

    /**
     * HTTP GET 请求
     * 
     * @param url 请求地址
     * @param headerMap 请求头
     * @return 响应参数
     */
    public static String get(String url, Map<String, String> headerMap, Charset charset) {

        String result;
        HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.setConfig(config());

            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse httpResponse = getHttpClient().execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                log.warn("请求失败：[{}：{}]", statusLine.getStatusCode(), statusLine.getReasonPhrase());
                return "";
            }

            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity, charset);

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    /**
     * HTTP GET 请求
     * 
     * @param url 请求地址
     * @param headerMap 请求头
     * @return 响应参数
     */
    public static String get(String url, Map<String, String> headerMap) {

        String result;
        HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.setConfig(config());

            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse httpResponse = getHttpClient().execute(httpGet);

            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    /**
     * HTTP GET 请求
     * 
     * @param url 请求地址
     * @return 响应参数
     */
    public static String get(String url) {

        String result;
        HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.setConfig(config());

            HttpResponse httpResponse = getHttpClient().execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                log.warn("请求失败：[{}：{}]", statusLine.getStatusCode(), statusLine.getReasonPhrase());
                return "";
            }

            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    /**
     * HTTP POST 请求 > ContentType：application/x-www-form-urlencoded
     * 
     * @param url 请求地址
     * @param headerMap 请求头参数
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String sendPost(String url, Map<String, String> headerMap, Map<String, String> paramMap,
                                  Charset charset) {

        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setConfig(config());

            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, charset);

            httpPost.setEntity(uefEntity);

            HttpResponse httpResponse = getHttpClient().execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity resEntity = httpResponse.getEntity();

            return EntityUtils.toString(resEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }

        return null;
    }

    /**
     * HTTP POST 请求 > ContentType：application/json
     *
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String postForJson(String url, Map<String, String> paramMap) {
        return postForJson(url, null, paramMap, StandardCharsets.UTF_8);
    }

    /**
     * HTTP POST 请求 > ContentType：application/json
     *
     * @param url 请求地址
     * @param jsonObject 请求参数
     * @return 响应参数
     */
    public static String postForJson(String url, JSONObject jsonObject) {
        return postForJson(url, jsonObject, StandardCharsets.UTF_8);
    }

    /**
     * HTTP POST 请求 > ContentType：application/json
     *
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String postForJson(String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        return postForJson(url, headerMap, paramMap, StandardCharsets.UTF_8);
    }

    /**
     * HTTP POST 请求 > ContentType：application/json
     *
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String postForJson(String url, Map<String, String> headerMap, Map<String, String> paramMap,
                                     Charset charset) {

        HttpPost httpPost = new HttpPost(url);

        String result;
        try {
            httpPost.setConfig(config());

            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            StringEntity entity =
                new StringEntity(JSON.toJSONString(paramMap), ContentType.create("application/json", charset));

            httpPost.setEntity(entity);

            HttpResponse httpResponse = getHttpClient().execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * HTTP POST 请求 > ContentType：application/json
     *
     * @param url 请求地址
     * @param jsonObject 请求参数
     * @return 响应参数
     */
    public static String postForJson(String url, JSONObject jsonObject, Charset charset) {

        HttpPost httpPost = new HttpPost(url);

        String result;
        try {
            httpPost.setConfig(config());

            StringEntity entity =
                new StringEntity(jsonObject.toString(), ContentType.create("application/json", charset));

            httpPost.setEntity(entity);

            HttpResponse httpResponse = getHttpClient().execute(httpPost);
            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * HTTP POST 请求 > ContentType：application/x-www-form-urlencoded
     *
     * @param url 请求地址
     * @param headerMap 请求头参数
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String post(String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        return post(url, headerMap, paramMap, StandardCharsets.UTF_8);
    }

    /**
     * HTTP POST 请求 > ContentType：application/x-www-form-urlencoded
     *
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String post(String url, Map<String, String> paramMap) {
        return post(url, null, paramMap, StandardCharsets.UTF_8);
    }

    /**
     * HTTP POST 请求 > ContentType：application/x-www-form-urlencoded
     *
     * @param url 请求地址
     * @param paramMap 请求参数
     * @param charset 编码
     * @return 响应参数
     */
    public static String post(String url, Map<String, String> paramMap, Charset charset) {
        return post(url, null, paramMap, charset);
    }

    /**
     * HTTP POST 请求 > ContentType：application/x-www-form-urlencoded
     *
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应参数
     */
    public static String post(String url, Map<String, String> headerMap, Map<String, String> paramMap,
                              Charset charset) {

        HttpPost httpPost = new HttpPost(url);

        String result;
        try {
            httpPost.setConfig(config());

            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, charset);

            httpPost.setEntity(uefEntity);

            HttpResponse httpResponse = getHttpClient().execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    private static String readHttpResponse(HttpResponse httpResponse) throws ParseException, IOException {
        StringBuilder builder = new StringBuilder();
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        builder.append("status:").append(httpResponse.getStatusLine());
        builder.append("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            builder.append("\t").append(iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            builder.append("response length:").append(responseString.length());
            builder.append("response content:").append(responseString.replace("\r\n", ""));
        }
        return builder.toString();
    }
}