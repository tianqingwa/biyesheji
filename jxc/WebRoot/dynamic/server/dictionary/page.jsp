<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>供应商信息管理</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<jsp:include page="/dynamic/link/checkLogin.jsp"></jsp:include>
	<jsp:include page="/dynamic/link/base.jsp"></jsp:include>
	
	<script type="text/javascript">
		var url;
		
		function searchDictionary(){
			$('#dg').datagrid('load',{
				s_name:$('#s_name').combobox("getValue"),
				s_value:$('#s_value').val()
			});
		}
		
		function deleteDictionary(){
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
					$.post("<%=basePath%>/server/dictionary!delete",{delIds:ids},function(result){
						if(result.success){
							$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].goodsname+'</font>'+result.errorMsg);
						}
					},"json");
				}
			});
		}
		
		function openDictionaryAddDialog(){
			$("#dlg").dialog("open").dialog("setTitle","添加数据字典信息");
			url="<%=basePath%>/server/dictionary!save";
		}
		
		function openDictionaryModifyDialog(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑数据字典信息");
			$("#name").val(row.name);
			$("#value").val(row.value);
			url="<%=basePath%>/server/dictionary!save?id="+row.id;
		}
		
		function closeDictionaryDialog(){
			$("#dlg").dialog("close");
			resetValue();
		}
		
		function resetValue(){
			$("#name").combobox("setValue","");
			$("#value").val("");
		}
		
		function saveDictionary(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
					if($('#name').combobox("getValue")==""){
						$.messager.alert("系统提示","请选择数据字典名称");
						return false;
					}
					return $(this).form("validate");
				},
				success:function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
						return;
					}else{
						$.messager.alert("系统提示","保存成功");
						resetValue();
						$("#dlg").dialog("close");
						$("#dg").datagrid("reload");
					}
				}
			});
		}
	</script>
	
  </head>
  
  <body style="margin: 5px;">
  	<table id="dg" title="供应商信息" class="easyui-datagrid"  fitColumns="true" 
  		pagination="true" rownumbers="true" url="<%=basePath%>/server/dictionary.action" fit="true" toolbar="#tb">
  			<thead>
  				<tr>
  					<th field="cb" checkbox="true"></th>
					<th field="id" width="10" align="center">编号</th>
					<th field="name" width="20" align="center">类型</th>
					<th field="value" width="20" align="center">供应商名称</th>
					<th field="remark" width="20" align="center">备注</th>
					<th field="tel" width="20" align="center">电话</th>
  				</tr>
  			</thead>
  	</table>
  			
	<div id="tb">
		<div>
			<a href="javascript:openDictionaryAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openDictionaryModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteDictionary()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;名称 ：&nbsp;<select class="easyui-combobox" id="s_name" name="s_name" editable="false" panelHeight="auto">
		    	<option value="">请选择...</option>
				<option value="供应商">供应商</option>
				<option value="商品类型">商品类型</option>
				</select>			
			&nbsp;供应商名称：&nbsp;<input type="text" name="s_value" id="s_value"/>
			<a href="javascript:searchDictionary()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>数据字典名称：</td>
					<td>
						<select class="easyui-combobox" id="name" name="dictionary.name" editable="false" panelHeight="auto">
					    	<option value="">请选择...</option>
							<option value="供应商">供应商</option>
							<option value="商品类型">商品类型</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>数据字典值：</td>
					<td><input type="text" name="dictionary.value" id="value" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveDictionary()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDictionaryDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
  </body>
</html>
