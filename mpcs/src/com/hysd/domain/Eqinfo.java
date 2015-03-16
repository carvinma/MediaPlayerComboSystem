package com.hysd.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 此类由MySQLToBean工具自动生成
 * 
 * @author
 * @since 2015-03-03 19:05:57
 */

@Entity
@Table(name = "tbeqinfo")
public class Eqinfo extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sn;
	private String area;
	private String group;
	private String state;
	private Long clientId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "sn", name = "sn", insertable = false, updatable = false)
	private List<Eqmonitorinfo> list;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public List<Eqmonitorinfo> getList() {
		return list;
	}

	public void setList(List<Eqmonitorinfo> list) {
		this.list = list;
	}

	 

}