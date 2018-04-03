<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>	
<script type="text/javascript">
	$(function(){
		// 数据表格属性
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '添加角色',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_role_add.action';
					}
				}           
			],
			url : '${pageContext.request.contextPath}/roleAction_pageQuery',
			columns : [[
				{
					field : 'id',
					title : '编号',
					width : 200,
					align : 'center'
				},
				{
					field : 'name',
					title : '名称',
					width : 200,
					align : 'center'
				}, 
				{
					field : 'description',
					title : '描述',
					width : 200,
					align : 'center'
				} 
			]],
			fit : true,
			pageList: [10,20,30],
			pagination : true,
			striped : true,
			
		});
	});
</script>	
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>
</body>
</html>