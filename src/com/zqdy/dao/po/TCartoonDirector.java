package com.zqdy.dao.po;

/**
 * TCartoonDirector entity. @author MyEclipse Persistence Tools
 */

public class TCartoonDirector implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer directorId;
	private Integer ctId;

	// Constructors

	/** default constructor */
	public TCartoonDirector() {
	}

	/** full constructor */
	public TCartoonDirector(Integer directorId, Integer ctId) {
		this.directorId = directorId;
		this.ctId = ctId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDirectorId() {
		return this.directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

}