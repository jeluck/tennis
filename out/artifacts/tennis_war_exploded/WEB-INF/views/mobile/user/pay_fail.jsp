<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>支付失败</title>
    <link  rel="stylesheet" href="../../css/c.css">
    <link rel="stylesheet" href="../../css/paySuccess.css">
</head>
<body>
<div class="paySuccess" style="color: red;">
    <span><img class="payFail" src="/images/payFailure.png"></span><br>
    支付失败，请重新支付！
</div>
<div class="payCG">
    <c:if test="${not empty buy_vip_order }" var="hava">
    	<span class="payCheck"><a href="/toindex.do">返回首页</a></span>
    </c:if>
    <c:if test="${!hava}">
    	<span class="payCheck"><a href="#">查看订单</a></span>
    </c:if>
</div>-->
</body>
</html>