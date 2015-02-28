package com.tcd.common.orm;

import org.hibernate.FetchMode;

/**
 * 抓取策略
 * 
 * @author zhengwd
 *
 */
public class FetchModeParam {
	private String name;
	private FetchMode fetchMode;

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FetchMode getFetchMode() {
		return fetchMode;
	}
	public void setFetchMode(FetchMode fetchMode) {
		this.fetchMode = fetchMode;
	}
	
	
	
}
