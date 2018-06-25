<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提现管理</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript">
	var querywhere = {};//查询条件
	var querywhere1;
	var querywhere2;
	var querywhere3;
	var shenqing_table;
	var page_data;
	var index;
	var shenhe_withdata;
	var transfering_table;//转账中的提现列表
	var show_index;
	//查询
	function Query() {
		querywhere = util.serializeObject($("#QueryForm"));
		go_page(1);
	}
	
	//取消查询
	function CancelQuery() {
		querywhere = {};
		go_page(1);
	}
	
	function Query1() {
		querywhere1 = util.Json2Str(util.serializeObject($("#QueryForm1")));
		$("#load_iframe1").attr("src", "withdraw_tran.do?status=2&" + querywhere1);
	}

	function CancelQuery1() {
		querywhere1 = "";
		$("#load_iframe1").attr("src", "withdraw_tran.do?status=2&" + querywhere1);
	}
	
	function Query2() {
		querywhere2= util.Json2Str(util.serializeObject($("#QueryForm2")));
		$("#load_iframe2").attr("src", "withdraw_tran.do?status=3&" + querywhere2);
	}

	function CancelQuery2() {
		querywhere2= "";
		$("#load_iframe2").attr("src", "withdraw_tran.do?status=3&" + querywhere2);
	}
	
	function Query3() {
		querywhere3= util.Json2Str(util.serializeObject($("#QueryForm3")));
		$("#load_iframe3").attr("src", "withdraw_tran.do?status=4&" + querywhere3);
	}

	function CancelQuery3() {
		querywhere3= "";
		$("#load_iframe3").attr("src", "withdraw_tran.do?status=4&" + querywhere3);
	}
	
	$(function() {
		init();
		go_page(1);
		if ($(document).height() >= $(window).height()) {
			$(".List_Iframe").height($(document).height() - 160);
		} else {
			$(".List_Iframe").height($(window).height() - 235);
		}
	});
	//申请中的提现列表分页
	function go_page(pageNumber) {
		page_data = util.GET("withdraw_list.do?status=1&pageNumber=" + pageNumber,querywhere);
		shenqing_table.bootstrapTable('load', page_data.dataList);
		if(page_data.dataList.length>0){
			$("#pagination").html(util.init_pagination(page_data));
		}
	}
	
	//初始化列表信息
	function init() {
		//初始化申请列表
		shenqing_table = $('#table-shenqing').bootstrapTable({
			height : 480,
			columns : [ {field : 'id',title : 'ID',align : 'left',width : 50}, 
			            {field : 'name',title : '用户名',align : 'left',width : 100,formatter:function(value,row){
			            	if(row.uid==0){
			            		return "系统帐户";
			            	}else{
			            		return value;
			            	}
			            }}, 
			            {field : 'bankcard.account_name',title : '真实姓名',align : 'left',width : 80}, 
			            {field : 'bankcard.card_num',title : '提现帐号',align : 'left',width : 200}, 
			            {field : 'bankcard.bank.bank_name',title : '提现银行',align : 'left',width : 100}, 
			            {field : 'bankcard.bank_address',title : '支行',align : 'left',width : 150}, 
			            {field : 'wd_money',title : '提现金额(￥)',align : 'left',width : 100}, 
			            {field : 'withdraw_rate',title : '手续费(￥)',align : 'left',width : 100}, 
			            {field : 'create_time',title : '申请时间',align : 'left',width : 180}, 
			            {field : '',title : '操作',align : 'left',width : 250,formatter:function(value,row){
							var thtml = "";
							thtml += '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">';
							//现在为手动线下转账,故屏蔽此代码.此方法为审核通过后,调用通联支付转账
							//thtml += "<button class='btn btn-xs btn-success' onclick=\"shenheForm('"+row.id+"','"+row.uid+"','"+row.real_name+"','"+row.wd_money+"','"+row.create_time+"','"+row.withdraw_rate+"');\">";
							
							//S add by lxc	2015-04-16	手动转账后,改状态	
								thtml += "<button id='success_ok_"+row.id+"' class='btn btn-xs btn-success' onclick=\"hand_movement('"+row.id+"','3');\">";
								thtml += '<i class="icon-ok bigger-130"></i>转账成功';
								thtml += '</button>';
								thtml += "<button id='fail_k_"+row.id+"' class='btn btn-xs btn-fail' onclick=\"hand_movement('"+row.id+"','4');\">";
								thtml += '<i class="icon-cancel bigger-130"></i>转账失败';
								thtml += '</button>';
							//E
							thtml += "<button class='btn btn-xs btn-info' onclick=\"watch_detail('"+row.id+"');\">";
							thtml += '<i class="icon-zoom-in bigger-130"></i>查看';
							thtml += '</button>';
							thtml += '</div>';
							return thtml;
					  }}]
		});
	}	
	
	//初始化弹出框
	function init_dialog() {
		index = $.layer({
			type : 1,
			shade : [ 0 ],
			moveOut : true,
			area : [ '600px', '400px' ],
			title : "<b>审核用户提现申请</b>",
			shade : [ 0.5, '#e4e6e9' ],
			shadeClose : true,
			offset : [ '60px', '' ],
			success : function() {
				layer.shift('top', 400);
			},
			border : [ 10, 0.5, '#666' ],
			page : {
				dom : '#add_form'
			}
		});
	}
	
	//提交审核
	function pass_shenqing(stauts){
		var form = util.serializeObject($("#option_form"));
		 var response = util.POST("withdraw_pass.do",{"id":form.id,"stauts":stauts,"remark":form.check_remark});
		 layer.close(index);
		 parent.ShowMsg("操作提示：","已经对该提现申请进行处理..");
		 $("#load_iframe1").attr("src", "withdraw_tran.do?status=2");
		 go_page(page_data.currentPage);
	}

	//审核
	function shenheForm(Id,uid,name,wd_money,crate_time,wd_rate) {
		init_dialog();
		$("#option_form").form("clear");
		$("#tx_name").html(name);
		$("#tx_money").html(wd_money);
		$("#tx_time").html(crate_time);
		$("#tx_rate").html(wd_rate);
		$("#shenhe_id").val(Id);
		if(uid==0){
			$("#tx_name").html("系统帐户");
    	}
	}
	
	function isArray(o) {  
		  return Object.prototype.toString.call(o) === '[object Array]';   
		} 
	//查看提现信息详情
	function watch_detail(id){
		var data = util.GET("withdraw_info.do",{"id":id});
		for(var key in data){
			alert(key)
			if("bankcard"==key){
				alert(isArray(data[key]));
			}
			$("#show_"+key).html(data[key]);
		}
		if(data.uid==0){
			$("#show_name").html("系统帐户");
    	}
		show_index = $.layer({
			type : 1,
			shade : [ 0 ],
			moveOut : true,
			area : [ '650px', '400px' ],
			title : "<b>提现信息详情</b>",
			shade : [ 0.5, '#e4e6e9' ],
			shadeClose : true,
			offset : [ '30px', '' ],
			success : function() {
				layer.shift('top', 400);
			},
			border : [ 10, 0.5, '#666' ],
			page : {
				dom : '#watch_table'
			}
		});
	}

	//导出---提现明细			add by lxc	2015-04-15
	function exportwithdraw_list(){
		$('#QueryForm').attr('action','exportwithdraw_list.do');
		$('#QueryForm').submit();
		$('#QueryForm').attr('action','withdraw_list.do?status=1&pageNumber=1');
	}
	/**
	线下手动转账,点击转账成功		add by lxc	2015-04-15
	***/
	function hand_movement(w_id,status){
		$.ajax({
			type:"POST",
			url :"withdraw_hand_movement.do",
			data:{"id":w_id,"stauts":status},
			async:false,
			success:function(data){
				$("#success_ok_"+w_id).css("display","none");
				$("#fail_k_"+w_id).css("display","none");
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
			<li class="active">系统管理</li>
			<li class="active">提现管理</li>
		</ul>
	</div>
	<div class="col-sm-13">
		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a data-toggle="tab" href="#shenqing"><i
						class="green icon-home bigger-110"></i>待审核的提现申请列表 </a></li>
				<!-- <li><a data-toggle="tab" href="#transfer_ing"><i
						class="green icon-cloud-download bigger-110"></i>转账中的提现列表 </a></li> -->
				<li><a data-toggle="tab" href="#transfer_success"><i
						class="green icon-check bigger-110"></i>转账成功的提现列表 </a></li>
				<li><a data-toggle="tab" href="#fail_list"><i
						class="green icon-fire bigger-110"></i>失败的提现列表 </a></li>
			</ul>
			<div class="tab-content">
				<!-- 申请的提现列表 -->
				<div id="shenqing" class="tab-pane in active">
					<div class="query_form">
						<form id="QueryForm" class="form-inline"
							action="javascript:void(0);" method="post">
							<label>用户名：</label>
							<input type="text" name="name" style="width: 90px; height: 25px;" /> 
							<label>真实姓名：</label><input type="text" name="real_name" style="width: 90px; height: 25px;" />
							<label>申请时间：</label>
							<input type="text" name="start_time" class="Wdate"	onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								style="width: 120px; height: 25px;" /> — 
							<input type="text"	class="Wdate" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								name="end_time" style="width: 120px; height: 25px;" />
							<span>
								<a class="btn btn-sm btn-success" onclick="Query();">[ 查询 ]</a>
								<a class="btn btn-sm btn-success" onclick="CancelQuery();">[取消查询]</a>
								<a class="btn btn-sm btn-success" onclick="exportwithdraw_list();">[ 导出 ]</a>
							</span>
						</form>
					</div>
					<div class="List_Iframe">
						<div class="table-responsive">
							<table id="table-shenqing"
								class="table table-striped table-bordered table-hover"></table>
							<div class="modal-footer no-margin-top" id="pagination"></div>
						</div>
					</div>
				</div>
				<!-- 转账中的提现列表 
				<div id="transfer_ing" class="tab-pane">
					<div class="query_form">
						<form id="QueryForm1" class="form-inline"
							action="javascript:void(0);" method="post">
							<label>用户名：</label><input type="text" name="name"
								style="width: 90px; height: 25px;" /> <label>真实姓名：</label><input
								type="text" name="real_name" style="width: 90px; height: 25px;" />
							<label>转账时间：</label><input type="text" name="start_time"
								class="Wdate"
								onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								style="width: 120px; height: 25px;" /> — <input type="text"
								class="Wdate"
								onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								name="end_time" style="width: 120px; height: 25px;" /> <span>
								<a class="btn btn-sm btn-success" onclick="Query1();">[ 查询 ]</a>
								<a class="btn btn-sm btn-success" onclick="CancelQuery1();">[取消查询
									]</a>
							</span>
						</form>
					</div>
					<div class="List_Iframe">
						<iframe id="load_iframe1" frameborder="no"
							src="withdraw_tran.do?status=2" scrolling="no"
							allowtransparency="true"
							style="border: medium none; width: 100%; display: block; height: 100%; overflow: hidden;">
						</iframe>
					</div>
				</div>-->
				<!-- 转账成功的提现列表 -->
				<div id="transfer_success" class="tab-pane">
					<div class="query_form">
						<form id="QueryForm2" class="form-inline"
							action="javascript:void(0);" method="post">
							<label>用户名：</label><input type="text" name="name"
								style="width: 90px; height: 25px;" /> <label>真实姓名：</label><input
								type="text" name="real_name" style="width: 90px; height: 25px;" />
							<label>转账时间：</label><input type="text" name="start_time"
								class="Wdate"
								onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								style="width: 120px; height: 25px;" /> — <input type="text"
								class="Wdate"
								onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								name="end_time" style="width: 120px; height: 25px;" /> <span>
								<a class="btn btn-sm btn-success" onclick="Query2();">[ 查询 ]</a>
								<a class="btn btn-sm btn-success" onclick="CancelQuery2();">[取消查询
									]</a>
							</span>
						</form>
					</div>
					<div class="List_Iframe">
						<iframe id="load_iframe2" frameborder="no"
							src="withdraw_tran.do?status=3" scrolling="no"
							allowtransparency="true"
							style="border: medium none; width: 100%; display: block; height: 100%; overflow: hidden;">
						</iframe>
					</div>
				</div>
				<!-- 转账失败的提现列表 -->
				<div id="fail_list" class="tab-pane">
					<div class="query_form">
						<form id="QueryForm3" class="form-inline"
							action="javascript:void(0);" method="post">
							<label>用户名：</label><input type="text" name="name"
								style="width: 90px; height: 25px;" /> <label>真实姓名：</label><input
								type="text" name="real_name" style="width: 90px; height: 25px;" />
							<label>转账时间：</label><input type="text" name="start_time"
								class="Wdate"
								onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								style="width: 120px; height: 25px;" /> — <input type="text"
								class="Wdate"
								onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
								name="end_time" style="width: 120px; height: 25px;" /> <span>
								<a class="btn btn-sm btn-success" onclick="Query3();">[ 查询 ]</a>
								<a class="btn btn-sm btn-success" onclick="CancelQuery3();">[取消查询 ]</a>
							</span>
						</form>
					</div>
					<div class="List_Iframe">
						<iframe id="load_iframe3" frameborder="no"
							src="withdraw_tran.do?status=4" scrolling="no" allowtransparency="true"
							style="border: medium none; width: 100%; display: block; height: 100%; overflow: hidden;">
						</iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div></div>
	<!-- 审核提现 -->
	<div id="add_form" style="display: none; margin: 0 0 0 0px; padding-top: 0px; width: 600px; height: 330px; overflow: auto;">
		<div>
			<div class="col-xs-13">
				<form id="option_form" class="form-horizontal"
					action="managerteam_add.do" role="form" method="post"
					enctype="multipart/form-data">
					<table class="table table-bordered" style="text-align: center;">
						<tr>
							<td style="width: 125px;">提现用户</td>
							<td class="text-left" id="tx_name"></td>
						</tr>
						<tr>
							<td style="width: 125px;">提现金额(￥)</td>
							<td class="text-left" id="tx_money"></td>
						</tr>
						<tr>
							<td style="width: 125px;">申请时间</td>
							<td class="text-left" id="tx_time"></td>
						</tr>
						<tr>
							<td style="width: 125px;">手续费(￥)</td>
							<td class="text-left" id="tx_rate"></td>
						</tr>
						<tr>
							<td style="width: 125px;">审核意见</td>
							<td class="text-left">
								<div class="col-sm-9" style="padding-top: 15px;">
									<input type="hidden" name="id" id="shenhe_id" />
									<textarea name="check_remark"
										style="height: 100px; width: 360px;"></textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td class="text-right col-sm-3 control-label no-padding-right"></td>
							<td class="text-left" style="padding: 25px 0 20px 0;">
								<div class="col-md-offset-3 col-md-9">
									<button class="btn btn-sm btn-success" type="button"
										onclick="pass_shenqing(2);">
										<i class="icon-ok bigger-90"></i>[ 审核通过 ]
									</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-sm" type="button"
										onclick="pass_shenqing(4);">
										<i class="icon-undo bigger-90"></i>[ 审核不通过 ]
									</button>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<!-- 查看提现详情 -->
	<div id="watch_table" style="display: none; margin: 0 0 0 0px; padding-top: 0px; width: 650px; height: 360px; overflow: auto;">
		<div>
			<div class="col-xs-13">
				<table class="table table-bordered" style="text-align: center;">
						<tr>
							<td style="width:100px;">提现用户</td>
							<td class="text-left" id="show_name"></td>
							<td style="width:100px;">真实姓名</td>
							<td class="text-left" id="show_account_name"></td>
						</tr>
						<tr>
							<td style="width:100px;">提现帐号</td>
							<td class="text-left" id="show_account_no"></td>
							<td style="width:100px;">提现金额(￥)</td>
							<td class="text-left" id="show_wd_money"></td>
						</tr>
						<tr>
							<td style="width:100px;">提现银行</td>
							<td class="text-left" id="show_bank_name"></td>
							<td style="width:100px;">支行</td>
							<td class="text-left" id="show_bank_address"></td>
						</tr>
						<tr>
							<td style="width:100px;">手续费(￥)</td>
							<td class="text-left" id="show_withdraw_rate"></td>
							<td style="width:100px;">到帐金额(￥)</td>
							<td class="text-left" id="show_amount_money"></td>
						</tr>
						<tr>
							<td style="width:100px;">提交时间</td>
							<td class="text-left" id="show_create_time"></td>
							<!--  <td style="width:100px;">到帐时间</td>
							<td class="text-left" id="show_amount_time"></td>-->
							<td style="width:100px;">处理员</td>
							<td class="text-left" id="show_username"></td>
						</tr>
						<tr>
							<td style="width:100px;">审核描述</td>
							<td class="text-left" colspan="3" id="show_check_remark"></td>
						</tr>
						<tr>
							<td class="text-center" style="padding: 25px 0 20px 0;" colspan="4">
								<div class="col-md-offset-3 col-md-9 text-center">
									<button class="btn btn-sm" type="button" onclick="layer.close(show_index);">
										<i class="icon-undo bigger-90"></i>[ 关闭  ]
									</button>
								</div>
							</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>