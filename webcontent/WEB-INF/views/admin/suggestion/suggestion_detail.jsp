<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>意见箱</title>
<jsp:include page="../init.jsp" />
<link rel="stylesheet" href="../css/admin.css" />
<script src="../js/admin_common.js"></script>

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
			<li class="active">意见箱管理</li>
			<li class="active">意见箱内容</li>
		</ul>
	</div>
	<div class="page_detail_view" style="width: 920px;">
		<div>
			<form action="edittest.do" id="add_commonwealprojectForm"
				method="post" >
				<table class="table table-striped table-bordered"
					style="text-align: center;">
					<tr>
						<td colspan="2" style="text-align: center; font-size: 20px;">意见箱</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">用户</td>
						<td style="text-align: left;">
							${suggestion.phone }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">意见</td>
						<td style="text-align: left;">
						${suggestion.detailcontent }
						</td>
					</tr>
					<tr>
						<td colspan="2"><input onclick="location='suggestionlist.do'"
							class="btn btn-sm btn-success" value="返回" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>