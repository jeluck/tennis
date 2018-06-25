<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改教练信息</title>
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
$(function(){
	var ser = $("#ser").val();
	var strs = ser.split(",");
	for(var i = 0; i < strs.length; i++) {
		$("input[name='service']").not("[checked]").each(function(y){
			if($(this).val()==strs[i]){
				$(this).attr("checked", "checked");
			}
		});
	}
	
	var head_portrait = "${o.head_portrait }";
	var length = head_portrait.lastIndexOf("/")+1;
	var imgurl_mulu = head_portrait.substring(0, length);
	var imgUrl = head_portrait.substring(head_portrait.lastIndexOf("/")+1);
	imgUrl = imgUrl.substring(imgUrl.indexOf("_")+1);
	$("#portrait").val("/"+(imgurl_mulu+imgUrl));
	
});

function onshowimg2(){
	onshowimg($("#portrait").val(),2);
}

function checkPhone(){
	var reg = /^((0?1[358]\d{9})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
	if(!reg.test($("#phone").val())){
		alert("电话格式不正确");
		return 0;
	}
	var info = util.POST("/pgm/updatecheckCoachPhone.do",{"phone":$("#phone").val(),"id":$("#coId").val()});
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
	var info = util.POST("/pgm/checkIdcard_no.do",{"idcard_no":idcard_no,"id":$("#userId").val()});
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
			<li class="active">修改教练信息</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改教练信息 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="/pgm/edit_coach.do"  
		  method="post"  onsubmit="return checkForm()">
		<input type="hidden"  id="kk" name="services" />
		<input type="hidden" id="coId" value="${o.id}" name="id" />
		<input type="hidden" value="${o.userid.id}" name="userId" id="userId"/>
		<input type="hidden" value="${qc.id}" name="pcId" />
		<input type="hidden" value="${o.services}" id="ser" />
		
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
										   class="col-xs-10 col-sm-5" required  value="${o.name }"/>
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
										   class="col-xs-10 col-sm-5" required value="${o.age }" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								电话：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="hidden"
										   name="phone" id="phone" onchange="checkPhone()" style="width: 360px;" placeholder="请输入电话号码"
										   class="col-xs-10 col-sm-5" value="${o.phone }" maxlength="11"/>
										   ${o.phone }
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
										   class="col-xs-10 col-sm-5" required value="${o.height }" />
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
										   class="col-xs-10 col-sm-5" required value="${o.weight }" />
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
										   class="col-xs-10 col-sm-5" required value="${o.personalitySignature }" />
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
										   class="col-xs-10 col-sm-5" required  value="${o.teaching }"/>
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
						           <c:forEach items="${clist }" var="o">
							           <li class="lz-addunit">    
							           		<div class="row">    
								           		<div class="col-md-3">
								           			<input type="text" value="${o.unitName }" placeholder="单位名称" name="unitName"></div>
								           	    <div class="col-md-3">
								           	    	<input type="text" value="${o.begin_time }" placeholder="开始时间" onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})"style="width: 150px;" name="begin_time" />
								           	    </div>    
								           	    <div class="col-md-3">
								           	   		<input type="text" value="${o.end_time }" placeholder="结束时间" onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})"style="width: 150px;" name="end_time" />
								           	    </div>    
								           	    <div style="text-align: right;" onclick="removeExper(this);" class="col-md-3">
								           	    	<i class="lz-iconfont lz-icon-guanbi"></i>
								           	    </div>  
							           	    </div>    
							           </li>
						            </c:forEach>
						       </ul>
							</td>
						</tr>
						
						<tr>
							<td>
								价格：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="price" id="price" style="width: 360px;" placeholder="请输入价格"  onkeyup="clearNoNum(this)" 
										   class="col-xs-10 col-sm-5" required value="${o.price }" />
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
										   name="money" id="money" style="width: 360px;" placeholder="请输入最低价"  onkeyup="clearNoNum(this)" 
										   class="col-xs-10 col-sm-5" required  value="${o.money }" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								现居住地：<span style="color: red;">*</span>
							</td>
							<td >
								<select id="user_province" style="width: 110px;"  onchange="getCity('user_province')" class="bankSelected" required >
									<option value="">请选择省份</option>
									<c:forEach items="${provincelist}" var="p">
										<option value="${p.region_id}" <c:if test="${sid == p.region_id }"> selected="selected" </c:if> >${p.region_name}</option>
									</c:forEach>
								</select>	
						        <select  id="user_city" style="width: 110px;" name="now_live_city" onchange="getArea('user_city')"  class="bankSelected" required >
						        	<option value=""  <c:if test="${r.region_id < 0  }">selected="selected" </c:if> >请选择城市</option>
						        	<c:if test="${r.region_id > 0  }">
						        		<option value="${r.region_id }"  selected="selected" >${r.region_name }</option>
						        	</c:if>
									
								</select>
								<select id="user_area" style="width: 110px;" name="areaid" class="bankSelected"  required >
									<option value="" <c:if test="${a.region_id < 0  }">selected="selected" </c:if> >请选择区县</option>
									 <c:if test="${a.region_id > 0  }">
									 	<option value="${a.region_id}" selected="selected"  >${a.region_name}</option>
									 </c:if> 
									
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
											<option value="初级" <c:if test="${o.teachtype == '初级' }">selected="selected"</c:if> >初级</option>
											<option value="中级" <c:if test="${o.teachtype == '中级' }">selected="selected"</c:if> >中级</option>
											<option value="高级" <c:if test="${o.teachtype == '高级' }">selected="selected"</c:if> >高级</option>
										</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								教练专业：<span style="color: red;">*</span>
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="professional" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="少儿(业余)" <c:if test="${o.professional == '少儿(业余)' }">selected="selected"</c:if> >少儿(业余)</option>
											<option value="成人(业余)" <c:if test="${o.professional == '成人(业余)' }">selected="selected"</c:if> >成人(业余)</option>
											<option value="全部(业余)" <c:if test="${o.professional == '全部(业余)' }">selected="selected"</c:if> >全部(业余)</option>
											<option value="职业" <c:if test="${o.professional == '职业' }">selected="selected"</c:if> >职业</option>
										</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								教练类型：<span style="color: red;">*</span>
							</td> 
							<td >
							<c:if test="${empty check }" >
								<div class="col-sm-9">
										<select name="coachType" style="width: 360px;" class="col-xs-10 col-sm-5" required onclick="belongtopg(this);" xdisabled="disabled">
										<!-- 后台只能添加驻场教练			edit by lxc	2015-12-15 -->
