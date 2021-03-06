<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改或者查看会员等级信息</title>
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
			<li class="active">会员入会管理</li>
			<li class="active">
				<c:if test="${empty check }">
					修改会员等级信息
				</c:if>
				<c:if test="${!empty check }">
					查看会员等级信息
				</c:if>
			</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			<c:if test="${empty check }">
				修改会员等级信息
			</c:if>
			<c:if test="${!empty check }">
				查看会员等级信息
			</c:if>
		 	<span id="time" style="font-size:20px;float: right"></span>
		</h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="user_vip_edit.do"  
		  method="post" enctype="multipart/form-data"  onsubmit="return checkForm()">
		<input type="hidden" value="${o.id}" name="id" />	
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >
								等级名称：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
											   name="name" style="width: 360px;" placeholder="请输入等级名称"
											   class="col-xs-10 col-sm-5" value="${o.name }" required  />	
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								价格：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="price" style="width: 360px;" placeholder="请输入价格"
										   class="col-xs-10 col-sm-5" value="${o.price }" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								图片：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="hidden" name="img1" value="${o.img }">
									<input type="file" class="input_text" name="vipImg" style="width: 200px;" />
									&nbsp;&nbsp;<span id="tips">*图片</span>
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
					<button class="btn btn-sm btn-success" type="button" onclick="location.href = 'user_vip.do?pagenumber=1';" > [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>




</body>

</html>
