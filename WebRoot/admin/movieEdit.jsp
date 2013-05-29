<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="<%=request.getContextPath()%>/css/style.css"
			rel="stylesheet" type="text/css" />
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/js/common.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/js/createModel.js'></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript">	
			//
			$(document).ready(function(){
				 
			});
			 var options = { 
					//  target:'myForm1',          
			        beforeSubmit:validateForm,   
			        success:succesSubmit,  
			        dataType : "script",//数据类型  
			        url: "<%=request.getContextPath()%>/admin/editMovie.action?t="+Math.random(),
			        type:"post" 
			    }; 
				
				function validateForm(){
					
					return true;
				}	
				function succesSubmit(responseText, statusText){
					alert(responseText);
					 
				}
				function save(){
					  $('#movieForm').ajaxSubmit(options); 
				}
				
				function returnBack(){
						window.location.href="<%=request.getContextPath()%>/admin/movieList.action";
				}	
				
				function deleteEmbed(embedId,trId){
					var index = document.getElementById(trId).rowIndex;
					if(embedId!==0){
						var url ="<%=request.getContextPath()%>/admin/deleteEmbed.action";	
						$.get(url,{"embedId":embedId},function(data){
							 alert(data);							  
						});		
					}
					document.getElementById('embedTableId').deleteRow(index);
				}
				function addEmbed(){
					  var embedTR = document.getElementById("embedTableId").insertRow(-1);
					  var trId = new Date().getTime();			  
			 		  embedTR.setAttribute("id","embed"+trId+"");			 		  
					  var td1 = embedTR.insertCell(-1);
					  td1.setAttribute("align","center");
					  td1.innerHTML =document.getElementById("serverDivid").innerHTML;
		              var td2 = embedTR.insertCell(-1);
		              td2.setAttribute("align","center");
					  td2.innerHTML ='<input type="hidden" name="embedIds" value="0"/><textarea name="embeds" cols="100" rows="3"></textarea>';
		              var td3 = embedTR.insertCell(-1);
		              td3.setAttribute("align","center");
					  td3.innerHTML ='<a href="#" onclick="deleteEmbed(0,\'embed'+trId+'\')">删除</a>';
              
				}
		</script>
	</head>

	<body>
		<s:form id="movieForm" action="/admin/editScheduleModel.action" enctype="multipart/form-data" >
			<br />
			<table align="center" width="100%" >
				<tr>
					<td>			
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="4">
									<img src="<%=request.getContextPath()%>/images/t01.jpg" width="4"
										height="28" />
								</td>
								<td background="<%=request.getContextPath()%>/images/t02.jpg">
									<b>&nbsp;&nbsp;当前位置:</b>电影管理>>
									<font color="red">修改电影</font>
								</td>
								<td width="4">
									<img src="<%=request.getContextPath()%>/images/t03.jpg" width="4"
										height="28" />
								</td>
							</tr>
						</table>
				    </td>
			    </tr>
			<tr>
			   <td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr >
					<td align="left" width="15px" colspan="6">
				<td width="1">				</tr>
				<tr>
				  <td width="123" rowspan="7" align="right">&nbsp;</td>
					<td width="123" rowspan="7" align="right">	
											 
						<img
							src="<s:property value="%{getText('zqdy.imageUrl')}"/>/<s:property value="movie.imageUrl" />"
							height="300px">		
							<s:hidden name="movie.imageUrl"></s:hidden>
					</td>
					<td align="right">
						名&nbsp;称：					</td>
					<td height="33" align="left">
						<s:textfield name="movie.name" maxlength="30" id="name"></s:textfield>
						&nbsp;
						<s:hidden name="movie.id"></s:hidden>
						<s:hidden name="movie.addTime"></s:hidden>					</td>
					<td align="right">
						别&nbsp;名：					</td>
					<td>
						<s:textfield name="movie.alias" maxlength="30" id="alias"></s:textfield>
&nbsp;					</td>
				</tr>
				<tr>
					<td width="79" align="right">上映年代：</td>
					<td width="232" height="28" align="left">
						<s:textfield name="movie.year" maxlength="30"
							id="releaseYear"></s:textfield>					</td>
					<td width="87" align="right">评分：</td>
					<td width="652">
						<s:textfield name="movie.rateScore" maxlength="30" id="rateScore"></s:textfield>
