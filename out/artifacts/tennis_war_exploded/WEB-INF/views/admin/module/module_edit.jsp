<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改教练信息</title>
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
			<li class="active">首页六个模版管理</li>
			<c:if test="${empty check }">
				<li class="active">修改模版</li>
			</c:if>
			<c:if test="${!empty check }">
				<li class="active">查看模版</li>
			</c:if>
		</ul>
	</div>
	<div class="page-header">
		<h1>
		<c:if test="${empty check }">修改模版</c:if>
		<c:if test="${!empty check }">查看模版</c:if>
		<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="edit_module.do"  
		  method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		<input type="hidden" value="${o.id}" name="id" />
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						
						<tr>
							<td width="120px">
								图片：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="hidden" name="imgfile" value="${o.img }">
									<input type="file" class="input_text" name="img" style="width: 200px;" />
									&nbsp;&nbsp;<span id="tips">*图片</span>
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								访问地址：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="address" style="width: 360px;" placeholder="请输入访问地址"
										   class="col-xs-10 col-sm-5" required  value="${o.address}"/>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								状态：
							</td>
							<td >&nbsp;&nbsp;
								<select  name="status" style="width: 120px;" class="bankSelected" required >
									<option value="1" <c:if test="${o.status == 1 }"> selected="selected" </c:if> >首页显示</option>
									<option value="2" <c:if test="${o.status == 2 }"> selected="selected" </c:if> >首页不显示</option>
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
					<c:if test="${empty check }">
						<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
						</button>
					</c:if>
					<button class="btn btn-sm btn-success" type="button" onclick="location='indexMbList.do'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
