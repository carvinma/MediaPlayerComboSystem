package com.hysd.domain;


public class EqmonitorinfoPO {
	private Long id;
	private String sn;
	private String ip;
	private String mac;
	private String phone;
	private String imei;
	private String updateDate;
	private String updateByte;
	private String downloadByte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateByte() {
		return updateByte;
	}

	public void setUpdateByte(String updateByte) {
		this.updateByte = updateByte;
	}

	public String getDownloadByte() {
		return downloadByte;
	}

	public void setDownloadByte(String downloadByte) {
		this.downloadByte = downloadByte;
	}

}