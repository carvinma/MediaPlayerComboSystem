package com.hysd.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 此类由MySQLToBean工具自动生成
 * 
 * @author
 * @since 2015-03-03 19:05:57
 */

@Entity
@Table(name = "tbversion")
public class Version extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean category;
	private String url;
	private Date updateTime;
	private String version;

	public boolean category() {
		return this.category;
	}

	public void category(boolean category) {
		this.category = category;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}