<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加活动</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/some_use.js"></script>
<script type="text/javascript">
function checkForm()
{
	return true;
}
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#content', {
		themeType : 'simple',
		uploadJson : '../js/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : '../js/kindeditor/jsp/file_manager_json.jsp',
		allowFileManager : true
	});
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
			<li class="active">平台信息管理</li>
			<li class="active">添加信息</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加信息 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addterraceMessage.do"  
		  method="post" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								消息类型：
							</td>
							<td >
								<div class="col-sm-9">
									<select id="mes_cloud_type" name="mes_cloud_type">
									<option value="0">消息</option>
									<option value="2">短信</option>
									</select>
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								发送类型：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="send_type" value="0" onclick="send_type_f(this);" />所有用户
									<input type="radio" name="send_type" value="1" onclick="send_type_f(this)" />手动输入
									<input type="radio" name="send_type" value="2" onclick="send_type_f(this)" />用户等级筛选
									<input type="radio" name="send_type" value="3" onclick="send_type_f(this)" />用户
									<input type="radio" name="send_type" value="4" onclick="send_type_f(this)" />教练
									<input type="radio" name="send_type" value="5" onclick="send_type_f(this)" />场馆运营者
									<input type="radio" name="send_type" value="6" onclick="send_type_f(this)" />用户数据导入
								</div>
							</td>
						</tr>
						<tr id="user_data_tr" style="display:none;">
							<td width="100px" >
								数据：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="user_data" id="user_data" style="width:100%;" placeholder="请输入信息"
										   class="col-xs-10 col-sm-5" />
								</div>
								<span id="user_data_span" style="display:none;color:red;">n以下或者n-m区间(只填数字)</span>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								标题：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="title" style="width: 360px;" placeholder="请输入信息标题"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								内容：
							</td>
							<td >
								<textarea name="content" xid="content"
									style="height: 150px; width:360px;" required ></textarea>
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>

			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>




</body>
</html>
