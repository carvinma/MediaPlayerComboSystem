package com.hysd.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.hysd.domain.User;
import com.hysd.domain.page.Page;
import com.hysd.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(BaseAction.class);
	private Page<User> page = new Page<User>();
	private String message;
	private User user;
	private Long id;
	@Resource
	private UserService userService;

	public String save() {
		log.debug("START: UserAction-save()");
		userService.save(user);
		message = "ok";
		log.debug("END  : UserAction-save()");
		return "save";
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
		Map<String, Object> param = new HashMap<String, Object>();
		page = userService.list(page, param);
		log.debug("END  : UserAction-list()");
		return "list";
	}

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

}
