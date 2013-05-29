package com.zqdy.dao.po;

/**
 * TMovieGenre entity. @author MyEclipse Persistence Tools
 */

public class TMovieGenre implements java.io.Serializable {

	// Fields

	private Integer id;
	private TGenre genre;
	private TMovie movie;
	private Integer ind;


	// Constructors

	/** default constructor */
	public TMovieGenre() {
	}

	 
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInd() {
		return ind;
	}

	public void setInd(Integer ind) {
		this.ind = ind;
	}


	public TGenre getGenre() {
		return genre;
	}


	public void setGenre(TGenre genre) {
		this.genre = genre;
	}


	public TMovie getMovie() {
		return movie;
	}


	public void setMovie(TMovie movie) {
		this.movie = movie;
	}

}