<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta http-equiv="content-type" content="text/html;charset=utf-8" /> 
<meta http-equiv="Cache-Control" content="max-age=60" />
<meta name="robots" content="all" />
<meta name="description" content="最全电影-致力于打造中国最全、最新的在线视频分享平台,分享在线视频网站内容,视频搜索,视频共享" />
<meta name="keywords" content="最新电影,最全电影,在线观看,在线视频,免费电影,电视剧" />
<meta name="viewport" content="width=device-width" />
<meta name="application-name" content="最全电影网" />
<meta name="msapplication-starturl" content="http://www.zqdy.cn/" />
<title>最全最新电影-中国最全在线视频共享平台,海量高清视频在线观看</title>
<link rel="stylesheet" type="text/css"
	href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />
	 
	
</head>

<body>
	<s:include value="head.jsp"></s:include>

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
				<!--热播大片-->
				<div class="titleBg">
					<span>热播电影</span>
					<div class="linkList">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">
						更多...</a>
					</div>
				</div>
				<div class="picContent">
					<s:iterator value="hotList" status="tvst">
						<div class="PicBlock">
							<a class="AlinkPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
								<s:if test="imageUrl==null||imageUrl==''">
									<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />" title="<s:property value="shortSummary" />"/>
								</s:else>
								<s:if test="isMember==1">
									<div class="free1"></div>
								</s:if>
								 
							</a> 
							<a class="Alink" target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"><b><s:property value="name" /></b></a>
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
						
						 <s:if test="(#tvst.index+1)%4==0&&#tvst.index!=0">
						 	 <div class="clear"></div>
						 </s:if>
					</s:iterator>
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
			<!--热播大片结尾-->
			<div class="ad">
				<!--广告开始-->
				<img src="images/ad/bigAd01.jpg" />
			</div>
			<!--ad广告结尾-->
			<div class="blockBox">
				<!--热播连续剧-->
				<div class="titleBg">
					<span>热播连续剧</span>
					<div class="linkList">
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_0_0_0.html">
						更多...</a>
					</div>
				</div>
				<div class="picContent">
					<s:iterator value="tvList" status="tvst">
						<div class="PicBlock">
							<a class="AlinkPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
								<s:if test="imageUrl==null||imageUrl==''">
									<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
								</s:if>
								<s:else>
									<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
								</s:else>
								<div class="update">
								 	 <s:if test="length==updateLength">
								 	 	<s:property value="length"/>集全
								 	 </s:if>
								 	 <s:else>
								 	 	更新至<s:property value="updateLength"/>集
								 	 </s:else>
			 					</div>
							</a> 
							<a class="Alink" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
								<s:property value="name" />
							</a>
						<div>
							<span class="vote-star"><i style="width:<s:property value="rateScorePre" />%"></i></span>
								<span class="vote-number"><s:property value="rateScore" />分</span>
						</div>
						<p>
							<s:iterator value="actorList" status="st">		
									<s:if test="#st.index==0">
										<a href="#"><s:property value="actor.name"/></a>
									</s:if>
							</s:iterator>	
						</p>
						</div>
						
						<s:if test="(#tvst.index+1)%4==0&&#tvst.index!=0">
						 	 <div class="clear"></div>
						 </s:if>
					</s:iterator>
					
				</div>
				<br style="clear:both;" />
				<!--IE8下清楚浮动-->
			</div>
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
		</div>
		<!--左边块容器结尾-->


		<div class="RightListBox">
			<!--右边块容器-->
			<div class="blockBox-paihang">
				<!--大片排行榜块-->
				<div class="titleBg-paihang">
					<span>今日推荐</span>
					<div class="linkList-paihang">
						 <a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html">>>更多</a>
						
					</div>
				</div>
				<div class="listContent">
					<ul>
						<s:iterator value="movieList" status="rmv">
							<li>
								<span><s:property value="%{#rmv.index+1}" /></span>
								<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html" target="_blank">
									<s:property value="name" />
								</a>
								
								<s:iterator value="genreList" status="st">		
									<s:if test="#st.index==0">
										<p>
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_0_<s:property value="genre.id"/>_0_0_0.html">
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
								<a  target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
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
		<br style="clear:both;" />
		<!--IE8下清楚浮动-->
	</div>
	<!--contentBox结尾-->

	<s:include value="foot.jsp"></s:include> 

</body>
</html>
