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
//		location.href = "orderlist.do?pagenumber="+pageNumber;
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = pageNumber;
		document.form1.action = "commissionpagelist.do";
		document.form1.submit();
	}

	function Query() {
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = 1;
		document.form1.action = "commissionpagelist.do";
		document.form1.submit();
	}
	function CancelQuery(){
		location.href = "commissionpagelist.do?pagenumber=1";
	}

	function orderdetail(oid){
//		location.href = "orderdetail.do?oid=" + oid;
		var inputoid = document.getElementById("oid");
		inputoid.value = oid;
		document.form1.action = "orderdetail.do";
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

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">管理</li>
			<li class="active">佣金记录</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>佣金记录列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="javascript:void(0);"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<input type="hidden" value="${basestatus}" name="basestatus" id="basestatus" />
			<input type="hidden" value="${paystatus}" name="paystatus" id="paystatus" />
			<input type="hidden" value="${deliverystatus}" name="deliverystatus" id="deliverystatus" />

			<label>订单编号：</label><input type="text" name="orderinfono" style="width: 110px; height: 25px;" value="${orderinfono}" />
			&nbsp;
			<label>用户姓名：</label><input type="text" name="username" style="width: 110px; height: 25px;" value="${username}" />
			&nbsp;
			<label>添加日期：</label><input type="text" name="startdate"
									   class="Wdate"
									   onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
									   style="width: 120px; height: 25px;" value="${startdate}" /> —
			<input type="text" class="Wdate" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
				   name="enddate" style="width: 120px; height: 25px;" value="${enddate}" /><span>
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
							<th>结算日期</th>
							<th>用户姓名</th>
							<th>单个佣金</th>
							<th>数量</th>
							<th>总佣金</th>
							<th>商品名称</th>
							<th>商品规格</th>
							<th>状态</th>
							<th>备注信息</th>
							<th style="width:150px;"></th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
						<c:forEach items="${data_page.dataList }" var="commission" varStatus="v">
							<tr>
								<td>${v.count + (data_page.currentPage-1)*10}</td>
								<td>${commission.orderInfoNo}</td>
								<td>${commission.create_time}</td>
								<td>${commission.weuser.username}</td>
								<td>${commission.commission}</td>
								<td>${commission.number}</td>
								<td>${commission.totalcommission}</td>
								<td>${commission.commodityName}</td>
								<td>${commission.commodityTypeName}</td>
								<td>
									<c:if test="${commission.commission_status==0}">未入账</c:if>
									<c:if test="${commission.commission_status==1}">入账中</c:if>
									<c:if test="${commission.commission_status==2}">已入账</c:if>
									<c:if test="${commission.commission_status==3}">取消入账</c:if>
								</td>
								<td>${commission.remark}
								</td>
								<%--<td>--%>
									<%--<div--%>
											<%--class="visible-md visible-lg hidden-sm hidden-xs btn-group">--%>
										<%--<button class="btn btn-xs btn-info"--%>
												<%--onclick="orderdetail('${order.id}');">--%>
											<%--<i class="icon-file"></i>&nbsp;详细--%>
										<%--</button>--%>
									<%--</div>--%>
								<%--</td>--%>
								<td>
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