# Nacos 配置文件模板

## 导入依赖

引入 Spring Cloud Alibaba Nacos 作为配置中心和注册中心的时候，需要现在项目pom.xml文件中引入必要的依赖

```xml
<!-- 作为Nacos服务注册与发现客户端 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <version>${latest.version}</version>
</dependency>
<!-- 作为Nacos配置中心客户端 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <version>${latest.version}</version>
</dependency>
```

## 添加配置

Spring Cloud 中配置文件加载有着优先顺序的，优先加载`bootstrap.yml`文件，然后在加载`application.yml` 文件，同时二者是可以共存的，只是Spring Cloud 会优先读取`bootstrap.yml`文件中的配置，因此，下述配置放置在`bootstrap.yml` 文件中，另一个比较重要的原因时，如果使用`application.yml`文件进行配置，则无法在应用启动的时候读取一些在应用启动的就需要使用到的配置(服务注册到Nacos中这个是没有问题的)，比如：**数据库连接、redis连接** 等等，因此建议在Spring Cloud 项目中建议使用`bootstrap.yml`作为配置文件名称。

```yaml
spring:
  application:
  	# 应用名称，如果Nacos不指定prefix时则必须指定应用名称
  	name: study-nacos
  cloud:
    nacos:
      discovery:
        # Nacos 服务器地址及端口号
        server-addr: localhost:8848
		# Nacos 命名空间，一般用于多环境切换
        namespace: study
      config:
        server-addr: ${spring.cloud.nacos.discovery.server-addr}
        namespace: ${spring.cloud.nacos.discovery.namespace}
        # 当在Nacos中修改配置并发布时是否更新客户端配置，默认：true
        refresh-enabled: true 
        # 连接Nacos失败时重试次数
        config-retry-time: 3 
        # 连接Nacos失败时最大重试次数
        max-retry: 10        
        # 指定应用配置文件名称前缀，默认：${spring.application.name}
        prefix: ${spring.application.name}
        # 指定应用配置文件名称后缀，默认：properties
        file-extension: properties
        # 共享配置文件 > 多个应用共享的配置
        shared-configs:
          - study-shared-datasource.properties # 数据库连接配置
          - study-shared-redis.properties # Redis 连接配置
        # 额外配置文件
        extension-configs:
          - study-extension-netty.properties # Netty 相关配置
```



