package com.zqdy.service.snatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zqdy.core.utils.JSoupUtil;
import com.zqdy.dao.po.TLink; 
import com.zqdy.dao.po.TMovie;
import com.zqdy.service.SnatchService;
import com.zqdy.service.SnatchUtilServer;

public class IqyUtil implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(IqyUtil.class);
	}

	public List<TLink> getLinkByUrl(String url, int soureServer, Integer tvType) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		Elements elemnts = doc.select("li[class=j-listanim]");
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				TLink link = null;
				Element liEl = elemnts.get(i);
				Elements imgEls = liEl.select("a[class=imgBg1]");
				if(imgEls!=null&&imgEls.size()>0){
					Element aimageEl = imgEls.get(0);
					link = new TLink();
					String name = aimageEl.child(0).attr("title");
					String movieUrl = aimageEl.attr("href");
					
					Elements membersEl = aimageEl.select("span[class=qiyue_hyBg]");
					if(membersEl!=null&&membersEl.size()>0){
						link.setIsMember(1);
					}
					Elements mTypeEl = aimageEl.select("span[class=imgBg1C imgBg1C1]");
					if(mTypeEl!=null&&mTypeEl.size()>0){
						link.setType(3);
						link.setStatus(1);
					}else if(name!=null&&name.contains("《")){
						link.setType(2);
						link.setStatus(1);
					}else{
						link.setType(1);
					}
					
					
					link.setName(name);
					link.setUrl(movieUrl);
					link.setSource(soureServer);
					link.setStatus(0);
					link.setTvType(tvType);
					link.setSnatchCount(0);
					link.setAddtime(new Date());
					
				}
				if(link!=null){
					Elements pels = liEl.select("p[class=s1]");
					if(pels!=null&&pels.size()>=2){
						Element pel = pels.get(1);
						String subTitle = pel.text();
						link.setShortSummary(subTitle);
					}
					
					linkList.add(link);
				}
				
			}
		}
		
		return linkList;
	}

	/**
	 * 提取电影详细	
	 */
	public DoubanBo extractMovieDetail(String name,String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		String embed = getDocMovieEmbed(doc,url);
		 
		DoubanBo douban = DoubanUtil.getMovieInfo(name,0);
		if(douban!=null){
			douban.setEmbed(embed);
			Elements elemnts = doc.select("div[class^=play_topright]");
			if(elemnts!=null&&elemnts.size()>0){
				douban.setIsMember(1);             //付费电影
			}

		}	
	  
		return douban;
	}
	
	
	public String extractMovieEmbed(String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		return getDocMovieEmbed(doc,url);
	}
	/**
	 * 提取电影播放地址
	 * @return
	 */
	public  String   extractMoviePlayUrl(String url){
		return url;
	}
	/**
	 * 提取Document电影共享地址
	 * @return
	 */
	private  String   getDocMovieEmbed(Document doc,String url){
		// 获取视频地址  
		StringBuffer flashUrlSb = new StringBuffer("http://player.video.qiyi.com/");
		String embed = null;
		try {		
			
			Element divEl = doc.getElementById("flashbox");
			String vid = divEl.attr("data-player-videoid");
			flashUrlSb.append(vid+"/0//dianying/");
			 
			int htmlIndex = url.indexOf(".html");
			String murl  = url.substring(30, htmlIndex);    //截取电影地址
			flashUrlSb.append(murl+".swf");
			
			String albumId = divEl.attr("data-player-albumid");
			flashUrlSb.append("-albumId="+albumId);
			
			String tvId = divEl.attr("data-player-tvid");
			flashUrlSb.append("-tvId="+tvId);
			
			String isMember = divEl.attr("data-player-ismember");
			String isPurchase ="0";
			if("true".equals(isMember)){
				isPurchase="1";
			}else if("false".equals(isMember)){
				isPurchase="0";
			}
			flashUrlSb.append("-isPurchase="+isPurchase);
			flashUrlSb.append("-autoPlay=1");
			embed ="<embed src=\""+flashUrlSb.toString()+"\" quality=\"high\" width=\"1005\" height=\"513\" align=\"middle\"  type=\"application/x-shockwave-flash\"></embed>";
		        
		} catch (Exception e) {
			logger.error(e.getMessage());
		}  
        
        
       return embed;
	}
	
	
	
	public String extractSubTvEmbed(String html, String regexEmbed) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSubTvEmbedByUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	 

	public DoubanBo extractTVDetail(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map getSubTv(String url, String tvName, int hasLength,
			int countLength) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 提取电影信息详情
	 * @param html
	 * @param charset
	 * @param movie
	 * @param snatchService
	 * @return
	 */
	public   TMovie extractMovieDetail(Integer server,String movieName,String html,String charset,SnatchService snatchService){
		return null;
	}
	
	public static void main(String args[]){
		IqyUtil iqy = new IqyUtil();
		//String embed = iqy.extractMovieEmbed("http://www.iqiyi.com/dianying/20130326/22a199026ddcbae5.html?fc=a64f3700229a0bc3");
		/**String url = "http://www.iqiyi.com/dianying/20120321/fbb6484a3400f419.html";
		DoubanBo douban = iqy.extractMovieDetail("回收北京", url);
		if(douban!=null){
			int isMember = douban.getIsMember();
			if(isMember==1){
			 
			}else{
				 
			}
		}**/
		String url = "http://list.iqiyi.com/www/1/----------0--6-2-1-6---.html";
		List<TLink> linkList = iqy.getLinkByUrl(url, 2, 0);
		for(int i=0;i<linkList.size();i++){
			TLink link = linkList.get(i);
			System.out.println("name="+link.getName());
			System.out.println("url="+link.getUrl());
			System.out.println(link.getShortSummary()); 
			if(link.getIsMember()!=null&&1==link.getIsMember().intValue()){
				System.out.println("会员电影");
			}
			int t = link.getType().intValue();
			
			if(t==1){
				System.out.println("正片");
			}else if(t==2){
				System.out.println("记录片");
			}else if(t==3){
				System.out.println("预告");
			}
			System.out.println("-------------------------------");
			
		}
		
		//String url = "http://www.iqiyi.com/dianying/20130205/458a93a9a77c6960.html";
		//String s = url.substring(30, url.length()-5);
		// 
	}

	

}
