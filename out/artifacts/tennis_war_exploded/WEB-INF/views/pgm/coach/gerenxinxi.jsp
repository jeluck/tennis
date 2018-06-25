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
});

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

</script>
</head>
<body>
	<c:if test="${not empty tip }">
		<script type="text/javascript">
			var tip = "${tip}";
			if(tip.indexOf("tip")>-1){
				alert('没有一个场馆审核通过并且是可预订状态或者带有超过两个场馆的运营者不能在前台登录!');
			}else{
				if(tip.indexOf("success")>-1){
					if(tip.indexOf("no_reserve")>-1){
						alert('成功,没有一个场馆审核通过并且是可预订状态,暂不能在前台登录');
					}else{
						alert('成功');
					}
				}
				if(tip.indexOf("error")>-1){
					alert('失败');
				}
			}
		</script>
	</c:if>
	
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">个人信息</li>
			<li class="active">个人信息</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>个人信息 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="/pgm/gerenxinxiedit_coach.do"  
		  method="post"  onsubmit="return checkForm()">
		<input type="hidden"  id="kk" name="services" />
		<input type="hidden" id="coId" value="${o.id}" name="id" />
		<input type="hidden" value="${o.userid.id}" name="userId" />
		<input type="hidden" value="${qc.id}" name="pcId" />
		<input type="hidden" value="${o.services}" id="ser" />
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="70%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								名字：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="name" style="width: 360px;" placeholder="请输入教练名字"
										   class="col-xs-10 col-sm-5" required  value="${o.name }"/>
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								年龄：
							</td>
							<td >
								
								<div class="col-sm-9">
									<input type="text"
										   name="age" style="width: 360px;" placeholder="请输入教练年龄"
										   class="col-xs-10 col-sm-5" required  <c:if test="${o.age > 0 }"> value="${o.age }" </c:if>  />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								电话：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="hidden"
										   name="phone" id="phone" style="width: 360px;" placeholder="请输入电话号码"
										   class="col-xs-10 col-sm-5"  value="${o.phone }" maxlength="11"/>
										   ${o.phone }
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								身高：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="height" style="width: 360px;" placeholder="请输入身高(CM)"
										   class="col-xs-10 col-sm-5" required  <c:if test="${o.height > 0 }"> value="${o.height }" </c:if> />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								体重：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="weight"   style="width: 360px;" placeholder="请输入体重(KG)"
										   class="col-xs-10 col-sm-5" required <c:if test="${o.weight > 0 }"> value="${o.weight }" </c:if>  />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								个性签名：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="personalitySignature" style="width: 360px;" placeholder="请输入个性签名"
										   class="col-xs-10 col-sm-5" required value="${o.personalitySignature }" />
								</div><span style="color: red;">*</span>
							</td>
						</tr>						
						<tr>
							<td>
								教学年限：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="teaching" style="width: 360px;" placeholder="请输入教学年限"
										   class="col-xs-10 col-sm-5" required   <c:if test="${o.teaching > 0 }"> value="${o.teaching }" </c:if> />
								</div><span style="color: red;">*</span>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								价格： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<input type="text" -->
<!-- 										   name="price" style="width: 360px;" placeholder="请输入价格" -->
<%-- 										   class="col-xs-10 col-sm-5" required value="${o.price }" /> --%>
<!-- 								</div><span style="color: red;">*</span> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								最低价： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<input type="text" -->
<!-- 										   name="money" style="width: 360px;" placeholder="请输入最低价" -->
<%-- 										   class="col-xs-10 col-sm-5" required  value="${o.money }" /> --%>
<!-- 								</div><span style="color: red;">*</span> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<td>
								现居住地：
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
									
								</select>		<span style="color: red;">*</span>
							</td>
						</tr>
						<tr>
							<td>
								教学等级：
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="teachtype" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="" selected="selected">请选择</option>
											<option value="初级" <c:if test="${o.teachtype == '初级' }">selected="selected"</c:if> >初级</option>
											<option value="中级" <c:if test="${o.teachtype == '中级' }">selected="selected"</c:if> >中级</option>
											<option value="高级" <c:if test="${o.teachtype == '高级' }">selected="selected"</c:if> >高级</option>
										</select>
								</div><span style="color: red;">*</span>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								教练专业： -->
