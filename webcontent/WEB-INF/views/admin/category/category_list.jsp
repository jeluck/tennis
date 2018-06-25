<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分类</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript"
	src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
    var index;
    //页面跳转
    function go_page(pageNumber){
//		location.href = "allsystemmessage.do?pagenumber="+pageNumber;
      var inputpagenumber = document.getElementById("pagenumberid");
      inputpagenumber.value = pageNumber;
      document.form1.action = "pagecategory.do";
      document.form1.submit();
    }

    function init(){
      index = $.layer({
        type : 1,
        shade : [0],
        moveOut: true,
        area : ['650px', '500px'],
        title : "<b>添加分类</b>",
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
      $('#AddPhraseForm').form("clear");
//      $("#edit_role_code").removeAttr("readonly");
//      $('#AddPhraseForm input[name=id]').val(0);
      init();
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
			<li class="active">管理</li>
			<li class="active">商品分类</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			分类列表 <span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>

	<div style="margin: 0 0 10px 15px; clear: both; height: 30px;">
		<button class="btn btn-sm btn-success pull-left"
			onclick="beforeAdd();">
			<i class="icon-ok"></i>添加分类
		</button>
	</div>

	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline"
			action="javascript:void(0);" method="post">
			<input type="hidden" value="${data_page.currentPage}"
				name="pagenumber" id="pagenumberid"> <input type="hidden"
				value="" name="oid" id="oid">
		</form>
	</div>


	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th width="10%">序号</th>
							<th>分类</th>
							<th>图标</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<c:if var="havadata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havadata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havadata }">
							<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
							<c:forEach items="${data_page.dataList }" var="category"
								varStatus="v">
								<tr>
									<td>${v.count + (data_page.currentPage-1)*10}</td>
									<td>${category.category_name}</td>
									<td><img alt="" src="/${category.imgurl}" style="width:50px;height:50px;"></td>
									<td>
										<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-danger"
												onclick="location='toeditCategory.do?pagenumber=${data_page.currentPage}&id=${category.id }'">
												<i class="icon-edit bigger-120">&nbsp;修改</i>
											</button>
<!-- 											<button class="btn btn-xs btn-danger" -->
<%-- 												onclick="location='deletecategory.do?pagenumber=${data_page.currentPage}&id=${category.id }'"> --%>
<!-- 												<i class="icon-remove bigger-120">&nbsp;删除</i> -->
<!-- 											</button> -->
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
					<li><a href="javascript:void(0);">第${data_page.currentPage
							}页/共${data_page.pageCount }页</a></li>
					<li><a href="javascript:go_page(1);">首页</a></li>
					<li
						class="prev <c:if test="${data_page.currentPage==1}"> disabled</c:if>">
						<c:if test="${data_page.currentPage-1>0 }">
							<a href="javascript:go_page(${data_page.currentPage-1 });">
								<<&nbsp;&nbsp;上一页 </a>
						</c:if> <c:if test="${data_page.currentPage-1==0 }">
							<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
						</c:if>
					</li>
					<c:forEach begin="${data_page.startPage }"
						end="${data_page.pageCount }" varStatus="v">
						<li
							<c:if test="${v.index==data_page.currentPage }" var="next current">class="active"</c:if>
							<c:if test="${!current}">class="prev"</c:if>><a
							href="javascript:go_page(${v.index });">${v.index }</a></li>
					</c:forEach>
					<li
						class="next <c:if test="${data_page.currentPage==data_page.pageCount}"> disabled</c:if>">
						<c:if test="${data_page.currentPage+1<=data_page.pageCount}">
							<a href="javascript:go_page(${data_page.currentPage+1 });">
								下一页&nbsp;&nbsp;>> </a>
						</c:if> <c:if test="${data_page.currentPage==data_page.pageCount}">
							<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
						</c:if>
					</li>
					<li><a href="javascript:go_page(${data_page.pageCount });">尾页</a>
					</li>
					<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox"
						value="${data_page.currentPage }"
						data-options="min:1,max:${data_page.pageCount }" id="pagenum"
						style="width: 55px;" />页&nbsp;&nbsp;<input
						class="btn btn-xs btn-info" type="button"
						onclick="go_page($('#pagenum').val());" value="确定" /></li>
				</ul>
			</div>
		</c:if>
	</div>

	<div id="add_form"
		style="display: none; width: 600px; height: 500px; margin-top: 30px;">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="AddPhraseForm" method="post"
					action="addcategory.do?pagenumber=${data_page.currentPage}"
					role="form"  enctype="multipart/form-data">
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-sm-3 control-label no-padding-right" -->
<!-- 							or="form-field-1"> 一级分类： </label> -->
<!-- 						<div class="col-sm-9"> -->
<!-- 							<select name="categoryInfoid" > -->
<!-- 								<option value="">不选则为一级</option> -->
<%-- 								<c:forEach items="${categorylist }" var="o" varStatus="v"> --%>
<%-- 									<option value="${o.id}">${o.category_name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 商品分类： </label>
						<div class="col-sm-9">
							<textarea rows="6" cols="10" xclass="col-xs-10 col-sm-5"
								name="categoryname" style="width: 360px;" placeholder="请输入分类名称"
								required>
           					</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							or="form-field-1"> 分类图标： </label>
						<div class="col-sm-9">
						<input type="file" name="imgurl" >
						</div>
					</div>
					<div class="space-4"></div>
					<div class="space-4"></div>
					<div class="space-4"></div>
					<div class="form-group">
						<div class="col-sm-9">
							<div class="col-sm-9">
								<input type="hidden" value="0" id="edit_id" name="id" />
								<%--<label class="col-sm-3 control-label"--%>
								<%--for="form-field-2"></label>--%>
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
