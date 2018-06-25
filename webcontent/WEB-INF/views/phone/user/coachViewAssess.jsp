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
  <title>我的订单-评价</title>
  <script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
  <script src="/js/admin_common.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
  <link rel="stylesheet" type="text/css" href="/css/common.css">
  <link rel="stylesheet" href="/css/venuesView.css?">
<%@ include file="./common/common_init.jsp" %>
  <script src="/js/common.js"></script>
  <!-- 弹层插件 start -->
  <link rel="stylesheet" href="/js/plugin/cloud.min.css">
  <script type="text/javascript" src="/js/plugin/cloud.min.js"></script>
  <!-- 弹层插件 end -->
  <script type="text/javascript">
    function clickPingFanCoach(iIndex) {
      var aLi = document.querySelectorAll('#lz-pingfen-boxCoach i');
      for (var i = 0; i < aLi.length; i++) {
        if (i <= iIndex) {

          aLi[i].className = 'lz-iconfont lz-icon-shixing';
        } else {
          aLi[i].className = 'lz-iconfont lz-icon-kongxing';
        }
      }

      var coachScore = document.getElementById("coachScore");
      coachScore.innerHTML = iIndex + 1;

    }


    function releaseAssess() {
      if (document.getElementById("coachScore").innerHTML == '0') {
        return cloud.alert('请给教练评分');
      }

      if (document.getElementById("coachContent").value == '') {
        return cloud.alert('请给教练评论');
      }

      var d = util.POST("/score.do", {
        "orderMainNo": "${orderMainNo}",
        "coachScore": document.getElementById("coachScore").innerHTML,
        "coachContent": document.getElementById("coachContent").value
      });
      cloud.msg('发表成功', 'bottom', 2000);
//    window.pay.goTOMy();
      //在发表评论后回"我的"		edit by lxc	2016-01-12
        order();
    }

  </script>
</head>

<body class="lz-body">
  <div class="lz-order-re">
    <div class="lz-table">
      <div class="lz-table-cell lz-order-reuserimg">
        <img src="/${coach.head_portrait } " alt="头像 ">
      </div>
      <div class="lz-table-cell lz-order-main">
        <h2 class="lz-order-retit">${coach.name }</h2>
        <span class="lz-pingfen "> 
          <span id="lz-pingfen-boxCoach">
	        	<i onclick="clickPingFanCoach(0);" class="lz-iconfont lz-icon-kongxing"></i> 
	        	<i onclick="clickPingFanCoach(1);" class="lz-iconfont lz-icon-kongxing"></i> 
	        	<i onclick="clickPingFanCoach(2);" class="lz-iconfont lz-icon-kongxing"></i> 
	        	<i onclick="clickPingFanCoach(3);" class="lz-iconfont lz-icon-kongxing"></i> 
	        	<i onclick="clickPingFanCoach(4);" class="lz-iconfont lz-icon-kongxing"></i>
	        </span><span id="coachScore">0</span>
        </span>
      </div>
    </div>
    <textarea id="coachContent" placeholder="我要评论 "></textarea>
  </div>
  <div class="lz-abreast-btn lz-order-rebtn">
    <div onclick="releaseAssess()">发表评价</div>
  </div>
</body>

</html>
