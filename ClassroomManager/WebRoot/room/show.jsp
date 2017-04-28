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

<title>My JSP 'show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<jsp:include page="/dynamic/link/base.jsp"></jsp:include>
<script type="text/javascript">
	var url;
	function openRoomAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加教室信息");
		url = "${pageContext.request.contextPath}/classroom/saveRoom.action";
	}
	function openRoomModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle", "修改教室信息");
		var row = selectedRows[0];
		$("#roomtype").val(row.roomtype);
		$("#roomnum").val(row.roomnum);
		$("xiaoquid").val(row.xiaoquid);
		url = "${pageContext.request.contextPath}/classroom/updateRoom.action";

	}

	function saveRoom() {
		$("#fm").form("submit", {
			//url为要提交到的那个requestmap
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function() {
				$.messager.alert("系统提示", "保存成功");
				resetValue();
				$("#dlg").dialog("close");
				//重新加载表格信息
				$("#dg").datagrid("reload");
			}
		});
	}

	function deleteRoom() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据!");
			return;
		}
		var strIds=[];
		for(var i=0; i<selectedRows.length; i++){
				strIds.push(selectedRows[i].id);
			}
			var ids = strIds.join(",");
			$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</front>条数据吗？",function(r){
				if(r){ 
					$.post("${pageContext.request.contextPath}/classroom/deleteRoom.action",{delIds:ids},function(result){
							$.messager.alert("系统提示","您已成功删除<font color=red>"+strIds.length+"</font>条数据！");
							$("#dg").datagrid("reload");
					},"json");
				}
			});
	}
	function closeRoomDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	function resetValue() {
		$("#roomnum").val("");
		$("#roomtype").val("");
		$("#xiaoquid").val("");
	}
</script>



</head>

<body>
	<table id="dg" title="教室信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true"
		url="${pageContext.request.contextPath }/classroom/roomList.action"
		fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="10" align="center">id</th>
				<th field="roomnum" width="20" align="center">教室编号</th>
				<th field="xiaoquid" width="20" align="center">所在校区id</th>
				<th field="roomtype" width="20" align="center">教室类型</th>
				<th field="createTime" width="20" align="center">创建时间</th>
				<th field="updateTime" width="20" align="center">修改时间</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openRoomAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openRoomModifyDialog()" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteRoom()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 600px; height: 280px;padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>教室类型：</td>
					<td><input name="roomtype" id="cost" class="easyui-combobox"
						required="true"
						data-options="panelHeight:'auto',editable:false,valueField:'roomtype',textField:'roomtype',url:'${pageContext.request.contextPath }/classroom/roomtypeList.action' " /></td>
				</tr>
				<tr>
					<td>教室门牌号：</td>
					<td><input type="text" name="roomnum" id="roomnum"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>所在校区：</td>
					<td><input name="xiaoquid" id="xiaoquid"
						class="easyui-combobox"
						data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'xiaoquaddr',url:'${pageContext.request.contextPath }/classroom/xiaoquList.action'" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveRoom()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeRoomDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
