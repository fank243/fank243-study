/**
 * 判断某个字符串是否以指定字符串结尾
 * @param endStr 指定字符串
 * @returns {boolean|boolean}
 */
String.prototype.endWith = function (endStr) {
    let index = this.length - endStr.length;
    return (index >= 0 && this.lastIndexOf(endStr) === index)
};

/**
 * 是否为空(不为空返回null)
 *
 * @return boolean
 */
String.prototype.isNotBlank = function () {
    let blank = /^\s*$/;
    return (!blank.test(this));
};
/**
 * 字符串长度是否符合要求
 *
 * @return
 */
String.prototype.isLess = function (minNum) {
    return (this.length < minNum);
};

String.prototype.isMore = function (maxNum) {
    return (this.length > maxNum);
};

String.prototype.isBetween = function (min, max) {
    return (this.isNotBlank() && (!this.isLess(min)) && (!this.isMore(max)));
};

/**
 * 是否有效的手机号码
 *
 * @returns
 */
String.prototype.isMobile = function () {
    return (new RegExp(/^1[3-9]\d{9}$/).test(this));
};

String.prototype.isPositInt = function () {
    let res = /^[1-9]+[0-9]*]*$/;
    let vl = this;
    return res.test(vl);
};

String.prototype.isNumLess = function (min) {
    if (!this.isNotBlank()) {
        return false;
    }
    let fl = parseFloat(this);
    return fl < min;

};

String.prototype.isNumMore = function (max) {
    if (!this.isNotBlank()) {
        return false;
    }
    let fl = parseFloat(this);
    return fl > max;

};

/** 字符串是否是属于该区间的数字【包含两个区间】 */
String.prototype.isNumBetween = function (min, max) {
    return (this.isNotBlank() && (!this.isNumLess(min)) && (!this.isNumMore(max)))
};

/** 一位小数或者非负整数 */
String.prototype.isFloat = function () {
    let res = new RegExp(/^(\d+\.\d|\d+)$/);
    return res.test(this);
};

/**
 * 是否为汉字
 * @returns
 */
String.prototype.isChinese = function () {
    return (new RegExp("[\\u4E00-\\u9FFF]+", "g").test(this));
};

/**
 * 是否有效的邮箱
 *
 * @returns
 */
String.prototype.isEmail = function () {
    return (
        new RegExp(/^([a-zA-Z0-9])+([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\.([a-zA-Z0-9_-]){2,3}){1,2})$/).test(this)
    );
};

/**
 * 是否是QQ邮箱
 */
String.prototype.isQQEmail = function () {
    return new RegExp(/^([\s\S]*@qq.com)$/).test(this);
};

String.prototype.isQQ = function () {
    return new RegExp(/^\d{6,10}$/).test(this);
};

//判断元素值是是否是日期类型
String.prototype.isDate = function () {
    return (new RegExp(
        /^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/ig).test(this));
};

//判断两个表单元素的值是否相等
$.fn.isEqual = function (another) {
    return ($(this).val() === another.val());
};

//正则范围：0到9的正整数,字母大小写。
$.fn.integerAndLetters = function () {
    let reg = new RegExp('^([0-9a-zA-Z]{0,20}).*');
    $(this).val($(this).val().replace(/[^0-9a-zA-Z]/g, '').replace(reg, '$1'));
};

//正则范围：0.0-99.9或者100，可输入一位小数
$.fn.proportionRange = function () {
    $(this).val($(this).val().replace(/[^\d\.]/g, '').replace(/^\.+/, '').replace(/^(100|\d{1,2}(\.\d{0,1})?).*/, '$1'));
};

//正则范围：0.0-9.9或者10，可输入一位小数，10的时候不可在输入
$.fn.discountRange = function () {
    $(this).val($(this).val().replace(/[^\d\.]/g, '').replace(/^\.+/, '').replace(/^(10|\d?(\.\d?)?).*/, '$1'));
};

//正则范围：1-12，正整数
$.fn.oneToTwelfth = function () {
    $(this).val($(this).val().replace(/[^\d]/g, '').replace(/(^12|11|10|(\d{0,1})).*/, '$1'));
};

//正则范围：0-10，正整数
$.fn.zeroToTen = function () {
    $(this).val($(this).val().replace(/[^\d]/g, '').replace(/(^10|(\d{0,1})).*/, '$1'));
};

//正则范围：0.00-9999999.99,可输入2位小数，7位整数位
$.fn.withdrawRange = function () {
    $(this).val($(this).val().replace(/[^\d.]/g, '').replace(/^\.+/, '').replace(/^(\d{1,7}(\.\d{0,2})?).*/, '$1'));
};

//正则范围：0.00-20位整数,可输入2位小数，
$.fn.maxRange = function () {
    $(this).val($(this).val().replace(/[^\d.]/g, '').replace(/^\.+/, '').replace(/^(\d{1,18}(\.\d{0,2})?).*/, '$1'));
};

//正则范围：0.00-99.99或者100, 可输入2位小数，100的时候不可以输入小数位
$.fn.percentage = function () {
    $(this).val($(this).val().replace(/[^\d.]/g, '').replace(/^\.+/, '').replace(/^(100|\d{1,2}(\.\d{0,2})?).*/, '$1'));
};

//正则范围：0到n位的最大值，正整数。n表示最大位数
$.fn.ndigitInteger = function (n) {
    let reg = new RegExp('^(\\d\{0,' + n + '\}).*');
    $(this).val($(this).val().replace(/[^\d]/g, '').replace(reg, '$1'));
};

$.fn.substr = function (len) {
    if ($(this).val().length > len) {
        $(this).val($(this).val().slice(0, len));
    }
};