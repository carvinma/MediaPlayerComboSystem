package com.hysd.domain;

public class EqmonitorinfoPO {
	private Long id;
	private String sn;
	private String dateYear;
	private String dateWeek;
	private String a;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDateYear() {
		return this.dateYear;
	}

	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}

	public String getDateWeek() {
		return this.dateWeek;
	}

	public void setDateWeek(String dateWeek) {
		this.dateWeek = dateWeek;
	}

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

}