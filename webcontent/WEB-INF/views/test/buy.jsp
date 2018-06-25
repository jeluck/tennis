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
								<td width="86">商品</td>
								<td>
									<select name="productno" class="col-xs-5 col-sm-6" >
										<c:forEach items="${c}" var="o" >
											<option value="${o.commodityTypeNo}">${o.commodityId.commodity_name}--${o.commodityType}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td width="86">数量</td>
								<td>
									<select name="num" class="col-xs-5 col-sm-6" >
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
									</select>
								</td>
							</tr>
						</table>
						<input type="submit" value="提交"/>
					</form>
</body>
</html>

