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
    <title>商品管理</title>
    <link rel="stylesheet" href="/css/c.css"/>
    <link rel="stylesheet" href="/css/prodCtrol.css"/>
</head>
<body style="background-color:#EBEBEB;">
<div class="index">
    <div class="leftDiv"  onclick="window.location.href='toaddProduces.do'">
        <span class="leftImg"></span><br>
        <span class="indexText">发布商品</span>
    </div>
    <div class="rightDiv" onclick="window.location.href='tomyProducts.do'">
        <span class="rightImg"></span><br>
        <span class="indexText">管理商品</span>
    </div>

</div>
<div>
    <img class="bottomImg" src="../images/indexImg.png"/>
</div>