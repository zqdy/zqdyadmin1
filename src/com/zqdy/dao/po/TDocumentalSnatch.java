package com.zqdy.dao.po;

/**
 * TDocumentalSnatch entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalSnatch implements java.io.Serializable {

	// Fields

	private TDocumentalSnatchId id;

	// Constructors

	/** default constructor */
	public TDocumentalSnatch() {
	}

	/** full constructor */
	public TDocumentalSnatch(TDocumentalSnatchId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalSnatchId getId() {
		return this.id;
	}

	public void setId(TDocumentalSnatchId id) {
		this.id = id;
	}

}