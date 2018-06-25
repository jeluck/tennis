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
	var price = document.getElementById('price').value;
    if(price<=0){
    	alert("场地单价必须大于0");
    	return false;
    }
	return true;
}
function clearNoNum(obj){

	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符

	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是

	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的

	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数

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

	<form id="QueryForm" name="form1" class="form-inline" action="add_space.do"  
		  method="post" onsubmit="return checkForm()">
		  <c:if test="${empty is_play }">
		  	<input type="hidden" name="belongto" value="1"/>
		  </c:if>
		  <c:if test="${not empty is_play }">
		  	<input type="hidden" name="belongto" value="0"/>
		  </c:if>
		  <input type="hidden" name="playground_id.id" value="${oid }">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								场地名称：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="name" style="width: 360px;" placeholder="请输入场地名称"
										   class="col-xs-10 col-sm-5" required  /> 
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								场地编号：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="spaceNo" style="width: 360px;" placeholder="请输入场地编号"
										   class="col-xs-10 col-sm-5" required  /> 
								</div>
							</td>
						</tr>
						<tr style="display:none;">
							<td width="100px" >
								驻场教练：
							</td>
							<td >
								<div class="col-sm-9">
									<select name="coach_id.id" style="width: 360px;" class="col-xs-10 col-sm-5" >
										<c:forEach items="${coachList }" var="coach">
											<option value="${coach.id }">${coach.name }</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								场地类型：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="spacetype" style="width: 360px;" class="col-xs-10 col-sm-5" required >
										<option value="红土">红土</option>
										<option value="硬地">硬地</option>
										<option value="草地">草地</option>
										<option value="地毯">地毯</option>
									</select>
								</div> 
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								球场类型 ：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="court_type" style="width: 360px;" class="col-xs-10 col-sm-5" required >
										<option value="普通">普通</option>
										<option value="智能">智能</option>
									</select>
								</div> 
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								空间类型：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="in_out" style="width: 360px;" class="col-xs-10 col-sm-5" required >
										<option value="室内">室内</option>
										<option value="室外">室外</option>
									</select>
								</div> 
							</td>
						</tr>  
						<tr >
							<td width="100px" >
								场地单价：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" id="price"
										   name="price" style="width: 360px;" placeholder="请输入场地单价"
										   class="col-xs-10 col-sm-5" onkeyup="clearNoNum(this)" required  /> 
								</div>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								会员价： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<input type="text" -->
<!-- 										   name="vip_price" style="width: 360px;" placeholder="请输入场地会员价" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
<!-- 								</div><span style="color: red;">*</span> -->
<!-- 							</td> -->
<!-- 						</tr> -->
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
<script>		
function getCity(id){
	var province = $("#"+id).val();
	var user_city = $("#user_city");
	var info = util.POST("city.do",{"province":province});
	var str ='<option value="0">请选择</option>';
	
	var user_area = $("#user_area");
	user_area.html(str);
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_city.html(str);
}

function getArea(id){
	var city = $("#"+id).val();
	var user_area = $("#user_area");
	var info = util.POST("area.do",{"city":city});
	var str ='<option value="0">请选择</option>';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_area.html(str);
}
</script>
</html>
