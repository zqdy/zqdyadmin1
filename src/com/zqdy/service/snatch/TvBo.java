package com.zqdy.service.snatch;
 
import java.util.List;

public class TvBo {
	
	private String name;
	
	private String alias;                           //别名

	private String imageUrl;

	private String releaseYear;
	
	private String pubDate;	

	private Float rateScore;               //  评分 
	 

	private String summary;

	private String length;
	
	private String language;               //语言
	
	private  String area;               //区域
	
	private List<String> directorList;             //导演
	
	private List<String> screenwriterList;         //编剧
	
	private List<String> genreList;                 //类型   
	
	private List<String> actorList;              //演员
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public Float getRateScore() {
		return rateScore;
	}

	public void setRateScore(Float rateScore) {
		this.rateScore = rateScore;
	}

	 
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<String> directorList) {
		this.directorList = directorList;
	}

	public List<String> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<String> genreList) {
		this.genreList = genreList;
	}

	public List<String> getActorList() {
		return actorList;
	}

	public void setActorList(List<String> actorList) {
		this.actorList = actorList;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<String> getScreenwriterList() {
		return screenwriterList;
	}

	public void setScreenwriterList(List<String> screenwriterList) {
		this.screenwriterList = screenwriterList;
	}

}
