<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript"
	src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber) {
		//		location.href = "orderlist.do?pagenumber="+pageNumber;
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = pageNumber;
		document.form1.action = "todayOrder.do";
		document.form1.submit();
	}

	function Query() {
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = 1;
		document.form1.action = "todayOrder.do";
		document.form1.submit();
	}
	function CancelQuery() {
		location.href = "todayOrder.do?pagenumber=1&basestatus=${basestatus}&paystatus=${paystatus}&deliverystatus=${deliverystatus}";
	}

	function orderdetail(oid) {
		//		location.href = "orderdetail.do?oid=" + oid;
		var inputoid = document.getElementById("oid");
		inputoid.value = oid;
		document.form1.action = "orderdetail.do";
		document.form1.submit();
	}

	//测试支付
	function testpay(oid) {
		var inputoid = document.getElementById("oid");
		inputoid.value = oid;
		document.form1.action = "testpay.do";
		document.form1.submit();
	}
	
	//测试完成
	function testcompleteOrder(oid) {
		var inputoid = document.getElementById("oid");
		inputoid.value = oid;
		document.form1.action = "testcompleteOrder.do";
		document.form1.submit();
	}
	
	function refuseorder(oid){
		document.form1.action = "refuseorder.do?id="+oid;
		document.form1.submit();
	}
	
	function paid_handle_bymanager(oid){

		document.form1.action = "paid_handle_bymanager.do?id="+oid;
		document.form1.submit();
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
			<li class="active">管理</li>
			<li class="active">订单管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			订单列表 <span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>

	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline"
			action="javascript:void(0);" method="post">
			<input type="hidden" value="${data_page.currentPage}"
				name="pagenumber" id="pagenumberid"> <input type="hidden"
				value="${basestatus}" name="basestatus" id="basestatus" /> <input
				type="hidden" value="${paystatus}" name="paystatus" id="paystatus" />
			<label>今日订单数：</label>${count }<label>今日订单总金额：</label>${moenyCount }
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
							<th>打球日期</th>
							<th>打球时间</th>
							<th>所在场馆</th>
							<th>所在场地</th>
							<th>所属教练</th>
							<th>订单编号</th>
							<th>下单日期</th>
							<th>用户</th>
							<th>总金额</th>
							<th>状态</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
							<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
							<c:forEach items="${data_page.dataList }" var="order"
								varStatus="v">
								<tr>
									<td>${v.count + (data_page.currentPage-1)*10}</td>
									<td>${order.create_date }</td>
									<td>${order.today_time }</td>
									<td>${order.playgroundName }</td>
									<td>${order.spaceName }</td>
									<td>${order.coachName.name }<c:if test="${order.coachName.coachType==1 }">(自由)</c:if>
									<c:if test="${order.coachName.coachType==2 }">(驻场)</c:if>
									<c:if test="${order.coachName.coachType==3 }">(场馆运营者)</c:if></td>
									<td>${order.orderSubNo}</td>
									<td>${order.create_date}</td>
									<c:if test="${paystatus == 9 }">
										<td>${order.payTime}</td>
									</c:if>
									<td>${order.weuser.username}</td>
									<td>${order.orderPayTotalAmont}</td>
									<td><c:if test="${order.status == 1 }">未支付</c:if> <c:if
											test="${order.status == 2 }">部分支付</c:if>
											<c:if test="${order.status == 3 }">已支付</c:if> <c:if
											test="${order.status == 4 }">执行中</c:if> <c:if
											test="${order.status == 5 }">已完成</c:if> <c:if
											test="${order.status == 6 }">退款申请中</c:if> <c:if
											test="${order.status == 7 }">部分退款</c:if>
											<c:if test="${order.status == 8 }">已取消</c:if>
											<c:if test="${order.status == 9 }">退款失败</c:if>
											<c:if test="${order.status == 10 }">作废</c:if>
											<c:if test="${order.status == 11 }">已评价</c:if></td>
									<td>	
<!-- 											<div -->
<!-- 											class="visible-md visible-lg hidden-sm hidden-xs btn-group"> -->
<!-- 											<button class="btn btn-xs btn-info" -->
<%-- 												onclick="orderdetail('${order.id}');"> --%>
<!-- 												<i class="icon-file"></i>&nbsp;详细 -->
<!-- 											</button> -->
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
					<li><a href="javascript:void(0);">第${data_page.currentPage
							}页/共${data_page.pageCount }页</a></li>
					<li><a href="javascript:go_page(1);">首页</a></li>
					<li
						class="prev <c:if test="${data_page.currentPage==1}"> disabled</c:if>">
						<c:if test="${data_page.currentPage-1>0 }">
							<a href="javascript:go_page(${data_page.currentPage-1 });">
								<<&nbsp;&nbsp;上一页 </a>
						</c:if> <c:if test="${data_page.currentPage-1==0 }">
							<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
						</c:if>
					</li>
					<c:forEach begin="${data_page.startPage }"
						end="${data_page.pageCount }" varStatus="v">
						<li
							<c:if test="${v.index==data_page.currentPage }" var="next current">class="active"</c:if>
							<c:if test="${!current}">class="prev"</c:if>><a
							href="javascript:go_page(${v.index });">${v.index }</a></li>
					</c:forEach>
					<li
						class="next <c:if test="${data_page.currentPage==data_page.pageCount}"> disabled</c:if>">
						<c:if test="${data_page.currentPage+1<=data_page.pageCount}">
							<a href="javascript:go_page(${data_page.currentPage+1 });">
								下一页&nbsp;&nbsp;>> </a>
						</c:if> <c:if test="${data_page.currentPage==data_page.pageCount}">
							<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
						</c:if>
					</li>
					<li><a href="javascript:go_page(${data_page.pageCount });">尾页</a>
					</li>
					<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox"
						value="${data_page.currentPage }"
						data-options="min:1,max:${data_page.pageCount }" id="pagenum"
						style="width: 55px;" />页&nbsp;&nbsp;<input
						class="btn btn-xs btn-info" type="button"
						onclick="go_page($('#pagenum').val());" value="确定" /></li>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>