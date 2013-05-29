package com.zqdy.dao.po;

import java.sql.Timestamp;

/**
 * TCartoonSub entity. @author MyEclipse Persistence Tools
 */

public class TCartoonSub implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer source;
	private Integer ctId;
	private Integer ctSnatchId;
	private String url;
	private String embed;
	private String shortSummary;
	private String summary;
	private String imageUrl;
	private Integer volume;
	private Integer payCount;
	private Integer snatchState;
	private Integer snatchCount;
	private Timestamp updateTime;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public TCartoonSub() {
	}

	/** full constructor */
	public TCartoonSub(String name, Integer source, Integer ctId,
			Integer ctSnatchId, String url, String embed, String shortSummary,
			String summary, String imageUrl, Integer volume, Integer payCount,
			Integer snatchState, Integer snatchCount, Timestamp updateTime,
			Timestamp addTime) {
		this.name = name;
		this.source = source;
		this.ctId = ctId;
		this.ctSnatchId = ctSnatchId;
		this.url = url;
		this.embed = embed;
		this.shortSummary = shortSummary;
		this.summary = summary;
		this.imageUrl = imageUrl;
		this.volume = volume;
		this.payCount = payCount;
		this.snatchState = snatchState;
		this.snatchCount = snatchCount;
		this.updateTime = updateTime;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getCtSnatchId() {
		return this.ctSnatchId;
	}

	public void setCtSnatchId(Integer ctSnatchId) {
		this.ctSnatchId = ctSnatchId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmbed() {
		return this.embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public String getShortSummary() {
		return this.shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getVolume() {
		return this.volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getPayCount() {
		return this.payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	public Integer getSnatchState() {
		return this.snatchState;
	}

	public void setSnatchState(Integer snatchState) {
		this.snatchState = snatchState;
	}

	public Integer getSnatchCount() {
		return this.snatchCount;
	}

	public void setSnatchCount(Integer snatchCount) {
		this.snatchCount = snatchCount;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}