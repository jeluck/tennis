<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach items="${orders.dataList }" var="o">
	<!-- 付款及关闭订单 -->
	<li>
		<!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
		<div class="proDetail orderD-common">
			<c:forEach items="${o.orderSubs }" var="os">
				<div class="orderD-head">
					<span class="proI-img" style="background-position: -428px -78%;"></span>
					<span class=" ">${os.agentCompany.company_name }&nbsp;<a
						class="orderStatus">待付款</a></span><br>
				</div>
				<c:forEach items="${os.orderDetail4subs }" var="o4s">
					<div class="expI-content orderD-com-cont">
						<span><a
							href="user_getCommodityDetail.do?cid=${o4s.commodityId}"><img
								class="pro-list-img" src="${o4s.commodityImage }"></a></span><br>
						<span class="pro-list-name">${o4s.prodName }&nbsp;
							${o4s.commoditySpecificationStr }</span><br>
						<div class="price-num">
							<span class="price">￥<fmt:formatNumber value="${o4s.prodUnitPrice }" pattern="#00.#"/></span><br> <span
								class="num"> &nbsp;&nbsp;&nbsp; x${o4s.commodityNum }</span>
						</div>
						<span class="clear"></span><br> <span class="pro-list-weight">${o4s.commoditySpecificationStr
							}</span>
					</div>
				</c:forEach>
				<div class="clear"></div>
			</c:forEach>
			<div class="clear"></div>
			<div class="total-pro">
				<span>共有<a class="">${o.prodTotalNum }</a>件商品
				</span> &nbsp; <span class="expPay">运费：￥<a class=""><fmt:formatNumber value="${o.expTotalPrice}" pattern="#00.#"/>
				</a></span> &nbsp; <span class="">实付：<a class="totalPay">￥<fmt:formatNumber value="${o.totalAmount}" pattern="#00.#"/></a></span>
			</div>
			<div class="orderPay-Close">
				<c:if test="${payStatus==7 }">
					<input class="orderPay" type="button" value="付款" onclick="window.location.href='confirmOrder.html'" />
				</c:if>
				<c:if test="${payStatus==16 }">
					<input class="close" type="button" value="退货" onclick = "deliveryRefuse('${o.orderMainNo}')"/>
				</c:if>
				
				<!--                         <input class="close" type="button" value="关闭订单" onclick = "window.location.href=('closeOrder.html')"/> -->
			</div>
		</div> <!-- end 反复运用代码 结束 -->
	</li>
</c:forEach>
