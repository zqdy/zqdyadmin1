package com.zqdy.dao.po;

import java.sql.Timestamp;

/**
 * TZongyi entity. @author MyEclipse Persistence Tools
 */

public class TZongyi implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String imageUrl;
	private String year;
	private Float rateScore;
	private Integer weight;
	private String shortSummary;
	private String summary;
	private Integer status;
	private Integer length;
	private Integer updateLength;
	private Integer playCount;
	private Integer commentsCount;
	private Integer doubanId;
	private Timestamp addTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TZongyi() {
	}

	/** full constructor */
	public TZongyi(String name, String imageUrl, String year, Float rateScore,
			Integer weight, String shortSummary, String summary,
			Integer status, Integer length, Integer updateLength,
			Integer playCount, Integer commentsCount, Integer doubanId,
			Timestamp addTime, Timestamp updateTime) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.year = year;
		this.rateScore = rateScore;
		this.weight = weight;
		this.shortSummary = shortSummary;
		this.summary = summary;
		this.status = status;
		this.length = length;
		this.updateLength = updateLength;
		this.playCount = playCount;
		this.commentsCount = commentsCount;
		this.doubanId = doubanId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Float getRateScore() {
		return this.rateScore;
	}

	public void setRateScore(Float rateScore) {
		this.rateScore = rateScore;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getUpdateLength() {
		return this.updateLength;
	}

	public void setUpdateLength(Integer updateLength) {
		this.updateLength = updateLength;
	}

	public Integer getPlayCount() {
		return this.playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getCommentsCount() {
		return this.commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Integer getDoubanId() {
		return this.doubanId;
	}

	public void setDoubanId(Integer doubanId) {
		this.doubanId = doubanId;
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