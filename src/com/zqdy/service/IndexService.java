package com.zqdy.service;

import java.util.List;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.po.TMovie; 
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;

public interface IndexService {
	 
	public List getNewMovie();
	
	public List getNewTv();
	
	public List getHotMovie();
	
	
	/**
	 * 得到推荐电影
	 */
	public List getRecommMovie(int count);
	
	/**
	 * 查询同一类型的电影
	 * @param genreId
	 * @return
	 */
	public List getMovieByGenre(Integer movieId,Integer genreId);
	
	/**
	 * 得到推荐视
	 */
	public List getRecommTv();
	
	/**
	 * 查询同一类型的视剧
	 * @param genreId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getTvByGenre(Integer tvId,Integer genreId);
	
	/**
	 * 得到最热电影
	 */
	public List getHotTv() ;
	
	/**
	 * 通過電影id得到電影
	 * @return
	 */
	public TMovie getMovieById(Integer id);
	
	/**
	 * 得到电影共享HTML代码
	 * @param movieId
	 * @param serverId
	 * @return
	 */
	public String getEmbedByMovieIdAndServerId(Integer movieId,Integer serverId);
	
	/**
	 * 更新电影播放次数
	 * @param movieId
	 * @return
	 */
	public boolean updateMoviePlayCount(Integer movieId,Integer serverId);
	
	/**
	 * 更新电视剧播放次数
	 * @param movieId
	 * @return
	 */
	public boolean updateTvPlayCount(Integer tvId,Integer subTvId);
	
	/**
	 * 得到单集电视剧共享HTML代码
	 * @param subTvId
	 * @return
	 */
	public TTvSubtv getEmbedBySubTvId(Integer subTvId);
	/**
	 * 通过电视剧id得到电视剧
	 * @param tvId
	 * @return
	 */
	public TTv getTvById(Integer tvId);
	
	public TTvSnatch getTvSnatch(Integer id);
	
	/**
	 * 得到子集信息
	 * @param snatchServerId
	 * @param cpage
	 * @return
	 */
	public List getSubtvList( Integer snatchServerId,int cpage);

}
