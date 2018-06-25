<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>           
<!doctype html>
<html class="lz-html" lang="en">

	<head>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	  <meta name="apple-mobile-web-app-capable" content="yes">
	  <meta name="apple-mobile-web-app-status-bar-style" content="black">
	  <meta name="format-detection" content="telephone=no">
	  <title>
	  	<c:if test="${flag == 1 }"> 支付成功 </c:if>
    	<c:if test="${flag == 2 }"> 支付失败 </c:if>
    	<c:if test="${flag == 3 }"> 订单过期 </c:if>
	  </title>
	  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
	  <link rel="stylesheet" type="text/css" href="/css/common.css">
	
	  <script src="/js/common.js"></script>
	  <style type="text/css">
	    /* 支付宝颜色 */
	    .lz-pay-box {
	      color: #2E7AD9; 
	    }
	    
	    /* 余额支付颜色 */
	    .lz-pay-box {
	      color: #ED4332; 
	    }
	    
	    /* 微信支付颜色 */
	    .lz-pay-box {
	      color: #45C01A; 
	    }
	  </style>
	</head>

<body class="lz-body">
  <div class="lz-pay-box" 
  		<c:if test="${payType == 1 }"> style="color: #2E7AD9;" </c:if>
	  	<c:if test="${payType == 2 }"> style="color: #ED4332;" </c:if>
	  	<c:if test="${payType == 3 }"> style="color: #45C01A;" </c:if> >
   
    <c:if test="${flag == 1 }"> <i class="lz-iconfont lz-icon-dui"></i> </c:if>
    <c:if test="${flag == 2 }"> <i class="lz-iconfont lz-icon-cuo"></i> </c:if>
    <c:if test="${flag == 3 }"> <i class="lz-iconfont lz-icon-jinggao"></i> </c:if>
    <p class="lz-pay-content">
    	<c:if test="${flag == 1 }"> 恭喜使用 </c:if>
    	<c:if test="${payType == 1 }"> 支付宝 </c:if>
	  	<c:if test="${payType == 2 }"> 余额 </c:if>
	  	<c:if test="${payType == 3 }"> 微信 </c:if>
	  	<c:if test="${flag == 1 }"> 支付成功！</c:if>
	  	<c:if test="${flag == 2 }"> 支付失败！</c:if>
	  	<c:if test="${flag == 3 }"> 订单已过期！ </c:if>
    </p>
  </div>
  <c:if test="${empty playgroundmanager }">
  <div class="lz-pay-btn">
    <div class="lz-pay-cell"><a class="lz-pay-a" href="javascript:order();">查看订单</a></div>
    <div class="lz-pay-cell"><a class="lz-pay-a" href="javascript:buy();">继续逛逛</a></div>
  </div>
  </c:if>
</body>
<script src="/js/ios/ios_common.js"></script>
</html>