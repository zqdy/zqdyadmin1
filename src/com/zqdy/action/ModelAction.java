package com.zqdy.action;
 
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
 
import com.zqdy.core.action.BaseAction;
import com.zqdy.core.common.dto.CommonDto; 
import com.zqdy.dao.po.TScheduleModel;
import com.zqdy.service.ModelService;
import com.zqdy.service.ScheduleService;
import com.zqdy.service.SnatchService;



public class ModelAction extends BaseAction {
	
	private ModelService modelService;
	 
	
	private SnatchService snatchService;
	
	private TScheduleModel schedulemodel;
	
	private String modelId;
	
	private String modelName;
	
	private String sourceServer;
	
	public String goScheduleModelList(){
		init();
		StringBuffer hql = new StringBuffer(" where 1=1 ");
		if(modelName!=null){
			hql.append(" and scheduleName like '%"+modelName.trim()+"%' ");
		}
		if(sourceServer!=null&&!"0".equals(sourceServer)){
			hql.append(" and snatchServer.id="+sourceServer+" ");
		}
		pageSize=50;
		CommonDto dto = (CommonDto) modelService.getResult(pageSize,
				cpage - 1, " TScheduleModel ", hql.toString());
		resultList = (List) dto.getProperty("result");
		Integer pageCount = (Integer) dto.getProperty("pageCount");
		this.setTotal(pageCount);
		
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	
	public String addScheduleModel(){
		init();
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	public String doAddScheduleModel(){
		init();
		String runWeek = this.getWeekStr(request);
		String runMonth = this.getMonthStr(request);
		if(schedulemodel!=null){
			schedulemodel.setRunWeek(runWeek);
			schedulemodel.setRunMonth(runMonth);
			schedulemodel.setIsActive(0);
			schedulemodel.setAddTime(new Date());
			schedulemodel.setSnatchSize(30);
//			if(schedulemodel.getSnatchServer().getId()==0){
//				schedulemodel.setSnatchServer(null);
//			}
			 
			modelService.createObject(schedulemodel);
			
		}
		msg = "新增成功";
		outMsg(msg);
		return null;
	}
	public String editScheduleModel(){
		init();
		
		String modelId = request.getParameter("modelId");
		schedulemodel = modelService.getSchedulemodelById(Integer.valueOf(modelId));
		
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	public String doEditScheduleModel(){
		msg = "修改成功";
		try {
			init();
			String runWeek = this.getWeekStr(request);
			String runMonth = this.getMonthStr(request);
			if(schedulemodel!=null){
				schedulemodel.setRunWeek(runWeek);
				schedulemodel.setRunMonth(runMonth);
				if(schedulemodel.getSource().getId()==0){
					schedulemodel.setSource(null);
				}
				schedulemodel.setSnatchSize(30);
				modelService.updateObject(schedulemodel);
			}			

		} catch (Exception e) {
			msg = "修改失败";
			e.printStackTrace();
		}
		outMsg(msg);
		return null;
	}

	public String deleteSchedule(){
		init();
		String modelIds = request.getParameter("modelIds");
		msg = modelService.delteScheduleModelById(modelIds);
		outMsg(msg);
		return null;
	}
	
	public String startScheduleModel(){
		init();
		String modelIds = request.getParameter("modelIds");
		msg = modelService.startScheduleModel(modelIds);
		outMsg(msg);
		return null;
	}
	public String stopScheduleModel(){
		init();
		String modelIds = request.getParameter("modelIds");
		msg = modelService.stopScheduleModel(modelIds);
		outMsg(msg);
		return null;
	}
	
	/**
	 * 得到运行周
	 * @param request
	 * @return
	 */
	public String getWeekStr(HttpServletRequest request){
		String runWeek = "";
		String[] runWeeks = request.getParameterValues("runWeek");
		if(runWeeks!=null){
			for(int i=0;i<runWeeks.length;i++){
				runWeek = runWeek+runWeeks[i]+",";
			}
		}
		if(runWeek.length()>1){
			runWeek = runWeek.substring(0, runWeek.length()-1);
		} 
		return runWeek;
	}
	/**
	 * 得到运行月
	 * @param request
	 * @return
	 */
	public String getMonthStr(HttpServletRequest request){
		String runMonth = "";
		String[] runMonths = request.getParameterValues("runMonth");
		if(runMonths!=null){
			for(int i=0;i<runMonths.length;i++){
				runMonth = runMonth+runMonths[i]+",";
			}
		}
		if(runMonth.length()>1){
			runMonth = runMonth.substring(0, runMonth.length()-1);
		} 
		return runMonth;
	}
	
	
	 

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	 

	public TScheduleModel getSchedulemodel() {
		return schedulemodel;
	}

	public void setSchedulemodel(TScheduleModel schedulemodel) {
		this.schedulemodel = schedulemodel;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public void setSnatchService(SnatchService snatchService) {
		this.snatchService = snatchService;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSourceServer() {
		return sourceServer;
	}

	public void setSourceServer(String sourceServer) {
		this.sourceServer = sourceServer;
	}

	 

	 
	
	
}
