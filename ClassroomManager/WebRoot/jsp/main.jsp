<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/dynamic/link/base.jsp"></jsp:include>
<html>
<head>
<base href="<%=basePath%>">
<style type="text/css">
a:link {
	text-decoration: none;
	color: black;
} /* 未被访问的链接 */
a:visited {
	text-decoration: none;
	color: black;
} /* 已被访问的链接 */
a:hover {
	text-decoration: none;
	color: red;
} /* 鼠标指针移动到链接上 */
a:active {
	text-decoration: none;
	color: red;
} /* 正在被点击的链接 */
</style>

<title>教室在线租借系统</title>
<script type="text/javascript">
$(function(){
		// 数据
		var treeData=[{
			text:"教室在线租借系统",
			children:[{
				text:"教室申请",
				attributes:{
					url:"<%=path%>/dynamic/server/dictionary/page.jsp"
				}
			},{
				text:"教室审核",
				attributes:{
					url:"<%=path%>/dynamic/server/goods/page.jsp"
				}
			},{
				text:"教室管理",
				children:[{
				text:"校区信息维护",
				attributes:{
					url:"${pageContext.request.contextPath }/xiaoqu/xiaoqupage.jsp"
				}
				
				},{
				text:"教室信息维护",
				attributes:{
					url:"<%=path%>/room/show.jsp"
				}
				
				}],
				attributes:{
					url:"<%=path%>/dynamic/server/jinhuo/page.jsp"
				}
			},{
				text:"用户信息管理",
				attributes:{
					url:"<%=path%>/dynamic/server/chuhuo/page.jsp"
				}
			},{
				text:"租借信息查看",
				attributes:{
					url:"<%=path%>/dynamic/server/kucun/page.jsp"
				}
			},{
				text:"审核信息查看",
				attributes:{
					url:"<%=path%>/dynamic/server/user/page.jsp"
				}
					} ]
		} ];

		// 实例化树菜单
		$("#tree").tree({
			data : treeData,
			lines : true,
			onClick : function(node) {
				if (node.attributes) {
					openTab(node.text, node.attributes.url);
				}
			}
		});

		// 新增Tab
		function openTab(text, url) {
			if ($("#tabs").tabs('exists', text)) {
				$("#tabs").tabs('select', text);
			} else {
				var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="
						+ url + "></iframe>";
				$("#tabs").tabs('add', {
					title : text,
					closable : true,
					content : content
				});
			}
		}
	});

	function logout() {
		var f = confirm('确定要注销登录?');
		if (!f)
			return false;
		top.location.href = '${pageContext.request.contextPath }/classroom/logout.action';
	}
</script>

</head>

<body class="easyui-layout">
	<div region="north" style="height:100px">
		<div align="left" style="width: 80%;float: left">
			<img src="<%=basePath%>/statics/skin/server/images/frame/main.jpg">
		</div>
		<div style="padding-top: 50px;padding-right: 20px;">
			当前用户：&nbsp;<font color="red">${currentUser.username }</font>
			&nbsp;&nbsp;<a href="#" onclick="logout();return false;"><font
				color="red">注销</font></a>
		</div>
	</div>
	<div title="菜单导航" region="west" split="true" style="width:150px">
		<ul id="tree"></ul>
	</div>
	<div title="主面板" region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" >
				<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用</font></div>
			</div>
		</div>
	</div>
</body>
</html>
