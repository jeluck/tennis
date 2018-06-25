<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加红包</title>
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
			<li class="active">教练管理</li>
			<li class="active">添加教练带人</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加教练带人 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addcoach_teach.do"  
		  method="post" onsubmit="return checkForm()">
		  <input name="coach_id" type="hidden" value="${coachId }">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >
								人数：
							</td>
							<td >&nbsp;&nbsp;
<!-- 								<select  id="people_num" name="people_num"  class="bankSelected" required > -->
<!-- 									<option value="1" >一对一</option> -->
<!-- 									<option value="2" >一对二</option> -->
<!-- 									<option value="3" >一对三</option> -->
<!-- 									<option value="4" >一对四</option> -->
<!-- 								</select> -->
<!-- 								<input type=""> -->
								
									<input class="lz-right" type="tel"  name="people_num"  style="IME-MODE: disabled; width: 360px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                							onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="2"  placeholder="请输入人数" required >
							</td>
						</tr>
						<tr >
							<td width="120px" >
								价格：
							</td>
							<td >
								<div class="col-sm-9">
									<input class="lz-right" type="tel"  name="price"  style="IME-MODE: disabled; width: 360px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                							onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="6"  placeholder="请输入金额" required >
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
					<button class="btn btn-sm btn-success" type="button" onclick="location='coach_teach_list.do?coachId=${coachId}'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
