<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
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
			<li class="active">管理</li>
			<li class="active">修改场地时间价格</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改场地时间价格<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<form id="QueryForm" name="form1" class="form-inline" action="editcoachspace_time_money.do"  
		  method="post" onsubmit="return checkForm()">
		  <input type="hidden" name="coachId" value="${coachId }">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%" valign="top" >
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr>
							<th colspan="2">工作日</th>
						</tr>
						<c:forEach var="t" items="${tiemMoenyList }">
							<c:if test="${t.time_type == 1 }">
								<tr>
									<td>${t.time }:00</td>
									<td>
										<input type="text" name="gprice" value="${t.price }"  class="col-xs-10 col-sm-5" required/>&nbsp;
										<select name="gspaceId"  class="bankSelected" required >
											<option value="0" <c:if test="${t.space_id == 0 }">selected="selected"</c:if> >休息</option>
											<option value="1" <c:if test="${t.space_id == 1 }">selected="selected"</c:if> >值班</option>
										</select>
										
										<input type="hidden" name="gid" value="${t.id }">
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</td>
				<td width="40%" valign="top">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover" >
						<tr>
							<th colspan="2">周末</th>
						</tr>
						<c:forEach var="t" items="${tiemMoenyList }">
							<c:if test="${t.time_type == 2 }">
								<tr>
									<td>${t.time }:00</td>
									<td>
										<input type="text" name="jprice" value="${t.price }"  class="col-xs-10 col-sm-5" required/>&nbsp;
										<select name="jspaceId"  class="bankSelected" required >
											<option value="0" <c:if test="${t.space_id == 0 }">selected="selected"</c:if> >休息</option>
											<option value="1" <c:if test="${t.space_id == 1 }">selected="selected"</c:if> >值班</option>
										</select>
										<input type="hidden" name="jid" value="${t.id }">
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
					</table>
				</td>
			</tr>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<c:if test="${empty check }">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
					</c:if>
					<button class="btn btn-sm btn-success" type="button" onclick="location='coach_list.do'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
