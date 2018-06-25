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
  <script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
  <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">

  <script src="/js/common.js?${getVersion}"></script>
  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/venuesView.css?${getVersion}">
  <link rel="stylesheet" href="/js/plugin/swiper.3.1.7.min.css?${getVersion}">
  <script src="/js/plugin/swiper.3.1.7.min.js?${getVersion}"></script>
  <script src="/js/reservation.js?${getVersion}"></script>
<%@ include file="./common/common_init.jsp" %>
  <script type="text/javascript">
    window.addEventListener('load', function() {
      window.reservation = new Reservation({
        getFieldUrl: 'timepoint.do?playgroundId=${playground.id}&belong=0',  //场馆详情获取场地url （playgroundId = 场馆id） （belong = 0） 0为场馆，1为教练
        jspName: 'venuesView.jsp', //场馆详情
        query: {
          playgroundId: '${playground.id }', //传入场馆id
          coachId: '0', //教练id
          spacePriceId: '', //场地选择的id，拼成字符串
          cycle: 'false', //周期预定
          endTime: '', //周期预定结束时间
          userId: '${userId }', //用户id
          person: '0',  //场馆预定，默认一对一
          inCoachId: '0' //助产教练id
        }
      });
    }, false);

  </script>
  <!--本页面单独使用文件 end-->
</head>

<body class="lz-body">

  <main class="lz-view">
	
    <!-- Swiper -->
    <div class="swiper-container lz-swiper-1" id="swiper-container-1">
      <div class="swiper-wrapper">
        <c:forEach items="${img }" var="i">
          <div class="swiper-slide">
            <div style="background-image: url('${i.img}');"></div>
          </div>
        </c:forEach>
      </div>
      <div class="swiper-pagination" id="swiper-pagination-1"></div>
    </div>
    <script>
      new Swiper('#swiper-container-1', {
        pagination: '#swiper-pagination-1',
        autoplay: 2000,
        slidesPerView: 1,
        paginationClickable: true,
        keyboardControl: true
      });

    </script>

    <!--轮播图 end-->
    <!--地址，评分 start-->
    <section class="lz-mb">
      <div class="lz-con">
        <ul>
          <li><i class="lz-iconfont lz-icon-dingwei"></i>${playground.address } &nbsp;&nbsp;500m <span class="lz-right" onclick="javascript:window.pay.goTOMap(0,${playground.id})"><i class="lz-iconfont lz-icon-ditu"></i></span> </li>
          <li class="lz-pingfen"> 
          <c:if test="${playground.evaluate_score!= '0' }">
          <span>
	        <c:forEach items="${score }" var="s">
	        	<c:if test="${s==0 }">
	        	<i class="lz-iconfont lz-icon-kongxing"></i>
	        	</c:if>
	        	<c:if test="${s==1 }">
	        	<i class="lz-iconfont lz-icon-shixing"></i> 
	        	</c:if>
	        </c:forEach>
     	   </span> &nbsp;&nbsp;${playground.evaluate_score } 
     	</c:if>
     	<c:if test="${playground.evaluate_score== '0'}">
     		暂无评价
     	</c:if>
     	<span class="lz-right">预定${playground.audCount }次</span></li>
        </ul>
      </div>
    </section>
    <!--地址，评分 end-->
    <!--场馆基本描述 start-->
    <section class="lz-mb">
      <ul class="lz-table-type-1">
        <li>
          <div class="lz-tit">场馆</div>
          <div class="lz-box">
            <nav class="lz-nav-1">
              <ul>
                <li><a href="#">${playground.site_type }</a></li>
                <li><a href="#">${playground.court_type }</a></li>
                <li><a href="#">${playground.space_type }</a></li>
              </ul>
            </nav>
          </div>
        </li>
        <li>
          <div class="lz-tit">服务</div>
          <div class="lz-box">
            <nav class="lz-nav-2">
              <ul>
                <c:if test="${playground.pservices.equipment==1 }">
                  <li><i class="lz-iconfont lz-icon-wangqiu"></i>器材</li>
                </c:if>
                <c:if test="${playground.pservices.locker_room==1 }">
                  <li><i class="lz-iconfont lz-icon-yifu"></i>更衣室</li>
                </c:if>
                <c:if test="${playground.pservices.lockers==1 }">
                  <li><i class="lz-iconfont lz-icon-yigui"></i>储存柜 </li>
                </c:if>
                <c:if test="${playground.pservices.shower==1 }">
                  <li><i class="lz-iconfont lz-icon-linyu"></i>淋浴</li>
                </c:if>
                <c:if test="${playground.pservices.vip_room==1 }">
                  <li><i class="lz-iconfont lz-icon-wangqiu"></i>贵宾室</li>
                </c:if>
                <c:if test="${playground.pservices.equipment_shop==1 }">
                  <li><i class="lz-iconfont lz-icon-dianpu"></i>装备店</li>
                </c:if>
                <c:if test="${playground.pservices.wifi==1 }">
                  <li><i class="lz-iconfont lz-icon-wifi"></i>wifi</li>
                </c:if>
                <c:if test="${playground.pservices.food==1 }">
                  <li><i class="lz-iconfont lz-icon-yinliao"></i>食品</li>
                </c:if>
                <c:if test="${playground.pservices.parking_lot==1 }">
                  <li><i class="lz-iconfont lz-icon-tingchechang"></i>停车场</li>
                </c:if>
              </ul>
            </nav>
          </div>
        </li>
        <li>
          <div class="lz-tit">描述</div>
          <div class="lz-box">${playground.details }</div>
        </li>
      </ul>
    </section>
    <!--场馆基本描述 end-->
    <%@ include file="./common/comment.jsp" %>
  </main>
  
  <a href="http://yu.yihezhai.cc/apk/JinDi_Coach.apk">下载教练端</a><br>
  <a href="http://yu.yihezhai.cc/apk/JinDi.apk">下载用户端</a>
</body>

</html>
