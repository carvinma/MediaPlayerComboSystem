package com.tcd.web.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.tcd.common.cache.CacheUtils;


/**
 * 页面菜单控制tag
 * @author 郑卫东
 *
 */
public class CallMenuTag extends BodyTagSupport {

	private static final long serialVersionUID = -1150099486838783637L;
	public String code;
	
	/**
	 * SKIP_BODY隐含0    ：跳过了开始和结束标签之间的代码。
	 * EVAL_BODY_INCLUDE隐含1：将body的内容输出到存在的输出流中
	 */
	public int doStartTag(){

		return EVAL_BODY_INCLUDE;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
