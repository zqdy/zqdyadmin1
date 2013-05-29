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
			        url: "<%=request.getContextPath()%>/admin/doAddSubTv.action?t="+Math.random(),
			        type:"post" 
			    }; 
				
				function validateForm(){
					
					return true;
				}	
				function succesSubmit(responseText, statusText){
					alert(responseText);
					 
				}
				function save(){
					  $('#tvForm').ajaxSubmit(options); 
				}
				
				function cancel(){
					window.close();
					opener.location.reload();
				}
				
		</script>
	</head>

	<body>
		<s:form id="tvForm" action="/admin/doAddSubTv.action" enctype="multipart/form-data" >
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
									<font color="red">新增电视剧集</font>
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
					<td align="left" colspan="6">
				<td width="1">				</tr>
				<tr>
				  <td width="4" rowspan="7" align="right">&nbsp;</td>
					<td width="106" rowspan="7" align="right">						 
						<img
							src="<%=request.getContextPath()%>/<s:property value="subtv.imageUrl" />"
							height="90px">		
							<s:hidden name="subtv.imageUrl"></s:hidden>				  </td>
					<td align="right">
						名&nbsp;&nbsp;称：					</td>
					<td height="33" align="left">
						<s:textfield name="subtv.name" maxlength="30" id="name"></s:textfield>
						 
						&nbsp;
						<input type="hidden" name="subtv.tvId" value='<s:property value="#request.tvId" />'>
						
						<s:hidden name="subtv.addTime"></s:hidden>					</td>
					<td align="right">电影名：</td>
					<td>
						<s:textfield name="tvName" readonly="true" maxlength="30" id="tvName"></s:textfield>
&nbsp;					</td>
				</tr>
				<tr>
					<td width="80" align="right">电视来源：</td>
					<td width="238" height="28" align="left">
						<s:select   name="subtv.sourceServer" list="#request.snatchServerList" listKey="id" 
              				listValue="name" />  
              		</td>
					<td width="112" align="right">电视地址：</td>
					<td width="637">
						<s:textfield name="subtv.url" maxlength="90" id="url"></s:textfield>&nbsp;		
					</td>
				</tr>
				<tr>
					<td width="80" align="right">集&nbsp;&nbsp;数：</td>
					<td width="238" height="28" align="left">
				  		<s:textfield name="subtv.volume" maxlength="30" id="volume"></s:textfield>					</td>
					<td width="112" align="right">播放次数·：</td>
					<td width="637">
				  		<s:textfield name="subtv.payCount" maxlength="30" id="subtv.payCount"></s:textfield>&nbsp;					
				  	</td>
				</tr>
				<tr>
					<td align="right">抓取状态：</td>
					<td height="32" align="left">
						<s:textfield name="subtv.snatchState" maxlength="30" id="snatchState"></s:textfield>					</td>
					<td align="right">抓取总人数：</td>
					<td>
						<s:textfield name="subtv.snatchCount" maxlength="30" id="subtv.snatchCount"></s:textfield>					</td>
				</tr>
				<tr>
					<td align="right">入库时间：</td>
					<td height="35" align="left">
					   <s:property value="subtv.addTime" />&nbsp;<s:hidden name="subtv.addTime" ></s:hidden>
						 				</td>
					<td align="right">更新时间：</td>
					<td>
						 <s:property value="subtv.updateTime" />&nbsp;<s:hidden name="subtv.updateTime" ></s:hidden>
						&nbsp;
						
					</td>
				</tr>
				<tr >
					<td height="34" align="right">&nbsp;上传图片：</td>
					<td>&nbsp;<input type="file" name="imgFile" /></td>
					<td align="right"> </td>
					<td>
							</td>
				</tr>
				<tr >
					<td height="32" align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>
				 
				<tr >
				  <td align="right">&nbsp;</td>
					<td height="39" align="right">短描述：</td>					
					<td colspan="4">
						<textarea name="subtv.shortSummary" cols="150" rows="5"><s:property value="subtv.shortSummary" /></textarea>&nbsp;					</td>
				</tr>
				<tr >
				    <td align="right">&nbsp;</td>				           
					<td height="39" align="right">描述：</td>
					<td colspan="4">
						<textarea name="subtv.summary" cols="150" rows="5"><s:property value="subtv.summary" /></textarea>&nbsp;					</td>
				</tr>
				
				<tr >
				    <td align="right">&nbsp;</td>				           
					<td height="39" align="right">共享地址：</td>
					<td colspan="4">
						<textarea name="subtv.embed" cols="150" rows="5"><s:property value="subtv.embed" /></textarea>&nbsp;					</td>
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
						<input type="button" name="button2" class="btn" onclick="save();" value="确 定" />&nbsp;&nbsp;					</td>
					<td align="left">
						&nbsp;&nbsp;&nbsp;
						<input type="button" name="button2" class="btn" onclick="cancel()" value="取 消" />					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						&nbsp; 
				</tr>
			</table>
			</td>
			</tr>
		</table>
		</s:form>
		<div id="serverDivid" style="display:none">
			
		</div>
	</body>
</html>

