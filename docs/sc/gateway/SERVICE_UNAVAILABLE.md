# Spring Cloud Gateway 503 SERVICE_UNAVAILABLE

## 问题描述

框架信息

```bash
Spring Boot：2.5.0
Spring Cloud：2020.0.3
Spring Cloud Alibaba：2021.1
nacos-client：2.0.2
```

最近基于以下版本开发时，遇到`Spring Cloud Gateway` 无法找到已经成功注册到`Nacos Server`中的服务，直接503了。

```java
503 SERVICE_UNAVAILABLE "Unable to find instance for xxx"
```

最初还以为时`Nacos`版本引发的问题，后面将`Nacos`降低到1.4.2的版本问题依旧，最后还是在github找到答案了：https://github.com/alibaba/nacos/issues/5210

原来是因为路由表使用的`lb`提供的负载均衡查找服务，但是在`spring-cloud-starter-gateway`并没有显示引入`lb`，导致lb没法根据服务名称查找到服务。

- `spring-cloud-starter-gateway`对`lb`的依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    <version>3.0.3</version>
    <scope>compile</scope>
    <optional>true</optional>
</dependency>
```

- 网关路由配置

  ```yaml
  spring:
    cloud:
      gateway:
        routes:
          - id: study-auth
            uri: lb://study-auth
            predicates:
              - Path=/api/auth/**
            filters:
              - StripPrefix=1
  ```

  

## 解决方案

在网关模块手动引入`lb`的依赖即可

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

