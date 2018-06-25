<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头条</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript"
	src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var K ;
	var index;
	var editor;
	KindEditor.ready(function(k) {
		K = k;
	});
	//页面跳转
	function go_page(pageNumber){
		location.href = "notice_list.do?&pageNumber="+pageNumber;
	}
	
	function addNotice(){
		document.getElementById('title').value = "";
		index = $.layer({
		    type : 1,
		    shade : [0],
		    moveOut: true,
		    area : ['850px', '600px'],
		    title : "<b>添加头条信息</b>",
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    border: [10, 0.5, '#666'],
		    offset: ['60px' , ''],
		    success: function(){
		    	layer.shift('top', 400);
		    },
		    page : {dom : '#add_form'}
		});
		if(editor!=undefined){
			K.remove('#content');
		}
		editor = K.create('#content', {
			width : '500px',
			themeType : 'simple',
			uploadJson : '../js/kindeditor/jsp/upload_json.jsp',
            fileManagerJson : '../js/kindeditor/jsp/file_manager_json.jsp',
            allowFileManager : true,
            afterBlur : function(){  
                //编辑器失去焦点时直接同步，可以取到值  
                this.sync();  
            }   
        });
		editor.html("");
		$('#option_form').attr("action","notice_add.do");
	}
	
	function editNotice(id){
		var info = util.GET("notice_info.do",{"id":id});
		$("#id_edit").val(id);
		index = $.layer({
		    type : 1,
		    shade : [0],
		    moveOut: true,
		    area : ['850px', '600px'],
		    title : "<b>编辑头条信息</b>",
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    offset: ['60px' , ''],
		    success: function(){
		    	layer.shift('top', 400);
		    },
		    border: [10, 0.5, '#666'],
		    page : {dom : '#add_form'}
		});
		if(editor!=undefined){
			K.remove('#content');
		}
		editor = K.create('#content', {
			themeType : 'simple',
			uploadJson : '../js/kindeditor/jsp/upload_json.jsp',
            fileManagerJson : '../js/kindeditor/jsp/file_manager_json.jsp',
            allowFileManager : true,
            afterBlur : function(){  
                //编辑器失去焦点时直接同步，可以取到值  
                this.sync();  
            }   
        });
		editor.html(info.content);
		$("#add_form").width(850);
		$('#option_form').attr("action","notice_update.do");
		$("#title").val(info.title);
		document.getElementById('user_province').value=info.province_id;
		getCity('user_province');
		document.getElementById('user_city').value=info.city_show_id;
	}
	
	//删除
	function del_notice(id){
		$.layer({
		    shade: [0],
		    area: ['auto','auto'],
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    dialog: {
		        msg: '您确定要对该<b> 公告信息  </b>进行删除操作吗?',
		        btns: 2,                    
		        type: 4,
		        btn: ['[确定]','[取消]'],
		        yes: function(){
		        	parent.parent.ShowMsg("操作提示：","已经成功删除该公告信息！ ");
		        	location.href="notice_del.do?id="+id;
		        }
		    }
		});
	}
	
	function checkForm(){
		if($("#content").val() == null || $("#content").val() == ""){
			alert("请填写内容");
			return false;	
		}
		return true;
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
			<li class="active">头条</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			头条列表 <span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>
	<div style="margin: 0 0 10px 10px; clear: both; height: 30px;">
		<button class="btn btn-sm btn-success pull-left"
			onclick="addNotice();">
			<i class="icon-ok"></i>添加头条
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
							<th>头条标题</th>
							<th><i class="icon-time"></i>发布时间</th>
							<th>发布人</th>
<!-- 							<th>显示设备类型</th> -->
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if var="havadata" test="${not empty notice_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="5" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
							<c:forEach items="${notice_page.dataList }" var="notice"
								varStatus="v">
								<tr>
									<c:set var="base" value="${(notice_page.currentPage-1)*10 }" />
									<td>${base+v.index+1}</td>
									<td>${notice.title}</td>
									<td>${notice.create_time}</td>
									<td>${notice.manager.username }</td>
<%-- 									<td><c:if test="${notice.type==1 }" var="has">PC端公告</c:if> --%>
<%-- 										<c:if test="${!has }">Mobile端公告</c:if></td> --%>
									<td>
										<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="editNotice(${notice.id});">
												<i class="icon-edit bigger-120"></i>修改
											</button>
											<button class="btn btn-xs btn-danger"
												onclick="del_notice(${notice.id});">
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
		</div>
		<!-- /span -->
		<c:if test="${havadata }">
			<div class="modal-footer no-margin-top">
				<ul class="pagination pull-right no-margin">
					<li><a href="javascript:void(0);">第${notice_page.currentPage
							}页/共${notice_page.pageCount }页</a></li>
					<li><a href="javascript:go_page(1);">首页</a></li>
					<li
						class="prev <c:if test="${notice_page.currentPage==1}"> disabled</c:if>">
						<c:if test="${notice_page.currentPage-1>0 }">
							<a href="javascript:go_page(${notice_page.currentPage-1 });">
								<<&nbsp;&nbsp;上一页 </a>
						</c:if> <c:if test="${notice_page.currentPage-1==0 }">
							<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
						</c:if>
					</li>
					<c:forEach begin="${notice_page.startPage }"
						end="${notice_page.pageCount }" varStatus="v">
						<li
							<c:if test="${v.index==notice_page.currentPage }" var="next current">class="active"</c:if>
							<c:if test="${!current}">class="prev"</c:if>><a
							href="javascript:go_page(${v.index });">${v.index }</a></li>
					</c:forEach>
					<li
						class="next <c:if test="${notice_page.currentPage==notice_page.pageCount}"> disabled</c:if>">
						<c:if test="${notice_page.currentPage+1<=notice_page.pageCount}">
							<a href="javascript:go_page(${notice_page.currentPage+1 });">
								下一页&nbsp;&nbsp;>> </a>
						</c:if> <c:if test="${notice_page.currentPage==notice_page.pageCount}">
							<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
						</c:if>
					</li>
					<li><a href="javascript:go_page(${notice_page.pageCount });">尾页</a>
					</li>
					<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox"
						value="${notice_page.currentPage }"
						data-options="min:1,max:${notice_page.pageCount }" id="pagenum"
						style="width: 55px;" />页&nbsp;&nbsp;<input
						class="btn btn-xs btn-info" type="button"
						onclick="go_page($('#pagenum').val());" value="确定" /></li>
				</ul>
			</div>
		</c:if>
	</div>
	<div id="add_form"
		style="display: none; margin: 0 0 0 0px; padding-top: 20px; width: 850px; height: 566px; overflow: auto;">
		<div>
			<div class="col-xs-12">
				<form id="option_form" style="width: 760px;" class="form-horizontal"
					action="notice_add.do" role="form" method="post" onsubmit="return checkForm()" >
					<input type="hidden" name="type" value="2">
					<table style="width: 800px;">
						<tr>
							<td class="text-right col-sm-3 control-label no-padding-right">
								公告标题</td>
							<td class="text-left" style="padding-top: 15px;">
								<div class="col-sm-9">
									<input type="text" name="title" id="title"  style="width: 360px;"
										placeholder="请输入公告标题" class="col-xs-10 col-sm-5" required />
								</div>
							</td>
						</tr>
						<tr>
							<td class="text-right col-sm-3 control-label no-padding-right">
								公告类型</td>
							<td class="text-left" style="padding-top: 15px;">
								<div class="col-sm-9">
									<select name="notice_type">
										<option value="1">网站公告</option>
										<option value="2">新闻板块</option>
									</select>
								</div>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td class="text-right col-sm-3 control-label no-padding-right"> -->
<!-- 								设备类型</td> -->
<!-- 							<td class="text-left" style="padding-top: 15px;"> -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<select name="type"> -->
<!-- 										<option value="2">Mobile端公告</option> -->
<!-- 										<option value="1">PC端公告</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr >
							<td class="text-right col-sm-3 control-label no-padding-right" >
								省份:
							</td>
							<td class="text-left" style="padding-top: 15px;">&nbsp;&nbsp;
						        <select id="user_province" name="province_id"   onchange="getCity('user_province')" class="bankSelected" >
									<option value="0">请选择省份</option>
									<c:forEach items="${provincelist}" var="p">
										<option value="${p.region_id}" <c:if test="${ca.user_province==p.region_id }">selected="selected"</c:if> >${p.region_name}</option>
									</c:forEach>
								</select>	
						        <select  id="user_city" name="city_show_id"  class="bankSelected" >
						        	<option value="0" <c:if test="${empty r.region_id }">selected="selected"</c:if>>请选择城市</option>
									<option value="${r.region_id }" <c:if test="${not empty r.region_id}">selected="selected"</c:if> >${r.region_name }</option>
								</select>	
	    					</td>
	    					
						</tr>
						<tr>
							<td class="text-right col-sm-3 control-label no-padding-right">
								公告标题内容</td>
							<td class="text-left" style="padding-top: 15px;">
								<div class="col-sm-9">
									<input type="hidden" name="id" id="id_edit" value="0" />
									<textarea name="content" id="content" 
										style="height: 370px; width: 600px;" placeholder="请输入公告标题内容"></textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td class="text-right col-sm-3 control-label no-padding-right"></td>
							<td class="text-left" style="padding: 25px 0 20px 0;">
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
	</div>
</body>
<script>		
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
</script>
</html>