package com.zqdy.dao.po;

/**
 * TCartoonGenre entity. @author MyEclipse Persistence Tools
 */

public class TCartoonGenre implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer genreId;
	private Integer ctId;

	// Constructors

	/** default constructor */
	public TCartoonGenre() {
	}

	/** full constructor */
	public TCartoonGenre(Integer genreId, Integer ctId) {
		this.genreId = genreId;
		this.ctId = ctId;
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

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

}