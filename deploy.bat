@echo off
REM echo "清理历史文件"
REM rm -rf server-*
REM mv study-server .
REM rm -rf study-server

set project=server-gateway;server-oauth2;server-system

REM 端口号
set port=8800

REM 版本号 > 当前日期
set version=`date +%m%d%H`

for %%a in (%project%) do (
    echo "******************************** 操作容器[%%~nxa]开始 ***************************"

    set container=%%~nxa
    set image="registry.cn-shanghai.aliyuncs.com/fank243/%container%"

    echo %container%

    echo "******************************** 操作容器[%%~nxa]结束 ***************************"
)
