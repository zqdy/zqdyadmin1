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
import com.zqdy.dao.po.TLink;
import com.zqdy.dao.po.TMovie;
import com.zqdy.service.SnatchService;
import com.zqdy.service.SnatchUtilServer;

public class SinaUtil  implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(SinaUtil.class);
	}

	public List<TLink> getLinkByUrl(String url, int soureServer, Integer tvType) {
		 
		List<TLink> linkList = new ArrayList<TLink>(); 
	
		String movieInfoJson = DownHtml.getHtmlByUrl(url,"utf-8");
		 
		return linkList;
	}

	public String extractMovieEmbed(String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		String embed = null;
		Elements eles = doc.select("dl[class=add] input[class=i-t]");
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

	public String extractSubTvEmbed(String html, String regexEmbed) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 提取电影播放地址
	 * @return
	 */
	public  String   extractMoviePlayUrl(String url){
		return url;
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
		//String url = "http://so.letv.com/list/c1_t154_a-1_y-1_f_at_o1_i-1_p.html";
		SinaUtil sina = new SinaUtil();
		//leTv.getLinkByUrl(url, 6, 0);
		
		String url ="http://video.sina.com.cn/interface/movie/category.php?category=movie&page=1&pagesize=20&liststyle=1&topid=2&leftid=movie-index&rnd=0.09697467457169429";
		 sina.getLinkByUrl(url, 1, 1);
		 
		
		
	}

	public DoubanBo extractMovieDetail(String name,String url) {
		// TODO Auto-generated method stub
		return null;
	}
}
