package com.zqdy.core.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element; 

public class JSoupUtil {
	private static Logger logger;

	static {
		logger = Logger.getLogger(JSoupUtil.class);
	}
	public static Document getJsoupDocumentByUrl(String url) {
		Document doc = null;
		try{ 
			doc = Jsoup.connect(url).get();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return doc;
	}
	
	public static String getElementAttrById(Document doc, String id, 
			String attrName)throws Exception{  

         Element et = doc.getElementById(id);  
        
         String attrValue = et.attr(attrName);           

         return attrValue;  

     }  

}
