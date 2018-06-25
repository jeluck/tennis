<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训/课程添加</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
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
			<li class="active">培训/课程管理</li>
			<li class="active">培训/课程添加</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>培训/课程添加 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="addcourse.do"  
		  method="post" enctype="multipart/form-data"  xonsubmit="return checkForm()">
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
										   name="title" style="width: 360px;" placeholder="请输入标题"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								类型：
							</td>
							<td >&nbsp;&nbsp;
								<select  name="type" style="width: 120px;" class="bankSelected" required >
									<option value="1" >课程</option>
									<option value="2" >培训</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="120px">
								图片：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="file" class="input_text" name="imgFile1" style="width: 200px;" />
									&nbsp;&nbsp;<span id="tips">*图片一</span>
									<input type="file" class="input_text" name="imgFile2" style="width: 200px;" />
									&nbsp;&nbsp;<span id="tips">*图片二</span>
									<input type="file" class="input_text" name="imgFile3" style="width: 200px;" />
									&nbsp;&nbsp;<span id="tips">*图片三</span>
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								省份:
							</td>
							<td width="100px" >&nbsp;&nbsp;
						        <select id="user_province" name="provinceid" onchange="getCity('user_province')" class="bankSelected" required >
									<option value="0">请选择省份</option>
									<c:forEach items="${provincelist}" var="p">
										<option value="${p.region_id}" <c:if test="${ca.user_province==p.region_id }">selected="selected"</c:if> >${p.region_name}</option>
									</c:forEach>
								</select>	
						        <select  id="user_city" name="city_show_id" class="bankSelected" required >
									<option value="0">请选择城市</option>
									<option value="${ca.user_city}" selected="selected">${ca.city}</option>
								</select>
						     
	    					</td>
						</tr>
						<tr>
							<td width="120px" >
								内容：
							</td>
							<td>
								<textarea name="content" id="content"
									style="height: 150px; width:360px;" ></textarea>
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
<script>		
function getCity(id){
	var province = $("#"+id).val();
	var user_city = $("#user_city");
	var info = util.POST("/city.do",{"province":province});
	var str ='<option value="0">请选择</option>';
	
	var user_area = $("#user_area");
	user_area.html(str);
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_city.html(str);
}
</script>
</html>