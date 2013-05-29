package com.zqdy.dao.po;

/**
 * TZongyiArea entity. @author MyEclipse Persistence Tools
 */

public class TZongyiArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer areaId;
	private Integer zyId;

	// Constructors

	/** default constructor */
	public TZongyiArea() {
	}

	/** full constructor */
	public TZongyiArea(Integer areaId, Integer zyId) {
		this.areaId = areaId;
		this.zyId = zyId;
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

	public Integer getZyId() {
		return this.zyId;
	}

	public void setZyId(Integer zyId) {
		this.zyId = zyId;
	}

}