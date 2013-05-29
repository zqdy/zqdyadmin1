package com.zqdy.dao.po;

/**
 * TDirector entity. @author MyEclipse Persistence Tools
 */

public class TDirector implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer workCount;
	private String doubanId;
	private String imagePath;

	// Constructors

	/** default constructor */
	public TDirector() {
	}

	/** minimal constructor */
	public TDirector(String name) {
		this.name = name;
	}

	/** full constructor */
	public TDirector(String name, Integer workCount, String doubanId,
			String imagePath) {
		this.name = name;
		this.workCount = workCount;
		this.doubanId = doubanId;
		this.imagePath = imagePath;
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

	public Integer getWorkCount() {
		return this.workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}

	public String getDoubanId() {
		return this.doubanId;
	}

	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}