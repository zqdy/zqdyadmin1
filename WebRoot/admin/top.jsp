<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<style type="text/css">
<!--
html,body{height:100%} 
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.T14 {
	font-family: "宋体";
	font-size: 14px;
	font-weight: bold;
	color: #0054a9;
}
-->
</style>
	<head>
		 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="/pub/style/unicrm.css" rel="stylesheet" type="text/css">
	
		<script type="text/javascript">
			function goSnatch(){
				window.parent.window.frames['main'].location.href="<%=request.getContextPath()%>/admin/goSnatch.action";
			}
			function goSnatchDetail(){
				window.parent.window.frames['main'].location.href="<%=request.getContextPath()%>/admin/goSnatchDetail.action";
			}
			function modelList(){
				window.parent.window.frames['main'].location.href="<%=request.getContextPath()%>/admin/scheduleModelList.action";
			}
			function movieList(){
				window.parent.window.frames['main'].location.href="<%=request.getContextPath()%>/admin/movieList.action";
			}
			function tvList(){
				window.parent.window.frames['main'].location.href="<%=request.getContextPath()%>/admin/tvList.action";
			}
			
			
			function loginOut(){
				parent.location.href="loginOut.action";
			}
		</script>

	</head>

	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td background="../images/top_bg.jpg">
					<img src="../images/top.jpg" width="652" height="59" />
				</td>
				<td background="../images/top_bg.jpg">
					<%=request.getSession().getAttribute("userCode")%>
								&nbsp;欢迎您！
								<a href="javascript:loginOut();">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;				
				</td>
			</tr>
		</table>
		<table width="100%" border="0"  cellpadding="0" cellspacing="0">
			<tr>
				<td height="30" background="../images/ht.jpg">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					   <tr>
							<td height="30" background="../images/ht.jpg" align="left" >
								<font>
								 	<a href="#" onclick="goSnatch()">手动抓取网站</a>
								 	<a href="#" onclick="goSnatchDetail()">手动抓取详细</a>
								 	<a href="#" onclick="modelList()">模型管理</a>
								 	<a href="#" onclick="movieList()">电影管理</a>
								 	<a href="#" onclick="tvList()">电视剧管理</a>
								  </font>
							</td>
						</tr>
						 
					</table>
				</td>
			</tr>
		</table>

	</body>
</html>
