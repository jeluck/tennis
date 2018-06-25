<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地-首页</title>
    <link href="/weixin/css/common.css" rel="stylesheet">
    <link href="/weixin/css/iconfont/iconfont.css" rel="stylesheet">
    <script src="/weixin/js/common.js"></script>
    <!-- mobile slider plugin start -->
    <link href="/js/plugin/swiper.3.1.7.min.css" rel="stylesheet">
    <script src="/js/plugin/swiper.3.1.7.min.js"></script>
    <!-- mobile slider plugin end -->
</head>

<body>
    <header class="header" flex="main:justify cross:stretch">
        <a class="icon" flex-box="0" href="#"><i class="iconfont icon-gerenzhongxin"></i></a>
        <div class="logo" flex="cross:stretch">
            <div class="pic"><img src="/weixin/images/test.jpg" alt="弘金地"></div>
            <div class="hongjindi">弘金地</div>
        </div>
        <a class="icon" flex-box="0" href="#"><i class="iconfont icon-search"></i></a>
    </header>
    <div class="index-top">
        <div class="index-slide" id="index-slide">
            <div class="swiper-wrapper">
                <c:forEach items="${advertise }" var="o">
                	<div class="swiper-slide">
	                    <div class="pictrue"><img src="/${o.ad_picture_url }" alt="${o.ad_title }"></div>
	                </div>
                </c:forEach>
            </div>
            <div class="swiper-pagination swiper-pagination-white"></div>
        </div>
        <div class="btn-box" flex>
            <a flex-box="1" href="toPalygroundData.do">订场馆</a>
            <a flex-box="1" href="tocoachData.do">约教练</a>
        </div>
    </div>
    <script>
        new Swiper('#index-slide', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            spaceBetween: 30,
        });
    </script>
    <div class="index-topic">
        <a flex="main:justify cross:stretch" href="#">
            <div class="tit">推荐场馆</div>
            <div class="more">更多场馆>></div>
        </a>
        <div class="club-view-block" id="index-pictrue">
            <div class="swiper-wrapper heng">
                	<c:forEach items="${playground }" var="o">
                	 <div class="swiper-slide">
	                    <div class="pictrue">
	                        <img src="/${o.pdImg }" alt="${o.name }">
	                        <div class="about">坪山网球管</div>
	                   </div>
	                    </div>
                    </c:forEach>
            </div>
        </div>
    </div>
    <script>
        new Swiper('#index-pictrue', {
            freeMode: true,
            slidesPerView: 'auto',
        });
    </script>
    <div class="index-topic">
        <a flex="main:justify cross:stretch" href="#">
            <div class="tit">明星教练</div>
            <div class="more">更多教练>></div>
        </a>
        <div class="club-view-block" id="index-pictrue">
            <div class="swiper-wrapper heng">
               	<c:forEach items="${coach }" var="o">
             	 <div class="swiper-slide">
                  <div class="pictrue">
                      <img src="/${o.head_portrait }" alt="${o.name }">
                      <div class="about">坪山网球管</div>
                  </div>
                 </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <script>
        new Swiper('#index-pictrue', {
            freeMode: true,
            slidesPerView: 'auto',
        });
    </script>
    <a class="index-download">
        <img src="/weixin/images/index-download.png" width="100%" alt="下周客户端">
        <div class="about">弘金地网球版权所有&copy;2015</div>
    </a>
</body>

</html>