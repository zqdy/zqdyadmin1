package com.zqdy.dao.po;

/**
 * TDocumentalAreaId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalAreaId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer areaId;
	private Integer dcId;

	// Constructors

	/** default constructor */
	public TDocumentalAreaId() {
	}

	/** full constructor */
	public TDocumentalAreaId(Integer id, Integer areaId, Integer dcId) {
		this.id = id;
		this.areaId = areaId;
		this.dcId = dcId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getDcId() {
		return this.dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TDocumentalAreaId))
			return false;
		TDocumentalAreaId castOther = (TDocumentalAreaId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getAreaId() == castOther.getAreaId()) || (this
						.getAreaId() != null && castOther.getAreaId() != null && this
						.getAreaId().equals(castOther.getAreaId())))
				&& ((this.getDcId() == castOther.getDcId()) || (this.getDcId() != null
						&& castOther.getDcId() != null && this.getDcId()
						.equals(castOther.getDcId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getAreaId() == null ? 0 : this.getAreaId().hashCode());
		result = 37 * result
				+ (getDcId() == null ? 0 : this.getDcId().hashCode());
		return result;
	}

}