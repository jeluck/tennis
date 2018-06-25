<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
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
<body >
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
			<li class="active">场馆批量添加补贴</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>场馆批量添加补贴<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="batch_add_playSub.do"  
		  method="post"  onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						
<!-- 						<tr> -->
<!-- 							<td width="150px" > -->
<!-- 								结束日期： -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<div class="col-sm-9" > -->
<!-- 									<input type="text" -->
<!-- 										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" -->
<%-- 										style="width: 150px;" value="${o.end_time }" name="end_time" required /> --%>
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						
						<tr >
							<td width="150px" >
								年份：
							</td>
							<td >
								<div class="col-sm-9">
									<select name="year"  class="col-xs-10 col-sm-5" required >
										<c:forEach begin="${year }" end="${year+10 }" var="o">
											<option value="${o }"  >${o }</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="150px" >
								月份：
							</td>
							<td >
								<div class="col-sm-9">
									<select name="month"  class="col-xs-10 col-sm-5" required >
										<c:forEach begin="1" end="12" var="o">
											<option value="${o }"  >${o }</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="150px" >
								补贴比例：单位：%
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" name="money"  class="col-xs-10 col-sm-5"
									 style="width: 180px;" required  />
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
