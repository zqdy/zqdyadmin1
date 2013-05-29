package com.zqdy.dao.po;

/**
 * TCartoonArea entity. @author MyEclipse Persistence Tools
 */

public class TCartoonArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer areaId;
	private Integer ctId;

	// Constructors

	/** default constructor */
	public TCartoonArea() {
	}

	/** full constructor */
	public TCartoonArea(Integer areaId, Integer ctId) {
		this.areaId = areaId;
		this.ctId = ctId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

}