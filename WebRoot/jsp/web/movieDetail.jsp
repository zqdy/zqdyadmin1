<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="<s:property value="movie.name" /> - 高清全集在线观看" />
<meta name="description" content="<s:property value="movie.name" />-在线观看"/>
<title><s:property value="movie.name" />—最全电影网</title>
<link rel="stylesheet" type="text/css"
	href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/common.js"></script>
	
	
</head>

<body>
	<jsp:include page="head.jsp" ><jsp:param name="p" value="1"/></jsp:include>

	<div class="contentBox">

		<div class="LeftMovieBox">
			<!--左边块容器-->
			<div class="blockBox">
				<div class="picContent">
					<!--片子详情-->
					<h2>
						<s:property value="movie.name" />(<s:property value="movie.year" />)
					</h2>
					<div class="WelBlock" style="border-bottom:0px solid #d4d4d4;">
						<a class="AlinkInforPic" href="#">						
							<s:if test="movie.imageUrl==null||movie.imageUrl==''">
								<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="movie.name" />"/>
							</s:if>
							<s:else>
								<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="movie.imageUrl" />" alt="<s:property value="movie.name" />"/>
							</s:else>
						</a>
						<div class="textBox">
							<div class="InfoTextBox">
								
								<p>评分：
									<span class="vote-star"><i style="width:<s:property value="movie.rateScorePre" />%"></i></span>
									<span class="vote-number"><s:property value="movie.rateScore" />分</span> 
								</p>
								<p>
									别名：
									<s:if test="movie.alias==null">
										无
									</s:if>
									<s:else>
										<s:property value="movie.alias" />
									</s:else>
									
								</p>
								
								<p>									
									<p>地区：
										<s:iterator value="movie.areaList" status="st">		
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_<s:property value="area.id"/>_0_0.html">
											<s:property value="area.name" />
											</a>/
										</s:iterator>
									</p>
								</p>
								<p>
									<p>类型：
										<s:iterator value="movie.genreList" status="st">		
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="genre.id"/>_0_0_0.html">
												<s:property value="genre.name" />
											</a>/
										</s:iterator>
									</p>
								</p>
								<p>
									<p>导演：
										<s:iterator value="movie.directorList" status="st">		
											<a href="#"><s:property value="director.name" /></a>/
										</s:iterator> 
									</p>
								</p>
								<p>
									<p>主演：
										<s:iterator value="movie.actorList" status="st">		
											<a href="#"><s:property value="actor.name" /></a>/
										</s:iterator>   
									</p>
								</p>
								 
								 <br/> 
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
						<s:iterator value="movie.embedList" status="st">		
							<button type="button" class="playBtn four" onclick="javascript:window.open('<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="movie.id" />_<s:property value="source.id" />.html')">
								<s:if test="isMember==1"><div class="free0"></div></s:if>
								<s:property value="source.name" />播放
							</button>
						</s:iterator>  
						</div>
					<!--播放按钮居中定位结尾-->
				</div>
				<!--播放按钮容器结尾-->

				<div class="picContent">
					<!--剧情和评价容器-->
					<h3 class="h3Style">
						<span></span>剧情介绍：
					</h3>
					<div class="WelBlock WelBlockborder">
						<p class="fontStyle">
							<s:property value="movie.summary" />
						</p>
					</div>
					<h3 class="h3Style">
						<span></span>评论：
					</h3>
					 <div id="uyan_frame"></div>
					<script type="text/javascript" id="UYScript" src="http://v1.uyan.cc/js/iframe.js?UYUserId=1681560" async=""></script>
				</div>
				<!--剧情和评价容器结尾-->

				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
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
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">>>更多</a>
					</div>
				</div>
				 <s:iterator value="recommMvList" status="rmv">
				 	<s:if test="id!=movie.id">
						<div class="listContent">
							<div class="ListBlock">
								<a class="AlinkListPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
									<s:if test="imageUrl==null||imageUrl==''">
										<img width="82" height="109" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg"  alt="<s:property value="name" />"/>
									</s:if>
									<s:else>
										<img width="82" height="109" src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
									</s:else>
								</a>
								<div class="WelInfo ListInfo">
									<h3>
										<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"> <s:property value="name" /></a>
									</h3>
									<p>
										评分：<span><s:property value="rateScore" />分</span>
									</p>
									<p>类型：
										<s:iterator value="genreSet" status="st">		
												<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="id"/>_0_0_0.html">
													<s:property value="name" />
												</a>/
										</s:iterator>
									</p>
									<p>主演：
										<s:iterator value="actorList" status="st">		
											<s:if test="#st.index==0">
												 <s:property value="actor.name"/> 
											</s:if>
										</s:iterator>	
									</p>
		
								</div>
								<br class="clearBoth" />
							</div>
						</div>
					</s:if>
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
						 <s:iterator value="recommTvList" status="rtv">
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
