<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/webcs.css" />
<title>关于我们</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('#content', {
			themeType : 'simple',
			uploadJson : '../js/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '../js/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true
		});
	});
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

		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">宣传管理</li>
			<li class="active">关于我们</li>
		</ul>
	</div>
	<div class="page-header"
		style="width: 270px; margin: 25px 8px 0px 60px;">
		<h1>关于我们</h1>
	</div>
	<div class="row" style="width: 900px; margin: 15px 8px 0px 25px;">
		<div class="col-xs-12">
			<form class="form-horizontal" action="company_submit.do" role="form"
				method="post">
				<div class="space-4"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						style="width: 4%"> </label>
					<div class="col-sm-9">
<%-- 						<input type="hidden" value="${company_info.id}" name="id" />  --%>
						<input type="hidden" value="${company_info.id}" name="id" /> 
							
						<input type="hidden" value="company_info" name="fieldName" />
						<textarea name="company_info" id="content"
							style="height: 600px; width: 900px;">${company_info.company_info}</textarea>
						<div class="space-4"></div>
						<div class="space-4"></div>
						<div class="col-md-offset-3 col-md-9 text-center">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-2"></label>
							<button class="btn btn-sm btn-success" type="submit">
								<i class="icon-ok bigger-90"></i>[ 确定 ]
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn btn-sm" type="reset">
								<i class="icon-undo bigger-90"></i>[ 重置 ]
							</button>
						</div>
			</form>
		</div>
	</div>
</body>
</html>