/**
 * 默认请求参数
 *
 * @param url 请求地址
 * @param type 请求方法
 * @param data 请求参数
 * @param contentType contentType
 * @param dataType dataType
 * @param success 请求成功时回调，可选
 * @param error 网络错误时回调，可选
 * @param isLoad 是否加载遮罩框，true：加载，false：不加载
 * @param isHandleFail success回调是否处理非成功操作，即接口返回的state!=00000时，不回调success函数
 * @param exLoginState 需要额外登录弹框的状态码集合，除401001以外
 */
const request = {
    url: '#',
    type: 'GET',
    data: {},
    contentType: 'application/json',
    dataType: 'json',
    success: function (res) {
        console.log(res);
    },
    error: function (res) {
        console.log(res);
        layer.msg("系统繁忙，请稍后重试", {icon: 2});
    },
    isLoad: false,
    isHandleFail: false,
    exLoginState: []
};

/** 登录弹框状态码集合 **/
const loginState = ["401001"];

/**
 * 封装GET请求
 */
function gasGet(obj) {
    obj["type"] = "GET";
    gasRequest(obj);
}

/**
 * 封装POST请求
 */
function gasPost(obj) {
    obj["type"] = "POST";
    gasRequest(obj);
}

/**
 * 封装PUT请求
 */
function gasPut(obj) {
    obj["type"] = "PUT";
    if(obj["isHandleFail"] === undefined){
        obj["isHandleFail"] = true;
    }
    gasRequest(obj);
}

/**
 * 封装DELETE请求
 */
function gasDelete(obj) {
    obj["type"] = "DELETE";
    if(obj["isHandleFail"] === undefined){
        obj["isHandleFail"] = true;
    }
    gasRequest(obj);
}

/** 启用日志调试标记 **/
let enableLog = false;

/**
 * 公共ajax请求封装
 * @param obj 请求传参，具体参考{@link request}定义
 */
function gasRequest(obj) {
    // 处理形参，若存在则进行替换，否则使用默认
    for (let key in obj) {
        request[key] = obj[key];
    }

    if(enableLog){
        console.log("请求实体：%o",request);
    }
    let index = request.isLoad ? layer.load(1) : "";
    $.ajax({
        url: request.url,
        type: request.type,
        contentType: request.contentType,
        data: request.data,
        success: function (res) {
            if(enableLog){
                console.log("响应实体：%o",res);
            }
            closeBox(index);
            if (res == null || res.state === undefined) {
                layer.msg("接口返回参数无法解析", {icon: 2});
                return;
            }
            // 未登录拦截
            if (request.exLoginState.length > 0) {
                loginState.concat(request.exLoginState);
            }
            for (let i = 0; i < loginState.length; i++) {
                if (loginState[i] === res.state) {
                    showLoginBox();
                    return;
                }
            }
            // 通用：非成功状态码时，返回错误提示
            if (request.isHandleFail && res.state !== '000000') {
                layer.msg(res.describe, {icon: 2});
                return;
            }
            request.success(res);
        },
        error: function (res) {
            closeBox(index);
            request.error(res);
        }
    });
}

/**
 * 关闭遮罩框
 */
function closeBox(index) {
    if (request.isLoad) {
        layer.close(index);
    }
}

/**
 * 登录弹框
 */
function showLoginBox() {
    if(enableLog){
        console.log("执行登录弹框...");
    }
    layer.open({
        title: "登录",
        url: "",
    });
}
