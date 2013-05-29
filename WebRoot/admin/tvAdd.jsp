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
			        url: "<%=request.getContextPath()%>/admin/addTv.action?t="+Math.random(),
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
						window.location.href="<%=request.getContextPath()%>/admin/tvList.action";
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
				
				function deleteActorTv(actorTvId,trId){
					if(actorTvId!==0){
						var url ="<%=request.getContextPath()%>/admin/deleteActorTv.action?t="+Math.random();	
						$.get(url,{"actorTvId":actorTvId},function(data){
							 alert(data);							  
						});		
					}
					var index = document.getElementById(trId).rowIndex;
					document.getElementById('tvTable').deleteRow(index);
					
				}
				function deleteSubTv(subTvId,trId){				
					if(subTvId!==0){
						
						$.get(url,{"subTvId":subTvId},function(data){
							 alert(data);							  
						});		
					}
					var index = document.getElementById(trId).rowIndex;
					document.getElementById('subTvTable').deleteRow(index);
				}
				function addSubTv(tvId){
					var url ="<%=request.getContextPath()%>/admin/goAddSubTv.action?tvId="+tvId+"&t="+Math.random();	
					window.open(url);
				}
				function editSubTv(subTvId,tvId){
					var url ="<%=request.getContextPath()%>/admin/goEditSubTv.action?subTvId="+subTvId+"&tvId="+tvId+"&t="+Math.random();	
					window.open(url);
				}
				function addActor(){
					  var actorTR = document.getElementById("tvTable").insertRow(-1);
					  var trId = new Date().getTime();			  
			 		  actorTR.setAttribute("id","actor"+trId);	
			 		  
			 		   var td1 = actorTR.insertCell(-1);
					  td1.setAttribute("align","right");
					  td1.innerHTML ='<select name="actorTvType" id="'+trId+'" onchange="actorTypeChange(this)"><option value="1">主演</option><option value="2">编剧</option><option value="3">监制</option><option value="4">原著</option></select>';
		              var td2 = actorTR.insertCell(-1);
		              td2.setAttribute("align","left");
					  td2.innerHTML ='<input type="text" name="actorNames"><input type="hidden" name="actorTvId" value="<s:property value="id" />"><input type="hidden" name="actorTvInd" >';
		              var td3 = actorTR.insertCell(-1);
		              td3.setAttribute("align","right");
					  td3.innerHTML ='&nbsp;<div id="actor'+trId+'Protray1">饰&nbsp;演：</div>';
					  
					  var td4 = actorTR.insertCell(-1);
		              td4.setAttribute("align","left");
					  td4.innerHTML ='<div id="actor'+trId+'Protray2"><input type="text" name="portray" ></div>&nbsp;<a href="#" onclick="deleteActorTv(0,\'actor'+trId+'\')" >删除</a>';
				}
				 function actorTypeChange(actorTypeObj){
				 	var v = actorTypeObj.value;
				 	var vId = actorTypeObj.id;
				 	if(v==1){
				 		$("#actor"+vId+"Protray1").show();
				 		$("#actor"+vId+"Protray2").show();
				 	}else{
				 		$("#actor"+vId+"Protray1").hide();
				 		$("#actor"+vId+"Protray2").hide();
				 	}
				 }
		</script>
	</head>

	<body>
		<s:form id="movieForm" action="/addTv/editTv.action" enctype="multipart/form-data" >
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
									<font color="red">添加电视剧</font>
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
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					
					<tr >
					  <td align="center" colspan="5">
					  	<img
								src="<%=request.getContextPath()%>/<s:property value="tv.imageUrl" />"
								height="300px">		
								<s:hidden name="tv.imageUrl"></s:hidden>
					  </td>
					    	 
					</tr>
					
					<tr >
						<td align="left"  colspan="5">
							<table width="100%"   border="0" align="center" cellpadding="0" cellspacing="0">
							     <tr>
									<td align="right" width="250px">
										名&nbsp;称：					</td>
									<td height="33" width="200px" align="left">
										<s:textfield name="tv.name" maxlength="30" id="name"></s:textfield>
										&nbsp;
										<s:hidden name="tv.addTime"></s:hidden>					</td>
									<td align="right" width="100px">
										别&nbsp;名：					</td>
									<td>
										<s:textfield name="tv.alias" maxlength="30" id="alias"></s:textfield>
										&nbsp;					
									</td>
								</tr>
								<tr> 
										<td align="right">上映年代：</td>
										<td height="28" align="left">
											<s:textfield name="tv.releaseYear" maxlength="30"
												id="releaseYear"></s:textfield>					</td>
										<td align="right">上映时间：</td>
										<td>
											<s:textfield name="tv.pubDate" maxlength="30" id="pubDate"></s:textfield>&nbsp;					
										</td>
							   </tr>
							<tr>
							   
								<td  align="right">地&nbsp;区：</td>
								<td height="28" align="left">
									<s:textfield name="areas" maxlength="30" id="areas"></s:textfield>					</td>
								<td   align="right">类&nbsp;型：</td>
								<td  >
									<s:textfield name="genres" maxlength="30" id="genres"></s:textfield>&nbsp;					</td>
							</tr>
							<tr>
								 
								<td align="right">导&nbsp;演：</td>
								<td height="32" align="left">
									<s:textfield name="directors" maxlength="30" id="directors"></s:textfield>					</td>
								<td align="right">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr> 
								<td  width="250px" align="right">时&nbsp;长：</td>
								<td  width="200px" height="35" align="left"><s:textfield name="tv.duration" maxlength="30" id="duration"></s:textfield></td>
								<td width="100px" align="right">播放次数：</td>
								<td><s:textfield name="tv.playCount" maxlength="30" id="playCount"></s:textfield>&nbsp;</td>
							</tr>
							<tr > 
								<td height="34" align="right">显示类型：</td>
								<td><s:textfield name="tv.displayType" maxlength="30" id="displayType"></s:textfield>&nbsp;</td>
								<td align="right">显示权重：</td>
								<td><s:textfield name="tv.weight" maxlength="30" id="weight"></s:textfield>&nbsp;</td>
							</tr>
							<tr >							  	
								<td height="32" align="right">上传图片：</td>
								<td><input type="file" name="imgFile" /></td>
								<td align="right">&nbsp;</td>
								<td>&nbsp;</td>				 
							</tr>
								<tr >
								<td height="34" align="right">是否最热：</td>
								<td>
										<s:if test="tv.isHot==1">
											<input type="checkbox" checked="true" value="1" name="tv.isHot"/>
										</s:if>
										<s:else>
											<input type="checkbox" name="tv.isHot" value="1"/>
										</s:else>
			&nbsp;					</td>
								<td align="right"> </td>
								<td>
									 
								</td>
							</tr>
				
							<tr > 
								<td height="32" align="right">添加时间：</td>
								<td><s:property value="tv.addTime" />&nbsp;<s:hidden name="tv.addTime" ></s:hidden>&nbsp;</td>
								<td align="right">更新时间：</td>
								<td>
									<s:property value="tv.updateTime" />&nbsp;<s:hidden name="tv.updateTime" ></s:hidden>
									<input type="button" onclick="addActor()" value="增加演员"/>
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr >
					<td align="left"  colspan="5">
						<table width="100%" id="tvTable" border="1" align="center" cellpadding="0" cellspacing="0">
								<s:iterator value="tv.actorList" status="at">
									<s:if test="type==1">
										<tr id="actor1<s:property value="%{#at.index+1}" />">
										   
											<td width="250px" align="right">
													主&nbsp;演：
											</td>
											<td width="200px" height="32" align="left">
												<input type="text" name="actorNames" value="<s:property value="actor.name" />"> 			 
												<input type="hidden" name="actorTvId" value="<s:property value="id" />">								
												<input type="hidden" name="actorTvType" value="<s:property value="type" />">
												<input type="hidden" name="actorTvInd" value="<s:property value="ind" />">
											</td>
											<td width="100px" align="right">饰&nbsp;演：</td>
											<td>
												<s:textfield name="portray" maxlength="30" id="portray"></s:textfield>	&nbsp;
												<a href="#" onclick="deleteActorTv(<s:property value="id" />,'actor1<s:property value="%{#at.index+1}" />')" >删除</a>
											</td>
										</tr>
									</s:if>
									<s:elseif test="type==2">
										<tr id="actor2<s:property value="%{#at.index+1}" />">
											 
											<td align="right">
													编&nbsp;剧：
											</td>
											<td height="32" align="left">
												<input type="text" name="actorNames" value="<s:property value="actor.name" />"> 	
												<input type="hidden" name="actorTvActorId" value="<s:property value="actor.id" />">				 
												<input type="hidden" name="actorTvId" value="<s:property value="id" />">								
												<input type="hidden"name="actorTvType" value="<s:property value="type" />">
												<input type="hidden" name="actorTvInd" value="<s:property value="ind" />">
												<input type="hidden" name="portray" />&nbsp;
											</td>
											<td align="right">&nbsp;</td>
											<td>
												 &nbsp;<a href="#" onclick="deleteActorTv(<s:property value="id" />,'actor2<s:property value="%{#at.index+1}" />')" >删除
											</td>
										</tr>
									</s:elseif>
									<s:elseif test="type==3">
										<tr id="actor3<s:property value="%{#at.index+1}" />">
											 
											<td align="right">
													监&nbsp;制：
											</td>
											<td height="32" align="left">
												<input type="text" name="actorNames" value="<s:property value="actor.name" />"> 	
												<input type="hidden" name="actorTvActorId" value="<s:property value="actor.id" />">				 
												<input type="hidden" name="actorTvId" value="<s:property value="id" />">								
												<input type="hidden"name="actorTvType" value="<s:property value="type" />">
												<input type="hidden" name="actorTvInd" value="<s:property value="ind" />">
												<input type="hidden" name="portray" />&nbsp;
											</td>
											<td align="right">&nbsp;</td>
											<td>
												 <a href="#" onclick="deleteActorTv(<s:property value="id" />,'actor3<s:property value="%{#at.index+1}" />')" >删除
											</td>
										</tr>
									</s:elseif>
									<s:elseif test="type==4">
										<tr id="actor4<s:property value="%{#at.index+1}" />">
											<td align="right">
													原&nbsp;著：
											</td>
											<td height="32" align="left">
												<input type="text" name="actorNames" value="<s:property value="actor.name" />"> 	
												<input type="hidden" name="actorTvActorId" value="<s:property value="actor.id" />">				 
												<input type="hidden" name="actorTvId" value="<s:property value="id" />">								
												<input type="hidden"name="actorTvType" value="<s:property value="type" />">
												<input type="hidden" name="actorTvInd" value="<s:property value="ind" />">
												<input type="hidden" name="portray" />&nbsp;
											</td>
											<td>&nbsp;</td>
											<td>
												&nbsp;<a href="#" onclick="deleteActorTv(<s:property value="id" />,'actor4<s:property value="%{#at.index+1}" />')" >删除</a>
											</td>
										</tr>
									</s:elseif>
								</s:iterator>
						</table>
					</td>
				</tr>
				
				
				<tr >
					<td align="left"   colspan="4">
						<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
							
							<tr > 
								<td width="250px" height="39" align="right">短描述：</td>					
								<td colspan="3"><textarea name="tv.shortSummary" cols="150" rows="5"><s:property value="tv.shortSummary" /></textarea></td>
							</tr>
							<tr > 	
								<td height="39" align="right">描述：</td>
								<td colspan="3">
									<textarea name="tv.summary" cols="150" rows="5"><s:property value="tv.summary" /></textarea>
								</td>
							</tr>
							<tr>
								<td height="30px" align="right">&nbsp;</td>
								<td align="right">
									<input type="button" name="button2" class="btn" onClick="save();" value="确 定" />&nbsp;&nbsp;
								</td>
								<td align="left">
									&nbsp;&nbsp;&nbsp;
									<input type="button" name="button2" class="btn" onClick="returnBack()" value="返  回" />	
								</td>
								<td>&nbsp;</td>
							</tr>
							 
										
						</table>
					</td>
				</tr>
			  </table>
			</td>
			</tr>
		</table>
		</s:form>
		 
	</body>
</html>

