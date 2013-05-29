package com.zqdy.service.snatch;

import org.apache.log4j.Logger; 
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.StatefulJob; 

import com.zqdy.core.common.Constants;
import com.zqdy.core.utils.SpringUtils;
import com.zqdy.dao.po.TScheduleModel; 
import com.zqdy.dao.po.TSource;
import com.zqdy.service.ModelService;
import com.zqdy.service.SnatchService;
 

public class AutoRunTaskJob implements StatefulJob{
	
	private static Logger logger = Logger.getLogger(AutoRunTaskJob.class);
	
	public void execute(JobExecutionContext context){	 
		
		 JobDataMap data = context.getJobDetail().getJobDataMap();
		 String bdSchemaId = (String) data.get(Constants.JOB_DATAMAP_SCHEMAID);
		 ModelService modelService =  (ModelService)SpringUtils.getBean("modelService");
		 
		 
		 TScheduleModel schedulemodel = modelService.getSchedulemodelById(Integer.valueOf(bdSchemaId));
		 if(schedulemodel!=null){
			 SnatchService snatchService =  (SnatchService)SpringUtils.getBean("snatchService");
			 
			 String schedulName = schedulemodel.getScheduleName();
			 logger.info("---schedulName="+schedulName);
			 Integer scheduleType = schedulemodel.getScheduleType();
			 
			 TSource server = schedulemodel.getSource();
			 Integer tvType = schedulemodel.getTvType();
			 if(server!=null){
				 String charSet = server.getCharSet();
				
				 if(scheduleType==1){   //抓取链接
					 String url = schedulemodel.getLinkUrl();
					 String regex = schedulemodel.getLinkRegex();					
					 snatchService.snatchMovieLink(url, regex, server.getId(),charSet,tvType);
				 } 
			 }
			 
			 if(scheduleType==2){     //抓取详情
				 String regexEmbed = schedulemodel.getEmbedRegex();
				 String regexDetail = schedulemodel.getDetailregex();
				 Integer snatchSize = schedulemodel.getSnatchSize();
				 snatchService.snatchDetail(regexEmbed,regexDetail,tvType,false,snatchSize);
			 } else if(scheduleType==3){      //定时更新电视剧，最新集数
				 snatchService.snatchTvSubTv(0);
			 } else	 if(scheduleType==4){      //抓取上次未抓取成功的电视子集
				 snatchService.snatchSubTv();
			 }else	 if(scheduleType==100){      //抓取八度資源
				 snatchService.snatchBdzy();
			 }
			
		 }
	}
}
