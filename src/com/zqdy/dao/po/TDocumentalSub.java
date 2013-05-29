package com.zqdy.dao.po;

/**
 * TDocumentalSub entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalSub implements java.io.Serializable {

	// Fields

	private TDocumentalSubId id;

	// Constructors

	/** default constructor */
	public TDocumentalSub() {
	}

	/** full constructor */
	public TDocumentalSub(TDocumentalSubId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalSubId getId() {
		return this.id;
	}

	public void setId(TDocumentalSubId id) {
		this.id = id;
	}

}