#!/bin/bash

# shellcheck disable=SC2006
project=("server-gateway" "server-oauth2" "server-system")
# 端口号
port=8800
# 版本号 > 当前日期
version=`date +%m%d%H`

for serve in "${project[@]}"
do

echo "清理镜像及容器"
container=${serve}
image="registry.cn-shanghai.aliyuncs.com/${container}"

if docker ps | grep "${container}" ;then
    docker stop "${container}"
fi

if docker ps -a | grep "${container}";then
    docker rm "${container}"
fi

if docker images | grep "${image}";then
    docker rmi "${image}"
fi

case ${serve} in
server-oauth2)
  port=8901
  ;;
server-system)
  port=8903
  ;;
esac

echo "镜像制作"
docker build -t "${image}:${version}" "${serve}/target/${serve}.jar"

echo "推送镜像"
docker push "${image}"

echo "容器运行"
docker run -d --restart=always --name "${container}" -p ${port}:${port}  "${image}" -Duser.timezone=GMT+8
done
