<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出单信息管理</title>
    
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
		
		function searchChuhuo(){
			$('#dg').datagrid('load',{
				s_goodsid:$('#s_goodsid').combobox("getValue"),
				s_outtime:$('#s_outtime').datebox("getValue")
			});
		}
		
		function openChuhuoAddDialog(){
			$("#dlg").dialog("open").dialog("setTitle","添加进货单信息");
			url="<%=basePath%>/server/chuhuo!save";
		}
		
		function openChuhuoModifyDialog(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑进货单信息");
			$("#goodsid").combobox("setValue",row.goodsid);
			$("#goodsid").combobox("setText",row.goodsname);
			$("#quantity").val(row.quantity);
			$("#out").datebox("setValue",row.out);
			url="<%=basePath%>/server/chuhuo!save?id="+row.id;
		}
		
		function closeChuhuoDialog(){
			$("#dlg").dialog("close");
			resetValue();
		}
		
		function resetValue(){
			$("#quantity").val("");
			$("#goodsid").combobox("setValue","");
			$("#goodsid").combobox("setText","");
			$("#out").datebox("setValue","");
		}
		
		function saveChuhuo(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
					if($('#goodsid').combobox("getValue")==""){
						$.messager.alert("系统提示","请选择商品");
						return false;
					}
					return $(this).form("validate");
				},
				success:function(result){
					var result = eval('('+result+')'); // result是个json串,得解析成js对象
					if(result.success){
						$.messager.alert("系统提示",result.errorMsg);
						resetValue();
						$("#dlg").dialog("close");
						$("#dg").datagrid("reload");
						
					}else{
						$.messager.alert("系统提示","保存失败");
						return;
					}
				}
			});
		}
	</script>
	
  </head>
  
  <body style="margin: 5px;">
  	<table id="dg" title="出货单信息" class="easyui-datagrid" fitColumns="true" 
  		pagination="true" rownumbers="true" url="<%=basePath%>/server/chuhuo.action" fit="true" toolbar="#tb">
  			<thead>
  				<tr>
  					<th field="cb" checkbox="true"></th>
					<th field="id" width="10" align="center">编号</th>
					<th field="goodsid" width="20" align="center" hidden="true">商品ID</th>
					<th field="goodsname" width="20" align="center">商品名</th>
					<th field="quantity" width="20" align="center">数量</th>
					<th field="outtime" width="20" align="center">日期 </th>
  				</tr>
  			</thead>
  	</table>
  			
	<div id="tb">
		<div>
			<a href="javascript:openChuhuoAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a><%--
			<a href="javascript:openJinhuoModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		--%></div>
		<div>
			&nbsp;商品名：&nbsp;<input class="easyui-combobox" name="s_goodsid" id="s_goodsid"
									data-options="panelHeight:'auto',editable:false,valueField:'goodsid',textField:'goodsname',url:'<%=basePath%>/server/goods!goodsComboList'"/>
			&nbsp;下单日期：&nbsp;<input class="easyui-datebox" name="s_outtime" id="s_outtime" editable="false" size="10">
			<a href="javascript:searchChuhuo()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>商品名：</td>
					<td>
						<input class="easyui-combobox" name="chuhuo.goodsid" id="goodsid"
									data-options="panelHeight:'auto',editable:false,valueField:'goodsid',textField:'goodsname',url:'<%=basePath%>/server/goods!goodsComboList'"/>
					</td>
				</tr>
				<tr>
					<td>数量：</td>
					<td><input class="easyui-numberbox" name="chuhuo.quantity" id="quantity" min="0" required="true"/></td>
				</tr>
				<tr>
					<td>日期：</td>
					<td><input class="easyui-datebox" name="chuhuo.outtime" id="outtime" required="true" editable="false"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveChuhuo()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeChuhuoDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
  </body>
</html>