<%-- 											<option value="1" <c:if test="${o.coachType == '1' }">selected="selected"</c:if>  >自由教练</option> --%>
											<option value="2" <c:if test="${o.coachType == '2' }">selected="selected"</c:if> >驻场教练</option>
<%-- 											<option value="3" <c:if test="${o.coachType == '3' }">selected="selected"</c:if> >场馆运营者</option> --%>
										</select>
								</div>
							</c:if>
							<c:if test="${not empty check }">
								<div class="col-sm-9" style="display:none;">
										<select name="coachType" style="width: 360px;" class="col-xs-10 col-sm-5" required onclick="belongtopg(this);" xdisabled="disabled" >
										<!-- 后台只能添加驻场教练			edit by lxc	2015-12-15 -->
<%-- 											<option value="1" <c:if test="${o.coachType == '1' }">selected="selected"</c:if>  >自由教练</option> --%>
											<option value="2" <c:if test="${o.coachType == '2' }">selected="selected"</c:if> >驻场教练</option>
<%-- 											<option value="3" <c:if test="${o.coachType == '3' }">selected="selected"</c:if> >场馆运营者</option> --%>
										</select>
								</div>
								<div class="col-sm-9">
								<c:if test="${o.coachType == '1' }">自由教练</c:if>
										<c:if test="${o.coachType == '2' }">驻场教练</c:if>
										<c:if test="${o.coachType == '3' }">场馆运营者</c:if>
								</c:if>
								</div>
							</td>
						</tr>
						<tr id="belongtopg"  <c:if test="${o.coachType == '1'  }">style="display: none;"</c:if> >
							<td>
								所属场馆：
							</td>
							<td >
								<div class="col-sm-9">
									<select name="playground_id" style="width: 360px;" class="col-xs-10 col-sm-5" >
										<c:forEach var="play" items="${groundList }">
											<c:if test="${not empty pgm }" var="hava">
												<c:if test="${pgm.pgm_president==play.playgroundmanager_id }">
													<option value="${play.id }" <c:if test="${play.id == o.playground_id }">selected="selecte"</c:if> >${play.name }</option>
												</c:if>
											</c:if>
											<c:if test="${!hava }">
												<option value="${play.id }" <c:if test="${play.id == o.playground_id }">selected="selecte"</c:if> >${play.name }</option>
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
											<option value="中教" <c:if test="${o.nationality == '中教' }">selected="selected"</c:if> >中教</option>
											<option value="外教" <c:if test="${o.nationality == '外教' }">selected="selected"</c:if> >外教</option>
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
											<option value="男" <c:if test="${o.sex == '男' }">selected="selected"</c:if> >男</option>
											<option value="女" <c:if test="${o.sex == '女' }">selected="selected"</c:if> >女</option>
										</select>
								</div>
							</td>
						</tr>
						
						
						<tr>
							<td>
								学历：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
										<select name="educationalBackground" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="大专及以下" <c:if test="${o.educationalBackground == '大专及以下' }">selected="selected"</c:if> >大专及以下</option>
											<option value="本科" <c:if test="${o.educationalBackground == '本科' }">selected="selected"</c:if> >本科</option>
											<option value="研究生及以上" <c:if test="${o.educationalBackground == '研究生及以上' }">selected="selected"</c:if> >研究生及以上</option>
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
										   class="col-xs-10 col-sm-5" required  value="${o.userid.school }"  />
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
										   class="col-xs-10 col-sm-5" required value="${o.goodAt }" />
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
										   class="col-xs-10 col-sm-5" value="${o.bank }" />
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
										   class="col-xs-10 col-sm-5"  value="${o.zbank }"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								银行帐号：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" style="IME-MODE: disabled;  width: 360px; " onkeyup="this.value=this.value.replace(/\D/g,'')"  
                								onafterpaste="this.value=this.value.replace(/\D/g,'')"
										   name="bankZh"  placeholder="请输入银行帐号" maxlength="19" id="bankZh" 
										   class="col-xs-10 col-sm-5" value="${o.bankZh }" />
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
										   class="col-xs-10 col-sm-5" value="${o.bankName }" >
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
										   name="idcard_no" id="idcard_no" onclick="checkIdcard_no()" style="width: 360px;" placeholder="请输入身份证号"
										   class="col-xs-10 col-sm-5" value="${o.userid.idcard_no}"  maxlength="18" required />
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
										<c:if test="${d.service  == '提供器材' || d.service  == '驻场服务'}">
											${d.service }<input type="checkbox" name="service" value="${d.id }" />
										</c:if>
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								头像：
							</td>
							<td >
								<div class="col-sm-9">
									<div class="row">
										<div class="col-md-1"><input type="hidden" value="${o.head_portrait }" id="head_portrait" name="head_portrait" >
										<input type="file" class="input_text" onchange="upload(this, fnOne , 1);"  style="width: 200px;" />&nbsp;&nbsp;<span id="tips">*头像</span></div>
										<div class="col-md-1">
											<img alt="" id="toux" src="/${o.head_portrait }" width="100px" onclick="onshowimg2()">
										</div>
										<input type="hidden" id="portrait"  >
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								身份证信息：
							</td>
							<td >
								<div class="col-sm-9">
									<div class="row"> 
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne7 , 3);" 
										<c:if test="${empty qc.ph7 }"  >required</c:if> />
										<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">身份证正面</span> <input type="hidden" value="${qc.ph7 }" name="z7" id="z7" >
										<div class="col-md-6"><img alt="" src="/${qc.ph7 }" width="100px" onclick="onshowimg('/${qc.ph7 }',2)"></div>
									</div>
									<div class="row"> 
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne8 , 3);" 
										<c:if test="${empty qc.ph8 }"  >required</c:if> />
										<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">身份证背面</span> <input type="hidden" value="${qc.ph8 }"  name="z8" id="z8" >
										<div class="col-md-6"><img alt="" src="/${qc.ph8 }" width="100px" onclick="onshowimg('/${qc.ph8 }',2)"></div>
									</div>
									<div class="row"> 
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne9 , 3);" 
										<c:if test="${empty qc.ph9 }"  >required</c:if> />
										<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">本人手持身份证照片</span> <input type="hidden" value="${qc.ph9 }"  name="z9" id="z9" >
										<div class="col-md-6"><img alt="" src="/${qc.ph9 }" width="100px" onclick="onshowimg('/${qc.ph9 }',2)"></div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								资质证书：
							</td>
							<td >
								<div class="col-sm-9">
									<div class="row"> 
										<div class="col-md-1">.<input type="hidden" value="${qc.ph1 }" name="z1" id="z1">
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne1 , 3);" />
										&nbsp;&nbsp;<span id="tips">ITF</span></div>
										<div class="col-md-1"><img alt="" src="/${qc.ph1 }" width="100px" onclick="onshowimg('/${qc.ph1 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-1">.<input type="hidden" value="${qc.ph2 }" name="z2" id="z2" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne2 , 3);" />
										&nbsp;&nbsp;<span id="tips">USPTA</span></div>
										<div class="col-md-1"><img alt="" src="/${qc.ph2 }" width="100px" onclick="onshowimg('/${qc.ph2 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-1">.<input type="hidden" value="${qc.ph3 }" name="z3" id="z3" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne3 , 3);" />
										&nbsp;&nbsp;<span id="tips">PTR</span></div>
										<div class="col-md-1"><img alt="" src="/${qc.ph3 }" width="100px" onclick="onshowimg('/${qc.ph3 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-1">.<input type="hidden" value="${qc.ph4 }" name="z4" id="z4" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne4 , 3);" />
										&nbsp;&nbsp;<span id="tips">RPT</span></div>
										<div class="col-md-1"><img alt="" src="/${qc.ph4 }" width="100px" onclick="onshowimg('/${qc.ph4 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-1">.<input type="hidden" value="${qc.ph5 }" name="z5" id="z5" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne5 , 3);" />
										&nbsp;&nbsp;<span id="tips">Equelite</span></div>
										<div class="col-md-1"><img alt="" src="/${qc.ph5 }" width="100px" onclick="onshowimg('/${qc.ph5 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-1">.<input type="hidden" value="${qc.ph6 }" name="z6" id="z6" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne6 , 3);" />
										&nbsp;&nbsp;<span id="tips">CTA</span></div>
										<div class="col-md-1"><img alt="" src="/${qc.ph6 }" width="100px" onclick="onshowimg('/${qc.ph6 }',2)"></div>
									</div>
									
								</div>
							</td>
						</tr>
						
