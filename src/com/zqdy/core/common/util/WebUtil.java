package com.zqdy.core.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

public class WebUtil {
	public static String convert(String objString){
		String result = null;
		if(objString != null && !"".equals(objString.trim())) {
			result = objString.trim();
		}
		
		return result;
	}
	
	public static String dateStr() {
		Calendar now = Calendar.getInstance();

		Formatter fmt = new Formatter();
		String dateStr = fmt.format("%tF",now).toString();

		return dateStr;
	}
	
	public static String dateStr(Date date){
		Formatter fmt = new Formatter();
		String dateStr = fmt.format("%tF",date).toString();

		return dateStr;
	}
	
	public static String nextDateStr(){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR,1);
		Formatter fmt = new Formatter();
		String dateStr = fmt.format("%tF",now).toString();
		
		return dateStr;
	}
	private  String getAddr() {

		String str = this.getClass().getResource("/").getPath();
		str = str.substring(1, str.length());
		str = str.replaceAll("%20", " ");
		return str;
	}
	public static String getAddress(){
		WebUtil wu = new WebUtil();
		return wu.getAddr();
	}
	
	public static Map getOptionsFromXML(String className) {
		XmlDom4j sax = new XmlDom4j();
		Map integralMap = new HashMap();
		String classpath = getAddress();
		String fileName = "selectValue.xml";
		//String address =  "/usr/local/tomcate6.0/webapps/eassol/WEB-INF/classes/com/eassol/struts/resource/";
		String address = classpath + "config/";

		StringBuffer opStr = new StringBuffer();
		Iterator it = sax.getOptions(address, fileName);

		while (it.hasNext()) {
			Element e = (Element) it.next();
			String vv = e.attribute("name").getValue();
			if (vv.equals(className)) {
				Iterator eit = e.elementIterator();
				while (eit.hasNext()) {
					Element elt = (Element) eit.next();
					String opkey = elt.attribute("name").getValue();					
					String opValue = elt.attribute("value").getValue();
					integralMap.put(opkey, opValue);
				}
			}
		}
		return integralMap;

	}
	
	public static void main(String[] args) {
	
 
	}
}
