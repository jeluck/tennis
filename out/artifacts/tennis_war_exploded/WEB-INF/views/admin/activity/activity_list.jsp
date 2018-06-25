<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
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
			<li class="active">活动管理</li>
			<li class="active">活动列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>活动列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="coach_list.do;"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<label>活动标题：</label><input type="text" name="name" style="width: 110px; height: 25px;" value="${company_name}" />
			&nbsp;
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<a class="btn btn-sm btn-success" onclick="location.href = 'playground_list.do?pagenumber=1';">[ 取消查询 ]</a></span>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>标题</th>
							<th>内容</th>
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
								<td>${o.title}</td>
								<td>${o.content}</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
											onclick="location='toedit_activity.do?oid=${o.id }&check=check'">
											<i class=" icon-eye-open bigger-120">查看</i>
										</button>
										<button class="btn btn-xs btn-info"
											onclick="location='toedit_activity.do?oid=${o.id }'">
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
	location.href = "playground_list.do?&pageNumber="+pageNumber;
}

</script>
	<script>
paging($('.pagination')[0],'pagination','playground_list.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</body>
</html>