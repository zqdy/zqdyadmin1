package com.zqdy.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.log4j.Logger;  
 
import com.zqdy.service.impl.SnatchServiceImpl; 

public class DownHtml {
	private static Logger logger;

	static {
		logger = Logger.getLogger(DownHtml.class);
	}

	private static int BUFFER_SIZE = 8096;// 缓冲区大小

	public static void outPutMsg(String msg) { 
	}

	
	public static String getHtmlByUrl2(String movieName) {
		 String info = null;
		 
		  ScriptEngineManager scriptManager = new ScriptEngineManager();
		  ScriptEngine engine = scriptManager.getEngineByExtension("js");
		  // 执行JS
		  // p1的获取 执行js中的方法
		  try {
			  engine.eval("function test(){return encodeURI(encodeURI('"+movieName+"'))}");      
			  Invocable inv = (Invocable) engine;      
			  info = String.valueOf(inv.invokeFunction("test"));   
			 
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		           

		return info;
	}
	/**
	 * 根据网站地址抓取网站内容
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getHtmlByUrl(String url, String charset) {
		if (url == null || "".equals(url.trim())) {
			return null;
		}
		StringBuffer sb = new StringBuffer("");
		try {
			URL urlmy = new URL(url);
			 
			HttpURLConnection con = (HttpURLConnection) urlmy.openConnection();
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			 
			con.setFollowRedirects(true);
			con.setInstanceFollowRedirects(false);
			con.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), charset));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("抓取网站出错，网站地址：" + url + "  " + e.getMessage());
		}
		return sb.toString();
	}

	public static String saveMovieImage(File file, String mvType,
			String tvName, String fileName) {
		// String filName = System.currentTimeMillis() + ".jpg";
		FileOutputStream fos = null;
		BufferedInputStream bis = null;

		byte[] buf = new byte[BUFFER_SIZE];
		String path = null;
		int size = 0;
		try {
			fileName = toHexString(fileName) + ".jpg";
			path = "/imgs/" + mvType + "/";
			if (tvName != null) {
				tvName = toHexString(tvName);
				path = path + tvName + "/";
			}
			String projectUrl = getImagePath(mvType, tvName);
			path += fileName;
			String filePath = projectUrl + fileName;

			bis = new BufferedInputStream(new FileInputStream(file));
			// 建立文件
			fos = new FileOutputStream(filePath);

			// 保存文件
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e) {
			}

		}
		return path;
	}

	public static String downImag(String imgUrl, String mvType, String tvName,
			String filName) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		String path = null;
		int size = 0;
		try {
			filName = toHexString(filName) + ".jpg";
			path = "/imgs/" + mvType + "/";
			if (tvName != null) {
				tvName = toHexString(tvName);
				path = path + tvName + "/";
			}
			String projectUrl = getImagePath(mvType, tvName);

			path += filName;
			String filePath = projectUrl + filName;
			
			url = new URL(imgUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			// 连接指定的资源
			httpUrl.connect();
			// 获取网络输入流
			bis = new BufferedInputStream(httpUrl.getInputStream());
			// 建立文件

			fos = new FileOutputStream(filePath);
 
			// 保存文件
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			

			

		} catch (Exception e) {
			logger.info("----------------------downHtml load Image Error:"+e.getMessage());
			e.printStackTrace();
			path = null;
		}finally{
			try{
				if(fos!=null){
					fos.close();
				}
				if(bis!=null){
					bis.close();
				}
			}catch(Exception e){
				
			}
			httpUrl.disconnect();
		}
		return path;
	}

	/**
	 * 生成电影图片地址，如果为电视剧，即每部电视剧下所以图片放在一个文件下
	 * 
	 * @param ServerType
	 * @param tvName
	 * @return
	 */
	public static String getImagePath(String mvType, String tvName) {
		String projectUrl = SnatchServiceImpl.getImagPath();
		String imagePath =  "zzdyImages/imgs/" + mvType+ "/";
		  //windows下
//		  if("\\".equals(File.separator)){   
//			  projectUrl = projectUrl.substring(6);
//			int ind = projectUrl.indexOf("zqdy");
//			projectUrl = projectUrl.substring(0, ind) +imagePath;
//			 
//				
//		  }
//		  //linux下
//		  if("/".equals(File.separator)){   
//			  projectUrl = projectUrl.substring(5);
//			  int ind = projectUrl.indexOf("ROOT/WEB-INF");
//			 // projectUrl  = projectUrl.substring(0,projectUrl.indexOf("ROOT/WEB-INF"));
//			  projectUrl = projectUrl.replace("\\", "/");
//			  projectUrl = projectUrl.substring(0, ind) +imagePath;
//			  // logger.info("--------DownHtml ---Linux----"+projectUrl ); 
//		  }
		  projectUrl = projectUrl + imagePath;
		  if (tvName != null) {
				projectUrl = projectUrl + tvName + "/";
		  }
		  //logger.info("--------DownHtml -------" + projectUrl);
		  File file = new File(projectUrl);
		  if (!file.exists()) {
				file.mkdirs();
		  }
		return projectUrl;
	}

	/**
	 * 将字符转16进制，
	 * 
	 * @param s
	 * @return
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static void main(String[] args) throws IOException {
		String imaUrl = "http://res.mfs.ykimg.com/050E00004FAB5EE09792731CC0076E4F";
		 
		String ss = getImagePath("5555", "ssss");
	 
	}
}
