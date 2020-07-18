
/***
 * 获取文件后缀
 * @param fileName 文件名称
 * @returns 文件后缀
 */
function getSuffix(fileName) {
    let pos = fileName.lastIndexOf(".");
    let suffix = '';
    if (pos !== -1) {
        suffix = fileName.substring(pos);
    }
    return suffix;
}

/**
 * 获取星期英文
 * @param week 数字
 */
function getWeekEn(week) {
    let weekEn;
    switch (week) {
        case 1:
            weekEn = "Mon";
            break;
        case 2:
            weekEn = "Tue";
            break;
        case 3:
            weekEn = "Wed";
            break;
        case 4:
            weekEn = "Thu";
            break;
        case 5:
            weekEn = "Fri";
            break;
        case 6:
            weekEn = "Sat";
            break;
        case 7:
            weekEn = "Sun";
            break;

        default:
            break;
    }
    return weekEn;
}


/**
 * 字符串赚日期
 * @param date 日期字符串
 * @returns {Date}
 */
let strToDate = function (date) {
    let t = Date.parse(date);
    if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
    } else {
        return new Date();
    }
};

/**
 * 加载提示语
 * @param e
 * @param msg
 */
function showTips(e, msg) {
    layer.tips(msg, e, {
        tips: 1
    });
}


/**
 * Formdata To Json
 * @param formData
 * @returns {string}
 */
function convertFormdataToJson(formData) {
    let objData = {};

    for (let entry of formData.entries()) {
        objData[entry[0]] = entry[1];
    }

    return JSON.stringify(objData);
}

/**
 * 预览图片
 * @param type 默认图片类别，用于图片加载异常时展示默认图片，1：视频图片、2：头像图片、其他：默认图片
 * @param url 图片绝对路径
 */
