<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包列表信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
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
			<li class="active">红包列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>红包列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="redbag_list.do;"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<label>红包类型：</label>
			<select  id="reward_type" name="reward_type" style="width: 160px;"  class="bankSelected" >
				<option value="0" >请选择红包类型</option>
				<option value="1" <c:if test="${reward_type == 1 }"> selected="selected" </c:if>  >代金券</option>
				<option value="2" <c:if test="${reward_type == 2 }"> selected="selected" </c:if>  >现金</option>
				<option value="3" <c:if test="${reward_type == 3 }"> selected="selected" </c:if>  >积分</option>
			</select>
			&nbsp;
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<a class="btn btn-sm btn-success" onclick="location.href = 'redbag_list.do?pagenumber=1';">[ 取消查询 ]</a></span>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>状态</th>
							<th>红包类型</th>
							<th>数量或者金额</th>
							<th>作用或者用途</th>
							<th>开始有效期</th>
							<th>结束有效期</th>
							<th style="width:150px;">操作</th>	
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
						<c:forEach items="${data_page.dataList }" var="o" varStatus="v">
							<tr>
								<td>${v.index+1}</td>
								<td>
									<c:if test="${o.status == 1}">
										有效
									</c:if>
									<c:if test="${o.status == 0}">
										无效
									</c:if>
								</td>
								<td>
									<c:if test="${o.reward_type == 1}">
										代金卷
									</c:if>
									<c:if test="${o.reward_type == 2}">
										现金
									</c:if>
									<c:if test="${o.reward_type == 3}">
										积分
									</c:if>
								</td>
								<td>${o.quantity}</td>
								<td>
									<c:if test="${o.purpose == 1}">
										支付后随机奖励
									</c:if>
									<c:if test="${o.purpose == 2}">
										支付后固定奖励积分
									</c:if>
									<c:if test="${o.purpose == 3}">
										登录奖励
									</c:if>
									<c:if test="${o.purpose == 4}">
										评论奖励
									</c:if>
								</td>
								<td>${o.start_time}</td>
								<td>${o.end_time}</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
											onclick="location='toredbag_edit.do?oid=${o.id }&check=check'">
											<i class=" icon-eye-open bigger-120">查看</i>
										</button>
										<button class="btn btn-xs btn-info"
											onclick="location='toredbag_edit.do?oid=${o.id }'">
											<i class=" icon-edit bigger-120">修改</i>
										</button>
									</div>
								</td>
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
		<c:if test="${havadata }">
		<div class="modal-footer no-margin-top">
			<ul class="pagination pull-right no-margin">

			</ul>
		</div>
		</c:if>
	</div>
<script type="text/javascript">
var index;
//页面跳转
function go_page(pageNumber){
	location.href = "redbag_list.do?&pageNumber="+pageNumber;
}

</script>
	<script>
paging($('.pagination')[0],'pagination','redbag_list.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</body>
</html>