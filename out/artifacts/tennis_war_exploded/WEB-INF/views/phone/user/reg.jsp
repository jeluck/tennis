<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js?${getVersion}"></script>
<script src="/js/common.js"></script>

<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/reg.css">
<script src="/js/reg.js?${getVersion}"></script>
<%@ include file="./common/common_init.jsp" %>
<!--本页面单独使用文件end-->
</head>
<body class="lz-body">
<header class="lz-header lz-theme-bg" id="lz-header">
  <div class="lz-header-left"><i class="lz-iconfont lz-icon-fanhui"></i></div>
  <h2 class="lz-header-title">注册</h2>
  <div class="lz-header-right"></div>
</header>
<main class="lz-reg">
  <form action="toSaveUser.do" method="post" onsubmit="return lz.testRegForm(this);">
  	<input type="hidden" id="userId" name="userId">
    <ul>
      <li class="lz-default"> <i class="lz-iconfont lz-icon-shouji"></i>
        <div class="lz-code" onClick="lz.sendCode()">获取验证码</div>
        <input id="account" type="number" name="phone" value="" placeholder="请输入您的手机号">
      </li>
      <li class="lz-default"> <i class="lz-iconfont lz-icon-duanxin"></i>
        <input id="code" type="text" name="phone_code" value="" placeholder="请输入短信验证码">
      </li>
      <li class="lz-default"> <i class="lz-iconfont lz-icon-touxiang"></i>
        <input id="name" type="text" name="nickname" value="" placeholder="请输入昵称">
      </li>
      <li class="lz-default"> <i class="lz-iconfont lz-icon-suo"></i>
        <div class="lz-password-cutover" onClick="lz.passwordCutover(this);"><i class="lz-iconfont lz-icon-yuedu"></i></div>
        <input id="password" type="password" name="password" value="" placeholder="请设置密码">
      </li>
      <li class="lz-default lz-btn">
        <input class="lz-theme-bg" type="submit" value="注册">
      </li>
      <li class="lz-default lz-checkbox"> <span onClick="lz.checkbox(this);"><i class="lz-iconfont lz-icon-dagou lz-theme2-font-color"></i>
        <input id="pact" type="checkbox" checked="checked">
        </span> 同意<a class="lz-theme2-font-color" href="#">《弘金地用户服务协议》</a> </li>
    </ul>
  </form>
</main>
</body>
</html>

