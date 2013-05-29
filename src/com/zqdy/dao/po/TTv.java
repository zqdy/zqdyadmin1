package com.zqdy.dao.po;

import java.util.Date;
import java.util.List; 

/**
 * TTv entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class TTv implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String imageUrl;
	private String year;
	private Float rateScore;
	private Integer weight;
	private String shortSummary;
	private String summary;
	private Integer length;
	private Integer updateLength;
	private Integer status;
	private Integer playCount;
	private Integer commentsCount;
	private Integer doubanId;
	private Date addTime;
	private Date updateTime;
	
	private List tvSnatchList;
	
	private List areaList;
	
	private List directorList;	
	
	private List genreList;
	
	private List actorList;
	
	private List tvSourceList;

	// Constructors

	/** default constructor */
	public TTv() {
	}
	public TTv(Integer id) {
		this.id=id;
	}
	/** full constructor */
	public TTv(String name, String imageUrl, String year, Float rateScore,
			Integer weight, String shortSummary, String summary,
			Integer length, Integer updateLength, Integer playCount,
			Integer commentsCount, Integer doubanId, Date addTime,
			Date updateTime) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.year = year;
		this.rateScore = rateScore;
		this.weight = weight;
		this.shortSummary = shortSummary;
		this.summary = summary;
		this.length = length;
		this.updateLength = updateLength;
		this.playCount = playCount;
		this.commentsCount = commentsCount;
		this.doubanId = doubanId;
		this.addTime = addTime;
		this.updateTime = updateTime;
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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Float getRateScore() {
		return this.rateScore;
	}

	public void setRateScore(Float rateScore) {
		this.rateScore = rateScore;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getShortSummary() {
		return this.shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getUpdateLength() {
		return this.updateLength;
	}

	public void setUpdateLength(Integer updateLength) {
		this.updateLength = updateLength;
	}

	public Integer getPlayCount() {
		return this.playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getCommentsCount() {
		return this.commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Integer getDoubanId() {
		return this.doubanId;
	}

	public void setDoubanId(Integer doubanId) {
		this.doubanId = doubanId;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public List getAreaList() {
		return areaList;
	}
	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}
	public List getDirectorList() {
		return directorList;
	}
	public void setDirectorList(List directorList) {
		this.directorList = directorList;
	}
	public List getGenreList() {
		return genreList;
	}
	public void setGenreList(List genreList) {
		this.genreList = genreList;
	}
	public List getActorList() {
		return actorList;
	}
	public void setActorList(List actorList) {
		this.actorList = actorList;
	}
	public List getTvSourceList() {
		return tvSourceList;
	}
	public void setTvSourceList(List tvSourceList) {
		this.tvSourceList = tvSourceList;
	}
	public List getTvSnatchList() {
		return tvSnatchList;
	}
	public void setTvSnatchList(List tvSnatchList) {
		this.tvSnatchList = tvSnatchList;
	}
	 

	public Integer getRateScorePre() {
		if(rateScore==null||rateScore==0){
			 return 0;
		}else{
			float f = rateScore*10;;
			return (int)f;
		}
	}

}