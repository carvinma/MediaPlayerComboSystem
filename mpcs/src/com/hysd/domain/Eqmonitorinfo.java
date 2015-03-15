package com.hysd.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
	*此类由MySQLToBean工具自动生成

	*@author 
	*@since 2015-03-15 12:33:30
	*/

@Entity
@Table(name = "tbeqmonitorinfo")
public class Eqmonitorinfo  extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sn;
	private Integer ip;
	private Integer mac;
	private String phone;
	private String imei;
	private Date updateDate;
	private Long updateByte;
	private Long downloadByte;
	public String getSn(){
		return this.sn;
	}
	public void setSn(String sn){
		this.sn=sn;
	}
	public Integer getIp(){
		return this.ip;
	}
	public void setIp(Integer ip){
		this.ip=ip;
	}
	public Integer getMac(){
		return this.mac;
	}
	public void setMac(Integer mac){
		this.mac=mac;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getImei(){
		return this.imei;
	}
	public void setImei(String imei){
		this.imei=imei;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateByte() {
		return updateByte;
	}

	public void setUpdateByte(Long updateByte) {
		this.updateByte = updateByte;
	}
	public Long getDownloadByte() {
		return downloadByte;
	}
	public void setDownloadByte(Long downloadByte) {
		this.downloadByte = downloadByte;
	}
	 

}