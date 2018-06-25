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
    <div class="my-user">
        <div class="head" flex="dir:top cross:stretch">
            <div class="lv" flex="dir:right" flex-box="0">
                <div>${o.weuser.tennis_level }</div>
            </div>
            <div class="main" flex-box="1" flex="dir:top main:center cross:center">
                <div class="pictrue">
                    <img src="/${o.weuser.head_photo}" alt="${o.weuser.username}">
                </div>
                <div <c:if test="${empty o.weuser.vip}"> class="name pu" </c:if> >
                    ${o.weuser.username}
                    <c:if test="${not empty o.weuser.vip}">
                    	<img class="vip" src="/weixin/images/vip.png" alt="vip">
                    </c:if>
                </div>
            </div>
        </div>
        <div class="leji" flex="main:justify cross:stretch">
            <div class="left">累计训练</div>
            <div class="right">${o.userlessonCount }小时(${o.trainingtimes }次)</div>
        </div>
        <ul class="nav-list">
            <li>
                <a href="#" flex="cross:stretch">
                    <div class="icon" flex-box="0">
                        <i class="iconfont icon-dingdan"></i>
                    </div>
                    <div class="title" flex-box="1">我的订单</div>
                    <div class="more" flex-box="0">
                        <i class="iconfont icon-jiantour"></i>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" flex="cross:stretch">
                    <div class="icon" flex-box="0">
                        <i class="iconfont icon-xiaoxi"></i>
                    </div>
                    <div class="title" flex-box="1">消息通知</div>
                    <div class="more" flex-box="0">
                        充值<i class="iconfont icon-jiantour"></i>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" flex="cross:stretch">
                    <div class="icon" flex-box="0">
                        <i class="iconfont icon-yue"></i>
                    </div>
                    <div class="title" flex-box="1">账户余额</div>
                    <div class="more" flex-box="0">
                        <i class="iconfont icon-jiantour"></i>
                    </div>
                </a>
            </li>
        </ul>
        <ul class="nav-list">
            <li>
                <a href="#" flex="cross:stretch">
                    <div class="icon" flex-box="0">
                        <i class="iconfont icon-shenqingvip"></i>
                    </div>
                    <div class="title" flex-box="1">申请VIP</div>
                    <div class="more" flex-box="0">
                        <i class="iconfont icon-jiantour"></i>
                    </div>
                </a>
            </li>

        </ul>
        <ul class="nav-list">
            <li>
                <a href="#" flex="cross:stretch">
                    <div class="icon" flex-box="0">
                        <i class="iconfont icon-caidan"></i>
                    </div>
                    <div class="title" flex-box="1">更多</div>
                    <div class="more" flex-box="0">
                        <i class="iconfont icon-jiantour"></i>
                    </div>
                </a>
            </li>

        </ul>
    </div>
</body>

</html>