<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">管理</li>
			<li class="active">订单详情</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>订单详情 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="form1" name="form1" class="form-inline" action="javascript:void(0);"
		  method="post">
	<table width="70%" border="0" align="center">
		<tr>
			<td width="5%">&nbsp;</td>
			<td width="40%">
				<table border="1"  align="left" class="table table-striped table-bordered table-hover">
					<tr >
						<td >
							订单编号：
						</td>
						<td >
							${order.orderSubNo}
						</td>
					</tr>

					<tr>
						<td>支付状态：</td>
						<td>
							<c:if test="${order.status == 1 }" >未支付</c:if>
							<c:if test="${order.status == 3 }" >已支付</c:if>
							<c:if test="${order.status == 10 }" >作废</c:if>
							<c:if test="${order.status == 7 }" >部分退款</c:if>
							<c:if test="${order.status == 8 }" >已取消</c:if>
							<c:if test="${order.status == 6 }" >退款申请中</c:if>
							<c:if test="${order.status == 5 }" >已完成</c:if>
							<c:if test="${order.status == 4 }" >执行中</c:if>
							<c:if test="${order.status == 2 }" >部分支付</c:if>
						</td>
					</tr>

					<tr>
						<td>用户姓名：</td>
						<td>
							${order.weuser.username}
						</td>
					</tr>

					<tr>
						<td>联系手机：</td>
						<td>
							${order.weuser.uphone}
						</td>
					</tr>
					
					<tr>
						<td>
							<c:if test="${order.orderType == 1 }">
								场馆名称：
							</c:if>
							<c:if test="${order.orderType == 2 }">
								教练名称：
							</c:if>
						</td>
						<td>
							${name}
						</td>
					</tr>
					<c:if test="${not empty space_name }">
					<tr>
						<td>
							场地名称：
						</td>
						<td>
							${space_name}
						</td>
					</tr>
					</c:if>
					<tr>
						<td>预定日期：</td>
						<td>
							${order.create_date}
						</td>
					</tr>
					<tr>
						<td>预定时间：</td>
						<td>
							${order.today_time} - ${order.today_time+1}
						</td>
					</tr>
					<tr>
						<td>下单日期：</td>
						<td>
							${order.creat_order_time}
						</td>
					</tr>
					<tr>
						<td>支付金额：</td>
						<td>
							${order.orderPayTotalAmont}
						</td>
					</tr>
					<tr>
						<td>支付时间：</td>
						<td>
							${order.payTime}
						</td>
					</tr>

					

				</table>
			</td>
			<td width="20%">&nbsp;</td>
		</tr>

		<tr>
			<td></td>
			<td>&nbsp;</td>
			<td></td>
		</tr>

	</table>

	</form>

</body>
</html>