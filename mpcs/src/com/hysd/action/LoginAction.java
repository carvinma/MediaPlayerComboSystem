package com.hysd.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.User;
import com.hysd.domain.UserSession;
import com.hysd.service.UserService;

@Controller
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginAction.class);
	@Resource
	private UserService userService;

	private String loginname;
	private String password;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		log.debug("START: LoginAction-login()");
		if (StringUtils.isEmpty(loginname) || StringUtils.isEmpty(password)) {
			this.addActionError("请输入用户名和密码!");
			log.debug("END  : LoginAction-login()--请输入用户名和密码");
			return ERROR;
		}
		User user = userService.findByLoginName(loginname);
		if (user == null || !user.getPassword().equals(password)) {
			this.addActionError("用户名或密码错误!");
			log.debug("END  : LoginAction-login()--用户名或密码错误");
			return ERROR;
		}

		// 加入session
		UserSession uSession = new UserSession();
		uSession.setUser(user);
		setUserSession(uSession);
		uSession.setNow((new Date()).getTime() + "");
		// 设置session里的内容
		setUserSession(uSession);
		// 4.设置Cookie
		// setCookie(uSession);
		getSession().put("USERSESSION", uSession);
		log.debug("END  : LoginAction-login()");
		return SUCCESS;

	}

}