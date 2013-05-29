package com.zqdy.service.snatch;

import java.util.List; 

public class DoubanBo {
	
	private String name;
	
	private String alias;
	
	private String length;
	
	private Float rateScore;               //  评分 
	
	private String releaseYear;
	
	private String shortSummary;
	
	private String summary;
	 
	private String movieId;
	
	private Integer playCount;
	
	private String embed;
	
	private Integer isMember=0;
	
	private String imageUrl;

	private Integer commentsCount;    //评论次数
	
	private Integer doubanId;        //豆瓣id
	
	private List<String> playList;   //播放地址
	
	private List<DoubanActor> directorList;        //导演
	
	private List<DoubanActor> actorList;         //演员
	
	private List<String> genreList;          //类型
	
	private List<String> areaList;           //国家

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

	public Float getRateScore() {
		return rateScore;
	}

	public void setRateScore(Float rateScore) {
		this.rateScore = rateScore;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	 
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	 
	public List<DoubanActor> getActorList() {
		return actorList;
	}

	public void setActorList(List<DoubanActor> actorList) {
		this.actorList = actorList;
	}

	public List<DoubanActor> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<DoubanActor> directorList) {
		this.directorList = directorList;
	}

	public List<String> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<String> genreList) {
		this.genreList = genreList;
	}

	public List<String> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<String> areaList) {
		this.areaList = areaList;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Integer getDoubanId() {
		return doubanId;
	}

	public void setDoubanId(Integer doubanId) {
		this.doubanId = doubanId;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public Integer getIsMember() {
		return isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public List<String> getPlayList() {
		return playList;
	}

	public void setPlayList(List<String> playList) {
		this.playList = playList;
	}

	public String getShortSummary() {
		return shortSummary;
	}

	public void setShortSummary(String shortSummary) {
		this.shortSummary = shortSummary;
	}

	 
	
	
}
