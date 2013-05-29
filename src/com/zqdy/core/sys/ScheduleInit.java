package com.zqdy.core.sys;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.zqdy.core.utils.SpringUtils;
import com.zqdy.service.ScheduleService;


 

public class ScheduleInit extends HttpServlet{
	
	private static final long serialVersionUID = 6813773135180089689L;
	
	private static Logger logger;
	
	private static boolean initFlag = false;
	
	public ScheduleInit(){
		 logger = Logger.getLogger(ScheduleInit.class);
		 try {
			
				 init();
			 
			
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* ��ʼ����ҵ����
	*/
	public void init() throws ServletException { 
		
		 try{
			 
			 /***
			  * ��ȡ�ȶԲ�����ҵ���ȷ���
			  */
			 ScheduleService  scheduleService = (ScheduleService) SpringUtils.getBean("scheduleService");
			 String quartzConfigureFileName = "quartzDefault.properties";
//			 if(quartzConfigureFileName==null){
//				 throw new Exception("没有在web.xml文件中配置名为quartzConfigureFileName的<context-param>参数!");
//			 }
			 if(!initFlag){
				 initFlag = true;
				 scheduleService.init(quartzConfigureFileName);
			 }
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
			 logger.info(ex.getMessage());
		 }		 		 
		 super.init();
	}
}
