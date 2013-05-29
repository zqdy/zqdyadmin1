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

public class LeTvUtil  implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(LeTvUtil.class);
	}

	public List<TLink> getLinkByUrl(String url, int soureServer, Integer tvType) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		Elements elemnts = doc.select("dl[class=m_dl]>dt");
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				Element dtel = elemnts.get(i);
				Element ael = dtel.child(0);
				TLink link = new TLink();
				String name = ael.attr("title");
				String movieUrl = ael.attr("href"); 
				
				Elements sdEls = dtel.select("b[class=sd]");
				if(sdEls!=null&&sdEls.size()>0){
					link.setIsMember(1);
				}
				if(name!=null&&name.contains("《")){
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
				linkList.add(link);
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
		 
		DoubanBo douban = null;
		if(name!=null&&name.contains("微电影")){
			 douban = getLeMvInfo(doc);
		}else{
			douban = DoubanUtil.getMovieInfo(name,0);
		}
		if(douban!=null){
			douban.setEmbed(embed);
			douban.setName(name) ;
		}	
	  
		return douban;
	}
	
	public String extractMovieEmbed(String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		return getDocMovieEmbed(doc);
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
	private  String   getDocMovieEmbed(Document doc){
		
		String embed = null;
		Elements  eles = doc.select("dl[class=add] input[class=i-t]");
		if(eles!=null&&eles.size()>1){
			Element ael = eles.get(0);
			String purl = ael.attr("value");
			if(purl!=null){
				int lastInd = purl.lastIndexOf("/")+1;
				int endInd = purl.indexOf(".html");
				String tvId = purl.substring(lastInd, endInd);
				embed="<embed src='http://i7.imgs.letv.com/player/swfPlayer.swf?autoPlay=1&id="+tvId+"' width='1005'  height='513' allowFullScreen='true' type='application/x-shockwave-flash'/>";
			}
			
		}
		return embed;
	}
	
	private DoubanBo getLeMvInfo(Document doc ){
		
		DoubanBo doubanBo = new DoubanBo();
		 
		if(doc==null){
			return null;
		}
		Elements pinfoEls = doc.select("div[class=Detail]>div[class=intro_bar]>ul[class=intro_box] li[class=Li01] p");
		if(pinfoEls!=null&&pinfoEls.size()>0){
			Element infoEl =pinfoEls.get(0);                          //导演
			if(infoEl!=null){
				Elements directorEls = infoEl.children();
				if(directorEls!=null&&directorEls.size()>0){
					List<DoubanActor> directorList = new ArrayList<DoubanActor>();
					for(int i=0;i<directorEls.size();i++){
						Element dirEl = directorEls.get(i);
						if(dirEl!=null){
							String dirName = dirEl.text();
							if(dirName!=null){
								DoubanActor actor = new DoubanActor();
								actor.setName(dirName);
								directorList.add(actor);
							}
						}
						
					}
					doubanBo.setDirectorList(directorList);
				}
			} 
		 
			Elements actorEls =pinfoEls.get(1).children();  //主演
			if(actorEls!=null&&actorEls.size()>0){
				List<DoubanActor> actorList = new ArrayList<DoubanActor>(); 
				for(int i=0;i<actorEls.size();i++){
					Element actorEl = actorEls.get(i);
					if(actorEl!=null&&actorEl.text()!=null){
						String actName = actorEl.text();
						DoubanActor actor = new DoubanActor();
						actor.setName(actName);
						actorList.add(actor);
					}
				}
				doubanBo.setActorList(actorList);
			}
			
			Elements generEls =pinfoEls.get(2).children();  //类型
			if(generEls!=null&&generEls.size()>0){
				List<String> genreList = new ArrayList<String>(); 
				for(int i=0;i<generEls.size();i++){
					Element generEl = generEls.get(i);
					if(generEl!=null&&generEl.text()!=null){
						String gerName = generEl.text();
						genreList.add(gerName);
					}
				}
				doubanBo.setGenreList(genreList);
			}
			
		}
		Elements descEls = doc.select("div[class=Detail]>div[class=intro_bar]>ul[class=intro_box] li[class=Li02]");
		if(descEls!=null&&descEls.size()>0){
			String desc = descEls.get(0).text();
			doubanBo.setSummary(desc);
			Element infoAEl = descEls.get(0).child(0);
			if(infoAEl!=null){
				String infoUrl = infoAEl.attr("href");
				Document infoDoc = JSoupUtil.getJsoupDocumentByUrl(infoUrl);
				if(infoDoc!=null){
					Elements infoEls = infoDoc.select("dl[class=w150]");
					if(infoEls!=null&&infoEls.size()>0){
						Elements imgEls = infoEls.select("dl>dt>p>a>img");
						if(imgEls!=null&&imgEls.size()>0){
							Element imgEl = imgEls.get(0);
							String imageUrl = imgEl.attr("src");
							doubanBo.setImageUrl(imageUrl);
						}
					}
					
					Elements areaEls = infoEls.select("dd p[class=p5] span[class=s4] a");  //区域
					if(areaEls!=null&&areaEls.size()>0){
						List<String> areaList = new ArrayList<String>(); 
						for(int i=0;i<areaEls.size();i++){
							Element areaEl = areaEls.get(i);
							if(areaEl!=null&&areaEl.text()!=null){
								String areaName = areaEl.text();
								areaList.add(areaName);
							}
						}
						doubanBo.setAreaList(areaList);
					}
				}
				
			}
			
			/**	Elements infoEls = doc.select("dl[class=w150]");
				if(infoEls!=null&&infoEls.size()>0){
					Elements imgEls = infoEls.select("dl>dt>p>a>img");
					if(imgEls!=null&&imgEls.size()>0){
						Element imgEl = imgEls.get(0);
						String imageUrl = imgEl.attr("src");
						doubanBo.setImageUrl(imageUrl);
					}
					Elements desEls = infoEls.select("dd p[class=p6]");
					if(desEls!=null&&desEls.size()>0){
						String desc = desEls.text();
						doubanBo.setSummary(desc);
					}
					
					Elements directorEls = infoEls.select("dd p[class=p2] span[class=s1] a");  //导演
					if(directorEls!=null&&directorEls.size()>0){
						List<DoubanActor> directorList = new ArrayList<DoubanActor>();
						for(int i=0;i<directorEls.size();i++){
							Element dirEl = directorEls.get(i);
							if(dirEl!=null){
								String dirName = dirEl.text();
								if(dirName!=null){
									DoubanActor actor = new DoubanActor();
									actor.setName(dirName);
									directorList.add(actor);
								}
							}
							
						}
						doubanBo.setDirectorList(directorList);
					}
					
					Elements actorEls = infoEls.select("dd p[class=p3] a");  //主演
					if(actorEls!=null&&actorEls.size()>0){
						List<DoubanActor> actorList = new ArrayList<DoubanActor>(); 
						for(int i=0;i<actorEls.size();i++){
							Element actorEl = actorEls.get(i);
							if(actorEl!=null&&actorEl.text()!=null){
								String actName = actorEl.text();
								DoubanActor actor = new DoubanActor();
								actor.setName(actName);
								actorList.add(actor);
							}
						}
						doubanBo.setActorList(actorList);
					}
					
					Elements areaEls = infoEls.select("dd p[class=p5] span[class=s4] a");  //区域
					if(areaEls!=null&&areaEls.size()>0){
						List<String> areaList = new ArrayList<String>(); 
						for(int i=0;i<areaEls.size();i++){
							Element areaEl = areaEls.get(i);
							if(areaEl!=null&&areaEl.text()!=null){
								String areaName = areaEl.text();
								areaList.add(areaName);
							}
						}
						doubanBo.setAreaList(areaList);
					}
					List<String> genreList = new ArrayList<String>();
					genreList.add("短片");
				}
				**/
		}
		return doubanBo;
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
		String url = "http://www.letv.com/ptv/pplay/86934/1.html";
		 LeTvUtil leTv = new LeTvUtil();
		
		 DoubanBo douban = leTv.extractMovieDetail("",url);
		System.out.println(douban);
		/** String url = "http://so.letv.com/list/c1_t-1_a-1_y2013_f-1_at1_o1_i-1_p3.html";
		List<TLink> linkList = leTv.getLinkByUrl(url, 1, 0);
		
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
			
		}**/
		//leTv.getLinkByUrl(url, 6, 0);
		
	//String url1 = "http://yuanxian.letv.com/detail/84658.html";
		//String embed = leTv.extractMovieEmbed(url1);
		 
		
	}

}
