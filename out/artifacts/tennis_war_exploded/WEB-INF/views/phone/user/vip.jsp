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
<link rel="stylesheet" href="/css/recharge.css?1433453029">
<script src="/js/recharge.js?${getVersion}"></script>

  <!-- 弹层插件 start -->
  <link rel="stylesheet" href="/js/plugin/cloud.min.css">
  <script type="text/javascript" src="/js/plugin/cloud.min.js"></script>
  <!-- 弹层插件 end -->
  <script type="text/javascript" src="/js/ios/ios_common.js"></script>
<!--本页面单独使用文件 end-->
</head>

<body class="lz-body">
    <section>
        <div class="sq-vip">
            <div class="sq-vip-pic">
                <img class="sq-vip-img" src="/images/vip.png" alt="vip">
                <div class="sq-vip-dan">${o.vip_id }</div>
            </div>
            <div class="flex sq-vip-info">
                <div class="flex-box-0 sq-vip-ruhui">入会费：</div>
                <div class="flex-box-1 sq-vip-money"><span style="font-size: 13px;">￥</span>${vip.price }</div>
            </div>
        </div>
    </section>
    
	    <section class="lz-mb">
	        <h2 class="lz-tit-4">充值方式</h2>
	        <ul class="lz-list-2" id="lz-chongzhifangshi">
	            <li>
	                <div class="lz-left"><i class="lz-iconfont lz-icon-weixin" style="color:#00D20D;"></i><span class="lz-tit">微信</span><span class="lz-info">微信支付</span></div>
	                <div class="lz-right">
	                    <span class="lz-checked-1"  
	                    		<c:if test="${not empty o.vip}">
	                    	    	style="border: 1px solid #ccc;"
	                    	    </c:if> 
	                    	    <c:if test="${empty o.vip}">
	                    	    	onclick="lz.clearCheckboxList(this, '#lz-chongzhifangshi'); lz.checkbox(this); selected(1);"
	                    	    </c:if> 
	                    	    >
	                    	    <c:if test="${empty o.vip}">
	                       			<i class="lz-iconfont lz-theme2-font-color  lz-icon-dagou"></i>
	                        	</c:if> 
	                        	<c:if test="${not empty o.vip}">
	                       			<i class="lz-iconfont lz-theme2-font-color"></i>
	                        	</c:if> 
	                        <input type="checkbox">
	                    </span>
	                </div>
	            </li>
	            <li>
	                <div class="lz-left"><i class="lz-iconfont lz-icon-zhifubao" style="color:#FF9001;"></i><span class="lz-tit">支付宝</span><span class="lz-info">支付宝支付</span></div>
	                <div class="lz-right">
	                    <span class="lz-checked-1"
	                    		<c:if test="${not empty o.vip}">
	                    	    	style="border: 1px solid #ccc;"
	                    	    </c:if> 
	                    	    <c:if test="${empty o.vip}">
	                    	    	onclick="lz.clearCheckboxList(this, '#lz-chongzhifangshi'); lz.checkbox(this); selected(2);"
	                    	    </c:if> 
	                    	>
	                        <i class="lz-iconfont lz-theme2-font-color"></i>
	                        <input type="checkbox" checked>
	                    </span>
	                </div>
	            </li>
	            <li>
	                <div class="lz-left"><i class="lz-iconfont lz-icon-yinlian" style="color:#ccc;"></i><span class="lz-tit">银联</span><span class="lz-info">银联支付</span></div>
	                <input type="hidden" id="userId" value="${o.id }">
	                <input type="hidden" id="theway" value="1">
	                <input type="hidden" id="vip_id" value="${vip.id }" >
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
        <h2 class="flex lz-tit-4">
            <div class="flex-box-0">温馨提示：</div>
            <div class="flex-box-1">
                ①VIP会员可享受更多充值优惠，平台特权和服务；<br>
                ②VIP入会费不可用于消费，不可退费；<br>
                ③VIP会员入会费为年费；<br>
                ④最终解释权归弘金地网球所有。
            </div>
        </h2>
    </section>
	    <div class="lz-abreast-btn">
	        <div id="di" <c:if test="${empty o.vip }"> onclick="Membership()"  style="background-color: #ea5532;color: white" </c:if> >确认支付</div>
	    </div>
    
</body>

</html>
