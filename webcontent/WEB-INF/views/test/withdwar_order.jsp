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
</head>
<body>
					<form id="registerForm" action="withdwar_applypay.do" method="post" >
						<table class="f14" width="800" >
							<tr>
								<td width="86" class="col-xs-5 col-sm-6" >用户</td>
								<td>
									<select name=uid id='uid' class="col-xs-5 col-sm-6" onchange="getcard('uid')">
										<c:forEach items="${w }" var="o" >
											<option value="${o.id}">${o.uphone}</option>
										</c:forEach>
									</select>
								</td>

							</tr>
							<tr>
								<td width="86">银行卡</td>
								<td>
									<select name="card_id" class="col-xs-5 col-sm-6" id="card_id">
											<option value="0">请选择</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="86">提现金额</td>
								<td>
								<input name="wd_money" value=""/>
								</td>
							</tr>
						</table>
						<input type="submit" value="提交"/>
					</form>
</body>
<script>		

function getcard(id){
	var uid = $("#"+id).val();
	var bank_area = $("#card_id");
	var info = util.POST("card_list.do",{"uid":uid});
	var str ='';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.id+">"+value.card_num+"</option>";
    });  
	if(str!=''){
		bank_area.html(str);
	}else{
		str ='<option value="0">请选择</option>';
		bank_area.html(str);
	}
	
}
</script>
</html>

