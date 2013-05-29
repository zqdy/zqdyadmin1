package com.zqdy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TMovieDirector;
import com.zqdy.dao.po.TMovieEmbed; 
import com.zqdy.dao.po.TMovie; 
import com.zqdy.dao.po.TRecoMovie;
import com.zqdy.dao.po.TSource;
import com.zqdy.service.MovieService;
import com.zqdy.service.SnatchService;

public class MovieServiceImpl implements MovieService {
	private SnatchService snatchService;
	private CommonDao commonDao;
	
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition) {
		return commonDao.getResult(pageSize, pageNow, poClass, condition);
	}
	
	/**
	 * 得到所有推荐类型
	 * @return
	 */
	public List getAllMovieType(){
		String hql = " from TRecommType ";
		return  commonDao.findByHQL(hql);
		
	}
	
	public TMovie getMovivById(Integer movieId){
		String hql = " from TMovie where id="+movieId;
		return (TMovie)commonDao.uniqueFind(hql);
	}

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
			String genres,Integer[] embedSourceServer,  String[] embeds,Integer[] embedIds){
		if(movie==null){
			return "保存失败";
		}
		Integer movieId = movie.getId();
		boolean insertFlag = false;
		if(movieId==null){
			insertFlag = true;
			snatchService.createObject(movie);
		}
		List areaList = snatchService.getMovieAreaList(areas,movie.getId());
		List directorList = snatchService.getMovieDirectorList(directors,movie.getId());
		List actorMovieList = snatchService.getActorMovieList(actors,movie.getId());
		List genreList = snatchService.getMovieGenreList(genres,movie.getId(),0);
		movie.setAreaList(areaList);
		movie.setDirectorList(directorList);
		movie.setActorList(actorMovieList);
		movie.setGenreList(genreList);
		
		List embedList = new ArrayList();
		if(embedIds!=null){
			for(int i=0;i<embedIds.length;i++){
				Integer intEmId = null;		
				TMovieEmbed embed = null;
				if(embedIds[i]!=0){
					intEmId = Integer.valueOf(embedIds[i]);
					String hql = "from TMovieEmbed where id="+intEmId;
					embed = (TMovieEmbed)commonDao.uniqueFind(hql);
				}
				if(embed==null){
					embed = new TMovieEmbed();
					embed.setId(intEmId);
				}				
				embed.setEmbed(embeds[i]);
				embed.setSource(new TSource(embedSourceServer[i]));
				embed.setMovie(movie);
				embedList.add(embed);
			}
			commonDao.createOrUpdateBatch(embedList);
		}
		movie.setEmbedList(embedList);
		if(insertFlag){
			commonDao.saveOrUpdate(movie);
		}else{
			commonDao.update(movie);
		}
	 
		 
		return "操作成功";
	}
	
	/**
	 * 更新为最热电影
	 * @param hotIds 电影id
	 * @param type  0为取消最新电影，1：更新为最新电影
	 * @return
	 */
	public String updateMovieHot(String hotIds,int type){
		String msg = "设置最热电影失败";;
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
					String hql = "update TMovie set isHot="+type +" where id in ("+sbHot.toString()+")";
					commonDao.executeQuery(hql);
					msg = "更新电影电影成功";
				} 
				
			}
		}
		return msg;
	}
	
	/**
	 * 推荐电影
	 * @param recommIds
	 * @param  
	 * @return
	 */
	public String updateMovieRecomm(String recommIds,Integer movieId,String shortSummary){
		if(movieId==null||recommIds==null){
			return "推荐失败";
		}
		String[] arrTypeIds = recommIds.split(",");
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<arrTypeIds.length;i++){
			String typeId = arrTypeIds[i];
			if(typeId!=null&&!"".equals(typeId)){
				 String hql ="from TRecoMovie where movieId="+movieId+" and typeId="+typeId;
				 List remmList = commonDao.findByHQL(hql);
				 if(remmList!=null&&remmList.size()>0){
					 sb.append("类型："+typeId+",");
				 }else{
					 TRecoMovie reMovie = new TRecoMovie();
					 reMovie.setMovieId(movieId);
					 reMovie.setTypeId(Integer.valueOf(typeId));
					 reMovie.setShowCount(0);
					 commonDao.create(reMovie);
					 String updatehql = "update TRecommType set workCount=workCount+1 where id="+typeId;
					 commonDao.executeQuery(updatehql);
				 }
			}
			
		}
		if(shortSummary!=null&&!"".equals(shortSummary)){
			String hql = "update TMovie set shortSummary=? where id="+movieId;
			commonDao.executeQuery(hql,shortSummary);
		}
		if(sb.length()>0){
			sb.append("电影已经在推荐列表中");
		}
		return sb.toString();
	}
	 
	/**
	 * 推荐电影
	 * @param recommIds
	 * @param type  0为取消推荐电影，1：更新为推荐电影
	 * @return
	 */
	public String updateMovieRecomm(String recommIds,int type){
		
		String msg = "设置推荐电影失败";;
		 
		return msg;
	}
	
	/**
	 * 取消推荐电影
	 * @param mvIds  电影ID
	 * @param mTypeId  电影类型id
	 * @return
	 */
	public String deleteMovieRecomm(String mvIds,int mTypeId){
		String msg = "取消推荐电影失败，未选择电影";;
		if(mvIds==null||"".equals(mvIds.trim())||mTypeId==0){
			return msg;
		}else{
			String[] arrMvId = mvIds.split(",");
			StringBuffer mvsb = new StringBuffer();
			for(int i=0;i<arrMvId.length;i++){
				String hotId = arrMvId[i];
				if(hotId!=null&&!"".equals(hotId)){
					mvsb.append(hotId+",");
				}
				
			}
			if(mvsb.length()>1){
				mvsb.delete(mvsb.length()-1,mvsb.length());	
				 
				String hql = "delete from TRecoMovie  where typeId="+mTypeId+" and movieId in ("+mvsb.toString()+")";
				commonDao.executeQuery(hql);
				msg = "取消推荐电影成功";
				 
				
			}
		}
		return msg;
	}
	
	/**
	 * 删除共享地址
	 * @param embedId
	 * @return
	 */
	public String deleteEmbedId(String embedId){
		String hql = "delete from TMovieEmbed where id="+embedId;
		commonDao.executeQuery(hql);
		return "删除成功";
	}
	
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setSnatchService(SnatchService snatchService) {
		this.snatchService = snatchService;
	}

}
