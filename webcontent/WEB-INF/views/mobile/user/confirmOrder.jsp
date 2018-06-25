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
    <title>确定订单</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
    <link rel="stylesheet" href="/css/personMyOrder.css"/>
    <style>
    span.pro-list-weight {
		padding-left: 10px;
		position: relative;
		color: #b4b4b4;
		top: -20px;
	}
    </style>
    <script src="../../js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">确定订单</a>
    </p>
</div>
<c:if test="${not empty address }" var="hava"></c:if>
<div class="addSumit" <c:if test="${!hava }">style="height: 120px;"</c:if>>
    <span class="hengtiao-img"></span><br>
    <span style="padding-left: 4%;" >收货信息</span><br>
    <c:if test="${hava}" >
	        <span class="receiveMes">${address.consignee }&nbsp; ${address.phone } <br>
	        ${address.province }${address.city }${address.area }${address.address }</span>
	  <!--   <span class="addressRight"></span> -->
    </c:if>
    <c:if test="${!hava }">
     <div>
        <input type="button" class="newButton" value="添加地址" onclick="tonewaddr('orderCome')"/>
    </div> 
<!--      <span class="newAddr-img04"></span> <span class="newAddr-text">添加新地址</span> -->
    </c:if>

<div class="leaveMesg">
   <span>留&nbsp; 言 &nbsp; </span> <input class="leaveSMS" style="border:none;" type="text" placeholder="给卖家留言（选填）"/>
</div>
<!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
<c:forEach items="${carts}" var="o">
<div class="proDetail orderD-common">
    <div class="expI-content orderD-com-cont">
        <span><img class="pro-list-img" src="${o.commodiy_image}"></span><br>
        <span class="pro-list-name">${o.commodityname} &nbsp; ${o.commodityType_desc}</span><br>
        <div class="price-num"> <span class="price">￥<fmt:formatNumber value="${o.product_price}" pattern="#00.#"/></span><br>
            <span class="num">   &nbsp;&nbsp;&nbsp;   x${o.product_num}</span>
        </div>
        <span class="clear"></span><br>
<%--         <span class="pro-list-weight">${o.commodityType_desc}</span> --%>
    </div>
    <div class="clear"></div>
<!--     <div class="total-pro"> -->
<!--         <span>共有<a class="">1</a>件商品</span> &nbsp; -->
<!--         <span class="expPay">运费：￥<a class="">0</a></span> &nbsp; -->
<!--         <span class="">实付：<a class="totalPay">￥58</a></span> -->
<!--     </div> -->
</div>
</c:forEach>
<!-- end 反复运用代码 结束 -->
<div  style="height: 30px;">
</div>
<form action="generate_order.do" name="form1">
	<div class="submitOrder">
	    <span>实付款：￥ ${price} </span>
	    <a class="confirmOrder" id="submit" onclick="generate_order()">提交订单</a>
	</div>
	<c:if test="${hava}" >
	<input type="hidden" value="${address.id }" name="addressid" id="addressid">
    </c:if>
</form>
</body>
<script src="/js/userFunction.js"></script>
</html>