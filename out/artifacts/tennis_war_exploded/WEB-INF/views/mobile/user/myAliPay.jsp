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
    <title>我的支付宝</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="/css/sale.css"/>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">我的支付宝</a>
    </p>
</div>
<div class="spacediv"></div>
<div>
    <div class="writeCard">
        <form method="post" action="user_addmyAliPay.do">
            <div class="NameCard">
                <input class="noborder" type="text" value="" placeholder="请输入支付宝真实姓名" name="alipay_realname"><br>
                <input class="noborder" type="text"  placeholder="请输入支付宝账号" name="alipay_account"><br>
            </div>
            <input type="submit" class="CardButton" value="确定"><br>

        </form>
    </div>
    <div class="warning">
        <ol>
            <li style="color:#EC5355;font-size:15px;">重要提示：</li>
            <li>1、请填写支付宝实名认证的真实姓名，填写后不可在修改；</li>
            <li>3、账号、姓名必准确填写，否则无法体现；</li>
        </ol>
    </div>

</div>
</body>
</html>