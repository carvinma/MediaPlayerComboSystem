package com.tcd.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.tcd.common.cache.CacheUtils;

/**
 * MD5加密工具了
 * @author 郑卫东
 *
 */
public class MD5Utils {
	
	public static void main(String args[]){
		//System.out.println(password("c4ca4238a0b923820dcc509a6f75849b"));
		System.out.println(password(""));
	}
	
	/**
	 * 得到缺省密码
	 * @return
	 */
	public  static String defaultPassword(){
		String pwd=CacheUtils.getValueByCode("SYS_BASE_PWD");
		return password(pwd);
	}
	
	public static String password(String str) {  
		if(str==null){
			return null;
		}
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
           return str;
        } catch (UnsupportedEncodingException e) {  
        	return str;
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }  
}
