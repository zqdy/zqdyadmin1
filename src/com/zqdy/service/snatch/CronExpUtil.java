package com.zqdy.service.snatch;

import java.util.Date; 

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger; 

import com.zqdy.core.common.Constants;
import com.zqdy.core.utils.DateConvert; 
import com.zqdy.dao.po.TScheduleModel;

public class CronExpUtil {
	
	private static Logger logger;
	
	static {
		logger = Logger.getLogger(CronExpUtil.class);
	}
	
 
	public static Trigger getTrigger(TScheduleModel scheduleModel){
		
		Trigger trigger = null;
		String cronExp = "";
		String runFormatStr = "";
		String hh = "";
		String mm = "";
		String startTime = "";
		String stopTime = "";
		String dateFormat14 = "yyyy-MM-dd HH:mm:ss";
		String runWeek = "";
		String runMonth = "";
		String runMonthDay = "";
		String autoRunTime = "";
		try
		{
			 
		 
			 
			String tempTriggerId = scheduleModel.getId()+"";
			runFormatStr =  scheduleModel.getAutoRunTime();
			startTime = scheduleModel.getStartTime();
			stopTime = scheduleModel.getStopTime();
			autoRunTime = scheduleModel.getAutoRunTime();
			
			if(Constants.RUNSTRATEGY_ONCE.equalsIgnoreCase(scheduleModel.getRunStrategy())){
				 
				  Date nowDate = new Date();
				  trigger = new SimpleTrigger(tempTriggerId,Constants.JOB_GROUP,new Date((nowDate.getTime() + 60 * 1000)),null,0,0l);
				  return trigger;
			}else if (Constants.RUNSTRATEGY_EVERY_DAY.equalsIgnoreCase(scheduleModel.getRunStrategy())) {
				 if(autoRunTime!=null&&autoRunTime.length()==4){
					 autoRunTime= "0"+autoRunTime;
				 }
				 
				 hh = autoRunTime.substring(0, 2);
				 mm = autoRunTime.substring(3, 5);
				 cronExp = "0 "+mm+" "+hh+" * * ?";  
				 
				 trigger = new CronTrigger(tempTriggerId,Constants.JOB_GROUP,cronExp);					 
				 trigger.setStartTime(DateConvert.getDate(startTime, dateFormat14));
				 trigger.setEndTime(DateConvert.getDate(stopTime,dateFormat14));
			 }else if(Constants.RUNSTRATEGY_EVERY_WEEK.equalsIgnoreCase(scheduleModel.getRunStrategy())){
				 
				 hh = autoRunTime.substring(0, 2);
				 mm = autoRunTime.substring(3, 5);
				 			
				 String strAutoRunWeek = "";
				 runWeek = scheduleModel.getRunWeek();
				 if("".equals(runWeek)){
					 strAutoRunWeek = "*";
				 }else{					 					
					 StringBuffer sbAutoRunWeek = new StringBuffer();
					 if (runWeek.contains("01")) {
						 sbAutoRunWeek.append("MON,");								
					 }  
					 if (runWeek.contains("02")) {
						 sbAutoRunWeek.append("TUE,");
					 }  
					 if (runWeek.contains("03")) {
						 sbAutoRunWeek.append("WED,");
					 }  
					 if (runWeek.contains("04")) {
						 sbAutoRunWeek.append("THU,");
					 }  
					 if (runWeek.contains("05")) {
						 sbAutoRunWeek.append("FRI,");
					 }  
					 if (runWeek.contains("06")) {
						 sbAutoRunWeek.append("SAT,");
					 } 
					 if (runWeek.contains("07")){
						 sbAutoRunWeek.append("SUN,");
					 }						 
					 
					 if(sbAutoRunWeek.toString().length()>0){
						 strAutoRunWeek =sbAutoRunWeek.delete(sbAutoRunWeek.length()-1,sbAutoRunWeek.length()).toString();
					 }					 
				 }
				 
				 
				 cronExp = "0 "+mm+" "+hh+" ?"+" * "+strAutoRunWeek ;				 			
				 
				 
				 trigger = new CronTrigger(tempTriggerId,Constants.JOB_GROUP,cronExp);		
				 trigger.setStartTime(DateConvert.getDate(startTime, dateFormat14));
				 trigger.setEndTime(DateConvert.getDate(stopTime,dateFormat14));
			 } else if(Constants.RUNSTRATEGY_EVERY_MONTH.equalsIgnoreCase(scheduleModel.getRunStrategy())){
				 
				 hh = autoRunTime.substring(0, 2);
				 mm = autoRunTime.substring(3, 5);
				 
				 
				 String strAutoRunMonth = "";
				 StringBuffer sbAutoRunMonth = new StringBuffer();		
				 runMonth = scheduleModel.getRunMonth();
				 runMonthDay = scheduleModel.getRunMonthDay();
				 /***
				  * һ����ʮ���¶�ִ��
				  */
				 if("".equals(runMonth)){
					 strAutoRunMonth = "1-12";			 					
				 }else{	
						 if (runMonth.contains("01")) {
							 sbAutoRunMonth.append("1,");
						 }   
						 if (runMonth.contains("02")) {
							 sbAutoRunMonth.append("2,");
						 } 
						 if (runMonth.contains("03")) {
							 sbAutoRunMonth.append("3,");
						 }  
						 if (runMonth.contains("04")) {
							 sbAutoRunMonth.append("4,");
						 }  
						 if (runMonth.contains("05")) {
							 sbAutoRunMonth.append("5,");
						 }  
						 if (runMonth.contains("06")){
							 sbAutoRunMonth.append("6,");
						 } 
						 if (runMonth.contains("07")) {
							 sbAutoRunMonth.append("7,");
						 }  
						 if (runMonth.contains("08")) {
							 sbAutoRunMonth.append("8,");
						 } 
						 if (runMonth.contains("09")) {
							 sbAutoRunMonth.append("9,");
						 }  
						 if (runMonth.contains("10")) {
							 sbAutoRunMonth.append("10,");
						 }  
						 if (runMonth.contains("11")) {
							 sbAutoRunMonth.append("11,");
						 }  
						 if (runMonth.contains("12")) {
							 sbAutoRunMonth.append("12,");
						 }						 
					 }
					 
					 if(sbAutoRunMonth.length()>0){
						 strAutoRunMonth = sbAutoRunMonth.delete(sbAutoRunMonth.length()-1,sbAutoRunMonth.length()).toString();
					 }										 
				  
				 				 
				 /***
				  * ��ɵ���������ʽ
				  */
				 cronExp = "0 "+mm+" "+hh+" "+runMonthDay+" "+strAutoRunMonth +" ?";			
				 					 
				 /***
				  * ���CronTrigger
				  */
				 trigger = new CronTrigger(tempTriggerId,Constants.JOB_GROUP,cronExp);		
				 trigger.setStartTime(DateConvert.getDate(startTime, dateFormat14));
				 trigger.setEndTime(DateConvert.getDate(stopTime,dateFormat14));	
			 } else if(Constants.RUNSTRATEGY_EVERY_TIME.equalsIgnoreCase(scheduleModel.getRunStrategy())){
				
				 int perHour = scheduleModel.getRunPeriodHour()==null?0:scheduleModel.getRunPeriodHour();
				 
				 long perMin = scheduleModel.getRunPeriodMinute()==null?0:scheduleModel.getRunPeriodMinute();
				  
				 long  per= perHour*60+perMin;
				 long intervalTime = per *60 *1000 ;
				 if(intervalTime==0){
					 intervalTime = 1*60*1000;
				 }
				 Date nowDate = new Date();

				 trigger = new SimpleTrigger(tempTriggerId,Constants.JOB_GROUP,new Date((nowDate.getTime() + 60 * 1000)),null,SimpleTrigger.REPEAT_INDEFINITELY,intervalTime);
				 trigger.setStartTime(DateConvert.getDate(startTime, dateFormat14));
				 trigger.setEndTime(DateConvert.getDate(stopTime,dateFormat14));					 				
			 }			 
		}
		catch(Exception ex){			
			ex.printStackTrace();
			logger.error("��ɱȶ�ģ��["+scheduleModel.getScheduleName()+" 运行时报错"+ex.getMessage());
		}				
		return trigger;
	}
	
