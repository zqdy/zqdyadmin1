package com.zqdy.dao.po;

import java.sql.Timestamp;

/**
 * TCartoonSnatch entity. @author MyEclipse Persistence Tools
 */

public class TCartoonSnatch implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer source;
	private Integer ctId;
	private String mainUrl;
	private Integer ctLength;
	private Integer updateLength;
	private Integer snatchCount;
	private Integer snatchState;
	private Timestamp addTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TCartoonSnatch() {
	}

	/** full constructor */
	public TCartoonSnatch(Integer source, Integer ctId, String mainUrl,
			Integer ctLength, Integer updateLength, Integer snatchCount,
			Integer snatchState, Timestamp addTime, Timestamp updateTime) {
		this.source = source;
		this.ctId = ctId;
		this.mainUrl = mainUrl;
		this.ctLength = ctLength;
		this.updateLength = updateLength;
		this.snatchCount = snatchCount;
		this.snatchState = snatchState;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public String getMainUrl() {
		return this.mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public Integer getCtLength() {
		return this.ctLength;
	}

	public void setCtLength(Integer ctLength) {
		this.ctLength = ctLength;
	}

	public Integer getUpdateLength() {
		return this.updateLength;
	}

	public void setUpdateLength(Integer updateLength) {
		this.updateLength = updateLength;
	}

	public Integer getSnatchCount() {
		return this.snatchCount;
	}

	public void setSnatchCount(Integer snatchCount) {
		this.snatchCount = snatchCount;
	}

	public Integer getSnatchState() {
		return this.snatchState;
	}

	public void setSnatchState(Integer snatchState) {
		this.snatchState = snatchState;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}