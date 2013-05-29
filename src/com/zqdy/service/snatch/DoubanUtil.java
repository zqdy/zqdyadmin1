package com.zqdy.service.snatch;

import java.io.UnsupportedEncodingException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zqdy.core.utils.DownHtml;
import com.zqdy.core.utils.JSoupUtil;
import com.zqdy.core.utils.PatternUtil; 

public class DoubanUtil {
	private static Logger logger;

	static {
		logger = Logger.getLogger(DoubanUtil.class);
	}
	
	private static String key = "05513249e472c8020ff396ddad585806";
	private static String movieSearchPath = "https://api.douban.com/v2/movie/search?apikey="+key;
	private static String movieInfoU = "https://api.douban.com/v2/movie/subject/";
	private static String url = "http://movie.douban.com/subject_search?cat=1002&search_text=";    //查询电视剧地址
	private static String charset = "UTF-8";                                                      //页面编码
	
	private static String imagePatten = "<img src=\"(.*)\" title=\".*\" alt=\".*\" rel=\"v:image\" />";
	private static String scorePatten ="<strong class=\".*\" property=\"v:average\">(.*)</strong>";     //评分正是表达式
	private static String genrePatten = "<span property=\"v:genre\">([\u3E00-\u9FA5]*)</span>";              //电视剧类型 
	private static String areaPatten = "<span class=\"pl\">制片国家/地区:</span>(.*)<br/>"; //地区 
	private static String languagePatten = "<span class=\"pl\">语言:</span>(.*)<br/>"; //地区 
	private static String pubPatten = "<span property=\"v:initialReleaseDate\" content=\".*\">((.*))</span><br/>";  //播方日期
	private static String yearPatten ="<span class=\"year\" style=\".*\">(.*)</span>";   //年代 
	private static String lengthPatten = "<span class=\"pl\">集数:</span>(.*)<br/>";   	
	private static String summaryPatten ="<span property=\"v:summary\">(.*)<span class=\"pl\">";
	
	
	/**
	 * 得到电影或电视剧住处
	 * @param movieName
	 * @param type 0，电影，1：电视剧
	 * @return
	 */
	public static DoubanBo getMovieInfo(String name,int type){
		DoubanBo movieBo = null;
		if(name==null||"".equals(name.trim())){
			return null;
		}
		if(name.endsWith(" TV版")){                //去掉以“ TV版”结束的电视剧名称
			int k = name.indexOf(" TV版");
			name = name.substring(0, k);
		}
		try{
			name =PatternUtil.extractName(name);    //处理名字
			String movieId = getMovieIdByName(name);
			//logger.info("tvName="+name);
			if(movieId!=null){
				movieBo = new DoubanBo();
				String movieInfoUrl = movieInfoU+movieId+"?apikey="+key;
				String movieInfoJson = DownHtml.getHtmlByUrl(movieInfoUrl,charset);
				 
				JSONObject movieJson = JSONObject.fromObject(movieInfoJson);
				//得到评分
				JSONObject rating = movieJson.getJSONObject("rating");
				if(rating!=null){
					double average = rating.getDouble("average");
					movieBo.setRateScore(new Float(average));
				 
				}
				//
				String year = movieJson.getString("year");
				movieBo.setReleaseYear(year);
			 
				
				int id = movieJson.getInt("id");
				movieBo.setDoubanId(id);
			 
				
				int commentsCount = movieJson.getInt("comments_count");
				movieBo.setCommentsCount(commentsCount);
			 
				
				//得到图片地址
				JSONObject imageObj = movieJson.getJSONObject("images"); 
				if(imageObj!=null){
					String mediumImage = imageObj.getString("large");  // medium
					movieBo.setImageUrl(mediumImage); 
				}
				//String title = movieJson.getString("title");
				movieBo.setName(name); 
				String summary = movieJson.getString("summary");
				movieBo.setSummary(summary);
				//aka
				String aka = movieJson.getString("aka");
				if(aka!=null&&aka.length()>200){
					aka = aka.substring(0, 190);
				}
				movieBo.setAlias(aka); 
				//genres 类型
				JSONArray genresArr = movieJson.getJSONArray("genres");
				if(genresArr!=null){ 
					List<String> genreList = new ArrayList<String>(); 
					for(int i=0;i<genresArr.size();i++){
						String genres = genresArr.getString(i);
						genreList.add(genres); 
					}
					movieBo.setGenreList(genreList);
				}
				//countries 国家
				JSONArray countriesArr = movieJson.getJSONArray("countries");
				if(countriesArr!=null){ 
					List<String> areaList = new ArrayList<String>();
					for(int i=0;i<countriesArr.size();i++){
						String contry = countriesArr.getString(i);
						areaList.add(contry); 
					}
					movieBo.setAreaList(areaList);
				}
				//主演
				JSONArray actorArr = movieJson.getJSONArray("casts");
				if(actorArr!=null){
					 
					List<DoubanActor> actorList = new ArrayList<DoubanActor>();
					for(int i=0;i<actorArr.size();i++){
						JSONObject actorObj =(JSONObject)actorArr.get(i);
						if(actorObj!=null){
							DoubanActor acor = new DoubanActor();
							String doubanId = actorObj.getString("id");
							String actorName = actorObj.getString("name");							
							acor.setName(actorName);							  
							acor.setDoubanId(doubanId);
							String imagePath = null;
							try{
								JSONObject avatarsObj = actorObj.getJSONObject("avatars");
								if(avatarsObj!=null){
									imagePath = avatarsObj.getString("medium");
								}
							}catch(Exception e){}
							acor.setImagePath(imagePath);							
							actorList.add(acor);						 
						}
					}
					movieBo.setActorList(actorList);
				}
				//导演
				JSONArray directorsArr = movieJson.getJSONArray("directors");
				if(directorsArr!=null){ 
					List<DoubanActor> directorList = new ArrayList<DoubanActor>();
					for(int i=0;i<directorsArr.size();i++){
						JSONObject directorObj =(JSONObject)directorsArr.get(i);
						if(directorObj!=null){
							DoubanActor acor = new DoubanActor();
							String doubanId = directorObj.getString("id");
							String actorName = directorObj.getString("name");
							
							acor.setDoubanId(doubanId);
							acor.setName(actorName);
							String imagePath = null;
							try{
								JSONObject avatarsObj = directorObj.getJSONObject("avatars");
								if(avatarsObj!=null){
									imagePath = avatarsObj.getString("medium");
								}
							}catch(Exception e){}  
							acor.setImagePath(imagePath);
							directorList.add(acor); 
						}
					}
					movieBo.setDirectorList(directorList);
				}
				
				
				//取电视剧集数
				if(type==1){
//					String detailPageUrl = getUrlBySearchName(name);					 			
//					String detailHtml = DownHtml.getHtmlByUrl(detailPageUrl,charset);	
//					String  length = PatternUtil.matcherOneResult(detailHtml, lengthPatten);  //集数
//					movieBo.setLength(length);
//					logger.info("length："+length);
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		 //
		return movieBo;
	}
	
	
	/**
	 * 通过电影名称得到电影ID
	 * @param movieName
	 * @return
	 */
	public static String getMovieIdByName(String movieName){
		if(movieName==null){
			return null;
		}
		String movieId = null;
		movieName = PatternUtil.extractName(movieName);
		movieName = DownHtml.getHtmlByUrl2(movieName);
		 
		 
		String movieSearchUrl = movieSearchPath+"&q="+movieName;
	 
		String detailHtml = DownHtml.getHtmlByUrl(movieSearchUrl,charset);
		 
		JSONObject searchJson = JSONObject.fromObject(detailHtml);
		JSONArray subjects = searchJson.getJSONArray("subjects");
		if(subjects!=null&&subjects.size()>0){
			JSONObject subObj = (JSONObject)subjects.get(0);
			Object id = subObj.get("id");
			if(id!=null){
				movieId = id.toString();
			} 
		}
		return movieId;
	}
	
	/**
	 * 查询电影或电视剧名称，得到查询结果页面的Document
	 * @param name
	 * @return
	 */
	private static String getUrlBySearchName(String name){
		String encodeTvName = null;
		try {
			name = PatternUtil.extractName(name);
			encodeTvName = java.net. URLEncoder.encode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url1 = url+encodeTvName;
		
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url1);   
		if(doc==null){
			return null;
		}
		String resultUrl = null; 
		Elements ahrefList = doc.select("a.nbg");
		if(ahrefList!=null&&ahrefList.size()>0){
			Element alink = ahrefList.get(0);
			if(alink!=null){
				resultUrl = alink.attr("href");
			}
			
		}
		return resultUrl;
	}
	
	public static TvBo getTvInfo(String tvName){
		if(tvName==null||"".equals(tvName.trim())){
			return null;
		}
		if(tvName.endsWith(" TV版")){                //去掉以“ TV版”结束的电视剧名称
			int k = tvName.indexOf(" TV版");
			tvName = tvName.substring(0, k);
		}
		TvBo tvBo = new TvBo();
		try{
			tvBo.setName(tvName);
			tvName =PatternUtil.extractName(tvName);
			String detailPageUrl = getUrlBySearchName(tvName);
			 
			
			String detailHtml = DownHtml.getHtmlByUrl(detailPageUrl,charset);			
			if(detailHtml==null){
				return null;
			}
			Parser detailParser = Parser.createParser(detailHtml,charset);
		
			 
			NodeFilter infoFilter = new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("id","info"));
			NodeList  baseInfoList = detailParser.parse(infoFilter);
			if(baseInfoList!=null&&baseInfoList.size()>0){
				NodeList baseSpanList = baseInfoList.elementAt(0).getChildren();
				if(baseSpanList!=null&&baseSpanList.size()>0){
					for(int i=0;i<baseSpanList.size();i++){
						Node spanNode = baseSpanList.elementAt(i);                    //span
						if(spanNode instanceof Span ){
							NodeList inList = spanNode.getChildren();
							if(inList!=null&&inList.size()>1){        //导演、编剧、主演 span
								NodeList nameList = inList.elementAt(0).getChildren();                                 
								if(nameList!=null&&nameList.size()>0){
									String name  = nameList.elementAt(0).getText();
									 
									if("导演".equals(name)){										
										List<String> directorList = getDirActGer(inList);
										tvBo.setDirectorList(directorList);
									}else if("编剧".equals(name)){
										List<String> screenwriterList = getDirActGer(inList);
										tvBo.setScreenwriterList(screenwriterList);
									}else if("主演".equals(name)){
										List<String> actorList = getDirActGer(inList);
										tvBo.setActorList(actorList);
									}
								}
								
							} 							
						}
					}
				}
			}
			
			//取图片地址
			//detailParser = Parser.createParser(detailHtml,charset);
			detailParser.reset();
			NodeFilter imageFilter = new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("id","mainpic"));
			NodeList  imageList = detailParser.parse(imageFilter);
			if(imageList!=null){
				String mainPicHtml = imageList.toHtml();				
				String  imageUrl = PatternUtil.matcherOneResult(mainPicHtml, imagePatten);  //图片地址
				if(imageUrl!=null){
					String imagePath = DownHtml.downImag(imageUrl, "mv2",tvName,tvName);
					tvBo.setImageUrl(imagePath);
					 
				}
				
			}
			String baseInfo = baseInfoList.toHtml();
		 
					
			Float  rateScore = PatternUtil.matcherReateScore(detailHtml, scorePatten);  //区域
			tvBo.setRateScore(rateScore); 
			
			List<String>  genreList = PatternUtil.matcherMultipResult(baseInfo, genrePatten);  //得到电视剧类型
			tvBo.setGenreList(genreList); 
			
			String  areaName = PatternUtil.matcherOneResult(detailHtml, areaPatten);  //区域			
			tvBo.setArea(areaName); 
			
			String  language = PatternUtil.matcherOneResult(detailHtml, languagePatten);  //语言
			tvBo.setLanguage(language);
			 
			
			String  pubDate = PatternUtil.matcherOneResult(detailHtml, pubPatten);  //上映日期
			
			 
			
			tvBo.setPubDate(pubDate);
			
			String  releaseYear = PatternUtil.matcherOneResult(detailHtml, yearPatten);  //年代 
			if(releaseYear!=null){
				releaseYear = releaseYear.replace("(", "");
				releaseYear = releaseYear.replace(")", "");
			}
			 
			tvBo.setReleaseYear(releaseYear);
			
			
			String  length = PatternUtil.matcherOneResult(detailHtml, lengthPatten);  //集数
			tvBo.setLength(length);
			 
			
			
			
			String  summary = PatternUtil.matcherOneResult(detailHtml, summaryPatten);  //剧情简介
			tvBo.setSummary(summary);
			 
			
		}catch(Exception e){
			tvBo = null;
			logger.error(e.getMessage());
		}
		return tvBo;
	}
	
	/**
	 * 得到导演、主演、编剧
	 * @param inList
	 * @return
	 */
	private static List<String> getDirActGer(NodeList inList){
		List<String> nameList = new ArrayList<String>();
		for(int j=1;j<inList.size();j++){
			Node anode = inList.elementAt(j);
			if(anode instanceof LinkTag){
				Node linkTag = ((LinkTag) anode).getChild(0);
				if(linkTag!=null){
					String nameStr = linkTag.getText();
					nameList.add(nameStr); 
				}
				
			}
		}
		return nameList;
	}
	
	
	public static void main(String[] args){
		
		//String html ="数字电影";
		//String name = extractName(html);
		//	System.out.print(name); 6890730
		
			
		String movieInfoUrl ="http://api.douban.com/movie/subject/6890730/reviews?start-index=1&max-results=2";
		String movieInfoJson = DownHtml.getHtmlByUrl(movieInfoUrl,charset);
		System.out.println(movieInfoJson);
		/**getMovieInfo("大上海",0);
		getTvInfo("终极一班2");
	
		
		
		String tvName = "我家有喜 TV版";
		if(tvName.endsWith(" TV版")){
			int k = tvName.indexOf(" TV版");
			tvName = tvName.substring(0, k);
		}
		 
		//getMovieInfo("天生爱情狂");
		*/
		
		/**try {
			String str=java.net. URLEncoder.encode("老表，你好嘢","UTF-8");
			 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 **/
		
		
		 
	}

}
