<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="image/liantiao_favicon.ico" type="image/x-icon" />
<title>银行卡管理</title>
</head>
<body class="witer">
<form action="save_bank_card.do">
	<div id="msg_height">
		<div class="basic_title">银行卡管理</div><br>
				<label>真实姓名:<input value="车水马龙" name="account_name" /></label><br> 
				<label>省:	<select id="bank_province" name="bank_province" onchange="getCity('bank_province')">
							<option value="0">请选择</option>
							<c:forEach items="${provincelist}" var="p">
							<option value="${p.region_id}">${p.region_name}</option>
							</c:forEach>
							</select>	
				</label><br>
				<label>市:	<select  id="bank_city" name="bank_city"  onchange="getArea('bank_city')">
							<option value="0">请选择</option>
							</select>	
				</label><br>
				<label>区:	<select id="bank_area" name="bank_area">
							<option value="0">请选择</option>
							</select>	
				</label><br>
				<label>银行:	<select  id="bankid" name="bank.id">
							<option value="0">请选择</option>
							<c:forEach items="${bank_list }" var="bank">
							<option value="${bank.id}">${bank.bank_name}</option>
							</c:forEach>
							</select>	
				</label><br>
				<label width="86" class="col-xs-5 col-sm-6" >用户 
									<select name=weuser.id class="col-xs-5 col-sm-6" >
										<c:forEach items="${w }" var="o" >
											<option value="${o.id}">${o.uphone}</option>
										</c:forEach>
									</select>
								</label>
				<label class="cardid">地址:<input value="乘兴千差万别10栋322" name="bank_address"/></label><br>
				<label class="cardid">卡号:<input value="324234234322121312" name="card_num"></label><br>
		
		<input type="submit" value="提交">
		</div>
</form>
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script>		
function getCity(id){
	var province = $("#"+id).val();
	var bank_city = $("#bank_city");
	var info = util.POST("city.do",{"province":province});
	var str ='<option value="0">请选择</option>';
	
	var bank_area = $("#bank_area");
	bank_area.html(str);
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	bank_city.html(str);
}

function getArea(id){
	var city = $("#"+id).val();
	var bank_area = $("#bank_area");
	var info = util.POST("area.do",{"city":city});
	var str ='<option value="0">请选择</option>';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	bank_area.html(str);
}
</script>

</body>
</html>