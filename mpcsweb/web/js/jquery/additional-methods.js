$.validator.addMethod("money", function(value, element) {
	var tel = /^[0-9]+(.[0-9]{1,2})?$/;
	return this.optional(element) || (tel.test(value));
}, "请正确的数值");

$.validator.addMethod("mobile", function(value, element) {
	var tel = /^((\([0-9]{3}\))|([0-9]{3}\-))?13[0-9]{9}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写手机号码");

$.validator
		.addMethod(
				"phone",
				function(value, element) {
					var tel = /^((\([0-9]{3}\))|([0-9]{3}\-))?(\(0[0-9]{2,3}\)|0[0-9]{2,3}-)?[1-9][0-9]{6,7}$/;
					return this.optional(element) || (tel.test(value));
				}, "请正确填写座机号码");

$.validator.addMethod("zmsz", function(value, element) {
	var tel = /^[A-Za-z0-9]+$/;
	return this.optional(element) || (tel.test(value));
}, "请输入字母或者数字");

$.validator.addMethod("zmsz16", function(value, element) {
	var tel = /^[A-Fa-f0-9]+$/;
	return this.optional(element) || (tel.test(value));
}, "请输入正确的十六位进制数");

$.validator
		.addMethod("requiredString",
				function(value, element) {
					return this.optional(element)
							|| ($.trim(value).length == 0) ? false : true;
				}, "必须填写");
/** 日期大于 */
$.validator.addMethod("greaterThen", function(value, element, param) {
	return this.optional(element) || value >= $(param).val();
}, '结束时间 小于 开始时间');

/** 日期小于 */
$.validator.addMethod("lessThen", function(value, element, param) {
	return this.optional(element) || value <= $(param).val();
}, '开始时间 大于 结束时间');

$.validator.addMethod("cpassword", function(value, element) {
	value = $.trim(value);
	var reg_word_lower = /[a-zA-Z]+/;
	var reg_number = /\d+/;

	return this.optional(element)
			|| (reg_word_lower.test(value) && reg_number.test(value));
}, "请使用字母和数字的组合");

$.validator.addMethod("notequalTo", function(value, element, param) {
	return this.optional(element) || value != $(param).val();
});
$.validator.addMethod("notequipOldPwd", function(value, element, param) {
	return this.optional(element) || value != $(param).val();
});

/**
 * 函数名： validateIP 函数功能： 验证IP的合法性 传入参数： what:点分十进制的IP(如：192.168.1.2) 返回值：
 * true:what为合法IP false: what为非法IP
 */
function validateIP(what) {
	if (what.search(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/) == -1)
		return false;
	var fs = 0, ls = 0;
	var myArray = what.split(/\./);
	var i;
	for (i = 0; i < 4; i++) {
		if (!isNumeric(myArray[i]))
			return false;

		var t = myArray[i]; /* 每个域值范围0-255 */
		if ((t < 0) || (t > 255))
			return false;
	}
	fs = myArray[0]; // 取第一位进行校验
	ls = myArray[3]; // 取最后一位进行校验

	/* 主机部分不能全是1和0（第一位不能为255和0），网络部分不能全是0（最后一位不能为0） */
	if ((fs == 255) || (fs == 0) || (ls == 0)) {
		return false;
	}
	return true;
}
/**
 * 函数名： isNumeric 函数功能： 检测是否是数字 传入参数： 传入strval参数 返回值： true:strval为数字
 * false:strval为非数字
 */
function isNumeric(strval) {
	var c;
	for ( var i = 0; i < strval.length; i++) {
		c = strval.charAt(i);
		if (c < "0" || c > "9") {
			return false;
		}
	}
	return true;
}
$.validator.addMethod("checkIP", function(value, element) {
	return this.optional(element) || validateIP(value);
}, "请输入正确的IP地址");
