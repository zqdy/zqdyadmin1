package com.zqdy.dao.po;

/**
 * TMovieDirector entity. @author MyEclipse Persistence Tools
 */

public class TMovieDirector implements java.io.Serializable {

	// Fields

	private Integer id;
	private TDirector director;
	private TMovie movie;
	private Integer ind;
	
	// Constructors

	/** default constructor */
	public TMovieDirector() {
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



	public TDirector getDirector() {
		return director;
	}



	public void setDirector(TDirector director) {
		this.director = director;
	}



	public TMovie getMovie() {
		return movie;
	}



	public void setMovie(TMovie movie) {
		this.movie = movie;
	}

}