$(function(){
	//计算金额
	$('#interestSubmit').click(function(){
		var _amount = $('#amount').val(); 
		var _month = $('#month').val();
		//var _ratetype = $('#ratetype').val(); 
		var _rate = $('#rate').val();
		var _repayment = $('#repayment').val();
		var _tooltype = $('#tool_type').val();
		var msgArr =new Array;
		msgArr[1] = '贷款金额有误，最低贷款金额2000元';
		msgArr[2] = '投标金额不能少于50元';
		//(1=>'贷款金额有误，最低贷款金额2000元',2=>'投标金额不能低于50元');
		if(!_amount || isNaN(_amount) || ((_amount < 2000 && _tooltype == 1)||(_amount <= 50 && _tooltype == 2))) {
			//alert(msgArr[_tooltype]); 
			// 弹出提示信息
			$(".plusBank .content").html(msgArr[_tooltype]);
    		showCon_0();
    		//IE6下兼容问题
    		$('#repayment').css('display','none');
    		return false;
		}
		if(_amount.indexOf(".") != -1 && _amount.substring(_amount.indexOf("."),_amount.length).length>3){
			//alert('金额不能超过两个小数位');
			// 弹出提示信息
			$(".plusBank .content").html('金额不能超过两个小数位');
    		showCon_0();
    		//IE6下兼容问题
    		$('#repayment').css('display','none');
			return false;
		}
		if(!_month || isNaN(_month) || _month  <= 0 || _month > 36) {
			//alert('贷款期限有误，贷款期限范围1-36个月之间'); 
			// 弹出提示信息
			$(".plusBank .content").html('贷款期限有误，贷款期限范围1-36个月之间');
    		showCon_0();
    		//IE6下兼容问题
    		$('#repayment').css('display','none');
			return false;
		}
		if(_month==null||!/^\d+$/.test(_month)){
			//alert("期限必须为整数");
			$(".plusBank .content").html('期限必须为整数');
    		showCon_0();
    		//IE6下兼容问题
    		$('#repayment').css('display','none');
			return false;
		}
		if(!_rate || isNaN(_rate) || _rate < 1 || _rate > 26.24) {
			//alert('贷款利率有误，贷款利率范围1%-26.24%之间'); 
			$(".plusBank .content").html('贷款利率有误，贷款利率范围1%-26.24%之间');
    		showCon_0();
    		//IE6下兼容问题
    		$('#repayment').css('display','none');
			return false;
		}
		$.ajax({
			url: 'http://www.niwodai.com/daikuan/interestCalc.do',
			type: 'post',
            dataType: "json",
            data: {
            	amount: _amount,
            	month: _month,
            	rate: _rate,
            	repayment: _repayment,
            	tool_type: _tooltype
            },
            success: function(data){
            	if(data.code=='err'){
            		//alert(data.result);
            		$(".plusBank .content").html(data.result);
            		showCon_0();
            		//IE6下兼容问题
            		$('#repayment').css('display','none');
            	}else{
            		$("#amountTotal").html(data.blh);
        			$("#capitalSum").html(data.amount);
        			
        			var paymentList = data.list;
        			var paymentHtml = "";
        			for (var i = 0, length = paymentList.length; i < length; i++) {
            			paymentHtml += '<tr>' +
					                   	'<td class="f">' + (i+1) + '/' + length + '</td>' +
					                    '<td class="tr"><span>' + paymentList[i].zonge + '</span></td>' +
					                    '<td class="tr"><span>' + paymentList[i].bj + '</span></td>' +
					                    '<td class="tr"><span>' + paymentList[i].lx + '</span></td>' +
					                    '<td class="tr l"><span>' + paymentList[i].bal + '</span></td>' +
					                   '</tr>';
            		}
            		$("#paymentContent").html(paymentHtml);
            		
            		// 弹出计算结果框
            		showCon_1();
            		//IE6下兼容问题
            		$('#repayment').css('display','none');
            	}
          	}
		});
		return false;
	});
	
	$(".plusBank1 a.btn").click(function() {
		// 关闭计算结果框
		closeAll_1();
		//IE6下兼容问题
		$('#repayment').css('display','');
	});
	
	$(".plusBank button.btn").click(function() {
		// 关闭提示信息弹出框
		closeAll_0();
		//IE6下兼容问题
		$('#repayment').css('display','');
	});
	
	
	//标详情页面计算器
	$('#interestNewSubmit').click(function(){
		var _amount = $('#amount').val(); 
		var _month = $('#month').val();
		//var _ratetype = $('#ratetype').val(); 
		var _rate = $('#rate').val();
		var _repayment = $('#repayment').val();
		var _tooltype = $('#tool_type').val();
		var msgArr =new Array;
		msgArr[1] = '贷款金额有误，最低贷款金额2000元';
		msgArr[2] = '投标金额不能少于50元';
		//(1=>'贷款金额有误，最低贷款金额2000元',2=>'投标金额不能低于50元');
		if(!_amount || isNaN(_amount) || ((_amount < 2000 && _tooltype == 1)||(_amount <= 50 && _tooltype == 2))) {
			alert(msgArr[_tooltype]); return false;
		}
		if(!_month || isNaN(_month) || _month  <= 0 || _month > 36) {
			alert('贷款期限有误，贷款期限范围1-36个月之间'); return false;
		}
		if(!_rate || isNaN(_rate) || _rate < 1 || _rate > 26.24) {
			alert('贷款利率有误，贷款利率范围1%-26.24%之间'); return false;
		}
		var host = window.location.host;
		$('#interestNewSubmit').attr('disabled', true);
		$('#interestNewForm').ajaxSubmit({
			success : function(_data){
				_data = $.parseJSON(_data);
				if(_data['status'] == 1) {
					art.dialog.data('list',_data['list']);
					$.dialog.load('/Home/Tpl/grey201310/Public/bidInterestList.html', {padding:30,id:'loanresult', ok:function(){
					$('#interestList').html(_data['list']); $('.gaishu').html(_data['gaishu']);
					}
					}).title('利息计算器');
				}
				else alert(_data['msg']);
				$('#interestNewSubmit').attr('disabled', false);
			}
		});
		return false;
	});
});