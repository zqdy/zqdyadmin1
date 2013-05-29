package com.zqdy.dao.po;

/**
 * TDocumentalGenreId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalGenreId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer genreId;
	private Integer dcId;

	// Constructors

	/** default constructor */
	public TDocumentalGenreId() {
	}

	/** full constructor */
	public TDocumentalGenreId(Integer id, Integer genreId, Integer dcId) {
		this.id = id;
		this.genreId = genreId;
		this.dcId = dcId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGenreId() {
		return this.genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
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
		if (!(other instanceof TDocumentalGenreId))
			return false;
		TDocumentalGenreId castOther = (TDocumentalGenreId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getGenreId() == castOther.getGenreId()) || (this
						.getGenreId() != null && castOther.getGenreId() != null && this
						.getGenreId().equals(castOther.getGenreId())))
				&& ((this.getDcId() == castOther.getDcId()) || (this.getDcId() != null
						&& castOther.getDcId() != null && this.getDcId()
						.equals(castOther.getDcId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getGenreId() == null ? 0 : this.getGenreId().hashCode());
		result = 37 * result
				+ (getDcId() == null ? 0 : this.getDcId().hashCode());
		return result;
	}

}