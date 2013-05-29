package com.zqdy.service;

import java.util.List;
import java.util.Map;

import com.zqdy.dao.po.TLink;
import com.zqdy.dao.po.TMovie;
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.service.snatch.DoubanBo;

public interface SnatchUtilServer {
	
	 
	/**
	 * 得到URL下页面的电影地址
	 * @param url
	 * @param soreceServer
	 * @param tvType
	 * @return
	 */
	public List<TLink> getLinkByUrl(String url,int soureServer, Integer tvType);
	
	/**
	 * 提取电视剧详细信息  
	 * @param url 
	 */
	public DoubanBo extractMovieDetail(String movieName,String url);
	
	/**
	 * 提取电影共享地址
	 * @return
	 */
	public  String   extractMovieEmbed(String url);
	
	/**
	 * 提取电影播放地址
	 * @return
	 */
	public  String   extractMoviePlayUrl(String url);
	
	/**
	 * 提取每集电视剧共享地址
	 * @param html
	 * @param regexEmbed
	 * @return
	 */
	public   String extractSubTvEmbed(String html,String regexEmbed);
 

	/**
	 * 得到电视剧分集embed
	 * @param url
	 * @return
	 */
	public  String getSubTvEmbedByUrl(String url) ;
	
 
	public DoubanBo extractTVDetail(String url);;
	
	public   Map getSubTv(String url,String tvName,int hasLength,int countLength);
}