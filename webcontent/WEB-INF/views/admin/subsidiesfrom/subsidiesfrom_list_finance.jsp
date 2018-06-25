<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
			
			function exportreport(){
				document.getElementById("QueryForm").action="exportreport_finance.do";
			    document.getElementById("QueryForm").submit();
			    document.getElementById("QueryForm").action="/pgm/checkSubsidiesFrom_forFinance.do";
			}
			
			/**
				点击结算
			**/
			function trade_balance(orderSubNo){
				document.getElementById("QueryForm").action="/pgm/trade_balance.do?orderSubNo="+orderSubNo;
			    document.getElementById("QueryForm").submit();
			}
			
			/**
			点击补贴发放
			**/
			function subsidies_grant(orderSubNo){
				document.getElementById("QueryForm").action="/pgm/subsidies_grant.do?orderSubNo="+orderSubNo;
			    document.getElementById("QueryForm").submit();
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">补贴结算表</li>
			<li class="active">补贴结算表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>补贴结算_财务用 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="page-header">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:交易结算时间大概为打球时间+${trade_balance_time }天</br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:补贴发放时间大概为打球时间+${subsidies_grant_time }天
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="/pgm/checkSubsidiesFrom_forFinance.do;"
			  method="post">
			<label>日期：</label><input
				type="text" name="start_time" class="Wdate"
				onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
				style="width: 120px; height: 25px;" value="${start_time}" /> — <input
				type="text" class="Wdate"
				onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
				name="end_time" style="width: 120px; height: 25px;"
				value="${end_time}" /> 
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<a class="btn btn-sm btn-success" onclick="location.href = '/pgm/checkSubsidiesFrom_forFinance.do?pagenumber=1';">[ 取消查询 ]</a></span>
			<input class="btn btn-sm btn-success"  type="button" onclick="exportreport()" value="[ 导出 ]">
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>下单日期</th>
							<th>订单编号</th>
							<th>订单状态</th>
							<th>总金额</th>
							<th>交易结算状态</th>
							<th>交易结算时间</th>
							<th>补贴比例</th>
							<th>补贴额</th>
							<th>补贴发放状态</th>
							<th>补贴发放时间	</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="17" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:forEach items="${dataList }" var="o" varStatus="v">
							<tr>
								<td>${v.index+1}</td>
								<td>${o.order_time }</td>
								<td>${o.orderSubNo }</td>
								<td>
									<c:if test="${o.status == 3 }">已支付</c:if> 
									<c:if test="${o.status == 4 }">执行中</c:if> 
									<c:if test="${o.status == 5 }">已完成</c:if> 
									<c:if test="${o.status == 11 }">已评价</c:if>
								</td>
								<td>${o.total_amount }</td>
								<td>
									<c:if test="${o.trade_balance_status == 1}">
										未结算
										<button class="btn btn-xs btn-info" onclick="trade_balance('${o.orderSubNo}')">
												<i class=" bigger-120">点击结算</i>
										</button>
									</c:if>
									<c:if test="${o.trade_balance_status == 0}">
										已结算
									</c:if>
								</td>
								<td>${o.trade_balance_time }</td>
								<td>
									<fmt:formatNumber value="${o.subsidies_proportion*100 }" pattern="#0.#"/>%
								</td>
								<td>
									<fmt:formatNumber value="${o.subsidies_money }" pattern="#0.#"/>
								</td>
								<td>
									<c:if test="${o.subsidies_grant_status == 1}">
										未发放
										<button class="btn btn-xs btn-info" onclick="subsidies_grant('${o.orderSubNo}')">
												<i class=" bigger-120">点击发放</i>
										</button>
									</c:if>
									<c:if test="${o.subsidies_grant_status == 0}">
										已发放
									</c:if>
								</td>
								<td>${o.subsidies_grant_time }</td>
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
<script type="text/javascript">
var index;
</script>
</body>
</html>