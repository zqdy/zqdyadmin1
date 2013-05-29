package com.zqdy.service;

import com.zqdy.core.common.dto.CommonDto; 
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSubtv;

public interface TvService {
	 /** 分页得到数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition);
	
	public TTv getTvById(Integer tvId);
	
	/**
	 * 保存电视剧
	 * @param tv
	 * @param actorTvId
	 * @param actorTvActorId
	 * @param actorName
	 * @param actorTvType
	 * @param actorTvInd
	 * @param portray
	 * @return
	 */
	public String saveTv(TTv tv,String areas,String directors,String genres,Integer [] actorTvId,Integer[] actorTvActorId,String[] actorName,
			Integer[] actorTvType,Integer[] actorTvInd,String[] portray);

	/**
	 * 更新为最热电视剧
	 * @param hotIds 电影id
	 * @param type  0为取消最新电视剧，1：更新为最新电视剧
	 * @return
	 */
	public String updateTvHot(String hotIds,int type);
	
	/**
	 * 推荐电视剧
	 * @param recommIds
	 * @param type  0为取消推荐电视剧，1：更新为推荐电视剧
	 * @return
	 */
	public String updateTvRecomm(String recommIds,int type);
	
	
	/**
	 * 得到每集电视剧
	 * @param subTvId
	 * @return
	 */
	public  TTvSubtv getSubTv(Integer subTvId);
	
	/**
	 * 保存每集电视剧
	 * @param subTvId
	 * @return
	 */
	public String  saveSubTv(TTvSubtv subtv);
	
	/**
	 * 保存对象
	 * @param subTvId
	 * @return
	 */
	public Object  saveOrUpdateTv(Object subtv);
	/**
	 * 删除演员、编剧、原著等
	 * @param actorTvId
	 * @return
	 */
	public boolean deleteActorTv(Integer actorTvId);
	
	/**
	 * 删除分集
	 * @param actorTvId
	 * @return
	 */
	public boolean deleteSubTv(Integer subTvId);
}
