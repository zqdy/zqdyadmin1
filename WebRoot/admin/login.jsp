<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>登录页面</title>

		<script language="javascript" type="text/javascript">
		  	function login(){
					var form = document.forms[0];
					var userCode = document.getElementById("_userCode").value;
					
					var password = document.getElementById("_password").value;
					if(userCode==""){
						alert("用户名不能为空！");
						return;
					}
					if(password==""){
						alert("密码不能为空！");
						return ;
					}
					//form.action = "login.action";				 
					form.submit();
				}	
				
	         
		</script>

	</head>

	<body>

		<form action="login.action" method="post">

			<div id="mainDiv">
				<table width="100%" height="100%" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td valign="middle">
							<table width="100%" border="0" cellspacing="10" cellpadding="0">
								<tr>
									<td width="57%" align="center">
										<table height="260" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<img
														src="<%=request.getContextPath()%>/images/login_01.jpg"
														width="390" height="64" border="0" />
												</td>
											</tr>
											<tr>
												<td height="200" align="center"
													background="<%=request.getContextPath()%>/images/login_02.jpg">
													<table border="0" align="center" cellpadding="0"
														cellspacing="0">														
														<tr>
															<td>
																<table width="100%" border="0" align="center"
																	cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="70" height="31" align="right" class="T141">
																			用户名：
																		</td>
																		<td rowspan="2">
																			<table width="80%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td height="31">
																						<input name="userCode" type="text" class="input_css" id="_userCode" size="18" />
																					</td>
																				</tr>
																				<tr>
																					<td height="31">
																						<input name="password" type="password"
																							class="input_css" id="_password"
																							onKeyPress="if(event.keyCode==13){login();}"
																							size="18" />
																					</td>
																				</tr>
																			</table>
																		</td>
																		<td width="100" rowspan="2" align="right">
																			<a href="javascript:login()"><img
																					src="<%=request.getContextPath()%>/images/dr_07.jpg"
																					width="87" height="62" border="0" /></a>
																		</td>
																	</tr>
																	<tr>
																		<td height="31" align="right" class="T141">
																			密&nbsp;&nbsp;码：
																		</td>
																	</tr>
																	 
																</table>
															</td>
														</tr>
														<tr>
															<td height="35" align="right">
																&nbsp;

															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
