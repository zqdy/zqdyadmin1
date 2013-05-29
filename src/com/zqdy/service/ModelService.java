package com.zqdy.service;
 

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.po.TScheduleModel;
 
 
public interface ModelService {
	
	/**
	 * 分页得到数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition);
	
	public TScheduleModel getSchedulemodelById(Integer modelId);
	
	
	/**
	 * 创建一个对象
	 * @param object
	 * @return
	 */
	public TScheduleModel createObject(TScheduleModel schedulemodel);
	
	/**
	 * 更新一个对象
	 * @param object
	 * @return
	 */
	public TScheduleModel updateObject(TScheduleModel schedulemodel);
	
	/**
	 * 删除模型
	 * @param modelId
	 * @return
	 */
	public String delteScheduleModelById(String modelIds);
	
	/**
	 * 启动模型
	 * @param modelIds
	 * @return
	 */
	public String startScheduleModel(String modelIds);
	 
	/**
	 * 停止模型
	 * @param modelIds
	 * @return
	 */
	public String stopScheduleModel(String modelIds);
}
