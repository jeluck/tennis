<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>修改商品</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
    <link rel="stylesheet" href="/css/addProduces.css"/>
    <link rel="stylesheet" href="../../css/jumpSort.css"/>
    <script src="/js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
    <script>
    var img_index=0;	//图片的下标
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
    </script>
        <style type="text/css">
    img.maxImages {
            width: 70px;
            height:70px;
            margin: 4px;
            position: relative;
        }
        .prod-add-img {
            background-position: -18px -41%;
            /* width: 85px; */
            /* height: 82px; */
            border: none;
            margin-left: -2px;
            margin: -87px auto;
            float: right;
            position: absolute;
            left: 28%;
            width: 22%;
        }
    </style>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">修改商品</a>
    </p>
</div>
<div class="spacediv"></div>
<form class="Total-Produce" style="margin: 10px auto;" action="agent_editcommodity.do" method="post">
    <!-- 商品图片 -->
    <div class="produce-images">
       商品图片<br><div id='log'></div>
      <div>
      	<c:if test="${not empty commodity.acquiescent_picture}" var="hava">
     		<img class="maxImages" style="margin:8px;" id="img0" src="${commodity.acquiescent_picture}"/>
          	<input type="hidden" name="acquiescent_picture" id="acquiescent_picture" value="${commodity.acquiescent_picture}">
          	<script>img_index++;</script>
     	</c:if>
     	<c:if test="${!hava }">
     		<img class="maxImages" style="margin:8px;"  id="img0"  src="/image/nophoto_120X120.jpg" />
     		<input type="hidden" name="acquiescent_picture" id="acquiescent_picture">
     	</c:if>
		<c:if test="${not empty commodity.picture_1}" var="hava">
     		<img class="maxImages" id="img1" style="margin:8px;" src="${commodity.picture_1}"/>
          	<input type="hidden" name="picture_1" id="picture_1" value="${commodity.picture_1}">
          	<script>img_index++;</script>
     	</c:if>
		<c:if test="${not empty commodity.picture_2}" var="hava">
     		<img class="maxImages" id="img2" style="margin:8px;" src="${commodity.picture_2}"/>
          	<input type="hidden" name="picture_2" id="picture_2" value="${commodity.picture_2}">
          	<script>img_index++;</script>
     	</c:if>
		<c:if test="${not empty commodity.picture_3}" var="hava">
     		<img class="maxImages" id="img3" style="margin:8px;" src="${commodity.picture_3}"/>
          	<input type="hidden" name="picture_3" id="picture_3" value="${commodity.picture_3}">
          	<script>img_index++;</script>
     	</c:if>

      </div>
     <a class="prod-add-img border-none" id="prod-add-img"><input type="file" name="imgFile" class="upload_file" /></a>
<!--       <input class="prod-add-img border-none" type="button" value=""/> -->

    </div>
    <!-- 商品简述 -->
    <div>
        <div class="produce-sum" style="margin-bottom: 2px;"> 
        	<textarea class="produce-title border-none" type="text"  placeholder="给商品填写一个响亮的标题吧" name="commodity_name" value="${commodity.commodity_name}" /></textarea>
        	<input type="hidden" value="${commodity.id}" name="id" />
        </div>
       <div  class="produce-sum" style="margin: 2px auto;height: 60px;"> 
       	<textarea class="produce-contents border-none" type="text" maxlength="200" placeholder="给商品填写一段描述吧" name="introduction" value="${commodity.introduction}"></textarea>
       </div>

    </div>
    <!-- 商品型号 -->
    <div class="produce-num produce-sum">
    	<c:forEach items="${commoditytypelist }" var="commoditytype" varStatus="v">
		<c:if test="${v.count==1 }"><div id="Model" xstyle="display:none;"></c:if>
		<c:if test="${ v.count>1}"><div id="div${0 - v.count}" class="adddivModel"></c:if>
          <input type="hidden" name="id${0 - v.count}" id="id${0 - v.count}" value="${commoditytype.id}" />
      		 <p class="model-border">  规格
	              <input type="number" class="height border-none" value="${commoditytype.chang}" placeholder="长" name="chang${0 - v.count}"/>*
	              <input type="number" class="height border-none" value="${commoditytype.kuan}" placeholder="宽" name="kuan${0 - v.count}">*
	              <input type="number" class="height border-none" value="${commoditytype.hou}" placeholder="厚" name="hou${0 - v.count}">
              </p>
              <p class="model-border"> 圈号 <input type="text" class="modelGray border-none" placeholder="填写商品圈号" value="${commoditytype.quanhao}" name="quanhao${0 - v.count}"/></p>
 
				<p class="model-border">库存 <input name="store${0 - v.count}" id="store${0 - v.count}" type="text" class="modelGray border-none" placeholder="填写商品数量" value="${commoditytype.commodityStore }" style="width: 25%;"/>
