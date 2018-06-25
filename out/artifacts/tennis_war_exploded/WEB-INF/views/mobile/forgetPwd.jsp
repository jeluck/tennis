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
        <input type="text" class="invitedCode" value="" id="idcard_no" placeholder="请输入注册绑定身份证号" required="required" style="margin-top: 20px;margin-bottom: 30px;"/>
        <input type="text" class="invitedCode" value="" id="real_name" placeholder="请输入姓名" required="required"   maxlength="15" style="margin-top: -20px;margin-bottom: 25px;"/>

        <input type="submit" class="submit" value="确定" />
    </form>
</div>

<script>
function chkform(){
	var data = {};
	data.idcard_no = $('#idcard_no').val();
	data.real_name = $('#real_name').val();
	
	var info = util.POST("toforgetPwd.do", data);
		
	if(info.status != '0'){
		alert(info.msg);
		return false;
	}
	else{
		window.location.href='setNewPwd.do?idcard_no=' + data.idcard_no; 
		try{ window.hideTab.exit(); }catch (e) {}
	}
	
	return false;
}

</script>
</body>
</html>