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
			<li class="active">地域管理</li>
			<li class="active">修改</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="edit_region_area.do"  
		  method="post" onsubmit="return checkForm()">
		<input type="hidden" value="${o.region_id}" name="region_id" />	
		<input type="hidden" value="${cityid }" name="cityid">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								区县名称：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="region_name" style="width: 360px;" placeholder="请输入区县名称"
										   class="col-xs-10 col-sm-5" required  value="${o.region_name }"/>
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								区号：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="region_code" style="width: 360px;" placeholder="请输入区县区号"
										   class="col-xs-10 col-sm-5" required value="${o.region_code }" />
								</div>
							</td>
						</tr>
						<c:if test="${not empty edit}">
						<tr >
							<td width="100px" >
								状态：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="status" value="0" <c:if test="${o.status == 0}">checked="checked"</c:if> >开放
									<input type="radio" name="status" value="1" <c:if test="${o.status == 1}">checked="checked"</c:if> >热门
								</div>
							</td>
						</tr>
						</c:if>
						<tr >
							<td width="100px" >
								中文拼音：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="region_name_en" style="width: 360px;" placeholder="请输入中文拼音"
										   class="col-xs-10 col-sm-5" required value="${o.region_name_en }" />
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								拼音缩写：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="region_shortname_en" style="width: 360px;" placeholder="请输入中文拼音缩写"
										   class="col-xs-10 col-sm-5" required value="${o.region_shortname_en }" />
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
					<button class="btn btn-sm btn-success" type="button" onclick="location='${backurl}?cityid=${cityid }'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>




</body>
<script>		
function getCity(id){
	var province = $("#"+id).val();
	var user_city = $("#user_city");
	var info = util.POST("/city.do",{"province":province});
	var str ='<option value="0">请选择</option>';
	
	var user_area = $("#user_area");
	user_area.html(str);
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_city.html(str);
}

</script>
</html>
