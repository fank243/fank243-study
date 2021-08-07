# 学习之旅

## Apache SkyWorking

idea 设置
```bash
# vm
-javaagent:${引擎绝路径}
-javaagent:/home/jason/devops/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar

# Environment variables
SW_AGENT_NAME=${应用名称};SW_AGENT_COLLECTOR_BACKEND_SERVICES=${SkyWorking后台服务地址:127.0.0.1:11800}
SW_AGENT_NAME=server-adapter;SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800
```