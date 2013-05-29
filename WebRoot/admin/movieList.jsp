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
		  <style type="text/css">
		    #greybackground 
		    {
		    background:#5E5E5E;
		    display:block;
		    z-index:100;
		    width:100%;
		    position:absolute;
		    top:0;
		    left:0;
		    }
		    .pop-box {  
		        z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/  
		        margin-bottom: 3px;  
		        display: none;  
		        position: absolute;  
		        background: #FFF;  
		        border:solid 1px #6e8bde;  
		    }  
		      
		    .divShouTesth4 {  
		        color: #FFF;  
		        cursor:default;  
		        height: 18px;  
		        font-size: 14px;  
		        font-weight:bold;  
		        text-align: left;  
		        padding-left: 8px;  
		        padding-top: 4px;  
		        padding-bottom: 2px;
		        background:#526DBE; 
		        margin-top:0px;   
		    }  
		      
		    .pop-box-body {  
		        clear: both;  
		        margin: 4px;  
		        padding: 2px;  
		    }  
    	</style>
		<script language="javascript">	
			function editMovie(movieId){
				window.location.href="<%=request.getContextPath()%>/admin/goEditMovie.action?movieId="+movieId;
			} 
			function addMovie(){
				window.location.href="<%=request.getContextPath()%>/admin/goAddMovie.action";
			}
			
			function addHotMovie(){
				var code_Values = $("input[name='mId'][checked]");//	  document.all['mId']; 
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids = ids + code_Values[i].value+',';
						} 
					} 					 
					 
					var url ="<%=request.getContextPath()%>/admin/hotRecommMovie.action?t="+Math.random();	
					$.get(url,{"hotIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电影");
				}
			}
			 function delHotMovie(){
				var code_Values = $("input[name='mId'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/unHotRecommMovie.action?t="+Math.random();		
					$.get(url,{"hotIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电影");
				}
			}
			
			function addRecommMovie(){
				var code_Values = $("input[name='mId'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/hotRecommMovie.action?t="+Math.random();		
					$.get(url,{"recommIds":ids},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电影");
				}
			}
			 function delRecommMovie(){
			 	var typeId = $("#recommMovieTypeId").val();
	        	if(typeId=='0'){
	        		alert("请选择所属分类查询");
	        		return false;
	        	}
				var code_Values = $("input[name='mId'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/unRecommMovie.action?t="+Math.random();		
					$.get(url,{"mvIds":ids,"typeId":typeId},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电影");
				}
			}
		function deleteShowMovieTypeDiv(){
			//deleteRecommMovie
			var mIds = $("input[name='mId'][checked]");
			var typeId = $("#recommMovieTypeId").val();
			if(typeId==0){
				alert('请选择所属分类');
				return;
			} 
			var ids=''; 
			if(mIds.length>0){ 
				for(var i=0;i<mIds.length;i++) 
				{ 
					if(mIds[i].checked){
						ids += mIds[i].value+',';
					} 
				} 
				var url ="<%=request.getContextPath()%>/admin/deleteRecommMovie.action?t="+Math.random();		
				$.get(url,{"movieIds":ids,"typId":typeId},function(data){
					 alert(data); 
				});		
			}else{ 
					alert("请选择要操作的电影");
			}
		}	
			
		function ShowMovieTypeDiv(mvoieId) {
            var windowWidth = 400;
            var windowHeight = 500;
            var div_obj = $("#divShouTest");
            var popupHeight = div_obj.height();
            var popupWidth = div_obj.width();
            //添加并显示遮罩层
            var docheight = $(document).height();
            $("body").append("<div id='greybackground'></div>");
            $("#greybackground").css({ "opacity": "0.5", "height": docheight });
            $("#greybackground").click(function() { HideDiv('divShouTest'); })

            $('#divShouTest').animate({ left: 60,
                top: 60, opacity: "show"
            }, "slow");
            $("#recoMovieId").val(mvoieId);
        }
        function HideDiv(div_id) {
        	$("#recoMovieId").val('');
            $("#greybackground").remove();
            $("#" + div_id).animate({ left: 0, top: 0, opacity: "hide" }, "slow");
            
            var code_Values = $("input[name='movieType'][checked]");
			 
			if(code_Values.length>0){ 
				for(var i=0;i<code_Values.length;i++) 
				{ 
					if(code_Values[i].checked){
						 code_Values[i].checked = false;
					} 
				} 
				 
			} 
        }  
        
        function submitMovieType(){
        	var mvoieId = $("#recoMovieId").val();
        	var shortSummary = $("#shortSummaryId").val(); 
        	var code_Values = $("input[name='movieType'][checked]");
				var ids=''; 
				if(code_Values.length>0){ 
					for(var i=0;i<code_Values.length;i++) 
					{ 
						if(code_Values[i].checked){
							ids += code_Values[i].value+',';
						} 
					} 
					var url ="<%=request.getContextPath()%>/admin/recommMovie.action?t="+Math.random();		
					$.get(url,{"recommIds":ids,"movieId":mvoieId,"shortSummary":shortSummary},function(data){
						 alert(data); 
					});		
				}else{ 
					alert("请选择要操作的电影");
				}
        }
         
		</script>
		
	</head>

	<body>
		 
		<s:form id="movieForm" action="/admin/movieList.action">
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="4">
						<img src="<%=request.getContextPath()%>/images/t01.jpg" width="4"
							height="28" />
					</td>
					<td background="<%=request.getContextPath()%>/images/t02.jpg">
						<b>&nbsp;&nbsp;当前位置:</b>后台管理>>
						<font color="red">电影列表</font>
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
					<td width="5%" align="right">
						<span class="body_title">电影名称：</span>
					</td>
					<td width="8%" align="center">
						<s:textfield name="movieName" size="20"></s:textfield>
					</td>
					<td width="17%" align="left">
						热门：<input name="hotMovie" type="checkbox" value="1">
						推荐：<input name="commMovie" type="checkbox" value="1">
					</td>
					<td>
						所属网站：<s:select   name="sourceServer" list="#request.snatchServerList" listKey="id" 
              				listValue="name" headerKey="0" headerValue="--请选择抓取网站--"/>  
              			所属分类：<s:select   name="recommMovieType" id="recommMovieTypeId" list="#request.movieTypeList" listKey="id" 
              				listValue="name" headerKey="0" headerValue="--请选择推荐分类--"/>  
					</td>
					 
					<td width="8%">
						<input type="submit" name="button" class="btn" value="查 询" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="addMovie();" value="新 增" />&nbsp;
							<!-- <input type="button" name="button2" class="btn"
								onClick="addHotMovie()" value="热门" />&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="delHotMovie()" value="取消热门" />&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="addRecommMovie()" value="推荐" />&nbsp;
							<input type="button" name="button2" class="btn"
								onClick="delRecommMovie()" value="取消推荐" />&nbsp; -->
							<input type="button" name="button2" class="btn"
								onClick="deleteShowMovieTypeDiv()" value="取消推荐" />&nbsp; 
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
						<input  type="checkbox" onclick="selectAll(this)">
					</th>
					<th     class="body_title">
						图片
					</th>
					<th  class="body_title">
						电影名称
					</th>	
					<th  class="body_title">
						简介
					</th>					
					<th class="body_title">
						年代
					</th>
					<th    class="body_title">
						地区 
					</th>
					<th    class="body_title">
						来源
					</th>
					 
					<th   class="body_title">
						入库时间
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
								<img src="<s:property value="%{getText('zqdy.imageUrl')}"/><s:property value="imageUrl" />" width="60px" height="90px">
						</td>
						<td align="center" class="body_td">
							<s:property value="name" />&nbsp;
						</td>
						<td align="center" class="body_td">
							<s:property value="shortSummary" />&nbsp;
						</td>
						
						<td align="center" class="body_td">
							<s:property value="year" /> &nbsp;
						</td>
						<td height="28" align="center" class="body_td">
							<s:iterator value="areaList">
								<s:property value="area.name" />/
							</s:iterator>
							&nbsp;
						</td>
						<td height="28" align="center" class="body_td">
							<s:iterator value="embedList">
								<s:property value="source.name" />/
								<s:if test="isMember==1">(收费)</s:if>
							</s:iterator>
							&nbsp;
						</td>
						<td align="center" class="body_td">							 
							<s:property value="addTime" /> 
							&nbsp;
						</td>
						
						 
						<td align="center" class="body_td">
								<a href="#" onclick="ShowMovieTypeDiv(<s:property value="id" />)">推荐</a> 
								
							&nbsp;<a href="#" onclick="editMovie(<s:property value="id" />)">编辑</a>
							&nbsp;<a target="_blank" href="<s:property value="%{getText('zqdy.mainUrl')}"/>/m_<s:property value="id" />.html" >前台查看</a>
							 
						</td>
						 
					</tr>
				</s:iterator>
				<tr align="center">
					<td colspan="7" class="body_td">
						<tangs:pages cpage="${cpage}" total="${total}"
							formId="movieForm" />
					</td>
				</tr>
			</table>
			 
		</s:form>
		
	 <div id='divShouTest' style="width: 900px" class="pop-box" >  
        <h4 class="divShouTesth4">请选择要推荐的类型</h4>  
        <div class="pop-box-body" >  
            <p>  
            	<input type="hidden" name="recoMovieId" id="recoMovieId">
               	<s:iterator value="movieTypeList" status="movieTypeSt">
               		<input name="movieType" type="checkbox" value="<s:property value="id" />"><s:property value="name" />(<s:property value="id" />)
               		&nbsp;&nbsp;&nbsp;
               		<s:if test="(#movieTypeSt.index+1)%3==0&&#movieTypeSt.index!=0">
						 	 <br/>
					</s:if>
               		
               	</s:iterator>	 
            </p>  
        </div> 
        <div>
        	<textarea rows="5" cols="100" id="shortSummaryId"></textarea>
        </div> 
        <div style="text-align: right">  
        	<input value="确定" id="btn1"  onclick="submitMovieType()"  type="button"  />
            <input value="关闭" id="btn1"  onclick="HideDiv('divShouTest');"  type="button"  />  
        </div>  
    </div>  
	</body>
</html>
