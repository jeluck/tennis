<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地国际学校</title>
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
        <div class="logo" flex="cross:stretch" flex-box="1">
            <div class="pic"><img src="/weixin/images/test.jpg" alt="连锁俱乐部"></div>
            <div class="club">${o.name }
                <br>Club&nbsp;Chain</div>
        </div>
    </header>
    <div class="guoji-pictrue"><img src="/${o.img }" alt="缩略图"></div>
    <article class="introduce">
		${o.context }
    </article>
    <div class="club-view-block">
        <h3 class="title">活动集锦</h3>
        <div id="field-pictrue">
            <div class="swiper-wrapper heng">
            	<c:forEach items="${galleryImgList }" var="c">
	                <div class="swiper-slide">
	                    <div class="pictrue"><img src="/${c.img }" alt="南山区"></div>
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