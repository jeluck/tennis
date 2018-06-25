<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理团队人员</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript"
	src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
		location.href = "friendlylink_list.do?&pageNumber="+pageNumber;
	}
	function init(action){
		index = $.layer({
		    type : 1,
		    shade : [0],
		    moveOut: true,
		    area : ['700px', '350px'],
		    title : "<b>"+action+"友情链接信息</b>",
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    offset: ['60px' , ''],
		    success: function(){
		    	layer.shift('top', 400);
		    },
		    border: [10, 0.5, '#666'],
		    page : {dom : '#add_form'}
		});
	}
	//添加
	function addForm(){
		$("#img_url").attr("required","");
		init("添加");
		$("#option_form").attr("action","friendlylink_add.do");
	}
	//修改
	function editLink(id){
		var info = util.GET("friendlylink_info.do",{"id":id});
		$('#option_form').form('load', info);
		$("#pic_url").val(info.link_img);
		$("#id_edit").val(id);
		$("#img_url").removeAttr("required");
		$("#append_img").html('<img src="../'+info.link_img+'"  style="width:150px;height:60px;border:1px solid #ccc;" />');
		$("#option_form").attr("action","friendlylink_update.do");
		init("修改");
	}
	function delLink(id){
		$.layer({
		    shade: [0],
		    area: ['auto','auto'],
		    shade: [0.5, '#e4e6e9'],
		    shadeClose:true,
		    dialog: {
		        msg: '您确定要对该<b> 友情链接信息  </b>进行删除操作吗?',
		        btns: 2,                    
		        type: 4,
		        btn: ['[确定]','[取消]'],
		        yes: function(){
		        	parent.parent.ShowMsg("操作提示：","已经成功删除该友情链接信息！ ");
		        	location.href = "friendlylink_del.do?id="+id;
		        }
		    }
		});
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
			<li class="active">友情链接</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			友情链接列表 <span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>

	<div style="margin: 0 0 10px 10px; clear: both; height: 30px;">
		<button class="btn btn-sm btn-success pull-left" onclick="addForm();">
			<i class="icon-ok"></i>添加友情链接
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
							<th>链接logo</th>
							<th>链接名称</th>
							<th>类型</th>
							<th>排序值</th>
							<th><i class="icon-time"></i>发布时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if var="havadata" test="${not empty link_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="6" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
							<c:set var="base" value="${(link_page.currentPage-1)*10 }" />
							<c:forEach items="${link_page.dataList }" var="link"
								varStatus="v">
								<tr>
									<td>${base+v.index+1}</td>
									<td><img src="/${link.link_img}"
										style="width: 150px; height: 60px;" /></td>
									<td><a href="${link.link_url}" target="_blank">${link.link_name}</a></td>
									<td>
									<c:if test="${link.link_type == 1}">媒体报道</c:if>
									<c:if test="${link.link_type == 2}">合作机构</c:if>
									</td>
									<td>${link.sort}</td>
									<td>${link.create_time }</td>
									<td>
										<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info"
												onclick="editLink(${link.id});">
												<i class="icon-edit bigger-120"></i>修改
											</button>
											<button class="btn btn-xs btn-danger"
												onclick="delLink(${link.id});">
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
		<c:if test="${havadata }">
			<div class="modal-footer no-margin-top">

				<ul class="pagination pull-right no-margin">
					<li><a href="javascript:void(0);">第${link_page.currentPage
							}页/共${link_page.pageCount }页</a></li>
					<li><a href="javascript:go_page(1);">首页</a></li>
					<li
						class="prev <c:if test="${link_page.currentPage==1}"> disabled</c:if>">
						<c:if test="${link_page.currentPage-1>0 }">
							<a href="javascript:go_page(${link_page.currentPage-1 });">
								<<&nbsp;&nbsp;上一页 </a>
						</c:if> <c:if test="${link_page.currentPage-1==0 }">
							<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
						</c:if>
					</li>
					<c:forEach begin="${link_page.startPage }"
						end="${link_page.pageCount }" varStatus="v">
						<li
							<c:if test="${v.index==link_page.currentPage }" var="next current">class="active"</c:if>
							<c:if test="${!current}">class="prev"</c:if>><a
							href="javascript:go_page(${v.index });">${v.index }</a></li>
					</c:forEach>
					<li
						class="next <c:if test="${link_page.currentPage==link_page.pageCount}"> disabled</c:if>">
						<c:if test="${link_page.currentPage+1<=link_page.pageCount}">
							<a href="javascript:go_page(${link_page.currentPage+1 });">
								下一页&nbsp;&nbsp;>> </a>
						</c:if> <c:if test="${link_page.currentPage==link_page.pageCount}">
							<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
						</c:if>
					</li>
					<li><a href="javascript:go_page(${link_page.pageCount });">尾页</a>
					</li>
					<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox"
						value="${link_page.currentPage }"
						data-options="min:1,max:${link_page.pageCount }" id="pagenum"
						style="width: 55px;" />页&nbsp;&nbsp;<input
						class="btn btn-xs btn-info" type="button"
						onclick="go_page($('#pagenum').val());" value="确定" /></li>
				</ul>
			</div>
		</c:if>
	</div>
	<div id="add_form"
		style="display: none; width: 600px; height: 600px; margin-top: 30px;">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="option_form"
					action="friendlylink_add.do" role="form" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 链接名称 </label>
						<div class="col-sm-9">
							<input type="text" name="link_name" style="width: 360px;"
								placeholder="请输入链接名称" class="col-xs-10 col-sm-5" required />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 链接地址 </label>
						<div class="col-sm-9">
							<input type="text" name="link_url" style="width: 360px;"
								placeholder="请输入链接地址" class="col-xs-10 col-sm-5" required />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" or="form-field-1">
							链接类型
						</label>
						<div class="col-sm-9">
							<select name="link_type">
								<option value="2">合作机构</option>
								<option value="1">媒体报道</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 请上传链接logo </label>
						<div class="col-sm-9">
							<span id="append_img"></span> <input type="file" name="img_url"
								id="img_url" style="width: 200px;" placeholder="请上传链接logo"
								class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 排序值 </label>
						<div class="col-sm-9">
							<input type="number" name="sort" value="1" style="width: 360px;"
								placeholder="请输入排序值" class="col-xs-10 col-sm-5" required />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<div class="col-md-offset-3 col-md-9">
								<input type="hidden" name="pic_url" id="pic_url" /> <input
									type="hidden" name="id" id="id_edit" value="0" /> <label
									class="col-sm-3 control-label no-padding-right"
									for="form-field-2"></label>
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