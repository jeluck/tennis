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
    <title>分类</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <style>
        #content ul {
            display:none;
        }
       li.current {
            background: #FFB73F;
           color: #000000;
           border-right: 0 !important;
         /*  position: absolute;*/
         /*  top: 0;*/
        }
    </style>
    <script src="../../js/jquery.min.js"></script>
    <script>
      $(function(){
          window.onload = function()
          {
              var $li = $('#menu1 li');

              var $ul = $('#content ul');
             // alert($ul.length);
              $li.mouseover(function(){
                  var $this = $(this);
                  var $t = $this.index();
                  $li.removeClass();
                  $this.addClass('current');
                  $ul.css('display','none');
                  $ul.eq($t).css('display','block');
              })
          }
      });
    </script>
</head>
<body style="background-color: white;">
<div class="sort-header">
    <div></div>
    <p class="search">
        <span class="search-img"></span>
        <form action="searchcommodity.do" method="post">
            <input class="search-text" type="text" name="name_categoryname_seo" placeholder="请输入搜索商品"/>
            <input class="search-submit" type="submit" name="" value="搜索">
        </form>
    </p>
</div>
<div style="margin-top: 135px;"></div>
<div class="sort-viewport">
    <div class="yihezhai-tab ">
        <div class="menuLeft">
            <ul class="contentList" id="menu1">
            <c:forEach items="${categorylist}" var="o" varStatus="status">
            	<c:choose>
            		<c:when test="${status.index  == 0}">
            			<li m_index="${status.count}" class="left-selected-border current" >
            		</c:when>  
        			<c:otherwise>
        				<li m_index="${status.count}" class="" >
        			</c:otherwise>
            	</c:choose>
                
                   	<a href="javascript:void(0);">${o.category_name}</a>
                </li>
              </c:forEach>
            </ul>
        </div>

        <div class="sort-content contentRight" id="content">
        	<c:forEach items="${categorylist}" var="o" varStatus="status">
            <!-- ${status.count}${o.category_name} -->
            <c:choose>
        		<c:when test="${status.index  == 0}">
        			<ul class="first" style="display:block;">
        		</c:when>  
    			<c:otherwise>
    				<ul class="first" style="display:none;">
    			</c:otherwise>
        	</c:choose>
            
            	<c:forEach items="${o.categoryInfos}" var="c" varStatus="cstatus">
                <li class="first-01" >
                    <a href="tocategoryCommodity.do?categoryId=${c.id}">
<!--                         <span class="No01-img common-img"></span> -->
						<img src="/${c.imgurl}" style="height:50px;width:50px;"><br/>
                        <strong>${c.category_name}</strong>
                    </a>
                </li>
                </c:forEach>
            </ul>
           </c:forEach>
        </div>
    </div>
</div>
<!-- 易和斋首页底部固定悬浮菜单 -->
<div class="sort-footer  com-footer">
    <ul class="yihezhai-index">
        <li class="index-com-footer li-home">
            <a href="index.html">
                <span class="home-img com-footer-img"></span>
                <strong>首页</strong>
            </a>
        </li>
        <li class="current index-com-footer li-sort">
            <a href="">
                <span class="sort-img com-footer-img"></span>
                <strong>分类</strong>
            </a>
        </li>
        <li class="index-com-footer li-cart">
            <a href="shoppingCart.html">
                <span class="cart-img com-footer-img"></span>
                <strong>购物车</strong>
            </a>
        </li>
        <li class="index-com-footer li-myself">
            <a href="myself.html">
                <span class="myself-img com-footer-img"></span>
                <strong>我的</strong>
            </a>
        </li>
    </ul>

</div>
</body>
</html>