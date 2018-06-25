<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改或者查看用户等级信息</title>
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
			<li class="active">用户等级管理</li>
			<li class="active">
				<c:if test="${empty check }">
					修改用户等级
				</c:if>
				<c:if test="${!empty check }">
					查看用户等级
				</c:if>
			</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			<c:if test="${empty check }">
				修改用户等级
			</c:if>
			<c:if test="${!empty check }">
				查看用户等级
			</c:if>
		 	<span id="time" style="font-size:20px;float: right"></span>
		</h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="user_level_edit.do"  
		  method="post" onsubmit="return checkForm()">
		<input type="hidden" value="${o.id}" name="id" />	
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >
								等级：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
											   name="level" style="width: 360px;" placeholder="请输入等级"
											   class="col-xs-10 col-sm-5" required value="${o.level }"  />	
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								内容：
							</td>
							<td >
								<div class="col-sm-9">
<!-- 									<input type="text" -->
<!-- 										   name="name" style="width: 360px;" placeholder="请输入内容" -->
<%-- 										   class="col-xs-10 col-sm-5" required value="${o.name }"  /> --%>
										   
										   <textarea name="name" id="content"
									style="height: 150px; width:360px;" required >${o.name }</textarea>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>

			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<c:if test="${empty check }">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
					</c:if>
					<button class="btn btn-sm btn-success" type="button" onclick="location.href = 'user_level.do?pagenumber=1';" > [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>




</body>

</html>
