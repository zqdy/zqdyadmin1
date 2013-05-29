package com.zqdy.action;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.zqdy.core.action.BaseAction; 
import com.zqdy.dao.po.TGenre;
import com.zqdy.dao.po.TMovie; 
import com.zqdy.dao.po.TTv;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.service.IndexService;
import com.zqdy.service.SearchService;


@SuppressWarnings("rawtypes")
public class IndexAction  extends BaseAction  implements Action,ModelDriven<IndexModel>{
	
	private IndexService indexService;
	
	private SearchService searchService;
	
	IndexModel indexModel = new IndexModel();
	
	public String indexPage(){
		
		List  movieList = indexService.getNewMovie();
		List tvList = indexService.getNewTv();
		List recommMvList = indexService.getRecommMovie(8);		
		List recommTvList = indexService.getRecommTv();
		 
		indexModel.setMovieList(movieList);
		indexModel.setTvList(tvList);
		indexModel.setRecommMvList(recommMvList);
		indexModel.setRecommTvList(recommTvList);
		
		
		return SUCCESS;
	}
	
	/**
	 *  电影详细
	 * @return
	 */
	public String movieInfo(){
		Integer id = indexModel.getId();
		if(id==null){
			return "noResult";			
		}
		TMovie movie = indexService.getMovieById(id);
		if(movie==null){
			return "noResult";	
		}
		indexModel.setMovie(movie);
		 
		
		List recommMvList = indexService.getRecommMovie(8);		
		List recommTvList = indexService.getRecommTv();
		indexModel.setRecommMvList(recommMvList);
		indexModel.setRecommTvList(recommTvList);
		return SUCCESS;
	}
	
	/**
	 * 电影首页
	 * @return
	 */	
	public String movie(){
		List  movieList = indexService.getRecommMovie(8);
		List  hotList = indexService.getHotMovie();
		
		List recommMvList = indexService.getRecommMovie(8);		
		List recommTvList = indexService.getRecommTv();
		indexModel.setMovieList(movieList);
		indexModel.setHotList(hotList);
		
		indexModel.setRecommMvList(recommMvList);
		indexModel.setRecommTvList(recommTvList);
		
		List genreList = searchService.getMovieGenre();		
		List areaList = searchService.getAreaList();		
		List yearList = searchService.getYear();
		indexModel.setGenreList(genreList);
		indexModel.setAreaList(areaList);
		indexModel.setYearList(yearList);
		
		return SUCCESS;
	}
	
	/**
	 * 电影播放页面
	 * @return
	 */
	public String moviePlay(){
		Integer id = indexModel.getId();
		List recommMvList = null;
		if(id==null){
			return "noResult";			
		}
		 
		TMovie movie = indexService.getMovieById(id);
		if(movie==null){
			return "noResult";	
		}
		indexModel.setMovie(movie);
		Integer serverId = indexModel.getServerId();
		String embed = indexService.getEmbedByMovieIdAndServerId(id, serverId);
		indexService.updateMoviePlayCount(id, serverId);     //更新播放次数
		indexModel.setEmbed(embed);
		if(movie!=null){
//			Set genreSet = movie.getGenreSet();
//			 
//			if(genreSet!=null&&genreSet.size()>0){
//				Iterator it = genreSet.iterator();
//				it.hasNext();
//				TGenre gere = (TGenre)it.next();;
//				if(gere!=null){
//					recommMvList = indexService.getMovieByGenre(movie.getId(),gere.getId());
//				} 
//			}
		}
			
		 
		if(recommMvList==null){
			recommMvList = indexService.getRecommMovie(12);	
		}
			
		List hotMovieList = indexService.getHotMovie();
		indexModel.setRecommMvList(recommMvList);
		indexModel.setHotList(hotMovieList);
		return SUCCESS;
	}
	
	
	
