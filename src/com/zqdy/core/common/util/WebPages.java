package com.zqdy.core.common.util;


import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;
 
import com.opensymphony.xwork2.util.ValueStack;



/**
 * 分页逻辑Bean
 * 
 * @author tangs
 */
public class WebPages extends Component {
	
	
	private HttpServletRequest request;    
    private String cpage;   //当前页码
    private String total;   //总页数
    private String styleClass;   //分页的样式
    private String theme;   //分页的主题
    private String url;   //action的路径
    private String urlType; //路径的类型，主要用于URL重写的扩展

    
  public WebPages(ValueStack arg0, HttpServletRequest request) {   
      super(arg0);   
      this.request = request;   
  }   

	
	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);   
        try {   
         //从ValueStack中取出数值   
            Object obj=this.getStack().findValue(cpage);               
            cpage = String.valueOf((Integer)obj);     
            obj=this.getStack().findValue(total);   
            total = String.valueOf((Integer)obj);    
            if(cpage==null||"null".equals(cpage)){
            	cpage ="0";
            }
            if(total==null||"null".equals(total)){
            	total="0";
            }
            StringBuilder str = new StringBuilder();   
            Map cont= this.getStack().getContext();   
            StrutsRequestWrapper req=(StrutsRequestWrapper)cont.get(StrutsStatics.HTTP_REQUEST); 
           
            String contextPath =  request.getScheme()+"://"+request.getServerName();
            int port = request.getServerPort();
            if(port!=80){
            	contextPath = contextPath+":"+port;
            } 
            if(url==null||"".equals(url)){                
                url=contextPath+(String)req.getAttribute("javax.servlet.forward.request_uri");
            }
            String cpageStr="?cpage=";
            if("dir".equals(urlType)){//当url的类型为目录类型时，比如http://localhost:8090/yongtree/page/1
	            cpageStr="";
	            if("1".equals(cpage)){//第一页时
	                if(url.lastIndexOf("/")!=url.length()-1){
	                    if(url.lastIndexOf("1")==url.length()-1){//如果有页码1，则去掉1
	                       url=url.substring(0, url.length()-1);
	                    }else if(url.lastIndexOf("/")!=url.length()-1){//如果没有页码1，并且最后不是'/'时，加上'/'
	                       url=url+"/";
	                    }
	                }
	             }else{
                    url=url.substring(0, url.lastIndexOf("/")+1);
                }
             }
             //下面这段处理主要是用来处理动态查询的参数，并拼接成url 
            StringBuffer perUrl=new StringBuffer(""); 
            if ( req.getParameterMap().size()!= 0 ) {
                Iterator iter = req.getParameterMap().keySet().iterator();
                while (iter.hasNext()) {
                    String key = (String) iter.next();
                    if(!"cpage".equals(key)){
                    	String[] vls = (String[])req.getParameterMap().get(key);
                        perUrl.append("&").append(key).append("=").append(vls[0]);
                    }
                    
                }
            }
            Integer cpageInt = Integer.valueOf(cpage);   
            str.append("<div ");   
            if (styleClass != null) {   
                str.append(" class='"+styleClass+"'>");   
            } else {   
                str.append(">");   
            }   
            if("0".equals(total)){
            	str.append("<h5>抱歉，没有找到相关的数据 </h5>");
            }else{            	
            	  str.append("<ul>");   
                  //文本样式   
                  if (theme == null || "text".equals(theme)) {   
                      //当前页与总页数相等   
                  	str.append("共"+total+"页&nbsp;");
                  	str.append("第"+cpage+"页&nbsp;");            	  
                      if (cpage.equals(total)) {   
                          //如果total = 1，则无需分页，显示“[第1页] [共1页]”   
                          if ("1".equals(total)) {   
                              str.append("[第 " + cpage + " 页]");   
                              str.append(" [共 " + total + " 页]");   
                          } else {   
                              //到达最后一页,显示“[首页] [上一页] [末页]”   
                              str.append("<a href='"+url+cpageStr+"1"+perUrl+"'>[首页]</a> ");    
                              str.append("<a href='"+url+cpageStr + (cpageInt - 1)+perUrl+"'>[上一页]</a>" );   
                              str.append("[下一页]");
                              str.append("[末页]");   
                          }   
                      } else {   
                          //当前页与总页数不相同   
                          if ("1".equals(cpage)) {   
                              //第一页，显示“[首页] [下一页] [末页]”   
                              str.append("[首页]");  
                              str.append("[上一页]");  
                              str.append("<a href='"+url+cpageStr + (cpageInt + 1) +perUrl+"'>[下一页]</a>");                          
                              str.append("<a href='"+url+cpageStr + total +perUrl+"'>[末页]</a>");   
                          } else {   
                              //不是第一页，显示“[首页] [上一页] [下一页] [末页]”   
                              str.append("<a href='"+url+cpageStr+"1"+perUrl+"'>[首页]</a>");   
                              str.append("<a href='"+url+cpageStr + (cpageInt - 1)+perUrl+"'>[上一页]</a>");   
                              str.append("<a href='"+url+cpageStr + (cpageInt + 1)+perUrl+"'>[下一页]</a>");   
                              str.append("<a href='"+url+cpageStr + total +perUrl+"'>[末页]</a>");   
                          }   
                      }   
                  } else if ("number".equals(theme)) { //数字样式 [1 2 3 4 5 6 7 8 9 10 > >>]   
                      Integer totalInt = Integer.valueOf(total);   
                      //如果只有一页，则无需分页   
                      if (totalInt == 1) {   
                      	str.append("<li><span>上一页</span></li>");
                          str.append("<li><span>1</span></li>");   
                          str.append("<li><span>下一页</span></li>");
                      } else {                       
                          if (cpageInt > 1) {   
                          	int lastPage = cpageInt-1;
                              str.append("  <li ><a href='"+url+cpageStr+lastPage+perUrl+"'>上一页</a></li>");     
                          }else{                             
                              str.append("<li ><span>上一页</span></li> " );   
                          }   
                             
                          int v=(cpageInt-4)>0?(cpageInt-4):1;   
                          int v1=(cpageInt+4)<totalInt?(cpageInt+4):totalInt;   
                          if(v1==totalInt){   
                              v=totalInt-10;
                              v=(v<=0?1:v); //如果为负数，则修改为1
                          }else if(v==1&&v1<totalInt){   
                              v1=totalInt>10?10:totalInt;   
                          }   
                          //10个为一组显示   
                          if(v>1){
                          	str.append("  <li ><a href='"+url+cpageStr+"1"+perUrl+"'>1</a></li>");     
                          	str.append("<li ><p>...</p></li>");
                          }
                          for (int i = v; i <= v1; i++) {   
                              if (cpageInt == i) { //当前页要加粗显示                                 
                                  str.append("<li><span>"+i+"</span></li>");   
                              }else{   
                              	 str.append("  <li ><a href='"+url+cpageStr+i+perUrl+"'>"+i+"</a></li>");
                              }                               
                          }   
                          if(v1<totalInt){
                          	str.append("<li ><p>...</p></li>");
                          	str.append("  <li ><a href='"+url+cpageStr+totalInt+perUrl+"'>"+totalInt+"</a></li>");
                          }
                         
                          if (cpageInt<totalInt) {   
                          	 int nextPage = cpageInt+1;
                              str.append(" <li ><a href='"+url+cpageStr+nextPage+perUrl+"'>下一页</a></li> " );   
                          }else{   
                              str.append(" <li ><span>下一页</span></li> " );   
                         }   
                      }   
                      str.append("</ul>");   
                  }  else if ("static".equals(theme)) { //静态URL，第一个参数约定为页码  
                      Integer totalInt = Integer.valueOf(total);   
                      
                      int urlInd = url.lastIndexOf("/");
                      String p1 = url.substring(0, urlInd+1);
                      String path = url.substring(urlInd+1, url.length());
                      if(path.indexOf("_")!=-1){
                    	  int start = path.indexOf("_")+1;
                  		  cpageStr = p1+path.substring(0,start);
                  		  String s3 = path.substring(start, path.length());
                  		  int endInd = s3.indexOf("_");
                  		  perUrl = new StringBuffer();
                  		  String s4 = s3.substring(endInd,s3.length());
                  		  perUrl.append(s4); 
                  		  url="";
                      }
                     
                      //如果只有一页，则无需分页   
                      if (totalInt == 1) {   
                      	  str.append("<li><span>上一页</span></li>");
                          str.append("<li><span>1</span></li>");   
                          str.append("<li><span>下一页</span></li>");
                      } else {                       
                          if (cpageInt > 1) {   
                          	int lastPage = cpageInt-1;
                              str.append("  <li ><a href='"+url+cpageStr+lastPage+perUrl+"'>上一页</a></li>");     
                          }else{                             
                              str.append("<li ><span>上一页</span></li> " );   
                          }   
                             
                          int v=(cpageInt-4)>0?(cpageInt-4):1;   
                          int v1=(cpageInt+4)<totalInt?(cpageInt+4):totalInt;   
                          if(v1==totalInt){   
                              v=totalInt-10;
                              v=(v<=0?1:v); //如果为负数，则修改为1
                          }else if(v==1&&v1<totalInt){   
                              v1=totalInt>10?10:totalInt;   
                          }   
                          //10个为一组显示   
                          if(v>1){
                          	str.append("  <li ><a href='"+url+cpageStr+"1"+perUrl+"'>1</a></li>");     
                          	str.append("<li ><p>...</p></li>");
                          }
                          for (int i = v; i <= v1; i++) {   
                              if (cpageInt == i) { //当前页要加粗显示                                 
                                  str.append("<li><span>"+i+"</span></li>");   
                              }else{   
                              	 str.append("  <li ><a href='"+url+cpageStr+i+perUrl+"'>"+i+"</a></li>");
                              }                               
                          }   
                          if(v1<totalInt){
                          	str.append("<li ><p>...</p></li>");
                          	str.append("  <li ><a href='"+url+cpageStr+totalInt+perUrl+"'>"+totalInt+"</a></li>");
                          }
                         
                          if (cpageInt<totalInt) {   
                          	 int nextPage = cpageInt+1;
                              str.append(" <li ><a href='"+url+cpageStr+nextPage+perUrl+"'>下一页</a></li> " );   
                          }else{   
                              str.append(" <li ><span>下一页</span></li> " );   
                         }   
                      }   
                      str.append("</ul>");   
                  }   
            	  
            	
            }
            
          
          
            str.append("</div>");     
            writer.write(str.toString());   
               
        } catch (IOException ex) {   
           ex.printStackTrace();
        }   
        return result;   


	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WebPages(ValueStack arg0) {
		super(arg0);
	}


	public String getCpage() {
		return cpage;
	}


	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	
	public static void main(String[] args){
//		String s = "m_1_0_0_0_0.html";
//		int start = s.indexOf("_");
//		String s2 = s.substring(0,start);
//		String s3 = s.substring(start+1, s.length());
//		int endInd = s3.indexOf("_");
//		String cPage = s3.substring(0,endInd);
//		String s4 = s3.substring(endInd+1,s3.length());
	 
		
	}

}

