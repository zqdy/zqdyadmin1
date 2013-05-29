package com.zqdy.core.common.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class EncodingFilter extends HttpServlet implements Filter {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
		
	private String edcoding=null;    
    @SuppressWarnings("unused")
	private FilterConfig filterConfig=null;    
    private boolean ignore=true;    
	   
	    public void destroy() {    
	         edcoding=null;    
	         filterConfig=null;    
	    }    
	   
	    public void doFilter(ServletRequest request, ServletResponse response,    
	            FilterChain filterChain) throws IOException, ServletException {    
	        if(ignore==true||request.getCharacterEncoding()==null){    
	            String encoding=setCharacterEncoding(request);    
	              if(encoding!=null){    
	                request.setCharacterEncoding(encoding);    
	                    
	                }    
	        }    
	          filterChain.doFilter(request, response);    
	    }    
	   
	    public void init(FilterConfig filterConfig) throws ServletException {    
	        this.filterConfig=filterConfig;    
	        this.edcoding=filterConfig.getInitParameter("encoding");    
	        String value=filterConfig.getInitParameter("ignore");    
	        if(value==null){    
	            this.ignore=true;    
	        }else if(value.equalsIgnoreCase("true")){    
	            this.ignore=true;    
	                
	        }else{    
	            this.ignore=false;    
	        }    
	            
	   
	    }    
	    public String setCharacterEncoding(ServletRequest request){    
	        return this.edcoding;    
	    }    

}
