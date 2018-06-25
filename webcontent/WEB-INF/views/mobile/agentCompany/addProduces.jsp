<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<title>添加商品</title>
<link rel="stylesheet" href="../../css/c.css" />
<link rel="stylesheet" href="../../css/customer.css" />
<link rel="stylesheet" href="../../css/sale.css" />
<link rel="stylesheet" href="/css/addProduces.css" />
<link rel="stylesheet" href="../../css/jumpSort.css"/>
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
<script>
        $(function() {
            window.onload = function() {
                var $model = $("#Model");
                var $addmodel = $("#AddModel");
                var $closemodel = $("#CloseModel");
                $addmodel.click(function() {
                    $model.css('display','block');
                    // console.log("test");
                });
                $closemodel.click(function() {
                    $model.css('display','none');
                })
            }
        });
        function ShowDiv(show_div,bg_div){
            document.getElementById(show_div).style.display='block';
            document.getElementById(bg_div).style.display='block' ;
            var bgdiv = document.getElementById(bg_div);
            bgdiv.style.width = document.body.scrollWidth;
        };
         console.log("test");
        //关闭弹出层
        function CloseDiv(show_div,bg_div)
        {
            document.getElementById(show_div).style.display='none';
            document.getElementById(bg_div).style.display='none';
        };
        
        function checkdata(){
        	var commodity_name = $("#commodity_name").val();
        	if(commodity_name==''){
        		return false;
        	}
        	var introduction = $("#introduction").val();
        	if(introduction==''){
        		return false;
        	}
        	var categoryInfoid = $("#categoryInfoid").val();
        	if(categoryInfoid==''){
        		return false;
        	}
        	var price = $("#price").val();
        	if(price==''){
        		return false;
        	}
        	var express_fee = $("#express_fee").val();
        	if(express_fee==''){
        		return false;
        	}
        	return true;
        }
    </script>
</head>
<body>
	<div class="header">
		<p>
			<a class="back" href="javascript:history.go(-1)"></a> <a
				class="loginTitle">添加商品</a>
		</p>
	</div>
	<div class="spacediv"></div>
	<form class="Total-Produce" style="margin: 10px auto;"
		action="agent_addcommodity.do" method="post" onsubmit="return checkdata();">
		<!-- 商品图片 -->
		<div class="produce-images">
			商品图片<br>
	<div id='log'></div>
			<div>
				<img class="maxImages" id="img0" src="/image/nophoto_120X120.jpg" />
				<input type="hidden" name="acquiescent_picture"
					id="acquiescent_picture">
			</div>
			<a class="prod-add-img border-none" id="prod-add-img"><input
				type="file" name="imgFile" class="upload_file" /></a>
			<!--       <input class="prod-add-img border-none" type="button" value=""/> -->

		</div>
		<!-- 商品简述 -->
		<div>
			<div class="produce-sum" style="margin-bottom: 2px;">
				<textarea class="produce-title border-none" type="text"
					placeholder="给商品填写一个响亮的标题吧" name="commodity_name" id="commodity_name" maxlength="200" required></textarea>
			</div>
			<div class="produce-sum" style="margin: 2px auto; height: 60px;">
				<textarea class="produce-contents border-none" type="text"
					maxlength="30" placeholder="给商品填写一段描述吧" name="introduction" id="introduction" maxlength="2000" required></textarea>
			</div>

		</div>
		<!-- 商品型号 -->
		<div class="produce-num produce-sum">
			<div id="Model" xstyle="display:none;">
				<p class="model-border">  规格
	              <input type="number" class="height border-none" value="" placeholder="长" name="chang0"/>*
	              <input type="number" class="height border-none" value="" placeholder="宽" name="kuan0">*
	              <input type="number" class="height border-none" value="" placeholder="厚" name="hou0">
              </p>
              <p class="model-border"> 圈号 <input type="text" class="modelGray border-none" placeholder="填写商品圈号" name="quanhao0"/></p>
 
				<p class="model-border">库存 <input name="store0" type="text" class="modelGray border-none" placeholder="填写商品数量" value="1" style="width: 25%;"/>
                         <input type="radio" name="unit" value="手" />&nbsp;手&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="unit" value="件" />&nbsp;件

				</p>
				<p class="model-border"> 价格 <input name="price0" id="price" type="text" class="modelGray border-none" placeholder="填写商品价格" required /></p> 
				<!--           <div  id="CloseModel" class="close-model"></div> -->
			</div>
