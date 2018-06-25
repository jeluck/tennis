<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商铺信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;

</script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			function passRequest(shopId)
			{
				alert("pass request");
				alert("shop id " + shopId);
				location.href = "pass_request.do?shopId=" + shopId;
			}

			function rejectRequest(shopId)
			{
				alert("reject request");
				alert("shop id " + shopId);
				location.href = "reject_request.do?shopId=" + shopId;
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">管理</li>
			<li class="active">商铺详情</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>商铺详情 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<table width="70%" border="0" align="center">
		<tr>
			<td width="5%">&nbsp;</td>
			<td width="40%">
				<table border="1"  align="left" class="table table-striped table-bordered table-hover">
					<tr >
						<td >
							商铺名称：
						</td>
						<td >
							${shop.shopName}
						</td>
						<td xwidth="5%">
						</td>
					</tr>

					<tr>
						<td>实名认证状态：</td>
						<td>
							<c:if test="${shop.isRealNameAuthentication == \"0\" }" >已认证</c:if>
							<c:if test="${shop.isRealNameAuthentication == \"1\" }" >未认证</c:if>
						</td>
						<td></td>
					</tr>

					<tr>
						<td>
							真实姓名：
						</td>
						<td>
							${shop.realName}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>
							身份证：
						</td>
						<td>
							${shop.identification}
						</td>
						<td></td>
					</tr>

					<tr>
						<td>身份证照片：</td>
						<td>
							<a href="/${shop.idCardPhotoUrl}" target="_blank">
								<img src="/${shop.idCardPhotoUrl}"width="210" height="120px" />
							</a>
						</td>
						<td></td>
					</tr>

					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>


				</table>
			</td>
			<td width="20%">&nbsp;</td>
		</tr>

		<tr>
			<td></td>
			<td>&nbsp;</td>
			<td></td>
		</tr>


		<tr>
			<td width="5%">&nbsp;</td>
			<td width="20%">
				<a class="btn btn-sm btn-success" onclick="passRequest('${shop.id}');"> 通过申请 </a>
				<a class="btn btn-sm btn-danger"  onclick="rejectRequest('${shop.id}');"> 驳回申请 </a>
			</td>
			<td width="20%">

			</td>
		</tr>
	</table>


</body>
</html>
