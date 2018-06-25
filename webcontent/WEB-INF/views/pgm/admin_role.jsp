<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统角色管理</title>
<jsp:include page="init.jsp" />
<script language="javascript" type="text/javascript" src="../js/common.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
var index;
var option = "save";
var right_index;
var user_index;
var op_rolecode;//正在操作的role_code
function init(action){
	if(action=='修改'){
		$("#cooperative_partner_id").css("display","none");
	}
	
	index = $.layer({
	    type : 1,
	    shade : [0],
	    moveOut: true,
	    area : ['650px', '500px'],
	    title : "<b>"+action+"系统角色信息</b>",
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
function initrightDialog(){
	right_index = $.layer({
	    type : 1,
	    shade : [0],
	    moveOut: true,
	    area : ['750px', ($(window).height()-100)+'px'],
	    title : "<b>编辑角色权限信息</b>",
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    border: [10, 0.5, '#666'],
	    offset: ['50px' , ''],
	    success: function(){
	    	layer.shift('top', 400);
	    },
	    page : {dom : '#right_form'}
	});
	$("#right_form").height($(window).height()-160);
};
function inituserDialog(){
	user_index = $.layer({
	    type : 1,
	    shade : [0],
	    moveOut: true,
	    area : ['800px', '450px'],
	    title : "<b>角色绑定用户信息</b>",
	    shade: [0.5, '#e4e6e9'],
	    shadeClose:true,
	    border: [10, 0.5, '#666'],
	    offset: ['50px' , ''],
	    success: function(){
	    	layer.shift('top', 400);
	    },
	    page : {dom : '#user_form'}
	});
}
function beforeAdd(){
	$("#role_code_erroe").html("");
	$('#EditRoleForm').form("clear");
	$("#edit_role_code").removeAttr("readonly");
	$('#EditRoleForm input[name=id]').val(0);
	init("添加");
}
function beforeEdit(role_code){
	$("#role_code_erroe").html("");
	var role = util.GET("get_role_info.do",{"role_code":role_code});
	$('#EditRoleForm').form('load', role);
	$("#edit_role_code").attr("readonly","readonly");//code不能修改
	$("#edit_id").val(role.id);
	init("修改");
}
//编辑角色权限信息
function beforeEditRight(role_code){
	var data = util.GET("get_role_rights.do",{"role_code":role_code});
	//封装对象
	var right_data = [];
	var right;
	var sub_right;
	var child_arr;
	$.each(data.menulist,function(i,item){
		if(item.parent_menu_code=='top' || item.parent_menu_code=='console'){
			right = {};
			right.menu_code = item.menu_code;
			right.menu_name = item.menu_name;
			right.checked = false;
			$.each(data.rolerights,function(si,it){
				if(it.menu_code==item.menu_code){
					right.checked = true;
					return false;
				}
			});
			child_arr = [];
			$.each(data.menulist,function(ci,child_it){
				if(child_it.parent_menu_code==item.menu_code){
					sub_right = {};
					sub_right.menu_code = child_it.menu_code;
					sub_right.menu_name = child_it.menu_name;
					sub_right.checked = false;
					$.each(data.rolerights,function(si1,it1){
						if(it1.menu_code==child_it.menu_code){
							sub_right.checked = true;
							return false;
						}
					});
					child_arr.push(sub_right);
				}
			});
			right.rights = child_arr;
			right_data.push(right);
		}
	});
	$("#right_role_id").val(role_code);
	eachRighs(right_data);
	initrightDialog();
}
//迭代出需要选中的值
function eachRighs(right_data){
 	var righthtml = '<div class="app"><p><input type="checkbox" class="btn btn-sm btn-success " onclick="check_all(this)" value="全选" /><strong>后台管理权限</strong></p>';
 	$.each(right_data,function(i,item){
 		righthtml += '<dl>';
 		righthtml += '<dt>';
 		righthtml += '<input type="checkbox" name="menu_id" level="1" onclick="check_childs(this);" value="'+item.menu_code+'" ';
 		if(item.checked){
			righthtml += ' checked="checked" />';
		}else{
			righthtml += ' />';
		}
 		righthtml += '<strong>'+item.menu_name+'</strong>';
 		righthtml += '</dt>';
 		$.each(item.rights,function(index,menu){
 			righthtml += '<dd>';
				righthtml += '<input type="checkbox" name="menu_id" level="2" onclick="check_childs2(this);" value="'+menu.menu_code+'" ';
				if(menu.checked){
					righthtml += ' checked="checked" />';
				}else{
					righthtml += ' />';
				}
				righthtml += '<span>'+menu.menu_name+'</span>';
			righthtml += '</dd>';
 		});
 		righthtml += '</dl>'; 
 	});
 	righthtml += '</div>'; 
	$("#warp").html(righthtml);
}
//对于ChcekBox 操作
function check_all(_this){
	if(_this.checked){
		$("#warp").find("input[type=checkbox][id!=check_all]").prop("checked",true);
	}else{
		$("#warp").find("input[type=checkbox][id!=check_all]").prop("checked",false);
	}
}
function check_childs(_this){
	if(_this.checked){
		$(_this).parents("dl").find("input[type=checkbox][level=2]").prop("checked",true);
	}else{
		
		$(_this).parents("dl").find("input[type=checkbox][level=2]").prop("checked",false);
	}
}
function check_childs2(_this){
	if(_this.checked){
		$(_this).parents("dl").find("input[type=checkbox][level=1]").prop("checked",true);
	}else{
		$.each($(_this).parents("dl").find("dd input[type=checkbox]"),function(index,items){
			if($(items).prop("checked")){
				$(_this).parents("dl").find("input[type=checkbox][level=1]").prop("checked",true);
				return false;
			}else{
				$(_this).parents("dl").find("input[type=checkbox][level=1]").prop("checked",false);
			}
		});
		
	}
}

/**
 * 保存角色权限信息
 */
function saveRoleRights(){
	var info = util.serializeObject($("#rights_form"));
	info = util.POST("save_role_rights.do",info);
	if(info.status){
		parent.ShowMsg("操作提示：",info.info);
		layer.close(right_index);
	}
}

/**
 * ---- 角色绑定用户  ------------------- 华丽的分割线 ------------------------
 */
 function beforeEditRoleUsers(role_code,cooperative_partner_id){
	$("#cooperative_partner_id").val(cooperative_partner_id);	//点击分配角色时,设置合作商id
	 op_rolecode = role_code;
	 var data = util.GET("get_role_users.do",{"role_code":role_code,"cooperative_partner_id":cooperative_partner_id}); 
	 $("#selected_user").html(each_option(data.select_users));
	 $("#all_user").html(each_option(data.not_select_users));
	 inituserDialog();
}
function each_option(data){
	var option_html = '';
	$.each(data,function(i,item){
		option_html += '<option value="'+item.id+'">'+item.username+'&nbsp;&nbsp;-&nbsp;&nbsp;'+item.usercode+'</option>';
	});
	return option_html;
}
//移动
function youyi(){
	$("#all_user").append($("#selected_user").find("option:selected").get(0));
}
function zuoyi(){
	$("#selected_user").append($("#all_user").find("option:selected").get(0));
}
//提交
function saveRoleUsers(){
	var options = $("#selected_user").find("option");
	var cooperative_partner_id = $("#cooperative_partner_id").val();
	var users = "";
	$.each(options,function(i,option){
		if(i==0){
			users += $(option).attr("value");
		}else{
			users += ','+$(option).attr("value");
		}
	});
	info = util.POST("save_role_users.do",{"role_code":op_rolecode,"users":users,"cooperative_partner_id":cooperative_partner_id});
	if(info.status){
		parent.ShowMsg("操作提示：",info.info);
		layer.close(user_index);
	}
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


function checkRole_code(){
	var info = util.POST("/admin/checkRole_code.do",{"role_code":$("#edit_role_code").val(),"id":$("#edit_id").val()});
	if(info<=0){
		$("#role_code_erroe").html("此角色标识已被使用");
		return 0;
	}else{
		$("#role_code_erroe").html("");
	}
	return 1;
}

function checkForm()
{
	if(checkRole_code()>0){
		return true;
	}else{
		return false;
	}
}
</script>
<style type="text/css">
<!--权限控制样式 -->
#warp {
	border: 1px solid #ccc;
	border-radius: 4px;
	overflow: hidden;
	width: 94%;
	height: auto;
	padding: 10px 20px;
	margin: 20px auto;
}

#warp .app {
	border: 1px solid #f6f6f6;
	border-raduis: 3px;
	margin-top: 20px;
	padding: 10px;
}

#warp .app p {
	line-height: 30px;
	height: 30px;
}

#warp .app strong {
	font-size: 20px;
	color: #0b99d8;
	font-weight: 700;
	padding: 10px 20px;
}

