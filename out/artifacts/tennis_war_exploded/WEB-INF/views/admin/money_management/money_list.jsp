<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript"
	src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		location.href = "money_list.do?&pageNumber="+pageNumber;
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

		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">权限管理</li>
			<li class="active">用户资金管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			用户资金列表 <span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline"
			action="money_list.do;" method="post">
			<input type="hidden" value="${data_page.currentPage}"
				name="pagenumber" id="pagenumberid"> <label>手机号：</label><input
				type="text" name="uphone"
				style="width: 110px; height: 25px;" value="${uphone}" />
			&nbsp; <input class="btn btn-sm btn-success" type="submit"
				value="[ 查询 ]"> <a class="btn btn-sm btn-success"
				onclick="location.href = 'money_list.do?pagenumber=1';">[ 取消查询 ]</a></span>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th class="hidden-480">手机号</th>
							<th class="hidden-480">用户名</th>
							<th>真实姓名</th>
							<th>充值金额</th>
							<th>佣金金额</th>
							<th>入账中金额</th>
							<th>可用金额</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
							<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
							<c:forEach items="${data_page.dataList }" var="w" varStatus="v">
								<tr>
									<td>${base+v.index+1 }</td>
										<td>${w.uphone}</td>
										<td>${w.username}</td>
										<td>${w.real_name}</td>
										<td>${w.recharge}</td>
										<td>${w.totalcommission }</td>
										<td></td>
										<td>${w.amount }</td>
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
	<script>
paging($('.pagination')[0],'pagination','money_list.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</body>
</html>