function previewImg(type, url) {
    let img, width = "auto", height = "auto";

    if (isUrl(url) && url.indexOf("/images/") > 0 && url.split("/")[2].endWith("aliyuncs.com")) {
        img = "<img src='" + url + "' alt = ''/>";
        // 创建对象
        let image = new Image();
        image.src = url;
        if (image.width > document.body.clientWidth) {
            width = document.body.clientWidth / 2;
            height = document.body.clientHeight / 2;
        }
    } else {
        switch (type) {
            // 视频
            case 1:
                img = "<img src='/images/default_video.png' alt=''/>";
                break;
            // 头像
            case 2:
                img = "<img src='/images/photo.jpg' alt=''/>";
                break;
            // 默认
            default:
                img = "<img src='/images/default.png' alt=''/>";
                break;
        }
    }

    layer.open({
        type: 1,
        closeBtn: 1,
        shade: false,
        title: false, //不显示标题
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: false,
        area: [width, height],
        content: img,//捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
}


/**
 * 预览视频
 * @param url 视频路径
 */
function previewVideo(url) {
    layer.open({
        type: 2,
        closeBtn: 1,
        shade: false,
        title: false, //不显示标题
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: false,
        area: ["65%", "60%"],
        content: url,//捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
}

/**
 * 获取操作系统信息
 * @returns {Object}
 */
function getOsInfo() {
    let userAgent = navigator.userAgent.toLowerCase();
    let name;
    if (userAgent.indexOf("win") > -1) {
        if (userAgent.indexOf("windows nt 5.0") > -1) {
            name = "Windows 2000";
        } else if (userAgent.indexOf("windows nt 5.1") > -1 || userAgent.indexOf("windows nt 5.2") > -1) {
            name = "Windows XP";
        } else if (userAgent.indexOf("windows nt 6.0") > -1) {
            name = "Windows Vista";
        } else if (userAgent.indexOf("windows nt 6.1") > -1 || userAgent.indexOf("windows 7") > -1) {
            name = "Windows 7";
        } else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows 8") > -1) {
            name = "Windows 8";
        } else if (userAgent.indexOf("windows nt 6.3") > -1) {
            name = "Windows 8.1";
        } else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 10.0") > -1) {
            name = "Windows 10";
        } else {
            name = "Unknown";
        }
    } else if (userAgent.indexOf("iphone") > -1) {
        name = "Iphone";
    } else if (userAgent.indexOf("mac") > -1) {
        name = "Mac";
    } else if (userAgent.indexOf("linux") > -1) {
        if (userAgent.indexOf("android") > -1) {
            name = "Android"
        } else {
            name = "Linux";
        }
    } else if (userAgent.indexOf("x11") > -1 || userAgent.indexOf("unix") > -1 || userAgent.indexOf("sunname") > -1 || userAgent.indexOf("bsd") > -1) {
        name = "Unix";
    } else {
        name = "Unknown";
    }
    return name;
}

/**
 * 获取客户端浏览器版本信息
 * @returns {*|{client: {isStrict: boolean, agent: string, operaVersion: operaVersion, supportScope: (function(): boolean), isIE: boolean, isWebkit: boolean, supportSubTitle: (function(): boolean), ieVersion: ieVersion, isGecko: (boolean|boolean)}}}
 */
function getBrowserInfo() {
    let browser;
    browser = browser || (function (window) {
        let document = window.document,
            navigator = window.navigator,
            agent = navigator.userAgent.toLowerCase(),
            //IE8+支持.返回浏览器渲染当前文档所用的模式
            //IE6,IE7:undefined.IE8:8(兼容模式返回7).IE9:9(兼容模式返回7||8)
            //IE10:10(兼容模式7||8||9)
            IEMode = document.documentMode,
            //chorme
            chrome = window.chrome || false,
            System = {
                //user-agent
                agent: agent,
                //是否为IE
                isIE: /trident/.test(agent),
                //Gecko内核
                isGecko: agent.indexOf("gecko") > 0 && agent.indexOf("like gecko") < 0,
                //webkit内核
                isWebkit: agent.indexOf("webkit") > 0,
                //是否为标准模式
                isStrict: document.compatMode === "CSS1Compat",
                //是否支持subtitle
                supportSubTitle: function () {
                    return "track" in document.createElement("track");
                },
                //是否支持scoped
                supportScope: function () {
                    return "scoped" in document.createElement("style");
                },

                //获取IE的版本号
                ieVersion: function () {
                    var rMsie = /(msie\s|trident.*rv:)([\w.]+)/;
                    var ma = window.navigator.userAgent.toLowerCase()
                    var match = rMsie.exec(ma);
                    try {
                        return match[2];
                    } catch (e) {
//									console.log("error");
                        return IEMode;
                    }
                },
                //Opera版本号
                operaVersion: function () {
                    try {
                        if (window.opera) {
                            return agent.match(/opera.([\d.]+)/)[1];
                        } else if (agent.indexOf("opr") > 0) {
                            return agent.match(/opr\/([\d.]+)/)[1];
                        }
                    } catch (e) {
//									console.log("error");
                        return 0;
                    }
                }
            };

        try {
            //浏览器类型(IE、Opera、Chrome、Safari、Firefox)
            System.type = System.isIE ? "IE" :
                window.opera || (agent.indexOf("opr") > 0) ? "Opera" :
                    (agent.indexOf("chrome") > 0) ? "Chrome" :
                        //safari也提供了专门的判定方式
                        window.openDatabase ? "Safari" :
                            (agent.indexOf("firefox") > 0) ? "Firefox" :
                                'unknow';

            //版本号
            System.version = (System.type === "IE") ? System.ieVersion() :
                (System.type === "Firefox") ? agent.match(/firefox\/([\d.]+)/)[1] :
                    (System.type === "Chrome") ? agent.match(/chrome\/([\d.]+)/)[1] :
                        (System.type === "Opera") ? System.operaVersion() :
                            (System.type === "Safari") ? agent.match(/version\/([\d.]+)/)[1] :
                                "0";

            //浏览器外壳
            System.shell = function () {

                if (agent.indexOf("edge") > 0) {
                    System.version = agent.match(/edge\/([\d.]+)/)[1] || System.version;
                    return "Edge浏览器";
                }
                //遨游浏览器
                if (agent.indexOf("maxthon") > 0) {
                    System.version = agent.match(/maxthon\/([\d.]+)/)[1] || System.version;
                    return "傲游浏览器";
                }
                //QQ浏览器
                if (agent.indexOf("qqbrowser") > 0) {
                    System.version = agent.match(/qqbrowser\/([\d.]+)/)[1] || System.version;
                    return "QQ浏览器";
                }

                //搜狗浏览器
                if (agent.indexOf("se 2.x") > 0) {
                    return '搜狗浏览器';
                }

                //Chrome:也可以使用window.chrome && window.chrome.webstore判断
                if (chrome && System.type !== "Opera") {
                    var external = window.external,
                        clientInfo = window.clientInformation,
                        //客户端语言:zh-cn,zh.360下面会返回undefined
                        clientLanguage = clientInfo.languages;

                    //猎豹浏览器:或者agent.indexOf("lbbrowser")>0
                    if (external && 'LiebaoGetVersion' in external) {
                        return '猎豹浏览器';
                    }
                    //百度浏览器
                    if (agent.indexOf("bidubrowser") > 0) {
                        System.version = agent.match(/bidubrowser\/([\d.]+)/)[1] ||
                            agent.match(/chrome\/([\d.]+)/)[1];
                        return "百度浏览器";
                    }
                    //360极速浏览器和360安全浏览器
                    if (System.supportSubTitle() && typeof clientLanguage === "undefined") {
                        //object.key()返回一个数组.包含可枚举属性和方法名称
                        let storeKeyLen = Object.keys(chrome.webstore).length,
                            v8Locale = "v8Locale" in window;
                        return storeKeyLen > 1 ? '360极速浏览器' : '360安全浏览器';
                    }
                    return "Chrome";
                }
                return System.type;
            };

            //浏览器名称(如果是壳浏览器,则返回壳名称)
            System.name = System.shell();
        } catch (e) {
            // console.log(e.message);
        }
        return {
            client: System
        };

    })(window);
    if (browser.client.name === undefined || browser.client.name === "") {
        browser.client.name = "Unknown";
        browser.client.version = "Unknown";
    } else if (browser.client.version === undefined) {
        browser.client.version = "Unknown";
    }
    return browser;
}

/**
 * 计时函数
 * @param ele
 * @param date 日期
 * @returns {string} 时间
 */
function countdown(ele, date) {
    let times = date.getTime();
    setInterval(function () {
        date = new Date(times);
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();

        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;
        hour = hour < 10 ? "0" + hour : hour;
        minute = minute < 10 ? "0" + minute : minute;
        second = second < 10 ? "0" + second : second;

        let dateStr = year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second;
        $("#" + ele).html(dateStr);

        times = times + 1000;
    }, 1000);
}

