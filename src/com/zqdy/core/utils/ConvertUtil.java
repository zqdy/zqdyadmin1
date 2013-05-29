package com.zqdy.core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.zqdy.core.common.Constants;
import com.zqdy.dao.po.TActor;
import com.zqdy.dao.po.TMovieActor;
import com.zqdy.dao.po.TMovieArea;
import com.zqdy.dao.po.TMovieDirector;
import com.zqdy.dao.po.TMovieGenre;
import com.zqdy.dao.po.TTvActor;
import com.zqdy.dao.po.TArea;
import com.zqdy.dao.po.TDirector;
import com.zqdy.dao.po.TGenre;
import com.zqdy.dao.po.TMovie;
import com.zqdy.dao.po.TTv;
import com.zqdy.service.SnatchService;
import com.zqdy.service.snatch.DoubanActor;
import com.zqdy.service.snatch.DoubanBo;
import com.zqdy.service.snatch.DoubanUtil;
import com.zqdy.service.snatch.TvBo;

public class ConvertUtil {
	private static Logger logger;

	static {
		logger = Logger.getLogger(ConvertUtil.class);
	}
	
	/**
	 * 将豆瓣抓取 下来的TVBo转为数据库TTv
	 * @return
	 */
	public static TMovie convertMovieBoToMovie(DoubanBo moviebo,TMovie movie,int server,SnatchService snatchService){
		if(moviebo==null){
			return null;
		}
		movie.setAddTime(new Date());
		String movieName = moviebo.getName();
		//movie.setName(movieName);
		Float score = moviebo.getRateScore();
		 
		movie.setRateScore(score);
		movie.setDoubanId(moviebo.getDoubanId());
		movie.setCommentsCount(moviebo.getCommentsCount());
		movie.setYear(moviebo.getReleaseYear());
		String alias = moviebo.getAlias();
		if(alias!=null){
			alias = alias.replace("[","");
			alias = alias.replace("]","");
			alias = alias.replace("\"","");
		}
		movie.setAlias(alias);
		movie.setPlayCount(moviebo.getPlayCount());
		movie.setSummary(moviebo.getSummary());
		String imageUrl = moviebo.getImageUrl();
		//logger.info("----------------------convertUtil imageUrl图片地址:"+mediumImage);
		if(imageUrl!=null){
			imageUrl = imageUrl.replace("\\", ""); 
			String imagePath = DownHtml.downImag(imageUrl, "m"+server, movieName,movieName);
			movie.setImageUrl(imagePath);
		}
		List<DoubanActor> doubanDirectorList = moviebo.getDirectorList();    //转换导演
		if(doubanDirectorList!=null){
			List<TMovieDirector> directorList = new ArrayList<TMovieDirector>();
			for(int i=0;i<doubanDirectorList.size();i++){
				DoubanActor doubanDirector = doubanDirectorList.get(i);
				String directorName = doubanDirector.getName();
				String doubanId= doubanDirector.getDoubanId();
				String doubanImageUrl = doubanDirector.getImagePath();
				TMovieDirector director = snatchService
						.createDirectorMovieIfNoCreate(directorName,i,movie.getId(),doubanId,doubanImageUrl);
				directorList.add(director);
				
			}
			movie.setDirectorList(directorList);
		}
		List<DoubanActor> aList = moviebo.getActorList();                //转换演员
		if(aList!=null){
			List<TMovieActor> actorList = new ArrayList<TMovieActor>();
			for(int i=0;i<aList.size();i++){
				DoubanActor doubanActor = aList.get(i);
				String name = doubanActor.getName();
				String doubanId = doubanActor.getDoubanId();
				String doubanImageUrl = doubanActor.getImagePath();
				 
				TMovieActor actorMovie = snatchService.createActorMovieIfNoCreate(name,i,movie.getId(),doubanId,doubanImageUrl);
				actorList.add(actorMovie);
			}
			movie.setActorList(actorList);
		}
		List<String> gList = moviebo.getGenreList();               //转换类型
		if(gList!=null){
			List genreList = new ArrayList();
			for(int i=0;i<gList.size();i++){
				String gerStr = gList.get(i);
				TMovieGenre genre = snatchService.createGenreMovieIfNoCreate(gerStr,i,movie.getId(), 0);
				genreList.add(genre);
			}
			movie.setGenreList(genreList);
		}
		List<String> arList = moviebo.getAreaList();
		if(arList!=null){
			List areaList = new ArrayList();
			for(int i=0;i<arList.size();i++){
				String areaStr = arList.get(i);
				TMovieArea movieArea = snatchService.createMovieAreaIfNoCreate(areaStr,i,movie.getId());
				areaList.add(movieArea);
			}
			movie.setAreaList(areaList);
		}
		
		return movie;
	}
	
	
	/**
	 * 将豆瓣抓取 下来的TVBo转为数据库TTv
	 * @return
	 */
	public static TTv convertTvBoToTV(DoubanBo doubanBo,TTv tv,int server,SnatchService snatchService){
		if(doubanBo==null||tv==null){
			return null;
		}
	  
		tv.setAddTime(new Date()); 
		tv.setName(doubanBo.getName());
		tv.setImageUrl(doubanBo.getImageUrl());
		tv.setYear(doubanBo.getReleaseYear());
		Float score = doubanBo.getRateScore();
		tv.setRateScore(score);
	 
		tv.setYear(doubanBo.getReleaseYear());
		tv.setSummary(doubanBo.getSummary());
		
		tv.setUpdateLength(0);
		tv.setAddTime(new Date());
		String length = doubanBo.getLength();
		if(length!=null){
			length = length.trim();
			try{
				tv.setLength(Integer.parseInt(length));
			}catch(Exception e){}
		}
		String mediumImage = doubanBo.getImageUrl();
		if(mediumImage!=null){
			mediumImage = mediumImage.replace("\\", ""); 
			String imagePath = DownHtml.downImag(mediumImage, "tv"+server,doubanBo.getName(),doubanBo.getName());
			tv.setImageUrl(imagePath);
		}
		List areaList = new ArrayList();                         //地区
		
		List directorList = new ArrayList();	                 //导演
		
		List genreList = new ArrayList();                        //类型
		
		List actorList = new ArrayList();                    //主演
		
		List<DoubanActor> doubanDirectorList = doubanBo.getDirectorList();
		//List<String> screenwriteList = doubanBo.getScreenwriterList();
		List<DoubanActor> actList = doubanBo.getActorList();
		List<String> doubanGenreList = doubanBo.getGenreList();
		
		//转地区
		List<String> areaNameList = doubanBo.getAreaList();
		for(String areaName:areaNameList){
			TArea area = snatchService.createAreaIfNoCreate(areaName);				 
			areaList.add(area);
		}
		
		if(directorList!=null){
			for(DoubanActor dir:doubanDirectorList){
				String dirName = dir.getName();
				String doubanId = dir.getDoubanId();
				String imageUrl = dir.getImagePath();
				TDirector director = snatchService.createDirectorIfNoCreate(dirName,doubanId,imageUrl);
				directorList.add(director);
			}
		}
		if(actList!=null){
			for(int i=0;i<actList.size();i++){
				DoubanActor actor  = actList.get(i);	
				String actorName = actor.getName();
				String doubanId = actor.getDoubanId();
				String imageUrl = actor.getImagePath();
				
				TTvActor actorTv = snatchService
						.createActorTvIfNoCreate(
								actorName,
								Constants.ACTOR,
								i, tv.getId(),doubanId,imageUrl);
				actorList.add(actorTv);
			}
		}
		
		/**if(screenwriteList!=null){                    //编剧
			for(int i=0;i<screenwriteList.size();i++){
				String actorName = screenwriteList.get(i);				
				TTvActor actorTv = snatchService
						.createActorTvIfNoCreate(
								actorName,
								Constants.WRITERPLAY,
								i, tv.getId());
				actorList.add(actorTv);
			}
		}**/
		
		if(genreList!=null){
			for(String gen:doubanGenreList){
				TGenre genre = snatchService.createGenreIfNoCreate(gen, Constants.TV);
				genreList.add(genre);
				
			}
		}
		tv.setDirectorList(directorList);
		tv.setActorList(actorList);
		tv.setAreaList(areaList);
		tv.setGenreList(genreList);
 		return tv;
	}
}
