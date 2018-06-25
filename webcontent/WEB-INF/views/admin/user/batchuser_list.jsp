<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		location.href = "userlist.do?&pageNumber="+pageNumber;
	}
	//添加测试信息
	function addNotice(){
		location.href = "toaddtest.do";
	}
	
	//编辑测试信息
	function editManager(id){
		location.href = "toedittest.do?id="+id;
	}
	//删除测试
	function deletetest(id){
		location.href = "deletetest.do?id="+id;
	}
	
</script>
</head>
<body>
	<c:if test="${not empty uPhone }">
		<script type="text/javascript">
			alert('已有此电话号码${uPhone}');
		</script>
	</c:if>
	<c:if test="${not empty Phone }">
		<script type="text/javascript">
			alert('表格中${Phone}电话号码重复');	
		</script>
	</c:if>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">权限管理</li>
			<li class="active">用户管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>导入数据 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="saveuserlist.do;"
			  method="post" enctype="multipart/form-data">
			<label>文件：</label><a href="/document/moban.xls">下载模板</a>
			<input id="file" type="file" name="file" style="width: 300px; height: 25px;" required /><span>文件必须为excel格式</span>
			<input class="btn btn-sm btn-success"  type="submit" value="[ 文件提交]">
		</form>
	</div>

</body>
</html>