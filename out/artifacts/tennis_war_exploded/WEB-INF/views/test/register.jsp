<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="image/liantiao_favicon.ico" type="image/x-icon" />
<title>用户注册页</title>
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validate/register.js"></script>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/web.css" />
<link rel="stylesheet" type="text/css" href="css/jygx/common.css">
<link rel="stylesheet" type="text/css" href="css/jygx/index.css">
<style>
.cr{
color:red;
}
a.tdn:hover{
text-decoration: none;
}
</style>
</head>
<body>
	<div id="contain">
		<div id="main" class="login_main" style="height:700px;">
<!-- 			<div id="toptitle_register">用户注册</div> -->
			<ul id="errorlist"></ul>
			<div class="register_main pr" id="register_one">
				<div class="register_main_div">
					<form id="registerForm" action="add_new_user.do" method="post" >
						<div class="top_register_image">
						</div>
						<table class="f14" width="800" style="margin-left:-50px;">
							<tr>
								<td width="86"><span class="cred mr5">*</span>用户名</td>

								<td>
									<input type="text" class="login_input input_text input_change" empty_show="请输入用户名"
									id="username" name="uphone"
									style="padding: 10px 40px; border: 1px solid rgb(204, 204, 204); background: url(/css/in.png) 10px 50% no-repeat;"/><span class="_valid"></span></td>

							</tr>
							<tr>
								<td><span class="cred mr5">*</span>密&nbsp;&nbsp;码</td>
								<td><input type="password"
									class="login_input input_text input_change" empty_show="请输入密码"
									id="password" name="pass"
									style="padding: 10px 40px; border: 1px solid rgb(204, 204, 204); background: url(/css/il.png) 10px 50% no-repeat;"/>
									<span class="_valid"></span></td>
							</tr>
						</table>
						<input type="hidden" value="${invite_code}" name="invite_code"/>
						<input type="submit" value="提交"/>
					</form>
				</div>
</body>
</html>

