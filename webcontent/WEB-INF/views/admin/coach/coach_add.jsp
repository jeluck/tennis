<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/coach.js?v=<%=new Date() %>"></script>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<style type="text/css">
	li{
	list-style: none;
	}
</style>
<script type="text/javascript">
function checkPhone(){
	var reg = /^((0?1[358]\d{9})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
	if(!reg.test($("#phone").val())){
		alert("电话格式不正确");
		return 0;
	}
	
	var info = util.POST("/pgm/savecheckCoachPhone.do",{"phone":$("#phone").val()});
	if(info<=0){
		alert("此电话已被使用");
		return 0;
	}
	return 1;
}

function checkIdcard_no(){
	var reg1 =/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	var reg2 =/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
	var idcard_no = document.getElementById('idcard_no').value;
	if(reg1.test(idcard_no) != true && reg2.test(idcard_no) != true){
		alert("身份证格式不对");
		return 0;
	}
	var info = util.POST("/pgm/checkIdcard_no.do",{"idcard_no":idcard_no,"id":0});
	if(info<=0){
		alert("此身份证已被使用");
		return 0;
	}
	return 1;
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
			<li class="active">教练管理</li>
			<li class="active">添加教练</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加教练 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="/pgm/add_coach.do"  
		  method="post" onsubmit="return checkForm()">
		  <input type="hidden"  id="kk" name="services" />
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="70%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								名字：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="name" style="width: 360px;" placeholder="请输入教练名字"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								密码：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="password"
										   name="pass" style="width: 360px;" placeholder="请输入密码"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								年龄：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="age" style="width: 360px;" placeholder="请输入教练年龄"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								电话：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="phone" id="phone" onchange="checkPhone()" style="width: 360px;" placeholder="请输入电话号码"
										   class="col-xs-10 col-sm-5" required  maxlength="11"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								身高：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="height" style="width: 360px;" placeholder="请输入身高(CM)"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								体重：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="weight"   style="width: 360px;" placeholder="请输入体重(KG)"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								个性签名：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="personalitySignature" style="width: 360px;" placeholder="请输入个性签名"
										   class="col-xs-10 col-sm-5" required />
								</div>
							</td>
						</tr>			
						<tr>
							<td>
								教学年限：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="teaching" style="width: 360px;" placeholder="请输入教学年限"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
                
            			<tr >
							<td>
								执教经历：
							</td>
							<td >
								
								<ul class="lz-user-list">
						            <li class="lz-indenting" style="padding-left: 10px;">
						                <span class="lz-right" onclick="addDate(this);"><i class="lz-iconfont lz-icon-jia"></i><span id="error" style="color: red;"></span></span>
						           </li>
						       </ul>
							</td>
						</tr>
						<tr>
							<td>
								价格：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
<!-- 									<input type="text" -->
<!-- 										   name="price" style="width: 360px;" placeholder="请输入价格" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
										   
								   <input type="tel" id="price"   name="price"
			                			 onkeyup="clearNoNum(this)" class="col-xs-10 col-sm-5"  style="width: 360px;" 
			                		placeholder="请输入价格"  required  />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								最低价：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="money"  id="money"  style="width: 360px;" onkeyup="clearNoNum(this)"  placeholder="请输入最低价"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						
						
						<tr>
							<td>
								现居住地：<span style="color: red;">*</span>
							</td>
							<td >
								 <select id="user_province"  onchange="getCity('user_province')" class="bankSelected" required >
									<option value="">请选择省份</option>
									<c:forEach items="${provincelist}" var="p">
										<option value="${p.region_id}" <c:if test="${ca.user_province==p.region_id }">selected="selected"</c:if> >${p.region_name}</option>
									</c:forEach>
								</select>	
						        <select  id="user_city" name="now_live_city" onchange="getArea('user_city')"  class="bankSelected" required >
									<option value="" selected="selected" >请选择城市</option>
								</select>
								<select id="user_area" name="areaid" class="bankSelected" required  >
									<option value=""  selected="selected" >请选择区、县</option>
								</select>	
							</td>
						</tr>
						<tr>
							<td>
								教学等级：<span style="color: red;">*</span>
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="teachtype" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="初级">初级</option>
											<option value="中级">中级</option>
											<option value="高级">高级</option>
										</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								教练专长：<span style="color: red;">*</span>
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="professional" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="少儿(业余)">少儿(业余)</option>
											<option value="成人(业余)">成人(业余)</option>
											<option value="全部(业余)">全部(业余)</option>
											<option value="职业">职业</option>
										</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								教练类型：<span style="color: red;">*</span>
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="coachType"  id="coachType" style="width: 360px;" class="col-xs-10 col-sm-5" required onclick="belongtopg(this);">
<!-- 后台只能添加驻场教练			edit by lxc	2015-12-15 -->
<!-- 											<option value="1">自由教练</option> -->
											<option value="2" selected="selected" >驻场教练</option>
<!-- 											<option value="3">场馆运营者</option> -->
										</select>
								</div>
							</td>
						</tr>
						<tr id="belongtopg">
							<td>
								所属场馆：
							</td>
							<td >
								<div class="col-sm-9">
									<select name="playground_id" id="playground_id" style="width: 360px;" class="col-xs-10 col-sm-5" >
										<c:forEach var="play" items="${groundList }">
											<c:if test="${not empty pgm }" var="hava">
												<c:if test="${pgm.pgm_president==play.playgroundmanager_id }">
													<option value="${play.id }">${play.name }</option>
												</c:if>
											</c:if>
											<c:if test="${!hava }">
												<option value="${play.id }">${play.name }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								国籍：<span style="color: red;">*</span>
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="nationality" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="中教">中教</option>
											<option value="外教">外教</option>
										</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								性别：<span style="color: red;">*</span>
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="sex" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="男">男</option>
											<option value="女">女</option>
										</select></div>
						<tr>
						
						<tr>
							<td>
								学历：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
										<select name="educationalBackground" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="大专及以下">大专及以下</option>
											<option value="本科">本科</option>
											<option value="研究生及以上">研究生及以上</option>
										</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								毕业院校：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="shcool" style="width: 360px;" placeholder="请输入毕业院校"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								专业：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="schoolzy" style="width: 360px;" placeholder="请输入专业"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								擅长技能：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="goodAt" style="width: 360px;" placeholder="请输入擅长技能"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								开户银行：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="bank" style="width: 360px;" placeholder="请输入开户银行"
										   class="col-xs-10 col-sm-5" />
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								支行：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="zbank" style="width: 360px;" placeholder="请输入支行"
										   class="col-xs-10 col-sm-5" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								银行帐号：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="tel"  style="IME-MODE: disabled;  width: 360px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                								onafterpaste="this.value=this.value.replace(/\D/g,'')"
										   name="bankZh" id="bankZh" maxlength="19"  placeholder="请输入银行帐号"
										   class="col-xs-10 col-sm-5" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								户名：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="bankName" style="width: 360px;" placeholder="请输入户名"
										   class="col-xs-10 col-sm-5" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								身份证号：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="idcard_no" onchange="checkIdcard_no()" id="idcard_no" style="width: 360px;" placeholder="请输入身份证号"
										   class="col-xs-10 col-sm-5"  maxlength="18" required />
										   <span id="noerroe"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								服务：
							</td> 
							<td >
								<div class="col-sm-9">
									<c:forEach items="${ds }" var="d" varStatus="v">
										<c:if test="${d.service  == '提供器材' || d.service  == '驻场服务' }">
											${d.service }<input type="checkbox" name="service" value="${d.id }" />
										</c:if>
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								头像：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="file" class="input_text" onchange="upload(this, fnOne , 1);"  style="width: 200px;" required/>
									<input type="hidden" name="head_portrait" id="head_portrait"  >
									&nbsp;&nbsp;<span id="tips">*头像</span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								身份证信息：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne7 , 3);" required/>
									<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">身份证正面</span> <input type="hidden" name="ph7" id="ph7" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne8 , 3);" required/>
									<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">身份证背面</span> <input type="hidden" name="ph8" id="ph8" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne9 , 3);" required/>
									<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">本人手持身份证照片</span> <input type="hidden" name="ph9" id="ph9" >
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								资质证书：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne1 , 3);" />
									&nbsp;&nbsp;<span id="tips">ITF</span> <input type="hidden" name="ph1" id="ph1" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne2 , 3);" />
									&nbsp;&nbsp;<span id="tips">USPTA</span> <input type="hidden" name="ph2" id="ph2" >
									<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne3 , 3);" />
									&nbsp;&nbsp;<span id="tips">PTR</span> <input type="hidden" name="ph3" id="ph3" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne4 , 3);" />
									&nbsp;&nbsp;<span id="tips">RPT</span> <input type="hidden" name="ph4" id="ph4" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne5 , 3);" />
									&nbsp;&nbsp;<span id="tips">Equelite</span> <input type="hidden" name="ph5" id="ph5" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne6 , 3);" />
									&nbsp;&nbsp;<span id="tips">CTA</span> <input type="hidden" name="ph6" id="ph6" >
								</div>
							</td>
						</tr>
						
