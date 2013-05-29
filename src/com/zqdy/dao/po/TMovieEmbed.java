package com.zqdy.dao.po;

/**
 * TMovieEmbed entity. @author MyEclipse Persistence Tools
 */

public class TMovieEmbed implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private TSource source;
	private String embed;
	private TMovie movie;
	private Integer playCount;
	private Integer isMember;
	private String url;
	private Integer type;

	// Constructors

	/** default constructor */
	public TMovieEmbed() {
	}

	/** full constructor */
	public TMovieEmbed(TSource source, String embed, TMovie movie,
			Integer playCount, Integer isMember, String url, Integer type) {
		this.source = source;
		this.embed = embed;
		this.movie = movie;
		this.playCount = playCount;
		this.isMember = isMember;
		this.url = url;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TSource getSource() {
		return this.source;
	}

	public void setSource(TSource source) {
		this.source = source;
	}

	public String getEmbed() {
		return this.embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public TMovie getMovie() {
		return this.movie;
	}

	public void setMovie(TMovie movie) {
		this.movie = movie;
	}

	public Integer getPlayCount() {
		return this.playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getIsMember() {
		return this.isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}