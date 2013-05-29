package com.zqdy.dao.po;

/**
 * TDocumentalArea entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalArea implements java.io.Serializable {

	// Fields

	private TDocumentalAreaId id;

	// Constructors

	/** default constructor */
	public TDocumentalArea() {
	}

	/** full constructor */
	public TDocumentalArea(TDocumentalAreaId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalAreaId getId() {
		return this.id;
	}

	public void setId(TDocumentalAreaId id) {
		this.id = id;
	}

}