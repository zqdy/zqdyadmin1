package com.zqdy.service.snatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map; 
 
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zqdy.core.utils.DownHtml;
import com.zqdy.core.utils.JSoupUtil;
import com.zqdy.core.utils.PatternUtil;
import com.zqdy.dao.po.TLink; 
import com.zqdy.service.SnatchUtilServer;

public class TengxunUtil  implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(TengxunUtil.class);
	}

	public List<TLink> getLinkByUrl(String url, int soureServer, Integer tvType) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		//Elements elemnts = doc.select("h6[class=caption] a");
		Elements elemnts = doc.select("div[id=content]>ul li");
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				Element liel = elemnts.get(i);
				if(liel!=null){
					TLink link = null;
					String name = null;
					Elements aels = liel.select("a");
					if(aels!=null&&aels.size()>0){
						Element ael = aels.get(0);
						link = new TLink();
						name = ael.attr("title");
						String movieUrl = ael.attr("href");
						
						Elements payElems = ael.select("span[class=mod_pay_sign]");
						if(payElems!=null&&payElems.size()>0){
							link.setIsMember(1);
						}
						Elements yugaoElems = ael.select("i[class=mark_trailer]");
						if(yugaoElems!=null&&yugaoElems.size()>0){
							link.setType(3);
							link.setStatus(1);
						}
						
						
						link.setName(name);
						link.setUrl(movieUrl);
						link.setSource(soureServer);
						link.setStatus(0);
						link.setTvType(tvType);
						link.setSnatchCount(0);
						link.setAddtime(new Date());
						
					}
					 
					if(name!=null&&name.contains("《")){
						link.setType(2);
						link.setStatus(1);
					}
					linkList.add(link);
					 
					
				}
				 
				
			}
		}
		
		return linkList;
	}

	public DoubanBo extractMovieDetail(String name,String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		String embed = extractMovieEmbed(url);
		 
		DoubanBo douban = DoubanUtil.getMovieInfo(name,0);
		if(douban!=null){
			douban.setEmbed(embed);
		}	
	  
		return douban;
	}
	
	/**
	 * 提取播放代码
	 */
	public String extractMovieEmbed(String url) {	
		String embed = null;
		try{
		String html = DownHtml.getHtmlByUrl(url, "utf-8");
		String pattern="vid:\"(.*?)\",";
		String vid = PatternUtil.matcherOneResult(html, pattern);
		embed ="<embed src=\"http://static.video.qq.com/TPout.swf?auto=1&vid="+vid+"\" quality=\"high\" width=\"1005\" height=\"513\" align=\"middle\" allowScriptAccess=\"sameDomain\" allowFullscreen=\"true\" type=\"application/x-shockwave-flash\"></embed>";
		}catch(Exception e){
		}
		return embed;
	}
	/**
	 * 提取Document电影共享地址
	 * @return
	 */
	private  String   getDocMovieEmbed(Document doc){
		 
		return null;
	}
	/**
	 * 提取电影播放地址
	 * @return
	 */
	public  String   extractMoviePlayUrl(String url){
		return url;
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
	

	public static void main(String args[]){
		TengxunUtil tenxun = new TengxunUtil();
		String url = "http://v.qq.com/cover/h/hultejw4js4wq40.html";
		String embed = tenxun.extractMovieEmbed(url);
		System.out.println(embed);
//		String url = "http://v.qq.com/list/1_-1_-1_-1_1_0_0_20_1_-1_0.html";
//		List<TLink> linkList = xunlei.getLinkByUrl(url, 1, 0);
//		
//		for(int i=0;i<linkList.size();i++){
//			TLink link = linkList.get(i);
//			System.out.println("name="+link.getName());
//			System.out.println("url="+link.getUrl());
//			System.out.println(link.getShortSummary()); 
//			if(link.getIsMember()!=null&&1==link.getIsMember().intValue()){
//				System.out.println("会员电影");
//			}
//			int t = link.getType().intValue();
//			
//			if(t==1){
//				System.out.println("正片");
//			}else if(t==2){
//				System.out.println("记录片");
//			}else if(t==3){
//				System.out.println("预告");
//			}
//			System.out.println("-------------------------------");
//			
//		}
	}

	 
}
