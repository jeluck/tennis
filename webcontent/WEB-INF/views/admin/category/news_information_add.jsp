<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻资讯</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">

function checkForm()
{
	return true;
}
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

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">分类管理</li>
			<li class="active">新闻资讯添加</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>新闻资讯添加 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addInformation.do"  
		  method="post" enctype="multipart/form-data"  xonsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td >
								标题：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="title" style="width: 360px;" placeholder="请输入标题"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td >
								新闻资讯类型：
							</td>
							<td >&nbsp;&nbsp;
								<select  name="information_categoryinfo_id.id" style="width: 120px;" class="bankSelected" required >
									<c:forEach items="${category }" var="o">
										<option value="${o.id}" >${o.category_name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						
						<tr>
							<td  >
								内容：
							</td>
							<td>
<!-- 								<div class="col-sm-9"> -->
<!-- 									<input type="text" -->
<!-- 										   name="content" style="width: 360px;" placeholder="请输入内容" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
<!-- 								</div> -->
								
								<textarea name="content" id="content"
									style="height: 200px; width:360px;" ></textarea>
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
