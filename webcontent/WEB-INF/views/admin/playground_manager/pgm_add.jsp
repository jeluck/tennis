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
	if(checkPhone()>0 && checkName()>0){
		return true;
	}else{
		return false;
	}
}

function checkPhone(){
	var info = util.POST("/admin/saveOrUpdateCheckMobile.do",{"mobile":$("#mobile").val(),"id":0});
	if(info<=0){
		$("#erroeMobile").html("此手机已被使用");
		return 0;
	}
	$("#erroeMobile").html("");
	return 1;
}

function checkName(){
	var info = util.POST("/admin/saveOrUpdateCheckName.do",{"name":$("#name").val(),"id":0});
	if(info<=0){
		$("#erroeName").html("此用户名已被使用");
		return 0;
	}
	$("#erroeName").html("");
	return 1;
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
			<li class="active">添加场馆运营者</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加场馆运营者 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="add_playground_manager.do"  
		  method="post" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="70%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								用户名：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="username" style="width: 360px;" placeholder="请输入用户名"
										   class="col-xs-10 col-sm-5" required  />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								登录名称：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="usercode" style="width: 360px;" id="name" onchange="checkName()"  placeholder="请输入登录名称"
										   class="col-xs-10 col-sm-5" required  /><span id="erroeName" style="color: red;"></span>
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								用户密码：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="password"
										   name="password" style="width: 360px;" placeholder="请输入用户密码"
										   class="col-xs-10 col-sm-5" required  />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								手机：
							</td>
							<td >	
								<div class="col-sm-9">
									<input type="text"
										   name="mobile" style="width: 360px;" id="mobile" onchange="checkPhone()" placeholder="请输入手机"
										   class="col-xs-10 col-sm-5" required  /><span id="erroeMobile" style="color: red;"></span>
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								状态：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="is_active" value="1" checked>可用
									<input type="radio" name="is_active" value="0">停用
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
