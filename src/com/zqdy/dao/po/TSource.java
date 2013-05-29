package com.zqdy.dao.po;

/**
 * TSource entity. @author MyEclipse Persistence Tools
 */

public class TSource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer status;
	private String websitUrl;
	private String charSet;

	// Constructors

	/** default constructor */
	public TSource() {
	}
	public TSource(Integer id) {
		this.id=id;
	}


	/** minimal constructor */
	public TSource(String name) {
		this.name = name;
	}

	/** full constructor */
	public TSource(String name, Integer status, String websitUrl, String charSet) {
		this.name = name;
		this.status = status;
		this.websitUrl = websitUrl;
		this.charSet = charSet;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	public String getWebsitUrl() {
		return this.websitUrl;
	}

	public void setWebsitUrl(String websitUrl) {
		this.websitUrl = websitUrl;
	}

	public String getCharSet() {
		return this.charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}