package com.zqdy.service;

import com.zqdy.dao.po.TScheduleModel;

 

 
public interface ScheduleService {

	public void init(String quartzConfigureFileName);
	
	/***
	 *  ��ӱȶ�ģ��������Ȳ���
	 * @param bdModel
	 * @return
	 */
	public boolean addTask(TScheduleModel scheduleModel);
	
	/***
	 * �޸ıȶ�ģ��������Ȳ���
	 * @param bdModel
	 * @return
	 */
	//public boolean updateTask(BdbjModel bdModel);
	
	/***
	 * ���ñȶ�ģ��������Ȳ���
	 * @param bdModel
	 * @return
	 */
	public boolean deleteTask(TScheduleModel scheduleModel);	
	
	/**
	 * �ж�JobDetail�Ƿ����
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws Exception
	 */
	public boolean JobDetailIsExists(String jobName) throws Exception;
	
	/**
	 * ɾ��JobDetail
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws Exception
	 */
	public boolean romoveJob(String jobName) throws Exception;
}
