window.addEventListener('load', function () {
	if (!window.pay) {
		lz.initIos();
	}
}, false);
function pays(){
	var flag = 0;
	var info ={};
	//充值活动
	if($("#rechargeEventsId").val() != null && $("#rechargeEventsId").val() != "" && $("#rechargeEventsId").val()>0){
		flag = 1;
		info = util.POST("accountRecharge.do",{"pay_type":$("#theway").val(),"money":0,"userId":$("#userId").val(),"recharId":$("#rechargeEventsId").val()});
	}
	//自定义金额
	if(flag != 1){
		if($("#money").val()!=null && $("#money").val()!="" && $("#money").val() > 0 ){
			info = util.POST("accountRecharge.do",{"pay_type":$("#theway").val(),"money":$("#money").val(),"userId":$("#userId").val(),"recharId":0});
		}else{
			
			if(window.hideTab){
				window.hideTab.toast("请输入大于0的金额");
			}else{
				return JSON.stringify({data: '',status:-1,msg: '请输入大于0的金额'});
			}
			
			$("#errorMonry").html("请输入大于0的金额");
			return;
		}
	}
	
	var pay_type = $("#theway").val();	//支付方式:2支付宝,1微信,3银联
	if(pay_type == 2){  //支付宝
		try{
			if(window.pay){
				window.pay.zhifubao(info.data.subject,info.data.body, info.data.price,info.data.orderno,info.data.notifyurl);
			}else{
				var data = {
						"subject":info.data.subject,
						"body":info.data.body,
						"price":info.data.price,
						"orderno":info.data.orderno,
						"notifyurl":info.data.notifyurl,
						"pay_type":pay_type
					};
				ios.callHandler('zhifubaoRequestRecharge', data);
			}
		}catch (e) {
		}
	}else if(pay_type == 1){  //微信
		try{
			if(window.pay){
				window.pay.weixin(info.data.subject,info.data.body, info.data.price,info.data.orderno,info.data.notifyurl);
			}else{
				var data = {
						"subject":info.data.subject,
						"body":info.data.body,
						"price":info.data.price,
						"orderno":info.data.orderno,
						"notifyurl":info.data.notifyurl,
						"pay_type":pay_type
					};
				ios.callHandler('zhifubaoRequestRecharge', data);
			}
		}catch (e) {
			e.message;
		}
	}else if(pay_type== 3){  //银联
		try {
			
		} catch (e) {
		}
	}
}


function selected(value){
	$("#theway").val(value);
}

/**
 * 选中自定义金额
 * 清除充值勾选
 */
function cancelSetCheck(){
	$("#rechargeEventsId").val("");
	if($("#money").val() != null && $("#money").val() !="" && $("#money").val() > 0){
		$("i[name='check']").each(function(y){
			$(this).attr("class", "lz-iconfont");
		});
		$("#di").css({'background-color':'#ea5532','color':'white'});
		$("#errorMonry").html("");
	}else{
		$("#errorMonry").html("请输入大于0的金额");
		$("#di").css({'background-color':'white','color':'#7f7f7f'});
	}
}

/**
 * 清除自定义充值
 */
function selectedMoneyAndId(id){
	$("#money").val("");
	$("#errorMonry").html("");
	$("#rechargeEventsId").val(id);
	$("#di").css({'background-color':'#ea5532','color':'white'});
}


function Membership(){
	var pay_type = $("#theway").val();	//支付方式:2支付宝,1微信,3银联
	var info =util.POST("membershipRecharge.do",{"pay_type":$("#theway").val(),"vip_id":$("#vip_id").val(),"userId":$("#userId").val()});
	if(info.status == 0){
		if(pay_type == 2){  //支付宝
			try{
				if(window.pay){
					window.pay.zhifubao(info.data.subject,info.data.body, info.data.price,info.data.orderno,info.data.notifyurl);
				}else{
					var data = {
							"subject":info.data.subject,
							"body":info.data.body,
							"price":info.data.price,
							"orderno":info.data.orderno,
							"notifyurl":info.data.notifyurl,
							"pay_type":pay_type
						};
					ios.callHandler('zhifubaoRequestRecharge', data);
				}
			}catch (e) {
			}
		}else if(pay_type == 1){  //微信
			try{
				if(window.pay){
					window.pay.weixin(info.data.subject,info.data.body, info.data.price,info.data.orderno,info.data.notifyurl);
				}else{
					var data = {
							"subject":info.data.subject,
							"body":info.data.body,
							"price":info.data.price,
							"orderno":info.data.orderno,
							"notifyurl":info.data.notifyurl,
							"pay_type":pay_type
						};
					ios.callHandler('zhifubaoRequestRecharge', data);
				}
			}catch (e) {
				e.message;
			}
		}else if(pay_type== 3){  //银联
			try {
				
			} catch (e) {
			}
		}
	}else{
		cloud.alert(info.msg);
	}
	
	
}
