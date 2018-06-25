<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>首页</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="../../css/idangerous.swiper.css"/>
    <link rel="stylesheet" href="../../css/scrollpagination.css"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/idangerous.swiper.min.js"></script>
    <script type="text/javascript" src="../../js/swipe.js"></script>
    <script type="text/javascript" src="../../js/scrollpagination.js"></script>
    <script>
    // 广告图片横屏滚动
        function showModal()
        {
            $("#myModal").css("visibility", "visible");
            $($(".reveal-modal-bg")[0]).css("display", "block");
        }
        function closeModal() {
            $("#myModal").css("visibility", "hidden");
            $($(".reveal-modal-bg")[0]).css("display", "none");
        }
        $(document).ready(function(){
            var bullets = $('#position > li');
            slider = Swipe($('#slider')[0], {
                auto : 2000,
                continuous : true,
                callback : function(pos) {
                    var maxNum = bullets.length;
                    if(maxNum == 3){
                        bullets.removeClass('on');
                        pos = pos < 3 ? pos : pos - 3;
                        bullets.eq(pos).addClass('on');
                    }else{
                        bullets.removeClass('on');
                        pos = pos > maxNum ? maxNum : pos;
                        bullets.eq(pos).addClass('on');
                    }
                }
            });
        });
    </script>

</head>
<body style="background-color: #ebebeb;">

<div id="slider" class="banner swipe" style="visibility: visible;">
    <div class="swipe-wrap">
    	<c:forEach items="${advertis}" var="o" end="3">
        <figure>
            <div class="wrap">
                <div class="image">
                    <a href="${o.ad_http_url}">
                        <img src="${o.ad_picture_url}" alt="${o.ad_title}" title="${o.ad_title}">
                    </a>
                </div>
            </div>
        </figure>
		</c:forEach>
        
    </div>
    <nav class="page-swipe">
        <ul id="position">
<!--             <li class="on"></li> -->
<!--             <li class=""></li> -->
<!--             <li class=""></li> -->
            <!-- <li class=""></li> -->
        <c:forEach items="${advertis}" var="o" end="3" varStatus="v">
        	<li class='<c:if test="${v.index==0}">on</c:if>' ></li>
		</c:forEach>
        </ul>
    </nav>
</div>
<div class="zhubao">
    <ul class="zhubao-ul">
    	<c:forEach items="${categorylist}" var="o" end="3">
        <li class="zhubao-li">
        	<a href="tocategoryCommodity.do?categoryId=${o.id}">
        	<c:if test="${not empty o.imgurl}" var="hava">
	     		<img class="zhubao-img" src="${o.imgurl }">
	     	</c:if>
	     	<c:if test="${!hava }">
	     		<img class="zhubao-img" src="../../images/products/whiteJade_03.png">
	     	</c:if>
            <br>
            <span class="zhubao-text">${o.category_name}</span>
            </a>
        </li>
        </c:forEach>
    </ul>
</div>
<div class="clear"></div>
<div class="productLast" id="content">
    <!-- 最新产品上架列表项目 -->
    <ul id="content_ul">
    	<c:forEach items="${commoditys}" var="o">
        <li class="jade sort-prod-lastNew">
            <a href="user_getCommodityDetail.do?cid=${o.id}">
            <c:if test="${o.commodityStore==0 }">
            	<img  class="hadSaled" src="/image/yisale.png" style="width:45%;"/>
            </c:if>
            <img src="${o.acquiescent_picture}"><br>
            <span class="zhubaoPrice">￥<fmt:formatNumber value="${o.defaultPrice}" pattern="#0.#"/>
            </span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
            <span class="zhubaoState">${o.commodity_name}</span><br>
            <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
			</a>
        </li>
        </c:forEach>
    </ul>
</div>
<div id="loading" class="loading"><img src="../../images/loading.gif"/></div>
</body>
<script src="/js/scrollpagination.js"></script>
<script src="/js/downpullpage_common.js"></script>
<script type="text/javascript">
//调用下拉分页
$(function(){
    page("content",'nextPage_index.do',function(d){
        $('#content_ul').append(d);
    });
});
</script>
</html>