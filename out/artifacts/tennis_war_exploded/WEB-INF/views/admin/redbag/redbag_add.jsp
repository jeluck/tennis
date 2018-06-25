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
			<li class="active">红包管理</li>
			<li class="active">添加红包</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加红包 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addredbag.do"  
		  method="post" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >
								红包类型：
							</td>
							<td >&nbsp;&nbsp;
								<select  id="reward_type" name="reward_type"  class="bankSelected" required >
									<option value="1" >代金券</option>
									<option value="2" >现金</option>
									<option value="3" >积分</option>
								</select>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								数量或者金额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="quantity" style="width: 360px;" placeholder="请输入数量或者金额"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								作用或者用途：
							</td>
							<td >&nbsp;&nbsp;
								<select  id="purpose" name="purpose"  class="bankSelected" required >
									<option value="1" >支付后随机奖励</option>
									<option value="2" >支付后固定奖励积分</option>
									<option value="3" >登录奖励</option>
									<option value="4" >评论奖励</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="120px" >
								有效期：
							</td>
							<td>
								<div class="col-sm-9" >
									<input type="text"
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" name="start_time" />&nbsp;至&nbsp;
									<input
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" type="text" name="end_time" />
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
