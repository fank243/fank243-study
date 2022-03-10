# 学习之旅

## JVM 配置
```bash
# gateway
-Dstudy.nacos-addr=${nacos addr:port} -Dstudy.nacos-namespace=${nacos namespace} -Dsentinel-dashboard=${sentinel:port}

# other
-Dstudy.nacos-addr=${nacos addr:port} -Dstudy.nacos-namespace=${nacos namespace}
```

## Apache SkyWorking

idea 设置

```bash
# vm
-javaagent:${引擎绝路径}
-javaagent:/home/**jason**/devops/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar

# Environment variables
SW_AGENT_NAME=${应用名称};SW_AGENT_COLLECTOR_BACKEND_SERVICES=${SkyWorking后台服务地址:127.0.0.1:11800}
SW_AGENT_NAME=server-gateway;SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800
```

## 更新版本

```bash
mvn versions:set -DnewVersion=1.0.0

# 提交
mvn versions:commit

# 撤销
mvn versions:revert
```