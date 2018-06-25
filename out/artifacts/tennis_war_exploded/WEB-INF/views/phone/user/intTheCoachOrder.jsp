<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html class="lz-html" lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <title>预定支付</title>
  <!-- 驻场教练预定支付 -->
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
  <link rel="stylesheet" type="text/css" href="/css/common.css">
  <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
  <script src="/js/common.js"></script>
  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/shoppingCart.css">

<%@ include file="./common/common_init.jsp" %>
  <script src="/js/shoppingCart.js"></script>
  <!--本页面单独使用文件 end-->
  <style type="text/css">
    .lz-tit-3 {
      box-shadow: none;
      font-size: 0.15rem;
      line-height: 0.28rem;
    }

  </style>
</head>

<body class="lz-body">
  <main class="lz-view">
    <!-- 客户信息 start -->
    <section class="lz-mb" style="background: #fff;">
      <h2 class="lz-tit-3">客户信息</h2>
      <ul class="lz-dduser-box">
        <li>
          <c:if test="${not empty user.head_photo }"><img src="${user.head_photo }" alt=""></c:if> 
          <c:if test="${empty user.head_photo }"><img src="/images/coachJap.pn" alt=""></c:if> ${user.username }
          <div class="lz-call"><i class="lz-iconfont lz-icon-bohao"></i>${user.uphone }</div>
          <i class="lz-iconfont lz-icon-renzheng"></i>
          <c:if test='${user.sex == 男 }'>
            <i class="lz-sex lz-iconfont lz-icon-nan"></i>
          </c:if>
          <c:if test='${user.sex == 女 }'>
            <i class="lz-sex lz-iconfont lz-icon-nv"></i>
          </c:if>
        </li>
      </ul>
    </section>
    <!-- 客户信息 end -->
    <!-- 场馆信息 start -->
    <section class="lz-mb" style="background: #fff;">
      <h2 class="lz-tit-3">场馆信息</h2>
      <div class="lz-zhifu-list">
        <h3 class="tit">${playground.name }</h3>
        <p class="con"><i class="lz-iconfont lz-icon-dingwei"></i>${playground.address } &nbsp;&nbsp;${interval }</p>
      </div>
    </section>
    <!-- 场馆信息 end -->
    <!-- 支付列表清单 start -->
    <section class="lz-mb" style="background: #fff;">
      <h2 class="lz-tit-3">预定信息</h2>
      <div class="lz-zhifu-list">
        <ul class="lz-goods">
          <li class="lz-time">
            <div class="lz-date-box">
              <i class="lz-iconfont lz-icon-shijian"></i>
              <div class="lz-date">
                <c:forEach items="${tList }" var="l">
                  <span class="lz-start">${l.month }-${l.day }<br>${l.week }</span>
                </c:forEach>
              </div>
              ~
              <div class="lz-date">
                <c:forEach items="${tList }" var="l">
                  <span class="lz-start">${l.month }-${l.day }<br>${l.week }</span>
                </c:forEach>
              </div>
              <div class="lz-count">共${weekcount }周次</div>
            </div>
            <div class="lz-hour-box">
              <div class="lz-hour">
                <c:forEach items="${sList }" var="s">
                  <div class="lz-hour-list">${s.time }:00~${s.time+1 }:00&nbsp;&nbsp;${s.space_id.name }&nbsp;&nbsp;￥${s.price }</div>
                  <input name="stpId" value="${s.id }" type="hidden" />
                </c:forEach>
              </div>
              <div class="lz-count">${hourCount }场次</div>
            </div>
          </li>
          <c:if test="${not empty coachList }">
            <c:forEach items="${coachList }" var="c">
              <li class="lz-coach">
                <i class="lz-iconfont lz-icon-touxiang"></i>
                <c:if test="${not empty c.head_portrait}">
	            	 <img class="lz-headimg" src="/${c.head_portrait }" alt="头像">
	            </c:if>
	             <c:if test="${empty c.head_portrait}">
	            	 <img class="lz-headimg" src="/images/coachJap.png" alt="头像">
	            </c:if>
                <span class="lz-name">${c.name }</span>
                <span class="lz-count">￥${coachPrice }/场次</span>
              </li>
            </c:forEach>
          </c:if>
          <c:if test="${not empty red && red!=0}">
            <li class="lz-coupon">
              <i class="lz-iconfont lz-icon-youhuijuan"></i>
              <strong>使用优惠卷</strong>（${red }张优惠卷可用）
              <span class="lz-checked-1" data-info="一个自定义的按钮" onclick="lz.checkbox(this);"><i class="lz-iconfont lz-theme2-font-color"></i>
              <input type="checkbox" id="red">
            </span>
            </li>
          </c:if>
        </ul>
      </div>
      <div class="lz-zhifu-list lz-zhifu-list-plus">
        <ul class="lz-goods">
        	<c:if test="${not empty person }">
	          <li class="lz-time">
	            <div class="lz-date-box">
	              <h2 class="lz-tit-3">教学方式</h2>
	              <div class="lz-count">${person }</div>
	            </div>
	          </li>
	          </c:if>
	          <li class="lz-total">
          
            <span class="lz-money">￥${price }</span>
          </li>
        </ul>
      </div>
    </section>
    <!-- 支付列表清单 end -->
  </main>
</body>

</html>
