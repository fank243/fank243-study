# Learning Journey

## JVM OPTIONS

```bash
# gateway
-Dstudy.remote-ip=127.0.0.1
-javaagent:E:/devops/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=server-gateway
-Dskywalking.collector.backend_service=127.0.0.1:11800

# other
-Dstudy.nacos-addr=127.0.0.1
-javaagent:E:/devops/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar
-Dskywalking.agent.service_name=server-oauth2
-Dskywalking.collector.backend_service=127.0.0.1:11800
```

## Application Start

idea VM options

```bash
-Dstudy.remote-ip=${remote addr} 
```

## Apache SkyWorking

idea VM options

```bash
# vm
-javaagent:${skywalking agent location}
# eg:
-javaagent:/home/**jason**/devops/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar

# Environment variables
SW_AGENT_NAME=${appName};SW_AGENT_COLLECTOR_BACKEND_SERVICES=${SkyWorking Server Addr:127.0.0.1:11800}
# eg:
-Dskywalking.agent.service_name=server-gateway -Dskywalking.collector.backend_service=127.0.0.1:11800
```

## Upgrade Maven Version

```bash
mvn versions:set -DnewVersion=1.0.0

# commit
mvn versions:commit

# revert
mvn versions:revert
```