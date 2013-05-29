package com.zqdy.dao.po;

/**
 * TRecoMovie entity. @author MyEclipse Persistence Tools
 */

public class TRecoMovie implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer movieId;
	private Integer typeId;
	private String remark;
	private Integer showCount;

	// Constructors

	/** default constructor */
	public TRecoMovie() {
	}

	/** minimal constructor */
	public TRecoMovie(Integer movieId) {
		this.movieId = movieId;
	}

	/** full constructor */
	public TRecoMovie(Integer movieId, Integer typeId, String remark,
			Integer showCount) {
		this.movieId = movieId;
		this.typeId = typeId;
		this.remark = remark;
		this.showCount = showCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMovieId() {
		return this.movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getShowCount() {
		return this.showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}

}