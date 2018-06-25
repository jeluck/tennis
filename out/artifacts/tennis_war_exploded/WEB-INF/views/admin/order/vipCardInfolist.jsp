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
		document.form1.action = "vipCardInfolist.do";
		document.form1.submit();
	}

	function Query() {
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = 1;
		document.form1.action = "vipCardInfolist.do";
		document.form1.submit();
	}
	function CancelQuery(){
		location.href = "vipCardInfolist.do?pagenumber=1";
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
			<input type="hidden" value="${data_page.currentPage}" name="pageNumber" id="pagenumberid">

			<label>用户邀请码ID：</label><input type="text" name="invite_code" style="width: 110px; height: 25px;" value="${invite_code}" />
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
							<th>用户邀请码ID</th>
							<th>支付状态 </th>
							<th>收货地址</th>
							<th>收货人姓名</th>
							<th>收货人电话</th>
							<th>支付方式</th>
							<th>查看用户</th>
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
								<td>${order.pay_orderNo}</td>
								<td>${order.create_time}</td>
								<td>${order.invite_code}</td>
								<td>
									<c:if test="${order.payStatus == 7 }" >未支付 </c:if>
									<c:if test="${order.payStatus == 9 }" >已支付</c:if>
								</td>
								<td>
									 ${order.s_address } 
								</td>
								<td>
									 ${order.s_name } 
								</td>
								<td>
									 ${order.s_phone } 
								</td>
								<td>
									<c:if test="${order.pay_type == 1 }" >微信 </c:if>
									<c:if test="${order.pay_type == 2 }" >支付宝</c:if>
								</td>
								<td>
									 <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
											onclick="location='queryuser.do?&aid=${order.userId }'">
											<i class="icon-circle-arrow-right bigger-120">查看</i>
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
			    <li>
					<a href="javascript:void(0);">第${data_page.currentPage }页/共${data_page.pageCount }页</a>
				</li>
				<li>
					<a href="javascript:go_page(1);">首页</a>
				</li>
				<li class="prev <c:if test="${data_page.currentPage==1}"> disabled</c:if>">
					<c:if test="${data_page.currentPage-1>0 }">
						<a href="javascript:go_page(${data_page.currentPage-1 });">
							<<&nbsp;&nbsp;上一页
						</a>
					</c:if>
					<c:if test="${data_page.currentPage-1==0 }">
						<a href="javascript:void(0);">
							<<&nbsp;&nbsp;上一页
						</a>
					</c:if>
				</li>
				<c:forEach begin="${data_page.startPage }"
					end="${data_page.pageCount }" varStatus="v">
					<li <c:if test="${v.index==data_page.currentPage }" var="next current">class="active"</c:if><c:if test="${!current}">class="prev"</c:if>><a href="javascript:go_page(${v.index });">${v.index }</a></li>
				</c:forEach>
				<li class="next <c:if test="${data_page.currentPage==data_page.pageCount}"> disabled</c:if>">
					<c:if test="${data_page.currentPage+1<=data_page.pageCount}">
						<a href="javascript:go_page(${data_page.currentPage+1 });">
						下一页&nbsp;&nbsp;>>
						</a>
					</c:if>
					<c:if test="${data_page.currentPage==data_page.pageCount}">
						<a href="javascript:void(0);">
						下一页&nbsp;&nbsp;>>
						</a>
					</c:if>
				</li>
				<li>
					<a href="javascript:go_page(${data_page.pageCount });">尾页</a>
				</li>
				<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox" value="${data_page.currentPage }" data-options="min:1,max:${data_page.pageCount }" id="pagenum" style="width:55px;" />页&nbsp;&nbsp;<input class="btn btn-xs btn-info" type="button" onclick="go_page($('#pagenum').val());" value="确定"/></li>
			</ul>
		</div>
		</c:if>
	</div>
</body>
</html>