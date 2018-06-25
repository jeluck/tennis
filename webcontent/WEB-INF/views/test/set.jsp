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
    <title>设置</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">设置</a>
    </p>
</div>
<div>
    <ul>
        <li class="set-li">
            <span class="set-img01"></span><span class="set-text">消息</span><br>
        </li>
        <li class="set-li">
            <span class="set-img02"></span><span class="set-text">通用</span><br>
        </li>
        <li class="set-li">
            <span class="set-img03"></span><span class="set-text">意见反馈</span><br>
        </li>
        <li class="set-li">
            <span class="set-img04"></span><span class="set-text">关于微店</span><br>
        </li>
        <li class="set-li">
            <span class="set-img05"></span><span class="set-text">检查新版本<a class="version">最新版V1.1.0</a></span><br>
        </li>
    </ul>
    <input type="button" class="SetdButton" style="margin-top: 12px;" value="退出当前登录" onclick="agentlogout();"/>
</div>
 <script src="/js/agentCompany.js"></script>
</body>
</html>