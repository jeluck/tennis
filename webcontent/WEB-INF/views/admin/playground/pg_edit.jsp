<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3" charset="UTF-8"></script>
<script type="text/javascript">

$(function(){
	$("input[type=checkbox]").not("[checked]").each(function(y){
		if($(this).val()==1){
			$(this).attr("checked", "checked");
		}
	});
	
	
	var head_portrait = "${o.pdImg }";
	var length = head_portrait.lastIndexOf("/")+1;
	var imgurl_mulu = head_portrait.substring(0, length);
	var imgUrl = head_portrait.substring(head_portrait.lastIndexOf("/")+1);
	imgUrl = imgUrl.substring(imgUrl.indexOf("_")+1);
	$("#hImg").val("/"+(imgurl_mulu+imgUrl));
});

function onshowimg2(){
	onshowimg($("#hImg").val(),2);
}

function checkForm()
{

	var ph4 =$("#z4").val();
	var ph5 =$("#z5").val();
	var ph6 =$("#z6").val();
	var ph7 =$("#z7").val();
	var ph8 =$("#z8").val();
	
	if(ph4=="" && ph5=="" && ph6==""&& ph7==""&& ph8==""){
		alert("资质证书至少一项");
		return false;
	}
	
	var bankZh = document.getElementById('bankZh').value;
	if(bankZh.length<14){
		alert("银行卡账号格式不对")
		return false;
	}
	
// 	var flag = true;
// 	var flag2 = true;
// 	flag = checkopentime($("#opentime").val());
// 	flag2 = checkendtime($("#endtime").val());
// 	if(flag == true && flag2 == true){
// 	 	if(parseInt($("#endtime").val())>parseInt($("#opentime").val())){
	 		if(checkPhone()>0){
				if('${is_aud}' == null || '${is_aud}' == "" ){
					return true;
				}else{
					if($("#audStaus").val() == 2){
		 				if($("#effective_time").val() == null || $("#effective_time").val() == "" ){
		 					alert("请填写有效时间");
		 					return false;
		 				}else{
		 					return true;
		 				}
		 			}else if($("#audStaus").val() == 3){
		 				if($("#return_reason").val() == null || $("#return_reason").val() == "" ){
		 					alert("请填写退回理由");
		 					return false;
		 				}else{
		 					return true;
		 				}
		 			}else{
		 				return true;
		 			}
				} 	
	 		}else{
	 			return false;
	 		}
// 		}else{
// 			alert("开始时间必须小于结束时间");	
// 			return false;
// 		}
// 	}else{
// 		return false;
// 	}
	
}
function checkopentime(ss){
	var type ="^[0-9]*[1-9][0-9]*$";
    var re = new RegExp(type);
    if(ss.match(re)==null){
        alert("开始时间请输入大于零的整数!");
        return false;
    }else{
    	return true;
    }
} 
function checkendtime(ss){
	var type ="^[0-9]*[1-9][0-9]*$";
    var re = new RegExp(type);
    if(ss.match(re)==null){
        alert("结束时间请输入大于零的整数!");
        return false;
    }else{
    	return true;
    }
} 

