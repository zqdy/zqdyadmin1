<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="movie.name" />—在线播放-最全电影，视频高清在线观看</title>
<meta name="title" content="<s:property value="movie.name" />-最全电影" /> 
<meta name="keywords" content="<s:property value="movie.name" />"></meta> 
<meta name="description" content="<s:property value="movie.name" />" ></meta> 


<link rel="stylesheet" type="text/css" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />
<link rel="stylesheet" type="text/css" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/remommd.css" />
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/jquery.ennui.contentslider.js"></script>
</head>

<body>
	<jsp:include page="head.jsp" ><jsp:param name="p" value="1"/></jsp:include>
	
	<div class="contentBox">
	 <div class="plsyTitleStyle">
	 <h2> <a target="_blank"  href="movieInfo.action?id=<s:property value="id" />"><s:property value="movie.name" /></a> </h2>
	 <div class="playAlinkBox">
	 <a href="<s:property value="%{getText('zqdy.mainUrl')}" />/movie.html" target="_blank">电影</a> >
	 
		<s:iterator value="movie.actorList" status="st">		
			<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_<s:property value="area.id"/>_0_0.html" target="_blank">
			<s:property value="area.name" />
			</a> >
		</s:iterator>
	 
		<s:iterator value="movie.genreList" status="st">		
			<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="genre.id"/>_0_0_0.html" target="_blank">
				<s:property value="genre.name" />
			</a>
		</s:iterator> 	
	</div>
	 </div>
	</div>
	<div class="playBox">
	 <div class="playBoxMid">
		<s:property value="embed" escape="false"/>
	 </div><!--linkbarBox结尾--> 
	</div>
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
	</div><br/>
	<div class="contentBox">
		<div class="LeftMovieBox">
			<!--左边块容器-->
			<div class="blockBox">
				<div class="picContent Chagemargin">
					<!--剧情-->
					<h3 class="h3Style h3StyleChagemarin">
						<span></span>你可能还喜欢：
					</h3>			 
					<div id="one" class="contentslider">
						<div class="cs_wrapper">
							<div class="cs_slider">		
								<s:iterator value="recommMvList" status="rmv">
									<s:if test="#rmv.index==0">
										<div class="cs_article">
											<div class="picContent marginL50">
									</s:if>									 
								 	<s:elseif test="(#rmv.index)%4==0">
									 	<div class="cs_article">
											<div class="picContent marginL50">
									</s:elseif>
									<div class="PicBlock picBlockChange">
										<a class="AlinkPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
											<s:if test="imageUrl==null||imageUrl==''">
												<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
											</s:if>
											<s:else>
												<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
											</s:else>
											<s:if test="isMember==1">
												<div class="free1"></div>
											</s:if>
										</a> 
											<a class="Alink" target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
												<s:property value="name" />									
											</a>
										<div>
											<span class="vote-star"><i style="width:<s:property value="rateScorePre" />%"></i></span>
											<span class="vote-number"><s:property value="rateScore" />分</span> 
										</div>
										<p>
											<s:if test="shortSummary==null">
												主演：
												<s:iterator value="actorList" status="st">		
													<s:if test="#st.index==0||#st.index==1">
														<s:property value="actor.name"/>
													</s:if>
												</s:iterator>	
											</s:if>
											<s:else>
												<s:property value="shortSummary" escape="false"/>								
											</s:else>					
										</p>
									</div>
									<s:if test="#rmv.last==true">
											</div>
										</div>
									</s:if>								 
									 <s:elseif test="(#rmv.index+1)%4==0&#rmv.index!=0">
								 	 		</div>
										</div>
								 	</s:elseif>
								</s:iterator>							
							
							</div>
							
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
						<br style="clear:both;" />
						<!--IE8下清楚浮动-->
					</div>
				</div>
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
				<!--评价容器结尾-->
				</div>
				<br style="clear:both;" />
			</div>
			<!--blockBox片结尾-->
			
		</div>
		<!--左边块容器结尾-->

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
								<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />">
									<s:property value="movie.name" />
								</a>
							</h2>
							<p>
								<b>上映：</b> <s:property value="movie.year" />
							</p>
							 
							<p>
								<b>类型：</b>
								<s:iterator value="movie.genreList" status="st">		
									<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="id"/>_0_0_0.html">
										<s:property value="genre.name" />
									</a>/
								</s:iterator>

							</p>							
							<p>
								<b>导演</b>：
								<s:iterator value="movie.directorList" status="st">		
											<s:property value="director.name" />/
								</s:iterator> 
							</p>
							<p>
								<b>主演：</b>
								<s:iterator value="movie.actorList" status="st">		
									<s:property value="actor.name" />/
								</s:iterator>  
							</p>
							<p>
								<b>剧情：</b><s:property value="movie.summary" />
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
					<span>热播排行榜</span>
					<div class="linkList-paihang">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html" target="_blank"  >>>更多</a>
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
									<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"  target="_blank" >
										<s:if test="name.length()>8">
											 <s:property value="%{name.substring(0, 6)}" />...
										</s:if>
										<s:else>
											<s:property value="name" />
										</s:else>
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

		</div>
		<!--右边块容器结尾-->

		<br class="clearBoth" />
		<!--IE8下清楚浮动-->
	</div>
	<!--contentBox结尾-->


	<s:include value="foot.jsp"></s:include> 

</body>
</html>
