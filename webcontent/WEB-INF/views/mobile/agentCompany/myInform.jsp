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
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">个人资料</a>
    </p>
</div>
<div class="inform photo">
    <div class="choice-wechat01">
        <span class="inform-img00"></span>
        <span class="inform-text"><a href="weixinzhifuAPI.html">我的昵称</a></span>
        <a href="weixinzhifuAPI.html"> <span class="arrowRig-img"><img class="photo-img" src="../../images/products/orderPro-list_03.png"/></span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img01"></span>
        <span class="inform-text"><a href="weixinzhifuAPI.html">我的昵称</a></span>
        <a href="weixinzhifuAPI.html"> <span class="arrowRig-text">钟高提</span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img02"></span>
        <span class="inform-text"><a>修改登录密码</a></span>
        <a href="https://www.alipay.com/"> <span class="arrowRig-text"></span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img03"></span>
        <span class="inform-text"><a href="weixinzhifuAPI.html">绑定手机</a></span>
        <a href="https://www.alipay.com/"> <span class="arrowRig-text">未绑定</span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img04"></span>
        <span class="inform-text"><a href="weixinzhifuAPI.html">绑定微信</a></span>
        <a href="https://www.alipay.com/"> <span class="arrowRig-text">钟高提</span></a>
    </div>
</div>
<!--    <span class="zfbPocket-img07">支付宝钱包支付</span><br>
    <span class="zfbPC-img08">支付宝网页支付</span>-->
</body>
</html>