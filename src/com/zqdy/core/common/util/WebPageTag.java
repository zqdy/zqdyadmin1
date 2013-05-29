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
public class WebPageTag extends ComponentTagSupport {   
	
	  	private String cpage;   
	    private String total;   
	    private String styleClass;   
	    private String theme;
	    private String url;
	    private String urlType;	 
	   
	    @Override 
	    public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
	        return new WebPages(arg0, arg1);   
	    }   
	 
	    protected void populateParams() {   
	        super.populateParams();   
	           
	        WebPages pages = (WebPages)component;   
	        pages.setCpage(cpage);     
	        pages.setTotal(total);   
	        pages.setStyleClass(styleClass);   
	        pages.setTheme(theme);   
	        pages.setUrl(url);
	        pages.setUrlType(urlType);
	 
	    }

		public String getCpage() {
			return cpage;
		}

		public void setCpage(String cpage) {
			this.cpage = cpage;
		}

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

		public String getStyleClass() {
			return styleClass;
		}

		public void setStyleClass(String styleClass) {
			this.styleClass = styleClass;
		}

		public String getTheme() {
			return theme;
		}

		public void setTheme(String theme) {
			this.theme = theme;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUrlType() {
			return urlType;
		}

		public void setUrlType(String urlType) {
			this.urlType = urlType;
		}
		
}  

