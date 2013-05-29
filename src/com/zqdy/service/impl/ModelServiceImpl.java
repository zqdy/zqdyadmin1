package com.zqdy.service.impl;

import java.util.List;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TScheduleModel;
import com.zqdy.service.ModelService;
import com.zqdy.service.ScheduleService;

public class ModelServiceImpl implements ModelService {
	
	private CommonDao commonDao;
	
	private ScheduleService scheduleService;
	
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition) {
		return commonDao.getResult(pageSize, pageNow, poClass, condition);
	}
	public TScheduleModel getSchedulemodelById(Integer modelId){
		if(modelId==null||modelId==0){
			return null;
		}
		String hql = " from TScheduleModel where id="+modelId;		
		return (TScheduleModel)commonDao.uniqueFind(hql);
	}
	/**
	 * 创建一个对象
	 * @param object
	 * @return
	 */
	public TScheduleModel createObject(TScheduleModel schedulemodel){
		return (TScheduleModel)commonDao.create(schedulemodel);
	}
	/**
	 * 删除模型
	 * @param modelId
	 * @return
	 */
	public String delteScheduleModelById(String modelIds){
		StringBuffer deleteMsg = new StringBuffer();
		if(modelIds==null||"".equals(modelIds.trim())){
			deleteMsg.append("未有要删除的数据");
			return deleteMsg.toString();
		}
		StringBuffer idsb = new StringBuffer();
		String[] arrsIds = modelIds.split(","); 
		for(int i=0;i<arrsIds.length;i++){
			idsb.append("'"+arrsIds[i]+"',");
		}
		if(idsb.length()>1){
			idsb.delete(idsb.length()-1, idsb.length());
			String hql1 =" from TScheduleModel where isActive =1 and id in ( "+idsb.toString()+" )";
			List modelList = commonDao.findByHQL(hql1);
			if(modelList!=null&&modelList.size()>0){
				for(int i=0;i<modelList.size();i++){
					TScheduleModel model = (TScheduleModel)modelList.get(i);
					deleteMsg.append(model.getScheduleName()+"，");
				}
				deleteMsg.append("正在运行当中，不能删除");
			}else{
				String hql = " delete from TScheduleModel where id in ( "+idsb.toString()+" )";
				commonDao.executeQuery(hql);
				deleteMsg.append("删除成功");
			}
		}
		return deleteMsg.toString();
	}
	
	/**
	 * 启动模型
	 * @param modelIds
	 * @return
	 */
	public String startScheduleModel(String modelIds){
		StringBuffer startUpMsg = new StringBuffer();
		if(modelIds==null||"".equals(modelIds.trim())){
			startUpMsg.append("未有要启动的模型");
			return startUpMsg.toString();
		}
		StringBuffer idsb = new StringBuffer();
		String[] arrsIds = modelIds.split(","); 
		for(int i=0;i<arrsIds.length;i++){
			idsb.append("'"+arrsIds[i]+"',");
		}
		if(idsb.length()>1){
			idsb.delete(idsb.length()-1, idsb.length());
			String hql1 =" from TScheduleModel where  id in ( "+idsb.toString()+" )";
			List modelList = commonDao.findByHQL(hql1);
			if(modelList!=null&&modelList.size()>0){
				for(int i=0;i<modelList.size();i++){
					TScheduleModel model = (TScheduleModel)modelList.get(i);
					if(model.getIsActive()!=null&&model.getIsActive()==1){
						startUpMsg.append(model.getScheduleName()+"，");
					}else{
						scheduleService.addTask(model);
						model.setIsActive(1);
						commonDao.update(model);
					}
				}
				if(startUpMsg.length()>0){
					startUpMsg.append("模型已经启动");
				}else{
					startUpMsg.append("启动模型成功");
				}
			} 
		}
		return startUpMsg.toString();
	}
	 
	/**
	 * 停止模型
	 * @param modelIds
	 * @return
	 */
	public String stopScheduleModel(String modelIds){
		StringBuffer startUpMsg = new StringBuffer();
		if(modelIds==null||"".equals(modelIds.trim())){
			startUpMsg.append("未有要停止的模型");
			return startUpMsg.toString();
		}
		StringBuffer idsb = new StringBuffer();
		String[] arrsIds = modelIds.split(","); 
		for(int i=0;i<arrsIds.length;i++){
			idsb.append("'"+arrsIds[i]+"',");
		}
		if(idsb.length()>1){
			idsb.delete(idsb.length()-1, idsb.length());
			String hql1 =" from TScheduleModel where  id in ( "+idsb.toString()+" )";
			List modelList = commonDao.findByHQL(hql1);
			if(modelList!=null&&modelList.size()>0){
				for(int i=0;i<modelList.size();i++){
					TScheduleModel model = (TScheduleModel)modelList.get(i);
					scheduleService.deleteTask(model);
					model.setIsActive(0);
					commonDao.update(model);
				}
				startUpMsg.append("停止模型成功");
			} 
		}
		return startUpMsg.toString();
	}
	
	/**
	 * 更新一个对象
	 * @param object
	 * @return
	 */
	public TScheduleModel updateObject(TScheduleModel schedulemodel){
		return (TScheduleModel)commonDao.update(schedulemodel);
	}
	
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	 

}
