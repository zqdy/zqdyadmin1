package com.zqdy.dao.po;

/**
 * TZongyiActor entity. @author MyEclipse Persistence Tools
 */

public class TZongyiActor implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer actorId;
	private Integer zyId;
	private Integer type;
	private Integer ind;

	// Constructors

	/** default constructor */
	public TZongyiActor() {
	}

	/** full constructor */
	public TZongyiActor(Integer actorId, Integer zyId, Integer type, Integer ind) {
		this.actorId = actorId;
		this.zyId = zyId;
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

	public Integer getZyId() {
		return this.zyId;
	}

	public void setZyId(Integer zyId) {
		this.zyId = zyId;
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

}