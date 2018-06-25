<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训/课程列表</title>
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
			<li class="active">培训/课程管理</li>
			<li class="active">培训/课程列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>培训/课程列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="course_list.do;"
			  method="post">
			<label>标题：</label><input type="text" name="title" style="width: 110px; height: 25px;" value="${course.title }"  />
			&nbsp;
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<a class="btn btn-sm btn-success" onclick="location.href = 'course_list.do?pagenumber=1';">[ 取消查询 ]</a></span>
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
							<th>类型</th>
							<th>设备类型</th>
							<th>图片一</th>
							<th>图片二</th>
							<th>图片三</th>
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
								<td>
									<c:if test="${o.type == 1}">
										课程
									</c:if>
									<c:if test="${o.type == 2}">
										培训
									</c:if>
								</td>
								<td>	
									<c:if test="${o.authod_type == 1}">
										用户
									</c:if>
									<c:if test="${o.authod_type == 2}">
										平台
									</c:if>
									<c:if test="${o.authod_type == 3}">
										场管理者
									</c:if>
								</td>
								<td><img src="/${o.image1}" style="width: 240px; height: 60px;" /></td>
								<td><img src="/${o.image2}" style="width: 240px; height: 60px;" /></td>
								<td><img src="/${o.image3}" style="width: 240px; height: 60px;" /></td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
											onclick="location='tocourse_edit.do?oid=${o.id }&check=check'">
											<i class=" icon-eye-open bigger-120">查看</i>
										</button>
										<button class="btn btn-xs btn-info"
											onclick="location='tocourse_edit.do?oid=${o.id }'">
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
	location.href = "course_list.do?&pageNumber="+pageNumber;
}

</script>
	<script>
paging($('.pagination')[0],'pagination','course_list.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</body>
</html>