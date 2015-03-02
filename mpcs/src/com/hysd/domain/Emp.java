package com.hysd.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 雇员
 */
public class Emp implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer empno; // 雇员编号
	private String ename; // 雇员姓名
	private String job; // 工作
	 

	public Emp() {
	}

	public Emp(Integer empno, String ename, Date hiredate, Double sal) {
		this.empno = empno;
		this.ename = ename;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	 
  

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job
				+ "]";
	}

}
