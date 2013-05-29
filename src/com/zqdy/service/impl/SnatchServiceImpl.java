package com.zqdy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.core.common.util.DateConvert;
import com.zqdy.core.utils.ConvertUtil;
import com.zqdy.core.utils.DownHtml;
import com.zqdy.core.utils.PatternUtil;
import com.zqdy.dao.CommonDao;
import com.zqdy.dao.po.TActor; 
import com.zqdy.dao.po.TArea;
import com.zqdy.dao.po.TDirector; 
import com.zqdy.dao.po.TGenre;
import com.zqdy.dao.po.TLink;
import com.zqdy.dao.po.TMovie; 
import com.zqdy.dao.po.TMovieActor;
import com.zqdy.dao.po.TMovieArea;
import com.zqdy.dao.po.TMovieDirector;
import com.zqdy.dao.po.TMovieEmbed;
import com.zqdy.dao.po.TMovieGenre;
import com.zqdy.dao.po.TSource;
import com.zqdy.dao.po.TTv; 
import com.zqdy.dao.po.TTvActor;
import com.zqdy.dao.po.TTvSnatch;
import com.zqdy.dao.po.TTvSubtv;
import com.zqdy.service.SnatchService;
import com.zqdy.service.SnatchUtilServer; 
import com.zqdy.service.snatch.BdzyUtil;
import com.zqdy.service.snatch.DoubanBo;
import com.zqdy.service.snatch.IqyUtil;
import com.zqdy.service.snatch.LeTvUtil;
import com.zqdy.service.snatch.SohuUtil;  
import com.zqdy.service.snatch.TengxunUtil;
import com.zqdy.service.snatch.XunleiUtil;
import com.zqdy.service.snatch.YoukuUtil;
import com.zqdy.service.snatch.tudouUtil;
 

@SuppressWarnings("rawtypes")
public class SnatchServiceImpl implements SnatchService {
	
	private static Logger logger;
	static {
		logger = Logger.getLogger(SnatchServiceImpl.class);
	}
	
	public static String imagPath = null;
	
	
	private CommonDao commonDao;
	
	private static Map<Integer,TSource> snatchServerMap =null;
	
	private static Map<Integer,String> serverCharset=null;
	
