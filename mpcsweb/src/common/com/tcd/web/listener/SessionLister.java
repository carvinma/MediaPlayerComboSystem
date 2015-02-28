package com.tcd.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcd.applog.service.AppLoginManager;
import com.tcd.common.cache.CacheUtils;
import com.tcd.common.utils.spring.SpringContextHolder;

/**
 * Session监听器
 * 
 * @author 郑卫东
 * 
 */
public class SessionLister implements HttpSessionListener {

	private static final Log log = LogFactory.getLog(SessionLister.class);

	/**
	 * 创建sesssion
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		log.debug("create news session sessionid=" + session.getId());
	}

	/*
	 * 当Session 失失效时自动调用
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		// log.debug("destroyed session sessionid="+session.getId());
		// String
		// userId=(String)session.getAttribute(CacheUtils.getValueByCode("SYS_SESSION_USERID"));
		// if(userId!=null){

		// 删除在线会话
		String loginname=(String)session.getAttribute("SYS_SESSION_LOGINNAME");
		AppLoginManager appLoginManager = SpringContextHolder.getBean(AppLoginManager.class);
		appLoginManager.logout((String) session.getAttribute("SYS_SESSION_LOGINNAME"),CacheUtils.loginnames.get(loginname));

		// }
		CacheUtils.removeUser(loginname);

		//
	}

}
