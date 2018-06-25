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
		location.href = "orderlist.do?pagenumber="+pageNumber;
	}

	function resetorder(oid)
	{
		var inputoid = document.getElementById("oid");
		inputoid.value = oid;
		document.form1.action = "resetorder.do";
		document.form1.submit();
	}

	function returnoverview()
	{
		document.form1.action = "orderlist.do";
		document.form1.submit();
	}

	function accpetOrder(orderId)
	{
		document.form1.action = "acceptorder.do";
		document.form1.submit();
	}

	function refuseOrder(orderId)
	{
		document.form1.action = "refuseorder.do";
		document.form1.submit();
	}

	function deliver()
	{
		var expressName = $('#expressname').val();
		if(expressName.trim() == '')
		{
			alert('请输入快递公司名称！');
			return;
		}

		var expressOrderId = $('#expressorderid').val();
		if(expressOrderId.trim() == '')
		{
			alert('请输入快递单号！');
			return;
		}

		document.form1.action = "delivergoods.do";
		document.form1.submit();

	}




</script>
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

	<input type="hidden" value="${reset}" id="reset" name="reset" />
	<form id="form1" name="form1" class="form-inline" action="javascript:void(0);"
		  method="post">
		<input type="hidden" value="${pagenumber}" name="pagenumber"/>
		<input type="hidden" value="${pagesize}" name="pagesize"/>
		<input type="hidden" value="${startdate}" name="startdate"/>
		<input type="hidden" value="${enddate}" name="enddate"/>
		<input type="hidden" value="${orderid}" name="orderid"/>
		<input type="hidden" value="${orderinfoid}" name="orderinfoid" id="orderinfoid"/>


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
						<td xwidth="5%">
							<c:if test="${order.payStatus == 9 }" >
								<c:if test="${order.baseStatus == 1 }" >
									<a class="btn btn-sm btn-success" onclick="accpetOrder('${order.id}');"> 通过 </a>
								<!-- 	<a class="btn btn-sm btn-danger" onclick="refuseOrder('${order.id}')"> 驳回 </a> -->
								</c:if>
							</c:if>
						</td>
					</tr>

					<tr>
						<td>
							总金额：
						</td>
						<td>
							${order.total_price}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>
							商品总价格：
						</td>
						<td>
							${order.ori_price}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>
							快递总费用：
						</td>
						<td>
							${order.exp_price}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>订单状态：</td>
						<td>
							<c:if test="${order.baseStatus == 1 }" >未处理</c:if>
							<c:if test="${order.baseStatus == 2 }" >已确认</c:if>
							<c:if test="${order.baseStatus == 3 }" >已挂起</c:if>
							<c:if test="${order.baseStatus == 4 }" >已完成</c:if>
							<c:if test="${order.baseStatus == 5 }" >已作废</c:if>
							<c:if test="${order.baseStatus == 6 }" >客服取消</c:if>
						</td>
						<td></td>
					</tr>

					<tr>
						<td>支付状态：</td>
						<td>
							<c:if test="${order.payStatus == 7 }" >未支付</c:if>
							<c:if test="${order.payStatus == 8 }" >用户线下支付</c:if>
							<c:if test="${order.payStatus == 9 }" >已支付</c:if>
							<c:if test="${order.payStatus == 10 }" >退款申请中</c:if>
							<c:if test="${order.payStatus == 11 }" >部分退款</c:if>
							<c:if test="${order.payStatus == 12 }" >已取消</c:if>
							<c:if test="${order.payStatus == 13 }" >退款失败</c:if>
						</td>
						<td></td>
					</tr>

					<tr>
						<td>配送状态：</td>
						<td>
							<c:if test="${order.deliveryStatus == 14 }" >准备</c:if>
							<c:if test="${order.deliveryStatus == 15 }" >部分发货</c:if>
							<c:if test="${order.deliveryStatus == 16 }" >已发货</c:if>
							<c:if test="${order.deliveryStatus == 17 }" >已收货</c:if>
							<c:if test="${order.deliveryStatus == 18 }" >拒收</c:if>
							<c:if test="${order.deliveryStatus == 19 }" >部分退货</c:if>
							<c:if test="${order.deliveryStatus == 20 }" >已取消发货</c:if>
							<c:if test="${order.deliveryStatus == 21 }" >已终止发货</c:if>
							<c:if test="${order.deliveryStatus == 22 }" >拣货中</c:if>
						</td>
						<td></td>
					</tr>

					<tr>
						<td>用户姓名：</td>
						<td>
							${order.weuser.username}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>联系手机：</td>
						<td>
							${order.weuser.uphone}
						</td>
						<td></td>
					</tr>
					<tr>
						<td>收件人姓名：</td>
						<td>
							${order.s_name}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>收件人手机：</td>
						<td>
							${order.s_phone}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>收货地址：</td>
						<td>
							${order.s_address}
						</td>
						<td></td>
					</tr>

					<c:if test="${order.payStatus == 9 }" >
						<c:if test="${order.baseStatus == 2 }" >
							<c:if test="${order.deliveryStatus == 14 }" >
								<tr>
									<td>快递公司：</td>
									<td>
										<jsp:include page="../logisticsName.jsp" />
									</td>
									<td></td>
								</tr>

								<tr>
									<td>快递单号：</td>
									<td>
										<input type="text" id="expressorderid" name="expressorderid" />
									</td>
									<td></td>
								</tr>
							</c:if>
						</c:if>
					</c:if>

					<c:if test="${order.deliveryStatus == 16 }" >
						<tr>
							<td>快递公司：</td>
							<td>
								${order.logisticsName}
							</td>
							<td></td>
						</tr>

						<tr>
							<td>快递单号：</td>
							<td>
									${order.expressOrderId}
							</td>
							<td></td>
						</tr>
					</c:if>

					<tr>
						<td colspan="3">&nbsp;</td>
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

		<tr>
			<td width="70%" colspan="3">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
					<tr>
						<th>序号</th>
						<th>商品名称</th>
						<th>商品规格</th>
						<th>单价</th>
						<th>数量</th>
						<th></th>
					</tr>
					</thead>

					<tbody>
					<c:if var="havedata" test="${not empty detailList }"></c:if>
					<c:if test="${!havedata }">
						<td colspan="8" align="center">暂无数据</td>
					</c:if>
					<c:if test="${havedata }">
						<c:forEach items="${detailList }" var="detail" varStatus="v">
							<tr>
								<td>${v.count }</td>
								<td>${detail.prodName}</td>
								<td>${detail.commoditySpecificationStr}</td>
								<td>${detail.prodUnitPrice}</td>
								<td>${detail.commodityNum}</td>
								<td><img src="/${detail.commodityImage}" width="50px;" height="50px;"></td>

							</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</td>
		</tr>

		<tr>
			<td width="5%">&nbsp;</td>
			<td width="40%">
				<c:if test="${order.payStatus == 9 }" >
					<c:if test="${order.baseStatus == 2 }" >
						<c:if test="${order.deliveryStatus == 14 }" >
							<a class="btn btn-sm btn-success" onclick="deliver();"> 发货 </a>
						</c:if>
					</c:if>
				</c:if>

				<%--<a class="btn btn-sm btn-success" onclick="returnoverview();"> 返回 </a>--%>
			</td>
			<td width="20%">&nbsp;</td>
		</tr>
	</table>

	</form>

	<script type="text/javascript">
		window.onload = function() {
			var inputreset = document.getElementById("reset");
			if(inputreset.value.indexOf("1") == 0)
			{
				alert("重置成功!", "");
			}
		}
	</script>
</body>
</html>