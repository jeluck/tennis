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
<title>身份认证</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js?${getVersion}"></script>
<script src="/js/admin_common.js?${getVersion}"></script>
<script src="/js/common.js?${getVersion}"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/user.css?${getVersion}">
<link rel="stylesheet" href="/css/ChoiceCity.css?${getVersion}">
<script src="/js/user.js?${getVersion}"></script>
<script src="/js/ChoiceCity.js?${getVersion}"></script>
<!--本页面单独使用文件 end-->
<%@ include file="./common/common_init.jsp" %>
</head>
<body class="lz-body">
<main class="lz-view">	
	<section class="lz-usre">
        <h2 class="lz-tit-4">身份认证-设置信息，方便交友，经营自己的品牌。</h2>
        <input id="userId" type="hidden" value="${o.id }">
        <ul class="lz-user-list">
            <li>
                <span class="lz-left">身份证正面</span>
                <span class="lz-right" onclick="auth_tion('1', 'img1')">
                	<c:if test="${not empty o.idcard_photo_positive}">
                   		<img class="headimg" id="img1" width="60" height="60" src="/${o.idcard_photo_positive }" >
                   	</c:if>
                   	<c:if test="${empty o.idcard_photo_positive}">
                   		<img class="headimg" id="img1" width="60" height="60" src="/image/morentouxiang.png"  alt="身份证正面" >
                   	</c:if>
                   	<span id="img1erroe"></span>
                </span>
            </li>
            <li>
                <span class="lz-left">身份证反面</span>
                <span class="lz-right" onclick="auth_tion('2', 'img2')">
                	<c:if test="${not empty o.idcard_photo_anti}">
                   		<img class="headimg" id="img2" width="60" height="60" src="/${o.idcard_photo_anti }" >
                   	</c:if>
                   	<c:if test="${empty o.idcard_photo_anti}">
                   		<img class="headimg" id="img2" width="60" height="60" src="/image/morentouxiang.png"  alt="身份证反面" >
                   	</c:if>
                	<span id="img2erroe"></span>
                </span>
            </li>
        </ul>
        <ul class="lz-user-list">
       		<li>
                <span class="lz-left">身份证号码</span>
                <span class="lz-right"><input type="text" id="idcard_no" placeholder="输入身份证号码"  value="" maxlength="18" /><span id="noerroe"></span></span>
            </li>
       	</ul>
       	
<!--        	 <li> -->
<!--           	<input type="button" onclick="saveAuth_tion()" value="保存"> -->
<!--           </li> -->
    </section>
    </main>
</body>
</html>