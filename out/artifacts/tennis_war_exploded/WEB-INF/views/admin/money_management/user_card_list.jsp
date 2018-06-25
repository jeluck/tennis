<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有银行卡列表</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
function go_page(pageNumber){
	location.href = "user_card_list.do?pageNumber="+pageNumber+"&"+parent.querywhere;
}
</script>
<script type="text/javascript">
	var querywhere;
	//显示卡片信息
	function showCardInfo(card_id,pageNumber){
		$("#pageNumber").val(pageNumber);
		var info = util.GET("get_card_info.do",{"card_id":card_id});
		for(var key in info){
			$("#"+key).html(info[key]).val(info[key]);
		}
		$("#Option_Form").form("load",info);
		$("#username").html(info.weuser.username);
		$("#real_name").html(info.weuser.real_name);
		$("#id_card").html(info.weuser.idcard_no);
		$("#bank_name").html(info.bank.bank_name);
		
		//$("#region").html(info.province.provincename+" - "+info.city.cityname);
		index = $.layer({
		    type : 1,
		    shade : [0],
		    moveOut: true,
		    area : ['600px', '500px'],
		    title : "<b>审核卡片信息-审核</b>",
		    shade: [0.5, '#e4e6e9'],
		    offset: ['60px' , ''],
		    success: function(){
		    	layer.shift('top', 400);
		    },
		    shadeClose:true,
		    border: [10, 0.5, '#666'],
		    page : {dom : '#add_form'}
		});
	}
	//查询
	function Query() {
		querywhere = util.Json2Str(util.serializeObject($("#QueryForm")));
		location.href = "user_card_list.do?pageNumber=1&" + querywhere;
	}
	//取消查询
	function CancelQuery(){
		querywhere="";
		$('#QueryForm [type=text]').val('');
		location.href = "user_card_list.do?pageNumber=1" ;
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
			<li class="active">资金管理</li>
			<li class="active">银行卡管理</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>
			银行卡列表 <span id="time" style="font-size: 20px; float: right"></span>
		</h1>
	</div>
	<div class="query_form">
		<form id="QueryForm" class="form-inline" action="javascript:void(0);" method="post">
			<label>银行卡号：</label><input type="text" name="card_num" style="width: 110px; height: 25px;" /> 
			<label>真实姓名：</label><input type="text" name="account_name" style="width:110px; height: 25px;" />
			<label>审核状态：</label>
			<select name="card_status" style="width:110px; height: 25px;">
			<option value="0">所有</option>
			<option value="1">待审核</option>
			<option value="2">通过</option>
			<option value="3">未通过</option>
			</select> 
			<span><a class="btn btn-sm btn-success" onclick="Query();">[ 查询 ]</a>
			<a class="btn btn-sm btn-success" onclick="CancelQuery();">[ 取消查询 ]</a></span>
		</form>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th class="hidden-480">用户名</th>
									<th>真实姓名</th>
									<th>身份证号</th>
									<th>开户银行</th>
									<th>支行地址</th>
									<th>卡号</th>
									<th>状态</th>
									<th>提交时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:if var="havadata" test="${empty bankcard_page.dataList }">
									<td colspan="9" align="center">暂无数据</td>
								</c:if>
								<c:if test="${!havadata }">
									<c:set var="base" value="${(bankcard_page.currentPage-1)*10 }" />
									<c:forEach items="${bankcard_page.dataList }" var="card"
										varStatus="v">
										<tr>
											<td>${base+v.index+1 }</td>
											<td>${card.weuser.username}</td>
											<td>${card.account_name}</td>
											<td>${card.weuser.idcard_no}</td>
											<td>${card.bank.bank_name}</td>
											<td>${card.bank_address}</td>
											<td>${card.card_num }</td>
											<td><c:if test="${card.card_status==1 }"><font style="color:red;">待审核</font></c:if> <c:if
													test="${card.card_status==2 }">已通过</c:if> <c:if
													test="${card.card_status==3 }">未通过</c:if></td>
											<td>${card.create_time }</td>
											<td>
												<div
													class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													<button class="btn btn-xs btn-danger"
														onclick="showCardInfo('${card.id }','${bankcard_page.currentPage}');">
														<i class="icon-lock bigger-120">审核</i>
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
				<c:if test="${bankcard_page.pageCount>0 }">
					<div class="modal-footer no-margin-top">
						<ul class="pagination pull-right no-margin">
							<li><a href="javascript:void(0);">第${bankcard_page.currentPage
									}页/共${bankcard_page.pageCount }页</a></li>
							<li><a href="javascript:go_page(1);">首页</a></li>
							<li
								class="prev <c:if test="${bankcard_page.currentPage==1}"> disabled</c:if>">
								<c:if test="${bankcard_page.currentPage-1>0 }">
									<a href="javascript:go_page(${bankcard_page.currentPage-1 });">
										<<&nbsp;&nbsp;上一页 </a>
								</c:if> <c:if test="${bankcard_page.currentPage-1==0 }">
									<a href="javascript:void(0);"> <<&nbsp;&nbsp;上一页 </a>
								</c:if>
							</li>
							<c:forEach begin="${bankcard_page.startPage }"
								end="${bankcard_page.pageCount }" varStatus="v">
								<li
									<c:if test="${v.index==bankcard_page.currentPage }" var="next current">class="active"</c:if>
									<c:if test="${!current}">class="prev"</c:if>><a
									href="javascript:go_page(${v.index });">${v.index }</a></li>
							</c:forEach>
							<li
								class="next <c:if test="${bankcard_page.currentPage==bankcard_page.pageCount}"> disabled</c:if>">
								<c:if
									test="${bankcard_page.currentPage+1<=bankcard_page.pageCount}">
									<a href="javascript:go_page(${bankcard_page.currentPage+1 });">
										下一页&nbsp;&nbsp;>> </a>
								</c:if> <c:if
									test="${bankcard_page.currentPage==bankcard_page.pageCount}">
									<a href="javascript:void(0);"> 下一页&nbsp;&nbsp;>> </a>
								</c:if>
							</li>
							<li><a
								href="javascript:go_page(${bankcard_page.pageCount });">尾页</a></li>
							<li>&nbsp;&nbsp;到第<input type="text"
								class="easyui-numberbox" value="${bankcard_page.currentPage }"
								data-options="min:1,max:${bankcard_page.pageCount }"
								id="pagenum" style="width: 55px;" />页&nbsp;&nbsp;<input
								class="btn btn-xs btn-info" type="button"
								onclick="go_page($('#pagenum').val());" value="确定" /></li>
						</ul>
					</div>
				</c:if>
			</div>
			<!-- /row -->
		</div>
	</div>
	
		<div id="add_form"
		style="display: none; width: 600px; margin-top: 30px;">
		<form id="Option_Form" action="update_status.do" method="post">
			<table class="table table-bordered" style="text-align: center;">
				<tr>
					<td>用户名：</td>
					<td id="username"></td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td id="real_name"></td>
				</tr>
				<tr>
					<td>身份证号：</td>
					<td id="id_card"></td>
				</tr>
				<tr>
					<td>开户行：</td>
					<td id="bank_name"></td>
				</tr>
				<!-- 			<tr>
					<td>开户地区：</td>
					<td id="region"></td>
				</tr> -->
				<tr>
					<td>开户行：</td>
					<td id="bank_address"></td>
				</tr>
				<tr>
					<td>银行卡号：</td>
					<td id="card_num"></td>
				</tr>
				<tr>
					<td>审核状态：</td>
					<td><input type="radio" name="card_status" checked="checked"
						value="2" />审核通过 <input type="radio" name="card_status" value="3" />审核不通过
					</td>
				</tr>
				<tr>
					<td>审核意见：</td>
					<td><textarea rows="5" name="card_remark" id="card_remark"
							style="width: 300px;"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="hidden" id="id"
						name="id" /><input type="hidden" name="pageNumber"
						id="pageNumber" />
						<button class="btn btn-sm btn-success">
							<i class="icon-ok"></i>[ 确定 ]
						</button> <a href="javascript:void(0);" class="btn btn-sm btn-danger"
						onclick="layer.close(index);"> <i class="icon-remove"></i>[ 取消
							]
					</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>