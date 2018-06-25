<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>场馆运营者 - 后台首页</title>
<meta name="keywords" content="场馆运营者" />
<meta name="description" content="场馆运营者" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="icon" href="../image/shop/icon/favicon.ico" type="image/x-icon" />
<!-- basic styles -->
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<!-- self_defind styles -->
<link rel="stylesheet" href="../assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="../assets/css/google.css" />
<link rel="stylesheet" href="../assets/css/ace.min.css" />
<!-- basic scripts -->
<script src='../assets/js/jquery-2.0.3.min.js'></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../js/admin_common.js"></script>
<!-- ace scripts -->
<script src="../assets/js/ace-extra.min.js"></script>
<script src="../assets/js/ace.min.js"></script>
<script src="/js/admin/index.js"></script>
<script type="text/javascript" src="../js/layer/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	if('${iframe_page}'){
		changeUrl('${iframe_page}');//加载页面
	}
});

function kinderlogout() {
	window.location.href = "/pgm/logout.do";
}

//如果有新通知,则显示提示
<c:if test="${not empty sm }">
$(function () {
	$.layer({
		type: 1,
		title: '新消息',
		area : ['550px', '400px'],
		shade: [0.5, '#e4e6e9'],
		shadeClose:true,
		border: [10, 0.5, '#666'],
		page : {dom : '#add_form'}
	});
});
</c:if>

