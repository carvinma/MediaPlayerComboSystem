package com.tcd.common.utils.web;

import org.springframework.context.ApplicationContext;

/**
 * 已废，不使用
 * @author 郑卫东
 *
 */
public class ApplicationContextUtil {

	private static ApplicationContext ctx = null;

	public static void setApplicationContext2(ApplicationContext ctx){
		ApplicationContextUtil.ctx=ctx;
	}

	public static Object getBean2(String name) {
		return ctx.getBean(name);
	}

}
