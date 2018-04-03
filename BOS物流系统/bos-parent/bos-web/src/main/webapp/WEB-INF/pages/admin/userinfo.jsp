<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("body").css({visibility:"visible"});
		$('#save').click(function(){
			var v = $("#form").form("validate");
			if(v){
				$('#form').submit();
			}
		});
	});
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
		</div>
	</div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
       <form id="form" method="post" action="${pageContext.request.contextPath }/userAction_add" >
           <table class="table-edit"  width="95%" align="center">
           		<tr class="title"><td colspan="4">基本信息</td></tr>
	           	<tr>
	           		<td>用户名:</td>
	           		<td>
	           			<input type="text" name="username" id="username" class="easyui-validatebox" required="true" />
	           			<font color="red"><s:actionerror/></font>
	           		</td>
					<td>口令:</td>
					<td><input type="password" name="password" id="password" 
						class="easyui-validatebox" required="true" validType="minLength[5]" />
					</td>
				</tr>
				<tr class="title"><td colspan="4">其他信息</td></tr>
	           	<tr><td>工资:</td><td><input type="text" name="salary" id="salary" class="easyui-numberbox" /></td>
					<td>生日:</td><td><input type="text" name="birthday" id="birthday" class="easyui-datebox" /></td></tr>
	           	<tr><td>性别:</td><td>
	           		<select name="gender" id="gender" class="easyui-combobox" style="width: 150px;">
	           			<option value="">请选择</option>
	           			<option value="男">男</option>
	           			<option value="女">女</option>
	           		</select>
	           	</td>
					<td>单位:</td><td>
					<select name="station" id="station" class="easyui-combobox" style="width: 150px;">
	           			<option value="">请选择</option>
	           			<option value="总公司">总公司</option>
	           			<option value="分公司">分公司</option>
	           			<option value="厅点">厅点</option>
	           			<option value="基地运转中心">基地运转中心</option>
	           			<option value="营业所">营业所</option>
	           		</select>
				</td></tr>
				<tr>
					<td>联系电话</td>
					<td colspan="3">
						<input type="text" name="telephone" id="telephone" class="easyui-validatebox" required="true" />
					</td>
				</tr>
	           	<tr><td>备注:</td><td colspan="3"><textarea style="width:80%"></textarea></td></tr>
	           	<tr><td>角色:</td>
	           		<td colspan="3" id="roleCheckbox">
						<script type="text/javascript">
							$(function(){
								//获取角色信息并显示
								$.post("${pageContext.request.contextPath }/roleAction_ajaxList",function(data){
									for (var i = 0; i < data.length; i++) {
										var content = "<input type='checkbox' id='"+data[i].id+"' name='roleId' value='"+data[i].id+"'><label for='"+data[i].id+"'>"+data[i].name+"</label>";
										$("#roleCheckbox").append(content);
									} 
								})
							});
						</script>
						
	           		</td>
	           	</tr>
           </table>
       </form>
	</div>
</body>
</html>