<!-- 						<tr > -->
<!-- 							<td width="100px" > -->
<!-- 								状态： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<%-- 									<input type="radio" name="status" value="1" <c:if test="${o.status == 1}">checked="checked"</c:if> >申请 --%>
<%-- 									<input type="radio" name="status" value="2" <c:if test="${o.status == 2}">checked="checked"</c:if> >开通 --%>
<%-- 									<input type="radio" name="status" value="3" <c:if test="${o.status == 3}">checked="checked"</c:if> >不通过 --%>
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						
						<tr >
							<td width="100px" >
								预订场地：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="is_reserve" value="1" <c:if test="${o.is_reserve == 1}">checked="checked"</c:if> >可以
									<input type="radio" name="is_reserve" value="0" <c:if test="${o.is_reserve == 0}">checked="checked"</c:if> >不可以
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								用户预订：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="reserve_me" value="1" <c:if test="${o.reserve_me == 1}">checked="checked"</c:if> >可以
									<input type="radio" name="reserve_me" value="0" <c:if test="${o.reserve_me == 0}">checked="checked"</c:if> >不可以
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
					<c:if test="${empty check }">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
					</c:if>
					<c:if test="${check == 'check'}">
						<button class="btn btn-sm btn-success" type="button" onclick="location='coach_list.do'"> [ 返回 ]
					</c:if>
					<c:if test="${check == 'nocheck'}">
						<button class="btn btn-sm btn-success" type="button" onclick="location='coach_check.do'"> [ 返回 ]
					</c:if>
					<c:if test="${check == 'applycheck'}">
						<button class="btn btn-sm btn-success" type="button" onclick="location='coach_apply_list.do'"> [ 返回 ]
					</c:if>
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
<jsp:include page="../common_image_show.jsp"></jsp:include>
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
	  $("#z1").val(obj.data);
  };
  function fnOne2(data) {
	  var obj = $.parseJSON(data);
	  $("#z2").val(obj.data);
  };
  function fnOne3(data) {
	  var obj = $.parseJSON(data);
	  $("#z3").val(obj.data);
  };
  function fnOne4(data) {
	  var obj = $.parseJSON(data);
	  $("#z4").val(obj.data);
  };
  function fnOne5(data) {
	  var obj = $.parseJSON(data);
	  $("#z5").val(obj.data);
  };
  function fnOne6(data) {
	  var obj = $.parseJSON(data);
	  $("#z6").val(obj.data);
  };
  
  function fnOne7(data) {
	  var obj = $.parseJSON(data);
	  $("#z7").val(obj.data);
  };
  function fnOne8(data) {
	  var obj = $.parseJSON(data);
	  $("#z8").val(obj.data);
  };
  function fnOne9(data) {
	  var obj = $.parseJSON(data);
	  $("#z9").val(obj.data);
  };
  
  //当教练在前台选择了市,则把当前市下的区显示出来		2016-01-11		edit by lxc 
  function getfontsetArea(id){
	  var areaid = "${o.areaid}";
	  var city = id;
		var user_area = $("#user_area");
		var info = util.POST("/area.do",{"city":city});
		var str ='';
		$.each(info.data,function(n,value) {  
			var temp = "";
			if(areaid==value.region_id){
				temp = "selected";
			}
	         str+="<option value="+value.region_id+" "+temp+" >"+value.region_name+"</option>";
	    });  
		user_area.html(str);
	}
  getfontsetArea("${o.now_live_city}");
</script>
</html>
