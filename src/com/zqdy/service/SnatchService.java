package com.zqdy.service;

import java.util.List;
import java.util.Set;

import com.zqdy.dao.po.TActor; 
import com.zqdy.dao.po.TArea;
import com.zqdy.dao.po.TDirector;
import com.zqdy.dao.po.TGenre; 
import com.zqdy.dao.po.TMovieActor;
import com.zqdy.dao.po.TMovieArea;
import com.zqdy.dao.po.TMovieDirector;
import com.zqdy.dao.po.TMovieGenre;
import com.zqdy.dao.po.TSource;
import com.zqdy.dao.po.TTvActor;
import com.zqdy.dao.po.TTvSubtv;

public interface SnatchService {
	
	/**
	 * 抓取电影链接
	 */	
	public String snatchMovieLink(String url,String regex,int soreceServer,String charSet,Integer tvType);
	
	/**
	 * 抓取详细页面
	 * @return
	 */
	public String snatchDetail(Integer server,String regexEmbed,String regexDetail,Integer tvType,boolean isUpdate,Integer pageSize);
	
	
	/**
	 * 抓取详细页面
	 * @return
	 */
	public String snatchDetail(String regexEmbed,String regexDetail,Integer tvType,boolean isUpdate,Integer pageSize);
	
	
	/**
	 * 抓取上次未抓取完成的，而最新有更新的电视剧	
	 * @param pageNow
	 * @return
	 */
	public String snatchTvSubTv(int pageNow);
	
	/**
	 * 得到所有抓取网站
	 * @return
	 */
	public List<TSource> getAllSnatchServer();
	
	/**
	 * 查询分集
	 * @param server
	 * @param subTvName
	 * @return
	 */
	public TTvSubtv findSubTv(Integer tvServer,String subTvName);
	
	/**
	 * 创建影演员
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieActor createActorMovieIfNoCreate(String actorName,int index,Integer movieId);
	
	/**
	 * 创建影演员
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieActor createActorMovieIfNoCreate(String actorName,int index,Integer movieId,String doubanId,String imagePath);
	
	/**
	 * 创建电视剧演员
	 * @param actorName
	 * @param type  //1演员，2编剧，3监制-4 原著
	 * @param index  
	 * @param snatchService
	 * @return
	 */
	public  TTvActor createActorTvIfNoCreate(String actorName,int type,int index,Integer tvId,String doubanId,String imagePath);
	
	/**
	 * 抓取以前未抓取到的子集embed
	 */
	public void snatchSubTv();
	
	/**
	 * 抓取八度資源
	 */
	public void snatchBdzy();
	
	/**
	 * 创建电影国家
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieArea createMovieAreaIfNoCreate(String areaName,int index,Integer movieId);
	
	
	/**
	 * 根据区域名称得到id
	 * @param areaName
	 * @return
	 */
	public  TArea getArea(String areaName);
	/*
	 * 得到地区，如果数据库没有，则创建
	 */
	public  TArea createAreaIfNoCreate(String areaName);
	/**
	 * 根据演员名称得到idcreate
	 * @param areaName
	 * @return
	 */
	public  TActor getActor(String actorName);
	
	/*
	 * 得到演员，如果数据库没有，则创建
	 */
	public  TActor createActorIfNoCreate(String actorName);
	
	/**
	 * 创建电影导演
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieDirector createDirectorMovieIfNoCreate(String actorName,int index,Integer movieId,String boubanId,String imagePath);
	/**
	 * 根据导演名称得到id
	 * @param areaName
	 * @return
	 */
	public  TDirector getDirector(String directorName);
	
	/*
	 * 得到导演，如果数据库没有，则创建
	 */
	public  TDirector createDirectorIfNoCreate(String directorName,String doubanId,String imagePath);
	
	/**
	 * 根据类型名称得到类型
	 * @param areaName
	 * @return
	 */
	public  TGenre getGenre(String genreName,Integer type);
	
	public  TGenre getMovieGenre(String genreName,Integer type);
	
	public  TMovieGenre createGenreMovieIfNoCreate(String actorName,int index,Integer movieId,Integer type);
	/*
	 * 得到类型，如果数据库没有，则创建
	 * type  0 为电影，1为电视剧
	 */
	public  TGenre createGenreIfNoCreate(String genreName,Integer type);
	
	public Object uniqueFind(String hql );
	/**
	 * 创建一个对象
	 * @param object
	 * @return
	 */
	public Object createObject(Object object);

	public List getAreaList(String areas);
	/**
	 * 得到电影地区List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieArea> getMovieAreaList(String areaes,Integer movieId);
	
	public List getDirectorList(String directors);
	/**
	 * 得到电影导演List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieDirector> getMovieDirectorList(String directors,Integer movieId);
	/**
	 * 得到电影演员List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieActor> getActorMovieList(String actors,Integer movieId);
	
	public Set getActorSet(String actors);
	
	public List getGenreList(String genres,Integer type);
	
	/**
	 * 得到电影地区List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieGenre> getMovieGenreList(String genres,Integer movieId,int type);
}
