package com.zqdy.action;

import java.util.List;

import com.zqdy.dao.po.TMovie; 
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;

@SuppressWarnings("rawtypes")
public class IndexModel {
	
	private Integer id;
	
	private Integer serverId;
	
	private String embed;
	
	private TMovie movie ;
	
	private TTv tv;
	
	private TTvSubtv subtv;
	
	private TTvSnatch tvSnatch;
	
	private Integer subTvId;
	
	public List tvList;
	
	public List movieList;
	
	private List recommMvList;
	
	private List hotList;
	
	private List recommTvList;
	
	private List genreList;
	
	private List areaList;
	
	private List yearList;

	public List getTvList() {
		return tvList;
	}

	public void setTvList(List tvList) {
		this.tvList = tvList;
	}

	public List getMovieList() {
		return movieList;
	}

	public void setMovieList(List movieList) {
		this.movieList = movieList;
	}

	public List getRecommMvList() {
		return recommMvList;
	}

	public void setRecommMvList(List recommMvList) {
		this.recommMvList = recommMvList;
	}

	public List getRecommTvList() {
		return recommTvList;
	}

	public void setRecommTvList(List recommTvList) {
		this.recommTvList = recommTvList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TMovie getMovie() {
		return movie;
	}

	public void setMovie(TMovie movie) {
		this.movie = movie;
	}

	public List getHotList() {
		return hotList;
	}

	public void setHotList(List hotList) {
		this.hotList = hotList;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public TTv getTv() {
		return tv;
	}

	public void setTv(TTv tv) {
		this.tv = tv;
	}

	public Integer getSubTvId() {
		return subTvId;
	}

	public void setSubTvId(Integer subTvId) {
		this.subTvId = subTvId;
	}

	 
	public TTvSnatch getTvSnatch() {
		return tvSnatch;
	}

	public void setTvSnatch(TTvSnatch tvSnatch) {
		this.tvSnatch = tvSnatch;
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

	public TTvSubtv getSubtv() {
		return subtv;
	}

	public void setSubtv(TTvSubtv subtv) {
		this.subtv = subtv;
	}
	

}