#warp .app dl {
	margin: 10px 0;
	border: 1px solid #dcdcdc;
	height: auto;
	overflow: hidden;
}

#warp .app dl dt {
	display: block;
	height: 36px;
	line-height: 36px;
	background: #e6e6fa;
	text-indent: 10px;
}

#warp .app dl dt strong {
	font-size: 16px;
	color: #61aefa;
}

#warp .app dl dd {
	padding: 10px;
	float: left;
	width:150px;
	font-family:"Microsoft YaHei", Verdana, Geneva, sans-serif;
	color: #2679b5;
}
</style>
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
			<li class="active">角色管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>系统角色列表</h1>
	</div>
	<div style="margin: 0 0 10px 15px; clear: both; height: 30px;">
		<button class="btn btn-sm btn-success pull-left"
			onclick="beforeAdd();">
			<i class="icon-ok"></i>添加角色信息
		</button>
	</div>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 50px;">序号</th>
							<th style="width: 120px;">角色名称</th>
							<th style="width: 100px;">角色标识</th>
							<th style="width: 300px;">角色描述</th>
							<th style="width: 120px;"><i class="icon-time"></i>创建时间</th>
							<th style="width: 140px;">权限操作</th>
							<th style="width: 100px;">操作</th>
						</tr>
					</thead>

					<tbody>
						<c:set var="hava" value="${not empty role_list}" />
						<c:if test="${hava }">
							<c:set value="0" var="v"/>
							<c:forEach items="${role_list }" var="role" varStatus="v">
								<tr>
									<td>${v.index+1}</td>
									<td>${role.role_name }</td>
									<td>${role.role_code }</td>
									<td>${role.role_desc }</td>
									<td>${role.create_time }</td>
									<td>
										<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="beforeEditRight('${role.role_code}');">
												<i class="icon-edit bigger-120"></i>分配权限
											</button>
											<button class="btn btn-xs"
												onclick="beforeEditRoleUsers('${role.role_code}','${role.cooperative_partner_id }');">
												<i class="icon-wrench  bigger-120 icon-only"></i>分配用户
											</button>
										</div>	
									</td>
									<td>
										<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="beforeEdit('${role.role_code}');">
												<i class="icon-edit bigger-120"></i>修改
											</button>

											<%-- <button class="btn btn-xs btn-danger"
												onclick="delAdvertise(${role.id});">
												<i class="icon-trash bigger-120"></i>删除
											</button> --%>
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
	</div>
	<div id="add_form" style="display: none; width: 600px; height: 500px; margin-top: 30px;">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="EditRoleForm" method="post" action="save_role_info.do"
					role="form" onsubmit="return checkForm()" >
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 角色名称： </label>
						<div class="col-sm-9">
							<input type="text"
								name="role_name" style="width: 320px;" placeholder="请输入角色名称"
								class="col-xs-10 col-sm-5" required />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 角色标识： </label>
						<div class="col-sm-9">
							<input type="text" name="role_code" id="edit_role_code" style="width: 320px;" placeholder="角色标识为小写字母"
								class="col-xs-10 col-sm-5"required onchange="checkRole_code()"  />
								<span id="role_code_erroe" style="color: red;"></span>
						</div>
					</div>
					<c:if test="${not empty kindergartens }">
						<div class="form-group" id="cooperative_partner_id">
							<label class="col-sm-3 control-label no-padding-right" or="form-field-1"> 角色归属： </label>
							<div class="col-sm-9">
								<select name="cooperative_partner_id" style="width:270px;">
									<c:forEach items="${kindergartens}" var="o">
									<option value="${o.id }">${o.username }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 角色描述： </label>
						<div class="col-sm-9">
							<textarea rows="6" cols="50" name="role_desc" required></textarea>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<div class="col-sm-9">
							<div class="col-md-offset-3 col-md-9">
								<input type="hidden" value="0" id="edit_id" name="id" /> 
								<label class="col-sm-3 control-label"
									for="form-field-2"></label>
								<button class="btn btn-sm btn-success" type="submit"> <i class="icon-ok bigger-90"></i>[ 确定 ]
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
	<!-- 编辑角色权限 -->
	<div id="right_form" style="display: none; height: 500px;width:750px; margin-top: 0px;overflow: auto;">
	<form id="rights_form">
	<div id="warp">
	</div>
	<input type="hidden" name="role_code" id="right_role_id"  />
	<div class="text-center">
	<button class="btn btn-sm btn-success" type="button" onclick="saveRoleRights();"> 
	<i class="icon-ok bigger-90"></i>[ 保存权限信息 ]
	</button>
	</div>
	</form>	
	</div>
	<!-- 编辑角色用户信息 -->
	<div id="user_form" style="display: none; width: 800px; height: 450px; margin-top: 30px;">
	<form id="users_form">
	<table align="center" style="margin-left:10px;">
		<tr>
			<th style="font-size: 20px;color: #0b99d8;">已绑定该角色用户</th>
			<th style="width:150px;font-size: 20px;text-align: center;"><span style="margin-left:-40px;">操作</span></th>
			<th style="font-size: 20px;color: #0b99d8;">未绑定该角色用户</th>
		</tr>
		<tr>
			<td>
			<select multiple="multiple" style="width:300px;height:250px;margin-right:20px;" id="selected_user" >
			</select>
			</td>
			<td>
				<input type="button" style="width:120px;" value=">>  右移" onclick="youyi();" class="btn btn-sm btn-white" />
				<input type="button" style="width:120px;margin-top:20px;" value="<<  左移" onclick="zuoyi();" class="btn btn-sm btn-white" />
			</td>
			<td>
			<select multiple="multiple" style="width:300px;height:250px;" id="all_user">
			</select>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<hr/>
				<div class="text-center">
				<input type="hidden" name="cooperative_partner_id" id="cooperative_partner_id" value="">
				<button class="btn btn-sm btn-success" type="button" onclick="saveRoleUsers();"> 
				<i class="icon-ok bigger-90"></i>[ 确定保存 ]
				</button>
				</div>
			</td>
		</tr>
	</table>
	<input type="hidden" name="role_id" id="right_role_id"  />
	
	</form>	
	</div>
</body>
</html>