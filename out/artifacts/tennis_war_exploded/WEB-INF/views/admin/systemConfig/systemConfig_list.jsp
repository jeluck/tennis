<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台系统配置信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		location.href = "systemConfiglist.do?&pageNumber="+pageNumber;
	}
	//添加系统配置信息
	function addNotice(){
		location.href = "toaddsystemConfig.do";
	}
	
	//编辑系统配置信息
	function toeditsystemConfig(id){
		location.href = "toeditsystemConfig.do?id="+id;
	}
	//关闭系统配置
	function closesystemConfig(id){
		location.href = "closesystemConfig.do?id="+id;
	}
	//关闭系统配置
	function useConfig(id){
		location.href = "useConfig.do?id="+id;
	}

	function Query() {
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = 1;
		document.form1.action = "systemConfiglist.do";
		document.form1.submit();
	}
	function CancelQuery(){
		location.href = "systemConfiglist.do?pagenumber=1";
	}
</script>
</head>
<body>
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
			<li class="active">系统配置管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>系统配置列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	
	<div style="margin:0 0 10px 10px;height:30px;clear:both;">
		<button class="btn btn-sm btn-success pull-left" onclick="addNotice();"><i class="icon-ok"></i>添加系统配置</button>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="javascript:void(0);"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<label>配置名称：</label><input type="text" name="configName" style="width: 110px; height: 25px;" value="${configName}" />
			&nbsp;
			<label>键：</label><input type="text" name="key" value="${key}" />
			<span>
			<a class="btn btn-sm btn-success" onclick="Query();">[ 查询 ]</a>
			<a class="btn btn-sm btn-success" onclick="CancelQuery();">[ 取消查询 ]</a></span>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>配置名称</th>
							<th>键</th>
							<th>值</th>
							<th>状态</th>
							<th>备注</th>
							<th style="width:150px;">操作</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
						<c:forEach items="${data_page.dataList }" var="systemConfig" varStatus="v">
							<tr>
								<td>${systemConfig.id}</td>
								<td>${systemConfig.configName}</td>
								<td>${systemConfig.key}</td>
								<td>${systemConfig.value}</td>
								<td>
									<c:if test="${systemConfig.deleteFlag == 1  }" var="qidong">
										可用
									</c:if>
									<c:if test="${!qidong }">
										无用
									</c:if>
								</td>
								<td>${systemConfig.remark}</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="toeditsystemConfig(${systemConfig.id});">
											<i class="icon-edit bigger-120"></i>修改
										</button>
									</div>	
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<c:if test="${systemConfig.deleteFlag == 1  }" var="qidong">
											<button class="btn btn-xs btn-info" onclick="closesystemConfig(${systemConfig.id});">
												<i class="icon-edit bigger-120"></i>关闭
											</button>
										</c:if>
										<c:if test="${!qidong }">
											<button class="btn btn-xs btn-info" onclick="useConfig(${systemConfig.id});">
												<i class="icon-edit bigger-120"></i>开启
											</button>
										</c:if>
									</div>	
								</td>
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
		<c:if test="${havadata }">
		<div class="modal-footer no-margin-top">
			<ul class="pagination pull-right no-margin">
			</ul>
		</div>
		</c:if>
	</div>
</body>
<script>
paging($('.pagination')[0],'pagination','systemConfiglist.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
</script>
</html>