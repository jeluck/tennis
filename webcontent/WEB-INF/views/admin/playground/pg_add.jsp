<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3" charset="UTF-8"></script>
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">

function checkForm()
{
	var ph4 =$("#ph4").val();
	var ph5 =$("#ph5").val();
	var ph6 =$("#ph6").val();
	var ph7 =$("#ph7").val();
	var ph8 =$("#ph8").val();
	if(ph4=="" && ph5=="" && ph6==""&& ph7==""&& ph8==""){
		alert("资质证书至少一项");
		return false;
	}
	var bankZh = document.getElementById('bankZh').value;
	if(bankZh.length<14){
		alert("银行卡账号格式不对")
		return false;
	}
	
	var flag = true;
	var flag2 = true;
// 	flag = checkopentime($("#opentime").val());
// 	flag2 = checkendtime($("#endtime").val());
// 	if(flag == true && flag2 == true){
// 	 	if(parseInt($("#endtime").val())>parseInt($("#opentime").val())){
	 		
	 		
	 		if(checkPhone()>0){
	 			if($("#coordinateslongitude").val()!=""){
		 			return true;
		 		}else{
		 			alert("请点击地图选择经纬度");
		 			return false;
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
	var info = util.POST("/pgm/savecheckPlayPhone.do",{"phone":$("#phone").val()});
	if(info<=0){
		alert("此电话已被使用");
		//return 0;
	}
	return 1;
}
</script>
</head>
<body onload="initMap(114.060938,22.548248)">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">管理</li>
			<li class="active">添加场馆</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加场馆 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="add_playground.do"  
		  method="post"  onsubmit="return checkForm()">
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
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								场地类型：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select name="site_type" style="width: 360px;" class="col-xs-10 col-sm-5" required >
										<option value="红土">红土</option>
										<option value="硬地">硬地</option>
										<option value="草地">草地</option>
										<option value="地毯">地毯</option>
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
									<select name="court_type" style="width: 360px;" class="col-xs-10 col-sm-5" required >
										<option value="普通">普通</option>
										<option value="智能">智能</option>
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
									<select name="space_type" style="width: 360px;" class="col-xs-10 col-sm-5" required >
										<option value="室内">室内</option>
										<option value="室外">室外</option>
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
								<option value="">请选择省份</option>
								<c:forEach items="${provincelist}" var="p">
									<option value="${p.region_id}" <c:if test="${ca.user_province==p.region_id }">selected="selected"</c:if> >${p.region_name}</option>
								</c:forEach>
							</select>	
					        <select  id="user_city" name="cityid"  onchange="getArea('user_city')"  class="bankSelected" required >
								<option value="">请选择城市</option>
								<option value="${ca.user_city}" selected="selected">${ca.city}</option>
							</select>
					        <select id="user_area" name="areaid" class="bankSelected"  onchange="change()" required>
								<option value="">请选择区、县</option>
								<option value="${ca.user_area}"  selected="selected">${ca.area}</option>
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
										   name="telphone" id="phone" onchange="checkPhone()" style="width: 360px;" placeholder="请输入电话号码"
										   class="col-xs-10 col-sm-5" required  />
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
										   class="col-xs-10 col-sm-5" required  />
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
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="120px" >
								描述：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<textarea rows="6" cols="10" xclass="col-xs-10 col-sm-5" name="details" style="width: 360px;" placeholder="请输入详细信息" required ></textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								服务：
							</td>
							<td>
								<div class="col-sm-9" >
									wifi：<input type="checkbox" name="wifi" value="0" onclick="valuechange(this)"/>　
									器材：<input type="checkbox" name="equipment" value="0" onclick="valuechange(this)" />　
									更衣室：<input type="checkbox" name="locker_room" value="0" onclick="valuechange(this)" />　</br>
									储物柜：<input type="checkbox" name="lockers" value="0" onclick="valuechange(this)" />　
									淋浴：<input type="checkbox" name="shower" value="0" onclick="valuechange(this)" />　
									贵宾室：<input type="checkbox" name="vip_room" value="0" onclick="valuechange(this)" />　　　　</br>
									装备店：<input type="checkbox" name="equipment_shop" value="0" onclick="valuechange(this)" />　
									食品：<input type="checkbox" name="food" value="0" onclick="valuechange(this)" />　
									停车场：<input type="checkbox" name="parking_lot" value="0" onclick="valuechange(this)" />　
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
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6" selected>6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
<!-- 									<input type="text" -->
<!-- 										   id="opentime" name="gopentime"  placeholder="开始时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  />		 -->
<!-- 									<input type="text" -->
<!-- 										   id="endtime" name="gendtime"  placeholder="结束时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
										   
									<select id="endtime" name="gendtime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20" selected>20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								节假营业时间：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<select id="opentime" name="jopentime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6" selected>6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20" selected>20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
<!-- 									<input type="text" -->
<!-- 										   id="opentime" name="jopentime"  placeholder="开始时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  />		 -->
<!-- 									<input type="text" -->
<!-- 										   id="endtime" name="jendtime"  placeholder="结束时间24小时制" -->
<!-- 										   class="col-xs-10 col-sm-5" required  /> -->
									<select id="endtime" name="jendtime"  placeholder="开始时间24小时制"
										   class="col-xs-10 col-sm-5" require>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20" selected>20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								场馆图片：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="file" class="input_text" style="width: 200px;" onchange="upload(this, fnOne , 2);" required/>
									<input type="hidden" name="pdImg" id="pdImg" >
									&nbsp;&nbsp;<span id="tips">图片</span>
								</div> 
							</td>
						</tr>
						<tr>
							<td width="120px">
								身份证信息：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne1 , 3);" required/>
									<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">身份证正面</span> <input type="hidden" name="ph1" id="ph1" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne2 , 3);" required/>
									<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">身份证背面</span> <input type="hidden" name="ph2" id="ph2" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne3 , 3);" required/>
									<span style="color: red;">*</span>&nbsp;&nbsp;<span id="tips">本人手持身份证照片</span> <input type="hidden" name="ph3" id="ph3" >
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
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne4 , 3);" />
									&nbsp;&nbsp;<span id="tips">营业执照</span> <input type="hidden" name="ph4" id="ph4" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne5 , 3);" />
									&nbsp;&nbsp;<span id="tips">场馆租赁协议</span> <input type="hidden" name="ph5" id="ph5" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne6 , 3);" />
									&nbsp;&nbsp;<span id="tips">场馆委托运营协议</span> <input type="hidden" name="ph6" id="ph6" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne7 , 3);" />
									&nbsp;&nbsp;<span id="tips">开户许可证</span> <input type="hidden" name="ph7" id="ph7" >
									<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, fnOne8 , 3);" />
									&nbsp;&nbsp;<span id="tips">组织机构代码证</span> <input type="hidden" name="ph8" id="ph8" >
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
										   class="col-xs-10 col-sm-5"  required/>
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
										   class="col-xs-10 col-sm-5"  required/> 
								</div>
							</td>
						</tr>
						<tr>
							<td>
								银行帐号：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="tel"  style="IME-MODE: disabled;  width: 360px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  
                								onafterpaste="this.value=this.value.replace(/\D/g,'')"
										   name="bankZh" maxlength="19" id="bankZh"  placeholder="请输入银行帐号"
										   class="col-xs-10 col-sm-5" required /> 
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
										   class="col-xs-10 col-sm-5"  required/> 
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								在线预订：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="radio" name="is_reserve" value="1" checked>可以
									<input type="radio" name="is_reserve" value="0">不可以
								</div>
							</td>
						</tr>
						
						<tr >
							<td width="120px" >
								经纬度：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text" id="coordinateslongitude" name="coordinateslongitude" class="col-xs-10 col-sm-5" placeholder="点击地图经度" />
									<input type="text" id="coordinateslatitude" name="coordinateslatitude" class="col-xs-10 col-sm-5" placeholder="点击地图纬度" />
								</div> 
							</td>
						</tr>
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
	var str ='<option value="">请选择</option>';
	
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
	var str ='<option value="">请选择</option>';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_area.html(str);
}
</script>
<script type="text/javascript">
	$("#")


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
	</script>	
</html>
