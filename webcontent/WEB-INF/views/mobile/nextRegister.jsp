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
    <title>立即注册</title>
    <link rel="stylesheet" href="css/c.css"/>
    <link rel="stylesheet" href="css/customer.css"/>
    <script src="./js/jquery.min.js"></script>
	<script src="./js/sdk/strophe.js"></script>
    <script src="./js/sdk/json2.js"></script>
    <script src="./js/sdk/easemob.im-1.0.7.js"></script>
    <script src="./js/easemob.im.config.js"></script>
    <script src="./js/admin_common.js"></script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">账号注册</a>
    </p>
</div>
<form class="form register-form">
	<input type="hidden" name="invite_code" id="invite_code" value="${invite_code}">
    <input type="hidden" name="uphone" id="uphone" value="${uphone}">
    <input type="hidden" name="pass" id="pass" value="${pass}">
    <input type="hidden" name="username" id="username" value="${username}">
    <input  class="invitedCode" type="text" maxlength="11" id="real_name" name="real_name" placeholder="姓名" required><br>
    <span class="invited-img01"></span>
    <input  class="setName" type="text" maxlength="18"  id="idcard_no" name="idcard_no" placeholder="身份证号" required><br>
    <span class="setName-img01"></span>
    <a href="javascript:void(0);"><input id="norepeatSubmit" onClick="return regist();" class="submit" type="button" value="立即注册"></a>
    <span class="agreement" style="color:#ACACAC;">注册即视为同意 &nbsp;<a href="agreement.html" style="font-size:16px; color:#788DF0">服务协议</a></span>
</form>
</body>

<script type="text/javascript">
     var regist = function() {
        var user = $("#uphone").val();
        var pass = $("#pass").val();
        var nickname = $("#username").val();
        if (user == '' || pass == '') {
            alert("手机号码或密码不能为空");
            return false;
        }
		
		var idcard = $('#idcard_no').val();
		var re = /^(\d{15})|(\d{17}[0-9]x)$/i;
		if(!re.test(idcard)) {
			alert("请输入有效身份证号");
			$('#idcard_no').focus();
			return false;
		}
		
	    if('' == '${friendphone}'){
			alert('邀请人不存在');
			return false;
		}
		
		 $('#norepeatSubmit').prop('disabled', true);
		
	
		//注册环信用户
		var options = {
            username : user,
            password : pass,
            nickname : nickname,
            appKey : Easemob.im.config.appkey,
            success : function(result) {
				//注册成功后进行好友关系的添加
				var ops = {
					ownerusername : user,
					friendusername : '${friendphone}',
					appKey : Easemob.im.config.appkey,
					token: '${token}',
					success : function(result) {
						//好友关系成功处理后向本地数据库添加数据
						var data = {};
						data.uphone = user;
						data.pass = pass;
						data.invite_code = $('#invite_code').val();
						data.username = nickname;
						data.real_name = $('#real_name').val();
						data.idcard_no = $('#idcard_no').val();
						
						var info = util.POST("add_new_user.do", data);
					
						if(info.status != '0') {
							alert(info.msg);
							return false;
						}
						alert('注册成功');
						location.href='touserlogin.do';
					},
					error : function(e) {
						alert(e.error);
						$('#norepeatSubmit').removeAttr('disabled');
					},
					apiUrl : Easemob.im.config.apiURL
				};
				Easemob.im.Helper.autoAddFriend(ops);
            },
            error : function(e) {
				if('duplicate_unique_property_exists' == e.error) {
					alert('该手机号码已在远程服务器上注册，\n请联系管理员处理，或者请返回修改');
					history.back(-1);
				}
				else{
					alert('同步注册即时通讯注册失败');
					$('#norepeatSubmit').removeAttr('disabled');
					
				}
            },
            apiUrl : Easemob.im.config.apiURL
        };
        Easemob.im.Helper.registerUser(options);
		
    };

	$(function(){
		$('#norepeatSubmit').removeAttr('disabled');
				
		if('' != '${errormsg}'){
			alert('${errormsg}');
			history.back();
		}
	});
</script>
</html>