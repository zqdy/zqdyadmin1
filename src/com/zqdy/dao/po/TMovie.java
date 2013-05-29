package com.zqdy.dao.po;
 
import java.util.Date;
import java.util.List; 

/**
 * TMovie entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class TMovie implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String alias;
	private Float rateScore;
	private String year;
	private Integer isMember=0;
	private String shortSummary;
	private String summary;
	private String imageUrl;
	private Integer weight;
	private Integer playCount;
	private Integer commentsCount;
	private Integer doubanId;
	private Date addTime;	
	
	private List directorList;	
	private List actorList;	
	private List genreList;	
	private List embedList; 	
	private List areaList;

	// Constructors

	/** default constructor */
	public TMovie() {
	}
	public TMovie(Integer id) {
		this.id=id;
	}
	/** full constructor */
	public TMovie(String name, String alias, Float rateScore, String year,
			Integer isMember, String shortSummary, String summary,
			String imageUrl, Integer weight, Integer playCount,
			Integer commentsCount, Integer doubanId, Date addTime) {
		this.name = name;
		this.alias = alias;
		this.rateScore = rateScore;
		this.year = year;
		this.isMember = isMember;
		this.shortSummary = shortSummary;
		this.summary = summary;
		this.imageUrl = imageUrl;
		this.weight = weight;
		this.playCount = playCount;
		this.commentsCount = commentsCount;
		this.doubanId = doubanId;
		this.addTime = addTime;
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

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Float getRateScore() {
		return this.rateScore;
	}

	public void setRateScore(Float rateScore) {
		this.rateScore = rateScore;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getIsMember() {
		return this.isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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

	public List getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List directorList) {
		this.directorList = directorList;
	}

	public List getActorList() {
		return actorList;
	}

	public void setActorList(List actorList) {
		this.actorList = actorList;
	}

	public List getGenreList() {
		return genreList;
	}

	public void setGenreList(List genreList) {
		this.genreList = genreList;
	}

	public List getEmbedList() {
		return embedList;
	}

	public void setEmbedList(List embedList) {
		this.embedList = embedList;
	}

	public List getAreaList() {
		return areaList;
	}

	public void setAreaList(List areaList) {
		this.areaList = areaList;
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