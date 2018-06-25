<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地-首页</title>
    <link href="/weixin/css/common.css" rel="stylesheet">
    <link href="/weixin/css/iconfont/iconfont.css" rel="stylesheet">
    <script src="/weixin/js/common.js"></script>
    <!-- mobile slider plugin start -->
    <link href="/js/plugin/swiper.3.1.7.min.css" rel="stylesheet">
    <script src="/js/plugin/swiper.3.1.7.min.js"></script>
    <!-- mobile slider plugin end -->
</head>

<body flex="dir:top">
    <div flex-box="0">
        <header class="header" flex="main:justify cross:stretch">
            <a class="icon" flex-box="0" href="#"><i class="iconfont icon-fanhui"></i></a>
            <div class="text" flex-box="0">下载</div>
            <div class="icon"></div>	
        </header>
    </div>
    <div class="download" flex="dir:top" flex-box="1">
        <div class="btn-box" flex="main:justify cross:stretch" flex-box="1">
            <div class="ios" flex="dir:top">
                <h2>IOS</h2>
                <a href="#">教练端</a>
                <a href="#">用户端</a>
            </div>
            <div class="android" flex="dir:top">
                <h2>安卓版</h2>
                <a href="${coach }">教练端</a>
                <a href="${user }">用户端</a>
            </div>
        </div>
        <div class="bq" flex-box="0">弘金地网球版权所有&copy;2015</div>
    </div>
</body>

</html>