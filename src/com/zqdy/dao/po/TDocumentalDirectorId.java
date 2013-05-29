package com.zqdy.dao.po;

/**
 * TDocumentalDirectorId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalDirectorId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer directorId;
	private Integer dcId;

	// Constructors

	/** default constructor */
	public TDocumentalDirectorId() {
	}

	/** minimal constructor */
	public TDocumentalDirectorId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TDocumentalDirectorId(Integer id, Integer directorId, Integer dcId) {
		this.id = id;
		this.directorId = directorId;
		this.dcId = dcId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDirectorId() {
		return this.directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
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
		if (!(other instanceof TDocumentalDirectorId))
			return false;
		TDocumentalDirectorId castOther = (TDocumentalDirectorId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getDirectorId() == castOther.getDirectorId()) || (this
						.getDirectorId() != null
						&& castOther.getDirectorId() != null && this
						.getDirectorId().equals(castOther.getDirectorId())))
				&& ((this.getDcId() == castOther.getDcId()) || (this.getDcId() != null
						&& castOther.getDcId() != null && this.getDcId()
						.equals(castOther.getDcId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getDirectorId() == null ? 0 : this.getDirectorId()
						.hashCode());
		result = 37 * result
				+ (getDcId() == null ? 0 : this.getDcId().hashCode());
		return result;
	}

}