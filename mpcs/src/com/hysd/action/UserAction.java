package com.hysd.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.User;
import com.hysd.domain.page.Page;
import com.hysd.service.UserService;
import com.hysd.util.Struts2Utils;

@Controller
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserAction.class);
	private Page<User> page = new Page<User>();
	private Map<String, Object> param;
	private String message;
	private User user;
	private Long id;
	@Resource
	private UserService userService;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<User> getPage() {
		return page;
	}

	public void setPage(Page<User> page) {
		this.page = page;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String save() {
		log.debug("START: UserAction-save()");
		userService.save(user);
		message = "ok";
		log.debug("END  : UserAction-save()");
		return "tolist";
	}

	public String edit() {
		log.debug("START: UserAction-edit()");
		userService.save(user);
		message = "ok";
		log.debug("END  : UserAction-edit()");
		return "edit";
	}

	public String load() {
		log.debug("START: UserAction-load()");
		user = userService.findById(id);
		log.debug("END  : UserAction-load()");
		return "load";
	}

	public String list() {
		log.debug("START: UserAction-list()");
		// 获取页面的参数
		page = userService.list(page, param);
		log.debug("END  : UserAction-list()");
		return "list";
	}

	/** 修改密码 */
	public String updatePwd() {
		log.debug("START: UserAction-updatePwd()");
		user = userService.findById(getUserId());
		String oldPwd = user.getPassword();
		HttpServletRequest request = getServletRequest();

		String oldpassword = request.getParameter("oldpassword");

		if (!oldpassword.equals(oldPwd)) {
			message = "error";
			return "updatePwd";
		}
		String newpassword = request.getParameter("newpassword");
		user.setPassword(newpassword);
		userService.save(user);
		message = "ok";
		log.debug("END  : UserAction-updatePwd()");
		return "updatePwd";
	}

	public String checkName() {
		HttpServletRequest request = getServletRequest();
		String userName = request.getParameter("userName");
		Long id = Long.valueOf(request.getParameter("id"));

		if (userService.hasCode(userName, id)) {// 存在code返回false
			Struts2Utils.renderText("false");
		} else {
			Struts2Utils.renderText("true");
		}
		return null;
	}

}
