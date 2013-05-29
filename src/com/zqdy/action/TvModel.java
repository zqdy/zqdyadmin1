package com.zqdy.action;


import java.io.File;
 
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSubtv;

public class TvModel {
	
	private TTv tv;
	
	private TTvSubtv subtv;
	
	private String tvName;
	
	 private File imgFile;
	
	private String areas="";
	
	private String directors="";
	
	private String actors="";
	
	private String genres="";
	
	private Integer [] actorTvId;
	
	private String [] actorNames;
	private Integer[] actorTvType;
	private Integer[] actorTvInd;
	private String[] portray;
	
	
	
	
	public TTv getTv() {
		return tv;
	}

	public void setTv(TTv tv) {
		this.tv = tv;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		if(actors!=null&&actors.endsWith("/")){
			actors = actors.substring(0, actors.length()-1);
		}
		this.actors = actors;
	}

	public String[] getActorNames() {
		return actorNames;
	}

	public void setActorNames(String[] actorNames) {
		this.actorNames = actorNames;
	}

	public String getAreas() {
		if(areas!=null&&areas.endsWith("/")){
			areas = areas.substring(0, areas.length()-1);
		}
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getDirectors() {
		if(directors!=null&&directors.endsWith("/")){
			directors = directors.substring(0, directors.length()-1);
		}
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getGenres() {
		if(genres!=null&&genres.endsWith("/")){
			genres = genres.substring(0, genres.length()-1);
		}
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

 
	public Integer[] getActorTvId() {
		return actorTvId;
	}

	public void setActorTvId(Integer[] actorTvId) {
		this.actorTvId = actorTvId;
	}

	public Integer[] getActorTvInd() {
		return actorTvInd;
	}

	public void setActorTvInd(Integer[] actorTvInd) {
		this.actorTvInd = actorTvInd;
	}

	public Integer[] getActorTvType() {
		return actorTvType;
	}

	public void setActorTvType(Integer[] actorTvType) {
		this.actorTvType = actorTvType;
	}

	public String[] getPortray() {
		return portray;
	}

	public void setPortray(String[] portray) {
		this.portray = portray;
	}

	public TTvSubtv getSubtv() {
		return subtv;
	}

	public void setSubtv(TTvSubtv subtv) {
		this.subtv = subtv;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}

}
