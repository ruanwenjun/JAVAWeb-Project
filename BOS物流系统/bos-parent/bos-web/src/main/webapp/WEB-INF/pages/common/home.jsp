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
<script type="text/javascript">
	$(function() {
		panels = [ {
			id : 'p1',
			title : '公共栏',
			height : 255,
			collapsible : true,
			href:'page_portal_gonggao.action'
		}, {
			id : 'p2',
			title : '代办事宜',
			height : 255,
			collapsible : true,
			href:'page_portal_daiban.action'
		}, {
			id : 'p3',
			title : '预警信息',
			height : 255,
			collapsible : true,
			href:'page_portal_yujing.action'
		}, {
			id : 'p4',
			title : '系统BUG反馈',
			height : 255,
			collapsible : true,
			href:'page_portal_bug.action'
		}];
		 $('#layout_portal_portal').portal({
			border : false,
			fit : true
		 });
		var state = state = 'p1,p2:p3,p4';/*冒号代表列，逗号代表行*/

		addPortalPanels(state);
		$('#layout_portal_portal').portal('resize');
	
	});
	
	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	function getPortalState() {
		var aa=[];
		for(var columnIndex=0;columnIndex<2;columnIndex++) {
			var cc=[];
			var panels=$('#layout_portal_portal').portal('getPanels',columnIndex);
			for(var i=0;i<panels.length;i++) {
				cc.push(panels[i].attr('id'));
			}
			aa.push(cc.join(','));
		}
		return aa.join(':');
	}
	function addPortalPanels(portalState) {
		var columns = portalState.split(':');
		for (var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			var cc = columns[columnIndex].split(',');
			for (var j = 0; j < cc.length; j++) {
				var options = getPanelOptions(cc[j]);
				if (options) {
					var p = $('<div/>').attr('id', options.id).appendTo('body');
					p.panel(options);
					$('#layout_portal_portal').portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
	}
</script>
</head>
<body>
	<div id="layout_portal_portal" style="position:relative;height:600px;">
		<div></div>
		<div></div>
	</div>
</body>
</html>