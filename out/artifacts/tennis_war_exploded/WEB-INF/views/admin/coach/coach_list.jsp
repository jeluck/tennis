<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/some_use.js?c=<%=new Date()%>"></script>
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
			<li class="active">教练管理</li>
			<li class="active">教练列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>教练列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="coach_list.do;"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<label>教练名字：</label><input type="text" name="name" style="width: 110px; height: 25px;" value="${company_name}" />
			&nbsp;
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<a class="btn btn-sm btn-success" onclick="location.href = 'coach_list.do?pagenumber=1';">[ 取消查询 ]</a></span>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>名字</th>
							<th>电话</th>
							<th>年龄</th>
							<th>教学年限</th>
							<th>认证状态</th>
<!-- 							<th>教学资深程度</th> -->
							<th>教练类型</th>
							<th style="width:380px;">操作</th>	
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
								<td>${o.name}</td>
								<td>${o.phone}</td>
								<td>${o.age}</td>
								<td>${o.teaching}</td>
								<td>
									<c:if test="${o.verified == 0}">
										未认证				
									</c:if>
									<c:if test="${o.verified == 2}">
										认证中				
									</c:if>
									<c:if test="${o.verified == 1}">
										已认证
									</c:if>
								</td>
<%-- 								<td>${o.teachtype }</td> --%>
								<td>
									<c:if test="${o.coachType == 1}">
										自由教练			
									</c:if>
									<c:if test="${o.coachType == 2}">
										驻场教练				
									</c:if>
									<c:if test="${o.coachType == 3}">
										场馆运营者
									</c:if>
								</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
											onclick="location='toedit_coach.do?oid=${o.id }&check=check'">
											<i class=" icon-eye-open bigger-120">查看</i>
										</button>
<!-- 										教练类型为3的教练,现在也不会出现查看场地按钮(edit by lxc)2015-12-15 -->
<%-- 										<c:if test="${o.coachType == 3 }"> --%>
<!-- 											<button class="btn btn-xs btn-info" -->
<%-- 												onclick="location='coach_space_list.do?oid=${o.id }'"> --%>
<!-- 												<i class=" icon-eye-open bigger-120">查看场地</i> -->
<!-- 											</button> -->
<%-- 										</c:if> --%>
										<c:if test="${o.coachType == 2 }">
											<button class="btn btn-xs btn-info"
												onclick="location='toeditspace_time_money.do?coachId=${o.id }&pdId=${o.playground_id }'">
												<i class=" icon-eye-open bigger-120">修改时间</i>
											</button>
											
											<button class="btn btn-xs btn-info"
												onclick="location='toedit_coach.do?oid=${o.id }'">
												<i class=" icon-edit bigger-120">修改</i>
											</button>
											
											<button class="btn btn-xs btn-info"
												onclick="location='coach_teach_list.do?coachId=${o.id }'">
												<i class=" icon-edit bigger-120">一对几</i>
											</button>
										</c:if>
<!-- 										<button class="btn btn-xs btn-info" -->
<%-- 											onclick="location='toedit_coach.do?oid=${o.id }'"> --%>
<!-- 											<i class=" icon-edit bigger-120">修改</i> -->
<!-- 										</button> -->
										<c:if test="${not empty manager }">
											<button class="btn btn-xs btn-info" onclick="pushcoach('${o.id}')">
												<c:if test="${o.stick==0}">
													<i class=" icon-edit bigger-120" id="pushobj_${o.id}">推荐</i>
												</c:if>
												<c:if test="${o.stick==1}">
													<i class=" icon-edit bigger-120" id="pushobj_${o.id}">取消推荐</i>
												</c:if>
											</button>
										</c:if>
										<c:if test="${o.coachType == 1 }">
											<button class="btn btn-xs btn-info" onclick="is_reservecoach('${o.id}',this)">
												<c:if test="${o.is_reserve==1}">
													<i class=" icon-edit bigger-120">可预定场地</i>
												</c:if>
												<c:if test="${o.is_reserve==0}">
													<i class=" icon-edit bigger-120">不可预定场地</i>
												</c:if>
											</button>
											<button class="btn btn-xs btn-info" onclick="reserve_mecoach('${o.id}',this)">
												<c:if test="${o.reserve_me==1}">
													<i class=" icon-edit bigger-120">可预约我</i>
												</c:if>
												<c:if test="${o.reserve_me==0}">
													<i class=" icon-edit bigger-120">不可预约我</i>
												</c:if>
											</button>
										</c:if>
										<input type="hidden" value="${o.stick}" id="pushobj_stick_${o.id}" />
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
	location.href = "coach_list.do?&pageNumber="+pageNumber;
}

</script>
	<script>
paging($('.pagination')[0],'pagination','coach_list.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</body>
</html>