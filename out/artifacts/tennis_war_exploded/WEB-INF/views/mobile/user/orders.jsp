<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>订单列表</title>
    <link rel="stylesheet" href="../../../css/c.css"/>
    <link rel="stylesheet" href="../../../css/customer.css"/>
    <link rel="stylesheet" href="../../../css/personMyOrder.css"/>
    <link rel="stylesheet" href="../../../css/alertJumpConfirm.css"/>
    <script src="/js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
    <script>
        // tab左右切换效果
        $(function(){
            window.onload = function() {
                var $li = $('#tab li');
                var $ul = $('#content ul');

                $li.mouseover(function() {
                    var $this = $(this);
                    var $t = $this.index();
                    $li.removeClass("current");
                    $this.addClass("current");
                    $ul.css('display','none');
                    $ul.eq($t).css('display','block');
                })
            }
        });
        // 弹出隐藏
        function ShowDiv(show_div,bg_div) {
            document.getElementById(show_div).style.display ='block';
            document.getElementById(bg_div).style.display = 'block';
            console.log("test");
        };
        // 关闭取消弹窗
        function CloseDiv(show_div,bg_div) {
            document.getElementById(show_div).style.display = 'none';
            document.getElementById(bg_div).style.display = 'none';
            bgdiv.style.width = document.body.scrollWidth;
// bgdiv.style.height = $(document).height();
            $("#"+bg_div).height($(document).height());
        };
     </script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">我的订单</a>
    </p>
</div>
<div class="orderIndex">
    <ul id="tab">
        <li class="current">待付款</li>
        <li>待发货</li>
        <li>待收货</li>
        <li>退货</li>
    </ul>
    <div id="content">
        <!-- 待付款-->
        <ul style="display:block;" id="content_ul">
        	<c:if test="${empty waitpay.dataList }">
				<div class="nottiao">没有订单记录</div>
			</c:if>
	        <c:forEach items="${waitpay.dataList }" var="o">
            <!-- 付款及关闭订单 -->
            <li>
                <!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
                <div class="proDetail orderD-common">
                    <c:forEach items="${o.orderSubs }" var="os">
                    	<div class="orderD-head">
                        <span class="proI-img" style="background-position: -428px -78%;"></span>
                        <span class=" ">${os.agentCompany.company_name }&nbsp;<a class="orderStatus">待付款</a></span><br>
                   		 </div>
                    	<c:forEach items="${os.orderDetail4subs }" var="o4s">
	                    <div class="expI-content orderD-com-cont">
	                        <span><a href="user_getCommodityDetail.do?cid=${o4s.commodityId}"><img class="pro-list-img" src="${o4s.commodityImage }"></a></span><br>
	                        <span class="pro-list-name">${o4s.prodName }&nbsp; ${o4s.commoditySpecificationStr }</span><br>
	                        <div class="price-num"> <span class="price">￥<fmt:formatNumber value="${o4s.prodUnitPrice }" pattern="#00.#"/></span><br>
	                            <span class="num">   &nbsp;&nbsp;&nbsp;   x${o4s.commodityNum }</span>
	                        </div>
	                        <span class="clear"></span><br>
	                        <span class="pro-list-weight">${o4s.commoditySpecificationStr }</span>
	                    </div>
                    	</c:forEach>
                    	<div class="clear"></div>
                    </c:forEach>
                    <div class="clear"></div>
                    <div class="total-pro">
                        <span>共有<a class="">${o.prodTotalNum }</a>件商品</span> &nbsp;
                        <span class="expPay">运费：￥<a class=""><fmt:formatNumber value="${o.expTotalPrice}" pattern="#00.#"/></a></span> &nbsp;
                        <span class="">实付：<a class="totalPay">￥<fmt:formatNumber value="${o.totalAmount}" pattern="#00.#"/></a></span>
                    </div>
                    <div class="orderPay-Close">
                        <input class="orderPay" type="button" value="付款" onclick="window.location.href='gotoOrderPayType.do?orderMainNo=${o.orderMainNo}'"/>
