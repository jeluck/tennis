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
    <title>商品详情</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/personMyOrder.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">商品详情</a>
    </p>
</div>
<div class="spacediv"></div>
<div style="border-bottom: 10px solid #ebebeb;background: #fff;">
    <span >
    	<c:if test="${not empty o.acquiescent_picture}">
    		<img class="max-img" src="${o.acquiescent_picture}"/>
    	</c:if>
    </span><br>
    <span  class="pro-state"> ${o.commodity_name}</span><br>
    <span class="redText" style="padding: 4%;">￥${o.defaultPrice}</span>
</div>

<div style="background-color:#fff;">
    <span class="prodDetail">商品详情</span><br>
    <div style="border-top: 2px solid #ebebeb;">
         <span style="padding:4%;color: #7D7D7D;">${o.introduction}</span><br>
          <c:if test="${not empty o.acquiescent_picture}">
    		<img class="max-img" src="${o.acquiescent_picture}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_1}">
    		<img class="max-img" src="${o.picture_1}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_2}">
    		<img class="max-img" src="${o.picture_2}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_3}">
    		<img class="max-img" src="${o.picture_3}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_4}">
    		<img class="max-img" src="${o.picture_4}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_5}">
    		<img class="max-img" src="${o.picture_5}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_6}">
    		<img class="max-img" src="${o.picture_6}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_7}">
    		<img class="max-img" src="${o.picture_7}"/>
    	</c:if>
    </div>

</div>
<!-- <div class="prodDetailFooter" style="display:none;"> -->
<!--    <ul> -->
<!--        <li class="waitCom"> -->
<!--            <span class="myCount"></span> -->
<!--              红色圆圈带有数字 -->
<!--             <span class="redRound-img"><a class="redRound-num">2</a></span> -->
<!--            <br> -->
<!--            <a>我的账号</a> -->
<!--        </li> -->
<!--        <li class="waitCom"> -->
<!--            <span class="myCart "></span> -->
<!--            红色圆圈带有数字 -->
<!--            <span class="cart-redRound-img"><a class="cart-redRound-num">2</a></span> -->
<!--            <br> -->
<!--            <a>购物车</a> -->
<!--        </li> -->
<!--        <li class="waitCom" style="width:30%;"> -->
<!--            <input class="addCart" type="button" value="加入购物车"/> -->
<!--        </li> -->
<!--        <li class="waitCom" style="width:22%;"> -->
<!--            <input class="nowPay" type="button"  value="立即购买"/> -->
<!--        </li> -->
<!--    </ul> -->
<!-- </div> -->
<!-- <div style="height:80px;"></div> -->
</body>
</html>