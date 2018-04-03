<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!--导入highcharts的类库  -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/js/modules/exporting.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--导入OCupload类库  -->
<script src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js" type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addSubareaWindow').window("open");
	}
	
	function doEdit(){
		alert("修改...");
	}
	
	function doDelete(){
		alert("删除...");
	}
	//点击查询按钮弹出窗口
	function doSearch(){
		$('#searchWindow').window("open");
	}
	//分区导入
	/* function doImport(){
		 $("#button-import").upload({
			name:"subareaFile",
			action:"subareaAction_uploadXls.action"
			});
	 }*/ 
	
	
	//分区导出按钮
	function doExport(){
		location.href="${pageContext.request.contextPath}/subareaAction_exportXls.action";
	}
	
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo',
		//handler : doImport
	},{
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	},{
		id : 'button-export',
		text : '查看分区分布',
		iconCls : 'icon-search',
		handler : showHighCharts
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分拣编号',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.id;
		}
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.district;
		}
	}, {
		field : 'addresskey',
		title : '关键字',
		width : 120,
		align : 'center'
	}, {
		field : 'startnum',
		title : '起始号',
		width : 100,
		align : 'center'
	}, {
		field : 'endnum',
		title : '终止号',
		width : 100,
		align : 'center'
	} , {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center'
	} , {
		field : 'position',
		title : '位置',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 管理分区表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [2,10,50],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/subareaAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		 //导入
		$("#button-import").upload({
			name:"subareaFile",
			action:"subareaAction_uploadXls.action"
		}); 
		
		
		// 添加、修改分区
		$('#addSubareaWindow').window({
	        title: '添加分区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
			
		// 查询分区
		$('#searchWindow').window({
	        title: '查询分区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查看分区分布图
		$('#showHighChartsWindow').window({
	        title: '查看分区分布',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 500,
	    });
		
		
		//将表单序列化为JSON对象的方法
		$.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();
            $(array).each(function(){  
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        }; 
		
		//点击查询窗口里面的查询按钮
		$("#btn").click(function(){
			var v = $("#subareaSearchForm").serializeJson();
			//使用数据表格的重新加载方法，提交的地址即数据表格数据来源地址（URL），该方法不会刷新页面，会提交给服务器参数，并重新加载数据
			//需要传一个JSON对象
			$('#grid').datagrid('load',v);
			$('#searchWindow').window("close");
		});
	});
	
	//查看分区分布图
	function showHighCharts() {
		$('#showHighChartsWindow').window("open");
		$.post("subareaAction_showHighChart",function(data){
			var province = new Array();
			var count = new Array();
			for (var i = 0; i < data.length; i++) {
				province.push(data[i][0]);
				count.push(data[i][1]);
			}
		 $('#highChartContainer').highcharts({
			 credits: {
		            enabled: false
		        },
			  chart: {
		            type: 'bar'
		        },
		        title: {
		            text: '分区分布图'
		        },
		        xAxis: {
		            categories: province
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: '分区分布图'
		            }
		        },
		        legend: {
		            reversed: true
		        },
		        plotOptions: {
		            series: {
		                stacking: 'normal'
		            }
		        },
		        series: [{
		            name: '分区数目',
		            data: count
		        }]
		    });
		});
		
	}

	function doDblClickRow(){
		alert("双击表格数据...");
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
		<!--数据表格  -->
    	<table id="grid"></table>
	</div>
	<!-- 添加 分区 -->
	<div class="easyui-window" title="分区添加" id="addSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<script type="text/javascript">
					//保存按钮
					$("#save").click(function (){
						//校验表单
						var v = $("#addSubareaForm").form("validate");
						if(v){
							$("#addSubareaForm").submit();
						}else{
							$.messager.alert('提示信息','分区信息填写错误','error');
						}
					} );
				</script>
			</div>
		</div>
		<!--添加分区  -->
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="addSubareaForm" action="${pageContext.request.contextPath }/subareaAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					
					<tr>
						<td>选择区域</td>
						<td>
							<input class="easyui-combobox" name="region.id"  
    							data-options="valueField:'id',textField:'name',mode:'remote',url:'${pageContext.request.contextPath }/regionAction_findRegionAjax.action'" />  
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="subareaSearchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="region.province"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city"/></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="region.district"/></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey"/></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	<!-- 查看分区分布 -->
	<div class="easyui-window" title="分区添加" id="showHighChartsWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div id="highChartContainer"  split="false" border="false" >
			
		</div>
		
	</div>
</body>
</html>