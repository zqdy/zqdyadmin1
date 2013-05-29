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
			
		<script language="javascript">	
			function editModel(modelId){
				window.location.href="<%=request.getContextPath()%>/admin/goEditScheduleModel.action?modelId="+modelId;
			} 
			function addModel(){
				window.location.href="<%=request.getContextPath()%>/admin/goAddScheduleModel.action";
			}
			function deleteSelectModel(){
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
					deleteModel(ids);
				}else{
					alert('请选择要删除的数据');
				}
			}
			function deleteModel(modelId){
				var url ="<%=request.getContextPath()%>/admin/deleteScheduleModel.action";	
				$.get(url,{"modelIds":modelId},function(data){
					 alert(data);
					 window.location.reload(); 
				});		
			}
			function startModel(modelId){
				var url ="<%=request.getContextPath()%>/admin/startScheduleModel.action?t="+Math.random();	
				$.get(url,{"modelIds":modelId},function(data){
					 alert(data);
					 window.location.reload(); 
				});		
			}
			function stopModel(modelId){
				var url ="<%=request.getContextPath()%>/admin/stopScheduleModel.action?t="+Math.random();	
				$.get(url,{"modelIds":modelId},function(data){
					 alert(data);
					 window.location.reload(); 
				});		
			}
		</script>
	</head>

	<body>
		 
		<s:form id="modelForm" action="/admin/scheduleModelList.action">
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="4">
						<img src="<%=request.getContextPath()%>/images/t01.jpg" width="4"
							height="28" />
					</td>
					<td background="<%=request.getContextPath()%>/images/t02.jpg">
						<b>&nbsp;&nbsp;当前位置:</b>后台管理>>
						<font color="red">模型列表</font>
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
						<span class="body_title">模型名称：</span>
					</td>
					<td width="15%" align="center">
						<s:textfield name="modelName" size="20"></s:textfield>
					</td>
					<td width="11%" align="right">
						抓取网站：
					</td>
					<td width="15%">
						<s:select   name="sourceServer" list="#request.snatchServerList" listKey="id" 
              				listValue="name" headerKey="0" headerValue="--请选择抓取网站--"/>  
					</td>
					 
					<td width="26%">
						<input type="submit" name="button" class="btn" value="查 询" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="addModel();" value="新 增" />&nbsp;&nbsp;
								<input type="button" name="button2" class="btn"
								onClick="deleteSelectModel();" value="删 除" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<table class="body_table" width="100%" border="1" cellpadding="0"
				cellspacing="0">
				<tr>
					<th  class="body_title" height="36" width="12px">
						 &nbsp;
					</th>
					<th  class="body_title">
						模型名称
					</th>					
					<th class="body_title">
						抓取类型
					</th>
					<th    class="body_title">
						抓取网站
					</th>
					<th     class="body_title">
						运行策略
					</th>
					<th     class="body_title">
						运行时间
					</th>
					<th   class="body_title">
						状态
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
						<td align="center" class="body_td">
							<s:property value="scheduleName" />&nbsp;
						</td>
						<td align="center" class="body_td">
							<s:if test="scheduleType==1">
								抓取链接
							</s:if>
							<s:if test="scheduleType==2">
								抓取影片
							</s:if>
							<s:if test="scheduleType==3">
								自动更新电视剧
							</s:if>
							<s:if test="scheduleType==4">
								抓取子集embed
							</s:if>&nbsp; 
							
							 
						</td>
						<td height="28" align="center" class="body_td">
							<s:property value="source.name" />
							&nbsp;
						</td>
						<td align="center" class="body_td">							
							<s:if test="runStrategy==1">
								一次性
							</s:if>
							<s:elseif test="runStrategy==2">
								每天
							</s:elseif>
							<s:elseif test="runStrategy==3">
								每周
							</s:elseif>
							<s:elseif test="runStrategy==4">
								每月
							</s:elseif>
							<s:elseif test="runStrategy==5">
								间隔
							</s:elseif>
							&nbsp;
						</td>
						<td align="center" class="body_td">							 
							 <s:if test="runStrategy==1">
								 
							</s:if>
							<s:elseif test="runStrategy==2">
								<s:property value="autoRunTime" />
							</s:elseif>
							<s:elseif test="runStrategy==3">
								<s:property value="autoRunTime" />
							</s:elseif>
							<s:elseif test="runStrategy==4">
								<s:property value="runMonthDay" />号<s:property value="autoRunTime" />
							</s:elseif>
							<s:elseif test="runStrategy==5">
								 <s:property value="runPeriodHour" />小时<s:property value="runPeriodMinute" />分
							</s:elseif>
							&nbsp;
						</td>
						<td align="center" class="body_td">							 
							<s:if test="isActive==0">
								未运行
							</s:if>
							<s:elseif test="isActive==1">
								<font color="green"> 运行中</font>
							</s:elseif>
							&nbsp;
						</td>
						<td align="center" class="body_td">
							&nbsp;<a href="#" onclick="editModel(<s:property value="id" />)">编辑</a>
							&nbsp;<a href="#" onclick="deleteModel(<s:property value="id" />)">删除</a>
							<s:if test="isActive==1">
								&nbsp;<a href="#" onclick="stopModel(<s:property value="id" />)">停止</a>
							</s:if>
							<s:else>
								&nbsp;<a href="#" onclick="startModel(<s:property value="id" />)">启动</a>
							</s:else>
							
						</td>
						 
					</tr>
				</s:iterator>
				<tr align="center">
					<td colspan="7" class="body_td">
						<tangs:pages cpage="${cpage}" total="${total}"
							formId="modelForm" />
					</td>
				</tr>
			</table>
			 
		</s:form>
	</body>
</html>
