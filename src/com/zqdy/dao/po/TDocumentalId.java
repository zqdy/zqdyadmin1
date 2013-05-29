package com.zqdy.dao.po;

import java.sql.Timestamp;

/**
 * TDocumentalId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalId implements java.io.Serializable {

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
	public TDocumentalId() {
	}

	/** minimal constructor */
	public TDocumentalId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TDocumentalId(Integer id, String name, String imageUrl, String year,
			Float rateScore, Integer weight, String shortSummary,
			String summary, Integer status, Integer length,
			Integer updateLength, Integer playCount, Integer commentsCount,
			Integer doubanId, Timestamp addTime, Timestamp updateTime) {
		this.id = id;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TDocumentalId))
			return false;
		TDocumentalId castOther = (TDocumentalId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getImageUrl() == castOther.getImageUrl()) || (this
						.getImageUrl() != null
						&& castOther.getImageUrl() != null && this
						.getImageUrl().equals(castOther.getImageUrl())))
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())))
				&& ((this.getRateScore() == castOther.getRateScore()) || (this
						.getRateScore() != null
						&& castOther.getRateScore() != null && this
						.getRateScore().equals(castOther.getRateScore())))
				&& ((this.getWeight() == castOther.getWeight()) || (this
						.getWeight() != null && castOther.getWeight() != null && this
						.getWeight().equals(castOther.getWeight())))
				&& ((this.getShortSummary() == castOther.getShortSummary()) || (this
						.getShortSummary() != null
						&& castOther.getShortSummary() != null && this
						.getShortSummary().equals(castOther.getShortSummary())))
				&& ((this.getSummary() == castOther.getSummary()) || (this
						.getSummary() != null && castOther.getSummary() != null && this
						.getSummary().equals(castOther.getSummary())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getLength() == castOther.getLength()) || (this
						.getLength() != null && castOther.getLength() != null && this
						.getLength().equals(castOther.getLength())))
				&& ((this.getUpdateLength() == castOther.getUpdateLength()) || (this
						.getUpdateLength() != null
						&& castOther.getUpdateLength() != null && this
						.getUpdateLength().equals(castOther.getUpdateLength())))
				&& ((this.getPlayCount() == castOther.getPlayCount()) || (this
						.getPlayCount() != null
						&& castOther.getPlayCount() != null && this
						.getPlayCount().equals(castOther.getPlayCount())))
				&& ((this.getCommentsCount() == castOther.getCommentsCount()) || (this
						.getCommentsCount() != null
						&& castOther.getCommentsCount() != null && this
						.getCommentsCount()
						.equals(castOther.getCommentsCount())))
				&& ((this.getDoubanId() == castOther.getDoubanId()) || (this
						.getDoubanId() != null
						&& castOther.getDoubanId() != null && this
						.getDoubanId().equals(castOther.getDoubanId())))
				&& ((this.getAddTime() == castOther.getAddTime()) || (this
						.getAddTime() != null && castOther.getAddTime() != null && this
						.getAddTime().equals(castOther.getAddTime())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getImageUrl() == null ? 0 : this.getImageUrl().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		result = 37 * result
				+ (getRateScore() == null ? 0 : this.getRateScore().hashCode());
		result = 37 * result
				+ (getWeight() == null ? 0 : this.getWeight().hashCode());
		result = 37
				* result
				+ (getShortSummary() == null ? 0 : this.getShortSummary()
						.hashCode());
		result = 37 * result
				+ (getSummary() == null ? 0 : this.getSummary().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getLength() == null ? 0 : this.getLength().hashCode());
		result = 37
				* result
				+ (getUpdateLength() == null ? 0 : this.getUpdateLength()
						.hashCode());
		result = 37 * result
				+ (getPlayCount() == null ? 0 : this.getPlayCount().hashCode());
		result = 37
				* result
				+ (getCommentsCount() == null ? 0 : this.getCommentsCount()
						.hashCode());
		result = 37 * result
				+ (getDoubanId() == null ? 0 : this.getDoubanId().hashCode());
		result = 37 * result
				+ (getAddTime() == null ? 0 : this.getAddTime().hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		return result;
	}

}