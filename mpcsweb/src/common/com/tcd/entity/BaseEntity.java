package com.tcd.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.tcd.applog.annotation.AppLog;

/**
 * 实体基类 默认定义ID 为 Long 型，ID 生成策略为 SEQUENCE
 * 
 * @author zhengwd
 */
@MappedSuperclass  
public abstract class BaseEntity extends SimpleEntity {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
	@AppLog(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
