package com.zqdy.core.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
    protected final Logger logger = Logger.getLogger(getClass());
    
    protected String id;
	protected Serializable obj;
	protected HttpServletRequest request;
	protected HttpServletResponse response=null;
	protected HttpSession session;
	//protected TUser user = null;
	protected String isLogin = "false";
	protected String result;
	protected String msg;
	protected String flag="-1";
	protected Integer cpage=1;   
	protected Integer total;   
    protected String url;  
    protected int pageSize=10;
    protected String searchName;
    
    protected List resultList;    
   // protected TAccountForm accountForm =null;
    
    protected String startDate;	
    protected String endDate;
	
    private String isMenu;
	
    public Integer getCpage() {
    	if(cpage<1){
    		cpage=1;
    	}
		return cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.request = ServletActionContext.getRequest();
		this.session = request.getSession();
		this.session.setAttribute("total", total);
		this.session.setAttribute("cpage", total/pageSize);
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String execute() throws Exception
	{
//    	request = ServletActionContext.

	    return SUCCESS;
	}
    
    protected  synchronized  void init(){

    	logger.info("初始�WX化BaseAction......");
    	
		this.request = ServletActionContext.getRequest();
		
		this.session = request.getSession();
    }



	public Serializable getObj() {
		return obj;
	}

	public void setObj(Serializable obj) {
		this.obj = obj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public String getMsg() {
		return msg;
	}

//	public TUser getUser() {
//		return user;
//	}
//
//	public void setUser(TUser user) {
//		this.user = user;
//	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	public void outMsg(String msg){
		init();
		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println(msg);
		    out.flush();
	        out.close();	        
		} catch (IOException e) {			 
			e.printStackTrace();
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	
}
