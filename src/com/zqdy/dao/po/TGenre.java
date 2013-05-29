package com.zqdy.dao.po;

/**
 * TGenre entity. @author MyEclipse Persistence Tools
 */

public class TGenre implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String name;
	private Integer type;

	// Constructors

	/** default constructor */
	public TGenre() {
	}

	/** full constructor */
	public TGenre(String code, String name, Integer type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}