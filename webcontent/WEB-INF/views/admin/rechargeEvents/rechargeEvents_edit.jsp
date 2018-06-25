<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改或者查看充值活动</title>
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
			<li class="active">充值活动管理</li>
			<li class="active">
				<c:if test="${empty check }">
					修改充值活动
				</c:if>
				<c:if test="${!empty check }">
					查看充值活动
				</c:if>
			</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			<c:if test="${empty check }">
				修改充值活动
			</c:if>
			<c:if test="${!empty check }">
				查看充值活动
			</c:if>
		 	<span id="time" style="font-size:20px;float: right"></span>
		</h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="rechargeEvents_edit.do"  
		  method="post" onsubmit="return checkForm()">
		<input type="hidden" value="${o.id}" name="id" />	
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >	
								充值金额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="recharge_money" value="${o.recharge_money }" style="width: 360px;" placeholder="请输入充值金额"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								赠送金额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="get_money" value="${o.get_money }" style="width: 360px;" placeholder="请输入赠送金额"
										   class="col-xs-10 col-sm-5" required  />
								</div>
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
										style="width: 150px;" name="begin_time" value="${o.begin_time }" required />&nbsp;至&nbsp;
									<input
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" type="text" name="end_time" value="${o.end_time }" required />
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
					<button class="btn btn-sm btn-success" type="button" onclick="location='rechargeEvents_list.do'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>




</body>

</html>
