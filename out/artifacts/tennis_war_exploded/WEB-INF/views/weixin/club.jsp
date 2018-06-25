<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>test</title>
    <link href="/weixin/css/common.css" rel="stylesheet">
    <link href="/weixin/css/iconfont/iconfont.css" rel="stylesheet">
    <script src="/weixin/js/common.js"></script>
    <!-- mobile slider plugin start -->
    <link href="/js/plugin/swiper.3.1.7.min.css" rel="stylesheet">
    <script src="/js/plugin/swiper.3.1.7.min.js"></script>
    <!-- mobile slider plugin end -->
</head>

<body>
    <header class="header" flex="cross:stretch">
        <a class="icon" flex-box="0" href="#"><i class="iconfont icon-fanhui"></i></a>
        <a class="text" flex-box="0" href="#">南京</a>
        <div flex="dir:right cross:stretch" flex-box="1">
            <div class="logo" flex="cross:stretch" flex-box="0">
                <div class="pic"><img src="/weixin/images/test.jpg" alt="连锁俱乐部"></div>
                <div class="club">连锁俱乐部
                    <br>Club&nbsp;Chain</div>
            </div>
        </div>
    </header>
    <div style="height: 5px;"></div>
    <div class="club-view-block">
        <h2 class="top-title">${o.name }
<!--         	<em>(南京)</em> -->
        </h2>
        <h3 class="title">分部介绍</h3>
        <div class="view-slide" id="view-slide">
            <div class="swiper-wrapper">
            	<c:forEach items="${clubImgList }" var="p">
	                <div class="swiper-slide">
	                    <div class="pictrue"><img src="/${p.img }" alt="俱乐部"></div>
	                </div>
                </c:forEach>
            </div>
            <div class="swiper-pagination swiper-pagination-white"></div>
        </div>
        <article class="content">　　
        	${o.context }
        </article>
    </div>
    <script>
        new Swiper('#view-slide', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            spaceBetween: 30,
        });
    </script>
    <div class="club-view-block">
        <h3 class="title">活动集锦</h3>
        <div id="field-pictrue">
            <div class="swiper-wrapper heng">
	            <c:forEach items="${galleryImgList }" var="c">
	                <div class="swiper-slide">
	                    <div class="pictrue"><img src="/${c.img }" alt="活动"></div>
	                </div>
	            </c:forEach>
            </div>
        </div>
    </div>
    <script>
        new Swiper('#field-pictrue', {
            freeMode: true,
            slidesPerView: 'auto',
        });
    </script>
    <div class="vertion" flex="main:justify corss:stretch">
        <a href="#">弘金地网球官网</a>
        <a href="#">连锁网球俱乐部</a>
    </div>
</body>

</html>