<!-- 							</td>  -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 										<select name="professional" style="width: 360px;" class="col-xs-10 col-sm-5" required> -->
<%-- 											<option value="少儿(业余)" <c:if test="${o.professional == '少儿(业余)' }">selected="selected"</c:if> >少儿(业余)</option> --%>
<%-- 											<option value="成人(业余)" <c:if test="${o.professional == '成人(业余)' }">selected="selected"</c:if> >成人(业余)</option> --%>
<%-- 											<option value="全部(业余)" <c:if test="${o.professional == '全部(业余)' }">selected="selected"</c:if> >全部(业余)</option> --%>
<%-- 											<option value="职业" <c:if test="${o.professional == '职业' }">selected="selected"</c:if> >职业</option> --%>
<!-- 										</select> -->
<!-- 								</div><span style="color: red;">*</span> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								国籍： -->
<!-- 							</td>  -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 										<select name="nationality" style="width: 360px;" class="col-xs-10 col-sm-5" required> -->
<%-- 											<option value="中教" <c:if test="${o.nationality == '中教' }">selected="selected"</c:if> >中教</option> --%>
<%-- 											<option value="外教" <c:if test="${o.nationality == '外教' }">selected="selected"</c:if> >外教</option> --%>
<!-- 										</select> -->
<!-- 								</div><span style="color: red;">*</span> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<td>
								性别：
							</td> 
							<td >
								<div class="col-sm-9">
										<select name="sex" style="width: 360px;" class="col-xs-10 col-sm-5" required>
											<option value="男" <c:if test="${o.sex == '男' }">selected="selected"</c:if> >男</option>
											<option value="女" <c:if test="${o.sex == '女' }">selected="selected"</c:if> >女</option>
										</select>
								</div><span style="color: red;">*</span>
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
										   class="col-xs-10 col-sm-5" value="${o.bank }"  />
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
										   class="col-xs-10 col-sm-5"  value="${o.zbank }" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								银行帐号：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" style="IME-MODE: disabled;  width: 360px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                								onafterpaste="this.value=this.value.replace(/\D/g,'')"
										   name="bankZh"  placeholder="请输入银行帐号"
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
										   class="col-xs-10 col-sm-5" value="${o.bankName }"  >
								</div>
							</td>
						</tr>
						
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								服务： -->
<!-- 							</td>  -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<%-- 									<c:forEach items="${ds }" var="d" varStatus="v"> --%>
<%-- 										${d.service }<input type="checkbox"  name="service"  value="${d.id }"  /> --%>
<%-- 									</c:forEach> --%>
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td width="120px"> -->
<!-- 								头像： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<div class="row"> -->
<%-- 										<div class="col-md-1"><input type="hidden" value="${o.head_portrait }" id="head_portrait" name="head_portrait" > --%>
<!-- 										<input type="file" class="input_text" onchange="upload(this, fnOne , 1);"  style="width: 200px;" />&nbsp;&nbsp;<span id="tips">*头像</span></div> -->
<!-- 										<div class="col-md-1"> -->
<%-- 											<img alt="" src="/${o.head_portrait }" width="100px" onclick="onshowimg('/${o.head_portrait }',2)"> --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->

<input type="hidden" value="nouse_but_must" id="head_portrait"  >
<!-- 						<tr> -->
<!-- 							<td width="120px"> -->
<!-- 								资质证书： -->
<!-- 							</td> -->
<!-- 							<td > -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<div class="row">  -->
<%-- 										<div class="col-md-1">.<input type="hidden" value="${qc.ph1 }" name="z1" id="z1"> --%>
<!-- 										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne1 , 3);" /> -->
<!-- 										&nbsp;&nbsp;<span id="tips">ITF</span></div> -->
<%-- 										<div class="col-md-1"><img alt="" src="/${qc.ph1 }" width="100px" onclick="onshowimg('/${qc.ph1 }',2)"></div> --%>
<!-- 									</div> -->
									
<!-- 									<div class="row">  -->
<%-- 										<div class="col-md-1">.<input type="hidden" value="${qc.ph2 }" name="z2" id="z2" > --%>
<!-- 										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne2 , 3);" /> -->
<!-- 										&nbsp;&nbsp;<span id="tips">USPTA</span></div> -->
<%-- 										<div class="col-md-1"><img alt="" src="/${qc.ph2 }" width="100px" onclick="onshowimg('/${qc.ph2 }',2)"></div> --%>
<!-- 									</div> -->
									
<!-- 									<div class="row">  -->
<%-- 										<div class="col-md-1">.<input type="hidden" value="${qc.ph3 }" name="z3" id="z3" > --%>
<!-- 										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne3 , 3);" /> -->
<!-- 										&nbsp;&nbsp;<span id="tips">PTR</span></div> -->
<%-- 										<div class="col-md-1"><img alt="" src="/${qc.ph3 }" width="100px" onclick="onshowimg('/${qc.ph3 }',2)"></div> --%>
<!-- 									</div> -->
									
<!-- 									<div class="row">  -->
<%-- 										<div class="col-md-1">.<input type="hidden" value="${qc.ph4 }" name="z4" id="z4" > --%>
<!-- 										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne4 , 3);" /> -->
<!-- 										&nbsp;&nbsp;<span id="tips">RPT</span></div> -->
<%-- 										<div class="col-md-1"><img alt="" src="/${qc.ph4 }" width="100px" onclick="onshowimg('/${qc.ph4 }',2)"></div> --%>
<!-- 									</div> -->
									
<!-- 									<div class="row">  -->
<%-- 										<div class="col-md-1">.<input type="hidden" value="${qc.ph5 }" name="z5" id="z5" > --%>
<!-- 										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne5 , 3);" /> -->
<!-- 										&nbsp;&nbsp;<span id="tips">Equelite</span></div> -->
<%-- 										<div class="col-md-1"><img alt="" src="/${qc.ph5 }" width="100px" onclick="onshowimg('/${qc.ph5 }',2)"></div> --%>
<!-- 									</div> -->
									
<!-- 									<div class="row">  -->
<%-- 										<div class="col-md-1">.<input type="hidden" value="${qc.ph6 }" name="z6" id="z6" > --%>
<!-- 										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne6 , 3);" /> -->
<!-- 										&nbsp;&nbsp;<span id="tips">CTA</span></div> -->
<%-- 										<div class="col-md-1"><img alt="" src="/${qc.ph6 }" width="100px" onclick="onshowimg('/${qc.ph6 }',2)"></div> --%>
<!-- 									</div> -->
									
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						
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
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
<jsp:include page="../../admin/common_image_show.jsp"></jsp:include>
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
