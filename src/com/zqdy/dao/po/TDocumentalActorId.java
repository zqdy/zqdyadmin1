package com.zqdy.dao.po;

/**
 * TDocumentalActorId entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalActorId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer actorId;
	private Integer dcId;
	private Integer type;
	private Integer ind;

	// Constructors

	/** default constructor */
	public TDocumentalActorId() {
	}

	/** minimal constructor */
	public TDocumentalActorId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TDocumentalActorId(Integer id, Integer actorId, Integer dcId,
			Integer type, Integer ind) {
		this.id = id;
		this.actorId = actorId;
		this.dcId = dcId;
		this.type = type;
		this.ind = ind;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActorId() {
		return this.actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public Integer getDcId() {
		return this.dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getInd() {
		return this.ind;
	}

	public void setInd(Integer ind) {
		this.ind = ind;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TDocumentalActorId))
			return false;
		TDocumentalActorId castOther = (TDocumentalActorId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getActorId() == castOther.getActorId()) || (this
						.getActorId() != null && castOther.getActorId() != null && this
						.getActorId().equals(castOther.getActorId())))
				&& ((this.getDcId() == castOther.getDcId()) || (this.getDcId() != null
						&& castOther.getDcId() != null && this.getDcId()
						.equals(castOther.getDcId())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getInd() == castOther.getInd()) || (this.getInd() != null
						&& castOther.getInd() != null && this.getInd().equals(
						castOther.getInd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getActorId() == null ? 0 : this.getActorId().hashCode());
		result = 37 * result
				+ (getDcId() == null ? 0 : this.getDcId().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getInd() == null ? 0 : this.getInd().hashCode());
		return result;
	}

}