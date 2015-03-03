package com.hysd.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.hysd.domain.User;
import com.hysd.domain.UserSession;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		SessionAware {
	private static Logger log = Logger.getLogger(BaseAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private HttpServletRequest request;
	private UserSession userSession;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession(boolean flag) {
		return request.getSession(flag);
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	/**
	 * 得到登陆者信息
	 * 
	 * @return UserSession
	 */
	public UserSession getUserSession() {
		log.debug("START: getUserSession()");
		if (userSession == null) {
			log.debug("END: getUserSession()");
			return (UserSession) sessionMap.get("USERSESSION");
		}
		log.debug("END: getUserSession()");
		return userSession;
	}

	/**
	 * 得到登陆者ID
	 * 
	 * @return String
	 */
	public Long getUserId() {
		log.debug("START: getUserId()");
		if (getSession().get("USERSESSION") == null) {
			log.debug("END: getUserId()");
			return null;
		}
		log.debug("END: getUserId()");
		return ((UserSession) getSession().get("USERSESSION")).getUser()
				.getId();

	}

	/** 取得登陆者 */
	public User getLoginUser() {
		log.debug("START: getLoginUser()");
		if (getSession().get("USERSESSION") == null) {
			log.debug("END: getLoginUser()");
			return null;
		}
		log.debug("END: getLoginUser()");
		return ((UserSession) getSession().get("USERSESSION")).getUser();
	}

	/**
	 * 得到登陆者的名字
	 * 
	 * @return String
	 */
	public String getUserName() {
		log.debug("START: getUserName()");
		if (getSession().get("USERSESSION") == null) {
			log.debug("END: getUserName()");
			return "";
		}
		log.debug("END: getUserName()");
		return ((UserSession) getSession().get("USERSESSION")).getUser()
				.getUserName();
	}

	public boolean isLogin(Integer loginId) {
		log.debug("START: isLogin()");
		if (getSession().get("USERSESSION") != null
				&& getLoginUser().getId().equals(loginId)) {
			log.debug("END: isLogin()");
			return true;
		}
		log.debug("END: isLogin()");
		return false;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

	public Map<String, Object> getSession() {
		return this.sessionMap;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param cName
	 * @return
	 * @throws Exception
	 */
	public String getCookieValue(String cName) throws Exception {
		String cValue = null;
		Cookie cookie = this.getCookie(cName);
		if (cookie != null) {
			cValue = cookie.getValue();
		}
		return cValue;
	}

	/**
	 * 获取指定Cookie
	 * 
	 * @param cName
	 * @return
	 * @throws Exception
	 */
	public Cookie getCookie(String cName) throws Exception {
		Cookie cookie = null;
		Cookie[] cs = this.request.getCookies();
		if (cs != null) {
			for (Cookie c : cs) {
				String name = c.getName();
				if (cName.equals(name)) {
					cookie = c;
				}
			}
		}
		return cookie;
	}

	/**
	 * 将设置cookie独立出来便于对cookie的配置统一控制
	 * 
	 * @param sName
	 * @param sValue
	 */
	public void addACookie(String cName, String cValue) throws Exception {
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setMaxAge(30 * 24 * 60 * 60);
		this.getResponse().addCookie(cookie);
	}

	/**
	 * 将登录的用户信息存放到cookie中
	 * 
	 * @param member
	 */
	public void setCookie(UserSession userSession) throws Exception {
		this.addACookie("loginId", userSession.getUser().getId().toString());
		this.addACookie("loginName", userSession.getUser().getUserName());
		// this.addACookie("departmentId", userSession.getDepartment().getId());
		// this.addACookie("departmentName",
		// userSession.getDepartment().getName());
	}

	/**
	 * 删除所有Cookie
	 * 
	 * @throws Exception
	 */
	public void invalidateCookies() throws Exception {
		Cookie[] cs = this.getServletRequest().getCookies();
		if (cs != null) {
			for (Cookie c : cs) {
				c.setMaxAge(0);
				this.getResponse().addCookie(c);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> findSessionListByKey(String key) {
		return (List<Object>) getUserSession().getMap().get(key);
	}

}
