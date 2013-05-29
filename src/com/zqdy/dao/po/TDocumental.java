package com.zqdy.dao.po;

/**
 * TDocumental entity. @author MyEclipse Persistence Tools
 */

public class TDocumental implements java.io.Serializable {

	// Fields

	private TDocumentalId id;

	// Constructors

	/** default constructor */
	public TDocumental() {
	}

	/** full constructor */
	public TDocumental(TDocumentalId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalId getId() {
		return this.id;
	}

	public void setId(TDocumentalId id) {
		this.id = id;
	}

}