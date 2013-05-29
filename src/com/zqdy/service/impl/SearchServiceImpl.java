package com.zqdy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.NameValueBo;
import com.zqdy.service.SearchService;


@SuppressWarnings("rawtypes")
public class SearchServiceImpl implements SearchService {
	
	private CommonDao commonDao;
	

	/**
	 * 分页得到電影数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getPageMoviet(int pageSize, int pageNow,CommonDto conditionDto){
		
		String year = (String)conditionDto.getProperty("year");
		Integer genre = (Integer)conditionDto.getProperty("genre");
		Integer area= (Integer)conditionDto.getProperty("area");
		Integer source= (Integer)conditionDto.getProperty("source");
		CommonDto commonDto = new CommonDto();
		try{
			commonDto = commonDao.executeMovieProcedure(year, genre, area, source, pageSize, pageNow);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return commonDto;
	}
	/**
	 * 分页得到电视剧数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getPageTv(int pageSize, int pageNow,CommonDto conditionDto){
		
		String year = (String)conditionDto.getProperty("year");
		Integer genre = (Integer)conditionDto.getProperty("genre");
		Integer area= (Integer)conditionDto.getProperty("area");
		Integer source= (Integer)conditionDto.getProperty("source");
		CommonDto commonDto = new CommonDto();
		try{
			commonDto = commonDao.executeTvProcedure(year, genre, area, source, pageSize, pageNow);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return commonDto;
	}
	
	/**
	 * 通过电影名称查询电影
	 * @param movieName
	 * @return
	 */
	public List getMovieByName(String movieName){
		String hql = " from TMovie where name like  '%"+movieName+"%'";
		 
		return commonDao.getListByHqlAndCount(hql,2);
	}
	
	/**
	 * 通过电视剧名称查询电视剧
	 * @param movieName
	 * @return
	 */
	public List getTvByName(String movieName){
		String hql = " from TTv where name like  '%"+movieName+"%'";
		return commonDao.getListByHqlAndCount(hql,2);
	}
	 
	 
	/**
	 * 通过名称查询电视、电影、电子剧子集住处
	 * @param movieName
	 * @return
	 */
	public CommonDto getViewResult(int pageSize, int pageNow,int serverId, String name) {
		CommonDto dto = null;
		String poClass = "Allview";
		StringBuffer condition = new StringBuffer(" where 1=1 ");
		if(name!=null){
			name = name.replace("delete", "");
			name = name.replace("update", "");
			name = name.replace("select", "");
			name = name.replace("drop", "");
			name = name.replace("'", "");
			condition.append(" and name like '%"+name+"%' ");
		}
		if(serverId!=0){
			condition.append(" and id.serverId="+serverId);
		}
		try{
			dto= commonDao.getResult(pageSize, pageNow, poClass, condition.toString());
		}catch(Exception e){
			
		}
		return dto;
	}
	/**
	 * 得到电影类型
	 * @return
	 */
	public List  getSource(){
		String hql = " from TSnatchServer ";
		return commonDao.findByHQL(hql);
	}
	
	/**
	 * 得到电影类型
	 * @return
	 */
	public List  getMovieGenre(){
		String hql = " from TGenre where type=0";
		return commonDao.findByHQL(hql);
	}
	
	/**
	 * 得到电视剧类型
	 * @return
	 */
	public List  getTvGenre(){
		String hql = " from TGenre where type=1";
		return commonDao.findByHQL(hql);
	}
	
	/**
	 * 得到电影类型
	 * @return
	 */
	public List  getAreaList(){
		String hql = " from TArea where name !=null";
		return commonDao.findByHQL(hql);
	}
	
	/**
	 * 得到年代 
	 * @return
	 */
	public List  getYear(){
		List  yearList = new ArrayList ();
		yearList.add(new NameValueBo("2013","2013"));	
		yearList.add(new NameValueBo("2012","2012"));
		yearList.add(new NameValueBo("2011","2011"));
		yearList.add(new NameValueBo("2010","2010"));
		yearList.add(new NameValueBo("2009","2009"));
		yearList.add(new NameValueBo("2008","2008"));
		yearList.add(new NameValueBo("2007","2007"));
		yearList.add(new NameValueBo("2006","2006"));
		yearList.add(new NameValueBo("2005","2005"));
		yearList.add(new NameValueBo("2004","2004"));
		yearList.add(new NameValueBo("2003","2003"));
		yearList.add(new NameValueBo("2002","2002"));
		yearList.add(new NameValueBo("2001","2001"));
		yearList.add(new NameValueBo("2000","2000"));
		yearList.add(new NameValueBo("90年代","90"));
		yearList.add(new NameValueBo("80年代","80"));
		yearList.add(new NameValueBo("70年代","70")); 
		
		return yearList;
	}
	
	

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}	
}
