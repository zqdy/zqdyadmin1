package com.zqdy.dao.impl;

  
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.core.dao.BaseHibernateDaoSupport;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TQueryCount; 
import com.zqdy.dao.po.TUser;

public class commonDaoImpl  extends BaseHibernateDaoSupport  implements CommonDao {
	
	public TUser getUserByUserCodeAndPassword(String userCode, String password) {
		String hql = " from TUser where userCode='" + userCode + "' and password='"+ password + "' and status=1";
		TUser user = (TUser) this.uniqueFind(hql);
		 
		return user;
	}
	
	/**
	 * 调用查询电影存储过程
	 * @param year
	 * @param genre
	 * @param area
	 * @param source
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws Exception
	 */
	public CommonDto executeMovieProcedure(String year,Integer genre,Integer area,Integer source,
			int pageSize, int pageNow)throws Exception{
		
		CommonDto dto = new CommonDto();
		try
		{
			Session session = this.getSession();	
			
			Query queryCount = session.getNamedQuery("pro_getMoie_count");  
			queryCount.setString(0, year);
			queryCount.setInteger(1, genre);
			queryCount.setInteger(2, area);
			queryCount.setInteger(3, source);
			List countList = queryCount.list();
			if(countList!=null){
				TQueryCount qCount = (TQueryCount)countList.get(0);
				if(qCount!=null){
					Integer total = qCount.getId();
					int pageCount =0;
					int totalRow = total.intValue();
					if(totalRow>0){
						pageCount =(totalRow-1)/pageSize+1;

					}else{
						pageCount=0;
					}
					dto.setProperty("pageCount", pageCount);
				}
				
			}
			
			int startNum = pageSize*pageNow;
			Query query = session.getNamedQuery("pro_getMoie");  
			query.setString(0, year);
			query.setInteger(1, genre);
			query.setInteger(2, area);
			query.setInteger(3, source);
			query.setInteger(4, startNum);
			query.setInteger(5, pageSize);
			List movieList = query.list(); 
			dto.setProperty("result", movieList);
			
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		return dto;
	}
	
	/**
	 * 调用分页查询电视剧存储过程
	 * @param year
	 * @param genre
	 * @param area
	 * @param source
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws Exception
	 */
	public CommonDto executeTvProcedure(String year,Integer genre,Integer area,Integer source,
			int pageSize, int pageNow)throws Exception{
		
		CommonDto dto = new CommonDto();
		try
		{
			Session session = this.getSession();	
			
			Query queryCount = session.getNamedQuery("pro_getTv_count");  
			queryCount.setString(0, year);
			queryCount.setInteger(1, genre);
			queryCount.setInteger(2, area);
			queryCount.setInteger(3, source);
			List countList = queryCount.list();
			if(countList!=null){
				TQueryCount qCount = (TQueryCount)countList.get(0);
				if(qCount!=null){
					Integer total = qCount.getId();
					int pageCount =0;
					int totalRow = total.intValue();
					if(totalRow>0){
						pageCount =(totalRow-1)/pageSize+1;

					}else{
						pageCount=0;
					}
					dto.setProperty("pageCount", pageCount);
				}
				
			}
			
			int startNum = pageSize*pageNow;
			Query query = session.getNamedQuery("pro_getTv");  
			query.setString(0, year);
			query.setInteger(1, genre);
			query.setInteger(2, area);
			query.setInteger(3, source);
			query.setInteger(4, startNum);
			query.setInteger(5, pageSize);
			List movieList = query.list(); 
			dto.setProperty("result", movieList);
			
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		return dto;
	}
	
	/**
	 *  得到所有地区
	 * @return
	 */
	public List getAllArea(){
		String hql = " from TArea ";
		return this.findByHQL(hql);
	}
	
	/**
	 *  得到所有演员
	 * @return
	 */
	public List getAllActor(){
		String hql = " from TActor ";
		return this.findByHQL(hql);
	}
	
	/**
	 *  得到所有导演
	 * @return
	 */
	public List getAllDirector(){
		String hql = " from TDirector ";
		return this.findByHQL(hql);
	}
	
	/**
	 * 得到电影/电视剧类型
	 * @param type 0 电影，1电视视剧
	 * @return
	 */
	public List getAllGenre(){
		String hql = " from TGenre ";
		return this.findByHQL(hql);
	}
	
	
}
