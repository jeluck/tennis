<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加红俱乐部</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">

function checkForm()
{
	return true;
}
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
			<li class="active">
				<c:if test="${empty check && empty o.id}">
					添加俱乐部活动集锦
				</c:if>
				<c:if test="${empty check && not empty o.id}">
					修改俱乐部活动集锦
				</c:if>
				<c:if test="${not empty check }">
					查看俱乐部活动集锦
				</c:if>
			</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			<c:if test="${empty check && empty o.id}">
				添加俱乐部活动集锦
			</c:if>
			<c:if test="${empty check && not empty o.id}">
				修改俱乐部活动集锦
			</c:if>
			<c:if test="${not empty check }">
				查看俱乐部活动集锦
			</c:if>
			<span id="time" style="font-size:20px;float: right"></span>
		</h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addOrUpdate_clubGallery.do"  
		  method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		  <c:if test="${not empty o.id }">
			  <input type="hidden" name="id" value="${o.id }"/>
		  </c:if>
		  <input type="hidden" name="clubId" value="${clubId }"/>
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td  width="40%" >
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td >
								图片：<span style="color: red;">*</span>
							</td>
							<td>
								<div class="col-sm-9" style="width: 600px;">
									<input type="hidden" name="imgSrc" value="${o.img }" />
									<input  type="file"  name="img"
										   class="col-xs-10 col-sm-5" <c:if test="${empty o.img }"> required </c:if> />
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
					<button class="btn btn-sm btn-success" type="button" onclick="location='clubGalleryList.do?oid=${clubId }'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
