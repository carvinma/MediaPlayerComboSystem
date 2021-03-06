package com.hysd.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 此类由MySQLToBean工具自动生成
 * 
 * @author
 * @since 2015-03-03 19:05:57
 */

@Entity
@Table(name = "tbcategory")
public class Category extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long superId;
	private String categoryCode;
	private String categoryName;
	private String categoryValue;
	private String categoryLevel;
	private Integer isDelete;
	@Transient
	private Category superCategory;

	public Long getSuperId() {
		return superId;
	}

	public void setSuperId(Long superId) {
		this.superId = superId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Category getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}

}