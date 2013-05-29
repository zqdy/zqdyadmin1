package com.zqdy.dao.po;

/**
 * TTvGenre entity. @author MyEclipse Persistence Tools
 */

public class TTvGenre implements java.io.Serializable {

	// Fields

	private Integer id;
	private TGenre genre;
	private Integer tvId;

	// Constructors

	/** default constructor */
	public TTvGenre() {
	}

	/** full constructor */
	public TTvGenre(TGenre genre, Integer tvId) {
		this.genre = genre;
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

	public TGenre getGenre() {
		return genre;
	}

	public void setGenre(TGenre genre) {
		this.genre = genre;
	}

}