	/**
	 * 抓取电影链接
	 */	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String snatchMovieLink(String url,String patterStr,int source,String charSet,Integer tvType){
		
		if(url==null||"".equals(url.trim())||patterStr==null||"".equals(patterStr)){
			return "snatch url is null";
		}
		SnatchUtilServer snatchUtilServer = getSnatchUtilServerByType(source);
		if(snatchUtilServer==null){
			return "snatchServer is not exist!";
		}
		List<TLink> mlinkList =  snatchUtilServer.getLinkByUrl(url,source,tvType);
		String msg = "";
		if(mlinkList==null){
			msg = "snatch tvLink false";
			return msg;
		}
		int count = mlinkList.size();
		int insertLink = 0 ;
		if(count>0){			
			String sql = " select count(1) from t_link where  source="+source;
			List<TLink>  insertLinkList = new ArrayList<TLink> ();
			for(int i=0;i<mlinkList.size();i++){
				try{
					TLink link = mlinkList.get(i);
					 
					String linkUrl = link.getUrl();
					 
					String sql1 = sql+" and  url='"+linkUrl+"'" ;				
					List isExist =  commonDao.findBySQL(sql1);
					int co = Integer.parseInt(isExist.get(0).toString());
					if(co ==0){
						insertLinkList.add(link);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			insertLink = insertLinkList.size();
			commonDao.createBatch(insertLinkList);
		}
		msg = "snatch "+count+" link,insert"+insertLink+" link to DB";
		logger.info(msg);
		return msg;		
	}
	
	/**
	 * 抓取上次未抓取完成的，而最新有更新的电视剧	
	 * @param pageNow
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String snatchTvSubTv(int pageNow){
		String nowDate = DateConvert.getStringDate();
		String preTime = DateConvert.getPreTime(nowDate,"-60");    //查询三小时前更新的
		 
		int pageSize = 30;		 
		String poClass = " TTvSnatch ";
		String condition = " where snatchState=0  and updateTime<'"+preTime+"'";
		CommonDto dto = commonDao.getResult(pageSize, pageNow, poClass, condition);
		List<TTvSnatch> resultList = (List<TTvSnatch>) dto.getProperty("result");
		for(int i=0;i<resultList.size();i++){
			TTvSnatch tvSnatch = resultList.get(i);
			updateSubTvInfo(tvSnatch,tvSnatch.getTv());     ////抓取最近更新电视剧子集
			 
		}
		Integer pageCount = (Integer) dto.getProperty("pageCount");
		if(pageCount>pageNow){
			pageNow++;
			return snatchTvSubTv(pageNow);
		}
		return "抓取成功";
	}
	/**
	 * 抓取详细页面
	 * @return
	 */
	public String snatchDetail	(String regexEmbed,String regexDetail,Integer tvType,boolean isUpdate,Integer pageSize){
		
		return snatchDetail(null,regexEmbed, regexDetail,
				 tvType, isUpdate,pageSize);
	}
	
	 
	
	/**
	 * 抓取详细页面
	 * @return
	 */
	
	public String snatchDetail(Integer server,String regexEmbed,String regexDetail,
			Integer tvType,boolean isUpdate,Integer pageSize){
		
	 
		
		if(tvType==2){  //抓取电视剧子集
			return snatchSubTvDetail(regexEmbed,regexDetail,tvType,isUpdate, pageSize);
		}
		String poClass = "TLink";
		int pageNow = 0;
		String condition = " where status=0 and snatchCount<3 and  tvType="+tvType;
		if(server!=null){
			condition += " and source="+server;
		}		 
		int sucessCount = 0; 
		CommonDto commonDto = commonDao.getResult(pageSize, pageNow, poClass, condition);
		List result = (List)commonDto.getProperty("result");
		
		for(int i=0;i<result.size();i++){
			TLink link = (TLink)result.get(i);
			boolean snatchFlag = updateLink(link,regexEmbed,regexDetail,tvType,isUpdate);
			if(snatchFlag){
				sucessCount++;
			}
			
		}
		String msg = "共抓取"+result.size()+" 个电影详细，抓取成功"+sucessCount+"个";;
		logger.info(" snatch movie success "+msg);
		return msg;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public boolean updateLink(TLink link,String regexEmbed,String regexDetail,Integer tvType,boolean isUpdate){
		boolean flag = false;
		if(link==null){
			return flag;
		}
		try{			
			if(link.getSnatchCount()>3){     //抓取3次未成功，即不再抓取
				link.setStatus(3);				
				logger.error(link.getName()+" snatch count>3"+link.getUrl());
				flag = false;
			}else{
				String url1 = link.getUrl();
				Integer server = link.getSource();
				String charset = getServerChartset(server);
				
				//String charset = "UTF-8";
				if(tvType==0){     //抓取电影  Integer sourceId,String movieName,String detailUrl,String subTitle ,int type ,int isMember
					int type = link.getType()==null?0:link.getType();
					int isMember = link.getIsMember()==null?0:link.getIsMember();
					flag = stractMovieInfo(link.getSource(),link.getName(),link.getUrl(),link.getShortSummary(),type,isMember);
				}else if(tvType==1){    //抓取电视剧
					flag = stractTvInfo(url1 ,link.getName(),server,charset,isUpdate,regexDetail); 
				}
				
				if(flag){
					link.setStatus(1);
					logger.info("snatch link success，name："+link.getName()+" source="+link.getSource());
				}else{
					int snatchCount = link.getSnatchCount();
					snatchCount ++;			 
					link.setSnatchCount(snatchCount);
					logger.error("snatch link false，name："+link.getName()+" url："+link.getUrl());
				} 
			}
		}catch(Exception e){			
			//计算出差次数
			if(link!=null){
				int snatchCount = link.getSnatchCount();
				snatchCount ++;			 
				link.setSnatchCount(snatchCount);
				logger.error("snatch link error name："+link.getName()+" url："+link.getUrl()+" ,message:"+e.getMessage());
			} 
			flag = false;
		}finally{
			commonDao.update(link);
		}
		return flag;
	}
	
	/**
	 * 提取电影详细信息
	 * @param url1
	 * @param server
	 * @param moveName
	 * @param regexEmbed
	 * @param regexDetail
	 * @return
	 */
	@SuppressWarnings({  "unchecked" })
	private boolean  stractMovieInfo(Integer sourceId,String movieName,String detailUrl,
			String subTitle ,int type ,int isMember ){
		boolean stractFlag = false;  
		  
		SnatchUtilServer snatchUtilServer = getSnatchUtilServerByType(sourceId);
		if(snatchUtilServer==null){
			return false;
		}	
		try{  	
			
			//提取视频共享地址			
			//logger.info("-------------snatch movie "+movieName);
			if(movieName!=null){
				movieName = PatternUtil.extractName(movieName);
				movieName = movieName.trim();
			}
			String hql = "  from TMovie where name ='"+movieName+"' ";
			List isExist =  commonDao.findByHQL(hql);
			 
			String playUrl = null;
			
			playUrl = snatchUtilServer.extractMoviePlayUrl(detailUrl);
			if(playUrl==null||"".equals(playUrl.trim())){
				playUrl = detailUrl;
			}
			 
			
			if(isExist!=null&&isExist.size()>0){     //电影在数据库已经存在，则只需要新增地址即可
				String embedInfo  =  snatchUtilServer.extractMovieEmbed(detailUrl);
				         //添加新的共享地址
					TMovie movie =(TMovie)isExist.get(0);
					List<TMovieEmbed> embedList = new ArrayList<TMovieEmbed>();
					TMovieEmbed embed = addMovieEmbed(playUrl,movieName,embedInfo,sourceId,movie,isMember,type);
					
					if(embed!=null){
						embedList.add(embed);	
					}	
					
					if(subTitle!=null&&!"".equals(subTitle.trim())){
						if(movie.getShortSummary()!=null&&!"".equals(movie.getShortSummary().trim())){
							movie.setShortSummary(subTitle);
						}
					}
					Integer mMember = movie.getIsMember()==null?0:movie.getIsMember();
					 
					if(mMember==1){
						if(type==1){
							movie.setIsMember(isMember);
						}
						
					} 
					movie.setEmbedList(embedList);
					commonDao.update(movie);
					stractFlag = true;
				 
				
				
			}else{    //电影未存在
				if(movieName==null||"".equals(movieName.trim())){
					return false;
				} 
				DoubanBo doubanBo = snatchUtilServer.extractMovieDetail(movieName,detailUrl); //DoubanUtil.getMovieInfo(movieName,0);
				stractFlag = createMovie(doubanBo,movieName,detailUrl,sourceId,
						type,isMember,subTitle,playUrl);
			}				
		}catch(Exception e){ 
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			 
		}
		 
		return stractFlag;
	}
	
	/**
	 * 新建電影
	 * @param snatchUtilServer
	 * @param movieName
	 * @param detailUrl
	 * @param sourceId
	 * @param type
	 * @param isMember
	 * @param subTitle
	 * @param playUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean createMovie(DoubanBo doubanBo,String movieName,String detailUrl,int sourceId,
			int type,int isMember,String subTitle,String playUrl ){
		String embedInfo = null;
		TMovie movie   = new TMovie();
		 
		if(doubanBo==null||doubanBo.getName()==null){
			return false;
		} 
		embedInfo = doubanBo.getEmbed();
		 
		if(isMember==1){
			movie.setIsMember(isMember);         //收费电影
		}else if(type ==1){
			movie.setIsMember(2);                 //正片免费
		} 
		if(subTitle!=null&&!"".equals(subTitle.trim())){
			movie.setShortSummary(subTitle);
		}
		movie.setName(movieName);
		movie.setPlayCount(0);
		movie.setAddTime(new Date()); 
		commonDao.create(movie);				
		
		movie = ConvertUtil.convertMovieBoToMovie(doubanBo,movie,sourceId,this);
		if(movie!=null&&movie.getName()==null){
			 return false;
		 }
		List<TMovieEmbed> embedList = new ArrayList<TMovieEmbed>();
		
		if(sourceId==100){
			List<String> playList = doubanBo.getPlayList();
			if(playList!=null){
				for(int i=0;i<playList.size();i++){
					String purl = playList.get(i);
					TMovieEmbed embed = addMovieEmbed(purl,movieName,null,sourceId,movie,isMember,type);
					if(embed!=null){
						embedList.add(embed);	
					}			
					
				}
			}
		
		}else{
			TMovieEmbed embed = addMovieEmbed(playUrl,movieName,embedInfo,sourceId,movie,isMember,type);
			if(embed!=null){
				embedList.add(embed);	
			}				
		}		
		movie.setEmbedList(embedList);		 
		commonDao.update(movie);
		return true;
	}
	
	/**
	 * 新增播放embed
	 * @param playUrl
	 * @param movieName
	 * @param sourceId
	 * @param movie
	 * @param isMember
	 * @param type
	 * @return
	 */
	private  TMovieEmbed   addMovieEmbed(String playUrl,String movieName,String embedInfor,int sourceId,
			TMovie movie,int isMember,int type){
		String hql2 = "select count(1) from TMovieEmbed where url='"+playUrl.trim()+"'";
		List urlIsExist =  commonDao.findByHQL(hql2);
		if(urlIsExist!=null&&urlIsExist.size()>0){         //播放地址已經存在
			return null;
		}
		TMovieEmbed embed = new TMovieEmbed();	
		
		embed.setName(movieName);
		embed.setEmbed(embedInfor);
		embed.setSource(new TSource(sourceId));
		embed.setPlayCount(0);
		embed.setMovie(movie); 
		embed.setIsMember(isMember);
		embed.setType(type);
		embed.setUrl(playUrl.trim());
		commonDao.create(embed);
		return embed;
	}
	
	public void addBdzyMovie(DoubanBo douban){
		if(douban==null||douban.getName()==null){
			return;
		}
		String movieName = douban.getName().trim();
		
		String hql = "  from TMovie where name ='"+movieName+"' ";
		List isExist =  commonDao.findByHQL(hql);
		String playUrl = null;
		String embedInfo = null;
		int sourceId = 100;
		int isMember=0;
		int type = 1;
		String subTitle = douban.getShortSummary();
		if(isExist!=null&&isExist.size()>0){     //电影在数据库已经存在，则只需要新增地址即可
			          //添加新的共享地址
				TMovie movie =(TMovie)isExist.get(0);
				
				List<TMovieEmbed> embedList = new ArrayList<TMovieEmbed>();
				TMovieEmbed embed = addMovieEmbed(playUrl,movieName,embedInfo,sourceId,movie,isMember,type);
				embedList.add(embed);
				movie.setEmbedList(embedList);
				commonDao.update(movie); 
			
		}else{    //电影未存在
			  createMovie(douban,movieName,playUrl,sourceId,
					type,isMember,subTitle,playUrl);
		}				
	}
	
	/**
	 * 抓取八度資源
	 */
	public void snatchBdzy(){
		BdzyUtil bdzy = new BdzyUtil();
		String mainUrl = "http://www.bdzy.cc";
		String indexUrl = "/list/?0-1.html";
		List<DoubanBo> boubanList = bdzy.snatchInfo(null, mainUrl+indexUrl);
		for(int i=0;i<boubanList.size();i++){
			DoubanBo douban = boubanList.get(i);
			List<String> genreList = douban.getGenreList();
			if(genreList!=null){
				String genre = genreList.get(i);
				if(genre!=null){
					if(genre.contains("电视剧")){
						
					}else if(genre.contains("卡通动漫")){
						
					}else if(genre.contains("综艺节目")){
						
					}else if(genre.contains("纪录片")){
						
					}else{
						addBdzyMovie(douban);
					}
				}
			}
			
		}
		
	}
	/**
	 * 抓取电视剧详细页面
	 * @return
	 */ 
	public String snatchSubTvDetail(String regexEmbed,String regexDetail,Integer tvType,boolean isUpdate,Integer pageSize){
		
		
		int pageNow = 0;
		
		SnatchUtilServer snatchUtilServer = getSnatchUtilServerByType(tvType);
		if(snatchUtilServer==null){
			return "抓取服务器不存在";
		}
		
		String condition = " where snatchState=0 and snatchCount<1 ";
		int sucessCount = 0;
		String poClass = "TTvSubtv";
		CommonDto commonDto = commonDao.getResult(pageSize, pageNow, poClass, condition);
		List result = (List)commonDto.getProperty("result");
		String charSet = null;
		for(int i=0;i<result.size();i++){
			TTvSubtv subTv = (TTvSubtv)result.get(i);	
			try{
				if(subTv!=null&&subTv.getUrl()!=null&&subTv.getEmbed()==null){
					logger.info("----------------snatch subTv"+subTv.getName());
					sucessCount++;
					String subUrl = subTv.getUrl();
					Integer sourceServerId = subTv.getSource();
					charSet = getServerChartset(sourceServerId);
					String urlHtml1 = DownHtml.getHtmlByUrl(subUrl,charSet);
					String embed = snatchUtilServer.extractSubTvEmbed(urlHtml1,regexEmbed);
					if(embed!=null&&!"".equals(embed.trim())){
						subTv.setEmbed(embed);
						subTv.setSnatchState(1);
					}else{
						logger.error("error未抓取到电视剧共享，名称为："+subTv.getName()+" 地址为："+subTv.getUrl()+"");
					}
					int snatchCount = subTv.getSnatchCount();
					subTv.setSnatchCount(++snatchCount);
					commonDao.saveOrUpdate(subTv);
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("error 抓取电视剧共享出错，出错名称为："+subTv.getName()+" 出错地址为："+subTv.getUrl()+" 报错信息为："+e.getMessage());
			}
			
		}
		String msg = "共抓取"+sucessCount+" 集电视"; 
		return msg;
	}
	/**
	 * 提取电视剧详情
	 * 先通过电视剧名称查询数据库中是否已经存在该电视剧住处，
	 * 如果不存在则抓取豆瓣网上住处，插入数据库
	 * @param url1
	 * @param tvName
	 * @param server
	 * @param charset
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean stractTvInfo(String url,String tvName,Integer server,String charset,boolean isUpdate,
			String fenqiRegex){
		boolean stractFlag = false;
		if(tvName==null||"".equals(tvName.trim())){
			return stractFlag;
		}
		logger.info("-------------snatch tv "+tvName);
		String hql = " from TTv where name= '"+tvName+"' ";
		TTv tv =(TTv)commonDao.uniqueFind(hql);
		
		if(tv==null){                     
			//如果数据库不存在该电视剧住处，则抓取 豆瓣网上的电视剧住处，
			//如果抓取失败，则抓取三次
			int k=0;
			DoubanBo doubanBo = null;
			do{
				k++;
				SnatchUtilServer snatchUtilServer = getSnatchUtilServerByType(server);
				doubanBo =snatchUtilServer.extractTVDetail(url);
				
				//doubanBo = DoubanUtil.getMovieInfo(tvName,1);
				//tvBo = DoubanUtil.getTvInfo(tvName);   //得到豆瓣电视剧信息
			}while(doubanBo==null&&k<4);                   //如果抓取失败，则循环抓取3次
			if(doubanBo==null){
				return false;
			}
			doubanBo.setName(tvName);
			tv = new TTv();
			tv.setName(tvName);	
			//tv.setPlayCount(playCount)
			tv = (TTv)commonDao.create(tv);
			tv = ConvertUtil.convertTvBoToTV(doubanBo,tv,server, this);    //将tvBo转换数据库TTV
			if(tv==null){
				tv = new TTv();
				tv.setAddTime(new Date()); 
				tv.setLength(0);
				tv.setUpdateLength(0); 
				tv.setStatus(0);
				tv.setPlayCount(0);
			}			
			tv = (TTv)commonDao.update(tv);
			stractFlag = true;
		} 
  	 
		try{
			
			//保存抓取网站电视剧信息
			String hql1 = " from TTvSnatch where tv.id="+tv.getId()+" and server.id="+server;
			TTvSnatch tvSnatch = (TTvSnatch)commonDao.uniqueFind(hql1);
			if(tvSnatch==null){
				tvSnatch = new TTvSnatch();
				tvSnatch.setSnatchState(0);               //未抓取
				tvSnatch.setSnatchCount(0);
				tvSnatch.setMainUrl(url);
				tvSnatch.setUpdateLength(0);
				tvSnatch.setTvLength(tv.getLength());             //抓取多少集
				tvSnatch.setSource(new TSource(server));
				tvSnatch.setAddTime(new Date());				
				tvSnatch.setTv(tv);
				
				tvSnatch = (TTvSnatch)commonDao.create(tvSnatch);
			}
			updateSubTvInfo(tvSnatch,tv);      //抓取电视剧子集
			 
			 
		}catch(Exception e){
			logger.error("snatch tv error name："+tvName+" url："+url+" ,message:"+e.getMessage());
			e.printStackTrace();
		}
		if(stractFlag){
			logger.info("-------------snatch tv success "+tvName);
		}else{
			logger.info("-------------snatch tv false "+tvName);
		}
		
		return stractFlag;
	}
	
	/**
	 * 抓取电视剧分集信息
	 * @param tvSnatch
	 * @param tv
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public boolean updateSubTvInfo(TTvSnatch tvSnatch,TTv tv){
		boolean stractFlag = false;
		try{
		
			if(tvSnatch==null||tv==null){
				return false;
			} else{
				if(tv.getLength()==tvSnatch.getUpdateLength()){     //已经全部抓取完，即无需再抓取
					stractFlag = true;
					return stractFlag;
				}
			} 
			TSource source = tvSnatch.getSource();
			if(source==null){
				return stractFlag;
			}
			SnatchUtilServer snatchUtilServer = getSnatchUtilServerByType(source.getId());
			Integer tvLen = tv.getLength();
			if(snatchUtilServer==null||tvLen==null||tvLen.intValue()==0){
				return false;
			}
			Integer updLength = tvSnatch.getUpdateLength();
			if(updLength==null){
				updLength = 0;
				tvSnatch.setUpdateLength(0);
			}
			logger.info("-------------begin snatch "+tv.getName()+" ,updLength="+updLength);
			Map subTvMap = snatchUtilServer.getSubTv(tvSnatch.getMainUrl(),tv.getName(),updLength, tvLen);           //抓取各子集电视剧
			if(subTvMap==null){
				logger.error("subTv error name ："+tv.getName()+" url："+tvSnatch.getMainUrl());
				return stractFlag;
			}	 
			
			Integer updateLen = (Integer)subTvMap.get("updateLen");
			
			logger.info("-------------end snatch "+tv.getName()+" ,has snatch updLength="+updateLen);
			if(updateLen==null){
				updateLen =0;
			}
			if(updateLen!=0){
				tvSnatch.setTvLength(tvLen);
				tvSnatch.setUpdateLength(updateLen);
			}
			
			if(tvLen.equals(updateLen)){
				tvSnatch.setSnatchState(1);                                   //已抓取完成
			}
			if(updateLen>tvLen){                                     //更新的集数大于总集数，如“爱·回家”豆瓣上是180集，而优酷上有191集	
				tv.setLength(updateLen);
				tvSnatch.setSnatchState(1);
			}
			
			Integer snatchCount = tvSnatch.getSnatchCount();
			snatchCount++;
			tvSnatch.setSnatchCount(snatchCount);                             //更新抓取次数
			if(snatchCount==500){                                             //如果抓取500次还未抓取完，则停止抓取
				tvSnatch.setSnatchState(1);  
			}
			
			
			
			List subTvList = getSubTvList(subTvMap,source.getId(),tvSnatch.getId());      //得到subTv     
			if(subTvList.size()>0){	
				int tvUpdateLen = tv.getUpdateLength()==null?0: tv.getUpdateLength();    //更新电视剧抓取集数
				if(updateLen>tvUpdateLen){
					tv.setUpdateLength(updateLen);
				}				
				tv.setUpdateTime(new Date());
				List tvSourceList = tv.getTvSourceList();
				if(tvSourceList==null){
					tvSourceList = new ArrayList();
				}
				tvSourceList.add(tvSnatch);
				tv.setTvSourceList(tvSourceList);
				
				tv = (TTv)commonDao.update(tv);				
				this.commonDao.createBatch(subTvList);
				
				List dbSubTvList = tvSnatch.getSubTvList();
				if(dbSubTvList==null){
					dbSubTvList = new ArrayList();
				}
				dbSubTvList.addAll(subTvList);				
				tvSnatch.setSubTvList(dbSubTvList);
			}
			tvSnatch.setTv(tv);
			tvSnatch.setUpdateTime(new Date());	
			commonDao.update(tvSnatch);
			stractFlag = true;
		}catch(Exception e){
			e.printStackTrace();
			stractFlag = false;
			logger.error("tvSnatch errro name=："+tv.getName()+" url："+tvSnatch.getMainUrl()+" ,message:"+e.getMessage());
			e.printStackTrace();
		}
		
		return stractFlag;
	}
	
	/**
	 * 抓取以前未抓取到的子集embed
	 */
	@SuppressWarnings({"unchecked" })
	public void snatchSubTv(){
		String hql = "from TTvSubtv where embed is null  and snatchCount<30";
		List subTvList = commonDao.getListByHqlAndCount(hql, 30);
		String url = null;
		if(subTvList!=null){
			for(int i=0;i<subTvList.size();i++){
				TTvSubtv subTv = (TTvSubtv)subTvList.get(i);
				url = subTv.getUrl();
				if(url!=null){
					Integer soureServerId = subTv.getSource();
					SnatchUtilServer snatchUtilServer = getSnatchUtilServerByType(soureServerId);
					String embed = snatchUtilServer.getSubTvEmbedByUrl(url);
					Integer snatchCount = subTv.getSnatchCount();   
					if(snatchCount!=null){
						snatchCount++;
					}else{
						snatchCount =2;
					}
					subTv.setSnatchCount(snatchCount);    //设置抓取次数
					if(embed!=null){
						subTv.setEmbed(embed);
					}
					commonDao.update(subTv);
				}
			}
		}
	}
	
	
	
	/**
	 * 将从各工具类中得到的  subTvMap 转成subTv Po
	 * @param subTvMap
	 * @param server  抓取网上
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private List<TTvSubtv> getSubTvList(Map subTvMap,Integer server,Integer tvSnatchId){
		List<String> titleList = (List<String>) subTvMap.get("titleList");
		List<String> hrefList =  (List<String>) subTvMap.get("hrefList");
		List<String> imageList =  (List<String>) subTvMap.get("imageList");
		List<String> descList =  (List<String>) subTvMap.get("descList");
		List<Integer> volumeList =  (List<Integer>) subTvMap.get("volumeList");
		List<String> embedList =  (List<String>) subTvMap.get("embedList");
		
		List<TTvSubtv> subTvList = new ArrayList<TTvSubtv>();
		if(titleList!=null){
			for(int i=0;i<titleList.size();i++){
				TTvSubtv subTv = new TTvSubtv();
				subTv.setName(titleList.get(i));
				subTv.setSource(server);
				subTv.setTvSnatch(new TTvSnatch(tvSnatchId));
				subTv.setSnatchCount(1);
				subTv.setAddTime(new Date());
				subTv.setPayCount(0);
				
				String imageUrl = null;     //保存图片地址	
				String subTvUrl = null;
				String desc = null;
				String embed = null;
				if(imageList !=null&&imageList.size()>0){
					imageUrl = imageList.get(i);
					subTv.setImageUrl(imageUrl);    
				}				
				        //电视剧地址
				if(hrefList!=null&&hrefList.size()>i){
					subTvUrl = hrefList.get(i);
					subTv.setUrl(subTvUrl);
				}
				//分集描述
				if(descList!=null&&descList.size()>i){
					desc = descList.get(i);
					subTv.setSummary(desc);
				}
				if(volumeList!=null&&volumeList.size()>i){
					Integer volume = volumeList.get(i);
					subTv.setVolume(volume);
				}
				
				if(embedList!=null&&embedList.size()>i){
					embed = embedList.get(i);
					subTv.setEmbed(embed);
					subTv.setSnatchState(1);
				}else{
					subTv.setSnatchState(0);
				}
				subTvList.add(subTv);
			}
		}
		return subTvList;
	}
	
	
	
	/**
	 * 查询分集
	 * @param server
	 * @param subTvName
	 * @return
	 */
	public TTvSubtv findSubTv(Integer tvServer,String subTvName){
		String hql  = " from TTvSubtv where tvServerId="+tvServer+" and name='"+subTvName+"'";
		TTvSubtv dbsubtv = (TTvSubtv)commonDao.uniqueFind(hql);
		return dbsubtv;
	}
	
	
	public String extractByRegex(String html,String regex){
		String  str = null;
		if(regex==null||"".equals(regex)){
			return str;
		}
		
		Pattern pt = Pattern.compile(regex);
		Matcher  mc = pt.matcher(html);
		if(mc.find()){
			str = mc.group(1).trim();				
		}
		return str;
	}
	
	/**
	 * 根据区域名称得到id
	 * @param areaName
	 * @return
	 */
	public  TArea getArea(String areaName){
		String hql = " from TArea where name='"+areaName+"'";
		return (TArea)commonDao.uniqueFind(hql);
		 
	} 
	/**
	 * 创建电影国家
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieArea createMovieAreaIfNoCreate(String areaName,int index,Integer movieId){
		if(movieId==null||areaName==null){
			return null;
		}
		TArea area = createAreaIfNoCreate(areaName);
		TMovieArea movieArea = null;
		 
		String hql = "from TMovieArea where area.id="+area.getId()+" and movie.id="+movieId;
		movieArea = (TMovieArea)commonDao.uniqueFind(hql);
		 
		if(movieArea==null){
			movieArea = new TMovieArea();
			movieArea.setMovie(new TMovie(movieId));
			movieArea.setArea(area); 
			movieArea.setInd(index);
			movieArea = (TMovieArea)createObject(movieArea);
		}
		
		return movieArea;
	}
	/*
	 * 得到地区，如果数据库没有，则创建
	 */
	public  TArea createAreaIfNoCreate(String areaName){
		if(areaName==null){
			return null;
		}
		areaName = areaName.trim();
		if("中国大陆".equals(areaName)){
			areaName = "大陆";
		}else if("中国香港".equals(areaName)){
			areaName = "香港";
		}else if("中国台湾".equals(areaName)){
			areaName="台湾";
		}
		TArea area = getArea(areaName);
		if(area==null){
			area = new TArea();
			area.setName(areaName);
			area = (TArea)createObject(area); 
		}
		return area;
	}
	
	/**
	 * 根据演员名称得到id
	 * @param areaName
	 * @return
	 */
	public  TActor getActor(String actorName){
		String hql = " from TActor where name='"+actorName+"'";
		return (TActor)commonDao.uniqueFind(hql);
	} 
	/*
	 * 得到演员，如果数据库没有，则创建
	 */
	public  TActor createActorIfNoCreate(String actorName){
		return createActorIfNoCreate(actorName,null,null);
	}
	/*
	 * 得到演员，如果数据库没有，则创建
	 */
	@SuppressWarnings("unchecked")
	public  TActor createActorIfNoCreate(String actorName,String doubanId,String imageUrl){
		if(actorName!=null&&actorName.length()>50){
			actorName = actorName.substring(0, 40);
		}
		TActor actor = getActor(actorName);
		if(actor==null){
			actor = new TActor();
			actor.setName(actorName);
			actor.setWorkCount(1);
			actor = (TActor)createObject(actor);
		}
		boolean updateFlag = false;
		if(doubanId!=null&&!"".equals(doubanId.trim())&&actor.getDoubanId()==null){
			actor.setDoubanId(doubanId);
			updateFlag = true;
		}
		if(imageUrl!=null&&!"".equals(imageUrl.trim())&&actor.getImagePath()==null){
			int actorId = actor.getId();
			int j =actorId/300; 
			String	imagePath = DownHtml.downImag(imageUrl, "actor"+j, actorName,actorName);
			actor.setImagePath(imagePath);
			updateFlag = true;
		}
		if(updateFlag){
			commonDao.update(actor);
		}
		return actor;
	}
	
	/**
	 * 根据导演名称得到id
	 * @param areaName
	 * @return
	 */
	public  TDirector getDirector(String directorName){
		String hql = " from TDirector where name='"+directorName+"'";
		return (TDirector)commonDao.uniqueFind(hql);
	} 
	/*
	 * 得到演员，如果数据库没有，则创建
	 */
	public  TDirector createDirectorIfNoCreate(String directorName){
		return createDirectorIfNoCreate(directorName,null,null);
	}

	
	
	/**
	 * 根据类型名称得到类型
	 * @param areaName
	 * @return
	 */
	public  TGenre getGenre(String genreName,Integer type){
		String hql = " from TGenre where name='"+genreName+"' and type="+type;
		return (TGenre)commonDao.uniqueFind(hql);
	} 

	public  TGenre getMovieGenre(String genreName,Integer type){
		
		return null;
	}
	/**
	 * 创建电影导演
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieGenre createGenreMovieIfNoCreate(String actorName,int index,Integer movieId,Integer type){
		if(movieId==null||actorName==null){
			return null;
		}
		TGenre genre = createGenreIfNoCreate(actorName,type);
		TMovieGenre movieGenre = null;
		 
		String hql = "from TMovieGenre where genre.id="+genre.getId()+" and movie.id="+movieId;
		movieGenre = (TMovieGenre)commonDao.uniqueFind(hql);
		 
		if(movieGenre==null){
			movieGenre = new TMovieGenre();
			movieGenre.setMovie(new TMovie(movieId));
			movieGenre.setGenre(genre); 
			movieGenre.setInd(index);
			movieGenre = (TMovieGenre)createObject(movieGenre);
		}
		
		return movieGenre;
	}
	/**
	 * 得到电影地区List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public List<TMovieGenre> getMovieGenreList(String genres,Integer movieId,int type){
		
		List movieGenreList = new ArrayList();
		String[] genreArr = genres.split("/");
		for(int i=0;i<genreArr.length;i++){
			String genreName = genreArr[i];
			TMovieGenre areaMovie =createGenreMovieIfNoCreate(genreName, i, movieId,type);
			movieGenreList.add(areaMovie);
		}
		return movieGenreList;
	}
	/*
	 * 得到类型，如果数据库没有，则创建
	 * 
	 */
	public  TGenre createGenreIfNoCreate(String genreName,Integer type){
		if(genreName==null){
			return null;
		}
		genreName = genreName.trim();
		if(!"纪录片".equals(genreName)&&genreName.endsWith("片")){
			genreName = genreName.substring(0, genreName.length()-1);
		}
		TGenre genre = getGenre(genreName,type);
		if(genre==null){
			genre = new TGenre();
			genre.setName(genreName);
			genre.setType(type);
			genre = (TGenre)createObject(genre);
		}
		return genre;
	}
	
	
	@SuppressWarnings({  "unchecked" })
	public List getAreaList(String areas){
		if(areas==null){
			return null;
		}
		List areaList = new ArrayList();
		String[] areaArr = areas.split("/");
		for(int i=0;i<areaArr.length;i++){
			String areaName = areaArr[i];
			TArea area = createAreaIfNoCreate(areaName);
			areaList.add(area);
		}
		 
		return areaList;
	}
	/**
	 * 得到电影地区List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieArea> getMovieAreaList(String areaes,Integer movieId){
		
		List<TMovieArea> movieAreaList = new ArrayList<TMovieArea>();
		String[] areaArr = areaes.split("/");
		for(int i=0;i<areaArr.length;i++){
			String areaName = areaArr[i];
			TMovieArea areaMovie =createMovieAreaIfNoCreate(areaName, i, movieId);
			movieAreaList.add(areaMovie);
		}
		return movieAreaList;
	}
	public List getDirectorList(String directors){
			
		List<TDirector> dirList = new ArrayList<TDirector>();
		String[] dirArr = directors.split("/");
		for(int i=0;i<dirArr.length;i++){
			String dirName = dirArr[i];
			TDirector dir = createDirectorIfNoCreate(dirName);
			dirList.add(dir);
		}	
		 
		return dirList;
	}
	/**
	 * 得到电影导演List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieDirector> getMovieDirectorList(String directors,Integer movieId){
		
		List<TMovieDirector> movieDirectorList = new ArrayList<TMovieDirector>();
		String[] directArr = directors.split("/");
		for(int i=0;i<directArr.length;i++){
			String actorName = directArr[i];
			TMovieDirector actorMovie =createDirectorMovieIfNoCreate(actorName, i, movieId,null,null);
			movieDirectorList.add(actorMovie);
		}
		return movieDirectorList;
	}
	/**
	 * 得到电影演员List
	 * @param actors
	 * @param movieId
	 * @return
	 */
	public List<TMovieActor> getActorMovieList(String actors,Integer movieId){
		
		List<TMovieActor> actorMovieList = new ArrayList<TMovieActor>();
		String[] actorArr = actors.split("/");
		for(int i=0;i<actorArr.length;i++){
			String actorName = actorArr[i];
			TMovieActor actorMovie =createActorMovieIfNoCreate(actorName, i, movieId);
			actorMovieList.add(actorMovie);
		}
		return actorMovieList;
	}

	public Set<TActor> getActorSet(String actors){
		
		Set<TActor> actorSet = new HashSet<TActor>();
		String[] actorArr = actors.split("/");
		for(int i=0;i<actorArr.length;i++){
			String actorName = actorArr[i];
			TActor actor =createActorIfNoCreate(actorName);
			actorSet.add(actor);
		}
		return actorSet;
	}
	public List<TGenre> getGenreList(String genres,Integer type){
		List<TGenre> genList = new ArrayList<TGenre>();
		String[] genArr = genres.split("/");
		for(int i=0;i<genArr.length;i++){
			String actorName = genArr[i];
			TGenre gen = createGenreIfNoCreate(actorName,type);
			genList.add(gen);
		}
		 
		return genList;
	}
	
	
	public TSource getSnatchServer(Integer serverId){
		if(snatchServerMap==null){
			snatchServerMap = new HashMap<Integer,TSource>();
			String hql = " from TSource";
			List snatchServerList = commonDao.findByHQL(hql);
			for(int i=0;i<snatchServerList.size();i++){
				TSource snatchServer = (TSource)snatchServerList.get(i);
				if(snatchServer!=null){
					snatchServerMap.put(snatchServer.getId(), snatchServer);
				}
				
			}
		}
		return snatchServerMap.get(serverId);
	}
	/**
	 * 创建电影导演
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieDirector createDirectorMovieIfNoCreate(String actorName,int index,Integer movieId,String boubanId,String imagePath){
		if(movieId==null||actorName==null){
			return null;
		}
		TDirector director = createDirectorIfNoCreate(actorName,boubanId,imagePath);
		TMovieDirector movieDirector = null;
		 
		String hql = "from TMovieDirector where director.id="+director.getId()+" and movie.id="+movieId;
		movieDirector = (TMovieDirector)commonDao.uniqueFind(hql);
		 
		if(movieDirector==null){
			movieDirector = new TMovieDirector();
			movieDirector.setMovie(new TMovie(movieId));
			movieDirector.setDirector(director); 
			movieDirector.setInd(index);
			movieDirector = (TMovieDirector)createObject(movieDirector);
		}
		
		return movieDirector;
	}
	/*
	 * 得到导演，如果数据库没有，则创建
	 */
	@SuppressWarnings("unchecked")
	public  TDirector createDirectorIfNoCreate(String directorName,String doubanId,String imageUrl){
		TDirector director = getDirector(directorName);
		if(director==null){
			director = new TDirector();
			director.setName(directorName);
			director.setWorkCount(1);
			if(doubanId!=null&&!"".equals(doubanId.trim())){
				director.setDoubanId(doubanId);
			}
			
			director = (TDirector)createObject(director);
		}
		boolean updateFlag = false;
		 
		if(imageUrl!=null&&!"".equals(imageUrl.trim())&&director.getImagePath()==null){
			String	imagePath = DownHtml.downImag(imageUrl, "actor", directorName,directorName);
			director.setImagePath(imagePath);
			updateFlag = true;
		}
		if(updateFlag){
			commonDao.update(director);
		}
		return director;
	}
	
	
	/**
	 * 创建影演员
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieActor createActorMovieIfNoCreate(String actorName,int index,Integer movieId){
		return createActorMovieIfNoCreate(actorName,index,movieId,null,null);
	}
	/**
	 * 创建影演员
	 * @param actorName
	 * @param movieId  电影id
	 * @param index    排序号 
	 * @return
	 */
	public  TMovieActor createActorMovieIfNoCreate(String actorName,int index,Integer movieId,String boubanId,String imagePath){
		if(movieId==null||actorName==null){
			return null;
		}
		TActor actor = createActorIfNoCreate(actorName,boubanId,imagePath);
		TMovieActor actorMovie = null;
		 
		String hql = "from TMovieActor where actor.id="+actor.getId()+" and movie.id="+movieId;
		actorMovie = (TMovieActor)commonDao.uniqueFind(hql);
		 
		if(actorMovie==null){
			actorMovie = new TMovieActor();
			actorMovie.setMovie(new TMovie(movieId));
			actorMovie.setActor(actor); 
			actorMovie.setInd(index);
			actorMovie = (TMovieActor)createObject(actorMovie);
		}
		
		return actorMovie;
	}
		
	/**
	 * 
	 * @param actorName
	 * @param type  //1演员，2编剧，3监制-4 原著
	 * @param index  
	 * @param snatchService
	 * @return
	 */
	public  TTvActor createActorTvIfNoCreate(String actorName,int type,int index,Integer tvId,String doubanId,String imagePath){
		if(tvId==null||actorName==null){
			return null;
		}
		TActor actor = createActorIfNoCreate(actorName,doubanId,imagePath);
	 
		String hql = "from TTvActor where actor.id="+actor.getId()+" and tv.id="+tvId+" and type="+type;
		TTvActor actorTv = (TTvActor)commonDao.uniqueFind(hql);
	 
		if(actorTv==null){
			actorTv = new TTvActor();
			actorTv.setTv(new TTv(tvId));
			actorTv.setActor(actor);
			actorTv.setType(type);
			actorTv.setInd(index);
			actorTv = (TTvActor)createObject(actorTv);
		}
		
		return actorTv;
		
	}
	public SnatchUtilServer getSnatchUtilServerByType(int serverType){
		
		SnatchUtilServer snatchUtilServer= null;
		if(serverType==1){
			snatchUtilServer = new YoukuUtil();
		}else if(serverType==2){
			snatchUtilServer = new SohuUtil();
		}else if(serverType==3){
			snatchUtilServer = new IqyUtil();
		}else if(serverType==4){
			snatchUtilServer = new tudouUtil();
		}else if(serverType==5){
			snatchUtilServer = new LeTvUtil();
		}else if(serverType==9){
			snatchUtilServer = new XunleiUtil();
		}
		else if(serverType==10){
			snatchUtilServer = new TengxunUtil();
		}
		return snatchUtilServer;
	}
	
	/**
	 * 创建一个对象
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object createObject(Object object){
		return commonDao.create(object);		
	}
	public Object uniqueFind(String hql ){
		return commonDao.uniqueFind(hql);
	}
	
	/**
	 * 通过抓取来源ID得到编码
	 * @param serverId
	 * @return
	 */
	public String getServerChartset(Integer serverId){
		if(serverCharset==null){
			serverCharset = new HashMap<Integer,String>();
			String hql = " from TSource ";
			List<TSource> serverList =  commonDao.findByHQL(hql);
			for(int i=0;i<serverList.size();i++){
				TSource server = (TSource)serverList.get(i);
				Integer id = server.getId();
				String chartSet = server.getCharSet();
				serverCharset.put(id, chartSet);
			}
		}
		return serverCharset.get(serverId);
	}
	
	/**
	 * 得到所有抓取网站
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TSource> getAllSnatchServer(){
		
		String hql = " from TSource where status=1";
		return commonDao.findByHQL(hql);
		
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public   void setImagPath(String imagPath1) {
		imagPath = imagPath1;
	}

	public static String getImagPath() {
		return imagPath;
	}

  
}
