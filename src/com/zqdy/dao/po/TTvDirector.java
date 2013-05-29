package com.zqdy.dao.po;

/**
 * TTvDirector entity. @author MyEclipse Persistence Tools
 */

public class TTvDirector implements java.io.Serializable {

	// Fields

	private Integer id;
	private TDirector director;
	private Integer tvId;

	// Constructors

	/** default constructor */
	public TTvDirector() {
	}

	/** full constructor */
	public TTvDirector(TDirector director, Integer tvId) {
		this.director = director;
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

	public TDirector getDirector() {
		return director;
	}

	public void setDirector(TDirector director) {
		this.director = director;
	}

}