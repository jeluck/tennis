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
    <title>商品详情</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
    <link rel="stylesheet" href="/css/personMyOrder.css"/>
    <link rel="stylesheet" href="/css/sale.css"/>
    <link rel="stylesheet" href="/css/prodDetails.css"/>
    <link rel="stylesheet" href="/css/index.css"/>
    <script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
<script>
        // 弹窗显示
       function ShowDiv(show_div,bg_div) {
           document.getElementById(show_div).style.display = 'block';
           document.getElementById(bg_div).style.display = 'block';
       }
        // 关闭弹窗
        function CloseDiv(show_div,bg_div) {
            document.getElementById(show_div).style.display = 'none';
            console.log("fail");
            document.getElementById(bg_div).style.display = 'none';
        }
      // 产品数量增减及剩余产品总数
        $(function() {

            var txt = $(".quantity");
            var initSurplus = parseInt($(".surplus").text());
           // alert(initSurplus);
            $(".quantity-decrease").click(function() {
                txt.val(parseInt(txt.val())-1);
                setTotal();
            })
            $(".quantity-increase").click(function() {
                txt.val(parseInt(txt.val())+ 1);
                setTotal();
            })
            function setTotal() {
                $(".surplus").html(parseInt(txt.val())+initSurplus).toFixed(2);
            }
        })
     // 规格选中样式
        $(function() {
            $(".redText-nowPay").click(function() {
                $(".redText-nowPay").removeClass("selected");
                $(this).addClass("selected");
            });
        });
     // 购物车数量红圈显示,当购物车数量为0,不显示“红圈+0”
        $(function(){

           var a_red = $(".cart-redRound-num");
           var a_redtext = $(a_red).text();
            if((a_redtext.toString()) == 0) {
                var a_redParents = $(a_red).parents(".cart-redRound-img");
                a_redParents.css("display","none");
            }
          //  alert(a_redtext);
        });

function gotomy(){
	try{
		window.pay.goTOMy();//跳到个人中心
	}catch (e) {
		touser();
	}
}    </script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">商品详情</a>
    </p>
</div>
<div class="spacediv"></div>
<div style="border-bottom: 10px solid #ebebeb;background: #fff;">
    <span >
    	<c:if test="${not empty o.acquiescent_picture}">
    		<img class="max-img" src="${o.acquiescent_picture}"/>
    	</c:if>
    </span><br>
<%--     <span class="pro-state"> 
    ${o.commodity_name}
    </span><br>
    <span class="redText" style="padding: 4%;">￥${o.defaultPrice}</span> --%>
    <div style="margin: 0 10px 10px;font-size: 16px;line-height: 25px;color: #7D7D7D;">
        <span class="zhubaoPrice">￥<fmt:formatNumber value="${o.defaultPrice}" pattern="#0.#"/></span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
        <span class="zhubaoState">${o.commodity_name}</span><br>
        <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
    </div>
</div>

<div style="background-color:#fff;">
    <span class="prodDetail">商品详情</span><br>
    <div style="border-top: 2px solid #ebebeb;">
         <span style="padding:4%;color: #7D7D7D;">${o.introduction}</span><br>
        <c:if test="${not empty o.acquiescent_picture}">
    		<img class="max-img" src="${o.acquiescent_picture}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_1}">
    		<img class="max-img" src="${o.picture_1}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_2}">
    		<img class="max-img" src="${o.picture_2}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_3}">
    		<img class="max-img" src="${o.picture_3}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_4}">
    		<img class="max-img" src="${o.picture_4}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_5}">
    		<img class="max-img" src="${o.picture_5}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_6}">
    		<img class="max-img" src="${o.picture_6}"/>
    	</c:if>
    	<c:if test="${not empty o.picture_7}">
    		<img class="max-img" src="${o.picture_7}"/>
    	</c:if>
    </div>

</div>
<div class="prodDetailFooter" >
   <ul>
       <li class="waitCom"  onclick="gotomy();touser();">
           <span class="myCount"></span>
<!--              红色圆圈带有数字 -->
<!--             <span class="redRound-img"><a class="redRound-num">2</a></span> -->
           <br>
           <a>我的账号</a>
       </li>
       <li class="waitCom" onclick="tocart();">
           <span class="myCart "></span>
<!--            红色圆圈带有数字 -->
           <span class="cart-redRound-img" id="countCart_span"><a class="cart-redRound-num" id="countCart">${count }</a></span>
           <input value="${count }" id="countNum" type="hidden">
           <br>
           <a>购物车</a>
       </li>
       <li class="waitCom" style="width: 28%;padding-left: 0%;">
           <input class="addCart" type="button" onclick="ShowDiv('nowPay','fade');addproduct2();" value="加入购物车"/>
       </li>
       <li class="waitCom" style="width:22%;padding-left: initial;">
           <input class="nowPay" type="button" onclick="ShowDiv('nowPay','fade');addproduct2();" value="立即购买"/>
       </li>
   </ul>
</div>
<div style="height:80px;"></div>
<!-- begin 立即购买弹窗界面 -->
<div id="fade" class="black_overlay"></div>
<div class="wrap" id="nowPay" style=" display:none; z-index:9999;">
    <div class="innerWrap" style="padding: 2px;padding-bottom: 15px;">
        <span>
	        <c:if test="${not empty o.acquiescent_picture}">
	    		<img class="pro-list-img" src="${o.acquiescent_picture}"/>
	    	</c:if>
        </span><br>
        <div class="innerWrap-text">
          <span>${o.commodity_name}<a class="close-model-pay" onclick="CloseDiv('nowPay','fade')"></a><br>
            <a class="redText">￥<fmt:formatNumber value="${o.defaultPrice}" pattern="#00.#"/></a>
          </span>
        </div>
        <div class="clear"></div>
    </div>

    <div class="innerWrap" style="margin-top: -15px;">
        规格<br>
        <c:if test="${not empty o.typeList }">
    	<c:forEach items="${o.typeList }" var="t" >
    		<span class="redText-nowPay selected"  xonclick="addproduct('${t.commodityTypeNo }');">${t.commodityType }
    		<input type="hidden" value="${t.commodityTypeNo }" name="commodityTypeNo" id="commodityTypeNo"/>
    		</span>&nbsp;
    	</c:forEach>	
   		</c:if>
    </div>
<!--     <div class="innerWrap"> -->
<!--         <span>数量</span> -->
<!--         <div class="quantity-wrapper"> -->
<!--             剩余<a class="surplus">80</a>件 -->
<!--             <span class="quantity-inner"> -->
<!--             <a  class="quantity-decrease disabled">-</a> -->
<!--             <input type="text" class="quantity" size="4" value="1" name="num" > -->
<!--             <a  class="quantity-increase">+</a> -->
<!--             </span> -->
<!--         </div> -->
<!--     </div> -->
    <div>
        <input class="CardButton" type="button" onclick="tocart();"  value="确定购买"/>
    </div>
</div>
<!-- end 立即购买弹窗界面 -->
</body>
 <script src="/js/userFunction.js"></script>
</html>