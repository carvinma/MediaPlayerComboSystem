package com.tcd.web.tag;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.tcd.user.entity.Functions;

/**
 * 页面菜单控制tag
 * @author 郑卫东
 *
 */
public class MenuTag  extends BodyTagSupport {

	private static final long serialVersionUID = -837577037091202491L;
	
	private String id;
	private String role;
	
//	private boolean isHasMenusIdByUser(String menuid,HttpSession session){
//		Boolean b = false;
//		List<Menu> list = (List<Menu>)session.getAttribute("userpurview");
//		if(list.size() > 0) {
//			for(Menu menu:list) {
//				if(menuid.equals(menu.getId().toString())) {
//					b = true;
//				}
//			}
//		}
//		return b;
//	}
	
	@SuppressWarnings("unchecked")
	private boolean isHasRole(String funid,HttpSession session){
		Boolean b = true;
//		List<Functions> list = (List<Functions>)session.getAttribute("userpurview");
//		if(list.size() > 0) {
//			for(Functions fun:list) {
//				if(funid.equals(fun.getId().toString())) {
//					b = true;
//				}
//			}
//		}
		return b;
	}
	/**
	 * SKIP_BODY隐含0    ：跳过了开始和结束标签之间的代码。
	 * EVAL_BODY_INCLUDE隐含1：将body的内容输出到存在的输出流中
	 */
	public int doStartTag(){
		HttpSession session = pageContext.getSession();
		
		if(isHasRole(id,session)){
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
