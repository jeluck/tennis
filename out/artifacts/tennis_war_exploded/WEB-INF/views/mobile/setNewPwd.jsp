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
    <title>找回密码</title>
    <link rel="stylesheet" href="./css/c.css"/>
    <link rel="stylesheet" href="./css/customer.css"/>
    <link rel="stylesheet" href="./css/personMyOrder.css"/>
    <link rel="stylesheet" href="./css/sale.css"/>
    <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>
</head>
<body>
<div>
    <form id="form1" method="post" onSubmit="return chkform();">

        <input type="password" class="invitedCode" value="" placeholder="请输入新的密码" id="pass" required="required" maxlength="15" style="margin-top:20px;margin-bottom: 30px;"/>
        <input type="password" class="invitedCode" value="" placeholder="请再次输入密码" id="repass" required="required"  maxlength="15" style="margin-top: -20px;margin-bottom: 25px;"/>
        <input type="hidden" id="idcard_no"  value="${idcard_no}" >
        <input type="submit" class="submit" value="保存"/>

    </form>
</div>

<script>
function chkform(){
	var data = {};
	data.pass = $('#pass').val();
	data.repass = $('#repass').val();
	data.idcard_no = $('#idcard_no').val();
	
	var info = util.POST("tosetNewPwd.do", data);
		
	if(info.status != '0'){
		alert('修改失败');
		return false;
	}
	else{
		alert('修改成功');
		window.location.href='touserlogin.do'; 
		try{ window.hideTab.exit(); }catch (e) {}
	}
	
	return false;
}

$(function (){
	if('' == '${idcard_no}') {
		alert('身份证号码不存在');
		//history.back('-1');
	}
});
</script>
</body>
</html>