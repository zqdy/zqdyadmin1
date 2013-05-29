package com.zqdy.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.zqdy.core.common.Constants; 
import com.zqdy.core.utils.DateConvert;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TScheduleModel;
import com.zqdy.service.ScheduleService;
import com.zqdy.service.snatch.AutoRunTaskJob;
import com.zqdy.service.snatch.CronExpUtil;

public class ScheduleServiceImp implements ScheduleService{
	
	private static Logger logger;
	private  static Scheduler scheduler;
	 
	private CommonDao commonDao;
	
	public ScheduleServiceImp(){
		logger = Logger.getLogger(ScheduleServiceImp.class);
		 					
	}
	
	public void setScheduler(Scheduler scheduler){
		this.scheduler = scheduler;	
	}
	
	public void init(String quartzConfigureFileName){
		try{
			/**
			 * 加载Quartz配置文件
			 */
	        Properties props = new Properties();
	        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(quartzConfigureFileName);
	        if(in != null){
		        props.load(in);
	        }else{
	        	throw new Exception("属性文件【" + quartzConfigureFileName + "】不可读！can't read");
	        }
			
			/**
			 * 初始化并启动Quartz Schedule
			 */
			StdSchedulerFactory factory = new StdSchedulerFactory();
			factory.initialize(props);
			scheduler = factory.getScheduler();
			scheduler.start();
			initAutoRunTask();
//			/**
//			 * 当配置为内存存储模式时,每次初始化时都需要手工将任务加载到内存
//			 */
//			String jobStoreClass = props.getProperty("org.quartz.jobStore.class");
//			if(jobStoreClass!=null|| jobStoreClass.trim().equals("org.quartz.simpl.RAMJobStore")){
//				initAutoRunTask();
//			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("【异常】启动调度管理器出常异常:" + ex.getMessage());
		}
	}
		
	private void initAutoRunTask(){
		
		logger.info("【初始化】正在初始化模型任务调度策略 begin");
		String hql = " from TScheduleModel where isActive=1 ";
		List modelList = commonDao.findByHQL(hql);
		for (int i=0;i<modelList.size();i++) {
			TScheduleModel schedulemodel = (TScheduleModel)modelList.get(i);	
			
			try
			{				
				 /**
				 * 获取当前日期
				 */
				 Date nowDate = new Date();
				 /***
				  * 判断当前时间是否大于模型运行时间策略设置的停止时间
				  */
				 
				 boolean b1= false ;
				 boolean b2 = !Constants.RUNSTRATEGY_ONCE.equalsIgnoreCase(schedulemodel.getRunStrategy());
				 boolean b3 =false;
				 if(schedulemodel.getIsActive()!=null){
					 int inActive = schedulemodel.getIsActive().intValue();
					 if(inActive==1){
						 b3 =true;
					 }
				 }
				 if(schedulemodel.getStopTime()!=null){
					 Date d = DateConvert.getDate(schedulemodel.getStopTime(),"yyyy-MM-dd HH:mm:ss");
					 if(d!=null){
						 b1= nowDate.before(d) ;
					 }
					 
				 }
				 if(b3 &&b1 && b2){
					 logger.info("【初始化】正在初始化模型 --" + schedulemodel.getScheduleName() + "--的任务调度策略");
					 /***
					  * 初始化模型的计划(源目表与目标表)
					  */
						String schemaId = schedulemodel.getId()+"";
						String modelName = schedulemodel.getScheduleName();						
						/***
						 * 创建计划工作
						 */
						JobDetail jobDetail = new JobDetail("job_" + modelName + "[" + schemaId +"]",Constants.JOB_GROUP,AutoRunTaskJob.class);
						jobDetail.getJobDataMap().put(Constants.JOB_DATAMAP_SCHEMAID,schemaId);
						jobDetail.setDurability(true);
						
						/**
						 * 根据模型运行时间策略创建相应触发器
						 */
						Trigger trigger = CronExpUtil.getTrigger(schedulemodel);
						trigger.setName("trigger_" + modelName + "[" + schemaId +"]");
						trigger.setJobName(jobDetail.getName());
						trigger.setJobGroup(jobDetail.getGroup());
						
						/**
						 * 添加到调度管理器中
						 */
						if(trigger != null){
							logger.info("【初始化】模型 ---" + schedulemodel.getScheduleName() +"添加到调度中");
							scheduler.addJob(jobDetail, true);
							scheduler.scheduleJob(trigger);
							//scheduler.scheduleJob(jobDetail,trigger);
						}											
				 }
			}
			catch(Exception ex){				
				logger.info("【初始化】初始化模型 ---" + schedulemodel.getScheduleName() + "--- 的任务调度策略时出现异常:" + ex.getMessage());
				ex.printStackTrace();
			}			
		}	
		logger.info("【初始化】成功初始化系统的模型任务调度策略 end ");
	}
	 
