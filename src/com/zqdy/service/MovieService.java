package com.zqdy.service;

import java.util.List;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.po.TMovie;

public interface MovieService {
	/**
	 * 分页得到数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition);
	
	public TMovie getMovivById(Integer movieId);
	
	/**
	 * 推荐电影
	 * @param recommIds
	 * @param  
	 * @return
	 */
	public String updateMovieRecomm(String recommIds,Integer movieId,String shortSummary);
	
	 
	/**
	 * 取消推荐电影
	 * @param mvIds  电影ID
	 * @param mTypeId  电影类型id
	 * @return
	 */
	public String deleteMovieRecomm(String mvIds,int mTypeId);
	
	/**
	 * 得到所有推荐类型
	 * @return
	 */
	public List getAllMovieType();
	
	/**
	 * 保存电影
	 * @param movie
	 * @param areas
	 * @param directors
	 * @param actors
	 * @param genres
	 * @param embedSourceServer
	 * @param embeds
	 * @param embedIds
	 * @return
	 */
	public String saveMovie(TMovie movie,String areas,String directors,String actors,
			String genres,Integer[] embedSourceServer,  String[] embeds,Integer[] embedIds);
	
	/**
	 * 更新为最热电影
	 * @param hotIds 电影id
	 * @param type  0为取消最新电影，1：更新为最新电影
	 * @return
	 */
	public String updateMovieHot(String hotIds,int type);
	
	/**
	 * 推荐电影
	 * @param recommIds
	 * @param type  0为取消推荐电影，1：更新为推荐电影
	 * @return
	 */
	public String updateMovieRecomm(String recommIds,int type);
	
	/**
	 * 删除共享地址
	 * @param embedId
	 * @return
	 */
	public String deleteEmbedId(String embedId);
}
