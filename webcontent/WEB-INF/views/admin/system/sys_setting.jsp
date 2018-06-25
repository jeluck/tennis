<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统参数设置</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
function updateEmlInfo(){
	var params = util.Json2Str(util.serializeObject($("#eml_form")));
	var info = util.POST("update_sys_email.do",params);
	parent.ShowMsg("操作提示：",info.info);
}
function updateEmsInfo(){
	var params = util.Json2Str(util.serializeObject($("#ems_form")));
	var info = util.POST("update_sys_sms.do",params);
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
			<li class="active">短信邮箱</li>
		</ul>
	</div>
	<div style="margin: 30px 20px;">
		<div class="row">
			<div class="col-sm-8">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a data-toggle="tab" href="#set_eml">
								<i class="green icon-envelope-alt bigger-110"></i>邮件配置
						</a></li>
						<li><a data-toggle="tab" href="#set_sms"> <i
								class="green  icon-comments-alt bigger-110"></i>亿美短信配置
						</a></li>
					</ul>

					<div class="tab-content">
						<!-- 邮件列表 -->
						<div id="set_eml" class="tab-pane in active">
							<div class="row">
								<div class="col-xs-12">
									<form class="form-horizontal" id="eml_form"
										action="update_sys_email.do" role="form" method="post">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-1"> 邮箱帐户 </label>
											<div class="col-sm-9">
												<input type="text" id="form-field-1"
													value="${setting.eml_account }" name="eml_account"
													placeholder="请输入邮箱帐户" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-2"> 邮箱密码 </label>

											<div class="col-sm-9">
												<input type="password" id="form-field-2"
													placeholder="请输入邮箱密码" value="${setting.eml_password }"
													name="eml_password" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-2"> SMTP服务器 </label>

											<div class="col-sm-9">
												<input type="text" id="form-field-2"
													placeholder="请输入SMTP服务器地址" value="${setting.eml_host }"
													name="eml_host" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-2"> 端口号 </label> <input type="hidden"
												name="id" value="${setting.id }" />
											<div class="col-sm-9">
												<input type="number" id="form-field-2"
													placeholder="请输入SMTP服务器端口号" style="width: 340px;"
													class="easyui-numberbox" data-options="min:0"
													value="${setting.eml_port }" name="eml_port"
													class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<div class="col-sm-9">
												<div class="col-md-offset-3 col-md-9">
													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-2"></label>
													<button class="btn btn-sm btn-success" type="button"
														onclick="updateEmlInfo();">
														<i class="icon-ok bigger-90"></i>[ 确定 ]
													</button>
													&nbsp; &nbsp; &nbsp;
													<button class="btn btn-sm" type="reset">
														<i class="icon-undo bigger-90"></i>[ 重置 ]
													</button>
												</div>
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>
						<!-- 短信列表 -->
						<div id="set_sms" class="tab-pane">
							<div class="row">
								<div class="col-xs-12">
									<form class="form-horizontal" id="ems_form"
										action="update_sys_sms.do" role="form" method="post">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-1"> CD_KEY </label>
											<div class="col-sm-9">
												<input type="text" id="form-field-1"
													value="${setting.sms_cd_key }" name="sms_cd_key"
													placeholder="请输入CD_KEY" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-2"> 密码 </label>

											<div class="col-sm-9">
												<input type="password" id="form-field-2"
													placeholder="请输入PassWord" value="${setting.sms_password }"
													name="sms_password" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-2"> 自定义Key值 </label>

											<div class="col-sm-9">
												<input type="text" id="form-field-1" placeholder="请输入Key值"
													value="${setting.sms_key }" name="sms_key"
													class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="form-field-2"> 短信签名 </label> <input type="hidden"
												name="id" value="${setting.id }" />
											<div class="col-sm-9">
												<input type="text" id="form-field-2" placeholder="请输入短信签名"
													value="${setting.sms_signature }" name="sms_signature"
													class="col-xs-10 col-sm-5" /><span style="color:red;">如果短信账号是独享通道则留空</span>
											</div>
										</div>
										<div class="space-4"></div>
										<div class="form-group">
											<div class="col-sm-9">
												<div class="col-md-offset-3 col-md-9">
													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-2"></label>
													<button class="btn btn-sm btn-success" type="button"
														onclick="updateEmsInfo();">
														<i class="icon-ok bigger-90"></i>[ 确定 ]
													</button>
													&nbsp; &nbsp; &nbsp;
													<button class="btn btn-sm" type="reset">
														<i class="icon-undo bigger-90"></i>[ 重置 ]
													</button>
												</div>
											</div>
										</div>

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