<!-- 			<div id="AddModel"> -->
<!-- 				<span class="model-block" onclick="addNewType();"></span><span>添加商品型号</span> -->
<!-- 			</div> -->

		</div>
		<!-- 商品类型、店铺类型 --><!--               <em class="textGray" onclick="ShowDiv('MyDiv','fade')">请选择商品所属类型</em> -->
		<div class="produce-sort produce-sum" style="margin: 2px auto;" onclick="ShowDiv('MyDiv','fade');getAllOnelevelCategory();">
			<a> <span>商品类型 
					<input class="textGray border-none" type="text"
					placeholder="请选择商品所属类型" id="categoryInfo" readonly="readonly"/> <input type="hidden"
					name="categoryInfo.id" id="categoryInfoid">
			</span> <span class="arrowRig-img" style="top: -12px;"> </span><br>
			</a>
		</div>
		<!-- 设置运费 -->
		<div class="setExpress-pay produce-sum">
			设置运费<input class="textGray border-none" type="number"
				name="express_fee" placeholder="如不输入默认包邮" name="express_fee" value="0" required/>

		</div>
		<!-- 设置佣金 -->
<!-- 		<div class="setPay produce-sum"> -->
<!-- 			设置佣金(<font color="red">最少</font>)<input class="textGray border-none" -->
<!-- 				type="number" placeholder="请设置佣金数额" name="commission_1" value="0"/> -->
<!-- 		</div> -->
<!-- 		<div class="setPay produce-sum"> -->
<!-- 			设置佣金(<font color="red">中间</font>)<input class="textGray border-none" -->
<!-- 				type="number" placeholder="请设置佣金数额" name="commission_2"  value="0"/> -->
<!-- 		</div> -->
<!-- 		<div class="setPay produce-sum"> -->
<!-- 			设置佣金(<font color="red">最多</font>)<input class="textGray border-none" -->
<!-- 				type="number" placeholder="请设置佣金数额" name="commission_3"  value="0"/> -->
<!-- 		</div> -->
		<div>
			<input class="CardButton" type="submit" value="保存" />
		</div>
	</form>

	<!-- 商品所属类型-弹窗界面 begin -->
	<div id="fade" class="black_overlay"></div>

	<div class="produceSort" id="MyDiv" style="display: none;">
		<div class="prodSortTitle">
			<span>选择商品所属类目<a class="redText">（必填）</a></span><br> <span
				class="close-img" onclick="CloseDiv('MyDiv','fade')"></span>
		</div>
		<div
			style="background-color: #fff; margin-top: -20px; padding-left: 1%; padding-right: 1%;" id="categorydiv">
			 <ul style="background-color:#fff;">
				<c:forEach items="${categorylist }" var="o" varStatus="v">
				<li class="sort-li" onclick="getTwocategoryByOneId('${o.id}');"> ${o.category_name} </li>
				</c:forEach>
        	</ul>
		</div>

	</div>
	<!-- 弹窗界面 end -->

