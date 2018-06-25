<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="-1">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <title>购物车</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/personMyOrder.css"/>
    <script src="../../js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
    <script>
        // 产品数量购买
        $(function() {
            $t = $(".quantity");
            $p = $(".price");
            $pval = $p.val();
            console.log("ok");
            $(".quantity-decrease").click(function(){
                t.val(parseInt(t.val()) - 1);
                alert(t.val);
               /* t.val(parseInt(t.val()) + 1);      //变量t的值增1
                setTotal();*/
            })
        })
    </script>
</head>
<style>
    .wrap {
        position: fixed;
        bottom: 0;
        width: 100%;
        background-color: #fff;
        padding-bottom: 15px;
    }
    .innerWrap {
        margin: 10px 4%;
        border-bottom: 1px solid #ebebeb;
        padding: 10px;
        /*     width:100%;*/

    }
    .redText-nowPay {
        color: #EB5153;
        font-size: 14px;
        border: 1px solid #FF3B21;
        border-radius: 5px;
        padding: 5px 15px;
        /*    vertical-align: text-bottom;*/
    }
    .innerWrap-text {
        position: relative;
        left: 10px;
        top: -25px;
    }
    .quantity-wrapper {
        float: right;
        position: relative;
        top: -62px;
        margin-bottom: -58px;
        width: 34%;
	    text-align: center;
   /*     width: 145px;*/
    }
    span.quantity-inner {
	    border: 1px solid #BABABA;
	    margin-left: 10px;
		border-radius: 3px;
		display: inline-block;
		width: 83%;
		vertical-align: -webkit-baseline-middle;
    }
    .quantity {
        border-style: none;
        border-left: 1px solid #BABABA;
        text-align: center;
        border-right: 1px solid #BABABA;
        padding: 5px 0;
        -webkit-appearance: none;
    }
    .close-model-pay {
        background: url("../../images/total.png");
        background-size: 660px;
        background-position: -375px -107%;
        height: 38px;
        display: block;
        width: 36px;
        position: absolute;
        right: 10px;
        top: -20px;
    }
    .quantity-decrease {
        padding-right: 8%;
    }
    .quantity-increase {
       padding-left: 2%;
    }
    .buyerInform, .orderD-common {
        padding: 10px;
        line-height: 25px;
        background-color: white;
    }

</style>
<body>
<div>
    <div class="header">
        <p>
            <a  class="back"  href="javascript:history.go(-1)"></a>
            <a class="loginTitle">购物车</a>
        </p>
    </div>
</div>
<div class="allSelected">
<!--     <span> -->
<!--     <input type="checkbox" class="checkbox-all" name="checkbox"/>全选<a class="delete-produce">删除商品</a> -->
<!--     </span> -->
</div>


<div id="cartcontent">
	<c:if test="${empty carts}">
		<div class="nottiao">没有商品记录</div>
		<a class="toindex" href="toindex.do">返回首页</a>
	</c:if>
	<c:forEach items="${carts}" var="o">
	<div style="color: #6B6B6B;">
	    <!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
	    <div class="proDetail orderD-common">
<!-- 	         <input type="checkbox" class="checkbox" name="checkbox"/> -->
	         <div class="shopping-content orderD-com-cont">
	            <span><img class="pro-list-img" src="${o.commodiy_image}"></span><br>
	            <span class="shopping-list-name">${o.commodityname}</span><br>
	            <span class="clear"></span><br>
	           
	            <div class="shopping-price-num">
	                <!--<span class="price">￥${o.product_price}</span>-->
	                <div class="quantity-wrapper">
	                    <a class="shopping-price">￥<fmt:formatNumber value="${o.product_price*o.product_num}" pattern="#00.#"/></a><br>
	                    <span class="quantity-inner">
	                    <a  class="quantity-decrease disabled"   onclick="subproduct_minus('${o.product_no}')">-</a>
	                    <input type="text" class="quantity" size="4"  value="${o.product_num}" name="num" id="num" >
	                    <a  class="quantity-increase" onclick="addproductFor_plus('${o.product_no}')">+</a>
	                    </span>
	                </div>
	            </div>
	         </div>
	        <div class="clear"></div>
	    </div>
	    <!-- end 反复运用代码 结束 -->
	</div>
	</c:forEach>
		<div class="submitOrder">
<%-- 			    <p style="margin-left: -15px;"><a class="gotoTotal">合计：</a><span class="gotoPrice" id="total_price">￥ ${price}<br><em>不含邮费</em></span></p> --%>
<!-- 	    <p><a class="gotoPay" id="submit" onclick="confirmOrder()" >去结算（<span id="checkNum">0</span>)</a></p> -->
	    <span id="total_price">合计:￥ ${price} </span>
	    <a class="confirmOrder" id="submit" onclick="confirmOrder()">去结算</a>
	</div>
</div>
</body>
 <script src="/js/userFunction.js"></script>
</html>