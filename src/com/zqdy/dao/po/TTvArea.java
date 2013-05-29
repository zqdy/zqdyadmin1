package com.zqdy.dao.po;

/**
 * TTvArea entity. @author MyEclipse Persistence Tools
 */

public class TTvArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private TArea area;
	private Integer tvId;

	// Constructors

	/** default constructor */
	public TTvArea() {
	}

	/** full constructor */
	public TTvArea(TArea area, Integer tvId) {
		this.area = area;
		this.tvId = tvId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	public Integer getTvId() {
		return this.tvId;
	}

	public void setTvId(Integer tvId) {
		this.tvId = tvId;
	}

	public TArea getArea() {
		return area;
	}

	public void setArea(TArea area) {
		this.area = area;
	}

}