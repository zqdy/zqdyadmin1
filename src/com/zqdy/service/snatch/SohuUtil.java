package com.zqdy.service.snatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set; 

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
import com.zqdy.core.utils.DownHtml;
import com.zqdy.core.utils.JSoupUtil; 
import com.zqdy.dao.po.TActor; 
import com.zqdy.dao.po.TDirector; 
import com.zqdy.dao.po.TGenre;
import com.zqdy.dao.po.TLink;
import com.zqdy.dao.po.TMovie; 
import com.zqdy.dao.po.TMovieActor; 
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.service.SnatchService;
import com.zqdy.service.SnatchUtilServer;

public class SohuUtil  implements SnatchUtilServer{
	private static Logger logger;	
	static {
		logger = Logger.getLogger(SohuUtil.class);
	}
	
	/**
	 * 得到URL下页面的电影地址
	 * @param url
	 * @param soreceServer
	 * @param tvType
	 * @return
	 */
	public List<TLink> getLinkByUrl(String url,int source, Integer tvType) {
		 
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		Elements divElemnts = doc.select("div[class=show-pic]");
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(divElemnts!=null){
			for(int i=0;i<divElemnts.size();i++){
				Element divel = divElemnts.get(i);
				Element ael = divel.child(0);
				Element imgEl = ael.child(0);
				TLink link = new TLink();
				String name = imgEl.attr("title");
				String movieUrl = ael.attr("href"); 
				
				Elements pels = divel.select("em[class=pay]");
				if(pels!=null&&pels.size()>0){
					link.setIsMember(1);
				}
				if(name!=null){
					if(name.contains("预告片")){
						link.setType(3);
						link.setStatus(1);
					}else if(name.contains("《")||name.contains("纪录片")){
						link.setType(2);
						link.setStatus(1);
					}else{
						link.setType(1);
					}
				}
				
				link.setName(name);
				link.setUrl(movieUrl);
				link.setSource(source);
				link.setStatus(0);
				link.setTvType(tvType);
				link.setSnatchCount(0);
				link.setAddtime(new Date());
				linkList.add(link);
			}
		}
		return linkList;
	}
	
	/**
	 * 搜狐电影，付费电影是抓取不到的
	 */
	public DoubanBo extractMovieDetail(String movieName,String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		String embed = getDocMovieEmbed(doc);
		 
		DoubanBo douban = DoubanUtil.getMovieInfo(movieName,0);
		if(douban!=null){
			douban.setEmbed(embed);
		}	
	  
		return douban;
	}

