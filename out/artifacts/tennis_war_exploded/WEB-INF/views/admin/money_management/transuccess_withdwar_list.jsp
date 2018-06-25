<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转让成功的提现列表</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
//页面跳转
function go_page(pageNumber){
	querywhere = util.Json2Str(util.serializeObject($("#QueryForm")));
	location.href = "withdraw_tran.do?status=3&pageNumber="+pageNumber+"&"+querywhere;
}
//查询
function Query() {
	querywhere = util.serializeObject($("#QueryForm"));
	go_page(1);
}

//取消查询
function CancelQuery() {
	querywhere = {};
	go_page(1);
}
</script>
</head>
<body>
<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">权限管理</li>
			<li class="active">提现管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			待审核的提现申请列表<span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="myTab">
			<li ><a href="withdraw_tran.do?status=1"><i
					class="green icon-home bigger-110"></i>待审核的提现申请列表 </a></li>
			<!-- <li><a data-toggle="tab" href="#transfer_ing"><i
					class="green icon-cloud-download bigger-110"></i>转账中的提现列表 </a></li> -->
			<li class="active"><a href="withdraw_tran.do?status=3"><i
					class="green icon-check bigger-110"></i>转账成功的提现列表 </a></li>
			<li><a  href="withdraw_tran.do?status=4"><i
					class="green icon-fire bigger-110"></i>失败的提现列表 </a></li>
		</ul>
	<div class="query_form">
		<form id="QueryForm" class="form-inline"
			action="javascript:void(0);" method="post">
			<label>用户名：</label>
			<input type="text" name="name" style="width: 90px; height: 25px;" /> 
			<label>真实姓名：</label><input type="text" name="real_name" style="width: 90px; height: 25px;" />
			<label>申请时间：</label>
			<input type="text" name="start_time" class="Wdate"	onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
				style="width: 120px; height: 25px;" /> — 
			<input type="text"	class="Wdate" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
				name="end_time" style="width: 120px; height: 25px;" />
			<span>
				<a class="btn btn-sm btn-success" onclick="Query();">[ 查询 ]</a>
				<a class="btn btn-sm btn-success" onclick="CancelQuery();">[取消查询]</a>
				<a class="btn btn-sm btn-success" onclick="exportwithdraw_list();">[ 导出 ]</a>
			</span>
		</form>
	</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12" style="height:500px;">
					<div class="table-responsive">
						<table id="sample-table-1"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>用户名</th>
									<th>提现方式</th>
									<th>真实姓名</th>
									<th>提现帐号</th>
									<th>提现金额(￥)</th>
									<th>到帐金额(￥)</th>
									<th>手续费(￥)</th>
									<th>转账时间</th>
								<!--<th>到帐时间</th> -->
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:if var="havadata" test="${empty tx_tran_page.dataList }">
									<td colspan="13" align="center">暂无数据</td>
								</c:if>
								<c:if test="${!havadata }">
									<c:set var="base" value="${(tx_tran_page.currentPage-1)*10 }" />
									<c:forEach items="${tx_tran_page.dataList }" var="tx"
										varStatus="v">
										<tr>
											<td>${base+v.index+1}</td>
											<td></td>
											<td>${tx.realname}</td>
											<td><c:if test="${tx.withdrawType == 1}">银行卡</c:if>
												<c:if test="${tx.withdrawType == 2}">支付宝</c:if>
											</td>
											<td>${tx.account_num}</td>
											<td>￥${tx.wd_money}</td>
											<td>￥${tx.amount_money}</td>
											<td>￥${tx.withdraw_rate}</td>
											<td>${tx.submit_time}</td>
											<!-- <td>${tx.amount_time}</td> -->
											<td>
												<div
													class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													<button class="btn btn-xs btn-info"
														onclick="watch_detail(${tx.id});">
														<i class="icon-zoom-in bigger-130">查看</i>
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
				<c:if test="${tx_tran_page.pageCount>0 }">
					<div class="modal-footer no-margin-top">
						<ul class="pagination pull-right no-margin">
							<li><a href="javascript:void(0);">第${tx_tran_page.currentPage
									}页/共${tx_tran_page.pageCount }页</a></li>
							<li><a href="javascript:go_page(1);">首页</a></li>
							<li
								class="prev <c:if test="${tx_tran_page.currentPage==1}"> disabled</c:if>">
								<c:if test="${tx_tran_page.currentPage-1>0 }">
									<a href="javascript:go_page(${tx_tran_page.currentPage-1 });">
										<<&nbsp;&nbsp;上一页 </a>
								</c:if> <c:if test="${tx_tran_page.currentPage-1==0 }">
									<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
								</c:if>
							</li>

							<c:forEach begin="${tx_tran_page.startPage }"
								end="${tx_tran_page.pageCount }" varStatus="v">
								<li
									<c:if test="${v.index==tx_tran_page.currentPage }" var="next current">class="active"</c:if>
									<c:if test="${!current}">class="prev"</c:if>><a
									href="javascript:go_page(${v.index });">${v.index }</a></li>
							</c:forEach>

							<li
								class="next <c:if test="${tx_tran_page.currentPage==tx_tran_page.pageCount}"> disabled</c:if>">
								<c:if
									test="${tx_tran_page.currentPage+1<=tx_tran_page.pageCount}">
									<a href="javascript:go_page(${tx_tran_page.currentPage+1 });">
										下一页&nbsp;&nbsp;>> </a>
								</c:if> <c:if
									test="${tx_tran_page.currentPage==tx_tran_page.pageCount}">
									<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
								</c:if>
							</li>
							<li><a
								href="javascript:go_page(${tx_tran_page.pageCount });">尾页</a></li>
							<li>&nbsp;&nbsp;到第<input type="text"
								class="easyui-numberbox" value="${tx_tran_page.currentPage }"
								data-options="min:1,max:${tx_tran_page.pageCount }" id="pagenum"
								style="width: 55px;" />页&nbsp;&nbsp;<input
								class="btn btn-xs btn-info" type="button"
								onclick="go_page($('#pagenum').val());" value="确定" /></li>
						</ul>
					</div>
				</c:if>
			</div>
			<!-- /row -->
		</div>
	</div>
	<script>
		//自适应高度		2015-04-29
		$(function(){
			if(parent&&parent.document){
				$(parent.document.body).find('#load_iframe2').height($('html').height());
			}
			
		});
	</script>
<jsp:include page="watch_detail.jsp" />
</body>
</html>