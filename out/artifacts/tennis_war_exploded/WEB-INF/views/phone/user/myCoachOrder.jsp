<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!doctype html>
        <html class="lz-html" lang="en">

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
            <meta name="apple-mobile-web-app-capable" content="yes">
            <meta name="apple-mobile-web-app-status-bar-style" content="black">
            <meta name="format-detection" content="telephone=no">
            <title>我的订单</title>
            <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
            <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
            <script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
            <script src="/js/admin_common.js?${getVersion}"></script>
            <script src="/js/common.js??${getVersion}"></script>
            <!--本页面单独使用文件 start-->
            <link rel="stylesheet" href="/css/myOrder.css?${getVersion}">
            <%@ include file="./common/common_init.jsp" %>
                <script src="/js/myOrder.js?${getVersion}"></script>
                <!--本页面单独使用文件 end-->
        </head>

        <body class="lz-body">
            <main class="lz-main">
                <section class="lz-myorder">
                    <input type="hidden" value="${weuserId }" id="userId" />
                    <input type="hidden" value="${orderType }" id="orderType" />
                    <!-- 弹层选择分类 start -->
                    <h2 class="lz-tit-5" onClick="lz.myorderType(this);">订单类型<span class="lz-right">
     		<c:if test="${not empty type }">
    			${type }
    		</c:if>
    		<c:if test="${empty type }">
    			全部
    		</c:if>   	
    	<i class="lz-iconfont lz-icon-jiantou"></i></span></h2>
                    <div class="lz-myorder-type">
                        <ul class="lz-list-4">
                            <li class="lz-cur" data-lz-url="toCoachMineOrderform.do?weuserId=${weuserId }&type=全部">全部</li>
                            <li data-lz-url="toCoachMineOrderform.do?orderType=1&weuserId=${weuserId }&type=场馆">场馆</li>
                            <li data-lz-url="toCoachMineOrderform.do?orderType=3&weuserId=${weuserId }&type=活动">活动</li>
                            <li data-lz-url="toCoachMineOrderform.do?orderType=4&weuserId=${weuserId }&type=培训">培训</li>
                            <li data-lz-url="toCoachMineOrderform.do?orderType=5&weuserId=${weuserId }&type=赛事">赛事</li>
                        </ul>
                    </div>
                    <!-- 弹层选择分类 end -->
                </section>
                <!-- 选项卡 start -->
                <section data-lz-tab="jiaoLianXuanXiangKa,#lz-tab-nav,#lz-tab-box">
                    <ul class="lz-tab-nav" id="lz-tab-nav">
                        <li>全部订单</li>
                        <li>本周订单</li>
                        <li>待进行</li>
                        <li>待评价</li>
                    </ul>
                    <input type="hidden" value="${weuserId } " />
                    <div class="lz-tab-box" id="lz-tab-box">
                        <div>
                            <ul class="lz-list-3" id="getNextAll-box">
                                <!-- 全部 -->
                                <c:forEach items="${whole.dataList }" var="o">
                                    <input type="hidden" value="${o.orderMainNo }">
                                    <li>
                                        <div class="lz-top">下单时间：
                                            <time>${o.createTime }</time>
                                            <span class="lz-right">
		              	<c:if test="${o.payStatus == 1 }">
		              		待付款
		              	</c:if>
		              	<c:if test="${o.payStatus == 2 }">
		              		部分支付
		              	</c:if>
		              	<c:if test="${o.payStatus == 3 }">
		              		待进行 
		              	</c:if>
		              	<c:if test="${o.payStatus == 4 }">
		              		消费中
		              	</c:if>
		              	<c:if test="${o.payStatus == 5 }">
		              		待评价
		              	</c:if>
		              	<c:if test="${o.payStatus == 8 }">
		              		已取消
		              	</c:if>
		              	<c:if test="${o.payStatus == 10 }">
		              		已取消
		              	</c:if>
		              	<c:if test="${o.payStatus == 11 }">
		              		已评价
		              	</c:if>
		              </span>
                                        </div>
                                        <!-- 全部的当前页数 -->
                                        <input type="hidden" value="${week.pageCount }" />
                                        <div class="lz-center" <c:if test="${o.payStatus == 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }" </c:if>
                                            <c:if test="${o.payStatus != 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }&check=check" </c:if>
                                            >
                                            <c:if test="${not empty o.img}" var="hava">
                                                <div class="lz-cell lz-pic" style="background-image:url('${o.img }');"></div>
                                            </c:if>
                                            <c:if test="${!hava }">
                                                <div class="lz-cell lz-pic" style="background-image:url('/image/nophoto_120X120.jpg');"></div>
                                            </c:if>
                                            <div class="lz-cell lz-tit">${o.name }
                                                <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>${o.prodTotalNum }小时</div>
                                            </div>
                                            <div class="lz-cell lz-count">
                                                <br> x${o.prodTotalNum }</div>
                                        </div>
                                        <div class="lz-bottom">
                                            <div class="lz-cell lz-l"> <strong>总金额：￥${o.totalAmount }</strong> </div>
                                            <%-- 					  <c:if test="${o.payStatus == 4 }"> --%>
                                                <%-- 		               	 <div class="lz-cell lz-r" onclick="carryOrderform(this,${weuserId },5,'${o.orderMainNo}')"  >确认完成</div> --%>
                                                    <%-- 		              </c:if> --%>
                                                        <c:if test="${o.payStatus == 1 }">
                                                            <div class="lz-cell lz-r" data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }">支付</div>
                                                        </c:if>

                                                        <c:if test="${o.payStatus == 3 }">
                                                            <div class="lz-cell lz-r" onclick="userCancelOrder(this,'${o.orderMainNo}')">取消订单</div>
                                                        </c:if>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div id="getNextAll" class="get-next">加载更多</div>
                        </div>
                        <div>
                            <ul class="lz-list-3" id="getWeekAll-box">
                                <!-- 本周订单 -->
                                <c:forEach items="${week.dataList }" var="o">
                                    <li>
                                        <div class="lz-top">下单时间：
                                            <time>${o.createTime }</time>
                                            <span class="lz-right">
		              	<c:if test="${o.payStatus == 1 }">
		              		待付款
		              	</c:if>
		              	<c:if test="${o.payStatus == 2 }">
		              		部分支付
		              	</c:if>
		              	<c:if test="${o.payStatus == 3 }">
		              		待进行 
		              	</c:if>
		              	<c:if test="${o.payStatus == 4 }">
		              		消费中
		              	</c:if>
		              	<c:if test="${o.payStatus == 5 }">
		              		待评价
		              	</c:if>
		              	<c:if test="${o.payStatus == 8 }">
		              		已取消
		              	</c:if>
		              	<c:if test="${o.payStatus == 10 }">
		              		已取消
		              	</c:if>
		              	<c:if test="${o.payStatus == 11 }">
		              		已评价
		              	</c:if>
		              </span>
                                        </div>
                                        <!--  本周订单当前页数 -->
                                        <input type="hidden" value="${week.pageCount }" />
                                        <div class="lz-center" <c:if test="${o.payStatus == 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }" </c:if>
                                            <c:if test="${o.payStatus != 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }&check=check" </c:if>
                                            >
                                            <c:if test="${not empty o.img}" var="hava">
                                                <div class="lz-cell lz-pic" style="background-image:url('${o.img }');"></div>
                                            </c:if>
                                            <c:if test="${!hava }">
                                                <div class="lz-cell lz-pic" style="background-image:url('/image/nophoto_120X120.jpg');"></div>
                                            </c:if>
                                            <div class="lz-cell lz-tit">${o.name }
                                                <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>${o.prodTotalNum }小时</div>
                                            </div>
                                            <div class="lz-cell lz-count">
                                                <br> x${o.prodTotalNum }</div>
                                        </div>
                                        <div class="lz-bottom">
                                            <div class="lz-cell lz-l"> <strong>总金额：￥${o.totalAmount }</strong> </div>
                                            <%-- 		              <c:if test="${o.payStatus == 4 }"> --%>
                                                <%-- 		               	 <div class="lz-cell lz-r" onclick="carryOrderform(this,${weuserId },5,'${o.orderMainNo}')"  >确认完成</div> --%>
                                                    <%-- 		              </c:if> --%>
                                                        <c:if test="${o.payStatus == 1 }">
                                                            <div class="lz-cell lz-r" data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }">支付</div>
                                                        </c:if>

                                                        <c:if test="${o.payStatus == 3 }">
                                                            <div class="lz-cell lz-r" onclick="userCancelOrder(this,'${o.orderMainNo}')">取消订单</div>
                                                        </c:if>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div id="getNextWeek" class="get-next">加载更多</div>
                        </div>
                        <div>
                            <ul class="lz-list-3" id="getToBePerformed-box">
                                <!-- 待进行 -->
                                <c:forEach items="${toBePerformed.dataList }" var="o">
                                    <li>
                                        <div class="lz-top">下单时间：
                                            <time>${o.createTime }</time>
                                            <span class="lz-right">
		              	<c:if test="${o.payStatus == 3 }">
		              		待进行 
		              	</c:if>
		              	<c:if test="${o.payStatus == 4 }">
		              		消费中
		              	</c:if>
		              </span>

                                        </div>
                                        <!-- 待进行当前页数 -->
                                        <input type="hidden" value="${week.pageCount }" />
                                        <div class="lz-center" <c:if test="${o.payStatus == 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }" </c:if>
                                            <c:if test="${o.payStatus != 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }&check=check" </c:if>
                                            >
                                            <c:if test="${not empty o.img}" var="hava">
                                                <div class="lz-cell lz-pic" style="background-image:url('${o.img }');"></div>
                                            </c:if>
                                            <c:if test="${!hava }">
                                                <div class="lz-cell lz-pic" style="background-image:url('/image/nophoto_120X120.jpg');"></div>
                                            </c:if>
                                            <div class="lz-cell lz-tit">${o.name }
                                                <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>${o.prodTotalNum }小时</div>
                                            </div>
                                            <div class="lz-cell lz-count">
                                                <br> x${o.prodTotalNum }</div>
                                        </div>
                                        <div class="lz-bottom">
                                            <div class="lz-cell lz-l"> <strong>总金额：￥${o.totalAmount }</strong> </div>
                                            <%-- 		              <c:if test="${o.payStatus == 4 }"> --%>
                                                <%-- 		              	<div class="lz-cell lz-r" data-lz-url="carryOrderform.do?weuserId=${weuserId }&payStatus=5&orderMainNo=${o.orderMainNo}" >确认完成</div> --%>
                                                    <%-- 		              </c:if> --%>

                                                        <c:if test="${o.payStatus == 3 }">
                                                            <div class="lz-cell lz-r" onclick="userCancelOrder(this,'${o.orderMainNo}')">取消订单</div>
                                                        </c:if>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div id="getNextToBePerformed" class="get-next">加载更多</div>
                        </div>
                        <div>
                            <ul class="lz-list-3" id="getToBeEvaluated-box">
                                <!-- 待评价 -->
                                <c:forEach items="${toBeEvaluated.dataList }" var="o">
                                    <li>
                                        <div class="lz-top">下单时间：
                                            <time>${o.createTime }</time>
                                            <span class="lz-right">
		              	待评价
		              </span>

                                        </div>
                                        <!-- 待评价当前页数 -->
                                        <input type="hidden" value="${week.pageCount }" />
                                        <div class="lz-center" <c:if test="${o.payStatus == 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }" </c:if>
                                            <c:if test="${o.payStatus != 1 }"> data-lz-url="orderdetail.do?orderMainNo=${o.orderMainNo }&check=check" </c:if>
                                            >
                                            <c:if test="${not empty o.img}" var="hava">
                                                <div class="lz-cell lz-pic" style="background-image:url('${o.img }');"></div>
                                            </c:if>
                                            <c:if test="${!hava }">
                                                <div class="lz-cell lz-pic" style="background-image:url('/image/nophoto_120X120.jpg');"></div>
                                            </c:if>
                                            <div class="lz-cell lz-tit">${o.name }
                                                <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>${o.prodTotalNum }小时</div>
                                            </div>
                                            <div class="lz-cell lz-count">
                                                <br> x${o.prodTotalNum }</div>
                                        </div>
                                        <div class="lz-bottom">
                                            <div class="lz-cell lz-l"> <strong>总金额：￥${o.totalAmount }</strong> </div>
                                            <c:if test="${o.payStatus == 5 }">
                                                <div class="lz-cell lz-r" data-lz-url="assess.do?orderMainNo=${o.orderMainNo }">去评价</div>
                                            </c:if>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div id="getToBeEvaluated" class="get-next">加载更多</div>
                        </div>
                    </div>
                </section>
            </main>
            <script type="text/javascript" src="/js/plugin/getNext.js"></script>
            <script type="text/javascript">
                //全部
                lz.getNext('getNextAll', 'getNextAll-box', 'getCoachOrder.do', {
                    type: 0,
                    orderType: $("#orderType").val(),
                    pageNumber: 2,
                    weuserId: $("#userId").val()
                }, function (index, data) {
                    var userId = $("#userId").val();
                    var payStatus = "";
                    var div = "";
                    if (data.payStatus == 1) {
                        payStatus = '待付款';
                        div = '<div class="lz-cell lz-r" data-lz-url="orderdetail.do?orderMainNo=' + data.orderMainNo + '"  >支付</div>';
                    } else if (data.payStatus == 2) {
                        payStatus = '部分支付';
                    } else if (data.payStatus == 3) {
                        payStatus = '待进行 ';
                        div = '<div class="lz-cell lz-r" onclick="userCancelOrder(this,' + data.orderMainNo + ')"  >取消订单</div>';
                    } else if (data.payStatus == 4) {
                        payStatus = '消费中';
                        // 		  div = '<div class="lz-cell lz-r" onclick="carryOrderform(this,'+userId+',5,'+data.orderMainNo+')"  >确认完成</div>';
                    } else if (data.payStatus == 5) {
                        payStatus = '待评价';
                    } else if (data.payStatus == 8) {
                        payStatus = '已取消';
                    } else if (data.payStatus == 10) {
                        payStatus = '已取消';
                    } else if (data.payStatus == 11) {
                        payStatus = '已评价';
                    }
                    var orderMainNo = data.orderMainNo;
                    var img = "";
                    if (data.img == null || data.img == "") {
                        img = '/image/nophoto_120X120.jpg';
                    } else {
                        img = data.img;
                    }
                    var url = "";
                    if (data.payStatus == 1) {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '"';
                    } else {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '&check=check"';
                    }
                    var sHtml = '\
		 <input type="hidden" value="orderMainNo">\
		 <div class="lz-top">下单时间：\
	      <time>' + data.createTime + '</time>\
	      <span class="lz-right">\
	      	' + payStatus + '\
	      </span>\
	    </div>\
	    <div class="lz-center" onclick=' + url + '>\
	      <div class="lz-cell lz-pic" style="background-image:url(' + img + ');"></div>\
	      <div class="lz-cell lz-tit">' + data.name + '\
	        <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>' + data.prodTotalNum + '小时</div>\
	      </div>\
	      <div class="lz-cell lz-count">\
	      <br>\
	        x' + data.prodTotalNum + '</div>\
	    </div>\
	    <div class="lz-bottom">\
	      <div class="lz-cell lz-l"> <strong>总金额：￥' + data.totalAmount + '</strong> </div>\
	      ' + div + '\
	    </div>\
	';
                    return sHtml;
                });

                //本周订单 
                lz.getNext('getNextWeek', 'getWeekAll-box', 'getCoachOrder.do', {
                    type: 1,
                    orderType: $("#orderType").val(),
                    pageNumber: 2,
                    weuserId: $("#userId").val()
                }, function (index, data) {
                    var userId = $("#userId").val();
                    var payStatus = "";
                    var div = "";
                    if (data.payStatus == 1) {
                        payStatus = '待付款';
                        div = '<div class="lz-cell lz-r" data-lz-url="orderdetail.do?orderMainNo=' + data.orderMainNo + '"  >支付</div>';
                    } else if (data.payStatus == 2) {
                        payStatus = '部分支付';
                    } else if (data.payStatus == 3) {
                        payStatus = '待进行 ';
                        div = '<div class="lz-cell lz-r" onclick="userCancelOrder(this,' + data.orderMainNo + ')"  >取消订单</div>';
                    } else if (data.payStatus == 4) {
                        payStatus = '消费中';
                        // 		  div = '<div class="lz-cell lz-r" onclick="carryOrderform(this,'+userId+',5,'+data.orderMainNo+')"  >确认完成</div>';
                    } else if (data.payStatus == 5) {
                        payStatus = '待评价';
                    } else if (data.payStatus == 8) {
                        payStatus = '已取消';
                    } else if (data.payStatus == 10) {
                        payStatus = '已取消';
                    } else if (data.payStatus == 11) {
                        payStatus = '已评价';
                    }
                    var url = "";
                    if (data.payStatus == 1) {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '"';
                    } else {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '&check=check"';
                    }
                    var img = "";
                    if (data.img == null || data.img == "") {
                        img = '/image/nophoto_120X120.jpg';
                    } else {
                        img = data.img;
                    }

                    var sHtml = '\
		 <div class="lz-top">下单时间：\
	      <time>' + data.createTime + '</time>\
	      <span class="lz-right">\
	      	' + payStatus + '\
	      </span>\
	    </div>\
	    <div class="lz-center" onclick=' + url + '>\
	      <div class="lz-cell lz-pic" style="background-image:url(' + img + ');"></div>\
	      <div class="lz-cell lz-tit">' + data.name + '\
	        <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>' + data.prodTotalNum + '小时</div>\
	      </div>\
	      <div class="lz-cell lz-count">\
	      <br>\
	        x' + data.prodTotalNum + '</div>\
	    </div>\
	    <div class="lz-bottom">\
	      <div class="lz-cell lz-l"> <strong>总金额：￥' + data.totalAmount + '</strong> </div>\
	      ' + div + '\
	    </div>\
	';
                    return sHtml;
                });

                //待进行
                lz.getNext('getNextToBePerformed', 'getToBePerformed-box', 'getCoachOrder.do', {
                    type: 2,
                    orderType: $("#orderType").val(),
                    pageNumber: 2,
                    weuserId: $("#userId").val()
                }, function (index, data) {
                    var userId = $("#userId").val();
                    var div = "";
                    var img = "";
                    var url = "";
                    if (data.payStatus == 1) {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '"';
                    } else {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '&check=check"';
                    }
                    if (data.payStatus == 3) {
                        payStatus = '待进行 ';
                        div = '<div class="lz-cell lz-r" onclick="userCancelOrder(this,' + data.orderMainNo + ')"  >取消订单</div>';
                    } else if (data.payStatus == 4) {
                        payStatus = '消费中';
                        // 		  div = '<div class="lz-cell lz-r" onclick="carryOrderform(this,'+userId+',5,'+data.orderMainNo+')"  >确认完成</div>';
                    }

                    if (data.img == null || data.img == "") {
                        img = '/image/nophoto_120X120.jpg';
                    } else {
                        img = data.img;
                    }
                    if (data.payStatus == 4) {
                        div = '<div class="lz-cell lz-r" onclick="carryOrderform(this,' + userId + ',5,' + data.orderMainNo + ')"  >确认完成</div>';
                    }
                    var sHtml = '\
		 <div class="lz-top">下单时间：\
	      <time>' + data.createTime + '</time>\
	      <span class="lz-right">\
	      	' + payStatus + '\
	      </span>\
	    </div>\
	    <div class="lz-center" onclick=' + url + '>\
	      <div class="lz-cell lz-pic" style="background-image:url(' + img + ');"></div>\
	      <div class="lz-cell lz-tit">' + data.name + '\
	        <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>' + data.prodTotalNum + '小时</div>\
	      </div>\
	      <div class="lz-cell lz-count">\
	      <br>\
	        x' + data.prodTotalNum + '</div>\
	    </div>\
	    <div class="lz-bottom">\
	      <div class="lz-cell lz-l"> <strong>总金额：￥' + data.totalAmount + '</strong> </div>\
	      ' + div + '\
	    </div>\
	';
                    return sHtml;
                });

                //待评价  
                lz.getNext('getToBeEvaluated', 'getToBeEvaluated-box', 'getCoachOrder.do', {
                    type: 3,
                    orderType: $("#orderType").val(),
                    pageNumber: 2,
                    weuserId: $("#userId").val()
                }, function (index, data) {
                    var userId = $("#userId").val();
                    var div = "";
                    var img = "";
                    var url = "";
                    if (data.payStatus == 1) {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '"';
                    } else {
                        url = 'location.href="orderdetail.do?orderMainNo=' + data.orderMainNo + '&check=check"';
                    }
                    if (data.img == null || data.img == "") {
                        img = '/image/nophoto_120X120.jpg';
                    } else {
                        img = data.img;
                    }
                    var sHtml = '\
		 <div class="lz-top">下单时间：\
	      <time>' + data.createTime + '</time>\
	      <span class="lz-right">\
	      	待评价 \
	      </span>\
	    </div>\
	    <div class="lz-center" onclick=' + url + '>\
	      <div class="lz-cell lz-pic" style="background-image:url(' + img + ');"></div>\
	      <div class="lz-cell lz-tit">' + data.name + '\
	        <div class="lz-time"><i class="lz-iconfont lz-icon-shijian"></i>' + data.prodTotalNum + '小时</div>\
	      </div>\
	      <div class="lz-cell lz-count">\
	      <br>\
	        x' + data.prodTotalNum + '</div>\
	    </div>\
	    <div class="lz-bottom">\
	      <div class="lz-cell lz-l"> <strong>总金额：￥' + data.totalAmount + '</strong> </div>\
	    </div>\
	';
                    return sHtml;
                });
            </script>
        </body>

        </html>