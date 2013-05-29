<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="<s:property value="tv.name" /> - 高清在线观看" />
<meta name="description" content="<s:property value="tv.name" />,<s:property value="tv.name" />在线观看,电视剧<s:property value="tv.name" />"/>
<title><s:property value="tv.name" />-最全电影</title>

<link rel="stylesheet" type="text/css"
	href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />
<script type="text/javascript"
	src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/common.js"></script>
<script type="text/javascript"
	src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/jquery-1.7.2.js"></script>
	
	<script type="text/javascript">
		function playBtnClick(btnId){
			 
			$("button[name^=siteBtn]").each(function(){
			   	$(this).attr("class","playBtn four");
			});
			$("#playBtn"+btnId).attr("class","playBtn four playBtnActive");
			$("div[id^=playDivId]").each(function(){
			   	$(this).hide();
			});
			$("#playDivId"+btnId).show();
			$("div[id^=subDivId]").each(function(){
			   	$(this).hide();
			});
			$("#subDivId"+btnId).show();
			
		}
		
		function getSubTvHtml(snachServer,cpage){
			var url = '<s:property value="%{getText('zqdy.mainUrl')}"/>/subPageInfo.action';
			$.get(url,{serverId: snachServer,cpage: cpage},function(data,state){
		        $("#subInforDivId").html(data);
		    });
		}
	</script>
</head>

