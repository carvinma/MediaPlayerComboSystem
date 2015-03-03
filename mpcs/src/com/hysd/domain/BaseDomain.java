package com.hysd.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseDomain {
	@Id
	// 表示主键
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	// 自增长
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
