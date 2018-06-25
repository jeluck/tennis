<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>账号充值</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
<script src="/js/common.js?${getVersion}"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/recharge.css?${getVersion}">
<script src="/js/recharge.js?${getVersion}"></script>
<%@ include file="./common/common_init.jsp" %>
<!--本页面单独使用文件 end-->
</head>

<body class="lz-body">
<main class="lz-main">
    <section class="lz-mb">
        <div class="lz-balance">
            <div class="lz-center">
                <span><i class="lz-iconfont lz-icon-qiandai"></i></span>
                <span class="lz-box">
                    账户余额（元）
                    <strong>${o.amount }</strong>
                </span>
            </div>
        </div>
    </section>
    <section class="lz-mb">
        <h2 class="lz-tit-4">充值金额</h2>
        <ul class="lz-list-2" id="lz-chongzhijine">
        	<c:forEach items="${rechargeList }" var="r">
        		<li>
	                <div class="lz-left" style="width: 70%;"><span class="lz-tit">充值${r.recharge_money }元</span><strong>送${r.get_money }元</strong></div>
	                <div class="lz-right" style="width: 30%;">
	                	<c:if test="${r.is_vip == 1 }">
	                 		<img alt="" src="image/vip.png" width="50" height="18" style="vertical-align: text-bottom;">
	                 	</c:if>
	                    <span class="lz-checked-1" 
	                   
	                    	<c:if test="${r.is_vip == 1 }">
	                        	<c:if test="${not empty o.vip}">
	                    			onclick="lz.clearCheckboxList(this, '#lz-chongzhijine'); lz.checkbox(this); selectedMoneyAndId(${r.id})"  
	                    	    </c:if>
	                    	    <c:if test="${empty o.vip}">
	                    	    	 style="border: 1px solid #ccc;"
	                    	    </c:if>
	                        </c:if>
	                        <c:if test="${r.is_vip == 2 }">
	                        	onclick="lz.clearCheckboxList(this, '#lz-chongzhijine'); lz.checkbox(this); selectedMoneyAndId(${r.id})" 
	                        </c:if>
	                    	>
	                    	
	                        <i name="check" class="lz-iconfont"></i>
	                        <input type="checkbox" checked>
	                    </span>
	                </div>
	            </li>
        	</c:forEach>
            <li>
                <div class="lz-left">
                  <div style="overflow: hidden;">
                  <span class="lz-tit" style="float:left;">自定义金额</span>
                	<input class="lz-right" type="tel" id="money" style="IME-MODE: disabled; width: 1.2rem; height: 0.38rem; line-height:0.38rem;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                			onafterpaste="this.value=this.value.replace(/\D/g,'')" onblur="cancelSetCheck()" maxlength="5" size="14" placeholder="输入充值金额" onclick="lz.clearCheckboxList(this, '#lz-chongzhijine');" >
                  </div>
                	<span id="errorMonry" style="color: red;"></span>
                </div>
            </li>
        </ul>
    </section>
    <section class="lz-mb">
        <h2 class="lz-tit-4">充值方式</h2>
        <ul class="lz-list-2" id="lz-chongzhifangshi">
            <li>
                <div class="lz-left"><i class="lz-iconfont lz-icon-weixin" style="color:#00D20D;"></i><span class="lz-tit">微信</span><span class="lz-info">微信支付</span></div>
                <div class="lz-right">
                    <span class="lz-checked-1" onclick="lz.clearCheckboxList(this, '#lz-chongzhifangshi'); lz.checkbox(this); selected(1);">
                        <i class="lz-iconfont lz-theme2-font-color  lz-icon-dagou"></i>
                        <input type="checkbox">
                    </span>
                </div>
            </li>
            <li>
                <div class="lz-left"><i class="lz-iconfont lz-icon-zhifubao" style="color:#FF9001;"></i><span class="lz-tit">支付宝</span><span class="lz-info">支付宝支付</span></div>
                <div class="lz-right">
                    <span class="lz-checked-1" onclick="lz.clearCheckboxList(this, '#lz-chongzhifangshi'); lz.checkbox(this); selected(2);">
                        <i class="lz-iconfont lz-theme2-font-color"></i>
                        <input type="checkbox" checked>
                    </span>
                </div>
            </li>
            <li>
                <div class="lz-left"><i class="lz-iconfont lz-icon-yinlian" style="color:#ccc;"></i><span class="lz-tit">银联</span><span class="lz-info">银联支付</span></div>
                <input type="hidden" id="userId" value="${o.id }">
                <input type="hidden" id="theway" value="1">
                <input type="hidden" id="rechargeEventsId"  >
                <div class="lz-right">
                    <span class="lz-checked-1" no-onclick="lz.clearCheckboxList(this, '#lz-chongzhifangshi'); lz.checkbox(this); selected(3);" style="border: 1px solid #ddd;">
                        <i class="lz-iconfont lz-theme2-font-color"></i>
                        <input type="checkbox">
                    </span>
                </div>
            </li>
        </ul>
    </section>
    <section>
        <h2 class="lz-tit-4">
            温馨提示：充值成功后，账户内金额可用于支付场地，约教练费使用。<br>
            充值异常等相关问题可拨打服务热线&nbsp;&nbsp;4000&nbsp;&nbsp;1818&nbsp;&nbsp;32&nbsp;&nbsp;咨询
        </h2>
    </section>
    <div class="lz-abreast-btn">
        <div onclick="pays()" id="di" style="">确认支付</div>
<!--         <input type="button" value="微信" onclick="weixin()" > -->

<!-- 		<div onclick="ruhui()"  style="">入会</div> -->
    </div>
</main>

</body>
<%-- <script src="/js/ios/iospayrecharge.js?${getVersion}"></script> --%>
</html>
