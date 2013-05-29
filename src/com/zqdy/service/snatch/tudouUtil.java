package com.zqdy.service.snatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zqdy.core.utils.JSoupUtil;
import com.zqdy.dao.po.TLink; 
import com.zqdy.service.SnatchUtilServer;

public class tudouUtil  implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(tudouUtil.class);
	}

	public List<TLink> getLinkByUrl(String url, int soureServer, Integer tvType) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		//Elements elemnts = doc.select("h6[class=caption] a");
		Elements elemnts = doc.select("div[class=pack pack_album]");
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				Element divel = elemnts.get(i);
				if(divel!=null){
					TLink link = null;
					String name = null;
					Elements aels = divel.select("div[class=txt] h6[class=caption] a");
					if(aels!=null&&aels.size()>0){
						Element ael = aels.get(0);
						link = new TLink();
						name = ael.attr("title");
						String movieUrl = ael.attr("href");
						
						link.setName(name);
						link.setUrl(movieUrl);
						link.setSource(soureServer);
						link.setStatus(0);
						link.setTvType(tvType);
						link.setSnatchCount(0);
						link.setAddtime(new Date());
						
					}
					if(link!=null){
						Elements liEls = divel.select("div[class=txt] ul[class=info]>li[class=desc]");
						if(liEls!=null&&liEls.size()>0){
							String subTitle = liEls.get(0).text();
							link.setShortSummary(subTitle);
						}
						Elements vinfEls = divel.select("div[class=pic] a[class=vinf]");
						if(vinfEls!=null&&vinfEls.size()>0){
							Element vinfEl = vinfEls.get(0);
							String mvType = vinfEl.text();
							if(mvType!=null){
								if("正片".equals(mvType.trim())){
									link.setType(1);
								}else if("记录片".equals(mvType.trim())){
									link.setType(2);
								}else if("预告".equals(mvType.trim())){
									link.setType(3);
								}
							}
							 
						}
						if(name!=null&&name.contains("《")){
							link.setType(2);
							link.setStatus(1);
						}
						linkList.add(link);
					}
					
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
		String embed = getDocMovieEmbed(doc);
		 
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
	
	/**
	 * 提取播放代码
	 */
	public String extractMovieEmbed(String url) {		
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);  
		if(doc==null){
			return null;
		}
		return getDocMovieEmbed(doc);
	}
	/**
	 * 提取Document电影共享地址
	 * @return
	 */
	private  String   getDocMovieEmbed(Document doc){
		String playUrl = null;
		Elements playBuns = doc.select("div[class=album-btn]>a");
		if(playBuns!=null&&playBuns.size()>0){
			Element ali = playBuns.get(0);
			if(ali!=null){
				playUrl = ali.attr("href");
			}
		}
		Document playDoc = JSoupUtil.getJsoupDocumentByUrl(playUrl);   
		if(playDoc==null){
			return null;
		}
		String embed = null;
		Elements  eles = playDoc.select("body>script");
		if(eles!=null&&eles.size()>1){
			Element el = eles.get(0);
			String scrHtml = el.html();	
			//正则表式取ID,
			String regex = ",ver='(.*)'";			
			Pattern embedPt = Pattern.compile(regex);
			Matcher embedMc = embedPt.matcher(scrHtml);
			String id="";
			if (embedMc.find()) {
				id = embedMc.group(1).trim(); 
			}
			//正则表达式取代码
			String regex2 = "acode='(.*)'";
			Pattern embedPt2 = Pattern.compile(regex2);
			Matcher embedMc2 = embedPt2.matcher(scrHtml);
			String code = "";
			if (embedMc2.find()) {
				code = embedMc2.group(1).trim(); 
			}
			String flashUrl = "http://www.tudou.com/a/"+code+"/&iid="+id+"&autoPlay=true&resourceId=0_05_02_99/v.swf";
			embed="<embed src=\""+flashUrl+"\" type=\"application/x-shockwave-flash\"  allowfullscreen=\"true\" wmode=\"opaque\" width=\"1005\" height=\"513\"></embed>";
		}
	 
		return embed;
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
		tudouUtil tudou = new tudouUtil();
		String url = "http://www.tudou.com/cate/ach22a-2b-2c-2d-2e-2f-2g-2h-2i-2j-2k-2l-2m-2n-2o-2so3pe-2pa1.html";
		List<TLink> linkList = tudou.getLinkByUrl(url, 1, 0);
		
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
	}

	 
}
