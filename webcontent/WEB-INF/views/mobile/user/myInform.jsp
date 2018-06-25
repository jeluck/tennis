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
    <title>个人资料</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="/css/sale.css"/>
    <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>

</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">个人资料</a>
    </p>
</div>
<div class="inform photo">
<input type="file" name="imgFile" style="position: absolute; top: 24px; left: 20px; opacity: 0; height: 72px; width: 164px;" />
    <div class="choice-wechat01">
        <span class="inform-img00"></span>
        <span class="inform-text" >修改头像</span>
        <a > <span class="arrowRig-img">
        	<c:if test="${not empty w.head_photo}" var="hava">
     		<img id="headerid" class="photo-img" src="${w.head_photo }" >
	     	</c:if>
	     	<c:if test="${!hava }">
	     		<img id="headerid" class="photo-img" src="/image/nophoto_120X120.jpg" />
	     	</c:if>
        </span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01" onclick="toeditname('${w.username }')" id="myname">
        <span class="inform-img01"></span>
        <span class="inform-text"><a >我的昵称</a></span>
        <a > <span class="arrowRig-text" id="editname"  style="width:auto;">
        ${w.username }
        </span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img02"></span>
        <a href="changePasswd.do">
         <span class="inform-text">修改登录密码</span>
            <span class="arrowRig-text"></span>
        </a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img03"></span>
        <span class="inform-text"><a href="bindPhone.html">手机</a></span>
        <a href="#"> <span class="arrowRig-text" style="width:auto;">${w.uphone }</span></a>
    </div>
</div>
<div class="inform">
    <div class="choice-wechat01">
        <span class="inform-img01"></span>
        <span class="inform-text"><a >我的邀请ID码</a></span>
        <a > <span class="arrowRig-text" style="width:auto;">
        ${w.invite_code }
        </span></a>
    </div>
</div>
<!-- <div class="inform"> -->
<!--     <div class="choice-wechat01"> -->
<!--         <span class="inform-img04"></span> -->
<!--         <span class="inform-text"><a href="bindWeixin.html">绑定微信</a></span> -->
<!--         <a href="bindWeixin.html"> <span class="arrowRig-text">钟高提</span></a> -->
<!--     </div> -->
<!-- </div> -->
<!--    <span class="zfbPocket-img07">支付宝钱包支付</span><br>
    <span class="zfbPC-img08">支付宝网页支付</span>-->
</body>
<script type="text/javascript">
///**/上传
var files;
$('input[type=file]').each(function(i,o){
	$('body').append('<div class="upfiles"><form class="imgform'+i+'" target="frameimg'+i+'" method="post" enctype="multipart/form-data" action="user_upload_head.do">'
			+'<input type="file" accept="image/jpe,image/jpg,image/jpeg,image/gif,image/png" name="'+o.name+'" onclick="uploadimg();" class="inimg'+i+'" style="position: absolute; top: 37px; left: 130px; opacity: 0; height: 64px; width: 64px;">'
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
 <script type="text/javascript">

 function uploadimg(){
	 try{ window.hideTab.showcontacts(); }catch (e) {}
 }
/***
 * 安卓端上传图片后,app调用JS,换头像
 */
 function changeheaderforandroid(url){
	 $("#headerid").attr('src','/'+url);
	 //var t2 = window.setTimeout("location.href='myInform.do'",2000);//使用字符串执行方法
	 location.reload();
 }
 </script>
 <script src="/js/userFunction.js"></script>
</html>