package com.zqdy.dao.po;

import java.util.Date;

/**
 * TLink entity. @author MyEclipse Persistence Tools
 */

public class TLink implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String url;
	private Integer source;
	private Integer isMember;
	private Integer status;
	private Date addtime;
	private Integer snatchCount;
	private Integer tvType;
	private String shortSummary;
	private Integer type=0;

	// Constructors

	/** default constructor */
	public TLink() {
	}

	/** full constructor */
	public TLink(String name, String url, Integer source, Integer isMember,
			Integer status, Date addtime, Integer snatchCount,
			Integer tvType, String shortSummary, Integer type) {
		this.name = name;
		this.url = url;
		this.source = source;
		this.isMember = isMember;
		this.status = status;
		this.addtime = addtime;
		this.snatchCount = snatchCount;
		this.tvType = tvType;
		this.shortSummary = shortSummary;
		this.type = type;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getIsMember() {
		return this.isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getSnatchCount() {
		return this.snatchCount;
	}

	public void setSnatchCount(Integer snatchCount) {
		this.snatchCount = snatchCount;
	}

	public Integer getTvType() {
		return this.tvType;
	}

	public void setTvType(Integer tvType) {
		this.tvType = tvType;
	}

	public String getShortSummary() {
		return this.shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}