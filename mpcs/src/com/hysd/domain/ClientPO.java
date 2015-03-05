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
@Table(name = "tbclient")
public class ClientPO extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	private String ip;
	private String deviceHardwareVersion;
	private String deviceFirmwareVersion;
	private String updateTime;
	private String activeTime;
	private Long uploadByte;
	private Long downloadByte;
	private String SIM1_SSID;
	private String SIM1_MAC;
	private String SIM1_ICCID;
	private String SIM1_SIM_IMSI;
	private String SIM1_SIM_SerialNo;
	private String SIM2_SSID;
	private String SIM2_MAC;
	private String SIM2_ICCID;
	private String SIM2_SIM_IMSI;
	private String SIM2_SIM_SerialNo;
	private Long RunTime;

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceHardwareVersion() {
		return this.deviceHardwareVersion;
	}

	public void setDeviceHardwareVersion(String deviceHardwareVersion) {
		this.deviceHardwareVersion = deviceHardwareVersion;
	}

	public String getDeviceFirmwareVersion() {
		return this.deviceFirmwareVersion;
	}

	public void setDeviceFirmwareVersion(String deviceFirmwareVersion) {
		this.deviceFirmwareVersion = deviceFirmwareVersion;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getActiveTime() {
		return this.activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public Long getUploadByte() {
		return this.uploadByte;
	}

	public void setUploadByte(Long uploadByte) {
		this.uploadByte = uploadByte;
	}

	public Long getDownloadByte() {
		return this.downloadByte;
	}

	public void setDownloadByte(Long downloadByte) {
		this.downloadByte = downloadByte;
	}

	public String getSIM1_SSID() {
		return this.SIM1_SSID;
	}

	public void setSIM1_SSID(String SIM1_SSID) {
		this.SIM1_SSID = SIM1_SSID;
	}

	public String getSIM1_MAC() {
		return this.SIM1_MAC;
	}

	public void setSIM1_MAC(String SIM1_MAC) {
		this.SIM1_MAC = SIM1_MAC;
	}

	public String getSIM1_ICCID() {
		return this.SIM1_ICCID;
	}

	public void setSIM1_ICCID(String SIM1_ICCID) {
		this.SIM1_ICCID = SIM1_ICCID;
	}

	public String getSIM1_SIM_IMSI() {
		return this.SIM1_SIM_IMSI;
	}

	public void setSIM1_SIM_IMSI(String SIM1_SIM_IMSI) {
		this.SIM1_SIM_IMSI = SIM1_SIM_IMSI;
	}

	public String getSIM1_SIM_SerialNo() {
		return this.SIM1_SIM_SerialNo;
	}

	public void setSIM1_SIM_SerialNo(String SIM1_SIM_SerialNo) {
		this.SIM1_SIM_SerialNo = SIM1_SIM_SerialNo;
	}

	public String getSIM2_SSID() {
		return this.SIM2_SSID;
	}

	public void setSIM2_SSID(String SIM2_SSID) {
		this.SIM2_SSID = SIM2_SSID;
	}

	public String getSIM2_MAC() {
		return this.SIM2_MAC;
	}

	public void setSIM2_MAC(String SIM2_MAC) {
		this.SIM2_MAC = SIM2_MAC;
	}

	public String getSIM2_ICCID() {
		return this.SIM2_ICCID;
	}

	public void setSIM2_ICCID(String SIM2_ICCID) {
		this.SIM2_ICCID = SIM2_ICCID;
	}

	public String getSIM2_SIM_IMSI() {
		return this.SIM2_SIM_IMSI;
	}

	public void setSIM2_SIM_IMSI(String SIM2_SIM_IMSI) {
		this.SIM2_SIM_IMSI = SIM2_SIM_IMSI;
	}

	public String getSIM2_SIM_SerialNo() {
		return this.SIM2_SIM_SerialNo;
	}

	public void setSIM2_SIM_SerialNo(String SIM2_SIM_SerialNo) {
		this.SIM2_SIM_SerialNo = SIM2_SIM_SerialNo;
	}

	public Long getRunTime() {
		return this.RunTime;
	}

	public void setRunTime(Long RunTime) {
		this.RunTime = RunTime;
	}

}