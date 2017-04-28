<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进销存系统管理平台</title>

<style type="text/css">
<!-- 
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE3 {
	color: #528311;
	font-size:12px;
}

.STYLE5 {
	color: red;
	font-size: 12px;
}

.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>

<!-- JavaScript -->
<script type="text/javascript" src="<%=basePath%>/statics/plugin/jquery/core.js"></script>

<script type="text/javascript">

	var loading = false;
	
	function loginForm() {
		var username = $("#username"); //#此处代表id
		var password = $("#password");
		var imageCode = $("#imageCode");
		
		if($.trim(username.val()) == "") {
			alert("用户名不能为空！");
			username.val("");
			username.focus();
			return false;
		}
		
		if($.trim(password.val()) == "") {
			alert("密码不能为空！");
			password.val("");
			password.focus();
			return false;
		}
		
		if($.trim(imageCode.val()) == ""){
			alert("验证码不能为空！");
			imageCode.val("");
			imageCode.focus();
			return false;
		}
		
		if(loading) {
			return false;
		}
		loading = true;
		$("#submitForm").submit();
	}
	
	function resetForm() {
		document.getElementById("username").value="";
		document.getElementById("password").value="";
		document.getElementById("imageCode").value="";
	}
	
	function loadimage(){
		document.getElementById("randImage").src = "<%=basePath%>/dynamic/server/view/image.jsp?"+Math.random();
	}
</script>

</head>

<body>
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td bgcolor="#e5f6cf">&nbsp;</td>
		</tr>
		<tr>
			<td height="608"
				background="<%=basePath%>/statics/skin/server/images/login/login_03.gif"><table
					width="862" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="266"
							background="<%=basePath%>/statics/skin/server/images/login/login_04.gif">&nbsp;</td>
					</tr>
					<tr>
						<td height="95">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<form action="<%=basePath%>/server/index.action" method="post" id="submitForm">
								<tr>
									<td width="424" height="95"
										background="<%=basePath%>/statics/skin/server/images/login/login_06.gif">&nbsp;</td>
									<td width="183"
										background="<%=basePath%>/statics/skin/server/images/login/login_07.gif">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="21%" height="18"><div align="center">
														<span class="STYLE3">用&nbsp;&nbsp;户</span>
													</div></td>
												<td width="79%" height="18"><input type="text"
													name="user.username" id="username"
													style="height: 15px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;">
												</td>
											</tr>
											<tr>
												<td height="18" style="height: 18px;"><div
														align="center">
														<span class="STYLE3">密&nbsp;&nbsp;码</span>
													</div></td>
												<td height="18"><input type="password" name="user.password"
													id="password"
													style="height: 15px; width: 130px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432;">
												</td>
											</tr>
											<tr>
												<td height="18" style="height: 18px;"><div
														align="center">
														<span class="STYLE3">验证码 </span>
													</div></td>
												<td height="18"><input type="text" value="${imageCode }" name="imageCode" id="imageCode"
													style="height: 15px; width: 60px; border: solid 1px #cadcb2; font-size: 12px; color: #81b432; padding-top: 1px;">&nbsp;
													<img onclick="javascript:loadimage();" title="换一张试试" name="randImage" id="randImage" src="<%=basePath%>/dynamic/server/view/image.jsp"
													style="height: 18px; width: 51px; text-align: center; margin-bottom: 0px;">
												</td>
											</tr>
											<tr>
												<td height="22">&nbsp;</td>
												<td height="22" style="height: 22px; padding-top: 1px;">
													<img src="<%=basePath%>/statics/skin/server/images/login/dl.gif"
													onclick="loginForm();" width="40" height="22" border="0">&nbsp;
													<img src="<%=basePath%>/statics/skin/server/images/login/cz.gif"
													onclick="resetForm();" width="40" height="22" border="0">
													</a>
												</td>
											</tr>
										</table>
								</td>
								<td width="255"
									background="<%=basePath%>/statics/skin/server/images/login/login_08.gif">&nbsp;</td>
								</tr>
								</form>
							</table>
						</td>
					</tr>
					<tr>
						<td height="247" valign="top"
							background="<%=basePath%>/statics/skin/server/images/login/login_09.gif"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="22%" height="30">&nbsp;</td>
									<td width="56%">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="57%"></td>
												<td algin="left"><span class="STYLE5" >${error} </span>
												</td>
											</tr>
										</table>
									</td>
									<td width="22%">&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td height="30"><table width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td width="44%" height="20">&nbsp;</td>
												<td width="56%" class="STYLE4">版本 2008V1.0</td>
											</tr>
										</table>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="#a2d962">&nbsp;</td>
		</tr>
	</table>
</body>
</html>