//初始化IOS上传控件(ios手机端调用JS,运行IOS方法)
function connectWebViewJavascriptBridge(callback) {
	if (window.WebViewJavascriptBridge) {
		callback(WebViewJavascriptBridge);
	} else {
		document.addEventListener('WebViewJavascriptBridgeReady', function() {
			callback(WebViewJavascriptBridge);
		}, false);
	}
}

connectWebViewJavascriptBridge(function(bridge) {
	function log(message, data) {
		var el = document.getElementById('log');
		//el.innerHTML = message + ":" + JSON.stringify(data);
	}
	bridge.init(function(message, responseCallback) {
		var data = {
			'Javascript Responds' : 'Wee!'
		};
		responseCallback(data);
	});

	var callbackButton = document.getElementById('alipay');
	callbackButton.onclick = function(e) {
		var info = util.POST("getVIP_PayOrderNo.do",{"pay_type":2});
		e.preventDefault();
		bridge.callHandler('AlipayRequestOrder', {
			"subject":info.data.subject,
			"body":info.data.body,
			"price":info.data.price,
			"orderno":info.data.orderno,
			"notifyurl":info.data.notifyurl,
			"buy_vip_order":"buy_vip_order"
		}, function(response) {
			log('JS got ------response', response);
		});
	};
	
	var wechatButton = document.getElementById('wechat');
	wechatButton.onclick = function(e) {
		var info = util.POST("getVIP_PayOrderNo.do",{"pay_type":1});
		e.preventDefault();
		bridge.callHandler('WechatRequestOrder', {
			"subject":info.data.subject,
			"body":info.data.body,
			"price":info.data.price,
			"orderno":info.data.orderno,
			"notifyurl":info.data.notifyurl,
			"buy_vip_order":"buy_vip_order"
		}, function(response) {
			log('JS got ------response', response);
		});
	};
});