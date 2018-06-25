<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站点资料</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
	function updateInfo(){
		var params = util.Json2Str(util.serializeObject($("#option_form")));
		var info = util.POST("internet_update.do",params);
		parent.ShowMsg("操作提示：",info.info);
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

		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">系统管理</li>
			<li class="active">站点资料</li>
		</ul>
	</div>
	<div style="margin: 30px 20px;">
		<div class="row">
			<div class="col-sm-8">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a data-toggle="tab" href="#set_eml">
								<i class="green icon-envelope-alt bigger-110"></i>站点资料
						</a></li>
					</ul>

					<div class="tab-content">
						<!-- 邮件列表 -->
						<div id="set_eml" class="tab-pane in active">
							<div class="row">
								<div class="col-xs-12">
									<form class="form-horizontal" id="option_form" role="form"
										method="post">
										<table style="width: 850px; margin: 0 auto;">
											<tr>
												<td class="text-right no-padding-right">站点名称</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" value="${internet_info.internet_name }"
															style="width: 300px;" name="internet_name"
															placeholder="请输入站点名称" class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">公司名称</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入公司名称"
															style="width: 300px;"
															value="${internet_info.company_name }"
															name="company_name" class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right">邮政编码</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入邮政编码"
															value="${internet_info.postal_code }"
															style="width: 300px;" name="postal_code"
															class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">公司地址</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入公司地址"
															style="width: 300px;"
															value="${internet_info.company_address }"
															name="company_address" class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right">负责人</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入负责人"
															value="${internet_info.principal }" style="width: 300px;"
															name="principal" class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">联系人</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入联系人"
															style="width: 300px;" value="${internet_info.linkman }"
															name="linkman" class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right">电话号码</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入电话号码"
															value="${internet_info.telphone }" style="width: 300px;"
															name="telphone" class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">手机号码</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入手机号码"
															style="width: 300px;"
															value="${internet_info.mobilephone }" name="mobilephone"
															class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right">传真</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入传真 "
															value="${internet_info.faxes }" style="width: 300px;"
															name="faxes" class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">公司邮箱</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入公司邮箱"
															style="width: 300px;"
															value="${internet_info.company_email }"
															name="company_email" class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right">企业QQ</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入企业QQ "
															value="${internet_info.enterprise_qq }"
															style="width: 300px;" name="enterprise_qq"
															class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">服务电话</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入服务电话"
															style="width: 300px;"
															value="${internet_info.service_phone }"
															name="service_phone" class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right">ICP证书号</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入ICP证书号 "
															value="${internet_info.icp_number }"
															style="width: 300px;" name="icp_number"
															class="col-xs-10 col-sm-5" />
													</div>
												</td>
												<td class="text-right no-padding-right">站点域名</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" placeholder="请输入站点域名"
															style="width: 300px;"
															value="${internet_info.internet_domain }"
															name="internet_domain" class="col-xs-10 col-sm-5" />
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="4"
													style="text-align: center; padding-top: 20px;"><input
													type="hidden" value="${internet_info.id }" name="id" />
													<div class="col-md-offset-3 col-md-9">
														<label class="col-sm-3 control-label no-padding-right"
															for="form-field-2"></label>
														<button class="btn btn-sm btn-success" type="button"
															onclick="updateInfo();">
															<i class="icon-ok bigger-90"></i>[ 确定 ]
														</button>
														&nbsp; &nbsp; &nbsp;
														<button class="btn btn-sm" type="reset">
															<i class="icon-undo bigger-90"></i>[ 重置 ]
														</button>
													</div></td>
											</tr>
										</table>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /span -->
		</div>
	</div>

</body>
</html>