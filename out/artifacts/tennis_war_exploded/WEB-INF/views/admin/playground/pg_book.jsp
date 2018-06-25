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
  <title>场馆详情</title>
  <jsp:include page="../init.jsp" />
  <script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
  <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">

  <script src="/js/common.js?${getVersion}"></script>
  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/venuesView.css?${getVersion}">
  <link rel="stylesheet" href="/js/plugin/swiper.3.1.7.min.css?${getVersion}">
  <script src="/js/plugin/swiper.3.1.7.min.js?${getVersion}"></script>
  <script src="/js/reservation.js?${getVersion}"></script>
  <script type="text/javascript">
    window.addEventListener('load', function() {
      window.reservation = new Reservation({
        getFieldUrl: '../timepoint.do?playgroundId=${playground.id}&belong=0',  //场馆详情获取场地url （playgroundId = 场馆id） （belong = 0） 0为场馆，1为教练
        jspName: 'venuesViewAdmin.jsp', //场馆详情
        formAction: '/pgm/toOrder.do', //数据提交给服务器的地址
        formData: {
          playgroundId: '${playground.id }', //传入场馆id
          coachId: '0', //教练id
          spacePriceId: '', //场地选择的id，拼成字符串
          cycle: 'false', //周期预定
          endTime: '', //周期预定结束时间
          userId: '${userId }', //用户id
          person: '0',  //场馆预定，默认一对一
          inCoachId: '0', //助产教练id
          phone:''	,//联系人电话号码
          phoneName:''  //联系人名称
        }
      });
    }, false);

  </script>
  <!--本页面单独使用文件 end-->
</head>

<body class="lz-body" style="padding-bottom: 200px;">

    <%@ include file="./common/onlineReservation.jsp" %>
  <%@ include file="./common/coachFooter.jsp" %>
</body>

</html>
