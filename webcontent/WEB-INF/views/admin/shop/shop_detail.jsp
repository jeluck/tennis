<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户</title>
<jsp:include page="../init.jsp" />
<link rel="stylesheet" href="../css/admin.css" />
<script src="../js/admin_common.js"></script>
<script src="../../js/commonwealProject.js"></script>
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
			<li class="active">店铺管理</li>
			<li class="active">店铺详细</li>
		</ul>
	</div>
	<div class="page_detail_view" style="width: 920px;">
		<div>
			<form action="edittest.do" id="add_commonwealprojectForm"
				method="post" >
				<table class="table table-striped table-bordered"
					style="text-align: center;">
					<tr>
						<td colspan="2" style="text-align: center; font-size: 20px;">店铺详情</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">名称</td>
						<td style="text-align: left;">
							 ${a.shopName } 
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">微信</td>
						<td style="text-align: left;">
							${a.shopWeChat }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">手机</td>
						<td style="text-align: left;">
						${a.shopPhone }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">身份证号码</td>
						<td style="text-align: left;">
						${a.identification }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">保证金</td>
						<td style="text-align: left;">
						${a.shopCashDeposit }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">最后更新时间</td>
						<td style="text-align: left;">
						${a.update_time }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">创建时间</td>
						<td style="text-align: left;">
						${a.create_time }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">店铺图标</td>
						<td style="text-align: left;">
						<c:if test="${fn:length(a.shopIconUrl)>0}" var="in">
							<img alt="" src="/${a.shopIconUrl}" style="cursor: pointer; width:56px;height:51px;" onclick="onshowimg('${a.shopIconUrl}','2')" >
						</c:if>
						<c:if test="${!in}">
							<img alt="" src="/image/nophoto_120X120.jpg" style="cursor: pointer; width:56px;height:51px;" />
						</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">身份证照片</td>
						<td style="text-align: left;">
						<c:if test="${fn:length(a.idCardPhotoUrl)>0}" var="id">
							<img alt="" src="/${a.idCardPhotoUrl}" style="cursor: pointer; width:56px;height:51px;" onclick="onshowimg('${a.idCardPhotoUrl}','2')" >
						</c:if>
						<c:if test="${!id}">
							<img alt="" src="/image/nophoto_120X120.jpg" style="cursor: pointer; width:56px;height:51px;" />
						</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">邀请链接</td>
						<td style="text-align: left;">
						${applicationScope.server_host}toregister.do?invite_code=${a.invite_code}
						</td>
					</tr>	
					<tr>
						<td colspan="2"><input onclick="location='shoplist.do'"
							class="btn btn-sm btn-success" value="返回" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 打开图片脚本 -->
	<jsp:include page="../common_image_show.jsp" />
</body>
</html>