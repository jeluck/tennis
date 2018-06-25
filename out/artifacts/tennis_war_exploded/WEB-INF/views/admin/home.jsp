<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<!-- basic styles -->
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<!-- self_defind styles -->
<link rel="stylesheet" href="../assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="../assets/css/google.css" />
<link rel="stylesheet" href="../assets/css/ace.min.css" />
<link rel="stylesheet" href="../assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="../assets/css/ace-skins.min.css" />
<!-- basic scripts -->
<script src='../assets/js/jquery-2.0.3.min.js'></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/typeahead-bs2.min.js"></script>

<!-- ace scripts -->
<script src="../assets/js/ace-extra.min.js"></script>
<script src="../assets/js/ace-elements.min.js"></script>
<script src="../assets/js/ace.min.js"></script>
<script type="text/javascript">
	$(function(){
		if($(document).height()<$(window).height()){
			$(".page-content").height($(window).height() - 100);
		}else{
			$(".page-content").height($(document).height() - 100);
		}
	});
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

		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li class="active">控制台</li>
		</ul>
		<!-- #nav-search -->
	</div>
	<div class="page-content">
		<div class="page-header">
			<h1>
				控制台 <small> <i class="icon-double-angle-right"></i> 我的待办事项
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-sm-5">
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="icon-star orange"></i> 借款管理审核
						</h4>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="icon-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body"
						style="border: 1px solid #ccc; border-top: 0px; border-radius: 4px; -webkit-box-shadow: 2px 2px 2px #CCC; -moz-box-shadow: 2px 2px 2px #CCC; padding: 4px 10px;">
						<div class="widget-main no-padding">
							<table class="table table-bordered table-striped">
								<thead class="thin-border-bottom">
									<tr>
										<th><i class="icon-caret-right blue"></i> 审核项目</th>
										<th><i class="icon-caret-right blue"></i> 数量</th>
										<th class="hidden-480"><i class="icon-caret-right blue"></i>操作</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td>借款等待资料审核：</td>
										<td><b class="green"> <c:set var="hava" value="false" />
												<c:forEach items="${loan_count }" var="loan">
													<c:if test="${loan.loan_status==1 }">
														<c:set var="hava" value="true" />${loan.count }</c:if>
												</c:forEach> <c:if test="${!hava }">0</c:if> 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=waitinfo_loan'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>借款初审审核：</td>
										<td><b class="green"> <c:set var="hava" value="false" />
												<c:forEach items="${loan_count }" var="loan">
													<c:if test="${loan.loan_status==2 }">
														<c:set var="hava" value="true" />${loan.count }</c:if>
												</c:forEach> <c:if test="${!hava }">0</c:if> 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=chushen_loan'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>借款满标审核：</td>
										<td><b class="green"> <c:set var="hava" value="false" />
												<c:forEach items="${loan_count }" var="loan">
													<c:if test="${loan.loan_status==6 }">
														<c:set var="hava" value="true" />${loan.count }</c:if>
												</c:forEach> <c:if test="${!hava }">0</c:if> 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=fullscale_loan'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>申请的散标债权转让：</td>
										<td><b class="green"><c:if
													test="${fn:length(transfer_count)>0}" var="hava">${transfer_count[0].total }</c:if>
												<c:if test="${!hava }">0</c:if>条</b></td>
										<td class="hidden-480"><span
											class="label label-info arrowed-right arrowed-in"
											style="cursor: pointer;"
											onclick="location='page.do?page=bond_transfer'">审核</span></td>
									</tr>
									<tr>
										<td>申请的定投债权转让：</td>
										<td><b class="green"><c:if
													test="${fn:length(transfer_count)>1}" var="hava">${transfer_count[1].total }</c:if>
												<c:if test="${!hava }">0</c:if>条</b></td>
										<td class="hidden-480"><span
											class="label label-info arrowed-right arrowed-in"
											style="cursor: pointer;"
											onclick="location='page.do?page=dt_bond_transfer'">审核</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /widget-main -->
					</div>
					<!-- /widget-body -->
				</div>
				<!-- /widget-box -->
			</div>
			<div class="col-sm-7">
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="icon-lock orange"></i> 认证管理审核
						</h4>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="icon-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body"
						style="border: 1px solid #ccc; border-top: 0px; border-radius: 4px; -webkit-box-shadow: 2px 2px 2px #CCC; -moz-box-shadow: 2px 2px 2px #CCC; padding: 4px 10px;">
						<div class="widget-main no-padding">
							<table class="table table-bordered table-striped">
								<thead class="thin-border-bottom">
									<tr>
										<th><i class="icon-caret-right blue"></i> 审核项目</th>
										<th><i class="icon-caret-right blue"></i> 数量</th>
										<th class="hidden-480"><i class="icon-caret-right blue"></i>操作</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td>等待用户基本信息审核：</td>
										<td><b class="green">${base_info_count } 条</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=user_basic_shenhe'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>等待用户必要资料审核：</td>
										<td><b class="green"> <c:set var="hava" value="false" />
												<c:forEach items="${authshenhe_count }" var="auth">
													<c:if test="${auth.auth_type==1&&auth.status==2 }">
														<c:set var="hava" value="true" />${auth.count }</c:if>
												</c:forEach> <c:if test="${!hava }">0</c:if> 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=needful_auth_view'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>等待用户可选资料审核：</td>
										<td><b class="green"> <c:set var="hava" value="false" />
												<c:forEach items="${authshenhe_count }" var="auth">
													<c:if test="${auth.auth_type==2&&auth.status==2 }">
														<c:set var="hava" value="true" />${auth.count }</c:if>
												</c:forEach> <c:if test="${!hava }">0</c:if> 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=optionnal_auth_view'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>等待手机号变更审核：</td>
										<td><b class="green">${phone_count}条</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=user_phone_audit'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /widget-main -->
					</div>
					<!-- /widget-body -->
				</div>
				<!-- /widget-box -->
			</div>
		</div>
		<div class="hr hr32 hr-dotted"></div>

		<div class="row">
			<div class="col-sm-5">
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="icon-coffee orange"></i> 资金管理审核
						</h4>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="icon-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body"
						style="border: 1px solid #ccc; border-top: 0px; border-radius: 4px; -webkit-box-shadow: 2px 2px 2px #CCC; -moz-box-shadow: 2px 2px 2px #CCC; padding: 4px 10px;">
						<div class="widget-main no-padding">
							<table class="table table-bordered table-striped">
								<thead class="thin-border-bottom">
									<tr>
										<th><i class="icon-caret-right blue"></i> 审核项目</th>
										<th><i class="icon-caret-right blue"></i> 数量</th>
										<th class="hidden-480"><i class="icon-caret-right blue"></i>操作</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td>等待提现审核：</td>
										<td><b class="green">${withdwar_count } 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=withdwar_view'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
									<tr>
										<td>等待银行卡变更申请审核：</td>
										<td><b class="green">${card_change_count } 条
										</b></td>
										<td class="hidden-480"><span style="cursor: pointer;"
											onclick="location='page.do?page=bank_card_view'"
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /widget-main -->
					</div>
					<!-- /widget-body -->
				</div>
				<!-- /widget-box -->
			</div>
<!-- 			<div class="col-sm-7">
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="icon-beer orange"></i> 用户管理审核
						</h4>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="icon-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body"
						style="border: 1px solid #ccc; border-top: 0px; border-radius: 4px; -webkit-box-shadow: 2px 2px 2px #CCC; -moz-box-shadow: 2px 2px 2px #CCC; padding: 4px 10px;">
						<div class="widget-main no-padding">
							<table class="table table-bordered table-striped">
								<thead class="thin-border-bottom">
									<tr>
										<th><i class="icon-caret-right blue"></i> 审核项目</th>
										<th><i class="icon-caret-right blue"></i> 数量</th>
										<th class="hidden-480"><i class="icon-caret-right blue"></i>操作</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td>等待举报用户审核：</td>
										<td><b class="green">(5)条</b></td>
										<td class="hidden-480"><span
											class="label label-info arrowed-right arrowed-in">审核</span></td>
									</tr>
								</tbody>
							</table>
						</div>
						/widget-main
					</div>
					/widget-body
				</div>
				/widget-box
			</div> -->
		</div>
		<div class="hr hr32 hr-dotted"></div>
		<!-- PAGE CONTENT ENDS -->
	</div>
</body>
</html>