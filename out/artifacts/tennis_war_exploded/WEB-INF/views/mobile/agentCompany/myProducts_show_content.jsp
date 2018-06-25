<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach items="${data_page.dataList }" var="o">
	<li class="Prod-Content-li" id="Content-li_${o.id}"><span>
			<c:if test="${not empty o.acquiescent_picture}" var="hava">
				<img class="count-order-list-img" src="${o.acquiescent_picture}">
			</c:if> <c:if test="${!hava }">
				<img class="count-order-list-img" src="/image/nophoto_120X120.jpg" />
			</c:if>
	</span> <span class="count-orderDetail">${o.commodity_name }<br> <a>￥${o.defaultPrice
				} &nbsp;<em style="display: none;">&nbsp;佣金：￥30abbr</em><br> <a
				class="countTime"> <%--                         <abbr >销量&nbsp;${o.sales_volume }</abbr> --%>
					<abbr class="backSpace">库存&nbsp;${o.commodityStore}</abbr><abbr
					class="backSpace">${o.date_mm_dd}</abbr>
			</a></span><br>
		<div class="clear"></div>
		<div class="prod-ssdown">
			<ul style="display: block;">
				<li class="ssdown" style="width: 50%; text-align: left;"
					onclick="getCommodityDetail('${o.id}');"><span
					class="ssdown-img01"></span>查看</li>
				<!--                         <li class="ssdown"> -->
				<!--                            <input type="button" class="ssdown-img02" value="" onclick="window.loacation.href='' " />分享 -->
				<!--                         </li> -->
				<li class="ssdown" onclick="agent_undercommodity('${o.id}');"><span
					class="ssdown-img03"></span>下架</li>
			</ul>
		</div></li>
</c:forEach>
