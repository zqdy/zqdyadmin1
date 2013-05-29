package com.zqdy.dao.po;

/**
 * TDocumentalGenre entity. @author MyEclipse Persistence Tools
 */

public class TDocumentalGenre implements java.io.Serializable {

	// Fields

	private TDocumentalGenreId id;

	// Constructors

	/** default constructor */
	public TDocumentalGenre() {
	}

	/** full constructor */
	public TDocumentalGenre(TDocumentalGenreId id) {
		this.id = id;
	}

	// Property accessors

	public TDocumentalGenreId getId() {
		return this.id;
	}

	public void setId(TDocumentalGenreId id) {
		this.id = id;
	}

}