package com.zqdy.dao.po;

import java.sql.Timestamp;

/**
 * TDocumentalSnatchId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalSnatchId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer source;
	private Integer dcId;
	private String mainUrl;
	private Integer dcLength;
	private Integer updateLength;
	private Integer snatchCount;
	private Integer snatchState;
	private Timestamp addTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TDocumentalSnatchId() {
	}

	/** minimal constructor */
	public TDocumentalSnatchId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TDocumentalSnatchId(Integer id, Integer source, Integer dcId,
			String mainUrl, Integer dcLength, Integer updateLength,
			Integer snatchCount, Integer snatchState, Timestamp addTime,
			Timestamp updateTime) {
		this.id = id;
		this.source = source;
		this.dcId = dcId;
		this.mainUrl = mainUrl;
		this.dcLength = dcLength;
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

	public Integer getDcId() {
		return this.dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	public String getMainUrl() {
		return this.mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public Integer getDcLength() {
		return this.dcLength;
	}

	public void setDcLength(Integer dcLength) {
		this.dcLength = dcLength;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TDocumentalSnatchId))
			return false;
		TDocumentalSnatchId castOther = (TDocumentalSnatchId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getSource() == castOther.getSource()) || (this
						.getSource() != null && castOther.getSource() != null && this
						.getSource().equals(castOther.getSource())))
				&& ((this.getDcId() == castOther.getDcId()) || (this.getDcId() != null
						&& castOther.getDcId() != null && this.getDcId()
						.equals(castOther.getDcId())))
				&& ((this.getMainUrl() == castOther.getMainUrl()) || (this
						.getMainUrl() != null && castOther.getMainUrl() != null && this
						.getMainUrl().equals(castOther.getMainUrl())))
				&& ((this.getDcLength() == castOther.getDcLength()) || (this
						.getDcLength() != null
						&& castOther.getDcLength() != null && this
						.getDcLength().equals(castOther.getDcLength())))
				&& ((this.getUpdateLength() == castOther.getUpdateLength()) || (this
						.getUpdateLength() != null
						&& castOther.getUpdateLength() != null && this
						.getUpdateLength().equals(castOther.getUpdateLength())))
				&& ((this.getSnatchCount() == castOther.getSnatchCount()) || (this
						.getSnatchCount() != null
						&& castOther.getSnatchCount() != null && this
						.getSnatchCount().equals(castOther.getSnatchCount())))
				&& ((this.getSnatchState() == castOther.getSnatchState()) || (this
						.getSnatchState() != null
						&& castOther.getSnatchState() != null && this
						.getSnatchState().equals(castOther.getSnatchState())))
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
				+ (getSource() == null ? 0 : this.getSource().hashCode());
		result = 37 * result
				+ (getDcId() == null ? 0 : this.getDcId().hashCode());
		result = 37 * result
				+ (getMainUrl() == null ? 0 : this.getMainUrl().hashCode());
		result = 37 * result
				+ (getDcLength() == null ? 0 : this.getDcLength().hashCode());
		result = 37
				* result
				+ (getUpdateLength() == null ? 0 : this.getUpdateLength()
						.hashCode());
		result = 37
				* result
				+ (getSnatchCount() == null ? 0 : this.getSnatchCount()
						.hashCode());
		result = 37
				* result
				+ (getSnatchState() == null ? 0 : this.getSnatchState()
						.hashCode());
		result = 37 * result
				+ (getAddTime() == null ? 0 : this.getAddTime().hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		return result;
	}

}