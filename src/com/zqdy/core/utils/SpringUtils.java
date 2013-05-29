package com.zqdy.core.utils;
 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	
	 private static ApplicationContext context;

	public static Object getBean(String beanName) {
		if(context==null){
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return context.getBean(beanName);
	}
}
