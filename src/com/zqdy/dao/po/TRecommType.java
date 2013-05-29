package com.zqdy.dao.po;

/**
 * TRecommType entity. @author MyEclipse Persistence Tools
 */

public class TRecommType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer showCount;
	private Integer workCount;

	// Constructors

	/** default constructor */
	public TRecommType() {
	}

	/** full constructor */
	public TRecommType(String name, Integer showCount, Integer workCount) {
		this.name = name;
		this.showCount = showCount;
		this.workCount = workCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getShowCount() {
		return this.showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}

	public Integer getWorkCount() {
		return this.workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}

}