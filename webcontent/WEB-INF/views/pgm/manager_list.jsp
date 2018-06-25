<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理员信息</title>
<jsp:include page="init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		location.href = "managerlist.do?&pageNumber="+pageNumber;
	}
	//添加管理员信息
	function addNotice(){
		$('#option_form').form('clear');
		$("#id_edit").val(0);
		var pswd_html = '<tr id="edit_pswd">';
		pswd_html +='<td class="text-right col-sm-3 control-label no-padding-right">登录密码</td>';
		pswd_html +='<td class="text-left" style="padding-top: 15px;">';
		pswd_html +='<div class="col-sm-9">';
		pswd_html +='<input type="text" name="password"  placeholder="请输入管理员登录密码"  style="width: 360px;" class="col-xs-10 col-sm-5" required />';
		pswd_html +='</div>';
		pswd_html +='</td>';
		pswd_html +='</tr>';
		$("#edit_pswd")[0]?1:$("#append_pswd").after(pswd_html);
		index = $.layer({
		    type : 1,
		    shade : [0],
		    moveOut: true,
		    area : ['750px', '500px'],
		    title: '<b>添加管理员信息</b>',
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    border: [10, 0.5, '#666'],
		    offset: ['20px' , ''],
		    success: function(){
		    	layer.shift('top', 400);
		    	layer.autoArea(index);
		    },
		    page : {dom : '#add_form'}
		});
	}
	
	//编辑管理员信息
	function editManager(id){
		$("#edit_pswd").remove();
		var info = util.GET("/pgm/pgminfo.do",{"id":id});
		$('#option_form').form('load', info);
		$("#id_edit").val(id);
		index = $.layer({
		    type : 1,
		    shade : [0],
		    moveOut: true,
		    area : ['750px', '450px'],
		    title : "<b>编辑管理员信息</b>",
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    offset: ['60px' , ''],
		    success: function(){
		    	layer.shift('top', 400);
		    	layer.autoArea(index);
		    },
		    border: [10, 0.5, '#666'],
		    page : {dom : '#add_form'}
		});
	}
	//锁定管理员
	function lockManager(id,active,action){
		$.layer({
		    shade: [0],
		    area: ['auto','auto'],
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    dialog: {
		        msg: '您确定要'+action+'该管理员吗?',
		        btns: 2,                    
		        type: 4,
		        btn: ['[确定]','[取消]'],
		        yes: function(){
		        	info = util.GET("/pgm/lock_pgm.do",{"id":id,"active":active});
		        	parent.ShowMsg("操作提示：",info.info);
		        	if(info.status){location.reload();}
		        }
		    }
		});
	}
	
	//保存信息
	function submitManager(){
		info = util.POST("save_pgm.do",util.serializeObject($("#option_form")));
		parent.ShowMsg("操作提示：",info.info);
		if(info.status){
			location.reload();
		}
		return false;
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
			<li class="active">权限管理</li>
			<li class="active">管理员管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>后台管理员列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>
	<c:if test="${not empty show}">
		<div style="margin:0 0 10px 10px;height:30px;clear:both;">
			<button class="btn btn-sm btn-success pull-left" onclick="addNotice();"><i class="icon-ok"></i>添加管理员</button>
		</div>
	</c:if>
	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>登录名(手机号)</th>
							<th>真实姓名</th>
							<th>积分</th>
							<th>性别</th>
							<th>所属类型</th>
							<th>管理员状态</th>
							<th style="width:150px;">操作</th>
							<th style="width:150px;">锁定操作</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty news_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
						<c:set var="base" value="${(news_page.currentPage-1)*10 }" />
						<c:forEach items="${news_page.dataList }" var="o" varStatus="v">
							<tr>
								<td>${v.index+1}</td>
								<td>${o.usercode}</td>
								<td>${o.real_name}</td>
								<td>${o.score}</td>
								<td>
									<c:if test="${o.sex == 1  }">男</c:if>
									<c:if test="${o.sex == 2  }">女</c:if>
								</td>
								<td>
									<c:choose>
										<c:when test="${o.kinder_manager_type == 1 }">园长</c:when>
										<c:when test="${o.kinder_manager_type == 2 }">老师</c:when>
										<c:when test="${o.kinder_manager_type == 3 }">行政处</c:when>
										<c:when test="${o.kinder_manager_type == 4 }">财务处</c:when>
										<c:when test="${o.kinder_manager_type == 5 }">教导处</c:when>
										<c:when test="${o.kinder_manager_type == 6 }">后勤处</c:when>
										<c:when test="${o.kinder_manager_type == 7 }">医务处</c:when>
										<c:when test="${o.kinder_manager_type == 8 }">保卫处</c:when>
										<c:when test="${o.kinder_manager_type == 9 }">校车司机</c:when>
										<c:otherwise>其他</c:otherwise>										
									</c:choose>
								</td>
								<td><c:if test="${o.flag==1 }" var="has">已启用</c:if>
									<c:if test="${!has }">已锁定</c:if>
								</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="editManager(${o.id});">
											<i class="icon-edit bigger-120"></i>修改
										</button>
									</div>	
								</td>
								<td>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<c:if test="${o.flag==1 }" var="has">
										<button class="btn btn-xs btn-danger" onclick="lockManager(${o.id},0,'锁定');">
											<i class="icon-lock bigger-120">锁定</i>
										</button>
										</c:if>
										<c:if test="${!has }">
										<button class="btn btn-xs btn-info" onclick="lockManager(${o.id},1,'激活');">
											<i class="icon-unlock bigger-130">激活</i>
										</button>
										</c:if>
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
		<c:if test="${havadata }">
		<div class="modal-footer no-margin-top">
			<ul class="pagination pull-right no-margin">
			    <li>
					<a href="javascript:void(0);">第${news_page.currentPage }页/共${news_page.pageCount }页</a>
				</li>
				<li>
					<a href="javascript:go_page(1);">首页</a>
				</li>
				<li class="prev <c:if test="${news_page.currentPage==1}"> disabled</c:if>">
					<c:if test="${news_page.currentPage-1>0 }">
						<a href="javascript:go_page(${news_page.currentPage-1 });">
							<<&nbsp;&nbsp;上一页
						</a>
					</c:if>
					<c:if test="${news_page.currentPage-1==0 }">
						<a href="javascript:void(0);">
							<<&nbsp;&nbsp;上一页
						</a>
					</c:if>
				</li>
				<c:forEach begin="${news_page.startPage }"
					end="${news_page.pageCount }" varStatus="v">
					<li <c:if test="${v.index==news_page.currentPage }" var="next current">class="active"</c:if><c:if test="${!current}">class="prev"</c:if>><a href="javascript:go_page(${v.index });">${v.index }</a></li>
				</c:forEach>
				<li class="next <c:if test="${news_page.currentPage==news_page.pageCount}"> disabled</c:if>">
					<c:if test="${news_page.currentPage+1<=news_page.pageCount}">
						<a href="javascript:go_page(${news_page.currentPage+1 });">
						下一页&nbsp;&nbsp;>>
						</a>
					</c:if>
					<c:if test="${news_page.currentPage==news_page.pageCount}">
						<a href="javascript:void(0);">
						下一页&nbsp;&nbsp;>>
						</a>
					</c:if>
				</li>
				<li>
					<a href="javascript:go_page(${news_page.pageCount });">尾页</a>
				</li>
				<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox" value="${news_page.currentPage }" data-options="min:1,max:${news_page.pageCount }" id="pagenum" style="width:55px;" />页&nbsp;&nbsp;<input class="btn btn-xs btn-info" type="button" onclick="go_page($('#pagenum').val());" value="确定"/></li>
			</ul>
		</div>
		</c:if>
	</div>
	<div class="row" ID="add_form" style="display: none;margin:0 0 0 0px;padding-top:20px;width:890px;;overflow: auto;">
		<div class="col-xs-10">
			<form class="form-horizontal" id="option_form" role="form" method="post" enctype="multipart/form-data" onsubmit="return submitManager();">
				<table style="width: 800px;">
					<tr>
						<td class="text-right col-sm-3 control-label no-padding-right">
							登录名</td>
						<td class="text-left" style="padding-top: 15px;">
							<div class="col-sm-9">
								<input type="text" name="usercode" style="width: 360px;"  placeholder="请输入管理员登录名" class="col-xs-10 col-sm-5" required />
							</div>
						</td>
					</tr>
					<tr>
						<td class="text-right col-sm-3 control-label no-padding-right">
							真实姓名</td>
						<td class="text-left" style="padding-top: 15px;">
							<div class="col-sm-9">
								<input type="text" name="real_name"  style="width: 360px;"  placeholder="请输入管理员真实姓名" class="col-xs-10 col-sm-5" required />
							</div>
						</td>
					</tr>
					<tr id="append_pswd">
						<td class="text-right col-sm-3 control-label no-padding-right">
							性别</td>
						<td class="text-left" style="padding-top: 15px;">
							<div class="col-sm-9">
								<select class="class" name="sex" style="width:  360px;">  
									<option value ="1">男士</option>
									<option value ="2">女士</option>  
								</select>  
							</div>
						</td>
					</tr>
					<tr id="append_pswd">
						<td class="text-right col-sm-3 control-label no-padding-right">
							所属类型</td>
						<td class="text-left" style="padding-top: 15px;">
							<div class="col-sm-9">
								<select class="class" name="kinder_manager_type" style="width:  360px;">  
									<c:forEach items="${getKinderStatus}" var="cv" varStatus="v">
										<c:set value="${ fn:split(cv, '=') }" var="str1" />
										<option value ="${str1[0]}">${str1[1]} </option>	
									</c:forEach>
								</select>  
							</div>
						</td>
					</tr>
					
					<tr>
						<td class="text-right col-sm-3 control-label no-padding-right">
							是否启用 </td>
						<td class="text-left" style="padding-top: 15px;">
							<div class="col-sm-9">
								<input type="radio" name="flag" value="1" checked="checked" />启用 &nbsp;&nbsp;<input type="radio" name="flag" value="0"/>不启用
							</div>
						</td>
					</tr>
					<tr>
						<td class="text-right col-sm-3 control-label no-padding-right"></td>
						<td class="text-left" style="padding: 25px 0 20px 0;">
							<input type="hidden" name="pageNumber" value="${news_page.currentPage }" />
							<input type="hidden" name="id" id="id_edit" value="0" />
							<div class="col-md-offset-3 col-md-9">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-2"></label>
								<button class="btn btn-sm btn-success" type="submit">
									<i class="icon-ok bigger-90"></i>[ 确定 ]
								</button>
								&nbsp; &nbsp; &nbsp;
								<button class="btn btn-sm" type="reset">
									<i class="icon-undo bigger-90"></i>[ 重置 ]
								</button>
							</div>
						</td>
					</tr>
				</table>	
			</form>
		</div>
	</div>
	
	<div class="row" ID="change_password_form" style="display: none;margin:0 0 0 0px;padding-top:20px;width:890px;;overflow: auto;">
		<div class="col-xs-10">
			<form class="form-horizontal" id="option_form" action="addmanager.do" role="form" method="post" enctype="multipart/form-data">
				<table style="width: 800px;">
					
					<!-- <tr>
						<td class="text-right col-sm-3 control-label no-padding-right">
							登录密码</td>
						<td class="text-left" style="padding-top: 15px;">
							<div class="col-sm-9">
								<input type="password" name="password" style="width: 360px;" class="col-xs-10 col-sm-5" required />
							</div>
						</td>
					</tr> -->
					
					<tr>
						<td class="text-right col-sm-3 control-label no-padding-right"></td>
						<td class="text-left" style="padding: 25px 0 20px 0;">
							<input type="hidden" name="pageNumber" value="${news_page.currentPage }" />
							<input type="hidden" name="id" id="id_edit" value="0" />
							<div class="col-md-offset-3 col-md-9">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-2"></label>
								<button class="btn btn-sm btn-success" type="submit">
									<i class="icon-ok bigger-90"></i>[ 确定 ]
								</button>
								&nbsp; &nbsp; &nbsp;
								<button class="btn btn-sm" type="reset">
									<i class="icon-undo bigger-90"></i>[ 重置 ]
								</button>
							</div>
						</td>
					</tr>
				</table>	
			</form>
		</div>
	</div>
</body>
</html>