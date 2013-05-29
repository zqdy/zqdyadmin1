package com.zqdy.dao.po;

/**
 * TZongyiGenre entity. @author MyEclipse Persistence Tools
 */

public class TZongyiGenre implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer genreId;
	private Integer zyId;

	// Constructors

	/** default constructor */
	public TZongyiGenre() {
	}

	/** full constructor */
	public TZongyiGenre(Integer genreId, Integer zyId) {
		this.genreId = genreId;
		this.zyId = zyId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGenreId() {
		return this.genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public Integer getZyId() {
		return this.zyId;
	}

	public void setZyId(Integer zyId) {
		this.zyId = zyId;
	}

}