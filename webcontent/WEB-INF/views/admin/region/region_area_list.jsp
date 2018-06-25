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
			<li class="active">地域管理</li>
			<li class="active">区县列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>区县列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="coach_list.do;"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
<%-- 			<label>区县名字：</label><input type="text" name="name" style="width: 110px; height: 25px;" value="${company_name}" /> --%>
<!-- 			&nbsp; -->
<!-- 			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]"> -->
<!-- 			<a class="btn btn-sm btn-success" onclick="location.href = 'playground_list.do?pagenumber=1';">[ 取消查询 ]</a></span> -->
			<a class="btn btn-sm btn-success" onclick="location.href = 'to_add_region_area.do?cityid=${cityid}&backurl=region_area_list.do?cityid=${cityid}&pageNumber=${areaList.currentPage}';">[ 添加区]</a>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>区县名称</th>
							<th>区号</th>
							<th>状态</th>
							<th style="width:150px;">操作</th>	
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty areaList.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(areaList.currentPage-1)*10 }" />
						<c:forEach items="${areaList.dataList }" var="o" varStatus="v">
							<tr>
								<td>${v.index+1}</td>
								<td>${o.region_name}</td>
								<td>${o.region_code}</td>
								<td>
									<c:if test="${o.status == 1}" var="lock">热门</c:if>
									<c:if test="${!lock }">开放</c:if>
								</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
											onclick="location='toregion_area_edit.do?oid=${o.region_id }&check=check&cityid=${cityid }&backurl=region_area_list.do'">
											<i class=" icon-eye-open bigger-120">查看</i>
										</button>
										<button class="btn btn-xs btn-info"
											onclick="location='toregion_area_edit.do?oid=${o.region_id }&cityid=${cityid }&backurl=region_area_list.do'">
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
	location.href = "region_area_list.do?pageNumber="+pageNumber;
}

</script>
	<script>
paging($('.pagination')[0],'pagination','region_area_list.do?cityid=${cityid}&pageNumber=',${areaList.rowCount},${areaList.pageSize},${areaList.currentPage});
	</script>
</body>
</html>