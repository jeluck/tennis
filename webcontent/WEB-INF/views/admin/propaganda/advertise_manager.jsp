<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告管理</title>
<jsp:include page="../init.jsp" />
<script language="javascript" type="text/javascript" src="../js/common.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>

<script type="text/javascript">
var index;
var option = "save";
function init(){
	var title="<b>添加广告信息</b>";
	if("update"==option){
		title="<b>修改广告信息</b>";
	}
	$('#AddAdvertiseForm').attr("action","advertise.do?action="+option);
	
	index = $.layer({
	    type : 1,
	    shade : [0],
	    moveOut: true,
	    area : ['650px', '500px'],
	    title : title,
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    border: [10, 0.5, '#666'],
	    offset: ['60px' , ''],
	    success: function(){
	    	layer.shift('top', 400);
	    },
	    page : {dom : '#add_form'}
	});
};
function beforeAdd(){
	option = "save";
	$('#AddAdvertiseForm').form("clear");
	$('#AddAdvertiseForm input[name=id]').val(0);
	init();
}
function beforeEdit(ad_id){
	option = "update";
	var advertise = util.POST("advertise.do?action=getinfo",{"ad_id":ad_id});
	$('#AddAdvertiseForm').form('load', advertise);
	$("#ad_pic_url").val(advertise.ad_picture_url);
	$("#ad_pic_url_img").attr("src","/"+advertise.ad_picture_url);
	$("#ad_http_url").val(advertise.ad_http_url);
	$("#show_color").css("background",advertise.bg_color);
	init();
}
function submitForm(){
	
	
	
	if($("#imgFile").val()!=null && $("#imgFile").val()!="" || $("#ad_pic_url").val()!=null && $("#ad_pic_url").val()!=""){
		if($("#start_time").val()==null || $("#start_time").val()==""){
			alert("请填写开始时间");
			return false;
		}
		if($("#end_time").val()==null || $("#end_time").val()==""){
			alert("请填写结束时间");
			return false;
		}
	}else{
		alert("请选择广告图片");
		return false;
	}
	return true;
	
	
	
// 	$('#AddAdvertiseForm').form('submit', {url: "advertise.do?action="+option,
// 		onSubmit: function(){
// 			return true;
// 		},
// 		success: function(data){
// 			data = $.parseJSON(data);
// 			parent.ShowMsg("操作提示：",data.info);
// 			if(data.status){
// 				location.href = "advertise.do?action=advertise_list&pageNumber=${advertisepage.currentPage }&pageSize=10";
// 			}
// 		}
// 	});
	
}
function delAdvertise(id){
	$.layer({
	    shade: [0],
	    area: ['auto','auto'],
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    dialog: {
	        msg: '您确定要对该<b> 广告信息  </b>进行删除操作吗?',
	        btns: 2,                    
	        type: 4,
	        btn: ['[确定]','[取消]'],
	        yes: function(){
	        	var info = util.POST("advertise.do?action=del",{"ad_id":id});
	        	parent.ShowMsg("操作提示：",info.info);
	        	if(info.status){
	        		location.href = "advertise.do?action=advertise_list&pageNumber=${advertisepage.currentPage }&pageSize=10";
	        	}
	        }
	    }
	});
}
//页面跳转
function go_page(pageNumber){
	location.href = "managerteam_list.do?&pageNumber="+pageNumber;
}
//改变类型时选项的改变
function changeTips(_this){
	var type = _this.value;
	if(type==2){
		$("#tips").html("*最佳尺寸：570*1080");
	}else if(type==5){
		$("#tips").html("*最佳尺寸：600*600");
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

		<ul class="breadcrumb" style="padding-top: 8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">宣传管理</li>
			<li class="active">广告管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>广告列表</h1>
	</div>
	<div style="margin: 0 0 10px 10px; clear: both; height: 30px;">
		<button class="btn btn-sm btn-success pull-left"
			onclick="beforeAdd();">
			<i class="icon-ok"></i>添加广告
		</button>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th style="width: 250px;">广告图片</th>
<!-- 							<th>背景色值</th> -->
							<th>广告标题</th>
							<th><i class="icon-time"></i>有效时间</th>
							<th>广告类型</th>
							<th>排序值</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<c:set var="hava" value="${not empty advertisepage.dataList}" />
						<c:set var="base" value="${(advertisepage.currentPage-1)*10 }" />
						<c:if test="${hava }">
							<c:forEach items="${advertisepage.dataList }" var="advertise"
								varStatus="v">
								<tr>
									<td>${base+v.index+1}</td>
									<td><img src="/${advertise.ad_picture_url}" style="width: 240px; height: 60px;" /></td>
<%-- 									<td><div style="width:30px;height:30px;background:${advertise.bg_color};"></div></td> --%>
									<td>${advertise.ad_title}</td>
									<td>${advertise.start_time} ~ ${advertise.end_time}</td>
									<td><c:if test="${advertise.adtype==1}">首页轮播图</c:if> 
										<c:if test="${advertise.adtype==2}">场馆列表轮播图</c:if>
										<c:if test="${advertise.adtype==3}">网球圈</c:if>
										<c:if test="${advertise.adtype==4}">赛事</c:if>
										<c:if test="${advertise.adtype==5}">首页悬浮广告</c:if>
										<c:if test="${advertise.adtype==6}">教练列表轮播图</c:if>
										<c:if test="${advertise.adtype==7}">微信端首页端轮播图</c:if>
									</td>
									<td>${advertise.sort_num }</td>
									<td>
										<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="beforeEdit(${advertise.id});">
												<i class="icon-edit bigger-120"></i>修改
											</button>

											<button class="btn btn-xs btn-danger"
												onclick="delAdvertise(${advertise.id});">
												<i class="icon-trash bigger-120"></i>删除
											</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
		<div class="modal-footer no-margin-top">
			<ul class="pagination pull-right no-margin">
				<li><a href="javascript:void(0);">第${advertisepage.currentPage
						}页/共${advertisepage.pageCount }页</a></li>
				<li><a
					href="advertise.do?action=advertise_list&pageNumber=1&pageSize=10">首页</a>
				</li>
				<li
					class="prev <c:if test="${advertisepage.currentPage==1}"> disabled</c:if>">
					<c:if test="${advertisepage.currentPage-1>0 }">
						<a
							href="advertise.do?action=advertise_list&pageNumber=${advertisepage.currentPage-1 }&pageSize=10">
							<<&nbsp;&nbsp;上一页 </a>
					</c:if> <c:if test="${advertisepage.currentPage-1==0 }">
						<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
					</c:if>
				</li>
				<li
					class="next <c:if test="${advertisepage.currentPage==advertisepage.pageCount}"> disabled</c:if>">
					<c:if
						test="${advertisepage.currentPage+1<=advertisepage.pageCount}">
						<a
							href="advertise.do?action=advertise_list&pageNumber=${advertisepage.currentPage+1 }&pageSize=10">
							下一页&nbsp;&nbsp;>> </a>
					</c:if> <c:if test="${advertisepage.currentPage==advertisepage.pageCount}">
						<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
					</c:if>
				</li>
				<li><a
					href="advertise.do?action=advertise_list&pageNumber=${advertisepage.pageCount }&pageSize=10">尾页</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="add_form"
		style="display: none; width: 600px; height: 500px; margin-top: 30px;">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="AddAdvertiseForm" method="post" action="advertise.do?action=update"
					role="form" enctype="multipart/form-data" onsubmit="return submitForm();">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 广告标题： </label>
						<div class="col-sm-9">
							<input type="hidden" value="0" name="id" /> <input type="text"
								name="ad_title" style="width: 360px;" placeholder="请输入广告标题"
								class="col-xs-10 col-sm-5" required /><span style="color: red;">*</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 广告类型： </label>
						<div class="col-sm-9">
							<select class="form-control" style="width: 360px;" name="adtype" required
								id="adtype" onchange="changeTips(this);">
								<option value="1">首页轮播图</option>
								<option value="2">场馆列表轮播图</option>
								<option value="3">网球圈</option>
								<option value="4">赛事</option>
								<option value="5">首页悬浮广告</option>
								<option value="6">教练列表轮播图</option>
								<option value="7">微信端首页端轮播图</option>
							</select><span style="color: red;">*</span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 广告图片： </label>
						<div class="col-sm-9">
							<input type="hidden" name="ad_pic_url" id="ad_pic_url" />
							<img name="ad_pic_url_img" id="ad_pic_url_img" style="width:50px;"/>
							<input type="file" class="input_text" name="imgFile" id="imgFile" style="width: 200px;"/>
							&nbsp;&nbsp;<span id="tips">*最佳尺寸：1920*365</span><span style="color: red;">*</span>
						</div>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-sm-3 control-label no-padding-right" -->
<!-- 							or="form-field-1"> 广告背景色值： </label> -->
<!-- 						<div class="col-sm-9"> -->
<!-- 							<input type="hidden" name="bg_color" id="bg_color" /> -->
<!-- 							<input type="text" class="input_text" name="bg_color" -->
<!-- 								style="width: 70px;" onblur="$('#show_color').css('background',$(this).val());" required />&nbsp;<span id="show_color" style="width:20px;height:20px;display: inline-block;"></span>&nbsp;当广告图宽度不够时，将以此色值填充. -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group" style="display:none;">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 广告背景色值： </label>
						<div class="col-sm-9">
							<!-- <input type="hidden" name="bg_color" id="bg_color" /> -->
							<input type="text" class="input_text" name="bg_color"
								style="width: 70px;" onblur="$('#show_color').css('background',$(this).val());" />&nbsp;<span id="show_color" style="width:20px;height:20px;display: inline-block;"></span>&nbsp;当广告图宽度不够时，将以此色值填充.<span style="color: red;">*</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 广告路径： </label>
						<div class="col-sm-9">
							<input type="text" class="input_text" id="ad_http_url" name="ad_http_url"  style="width: 360px;" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 排序权重： </label>
						<div class="col-sm-9">
							<input type="number" class="input_text" value="1" name="sort_num" style="width: 360px;" required />

						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 有效期：</label>
						<div class="col-sm-9">
							<input type="text"
								onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})"
								style="width: 182px;" name="start_time" id='start_time' />&nbsp;至&nbsp;
							<input
								onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})"
								style="width: 182px;" type="text" name="end_time" id='end_time'/>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<div class="col-sm-9">
							<div class="col-md-offset-3 col-md-9">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-2"></label>
								<button class="btn btn-sm btn-success no-padding-right"
									type="submit">
									<i class="icon-ok bigger-90"></i>[ 确定 ]
								</button>
								&nbsp; &nbsp; &nbsp;
								<button class="btn btn-sm" type="reset">
									<i class="icon-undo bigger-90"></i>[ 重置 ]
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>