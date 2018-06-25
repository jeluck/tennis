window.addEventListener('load', function () {
	if (!window.ios) {
		lz.initIos();
	}
}, false);

function p(){
	var orderMainNo = $("#orderMainNo").val();
	var playgroundName = $("#playgroundName").val();
	var paytype = $("#pay_type").val();
	var info = util.GET("/getPayOrderNo.do",{"orderMainNo":orderMainNo,"pay_type":paytype,"playgroundName":playgroundName});
	$("#payOrderNo").val(info.data.orderno);
	if(info.status == 0){
		if($("#flag").attr("class").indexOf("lz-icon-dagou") == -1){ //判断是否是支付宝支付
			if($("#flag2").attr("class").indexOf("lz-icon-dagou") == -1){ //判断是否是微信支付
				var uamount= $("#amount").val();
				var price=$("#price").val();
				if(parseInt(uamount)<parseInt(price)){
					cloud.explain("余额不足，请充值");
					return;
				}
				$("#pay_nouse").submit();
			}else{
				if(window.pay){
					window.pay.weixin(info.data.subject,info.data.body, info.data.price,info.data.orderno,info.data.notifyurl);
				}else{
					var data = {
							"subject":info.data.subject,
							"body":info.data.body,
							"price":info.data.price,
							"orderno":info.data.orderno,
							"notifyurl":info.data.notifyurl,
							"pay_type":paytype
						};
					ios.callHandler('iosConfirmPayment', data);
				}
			}
		}else{
			if(window.pay){
				window.pay.zhifubao(info.data.subject,info.data.body, info.data.price,info.data.orderno,info.data.notifyurl);
			}else{
				var data = {
						"subject":info.data.subject,
						"body":info.data.body,
						"price":info.data.price,
						"orderno":info.data.orderno,
						"notifyurl":info.data.notifyurl,
						"pay_type":paytype
					};
				ios.callHandler('iosConfirmPayment', data);
			}			
		}
	}else{
		window.hideTab.toast(info.msg);
	}
}