<body>
	
	<jsp:include page="head.jsp" ><jsp:param name="p" value="2"/></jsp:include>

	<div class="contentBox">
		<div class="LeftMovieBox">
			<!--左边块容器-->
			<div class="blockBox marginB">
				<div class="picContent">
					<!--片子详情-->
					<h2><s:property value="tv.name" />(<s:property value="tv.year" />)</h2>
					<div class="WelBlock" style="border-bottom:0px solid #d4d4d4;">
						<a class="AlinkInforPic" href="#">
							<s:if test="tv.imageUrl==null||tv.imageUrl==''">
								<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="tv.name" />"/>
							</s:if>
							<s:else>
								<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="tv.imageUrl" />" alt="<s:property value="tv.name" />"/>
							</s:else>
						</a>
						<div class="textBox">
							<div class="InfoTextBox">
								<p>
									评分：<span class="vote-star"><i style="width:<s:property value="tv.rateScorePre" />%"></i></span>
									<span class="vote-number"><s:property value="tv.rateScore" />分</span> 
								</p>
								 
								<p>地区：
									<s:if test="tv.areaSet==null">
											暂无
									</s:if>
									<s:else>
										<s:iterator value="tv.areaList" status="st">		
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_<s:property value="id"/>_0_0.html">
												<s:property value="area.name" />
											</a>/
										</s:iterator>
									</s:else>	
								</p>
								<p>类型： 
									<s:iterator value="tv.genreList" status="st">		
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="id"/>_0_0_0.html">
											<s:property value="genre.name" />
										</a>/
									</s:iterator>
								</p>
								<p>导演： 
									<s:iterator value="tv.directorList" status="st">		
										<a href="#"><s:property value="director.name" /></a>/
									</s:iterator> 
								</p>
								<p>主演：
									<s:iterator value="tv.actorList" status="st">		
										<a href="#"><s:property value="actor.name" /></a>/
									</s:iterator> 
								</p>
								<p> 
									<s:if test="tv.updateLength<tv.length">
										更新至<s:property value="tv.updateLength" />/
									</s:if>
									共<s:property value="tv.length" />集
								</p>
								 <p>
								 	<!-- Baidu Button BEGIN -->
									<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
										<span class="bds_more">分享到：</span>
										<a class="bds_qzone"></a>
										<a class="bds_tsina"></a>
										<a class="bds_tqq"></a>
										<a class="bds_renren"></a>
										<a class="bds_t163"></a>
										<a class="shareCount"></a>
									</div>
									<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" ></script>
									<script type="text/javascript" id="bdshell_js"></script>
									<script type="text/javascript">
									document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
									</script>
									<!-- Baidu Button END -->
								 </p> 
							</div>
						</div>

						<br class="clearBoth" />
					</div>
				</div>
				<!--片子详情结尾-->
				<div class="playBtnBox">
					<!--播放按钮容器-->
					<div class="playCent">
						<!--播放按钮居中定位-->
						<s:iterator value="tv.tvSourceList" status="st">		
							<button type="button" name="siteBtn<s:property value="source.id" />"  onclick="playBtnClick(<s:property value="source.id" />)" id="playBtn<s:property value="source.id" />"
								<s:if test="#st.index==0"> 
									class="playBtn four playBtnActive"
								</s:if>
								<s:else>
									class="playBtn four"
								</s:else>
							><s:property value="source.name" />播放</button>
						</s:iterator> 
						 
					</div>
					<!--播放按钮居中定位结尾-->
				</div>
				<!--播放按钮容器结尾-->
			</div>
			<!--blockBox片结尾-->
			<div class="blockBox bgColorStyle">
				
				<s:iterator value="tv.tvSourceList" status="st" id="snatchTvObj">	
					
					<div class="playNumBox" id="playDivId<s:property value="source.id" />"
							<s:if test="#st.index!=0"> 
								style="display: none" 
							</s:if>
						>
						<s:iterator value="subTvList" status="stSubTv" id="subObj">
							<s:if test="#stSubTv.index==30">
								 <div id="moreSubTv<s:property value="#st.index" />" style="display: none;">
							</s:if>	
							
							<a  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="tvSnatch.id" />_<s:property value="id" />.html"  target="_blank" 								
								<s:if test="#stSubTv.index==0"> 
								 	class="playNumActive"
								</s:if>
								<s:else>
									class="Disactive"
								</s:else>
							>
								<s:property value="volume" />
							</a> 
							
							<s:if test="#stSubTv.index>29&&#stSubTv.last==true">
							  	</div>
							</s:if> 
						</s:iterator>
						<a id="moreSubTvA<s:property value="#st.index" />"  class="More">......</a><span> 
						</span>
						<a id="moreSubTvBtn<s:property value="#st.index" />" href="javascript:moreSubTv('<s:property value="#st.index" />')" class="More">
							全部<span><img src="images/moreDown.jpg" /></span>
						</a>
						<a id="lessSubTvBtn<s:property value="#st.index" />" href="javascript:lessSubTv('<s:property value="#st.index" />')" class="More" style="display: none" >
							收起<span><img src="images/moreDown.jpg" /></span>
						</a>
					</div>
				</s:iterator> 
			</div>
			<s:iterator value="tv.tvSourceList" status="st">
			  <div id="subDivId<s:property value="source.id" />"
			  		<s:if test="#st.index!=0"> 
						style="display: none" 
					</s:if>
			  	>		
				<div class="blockBox marginB" >
					<div class="picContent ">
						<!--评价容器-->
						<h3 class="h3Style h3Margin">
							<span></span>分集剧情：
						</h3>
						<div class="tabTitle">
							 <s:iterator value="subTvNavigation">
							 	<a href="#" onclick="getSubTvHtml(<s:property value="id" />,<s:property value="value"/>)"><s:property value="name"/></a>
							 </s:iterator>
							 
						</div>
					</div>
				</div>
				<div class="blockBox bgColorStyle NonebgColor">
					<div class="picContent overflowStyle" id="subInforDivId">
						<s:iterator value="subTvList" status="st">		
							<s:if test="#st.index<25">
								<div class="WelBlock Inforborder">
									<a class="AlinkCommentPic sizeStyle"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="tvSnatch.id" />_<s:property value="id" />.html"  target="_blank">
										<img width="106" height="65" src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
										
										
									</a>
									<div class="WelInfo InforSizeStyle">
										<h3>
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="tvSnatch.id" />_<s:property value="id" />.html"  target="_blank">
												<s:property value="name" />
											</a>
										</h3>
										<p>
											 <s:if test="%{summary.length()>100}">
											          <s:property value="%{summary.substring(0,100)}" />...
											  </s:if>
											  <s:else>
											          <s:property value="%{summary}" />
											   </s:else>
										</p>
			
									</div>
									<br class="clearBoth" />
								</div>
							</s:if>
						</s:iterator>
					</div>
					<!--评价容器结尾-->
				</div>
			  </div>
			</s:iterator>
			<!--blockBox片结尾-->
			<div class="blockBox">
				<div class="picContent">
					<!--评价容器-->
					<h3 class="h3Style">
						<span></span>评论：
					</h3>
					  <div id="uyan_frame"></div>
					<script type="text/javascript" id="UYScript" src="http://v1.uyan.cc/js/iframe.js?UYUserId=1681560" async=""></script>

				</div>
				<!--评价容器结尾-->
			</div>
			<!--blockBox片结尾-->
		</div>
		<!--左边块容器结尾-->
		<div class="RightListBox">
			<!--右边块容器-->
			<div class="blockBox-paihang">
				<!--大片排行榜块-->
				<div class="titleBg-paihang">
					<span>相关推荐</span>
					<div class="linkList-paihang">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_0_0_0.html">>>更多</a>
					</div>
				</div>
				
				 <s:iterator value="recommTvList" status="rtv">
				 	<div class="listContent">
						<div class="ListBlock">
							<a class="AlinkListPic"  target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
								
								<s:if test="imageUrl==null||imageUrl==''">
									<img width="82" height="109"   src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img width="82" height="109"   src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
								</s:else>
							</a>
							<div class="WelInfo ListInfo">
								<h3>
									<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html"> <s:property value="name" /></a>
								</h3>
								<p>
									评分：<span><s:property value="rateScore" />分</span>
								</p> 
								<p>类型：
									<s:iterator value="genreList" status="st">		
											<a href="#"><s:property value="genre.name" /></a>/
									</s:iterator>
								</p>
								<p>主演：
									<s:iterator value="actorList" status="st">		
										<s:if test="#st.index==0">
											<a href="#"><s:property value="actor.name"/></a>
										</s:if>
									</s:iterator>	</p>
	
							</div>
							<br class="clearBoth" />
						</div>
					</div>
				 </s:iterator>
				 
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--大片排行榜块-->
			<div class="ad">
				<!--广告开始-->
				<img src="images/ad/smallAd01.jpg" />
			</div>
			<!--ad广告结尾-->
			<div class="blockBox-paihang">
				<!--连续剧排行榜-->
				<div class="titleBg-paihang">
					<span>连续剧排行榜</span>
					<div class="linkList-paihang">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_0_0_0.html">>>更多</a>
					</div>
				</div>
				<div class="listContent">
					<ul>
						 <s:iterator value="hotList" status="rtv">
							 <s:if test="#rtv.odd == true">
							   <li>
							 </s:if>
							 
							 <div>
								<span><s:property value="%{#rtv.index+1}" /></span>
								<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
									<s:property value="name" />
								</a>
							 </div>	 
							 
							 <s:if test="#rtv.even == true">
							   </li>
							 </s:if>
						</s:iterator>
					</ul>
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--连续剧排行榜块结尾-->
			<div class="ad">
				<!--广告开始-->
				<img src="images/ad/smallAd02.jpg" />
			</div>
			<!--ad广告结尾-->
		</div>
		<!--右边块容器结尾-->
		<br class="clearBoth" />
		<!--IE8下清楚浮动-->
	</div>
	<!--contentBox结尾-->


	<s:include value="foot.jsp"></s:include> 

</body>
</html>
