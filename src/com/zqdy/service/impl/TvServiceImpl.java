package com.zqdy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TActor;  
import com.zqdy.dao.po.TTv; 
import com.zqdy.dao.po.TTvActor;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.service.SnatchService;
import com.zqdy.service.TvService;

public class TvServiceImpl implements TvService{
	
	private CommonDao commonDao;
	private SnatchService snatchService;
	
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition) {
		return commonDao.getResult(pageSize, pageNow, poClass, condition);
	}
	
	public TTv getTvById(Integer tvId){
		String hql = " from TTv where id="+tvId;
		TTv tv = (TTv)commonDao.uniqueFind(hql);
		if(tv==null){
			return tv;
		}
		 
		String hql1 = " from TTvActor where tv.id="+tvId;
		List actorTvList = commonDao.findByHQL(hql1);
		tv.setActorList(actorTvList);
		
		String hql2 = " from TTvSnatch where tv.id ="+tvId;
		List<TTvSnatch> tvSnatchList = commonDao.findByHQL(hql2);;
		if(tvSnatchList!=null){
			for(TTvSnatch tvServer:tvSnatchList){
				 
				List<TTvSubtv>  newList = new ArrayList<TTvSubtv>();   //不知为什么，会有很多空值
				List<TTvSubtv> subTvList = tvServer.getSubTvList();
				if(subTvList!=null){
					for(TTvSubtv subtv:subTvList){
						if(subtv!=null){
							newList.add(subtv);
						}
					}
					
				}
				tvServer.setSubTvList(newList);
			 
			}
		}
		
		tv.setTvSnatchList(tvSnatchList);
		
		return tv;
	}
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
			Integer[] actorTvType,Integer[] actorTvInd,String[] portray){
		if(tv==null){
			return null;
		}
		List actorList = new ArrayList();
		if(actorTvId!=null){
			//以下得到演员、编剧、监制、原著
			for(int i=0;i<actorTvId.length;i++){
				TTvActor atv = new TTvActor();
				atv.setId(actorTvId[i]);
				String aName = actorName[i];
				if(aName==null||"".equals(aName.trim())){
					continue;
				}
				TActor actor = snatchService.createActorIfNoCreate(aName);
				atv.setActor(actor); 
				atv.setType(actorTvType[i]);
				atv.setInd(actorTvInd[i]);
				atv.setTv(tv);
				actorList.add(atv);
			}
			List areaSet = snatchService.getAreaList(areas);
			List directorSet = snatchService.getDirectorList(directors);
			List genreSet = snatchService.getGenreList(genres,1);
			tv.setAreaList(areaSet);
			tv.setDirectorList(directorSet);
			tv.setGenreList(genreSet);
		}
		tv.setActorList(actorList);
		commonDao.saveOrUpdate(tv);
		
	 
		
		return "操作成功";
	}
	
	/**
	 * 得到每集电视剧
	 * @param subTvId
	 * @return
	 */
	public  TTvSubtv getSubTv(Integer subTvId){
		String hql = " from TTvSubtv where id="+subTvId;
		return (TTvSubtv)commonDao.uniqueFind(hql);
	}
	
	/**
	 * 更新为最热电视剧
	 * @param hotIds 电影id
	 * @param type  0为取消最新电视剧，1：更新为最新电视剧
	 * @return
	 */
	public String updateTvHot(String hotIds,int type){
		String msg = "设置最热电视剧失败";;
		if(hotIds==null||"".equals(hotIds.trim())){
			return msg;
		}else{
			String[] arrHot = hotIds.split(",");
			StringBuffer sbHot = new StringBuffer();
			for(int i=0;i<arrHot.length;i++){
				String hotId = arrHot[i];
				if(hotId!=null&&!"".equals(hotId)){
					sbHot.append(hotId+",");
				}
				
			}
			if(sbHot.length()>1){
				sbHot.delete(sbHot.length()-1,sbHot.length());	
				if(type==0||type==1){
					String hql = "update TTv set isHot="+type +" where id in ("+sbHot.toString()+")";
					commonDao.executeQuery(hql);
					msg = "更新电视剧成功";
				} 
				
			}
		}
		return msg;
	}
	
	/**
	 * 推荐电视剧
	 * @param recommIds
	 * @param type  0为取消推荐电视剧，1：更新为推荐电视剧
	 * @return
	 */
	public String updateTvRecomm(String recommIds,int type){

		String msg = "设置推荐电视剧失败";;
		 
		return msg;
	}
	
	
	/**
	 * 保存每集电视剧
	 * @param subTvId
	 * @return
	 */
	public String  saveSubTv(TTvSubtv subtv){
		return null;
	}
	/**
	 * 保存对象
	 * @param subTvId
	 * @return
	 */
	public Object  saveOrUpdateTv(Object obj){
		return commonDao.saveOrUpdate(obj);
	}
	
	
	/**
	 * 
	 * @param actorTvId
	 * @return
	 */
	public boolean deleteActorTv(Integer actorTvId){
		String hql = " delete from TTvActor where id="+actorTvId;
		commonDao.executeQuery(hql);
		return true;
	}
	
	/**
	 * 删除分集
	 * @param actorTvId
	 * @return
	 */
	public boolean deleteSubTv(Integer subTvId){
		String hql = " delete from TTvSubtv where id="+subTvId;
		commonDao.executeQuery(hql);
		return true;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setSnatchService(SnatchService snatchService) {
		this.snatchService = snatchService;
	}
	
}
