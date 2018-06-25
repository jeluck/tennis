<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach items="${commission.dataList }" var="o">
	<li class="count-li"><c:if
			test="${not empty o.acquiescent_picture}" var="hava">
			<img class="count-order-list-img" src="${o.acquiescent_picture }" />
		</c:if> <c:if test="${!hava }">
			<img class="count-order-list-img" src="/image/nophoto_120X120.jpg" />
		</c:if> <span class="count-orderDetail">订单：${o.orderInfoNo} <br>商品：${o.commodityName}
			<br> <a>佣金：${o.totalcommission}</a><br> <a
			class="countTime">${o.create_time}</a>
	</span><br> <span class="countMoney"> <a
			style="color: #F25D5F; margin-left: -16px;">已入账</a>
	</span>
		<div class="clear"></div></li>
</c:forEach>
