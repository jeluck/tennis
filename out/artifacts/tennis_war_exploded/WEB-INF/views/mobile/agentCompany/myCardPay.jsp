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
    <title>确定订单</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">我的银行卡</a>
    </p>
</div>
<div class="spacediv"></div>
<div>
   <div class="writeCard">
   <form action="addmyCardPay.do" method="post">
       <div class="NameCard">
       <input class="noborder" type="text" value="" placeholder="请填写开户姓名" name="bankcard_realname"><br>
      	<select  id="bankid" name="bankinfoid" class="bankSelected">
			<option value="0">请选择开户银行</option>
			<c:forEach items="${bank_list }" var="bank">
			<option value="${bank.id}">${bank.bank_name}</option>
			</c:forEach>
		</select>	
       <br>
       <input class="noborder" type="text"  placeholder="请输入银行卡卡号"  name="cardnumber"><br>
       </div>
       <input type="submit" class="CardButton" value="确定"><br>

   </form>
   </div>
   <div class="warning">
       <ol>
           <li style="color:#EC5355;font-size:15px;">重要提示：</li>
           <li>1、持卡人必须为本人，开户姓名填写后不会修改；</li>
           <li>2、银行卡仅支持储蓄卡，请不要填写信用卡；</li>
           <li>3、开户姓名、卡号务必准确填写，否则无法体现；</li>
       </ol>
   </div>

</div>
</body>
</html>