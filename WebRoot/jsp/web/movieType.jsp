<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-CN"/>
<meta name="title" content="<s:property value="name"/>-最全电影" />
<meta name="keywords" content="<s:property value="name"/>,最全电影,在线视频,免费在线电影,电视剧,在线观看" />
<meta name="description" content="<s:property value="name"/>在线观看" />
<link type="application/rss+xml" rel="alternate" title="最全电影网" href="/index/rss_cool_v" />
<link rel="stylesheet" type="text/css"
	href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />
</head>

<body>
	<jsp:include page="head.jsp" ><jsp:param name="p" value="1"/></jsp:include>
 

	<div class="contentBox">
		<div class="jiansuoBox">
			<!--检索-->
			<div class="jiansuoTitle">
				<h2>
					电影<br />检索
				</h2> 
				<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">>>高级检索</a>
			</div>
			<div class="kuaibox">
				<div class="linkContent">
					<h3>按类型</h3>					
					<s:iterator value="genreList" status="st">
						<s:if test="#st.index<6">
							<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="id"/>_0_0_0.html" >	
								<s:property value="name"/>
							</a>|
						</s:if>
					</s:iterator>	<br/>
					<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">
							更多...
					</a>
				</div>
			</div>
			<div class="kuaibox">
				<div class="linkContent">
					<h3>按年代</h3>
					<s:iterator value="yearList" status="st">
						<s:if test="#st.index<6">
							<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_<s:property value="value"/>.html" >	
									<s:property value="name"/>
							</a>|
						</s:if>
					</s:iterator><br/>	
					<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">
							更多...
					</a>
				</div>
			</div>
			<div class="kuaibox">
				<div class="linkContent">
					<h3>按地区</h3>
					<s:iterator value="areaList" status="st">
						<s:if test="#st.index<6">
							<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_<s:property value="id"/>_0_0.html" >	
									<s:property value="name"/>
							</a> |
						</s:if>
					</s:iterator><br/>	
					<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">
							更多...
					</a>
				</div>
			</div>
		</div>
		<!--检索页面结尾-->
		<div class="LeftMovieBox">
			<!--左边块容器-->
			<div class="blockBox">
			 
				<div class="titleBg">
					<span><s:property value="name"/></span>
				</div>
				<div class="picContent">
					
					<s:iterator value="resultList">
						<div class="WelBlock">
							<a class="AlinkWelPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
								<s:if test="imageUrl==null||imageUrl==''">
									<img width="85px" height="120px" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img width="85px" height="120px" src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
								</s:else>
								<s:if test="isMember==1">
									<div class="free1"></div>
								</s:if>
							</a> 
							 
							<div class="WelInfo">
								<a class="AlinkWel" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html" >
									<s:property value="name" />
								</a>
								<div class="floatLeft">
									<span class="vote-star"><i style="width:<s:property value="rateScorePre" />%"></i></span>
									<span class="vote-number"><s:property value="rateScore" />分</span> 
								</div> 
								<p>主演：
									<s:iterator value="actorList" status="st">		
										<s:if test="#st.index==0">
											 <s:property value="actor.name"/> 
										</s:if>
									</s:iterator>	
								</p>
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
					</s:iterator>
					 
				</div>
				<br style="clear:both;" />
				
				<tangs:webpages cpage="cpage" total="total" styleClass="pagination" theme="static"></tangs:webpages>
				<!--IE8下清楚浮动-->
			</div> 
		 </div>
			<!--最受欢迎的电影-->
		</div>
		<!--左边块容器结尾-->


		<div class="RightListBox">
			<!--右边块容器-->
			<div class="blockBox-paihang">
				<!--大片排行榜块-->
				<div class="titleBg-paihang">
					<span>大片排行榜</span>
					<div class="linkList-paihang">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">>>更多</a>
					</div>
				</div>
				<div class="listContent">
					<ul>
						 <s:iterator value="recommMvList" status="rmv">
							<li>
								<span><s:property value="%{#rmv.index+1}" /></span>
								<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
									<s:property value="name" />
								</a>
								
								<s:iterator value="genreSet" status="st">		
									<s:if test="#st.index==0">
										<p>
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="id"/>_0_0_0.html">
												<s:property value="name" />
											</a>
										</p>
									</s:if>
								</s:iterator>	
							</li>
						</s:iterator>
					</ul>
				</div>
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
			 
			<!--连续剧排行榜块结尾-->
		</div>
		<!--右边块容器结尾-->
		<br style="clear:both;" />
		<!--IE8下清楚浮动-->
	</div>
	<!--contentBox结尾-->

	<s:include value="foot.jsp"></s:include> 

</body>
</html>
