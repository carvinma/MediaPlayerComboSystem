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
@Table(name = "tbeqmonitorinfo")
public class Eqmonitorinfo extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sn;
	private Integer dateYear;
	private Integer dateWeek;
	private String a;

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getDateYear() {
		return this.dateYear;
	}

	public void setDateYear(Integer dateYear) {
		this.dateYear = dateYear;
	}

	public Integer getDateWeek() {
		return this.dateWeek;
	}

	public void setDateWeek(Integer dateWeek) {
		this.dateWeek = dateWeek;
	}

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

}