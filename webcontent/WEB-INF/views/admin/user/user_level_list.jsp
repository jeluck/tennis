<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户等级列表信息</title>
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
			<li class="active">用户等级管理</li>
			<li class="active">用户等级列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>用户等级列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="user_level.do;"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<label>等级：</label>
			<select  id="level" name="level" style="width: 160px;"  class="bankSelected" >
				<option value="0" >请选择等级</option>
				<c:forEach items="${user_all }" var="u">
					<option value="${u.level }"  <c:if test="${level == u.level }"> selected="selected" </c:if>  >${u.level }</option>
				</c:forEach>
			</select>
			&nbsp;
			<input class="btn btn-sm btn-success"  type="submit" value="[ 查询 ]">
			<a class="btn btn-sm btn-success" onclick="location.href = 'user_level.do?pagenumber=1';">[ 取消查询 ]</a></span>
			<a class="btn btn-sm btn-success" onclick="location.href = 'toadd_user_level.do';">[ 添加用户等级]</a></span>
		</form>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>等级</th>
							<th>内容</th>
							<th style="width:300px;" >操作</th>	
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
								<td>${o.level}</td>
								<td>${o.name}</td>
								<td>			
									<button class="btn btn-xs btn-info"
										onclick="location='touser_level_edit.do?id=${o.id }&check=check'">
										<i class=" icon-eye-open bigger-120">查看</i>
									</button>
									<button class="btn btn-xs btn-info"
										onclick="location='touser_level_edit.do?id=${o.id }'">
										<i class=" icon-edit bigger-120">修改</i>
									</button>
									<button class="btn btn-xs btn-danger"
										onclick="del(${o.id});">
										<i class="icon-trash bigger-120"></i>删除
									</button>
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
	location.href = "user_level.do?&pageNumber="+pageNumber;
}

//删除
function del(id){
	$.layer({
	    shade: [0],
	    area: ['auto','auto'],
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    dialog: {
	        msg: '您确定要对该<b> 等级  </b>进行删除操作吗?',
	        btns: 2,                    
	        type: 4,
	        btn: ['[确定]','[取消]'],
	        yes: function(){
	        	parent.parent.ShowMsg("操作提示：","已经成功删除该等级信息！ ");
	        	location.href="del_user_level.do?id="+id;
	        }
	    }
	});
}

</script>
	<script>
paging($('.pagination')[0],'pagination','user_level.do?pageNumber=',${data_page.rowCount},${data_page.pageSize},${data_page.currentPage});
	</script>
</body>
</html>