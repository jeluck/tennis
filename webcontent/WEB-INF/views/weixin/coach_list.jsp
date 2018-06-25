<!--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
-->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地-教练</title>
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
                <h3>服务<i class="iconfont icon-jiantoub"></i></h3>
            </li>
            <li class="item" bind-search>
                <h3>排序<i class="iconfont icon-jiantoub"></i></h3>
            </li>
            <li class="item" bind-search>
                <h3>筛选<i class="iconfont icon-jiantoub"></i></h3>
            </li>
        </ul>
        <div class="box" id="area">
            <ul class="list">
                <c:forEach items="${Area }" var="o">
                    <li bind-search-key data-value="${o.key }" data-name="areaid" <c:if test="${empty o.key }"> class="on" </c:if> >${o.value }</li>
                </c:forEach>
            </ul>
        </div>
        <div class="box" id="service">
            <ul class="list">
                <c:forEach items="${Services }" var="o">
                    <li bind-search-key data-value="${o.key }" data-name="services" <c:if test="${empty o.key }"> class="on" </c:if> >${o.value }</li>
                </c:forEach>
            </ul>
        </div>
        <div class="box" id="sort">
            <ul class="list">
                <c:forEach items="${Sort }" var="o">
                    <c:if test="${o.key != 'distance_farToNearly' && o.key != 'price_lowToHigh' && o.key != 'evaluation_highToLow' && o.key != 'Seniority_lowToHigh'}">
                        <li  data-value="${o.key }" data-name="sort" onclick="changeSort(this)" <c:if test="${empty o.key }"> class="on" </c:if> >${o.value }</li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="box screen" id="screen">
            <div class="item" flex="cross:stretch">
                <div class="key" flex-box="0">性别</div>
                <div class="select" flex-box="1" flex>
                    <div class="value on" flex-box="0" data-value="" data-name="screening" bind-search-screen>不限</div>
                    <div class="value" flex-box="0" data-value="男" data-name="screening" bind-search-screen>男</div>
                    <div class="value" flex-box="0" data-value="女" data-name="screening" bind-search-screen>女</div>
                </div>
            </div>
            <div class="item" flex="cross:stretch">
                <div class="key" flex-box="0">国籍</div>
                <div class="select" flex-box="1" flex>
                    <div class="value on" flex-box="0" data-value="" data-name="screening" bind-search-screen>不限</div>
                    <div class="value" flex-box="0" data-value="中教" data-name="screening" bind-search-screen>中教</div>
                    <div class="value" flex-box="0" data-value="外教" data-name="screening" bind-search-screen>外教</div>
                    <div></div>
                    <div></div>
                </div>
            </div>
            <div class="item" flex="cross:stretch">
                <div class="key" flex-box="0">执教经验</div>
                <div class="select" flex-box="1" flex>
                    <div class="value on" flex-box="0" data-value="" data-name="screening" bind-search-screen>不限</div>
                    <div class="value" flex-box="0" data-value="3" data-name="screening" bind-search-screen>&lt;3年</div>
                    <div class="value" flex-box="0" data-value="3_5" data-name="screening" bind-search-screen>3-5年</div>
                    <div class="value" flex-box="0" data-value="5" data-name="screening" bind-search-screen>&gt;5年</div>
                </div>
            </div>
            <div class="item" flex="cross:stretch">
                <div class="key" flex-box="0">教练专业</div>
                <div class="select" flex-box="1" flex>
                    <div class="value on" flex-box="0" data-value="" data-name="screening" bind-search-screen>不限</div>
                    <div class="value" flex-box="0" data-value="少儿(业余)" data-name="screening" bind-search-screen>少儿(业余)</div>
                    <div class="value" flex-box="0" data-value="成人(业余)" data-name="screening" bind-search-screen>成人(业余)</div>
                    <div class="value" flex-box="0" data-value="全部(业余)" data-name="screening" bind-search-screen>全部(业余)</div>
                    <div class="value" flex-box="0" data-value="职业" data-name="screening" flex-box="0" bind-search-screen>职业</div>
                </div>
            </div>
            <div class="item" flex="cross:stretch">
                <div class="key" flex-box="0">资格认证</div>
                <div class="select" flex-box="1" flex>
                    <div class="value on" flex-box="0" data-value="" data-name="screening" bind-search-screen>不限</div>
                    <div class="value" flex-box="0" data-value="ph1" data-name="screening" bind-search-screen>ITF</div>
                    <div class="value" flex-box="0" data-value="ph2" data-name="screening" bind-search-screen>USPTA</div>
                    <div class="value" flex-box="0" data-value="ph3" data-name="screening" bind-search-screen>PTR</div>
                    <div class="value" flex-box="0" data-value="ph4" data-name="screening" bind-search-screen>RPT</div>
                    <div class="value" flex-box="0" data-value="ph5" data-name="screening" bind-search-screen>EQUELITE</div>
                    <div class="value" flex-box="0" data-value="ph6" data-name="screening" bind-search-screen>CTA</div>
                </div>
            </div>
            <div class="btnbox" flex="box:mean">
                <div bind-search-close>取消</div>
                <div bind-search-close onclick="clickMap(this);">确定</div>
            </div>
        </div>
    </nav>
    <ul class="yuyue-list jiaolian" id="next-box">
        <!--start-->
        <c:forEach items="${list }" var="o">
            <li>
                <a href="#" flex="cross:stretch">
                    <div class="left" flex-box="0" flex="main:center cross:center">
                        <div class="pictrue">
                            <img src="/${o.head_portrait }" alt="${o.name }">
                        </div>
                    </div>
                    <div class="center" flex="dir:top main:justify" flex-box="1">
                        <h4 class="title">${o.name }<em>执教${o.teaching }年</em></h4>
                        <div class="pingfen">
                            <fmt:parseNumber integerOnly="true" value="${o.evaluate_score }" var="score" />
                            <c:forEach begin="1" end="${score }">
                                <i class="iconfont icon-xingtrue"></i>
                            </c:forEach>
                            <c:forEach begin="1" end="${5-score }">
                                <i class="iconfont icon-xingfalse"></i>
                            </c:forEach>
                        </div>
                        <div class="tag">
                            <c:if test="${fn:contains(o.services, 4) }"><span>上门</span></c:if>
                            <c:if test="${fn:contains(o.services, 2) }"><span>器材</span></c:if>
                            <c:if test="${fn:contains(o.services, 3) }"><span>驻场</span></c:if>
                            <c:if test="${fn:contains(o.services, 1) }"><span>场地</span></c:if>
                        </div>
                        <div class="price">
                            <b>￥${o.money }</b><del>${o.price }</del>
                        </div>
                    </div>
                    <div class="right" flex="dir:top main:center cross:center" flex-box="0">
                        <div class="btn" onclick="reservations(${o.id},${o.coachType})">预定</div>
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
            var all = document.querySelectorAll('#area .on,#service .on,#sort .on');
            var screen = document.querySelectorAll('#screen .on');
            var arr = [];
			var data = next.data;
			data.pageNumber = 1;
            for (var i = 0; i < all.length; i++) {
            	 console.info(all[i].dataset.value);
            	var name = all[i].dataset.name;
            	var value = all[i].dataset.value;
            	data[name] = value;
            }
            for (var i=0;i<screen.length;i++) {
            	arr.push(screen[i].dataset.value);
            }
            data['screening'] = arr.join(',');
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

        Tool.tapAttr('bind-search', Tool.showSelect); //显示选择排序
        Tool.tapAttr('bind-search-close', Tool.selectClose); //关闭选择
        Tool.tapAttr('bind-search-key', Tool.selectKey); //选择关键词
        Tool.tapAttr('bind-search-key', clickMap); //选择关键词
        Tool.tapAttr('bind-search-screen', Tool.selectScreen); //筛选

        function changeSort(el) {
        	Tool.selectKey.call(el);
        	clickMap();
            if (el.dataset.value == "distance_farToNearly") {
                el.innerHTML = "距离从近到远";
                el.dataset.value = "distance_nearlyToFar";
            } else if (el.dataset.value == "distance_nearlyToFar") {
                el.innerHTML = "距离从远到近";
                el.dataset.value = "distance_farToNearly";
            } else if (el.dataset.value == "price_lowToHigh") {
                el.innerHTML = "价格从高到低";
                el.dataset.value = "price_highToLow";
            } else if (el.dataset.value == "price_highToLow") {
                el.innerHTML = "价格从低到高";
                el.dataset.value = "price_lowToHigh";
            } else if (el.dataset.value == "evaluation_highToLow") {
                el.innerHTML = "评价从低到高";
                el.dataset.value = "evaluation_lowToHigh";
            } else if (el.dataset.value == "evaluation_lowToHigh") {
                el.innerHTML = "评价从高到低";
                el.dataset.value = "evaluation_highToLow";
            } else if (el.dataset.value == "Seniority_highToLow") {
                el.innerHTML = "教龄从低到高";
                el.dataset.value = "Seniority_lowToHigh";
            } else if (el.dataset.value == "Seniority_lowToHigh") {
                el.innerHTML = "教龄从高到低";
                el.dataset.value = "Seniority_highToLow";
            }
        }
        function reservations(id,type){
        	location.href = 'coachdetail.do?coachId='+id+'&coachType='+type+'&userId=${user.id}';
        }
    </script>
</body>

</html>