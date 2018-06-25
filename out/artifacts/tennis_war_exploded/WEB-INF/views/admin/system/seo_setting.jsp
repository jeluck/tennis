<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEO设置</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
function updateInfo(){
	var params = util.Json2Str(util.serializeObject($("#option_form")));
	var info = util.POST("seosetting_update.do",params);
	parent.ShowMsg("操作提示：",info.info);
}
</script>
<script type="text/javascript" src="/js/admin/seo_setting.js"></script>
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
			<li class="active">SEO设置</li>
		</ul>
	</div>
	<div style="margin: 30px 20px;">
		<div class="row">
			<div class="col-sm-8">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a data-toggle="tab" href="#set_eml">
								<i class="green icon-cog bigger-110"></i>SEO设置
						</a></li>
						<li class="active"><a  href="javascript:void(0)" onclick="buildIndexHtml()">
								更新首页
						</a></li>
					</ul>

					<div class="tab-content">
						<!-- 邮件列表 -->
						<div id="set_eml" class="tab-pane in active">
							<div class="row">
								<div class="col-xs-12">
									<form id="option_form" role="form" method="post">
										<table style="width: 850px; margin: 0 auto;">
											<tr>
												<td style="width: 450px;"
													class="text-right no-padding-right"><b>标题附加字:</b> <br />网页标题通常是搜索引擎关注的重点，本附加字设置将出现在标题中论坛名称的后面，如果有多个关键字，建议用
													"|"、","(不含引号) 等符号分隔</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" value="${setting.html_title }"
															name="html_title" style="width: 360px;"
															placeholder="请输入标题附加字" class="col-xs-10 col-sm-5"
															required />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right"
													style="padding-top: 15px;"><b>Meta Keywords: </b><br />
													Keywords 项出现在页面头部的 Meta 标签中，用于记录本页面的关键字，多个关键字间请用半角逗号 "," 隔开
												</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" value="${setting.meta_keywords }"
															name="meta_keywords" style="width: 360px;"
															placeholder="请输入Meta Keywords" class="col-xs-10 col-sm-5"
															required />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right"
													style="padding-top: 15px;"><b>Meta Description: </b><br />
													Description 出现在页面头部的 Meta 标签中，用于记录本页面的概要与描述</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<input type="text" value="${setting.meta_description }"
															name="meta_description" style="width: 360px;"
															placeholder="请输入Meta Description"
															class="col-xs-10 col-sm-5" required />
													</div>
												</td>
											</tr>
											<tr>
												<td class="text-right no-padding-right"
													style="padding-top: 15px;"><b>其它头部信息: </b> <br />
													如需在&lt;head&gt;&lt;/head&gt; 中添加其它的 HTML 代码，可以使用本设置，否则请留空</td>
												<td class="text-left" style="padding-top: 15px;">
													<div class="col-sm-9">
														<textarea style="width: 360px; height: 100px;"
															name="header_info" placeholder="请输入其它头部信息">${setting.header_info }</textarea>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="2"
													style="text-align: center; padding-top: 20px;"><input
													type="hidden" value="${setting.id }" name="id" />
													<div class="col-md-offset-3 col-md-9">
														<label class="col-sm-3 control-label no-padding-right"
															for="form-field-2"></label>
														<button class="btn btn-sm btn-success" type="button"
															onclick="updateInfo()">
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