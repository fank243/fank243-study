/**
 * 默认请求参数
 *
 * @param url 请求地址
 * @param type 请求方法，默认：GET
 * @param data 请求参数，默认：{}，可选
 * @param contentType contentType，默认：application/json，可选
 * @param dataType dataType，默认；json，可选
 * @param success 请求成功时回调，可选
 * @param error 网络错误时回调，可选
 * @param isNeedLogin 是否需要登录，默认：否，可选
 * @param isLoad 是否加载遮罩框，true：加载，false(默认)：不加载
 * @param isHandleFail success回调是否处理非成功操作，即接口返回的status!=200时，不回调success函数
 * @param exLoginStatus 需要额外登录弹框的状态码集合，除401以外
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
    isNeedLogin: false,
    isLoad: false,
    isHandleFail: false,
    exLoginStatus: []
};

/** 登录弹框状态码集合 **/
const loginStatus = ["401"];

/**
 * 封装GET请求
 */
function ajaxGet(obj) {
    obj["type"] = "GET";
    ajaxRequest(obj);
}

/**
 * 封装POST请求
 */
function ajaxPost(obj) {
    obj["type"] = "POST";
    ajaxRequest(obj);
}

/**
 * 封装PUT请求
 */
function ajaxPut(obj) {
    obj["type"] = "PUT";
    if (obj["isHandleFail"] === undefined) {
        obj["isHandleFail"] = true;
    }
    ajaxRequest(obj);
}

/**
 * 封装DELETE请求
 */
function ajaxDelete(obj) {
    obj["type"] = "DELETE";
    if (obj["isHandleFail"] === undefined) {
        obj["isHandleFail"] = true;
    }
    ajaxRequest(obj);
}

/** 启用日志调试标记 **/
let enableLog = false;

/**
 * 校验参数
 */
function checkParam(obj) {
    if (obj === null || obj === undefined) {
        throw new Error("请求对象不能为NULL");
    }
    if (obj['url'] === undefined || obj['url'] === '') {
        throw new Error("请求地址[url]参数不能为空");
    }
}

/**
 * 公共ajax请求封装
 * @param obj 请求传参，具体参考{@link request}定义
 */
function ajaxRequest(obj) {
    checkParam(obj);

    // 处理形参，若存在则进行替换，否则使用默认
    for (let key in obj) {
        request[key] = obj[key];
    }

    if (enableLog) {
        console.log("请求实体：%o", request);
    }
    let index = request.isLoad ? layer.load(1) : "";
    $.ajax({
        url: request.url,
        type: request.type,
        contentType: request.contentType,
        data: request.data,
        success: function (res) {
            if (enableLog) {
                console.log("响应实体：%o", res);
            }
            closeBox(index);
            if (res == null) {
                layer.msg("接口请求失败，请检查网络后重试", {icon: 2});
                return;
            }
            // 未登录拦截
            if (request.isNeedLogin) {
                if (request.exLoginStatus.length > 0) {
                    loginStatus.concat(request.exLoginStatus);
                }
                for (let i = 0; i < loginStatus.length; i++) {
                    if (loginStatus[i] === res.status) {
                        showLoginBox();
                        return;
                    }
                }
            }
            // 通用：非成功状态码时，返回错误提示
            if (request.isHandleFail && !res.success) {
                layer.msg(res.message, {icon: 2});
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
    if (enableLog) {
        console.log("执行登录弹框...");
    }
    layer.open({
        title: "登录",
        url: "",
    });
}
