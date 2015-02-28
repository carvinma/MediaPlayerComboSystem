package com.tcd.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java验证
 * @author 郑卫东
 *
 */
public class JavaValidate {

	public static boolean isPhone(String num){
		String check = "\\d{5,20}";
		 Pattern regex = Pattern.compile(check);
		 Matcher matcher = regex.matcher( num );
		 return matcher.matches();

	}
	
	public static void main(String args[]){
		System.out.println(isPhone(null));
	}
}
