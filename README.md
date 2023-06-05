# Learning Journey

## JVM OPTIONS

```bash
-javaagent:D:/devops/local/skywalking-agent/skywalking-agent.jar
-Dskywalking.agent.service_name=${服务名称}
-Dskywalking.collector.backend_service=127.0.0.1:11800
```

## Apache SkyWorking

idea vm options

```bash
# vm
-javaagent:${skywalking agent location}
# eg:
-javaagent:/home/**jason**/devops/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar

# Environment variables
SW_AGENT_NAME=${appName};SW_AGENT_COLLECTOR_BACKEND_SERVICES=${SkyWorking Server Addr:127.0.0.1:11800}
# eg:
-Dskywalking.agent.service_name=study-gateway -Dskywalking.collector.backend_service=127.0.0.1:11800
```

## Upgrade Maven Version

```bash
mvn versions:set -DnewVersion=1.0.0

# commit
mvn versions:commit

# revert
mvn versions:revert
```