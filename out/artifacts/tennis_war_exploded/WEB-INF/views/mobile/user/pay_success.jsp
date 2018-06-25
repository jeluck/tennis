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
    <title>支付成功</title>
    <link  rel="stylesheet" href="../../css/c.css">
    <link rel="stylesheet" href="../../css/paySuccess.css">
</head>
<body>
<div class="paySuccess">
    <span class="paySuccess-img"></span><br>
    恭喜您支付成功！
</div>
<div class="payCG">
    <c:if test="${not empty buy_vip_order }" var="hava">
    	<span class="payCheck" onclick="gotohome();"><a href="/toindex.do">返回首页</a></span>
    </c:if>
    <c:if test="${!hava}">
    	<span class="payCheck" onclick="gotomy();"><a href="/touser.do">个人中心</a></span>
    	<span class="payGoOn"><a href="/toindex.do">继续逛逛</a></span>
    </c:if>
</div>
</body>
<script type="text/javascript">
function gotomy(){
	try{
		window.pay.goTOMy();//跳到个人中心
	}catch (e) {
		
	}
}
function gotohome(){
	try{
		window.pay.goTOHome();//跳到首页
	}catch (e) {
		
	}
}
</script>
</html>