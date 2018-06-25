<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="lz-html" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>邀请注册-成功</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <link rel="stylesheet" type="text/css" href="/css/invite.css">
    <script src="/js/invite.js"></script>
    <script src="/js/jquery-1.8.1.min.js"></script>
    
      <!-- 弹层插件 start -->
  <link rel="stylesheet" href="/js/plugin/cloud.min.css">
  <script type="text/javascript" src="/js/plugin/cloud.min.js"></script>
  <!-- 弹层插件 end -->
</head>

<body class="lz-body" style="max-width: 640px; margin:0 auto;">
    <div style="position: relative;">
        <img src="/images/www.grandale.com.png" width="100%" alt="">
        <div class="invite-absolute">
            <div class="flex flex-main-center flex-cross-center" style="height: 100%;">
                <div class="flex-box-0 invite-main">
                    <div class="invite-info">你的好友${o.username }（${phone }）邀请你加入U橙</div>
                    <div class="flex invite-reg-type">
                        <div class="flex-box-1 on" onclick="selectUserType(this,1);">我是用户</div>
                        <div class="flex-box-1" onclick="selectUserType(this,2);">我是教练</div>
                    </div>
                    <div class="invite-type-box">
                        <input class="invite-type-text" id="uphone" type="text" placeholder="请输入手机号"  maxlength="11">
                    </div>
                    <div class="invite-type-box flex">
                        <div class="flex-box-1">
                            <input class="invite-type-text" id="phone_code" type="text" placeholder="请输入验证码">
                        </div>
                        <div class="flex-box-0">
                            <div class="invite-send-code" onclick="settime(this)" >发送验证码</div>
                        </div>
                    </div>
                    <div class="invite-type-box">
                        <input class="invite-type-text" id="nickname" type="text" placeholder="请输入昵称" maxlength="10">
                    </div>
                    <div class="invite-type-box">
                        <input class="invite-type-text" id="password" type="password" placeholder="请输入密码">
                    </div>
                    <div class="flex invite-reg-treaty">
                        <div class="flex-box-0 invite-reg-true" id="htp" onclick="selectTreaty(this);"></div>
                        <div class="flex-box-1"><a target="_back" style="color: #fff;" href="about_user.do?type=2">同意《弘金地用户服务协议》</a></div>
                    </div>
                    <input type="hidden" value="${o.id }" id="userId">
                    <input type="hidden" value="1" id="type">
                    <div>
                        <div class="invite-type-btn" onclick="reg()" >注册</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
<script type="text/javascript">
/**
 * 60秒倒计时
 */

function settime(element) {
	var phone = document.getElementById('uphone').value;
	if(phone == null || phone == undefined || phone == ""){
		cloud.explain("请输入手机号");
		return;
	}
	var reg = /^[1][3587][0-9]{9}$/;
	if(!reg.test(phone)){
		cloud.explain("手机号码格式不正确");
	    return;
	}
	if (element.timer) {
		return;
	}
	var type =  document.getElementById('type').value;
	if(type==1){
		$.ajax({ 
				url: "send_sms_for_register.do?phone="+phone+"", 
				success: function(val){
	      	 		if(val.status == 9){
	      	 			location.href="invitation_alreadyReg.do";
	      	 			return;
	      	 		}
	      		}
			  });
	}else{
		$.ajax({ 
			url: "send_sms_for_register.do?phone="+phone+"&appType=1", 
			success: function(val){
      	 		if(val.status == 9){
      	 			location.href="invitation_alreadyReg.do";
      	 			return;
      	 		}
      		}
		  });
	}
	var conut = 60;
	element.innerHTML="重新发送(" + conut + ")"; 
	element.timer = setInterval(function() { 
		element.innerHTML="重新发送(" + conut + ")"; 
		conut--; 
		if (conut <= 0) {
			clearInterval(element.timer);
			element.timer = undefined;
			element.innerHTML= '发送验证码';
		}
	},1000);
	
} 

function reg(){
	
	
	var phone = document.getElementById('uphone').value;
	var phone_code = document.getElementById('phone_code').value;
	var nickname = document.getElementById('nickname').value;
	var password = document.getElementById('password').value;
	var userId = document.getElementById('userId').value;
	var type =  document.getElementById('type').value;
	var htp =  document.getElementById('htp');
	if(phone == null || phone == undefined || phone == ""){
		cloud.explain("请输入手机号");
		return;
	}
	var reg = /^[1][3578][0-9]{9}$/;
	if(!reg.test(phone)){
		cloud.explain("手机号码格式不正确");
	    return;
	}
	if(phone_code == null || phone_code == undefined || phone_code == ""){
		cloud.explain("请输入验证码");
		return;
	}
	if(nickname == null || nickname == undefined || nickname == ""){
		cloud.explain("请输入昵称");
		return;
	}
	if(password == null || password == undefined || password == ""){
		cloud.explain("请输入密码");
		return;
	}
	if(htp.className == 'flex-box-0 invite-reg-false'){
		cloud.explain("请确认同意服务协议");
		return;
	}
	if(type==1){
		$.ajax({ 
			url: "addUser.do?phone_code="+phone_code+"&phone="+phone+"&nickname="+nickname+"&password="+password+"&userId="+userId+"", 
			success: function(val){
	  	 		if(val.status == 0){
	  	 			location.href="invitation_success.do";
	  	 			return;
	  	 		}else{
	  	 			cloud.explain(val.msg);
	  	 		}
	  		}
		});
	}else{
		$.ajax({ 
			url: "addCoach.do?phone_code="+phone_code+"&phone="+phone+"&nickname="+nickname+"&password="+password+"&userId="+userId+"", 
			success: function(val){
	  	 		if(val.status == 0){
	  	 			location.href="invitation_success.do";
	  	 			return;
	  	 		}else{
	  	 			cloud.explain(val.msg);
	  	 		}
	  		}
		});
	}
	
}
</script>
</html>