function checkPhone(){
	var reg = /^((0?1[358]\d{9})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
	var reg2 = /^400(.*?)$/;
	if(!reg.test($("#phone").val())){
		if(!reg2.test($("#phone").val())){
			alert("电话格式不正确");
			return 0;
		}
	}
	var info = util.POST("/pgm/updatecheckPlayPhone.do",{"phone":$("#phone").val(),"id":$("#pdId").val()});
	if(info<=0){
		alert("此电话已被使用");
		return 0;
	}
	return 1;
}
</script>
</head>
<body onload="initMap(${o.coordinateslongitude },${o.coordinateslatitude })">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">场馆管理</li>
			<li class="active">修改场馆</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改场馆<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="edit_playground.do"  
		  method="post"  onsubmit="return checkForm()">
		  <input type="hidden" id="pdId" value="${o.id}" name="id" />	
		  <input type="hidden" value="${qc.id}" name="pcId" />
		  <input type="hidden" value="${ps.id }" name="psId" />
		  <input type="hidden" value="${o.return_count }" name="return_count" />
		  <input type="hidden" value="${o.stick }" name="stick" />
		  <input type="hidden" value="${o.playgroundmanager_id }" name="playgroundmanager_id" />
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								名称：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="name" style="width: 360px;" placeholder="请输入场馆名称"
										   class="col-xs-10 col-sm-5" required  value="${o.name }" />
								</div> 
							</td>
						</tr>
						<tr >
							<td width="100px" >
								场地类型：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="site_type" style="width: 360px;" class="col-xs-10 col-sm-5" required>
										<option value="" selected="selected">请选择</option>
										<option value="红土" <c:if test="${o.site_type == '红土' }">selected="selected"</c:if> >红土</option>
										<option value="硬地" <c:if test="${o.site_type == '硬地' }">selected="selected"</c:if> >硬地</option>
										<option value="草地" <c:if test="${o.site_type == '草地' }">selected="selected"</c:if> >草地</option>
										<option value="地毯" <c:if test="${o.site_type == '地毯' }">selected="selected"</c:if> >地毯</option>
									</select>
								</div> 
							</td>
						</tr>
						<tr >
							<td width="100px" >
								球场类型 ：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="court_type" style="width: 360px;" class="col-xs-10 col-sm-5" required>
										<option value="" selected="selected">请选择</option>
										<option value="普通" <c:if test="${o.court_type == '普通' }">selected="selected"</c:if> >普通球场</option>
										<option value="智能" <c:if test="${o.court_type == '智能' }">selected="selected"</c:if> >智能球场</option>
									</select>
								</div> 
							</td>
						</tr>
						<tr >
							<td width="100px" >
								空间类型：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="space_type" style="width: 360px;" class="col-xs-10 col-sm-5" required>
										<option value="" selected="selected">请选择</option>
										<option value="室内" <c:if test="${o.space_type == '室内' }">selected="selected"</c:if> >室内</option>
										<option value="室外" <c:if test="${o.space_type == '室外' }">selected="selected"</c:if> >室外</option>
									</select>
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
										省份：<span style="color: red;">*</span>
									</td>
							<td width="100px" >
						        <select id="user_province" name="provinceid" onchange="getCity('user_province')" class="bankSelected" required >
									<option value="0">请选择省份</option>
									<c:forEach items="${provincelist}" var="p">
										
										<option value="${p.region_id}" <c:if test="${p.region_id == sid }"> selected="selected" </c:if> >${p.region_name}</option>
									</c:forEach>
								</select>	
						        <select  id="user_city" name="cityid"  onchange="getArea('user_city')"  class="bankSelected" required >
									<option value="${r.region_id }" selected="selected" >${r.region_name }</option>
								</select>
						        <select id="user_area" name="areaid" class="bankSelected" required onchange="change()" >
									<option value="0">请选择区、县</option>
									<c:forEach items="${areaList}" var="a">
										<option value="${a.region_id}" <c:if test="${o.areaid==a.region_id }">selected="selected"</c:if> >${a.region_name}</option>
									</c:forEach>
								</select> 
		    				</td>
						</tr>
						<tr >
							<td width="100px" >
								具体地址：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="address" style="width: 360px;" placeholder="请输入具体地址"
										   class="col-xs-10 col-sm-5" required  value="${o.address }"/>
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
										   name="telphone" id="phone" style="width: 360px;" onchange="checkPhone()" placeholder="请输入电话号码"
										   class="col-xs-10 col-sm-5" required  value="${o.telphone }"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								原价：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="price" style="width: 360px;" placeholder="请输入原价"
										   class="col-xs-10 col-sm-5" required  value="${o.price }"/>
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
										   name="money" style="width: 360px;" placeholder="请输入最低价"
										   class="col-xs-10 col-sm-5" required value="${o.money }" />
								</div> 
							</td>
						</tr>
						
						<tr >
							<td width="120px" >
								描述：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<textarea rows="6" cols="10" xclass="col-xs-10 col-sm-5" name="details" style="width: 360px;" placeholder="请输入详细信息" required >${o.details }</textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								服务：
							</td>
							<td>
								<div class="col-sm-9" >
									wifi：<input type="checkbox" name="wifi" value="${ps.wifi }" onclick="valuechange(this)"/>　
									器材：<input type="checkbox" name="equipment" value="${ps.equipment }" onclick="valuechange(this)" />　
									更衣室：<input type="checkbox" name="locker_room" value="${ps.locker_room }" onclick="valuechange(this)" />　</br>
									储物柜：<input type="checkbox" name="lockers" value="${ps.lockers }" onclick="valuechange(this)" />　
									淋浴：<input type="checkbox" name="shower" value="${ps.shower }" onclick="valuechange(this)" />　
									贵宾室：<input type="checkbox" name="vip_room" value="${ps.vip_room }" onclick="valuechange(this)" />　　　　
									装备店：<input type="checkbox" name="equipment_shop" value="${ps.equipment_shop }" onclick="valuechange(this)" />　</br>
									食品：<input type="checkbox" name="food" value="${ps.food }" onclick="valuechange(this)" />　
									停车场：<input type="checkbox" name="parking_lot" value="${ps.parking_lot }" onclick="valuechange(this)" />　
								</div>
							</td>
						</tr>
						<tr>
							<td>
								工作营业时间：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select id="opentime" name="gopentime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="">请选择</option>
										<option <c:if test="${gopentime.start_time==0 }">selected="selected"</c:if> value="0">0</option>
										<option <c:if test="${gopentime.start_time==1 }">selected="selected"</c:if> value="1">1</option>
										<option <c:if test="${gopentime.start_time==2 }">selected="selected"</c:if> value="2">2</option>
										<option <c:if test="${gopentime.start_time==3 }">selected="selected"</c:if> value="3">3</option>
										<option <c:if test="${gopentime.start_time==4 }">selected="selected"</c:if> value="4">4</option>
										<option <c:if test="${gopentime.start_time==5 }">selected="selected"</c:if> value="5">5</option>
										<option <c:if test="${gopentime.start_time==6 }">selected="selected"</c:if> value="6">6</option>
										<option <c:if test="${gopentime.start_time==7 }">selected="selected"</c:if> value="7">7</option>
										<option <c:if test="${gopentime.start_time==8 }">selected="selected"</c:if> value="8">8</option>
										<option <c:if test="${gopentime.start_time==9 }">selected="selected"</c:if> value="9">9</option>
										<option <c:if test="${gopentime.start_time==10 }">selected="selected"</c:if> value="10">10</option>
										<option <c:if test="${gopentime.start_time==11 }">selected="selected"</c:if> value="11">11</option>
										<option <c:if test="${gopentime.start_time==12 }">selected="selected"</c:if> value="12">12</option>
										<option <c:if test="${gopentime.start_time==13 }">selected="selected"</c:if> value="13">13</option>
										<option <c:if test="${gopentime.start_time==14 }">selected="selected"</c:if> value="14">14</option>
										<option <c:if test="${gopentime.start_time==15 }">selected="selected"</c:if> value="15">15</option>
										<option <c:if test="${gopentime.start_time==16 }">selected="selected"</c:if> value="16">16</option>
										<option <c:if test="${gopentime.start_time==17 }">selected="selected"</c:if> value="17">17</option>
										<option <c:if test="${gopentime.start_time==18 }">selected="selected"</c:if> value="18">18</option>
										<option <c:if test="${gopentime.start_time==19 }">selected="selected"</c:if> value="19">19</option>
										<option <c:if test="${gopentime.start_time==20 }">selected="selected"</c:if> value="20">20</option>
										<option <c:if test="${gopentime.start_time==21 }">selected="selected"</c:if> value="21">21</option>
										<option <c:if test="${gopentime.start_time==22 }">selected="selected"</c:if> value="22">22</option>
										<option <c:if test="${gopentime.start_time==23 }">selected="selected"</c:if> value="23">23</option>
									</select>
<!-- 									<input type="text" -->
<!-- 										   id="opentime" name="gopentime"  placeholder="开始时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  />		 -->
<!-- 									<input type="text" -->
<!-- 										   id="endtime" name="gendtime"  placeholder="结束时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
										   
									<select id="endtime" name="gendtime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="">请选择</option>
										<option <c:if test="${gopentime.end_time==0 }">selected="selected"</c:if> value="0">0</option>
										<option <c:if test="${gopentime.end_time==1 }">selected="selected"</c:if> value="1">1</option>
										<option <c:if test="${gopentime.end_time==2 }">selected="selected"</c:if> value="2">2</option>
										<option <c:if test="${gopentime.end_time==3 }">selected="selected"</c:if> value="3">3</option>
										<option <c:if test="${gopentime.end_time==4 }">selected="selected"</c:if> value="4">4</option>
										<option <c:if test="${gopentime.end_time==5 }">selected="selected"</c:if> value="5">5</option>
										<option <c:if test="${gopentime.end_time==6 }">selected="selected"</c:if> value="6">6</option>
										<option <c:if test="${gopentime.end_time==7 }">selected="selected"</c:if> value="7">7</option>
										<option <c:if test="${gopentime.end_time==8 }">selected="selected"</c:if> value="8">8</option>
										<option <c:if test="${gopentime.end_time==9 }">selected="selected"</c:if> value="9">9</option>
										<option <c:if test="${gopentime.end_time==10 }">selected="selected"</c:if> value="10">10</option>
										<option <c:if test="${gopentime.end_time==11 }">selected="selected"</c:if> value="11">11</option>
										<option <c:if test="${gopentime.end_time==12 }">selected="selected"</c:if> value="12">12</option>
										<option <c:if test="${gopentime.end_time==13 }">selected="selected"</c:if> value="13">13</option>
										<option <c:if test="${gopentime.end_time==14 }">selected="selected"</c:if> value="14">14</option>
										<option <c:if test="${gopentime.end_time==15 }">selected="selected"</c:if> value="15">15</option>
										<option <c:if test="${gopentime.end_time==16 }">selected="selected"</c:if> value="16">16</option>
										<option <c:if test="${gopentime.end_time==17 }">selected="selected"</c:if> value="17">17</option>
										<option <c:if test="${gopentime.end_time==18 }">selected="selected"</c:if> value="18">18</option>
										<option <c:if test="${gopentime.end_time==19 }">selected="selected"</c:if> value="19">19</option>
										<option <c:if test="${gopentime.end_time==20 }">selected="selected"</c:if> value="20">20</option>
										<option <c:if test="${gopentime.end_time==21 }">selected="selected"</c:if> value="21">21</option>
										<option <c:if test="${gopentime.end_time==22 }">selected="selected"</c:if> value="22">22</option>
										<option <c:if test="${gopentime.end_time==23 }">selected="selected"</c:if> value="23">23</option>
									</select>
									<input type="hidden" name="gid" value="${gopentime.id }">
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								节假营业时间：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select id="jpentime" name="jopentime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="">请选择</option>
										<option <c:if test="${jopentime.start_time==0 }">selected="selected"</c:if> value="0">0</option>
										<option <c:if test="${jopentime.start_time==1 }">selected="selected"</c:if> value="1">1</option>
										<option <c:if test="${jopentime.start_time==2 }">selected="selected"</c:if> value="2">2</option>
										<option <c:if test="${jopentime.start_time==3 }">selected="selected"</c:if> value="3">3</option>
										<option <c:if test="${jopentime.start_time==4 }">selected="selected"</c:if> value="4">4</option>
										<option <c:if test="${jopentime.start_time==5 }">selected="selected"</c:if> value="5">5</option>
										<option <c:if test="${jopentime.start_time==6 }">selected="selected"</c:if> value="6">6</option>
										<option <c:if test="${jopentime.start_time==7 }">selected="selected"</c:if> value="7">7</option>
										<option <c:if test="${jopentime.start_time==8 }">selected="selected"</c:if> value="8">8</option>
										<option <c:if test="${jopentime.start_time==9 }">selected="selected"</c:if> value="9">9</option>
										<option <c:if test="${jopentime.start_time==10 }">selected="selected"</c:if> value="10">10</option>
										<option <c:if test="${jopentime.start_time==11 }">selected="selected"</c:if> value="11">11</option>
										<option <c:if test="${jopentime.start_time==12 }">selected="selected"</c:if> value="12">12</option>
										<option <c:if test="${jopentime.start_time==13 }">selected="selected"</c:if> value="13">13</option>
										<option <c:if test="${jopentime.start_time==14 }">selected="selected"</c:if> value="14">14</option>
										<option <c:if test="${jopentime.start_time==15 }">selected="selected"</c:if> value="15">15</option>
										<option <c:if test="${jopentime.start_time==16 }">selected="selected"</c:if> value="16">16</option>
										<option <c:if test="${jopentime.start_time==17 }">selected="selected"</c:if> value="17">17</option>
										<option <c:if test="${jopentime.start_time==18 }">selected="selected"</c:if> value="18">18</option>
										<option <c:if test="${jopentime.start_time==19 }">selected="selected"</c:if> value="19">19</option>
										<option <c:if test="${jopentime.start_time==20 }">selected="selected"</c:if> value="20">20</option>
										<option <c:if test="${jopentime.start_time==21 }">selected="selected"</c:if> value="21">21</option>
										<option <c:if test="${jopentime.start_time==22 }">selected="selected"</c:if> value="22">22</option>
										<option <c:if test="${jopentime.start_time==23 }">selected="selected"</c:if> value="23">23</option>
									</select>
<!-- 									<input type="text" -->
<!-- 										   id="opentime" name="gopentime"  placeholder="开始时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  />		 -->
<!-- 									<input type="text" -->
<!-- 										   id="endtime" name="gendtime"  placeholder="结束时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
										   
									<select id="endtime" name="jendtime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="">请选择</option>
										<option <c:if test="${jopentime.end_time==0 }">selected="selected"</c:if> value="0">0</option>
										<option <c:if test="${jopentime.end_time==1 }">selected="selected"</c:if> value="1">1</option>
										<option <c:if test="${jopentime.end_time==2 }">selected="selected"</c:if> value="2">2</option>
										<option <c:if test="${jopentime.end_time==3 }">selected="selected"</c:if> value="3">3</option>
										<option <c:if test="${jopentime.end_time==4 }">selected="selected"</c:if> value="4">4</option>
										<option <c:if test="${jopentime.end_time==5 }">selected="selected"</c:if> value="5">5</option>
										<option <c:if test="${jopentime.end_time==6 }">selected="selected"</c:if> value="6">6</option>
										<option <c:if test="${jopentime.end_time==7 }">selected="selected"</c:if> value="7">7</option>
										<option <c:if test="${jopentime.end_time==8 }">selected="selected"</c:if> value="8">8</option>
										<option <c:if test="${jopentime.end_time==9 }">selected="selected"</c:if> value="9">9</option>
										<option <c:if test="${jopentime.end_time==10 }">selected="selected"</c:if> value="10">10</option>
										<option <c:if test="${jopentime.end_time==11 }">selected="selected"</c:if> value="11">11</option>
										<option <c:if test="${jopentime.end_time==12 }">selected="selected"</c:if> value="12">12</option>
										<option <c:if test="${jopentime.end_time==13 }">selected="selected"</c:if> value="13">13</option>
										<option <c:if test="${jopentime.end_time==14 }">selected="selected"</c:if> value="14">14</option>
										<option <c:if test="${jopentime.end_time==15 }">selected="selected"</c:if> value="15">15</option>
										<option <c:if test="${jopentime.end_time==16 }">selected="selected"</c:if> value="16">16</option>
										<option <c:if test="${jopentime.end_time==17 }">selected="selected"</c:if> value="17">17</option>
										<option <c:if test="${jopentime.end_time==18 }">selected="selected"</c:if> value="18">18</option>
										<option <c:if test="${jopentime.end_time==19 }">selected="selected"</c:if> value="19">19</option>
										<option <c:if test="${jopentime.end_time==20 }">selected="selected"</c:if> value="20">20</option>
										<option <c:if test="${jopentime.end_time==21 }">selected="selected"</c:if> value="21">21</option>
										<option <c:if test="${jopentime.end_time==22 }">selected="selected"</c:if> value="22">22</option>
										<option <c:if test="${jopentime.end_time==23 }">selected="selected"</c:if> value="23">23</option>
									</select>
									<input type="hidden" name="jid" value="${jopentime.id }">
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								场馆图片：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="hidden" value="${o.pdImg }" name="pdImg" id="pdImg" >
									<input type="file" class="input_text" style="width: 200px;"  onchange="upload(this, fnOne , 2);"  />
									&nbsp;&nbsp;<span id="tips">图片</span>
									<div class="col-md-1">
										<img alt="" src="/${o.pdImg }" width="100px" onclick="onshowimg2()">
									</div>
									<input type="hidden" id="hImg" >
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
										<div class="col-md-6">.<input type="hidden" value="${qc.ph1 }" name="z1" id="z1" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne1 , 3);" 
										<c:if test="${empty qc.ph1 }" var="hava1" >required</c:if> 	/>
										&nbsp;&nbsp;<span id="tips">身份证正面</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph1 }" width="100px" onclick="onshowimg('/${qc.ph1 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph2 }" name="z2" id="z2" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne2 , 3);" 
<c:if test="${empty qc.ph2 }" var="hava2" >required</c:if> 	/>
										&nbsp;&nbsp;<span id="tips">身份证背面</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph2 }" width="100px" onclick="onshowimg('/${qc.ph2 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph3 }" name="z3" id="z3" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne3 , 3);" 
<c:if test="${empty qc.ph3 }" var="hava3" >required</c:if> 	/>
										&nbsp;&nbsp;<span id="tips">本人手持身份证照片</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph3 }" width="100px" onclick="onshowimg('/${qc.ph3 }',2)"></div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px">
								资质证书：
								<br/><span style="color: red;">以下证书请至少选择一项</span>
							</td>
							<td >
								<div class="col-sm-9">
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph4 }" name="z4" id="z4" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne4 , 3);" />
										&nbsp;&nbsp;<span id="tips">营业执照</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph4 }" width="100px" onclick="onshowimg('/${qc.ph4 }',2)">
										</div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph5 }" name="z5" id="z5" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne5 , 3);" />
										&nbsp;&nbsp;<span id="tips">场馆租赁协议</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph5 }" width="100px" onclick="onshowimg('/${qc.ph5 }',2)">
										</div></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph6 }" name="z6" id="z6" >
										<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne6 , 3);" />
										&nbsp;&nbsp;<span id="tips">场馆委托运营协议</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph6 }" width="100px" onclick="onshowimg('/${qc.ph6 }',2)">
										</div></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph7 }" name="z7" id="z7" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne7 , 3);" />
										&nbsp;&nbsp;<span id="tips">开户许可证</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph7 }" width="100px" onclick="onshowimg('/${qc.ph7 }',2)">
										</div></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">.<input type="hidden" value="${qc.ph8 }" name="z8" id="z8" >
										<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne8 , 3);" />
										&nbsp;&nbsp;<span id="tips">组织机构代码证</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph8 }" width="100px" onclick="onshowimg('/${qc.ph8 }',2)">
										</div>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								开户银行：<span style="color: red;">*</span>
								</br><span style="font-size:12px; color: #aaa;">此银行账号用于场馆收入结款转账用</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="bank" style="width: 360px;" placeholder="请输入开户银行"
										   class="col-xs-10 col-sm-5" value="${o.bank }" required />
								</div> 
							</td>
						</tr>
						
						<tr>
							<td>
								支行：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="zbank" style="width: 360px;" placeholder="请输入支行"
										   class="col-xs-10 col-sm-5"  value="${o.zbank }" required/>
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								银行帐号：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" style="IME-MODE: disabled;  width: 360px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                								onafterpaste="this.value=this.value.replace(/\D/g,'')"
										   name="bankZh" maxlength="19" id="bankZh"   placeholder="请输入银行帐号"
										   class="col-xs-10 col-sm-5" value="${o.bankZh }" required />
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								户名：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="bankName" style="width: 360px;" placeholder="请输入户名"
										   class="col-xs-10 col-sm-5" value="${o.bankName }" required />
								</div> 
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
								预订状态：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="is_reserve" value="1" <c:if test="${o.is_reserve == 1 }"  >checked=checked</c:if>>可在线预订
									<input type="radio" name="is_reserve" value="0" <c:if test="${o.is_reserve == 0 }"  >checked=checked</c:if>>不可在线预订
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								经纬度：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" id="coordinateslongitude" name="coordinateslongitude" class="col-xs-10 col-sm-5" required placeholder="点击地图经度" 
										value="${o.coordinateslongitude }"/>
									<input type="text" id="coordinateslatitude" name="coordinateslatitude" class="col-xs-10 col-sm-5" required placeholder="点击地图纬度"
									value="${o.coordinateslatitude }"/>
								</div> 
							</td>
						</tr>
						
						<c:if test="${not empty k}">
							<c:if test="${o.auditStatus == 3 }">
								<tr >
									<td width="120px" >
										退回理由：
									</td>
									<td >
										<div class="col-sm-9">
											<input type="hidden" value="${o.return_reason }" name="return_reason" placeholder="退回理由" readonly="readonly" >
											<textarea  style="height: 150px; width:360px;" disabled="disabled"  >${o.return_reason }</textarea>
										</div>
									</td>
								</tr>
							</c:if>
						</c:if>
						
						<c:if test="${not empty is_aud }">
							<tr >
								<td width="120px" >
									退回理由：
								</td>
								<td >
									<div class="col-sm-9">
										<input type="text" name="return_reason" id="return_reason" placeholder="退回理由"  >
									</div>
								</td>
							</tr>
							
							<tr >
								<td width="120px" >
									退回次数：
								</td>
								<td >
									<div class="col-sm-9">
										<input type="text" value="${o.return_count }" readonly="readonly"  >
									</div>
								</td>
							</tr>
							
							<tr >
								<td width="120px" >
									有效时间：
								</td>
								<td >
									<div class="col-sm-9">
										<input type="text"
												onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
												style="width: 150px;" id="effective_time" name="effective_time"  placeholder="有效时间" />
									</div>
								</td>
							</tr>
							<input type="hidden" name="auditStatus" id="audStaus" >
						</c:if>
						<c:if test="${empty is_aud }">
							<tr >
								<td width="120px" >
									有效时间：
								</td>
								<td >
									<div class="col-sm-9">
										<span>${o.effective_time }</span>
									</div>
								</td>
							</tr>
							<input type="hidden" name="auditStatus" id="audStaus" value="${o.auditStatus }" >
						</c:if>
						
						<c:forEach items="${reasonList }" var="o" varStatus="v" >
								<tr >
									<td width="120px" >
										第${v.count }次退回理由：
									</td>
									<td >
										<div class="col-sm-9">
											<textarea style="height: 150px; width:360px;" disabled="disabled"  >${o.reason }</textarea>
										</div>
									</td>
								</tr>
							</c:forEach>
						<tr>
							<td colspan="2">
								<div style="width: 900px;height: 700px" id="map_content" onclick="showInfo()"></div>
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
					<c:if test="${empty is_aud }">
						<button class="btn btn-sm btn-success" type="button" onclick="location='playground_list.do'"> [ 返回 ]
						</button>
					</c:if>
					<c:if test="${not empty is_aud }">
						<button class="btn btn-sm btn-success" type="button" onclick="location='audPlayground.do'"> [ 返回 ]
						</button>
						<button class="btn btn-sm btn-success" type="submit" onclick="checkIsRechar(2)"> [ 通过 ]
						</button>
						<button class="btn btn-sm btn-success" type="submit" onclick="checkIsRechar(3)"> [ 打回 ]
						</button>
					</c:if>
				</td>
				<td width="20%">
				</td>
			</tr>
		</table>
	</form>




