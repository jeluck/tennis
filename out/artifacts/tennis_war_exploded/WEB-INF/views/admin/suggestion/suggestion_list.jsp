<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台意见箱信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		location.href = "suggestionlist.do?&pageNumber="+pageNumber;
	}
	//查看意见箱信息
	function readSuggestion(id){
		location.href = "readSuggestion.do?id="+id;
	}
	//删除意见箱
	function deletetest(id){
		location.href = "deletesuggestion.do?id="+id;
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
			<li class="active">意见箱管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>后台意见箱列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>手机号</th>
							<th>内容</th>
							<th>创建时间</th>
							<th>状态</th>
							<th style="width:250px;">操作</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
						<c:forEach items="${data_page.dataList }" var="suggestion" varStatus="v">
							<tr>
								<td>${v.index+1}</td>
								<td>${suggestion.phone}</td>
								<td>${suggestion.detailcontent}</td>
								<td>${suggestion.create_time}</td>
								<td>
									<c:if test="${suggestion.readstatus == 0  }" var="read">
										未读
									</c:if>
									<c:if test="${!read }">已读</c:if>
								</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="readSuggestion(${suggestion.sid});">
											<i class="icon-edit bigger-120"></i>查看
										</button>
									</div>	
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="deletetest(${suggestion.sid});">
											<i class="icon-edit bigger-120"></i>删除
										</button>
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
paging($('.pagination')[0],'pagination','suggestionlist.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
</script>
</html>