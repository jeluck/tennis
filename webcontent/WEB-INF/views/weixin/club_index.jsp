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
    <header class="header" flex="main:justify cross:stretch">
        <a class="icon" href="#"><i class="iconfont icon-fanhui"></i></a>
        <div flex="cross:stretch">
            <div class="logo" flex="cross:stretch" flex-box="1">
                <div class="pic"><img src="/weixin/images/test.jpg" alt="弘金地"></div>
                <div class="hongjindi">弘金地</div>
            </div>
            <div class="logo" flex="cross:stretch" flex-box="1">
                <div class="pic"><img src="/weixin/images/test.jpg" alt="连锁俱乐部"></div>
                <div class="club">连锁俱乐部
                    <br>Club&nbsp;Chain</div>
            </div>
        </div>
    </header>
    <div class="club-chain">
        <div class="about">
            <div>2003-2016年,</div>
            <div>专注网球13年,</div>
            <div style="height: 10px;"></div>
            <div>5亿+人民币,</div>
            <div>网球产业链投资</div>
        </div>
        <div class="pic"><img src="/weixin/images/club-chain.png" alt="连锁中国网球俱乐部"></div>
    </div>
    <article class="introduce">
<!--     	又是一季秋，又是一季秋尾，秋风萧条，秋雨冷凉，秋虫呢喃。天空不再高远，云朵不再轻淡。抬眼望去，满目的雾气。近处，行人匆匆；远方，北雁飞南。无论风中，无论雨里，都不会停留，或许是听见冬的序曲了。 -->
	${o.des }
        <!-- <div class="mian">又是一季秋，又是一季秋尾，秋风萧条，秋雨冷凉，秋虫呢喃。天空不再高远，云朵不再轻淡。抬眼望去，满目的雾气。近处，行人匆匆；远方，北雁飞南。无论风中，无论雨里，都不会停留，或许是听见冬的序曲了。</div>
            <ol class="list">
                <li>有人说，幸福是在别人的眼里，快乐却在自己的心中。看着她深邃的眸子，心疼地问她：您幸福吗？她微笑着说：你们的快乐就是我的快乐。</li>
                <li>简媜说，像每一滴酒，回不了最初的葡萄，我回不到年少。是的，可以回到那个简单的相依相偎的年代吗？一个馒头分几半，却吃得津津有味；一本小人书，可以忘了吃饭；一个简单的乖字，小脸开了花！</li>
                <li>春去冬来，花开花落，总有许多情不自禁的忧伤，于是慢慢学会了隐藏。时间，教会了我们很多，却教不会我们怎样不老；岁月，催老了容颜，却抹不去温馨的回忆。正如，风是雨手，雨是风的脚，年年岁岁，携手永远！</li>
                <li>那消瘦的双肩，从未脱离我的视线。午夜辗转，总会用心去拥抱，很轻很暖。是啊，小时候拥抱属于父母，长大了拥抱属于爱人，老了拥抱属于谁呢？张小娴说，拥抱的感觉真好，那是肉体的安慰，尘世的奖赏。</li>
            </ol>
            -->
    </article>
    <div class="club-field" style="overflow: hidden;">
        <div id="field-pictrue">
            <div class="swiper-wrapper">
               
                <c:forEach items="${list }" var="c">
                	<div class="swiper-slide">
	                    <div class="title">${c.name }</div>
	                    <div class="pictrue"><a href="getClub.do?id=${c.id }"><img src="/${c.img }" alt="南山区"></a></div>
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
</body>

</html>
