<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach items="${commoditys}" var="o">
	<li class="jade sort-prod-lastNew"><a
		href="user_getCommodityDetail.do?cid=${o.id}"> 
		<c:if test="${o.commodityStore==0 }">
            	<img  class="hadSaled" src="/image/yisale.png" style="width:45%;"/>
            </c:if>
		<img src="${o.acquiescent_picture}"><br>
            <span class="zhubaoPrice">￥<fmt:formatNumber value="${o.defaultPrice}" pattern="#0.#"/>
            </span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
            <span class="zhubaoState">${o.commodity_name}</span><br>
            <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
	</a></li>
</c:forEach>