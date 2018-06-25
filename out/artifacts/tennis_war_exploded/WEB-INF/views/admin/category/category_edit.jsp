<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改品牌</title>
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
			<li class="active">修改分类</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改分类<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<form class="form-horizontal" id="form" method="post" action="editCategory.do" role="form"  
			enctype="multipart/form-data" >
		<input type="hidden" value="${c.id}" name="id" >
		<input type="hidden" value="${c.imgurl }" name="imgfile">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				or="form-field-1"> 商品分类： </label>
			<div class="col-sm-9">
				<input type="text" 	name="category_name" style="width: 360px;" placeholder="请输入分类名称" value="${c.category_name }" required>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				or="form-field-1"> 分类图标： </label>
			<div class="col-sm-9">
			<input type="file" name="img" >
			</div>
		</div>
		<div class="space-4"></div>
		<div class="space-4"></div>
		<div class="space-4"></div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" or="form-field-1"></label>
			<div class="col-sm-9">
				<div class="col-md-offset-3 col-md-9">
					<button class="btn btn-sm btn-success" type="submit">
						<i class="icon-ok bigger-90"></i>[ 确定 ]
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn btn-sm" type="reset" onclick="location='pagecategory.do?pagenumber=${pagenumber}'">
						<i class="icon-undo bigger-90"></i>[ 返回  ]
					</button>
				</div>
			</div>
		</div>
	</form>


</body>
</html>
