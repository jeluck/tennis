<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>提现账户</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
</head>
<body>
<div class='header'>
    <p>
        <a class="back" href="javascript:history.go(-1)"></a>
        <a class="loginTitle">提现账户</a>
    </p>
</div>
<div class="spacediv"></div>
<div class="getPay">
    <div class="choice-wechat01">
        <span class="getPay-img01"></span>
        <span class="getPay-text">
            <a>我的银行卡</a><br>
            <c:if test="${not empty w.card_num_show }" var="hava">
            	<a>${w.card_num_show}</a>
            </c:if>
            <c:if test="${!hava}">
            <a style="color:#ED5456;">未绑定</a>
            </c:if>
        </span>
        <a href="user_myCardPay.do"> <span class="arrowRig-img" style="top:-65px;"></span></a>
    </div>
</div>
<div class="getPay">
    <div class="choice-wechat01">
        <span class="getPay-img02"></span>
        <span class="getPay-text">
            <a>我的支付宝</a><br>
			<c:if test="${not empty w.alipay_account_show }" var="hava">
            	<a>${w.alipay_account_show}</a>
            </c:if>
            <c:if test="${!hava}">
            <a style="color:#ED5456;">未绑定</a>
            </c:if>
        </span>
        <a href="user_myAliPay.do"> <span class="arrowRig-img" style="top:-65px;"></span></a>
    </div>
</div>
<div class="whatGetPUser">
    <a href="">什么是提现账户？</a>
</div>
</body>
</html>