package com.zqdy.dao.po;

/**
 * TMovieActor entity. @author MyEclipse Persistence Tools
 */

public class TMovieActor implements java.io.Serializable {

	// Fields

	private Integer id;
	private TActor actor;
	private TMovie movie;
	private Integer ind;

	// Constructors

	/** default constructor */
	public TMovieActor() {
	}

	/** full constructor */
	public TMovieActor(TActor actorId, TMovie movie, Integer ind) {
		this.actor = actor;
		this.movie = movie;
		this.ind = ind;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TActor getActor() {
		return this.actor;
	}

	public void setActor(TActor actor) {
		this.actor= actor;
	}

	 

	public Integer getInd() {
		return this.ind;
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

}