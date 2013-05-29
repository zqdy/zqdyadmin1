package com.zqdy.dao.po;

/**
 * TMovieArea entity. @author MyEclipse Persistence Tools
 */

public class TMovieArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private TMovie movie;
	private TArea area;
	private Integer ind;

	// Constructors

	/** default constructor */
	public TMovieArea() {
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


	public TMovie getMovie() {
		return movie;
	}


	public void setMovie(TMovie movie) {
		this.movie = movie;
	}


	public TArea getArea() {
		return area;
	}


	public void setArea(TArea area) {
		this.area = area;
	}

}