<!-- 						<tr > -->
<!-- 							<td width="100px" > -->
<!-- 								状态： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<input type="radio" name="status" value="1" checked>申请 -->
<!-- 									<input type="radio" name="status" value="2">开通 -->
<!-- 									<input type="radio" name="status" value="3">不通过 -->
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						
						<tr >
							<td width="100px" >
								预订场地：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="is_reserve" value="1" checked>可以
									<input type="radio" name="is_reserve" value="0">不可以
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								用户预订：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="reserve_me" value="1" checked>可以
									<input type="radio" name="reserve_me" value="0">不可以
								</div>
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
<script type="text/javascript">
function upload(element, fn,type) {

    if (!window.FormData) {
      return alert('请换一个支持H5的浏览器');
    }
    var fd = new FormData();
    fd.append('imgName', element.files[0]);

    var xhr = new XMLHttpRequest();
    
    var folder = ""; 
    if(type == 1){
    	folder = "coach";
    }else if(type == 2){
    	folder = "playpround";
    }else if(type == 3){
    	folder = "certificate";
    }
    
    xhr.open('post', '/pgm/upload_picture.do?folder='+folder+'&type='+type+'', true);
    xhr.send(fd); //发送文件
    xhr.onload = function(e) {
      if (this.status == 200) {
        fn(this.responseText);
      }
    };
  }
  function fnOne(data) {
	var obj = $.parseJSON(data);
	$("#head_portrait").val(obj.data);
  };
  function fnOne1(data) {
	  var obj = $.parseJSON(data);
	  $("#ph1").val(obj.data);
  };
  function fnOne2(data) {
	  var obj = $.parseJSON(data);
	  $("#ph2").val(obj.data);
  };
  function fnOne3(data) {
	  var obj = $.parseJSON(data);
	  $("#ph3").val(obj.data);
  };
  function fnOne4(data) {
	  var obj = $.parseJSON(data);
	  $("#ph4").val(obj.data);
  };
  function fnOne5(data) {
	  var obj = $.parseJSON(data);
	  $("#ph5").val(obj.data);
  };
  function fnOne6(data) {
	  var obj = $.parseJSON(data);
	  $("#ph6").val(obj.data);
  };
  
  function fnOne7(data) {
	  var obj = $.parseJSON(data);
	  $("#ph7").val(obj.data);
  };
  function fnOne8(data) {
	  var obj = $.parseJSON(data);
	  $("#ph8").val(obj.data);
  };
  function fnOne9(data) {
	  var obj = $.parseJSON(data);
	  $("#ph9").val(obj.data);
  };
</script>
</html>
