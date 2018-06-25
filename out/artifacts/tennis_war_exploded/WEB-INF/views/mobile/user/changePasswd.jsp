<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <title>修改密码</title>
    <link rel="stylesheet" href="./css/c.css"/>
    <link rel="stylesheet" href="./css/customer.css"/>
    <link rel="stylesheet" href="./css/personMyOrder.css"/>
    <link rel="stylesheet" href="./css/sale.css"/>
    <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">修改登录密码</a>
    </p>
</div>
<div>
    <form id="form1" method="post" onSubmit="return chkform();">
        <input style="padding-left: 5%;margin: 0px auto;margin-top:10px;" id="pass" class="nameNoBorder" type="password" maxlength="20" value="" placeholder="现在的密码" required="required" /><br>
        <input style="padding-left: 5%;margin: 0px auto;" class="nameNoBorder" id="newpass" type="password" maxlength="20" value="" placeholder="新的密码" required="required" />
        <input style="padding-left: 5%;margin: 0px auto;margin-bottom:10px;"class="nameNoBorder" id="renewpass" type="password" maxlength="20" value="" placeholder="确定新的密码" required="required" /><br>
        <input class="CardButton" type="submit"  value="确定修改"/><br>
       <!-- <p class="nameWarning">昵称不能超过10个汉子或者20个英文字符，支持特殊符号。</p>-->
    </form>
</div>
<script>
function chkform(){
	var data = {};
		data.pass = $('#pass').val();
		data.newpass = $('#newpass').val();
		data.renewpass = $('#renewpass').val();
		
		if(data.pass == ''){
			alert('用户密码不能为空');
			return false;
		}
		
		if(data.repass == ''){
			alert('用户新密码不能为空');
			return false;
		}
		
		if(data.newpass != data.renewpass){
			alert('新密码和确认新密码不一致');
			return false;
		}
		
		
		var info = util.POST("tochangePasswd.do", data);
		
		if(info.status != '0'){
			alert(info.msg);
		}
		else{
			alert('修改成功');
			window.location.href='userlogout.do'; 
			try{ window.hideTab.exit(); }catch (e) {}
		}
		
		return false;
}
</script>
</body>
</html>