	/**
	 * 提取电影共享地址
	 * @return
	 */
	public  String   extractMovieEmbed(String url){
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
		
		// 获取视频地址  
				String flashUrl=null;
				
				Elements elemnts = doc.getElementsByAttributeValue("property", "og:videosrc");
				if(elemnts!=null&elemnts.size()>0){
					Element el = elemnts.get(0);
					flashUrl =  el.attr("content");
				}
				
		        if(flashUrl==null){
		    	    return null;
		        }
		        flashUrl = flashUrl.replace("autoplay=false", "autoplay=true");
		        String embed = " <embed width=\"1005\" height=\"511\"  allowfullscreen=\"true\" allowscriptaccess=\"always\" " +
		        		"quality=\"high\" src=\""+flashUrl+"\" type=\"application/x-shockwave-flash\"/></embed>";
				return embed;
	}
	/**
	 * 提取电影播放地址
	 * @return
	 */
	public  String   extractMoviePlayUrl(String url){
		return url;
	}
	/**
	 * 提取电影详细住处
	 */
	public TMovie extractMovieDetail(Integer server, String movieName, String html, String charset, SnatchService snatchService) {
		 
		Parser parser = Parser.createParser(html,charset);
		TMovie movie = new TMovie();
		try {
			movie.setName(movieName);
			movie = (TMovie)snatchService.createObject(movie);
			 
			
			parser.setEncoding(charset);
			//			取图片
			NodeFilter filter_img = new AndFilter(
					new TagNameFilter("img"),
					new HasParentFilter(new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("class","pic l"))));
			NodeList imageList = parser.parse(filter_img);
			if(imageList!=null&&imageList.size()>0){
				ImageTag imageTag = (ImageTag)imageList.elementAt(0);
				String imgUrl = imageTag.getAttribute("src");
				
				String imagePath = DownHtml.downImag(imgUrl, "mv",null,movieName);
				movie.setImageUrl(imagePath);			 	
			}
			
		 
			// 
			parser.reset();
			NodeFilter filter_ul = new AndFilter(
					new TagNameFilter("ul"),
					new HasAttributeFilter("class","u1"));
			NodeList ulList = parser.parse(filter_ul);
			if(ulList!=null&&ulList.size()>0){
				Node node = ulList.elementAt(0);
				NodeList  liNode = node.getChildren(); //Bullet(li)   ParagraphTag(p)  LinkTag
				int liIndex =0;
				List<TDirector> directorList = new ArrayList<TDirector>();
				List<TMovieActor> actorList = new ArrayList<TMovieActor>();
				List<TGenre> genreList = new ArrayList<TGenre>();
				
				for(int i=0;i<liNode.size();i++){
					Node subLiNode = liNode.elementAt(i);            //li标签
					if(subLiNode instanceof Bullet){
						NodeList pNodeList = subLiNode.getChildren();    //P标签
						int pIndex=0;
						for(int j=0;j<pNodeList.size();j++){
							Node pNode = pNodeList.elementAt(j);
							if(pNode instanceof ParagraphTag){
								NodeList atagList = pNode.getChildren();
									 for(int k=0;k<atagList.size();k++){
										 Node anode = atagList.elementAt(k);
										 if(anode instanceof LinkTag ){
											 String cText = anode.toPlainTextString().trim();
											 if(liIndex==0){
												 if(pIndex==0){
													
													 TDirector director = snatchService.createDirectorIfNoCreate(cText,null,null);
													// genreList.add(director); 
												 }else{
													TActor actor = snatchService.createActorIfNoCreate(cText);
													///actorSet.add(actor);
													snatchService.createActorMovieIfNoCreate(cText,i,movie.getId());
												 }
											 }else if(liIndex==1){
												 if(pIndex==0){
													// movie.setReleaseYear(cText);
												 }else{
													 TGenre genre = snatchService.createGenreIfNoCreate(cText, 0);
													 genreList.add(genre);
													 
												 }
											 }
										 }else if(anode instanceof Span) {
											 NodeList yearNodeList = anode.getChildren();
											 for(int e=0;e<yearNodeList.size();e++){
												 Node ynode = yearNodeList.elementAt(e);
												 if(ynode instanceof LinkTag ){
													 String cText = ynode.toPlainTextString().trim();
													 movie.setYear(cText);
												 }
											 }
										 }
									 }
									 pIndex++;
							}
						}
						
						liIndex++;
					}
				}
				movie.setDirectorList(directorList);
				movie.setGenreList(genreList);
				movie.setActorList(actorList);
			}
			
			//取简介			  new HasAttributeFilter("class","u1")
			parser.reset();
			NodeFilter filter_short = new AndFilter(
					new TagNameFilter("span"), new AndFilter(new HasAttributeFilter("class","color3"),
							new HasParentFilter(new HasParentFilter(new AndFilter(new TagNameFilter("div"),
									new HasAttributeFilter("class","dCont"))))));
			 
			//			取详细
			parser.reset();
			NodeFilter filter_summary = new AndFilter(
					new TagNameFilter("div"),new HasAttributeFilter("class","wz"));
			NodeList summaryList = parser.parse(filter_summary);
			if(summaryList!=null&&summaryList.size()>0){
				Node summaryNode = summaryList.elementAt(0);
				String summary = summaryNode.toPlainTextString().trim();
				summary = summary.replace("・", "-");
				summary = summary.replace("�", "");
				summary = summary.replace("・", "-");
				movie.setSummary(summary); 
			}
			
		}catch(Exception e){
			logger.error("抓取电影详细出错"+movie.getName()+",出错信息为："+e.getMessage());
			e.printStackTrace();
		}
		return movie;
	}
	
	 

	public String extractSubTvEmbed(String html, String regexEmbed) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 提取电视剧详细信息 先取样式为“baseinfo”的ul,所有基本住处都在此ul中，再取各项基本信息
	 * 
	 * @param html
	 * @param charset
	 */
	public DoubanBo extractTVDetail(String url) {	 
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		DoubanBo doubanBo  = new DoubanBo();
		
		
		
		return null;
	}

	public Map getFenjiUrl(Integer server, Integer tvServerId, String tvName, String webDomain, String fenjiPageurl, String charset, int pageNo, boolean isUpdate, SnatchService snatchService) {
		
		if(fenjiPageurl==null||"".equals(fenjiPageurl.trim())){
			return null;
		}
		Map map = new HashMap();
		List<TTvSubtv> subList = new ArrayList<TTvSubtv>();
		try{
			boolean continueFlag = true;
			int fenjiCount =0;
			String urlHtml = DownHtml.getHtmlByUrl(webDomain+fenjiPageurl,charset);
			Parser parser = Parser.createParser(urlHtml,charset);
			parser.setEncoding(charset);
			String snatchState = "1";
			//取更新状态
			NodeFilter filter_update = new AndFilter(new TagNameFilter("div"),new AndFilter(new HasAttributeFilter("class","l"),new HasParentFilter(new AndFilter(
					new TagNameFilter("div"),new HasAttributeFilter("class","d1 clear")))));
			NodeList updateDivList = parser.parse(filter_update);
			if(updateDivList!=null&&updateDivList.size()>0){
				Node upNode = updateDivList.elementAt(0);
				String upstate = upNode.toPlainTextString().trim();
				if(upstate!=null&&upstate.contains("更新至")){
					snatchState="1";        // 继续抓取
				}else{
					snatchState="2";       //不需要继续抓取
				}
				 
			}
			
			parser.reset();
			NodeFilter filter_fenji = new AndFilter(
					new TagNameFilter("li"),new OrFilter(new HasAttributeFilter("clear","21"),new HasAttributeFilter("clear","10"))
					);
		//	org.htmlparser.filters.OrFilter
			NodeList fenjiList = parser.parse(filter_fenji);
			Set<String> subTvNameSet = new HashSet<String>();
			 
			TTvSubtv subTv = null;
			 
			if(fenjiList!=null&&fenjiList.size()>0){
				breakFor:for(int i=0;i<fenjiList.size();i++){
					Node linode = fenjiList.elementAt(i);
					NodeList aNodeList = linode.getChildren();
					for(int j=0;j<aNodeList.size();j++){
						Node aNode = aNodeList.elementAt(j);
						if(aNode instanceof LinkTag){
							LinkTag atag = (LinkTag)aNode;
							NodeList imagList = atag.getChildren();
							String subTVUrl = atag.getAttribute("href");
							for(int k=0;k<imagList.size();k++){
								Node imaNode = imagList.elementAt(k);
								if(imaNode instanceof ImageTag){
									ImageTag imageTag = (ImageTag)imaNode;
									String imageSrc = imageTag.getAttribute("src");
									
									
									String subTvName = imageTag.getAttribute("alt");
									 
									if(subTvNameSet.contains(subTvName)){
										break breakFor;
									}
									subTvNameSet.add(subTvName);
									subTv = snatchService.findSubTv(tvServerId, subTvName);
									if(subTv==null){
										subTv = new TTvSubtv();
										String subname = subTvName;
										if(subname!=null){
											subname = subname.trim();
											subname.replace(" ", "");
										}
										String imageName = "subtv"+subname;
										String imagePath = DownHtml.downImag(imageSrc, "tv",tvName,imageName);
										subTv.setImageUrl(imagePath);
										
										 
										subTv.setName(subTvName);
										subTv.setSource(server);
										subTv.setTvSnatch(new TTvSnatch(tvServerId));
										subTv.setSnatchState(0);
										subTv.setSnatchCount(0);
										subTv.setPayCount(0);
										subTv.setAddTime(new Date());
									}else{
										if(!isUpdate){//不需要更新
											subTv = null;
											subList.add(null);
											continue;
										}
									}
									subTv.setUrl(subTVUrl);
									subList.add(subTv);
									fenjiCount++;
								} 
							}
							 
							
							
						}
					}
					
				}
					
			}
			map.put("subList",subList);
			map.put("snatchState",snatchState);      //不需要继续抓取
			map.put("snatchUrl", fenjiPageurl);
			map.put("snatchPageNo", 1);
		}catch(Exception e){
			logger.error("抓取电视剧分析出错"+tvName+",出错信息为："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	public  Map getSubTv(String url,int hasLength,int countLength){
		return null;
	}

	public Map getSubTv(String url, String tvName, int hasLength,
			int countLength) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到电视剧分集embed
	 * @param url
	 * @return
	 */
	public  String getSubTvEmbedByUrl(String url){
		return null;
	}
	 
	public static void main(String[] args) {
		SohuUtil sohu = new SohuUtil(); 
		String url = "http://so.tv.sohu.com/list_p1100_p20_p3_p40_p5_p6_p73_p80_p9.html";
		List<TLink> linkList = sohu.getLinkByUrl(url, 2, 0);
		
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
