<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = pageNumber;
		document.form1.action = "orderloglist.do";
		document.form1.submit();
	}

	function Query() {
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = 1;
		document.form1.action = "orderloglist.do";
		document.form1.submit();
	}
	function CancelQuery(){
		location.href = "orderloglist.do?pagenumber=1";
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
			<li class="active">管理</li>
			<li class="active">订单日志管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>订单日志列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="javascript:void(0);"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">

			<label>订单编号：</label><input type="text" name="orderNo" style="width: 110px; height: 25px;" value="${orderid}" />
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
							<th>订单编号</th>
							<th>日期</th>
							<th>日志类型</th>
							<th>操作人</th>
							<th>操作状态</th>
							<th>备注</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
						<c:forEach items="${data_page.dataList }" var="order" varStatus="v">
							<tr>
								<td>${v.count + (data_page.currentPage-1)*10}</td>
								<td>${order.orderNo}</td>
								<td>${order.createTime}</td>
								<td>
									<c:forEach items="${orderLogTypeMap}" var="mymap" >
										<c:if test="${mymap.key==order.logType}">
											<c:out value="${mymap.value}" />
										</c:if>
									</c:forEach>
								</td>
								<td>
									 ${order.operateUser } 
								</td>
								<td>
									<c:if test="${order.operateResult == 1 }" >成功 </c:if>
									<c:if test="${order.operateResult == 2 }" >失败</c:if>
								</td>
								<td>
									 ${order.remark } 
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
paging($('.pagination')[0],'pagination','orderloglist.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</html>