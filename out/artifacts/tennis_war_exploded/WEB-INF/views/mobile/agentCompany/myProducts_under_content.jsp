<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach items="${underlist_page.dataList }" var="o">
	<li class="Prod-Content-li" id="under_Content-li_${o.id}">
		<div class="li-one">
			<span> <c:if test="${not empty o.acquiescent_picture}"
					var="hava">
					<img class="count-order-list-img" src="${o.acquiescent_picture}">
				</c:if> <c:if test="${!hava }">
					<img class="count-order-list-img" src="/image/nophoto_120X120.jpg" />
				</c:if>
			</span> <span class="count-orderDetail">${o.commodity_name }<br>
				<a>￥${o.defaultPrice } &nbsp;<em style="display: none;">&nbsp;佣金：￥30</em></a><br>
				<a class="countTime"><abbr>销量&nbsp;${o.sales_volume }</abbr> <abbr
					class="backSpace">库存&nbsp;${o.commodityStore}</abbr><abbr
					class="backSpace">${o.date_mm_dd}</abbr> </a>
			</span><br>
			<div class="clear"></div>
		</div>

		<div class="prod-ssdown">
			<ul style="display: block;">
				<li class="ssdown" onclick="agent_commodityedit('${o.id}');"><span
					class="edit-img01"></span>编辑</li>
				<c:if test="${o.listing=='1' }">
                        	<li class="ssdown" onclick="agent_oncommodity('${o.id}');"><span class="add-img02"></span>上架</li>
                        </c:if>
                        <c:if test="${o.listing=='2' }">
                        	<li class="ssdown"><span class=""></span>管理员驳回</li>
                        </c:if>
				<li class="ssdown"
					onclick="agent_deleteCommodity('${o.id}','${o.commodity_name}');"><span
					class="delete-img03"></span>删除</li>
			</ul>
		</div>
	</li>
</c:forEach>
