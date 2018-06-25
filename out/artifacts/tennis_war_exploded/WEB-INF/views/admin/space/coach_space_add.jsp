<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">

function checkForm()
{
	return true;
}
function getSpace(id){
	var pdId = $("#"+id).val();
	var spaceName = $("#spaceName");
	var info = util.POST("/pgm/spaceName.do",{"pdId":pdId});
	var str ='<option value="">请选择场馆</option>';
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.id+">"+value.name+"</option>";
    });  
	spaceName.html(str);
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
			<li class="active">添加场地</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加场地 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="add_space_coach.do"  
		  method="post" onsubmit="return checkForm()">
		<input type="hidden" name="oid" value="${oid }">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">

						<tr >
							<td width="100px" >
								场馆名称：
							</td>
							<td >
								<select id="pdId"  name="pdId"  class="bankSelected" onchange="getSpace('pdId')" style="width: 120px;" required >
									<option value="" >请选择场馆</option>
									<c:forEach items="${p}" var="o" varStatus="v">
											<option value=${o.id } >${o.name }</option>
									</c:forEach>
								</select><span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								场地名称：
							</td>
							<td >
								 <select  id="spaceName"  name="spaceId"   class="bankSelected" required >
									<option value="0">请选择场地</option>
								 </select><span style="color: red;">*</span>
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
