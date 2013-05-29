package com.zqdy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zqdy.core.action.BaseAction;
import com.zqdy.service.SnatchService;

public class SnatchAction extends BaseAction {
	private SnatchService snatchService;
	
	public String goSnatch(){
		init();
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	
	/**
	 * 抓取电影链接
	 * @return
	 */
	public String snatchLink(){
		init();
		String url = request.getParameter("url");
		String regex = request.getParameter("regex");
		String server = request.getParameter("snatchServer");
		Integer soreceServer = Integer.valueOf(server);
		String tvTypeString = request.getParameter("tvType");
		String charSet = request.getParameter("charSet");
		Integer tvType = Integer.valueOf(tvTypeString);
		String msg = snatchService.snatchMovieLink(url, regex , soreceServer,charSet,tvType);
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8"); 
		    PrintWriter out = response.getWriter();
		    out.println(msg);
		    out.flush();
	        out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String goSnatchDetail(){
		init();
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	
	public String snatchDetail(){
		init();
		String server = request.getParameter("snatchServer");
		String regexEmbed =request.getParameter("regexEmbed");
		String regexDetail = request.getParameter("regexDetail");
		String tvTypeString = request.getParameter("tvType");
		Integer tvType = Integer.valueOf(tvTypeString);
		String snatchSizeStr = request.getParameter("snatchSize"); 
		int snatchSize = 50;
		if(snatchSizeStr!=null&&!"".equals(snatchSizeStr.trim())){
			snatchSize = Integer.parseInt(snatchSizeStr);
		}
		
		String msg = snatchService.snatchDetail(Integer.valueOf(server),regexEmbed,regexDetail,tvType,false,snatchSize);
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8"); 
		    PrintWriter out = response.getWriter();
		    out.println(msg);
		    out.flush();
	        out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void setSnatchService(SnatchService snatchService) {
		this.snatchService = snatchService;
	}

}
