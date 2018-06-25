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
			<li class="active">管理</li>
			<li class="active">修改场地时间价格</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改场地时间价格<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="add_space_time_price.do"  
		  method="post" onsubmit="return checkForm()">
		  <input type="hidden" name="playgroundId" value="${oid }">
		  <input type="hidden" name="spaceId" value="${spaceId }">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr>
							<th colspan="2">工作日</th>
						</tr>
						<c:forEach var="gTimePrice" items="${tiemMoenyList }">
							<c:if test="${gTimePrice.time_type == 1 }">
								<tr>
									<td>${gTimePrice.hour }:00-${gTimePrice.hour+1 }:00</td>
									<td>
										<input type="hidden" name="gid" value="${gTimePrice.id }">
										<input type="text" name="gprice" value="${gTimePrice.price }" onchange="gChange(this)" onkeyup="clearNoNum(this)"   class="col-xs-10 col-sm-5" required/>
										<select name="gmust">
											<option value="2" <c:if test="${gTimePrice.help_filed ==2 }">selected="selected"</c:if>>不需带教练</option>
											<option value="1" <c:if test="${gTimePrice.help_filed ==1 }">selected="selected"</c:if>>必需带教练</option>
										</select>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</td>
				<td width="40%" valign="top">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover" >
						<tr>
							<th colspan="2">周末</th>
						</tr>
						<c:forEach var="gTimePrice" items="${tiemMoenyList }">
							<c:if test="${gTimePrice.time_type == 2 }">
								<tr>
									<td>${gTimePrice.hour }:00-${gTimePrice.hour+1 }:00</td>
									<td>
										<input type="hidden" name="jid" value="${gTimePrice.id }">
										<input type="text" name="jprice" value="${gTimePrice.price }" onchange="gChange(this)"  onkeyup="clearNoNum(this)"  class="col-xs-10 col-sm-5" required/>
										<select name="jmust">
											<option value="2" <c:if test="${gTimePrice.help_filed ==2 }">selected="selected"</c:if>>不需带教练</option>
											<option value="1" <c:if test="${gTimePrice.help_filed ==1 }">selected="selected"</c:if>>必需带教练</option>
										</select>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
					</table>
				</td>
			</tr>

			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<c:if test="${empty is_edit }">
					<button id="space_time" class="btn btn-sm btn-success" type="submit" onclick="hiddenSubmit(this)"> [ 提交 ]
					</button>
					</c:if>
					<c:if test="${not empty is_edit }">
					<button id="space_time" class="btn btn-sm btn-success" type="button" onclick="hiddenButton(this)"> [ 修改 ]
					</button>
					</c:if>
					<button class="btn btn-sm btn-success" type="button" onclick="location='space_list.do?oid=${oid}'"> [ 返回 ]
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

function gChange(obj){
	var oSelect = obj.parentNode.getElementsByTagName('select')[0];
	
	if (obj.value == '0') {
		oSelect.value = '1';
	}
	
}
function clearNoNum(obj){

	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符

	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是

	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的

	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数

}
</script>
</html>