	/***
	 *  添加模型任务调度策略
	 * @param bdModel
	 * @return
	 */
	public boolean addTask(TScheduleModel scheduleModel){
		boolean succ = false;
		try {									 
			 if(scheduleModel != null){
				 logger.info("正在启动模型:[" + scheduleModel.getScheduleName()+ "]的任务调度策略-------");
				 /***
				  * 初始化模型的计划(源目表与目标表)
				  */
					String schemaId = scheduleModel.getId()+"";
					String modelName = scheduleModel.getScheduleName();
					/***
					 * 创建计划工作
					 */
					JobDetail jobDetail = new JobDetail("job_" + modelName + "[" + schemaId +"]",Constants.JOB_GROUP,AutoRunTaskJob.class);
					jobDetail.getJobDataMap().put(Constants.JOB_DATAMAP_SCHEMAID,schemaId);
					jobDetail.setDurability(true);
					
					/**
					 * 根据模型运行时间策略创建相应触发器
					 */
					Trigger trigger = CronExpUtil.getTrigger(scheduleModel);
					trigger.setName("trigger_" + modelName + "[" + schemaId +"]");
					trigger.setJobName(jobDetail.getName());
					trigger.setJobGroup(jobDetail.getGroup());
					
					/**
					 * 添加到调度管理器中
					 */
					if(trigger != null){
						logger.info(" 模型:[" + scheduleModel.getScheduleName()+"]启动");
						scheduler.addJob(jobDetail, true);
						scheduler.scheduleJob(trigger);
						succ = true;
					}											
			 }			 			 						 						
		}catch (Exception ex) {
			logger.info("-----启动名为:<<" + scheduleModel.getScheduleName()+ ">>的任务调度策略失败,原因: -------");
			logger.info(ex);
			ex.printStackTrace();
			throw new RuntimeException("启动名为:<<" + scheduleModel.getScheduleName()+ ">>的任务调度策略失败,原因" + ex.getMessage(), ex);
		}
		return succ;		
	}
	
	/***
	 * 修改模型任务调度策略
	 * @param ScheduleModel
	 * @return
	 */
//	public boolean updateTask(ScheduleModel ScheduleModel){
//		boolean succ = false;
//		boolean isRunning = false;
//		try {
//			logger.info("【】-----修改名为:<<" + ScheduleModel.getModelName()+ ">>的任务调度策略 -------");
//			if(ScheduleModel != null){
//				
//				/***
//				  * 初始化模型的计划(源目表与目标表)
//				  */
//				for (Iterator schemeIter = ScheduleModel.getBdbjSchemas().iterator(); schemeIter.hasNext();) {
//					BdbjSchema bdSchema = (BdbjSchema)schemeIter.next();					
//					isRunning = isBdTaskRunning(bdSchema);					
//					break;
//				}				
//				
//				if (isRunning && "1".equals(ScheduleModel.getIsActive())){
//					for (Iterator schemeIter = ScheduleModel.getBdbjSchemas().iterator(); schemeIter.hasNext();) {
//						BdbjSchema bdSchema = (BdbjSchema)schemeIter.next();		
//						/**
//						 * 修改模型调度作业
//						 */
//						scheduler.deleteJob(bdSchema.getSchemaId(),BdbjConstants.BD_JOB_GROUP);
//						
//						/***
//						 * 创建计划工作
//						 */
//						JobDetail jobDetail = new JobDetail(bdSchema.getSchemaId(),BdbjConstants.BD_JOB_GROUP,AutoRunBdTaskJob.class);
//						jobDetail.getJobDataMap().put(BdbjConstants.BD_JOB_DATAMAP_SCHEMAID,bdSchema.getSchemaId());
//						
//						/**
//						 * 根据模型运行时间策略创建相应触发器
//						 */
//						Trigger trigger = CronExpUtil.getTrigger(ScheduleModel);
//						
//						scheduler.scheduleJob(jobDetail, trigger);
//						succ = true;
//					}					
//				}else if(isRunning && "0".equals(ScheduleModel.getIsActive())){
//					deleteBdTask(ScheduleModel);
//				}else if(!isRunning && "1".equals(ScheduleModel.getIsActive())){
//					addBdTask(ScheduleModel);
//				}
//			}							
//		}catch (Exception ex) {
//			logger.info("-----修改名为:<<" + ScheduleModel.getModelName()+ ">>的任务调度策略失败,原因: -------");			
//			logger.info(ex);
//			throw new RuntimeException("修改名为:<<" + ScheduleModel.getModelName()+ ">>的任务调度策略失败,原因:", ex);
//		}
//		return succ;
//	}
	
