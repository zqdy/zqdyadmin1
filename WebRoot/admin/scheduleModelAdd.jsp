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
			 var options = { 
					//  target:'myForm1',          
			        beforeSubmit:validateForm,   
			        success:succesSubmit,  
			        url: "<%=request.getContextPath()%>/admin/addScheduleModel.action",
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
		<s:form id="modelForm" action="/admin/addScheduleModel.action">
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="4">
						<img src="<%=request.getContextPath()%>/images/t01.jpg" width="4"
							height="28" />
					</td>
					<td background="<%=request.getContextPath()%>/images/t02.jpg">
						<b>&nbsp;&nbsp;当前位置:</b>模型管理>>
						<font color="red">新增模型</font>
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
						<input type="text" name="schedulemodel.scheduleName" id="_parentsCode"
							  maxlength="30" />
						 
					</td>
					
					<td width="10%" align="right">
						抓取类型:
					</td>
					<td width="44%">
						&nbsp;<s:select name="schedulemodel.scheduleType" onchange="scheduleTypeChange(this)" list="#{'1':'抓取链接','2':'抓取详细','3':'自动更新电视剧','4':'抓取子集embed'}" />
					</td>
					
				
				</tr>
				<tr id="serverTr">
					<td align="right">
						 抓取网站：
					</td>
					<td>
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
						<s:textfield name="schedulemodel.charset" maxlength="10" value="UTF-8" id="charset"></s:textfield>
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
										<label><input type="radio" checked name="schedulemodel.runStrategy" value="1"
											onClick="hideAllAutotaskRun()">一次性</label>
										<label><input type="radio" name="schedulemodel.runStrategy" value="2" 
											onClick="showEveryDateRun()">每天</label>
										<label><input type="radio" name="schedulemodel.runStrategy" value="3" 
											onClick="showEveryWeekRun()">每周</label>
										<label><input type="radio" name="schedulemodel.runStrategy" value="4" 
											onClick="showEveryMonthRun()">每月</label>
										<label><input type="radio" name="schedulemodel.runStrategy" value="5" 
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
									<input class="Form_textBox" style="width:120px;" type="text" maxlength="5" size="6" name="schedulemodel.autoRunTime" value="12:00"></input>
								</td>
								<td align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 要在几点几分运行,格式 小时:分
								</td>
							</tr>
							 
							<tr id="autoRun_week2" style="display: none">	
							    <td height="30" align="right" class="bdbk_thdotted"></td>										
								<td height="30"  align="left" class="bdbk_thdotted">
									<input name="runWeek" type="checkbox" value="01">
									星期一
									</input>
									<input name="runWeek" type="checkbox" value="02">
									星期二
									</input>
									<input name="runWeek" type="checkbox" value="03">
									星期三
									</input></br>
									<input name="runWeek" type="checkbox" value="04">
									星期四
									</input>												
									<input name="runWeek" type="checkbox" value="05">
									星期五
									</input>
									<input name="runWeek" type="checkbox" value="06">
									星期六
									</input></br>
									<input name="runWeek" type="checkbox" value="07">
									星期日
									</input>
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
									<input class="Form_textBox" style="width:40px;" size="3" maxlength="2" type="text" name="schedulemodel.runMonthDay" value="1" />号
								</td>
								<td  align="left" class="bdbk_thdotted">
									<img border="0" src="../images/must_icon.gif"> 选择几号运行
								</td>
							</tr>
							<tr id="autoRun_month2" style="display: none">	
							    <td height="30" align="right" class="bdbk_thdotted"></td>									
								<td height="30"  align="left" width="250px" class="bdbk_thdotted">
									<input name="runMonth"  type="checkbox" value="01">
									一月
									</input>
									<input name="runMonth"  type="checkbox" value="02">
									二月
									</input>
									<input name="runMonth"  type="checkbox" value="03">
									三月
									</input>
									<input name="runMonth"  type="checkbox" value="04">
									四月
									</input></br>
									<input name="runMonth"  type="checkbox" value="05">
									五月
									</input>												
									<input name="runMonth"  type="checkbox" value="06">
									六月
									</input>
									<input name="runMonth"  type="checkbox" value="07">
									七月
									</input>
									<input name="runMonth"  type="checkbox" value="08">
									八月
									</input></br>
									<input name="runMonth"  type="checkbox" value="09">
									九月
									</input>
									<input name="runMonth"  type="checkbox" value="10">
									十月
									</input>
									<input name="runMonth"  type="checkbox" value="11">
									十一月
									</input></br>
									<input name="runMonth"   type="checkbox" value="12">
									十二月
									</input>
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
									<input class="Form_textBox" style="width:40px;" type="text" maxlength="2" size="2"
										type="text" name="schedulemodel.runPeriodHour" value="1" />
									小时
									<input class="Form_textBox" style="width:40px;" type="text" maxlength="2" size="2"
										type="text" name="schedulemodel.runPeriodMinute" value="00" />
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
									<input  style="width:120px;height: 22px" type="text" name="schedulemodel.startTime"   onclick="new WdatePicker(this,'%Y-%M-%D %h:%m:%s',true)" />
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
									<input style="width:120px;height: 22px" type="text" name="schedulemodel.stopTime"  onclick="new WdatePicker(this,'%Y-%M-%D %h:%m:%s',true)" />
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
 
