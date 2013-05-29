package com.zqdy.dao.po;

/**
 * TZongyiDirector entity. @author MyEclipse Persistence Tools
 */

public class TZongyiDirector implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer directorId;
	private Integer zyId;

	// Constructors

	/** default constructor */
	public TZongyiDirector() {
	}

	/** full constructor */
	public TZongyiDirector(Integer directorId, Integer zyId) {
		this.directorId = directorId;
		this.zyId = zyId;
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

	public Integer getZyId() {
		return this.zyId;
	}

	public void setZyId(Integer zyId) {
		this.zyId = zyId;
	}

}