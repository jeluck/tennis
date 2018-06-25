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
					<form id="registerForm" action="addproduct.do" method="post" >
						<table class="f14" width="800" >
							<tr>
								<td width="86" class="col-xs-5 col-sm-6" >用户</td>
								<td>
									<select name=userid class="col-xs-5 col-sm-6" >
										<c:forEach items="${w }" var="o" >
											<option value="${o.id}">${o.uphone}</option>
										</c:forEach>
									</select>
								</td>

							</tr>
							<tr>
								<td width="86">场馆</td>
								<td>
									<select name="productno" class="col-xs-5 col-sm-6" >
										<c:forEach items="${p}" var="o" >
											<option value="${o.id}">${o.name}--${o.id}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td width="86">数量</td>
								<td >
								<div id="spaceandtime">
								<table>
									<tr>
									<td></td>
									<c:forEach items="${s}" var="o">
									<td id="${i.index}_${o.id}">${o.name}&nbsp;&nbsp;&nbsp;</td>
									</c:forEach>
									</tr>
								<c:forEach begin="${pe.opentime}" end="${pe.endtime}" varStatus="i">
								
								<tr>
									<td>${i.index }&nbsp;&nbsp;&nbsp;</td>
									<c:forEach items="${s}" var="o">
									<td id="${i.index}_${o.id}">${o.price}元&nbsp;&nbsp;&nbsp;</td>
									</c:forEach>
								</tr>
								
								</c:forEach>
								</table>
								</div>
								</td>
							</tr>
						</table>
						<input type="submit" value="提交"/>
					</form>
</body>

<script>
function spaceandtime(){
	var str="";
	var opentime = ${pe.opentime};
	var endtime = ${pe.endtime};
	alert(opentime);
	alert(endtime);
	$("#spaceandtime").html(str);
}
</script>

</html>

