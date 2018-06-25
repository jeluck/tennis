<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>我的账户</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/js/common.js"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/recharge.css">
<%@ include file="./common/common_init.jsp" %>
<script src="/js/recharge.js"></script>
<!--本页面单独使用文件 end-->
</head>
<body class="lz-body">
<main class="lz-main">
     <!-- 交易记录 start -->
    <section class="lz-mb">
        <h2 class="lz-tit-4">注：单位（元）</h2>
        <table class="lz-log-list"  cellspacing="0" cellpadding="0">
            <tr class="lz-tit">
                <th>交易日期</th>
                <th>交易收入</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;结算&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;补贴&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;&nbsp;</th>
            </tr>
           	<c:forEach items="${list }" var="s">
			    <tr class="lz-list" >
			    	
	                <td>${fn:substring(s.play_date, 5, 11)}</td>
	                <td>${s.total_amount }</td>
	                <td>
	                	<c:if test="${s.trade_balance_status == 0 }">
	                		<i class="lz-iconfont lz-icon-guanbi"></i>
	                	</c:if>
	               	    <c:if test="${s.trade_balance_status == 1 }">
	                		<i class="lz-iconfont lz-icon-xiaogou"></i>
	                	</c:if>
	                </td>
	                <td>${s.subsidies_money }</td>
	                <td>
	                	<c:if test="${s.subsidies_grant_status == 0 }">
	                		<i class="lz-iconfont lz-icon-guanbi"></i>
	                	</c:if>
	               	    <c:if test="${s.subsidies_grant_status == 1 }">
	                		<i class="lz-iconfont lz-icon-xiaogou"></i>
	                	</c:if>
	                </td>
	            </tr>
	        </c:forEach>
        </table>
    </section>
    <!-- 交易记录 end -->
</main>
</body>
</html>
