/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * @param query uri参数
 * @param variable 参数名称
 * @returns {string|boolean} 获取成功则返回参数值，否则返回空串
 */
function getQueryVariable4UriStr(query, variable) {
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
