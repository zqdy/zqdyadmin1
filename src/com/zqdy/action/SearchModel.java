package com.zqdy.action;

import java.util.List;

public class SearchModel {
	
	private String keyword;
	
	private Integer genre=0;
	
	private Integer area=0;
	
	private Integer source=0;
	
	private String year;
	
	private List genreList;
	
	private List areaList;
	
	private List yearList;
	
	private List sourceList;
	
	public List movieList;
	
	private List tvList;
	
	private List viewList;

	public List getMovieList() {
		return movieList;
	}

	public void setMovieList(List movieList) {
		this.movieList = movieList;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List getGenreList() {
		return genreList;
	}

	public void setGenreList(List genreList) {
		this.genreList = genreList;
	}

	public List getAreaList() {
		return areaList;
	}

	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}

	public List getYearList() {
		return yearList;
	}

	public void setYearList(List yearList) {
		this.yearList = yearList;
	}

	public List getSourceList() {
		return sourceList;
	}

	public void setSourceList(List sourceList) {
		this.sourceList = sourceList;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public List getTvList() {
		return tvList;
	}

	public void setTvList(List tvList) {
		this.tvList = tvList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List getViewList() {
		return viewList;
	}

	public void setViewList(List viewList) {
		this.viewList = viewList;
	}

	 

	 
}
