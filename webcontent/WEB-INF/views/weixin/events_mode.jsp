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
    <script charset="utf-8" src="http://map.qq.com/api/js?v=1"></script>
	<script>
	var searchService,map,markers = [];
	var markersArray = [];
	var init = function() {
	    var center = new soso.maps.LatLng('${o.latitude}','${o.longitude}');
	    map = new soso.maps.Map(document.getElementById('map_canvas'),{
	        center: center,
	        zoomLevel: 13
	    });
	    var infoWin = new soso.maps.InfoWindow({
	        map: map
	    });
	    infoWin.open('<div style="width:200px;padding-top:10px;">'+
	//         '<img style="float:left;" src=""/> '+
	        '地点</div>', 
	        map.getCenter()
	    );
	    
	    searchService = new soso.maps.SearchService();
	    var zoomControl = new soso.maps.ZoomHintControl({map:map});
	    
	}
	</script>
</head>

<body onload="init()">
    <header class="header" flex="box:mean">
        <div>
            <a class="icon" href="#"><i class="iconfont icon-fanhui"></i></a>
        </div>
        <div flex="main:center cross:center">
            <div flex="cross:stretch">
                <div class="logo" flex="cross:stretch" flex-box="1">
                    <div class="pic"><img src="/weixin/images/test.jpg" alt="弘金地"></div>
                    <div class="hongjindi" style="color: #fff;">弘金地</div>
                </div>
            </div>
        </div>
        <div flex="main:center cross:center">
            <div class="city">网球赛事<a class="icon" flex-box="0" href="#"><i class="iconfont icon-jiantoub"></i></a></div>
        </div>
    </header>

    <div class="vertion" flex="main:justify corss:stretch" style="margin-top: 5px;">
        <a href="#">最新赛事<em class="new">new</em></a>
        <a href="#">赛事报名</a>
    </div>
    <div class="saishi-view">
        <h2 class="title">${o.title }</h2>
        <p class="about">${o.propaganda }</p>
        <div flex>
            <time>${o.create_time }</time>
            <div class="class">${o.conductor }</div>
        </div>
        <div class="yulan">
            <img src="/${o.propagandaImg }" alt="缩略图">
        </div>
        <h3>${o.ztitle }</h3>
        <h4>一：比赛时间</h4>
        <p class="info">${o.timeExplanation }</p>
        <h4>二：比赛地点</h4>
        <p class="info">${o.gameLocation }
       		<div style="width:100%;height:200px" id="map_canvas"></div>
			<div id="info" style="margin-top:10px;">
        </p>
        <h4>三：报名时间</h4>
        <p class="info">${o.signTimeExplanation }</p>
        <h4>四：主办单位</h4>
        <div class="info zhubandanwei" flex="box:mean">
            
            <c:forEach items="${eList }" var="e">
				<c:if test="${e.flag == 'zhuban' }">	
					<div class="item">
		                <div class="pictrue">
		                    <img src="/${e.img }">
		                </div>
		                <div class="img-tit">${e.context }</div>
		            </div>
				</c:if>
            </c:forEach>
        </div>
        <h4>五：承办单位</h4>
        <div class="info chengbandanwei" flex="box:mean">
            <c:forEach items="${eList }" var="e">
				<c:if test="${e.flag == 'chengban' }">	
					<div class="item">
		                <div class="pictrue">
		                    <img src="/${e.img }">
		                </div>
		                <div class="img-tit">${e.context }</div>
		            </div>
				</c:if>
            </c:forEach>
        </div>
        <h4>六：协办单位</h4>
        <div class="info chengbandanwei" flex="box:mean">
            <c:forEach items="${eList }" var="e">
				<c:if test="${e.flag == 'xieban' }">	
					<div class="item">
		                <div class="pictrue">
		                    <img src="/${e.img }">
		                </div>
		                <div class="img-tit">${e.context }</div>
		            </div>
				</c:if>
            </c:forEach>
        </div>
        <h4>七：合作媒体</h4>
        <ul class="hezuo">
	        <c:forEach items="${eList }" var="e">
				<c:if test="${e.flag == 'hez' }">	
		            <li>
		                <img src="/${e.img }" alt="">
		            </li>
		        </c:if>
            </c:forEach>
        </ul>
        <h4>八：赞助商</h4>
        <ul class="hezuo">
          	<c:forEach items="${eList }" var="e">
				<c:if test="${e.flag == 'zanz' }">	
		            <li>
		                <img src="/${e.img }" alt="">
		            </li>
		        </c:if>
            </c:forEach>
        </ul>
        <div class="post-btn">立即报名</div>
        <div class="info">${o.context }</div>
    </div>
    <div class="vertion" flex="main:justify corss:stretch">
        <a href="#">弘金地网球官网</a>
        <a href="#">网球赛事电脑版</a>
    </div>
</body>

</html>