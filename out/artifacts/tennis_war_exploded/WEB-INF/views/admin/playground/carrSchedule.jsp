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
<title>日程表</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?100">
<link rel="stylesheet" type="text/css" href="/css/common.css?100">
<script src="/js/common.js?100"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/venuesView.css?100">
<link rel="stylesheet" href="/css/calendar.css?100">
<link rel="stylesheet" href="/js/plugin/swiper.3.1.7.min.css?100">
<script src="/js/plugin/swiper.3.1.7.min.js?100"></script>
<script src="/js/calendar.js?100"></script>
<script src="/js/venuesView.js"></script>
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
<script>
  var sLocalStorage = 'riChengBiao'; //app唯一标识，不可与其他页面重复，否则会报错
</script>
<!--本页面单独使用文件 end-->
</head>
<body class="lz-body">
<main class="lz-view" data-lz-tab="riChengBiao,#lz-tab-nav,#lz-tab-box">
  <ul class="lz-tab-nav" id="lz-tab-nav">
    <li>预约时段</li>
  </ul>
  <div id="lz-tab-box">
    <div style="display:none;" data-info="预约时段盒子"> 
      <!-- 预约时段 start --> 
      
      <!-- 说明 start -->
      <div class="lz-table lz-explains">
        <div class="lz-table-cell lz-l">注<span class="lz-transparent">意</span>：</div>
        <div class="lz-table-cell lz-r"> 预约时段和收费设置由您所在的俱乐部或场馆运营者安排，如有其他变动，请及时通知俱乐部或场馆更新 </div>
      </div>
      <!-- 说明end --> 
      <!-- 预约说明 start -->
      <div class="lz-table lz-info">
        <div class="lz-table-cell lz-no"> <span class="lz-transparent">隐藏的</span>不可预约 </div>
        <div class="lz-table-cell lz-go"> <span class="lz-transparent">隐<i class="lz-iconfont lz-icon-xiaogou"></i>藏</span>已预约 </div>
        <div class="lz-table-cell lz-yes"> <span class="lz-transparent">隐<i class="lz-iconfont lz-icon-xiaogou"></i>藏</span>可预约 </div>
      </div>
      <!-- 预约说明 end --> 
      <!-- 预约选择时间 start -->
      <section class="lz-mb">
        <div class="lz-destine">
          <div class="lz-date swiper-container-horizontal swiper-container-free-mode" id="lz-date-0" data-info="选择一周的某一天">
            <ul class="swiper-wrapper">
            <c:forEach items="${dateList }" var="d">
	        	<li onClick="lz.schedule(this,'${d.month}','${d.day }',${coach.id },'${d.week }',0,'${coach.coachType }','${d.year }',0);" class="swiper-slide">
	            <time>${d.year}&nbsp;-&nbsp;${d.month } &nbsp;-&nbsp;${d.day }</time>
	             ${d.week }</li>
        	</c:forEach>
            </ul>
          </div>
          <div class="lz-hour lz-show" id="lz-hour-box-0" style="border-top:none;" data-info="查看场地在不同时段的价格">
            <div class="lz-time">
              <ul class="lz-list" id="lz-list">
              </ul>
            </div>
            <div id="lz-hour-child-0">
              <div></div>
              <div></div>
              <div></div>
              <div></div>
              <div></div>
              <div></div>
              <div></div>
            </div>
          </div>
        </div>
      </section>
      <!-- 预约选择时间 end --> 
      
    </div>
</main>
</body>
</html>