	/***
	 * 禁用模型任务调度策略
	 * @param ScheduleModel
	 * @return
	 */
	public boolean deleteTask(TScheduleModel scheduleModel){
		boolean succ = false;
		try {
			logger.info("-----禁用名为:<<" + scheduleModel.getScheduleName()+ ">>的任务调度策略 -------");
		 
				String schemaId = scheduleModel.getId()+"";
				String modelName = scheduleModel.getScheduleName();				
				/**
				 * 修改模型调度作业
				 */
				scheduler.deleteJob("job_" + modelName + "[" + schemaId +"]",Constants.JOB_GROUP);
				succ = true;
			 				
		} catch (Exception ex) {
			logger.info("-----禁用名为:<<" + scheduleModel.getScheduleName()+ ">>的任务调度策略失败,原因: -------");
			logger.info(ex);
			throw new RuntimeException("禁用名为:<<" + scheduleModel.getScheduleName()+ ">>的任务调度策略失败,原因:", ex);
		}
		return succ;
	}
	
	/**
	 * 判断JobDetail是否存在
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws Exception
	 */
	public boolean JobDetailIsExists(String jobName) throws Exception {

		JobDetail jobDetail = scheduler.getJobDetail(jobName, Constants.JOB_GROUP);

		return jobDetail != null;
	}	
	
	/**
	 * 删除JobDetail
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws Exception
	 */
	public boolean romoveJob(String jobName) throws Exception {

		return scheduler.deleteJob(jobName, Constants.JOB_GROUP);

	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	
	
	/**
	 * 判断模型方案作业是否正在运行中
	 * 
	 * @param bdSchema
	 * @return
	 */
//	private boolean isBdTaskRunning(BdbjSchema bdSchema) throws Exception {
//		boolean find = false;
//		List jobs = this.getJobsByGroupName(BdbjConstants.BD_JOB_GROUP);
//		if (!jobs.isEmpty()) {
//			for (Iterator it = jobs.iterator(); it.hasNext();) {
//				JobDetail jobDetail = (JobDetail) it.next();
//				if (jobDetail.getName().equals(bdSchema.getSchemaId())) {
//					find = true;
//				}
//			}
//		}
//		return find;
//	}
	
	/**
	 * 根据groupName获取jobs深度
	 * 
	 * @param groupName
	 * @return
	 * @throws Exception
	 */
//	private List getJobsByGroupName(String groupName) throws Exception {
//		List jobList = new ArrayList();
//		try {
//			String[] jobs = scheduler.getJobNames(groupName);
//			for (int j = 0; j < jobs.length; j++) {
//				String job = jobs[j];
//				JobDetail jobDetail = scheduler.getJobDetail(job, groupName);
//				String key = job + groupName;
//				if (!jobList.contains(key)) {
//					jobList.add(jobDetail);
//				}
//			}
//		} catch (SchedulerException e) {
//			throw new Exception("读取作业调度JOS出现异常.", e);
//		}
//		return jobList;
//	}	
}
