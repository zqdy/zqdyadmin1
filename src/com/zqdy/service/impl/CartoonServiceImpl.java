package com.zqdy.service.impl;

import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TCartoon;
import com.zqdy.service.CartoonService;
import com.zqdy.service.snatch.DoubanBo;

public class CartoonServiceImpl implements CartoonService{
	
	private CommonDao commonDao;
	
	/**
	 * 创建或者更新卡通
	 * @param douban
	 * @return
	 */
	public boolean createOrUpdateCartoon(DoubanBo douban,Integer source){
		boolean flag = false;
		if(douban==null||douban.getName()==null||"".equals(douban.getName())){
			return flag;
		}
		String name = douban.getName();
		String hql = " from TCartoon where name='"+name+"'";
		TCartoon cartoon = (TCartoon)commonDao.uniqueFind(hql);
		if(cartoon==null){
			
		}else{
			
		}
		return true;
	}
	
	/**
	 * 新建动漫卡通
	 * @param douban
	 * @param source
	 * @return
	 */
	public boolean addCartoon(DoubanBo douban){
		boolean addFlag = false;
		if(douban==null){
			return addFlag;
		}
		TCartoon cartoon = new TCartoon();
		
		
		return false;
	}
	
	public boolean addCartoonSub(){
		
		return false;
	}
	

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}
