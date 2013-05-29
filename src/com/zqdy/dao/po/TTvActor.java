package com.zqdy.dao.po;

/**
 * TTvActor entity. @author MyEclipse Persistence Tools
 */

public class TTvActor implements java.io.Serializable {

	// Fields

	private Integer id;
	private TActor actor;
	private TTv tv;
	private Integer type;
	private Integer ind;

	// Constructors

	/** default constructor */
	public TTvActor() {
	}

	/** full constructor */
	public TTvActor(TActor actor, TTv tvId, Integer type, Integer ind) {
		this.actor = actor;
		this.tv = tv;
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

	public TActor getActor() {
		return actor;
	}

	public void setActor(TActor actor) {
		this.actor = actor;
	}

	public TTv getTv() {
		return tv;
	}

	public void setTv(TTv tv) {
		this.tv = tv;
	}

}