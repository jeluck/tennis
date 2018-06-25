<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<style type="text/css">
	li{
	list-style: none;
	}
</style>
<script type="text/javascript">
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#des', {
		themeType : 'simple',
		uploadJson : 'js/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : 'js/kindeditor/jsp/file_manager_json.jsp',
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
			<li class="active">俱乐部管理</li>
			<li class="active">俱乐部首页管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>俱乐部首页管理 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addOrUpdateClub_index.do"  
		  method="post" enctype="multipart/form-data" onsubmit="return checkForm()" >
		  <c:if test="${not empty o.img}">
		   <input type="hidden"   name="id" value="${o.id }" />
		   </c:if>
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="70%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
<!-- 						<tr > -->
<!-- 							<td > -->
<!-- 								名称：<span style="color: red;">*</span> -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<input type="text" -->
<%-- 										   name="name" style="width: 360px;" value="${o.name }" placeholder="请输入俱乐部名称" --%>
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr >
							<td >
								图片：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="hidden" name="imgSrc" value="${o.img }" />
									<input  type="file"  name="img"
										   class="col-xs-10 col-sm-5" <c:if test="${empty o.img }"> required </c:if> />
								</div>
							</td>
						</tr>
						<tr >
							<td  >
								描述：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<textarea name="des" id="des" 
										style="height: 370px; width: 600px;" >${o.des }</textarea>
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
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
			
		</table>
	</form>

</body>
</html>
