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
    <title>提现账户</title>
    <link rel="stylesheet" href="/css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
    <link rel="stylesheet" href="/css/sale.css"/>
    <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>
    <style>
        body {
            background-color: #FFF;
        }
        span.getMoney {
            margin: 5%;
            vertical-align: text-top;
        }
        .choice-wechat01 {
            border: 1px solid #ebebeb;
            padding: 3%;
            margin-right: 3%;
            border-radius: 4px;
        }
        input.maxGetMoney {
            margin: 0 4%;
            width: 85%;
            border: 1px solid #ebebeb;
            height: 40px;
            padding-left: 4%;
            border-radius: 4px;
        }
        input.CardButton {
            width: 90%;
            margin-left: 4%;
            background-color: #E4393C;
            height: 45px;
            border-width: 0;
            color: #fff;
            font-size: 18px;
            border-radius: 4px;
            margin-top: 25px
        }
        /* 选择提现方式 */
        .methodselected1,.methodselected2 {
            background: url("../../images/total.png");
            background-size: 650px;
            display: inline-block;
            position: absolute;
            background-position: -440px -112%;
            width: 50px;
            height: 45px;
            top: 12px;
            right: -10px;
            display:none;
        }
        .currentSelected {
            border-color:#ED5456;
        }
    </style>
</head>
<body>
<div class='header'>
    <p>
        <a class="back" href="javascript:history.go(-1)"></a>
        <a class="loginTitle">我要提现</a>
    </p>
</div>
<div class="spacediv"></div>
<form action="" id="getMoney" name="getMoney" onsubmit="return checkForm()">
<div>
    <span class="getMoney">提现金额（元）</span><br>
   
        <input type="text" class="maxGetMoney" id="wd_money" name="wd_money" value="" placeholder="最多可提现${agent.agentCompany.amount}元" required onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
        <input type="hidden" id="amount" value="${agent.agentCompany.amount}">
</div>
<div>
    <span class="getMoney">提现方式</span><br>
    <div class="getPay" style="margin-top:-10px;">
        <div  id="BankPay" class="choice-wechat01">
            <span class="getPay-img01"></span>
        <span class="getPay-text">
             <a>我的银行卡</a><br>
            <c:if test="${not empty agent.agentCompany.card_num_show }" var="hava">
            	<a>${agent.agentCompany.card_num_show}</a>
            </c:if>
            <c:if test="${!hava}">
            <a style="color:#ED5456;">未绑定</a>
            </c:if>
        </span>
        <span class="methodselected"></span>
     <!--       <a href="myCardPay.html"> <span class="arrowRig-img" style="top:-65px;"></span></a>-->
        </div>
    </div>
    <div class="getPay" style="margin-top: 13px;">
        <div id="AliPay" class="choice-wechat01">
            <span class="getPay-img02"></span>
        <span class="getPay-text" >
           <a>我的支付宝</a><br>
			<c:if test="${not empty agent.agentCompany.alipay_account_show }" var="hava">
            	<a>${agent.agentCompany.alipay_account_show}</a>
            </c:if>
            <c:if test="${!hava}">
            <a style="color:#ED5456;">未绑定</a>
            </c:if>
        </span>
        <span class="methodselected"></span>
        <span class="clear"></span>
        </div>
    </div>
    <div class="clear"></div>
    <div >
        <input class="CardButton" type="submit" value="确认提现" name="" form="getMoney" />
    </div>
    <div class="warning" style="padding-right: 7%;">
        <ol>
            <li style="color:#EC5355;font-size:15px;">重要提示：</li>
            <p>体现资金一般1-2个工作日内到账，如超期未到账、提现失败，请联系客服<!-- <a href="#" style="color: #51A9E6;">feicui</a> --></p>
        </ol>
    </div>
</div>
<input type="hidden" value="" name="account_type" id="account_type">
</form>
</body>
<script>
$(function() {
   // var bankpay = $("#BankPay");
    var alipay = $(".choice-wechat01");
    var selected = $(".methodselected");

        alipay.click(function() {
            alipay.removeClass("currentSelected");
            $(this).addClass("currentSelected");
            var id =$(this).attr("id");
            if("BankPay"==id){
            	$("#account_type").val(1);
            }else{
            	$("#account_type").val(2);
            }
        });
           
});
function checkForm() {
    var wd_money = document.getElementById("wd_money").value;
    var amount = document.getElementById("amount").value;
    if(wd_money==""){
    	alert("请填写金额");
    	return false;
    }
    
    if(wd_money>=parseFloat(amount)) {
        alert("可提现金额为:"+amount);
        document.getElementById("amount").focus();
        return false;
    }
    var account_type=$("#account_type").val();
    if(account_type==""){
    	alert("请选择提现方式");
    	return false;
    }
    var info = util.POST("withdwar_company.do",{"wd_money":wd_money,"account_type":account_type});
    if(info.status==0){
    	alert(info.msg);
    	document.getElementById("wd_money").value="";
    	location.reload();
    }else{
    	alert(info.msg);
    }
    
    return false;
}
</script>
</html>