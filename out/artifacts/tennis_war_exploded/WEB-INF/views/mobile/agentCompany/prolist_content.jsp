<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<c:forEach items="${commoditys }" var="o">
		<li class="jade sort-prod-lastNew" id="Content-li_${o.id}"><a
			href="user_getCommodityDetail.do?cid=${o.id}"> <c:if
					test="${not empty o.acquiescent_picture}" var="hava">
					<img src="${o.acquiescent_picture}">
					<br>
				</c:if> <c:if test="${!hava }">
					<img src="/image/nophoto_120X120.jpg" />
					<br>
				</c:if> 
<%-- 				<span  class="texthidden" >${o.commodity_name } </span><br> --%>
<%-- 			<span class="proPrice">￥${o.defaultPrice } </span> --%>
<span class="zhubaoPrice">￥${o.defaultPrice } </span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
                    <span class="zhubaoState">${o.commodity_name }</span><br>
                    <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
		</a></li>
	</c:forEach>
