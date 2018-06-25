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
    <title>购买会员证</title>
    <link rel="stylesheet" href="/css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">支付订单</a>
    </p>
</div>
<div class="pro-dollar">
    <p>请选择支付方式并付款</p>
    	<span>商品：会员证</span><br>
        <span>金额：<a class="dollar-img05"> ￥${Amount}元</a> </span>
    </p>
</div>
<div class="choicePays" xonclick="getVIP_PayOrderNo(1)" xid="wechat" onclick="window.location.href='vipcardpaytype_code.do?paytype=1'">
    <div class="choice-wechat01"  >
        <span class="wechat-img06"></span>
        <span class="wechat-text"><a >微信App支付</a></span>
        <a > <span class="arrowRig-img"></span></a>
    </div>
</div>
    <div class="choicePays"  xonclick="getVIP_PayOrderNo(2)" xid="alipay" onclick="window.location.href='vipcardpaytype_code.do?paytype=2'">
        <div class="choice-wechat01">
            <span class="alipay-img07"></span>
            <span class="wechat-text"><a>支付App支付</a></span>
            <a  > <span class="arrowRig-img"></span></a>
        </div>
    </div>
<!--     <div class="choicePays"> -->
<!--         <div class="choice-wechat01"> -->
<!--             <span class="alipay-pc-img08"></span> -->
<!--             <span class="wechat-text"><a href="union_card.html">银行卡支付</a></span> -->
<!--             <a href="union_card.html"> <span class="arrowRig-img"></span></a> -->
<!--         </div> -->
<!--     </div> -->
  <!--  <span class="zfbPocket-img07">支付宝钱包支付</span><br>
    <span class="zfbPC-img08">支付宝网页支付</span>-->
</body>
<script src="/js/userFunction.js"></script>
<script src="/js/iospayvip.js"></script>
</html>