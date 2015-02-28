package com.tcd.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 语音工具类
 * @author 郑卫东
 *
 */
public class MediaUtils {
	
	private static Log logger = LogFactory.getLog(MediaUtils.class); 

	/**
	 * 转成WAV格式
	 * @param input
	 * @param output
	 * @return
	 */
	public static boolean toWav(String input, String output) {
		try {
			String[] commands = new String[] { "/bin/bash", "-c","/usr/local/bin/sox "+ input+" -q -t wav -r 8000 -b 16 -s -c 1 "+ output + 
					" >/dev/null 2>&1" };

			Process ps = Runtime.getRuntime().exec(commands);
			logger.debug("getInputStream="
					+ IOUtils.toString(ps.getInputStream()));
			logger.debug("getErrorStream="
					+ IOUtils.toString(ps.getErrorStream()));
			
			File a=new File(output);
			return a.exists();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
}
