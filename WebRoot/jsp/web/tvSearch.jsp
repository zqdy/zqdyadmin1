<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-CN"/>
<meta name="title" content="最全电影─电影剧检索" />
<meta name="keywords" content="热播电视剧,在线视频,电影,电视剧,在线观看" />
<meta name="description" content="最全电影-致力于打造中国最全的在线视频分享平台,分享在线视频网站内容,视频搜索,视频共享" /> 	
<title>电视剧搜索-最全电影</title>
<link rel="stylesheet" type="text/css"
	href="<s:property value="%{getText('zqdy.mainUrl')}"/>/css/commonStyle.css" />	
<script type="text/javascript" src="<s:property value="%{getText('zqdy.mainUrl')}"/>/js/jquery-1.7.2.js"></script>
 
<script>

function moveSearch(type){
	var divId = "moreDivId"+type;
	var lessDivId = "lessDivId"+type;
	var moreCondId = "moreConditionDivId"+type;
	
	$("#"+divId).hide();
	$("#"+lessDivId).show();
	$("#"+moreCondId).show();
}
function lessSearch(type){
	var divId = "moreDivId"+type;
	var lessDivId = "lessDivId"+type;
	var moreCondId = "moreConditionDivId"+type;
	$("#"+divId).show();
	$("#"+lessDivId).hide();
	$("#"+moreCondId).hide();
}

</script>
</head>

<body>
	<jsp:include page="head.jsp" ><jsp:param name="p" value="2"/></jsp:include>
<div class="contentBox">
<div class="blockSearchBox elseStyle"><!--条件--> 
<div class="titleBg"><p>全部电视剧</p><div class="linkList paddingL"><a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_1_0_0_0_0.html"> 全部电影>></a> </div></div>
 <div class="sort">
	 <span><b>地区:</b></span>
	<div class="aBox">
		<a
			href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre"/>_0_<s:property value="source"/>_<s:property value="year"/>.html"
			<s:if test="area==0">
			 class="SortActiveLink"
		</s:if>>不限</a>
		<s:iterator value="areaList" status="st">
			<s:if test="#st.index==15">
				 <div id="moreConditionDivId1" style="display: none;">
			</s:if>	
			<a
				href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre"/>_<s:property value="id"/>_<s:property value="source"/>_<s:property value="year"/>.html"
				<s:if test="area==id">
					 class="SortActiveLink"
				</s:if>>
				<s:property value="name" /> </a>
			<s:if test="#st.index>14&&#st.last==true">
		  	</div>
		</s:if>
		</s:iterator>
		<div class="t_more1">
			<div id="moreDivId1" onclick="moveSearch(1)"
				style="display: block;">
				<a href="#" class="t_morzk">更多</a>
			</div>
			<div id="lessDivId1" onclick="lessSearch(1)"
				style="display: none;">
				<a href="#" class="t_morsq">收起</a>
			</div>
		</div>
	</div>
</div> 
  <div class="sort"> 
	<span><b>类型:</b></span>
 
	<div class="aBox">
		<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_0_<s:property value="area"/>_<s:property value="source"/>_<s:property value="year"/>.html" 
			<s:if test="genre==0">
				 class="SortActiveLink"
			</s:if>
		>
		不限
		</a>
		<s:iterator value="genreList" status="st">
			<s:if test="#st.index==15">
				 <div id="moreConditionDivId2" style="display: none;">
			</s:if>	
			<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="id"/>_<s:property value="area"/>_<s:property value="source"/>_<s:property value="year"/>.html"
				<s:if test="genre==id">
				 class="SortActiveLink"
				</s:if>
			>	
				<s:property value="name"/>
			</a>
			
			<s:if test="#st.index>14&&#st.last==true">
			  	</div>
			</s:if>
		</s:iterator> 
	 <div class="t_more1">
		 <a id="moreDivId2" onclick="moveSearch(2)" href="#" class="t_morzk" style="display: block;">更多</a>
		 <a id="lessDivId2" onclick="lessSearch(2)" href="#" class="t_morsq" style="display: none;">收起</a>
	</div>
	</div>
 </div>


 <div class="sort">
	 <span><b>上映:</b></span>
	 <div class="aBox">
	 <a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre"/>_<s:property value="area"/>_<s:property value="source"/>_0.html"
		<s:if test="year==null||year==''||year==0">
			class="SortActiveLink"
		</s:if>
	>不限
	</a>
	<s:iterator value="yearList" status="st">
		<s:if test="#st.index==15">
			 <div id="moreConditionDivId3" style="display: none;">
		</s:if>	
		<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre"/>_<s:property value="area"/>_<s:property value="source"/>_<s:property value="value"/>.html"
			<s:if test="year==value">
			 class="SortActiveLink"
			</s:if>
		>
			<s:property value="name"/>  
		</a>
		<s:if test="#st.index>14&&#st.last==true">
		  	</div>
		</s:if>
	</s:iterator> 
	<s:if test="#yearList.size>14">
		 <div class="t_more1">
			 <a id="moreDivId3" onclick="moveSearch(3)" href="#" class="t_morzk" style="display: block;">更多</a>
			 <a id="lessDivId3" onclick="lessSearch(3)" href="#" class="t_morsq" style="display: none;">收起</a>
		 </div>
	 </s:if>
 </div>
</div>
 
 
 
 <div class="sort">
	 <span><b>来源:</b></span>
	 <div class="aBox">
	    <a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre"/>_<s:property value="area"/>_0_<s:property value="year"/>.html"
			<s:if test="source==0">
					 class="SortActiveLink"
				</s:if>
		>不限</a>
		<s:iterator value="sourceList" >
				<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>/t_1_<s:property value="genre"/>_<s:property value="area"/>_<s:property value="id"/>_<s:property value="year"/>.html"
					<s:if test="source==id">
					 class="SortActiveLink"
					</s:if>
				>
					<s:property value="name"/>
				</a>
		</s:iterator>
   </div>
</div>

 
<br style="clear:both;" /><!--IE8下清楚浮动-->
</div>
<div class="blockSearchBox"><!--搜索影片-->
 <div class="picContent">
	<s:iterator value="tvList" status="tvst">
		 <div class="PicBlock">
			 <a class="AlinkPic" target="_blank"  href="t_<s:property value="id" />.html">
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
			<a class="Alink" target="_blank" href="t_<s:property value="id" />.html"><s:property value="name" /></a>
			<div>
				<span class="vote-star"><i style="width:<s:property value="rateScorePre" />%"></i></span>
				<span class="vote-number"><s:property value="rateScore" />分</span> 
			</div>
			 <p>主演：
			 	<s:iterator value="actorList" status="st">		
					<s:if test="#st.index==0">
						<a href="#"> <s:property value="actor.name"/></a>  
					</s:if>
				</s:iterator>	
			 </p>
		 </div> 
		 <s:if test="(#tvst.index+1)%6==0&&#tvst.index!=0">
		 	 <div class="clear"></div>
		 </s:if>
	 </s:iterator>
 </div> 
<br style="clear:both;" /><!--IE8下清楚浮动-->

 </div><!--搜索影片片结尾-->
 
 <tangs:webpages cpage="cpage" total="total" styleClass="pagination" theme="static"></tangs:webpages>   

 <br style="clear:both;" /><!--IE8下清楚浮动-->
</div><!--contentBox结尾-->

	<s:include value="foot.jsp"></s:include>



</body>
</html>
