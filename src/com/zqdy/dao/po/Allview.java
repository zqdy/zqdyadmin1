package com.zqdy.dao.po;

/**
 * Allview entity. @author MyEclipse Persistence Tools
 */

public class Allview implements java.io.Serializable {

	// Fields

	private AllviewId id;

	// Constructors

	/** default constructor */
	public Allview() {
	}

	/** full constructor */
	public Allview(AllviewId id) {
		this.id = id;
	}

	// Property accessors

	public AllviewId getId() {
		return this.id;
	}

	public void setId(AllviewId id) {
		this.id = id;
	}

}