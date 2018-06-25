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
    <title>商品首页</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
    <style>
        #tab li.current {
          border-bottom: 2px solid #238822;
          color: #238822;
        }
    </style>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">配饰</a>
    </p>
</div>
<div class="" id="outer" style="border-bottom: 2px solid #E6E6E6;margin-bottom: 15px;height:40px;">

    <ul id="tab">
        <li class="lastest current">上架</li>
        <li class="sortPrice">待审核</li>
    </ul>
    <div class="productLast" id="content">
        <!-- 最新产品上架列表项目 -->
        <ul style="display:block;" id="sale_ing_ul">
            <c:if test="${empty data_page }">
				<div class="nottiao">没有商品记录</div>
			</c:if>
	        <c:forEach items="${data_page.dataList }" var="o">
	        <li class="jade sort-prod-lastNew" id="Content-li_${o.id}">
	         <a href="getCommodityDetail.do?cid=${o.id}" >
                <c:if test="${not empty o.acquiescent_picture}" var="hava">
			     		<img src="${o.acquiescent_picture}"><br>
			     	</c:if>
			     	<c:if test="${!hava }">
			     		<img  src="/image/nophoto_120X120.jpg"/><br>
			     	</c:if>
                <%-- <span  >${o.commodity_name }
                </span><br><span class="proPrice">￥${o.defaultPrice } </span> --%>
                  <span class="zhubaoPrice">￥${o.defaultPrice } </span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
                    <span class="zhubaoState">${o.commodity_name }</span><br>
                    <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
                 </a>
            </li>
	        </c:forEach>
        </ul>
        <!-- 价格排序列表项目 -->
        <ul style="display:none;" id="under_ing_ul">
            <c:if test="${empty underlist_page }">
				<div class="nottiao">没有商品记录</div>
			</c:if>
	        <c:forEach items="${underlist_page.dataList }" var="o">
	        <li class="jade sort-prod-lastNew" id="Content-li_${o.id}">
	        <a href="getCommodityDetail.do?cid=${o.id}">
                <c:if test="${not empty o.acquiescent_picture}" var="hava">
			     		<img src="${o.acquiescent_picture}"><br>
			     	</c:if>
			     	<c:if test="${!hava }">
			     		<img  src="/image/nophoto_120X120.jpg"/><br>
			     	</c:if>
              		<span class="zhubaoPrice">￥${o.defaultPrice } </span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
                    <span class="zhubaoState">${o.commodity_name }</span><br>
                    <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
               </a>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        window.onload = function() {

            var $li = $('#tab li');     //获取#tab 下li元素并作为对象
            var $ul = $('#content ul');  //获取#content ul 作为变量

            $li.mouseover(function() {  // 鼠标悬停在 $li上时，触发匿名函数执行
                var $this = $(this);
                var $t = $this.index();
                // alert($t);
                $li.removeClass("current");
                $this.addClass("current");
                $ul.css('display','none');   //$ul设置css样式，即显示此样式css
                $ul.eq($t).css('display','block');
            })
        }
    });
</script>
<script src="/js/agentCompany.js"></script>
<script src="/js/scrollpagination.js"></script>
<script src="/js/downpullpage_common.js"></script>
<script type="text/javascript">
//调用下拉分页
$(function(){
    page("content",'agentprolist_nextPage.do?listing=0',function(d){
        $('#sale_ing_ul').append(d);
        getId_under_ing_ul_nextPage_list();
    });
});
</script>
</body>
</html>