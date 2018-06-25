<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>网球圈列表</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
</head>

<body class="lz-body">
    <ul class="group-ul">
    	<c:forEach items="${advertiseList }" var="o" >
    		<li class="group-li">
	            <div class="group-pic">
	                <img class="group-img" src="${o.ad_picture_url }" onclick="location.href = '${o.ad_http_url}';"  alt="">
	            </div>
	           <div class="group-about">${o.ad_title }</div>
	        </li>
    	</c:forEach>
    </ul>
</body>
</html>
