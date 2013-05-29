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
				var strategy = ${schedulemodel.runStrategy};
				if(strategy==1){
					hideAllAutotaskRun();
				}else if(strategy==2){
					showEveryDateRun();
				}else if(strategy==3){
					showEveryWeekRun();
				}else if(strategy==4){
					showEveryMonthRun();
				}else if(strategy==5){
					showEveryTime();
				}
				var  scheduleTypeObj =  document.getElementById("scheduleType");
				 
				scheduleTypeChange(scheduleTypeObj);
			});
			 var options = { 
					//  target:'myForm1',          
			        beforeSubmit:validateForm,   
			        success:succesSubmit,  
			        url: "<%=request.getContextPath()%>/admin/editScheduleModel.action",
			        type:"post" 
			    }; 
				
				function validateForm(){
					
					return true;
				}	
				function succesSubmit(responseText, statusText){
					$("#msgDiv").html(responseText);		 
					 
				}
				function save(){
					  $('#modelForm').ajaxSubmit(options); 
				}
				
				function returnBack(){
						window.location.href="<%=request.getContextPath()%>/admin/scheduleModelList.action";
				}	
		</script>
	</head>

	<body>
		<s:form id="modelForm" action="/admin/editScheduleModel.action">
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="4">
						<img src="<%=request.getContextPath()%>/images/t01.jpg" width="4"
							height="28" />
					</td>
					<td background="<%=request.getContextPath()%>/images/t02.jpg">
						<b>&nbsp;&nbsp;当前位置:</b>模型管理>>
						<font color="red">修改模型</font>
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
					<td width="28%" height="28" align="right">
						<p>
							模型名称：
						</p>
					</td>
					<td width="18%">
						 
						<s:textfield name="schedulemodel.scheduleName" maxlength="30" id="linkUrl"></s:textfield> 
						<s:hidden name="schedulemodel.id"></s:hidden>
						<s:hidden name="schedulemodel.addTime"></s:hidden>
						<s:hidden name="schedulemodel.isActive"></s:hidden>
					</td>
					<td width="10%" align="right">
						抓取类型:
					</td>
					<td width="44%">
						&nbsp;<s:select name="schedulemodel.scheduleType" id="scheduleType" onchange="scheduleTypeChange(this)" list="#{'1':'抓取链接','2':'抓取电影','3':'自动更新电视剧','4':'抓取子集embed'}" />
					</td>
					
					
				</tr>
				<tr id="serverTr">
					<td align="right">
						 &nbsp;抓取网站：
					</td>
					<td >&nbsp;
					<s:select   name="schedulemodel.source.id" list="#request.snatchServerList" listKey="id" 
              				listValue="name" headerKey="0" headerValue="--请选择抓取网站--"/>  
					</td>
					<td height="28" align="right">
						<p>
							视频类型
						</p>
					</td>
					<td >
						 <s:select name="schedulemodel.tvType"  list="#{'0':'电影','1':'电视剧','2':'单集电视剧'}" />
					</td>
					
				</tr>
				<tr id="linkTr">
					<td height="32" align="right">
						链接地址：
					</td>
					<td>
						<s:textfield name="schedulemodel.linkUrl" maxlength="200" id="linkUrl"></s:textfield>
					</td>
					<td align="right">
						解析正则表达式：
					</td>
					<td>
						<s:textfield name="schedulemodel.linkRegex" maxlength="200"></s:textfield>
						&nbsp;
					</td>
				</tr>
				<tr id="movieTr" style="display: none">
					<td height="35" align="right">
						共享地址正则表达式：
					</td>
					<td>
						<s:textfield name="schedulemodel.embedregex" maxlength="200" id="embedregex"></s:textfield>
					</td>
					<td align="right">
						详细页面正则表达式：
					</td>
					<td>
						<s:textfield name="schedulemodel.detailregex" maxlength="200" id="detailregex"></s:textfield>
					</td>
				</tr>
				<tr id="charsetTr"  style="display: none">
					<td align="right">
						编码格式：
					<td>
						<s:textfield name="schedulemodel.charset" maxlength="10" id="charset"></s:textfield>
					</td>
					<td align="right">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>					 
					<td colspan="4">
						<table border="0" align="center"  cellpadding="0" cellspacing="0" >
							<tr>
								<td  >
									<fieldset style="width:100">
										<legend style="font-size:14px;font-weight:bold;height:30px;padding-top:5px;padding-bottom:5px;">
											运行策略
										</legend>
										<nobr>
										<label><input type="radio" 
													<s:if test="schedulemodel.runStrategy==1">  
													 	checked
													</s:if>
											 name="schedulemodel.runStrategy" value="1"
											onClick="hideAllAutotaskRun()">一次性</label>
										<label>
										   <input type="radio" name="schedulemodel.runStrategy" value="2" 
											<s:if test="schedulemodel.runStrategy==2">  
													 	checked
													</s:if>
											onClick="showEveryDateRun()">每天</label>
										<label>
										 <input type="radio" name="schedulemodel.runStrategy" value="3" 
										     <s:if test="schedulemodel.runStrategy==3">  
													 	checked
													</s:if>
											onClick="showEveryWeekRun()">每周</label>
										<label>
										  <input type="radio" name="schedulemodel.runStrategy" value="4" 
											<s:if test="schedulemodel.runStrategy==4">  
													 	checked
													</s:if>
											onClick="showEveryMonthRun()">每月</label>
										<label>
										<input type="radio" name="schedulemodel.runStrategy" value="5" 
											<s:if test="schedulemodel.runStrategy==5">  
													 	checked
													</s:if>
											onClick="showEveryTime()">时分</label>										
									</fieldset>
								</td>
							</tr>
						</table>
					</td>	 
				</tr>
				<tr>
					<td colspan="4">
						<table border="0" cellpadding="0" cellspacing="0"   align="center" width="98%" style="margin-top:10px;margin-left: 10px;" >
							<tr id="autoRun_time" style="display: none">
								<td height="30" width="30%" align="right" class="bdbk_thdotted">
									执行时间：
								</td>
								<td width="250px" align="left" class="bdbk_thdotted">
									<input class="Form_textBox" style="width:120px;" type="text" maxlength="5" size="6" name="schedulemodel.autoRunTime" value="<s:property value="schedulemodel.autoRunTime"/>"></input>
								</td>
								<td align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 要在几点几分运行,格式 小时:分
								</td>
							</tr>
							 
							<tr id="autoRun_week2" style="display: none">	
							    <td height="30" align="right" class="bdbk_thdotted"></td>										
								<td height="30"  align="left" class="bdbk_thdotted">
									<input name="runWeek" type="checkbox" 
										<s:if test="%{schedulemodel.runWeek.indexOf('01')>=0}">  
										 	checked
										</s:if>										
									value="01" />
									星期一
									 
									<input name="runWeek" type="checkbox" 
										<s:if test="%{schedulemodel.runWeek.indexOf('02')>=0}">  
										 	checked
										</s:if>	
									value="02" />
									星期二
									<input name="runWeek" type="checkbox"
										<s:if test="%{schedulemodel.runWeek.indexOf('03')>=0}">  
										 	checked
										</s:if>	
									 value="03" />
									星期三
									 <br/>
									<input name="runWeek" type="checkbox"
										<s:if test="%{schedulemodel.runWeek.indexOf('04')>=0}">  
										 	checked
										</s:if>	
									 value="04" />
									星期四
									<input name="runWeek" type="checkbox" 
										<s:if test="%{schedulemodel.runWeek.indexOf('05')>=0}">  
										 	checked
										</s:if>	
									value="05" />
									星期五
									<input name="runWeek" type="checkbox" 
										<s:if test="%{schedulemodel.runWeek.indexOf('06')>=0}">  
										 	checked
										</s:if>	
									value="06" />
									星期六
									<br/>
									<input name="runWeek" type="checkbox" 
										<s:if test="%{schedulemodel.runWeek.indexOf('07')>=0}">  
										 	checked
										</s:if>	
									value="07" />
									星期日
									 
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 选择执行日期
								</td>
							</tr>
							<tr id="autoRun_month1" style="display: none">
								<td height="30" align="right" class="bdbk_thdotted">
									执行日期：
								</td>
								<td  align="left" class="bdbk_thdotted">
									<s:textfield   name="schedulemodel.runMonthDay"  size="3" maxlength="2" ></s:textfield> 号
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 选择几号运行
								</td>
							</tr>
							<tr id="autoRun_month2" style="display: none">	
							    <td height="30" align="right" class="bdbk_thdotted"></td>									
								<td height="30"  align="left" width="250px" class="bdbk_thdotted">
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('01')>=0}">  
										 	checked
										</s:if>	
									value="01">
									一月
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('02')>=0}">  
										 	checked
										</s:if>	
									value="02">
									二月
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('03')>=0}">  
										 	checked
										</s:if>	
									value="03">
									三月
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('04')>=0}">  
										 	checked
										</s:if>	
									value="04">
									四月
									<br/>
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('05')>=0}">  
										 	checked
										</s:if>	
									value="05">
									五月
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('06')>=0}">  
										 	checked
										</s:if>	
									value="06">
									六月
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('07')>=0}">  
										 	checked
										</s:if>	
									value="07">
									七月
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('08')>=0}">  
										 	checked
										</s:if>	
									value="08">
									八月
									<br/>
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('09')>=0}">  
										 	checked
										</s:if>	
									value="09">
									九月
									</input>
									<input name="runMonth"  type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('10')>=0}">  
										 	checked
										</s:if>	
									value="10">
									十月
									<input name="runMonth"  type="checkbox"
										<s:if test="%{schedulemodel.runMonth.indexOf('11')>=0}">  
										 	checked
										</s:if>	
									 value="11">
									十一月
									<br/>
									<input name="runMonth"   type="checkbox" 
										<s:if test="%{schedulemodel.runMonth.indexOf('12')>=0}">  
										 	checked
										</s:if>	
									value="12">
									十二月
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 选择月份
								</td>
							</tr>
							<tr id="autoRun_HM" style="display: none">
								<td height="30" align="right" class="bdbk_thdotted">
									执行周期：
								</td>
								<td  align="left" width="250px"  class="bdbk_thdotted">
									 
									<s:textfield   name="schedulemodel.runPeriodHour"  size="3" maxlength="2" ></s:textfield> 
									小时
									<s:textfield   name="schedulemodel.runPeriodMinute"  size="3" maxlength="2" ></s:textfield> 
									分钟
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 多久运行一次
								</td>
							</tr>
							<tr id="autoRun_dtStart" style="display: none">
								<td height="30" align="right" class="bdbk_thdotted">
									开始时间：
								</td>
								<td  align="left" class="">
									 
									<s:textfield   name="schedulemodel.startTime" cssStyle="width:120px;height: 22px"  onclick="new WdatePicker(this,'%Y-%M-%D %h:%m:%s',true)" size="3" maxlength="2" ></s:textfield> 
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 何时开始执行任务
								</td>
							</tr>
							<tr id="autoRun_stopTime" style="display: none; clear: both;">
								<td height="30" align="right" class="bdbk_thdotted">
									停止时间：
								</td>
								<td  align="left" class="bdbk_thdotted">
									<s:textfield   name="schedulemodel.stopTime" cssStyle="width:120px;height: 22px" onclick="new WdatePicker(this,'%Y-%M-%D %h:%m:%s',true)" size="3" maxlength="2" ></s:textfield> 
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 何时停止执行任务
								</td>
							</tr>
							 
						</table>	
					</td>	 
				</tr>
				<tr>
					<td height="40" align="right">
						&nbsp;
					</td>
					<td align="right">
						<input type="button" name="button2" class="btn"
							onclick="save();" value="确 定" />
						&nbsp;&nbsp;
					</td>
					<td align="left">
						&nbsp;&nbsp;&nbsp;
						<input type="button" name="button2" class="btn"
							onclick="returnBack()" value="返  回" />
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td   align="center" colspan="4">
						&nbsp;<font color="red"><div id="msgDiv"></div></font>
					</td>
					 
				</tr>
			</table>
		</s:form>
	</body>
</html>
 
