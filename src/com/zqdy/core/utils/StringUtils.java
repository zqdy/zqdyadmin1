package com.zqdy.core.utils;

public class StringUtils {
	public static String trim(String str){
		if(str!=null){
			str = str.trim();
		}
		return str;
	}
	
	public static Integer stringToInteger(String str){
		Integer i = null;
		try{
			if(str==null){
				return null;
			}
			str = str.trim();
			str = str.replace(",","");
			i = Integer.valueOf(str);
		}catch(Exception e){
			
		}
		return i;
	}
	
	public static  Float stringToFloat(String str){
		Float f = null;
		try{
			if(str==null){
				return null;
			}
			str = str.trim();
			 
			f = Float.valueOf(str);
		}catch(Exception e){
			
		}
		return f;
	}
}
