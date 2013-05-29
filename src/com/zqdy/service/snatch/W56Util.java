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

public class W56Util  implements SnatchUtilServer {
	private static Logger logger;

	static {
		logger = Logger.getLogger(W56Util.class);
	}

	public List<TLink> getLinkByUrl(String url, int soureServer, Integer tvType) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);   
		if(doc==null){
			return null;
		}
		Elements elemnts = doc.select("dl[id^=i_] dt a:not(.img)");
		List<TLink> linkList = new ArrayList<TLink>(); 
		if(elemnts!=null){
			for(int i=0;i<elemnts.size();i++){
				Element ael = elemnts.get(i);
				 
				TLink link = new TLink();
				String name = ael.html();
				String movieUrl = ael.attr("href"); 
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
		if(embed==null){
			return null;
		}
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
		
		return getDocMovieEmbed(doc);
	}
	/**
	 * 提取Document电影共享地址
	 * @return
	 */
	private  String   getDocMovieEmbed(Document doc){
		String embed = null;
		Elements eles = doc.select("div[id=albumVideos]>dl>dt>a[class=img]");
		if(eles!=null&&eles.size()>0){
			Element ael = eles.get(0);
			String purl = ael.attr("href");
			if(purl!=null){
				int lastInd = purl.lastIndexOf("/")+1;
				int endInd = purl.indexOf(".html");
				String tvId = purl.substring(lastInd, endInd);
				embed="<embed src='http://player.56.com/"+tvId+".swf'  type='application/x-shockwave-flash' width='1005'  height='513' allowFullScreen='true' allowNetworking='all' allowScriptAccess='always'></embed>";
			}
			
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
		//String url = "http://so.letv.com/list/c1_t154_a-1_y-1_f_at_o1_i-1_p.html";
		W56Util w56 = new W56Util();
		//leTv.getLinkByUrl(url, 6, 0);
		
		// String url ="http://video.56.com/tv-v-movie_sort-n_tid-_zid-_yid-_page-1.html";
		 //w56.getLinkByUrl(url, 1, 1);
		
		String url = "http://video.56.com/opera/6683.html";
		String embed = w56.extractMovieEmbed(url);
		 
		
		
	}

	
}