	///-------------------------华丽分割线--------------------------------
	/**
	 *  电视剧主页
	 * @return
	 */
	public String tv(){
		List hotTvList = indexService.getHotTv();
		List recommTvList = indexService.getRecommTv();
		List recommMvList = indexService.getRecommMovie(8);	
		indexModel.setHotList(hotTvList);
		indexModel.setRecommTvList(recommTvList);
		indexModel.setRecommMvList(recommMvList);
		
		List genreList = searchService.getTvGenre();		
		List areaList = searchService.getAreaList();		
		List yearList = searchService.getYear();
		indexModel.setGenreList(genreList);
		indexModel.setAreaList(areaList);
		indexModel.setYearList(yearList);
		
		return SUCCESS;
	}
	/**
	 *  电视剧详细
	 * @return
	 */
	public String tvInfo(){
		Integer id = indexModel.getId();
		if(id==null){
			return "noResult";			
		}
		
		 
		TTv tv = indexService.getTvById(id);
		if(tv==null){
			return "noResult";			
		}
		indexModel.setTv(tv);
		 
		List recommTvList = indexService.getRecommTv();
		
		List hotTvList = indexService.getHotTv();
		indexModel.setRecommTvList(recommTvList);
		indexModel.setHotList(hotTvList);
		
		return SUCCESS;
	}
	
	/**
	 * 得到翻页子集电视剧
	 * @return
	 */
	public String subPageInfo(){
		Integer serverId = indexModel.getServerId();
		List subList = indexService.getSubtvList(serverId, cpage);
		msg = converSubToHtml(subList,serverId);
		this.outMsg(msg);
		return null;
	}
	/**
	 * 将子集转为HTML
	 * @param subList
	 * @return
	 */
	private String converSubToHtml(List subList,Integer serverId){
		if(subList==null){
			return null;
		}
		Locale locale = Locale.getDefault();
		String  mainUrl = LocalizedTextUtil.findDefaultText("zqdy.mainUrl", locale);
		String  imagePath = LocalizedTextUtil.findDefaultText("zqdy.imageUrl", locale);//
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<subList.size();i++){
			TTvSubtv subtv = (TTvSubtv)subList.get(i);
			if(subtv!=null){
				sb.append("<a class='AlinkCommentPic sizeStyle'  href='"+mainUrl+"/tvPlay.action?id="+serverId+"&subTvId="+subtv.getId()+"'  target='_blank'>");
				sb.append("<img width='106' height='65' src='"+imagePath+"/"+subtv.getImageUrl()+"' /></a>");
				
				sb.append("<div class='WelInfo InforSizeStyle'>");
				sb.append("<h3><a  href='"+mainUrl+"/tvPlay.action?id="+serverId+"&subTvId="+subtv.getId()+"'  target='_blank'>"+subtv.getName()+"</a></h3>");
				sb.append("<p>"+subtv.getSummary()+"</p>");
				sb.append("</div><br class='clearBoth' />");
			}
			
		} 
		return sb.toString();
	}
	
	/**
	 *  电视剧播放页
	 * @return
	 */
	public String tvPlay(){
		Integer id = indexModel.getId();
		Integer subTvId = indexModel.getSubTvId();
		List recommTvList = null;
		if(id==null){
			return "noResult";			
		}
			
		TTvSnatch tvSnatch = indexService.getTvSnatch(id);
		indexModel.setTvSnatch(tvSnatch);
//		TTv tv  = tvSnatch.getTv();
//		if(tv==null){
//			return "noResult";		
//		}
//		Set genreSet = tv.getGenreSet();
//		 
//		if(genreSet!=null){
//			Iterator it = genreSet.iterator();
//			it.hasNext();
//			TGenre gere = (TGenre)it.next();;
//			if(gere!=null){
//				recommTvList = indexService.getTvByGenre(tv.getId(),gere.getId());
//			} 
//		}
//		if(subTvId!=null){
//			TSubtv subtv = indexService.getEmbedBySubTvId(subTvId);
//			indexModel.setSubtv(subtv) ;
//			
//			indexService.updateTvPlayCount(tv.getId(), subTvId);               //更新播放次数
//		}
		if(recommTvList==null){
			recommTvList = indexService.getRecommTv();
		}
		List hotTvList = indexService.getHotTv();
		indexModel.setRecommTvList(recommTvList);
		indexModel.setHotList(hotTvList);
		return SUCCESS;
	}
	
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	public IndexModel getModel() {
		 
		return indexModel;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	
}
