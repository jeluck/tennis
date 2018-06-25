<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云推送</title>
<jsp:include page="init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	if($("#che").val() == 1){
		alert("推送成功!");
	}
});
function checkForm()
{
	checkedUsertype();
	checkedPhoneTypes();
	if($("#title").val() == null || $("#title").val() == ""){
		alert("请填写标题");
		return false;
	}
	if($("#content").val() == null || $("#content").val() == ""){
		alert("请填写内容");
		return false;
	}
	var chk_value =[];    
	if($("#type").val() == -3){
		if($("#phone").val() == null || $("#phone").val() ==""){
			alert("请手机号码");
			return false;
		}else{
			chk_value.push($("#phone").val());
		}
	}else if($("#type").val() == -2){
		if($("#range").val() == null || $("#range").val() ==""){
			alert("请筛选范围");
			return false;
		}else{
			chk_value.push($("#range").val());
		}
		
	}
	chk_value.push(getText());
	chk_value.push($("#usertype").val());
	chk_value.push($("#phoneType").val());
	$("#textData").val(chk_value)
	$('#submitButton').text("[ 请等待 ]");
	$('#submitButton').attr("disabled","disabled");
	return true;
// return false;
}

var gettext_val="";
function getText(){
	/* 取得下拉列表框对象 */
	var obj = document.getElementById("type");
	/* 取得当前选中的索引 */
	var cindex = document.getElementById("type").selectedIndex;
	/* 根据索引取得options集合之中的一个option元素,它的innerText属性就是页面显示的文本啦 */
	 gettext_val = obj.options[cindex].text;
	
	return gettext_val;

}

// var editor;
// KindEditor.ready(function(K) {
// 	editor = K.create('#content', {
// 		themeType : 'simple',
// 		uploadJson : 'js/kindeditor/jsp/upload_json.jsp',
// 		fileManagerJson : 'js/kindeditor/jsp/file_manager_json.jsp',
// 		allowFileManager : true
// 	});
// });

function change(){
	if($("#type").val() == -3){
		$("#modis").show();
		$("#ranges").hide();
	}else if($("#type").val() == -2){
		$("#modis").hide();
		$("#ranges").show();
	}else{
		$("#modis").hide();
		$("#ranges").hide();
	}
	
}


function checkedUsertype(){  //jquery获取复选框值    
	  var chk_value =[];    
	  $('input[name="usertypes"]:checked').each(function(){    
	   chk_value.push($(this).val());    
	  });
	  if(chk_value.length>0){
		  $("#usertype").val(chk_value);    
	  }
}

function checkedPhoneTypes(){  //jquery获取复选框值    
	  var chk_value =[];    
	  $('input[name="phoneTypes"]:checked').each(function(){    
	   chk_value.push($(this).val());    
	  });
	  if(chk_value.length>0){
		  $("#phoneType").val(chk_value);    
	  }
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
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px" >
								内容：
							</td>
							<td>
								<textarea name="content"  id="content"
									style="height: 150px; width:360px;" ></textarea>
							</td>
						</tr>
						
						<tr style="display: none;" id="modis" >
							<td width="120px" >
								手机号码：
							</td>
							<td>
								<input type="text"
									   name="phone" id="phone" style="width: 360px;" placeholder="请输入手机号码"
									   class="col-xs-10 col-sm-5"  />
							</td>
						</tr>
						
						<tr style="display: none;" id="ranges" >
							<td width="120px" >
								筛选范围：
							</td>
							<td>
								<input type="text"
									   name="range" id="range" style="width: 360px;" placeholder="请输入筛选范围"
									   class="col-xs-10 col-sm-5"  />
									   &nbsp;<span id="user_data_span" style="color:red;">n以下或者n-m区间(只填数字)</span>
							</td>
						</tr>
						
						<tr>
							<td width="120px">
								推送给：
							</td>
							<td >&nbsp;&nbsp;
								<select  name="type" id="type"  class="bankSelected" required onchange="change()" >
									<option value="-4" >所有</option>
									<option value="0" >用户</option>
									<option value="1" >教练</option>
									<option value="-2" >用户筛选</option>
									<option value="-3" >单个用户</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td width="120px">
								推送端口类型：
							</td>
							<td >&nbsp;&nbsp;
<!-- 								<select  name="usertype" id="usertype"  class="bankSelected" required" > -->
<!-- 									<option value="0" >用户端</option> -->
<!-- 									<option value="1" >教练端</option> -->
<!-- 								</select> -->
								<input type="checkbox"name="usertypes" value="0"  /> 用户端
								<input type="checkbox"name="usertypes" value="1" /> 教练端
								<input type="hidden" id="usertype" name="usertype">
							</td>
						</tr>
						
						
						
						<tr>
							<td width="120px">
								类型：
							</td>
							<td >&nbsp;&nbsp;
<!-- 								<select  name="phoneType" id="phoneType"  class="bankSelected" required > -->
<!-- 									<option value="3" >安卓</option> -->
<!-- 									<option value="4" >苹果</option> -->
<!-- 								</select> -->
								<input type="checkbox" name="phoneTypes" value="3" /> 安卓
								<input type="checkbox" name="phoneTypes" value="4" /> 苹果
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
					<button class="btn btn-sm btn-success" type="submit" id="submitButton" > [ 提交 ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
