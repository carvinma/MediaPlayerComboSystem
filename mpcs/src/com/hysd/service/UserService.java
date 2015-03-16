package com.hysd.service;

import java.util.Map;

import com.hysd.domain.User;
import com.hysd.domain.page.Page;

public interface UserService {
	
	public void save(User user);

	public void update(User user);

	public User findById(Long id);

	public void delete(User user);

	Page<User> list(Page<User> page, Map<String, String> map);

	public User findByLoginName(String loginname);
	/**判断是否已存在用户名*/
	public boolean hasCode(String loginname, Long id);
}
