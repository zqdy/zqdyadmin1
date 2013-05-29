package com.zqdy.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.zqdy.core.action.BaseAction;
import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.service.IndexService;
import com.zqdy.service.SearchService;

public class SearchAction extends BaseAction  implements Action,ModelDriven<SearchModel>{
	
	private IndexService indexService;
	
	private SearchService searchService;
	
	SearchModel searchModel  = new SearchModel();
	
	/**
	 *  
	 * @return
	 */
	public String movieSearch(){
		CommonDto condtionDto = new CommonDto();
		Integer genre= searchModel.getGenre();
		Integer area= searchModel.getArea();
		Integer source= searchModel.getSource();
		String year = searchModel.getYear();
		if("0".equals(year)){
			year="";
			searchModel.setYear("0");
		}
		condtionDto.setProperty("genre", genre);
		condtionDto.setProperty("area", area);
		condtionDto.setProperty("source", source);
		condtionDto.setProperty("year", year);
		if(cpage==0){
			cpage=1;
		}
		
		pageSize= 30;
		CommonDto dto = (CommonDto) searchService.getPageMoviet(pageSize,
				cpage - 1, condtionDto);
		List movieList = (List) dto.getProperty("result");
		Integer pageCount = (Integer) dto.getProperty("pageCount");
		setTotal(pageCount);
		searchModel.setMovieList(movieList);	
		
		
		List genreList = searchService.getMovieGenre();		
		List areaList = searchService.getAreaList();		
		List yearList = searchService.getYear();
		List sourceList = searchService.getSource();
		searchModel.setGenreList(genreList);
		searchModel.setAreaList(areaList);
		searchModel.setYearList(yearList);
		searchModel.setSourceList(sourceList);
		return SUCCESS;
	}
	
	/**
	 *  
	 * @return
	 */
	public String tvSearch(){
		CommonDto condtionDto = new CommonDto();
		Integer genre= searchModel.getGenre();
		Integer area= searchModel.getArea();
		Integer source= searchModel.getSource();
		String year = searchModel.getYear();
		if("0".equals(year)){
			year="";
			searchModel.setYear("0");
		}
		condtionDto.setProperty("genre", genre);
		condtionDto.setProperty("area", area);
		condtionDto.setProperty("source", source);
		condtionDto.setProperty("year", year);
		
		if(cpage==0){
			cpage=1;
		}
		pageSize= 30;
		CommonDto dto = (CommonDto) searchService.getPageTv(pageSize,
				cpage - 1, condtionDto);
		List tvList = (List) dto.getProperty("result");
		Integer pageCount = (Integer) dto.getProperty("pageCount");
		this.setTotal(pageCount);
		searchModel.setTvList(tvList);	
		
		
		List genreList = searchService.getTvGenre();		
		List areaList = searchService.getAreaList();		
		List yearList = searchService.getYear();
		List sourceList = searchService.getSource();
		searchModel.setGenreList(genreList);
		searchModel.setAreaList(areaList);
		searchModel.setYearList(yearList);
		searchModel.setSourceList(sourceList);
		return SUCCESS;
	}
	/**
	 * 搜索结果页
	 * @return
	 */
	public String searchResult(){
		List sourceList = searchService.getSource();
		searchModel.setSourceList(sourceList);
		String name = searchModel.getKeyword();
		if(name!=null&&!"".equals(name)){

			try {
				// String str=new String(name.getBytes("UTF-8"),"gb2312");
				name = java.net.URLDecoder.decode(name,"UTF-8");
				name = name.trim();
				this.setSearchName(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//对查询名称进行处理，防止SQL注入攻击
			
			int serverId = searchModel.getSource();
			List movieList = searchService.getMovieByName(name);
			List tvList = searchService.getTvByName(name);
			pageSize=20;
			//分页查询，电影，电影视剧，电视剧子集信息
			CommonDto dto = (CommonDto) searchService.getViewResult(pageSize,cpage - 1, serverId,name);
			if(dto!=null){
				List viewList = (List) dto.getProperty("result");
				Integer pageCount = (Integer) dto.getProperty("pageCount");
				this.setTotal(pageCount);
				searchModel.setViewList(viewList);
			}
			
			searchModel.setMovieList(movieList);
			searchModel.setTvList(tvList);
			
		}		
		return SUCCESS;
	}
	
	public SearchModel getModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}


	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

}
