<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <title>新增地址</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
        <script src="../../js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
    <style>
        #textarea {
            margin: -6px auto;
            margin-bottom: 15px;
            text-align: -webkit-auto;
            width: 92%;
            line-height: 25px;
            color: #A9A9A9;
            padding-top: 15px;
            border: none;
            padding: 4%;
        }
        .produce-sum {
			padding: 4%;
			background-color: #fff;
			width: 96%;
		}
		input.CardButton {
			margin-left: 8%;		
		}
    </style>
</head>
<body style="width: 96%;">
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">新增地址</a>
    </p>
</div>
<form action="addaddress.do" method="post" onSubmit="return chkform();">
    <div class="produce-sort produce-sum" style="margin: 2px auto;">
        收货人<input  class="textGray border-none" type="text" maxlength="" id="consignee" name="consignee" placeholder="请输入收货人姓名" style="margin-left: 6%;"/>
    </div>
    <div class="produce-sort produce-sum" style="margin: 2px auto;">
        电话<input class="textGray border-none" type="number" name="phone" id="phone" maxlength="11" placeholder="请输入手机号码"/>
     </div>
    <!-- 设置运费 -->
    <div class="setExpress-pay produce-sum" style="margin: 10px auto;margin-bottom: 1px;">
        <select id="user_province" name="user_province" onchange="getCity('user_province')" class="bankSelected">
			<option value="0">请选择省份</option>
			<c:forEach items="${provincelist}" var="p">
			<option value="${p.region_id}">${p.region_name}</option>
			</c:forEach>
		</select>	
    </div>
    <!-- 设置佣金 -->
    <div class="setPay produce-sum" style="margin: -12px auto;">
        <select  id="user_city" name="user_city"  onchange="getArea('user_city')"  class="bankSelected">
			<option value="0">请选择城市</option>
		</select>
    </div>
    <div class="setPay produce-sum" style="margin: 2px auto;"  >
        <select id="user_area" name="user_area" class="bankSelected">
			<option value="0">请选择区、县</option>
		</select>
    </div>
    <input name="address" id="address" type="text" class="textGray border-none" placeholder="请输入详细街道细信息"/>
    <div>
        <input class="CardButton" type="submit" value="保存"/>
    </div>
    <c:if test="${not empty orderCome }">
    	<input value="orderCome" name="orderCome" type="hidden">
    </c:if>
    <c:if test="${not empty buyvipcard }">
    	<input value="buyvipcard" name="buyvipcard" type="hidden">
    </c:if>
</form>
</body>
<script>		
function getCity(id){
	var province = $("#"+id).val();
	var user_city = $("#user_city");
	var info = util.POST("city.do",{"province":province});
	var str ='<option value="0">请选择</option>';
	
	var user_area = $("#user_area");
	user_area.html(str);
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_city.html(str);
}

function getArea(id){
	var city = $("#"+id).val();
	var user_area = $("#user_area");
	var info = util.POST("area.do",{"city":city});
	var str ='<option value="0">请选择</option>';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_area.html(str);
}
function chkform(){
	if($('#consignee').val() == ''){
		alert('收货人姓名不能为空');
		return false;
	}
	
	if($('#phone').val().length < 11){
		alert('电话不正确');
		return false;
	}
	
	if($('#user_province').val() == '0'){
		alert('请选择省份');
		return false;
	}
	
	if($('#user_city').val() == '0'){
		alert('请选择城市');
		return false;
	}
	
	if($('#user_area').val() == '0'){
		alert('请选择区域');
		return false;
	}
	
	if($('#address').val() == ''){
		alert('详细街道信息不能为空');
		return false;
	}
	
	return true;
}
</script>
</html>