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
			<li class="active">修改品牌</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改品牌 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="editbrandInfo.do"  
		  method="post" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								品牌名称：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="brand_name" style="width: 360px;" placeholder="请输入品牌名称"
										   class="col-xs-10 col-sm-5" required value="${o.brand_name}" />
									<input type="hidden" value="${o.id}" name="id" />	
									<input type="hidden" value="${o.create_time}" name="create_time" />
								</div>
							</td>
						</tr>

						<tr>
							<td>
								品牌介绍：
							</td>
							<td>
								<div class="col-sm-9">
									<textarea rows="6" cols="10" xclass="col-xs-10 col-sm-5" name="introduction" style="width: 360px;" placeholder="请输入介绍" required >${o.introduction}</textarea>
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								状态：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="flag" value="0" <c:if test="${o.flag == '0' }"  >checked=checked</c:if>>可用
									<input type="radio" name="flag" value="1" <c:if test="${o.flag == '1' }"  >checked=checked</c:if>>停用
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
					<button class="btn btn-sm btn-success" type="button" onclick="location='brandInfolist.do'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">
				</td>
			</tr>
		</table>
	</form>




</body>
</html>
