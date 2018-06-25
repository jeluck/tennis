<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <title>账户</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
        <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>
    <style type="text/css">
       span.shopping-img {
            background: url("../../images/total.png");
            /* background-color: #EC5355; */
            background-size: 490px;
            /* background-position: -56px 12%; */
            height: 30px;
            width: 29px;
            /* margin-left: 2%; */
            display: block;
            margin-bottom: -7px;
            margin-top: 4px;
        }
       span.shopping-img {
           background-position: -435px 35%;
           background-size: 600px;
           height: 30px;
           width: 37px;
       }
        li.index-com-footer {
            list-style: none;
            float: left;
            margin: 0px auto;
            height: 59px;
            display: inline-table;
            margin-left: 10px;
            margin-right: 10px;
            width:48px;
        }
        span.sort-img {
            background-position: -18px 35%;
            background-size: 530px;
            height: 30px;
            width: 34px;
        }
        span.cart-img {
            background-position: -364px 35%;
            background-size: 600px;
            height: 30px;
            width: 37px;
        }
    </style>
</head>
<body>
<div class="user">
    <span >
<input type="file" name="imgFile" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;" />

    	<c:if test="${not empty agent.header }" var="hava">
     		<img class="head-img" src="${agent.header}">
     	</c:if>
     	<c:if test="${!hava }">
     		<img class="head-img" src="/image/nophoto_120X120.jpg" />
     	</c:if>
    </span><br>
    <span><a class="userName">${agent.agentCompany.company_name}</a><br>
<!--     <a class="userFrom">微信用户</a> -->
    </span>
</div>
<div class="mySet myPocket">
    <div class="choice-wechat01">
     <!--   <span class="Count-img"></span>-->
        <span class="Count-text" style="margin-top: 15px;line-height: 40px;margin-left: 0%;">我的钱包（元）<a href="countDetali.html" class="arrowRight">&nbsp;资金管理/明细&nbsp;></a><br><a class="nowPocket">1000</a></span><br>

    </div>
</div>
<div class="mySet">
    <div class="choice-wechat01">
        <span class="Count-img01"></span>
        <span class="Count-text"><a href="tomyProducts.do">商品管理</a></span>
        <a href="tomyProducts.do"> <span class="arrowRig-img"></span></a>
    </div>
</div>
<div class="mySet">
    <div class="choice-wechat01">
        <span class="Count-img02"></span>
        <span class="Count-text"><a href='toset.do'>设置</a></span>
        <a href="toset.do"> <span class="arrowRig-img"></span></a>
    </div>
</div>
<div class="mySet" style="height: 100px;">
 </div>
<!--<div class="mySet" style="height: 100px;">
</div>-->
<!-- 易和斋首页底部固定悬浮菜单 -->
<div class="sort-footer  com-footer">
    <ul class="yihezhai-index">
        <li class="index-com-footer li-home">
            <a href="index.html">
                <span class="home-img com-footer-img"></span>
                <strong>首页</strong>
            </a>
        </li>
        <li class=" index-com-footer li-sort">
            <a href="">
                <span class="sort-img com-footer-img"></span>
                <strong>特卖</strong>
            </a>
        </li>
        <li class="index-com-footer li-cart">
            <a href="shoppingCart.html">
                <span class="cart-img com-footer-img"></span>
                <strong>逛逛</strong>
            </a>
        </li>
        <li class="index-com-footer li-cart">
            <a href="shoppingCart.html">
                <span class="shopping-img com-footer-img"></span>
                <strong>微店</strong>
            </a>
        </li>
        <li class=" current index-com-footer li-myself">
            <a href="myself.html">
                <span class="myself-img com-footer-img"></span>
                <strong>我的</strong>
            </a>
        </li>
    </ul>

</div>
<!-- <form class="imgform0" action="agent_upload_head.do" enctype="multipart/form-data" method="post" target="frameimg0"> -->
<!-- <input class="inimg inimg0" type="file" name="imgFile" accept="image/jpe,image/jpg,image/jpeg,image/gif,image/png" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;"> -->
<!-- </form> -->
<script type="text/javascript">
///**/上传
var files;
$('input[type=file]').each(function(i,o){
	$('body').append('<div class="upfiles"><form class="imgform'+i+'" target="frameimg'+i+'" method="post" enctype="multipart/form-data" action="agent_upload_head.do">'
			+'<input type="file" accept="image/jpe,image/jpg,image/jpeg,image/gif,image/png" name="'+o.name+'" class="inimg'+i+'" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;">'
			+'<input type="hidden" value="${uid}" name="uid">'
			+'<input type="hidden" value="'+o.name+'" name="imgName">'
			+'<input type="hidden" value="${loanapply.id}" name="applyId">'
			+'</form><iframe name="frameimg'+i+'" style="display:none;"></iframe></div>');
	 $('.inimg'+i).css({'position':'absolute','top':$(o).offset().top,
		 	'left':$(o).offset().left})
	 .change(function(e){
         if(this.value===''||files===e.target.files[0])return;
         var imgform=$('.imgform'+i)[0];
         var fr=frames['frameimg'+i];
         $('[name=frameimg'+i+']')[0].onload=function(rs){
             rs=fr.document.body.innerHTML;
             $(o.parentNode.parentNode).find('img').eq(0).attr('src','/'+rs);//.height(198).width(198);
         };
         files=e.target.files[0];
         imgform.submit();
     });
     $('.uimg').click(function(){
         $('.inimg'+i).click();$('.inimg'+i).change();
     });
     $(o).css('visibility','hidden');
});
//上传/**/

</script>
</body>
</html>