<!--                         <input class="close" type="button" value="关闭订单" onclick = "window.location.href=('closeOrder.html')"/> -->
                    </div>
                </div>
                <!-- end 反复运用代码 结束 -->
            </li>
		</c:forEach>
        </ul>
        <!-- 待发货 -->
        <ul  id="content_ul_paied">
            <c:if test="${empty paied.dataList }">
				<div class="nottiao">没有订单记录</div>
			</c:if>
	        <c:forEach items="${paied.dataList }" var="o">
            <!-- 付款及关闭订单 -->
            <li>
                <!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
                <div class="proDetail orderD-common">
                    <c:forEach items="${o.orderSubs }" var="os">
                    	<div class="orderD-head">
                        <span class="proI-img" style="background-position: -428px -78%;"></span>
                        <span class=" ">${os.agentCompany.company_name }&nbsp;<a class="orderStatus">待发货</a></span><br>
                   		 </div>
                    	<c:forEach items="${os.orderDetail4subs }" var="o4s">
	                    <div class="expI-content orderD-com-cont">
	                        <span><a href="user_getCommodityDetail.do?cid=${o4s.commodityId}"><img class="pro-list-img" src="${o4s.commodityImage }"></a></span><br>
	                        <span class="pro-list-name">${o4s.prodName }&nbsp; ${o4s.commoditySpecificationStr }</span><br>
	                        <div class="price-num"> <span class="price">￥<fmt:formatNumber value="${o4s.prodUnitPrice }" pattern="#00.#"/></span><br>
	                            <span class="num">   &nbsp;&nbsp;&nbsp;   x${o4s.commodityNum }</span>
	                        </div>
	                        <span class="clear"></span>
	                        <span class="pro-list-weight">${o4s.commoditySpecificationStr }</span>
	                    </div>
                    	</c:forEach>
                    	<div class="clear"></div>
                    </c:forEach>
                    <div class="clear"></div>
                    <div class="total-pro">
                        <span>共有<a class="">${o.prodTotalNum }</a>件商品</span> &nbsp;
                        <span class="expPay">运费：￥<a class=""><fmt:formatNumber value="${o.expTotalPrice}" pattern="#00.#"/></a></span> &nbsp;
                        <span class="">实付：<a class="totalPay">￥<fmt:formatNumber value="${o.totalAmount}" pattern="#00.#"/></a></span>
                    </div>
                    <div class="orderPay-Close">
