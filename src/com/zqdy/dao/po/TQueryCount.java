package com.zqdy.dao.po;

/**
 * TQueryCount entity. @author MyEclipse Persistence Tools
 */

public class TQueryCount implements java.io.Serializable {

	// Fields

	private Integer id;

	// Constructors

	/** default constructor */
	public TQueryCount() {
	}

	/** full constructor */
	public TQueryCount(Integer id) {
		this.id = id;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}