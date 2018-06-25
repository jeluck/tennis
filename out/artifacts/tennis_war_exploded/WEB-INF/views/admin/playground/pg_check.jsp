<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
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

</script>
</head>
<body >
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
			<li class="active">审核场馆</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>审核场馆<span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="pdCheck.do"  
		  method="post"  onsubmit="return checkForm()">
		  <input type="hidden" id="pdId" value="${o.id}" name="id" />	
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="100px" >
								名称：
							</td>
							<td >
								<div class="col-sm-9">
									${o.name }
								</div> 
							</td>
						</tr>
						<tr >
							<td width="100px" >
								场地类型：
							</td>
							<td >
								<div class="col-sm-9">
										<c:if test="${o.site_type == '红土' }">红土</c:if> 
										<c:if test="${o.site_type == '硬地' }">硬地</c:if> 
										<c:if test="${o.site_type == '草地' }">草地</c:if>
										<c:if test="${o.site_type == '地毯' }">地毯</c:if>
								</div> 
							</td>
						</tr>
						<tr >
							<td width="100px" >
								球场类型 ：
							</td>
							<td >
								<div class="col-sm-9">
									<c:if test="${o.court_type == '普通' }">普通球场</c:if> 
									<c:if test="${o.court_type == '智能' }">智能球场</c:if>
							
								</div> 
							</td>
						</tr>
						<tr >
							<td width="100px" >
								空间类型：
							</td>
							<td >
								<div class="col-sm-9">
									<c:if test="${o.space_type == '室内' }">室内</c:if>
									<c:if test="${o.space_type == '室外' }">室外</c:if>
								</div> 
							</td>
						</tr>
						
						<tr >
							<td width="100px" >
										省份
									</td>
							<td width="100px" >
									<c:forEach items="${provincelist}" var="p">
										<c:if test="${p.region_id == sid }">${p.region_name}</c:if>
									</c:forEach>
									${r.region_name }
									<c:forEach items="${areaList}" var="a">
										<c:if test="${o.areaid==a.region_id }">${a.region_name}</c:if> 
									</c:forEach>
								</select>
		    				</td>
						</tr>
						<tr >
							<td width="100px" >
								具体地址：
							</td>
							<td >
								<div class="col-sm-9">
									${o.address }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								电话：
							</td>
							<td >
								<div class="col-sm-9">
									${o.telphone }
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								原价：
							</td>
							<td >
								<div class="col-sm-9">
									${o.price }
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								最低价：
							</td>
							<td >
								<div class="col-sm-9">
									${o.money }
								</div> 
							</td>
						</tr>
						
						<tr >
							<td width="120px" >
								描述：
							</td>
							<td >
								<div class="col-sm-9">
									${o.details }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								服务：
							</td>
							<td>
								<div class="col-sm-9" >
									<c:if test="${ps.wifi == 1}">
										wifi&nbsp;
									</c:if>
									<c:if test="${ps.equipment == 1}">
										器材&nbsp;
									</c:if>
									<c:if test="${ps.locker_room == 1}">
										更衣室&nbsp;
									</c:if>
									<c:if test="${ps.lockers == 1}">
										储物柜&nbsp;
									</c:if>
									<c:if test="${ps.shower == 1}">
										淋浴&nbsp;
									</c:if>
									<c:if test="${ps.vip_room == 1}">
										贵宾室&nbsp;
									</c:if>
									<c:if test="${ps.equipment_shop == 1}">
										装备店&nbsp;
									</c:if>
									<c:if test="${ps.food == 1}">
										停车场&nbsp;
									</c:if>
									<c:if test="${ps.parking_lot == 1}">
										停车场&nbsp;
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								工作营业时间：
							</td>
							<td >
								<div class="col-sm-9">
									开始时间：${gopentime.start_time}
									结束时间：${gopentime.end_time}										   
									<input type="hidden" name="gid" value="${gopentime.id }">
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								节假营业时间：
							</td>
							<td >
								<div class="col-sm-9">
								开始时间：${jopentime.start_time}
								结束时间：${jopentime.end_time }
									<input type="hidden" name="jid" value="${jopentime.id }">
								</div> 
							</td>
						</tr>
						<tr>
							<td>
								场馆图片：
							</td>
							<td >
								<div class="col-sm-9">
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
								资质证书：
							</td>
							<td >
								<div class="col-sm-9">
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">身份证正面</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph1 }" width="100px" onclick="onshowimg('/${qc.ph1 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">身份证背面</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph2 }" width="100px" onclick="onshowimg('/${qc.ph2 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">本人手持身份证照片</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph3 }" width="100px" onclick="onshowimg('/${qc.ph3 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">营业执照</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph4 }" width="100px" onclick="onshowimg('/${qc.ph4 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">场馆租赁协议</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph5 }" width="100px" onclick="onshowimg('/${qc.ph5 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">场馆委托运营协议</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph6 }" width="100px" onclick="onshowimg('/${qc.ph6 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">开户许可证</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph7 }" width="100px" onclick="onshowimg('/${qc.ph7 }',2)"></div>
									</div>
									
									<div class="row"> 
										<div class="col-md-6">
										&nbsp;&nbsp;<span id="tips">组织机构代码证</span></div>
										<div class="col-md-6"><img alt="" src="/${qc.ph8 }" width="100px" onclick="onshowimg('/${qc.ph8 }',2)"></div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								开户银行：
							</td>
							<td >
								<div class="col-sm-9">
									${o.bank }
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								支行：
							</td>
							<td >
								<div class="col-sm-9">
									${o.zbank }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								银行帐号：
							</td>
							<td >
								<div class="col-sm-9">
									${o.bankZh }
								</div>
							</td>
						</tr>
						<tr>
							<td>
								户名：
							</td>
							<td >
								<div class="col-sm-9">
									${o.bankName }
								</div>
							</td>
						</tr>
						<tr >
							<td width="100px" >
								预订状态：
							</td>
							<td >
								<div class="col-sm-9">
								 	<c:if test="${o.is_reserve == 1 }"  >可在线预订</c:if>
									<c:if test="${o.is_reserve == 0 }"  >不可在线预订</c:if>
								</div>
							</td>
						</tr>
						<tr >
							<td width="120px" >
								经纬度：
							</td>
							<td >
								<div class="col-sm-9">
									经度 ：${o.coordinateslongitude }
									纬度：${o.coordinateslatitude }
								</div> 
							</td>
						</tr>
						<c:if test="${not empty k }">
							<c:if test="${o.auditStatus == 3 }">
								<tr >
									<td width="120px" >
										退回理由：
									</td>
									<td >
										<div class="col-sm-9">
<%-- 											<input type="text" value="${o.return_reason }" placeholder="退回理由"  > --%>
											<textarea style="height: 150px; width:360px;" disabled="disabled"  >${o.return_reason }</textarea>
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
<!-- 										<input type="text" name="return_reason" id="return_reason" placeholder="退回理由"  > -->
										
										<textarea name="return_reason" id="return_reason" 
												style="height: 150px; width:360px;" maxlength="200"  ></textarea>
									</div>
								</td>
							</tr>
							
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
							
							<tr >
								<td width="120px" >
									退回次数：
								</td>
								<td >
									<div class="col-sm-9">
										<input type="text"  value="${o.return_count }" readonly="readonly"  >
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
												onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})"
												style="width: 150px;" id="effective_time" name="effective_time"  placeholder="有效时间" />
									</div>
								</td>
							</tr>
							<input type="hidden" name="auditStatus" id="audStaus" >
						</c:if>
						<c:if test="${empty is_aud }">
							<input type="hidden" name="auditStatus" id="audStaus" value="${o.auditStatus }" >
						</c:if>
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
</script>
</html>
