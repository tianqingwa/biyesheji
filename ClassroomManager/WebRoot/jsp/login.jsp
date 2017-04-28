<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录</title>

</head>

<body>
	<form action="${pageContext.request.contextPath }/classroom/login.action" method="post">
		<table align="center">
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td><input type="radio" name="role" value="1">管理员</td>
				<td><input type="radio" name="role" value="2" checked>学生</td>
			</tr>
			<tr>
				<td><input type="submit" value="登录"></td>
				<td><input type="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>
