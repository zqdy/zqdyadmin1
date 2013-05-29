package com.zqdy.core.common.util;

import com.opensymphony.xwork2.util.ValueStack;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import org.apache.struts2.components.Component;   
import org.apache.struts2.views.jsp.ComponentTagSupport;   
/**  
 * 分页标签  
 * @author tangs  
 */  
public class PageTag extends ComponentTagSupport {   
    private Integer cpage;  //当前�?  
    private Integer total;  //总页�?  
    private String url;  //请求地址  
    private String urlType;
    private String formId;
  
    public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setCpage(Integer cpage) {   
        this.cpage = cpage;   
    }   
  
    public void setTotal(Integer total) {   
        this.total = total;   
    }   
  
    public void setUrl(String url) {   
        this.url = url;   
    }   
  
    @Override  
    public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
        return new Pages(arg0); //返回Pages Component，分页的逻辑处理都在这个Component�?  
    }   
  
    //获得参数   
    protected void populateParams() {   
        super.populateParams();   
           
        Pages pages = (Pages)component;   
        pages.setCpage(cpage);   
        pages.setTotal(total); 
        pages.setFormId(formId);
        pages.setUrl(url);   
    }

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}   
}  

