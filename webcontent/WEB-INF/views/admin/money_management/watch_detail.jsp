<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
//查看提现信息详情
function watch_detail(id){
	var data = util.GET("withdraw_info.do",{"id":id});
	for(var key in data){
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
</script>
		<!-- 查看提现详情 -->
	<div id="watch_table" style="display: none; margin: 0 0 0 0px; padding-top: 0px; width: 650px; height: 360px; overflow: auto;">
		<div>
			<div class="col-xs-13">
				<table class="table table-bordered" style="text-align: center;">
						<tr>
							<td style="width:100px;">提现用户</td>
							<td class="text-left" id="show_name"></td>
							<td style="width:100px;">真实姓名</td>
							<td class="text-left" id="show_realname"></td>
						</tr>
						<tr>
							<td style="width:100px;">提现帐号</td>
							<td class="text-left" id="show_account_num"></td>
							<td style="width:100px;">提现金额(￥)</td>
							<td class="text-left" id="show_wd_money"></td>
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
							<td class="text-left" id="show_operaer"></td>
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