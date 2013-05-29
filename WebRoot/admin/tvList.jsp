<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tangs.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>任务模型列表</title>
		<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>	
		<script language="javascript">	
			function editMovie(tvId){
				window.location.href="<%=request.getContextPath()%>/admin/goEditTv.action?tvId="+tvId;
			} 
			function addMovie(){
				window.location.href="<%=request.getContextPath()%>/admin/goAddTv.action?t="+Math.random();	
			}
			function getSelectId(){
				var mIdArr = document.getElementsByName("mId");
				var ids='';
				for(i=0;i<mIdArr.length;i++){
					if(mIdArr[i].checked){
						var v = mIdArr[i].value;
						ids = ids+v+',';
					}
				}
				
				if(ids.length>1){
					ids = ids.substring(0,ids.length-1);					
				 	return ids;
				}else{
					alert('请选择要删除的数据');
					return '';
				}
			}
			//添加推荐
			function addRecomm(){
				var ids = getSelectId();
				if(ids!=''){
					var url ="<%=request.getContextPath()%>/admin/deleteScheduleModel.action?t="+Math.random();	
					$.get(url,{"ids":ids},function(data){
					 	alert(data);					  
					});		
				}
			}
			//添加最热
			function addHot(){
				var ids = getSelectId();
				if(ids!=''){
					
				}
			}
			
			
			function addHotTv(){
				var code_Values = $("input[name='mId'][checked]");//	  document.all['mId']; 
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids = ids + code_Values[i].value+',';
						} 
					} 					 
					 
					var url ="<%=request.getContextPath()%>/admin/hotRecommTv.action?t="+Math.random();	
					$.get(url,{"hotIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电视剧");
				}
			}
			 function delHotTv(){
				var code_Values = $("input[name='mId'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/unHotRecommTv.action?t="+Math.random();		
					$.get(url,{"hotIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电视剧");
				}
			}
			
			function addRecommTv(){
				var code_Values = $("input[name='mId'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/hotRecommTv.action?t="+Math.random();		
					$.get(url,{"recommIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电视剧");
				}
			}
			 function delRecommTv(){
				var code_Values = $("input[name='mId'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/unHotRecommTv.action?t="+Math.random();		
					$.get(url,{"recommIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电视剧");
				}
			}
		</script>
	</head>

	<body>
		 
		<s:form id="tvForm" action="/admin/tvList.action">
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="4">
						<img src="<%=request.getContextPath()%>/images/t01.jpg" width="4"
							height="28" />
					</td>
					<td background="<%=request.getContextPath()%>/images/t02.jpg">
						<b>&nbsp;&nbsp;当前位置:</b>后台管理>>
						<font color="red">电视剧列表</font>
					</td>
					<td width="4">
						<img src="<%=request.getContextPath()%>/images/t03.jpg" width="4"
							height="28" />
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="10%" align="right">
						<span class="body_title">电视剧名称：</span>
					</td>
					<td width="15%" align="center">
						<s:textfield name="tvName" size="50"></s:textfield>
					</td>
					<td width="11%" align="right">
						 
					</td>
					<td width="15%">
						 
					</td>
					 
					<td width="26%">
						<input type="submit" name="button" class="btn" value="查 询" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="addMovie();" value="新 增" />&nbsp;&nbsp;
						<input type="button" name="button2" class="btn"
								onClick="addHotTv()" value="热门" />&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="delHotTv()" value="取消热门" />&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="addRecommTv()" value="推荐" />&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="delRecommTv()" value="取消推荐" />&nbsp;
					</td>
					 
				</tr>
			</table>
			<table class="body_table" width="100%" border="1" cellpadding="0"
				cellspacing="0">
				<tr>
					<th  class="body_title" height="36" width="12px">
						 &nbsp;<input  type="checkbox" onclick="selectAll(this)">
					</th>
					<th     class="body_title">
						图片
					</th>
					<th  class="body_title">
						电影名称
					</th>					
					<th class="body_title">
						上映日期
					</th> 
					<th    class="body_title">
						更新状态
					</th>
					<th    class="body_title">
						是否推荐
					</th>
						<th    class="body_title">
						是否最热
					</th>
					<th   class="body_title">
						入库时间
					</th>
					<th   class="body_title">
						更新时间
					</th>
					<th  class="body_title">
						 &nbsp;
					</th>
					 
				</tr>
			 
				<s:iterator value="resultList">
					<tr onMouseOver="this.style.backgroundColor='#F0F9FB'"
						onmouseout="this.style.backgroundColor='#FFFFFF'">
						<td  class="body_td">
						 &nbsp;<input name="mId" type="checkbox" value="<s:property value="id" />">
						</td>
						<td align="center"  width="60px"  class="body_td">							
								<s:property value="area.name" /> 
								<img src="<s:property value="%{getText('zqdy.imageUrl')}"/>/<s:property value="imageUrl" />" width="60px" height="90px">
						</td>
						<td align="center" class="body_td">
							<s:property value="name" />&nbsp;
						</td>
						<td align="center" class="body_td">
							<s:property value="pubDate" /> &nbsp;
						</td>
						<td height="28" align="center" class="body_td">
							<s:property value="updateLength" />/<s:property value="length" />集
							&nbsp;
						</td>
					 	
					 	<td align="center" class="body_td">							 
							 
							&nbsp;
						</td>
						<td align="center" class="body_td">							 
							 
							&nbsp;
						</td>
						<td align="center" class="body_td">							 
							<s:property value="addTime" /> 
							&nbsp;
						</td>
						
						<td align="center" class="body_td">							 
							<s:property value="updateTime" /> 
							&nbsp;
						</td>
						
						<td align="center" class="body_td">
							
							&nbsp;<a href="#" onclick="editMovie(<s:property value="id" />)">编辑</a>
							&nbsp;<a href="#" >前台查看</a>
							 
						</td>
						 
					</tr>
				</s:iterator>
				<tr align="center">
					<td colspan="10" class="body_td">
						<tangs:pages cpage="${cpage}" total="${total}"
							formId="tvForm" />
					</td>
				</tr>
			</table>
			 
		</s:form>
	</body>
</html>