</body>
<jsp:include page="../common_image_show.jsp"></jsp:include>
<script>	
function checkIsRechar(flag){
	$("#audStaus").val(flag);
}

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

function getArea(id){
	var city = $("#"+id).val();
	var user_area = $("#user_area");
	var info = util.POST("/area.do",{"city":city});
	var str ='<option value="0">请选择</option>';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_area.html(str);
}
</script>
<script type="text/javascript">
	map = new BMap.Map("map_content");
	var point = new BMap.Point(116.331398,39.897445);
	var myGeo = new BMap.Geocoder();
	/* 初始化地图 */
	function initMap(x,y){
		
		map.enableScrollWheelZoom();///允许鼠标放大缩小
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());
		map.addControl(new BMap.OverviewMapControl());
		var pp = newPoint(x,y); // 创建点坐标
		map.centerAndZoom(pp, 15);
		// 百度地图API功能
		map.centerAndZoom(new BMap.Point(114.060938,22.548248), 11);
		
		map.addEventListener("click", showInfo);
	}
	
	function change(){
		//取得城市
		/* 取得下拉列表框对象 */
		var user_city = document.getElementById("user_city");
		/* 取得当前选中的索引 */
		var cindex = document.getElementById("user_city").selectedIndex;
		/* 根据索引取得options集合之中的一个option元素,它的innerText属性就是页面显示的文本啦 */
		var ctext = user_city.options[cindex].text;
		/* 取得当前选中项的值 */
		
		//取得区县
		/* 取得下拉列表框对象 */
		var user_area = document.getElementById("user_area");
		/* 取得当前选中的索引 */
		var aindex = document.getElementById("user_area").selectedIndex;
		/* 根据索引取得options集合之中的一个option元素,它的innerText属性就是页面显示的文本啦 */
		var atext = user_area.options[aindex].text;
		/* 取得当前选中项的值 */
		
		var name=ctext+atext;
// 		alert(name);
		myGeo.getPoint(name, function(point){
			if (point) {
				map.centerAndZoom(point, 13);
				map.addOverlay(new BMap.Marker(point));
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, name);
	}
	
	function showInfo(e){
		$("#coordinateslongitude").val(e.point.lng);
		$("#coordinateslatitude").val(e.point.lat);
	}
	
	/* 清空地图 */
	function clearMap(){
		map.clearOverlays();
	}
	
	/* 创建点 */
	function newPoint(x,y){
		return new BMap.Point(x,y);
	}
	
	function valuechange(pram){
		if(pram.checked){
			pram.value = 1;
		}else{
			pram.value = 0;
		}
	}
	
	
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
		$("#pdImg").val(obj.data);
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
	</script>	
</html>
