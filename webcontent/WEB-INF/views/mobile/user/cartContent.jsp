<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	                    <a  class="quantity-decrease disabled"  onclick="subproduct_minus('${o.product_no}')">-</a>
	                    <input type="text" class="quantity" size="4" value="${o.product_num}" name="num"  id="num">
	                    <a  class="quantity-increase"  onclick="addproductFor_plus('${o.product_no}')"  >+</a>
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
<%-- 	    <p style="margin-left: -15px;"><a class="gotoTotal">合计：</a><span class="gotoPrice" id="total_price">￥ ${price}<br><em>不含邮费</em></span></p> --%>
<!-- 	    <p><a class="gotoPay" id="submit" onclick="confirmOrder()" >去结算</a></p> -->
	    <span id="total_price">合计:￥ ${price} </span>
	    <a class="confirmOrder" id="submit" onclick="confirmOrder()">去结算</a>
	</div>
