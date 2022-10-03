package com.fank243.study.common.mybatis.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author FanWeiJie
 * @since 2022-09-30 21:19:43
 */
@Slf4j
public class SqlFilterArgumentResolver implements HandlerMethodArgumentResolver {

    private final static String[] KEYWORDS = {"master", "truncate", "insert", "select", "delete", "update", "declare",
        "alter", "drop", "sleep", "extractvalue", "concat"};

    /**
     * 判断Controller是否包含page 参数
     * 
     * @param parameter 参数
     * @return 是否过滤
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Page.class);
    }

    /**
     * @param parameter 入参集合
     * @param mavContainer model 和 view
     * @param webRequest web相关
     * @param binderFactory 入参解析
     * @return 检查后新的page对象
     *         <p>
     *         page 只支持查询 GET .如需解析POST获取请求报文体处理
     */
    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String[] ascs = request.getParameterValues("ascs");
        String[] descs = request.getParameterValues("descs");
        String current = request.getParameter("current");
        String size = request.getParameter("size");

        Page<?> page = new Page<>();
        if (StrUtil.isNotBlank(current)) {
            page.setCurrent(Long.parseLong(current));
        }

        if (StrUtil.isNotBlank(size)) {
            page.setSize(Long.parseLong(size));
        }

        List<OrderItem> orderItemList = new ArrayList<>();
        Optional.ofNullable(ascs).ifPresent(
            s -> orderItemList.addAll(Arrays.stream(s).filter(sqlInjectPredicate()).map(OrderItem::asc).toList()));
        Optional.ofNullable(descs).ifPresent(
            s -> orderItemList.addAll(Arrays.stream(s).filter(sqlInjectPredicate()).map(OrderItem::desc).toList()));
        page.addOrder(orderItemList);

        return page;
    }

    /**
     * 判断用户输入里面有没有关键字
     * 
     * @return Predicate
     */
    private Predicate<String> sqlInjectPredicate() {
        return sql -> Arrays.stream(KEYWORDS).noneMatch(keyword -> StrUtil.containsIgnoreCase(sql, keyword));
    }

}