<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-CN"/>
<meta name="title" content="电影频道-最全电影" />
<meta name="keywords" content="最新电影,最全电影,在线观看,在线视频,免费电影,电视剧" />
<meta name="description" content="视频服务平台,提供最新电影视频播放,视频搜索,视频分享" />
<link type="application/rss+xml" rel="alternate" title="最全电影网-推荐电影" href="/index/rss_cool_v" />
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
				<!--女性影院大片-->
				<div class="titleBg">
					<span>女性影院</span>
					<div class="linkList">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/tm_0_6.html">
						更多...</a>
					</div>
				</div>
				<div class="picContent">
				
					<s:iterator value="womenList">
						<div class="PicBlock">
							<a class="AlinkPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
								<s:if test="imageUrl==null||imageUrl==''">
									<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" title="<s:property value="shortSummary" />" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" title="<s:property value="shortSummary" />" alt="<s:property value="name" />"/>
								</s:else>
								<s:if test="isMember==1">
									<div class="free1"></div>
								</s:if>
								 
							</a> 
							<a class="Alink" target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"><s:property value="name" /></a>
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
					</s:iterator>
					 
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--女性影院结尾-->
			<div class="ad">
				<!--广告开始-->
				<img src="images/ad/bigAd01.jpg" />
			</div>
			<!--ad广告结尾-->
			
			<div class="blockBox">
				<!--男性影院大片-->
				<div class="titleBg">
					<span>男性影院</span>
					<div class="linkList">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/tm_0_5.html">
						更多...</a>
					</div>
				</div>
				<div class="picContent">
				
					<s:iterator value="manList">
						<div class="PicBlock">
							<a class="AlinkPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
								<s:if test="imageUrl==null||imageUrl==''">
									<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" title="<s:property value="shortSummary" />" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" title="<s:property value="shortSummary" />" alt="<s:property value="name" />"/>
								</s:else>
								<s:if test="isMember==1">
									<div class="free1"></div>
								</s:if>
								 
							</a> 
							<a class="Alink" target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"><s:property value="name" /></a>
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
					</s:iterator>
					 
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--男性影院结尾--> 
			<div class="ad">
				<!--广告开始-->
				<img src="images/ad/bigAd01.jpg" />
			</div>
			<!--ad广告结尾-->
			
			<div class="blockBox">
				<!--好莱坞院大片-->
				<div class="titleBg">
					<span>好莱坞院大片</span>
					<div class="linkList">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/tm_0_7.html">
						更多...</a>
					</div>
				</div>
				<div class="picContent">
				
					<s:iterator value="haoLaiwuList">
						<div class="PicBlock">
							<a class="AlinkPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
								<s:if test="imageUrl==null||imageUrl==''">
									<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" title="<s:property value="shortSummary" />" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" title="<s:property value="shortSummary" />" alt="<s:property value="name" />"/>
								</s:else>
								<s:if test="isMember==1">
									<div class="free1"></div>
								</s:if>
								 
							</a> 
							<a class="Alink" target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"><s:property value="name" /></a>
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
					</s:iterator>
					 
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--好莱坞院大片结尾-->
			
			<div class="blockBox">
				<!-- 推荐类型-->
				<s:iterator value="recoTypeList" status="st">	
					<div class="titleBg">
						<span>
							<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/tm_0_<s:property value="id"/>.html">
								<s:property value="name"/>
							</a>(<s:property value="workCount"/>部)
						</span>
						<div class="linkList">
							<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/tm_0_<s:property value="id"/>.html">
							详细...</a>
						</div>
					</div>				 
					<br style="clear:both;" />
				</s:iterator>
				   
				<!--IE8下清楚浮动-->
			</div>
			<!--最受欢迎的电影-->
		</div>
		<!--左边块容器结尾-->


		<div class="RightListBox">
			<!--右边块容器-->
			<div class="blockBox-paihang">
				<!--大片排行榜块-->
				<div class="titleBg-paihang">
					<span>最近更新</span>
					<div class="linkList-paihang">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">>>更多</a>
					</div>
				</div>
				<div class="listContent">
					<ul>
						 <s:iterator value="hotList" status="rmv">
							<li>
								<span><s:property value="%{#rmv.index+1}" /></span>
								<a target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
									<s:property value="name" />
								</a>
								
								<s:iterator value="genreList" status="st">		
									<s:if test="#st.index==0">
										<p>
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="genre.id"/>_0_0_0.html">
												<s:property value="genre.name" />
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
