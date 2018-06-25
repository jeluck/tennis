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
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
<script src="/js/common.js?${getVersion}"></script>
<script src="/js/shoppingCart.js?${getVersion}"></script>

<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/shoppingCart.css">

<script type="text/javascript">
	function checkFlase(){
		if (document.getElementById('red')) {
			document.getElementById('red').checked = false;
		}
	}
</script>

<!--本页面单独使用文件 end-->
</head>
<body class="lz-body" onload="checkFlase()">
<main class="lz-view">
  <!-- 支付列表清单 start -->
  <section class="lz-mb" style="background: #fff;">
      <div class="lz-zhifu-list">
        <h3 class="tit">${playground.name }</h3>
        <p class="con"><i class="lz-iconfont lz-icon-dingwei"></i>${playground.address } &nbsp;&nbsp;${interval }</p>
        <ul class="lz-goods">
          <li class="lz-time">
            <div class="lz-date-box">
              <i class="lz-iconfont lz-icon-shijian"></i>
              <div class="lz-date">
              	<c:forEach items="${tList }" var="l">
              		<span class="lz-start">${l.month }-${l.day }<br>${l.week }</span>
              	</c:forEach>
              </div>
              <div class="lz-count">共${weekcount }周次</div>
            </div>
            <div class="lz-hour-box">
              <div class="lz-hour">
              	<c:if test="${not empty sList }">
	              	<c:forEach items="${sList }" var="s">
	              		<div class="lz-hour-list">${s.time }:00~${s.time+1 }:00&nbsp;&nbsp;${coach.name }</div>
	              		<input name="stpId" value="${s.id }" type="hidden"/>
	              		<c:set var="count" value="${count+1 }" scope="request"></c:set>
	              	</c:forEach>
              	</c:if>
              	<c:if test="${not empty crtList }">
              		<c:forEach items="${crtList  }" var="s">
	              		<div class="lz-hour-list">${s.timepoint }:00~${s.timepoint+1 }:00&nbsp;&nbsp;${coach.name }</div>
	              		<input name="stpId" value="${s.id }" type="hidden"/>
	              	</c:forEach>
              	</c:if>
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
          <li class="lz-total">
            合计：<span class="lz-money">￥${price }</span>
          </li>
        </ul>
      </div>
  </section>
  <!-- 支付列表清单 end -->
  <!-- 支付方式 start -->
  <c:if test="${empty check }">
  <section class="lz-zhifuway">
    <h2 class="lz-zhifuway-tit">支付方式</h2>
    <ul class="lz-zhifuway-list" id="lz-zhifuway-list">
      <li class="lz-zhifuway-list-li"><i class="lz-iconfont lz-icon-weixin"></i><strong>微信</strong>
        <span class="lz-right">
          <span class="lz-checked-1" data-info="一个自定义的按钮" onclick="lz.clearCheckboxList(this, '#lz-zhifuway-list'); lz.checkbox(this,1);"><i  id="flag2" class="lz-iconfont lz-theme2-font-color lz-icon-dagou"></i>
            <input type="checkbox" checked="checked">
          </span>
        </span>
      </li>
      <li class="lz-zhifuway-list-li"><i class="lz-iconfont lz-icon-zhifubao"></i><strong>支付宝</strong>
        <span class="lz-right">
          <span class="lz-checked-1" data-info="一个自定义的按钮" onclick="lz.clearCheckboxList(this, '#lz-zhifuway-list'); lz.checkbox(this,2);"><i id="flag" class="lz-iconfont lz-theme2-font-color"></i>
            <input type="checkbox" checked="checked">
          </span>
        </span>
      </li>
      <li class="lz-zhifuway-list-li"><i class="lz-iconfont lz-icon-qiandai"></i><strong>余额支付</strong>（${user.amount }元可用）
        <span class="lz-right">
         <c:if test="${user.amount<price }"> 余额不足&nbsp;&nbsp;</c:if>
          <span class="lz-checked-1" data-info="一个自定义的按钮" onclick="lz.clearCheckboxList(this, '#lz-zhifuway-list'); lz.checkbox(this,0);"><i class="lz-iconfont lz-theme2-font-color"></i>
            <input type="checkbox">
          </span>
        </span>
      </li>
    </ul>
    <div class="lz-abreast-btn">
      <div class="lz-money">￥${price }</div>
      <div class="lz-btn" onclick="p()" id="confirmPayment" >确认支付</div>
      <input value="${playground.name }"  type="hidden" id="playgroundName"/>
      <input value="${orderMainNo }"  type="hidden" id="orderMainNo"/>
      <form id="pay_nouse" action="spaceOrder.do" method="post">  
      	<input type="hidden" name="pay_type" id="pay_type" value="1">
      	<input type="hidden" name="mainId" value="${mainId }"/>
      	<input type="hidden" name="userId" value="${userId }"/>
      	
      	<input type="hidden" name="amount" id="amount" value="${user.amount }"/>
      	<input type="hidden" name="price" id="price" value="${price }"/>
      	
      	<input type="hidden" name="playgroundId" value="${coach.playground_id }"/>
      	<input type="hidden" name="payOrderNo" id="payOrderNo" value=""/>
      	<c:if test="${not empty coachList }">
          	<c:forEach items="${coachList }" var="c">
	          <input type="hidden" name="coachId" value="${c.id }"/>
          	</c:forEach>
        </c:if>
      	<c:if test="${empty coachList }">
      		<input type="hidden" name="coachId" value="0"/>
      	</c:if>
      	<input type="hidden" name="spaceBelong" value="${spaceBelong }">
      	<input type="hidden" name="is_coach" value="${is_coach }"/>
      </form>
    </div>
  </section>
  </c:if>
  <!-- 支付方式 end  -->
</main>
</body>
<%-- <script src="/js/ios/iospay.js?${getVersion}"></script> --%>
</html>