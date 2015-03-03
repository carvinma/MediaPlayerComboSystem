package com.hysd.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSession implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private User user;
	private String now;
	private Map<String, List<?>> map;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, List<?>> getMap() {
		if (map == null) {
			map= new HashMap<String, List<?>>();
		}
		return map;
	}

	public void setMap(Map<String, List<?>> map) {
		this.map = map;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

}
