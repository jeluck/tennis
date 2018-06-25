<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统配置</title>
<jsp:include page="../init.jsp" />
<link rel="stylesheet" href="../css/admin.css" />
<script src="../js/admin_common.js"></script>
<script src="../../js/commonwealProject.js"></script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">系统配置管理</li>
			<li class="active">添加系统配置项目</li>
		</ul>
	</div>
	<div class="page_detail_view" style="width: 920px;">
		<div>
			<form action="editsystemConfig.do" id="add_commonwealprojectForm"
				method="post" >
				<table class="table table-striped table-bordered"
					style="text-align: center;">
					<tr>
						<td colspan="2" style="text-align: center; font-size: 20px;">修改系统配置</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">名称</td>
						<td style="text-align: left;">
							<input value = "${s.id}" type="hidden" name="id" />
							<input style="width: 400px;" value="${s.configName}" type="text" name="configName" id="configName" required=""/>
						</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">键</td>
						<td style="text-align: left;">
							<input style="width: 400px;" value="${s.key}" type="text" name="key" id="key" required=""/>
						</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">值</td>
						<td style="text-align: left;">
							<input style="width: 400px;" value="${s.value}" type="text" name="value" id="value" required=""/>
						</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">备注</td>
						<td style="text-align: left;">
							<textarea id="remark" wrap="off" name="remark"  style="width:100%;height:350px" maxlength="1232896" >${s.remark}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit"
							class="btn btn-sm btn-success" value=" 修改系统配置" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>