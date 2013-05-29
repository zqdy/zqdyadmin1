<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	function search(){
	 
		var url = '<s:property value="%{getText('zqdy.mainUrl')}"/>/search.html';
		var Keyword = document.getElementById("keyword").value;
		if(Keyword==''){
			alert('请输入要查询的关键字！');
		}else{
			Keyword = encodeURI(encodeURI(Keyword));
			url = url+'?keyword='+Keyword;
			window.location.href = url;
		}
		
	}	
 
 
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?ea104c914921f87ce5cbe818c11a87bf";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
 
</script>
<div class="bannerBox">
		<div class="bannerContent">
			<a href="<s:property value="%{getText('zqdy.mainUrl')}"/>" ><img src="images/logo.jpg" /></a>

			<div class="searchBox">
				<div class="searchBg">
				<input name="keyword" id="keyword" onFocus="if(this.value=='电影名称/导演/主演'){this.value=''}"
     			 onBlur="if(this.value==''){this.value='电影名称/导演/主演'}" 
				 onkeypress="if(event.keyCode==13) {search();return false;}" value="<s:property value="searchName" />" />
				</div>
				<a  class="searchBtn" href="javascript:search()">&nbsp;</a>
				 
			</div>
			 
		</div>
	</div>
	<!--bannerBox结尾-->

	<div class="linkbarBox">
		<div class="linkbarCenter">
			<a href="<s:property value="%{getText('zqdy.mainUrl')}" />" 
					<%String p0 = request.getParameter("p");
					if(p0==null||"0".equals(p0)){
					%>
						class="active"
					<%
					}
				 %> 
			>首页</a> |
			<a href="<s:property value="%{getText('zqdy.mainUrl')}" />/movie.html"   
				<%String p1 = request.getParameter("p");
					if("1".equals(p1)){
					%>
						class="active"
					<%
					}
				 %> 
			>电影</a> |
			<a  href="<s:property value="%{getText('zqdy.mainUrl')}"/>/tv.html"   
				<%String p2 = request.getParameter("p");
					if("2".equals(p2)){
					%>
						class="active"
					<%
					}
				 %> 
			>电视剧</a>| 
		</div>
		<!--linkbarBox结尾-->
	</div>
	<!--linkbarBox结尾-->