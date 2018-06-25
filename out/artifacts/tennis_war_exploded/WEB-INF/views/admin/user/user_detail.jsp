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
			<li class="active">用户管理</li>
			<li class="active">用户详细</li>
		</ul>
	</div>
	<div class="page_detail_view" style="width: 920px;">
		<div>
			<form action="edittest.do" id="add_commonwealprojectForm"
				method="post" >
				<table class="table table-striped table-bordered"
					style="text-align: center;">
					<tr>
						<td colspan="2" style="text-align: center; font-size: 20px;">用户详情</td>
					</tr>
					<tr>
						<td style="width: 200px; text-align: center;">名称</td>
						<td style="text-align: left;">
							 ${a.username } 
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td style="text-align: center;">微信</td> -->
<!-- 						<td style="text-align: left;"> -->
<%-- 							${a.wenumber } --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td style="text-align: center;">手机</td>
						<td style="text-align: left;">
						${a.uphone }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">身份证号码</td>
						<td style="text-align: left;">
						${a.idcard_no }
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">余额</td>
						<td style="text-align: left;">
						${a.amount }
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td style="text-align: center;">最后登录时间</td> -->
<!-- 						<td style="text-align: left;"> -->
<%-- 						${a.last_login_time } --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td style="text-align: center;">头像</td>
						<td style="text-align: left;">
						<c:if test="${fn:length(a.head_photo)>0}" var="in">
							<img alt="" src="/${a.head_photo}" style="cursor: pointer; width:56px;height:51px;" onclick="onshowimg('${a.head_photo}','2')" >
						</c:if>
						<c:if test="${!in}">
							<img alt="" src="/image/nophoto_120X120.jpg" style="cursor: pointer; width:56px;height:51px;" />
						</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">身份证照片</td>
						<td style="text-align: left;">
						<c:if test="${fn:length(a.idcard_photo_positive)>0}" var="id">
							<img alt="" src="/${a.idcard_photo_positive}" style="cursor: pointer; width:56px;height:51px;" onclick="onshowimg('${a.idcard_photo_positive}','2')" >
						</c:if>
						<c:if test="${!id}">
							<img alt="" src="/image/nophoto_120X120.jpg" style="cursor: pointer; width:56px;height:51px;" />
						</c:if>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">身份证反面照片</td>
						<td style="text-align: left;">
						<c:if test="${fn:length(a.idcard_photo_anti)>0}" var="id">
							<img alt="" src="/${a.idcard_photo_anti}" style="cursor: pointer; width:56px;height:51px;" onclick="onshowimg('${a.idcard_photo_anti}','2')" >
						</c:if>
						<c:if test="${!id}">
							<img alt="" src="/image/nophoto_120X120.jpg" style="cursor: pointer; width:56px;height:51px;" />
						</c:if>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td style="text-align: center;">邀请id</td> -->
<!-- 						<td style="text-align: left;"> -->
<%-- 						${a.invite_code} --%>
<!-- 						</td> -->
<!-- 					</tr>	 -->
					<tr>
						<td style="text-align: center;">类型</td>
						<td style="text-align: left;">
							<c:if test="${a.is_coach==0 }" var="xiangdeng" >
										会员
									</c:if>
									<c:if test="${!xiangdeng }">
										<c:if test="${a.coach.coachType == 1}">
										自由教练			
										</c:if>
										<c:if test="${a.coach.coachType == 2}">
											驻场教练				
										</c:if>
										<c:if test="${a.coach.coachType == 3}">
											场馆运营者
										</c:if>
									</c:if>
						</td>
					</tr>	
					<tr>
						<td colspan="2">
							<c:if test="${not empty backurl }" var="hava">
								<input onclick="location='real_name_authentication.do'"	class="btn btn-sm btn-success" value="返回" />
							</c:if>
							<c:if test="${!hava }"><input onclick="location='userlist.do'"
							class="btn btn-sm btn-success" value="返回" />
							</c:if>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 打开图片脚本 -->
	<jsp:include page="../common_image_show.jsp" />
</body>
</html>