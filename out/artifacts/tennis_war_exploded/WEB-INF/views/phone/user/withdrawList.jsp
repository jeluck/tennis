<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<title>提现列表</title>
<link rel="stylesheet" href="/css/c.css"/>
<link rel="stylesheet" href="/css/customer.css"/>
<link rel="stylesheet" href="/css/index.css"/>
<link rel="stylesheet" href="/css/idangerous.swiper.css"/>
<link rel="stylesheet" href="/css/scrollpagination.css"/>
<%@ include file="./common/common_init.jsp" %>
<script type="text/javascript" src="/js/jquery.min.js"></script>

<style>
.withdraw-list-tit{ padding:5px 10px; font-size: 14px; color: #444;}
.withdraw-list>li { overflow: hidden; padding: 10px; line-height: 28px; border-bottom: 1px solid #DDD; background: #fff; }
.withdraw-list>li.tit { background: #eee; }
.withdraw-list>li.tit>div{ font-size:13px; color:#444;}
.withdraw-list>li>div { font-size: 14px; color: #222; }
.withdraw-list>li>div.date { float: left; color: #555; }
.withdraw-list>li>div.over { float: right; }
.withdraw-list>li>div>span { padding:2px 3px; margin-left:3px; border-radius:5px; line-height:24px; font-size:12px; color:#fff;}
.withdraw-list>li>div>span.err{ background:#F33;}
.withdraw-list>li>div>span.do{ background:#F90;}
</style>
</head>

<body>
<div id="content">
<%-- <h2 class="withdraw-list-tit">${list[0].year }</h2> --%>
<ul class="withdraw-list" id="content_ul">

  <li class="default tit">
    <div class="date">月份</div>
    <div class="over">补贴比例</div>
  </li>
<!--     <div class="nottiao">没有提现记录</div> -->

  
  <c:forEach items="${list }" var="o">
  	  <li class="default">
	    <div class="date">
	    	${o.year }-${o.month }
	    </div>
	    <div class="over"><fmt:formatNumber value="${o.money*100 }" pattern="#0.#"/>%</div>
	  </li>
  </c:forEach>
</ul>


</div>
<div id="loading"><div class="box"><img src="../../images/loading.gif"/><span>真爱粉，居然看完了！</span></div></div>
</body>
 <script src="/js/userFunction.js"></script>
 <script src="/js/scrollpagination.js"></script>
<script src="/js/downpullpage_common.js"></script>
<script type="text/javascript">
//调用下拉分页
$(function(){
    page("content",'nextPage_getWithdraw.do',function(d){
        $('#content_ul').append(d);
    });
});
</script>
</html>