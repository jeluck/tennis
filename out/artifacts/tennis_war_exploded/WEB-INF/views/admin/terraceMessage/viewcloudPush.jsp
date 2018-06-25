<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云推送</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	var data = "${o.user_data}";
	var text = data.substring(0, data.indexOf(","));
	$("#data").val(text);
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

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">云推送管理</li>
			<li class="active">云推送</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>云推送<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<input type="hidden" value="${che }" id="che" >
	<form id="QueryForm" name="form1" class="form-inline" action="addpush.do"  
		  method="post" onsubmit="return checkForm()">
		 <input type="hidden" name="textData" id="textData" >
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="120px" >
								标题：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="title" id="title" style="width: 360px;" placeholder="请输入标题"
										   class="col-xs-10 col-sm-5" value="${o.title }" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px" >
								内容：
							</td>
							<td>
								<textarea name="content"  id="content"
									style="height: 150px; width:360px;" >${o.content }</textarea>
							</td>
						</tr>
						<c:if test='${fn:contains(o.user_data, "单个用户") ==true}'>
							<tr id="modis" >
								<td width="120px" >
									手机号码：
								</td>
								<td>
									<input type="text" 
										   name="phone" id="data" style="width: 360px;" placeholder="请输入手机号码"
										   class="col-xs-10 col-sm-5"  />
								</td>
							</tr>
						</c:if>
						<c:if test='${fn:contains(o.user_data, "用户筛选") == true }'>
							<tr id="ranges" >
								<td width="120px" >
									筛选范围：
								</td>
								<td>
									<input type="text"
										   name="range" id="data" style="width: 360px;" placeholder="请输入筛选范围"
										   class="col-xs-10 col-sm-5"  />
<!-- 										   &nbsp;<span id="user_data_span" style="color:red;">n以下或者n-m区间(只填数字)</span> -->
								</td>
							</tr>
						</c:if>
						
						<tr>
							<td width="120px">
								推送给：
							</td>
							<td >&nbsp;&nbsp;
								<select  name="type" id="type"  class="bankSelected" required onchange="change()" >
									<option value="-4" <c:if test="${fn:contains(o.user_data, '所有') == true }"> selected="selected" </c:if> >所有</option>
									<option value="0" <c:if test="${fn:contains(o.user_data, '用户') == true }"> selected="selected" </c:if> >用户</option>
									<option value="1" <c:if test="${fn:contains(o.user_data, '教练') == true }"> selected="selected" </c:if> >教练</option>
									<option value="-2"   <c:if test="${fn:contains(o.user_data, '用户筛选') == true }"> selected="selected" </c:if> >用户筛选</option>
									<option value="-3" <c:if test="${fn:contains(o.user_data, '单个用户') == true }"> selected="selected" </c:if> >单个用户</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td width="120px">
								推送端口类型：
							</td>
							<td >&nbsp;&nbsp;
								<input type="checkbox"name="usertypes" value="0"  
									<c:if test="${fn:contains(o.user_data, ',0') == true }"> checked="checked" </c:if> > 用户端
								<input type="checkbox"name="usertypes" value="1"
									<c:if test="${fn:contains(o.user_data, ',1') == true }"> checked="checked" </c:if> /> 教练端
								<input type="hidden" id="usertype" name="usertype">
							</td>
						</tr>
						
						
						
						<tr>
							<td width="120px">
								类型：
							</td>
							<td >&nbsp;&nbsp;
								<input type="checkbox" name="phoneTypes" value="3"
									<c:if test="${fn:contains(o.user_data, ',3') == true }"> checked="checked" </c:if> /> 安卓
								<input type="checkbox" name="phoneTypes" value="4" 
									<c:if test="${fn:contains(o.user_data, ',4') == true }"> checked="checked" </c:if> /> 苹果
								<input type="hidden" id="phoneType" name="phoneType">
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<button class="btn btn-sm btn-success" type="button" onclick="location='terraceMessage_list.do?note=${note }'"> [ 返回 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