	public static JobInfo getJobInfo(TScheduleModel scheduleModel){
		
		JobInfo jobInfo = null;
		
		String cronExp = "";
		String autoRunTime = "";
		String hh = "";
		String mm = "";
		String startTime = "";
		String stopTime = "";
		String dateFormat14 = "yyyyMMddHHmmss";
		String runWeek = "";
		String runMonth = "";
		String runMonthDay = "";
		try
		{
			/***
			 * ���ȶ�ģ�͵�����ʱ���������Ϊ"ȫ���ȶ�"��û����������ʱ����ԾͲ����Trigger
			 */
			if (null!=scheduleModel.getRunStrategy())
				return jobInfo;
			 autoRunTime = scheduleModel.getAutoRunTime();
			 startTime = scheduleModel.getStartTime();
			 stopTime = scheduleModel.getStopTime();
			 
			 //String tempTriggerId = StringUtil.getGuid32();
			 //tempTriggerId = StringUtil.replaceString(tempTriggerId, "-", "");
			
			if(Constants.RUNSTRATEGY_ONCE.equalsIgnoreCase(scheduleModel.getRunStrategy())){
								 				 
				jobInfo = new JobInfo();				  				  
				jobInfo.setTriggerType(Constants.JOB_TRIGGER_SIMPLETRIGGER);
				jobInfo.setRepeatCount(0);
				jobInfo.setRepeatInteral(0l);								  				
				return jobInfo;
			}else if (Constants.RUNSTRATEGY_EVERY_DAY.equalsIgnoreCase(scheduleModel.getRunStrategy())) {
				 hh = autoRunTime.substring(0, 2);
				 mm = autoRunTime.substring(3, 5);
				 cronExp = "0 "+mm+" "+hh+" * * ?";  
				 jobInfo = new JobInfo();				  				  
				 jobInfo.setTriggerType(Constants.JOB_TRIGGER_CRONIGGER);
				 jobInfo.setCronExp(cronExp);
				 jobInfo.setStartTime(startTime);
				 jobInfo.setEndTime(stopTime);				 
			 }
			 /***
			  * ����ʱ�����"�����ȶ�ÿ��"
			  */
			 else if(Constants.RUNSTRATEGY_EVERY_WEEK.equalsIgnoreCase(scheduleModel.getRunStrategy())){
			 
				 hh = autoRunTime.substring(0, 2);
				 mm = autoRunTime.substring(3, 5);			 
						
				 String strAutoRunWeek = "";				 
				 			 					
				 StringBuffer sbAutoRunWeek = new StringBuffer();
				 if (runWeek.contains("01")) {
					 sbAutoRunWeek.append("MON,");								
				 }  
				 if (runWeek.contains("02")) {
					 sbAutoRunWeek.append("TUE,");
				 }  
				 if (runWeek.contains("03")) {
					 sbAutoRunWeek.append("WED,");
				 }  
				 if (runWeek.contains("04")) {
					 sbAutoRunWeek.append("THU,");
				 }  
				 if (runWeek.contains("05")) {
					 sbAutoRunWeek.append("FRI,");
				 }  
				 if (runWeek.contains("06")) {
					 sbAutoRunWeek.append("SAT,");
				 } 
				 if (runWeek.contains("07")){
					 sbAutoRunWeek.append("SUN,");
				 }						 
				 
				 if(sbAutoRunWeek.toString().length()>0){
					 strAutoRunWeek =sbAutoRunWeek.delete(0,sbAutoRunWeek.length()-1).toString();
				 }						 
				 
				 
				 /***
				  * ��ɵ���������ʽ
				  */
				 cronExp = "0 "+mm+" "+hh+" ?"+" * "+strAutoRunWeek ;				 			
				 
				 /***
				  * ���CronTrigger
				  */
				 jobInfo = new JobInfo();				  				  
				 jobInfo.setTriggerType(Constants.JOB_TRIGGER_CRONIGGER);
				 jobInfo.setCronExp(cronExp);
				 jobInfo.setStartTime(startTime);
				 jobInfo.setEndTime(stopTime);						 				
			 }
			 /***
			  * ����ʱ�����"�����ȶ�ÿ��"
			  */
			 else if(Constants.RUNSTRATEGY_EVERY_MONTH.equalsIgnoreCase(scheduleModel.getRunStrategy())){
				 
				 /***
				  * ִ��ʱ��
				  */
				 hh = autoRunTime.substring(0, 2);
				 mm = autoRunTime.substring(3, 5);
				 
				 String strAutoRunMonth = "";
				 
				 /***
				  * һ����ʮ���¶�ִ��
				  */
				 if ("0000".equals(runMonth)) {
					 strAutoRunMonth = "1-12";			 					
				 }else{
					 StringBuffer sbAutoRunMonth = new StringBuffer();
					 
					 if (runMonth.contains("01")) {
						 sbAutoRunMonth.append("1,");
					 }   
					 if (runMonth.contains("02")) {
						 sbAutoRunMonth.append("2,");
					 } 
					 if (runMonth.contains("03")) {
						 sbAutoRunMonth.append("3,");
					 }  
					 if (runMonth.contains("04")) {
						 sbAutoRunMonth.append("4,");
					 }  
					 if (runMonth.contains("05")) {
						 sbAutoRunMonth.append("5,");
					 }  
					 if (runMonth.contains("06")){
						 sbAutoRunMonth.append("6,");
					 } 
					 if (runMonth.contains("07")) {
						 sbAutoRunMonth.append("7,");
					 }  
					 if (runMonth.contains("08")) {
						 sbAutoRunMonth.append("8,");
					 } 
					 if (runMonth.contains("09")) {
						 sbAutoRunMonth.append("9,");
					 }  
					 if (runMonth.contains("10")) {
						 sbAutoRunMonth.append("10,");
					 }  
					 if (runMonth.contains("11")) {
						 sbAutoRunMonth.append("11,");
					 }  
					 if (runMonth.contains("12")) {
						 sbAutoRunMonth.append("12,");
					 }						 
					 
					 
					 if(sbAutoRunMonth.toString().length()>0){
						 strAutoRunMonth = sbAutoRunMonth.toString().substring(0,sbAutoRunMonth.toString().length()-1);
					 }										 
				 }
				 				 
				  
				 cronExp = "0 "+mm+" "+hh+" "+runMonthDay+" "+strAutoRunMonth +" ?";			
				 
				 jobInfo = new JobInfo();				  				  
				 jobInfo.setTriggerType(Constants.JOB_TRIGGER_CRONIGGER);
				 jobInfo.setCronExp(cronExp);
				 jobInfo.setStartTime(startTime);
				 jobInfo.setEndTime(stopTime);		
			 }
			 
			 else if(Constants.RUNSTRATEGY_EVERY_TIME.equalsIgnoreCase(scheduleModel.getRunStrategy())){
				  
				 int perHour = scheduleModel.getRunPeriodHour()==null?0:scheduleModel.getRunPeriodHour();
				 
				 long perMin = scheduleModel.getRunPeriodMinute()==null?0:scheduleModel.getRunPeriodMinute();
				  
				 long  per= perHour*60+perMin;
				 long intervalTime = per * 60 * 1000;
				 if(intervalTime==0){
					 intervalTime = 1*60*1000;
				 }
				 jobInfo = new JobInfo();				  				  
				 jobInfo.setTriggerType(Constants.JOB_TRIGGER_SIMPLETRIGGER);
				 jobInfo.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
				 jobInfo.setRepeatInteral(intervalTime);
				 jobInfo.setStartTime(startTime);
				 jobInfo.setEndTime(stopTime);						 				 				
			 }			 
		}
		catch(Exception ex){			
			ex.printStackTrace();
			logger.error("��ɱȶ�ģ��["+scheduleModel.getScheduleName()+"  "+ex.getMessage());
		}				
		
		return jobInfo;
	}
}
