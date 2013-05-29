package com.zqdy.service.impl;

import java.util.List;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TMovieEmbed;
import com.zqdy.dao.po.TMovieGenre;
import com.zqdy.dao.po.TTvGenre;
import com.zqdy.dao.po.TMovie;
import com.zqdy.dao.po.TMovieEmbed;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.service.IndexService;

public class IndexServiceImpl implements IndexService {
	
	private CommonDao commonDao;	
	
	/**
	 * 得到最新电影
	 */
	public List getNewMovie() {
		String hql = " from TMovie where imageUrl is not null order by weight, addTime desc ";	 
		List movieList = commonDao.getListByHqlAndCount(hql, 12);		 
		return movieList;
	}

	/**
	 * 得到最热电影
	 */
	public List getHotMovie() {
		String hql = " from TMovie where isHot=1 order by weight, addTime desc ";	 
		List movieList = commonDao.getListByHqlAndCount(hql, 8);		 
		return movieList;
	}
	
	/**
	 * 得到推荐电影
	 */
	public List getRecommMovie(int count) {
		String hql = " from TMovie  order by weight, addTime desc ";	 
		List movieList = commonDao.getListByHqlAndCount(hql,count);		 
		return movieList;
	}
	/**
	 * 更新电影播放次数
	 * @param movieId
	 * @return
	 */
	public boolean updateMoviePlayCount(Integer movieId,Integer serverId){
		String hql = "update TMovie set playCount=playCount+1 where id="+movieId;
		commonDao.executeQuery(hql);
		String hql2 = "update TMovieEmbed set playCount=playCount+1 where movie.id="+movieId+" and serverServer.id="+serverId;
		commonDao.executeQuery(hql2);
		return true;
	}
	
	/**
	 * 更新电视剧播放次数
	 * @param movieId
	 * @return
	 */
	public boolean updateTvPlayCount(Integer tvId,Integer subTvId){
		String hql = "update TTv set playCount=playCount+1 where id="+tvId;
		commonDao.executeQuery(hql);
		String hql2 = "update TTvSubtv set payCount=payCount+1 where id="+subTvId;
		commonDao.executeQuery(hql2);
		return true;
	}
	/**
	 * 查询同一类型的电影
	 * @param genreId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getMovieByGenre(Integer movieId,Integer genreId){
		List movieList = null;
		String hql = " from TMovieGenre where id.genreId="+genreId;	 
		List movieIdList = commonDao.getListByHqlAndCount(hql,12);
		if(movieIdList!=null){
			StringBuffer mvIdSb = new StringBuffer();
			for(int i=0;i<movieIdList.size();i++){
				TMovieGenre genreMv = (TMovieGenre)movieIdList.get(i);
				if(genreMv!=null){
					mvIdSb.append("'"+genreMv.getMovie().getId()+"',");
				}
				
			}
			if(mvIdSb.length()>1){
				mvIdSb.delete(mvIdSb.length()-1, mvIdSb.length());
				String hql2 = "from TMovie where id in("+mvIdSb.toString()+") and id!="+movieId;
				movieList = commonDao.findByHQL(hql2);
			}
		}
		
		return movieList;
	}
	/**
	 * 查询同一类型的视剧
	 * @param genreId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getTvByGenre(Integer tvId,Integer genreId){
		List tvList = null;
		String hql = " from TTvGenre where id.genreId="+genreId;	 
		List genreTvList = commonDao.getListByHqlAndCount(hql,12);
		if(genreTvList!=null){
			StringBuffer mvIdSb = new StringBuffer();
			for(int i=0;i<genreTvList.size();i++){
				TTvGenre genreTv = (TTvGenre)genreTvList.get(i);
				if(genreTv!=null){
					mvIdSb.append("'"+genreTv.getTvId()+"',");
				}
				
			}
			if(mvIdSb.length()>1){
				mvIdSb.delete(mvIdSb.length()-1, mvIdSb.length());
				String hql2 = "from TTv where id in("+mvIdSb.toString()+") and id!="+tvId;
				tvList = commonDao.findByHQL(hql2);
			}
		}
		
		return tvList;
	}
	/**
	 * 得到子集信息
	 * @param snatchServerId
	 * @param cpage
	 * @return
	 */
	public List getSubtvList( Integer snatchServerId,int cpage){
		int startVolume = cpage*25;
		int endVolume = cpage*25+25;
		String hql =" from TTvSubtv where tvSnatch="+snatchServerId+" and volume>"+startVolume+" and volume <="+endVolume;
		List subList = commonDao.findByHQL(hql);
		return subList;
	}
	
	/**
	 * 得到推荐视
	 */
	public List getRecommTv(){
		String hql = " from TTv  order by weight, updateTime desc ";	 
		List tvList = commonDao.getListByHqlAndCount(hql, 12);		 
		return tvList;
	}

	/**
	 * 得到最新电 视剧
	 */
	public List getNewTv() {
		String hql = " from TTv order by weight, updateTime desc ";	 
		List movieList = commonDao.getListByHqlAndCount(hql, 8);		 
		return movieList;
	}
	
	/**
	 * 得到最热电影
	 */
	public List getHotTv() {
		String hql = " from TTv where isHot=1 order by weight, updateTime desc ";	
		List movieList = commonDao.getListByHqlAndCount(hql, 6);		 
		return movieList;
	}

	/**
	 * 通過電影id得到電影
	 * @return
	 */
	public TMovie getMovieById(Integer id){
		if(id==null){
			return null;
		}
		String hql = "from TMovie where id="+id;
		TMovie movie = (TMovie)commonDao.uniqueFind(hql);
		return movie;
	}
	
	/**
	 * 得到电影共享HTML代码
	 * @param movieId
	 * @param serverId
	 * @return
	 */
	public String getEmbedByMovieIdAndServerId(Integer movieId,Integer serverId){
		if(movieId==null||serverId==null){
			return null;
		}
		String embedHtml = null;
		String hql = "from TMovieEmbed where movie.id="+movieId+" and serverServer.id="+serverId;
		TMovieEmbed embed = (TMovieEmbed)commonDao.uniqueFind(hql);
		if(embed!=null){
			embedHtml = embed.getEmbed();
		}
		return embedHtml;
	}
	
	/**
	 * 得到单集电视剧共享HTML代码
	 * @param subTvId
	 * @return
	 */
	public TTvSubtv getEmbedBySubTvId(Integer subTvId){
		String hql = "   from TTvSubtv where id="+subTvId;
		TTvSubtv subTv  = (TTvSubtv)commonDao.uniqueFind(hql);
		return subTv ;
	}
	 
	/**
	 * 通过电视剧id得到电视剧
	 * @param tvId
	 * @return
	 */
	public TTv getTvById(Integer tvId){
		if(tvId==null){
			return null;
		}
		String hql = "from TTv where id="+tvId;
		TTv tv = (TTv)commonDao.uniqueFind(hql);
		return tv;
	}
	public TTvSnatch getTvSnatch(Integer id){
		if(id==null){
			return null;
		}
		String hql = "from TTvSnatch where id="+id;
		TTvSnatch tvSnatch = (TTvSnatch)commonDao.uniqueFind(hql);
		return tvSnatch;
		 
	}
	
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}





}
