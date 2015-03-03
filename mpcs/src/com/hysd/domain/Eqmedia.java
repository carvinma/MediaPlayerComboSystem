package com.hysd.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
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
	private Timestamp updateTime;

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

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}