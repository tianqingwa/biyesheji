<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品信息管理</title>
    
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
		
		function searchGoods(){
			$('#dg').datagrid('load',{
				s_goodsname:$('#s_goodsname').val(),
				s_supplier:$('#s_supplier').combobox("getValue"),
				s_type:$('#s_type').combobox("getValue")
			});
		}
		
		function deleteGoods(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			var strIds=[];
			for(var i=0; i<selectedRows.length; i++){
				strIds.push(selectedRows[i].goodsid);
			}
			var ids = strIds.join(",");
			$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</front>条数据吗？",function(r){
				if(r){
					$.post("<%=basePath%>/server/goods!delete",{delIds:ids},function(result){
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
		
		function openGoodsAddDialog(){
			$("#dlg").dialog("open").dialog("setTitle","添加商品信息");
			url="<%=basePath%>/server/goods!save";
		}
		
		function openGoodsModifyDialog(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
			$("#goodsname").val(row.goodsname);
			$("#cost").val(row.cost);
			$("#sell").val(row.sell);
			$("#supplier").val(row.supplier);
			$("#supplierId").combobox("setValue",row.supplierId);
			$("#supplierId").combobox("setText",row.supplier);
			$("#type").val(row.type);
			$("#typeId").combobox("setValue",row.typeId);
			$("#typeId").combobox("setText",row.type);
			url="<%=basePath%>/server/goods!save?goodsid="+row.goodsid;
		}
		
		function closeGoodsDialog(){
			$("#dlg").dialog("close");
			resetValue();
		}
		
		function resetValue(){
			$("#goodsname").val("");
			$("#cost").val("");
			$("#sell").val("");
			$("#supuplier").val("");
			$("#type").val();
			$("#supplierId").combobox("setValue","");
			$("#typeId").combobox("setValue","");
		}
		
		function saveGoods(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
					if($('#supplierId').combobox("getValue")==""){
						$.messager.alert("系统提示","请选择供应商");
						return false;
					}
					if($('#typeId').combobox("getValue")=="") {
						$.messager.alert("系统提示","请选择商品类型");
						return false;
					}
					$('#supplier').val($('#supplierId').combobox("getText"));
					$('#type').val($('#typeId').combobox("getText"));
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
  	<table id="dg" title="商品信息" class="easyui-datagrid" fitColumns="true" 
  		pagination="true" rownumbers="true" url="<%=basePath%>/server/goods.action" fit="true" toolbar="#tb">
  			<thead>
  				<tr>
  					<th field="cb" checkbox="true"></th>
					<th field="goodsid" width="10" align="center">编号</th>
					<th field="goodsname" width="20" align="center">商品名</th>
					<th field="supplierId" width="20" align="center" hidden="true">供应商ID</th>
					<th field="typeId" width="20" align="center" hidden="true">商品类型ID</th>
					<th field="supplier" width="20" align="center">供应商</th>
					<th field="type" width="20" align="center">商品类型</th>
					<th field="cost" width="15" align="center">成本</th>
					<th field="sell" width="15" align="center">售价</th>
  				</tr>
  			</thead>
  	</table>
  			
	<div id="tb">
		<div>
			<a href="javascript:openGoodsAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openGoodsModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteGoods()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;供应商：&nbsp;<input class="easyui-combobox" name="goods.s_supplier" id="s_supplier"
									data-options="panelHeight:'auto',editable:true,valueField:'value',textField:'value',url:'<%=basePath%>/server/dictionary!dictionaryComboList?name=1'"/>
			&nbsp;商品类型：&nbsp;<input class="easyui-combobox" name="goods.s_type" id="s_type"
									data-options=" panelHeight:'auto',editable:true,valueField:'value',textField:'value',url:'<%=basePath%>/server/dictionary!dictionaryComboList?name=2'"/>	
			&nbsp;商品名：&nbsp;<input type="text" name="goods.s_goodsname" id="s_goodsname"/>
			<a href="javascript:searchGoods()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>商品名：</td>
					<td><input type="text" name="goods.goodsname" id="goodsname" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>成本：</td>
					<td><input  name="goods.cost" id="cost" min="0" precision="2" required="true"/></td>
				</tr>
				<tr>
					<td>售价：</td>
					<td><input  name="goods.sell" id="sell" min="0" precision="2" required="true"/></td>
				</tr>
				<tr>
					<td>供应商：</td>
					<td>
						<input type="hidden" class="easyui-validatebox" id="supplier" name="goods.supplier" />
						<input class="easyui-combobox" id="supplierId" name="goods.supplierId"
							data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'value',url:'<%=basePath%>/server/dictionary!dictionaryComboList?name=1'"/>
					</td>
				</tr>
				<tr>
					<td>商品类型：</td>
					<td>
						<input type="hidden" name="goods.type" id="type" class="easyui-validatebox"/>
						<input class="easyui-combobox" name="goods.typeId" id="typeId"
							data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'value',url:'<%=basePath%>/server/dictionary!dictionaryComboList?name=2'"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveGoods()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeGoodsDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
  </body>
</html>