</body>
<script src="/js/agentCompany.js"></script>
<script type="text/javascript">
///**/上传
var files;
var img_index=0;
$('input[type=file]').each(function(i,o){
	$('body').append('<div class="upfiles"><form class="imgform'+i+'" target="frameimg'+i+'" method="post" enctype="multipart/form-data" action="agent_upload_photo.do">'
			+'<input type="file" id="uploadfile" accept="image/jpe,image/jpg,image/jpeg,image/gif,image/png" name="'+o.name+'" onclick="uploadimg();"  class="inimg'+i+'" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;">'
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
             
             if(img_index==0){
             	$(o.parentNode.parentNode).find('img').eq(0).attr('src','/'+rs);//.height(198).width(198);
             	$("#acquiescent_picture").val('/'+rs);
             }else{
            	 var str = "<img class='maxImages' id='img"+img_index+"'";
            	 str +=" src='"+rs+"'/>";
            	 str +='<input type="hidden" name="picture_' ;
            	 str += img_index+'"';
            	 str +=' value="'+rs+'">';
             	$("#img"+(img_index-1)).after(str);
             	
             	var left = $("#uploadfile").css("left");
             	var i = left.indexOf("px");
             	left = left.substring(0,i);
             	var n_left = Number(left);
             	$("#uploadfile").css("left",(n_left+70)+"px");
             	$("#prod-add-img").css("left",(n_left+70)+"px");
             	
             	if(img_index==3) {
             		$("#uploadfile").css({"left":"4%","top":"155px"});
                 	$("#prod-add-img").css({"left":"4%","top":"224px"});
                 	$(".produce-images").css("height","190px");
                	// $(o.parentNode.parentNode).find('img').eq(0).attr('src','/'+rs);//.height(198).width(198);
                 	// $("#acquiescent_picture").val('/'+rs);
             	}
             	else if(img_index >= 4) {
             		$("#uploadfile").css("left",(n_left+75)+"px");
                 	$("#prod-add-img").css("left",(n_left+75)+"px");
             	}
             	
             	
             	if(img_index==7){
             		$("#uploadfile").css("display","none");
                 	$("#prod-add-img").css("display","none");
             	}
             }
             img_index+=1;
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

 function uploadimg(){
	 try{ window.hideTab.showcontacts(); }catch (e) {
	}
 }
 
 /***
  * 安卓端上传商品图片后,app调用JS,换商品图片
  	后期IOS批量上传图片(循环调用此方法),也用到此方法		edit by 2015-09-18
  */
var img_index_android=0;
function changeproductforandroid(url){
	  if(img_index_android==0){
		  $("#img0").attr('src','/'+url);
       	$("#acquiescent_picture").val('/'+url);
       }else{
		 var str = "<img class='maxImages' id='img"+img_index_android+"'";
		 	 str +=" src='"+url+"'/>";
		 	 str +='<input type="hidden" name="picture_' ;
		 	 str += img_index_android+'"';
		 	 str +=' value="'+url+'">';
		  	$("#img"+(img_index_android-1)).after(str);
		  	
		  	var left = $("#uploadfile").css("left");
		  	var i = left.indexOf("px");
		  	left = left.substring(0,i);
		  	var n_left = Number(left);
			$("#uploadfile").css("left",(n_left+70)+"px");
         	$("#prod-add-img").css("left",(n_left+70)+"px");
         	
         	if(img_index_android==3) {
         		$("#uploadfile").css({"left":"4%","top":"155px"});
             	$("#prod-add-img").css({"left":"4%","top":"224px"});
             	$(".produce-images").css("height","190px");
            	// $(o.parentNode.parentNode).find('img').eq(0).attr('src','/'+rs);//.height(198).width(198);
             	// $("#acquiescent_picture").val('/'+rs);
         	}
         	else if(img_index_android >= 4) {
         		$("#uploadfile").css("left",(n_left+75)+"px");
             	$("#prod-add-img").css("left",(n_left+75)+"px");
         	}
         	
         	if(img_index_android==7){
         		$("#uploadfile").css("display","none");
             	$("#prod-add-img").css("display","none");
         	}
		  	
//          	$("#uploadfile").css("left",(n_left+64)+"px");
// 		  	$("#prod-add-img").css("left",(n_left+64)+"px");
// 		if(img_index_android==7){
// 		 	$("#uploadfile").css("display","none");
// 		   	$("#prod-add-img").css("display","none");
// 		}
    }
	  img_index_android++;
}
</script>
<script src="/js/iosuploadimage.js"></script>
</html>
