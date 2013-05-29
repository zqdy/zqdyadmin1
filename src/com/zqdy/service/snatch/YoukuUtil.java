package com.zqdy.service.snatch;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 
import java.util.List;
import java.util.Map; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Text;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter; 
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.TagNameFilter; 
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag; 
import org.htmlparser.util.NodeList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
import com.zqdy.core.utils.DownHtml;
import com.zqdy.core.utils.JSoupUtil;
import com.zqdy.core.utils.PatternUtil;
import com.zqdy.core.utils.StringUtils;  
import com.zqdy.dao.po.TLink;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.service.SnatchService;
import com.zqdy.service.SnatchUtilServer;

public class YoukuUtil implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(YoukuUtil.class);
	}
	/**
	 * 得到URL下页面的电影地址
	 * @param url
	 * @param soreceServer
	 * @param tvType
	 * @return
	 */
	public List<TLink> getLinkByUrl(String url,int soureServer, Integer tvType) {
		if(tvType==1){
			return getTvList(url,soureServer, tvType);
		}
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		} 
		Elements elemnts = doc.select("ul[class=p pv]");
		
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				Element ule = elemnts.get(i);
				Elements aels = ule.select("li[class=p_title] a");
				TLink link =  null;
				String name = "";
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
					Elements isChargeEk = ule.select("li[class=p_ischarge]");
					if(isChargeEk!=null&&isChargeEk.size()>0){
						link.setIsMember(1);
					}
					Elements subTitle = ule.select("li[class=p_subtitle]");
					if(subTitle!=null&&subTitle.size()>0){
						String shortSum = subTitle.get(0).text();
						link.setShortSummary(shortSum);
					}
					Elements statuses = ule.select("span[class=status]");
					if(statuses!=null&&statuses.size()>0){
						String status = statuses.get(0).text();
						if(status!=null){
							if("正片".equals(status.trim())){
								link.setType(1);
							}else if("记录片".equals(status.trim())){
								link.setType(2);
								link.setStatus(1);
							}else if("预告".equals(status.trim())){
								link.setType(3);
								link.setStatus(1);
							}
						}
						
						
						 
					}
					if(name!=null&&name.contains("《")){
						link.setType(2);
					}
					linkList.add(link);
				}
				
				
			}
		}
		
		return linkList;
	}
	
	private List<TLink> getTvList(String url,int soureServer, Integer tvType){
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		} 
		Elements elemnts = doc.select("div[class=items] ul[class=p] li[class=p_link]>a");
		
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				Element aels = elemnts.get(i);
				 
				TLink link =  null;
				String name = "";
				if(aels!=null){
				
					link = new TLink();
					name = aels.attr("title");
					String movieUrl = aels.attr("href");
					
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
		}
		
		return linkList;
	}
	
	/**
	 * 提取电视剧详细信息  
	 * @param url 
	 */
	public DoubanBo extractMovieDetail(String name,String url) {
		String playUrl = extractMoviePlayUrl(url);
		if(playUrl==null){
			return null;
		}
		Document doc = JSoupUtil.getJsoupDocumentByUrl(playUrl);   
		if(doc==null){
			return null;
		}
		DoubanBo douban = null;
		String embed = getDocMovieEmbed(doc);
	 
		Elements aElemnts = doc.select("div[class=show_intro] h1 a");
		if(aElemnts!=null&&aElemnts.size()>0){
			Element ael = aElemnts.get(0);
			String detailUrl = ael.attr("href"); 
			if(detailUrl!=null){
				douban = DoubanUtil.getMovieInfo(name,0);
				//douban = getMovieDetail(detailUrl);
			}
			if(douban==null){
				douban = new DoubanBo();
				String mvName = ael.text();
				douban.setName(mvName);
			}
		}
		if(embed!=null&douban!=null){
			douban.setEmbed(embed);
		}
		return douban;
	}
	
	private  DoubanBo getMovieDetail(String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		DoubanBo douban = new DoubanBo();
		String name = "";             //电影名称
		Elements nameSpanEls = doc.select("h1[class=title] span[class=name]");
		if(nameSpanEls!=null&&nameSpanEls.size()>0){
			Element nameEl = nameSpanEls.get(0);
			name = nameEl.text();
		}
		douban.setName(name);
		
		String year = "";           //上映年代
		Elements yearSpanEls = doc.select("h1[class=title] span[class=pub]");
		if(yearSpanEls!=null&&yearSpanEls.size()>0){
			Element yearEl = yearSpanEls.get(0);
			year = yearEl.text();
		}
		douban.setReleaseYear(year);
		
		String imageUrl = "";           //上映年代
		Elements imgEls = doc.select("ul[class=baseinfo] li[class=thumb] img");
		if(imgEls!=null&&imgEls.size()>0){
			Element imgElsEl = imgEls.get(0);
			imageUrl = imgElsEl.attr("src");
		}
		douban.setImageUrl(imageUrl);
		
		
		String rating = "";           //評分
		Elements ratingSpanEls = doc.select("span[class=rating_dp] em[class=num]");
		if(ratingSpanEls!=null&&ratingSpanEls.size()>0){
			Element ratinEl = ratingSpanEls.get(0);
			rating = ratinEl.text();
		}
		if(rating!=null&&!"".equals(rating)){
			douban.setRateScore(Float.valueOf(rating));
		}
		
		
		
		String alias = "";           //別名
		Elements aliasEls = doc.select("li[class=row1 alias]");
		if(aliasEls!=null&&aliasEls.size()>0){
			Element aliasEl = aliasEls.get(0);
			String aliasStr = aliasEl.html();
			if(aliasStr!=null){
				int index = aliasStr.indexOf("</label>");
				if(index!=-1){
					alias = aliasStr.substring(index+8, aliasStr.length());
					if(alias!=null){
						alias =alias.trim();
					}
				}
			} 
		}
		douban.setAlias(alias);
		
		List<String> areaList = new ArrayList<String>();         //地区
		Elements areaEls = doc.select("span[class=area] a");
		if(aliasEls!=null){
			for(int i=0;i<areaEls.size();i++){
				Element areael = areaEls.get(i);
				String area = areael.text();
				areaList.add(area);
			}
			 
		}
		douban.setAreaList(areaList); 
		
		List<String> genreList = new ArrayList<String>();         //類型
		Elements genreEls = doc.select("ul[class=baseinfo] span[class=type] a");
		if(genreEls!=null){
			for(int i=0;i<genreEls.size();i++){
				Element genreel = genreEls.get(i);
				String genre = genreel.text();
				genreList.add(genre);
			}
		}
		douban.setGenreList(genreList);
		List<DoubanActor> directorList = new ArrayList<DoubanActor>();
		//List<String> directorList = new ArrayList<String>();         //導演
		Elements directorEls = doc.select("span[class=director] a");
		if(directorEls!=null){
			
			for(int i=0;i<directorEls.size();i++){
				Element directel = directorEls.get(i);
				String direct = directel.text();
				DoubanActor doubanDirect = new DoubanActor(direct);
				directorList.add(doubanDirect);
			}
			 
		}
		douban.setDirectorList(directorList);
		
		List<DoubanActor> actorList = new ArrayList<DoubanActor>();         //演員
		Elements actorEls = doc.select("span[class=actor] a");
		if(actorEls!=null){
			for(int i=0;i<actorEls.size();i++){
				Element actortel = actorEls.get(i);
				String actor = actortel.text();
				DoubanActor doubanActor= new DoubanActor(actor);
				actorList.add(doubanActor);
			}
			 
		}
		douban.setActorList(actorList);
		
		String summary="";
		Elements summaryEls = doc.select("span[class=short]");
		if(summaryEls!=null&&summaryEls.size()>0){
			Element summaryEl = summaryEls.get(0);
			summary = summaryEl.text();
		}
		douban.setSummary(summary);
		
		Elements priceEls = doc.select("li[class=price]");
		if(priceEls!=null&&priceEls.size()>0){
			douban.setIsMember(1);
		}
		doc = null;
		return douban;
	}

	/**
	 * 提取电影共享地址
	 * @return
	 */
	public  String   extractMovieEmbed(String url){
		String playUrl = extractMoviePlayUrl(url);
		if(playUrl==null){
			return null;
		}
		Document doc = JSoupUtil.getJsoupDocumentByUrl(playUrl);   
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
		try {
			flashUrl = JSoupUtil.getElementAttrById(doc, "link2", "value");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{
			doc = null;
		}  
        if(flashUrl==null){
    	    return null;
        }
        String embed = "<embed src=\""+flashUrl+"\" quality=\"high\" flashvars =\"isAutoPlay=true\" width=\"1005\" height=\"513\" align=\"middle\" allowScriptAccess=\"sameDomain\" allowFullscreen=\"true\" type=\"application/x-shockwave-flash\"></embed>";
        return embed;
	}
	
	/**
	 * 提取电影播放地址
	 * @return
	 */
	public  String   extractMoviePlayUrl(String url){
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		String playUrl=url;
		try {
			Elements aEls = doc.select("li[class=action] a");
			if(aEls!=null&&aEls.size()>0){
				Element ael= aEls.get(0);
				if(ael!=null){
					playUrl = ael.attr("href");
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}  
       
		return playUrl;
	}
	
	 
 
	/**
	 * 提取每集电视剧共享地址
	 * 
	 * @param html
	 * @param regexEmbed
	 * @return
	 */
	public String extractSubTvEmbed(String html, String regexEmbed) {
		Pattern embedPt = Pattern.compile(regexEmbed);
		Matcher embedMc = embedPt.matcher(html);
		String embed = "";
		if (embedMc.find()) {
			embed = "<embed " + embedMc.group(1).trim() + "</embed>";
			// extractMap.put("embed", embed);
			logger.debug(embed);
		}
		return embed;
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
		//取放映年
		String year = "";
		Elements yearEles = doc.select("h1[class=title] span[class=pub]");
		if(yearEles!=null){
			Element yearEl = yearEles.get(0);
			if(yearEl!=null){
				year = yearEl.html();
			}
		} 
		doubanBo.setReleaseYear(year);
		
		Elements ratingEles = doc.select("span[class=ratingstar] em[class=num]");
		//取评分
		float rateScore =(float)0.0;
		if(ratingEles!=null){
			for(int i=0;i<ratingEles.size();i++){
				Element ael = ratingEles.get(i);
				if(ael!=null){
					String scoreStr = ael.html();
					rateScore = StringUtils.stringToFloat(scoreStr);
				}
			}
		}
		
		doubanBo.setRateScore(rateScore); 
		//取别名
		String alias = "";
		Elements aliasEles = doc.select("li[class=row1 alias]");
		if(aliasEles!=null){
			for(int i=0;i<aliasEles.size();i++){
				Element aliasEl = aliasEles.get(i);
				if(aliasEl!=null){
					String aliasStr = aliasEl.html();   //<li class="row1 alias"><label>别名:</label>Good Wife</li>
					if(aliasStr!=null){
						int index = aliasStr.indexOf("</label>");
						if(index!=-1){
							alias = aliasStr.substring(index+8, aliasStr.length());
							if(alias!=null){
								alias =alias.trim();
							}
						}
					} 
				}
			}
		} 
		doubanBo.setAlias(alias);
		
		//取地区
		List<String> areaList = new ArrayList<String>();
		Elements cuntryEles = doc.select("span[class=area] a");
		if(cuntryEles!=null){
			for(int i=0;i<cuntryEles.size();i++){
				Element areaEl = cuntryEles.get(i);
				if(areaEl!=null){
					String area = areaEl.html();
					areaList.add(area); 
				}
			}
		}
		doubanBo.setAreaList(areaList);
		
		//取类型
		List<String> genreList = new ArrayList<String>();
		Elements typeEles = doc.select("span[class=type] a");
		if(typeEles!=null){
			for(int i=0;i<typeEles.size();i++){
				Element typeEl = typeEles.get(i);
				if(typeEl!=null){
					String type = typeEl.html();
					genreList.add(type); 
				}
			}
		}
		doubanBo.setGenreList(genreList);
		
		//取主演
		List<DoubanActor> actorList = new ArrayList<DoubanActor>(); 
		Elements actorEles = doc.select("span[class=actor] a");
		if(actorEles!=null){
			for(int i=0;i<actorEles.size();i++){
				Element actorEl = actorEles.get(i);
				if(actorEl!=null){
					String actor = actorEl.html();
					DoubanActor Doubanactor = new DoubanActor(actor);
					actorList.add(Doubanactor); 
				}
			}
		}
		doubanBo.setActorList(actorList);
		
		
		//取集数
		
		String length ="";
		Elements lengthEles = doc.select("div[class=basenotice]");
		if(lengthEles!=null){
			Element lengthEle = lengthEles.get(0);
			String lengthStr = lengthEle.html();
			if(lengthStr!=null){
				int k1 = lengthStr.indexOf("共");
				int k2 = lengthStr.indexOf("集");
				length = lengthStr.substring(k1+1, k2);
			}
		}
		doubanBo.setLength(length);
		
		//取简介
		String summary = null;
		Elements descEles = doc.select("span[class=short]");
		if(descEles!=null){
			Element descEle = descEles.get(0);
			summary = descEle.html();
		}
		doubanBo.setSummary(summary);
		
		//取图片
		String imagUrl = "";
		Elements imageEles = doc.select("ul[class=baseinfo] li[class=thumb] img");
		if(imageEles!=null){
			Element imagEl = imageEles.get(0);
			if(imagEl!=null){
				imagUrl = imagEl.attr("src");
			}
		}
		doubanBo.setImageUrl(imagUrl);
		return doubanBo;
	}
	
	

	/**
	 * 迭代循环得到每集的URL 由于有时电视剧并未有全部更新完,所以需每天抓取,此时需要记录每次抓取的页的URL和当前抓取页吗
	 * 
	 * @param webDomain网站根目录
	 * @param fenjiPageurl
	 *            分集页UL
	 * @param charset
	 *            编码格式
	 * @param pageNo
	 *            抓取页
	 * @return Map 存储抓取到的每集的链接，及当前页号，和当前页的URL链接,以便以后继续抓取
	 */
	@SuppressWarnings("unchecked")
	public Map getFenjiUrl(Integer server, Integer tvSnatchId, String tvName,
			String webDomain, String fenjiPageurl, String charset, int pageNo,
			boolean isUpdate, SnatchService snatchService) {
		if (fenjiPageurl == null || "".equals(fenjiPageurl.trim())) {
			return null;
		}
		Map map = new HashMap();
		List<TTvSubtv> subList = new ArrayList<TTvSubtv>();
		try {
			boolean continueFlag = true;
			int fenjiCount = 0;
			String urlHtml = DownHtml.getHtmlByUrl(webDomain + fenjiPageurl,
					charset);
			Parser parser = Parser.createParser(urlHtml, charset);
			parser.setEncoding(charset);
			// 以下抓取每集URL
			NodeFilter filter_link = new AndFilter(new TagNameFilter("ul"),
					new HasParentFilter(new AndFilter(new TagNameFilter("div"),
							new HasAttributeFilter("class", "coll_10"))));
			NodeList uiList = parser.parse(filter_link);
			for (int j = 0; j < uiList.size(); j++) {
				NodeList linkList = uiList.elementAt(j).getChildren();

				if (linkList != null && linkList.size() > 0) {
					for (int i = 0; i < linkList.size(); i++) {
						Node liNode = linkList.elementAt(i);

						if (liNode instanceof Text) { // 没有子节点,还未有更新
							// continueFlag = false;
							continue;
						}
						NodeList nodeList = liNode.getChildren();
						LinkTag linkTag = (LinkTag) nodeList.elementAt(0);
						TTvSubtv subTv = null;
						String subTvName = linkTag.getAttribute("title");
						String href = linkTag.getAttribute("href");
						if (subTvName != null) {
							subTvName = subTvName.trim();
						}
						if (href != null) {
							href = href.trim();
						}
						logger.debug("title=" + subTvName + "  href=" + href);
						subTv = snatchService.findSubTv(tvSnatchId, subTvName);
						if (subTv == null) {
							subTv = new TTvSubtv();
							subTv.setName(subTvName);
							subTv.setSource(server);
							subTv.setTvSnatch(new TTvSnatch(tvSnatchId)) ;
							subTv.setSnatchState(0);
							subTv.setSnatchCount(0);
							subTv.setPayCount(0);
							subTv.setAddTime(new Date());
						} else {
							if (!isUpdate) {// 不需要更新
								subTv = null;
								subList.add(null);
								continue;
							}
						}
						subTv.setUrl(href);
						subList.add(subTv);
						fenjiCount++;
					}

				}
			}
			// 取图片

			parser.reset();
			NodeFilter filter_Img = new AndFilter(new TagNameFilter("li"),
					new HasAttributeFilter("class", "v_thumb"));
			NodeList imgNodeList = parser.parse(filter_Img);

			if (imgNodeList != null && imgNodeList.size() > 0) {
				for (int i = 0; i < imgNodeList.size(); i++) {
					Node liNode = imgNodeList.elementAt(i);
					NodeList nodeList = liNode.getChildren();
					ImageTag linkTag = (ImageTag) nodeList.elementAt(0);
					if (i >= fenjiCount) { // 防止超出范围
						break;
					}

					TTvSubtv subTv = subList.get(i);
					if (subTv != null) {
						String src = linkTag.getAttribute("src");
						String subname = subTv.getName();
						if (subname != null) {
							subname = subname.trim();
							subname.replace(" ", "");
						}
						String imageName = "subtv" + subname;
						String imagePath = DownHtml.downImag(src, "tv", tvName,
								imageName);
						subTv.setImageUrl(imagePath);
					}
				}
			}

			// 以下抓取每集简介描述 org.htmlparser.filters.NotFilter
			parser.reset();
			NodeFilter filter_shortDesc = new AndFilter(
					new TagNameFilter("div"), new AndFilter(
							new HasAttributeFilter("class", "v_desc"),
							new NotFilter(new HasAttributeFilter("style",
									"display:none;"))));
			NodeList shortDescList = parser.parse(filter_shortDesc);
			if (shortDescList != null && shortDescList.size() > 0) {
				for (int i = 0; i < shortDescList.size(); i++) {
					String shorDesc = shortDescList.elementAt(i)
							.toPlainTextString();
					if (i >= fenjiCount) { // 防止超出范围
						break;
					}
					TTvSubtv subTv = subList.get(i);
					if (subTv != null) {
						subTv.setShortSummary(shorDesc);
					}
					logger.debug("***" + shorDesc);
				}
			}
			// 以下是每集详细
			parser.reset();
			NodeFilter filter_longDesc = new AndFilter(
					new TagNameFilter("div"), new AndFilter(
							new HasAttributeFilter("class", "v_desc"),
							new HasAttributeFilter("style", "display:none;")));
			NodeList longDescList = parser.parse(filter_longDesc);
			if (longDescList != null && longDescList.size() > 0) {
				for (int i = 0; i < longDescList.size(); i++) {
					String longDsc = longDescList.elementAt(i)
							.toPlainTextString().trim();
					if (i >= fenjiCount) { // 防止超出范围
						break;
					}
					TTvSubtv subTv = subList.get(i);
					if (subTv != null) {
						subTv.setSummary(longDsc);
					}

					logger.debug("---------" + longDsc);
				}
			}
			if (!continueFlag) { // 未更新完，还需继续抓取
				map.put("subList", subList);
				map.put("snatchState", "1"); // 需继续抓取
				map.put("snatchUrl", fenjiPageurl);
				map.put("snatchPageNo", pageNo);
				return map;
			}
			parser.reset();
			NodeFilter filter_pageLink = new AndFilter(new TagNameFilter("a"),
					new HasParentFilter(new HasParentFilter(new AndFilter(
							new TagNameFilter("ul"), new HasAttributeFilter(
									"class", "pages")))));
			NodeList pageLinkList = parser.parse(filter_pageLink);
			int nextPageNo = pageNo + 1;
			String nextPageUrl = null;
			if (pageLinkList != null && pageLinkList.size() > 0) {
				for (int i = 0; i < pageLinkList.size(); i++) {
					String no = pageLinkList.elementAt(i).toPlainTextString();
					int pno = Integer.parseInt(no);
					if (pno == nextPageNo) {
						LinkTag linkTag = (LinkTag) pageLinkList.elementAt(i);
						nextPageUrl = linkTag.getAttribute("href");
						break;
					}
				}
			}
			map.put("subList", subList);
			map.put("snatchState", "2"); // 不需要继续抓取
			map.put("snatchUrl", fenjiPageurl);
			map.put("snatchPageNo", pageNo);
			if (nextPageUrl != null) {
				Map submap = getFenjiUrl(server, tvSnatchId, tvName, webDomain,
						nextPageUrl, charset, nextPageNo, isUpdate,
						snatchService);
				if (submap == null) {
					return map;
				}
				// 收集每页抓取集数
				List sList = (List) map.get("subList");
				List nextList = (List) submap.get("subList");
				if (subList == null) {
					subList = new ArrayList();
				}
				for (int i = 0; i < nextList.size(); i++) {
					sList.add(nextList.get(i));
				}

				map.put("subList", sList);
				map.put("snatchState", submap.get("snatchState")); // 不需要继续抓取
				map.put("snatchUrl", submap.get("snatchUrl"));
				map.put("snatchPageNo", submap.get("snatchPageNo"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	 

	/**
	 * 得到电视剧分集embed
	 * @param url
	 * @return
	 */
	public  String getSubTvEmbedByUrl(String url) { 
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);
		if(doc==null){
			return null;
		}
		Element et = doc.getElementById("link2");
		String attrValue = et.attr("value");
		String embedStr = "<embed src=\""+attrValue+"\" quality=\"high\"  flashvars =\"isAutoPlay=true\" width=\"1005\" height=\"513\" " +
				"align=\"middle\" allowScriptAccess=\"sameDomain\" allowFullscreen=\"true\" " +
				"type=\"application/x-shockwave-flash\"></embed>";
		return embedStr;
	}
	
	@SuppressWarnings("rawtypes")
	public   Map getSubTv(String url,String tvName,int hasLength,int countLength){
		if(url==null){
			return null;
		}
		Map subTvMap = new HashMap();
		try {
			int nameIndex = url.lastIndexOf("/");
			String name = url.substring(nameIndex+1, url.length());
			
			hasLength++;
			//得到分集json内容
			String fenjiJson = "http://www.youku.com/show_point/"+name+"?dt=json&divid=point_reload_"+hasLength+
					"&tab=0&__rt=1&__ro=point_reload_"+hasLength+"";
			Document doc = JSoupUtil.getJsoupDocumentByUrl(fenjiJson);   
			if(doc==null){
				return null;
			}
			List<String> titleList = new ArrayList<String>();
			List<String> hrefList = new ArrayList<String>();
			List<String> imageList = new ArrayList<String>();
			List<String> descList = new ArrayList<String>();
			List<String> embedList = new ArrayList<String>();
			List<Integer> volumeList = new ArrayList<Integer>();
			
			Elements fenjiItems = doc.select("div.item div.link a[href]"); // 取子集地址
			Elements fenjiImages = doc.select("div.item div.thumb img"); // 取图片地址
			Elements fenjiItemsDesc = doc.select("div.item div.desc"); // 取描述
			int k=0;
			int updateLen = 0;
			int thisUpdate = 0;
			if (fenjiItems != null) {
				thisUpdate = fenjiItems.size();
				for (int i = 0; i < fenjiItems.size(); i++) {
					Element fenjiEle = fenjiItems.get(i);
					String title = fenjiEle.attr("title");
					String href = fenjiEle.attr("href");
					 
						String patterStr1 = " \\d{2,3} ";
						String patterStr2 = " \\d{2,3}";
						String patterStr3 = "\\d{2,3}";
						String len = PatternUtil.matcherQishuResult(title, patterStr1); // 匹配如下情况“爱情公寓 第三季 01 答案”
						if(len==null){
							len = PatternUtil.matcherQishuResult(title, patterStr2); // 匹配如下情况“绿箭侠 第一季 01”
						}
						if(len==null){
							len = PatternUtil.matcherQishuResult(title, patterStr3); // 匹配如下情况“绿箭侠01”
						}
						if(len==null){
							len ="0";
						}
						len = len.trim();
						
						int currLen = Integer.parseInt(len);
						updateLen = currLen;
						if(currLen<hasLength){
							continue;
						}else if (currLen==hasLength){                 //从已经抓取的集数+1
							
							k = i;
						} else{
							updateLen = currLen;
						}
						
						 
						
					volumeList.add(currLen); 
					titleList.add(title);
					hrefList.add(href);
					String embed = getSubTvEmbedByUrl(href);
					
					embedList.add(embed);
					
				}
			}
			if (fenjiImages != null) {
				for (int i = k; i < fenjiImages.size(); i++) {
					Element fenjiImage = fenjiImages.get(i);
					String imageSrc = fenjiImage.attr("src");
					String suTvName = null;
					if(titleList.size()>i){
						suTvName = titleList.get(i);
					}else{
						suTvName= i+"";
					}
					String imagePath = DownHtml.downImag(imageSrc, "mv2",tvName,suTvName);
					imageList.add(imagePath);
				}
			}
			if (fenjiItemsDesc != null) {
				for (int i = k; i < fenjiItemsDesc.size(); i++) {
					Element descEle = fenjiItemsDesc.get(i);
					String desc = descEle.html();
					descList.add(desc);
					 
				}

			}
			
			if(updateLen<countLength&&thisUpdate==40){
				Map nexMap = getSubTv( url,tvName,updateLen,countLength);
				if(nexMap!=null){
					List<String> nextTitleList = (List<String>) nexMap.get("titleList");
					List<String> nextHrefList =  (List<String>) nexMap.get("hrefList");
					List<String> nextImageList =  (List<String>) nexMap.get("imageList");
					List<String> nextDescList =  (List<String>) nexMap.get("descList");
					List<String> nextEmbedList =  (List<String>) nexMap.get("embedList");
					List<Integer> nextVolumeList =   (List<Integer>) nexMap.get("volumeList");
					Integer nextUpdateLen = (Integer)nexMap.get("updateLen");
					if(nextUpdateLen!=null&&!"".equals(nextUpdateLen)){
						updateLen = nextUpdateLen;
					}
					titleList.addAll(nextTitleList);
					hrefList.addAll(nextHrefList);
					imageList.addAll(nextImageList);
					descList.addAll(nextDescList);
					embedList.addAll(nextEmbedList);
					volumeList.addAll(nextVolumeList);
				}
				
			}
			
			if(updateLen==0){
				hasLength--;
				updateLen = hasLength;
			}
			subTvMap.put("titleList", titleList);
			subTvMap.put("hrefList", hrefList);
			subTvMap.put("imageList", imageList);
			subTvMap.put("descList", descList);
			subTvMap.put("volumeList", volumeList);			
			subTvMap.put("embedList", embedList);
			subTvMap.put("updateLen", updateLen);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subTvMap;
	}
	

 

	public static void main(String[] args) {
		YoukuUtil you = new YoukuUtil();
//		
//		String url1 = "http://www.youku.com/show_page/id_zcc0e8384962411de83b1.html";
//		String palyUrl = you.extractMoviePlayUrl(url1);
//		System.out.println("palyUrl="+palyUrl);
		
		String url = "http://tv.youku.com/new/new/_page16113_1.html";
		List<TLink> linkList = you.getLinkByUrl(url, 1,1);
		
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
		
		 //String embed = you.getSubTvEmbedByUrl("http://v.youku.com/v_show/id_XMzA4NjE0NDIw.html");
		//String url = "http://www.youku.com/show_page/id_zfe875d127fec11e2b356.html";
		///you.extractTVDetail(url);
		
/**		String url = "http://v.youku.com/v_show/id_XNTAwNTk2MTg4.html";
		DoubanBo douban = you.extractMovieDetail("",url);
		 
		
	 
		 ;**/
		 
	}

	 

}
