package com.tcd.entity;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * 简单实体抽象类
 * @author 郑卫东
 *
 */
public abstract class SimpleEntity implements Serializable {
	
	protected Long logKey = null;
	private int index = 0;
	
	protected Long id = null;
	

	abstract public Long getId();

	abstract public void setId(Long id) ;

	
	@Transient
	public Long getLogKey() {
		return logKey;
	}

	public void setLogKey(Long logKey) {
		this.logKey = logKey;
	}

	public int nextIntex() {
		return ++index;
	}

	public boolean idIsNull() {
		if (id == null || id == 0) {
			return true;
		} else {
			return false;
		}
	}
}
