<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'page.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<jsp:include page="/dynamic/link/checkLogin.jsp"></jsp:include>
	<jsp:include page="/dynamic/link/base.jsp"></jsp:include>

  </head>
  
<body style="margin: 5px;">
  	<table id="dg" title="库存信息列表" class="easyui-datagrid" fitColumns="true"
  		pagination="true" rownumbers="true" url="<%=basePath%>/server/kucun.action" fit="true" toolbar="#tb">
  			<thead>
  				<tr>
  					<th field="cb" checkbox="true"></th>
  					<th field="id" width="10" align="center">编号</th>
  					<th field="goodsid" width="25" align="center" hidden="true">商品ID</th>
  					<th field="goodsname" width="25" align="center">商品名称</th>
  					<th field="inventory" width="30" align="center">库存量</th>
  					<th field="endDate" width="25" align="center">日期</th>
  				</tr>
  			</thead>
  	</table>
  </body>
</html>