&nbsp;					</td>
				</tr>
				<tr>
					<td width="79" align="right">地&nbsp;区：</td>
					<td width="232" height="28" align="left">
						<s:textfield name="areas" maxlength="30" id="areas"></s:textfield>					</td>
					<td width="87" align="right">类&nbsp;型：</td>
					<td width="652">
						<s:textfield name="genres" maxlength="100" id="genres"></s:textfield>&nbsp;					</td>
				</tr>
				<tr>
					<td align="right">导&nbsp;演：</td>
					<td height="32" align="left">
						<s:textfield name="directors" maxlength="300" id="directors"></s:textfield>					</td>
					<td align="right">主&nbsp;演：</td>
					<td>
						<s:textfield name="actors" maxlength="300" id="actors"></s:textfield>					</td>
				</tr>
				<tr>
					<td align="right">权重：</td>
					<td height="35" align="left">
						<s:textfield name="movie.weight" maxlength="30" id="weight"></s:textfield>&nbsp;					</td>
					<td align="right">播放次数：</td>
					<td><s:textfield name="movie.playCount" maxlength="30" id="playCount"></s:textfield>&nbsp;</td>
				</tr>
				 <tr>
					<td align="right">豆瓣ID：</td>
					<td height="35" align="left">
						<s:textfield name="movie.doubanId" maxlength="30" id="doubanId"></s:textfield>&nbsp;					</td>
					<td align="right">评论次数：</td>
					<td><s:textfield name="movie.commentsCount" maxlength="30" id="commentsCount"></s:textfield>&nbsp;</td>
				</tr>
				 
				
				<tr >
					<td height="34" align="right">是否付费：</td>
					<td>
							<s:if test="movie.isMember==1">
								<input type="checkbox" checked="true" value="1" name="movie.isMember"/>
							</s:if>
							<s:else>
								<input type="checkbox" name="movie.isMember" value="1"/>
							</s:else>
&nbsp;					</td>
					<td align="right">添加时间：</td>
					<td>
						<s:property value="movie.addTime" />&nbsp;<s:hidden name="movie.addTime" ></s:hidden>&nbsp; 
					</td>
				</tr>
				
				
				
				
				<tr >
					<td height="32" align="right"></td>
					<td></td>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>
				<tr >
				  <td align="right">&nbsp;</td>
				   <td align="right">&nbsp;</td>
					<td height="32" align="right">上传图片：</td>
					<td>
						 <input type="file" name="movieImg" />
						
					</td>
					<td align="right">上传其它图片：</td>
					<td>&nbsp;</td>				 
				</tr>
				<tr >
				  <td align="right">&nbsp;</td>
					<td height="39" align="right">短描述：</td>					
					<td colspan="4">
						<textarea name="movie.shortSummary" cols="150" rows="5"><s:property value="movie.shortSummary" /></textarea>&nbsp;					</td>
				</tr>
				<tr >
				    <td align="right">&nbsp;</td>				           
					<td height="39" align="right">描述：</td>
					<td colspan="4">
						<textarea name="movie.summary" cols="150" rows="5"><s:property value="movie.summary" /></textarea>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>	
				    <td align="right">&nbsp;</td>
					<td colspan="4" align="left">
						<table width="880px" id="embedTableId" cellspacing="0"
							cellpadding="0" border="1" height="15px">
							<tr>
								<td width="80px" align="center">网站	</td>
								<td width="120px" align="center">名称</td>
								<td width="80px" align="center">类型	</td>
								<td align="center">	共享地址</td>
								<td width="80px" align="center"><a href="#" onclick="addEmbed()">新增</a></td>
							</tr>
							<s:iterator value="movie.embedList" id="em" status="st">
								<s:if test="id!=null">
									<tr align="center" id="embedTr<s:property value="%{#st.index+1}" />">
									  	<td height="30">
										  	<input type="hidden" name="embedIds" value="<s:property value="id" />" />
										  	<input type="hidden" name="embedSourceServer" value="<s:property value="source.id" />" />
										  	<s:property value="source.name" />
									  	</td>
									  	<td height="30">
										   <s:property value="name" />
										   <s:if test="isMember==1">（收费）</s:if>
									  	</td>
									  	<td height="30">
									  		<s:if test="type==1">正片</s:if>
									  		<s:elseif test="type==2">记录片</s:elseif>
										   <s:elseif test="type==3">预告</s:elseif>
									  	</td>
									  	<td align="left"> 									  		
									  		<textarea name="embeds" cols="100" rows="3"><s:property value="embed" /></textarea>&nbsp;
									  	</td>
									    <td><a href="#" onclick="deleteEmbed(<s:property value="id" />,'embedTr<s:property value="%{#st.index+1}" />')">删除</a></td>
									</tr>
								</s:if>
							</s:iterator>							 
						</table>			
					</td>
				</tr>

				<tr>
					<td colspan="6" height="8px">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>
				<tr>
				  <td align="right">&nbsp;</td>
					<td height="40" align="right">&nbsp;					</td>
					<td align="right">&nbsp;					</td>
					<td align="right">
						<input type="button" name="button2" class="btn" onclick="save();" value="确 定" />&nbsp;&nbsp;
					</td>
					<td align="left">
						&nbsp;&nbsp;&nbsp;
						<input type="button" name="button2" class="btn" onclick="returnBack()" value="返  回" />	
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						&nbsp;
						<font color="red"><div id="msgDiv"></div> </font></td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		</s:form>
		<div id="serverDivid" style="display:none">
			<s:select   name="embedSourceServer" list="#request.snatchServerList" listKey="id" 
              				listValue="name" />  
		</div>
	</body>
</html>

