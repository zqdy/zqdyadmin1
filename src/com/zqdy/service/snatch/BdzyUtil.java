package com.zqdy.service.snatch;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zqdy.core.utils.JSoupUtil;

public class BdzyUtil {
	static String mainUrl = "http://www.bdzy.cc";
	static String indexUrl = "/list/?0-1.html";

	public List<DoubanBo> snatchInfo(String date,String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);
		if (doc == null) {
			return null;
		}
		List<DoubanBo> boList = new ArrayList<DoubanBo>();
		Elements trElemnts = doc.select("table tr[class=row]");
		if (trElemnts != null) {
			for (int i = 0; i < trElemnts.size(); i++) {
				String name = null;
				String dateStr = null;
				String detailUrl = null;
				Element trel = trElemnts.get(i);
				if (trel != null) {
					Element tdel = trel.child(0); // 第0列，名称列
					if (tdel != null && tdel.child(0) != null) {
						Element ael = tdel.child(0);
						String nameText = ael.text();
						if (nameText != null) {
							String[] nArr = nameText.split(" ");
							name = nArr[0];
						}
						detailUrl = ael.attr("href");
					}
				}
				if (trel.childNodeSize() >= 4) {
					Element dateTd = trel.child(3);
					if (dateTd != null) {
						dateStr = dateTd.text();
					}
				}
				System.out.println(name);
				System.out.println(detailUrl);
				System.out.println(dateStr);
				DoubanBo doubanBo = snatchDetail(mainUrl + detailUrl);
				if(doubanBo!=null){
					boList.add(doubanBo);
				}
				
			}
		}
		return boList;
	}

	/**
	 * 抓取详情
	 * 
	 * @param url
	 * @return
	 */
	public DoubanBo snatchDetail(String url) {
		Document doc = JSoupUtil.getJsoupDocumentByUrl(url);
		if (doc == null) {
			return null;
		}
		Elements trEls = doc.select("table[style=text-align:left]>tbody>tr");

		String imageUrl = null;
		String name = null;
		String actors = null;
		String shortSumm = null;
		String genre = null;
		String area = null;
		String status = null;
		String language = null;
		String year = null;
		String summary = null;
		if (trEls != null && trEls.size() >= 2) {
			Element tr0 = trEls.get(0);
			Element tr1 = trEls.get(1);

			if (tr0 != null && tr0.childNodeSize() >= 2) {
				Element imageTd = tr0.child(0);
				Element infoTd = tr0.child(1);
				Elements imageEls = imageTd.select("img"); // 取图片地址
				if (imageEls != null && imageEls.size() > 0) {
					Element imageEl = imageEls.get(0);
					if (imageEl != null) {
						imageUrl = imageEl.attr("src");
					}

				}
				// 取基本信息
				if (infoTd != null) {
					Elements infoTrEls = infoTd.select("table tr");
					if (infoTrEls != null) {
						Element nameTrEl = infoTrEls.get(0); // 影片名称
						name = getInfor(nameTrEl);
						Element actorTrEl = infoTrEls.get(1); // 演员名称
						actors = getInfor(actorTrEl);
						Element shortTrEl = infoTrEls.get(2); // 备注
						shortSumm = getInfor(shortTrEl);
						Element genretTrEl = infoTrEls.get(3); // 类型
						genre = getInfor(genretTrEl);
						Element areaTrEl = infoTrEls.get(4); // 区域
						area = getInfor(areaTrEl);
						Element statusTrEl = infoTrEls.get(6); // 状态
						status = getInfor(statusTrEl);
						Element languageTrEl = infoTrEls.get(7); // 语言
						language = getInfor(languageTrEl);
						Element yearTrEl = infoTrEls.get(8); // 年代
						year = getInfor(yearTrEl);
						Element summaryTrEl = infoTrEls.get(9); // 年代
						summary = getInfor(summaryTrEl);
					}
				}

			}
			if (tr1 != null) {
				Elements liEls = tr1.select("li input");
				for (int i = 0; i < liEls.size(); i++) {
					Element el = liEls.get(i);
					if (el != null) {
						System.out.println(el.attr("value"));
					}
				}

			}
		}
		DoubanBo douban = null;
		if(name!=null){
			douban = new DoubanBo();
			douban.setName(name);
			douban.setImageUrl(imageUrl);
			douban.setShortSummary(shortSumm);
			douban.setSummary(summary);
			douban.setReleaseYear(year);
			
			if(actors!=null){                     //添加演员
				List<DoubanActor> actorList = new ArrayList<DoubanActor>();
				String[] actArr = null;  // actors.split(",");
				if(actors.contains("内详")){
					
				}else if(actors.contains(" ")){
					actArr = actors.split(" ");
				}else if(actors.contains(",")){
					actArr =  actors.split(",");
				}
				if(actArr!=null){
					for(int i=0;i<actArr.length;i++){
						String aname = actArr[i];
						if(aname!=null&&!"".equals(aname.trim())){
							DoubanActor da = new DoubanActor();
							da.setName(aname);
							actorList.add(da);
						}
						
					}
					douban.setActorList(actorList);
				}
				
			}
			if(genre!=null&&!"".equals(genre.trim())){
				String[] genArr = genre.split(" ");
				List<String> genreList = new ArrayList<String>();
				for(int i=0;i<genArr.length;i++){
					String genname = genArr[i];
					if(genname!=null&&!"".equals(genname)){
						genreList.add(genname.trim());
					}
					
				}
				douban.setGenreList(genreList);
			}
			if(area!=null&&!"".equals(area.trim())){
				List<String> areaList = new ArrayList<String>();
				String[] areaArr = area.split(" ");
				for(int i=0;i<areaArr.length;i++){
					String areaName = areaArr[i];
					areaList.add(areaName);
				}
				douban.setAreaList(areaList);
			}
			
		}
		
		System.out.println("name=" + name);
		System.out.println("imageUrl=" + imageUrl);
		System.out.println("actors=" + actors);
		System.out.println("shortSumm=" + shortSumm);
		System.out.println("genre=" + genre);
		System.out.println("area=" + area);
		System.out.println("status=" + status);
		System.out.println("language=" + language);
		System.out.println("year=" + year);
		System.out.println("summary=" + summary);

		return douban;
	}

	private String getInfor(Element trEl) {
		String value = null;
		if (trEl != null) {
			Elements valueFontEls = trEl.select("td font");
			if (valueFontEls != null && valueFontEls.size() > 0) {
				value = valueFontEls.get(0).text();
			}
		}
		return value;
	}

	public static void main(String[] args) {

		BdzyUtil bdzy = new BdzyUtil();
		String url = "http://www.bdzy.cc";
		List doubanList = bdzy.snatchInfo("", mainUrl + indexUrl);
		System.out.println(doubanList.size());
		
	}
}