</script>
</head>
<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> 场馆运营者后台管理系统
				</small>
				</a>
			</div>

			<div class="navbar-header pull-right" role="navigation"
				id="navigation_p2p">
				<ul class="nav ace-nav">
					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> 
						
						<c:if test="${not empty k }" var="hava">
							<c:if test="${not empty k.header }" var="thava">
								<img class="nav-user-photo" src="/${k.header }" alt="管理员头像" width="40" height="40"/> 
							</c:if>
							<c:if test="${!thava }">
								<img class="nav-user-photo" src="../image/1_small.png" alt="默认头像" /> 
							</c:if>
						</c:if>
						<c:if test="${!hava }">
								<img class="nav-user-photo" src="../image/1_small.png" alt="无头像" /> 
						</c:if>
							<span
							class="user-info"> <small>欢迎光临,</small>
								${k.username }
						</span> <i class="icon-caret-down"></i>
					</a>
						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<!-- 							<li><a href="#"><i class="icon-cog"></i>设置</a></li>
							<li><a href="#"><i class="icon-user"></i>个人资料</a></li> -->
							<li><a href="#modal-table" role="button" data-toggle="modal"><i
									class="icon-unlock"></i>修改密码</a></li>
							<li class="divider"></li>
							<li><a href="javascript:kinderlogout();"><i class="icon-off"></i>退出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="main-container-inner" id="main-container_left">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<div class="sidebar" id="sidebar" style="height: 100%; overflow: auto;">
				<c:set var="hava_console" value="false" />
				<c:set var="m_index" value="0" />
				<ul class="nav nav-list">
					<!-- 用户权限菜单-->
					<c:forEach items="${menulist }" var="menu">
						<c:if test="${menu.parent_menu_code=='console'}">
						<c:set var="hava_console" value="true" />
						<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('${menu.menu_url }','${menu.menu_code}');"><i class="${menu.menu_icon }"></i> <span class="menu-text"> ${menu.menu_name} </span> </a></li>
						</c:if>
						<c:if test="${menu.parent_menu_code=='top' }">
						<li>
						<a href="#" class="dropdown-toggle"> <i class="${menu.menu_icon }"></i> <span class="menu-text"> ${menu.menu_name}   </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<c:forEach items="${menulist }" var="sub_menu">
							<c:if test="${sub_menu.parent_menu_code==menu.menu_code }">
							<c:if test="${m_index==0 }"><c:set var="first_menu" value="${sub_menu }" /><c:set var="m_index" value="1" /></c:if>
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('${sub_menu.menu_url }','${menu.menu_code}');"><i class="icon-double-angle-right"></i> ${sub_menu.menu_name } </a></li>
							</c:if>	
							</c:forEach>
						</ul>
						</li>
						</c:if>
					</c:forEach>
					 
					<!-- 新增链接 
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-key"></i> <span class="menu-text">权限管理</span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab">
								<a onclick="changeUrl('managerlist.do','Rights_management');" href="javascript:void(0);">
								<i class="icon-double-angle-right"></i> 管理员管理  </a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-user"></i> <span class="menu-text">用户 </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('userlist.do','');">
							<i class="icon-double-angle-right"></i> 列表 </a></li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-user"></i> <span class="menu-text">商品 </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('commoditypagelist.do','');">
							<i class="icon-double-angle-right"></i> 列表 </a></li>
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('toaddcommodity.do','');">
								<i class="icon-double-angle-right"></i> 添加商品 </a></li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-user"></i> <span class="menu-text">商品分类 </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('pagecategory.do','');">
								<i class="icon-double-angle-right"></i> 列表 </a></li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-reorder"></i> <span class="menu-text">订单 </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab">
								<a href="javascript:void(0);" onclick="changeUrl('orderlist.do','');">
									<i class="icon-double-angle-right"></i> 列表
								</a>
							</li>
							<li class="nav_tab">
								<a href="javascript:void(0);" onclick="changeUrl('waitforconfirmlist.do','');">
									<i class="icon-double-angle-right"></i> 待确认列表
								</a>
							</li>
							<li class="nav_tab">
								<a href="javascript:void(0);" onclick="changeUrl('waitfordeliverylist.do','');">
									<i class="icon-double-angle-right"></i> 待发货列表
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-reorder"></i> <span class="menu-text">认证 </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('real_name_authentication_list.do','');">
								<i class="icon-double-angle-right"></i> 实名认证 </a></li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-reorder"></i> <span class="menu-text">佣金 </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('commissionpagelist.do','');">
								<i class="icon-double-angle-right"></i> 列表 </a></li>
						</ul>
					</li>
					<li>
						<a href="#" class="dropdown-toggle"> <i class="icon-cog"></i> <span class="menu-text">系统配置</span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('systemConfiglist.do','');">
							<i class="icon-double-angle-right"></i> 系统配置 </a></li>
						</ul>
					</li>
					-->
					<!-- <li>
						<a href="#" class="dropdown-toggle"> <i class="0"></i> <span class="menu-text">测试  </span><b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="nav_tab"><a href="javascript:void(0);" onclick="changeUrl('list.do','test');">
							<i class="icon-double-angle-right"></i> 0 </a></li>
						</ul>
					</li> -->
				</ul>
				<!-- /.nav-list -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed');
					} catch (e) {
					}
				</script>
			</div>
			<div id="MainContentDiv" class="main-content"
				style="position: relative; left: 0px;">
				<iframe id="MainiFrame" <c:if test="${!hava_console}">src="${first_menu.menu_url}"</c:if>  frameborder="0"
					style="border: 0; width: 100%; height: 100%;"></iframe>
			</div>
		</div>
		<!-- /.main-container-inner -->
	</div>
	<div id="modal-table" class="modal fade" tabindex="100"
		style="position: absolute; z-index: 9999; top: 100px;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						重置您的密码
					</div>
				</div>
				<div class="modal-body no-padding">
					<table
						style="text-align: center; margin: 20px 0 20px 100px; height: 150px;">
						<tr>
							<td>请输入原密码：</td>
							<td><input type="password" name="oldpassword"
								id="oldpassword" /></td>
						</tr>
						<tr>
							<td>请输入新密码：</td>
							<td><input type="password" name="password" id="password" /></td>
						</tr>
						<tr>
							<td>请确认新密码：</td>
							<td><input type="password" name="repassword" id="repassword" /></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer no-margin-top">
					<button class="btn btn-sm btn-success pull-right"
						onclick="readyEditPWD()">
						<i class="icon-ok"></i> 确定
					</button>
					<button class="btn btn-sm btn-danger pull-right"
						data-dismiss="modal">
						<i class="icon-remove"></i> 取消
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 显示MSG -->
	<div id="show_success_msg" class="alert alert-success"
		style="position: absolute; top: 5px; left: 38%; display: none; z-index: 10000; width: 450px;">
		<button type="button" class="close">
			<i class="icon-remove" onclick="$('#show_success_msg').hide()"></i>
		</button>
		<strong> <i class="icon-ok"></i> <span id="msg_title"></span>
		</strong> <span id="msg_content"></span> <br>
	</div>
	
	
	<div class="row" ID="add_form" style="display: none;margin:0 0 0 0px;padding-top:20px;width:590px;;overflow: auto;">
		<div class="col-xs-10">
			<form class="form-horizontal" id="option_form" role="form" method="post" >
				<table style="width: 520px;">
					<tr>
						<td style="text-align: center;">
							${sm.title }
						</td>
					</tr>
					<tr>
						<td class="text-left" style="padding-top: 15px;">
							<div>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sm.content }
							</div>
						</td>
					</tr>
				</table>	
			</form>
		</div>
	</div>
	
</body>
</html>
