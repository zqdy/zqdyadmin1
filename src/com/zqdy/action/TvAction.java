package com.zqdy.action;
 
import java.util.Date;
import java.util.Iterator;
import java.util.List; 
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.zqdy.service.SnatchService;
import com.zqdy.service.TvService;
import com.zqdy.core.action.BaseAction;
import com.zqdy.core.common.dto.CommonDto;  
import com.zqdy.core.utils.DownHtml;
import com.zqdy.dao.po.TArea;
import com.zqdy.dao.po.TDirector;
import com.zqdy.dao.po.TGenre;
import com.zqdy.dao.po.TTvArea;
import com.zqdy.dao.po.TTvDirector;
import com.zqdy.dao.po.TTvGenre;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.dao.po.TTv;

@SuppressWarnings("serial")
public class TvAction extends BaseAction  implements Action,ModelDriven<TvModel>{
	
	private TvService tvService;
	
	private SnatchService snatchService;
	
	private TvModel tvModel = new TvModel();
	

	public String goTvList(){
		init();
		StringBuffer hql = new StringBuffer(" where 1=1 ");
		String tvName = tvModel.getTvName();
		
		
		if(tvName!=null&&!"".equals(tvName.trim())){
			hql.append(" and name like '%"+tvName+"%'");
		}
		
		hql.append(" order by updateTime desc ");
		CommonDto dto = (CommonDto) tvService.getResult(pageSize,
				cpage - 1, " TTv ", hql.toString());
		resultList = (List) dto.getProperty("result");
		Integer pageCount = (Integer) dto.getProperty("pageCount");
		this.setTotal(pageCount);
		
//		List snatchServerList = snatchService.getAllSnatchServer();
//		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}

	public String goAddTv(){
		init();
	 
		return SUCCESS;
	}
	public String doAddTv(){
		init(); 
		TTv tv = tvModel.getTv();
		if(tvModel.getImgFile()!=null){
			String imagePath = DownHtml.saveMovieImage(tvModel.getImgFile(),"tv",tv.getName(),"tv"+tv.getName());
			tv.setImageUrl(imagePath);
		}
		tv.setAddTime(new Date());
		tv.setUpdateTime(new Date());
		msg = tvService.saveTv(tv,tvModel.getAreas(),tvModel.getDirectors(),tvModel.getGenres(), tvModel.getActorTvId(),tvModel.getActorTvId(),
				tvModel.getActorNames(),tvModel.getActorTvType(),tvModel.getActorTvInd(),tvModel.getPortray());
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	public String goEditTv(){
		init();
		String tvIdStr = request.getParameter("tvId");
		Integer tvId = Integer.valueOf(tvIdStr);
		TTv tv = tvService.getTvById(tvId);
		this.getModel().setTv(tv);
		
		if(tv!=null){
			String directors = ""; 
			List directorList = tv.getDirectorList();
			if(directorList!=null&&directorList.size()>0){ 
				for(int i=0;i<directorList.size();i++){
					TTvDirector dir = (TTvDirector)directorList.get(i);
					if(dir!=null){
						directors+=dir.getDirector().getName()+"/";
					}
					
				}
			}
			this.getModel().setDirectors(directors);
			
			String genres = "";
			List genList = tv.getGenreList();
			if(genList!=null&&genList.size()>0){
				for(int i=0;i<genList.size();i++){
					TTvGenre tvGenre = (TTvGenre)genList.get(i);
					if(tvGenre!=null){
						TGenre gen  = tvGenre.getGenre();
						genres+=gen.getName()+"/";
					}
					 
				}
			}
			this.getModel().setGenres(genres);
			String areas = "";
			List areaList = tv.getAreaList();
			if(areaList!=null&&areaList.size()>0){
			 
				for(int i=0;i<areaList.size();i++){
					TTvArea tvarea = (TTvArea)areaList.get(i);
					if(tvarea!=null){
						areas+=tvarea.getArea().getName()+"/";
					}
					
				}
			}
			this.getModel().setAreas(areas);
			
		}

		
		 
		
		return  SUCCESS;
	}
	
	public String deleteActorTv(){
		init();
		String acTvIdStr = request.getParameter("actorTvId");
		Integer actorTvId = Integer.valueOf(acTvIdStr);
		boolean flag = tvService.deleteActorTv(actorTvId);
		if(flag){
			msg = "删除成功";
		}else{
			msg = "删除失败";
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	public String deleteSubTv(){
		init();
		String subTvIdStr = request.getParameter("subTvId");
		Integer subTvId = Integer.valueOf(subTvIdStr);
		boolean flag = tvService.deleteSubTv(subTvId);
		if(flag){
			msg = "删除成功";
		}else{
			msg = "删除失败";
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	public String doEditTv(){
		init(); 
		TTv tv = tvModel.getTv();
		tv.setUpdateTime(new Date());
		if(tvModel.getImgFile()!=null){
			String imagePath = DownHtml.saveMovieImage(tvModel.getImgFile(),"tv",tv.getName(),"tv"+tv.getName());
			tv.setImageUrl(imagePath);
		}
		msg = tvService.saveTv(tv,tvModel.getAreas(),tvModel.getDirectors(),tvModel.getGenres(), tvModel.getActorTvId(),tvModel.getActorTvId(),
				tvModel.getActorNames(),tvModel.getActorTvType(),tvModel.getActorTvInd(),tvModel.getPortray());
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	
	public String goAddSubTv(){
		init(); 
		String tvIdStr = request.getParameter("tvId");
		request.setAttribute("tvId", tvIdStr);
		TTv tv = tvService.getTvById(Integer.valueOf(tvIdStr));
		if(tv!=null){
			tvModel.setTvName(tv.getName());
		} 
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	public String doAddSubTv(){
		init();
		String tvName = request.getParameter("tvName");
		TTvSubtv subtv = tvModel.getSubtv();
		if(tvModel.getImgFile()!=null){
			String imagePath = DownHtml.saveMovieImage(tvModel.getImgFile(),"tv",tvName,"subtv"+subtv.getName());
			subtv.setImageUrl(imagePath);
		}
		subtv.setAddTime(new Date());
		subtv.setUpdateTime(new Date());
		tvService.saveOrUpdateTv(subtv);
		msg= "操作成功";
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	public String goEditSubTv(){
		init();
		String subTvIdStr = request.getParameter("subTvId");
		String tvIdStr = request.getParameter("tvId");
		Integer subTvId = Integer.valueOf(subTvIdStr);
		TTv tv = tvService.getTvById(Integer.valueOf(tvIdStr));
		if(tv!=null){
			tvModel.setTvName(tv.getName());
		}
		
		TTvSubtv subtv = tvService.getSubTv(subTvId);
		tvModel.setSubtv(subtv);
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	public String doEditSubTv(){
		init();
		String tvName = request.getParameter("tvName");
		TTvSubtv subtv = tvModel.getSubtv();
		if(tvModel.getImgFile()!=null){
			String imagePath = DownHtml.saveMovieImage(tvModel.getImgFile(),"tv",tvName,"subtv"+subtv.getName());
			subtv.setImageUrl(imagePath);
		}
		tvService.saveOrUpdateTv(subtv);
		msg= "操作成功";
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	
	/**
	 * 推荐、最热门电影
	 * @return
	 */
	public String hotRecommTv(){
		init();
		String hotIds = request.getParameter("hotIds");
		String recommIds = request.getParameter("recommIds");
		if(hotIds==null&&recommIds==null){
			msg = "更新失败，请选择需要操作的电影";
		}else{
			if(hotIds!=null&&!"".equals(hotIds.trim())){
				msg = tvService.updateTvHot(hotIds, 1);
			}
			if(recommIds!=null&&!"".equals(recommIds.trim())){
				msg = tvService.updateTvRecomm(recommIds, 1);
			}
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	/**
	 *  取消 推荐、最热门电影
	 * @return
	 */
	public String unHotRecommTv(){
		init();
		String hotIds = request.getParameter("hotIds");
		String recommIds = request.getParameter("recommIds");
		if(hotIds==null&&recommIds==null){
			msg = "更新失败，请选择需要操作的电影";
		}else{
			if(hotIds!=null&&!"".equals(hotIds.trim())){
				msg = tvService.updateTvHot(hotIds, 0);
			}
			if(recommIds!=null&&!"".equals(recommIds.trim())){
				msg = tvService.updateTvRecomm(recommIds, 0);
			}
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	public void setTvService(TvService tvService) {
		this.tvService = tvService;
	}

	 

	public void setSnatchService(SnatchService snatchService) {
		this.snatchService = snatchService;
	}

	
	public TvModel getModel() {
		return tvModel;
	}
	
	 
	
}
