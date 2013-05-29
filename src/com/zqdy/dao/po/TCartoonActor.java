package com.zqdy.dao.po;

/**
 * TCartoonActor entity. @author MyEclipse Persistence Tools
 */

public class TCartoonActor implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer actorId;
	private Integer ctId;
	private Integer type;
	private Integer ind;

	// Constructors

	/** default constructor */
	public TCartoonActor() {
	}

	/** full constructor */
	public TCartoonActor(Integer actorId, Integer ctId, Integer type,
			Integer ind) {
		this.actorId = actorId;
		this.ctId = ctId;
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

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
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