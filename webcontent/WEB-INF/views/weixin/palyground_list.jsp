<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地-场馆</title>
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
        <div class="reserve-nav">
            <a class="icon" flex-box="0" href="#"><i class="iconfont icon-shouye"></i></a>
            <a class="icon" flex-box="0" href="#"><i class="iconfont icon-search"></i></a>
            <a class="icon" flex-box="0" href="#"><i class="iconfont icon-ditu"></i></a>
        </div>
        <div class="city">深圳<a class="icon" flex-box="0" href="#"><i class="iconfont icon-jiantoub"></i></a></div>
    </header>
    <nav class="select-query hide" id="select-query">
        <div class="cover-layer" bind-search-close></div>
        <ul class="nav" flex="box:mean">
            <li class="item" bind-search>
                <h3>全城<i class="iconfont icon-jiantoub"></i></h3>
            </li>
            <li class="item" bind-search>
                <h3>球场类型<i class="iconfont icon-jiantoub"></i></h3>
            </li>
        </ul>
        <div class="box" id="area">
            <ul class="list">
                <c:forEach items="${Area }" var="o">
					<li bind-search-key data-value="${o.key }" data-name="areaid" <c:if test="${empty o.key }"> class="on" </c:if> >${o.value }</li>
				</c:forEach>
            </ul>
        </div>
        <div class="box" id="courtType">
            <ul class="list">
                <c:forEach items="${CourtType }" var="o">
					<li bind-search-key data-value="${o.key }" data-name="courtType" <c:if test="${empty o.key }"> class="on" </c:if>  >${o.value }</li>
				</c:forEach>
            </ul>
        </div>

    </nav>
    <ul class="yuyue-list" id="next-box" >
     	<!--start-->
    	<c:forEach items="${list }" var="o">
	        <li>
	            <a href="#" flex="cross:stretch">
	                <div class="left" flex-box="0" flex="main:center cross:center">
	                    <div class="pictrue">
	                        <img src="/${o.pdImg }" alt="${o.name }">
	                    </div>
	                </div>
	                <div class="center" flex="dir:top main:justify" flex-box="1">
	                    <h4 class="title">${o.name }</h4>
	                    <div class="pingfen">
	                    	<fmt:parseNumber integerOnly="true" value="${o.evaluate_score }" var="score" /> 
	                        <c:forEach begin="1" end="${score }" >
                        	 	<i class="iconfont icon-xingtrue"></i>
	                        </c:forEach>
	                        <c:forEach begin="1" end="${5-score }" >
	                        	<i class="iconfont icon-xingfalse"></i>
	                        </c:forEach>
	                    </div>
	                    <div class="tag">
	                        <span>${o.site_type }</span>
	                        <span>${o.court_type }</span>
	                        <span>${o.space_type }</span>
	                    </div>
	                    <div class="price">
<!-- 	                        <b>￥350</b>起<del>300</del> -->
	                        
	                        <b>￥${o.money }</b>起<del>${o.price }</del>
	                    </div>
	                    <div class="dizhi">
	                        <i class="iconfont icon-dingwei"></i>${o.address }
	                    </div>
	                </div>
	                <div class="right" flex="dir:top main:center cross:center" flex-box="0">
	                    <div class="btn" onclick="reservations(${o.id})">预定</div>
	                    <div class="juli">${o.distanceMeters }km</div>
	                </div>
	            </a>
	        </li>
	    </c:forEach>
	    <!--end-->
    </ul>
    <div class="load" id="next-load">上拉加载更多</div>
    <script src="/weixin/js/getNext.js"></script>
    <script>
	    function clickMap(){
	    	var all = document.querySelectorAll('#area .on,#courtType .on');
			var data = next.data;
			data.pageNumber = 1;
	        for (var i = 0; i < all.length; i++) {
	        	 console.info(all[i].dataset.value);
	        	var name = all[i].dataset.name;
	        	var value = all[i].dataset.value;
	        	data[name] = value;
	        }
	        console.log(data);
	        document.getElementById('next-box').innerHTML = '';
	        next.status = true;
	        next.getNextData();
		}    
	    var next = Tool.getNext({
	        data: {
	            pageNumber: 2
	        }
	    });
	    
	    function reservations(id,type){
        	location.href = 'playgrounddetail.do?playgroundId='+id+'&userId=${user.id}';
        }
        Tool.tapAttr('bind-search', Tool.showSelect); //显示选择排序
        Tool.tapAttr('bind-search-close', Tool.selectClose); //关闭选择
        Tool.tapAttr('bind-search-key', Tool.selectKey); //选择关键词
        Tool.tapAttr('bind-search-screen', Tool.selectScreen); //筛选
        Tool.tapAttr('bind-search-key', clickMap); //选择关键词
    </script>
</body>

</html>