<!--                         <input class="orderPay" type="button" value="付款" onclick="window.location.href='confirmOrder.html'"/> -->
<!--                         <input class="close" type="button" value="关闭订单" onclick = "window.location.href=('closeOrder.html')"/> -->
                    </div>
                </div>
                <!-- end 反复运用代码 结束 -->
            </li>
		</c:forEach>
        </ul>

        <!-- 待收货 -->
        <ul id="content_ul_waitshou">
            <c:if test="${empty waitshou.dataList }">
				<div class="nottiao">没有订单记录</div>
			</c:if>
	        <c:forEach items="${waitshou.dataList }" var="o">
            <!-- 付款及关闭订单 -->
            <li>
                <!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
                <div class="proDetail orderD-common">
                    <c:forEach items="${o.orderSubs }" var="os">
                    	<div class="orderD-head">
                        <span class="proI-img" style="background-position: -428px -78%;"></span>
                        <span class=" ">${os.agentCompany.company_name }&nbsp;<a class="orderStatus"><c:if test="${o.payStatus==17}">已完成</c:if>
                        <c:if test="${o.payStatus==16}">待收货</c:if></a></span><br>
                   		 </div>
                    	<c:forEach items="${os.orderDetail4subs }" var="o4s">
	                    <div class="expI-content orderD-com-cont">
	                        <span><a href="user_getCommodityDetail.do?cid=${o4s.commodityId}"><img class="pro-list-img" src="${o4s.commodityImage }"></a></span><br>
	                        <span class="pro-list-name">${o4s.prodName }&nbsp; ${o4s.commoditySpecificationStr }</span><br>
	                        <div class="price-num"> <span class="price">￥<fmt:formatNumber value="${o4s.prodUnitPrice }" pattern="#00.#"/></span><br>
	                            <span class="num">   &nbsp;&nbsp;&nbsp;   x${o4s.commodityNum }</span>
	                        </div>
	                        <span class="clear"></span>
	                        <span class="pro-list-weight">${o4s.commoditySpecificationStr }</span>
	                    </div>
                    	</c:forEach>
                    	<div class="clear"></div>
                    </c:forEach>
                    <div class="clear"></div>
                    <div class="total-pro">
                        <span>共有<a class="">${o.prodTotalNum }</a>件商品</span> &nbsp;
                        <span class="expPay">运费：￥<a class=""><fmt:formatNumber value="${o.expTotalPrice}" pattern="#00.#"/></a></span> &nbsp;
                        <span class="">实付：<a class="totalPay">￥<fmt:formatNumber value="${o.totalAmount}" pattern="#00.#"/></a></span>
                    </div>
                    <div class="orderPay-Close">
                    <c:if test="${o.payStatus==16}">
                        <input class="orderPay" type="button" value="收货" onclick="completeOrder('${o.orderMainNo}')"/>
                        <input class="close" type="button" value="退货" onclick = "deliveryRefuse('${o.orderMainNo}')"/>
                    </c:if>
                    </div>
                </div>
                <!-- end 反复运用代码 结束 -->
            </li>
		</c:forEach>
        </ul>

        <!-- 退货 -->
        <ul  id="content_ul_tuihou">
            <c:if test="${empty tuihou.dataList }">
				<div class="nottiao">没有订单记录</div>
			</c:if>
	        <c:forEach items="${tuihou.dataList }" var="o">
            <!-- 付款及关闭订单 -->
            <li>
                <!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
                <div class="proDetail orderD-common">
                    <c:forEach items="${o.orderSubs }" var="os">
                    	<div class="orderD-head">
                        <span class="proI-img" style="background-position: -428px -78%;"></span>
                        <span class=" ">${os.agentCompany.company_name }&nbsp;<a class="orderStatus">退货中</a></span><br>
                   		 </div>
                    	<c:forEach items="${os.orderDetail4subs }" var="o4s">
	                    <div class="expI-content orderD-com-cont">
	                        <span><a href="user_getCommodityDetail.do?cid=${o4s.commodityId}"><img class="pro-list-img" src="${o4s.commodityImage }"></a></span><br>
	                        <span class="pro-list-name">${o4s.prodName }&nbsp; ${o4s.commoditySpecificationStr }</span><br>
	                        <div class="price-num"> <span class="price">￥<fmt:formatNumber value="${o4s.prodUnitPrice }" pattern="#00.#"/></span><br>
	                            <span class="num">   &nbsp;&nbsp;&nbsp;   x${o4s.commodityNum }</span>
	                        </div>
	                        <span class="clear"></span>
	                        <span class="pro-list-weight">${o4s.commoditySpecificationStr }</span>
	                    </div>
                    	</c:forEach>
                    	<div class="clear"></div>
                    </c:forEach>
                    <div class="clear"></div>
                    <div class="total-pro">
                        <span>共有<a class="">${o.prodTotalNum }</a>件商品</span> &nbsp;
                        <span class="expPay">运费：￥<a class=""><fmt:formatNumber value="${o.expTotalPrice}" pattern="#00.#"/></a></span> &nbsp;
                        <span class="">实付：<a class="totalPay">￥<fmt:formatNumber value="${o.totalAmount}" pattern="#00.#"/></a></span>
                    </div>
                    <div class="orderPay-Close">
<!--                         <input class="orderPay" type="button" value="付款" onclick="window.location.href='confirmOrder.html'"/> -->
<!--                         <input class="close" type="button" value="关闭订单" onclick = "window.location.href=('closeOrder.html')"/> -->
                    </div>
                </div>
                <!-- end 反复运用代码 结束 -->
            </li>
		</c:forEach>
        </ul>
    </div>
</div>

<!-- 确定收货弹出窗口界面begin-->
<!-- 弹出时，背景层div-->
<div id="fade" class="black_overlay"></div>
<div id="MyDiv" class="white_content">
    <!-- <div style="text-align: right; cursor: default; height: 40px;">
         <span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')">关闭</span>
     </div>-->
    <div class="jumpText">
        <p class="index">请收到货后再确认收货，确认收货后货款将支付给卖家。</p><br>
        <div class="cancelConfirm">
            <div class="cancel"><a class="quexiao" onclick="CloseDiv('MyDiv','fade')">取消</a></div>
            <div class="confirm"><a class="queding" onclick="Confirm()">确定收货</a></div>
        </div>
    </div>
</div>
<!-- 确定收货弹出窗口界面end -->
</body>
 <script src="/js/userFunction.js"></script>
 <script src="/js/scrollpagination.js"></script>
<script src="/js/downpullpage_common.js"></script>
<script type="text/javascript">
//调用下拉分页
$(function(){
    page("content",'nextPage_orders.do?payStatus=7',function(d){
        $('#content_ul').append(d);
        getorder_nextPage("content_ul_paied",9);		//获取已支付
        getorder_nextPage("content_ul_waitshou",16);	//获取已发货
        getorder_nextPage("content_ul_tuihou",12);		//获取已退货
        
    });
});
</script>
</html>