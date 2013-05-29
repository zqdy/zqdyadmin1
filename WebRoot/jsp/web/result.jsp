<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title><s:property value="searchName" />_在线观看 –最全电影视频搜索</title>
  <meta name="title" content="<s:property value="searchName" />"> </meta>
  <meta name="keywords" content="<s:property value="searchName" />"> </meta>
  <meta name="description" content="<s:property value="searchName" />搜索 - 最全电影网为你提供最全的<s:property value="searchName" />视频搜索"></meta> 

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/commonStyle.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>

<script>
	function moveSearch(type) {
		var divId = "moreDivId" + type;
		var lessDivId = "lessDivId" + type;
		var moreCondId = "moreConditionDivId" + type;

		$("#" + divId).hide();
		$("#" + lessDivId).show();
		$("#" + moreCondId).show();
	}
	function lessSearch(type) {
		var divId = "moreDivId" + type;
		var lessDivId = "lessDivId" + type;
		var moreCondId = "moreConditionDivId" + type;
		$("#" + divId).show();
		$("#" + lessDivId).hide();
		$("#" + moreCondId).hide();
	}
</script>
</head>

<body>
	<jsp:include page="head.jsp"></jsp:include>


	<div class="contentBox">
		<div class="blockBoxElse">
			<s:iterator value="movieList" status="mvst">
				<div class="picContent">
					<h2>
						<a class="Alink" target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html"><s:property value="name" /></a>
					</h2>
					 
					<a class="AlinkInforPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html">
					 	<s:if test="imageUrl==null||imageUrl==''">
							<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
						</s:if>
						<s:else>
							<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
						</s:else>
					 </a>
					<div class="textBoxWidth">
						<div class="InfoTextBox">
							<p>
								主演：<s:iterator value="actorList" status="st">	
										<a href="#"> <s:property value="actor.name"/></a>/
									</s:iterator>
							</p>
							<p>地区：
									<s:iterator value="areaSet" status="st">		
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_<s:property value="id"/>_0_0.html">
										<s:property value="name" />
										</a>/
									</s:iterator>
							</p>
							<p>
								类型：
									<s:iterator value="genreSet" status="st">		
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_<s:property value="id"/>_0_0_0.html">
											<s:property value="name" />
										</a>/
									</s:iterator>
							</p>
							<p>
								剧情：<span>
										<s:if test="summary.length()>100">  
										    <s:property value="summary.substring(0,100)"/>... 
										</s:if> 									 
										<s:else>
											<s:property value="summary" />
										</s:else>
								</span>
							</p>
							<s:iterator value="embedList" status="st">		
								<button type="button" class="playBtn four" onclick="goTo('<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="movie.id" />_<s:property value="source.id" />.html')">
									<s:if test="isMember==1"><div class="free0"></div></s:if>
									<s:property value="source.name" />播放
								</button>
							</s:iterator>  
							 
						</div>
					</div>
					<div class="bottomLine"></div>
				</div>
			</s:iterator>			
			<!--查出来的电影结尾-->
			
			
			<s:iterator value="tvList" status="rvst">
				<div class="picContent">
					<!--查出来的电视-->
					<h2>
						<a  target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
							<s:property value="name" />
						</a>
					</h2>
					 
					<a class="AlinkInforPic" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id" />.html">
					 	<s:if test="imageUrl==null||imageUrl==''">
							<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
						</s:if>
						<s:else>
							<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" alt="<s:property value="name" />"/>
						</s:else>
					 </a>
					<div class="textBoxWidth">
						<div class="InfoTextBox">
							<p>
								主演：
									<s:iterator value="actorList" status="st">		
										<a href="#"> <s:property value="actor.name"/></a>/ 
									</s:iterator>	
							</p>
							<p>地区：
									<s:if test="areaSet==null">
											暂无
									</s:if>
									<s:else>
										<s:iterator value="areaSet" status="st">		
											<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_<s:property value="id"/>_0_0.html">
												<s:property value="name" />
											</a>/
										</s:iterator>
									</s:else>	
							</p>
							<p>
								类型：
									<s:iterator value="genreSet" status="st">		
										<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="id"/>_0_0_0.html">
											<s:property value="name" />
										</a>/
									</s:iterator>
							</p>
							<p>
								<span>剧情：
										<s:if test="summary.length()>100">  
										    <s:property value="summary.substring(0,100)"/>... 
										</s:if> 									 
										<s:else>
											<s:property value="summary" />
										</s:else>
										
								</span>
							</p>
						 
								<!--播放按钮居中定位-->
								<s:iterator value="tvServerList" status="st">		
									<button type="button" name="siteBtn<s:property value="server.id" />"  onclick="playBtnClick(<s:property value="server.id" />)" id="playBtn<s:property value="server.id" />"
										<s:if test="#st.index==0"> 
											class="playBtn four playBtnActive"
										</s:if>
										<s:else>
											class="playBtn four"
										</s:else>
									><s:property value="server.name" />播放</button>
								</s:iterator> 
								 
							 
							<div class="playNumBox marginL">
								<s:iterator value="tvServerList" status="st" id="snatchTvObj">	
									<div class="playNumBox" id="playDivId<s:property value="server.id" />"
											<s:if test="#st.index!=0"> 
												style="display: none" 
											</s:if>
										>
										<s:iterator value="subTvList" status="stSubTv" id="subObj">
											<s:if test="#stSubTv.index==24">
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
											<s:if test="#stSubTv.index>23&&#stSubTv.last==true">
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
						</div>
					</div>
					<div class="bottomLine"></div>
				</div>
			</s:iterator>
			 
			</div>
			<!--查出来的电影结尾-->
			<div class="marginT20">
				<!--查出来的电视-->
				<div class="floatLeft term">
					 <h4>来源网站:</h4>				
					<s:iterator value="sourceList" >
						<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/search.html?source=<s:property value="id"/>&keyword=<s:property value="keyword"/>"
							<s:if test="source==id">
							 class="activeAbox"
							</s:if>
						>
							<s:property value="name"/>
						</a>
					</s:iterator>
				</div>
				<div class="floatLeft">
					<s:iterator value="viewList" status="vst">
						<div class="picBlockWidth">
							<s:if test="id.type==1">
								<a class="AlinkPicHorizontal" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id.id" />.html">
					 				<s:if test="id.imageUrl==null||id.imageUrl==''">
										<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
									</s:if>
									<s:else>
										<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="id.imageUrl" />" alt="<s:property value="name" />"/>
									</s:else>
							 	</a> 
							 	<a class="Alink" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id.id" />.html">
							 		<s:property value="id.name" />
							 	</a>
							</s:if>
							<s:elseif test="id.type==2">
								<a class="AlinkPicHorizontal" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id.id" />.html">
					 				 <s:if test="id.imageUrl==null||id.imageUrl==''">
										<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="name" />"/>
									</s:if>
									<s:else>
										<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="id.imageUrl" />" alt="<s:property value="name" />"/>
									</s:else>
							 	</a> 
							 	<a class="Alink" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id.id" />.html">
							 		<s:property value="id.name" />
							 	</a>
							</s:elseif>
							<s:elseif test="id.type==3">
								<a class="AlinkPicHorizontal" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id.tvServerId" />_<s:property value="id.id" />.html">
					 				 <s:if test="id.imageUrl==null||id.imageUrl==''">
										<img src="<s:property value="%{getText('zqdy.mainUrl')}"/>/images/noPic.jpg" alt="<s:property value="id.name" />"/>
									</s:if>
									<s:else>
										<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="id.imageUrl" />" alt="<s:property value="id.name" />"/>
									</s:else>
							 	</a> 
							 	<a class="Alink" target="_blank"  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_<s:property value="id.tvServerId" />_<s:property value="id.id" />.html">
							 		<s:property value="id.name" />
							 	</a>
							</s:elseif>
						</div>
						  <s:if test="(#vst.index+1)%4==0&&#vst.index!=0">
						 	 <div class="clear"></div>
						 </s:if>
					</s:iterator>
					<tangs:webpages cpage="cpage" total="total" styleClass="pagination" theme="static"></tangs:webpages>   
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<!--blockBoxElse片结尾-->



	</div>
	<!--contentBox结尾-->


	<s:include value="foot.jsp"></s:include> 

</body>
</html>
