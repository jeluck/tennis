<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <title>我的</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
    <link rel="stylesheet" href="../../css/personMyOrder.css"/>
    <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>
    <style type="text/css">
        span.shopping-img {
            background: url("../../images/total.png");
            /* background-color: #EC5355; */
            background-size: 490px;
            /* background-position: -56px 12%; */
            height: 30px;
            width: 29px;
            /* margin-left: 2%; */
            display: block;
            margin-bottom: -7px;
            margin-top: 4px;
        }
        span.shopping-img {
            background-position: -435px 35%;
            background-size: 600px;
            height: 30px;
            width: 37px;
        }
        li.index-com-footer {
            list-style: none;
            float: left;
            margin: 0px auto;
            height: 59px;
            display: inline-table;
            margin-left: 10px;
            margin-right: 10px;
            width:48px;
        }
        span.sort-img {
            background-position: -18px 35%;
            background-size: 530px;
            height: 30px;
            width: 34px;
        }
        span.cart-img {
            background-position: -364px 35%;
            background-size: 600px;
            height: 30px;
            width: 37px;
        }
    </style>
    <script>
    //如果红圈中数据为0，不显示"红圈+0"
    $(function() {
        var a_redChild = $(".redRound-num");
        var a_red = a_redChild.text();
        for(i=0; i<a_red.length; i++) {
            var aNum = a_red.toString();
            //  alert(aNum[i]);
           if(aNum[i] == 0) {
           var a_redParent = $(a_redChild[i]).parents(".redRound-img");
           $(a_redParent).css("display","none");
             //  alert("不显示红圈");
           }
        }
    })
    </script>
</head>
<body>
<div class="user">
    <span >

<!-- <input type="file" name="imgFile" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;" /> -->

    	<c:if test="${not empty w.head_photo}" var="hava">
     		<img id="headerid" class="head-img" src="${w.head_photo }" onclick="window.location.href='myInform.do'">
     	</c:if>
     	<c:if test="${!hava }">
     		<img id="headerid" class="head-img" src="/image/nophoto_120X120.jpg" onclick="window.location.href='myInform.do'"/>
     	</c:if>
     	</span><br>
    <span><a class="userName">${w.username }</a><br>
<!--     <a class="userFrom">微信用户</a> -->
    </span>
</div>
<!-- 我的订单产品状态 -->
<div class="">
  <ul>
      <li class="waitCom">
          <span class="waitPay"></span>
        <!--   红色圆圈带有数字 -->
         <span class="redRound-img"><a class="redRound-num">${waitpay }</a></span>
          <br>
          <a>待付款</a>
      </li>
      <li class="waitCom">
          <span class="waitSend "></span>
          <!-- 红色圆圈带有数字 -->
          <span class="redRound-img"><a class="redRound-num">${waitsend }</a></span>
          <br>
          <a>待发货</a>

      </li>
      <li class="waitCom">
          <span class="waitRec"></span>
          <!-- 红色圆圈带有数字 -->
          <span class="redRound-img"><a class="redRound-num">${sended }</a></span>
          <br>
          <a>待收货</a>
      </li>
      <li class="waitCom">
          <span class="waitAdvice "></span>
<!--           红色圆圈带有数字 -->
<!--           <span class="redRound-img"><a class="redRound-num">9</a></span> -->
          <br>
          <a>&nbsp;退货</a>
      </li>
  </ul>
</div>
<div class="clear"></div>
<!-- 我的订单 -->
<div class="mySet" style="margin-bottom: 10px;">
    <div class="choice-wechat01"  onclick="window.location.href='orders.do'">
        <span class="Count-img01" style="background-position: -208px -75%;"></span>
        <span class="Count-text"><a>我的订单</a></span>
        <a href="orders.do"> <span class="arrowRig-img"></span><em class="lookAllOrder">查看全部订单</em></a>
    </div>
</div>
<!-- 我的钱包 -->
<div class="mySet myPocket">
    <div class="choice-wechat01" onclick="window.location.href='countdetail.do'">
          <span class="pocket-img01"></span>
        <span class="Count-text" style="margin-top:-10px;line-height: 35px;padding-left: 45px;">我的钱包（元）<a href="countdetail.do" class="arrowRight">&nbsp;资金管理/明细&nbsp;></a><br><a class="nowPocket">${w.amount }</a></span><br>
    
    </div>
</div>

<!-- 我的团队 -->
<div class="mySet myPocket" style="border-top: 10px solid #f8f8f8">
    <div class="choice-wechat01">
        <span class="team-img01" ></span>
        <span class="Count-text" style="margin-top: -10px;line-height: 40px;padding-left: 45px;">我的团队（人数）<!--<a href="../../dongguan-sale/personCenter/countDetali.html" class="arrowRight">&nbsp;资金管理/明细&nbsp;></a><br>--><br><a class="nowPocket"></a></span><br>

    </div>
</div>

<!-- 购物车 -->
<div class="mySet" style="margin-bottom: -18px;">
    <div class="choice-wechat01">
    	<a href="tocart.do">
        <span class="Count-img01" style="background-position: -558px -108%;"></span>
        <span class="Count-text">购物车管理</span>
        <span class="arrowRig-img"></span>
        </a>
    </div>
</div>


<!-- 收货地址管理 -->
<div class="mySet" style="margin-bottom: -18px;">
    <div class="choice-wechat01">
    	<a href="toaddr.do">
        <span class="Count-img01" style="background-position: -257px -75%;"></span>
        <span class="Count-text">收货地址管理</span>
        <span class="arrowRig-img"></span>
        </a>
    </div>
</div>
<!-- 帮助中心 -->
<!--
<div class="mySet" style="margin-bottom: -18px;">
    <div class="choice-wechat01">
        <span class="Count-img01" style="background-position:-304px -76%"></span>
        <span class="Count-text"><a>帮助中心</a></span>
        <a href="../addProduce/myProducts.html"> <span class="arrowRig-img"></span></a>
    </div>
</div>
-->
<div class="mySet" style="margin-bottom: -18px;">
    <div class="choice-wechat01">
    	<a href="set.do">
        <span class="Count-img02"></span>
        <span class="Count-text">设置</span>
        <span class="arrowRig-img"></span>
        </a>
    </div>
</div>
<div class="mySet">
    <div class="choice-wechat01">
        <span class="daily-img02"></span>
        <span class="Count-text"><a>单日上新：${today_commodityNum }</a></span>
    </div>
</div>
<div class="mySet" style="margin-top: 12px;">
    <div class="choice-wechat01">
        <span class="total-img02"></span>
        <span class="Count-text"><a>总 数  量：${all_commodityNum }</a></span>
    </div>
</div>
<div  style="height: 100px;">
</div>
<!--<div class="mySet" style="height: 100px;">
</div>-->
</body>
</html>