package com.hysd.domain;

public class MediaPO {
	private String id;
	private String mediaName;
	private String url;
	private String mediaArea;
	private String mediaGroup;
	private String updateTime;
	private String description;
	private String mediaType;
	private String mediaSize;
	private Integer isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMediaName() {
		return mediaName;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaSize() {
		return mediaSize;
	}

	public void setMediaSize(String mediaSize) {
		this.mediaSize = mediaSize;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
