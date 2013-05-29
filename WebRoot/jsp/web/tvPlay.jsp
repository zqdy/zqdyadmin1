<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="tvSnatch.tv.name" />-第<s:property value="subtv.volume"  />集-最全电影，视频高清在线观看</title>
<meta name="title" content="<s:property value="tvSnatch.tv.name" />"></meta>
<meta name="keywords" content="<s:property value="tvSnatch.tv.name" />"> </meta>
<meta name="description" content="<s:property value="subtv.summary" />" />


<link rel="stylesheet" type="text/css" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />
<link rel="stylesheet" type="text/css" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/remommd.css" />
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/common.js"></script>
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/jquery.ennui.contentslider.js"></script>
						
<script type="text/javascript">
	function playBtnClick(btnId) {

		$("button[name^=siteBtn]").each(function() {
			$(this).attr("class", "playBtn four");
		});
		$("#playBtn" + btnId).attr("class", "playBtn hover");
		$("div[id^=playDivId]").each(function() {
			$(this).hide();
		});
		$("#playDivId" + btnId).show();
		$("div[id^=subDivId]").each(function() {
			$(this).hide();
		});
		$("#subDivId" + btnId).show();

	}
</script>
</head>

<body>
	<s:include value="head.jsp"></s:include>

	<div class="contentBox">
		<div class="plsyTitleStyle">
			<h2>
			<a  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="tvSnatch.tv.id" />.html"  target="_blank">
				<s:property value="tvSnatch.tv.name" />
			</a> 
			&nbsp;第<s:property value="subtv.volume"  />集</h2>
			<div class="playAlinkBox">
				<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_0_0_0.html" target="_blank">
					电视剧
				</a>&nbsp;>
				<s:if test="tvSnatch.tv.areaList!=null">
					<s:iterator value="tvSnatch.tv.areaList" status="st">		
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_<s:property value="area.id"/>_0_0.html" target="_blank">
							<s:property value="area.name" />
						</a> >  
					</s:iterator>
				</s:if> 
				<s:if test="tvSnatch.tv.genreList!=null">
					<s:iterator value="tvSnatch.tv.genreList" status="st">		
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre.id"/>_0_0_0.html" target="_blank">
							<s:property value="genre.name" />
						</a>
					</s:iterator>
				</s:if>
			</div>
		</div>
	</div>

	<div class="playBox">
		<div class="playBoxMid">
			<s:property value="subtv.embed" escape="false"/>
		</div>
		<!--playBoxMid结尾-->
	</div>
	<!--playBox结尾-->
	<div class="contentBox">
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
	</div><br/><br/>
	<div class="contentBox bgColorStyle bgboder">
		<div class="playNumBox">
			 
			<s:iterator value="tvSnatch.subTvList" status="stSubTv">
				<s:if test="#stSubTv.index==32">
					 <div id="moreSubTv<s:property value="#st.index" />" style="display: none;">
				</s:if>	
				 <a  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="tvSnatch.id" />_<s:property value="id" />.html"  							
					<s:if test="id==subtv.id"> 
					 	class="playNumActive"
					</s:if>
					<s:else>
						class="Disactive"
					</s:else>
				>
					<s:property value="volume" />
				</a>  
				<s:if test="#stSubTv.index>31&&#stSubTv.last==true">
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
	</div>


	<div class="contentBox">
		<div class="LeftMovieBox">
		  <div class="blockBox ">
			<!--左边块容器--> 
				<div class="picContent Chagemargin">
					<!--剧情-->
					<h3 class="h3Style h3StyleChagemarin">
						<span></span>推荐连续剧：
					</h3>
					<div id="one" class="contentslider">
						<div class="cs_wrapper">
							<div class="cs_slider">	
								<s:iterator value="recommTvList" status="st">
									<s:if test="#st.index==0">
										<div class="cs_article">
											<div class="picContent marginL50">
									</s:if>									 
								 	<s:elseif test="(#st.index)%4==0">
									 	<div class="cs_article">
											<div class="picContent marginL50">
									</s:elseif>
									<div class="PicBlock PicBlockChangeWidth">
										<a class="AlinkPic" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html" target="_blank" >
											<s:if test="imageUrl==null||imageUrl==''">
												<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
											</s:if>
											<s:else>
												<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
											</s:else>
										</a> 
										<a class="Alink"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html" target="_blank" >
											<s:property value="name" />
										</a>
										<div>
											<span class="vote-star">
												<i style="width:<s:property value="rateScorePre" />%"></i></span>
												<span class="vote-number"><s:property value="rateScore" />分
											</span> 
										</div> 
									</div>
									
									<s:if test="#st.last==true">
											</div>
										</div>
									</s:if>								 
									 <s:elseif test="(#st.index+1)%4==0&#st.index!=0">
								 	 		</div>
										</div>
								 	</s:elseif>
								</s:iterator>
							
													 
							</div>
							<!-- End cs_slider -->
						</div>
						<!-- End cs_wrapper -->
					</div> 
					<script type="text/javascript">
						$(function() {
							$('#one').ContentSlider({
								width : '660px',
								height : '290px',
								speed : 600,
								/* easing : 'easeInOutExpo' */
								/* easing : 'easeOutQuad' */
								easing : 'easeInOutBack'
							});
						});
					</script>
				  </div>
				<div class="clear"></div>
			</div>
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

		 
				<!--左边块容器结尾-->
		</div>
		<div class="RightListBox">
			<!--右边块容器-->
			<div class="blockBox-paihang">
				<!--剧情介绍-->
				<div class="titleBg-paihang">
					<span>剧情介绍</span>
				</div>
				<div class="listContent">
					<div class="ListBlock noBottomLine">
						<div class="WelInfo playInfo">
							<h2>
								<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="tvSnatch.tv.id" />.html">
									<s:property value="tvSnatch.tv.name" />
								</a>
								<s:if test="tvSnatch.tv.releaseYear!=null">
									(<s:property value="tvSnatch.tv.releaseYear" />)
								</s:if>
							</h2>
							<p>
								<b>类型：</b>
									<s:iterator value="tvSnatch.tv.genreList" status="st">		
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre.id"/>_0_0_0.html" target="_blank">
											<s:property value="genre.name" />
										</a>
									</s:iterator>
							</p>
							<p>
								<b>导演</b>：
								    <s:iterator value="tvSnatch.tv.directorList" status="st">		
										<a href="#"><s:property value="director.name" /></a>/
									</s:iterator> 
							</p>
							<p>
								<b>主演：</b>
								<s:iterator value="tvSnatch.tv.actorList" status="st">		
										<a href="#"><s:property value="actor.name" /></a>/
								</s:iterator> 
							</p>
							<p>
								<b>剧情：</b><s:property value="subtv.summary" />
							</p>

						</div>
						<br class="clearBoth" />
					</div>
				</div>

				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--剧情介绍结尾-->
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
						<a href="#">>>更多</a>
					</div>
				</div>
				<div class="listContent">
					<ul>
						<s:iterator value="hotList" status="rtv">
							 <li>
							 	<span><s:property value="%{#rtv.index+1}" /></span>
							 	<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html" target="_blank" >
							 		<s:property value="name" />
							 	</a>
							 	<s:iterator value="genreList" status="st">	
							 		<s:if test="#st.index==0"> 	
										<p>
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre.id"/>_0_0_0.html" target="_blank">
											<s:property value="genre.name" />
										</a></p>
									</s:if>
								</s:iterator>
							 </li>
						</s:iterator>
					
						 
					</ul>
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--连续剧排行榜块结尾-->

		</div>
		<!--右边块容器结尾-->

		<br class="clearBoth" />
		<!--IE8下清楚浮动-->
	</div>
	<!--contentBox结尾-->

	<s:include value="foot.jsp"></s:include> 

</body>
</html>
