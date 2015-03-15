package com.hysd.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 此类由MySQLToBean工具自动生成
 * 
 * @author
 * @since 2015-03-03 19:05:57
 */

@Entity
@Table(name = "tbrole")
public class Role extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String roleName;
	private String roleMenu;
	private Integer isDelete;

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMenu() {
		return this.roleMenu;
	}

	public void setRoleMenu(String roleMenu) {
		this.roleMenu = roleMenu;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}