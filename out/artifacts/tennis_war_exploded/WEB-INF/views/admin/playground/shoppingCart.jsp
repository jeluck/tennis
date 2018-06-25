<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
<script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
<script src="/js/admin_common.js?${getVersion}"></script>

<script src="/js/common.js?${getVersion}"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/shoppingCart.css?${getVersion}">
<script src="/js/shoppingCartAdmin.js?${getVersion}"></script>
<!--本页面单独使用文件 end-->

</head>

<body class="lz-body">
<main class="lz-view">
  <!-- 支付列表清单 start -->
  <section class="lz-mb" style="background: #fff;">
      <h2 class="lz-tit-3">场地预定</h2>
      <div class="lz-zhifu-list">
        <h3 class="tit">${playground.name }</h3>
        <p class="con"><i class="lz-iconfont lz-icon-dingwei"></i>${playground.address } &nbsp;&nbsp;</p>
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
              	
              	<c:forEach items="${sList }" var="s">
              		<div class="lz-hour-list">${s.time }:00~${s.time+1 }:00&nbsp;&nbsp;${s.space_id.name }&nbsp;&nbsp;￥${s.price }</div>
              		<input name="stpId" value="${s.id }" type="hidden"/>
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
          <li class="lz-total">
            合计：<span class="lz-money">￥${price }</span>
          </li>
        </ul>
      </div>
  </section>
  <!-- 支付列表清单 end -->
  <!-- 支付方式 start -->
  <section class="lz-zhifuway">
    <h2 class="lz-zhifuway-tit">支付方式</h2>
    <ul class="lz-zhifuway-list" id="lz-zhifuway-list">
      <c:if test="${userId != 0 }">
	      <li class="lz-zhifuway-list-li"><i class="lz-iconfont lz-icon-qiandai"></i><strong>余额支付</strong>（${user.amount }元可用）
	        <span class="lz-right">
	         <c:if test="${user.amount<price }"> 余额不足&nbsp;&nbsp;</c:if>
	          <span class="lz-checked-1" data-info="一个自定义的按钮" onclick="lz.clearCheckboxList(this, '#lz-zhifuway-list'); lz.checkbox(this,0); "><i class="lz-iconfont lz-theme2-font-color" ></i>
	            <input type="checkbox" >
	          </span>
	        </span>
	      </li>
      </c:if>
      
    </ul>
    <input type="hidden" id="orderMainNo" value="${orderMainNo }"/>
    <input type="hidden" id="playgroundName" value="${playground.name }"/>
    <div class="lz-abreast-btn">
      <div class="lz-money">￥${price }</div>
      <c:if test="${userId>0 }">
      	<div class="lz-btn" onclick="p()">确认支付</div>
      </c:if>
      <form id="pay" action="/spaceOrder.do" method="post">
      	<input type="hidden" name="pay_type" id="pay_type" value="0">
      	<input type="hidden" name="mainId" value="${mainId }"/>
      	<input type="hidden" name="userId" value="${userId }"/>
      	
      	<input type="hidden" name="amount" value="${user.amount }"/>
      	<input type="hidden" name="price" value="${price }"/>	
      	
      	<input type="hidden" name="playgroundId" value="${playground.id }"/>
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
  <!-- 支付方式 end  -->
</main>
<script type="text/javascript">
// function p(){
// 	var uamount="${user.amount}";
// 	var price="${price}";
// 	if(parseInt(uamount)<parseInt(price)){
// 		alert("余额不足，请充值");
// 		return;
// 	}
// 	$("#pay").submit();
// }
</script>
</body>
</html>
