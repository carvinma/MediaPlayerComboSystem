package com.hysd.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 此类由MySQLToBean工具自动生成
 * 
 * @author
 * @since 2015-03-03 19:05:57
 */

@Entity
@Table(name = "tbeqmedia")
public class Eqmedia extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long eqId;
	private Long mediaId;
	private Integer canDownLoad;
	private Integer canPlay;
	private Date updateTime;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "eqId", insertable = false, updatable = false)
	private Eqinfo eqinfo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "mediaId", insertable = false, updatable = false)
	private Media media;

	public Long getEqId() {
		return this.eqId;
	}

	public void setEqId(Long eqId) {
		this.eqId = eqId;
	}

	public Long getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getCanDownLoad() {
		return this.canDownLoad;
	}

	public void setCanDownLoad(Integer canDownLoad) {
		this.canDownLoad = canDownLoad;
	}

	public Integer getCanPlay() {
		return this.canPlay;
	}

	public void setCanPlay(Integer canPlay) {
		this.canPlay = canPlay;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Eqinfo getEqinfo() {
		return eqinfo;
	}

	public void setEqinfo(Eqinfo eqinfo) {
		this.eqinfo = eqinfo;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}