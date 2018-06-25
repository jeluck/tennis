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
    <title>线下支付</title>
    <link rel="stylesheet" href="/css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
</head>
<body>
<div class="pro-dollar">
    <p class="sixty">请选择支付方式并付款</p>
    <p class="pro-dollar-text"> 
	    	<c:if test="${not empty om.orderSubs }">
    		<c:forEach items="${om.orderSubs}" var="os" >
    			<c:forEach items="${os.orderDetail4subs}" var="od">
    			<span>订单号：${om.orderMainNo}</span><br>
    			<span>商品：${od.prodName }</span><br>
    			</c:forEach>
    		</c:forEach>
    	</c:if>
        <span>金额：<a class="dollar-img05"> ￥${om.totalAmount}元</a> </span>
        <input name="orderMainNo" value="${om.orderMainNo}" id="orderMainNo" type="hidden">
    </p>

</div>
<div class="center">
	<c:if test="${paytype==1}">
  		<img class="payScan-img" src="/image/weixin.png"/>
  		
  	</c:if>
  	<c:if test="${paytype==2}">
  		<img class="payScan-img" src="/image/zhifubao.png"/>
  	</c:if>
</div>
<div>
<!--     <div  class="leftPay"> -->
<!--     <a class="sixty">备注：</a> -->
<!--     <p>开户银行：招商银行</p> -->
<!--     <p>开户姓名：王某某</p> -->
<!--     <p class="bank">开户卡号：6464564616123132132121123</p> -->
<!--     </div> -->
<br/>
    <div class="center">
    <input type="button" class="submit" value="已付款" onclick="userhandlepay('${om.orderMainNo}')" style="width: 90%;"/>
    </div>
</div>
</body>
<script src="/js/userFunction.js"></script>
</html>