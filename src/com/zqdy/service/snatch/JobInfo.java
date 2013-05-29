package com.zqdy.service.snatch;

 
import java.io.Serializable;

/**
 * <p>
 * 名称:JobInfo(作业详情模型)
 * </p>
 * <p>
 * 功能: 作业调度引擎处理的作业详情模型定义
 * </p>
 * <p>
 * Copyright: 深圳神盾信息技术有限公司 (c) 2010
 * </p>
 * <p>
 * 公司:深圳神盾信息技术有限公司
 * </p>
 * <p>
 * 修改历史记录 2008-12-18 原始架构
 * </p>
 */
public class JobInfo implements Serializable{
	
	//序列化Id
	private static final long serialVersionUID = -129270567580281173L;

	//作业Id
	private String jobId;
	
	//作业名称
	private String jobName;
	
	//作业组名称
	private String jobGroupName;
	
	//作业处理类类型
	private String jobClassType;
	
	//作业处理类型(添加或者删除)
	private String jobDealType;
	
	//作业数据个性化主键
	private String jobDataMapKey;
	
	//作业调度触发器类型(0为SimpleTrigger,1为CronTrigger)
	private String triggerType;
	
	//作业调度触发器重复调度次数
	private int repeatCount;
	
	//作业调度触发器调度间隔时间
	private long repeatInteral;
	
	//作业调度触发器调度开始时间
	private String startTime;
	
	//作业调度触发器调度结束时间
	private String endTime;
	
	//作业调度触发器调度日历表达式
	private String cronExp;
	
	//日志记录信息
	private String logInfo;

	/**
	 * 获取作业组名称
	 * @return String 作业组名称
	 */
	public String getJobGroupName() {
		return jobGroupName;
	}

	/**
	 * 设置作业组名称
	 * @param jobGroupName String 作业Id
	 */
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	/**
	 * 获取作业处理类类型
	 * @return String 作业处理类类型
	 */
	public String getJobClassType() {
		return jobClassType;
	}

	/**
	 * 设置作业处理类类型
	 * @param jobClassType String 作业处理类类型
	 */
	public void setJobClassType(String jobClassType) {
		this.jobClassType = jobClassType;
	}

	/**
	 * 获取作业Id
	 * @return String 作业Id
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * 设置作业Id
	 * @param jobId String 作业Id
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 获取日志记录信息
	 * @return String 日志记录信息
	 */
	public String getLogInfo() {
		return logInfo;
	}

	/**
	 * 设置日志记录信息
	 * @param logInfo String 日志记录信息
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	/**
	 * 获取作业数据个性化主键
	 * @return String 作业数据个性化主键
	 */
	public String getJobDataMapKey() {
		return jobDataMapKey;
	}

	/**
	 * 设置作业数据个性化主键
	 * @param jobDataMapKey String 作业数据个性化主键
	 */
	public void setJobDataMapKey(String jobDataMapKey) {
		this.jobDataMapKey = jobDataMapKey;
	}

	/**
	 * 获取作业处理类型(添加或者删除)
	 * @return String 作业处理类型(添加或者删除)
	 */
	public String getJobDealType() {
		return jobDealType;
	}

	/**
	 * 设置作业处理类型(添加或者删除)
	 * @param jobDealType String 作业处理类型(添加或者删除)
	 */
	public void setJobDealType(String jobDealType) {
		this.jobDealType = jobDealType;
	}

	/**
	 * 获取作业名称
	 * @return String 作业名称
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 设置作业名称
	 * @param jobName String 作业名称
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 获取作业调度触发器调度日历表达式
	 * @return String 作业调度触发器调度日历表达式
	 */
	public String getCronExp() {
		return cronExp;
	}

	/**
	 * 设置作业调度触发器调度日历表达式
	 * @param cronExp String 作业调度触发器调度日历表达式
	 */
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}

	/**
	 * 获取作业调度触发器调度结束时间
	 * @return String 作业调度触发器调度结束时间
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置作业调度触发器调度结束时间
	 * @param endTime String 作业调度触发器调度结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取作业调度触发器重复调度次数
	 * @return int 作业调度触发器重复调度次数
	 */
	public int getRepeatCount() {
		return repeatCount;
	}

	/**
	 * 设置作业调度触发器重复调度次数
	 * @param repeatCount int 作业调度触发器重复调度次数
	 */
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	/**
	 * 获取作业调度触发器调度间隔时间
	 * @return long 作业调度触发器调度间隔时间
	 */
	public long getRepeatInteral() {
		return repeatInteral;
	}

	/**
	 * 设置作业调度触发器调度间隔时间
	 * @param repeatInteral long 作业调度触发器调度间隔时间
	 */
	public void setRepeatInteral(long repeatInteral) {
		this.repeatInteral = repeatInteral;
	}

	/**
	 * 获取作业调度触发器调度开始时间
	 * @return String 作业调度触发器调度开始时间
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置作业调度触发器调度开始时间
	 * @param startTime String 作业调度触发器调度开始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取作业调度触发器类型(0为SimpleTrigger,1为CronTrigger)
	 * @return String 作业调度触发器类型(0为SimpleTrigger,1为CronTrigger)
	 */
	public String getTriggerType() {
		return triggerType;
	}

	/**
	 * 设置作业调度触发器类型(0为SimpleTrigger,1为CronTrigger)
	 * @param triggerType String 作业调度触发器类型(0为SimpleTrigger,1为CronTrigger)
	 */
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
}