<!-- 					<select class="modelGray border-none"  name="unit"> -->
<%-- 		                  <option value="手" <c:if test="${commodity.unit == '手' }">selected</c:if> >手</option> --%>
<%-- 		                  <option value="件" <c:if test="${commodity.unit == '件' }">selected</c:if> >件</option> --%>
<!-- 		              </select> -->
		              <input type="radio" name="unit" value="手" <c:if test="${commodity.unit == '手' }">checked</c:if> />&nbsp;手&nbsp;&nbsp;&nbsp;
                      <input type="radio" name="unit" value="件" <c:if test="${commodity.unit == '件' }">checked</c:if>/>&nbsp;件
				</p>
				<p class="model-border"> 价格 <input name="price${0 - v.count}" id="price${0 - v.count}"  type="text" class="modelGray border-none" placeholder="填写商品价格" required value="${commoditytype.commodityPrice }"/></p> 
        </div>

		</c:forEach>
<!--         <div id="AddModel"> -->
<!--             <span class="model-block" onclick="addNewType();"></span><span>添加商品型号</span> -->
<!--         </div> -->

    </div>
    <!-- 商品类型、店铺类型 -->
    <div class="produce-sort produce-sum" style="margin: 2px auto;">
          <a>
              <span onclick="ShowDiv('MyDiv','fade');getAllOnelevelCategory();">商品类型
						<input class="textGray border-none" type="text" placeholder="请选择商品所属类型"  id="categoryInfo" value="${commodity.categoryInfo.category_name}"/>
              			<input type="hidden" name="categoryInfo.id" id="categoryInfoid" value="${commodity.categoryInfo.id}">
              </span>
              <span class="arrowRig-img" style="top: -12px;">
              </span><br>
          </a>
    </div>
    <!-- 设置运费 -->
    <div class="setExpress-pay produce-sum">
        设置运费<input class="textGray border-none" type="number" name="express_fee" placeholder="如不输入默认包邮" name="express_fee" value="${commodity.express_fee}"/>

    </div>
    <div>
        <input class="CardButton" type="submit" value="保存"/>
    </div>
</form>

<!-- 商品所属类型-弹窗界面 begin -->
<div id="fade" class="black_overlay"></div>

<div class="produceSort" id="MyDiv" style="display: none;">
    <div class="prodSortTitle">
        <span>选择商品所属类目<a class="redText">（必填）</a></span><br>
        <span class="close-img" onclick="CloseDiv('MyDiv','fade')"></span>
    </div>
    <div style="background-color: #fff;margin-top: -20px;padding-left:1%;padding-right:1%;" id="categorydiv">
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
$('input[type=file]').each(function(i,o){
	$('body').append('<div class="upfiles"><form class="imgform'+i+'" target="frameimg'+i+'" method="post" enctype="multipart/form-data" action="agent_upload_photo.do">'
			+'<input type="file" id="uploadfile" accept="image/jpe,image/jpg,image/jpeg,image/gif,image/png" name="'+o.name+'" class="inimg'+i+'" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;">'
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
             	$("#uploadfile").css("left",(n_left+64)+"px");
             	$("#prod-add-img").css("left",(n_left+64)+"px");
             	if(img_index==3){
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
    function filecss(){
    	var leftcss = $("#uploadfile").css("left");
     	var i = leftcss.indexOf("px");
     	leftcss = leftcss.substring(0,i);
     	var n_left = Number(leftcss);
     	$("#uploadfile").css("left",(n_left+64)+"px");
     	$("#prod-add-img").css("left",(n_left+64)+"px");
     	if(img_index==4){
     		$("#uploadfile").css("display","none");
         	$("#prod-add-img").css("display","none");
     	}
    }
    for(var i=0;i<(img_index-1);i++){
    	filecss();
    }
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
   		  	$("#uploadfile").css("left",(n_left+64)+"px");
   		  	$("#prod-add-img").css("left",(n_left+64)+"px");
   		if(img_index_android==7){
   		 	$("#uploadfile").css("display","none");
   		   	$("#prod-add-img").css("display","none");
   		}
       }
   	  img_index_android++;
   }
   </script>
   <script src="/js/iosuploadimage.js"></script>
</html>
