 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/some_use.js"></script>
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
			<li class="active">场馆管理</li>
			<li class="active">场馆列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>场馆管理列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="playground_list.do;"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<c:if test="${not empty update }">
				<input type="hidden" value="update" name="update">
			</c:if>
			<label>场馆名称：</label><input type="text" name="name" style="width: 110px; height: 25px;" value="${company_name}" />
			&nbsp;
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<c:if test="${not empty update }">
			<a class="btn btn-sm btn-success" onclick="location.href = 'playground_list.do?pagenumber=1&update=update';">[ 取消查询 ]</a></span>
			</c:if>
			<c:if test="${empty update }">
			<a class="btn btn-sm btn-success" onclick="location.href = 'playground_list.do?pagenumber=1';">[ 取消查询 ]</a></span>
			</c:if>
			<c:if test="${empty update }">
			<a class="btn btn-sm btn-success" onclick="location.href = 'toaddplayground.do';">[ 添加场馆 ]</a></span>
			</c:if>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>场馆名称</th>
							<th>具体地址</th>
							<th>电话</th>
							<th>预订状态</th>
							<th>审核状态</th>
							<th>审核次数</th>
							<th>是否锁定</th>
							<th>到期时间</th>
							<th style="width:600px;">操作</th>	
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
								<td>${o.address}</td>
								<td>${o.telphone}</td>
								<td>
									<c:if test="${o.is_reserve == 1}" var="lock">可在线预订</c:if>
									<c:if test="${!lock }">不可在线预订</c:if>
								</td>
								<td>
									<c:if test="${o.auditStatus == 1}" >待审核</c:if>
									<c:if test="${o.auditStatus == 2}" >通过</c:if>
									<c:if test="${o.auditStatus == 3}" >拒绝</c:if>
									<c:if test="${o.auditStatus == 4}" >驳回</c:if>
								</td>
								<td>${o.return_count}</td>
								<td>
									<c:if test="${o.is_locked == 1}" var="lock">锁定</c:if>
									<c:if test="${!lock }">未锁定</c:if>
								</td>
								<td>${o.effective_time}</td>
								<td width="600">
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<c:if test="${not empty update }">
											<button class="btn btn-xs btn-info"
												onclick="location='toedit_playground.do?oid=${o.id }'">
												<i class=" icon-eye-open bigger-120">修改</i>
											</button>
										</c:if>
										<c:if test="${empty update }">
											<button class="btn btn-xs btn-info"
												onclick="location='toedit_playground.do?oid=${o.id }&check=check'">
												<i class=" icon-eye-open bigger-120">查看</i>
											</button>
											
											<c:if test="${not empty k }">
												<c:if test="${o.auditStatus == 3 }">
												<button class="btn btn-xs btn-info" onclick="reset(${o.id },${o.is_locked})">
													<i class=" icon-edit bigger-120">
																重填
													</i>
												</button>
												</c:if>
											</c:if>
											<button class="btn btn-xs btn-info"
												onclick="location='space_list.do?oid=${o.id }&is_play=is_play'">
												<i class=" icon-eye-open bigger-120">查看场地</i>
											</button>
											<button class="btn btn-xs btn-info"
												onclick="location='toaddPlaygroundImg.do?oid=${o.id }'">
												<i class=" icon-eye-open bigger-120">添加图片</i>
											</button>
											
											<c:if test="${empty k }">
												<button class="btn btn-xs btn-info" onclick="pushplayground('${o.id}')">
													<c:if test="${o.stick==0}">
														<i class=" icon-edit bigger-120" id="pushobj_${o.id}">推荐</i>
													</c:if>
													<c:if test="${o.stick==1}">
														<i class=" icon-edit bigger-120" id="pushobj_${o.id}">取消推荐</i>
													</c:if>
												</button>
											</c:if>
											<c:if test="${not empty k }">
													<button class="btn btn-xs btn-info"
													onclick="location.href='/pgm/carrschedule.do?coachId=${o.playgroundmanager_id }'">
													<i class=" icon-edit bigger-120">场地营业时间设置</i>
													</button>
													<button class="btn btn-xs btn-info"
														onclick="location='backplaygrounddetail.do?playgroundId=${o.id }'">
														<i class=" icon-eye-open bigger-120">预订</i>
													</button>
											</c:if>
											<input type="hidden" value="${o.stick}" id="pushobj_stick_${o.id}" />
											<button class="btn btn-xs btn-info"
												onclick="location='/pgm/memberList.do?member=${o.id }&check=check'">
												<i class=" icon-eye-open bigger-120">查看会员</i>
											</button>
										</c:if>
										
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
	if('${update}'!=null && '${update}'!=""){
		location.href = "playground_list.do?update=update&pageNumber="+pageNumber;
	}else{
		location.href = "playground_list.do?&pageNumber="+pageNumber;
	}
	
}
function reset(id,flag){
	if(flag == 1){
		alert("场馆已被锁定，联系客服解锁");
		return;
	}
	location='toedit_playground.do?oid='+id;
}

</script>
	<script>
	if('${update}'!=null && '${update}'!=""){
		paging($('.pagination')[0],'pagination','playground_list.do?update=update&pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	}else{
		paging($('.pagination')[0],'pagination','playground_list.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	}
	
	</script>
</body>
</html>