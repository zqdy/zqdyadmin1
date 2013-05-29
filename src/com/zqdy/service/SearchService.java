package com.zqdy.service;

import java.util.List;

import com.zqdy.core.common.dto.CommonDto;
@SuppressWarnings("rawtypes")
public interface SearchService {
	
	/**
	 * 通过电影名称查询电影
	 * @param movieName
	 * @return
	 */
	public List getMovieByName(String name);
	
	/**
	 * 通过电视剧名称查询电视剧
	 * @param movieName
	 * @return
	 */
	public List getTvByName(String name);
	
	/**
	 * 通过名称查询电视、电影、电子剧子集住处
	 * @param movieName
	 * @return
	 */
	public CommonDto getViewResult(int pageSize, int pageNow, int serverId,String name);
	
	/**
	 * 分页得到電影数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getPageMoviet(int pageSize, int pageNow,CommonDto conditionDto);
	
	/**
	 * 分页得到电视剧数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getPageTv(int pageSize, int pageNow,CommonDto conditionDto);
	/**
	 * 得到电影类型
	 * @return
	 */
	public List  getSource();
	
	
	/**
	 * 得到电影类型
	 * @return
	 */
	public List  getAreaList();
	
	
	/**
	 * 得到电影类型
	 * @return
	 */
	
	public List  getMovieGenre();
	
	/**
	 * 得到电视剧类型
	 * @return
	 */
	public List  getTvGenre();
	
	
	/**
	 * 得到年代 
	 * @return
	 */
	public List  getYear();

}
