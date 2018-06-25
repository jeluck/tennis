/**
 * 添加商品进购物车
 * @param commodityTypeNo
 */
function addproduct(commodityTypeNo){
	var info = util.POST("addproduct.do",{"productno":commodityTypeNo,"userid":0});
	var countNum = $("#countNum").val();
    if(info.status==0){
    	countNum++;
   	 	$("#countCart").html(countNum);
   	 	$("#countNum").val(countNum);
    }else{
   	 	alert(info.msg);
    }
}

/**
 * 点击加入购物车
 */
function addproduct2(){
	var commodityTypeNo = $("#commodityTypeNo").val();
	var info = util.POST("addproduct.do",{"productno":commodityTypeNo,"userid":0});
	var countNum = $("#countNum").val();
    if(info.status==0){
    	countNum++;
   	 	$("#countCart").html(countNum);
   	 	$("#countNum").val(countNum);
   	 	$("#countCart_span").css("display","block");
    }else{
   	 	alert(info.msg);
    }
}
/**
 * 去购物车
 */
function tocart(){
	window.location.href="tocart.do"; 
}

/**
 * 点击加号加入购物车
 */
function addproductFor_plus(commodityTypeNo){
	var num = $("#num").val();
	if(!getProductStore(commodityTypeNo,num)){
		return false;
	}
	$.ajax({
		type:"POST",
		async:false,
		url:"addproductFor_plus.do",
		data:{"productno":commodityTypeNo},
		dataType:"html",
		success:function(data){
			$("#cartcontent").html(data);
		}
	});
}

/**
 * 点击减号减入购物车
 */
function subproduct_minus(commodityTypeNo){
	$.ajax({
		type:"POST",
		async:false,
		url:"subproduct_minus.do",
		data:{"productno":commodityTypeNo},
		dataType:"html",
		success:function(data){
			$("#cartcontent").html(data);
		}
	});
}

/**
 * 对比库存数
 * @param commodityTypeNo
 */
function getProductStore(commodityTypeNo,num){
	var info = util.POST("getProductStore.do",{"productno":commodityTypeNo,"num":num});
    if(info.status==0){
    	return true;
    }else{
   	 	alert(info.msg);
   	 	return false;
    }
}

/**
 * 去我的
 */
function touser(){
	window.location.href="touser.do"; 
}
/**
 * 去结算
 */
function confirmOrder(){
	window.location.href="confirmOrder.do";
}

/**
 * 确定订单页,点击去添加地址
 */
function tonewaddr(orderCome){
	window.location.href="tonewaddr.do?orderCome="+orderCome;
}

/**
 * 修改默认地址
 * @param id
 */
function setdefault(id){
	var info = util.POST("setdefault.do",{"id":id});
    if(info.status==0){
    	alert("默认地址修改成功");
    }else{
   	 	alert(info.msg);
    }
}

/**
 * 生成订单
 */
function generate_order(){
	var addressid = $("#addressid").val();
	if(typeof(addressid)=="undefined"){
		alert("请先添加地址");
		return ;
	}
	document.form1.submit();
}

/**
 * 点击我的昵称,变成输入imput
 * @param name
 */
function toeditname(name){
	$("#myname").removeAttr("onclick"); 
	var str="";
	str+="<input type='text' id='username' value='"+name+"' onblur='editname()' />";
	$("#editname").html(str);
	$("#username").focus();
}
//修改名称
function editname(){
	var username = $("#username").val();
	$("#editname").html(username);
	$("#myname").attr("onclick","toeditname('"+username+"')");
	var info = util.POST("changeNmae.do",{"username":username});
	if(info.status==14){
		window.location.href="touserlogin.do"; 
	}
	
}

/**
 * 用户确认收货
 * @param orderMainNo
 */
function completeOrder(orderMainNo){
	var info = util.POST("completeOrder.do",{"orderMainNo":orderMainNo});
    if(info.status==0){
    	alert("确认收货成功");
    	location.reload();
    }else{
   	 	alert(info.msg);
    }
}

/**
 * 用户取消收货
 * @param orderMainNo
 */
function deliveryRefuse(orderMainNo){
	var info = util.POST("deliveryRefuse.do",{"orderMainNo":orderMainNo});
    if(info.status==0){
    	alert("退货成功");
    	location.reload();
    }else{
   	 	alert(info.msg);
    }
}


