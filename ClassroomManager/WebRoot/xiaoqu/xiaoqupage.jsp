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

<title>My JSP 'xiaoqupage.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="/dynamic/link/checkLogin.jsp"></jsp:include>
<jsp:include page="/dynamic/link/base.jsp"></jsp:include>
<script type="text/javascript">
	function openXiaoquAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加校区信息");
		url = "${pageContext.request.contextPath}/classroom/saveXiaoqu.action";
	}

	function openXiaoquModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑校区信息");
		$("#xiaoquaddr").val(row.xiaoquaddr);
		$("#studentcount").val(row.studentcount);
		url = "${pageContext.request.contextPath}/classroom/updateXiaoqu.action?id="
				+ row.id;
	}

	function resetValue() {
		$("#xiaoquaddr").val("");
		$("#studentcount").val("");
		$("#createdate").val("");
		$("#updatedate").val("");
	}

	function saveXiaoqu() {
		$("#fm").form("submit", {
			//url为要提交到的那个requestmap
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					resetValue();
					$("#dlg").dialog("close");
					//重新加载表格信息
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	
	
	function deleteXiaoqu(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			var strIds=[];
			for(var i=0; i<selectedRows.length; i++){
				strIds.push(selectedRows[i].id);
			}
			var ids = strIds.join(",");
			$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</front>条数据吗？",function(r){
				if(r){ 
					$.post("${pageContext.request.contextPath}/classroom/deleteXiaoqu.action",{delIds:ids},function(result){
							$.messager.alert("系统提示","您已成功删除<font color=red>"+strIds.length+"</font>条数据！");
							$("#dg").datagrid("reload");
					},"json");
				}
			});
			
		}
		
		

	function closeXiaoquDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
</script>
</head>

<body>
<!-- 中间显示信息的表格 -->
	<table id="dg" title="校区信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true"
		url="${pageContext.request.contextPath }/classroom/xiaoquList.action"
		fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="10" align="center">校区id</th>
				<th field="xiaoquaddr" width="20" align="center">校区地址</th>
				<th field="studentcount" width="20" align="center">校区人数</th>
				<th field="createtime" width="20" align="center">创建时间</th>
				<th field="updatetime" width="20" align="center">修改时间</th>
			</tr>
		</thead>
	</table>
	<!-- 上面显tab，三个按钮，增加、删除、修改 -->
	<div id="tb">
		<div>
			<a href="javascript:openXiaoquAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openXiaoquModifyDialog()" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteXiaoqu()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<!-- 搜索的那个div -->
		<div>
			&nbsp;校区人数：&nbsp;<input class="easyui-combobox" name="studentcount"
				id="studentcount1"
				data-options="panelHeight:'auto',editable:true,valueField:'value',textField:'value',url:'<%=basePath%>/server/dictionary!dictionaryComboList?name=1'" />
			<a href="javascript:searchXiaoqu()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>


	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px;padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>校区地址：</td>
					<td><input type="text" name="xiaoquaddr" id="xiaoquaddr"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>校区人数：</td>
					<td><input type="text" name="studentcount" id="studentcount" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>


	<div id="dlg-buttons">
		<a href="javascript:saveXiaoqu()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeXiaoquDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
