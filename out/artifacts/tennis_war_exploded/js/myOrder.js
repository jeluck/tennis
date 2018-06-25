// JavaScript Document

lz.myorderType = function (_this) {
	var oIcon = _this.getElementsByTagName('i')[0];
	var oTypeBox = _this.parentNode.getElementsByClassName('lz-myorder-type')[0];
	
	if (_this.dataset.status) {
		oTypeBox.style.display = 'none';
		lz.removeClass(oIcon, 'lz-show');
		delete _this.dataset.status;
	} else {
		oTypeBox.style.display = 'block';
		oIcon.className += ' lz-show';
		_this.dataset.status = true;
		
	}
	
}

function userCancelOrder(obj,orderMainNo){
	
	var info = util.POST("/userCancelOrder.do",{"orderMainNo":orderMainNo});
	if(info.status == 0){
//		cloud.msg("取消成功");
		obj.style.display = 'none';
		var oMsg = obj.parentNode.parentNode.querySelector('.lz-top>.lz-right');
		oMsg.innerHTML = "已取消";
		location.reload();
	}else if(info.status == 30){
		cloud.msg("您的订单已经不能取消了");
	}else{
		cloud.msg("取消失败");
	}

}

//确认完成订单
function carryOrderform(obj,userId,payStatus,orderMainNo){
	var info = util.POST("/carryOrderform.do",{"userId":userId,"payStatus":payStatus,"orderMainNo":orderMainNo});
	obj.style.display = 'none';
}