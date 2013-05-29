package com.zqdy.dao.po;

import java.sql.Timestamp;

/**
 * TDocumentalSubId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalSubId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer source;
	private Integer dcId;
	private Integer dcSnatchId;
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
	public TDocumentalSubId() {
	}

	/** minimal constructor */
	public TDocumentalSubId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TDocumentalSubId(Integer id, String name, Integer source,
			Integer dcId, Integer dcSnatchId, String url, String embed,
			String shortSummary, String summary, String imageUrl,
			Integer volume, Integer payCount, Integer snatchState,
			Integer snatchCount, Timestamp updateTime, Timestamp addTime) {
		this.id = id;
		this.name = name;
		this.source = source;
		this.dcId = dcId;
		this.dcSnatchId = dcSnatchId;
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

	public Integer getDcId() {
		return this.dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	public Integer getDcSnatchId() {
		return this.dcSnatchId;
	}

	public void setDcSnatchId(Integer dcSnatchId) {
		this.dcSnatchId = dcSnatchId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TDocumentalSubId))
			return false;
		TDocumentalSubId castOther = (TDocumentalSubId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getSource() == castOther.getSource()) || (this
						.getSource() != null && castOther.getSource() != null && this
						.getSource().equals(castOther.getSource())))
				&& ((this.getDcId() == castOther.getDcId()) || (this.getDcId() != null
						&& castOther.getDcId() != null && this.getDcId()
						.equals(castOther.getDcId())))
				&& ((this.getDcSnatchId() == castOther.getDcSnatchId()) || (this
						.getDcSnatchId() != null
						&& castOther.getDcSnatchId() != null && this
						.getDcSnatchId().equals(castOther.getDcSnatchId())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getEmbed() == castOther.getEmbed()) || (this
						.getEmbed() != null && castOther.getEmbed() != null && this
						.getEmbed().equals(castOther.getEmbed())))
				&& ((this.getShortSummary() == castOther.getShortSummary()) || (this
						.getShortSummary() != null
						&& castOther.getShortSummary() != null && this
						.getShortSummary().equals(castOther.getShortSummary())))
				&& ((this.getSummary() == castOther.getSummary()) || (this
						.getSummary() != null && castOther.getSummary() != null && this
						.getSummary().equals(castOther.getSummary())))
				&& ((this.getImageUrl() == castOther.getImageUrl()) || (this
						.getImageUrl() != null
						&& castOther.getImageUrl() != null && this
						.getImageUrl().equals(castOther.getImageUrl())))
				&& ((this.getVolume() == castOther.getVolume()) || (this
						.getVolume() != null && castOther.getVolume() != null && this
						.getVolume().equals(castOther.getVolume())))
				&& ((this.getPayCount() == castOther.getPayCount()) || (this
						.getPayCount() != null
						&& castOther.getPayCount() != null && this
						.getPayCount().equals(castOther.getPayCount())))
				&& ((this.getSnatchState() == castOther.getSnatchState()) || (this
						.getSnatchState() != null
						&& castOther.getSnatchState() != null && this
						.getSnatchState().equals(castOther.getSnatchState())))
				&& ((this.getSnatchCount() == castOther.getSnatchCount()) || (this
						.getSnatchCount() != null
						&& castOther.getSnatchCount() != null && this
						.getSnatchCount().equals(castOther.getSnatchCount())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())))
				&& ((this.getAddTime() == castOther.getAddTime()) || (this
						.getAddTime() != null && castOther.getAddTime() != null && this
						.getAddTime().equals(castOther.getAddTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getSource() == null ? 0 : this.getSource().hashCode());
		result = 37 * result
				+ (getDcId() == null ? 0 : this.getDcId().hashCode());
		result = 37
				* result
				+ (getDcSnatchId() == null ? 0 : this.getDcSnatchId()
						.hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getEmbed() == null ? 0 : this.getEmbed().hashCode());
		result = 37
				* result
				+ (getShortSummary() == null ? 0 : this.getShortSummary()
						.hashCode());
		result = 37 * result
				+ (getSummary() == null ? 0 : this.getSummary().hashCode());
		result = 37 * result
				+ (getImageUrl() == null ? 0 : this.getImageUrl().hashCode());
		result = 37 * result
				+ (getVolume() == null ? 0 : this.getVolume().hashCode());
		result = 37 * result
				+ (getPayCount() == null ? 0 : this.getPayCount().hashCode());
		result = 37
				* result
				+ (getSnatchState() == null ? 0 : this.getSnatchState()
						.hashCode());
		result = 37
				* result
				+ (getSnatchCount() == null ? 0 : this.getSnatchCount()
						.hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		result = 37 * result
				+ (getAddTime() == null ? 0 : this.getAddTime().hashCode());
		return result;
	}

}