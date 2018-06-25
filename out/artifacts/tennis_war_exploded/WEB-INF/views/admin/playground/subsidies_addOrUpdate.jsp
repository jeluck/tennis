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
			<li class="active">修改补贴</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改补贴<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addOrUpdate_subsidies.do"  
		  method="post"  onsubmit="return checkForm()">
		<input type="hidden" name="id" value="${o.id }">
		<input type="hidden" name="oid" value="${oid }">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
					
						<tr >
							<td width="150px" >
								场馆名称：
							</td>
							<td >
								<div class="col-sm-9">
									<c:forEach items="${plist }" var="p" >
										<c:if test="${p.id == o.zhe_id}">
											<input type="text"  class="col-xs-10 col-sm-5" value="${p.name }"
									 			style="width: 180px;" disabled="disabled" />
										</c:if>
									</c:forEach>
								</div>
							</td>
						</tr>
											
						<tr >
							<td width="150px" >
								补贴比例：单位：%
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" name="money"  class="col-xs-10 col-sm-5" value=" <fmt:formatNumber value="${o.money*100 }" pattern="#0.#"/>"
									 style="width: 180px;" required placeholder="请填写补贴金(单位：%)" />
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="150px" >
								有效交易额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"  class="col-xs-10 col-sm-5" value="${o.jmoney}"
									 style="width: 180px;" disabled="disabled" />
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="150px" >
								补贴额：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"  class="col-xs-10 col-sm-5" value="${o.subsidies_money}"
									 style="width: 180px;"  disabled="disabled"  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="150px" >
								年份：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" name="year" class="col-xs-10 col-sm-5" value="${o.year}"
									 style="width: 180px;"  disabled="disabled"  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="150px" >
								月份：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" name="month"  class="col-xs-10 col-sm-5" value="${o.month}"
									 style="width: 180px;"  disabled="disabled"  />
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="150px" >
								结算状态：
							</td>
							<td >
								<div class="col-sm-9">
									<c:if test="${o.status == 1}">
										未结算
									</c:if>
									<c:if test="${o.status == 0}">
										已结算
									</c:if>
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
					<a class="btn btn-sm btn-success" onclick="location.href = 'sub_play_list.do?pagenumber=1&oid=${oid}';">[ 返回 ]</a></span>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>

</body>
</html>
