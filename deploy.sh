#!/bin/bash

#echo "清理历史文件"
#rm -rf server-*
#mv study-server .
#rm -rf study-server

# shellcheck disable=SC2006
project=("server-gateway" "server-oauth2" "server-system")
# 端口号
port=8800
# 版本号 > 当前日期
# shellcheck disable=SC2006
version=`date +%m%d%H`

for serve in "${project[@]}"
do

echo "******************************** 操作容器[${serve}]开始 ***************************"
container=${serve}
image="registry.cn-shanghai.aliyuncs.com/fank243/${container}"

#if docker ps | grep "${container}" ;then
#    echo "开始停止容器：docker stop ${container}"
#    docker stop "${container}"
#fi

if docker ps -a | grep "${container}" ;then
    echo "开始清理容器：docker rm -f ${container}"
    docker rm -f "${container}"
fi

if docker images | grep "${image}" ;then
    echo "开始清理镜像：docker rmi $(docker images | grep "${image}*" | awk '{print $3}')"
    # shellcheck disable=SC2046
    docker rmi $(docker images | grep "${image}*" | awk '{print $3}')
fi

case ${serve} in
server-oauth2)
  port=8901
  ;;
server-system)
  port=8903
  ;;
esac
echo "端口号设置完成：${port}"

echo "开始镜像制作：docker build -t ${image}:${version} ${serve}"
# docker build -t Dockerfile文件所在目录
docker build -t "${image}:${version}" "${serve}"

echo "开始运行容器：docker run -d --restart=always --name ${container} -p ${port}:${port} ${image}:${version}"
docker run -d --restart=always --name "${container}" -p ${port}:${port} "${image}:${version}"

if [ "$1" == "prod" ]; then
    echo "开始推送镜像：docker push ${image}:${version}"
    nohup docker push "${image}:${version}" > nohup.log 2>&1 &
fi

echo "******************************** 操作容器[${serve}]结束 ***************************"
done

# shellcheck disable=SC2022
echo "开始清理none镜像：docker rmi $(docker images | grep "none*" | awk '{print $3}')"
# shellcheck disable=SC2046
# shellcheck disable=SC2022
docker rmi $(docker images | grep "none*" | awk '{print $3}')
