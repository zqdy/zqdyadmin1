package com.zqdy.dao.po;

/**
 * TDocumentalDirector entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalDirector implements java.io.Serializable {

	// Fields

	private TDocumentalDirectorId id;

	// Constructors

	/** default constructor */
	public TDocumentalDirector() {
	}

	/** full constructor */
	public TDocumentalDirector(TDocumentalDirectorId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalDirectorId getId() {
		return this.id;
	}

	public void setId(TDocumentalDirectorId id) {
		this.id = id;
	}

}