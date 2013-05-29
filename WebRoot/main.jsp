<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="pub/style/unicrm.css" rel="stylesheet" type="text/css">
<script language="javascript">

function switchBar(){
 
 if (parent.myframe.cols=="0,8,*"){
 
  parent.myframe.cols="200,8,*"
  document.getElementById("arrow").src="images/top_012.gif" ;
  //document.getElementById("shleft").value="隐藏左部";
 }
 else{
 
  parent.myframe.cols="0,8,*"  
  document.getElementById("arrow").src="images/top_013.gif" ;
  //document.getElementById("shleft").value="显示左部"
 }
}

function inintSwitchBar(){
   parent.myframe.cols="0,*"  
  document.getElementById("arrow").src="images/top_013.gif" ;
  //document.getElementById("shleft").value="显示左部"
  if (parent.myframe.cols=="0,*"){
  parent.myframe.cols="200,*,"
  document.getElementById("arrow").src="images/top_012.gif" ;
  //document.getElementById("shleft").value="隐藏左部";
 }
 
}

</script>
</head>

<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4"><img src="images/t01.jpg" width="4" height="28" /></td>
              <td background="images/t02.jpg">&nbsp;</td>
              <td width="4"><img src="images/t03.jpg" width="4" height="28" /></td>
            </tr>
        </table>
</body>
</html>
