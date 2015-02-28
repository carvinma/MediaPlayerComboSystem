package com.tcd.applog.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.tcd.applog.format.DefaultFormat;

/**
 * 操作日志 annotation
 * 
 * @author 郑卫东
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AppLog {

	String name();

	String dataParentCode() default "";

	@SuppressWarnings("rawtypes")
	Class tableclass() default Object.class;

	String showpropertie() default "";

	@SuppressWarnings("rawtypes")
	Class collectionClass() default Object.class;

	boolean isLog() default true;

	@SuppressWarnings("rawtypes")
	Class format() default DefaultFormat.class;

}
