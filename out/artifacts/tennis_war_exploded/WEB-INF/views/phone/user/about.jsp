<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>关于U橙</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">

<script src="/js/common.js"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/about.css">
<!--本页面单独使用文件 end-->
<%@ include file="./common/common_init.jsp" %>
</head>

<body class="lz-body">
<main class="lz-main">
    <section class="lz-mb lz-version">
        <img src="/images/logo.png" alt="U橙logo">
        <p>U橙&nbsp;&nbsp;${version }</p>
    </section>
    <section class="lz-mb">
        <ul class="lz-list-2">
        	<c:if test="${mobile == 'iphone' }">
        		 <li data-lz-url="#">
	                <div class="lz-left"><i class="lz-iconfont lz-icon-shixing" style="color:#E2982E;"></i><span class="lz-tit">去评分</span></div>
	                <div class="lz-right"><i class="lz-iconfont lz-icon-jiantouright"></i></div>
	            </li>
        	</c:if>
           
            <li data-lz-url="#" onclick="window.pay.toGuide();">
                <div class="lz-left"><i class="lz-iconfont lz-icon-yemian" style="color:#FB5A5A;" ></i><span class="lz-tit">欢迎页</span></div>
                <div class="lz-right"><i class="lz-iconfont lz-icon-jiantouright"></i></div>
            </li>
            <li data-lz-url="#"  onclick="window.location.href='/about_user.do?type=2';" >
                <div class="lz-left"><i class="lz-iconfont lz-icon-woshou" style="color:#2E8FE2;"></i><span class="lz-tit">服务协议</span></div>
                <div class="lz-right"><i class="lz-iconfont lz-icon-jiantouright"></i></div>
            </li>
            <li data-lz-url="#" onclick="window.location.href='/about_user.do?type=1';">
                <div class="lz-left"><i class="lz-iconfont lz-icon-qunren" style="color:#00C280;"></i><span class="lz-tit">关于我们</span></div>
                <div class="lz-right"><i class="lz-iconfont lz-icon-jiantouright"></i></div>
            </li>
        </ul>
    </section>
    <section class="lz-mb">
        <p class="lz-aboutcon">弘金地网球<br>
            www.grandale.com
        </p>
    </section>
</main>

</body>
</html>
