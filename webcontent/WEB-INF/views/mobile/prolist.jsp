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
    <title>
    <c:if test="${not empty category.category_name }" var="hava">
    	${category.category_name }
    </c:if>
    <c:if test="${!hava}">
    	搜索
    </c:if>
    </title>
    <link rel="stylesheet" href="./css/c.css"/>
    <link rel="stylesheet" href="./css/customer.css"/>
    <link rel="stylesheet" href="./css/scrollpagination.css"/>
    <link rel="stylesheet" href="/css/index.css"/>
    <style>
        #tab li.current {
          border-bottom: 2px solid #238822;
          color: #238822;
        }
    </style>
    <script src="/js/jquery-1.8.1.min.js"></script>
</head>
<body>
<div class="header">
    <p>
        <a class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">${category.category_name }</a>
    </p>
</div>
<div id="outer">
	<div id="ot">
		<ul id="tab">
	        <li class="lastest sortSelected <c:if test="${orderByparam == 'create_time'}">current</c:if>"  onclick="searchcommodity('create_time')">最新上架
	            <span class="sortDateGray-img"></span>
	        </li>
	        <li class="sortPrice sortSelected <c:if test="${orderByparam == 'defaultPrice'}">current</c:if>" onclick="searchcommodity('defaultPrice')">价格排序
	            <span class="sortPriceGray-img"></span>
	        </li>
	    </ul>
	    <input type="hidden" value="${sort}" name="sort" id="sort" />
	     <input type="hidden" value="" name="orderByparam" id="orderByparam" />
	     <input type="hidden" value="${categoryId}" name="categoryId" id="categoryId" />
	</div>
    <div class="cbox" id="content">
        <!-- 最新产品上架列表项目 -->
        <ul style="display:block;" id="content_ul" class="cli">
            <c:if test="${empty commoditys }">
				<div class="nottiao">没有商品记录</div>
			</c:if>
	        <c:forEach items="${commoditys }" var="o">
	        <li class="jade sort-prod-lastNew" id="Content-li_${o.id}">
	         <a href="user_getCommodityDetail.do?cid=${o.id}" >
	         <c:if test="${o.commodityStore==0 }">
            	<img  class="hadSaled" src="/image/yisale.png" style="width:45%;"/>
            </c:if>
                <c:if test="${not empty o.acquiescent_picture}" var="hava">
			     		<img src="${o.acquiescent_picture}"><br>
			     	</c:if>
			     	<c:if test="${!hava }">
			     		<img  src="/image/nophoto_120X120.jpg"/><br>
			     	</c:if>
             <%--    <span class="texthidden"  >${o.commodity_name }
                </span><br><span class="proPrice">￥${o.defaultPrice } </span> --%>
                <span class="zhubaoPrice">￥<fmt:formatNumber value="${o.defaultPrice}" pattern="#0.#"/></span><span><a class="redtext">日期:${o.date_mm_dd}</a></span><br>
                <span class="zhubaoState">${o.commodity_name }</span><br>
                <span class="zhubaoPrice">编号：<a>${o.companyinfo_name}</a></span>
             </a>
            </li>
	        </c:forEach>
        </ul>
    </div>
</div>
<script src="/js/scrollpagination.js" ></script>
<script src="/js/downpullpage_common.js" id="aaa"></script>
<script type="text/javascript">
//最新上架、价格排序
/* $(function(){
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
}); */
//日期和价格排序效果更换提示背景图片
$(function() {
    var sortdate = $(".sortDateGray-img");
    var sortprice = $(".sortPriceGray-img");
   sortdate.click(function() {
        $(this).toggleClass("sortDateGreen-img");
   })
   sortprice.click(function() {
       $(this).toggleClass("sortPriceGreen-img");
   })
})
//更换当前选中栏
$(function() {
    var currentselected = $(".sortSelected");
     currentselected.click(function() {
         currentselected.removeClass("current");
         $(this).addClass("current");
     })
});

//点击排序,调用的方法
function searchcommodity(orderByparam){
	var sort = $("#sort").val();
	if(sort=="2"){
		$("#sort").val(1);
	}else{
		$("#sort").val(2);
	}
	var categoryId = $("#categoryId").val();
	 $("#orderByparam").val(orderByparam);
	$.ajax({
		type:"POST",
		url:'nextPage.do?pageNumber=1&orderByparam='+orderByparam+'&sort='+sort+'&categoryId='+categoryId,
		dataType:"html",
		success:function(data){
			if($.trim(data) != ''){
				$('#content_ul').html(data);
			}
		}
	});
}

//调用下拉分页
$(function(){
	var sort = $("#sort").val();
	var categoryId = $("#categoryId").val();
	page("content",'nextPage.do?sort='+sort+'&categoryId='+categoryId,function(d){
        $('#content_ul').append(d);
    });
});

function aaa(){
	   var oHead = document.getElementsByTagName('HEAD').item(0); 
	    var oScript= document.createElement("script"); 
	    oScript.type = "text/javascript"; 
	    oScript.src="/js/downpullpage_common.js"; 
	    oHead.appendChild( oScript); 
//  $("#aaa").src="/js/downpullpage_commo3n.js";
}
</script>
</body>
</html>