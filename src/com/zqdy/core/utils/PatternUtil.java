package com.zqdy.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
	
	
	/**
	 * 正则表达式得到匹配结果,匹配集数
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static String matcherQishuResult(String str,String pattern){
		Pattern pt = Pattern.compile(pattern);		 
		Matcher mc = pt.matcher(str);
		String result = null;		 
		while (mc.find()) {
			result = mc.group(0);			
		}
		return result;
	}
	
	/**
	 * 正则表达式得到匹配结果，只有一个匹配结果
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static String matcherOneResult(String str,String pattern){
		if(str==null||pattern==null){
			return null;
		}
		Pattern pt = Pattern.compile(pattern);		 
		Matcher mc = pt.matcher(str);
		String result = null;		 
		while (mc.find()) {
			result = mc.group(1);			
		}
		return result;
	}
	
	/**
	 * 正则表达式得到匹配结果，只有一个匹配结果
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Float matcherReateScore(String str,String pattern){
		Pattern pt = Pattern.compile(pattern);		 
		Matcher mc = pt.matcher(str);
		String result = null;	
		Float intResult = new Float((int) 6.1);
		while (mc.find()) {
			result = mc.group(1);			
		}
		try{
			intResult = Float.valueOf(result);
		}catch(Exception e){
			
		}
		return intResult;
	}
	/**
	 * 正则表达式得到匹配结果，多个匹配结果
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static List<String> matcherMultipResult(String str,String pattern){
		List<String> list = new ArrayList<String>();
		Pattern pt = Pattern.compile(pattern);		 
		Matcher mc = pt.matcher(str);
		 	 
		while (mc.find()) {
			String result = mc.group(1);	
			list.add(result);
		}
		return list;
	}
	
	public static String extractName(String name){
		if(name==null||"".equals(name.trim())){
			return null;
		}
		Pattern embedPt = Pattern.compile("《(.*)》");
		Matcher embedMc = embedPt.matcher(name);
		String name1 =name;
		if (embedMc.find()) {			 
			name1 =embedMc.group(0).trim(); 
			if(name1!=null){
				if(name1.startsWith("《")){
					name1=name1.substring(1, name1.length());
				}
				if(name1.endsWith("》")){
					name1=name1.substring(0, name1.length()-1);
				}
			}
		}
		return name1;
	}
	
}
