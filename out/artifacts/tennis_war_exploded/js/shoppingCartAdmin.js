function p(){
	var orderMainNo = $("#orderMainNo").val();
	var playgroundName = $("#playgroundName").val();
	var paytype = $("#pay_type").val();
	var info = util.GET("/getPayOrderNo.do",{"orderMainNo":orderMainNo,"pay_type":paytype,"playgroundName":playgroundName});
	$("#payOrderNo").val(info.data.orderno);
	if(info.status == 0){
		var uamount= $("#amount").val();
		var price=$("#price").val();
		if(parseInt(uamount)<parseInt(price)){
			alert("余额不足，请充值");
			return;
		}
		$("#pay").submit();
	}else{
		window.hideTab.toast(info.msg);
	}
}