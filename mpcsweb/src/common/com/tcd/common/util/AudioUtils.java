package com.tcd.common.util;

import java.io.IOException;

/**
 * 语音工具类（不使用）
 * @author 郑卫东
 *
 */
public class AudioUtils {
	
	public static void main(String args[]){
		try {
			mp3towav();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public static void mp3towav() throws IOException{
//		JSpeexEnc s=new JSpeexEnc();
//		s.parseArgs(new String[]{"-n"});
//		
//		s.encode(new File("E:\\workspace\\callstar\\asterisk\\release\\asterisk\\lib\\1314094278610.wav"), new File("E:\\TDDOWNLOAD\\asterisk\\1.spx")); 
	}
}	
