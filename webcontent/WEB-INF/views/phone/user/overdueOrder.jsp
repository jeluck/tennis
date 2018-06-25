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
	  	订单过期
	  </title>
	  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
	  <link rel="stylesheet" type="text/css" href="/css/common.css">
		<%@ include file="./common/common_init.jsp" %>
	
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
  <div class="lz-pay-box" style="color: #2E7AD9;"  >
   
    <i class="lz-iconfont lz-icon-jinggao"></i>
    <p class="lz-pay-content">
    	订单已过期!
    </p>
  </div>
  <div class="lz-pay-btn">
    <div class="lz-pay-cell"><a class="lz-pay-a" href="#">查看订单</a></div>
    <div class="lz-pay-cell"><a class="lz-pay-a" href="#">继续逛逛</a></div>
  </div>
</body>
</html>