/**
 * 获取URL请求参数
 * @param variable 参数名称
 * @returns {string|boolean} 获取成功则返回参数值，否则返回空串
 */
function getQueryVariable(variable) {
    const query = window.location.search.substring(1);
    const vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        const pair = vars[i].split("=");
        if (pair[0] === variable) {
            return pair[1];
        }
    }
    return "";
}

/**
 * 获取URL请求参数
 * @param variable 参数名称
 * @returns {string} 获取成功则返回参数值，否则返回空串
 */
function getQueryUriVariable(variable) {
    const query = window.location.search.substring(1);
    const vars = query.split(variable + "=");
    for (let str of vars) {
        if (!isEmpty(str)) {
            return str;
        }
    }
    return "";
}

function isEmpty(str) {
    return str == null || str.trim() === '';
}
