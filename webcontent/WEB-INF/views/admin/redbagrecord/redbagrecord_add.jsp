<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加红包记录</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">

function checkForm()
{
	return true;
}

</script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">红包记录管理</li>
			<li class="active">添加红包记录</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加红包记录 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addredbagrecord.do"  
		  method="post" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >
								红包编号：
							</td>
							<td >&nbsp;&nbsp;
								<select  name="redbagid"  class="bankSelected" style="width: 200px;" required >
									<c:if test="${not empty redBag}">
										<c:forEach items="${redBag}" var="o" varStatus="v">
											<option value=${o.id } >${o.id }</option>
										</c:forEach>
									</c:if>
									<c:if test="${empty redBag}">
										<option value="" >目前暂无红包,请先添加红包</option>
									</c:if>
								</select>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								分配给：
							</td>
							<td >&nbsp;&nbsp;
								<select   name="weuserid"  class="bankSelected" style="width: 200px;" required >
									<c:forEach items="${weuUser}" var="o" varStatus="v">
											<option value=${o.id } >${o.username }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
