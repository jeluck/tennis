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

	var callbackButton = document.getElementById('uploadfile');
	callbackButton.onclick = function(e) {
		e.preventDefault();
		bridge.callHandler('JavaScriptAndBridgeOC', {
			'foo' : 'bar'
		}, function(response) {
			log('JS got ------response', response);
		});
	};
});