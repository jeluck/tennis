<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加充值活动</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">

function checkForm()
{
	var recharge_money = document.getElementById('recharge_money').value;
    if(recharge_money<=0){
    	alert("充值金额必须大于0");
    	return false;
    }
    var get_money = document.getElementById('get_money').value;
    if(get_money<=0){
    	alert("赠送金额必须大于0");
    	return false;
    }
	if($("#begin_time").val()==null || $("#begin_time").val()==""){
		alert("请填写开始时间")
		return false;
	}
	if($("#end_time").val()==null || $("#end_time").val()==""){
		alert("请填写结束时间")
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
			<li class="active">充值活动管理</li>
			<li class="active">添加充值活动</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加充值活动 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

		<form id="QueryForm" name="form1" class="form-inline" action="add_rechargeEvents.do"  
		  method="post" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="70%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="140px" >	
								充值金额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" id="recharge_money"
										   name="recharge_money"  style="width: 360px;" placeholder="请输入充值金额"
										   class="col-xs-10 col-sm-5" required onkeyup="clearNoNum(this)" />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								赠送金额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" id="get_money"
										   name="get_money" style="width: 360px;" placeholder="请输入赠送金额"
										   class="col-xs-10 col-sm-5" required onkeyup="clearNoNum(this)" />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								会员特权：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="is_vip" value="1">是
									<input type="radio" name="is_vip" value="2" checked>否
								</div><span style="color: red;">*</span>
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
										style="width: 150px;" name="begin_time"  id="begin_time" />&nbsp;至&nbsp;
									<input
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" type="text" name="end_time" id="end_time"  /><span style="color: red;">*</span>
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
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
