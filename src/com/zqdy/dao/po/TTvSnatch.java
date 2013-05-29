package com.zqdy.dao.po;

import java.util.Date;
import java.util.List;

/**
 * TTvSnatch entity. @author MyEclipse Persistence Tools
 */

public class TTvSnatch implements java.io.Serializable {

	// Fields

	private Integer id;
	private TSource source;
	private TTv tv;
	private String mainUrl;
	private Integer tvLength;
	private Integer updateLength;
	private Integer snatchCount;
	private Integer snatchState;
	private Date addTime;
	private Date updateTime;

	private List subTvList;

	// Constructors

	/** default constructor */
	public TTvSnatch() {
	}
	public TTvSnatch(Integer id) {
		this.id=id;
	}

	/** full constructor */
	public TTvSnatch(TSource source, TTv tv, String mainUrl,
			Integer tvLength, Integer updateLength, Integer snatchCount,
			Integer snatchState, Date addTime, Date updateTime) {
		this.source = source;
		this.tv = tv;
		this.mainUrl = mainUrl;
		this.tvLength = tvLength;
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

	public TSource getSource() {
		return this.source;
	}

	public void setSource(TSource source) {
		this.source = source;
	}

	 

	public String getMainUrl() {
		return this.mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public Integer getTvLength() {
		return this.tvLength;
	}

	public void setTvLength(Integer tvLength) {
		this.tvLength = tvLength;
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

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public TTv getTv() {
		return tv;
	}

	public void setTv(TTv tv) {
		this.tv = tv;
	}

	public List getSubTvList() {
		return subTvList;
	}

	public void setSubTvList(List subTvList) {
		this.subTvList = subTvList;
	}

}