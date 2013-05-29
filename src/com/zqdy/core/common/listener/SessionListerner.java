package com.zqdy.core.common.listener;

import java.io.File;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListerner implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		
		 

	}
	
	
	public String getAddress(Class c) {
		Class theClass = c;
		java.net.URL u = theClass.getResource("");
		String str = u.toString();
		str = str.substring(6, str.length());
		str = str.replaceAll("%20", " ");
		int num = str.indexOf("WEB-INF/");
		// // 截取即可 F1372D3399C01F017D7F434E56211F32.xml
		str = str.substring(0, num);
		str += "tree/";
		return str;
	}

}
