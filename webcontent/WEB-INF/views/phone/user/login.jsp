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
  <title>登录</title>
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
  <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
  <script src="/js/jquery-1.8.1.min.js"></script>
  <script src="/js/admin_common.js?${getVersion}"></script>
  <script src="/js/common.js?${getVersion}"></script>

  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/reg.css?${getVersion}">
  <script src="/js/reg.js?${getVersion}"></script>
  <!--本页面单独使用文件end-->
</head>

<body class="lz-body">
  <header class="lz-header lz-theme-bg" id="lz-header">
    <div class="lz-header-left"></div>
    <h2 class="lz-header-title">登录</h2>
    <div class="lz-header-right"></div>
  </header>
  <section class="lz-version">
    <img src="/images/logo.png" alt="U橙logo">
    <h3>U橙</h3>
    <p>Take&nbsp;&nbsp;it&nbsp;&nbsp;Play</p>
  </section>
  <main class="lz-reg">
    <form action="toSaveUser.do" method="post" onsubmit="return lz.testLoginForm(this);">
     <input type="hidden" id="userId" name="userId">
      <ul>
        <li class="lz-default"> <i class="lz-iconfont lz-icon-shouji"></i>
          <input id="account" type="text" value="" placeholder="请输入您的手机号">
        </li>
        <li class="lz-default"> <i class="lz-iconfont lz-icon-suo"></i>
          <div class="lz-password-cutover" onClick="lz.passwordCutover(this);"><i class="lz-iconfont lz-icon-yuedu"></i></div>
          <input id="password" type="password" value="" placeholder="请输入密码">
        </li>
        <li class="lz-default lz-btn">
          <input class="lz-theme-bg" type="submit" value="登录">
        </li>
        <li>
          <span class="lz-left" data-lz-url="towecharReg.do">立即注册</span>
          <span class="lz-right" data-lz-url="#">忘记密码</span>
        </li>
      </ul>
    </form>
    <div class="lz-other-login">
<!--       <h4><span>快速登录</span></h4> -->
<!--       <div class="lz-btn"> -->
<!--         <div class="lz-table"> -->
<!--           <div class="lz-table-cell" data-lz-url="#"> -->
<!--             <i class="lz-iconfont lz-icon-yuan-weixin"></i> -->
<!--           </div> -->
<!--           <div class="lz-table-cell" data-lz-url="#"> -->
<!--             <i class="lz-iconfont lz-icon-yuan-qq"></i> -->
<!--           </div> -->
<!--           <div class="lz-table-cell" data-lz-url="#"> -->
<!--             <i class="lz-iconfont lz-icon-yuan-weibo"></i> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
    </div>
  </main>
</body>

</html>
