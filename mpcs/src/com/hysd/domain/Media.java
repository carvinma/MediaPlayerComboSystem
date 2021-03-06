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
@Table(name = "tbmedia")
public class Media extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mediaName;
	private String url;
	private String mediaArea;
	private String mediaGroup;
	private Date updateTime;
	private String description;
	private String mediaType;
	private String mediaSize;
	private Integer isDelete;

	public String getMediaName() {
		return this.mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaArea() {
		return mediaArea;
	}

	public void setMediaArea(String mediaArea) {
		this.mediaArea = mediaArea;
	}

	public String getMediaGroup() {
		return mediaGroup;
	}

	public void setMediaGroup(String mediaGroup) {
		this.mediaGroup = mediaGroup;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaSize() {
		return this.mediaSize;
	}

	public void setMediaSize(String mediaSize) {
		this.mediaSize = mediaSize;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}