//退出用户
function userlogout(){
	window.location.href='userlogout.do'; 
	try{ window.hideTab.exit(); }catch (e) {}
}



/***
 * 下拉加载数据,我的订单
 *  ul_id				页面装订单信息ul的id		
 *  payStatus			订单状态
 */
function getorder_nextPage(ul_id,payStatus){
	var pageNumber = $('.more_p').attr('p');//获取第前页
	pageNumber--;
	$.ajax({
		type:"POST",
		async:false,
		url:"nextPage_orders.do",
		data:{"pageNumber":pageNumber,"payStatus":payStatus},
		dataType:"html",
		success:function(data){
			 var divDel = $("#"+ul_id).children("div");
	         divDel.remove();
			$("#"+ul_id).append(data);
		}
	});
}


/***
 * 下拉加载数据,(统计页__ countDetali.jsp ,佣金记录)
 *  ul_id				页面装佣金信息ul的id		
 *  commission_status			订单状态
 */
function getcommission_nextPage(ul_id,commission_status){
	var pageNumber = $('.more_p').attr('p');//获取第前页
	pageNumber--;
	$.ajax({
		type:"POST",
		async:false,
		url:"nextPage_commissions.do",
		data:{"pageNumber":pageNumber,"commission_status":commission_status},
		dataType:"html",
		success:function(data){
			 var divDel = $("#"+ul_id).children("div");
	         try{
	        	 divDel.remove();
	         }catch(e){};
			$("#"+ul_id).append(data);
		}
	});
}

/***
 * 获得订单支付订单号
 * @param orderMainNo
 * @param pay_type
 * @return
 */
function getPayOrderNo(orderMainNo,pay_type){
	var info = util.POST("getPayOrderNo.do",{"orderMainNo":orderMainNo,"pay_type":pay_type});
    if(info.status==0){
    	try{
    		if(pay_type==1){
    			//调用安卓方法_支付方式:微信
    			window.pay.weixin(info.data.subject,info.data.body,info.data.price,info.data.orderno,info.data.notifyurl);
    		}else{
    			//调用安卓方法_支付方式:支付宝
    			window.pay.zhifubao(info.data.subject,info.data.body,info.data.price,info.data.orderno,info.data.notifyurl,"");
    		}
    	}catch (e) {
    		alert("抱歉,暂不能支付");
    	}
    }else if(info.status==22){
   	 	alert(info.msg);
    }else {
   	 	alert(info.msg);
    }
}

/***
 * 获得会员证支付订单号
 * @param orderMainNo
 * @param pay_type
 * @return
 */
function getVIP_PayOrderNo(pay_type){
	var info = util.POST("getVIP_PayOrderNo.do",{"pay_type":pay_type});
    if(info.status==0){
    	try{
    		if(pay_type==1){
    			//调用安卓方法_支付方式:微信
    			window.pay.weixin(info.data.subject,info.data.body,info.data.price,info.data.orderno,info.data.notifyurl,"buy_vip_order");
    		}else{
    			//调用安卓方法_支付方式:支付宝
    			window.pay.zhifubao(info.data.subject,info.data.body,info.data.price,info.data.orderno,info.data.notifyurl,"buy_vip_order");
    		}
    	}catch (e) {
    		alert("抱歉,暂不能支付");
    	}
    }else if(info.status==22){
   	 	alert(info.msg);
    }else {
   	 	alert(info.msg);
    }
}

/**
 * 点击再看入账中的数据
 */
function check_counting(){
	var info = util.POST("countUserCommission.do");
    if(info.status==0){
    	$("#check_counting").html(info.data);
    }else {
   	 	alert(info.msg);
    }
}

/**
 * 用户点击代表线下付款,后台改子订单状态为部分付款.
 * @param orderMainNo
 */
function userhandlepay(orderMainNo){
	var info = util.POST("userhandlepay.do",{"orderMainNo":orderMainNo});
    if(info.status==0){
    	alert("已付款,请通知客服!");
    	return true;
    }else{
   	 	alert(info.msg);
   	 	return false;
    }
}

/**
 * 用户点击代表线下付款,后台状态.
 * @param commodityTypeNo
 */
function userhandlepayvip(pay_orderNo){
	var info = util.POST("userhandlepayvip.do",{"pay_orderNo":pay_orderNo});
    if(info.status==0){
    	alert("已付款,请通知客服!");
    	return true;
    }else{
   	 	alert(info.msg);
   	 	return false;
    }
}