<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抓取详细页</title>
<link href="pub/style/unicrm.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.form.js"></script>			
<script language="javascript">
	var options = { 
		//  target:'myForm1',          
        //beforeSubmit:validateForm,   
        success:succesSubmit,  
        url: "<%=request.getContextPath()%>/admin/snatchDetail.action",
        type:"post" 
    }; 
	
	function validateForm(){
		var snatchServer = $("#snatchServer").val();
		var url =  $("#url").val();
		var regex =$("#regex").val(); 
		if(snatchServer=='0'){
			alert('请选择抓取网站');
			return false;
		}
		 
		$('#subButton').attr("disabled","disabled");
		$('#subButton').val("正中抓取...");
		return true;
	}	
	function succesSubmit(responseText, statusText){
		$("#msgDiv").html(responseText);		 
		$('#subButton').removeAttr("disabled");
		$('#subButton').val("开始抓取");
	}
	function startSnatch(){
		  $('#myForm1').ajaxSubmit(options); 
	}
</script>
</head>

<body>
	<form action="<%=request.getContextPath()%>/admin/snatchDetail.action" name="myForm1" id="myForm1">
	   <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td align="right"> 抓取网站：</td>
              <td>
              	<s:select   name="snatchServer" list="#request.snatchServerList" listKey="id" 
              	listValue="name" headerKey="0" headerValue="--请选择抓取网站--"/>  
              	
              </td>
              <td></td>
            </tr>
             <tr>
              <td align="right">视频类型：</td>
              <td>
              	<s:select name="tvType"  list="#{'0':'电影','1':'电视剧','2':'单集电视剧'}" />
              </td>
              <td></td>
            </tr>
            <tr>
              <td align="right">解析共享地址正则表达式：</td>
              <td>
              	<input type="text" name="regexEmbed">
              </td>
              <td></td>
            </tr>
            <tr>
              <td align="right"> 详细页面地址正则表达式：</td>
              <td>
              	<input type="text" name="regexDetail">
              </td>
              <td></td>
            </tr>
             <tr>
              <td align="right">字符编码：</td>
              <td>
              	<input type="text" name="chartSet">
              </td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td>
              	<input type="button" onclick="startSnatch()" id="subButton" value="开始抓取" />
              </td>
              <td></td>
            </tr>
             <tr>
              <td></td>
              <td><br/>
              		<font color="red"><div id="msgDiv"></div></font>
              </td>
              <td></td>
            </tr>
        </table>
    </form>
</body>
</html>
