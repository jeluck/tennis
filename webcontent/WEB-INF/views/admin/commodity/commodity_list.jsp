<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	//页面跳转
	function go_page(pageNumber){
//		location.href = "orderlist.do?pagenumber="+pageNumber;
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = pageNumber;
		document.form1.action = "commoditypagelist.do";
		document.form1.submit();
	}

	function Query() {
		var inputpagenumber = document.getElementById("pagenumberid");
		inputpagenumber.value = 1;
		document.form1.action = "commoditypagelist.do";
		document.form1.submit();
	}
	function CancelQuery(){
		location.href = "commoditypagelist.do?pagenumber=1";
	}

	function orderdetail(oid){
		var inputoid = document.getElementById("id");
		inputoid.value = oid;
		document.form1.action = "commodityedit.do";
		document.form1.submit();
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
			<li class="active">管理</li>
			<li class="active">商品列表</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>商品列表 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<div class="query_form">
		<form id="QueryForm" name="form1" class="form-inline" action="javascript:void(0);"
			  method="post">
			<input type="hidden" value="${data_page.currentPage}" name="pagenumber" id="pagenumberid">
			<input type="hidden" value="" name="id" id="id">
			<input type="hidden" value="${sales_volume}" name="sales_volume" id="sales_volume">
			<label>商品名称：</label><input type="text" name="commoditynameselected" style="width: 110px; height: 25px;" value="${commodityname}" />
			&nbsp;
			<label>商家：</label><select name="company"  >
									<option value="0">所有</option>
									<c:forEach items="${companylist }" var="company" varStatus="c">
										<option value="${company.id}" <c:if test="${company.id == companyid }" >selected</c:if>>${company.company_name}</option>
									</c:forEach>
								</select>
			&nbsp;
			<label>类别：</label><select name="category"  >
									<option value="0">所有</option>
									<c:forEach items="${categorylist }" var="category" varStatus="c">
										<option value="${category.id}" <c:if test="${category.id == categoryid }" >selected</c:if>>${category.category_name}</option>
									</c:forEach>
								</select>
			<label>状态：</label><select name="listing" >
										<option value="0" <c:if test="${listing == \"0\"}">selected</c:if>>上架</option>
										<option value="1" <c:if test="${listing == \"1\"}">selected</c:if>>待审核</option>
									</select>
			<span>
				<a class="btn btn-sm btn-success" onclick="Query();">[ 查询 ]</a>
				<a class="btn btn-sm btn-success" onclick="CancelQuery();">[ 取消查询 ]</a>
			</span>
		</form>
	</div>


	<div>
		<div class="col-xs-13">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>
							序号&nbsp;<c:if test="${listing ==1 }"><input type="checkbox" id="all" /></c:if>
							</th>
							<th>商品名称</th>
							<th>商品图片</th>
							<th>商家</th>
							<th>默认价格</th>
							<th>一级佣金(<font color="red">最少</font>)</th>
							<th>二级佣金(<font color="red">中间</font>)</th>
							<th>三级佣金(<font color="red">最多</font>)</th>
							<th>分类</th>
							<th>状态</th>
							<th style="width:150px;"></th>
						</tr>
					</thead>

					<tbody id="list">
						<c:if var="havedata" test="${not empty data_page.dataList }"></c:if>
						<c:if test="${!havedata }">
							<td colspan="8" align="center">暂无数据</td>
						</c:if>
						<c:if test="${havedata }">
						<c:set var="base" value="${(data_page.currentPage-1)*10 }" />
						<c:forEach items="${data_page.dataList }" var="commodity" varStatus="v">
							<tr>
								<td >${v.count + (data_page.currentPage-1)*10}
									&nbsp;
									<c:if test="${listing ==1 }"><input type="checkbox" value="${commodity.id}" name='checkbox1' /></c:if>
								</td>
								<td>${commodity.commodity_name}</td>
								<td><img src="${commodity.acquiescent_picture}" width="50px;" height="50px;"></td>
								<td>${commodity.companyInfo.company_name}</td>
								<td>${commodity.defaultPrice}</td>
								<td>${commodity.commission_1}</td>
								<td>${commodity.commission_2}</td>
								<td>${commodity.commission_3}</td>
								<td>${commodity.categoryInfo.category_name}</td>
								<td>
									<c:if test="${commodity.listing == \"0\" }" >上架</c:if>
									<c:if test="${commodity.listing == \"1\" }" >待审核</c:if>
								</td>
								<td>
								<c:if test="${sales_volume>=1 }" var="hava"></c:if>
									<c:if test="${!hava }">
									<div
											class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info"
												onclick="orderdetail('${commodity.id}');">
											<i class="icon-file"></i>&nbsp;编辑
										</button>
									</div>
									</c:if>
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
		<c:if test="${havedata }">
		<div class="modal-footer no-margin-top">
			<c:if test="${listing ==1 }">
			<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
				<button class="btn btn-xs btn-info" onclick="shangjia();">
					<i class="icon-file"></i>&nbsp;批量上架
				</button>
			</div>
			</c:if>
			<ul class="pagination pull-right no-margin">
			    <li>
					<a href="javascript:void(0);">第${data_page.currentPage }页/共${data_page.pageCount }页</a>
				</li>
				<li>
					<a href="javascript:go_page(1);">首页</a>
				</li>
				<li class="prev <c:if test="${data_page.currentPage==1}"> disabled</c:if>">
					<c:if test="${data_page.currentPage-1>0 }">
						<a href="javascript:go_page(${data_page.currentPage-1 });">
							<<&nbsp;&nbsp;上一页
						</a>
					</c:if>
					<c:if test="${data_page.currentPage-1==0 }">
						<a href="javascript:void(0);">
							<<&nbsp;&nbsp;上一页
						</a>
					</c:if>
				</li>
				<c:forEach begin="${data_page.startPage }"
					end="${data_page.pageCount }" varStatus="v">
					<li <c:if test="${v.index==data_page.currentPage }" var="next current">class="active"</c:if><c:if test="${!current}">class="prev"</c:if>><a href="javascript:go_page(${v.index });">${v.index }</a></li>
				</c:forEach>
				<li class="next <c:if test="${data_page.currentPage==data_page.pageCount}"> disabled</c:if>">
					<c:if test="${data_page.currentPage+1<=data_page.pageCount}">
						<a href="javascript:go_page(${data_page.currentPage+1 });">
						下一页&nbsp;&nbsp;>>
						</a>
					</c:if>
					<c:if test="${data_page.currentPage==data_page.pageCount}">
						<a href="javascript:void(0);">
						下一页&nbsp;&nbsp;>>
						</a>
					</c:if>
				</li>
				<li>
					<a href="javascript:go_page(${data_page.pageCount });">尾页</a>
				</li>
				<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox" value="${data_page.currentPage }" data-options="min:1,max:${data_page.pageCount }" id="pagenum" style="width:55px;" />页&nbsp;&nbsp;<input class="btn btn-xs btn-info" type="button" onclick="go_page($('#pagenum').val());" value="确定"/></li>
			</ul>
		</div>
		</c:if>
	</div>
</body>

<script type="text/javascript">
$("#all").click(function(){  
	if(this.checked){
		$("input[name='checkbox1']").each(function(){
		   $(this).attr("checked","checked");
		  });  
	 }else{
		$("input[name='checkbox1']").each(function(){
	 	   $(this).attr("checked",false);
	 	  });
	 }
}); 

function shangjia(){
	var check = "";
	$("input[name='checkbox1']").each(function(){
		   if(this.checked){
			   check += $(this).val()+",";
		   }
	});  
	if(check!=''){
		var info = util.POST("batchshangjia.do",{"check":check});
		location.reload();
	}
}
</script>
</html>