<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统菜单管理</title>
<jsp:include page="init.jsp" />
<script language="javascript" type="text/javascript" src="../js/common.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>

<script type="text/javascript">
var index;
function init(action){
	index = $.layer({
	    type : 1,
	    shade : [0],
	    moveOut: true,
	    area : ['650px', '500px'],
	    title : "<b>"+action+"系统菜单信息</b>",
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
	$("#menu_code_erroe").html("");
	$('#AddAdvertiseForm').form("clear");
	$("#menu_code").removeAttr("readonly");
	$("#edit_id").val(0);
	init("添加");
}
//准备修改数据
function beforeEdit(menu_code){
	$("#menu_code_erroe").html("");
	var menu = util.GET("get_menu_info.do",{"menu_code":menu_code});
	$('#AddAdvertiseForm').form('load', menu);
	$("#menu_code").attr("readonly","readonly");//code不能修改
	$("#menu_belong_to_role").val(menu.menu_belong_to_role);
	$("#edit_id").val(menu.id);
	init("修改");
}
//删除菜单信息
function del_menu(id){
	$.layer({
	    shade: [0],
	    area: ['auto','auto'],
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    dialog: {
	        msg: '您确定要删除该<b> 系统菜单信息  </b>吗?',
	        btns: 2,                    
	        type: 4,
	        btn: ['[确定]','[取消]'],
	        yes: function(){
	        	/* var info = util.POST("advertise.do?action=del",{"ad_id":id});
	        	parent.ShowMsg("操作提示：",info.info);
	        	if(info.status){
	        		location.href = "advertise.do?action=advertise_list&pageNumber=${advertisepage.currentPage }&pageSize=10";
	        	} */
	        }
	    }
	});
}

function checkMenucode(){
	var info = util.POST("checkMenu_code.do",{"menu_code":$("#menu_code").val(),"id":$("#edit_id").val()});
	if(info<=0){
		$("#menu_code_erroe").html("此菜单标识已被使用");
		return 0;
	}else{
		$("#menu_code_erroe").html("");
	}
	return 1;
}
function checkForm()
{
	if(checkMenucode()>0){
		return true;
	}else{
		return false;
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
			<li class="active">权限管理</li>
			<li class="active">菜单管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>系统菜单列表</h1>
	</div>
	<div style="margin: 0 0 10px 15px; clear: both; height: 30px;">
		<button class="btn btn-sm btn-success pull-left"
			onclick="beforeAdd();">
			<i class="icon-ok"></i>系统菜单列表
		</button>
	</div>
	<div>
		<div class="col-xs-12">
			<div class="table-responsive"  style="border:1px solid #fff;border-radius:5px;">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 50px;">序号</th>
							<th style="width: 260px;">菜单名称</th>
							<th>菜单标识</th>
							<th>菜单地址</th>
							<th>菜单描述</th>
							<th><i class="icon-time"></i>创建时间</th>
							<th>排序</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<c:set var="hava" value="${not empty menu_list}" />
						<c:if test="${hava }">
							<c:set value="0" var="v"/>
							<c:forEach items="${menu_list }" var="menu">
								<c:if test="${menu.parent_menu_code=='console'||menu.parent_menu_code=='top' }">
								<c:set value="${v+1 }" var="v"/>
								<tr>
									<td>${v}</td>
									<td><b>${menu.menu_name }</b></td>
									<td>${menu.menu_code }</td>
									<td>${menu.menu_url }</td>
									<td>${menu.menu_desc }</td>
									<td>${fn:split(menu.create_time," ")[0] }</td>
									<td>${menu.weight }</td>
									<td>
										<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="beforeEdit('${menu.menu_code}');">
												<i class="icon-edit bigger-120"></i>修改
											</button>
<%-- 
											<button class="btn btn-xs btn-danger"
												onclick="del_menu(${menu.id});">
												<i class="icon-trash bigger-120"></i>删除
											</button> --%>
										</div>
									</td>
								</tr>
								<c:set value="0" var="sub_v"/>
								<c:forEach items="${menu_list }" var="sub_menu">
								<c:if test="${sub_menu.parent_menu_code==menu.menu_code }">
								<c:set value="${sub_v+1 }" var="sub_v"/>
								<tr>
									<td></td>
									<td>${sub_v}<span style="padding:0 0 0 30px;border-right:1px solid #000;">——</span>&nbsp;&nbsp;&nbsp;${sub_menu.menu_name }</td>
									<td>${sub_menu.menu_code }</td>
									<td>${sub_menu.menu_url }</td>
									<td>${sub_menu.menu_desc }</td>
									<td>${fn:split(sub_menu.create_time," ")[0] }</td>
									<td>${sub_menu.weight }</td>
									<td>
										<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="beforeEdit('${sub_menu.menu_code}');">
												<i class="icon-edit bigger-120"></i>修改
											</button>
										</div>
									</td>
								</tr>
								</c:if>
								</c:forEach>
								</c:if>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- /.table-responsive -->
		</div>
	</div>
	<div id="add_form"
		style="display: none; width: 600px; height: 500px; margin-top: 30px;">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="AddAdvertiseForm" method="post" action="save_menu_info.do"
					role="form" onsubmit="return checkForm()">
					<input type="hidden" name="menu_belong_to_role" id="menu_belong_to_role">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 菜单名称： </label>
						<div class="col-sm-9">
							<input type="text"
								name="menu_name" style="width: 320px;" placeholder="请输入菜单名称"
								class="col-xs-10 col-sm-5" required /><span style="color: red;">*</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 菜单标识： </label>
						<div class="col-sm-9">
							<input type="text" name="menu_code" id="menu_code" style="width: 320px;" placeholder="菜单标识为小写字母"
								class="col-xs-10 col-sm-5" required onchange="checkMenucode(this)" />
							<span id="menu_code_erroe" style="color: red;"></span><span style="color: red;">*</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 父级菜单标识： </label>
						<div class="col-sm-9">
							<select name="parent_menu_code" style="width:270px;">
								<option value="console">控制台</option>
								<option value="top">顶级菜单</option>
								<c:forEach items="${menu_list}" var="menu">
								<c:if test="${menu.parent_menu_code=='top' }">
								<option value="${menu.menu_code }">${menu.menu_name }</option>
								</c:if>
								</c:forEach>
							</select><span style="color: red;">*</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 菜单地址： </label>
						<div class="col-sm-9">
						<input type="text" name="menu_url" style="width: 320px;" placeholder="请输入菜单地址"
								class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 排序权重： </label>
						<div class="col-sm-9">
							<input type="number" class="input_text" value="1" name="weight" style="width: 320px;" required />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 菜单描述： </label>
						<div class="col-sm-9">
							<textarea rows="6" cols="50" name="menu_desc" required></textarea><span style="color: red;">*</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<div class="col-sm-9">
							<div class="col-md-offset-3 col-md-9 text-right">
								<input type="hidden" value="0" id="edit_id" name="id" /> 
								<button class="btn btn-sm btn-success" type="submit">
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