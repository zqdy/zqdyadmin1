package com.zqdy.dao.po;

/**
 * TDocumentalActor entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalActor implements java.io.Serializable {

	// Fields

	private TDocumentalActorId id;

	// Constructors

	/** default constructor */
	public TDocumentalActor() {
	}

	/** full constructor */
	public TDocumentalActor(TDocumentalActorId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalActorId getId() {
		return this.id;
	}

	public void setId(TDocumentalActorId id) {
		this.id = id;
	}

}