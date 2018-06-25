<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地-首页</title>
    <link href="/weixin/css/common.css" rel="stylesheet">
    <link href="/weixin/css/iconfont/iconfont.css" rel="stylesheet">
    <script src="/weixin/js/common.js"></script>
    <!-- mobile slider plugin start -->
    <link href="/js/plugin/swiper.3.1.7.min.css" rel="stylesheet">
    <script src="/js/plugin/swiper.3.1.7.min.js"></script>
    <!-- mobile slider plugin end -->
    <!-- cloud start -->
    <link href="/weixin/js/cloud.min.css" rel="stylesheet">
    <script src="/weixin/js/cloud.min.js"></script>
    <!-- cloud end -->
    <script src='/js/jquery-1.8.1.min.js'></script>
    <script type="text/javascript">
    	function changePrice(el){
    		var option = el.getElementsByTagName('option');
    		var price = '';
    		for (var i=0;i<option.length;i++) {
    			if (option[i].value == el.value) {
    				price = option[i].dataset.price;
    				break;
    			}
    		}
    		document.getElementById('price').innerHTML = price;
    	}
    	
    	function checkSex(el,sex) {
    		var iName = el.parentNode.getElementsByTagName('i');
    		for (var i = 0; i < iName.length; i++) {
				iName[i].className = 'iconfont icon-weixuanzhong';
			}
    		el.getElementsByTagName('i')[0].className = 'iconfont icon-xuanzhong';
    		document.getElementById('sex').value = sex;
    	}
    	
    	function signup(){
    		var id = document.getElementById('id').value;
    		var name = document.getElementById('name').value;
    		var phone = document.getElementById('phone').value;
    		var idcard_no = document.getElementById('idcard_no').value;
    		if(name == null || name == ""){
    			cloud.msg('请填写姓名','60%');
    			return;
    		}
    		if(phone == null || phone == ""){
    			cloud.msg('请填写手机号','60%');
    			return;
    		}
    		var reg = /^[1][3578][0-9]{9}$/;
    		if(!reg.test(phone)){
    			cloud.msg('手机号码格式不正确','60%');
    			return;
    		}
    		
    		if(idcard_no != null && idcard_no != ""){
    			var reg1 =/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    			var reg2 =/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
    			if(reg1.test(idcard_no) != true && reg2.test(idcard_no) != true){
    				cloud.msg("身份证格式不对");
    				return;
    			}
    		}
    		
    		$.ajax(
    			{ 
    				url: 'toEventsOrder.do?eventsId='+id+'&phone='+phone+'&name='+name+'',
    				type: 'post', 
	    			success: function(){
	//     	       		$(this).addClass("done");
	    	      	}
    			}
    		);
    	}
    </script>
</head>

<body>
    <header class="header" flex="main:justify cross:stretch">
        <a class="icon" flex-box="0" href="#"><i class="iconfont icon-fanhui"></i></a>
        <div class="logo" flex="cross:stretch">
            <div class="pic"><img src="/weixin/images/test.jpg" alt="弘金地"></div>
            <div class="hongjindi" style="color: #fff;">弘金地</div>
        </div>
        <div class="icon"></div>
    </header>
    <div style="height: 5px;"></div>
    <div class="enrol">
        <div class="head">赛事报名</div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">报名赛事：</div>
            <div class="value" flex-box="1">
                <select class="select"  onchange="changePrice(this)" id="id" >
                    <c:forEach items="${list }" var="o">
                    	<option value="${o.id }" data-price="${o.price }">${o.title }</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">费用：</div>
            <div class="value">
                <div class="tip" flex="main:justify cross:stretch" flex-box="1">
                    <div id="price">${price }元</div>
                    <div class="weixin">微信支付：<i class="iconfont icon-weixin"></i></div>
                </div>
                <div class="about">
                    费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明费用说明
                </div>
            </div>
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">姓名<em>*</em>：</div>
            <div class="value" flex-box="1">
                <input class="text" id="name" type="text" placeholder="必填">
            </div>
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">性别<em>*</em>：</div>
            <div class="sex" flex="box:mean" flex-box="1">
                <div onclick="checkSex(this,'男')" ><i class="iconfont icon-xuanzhong" ></i>男</div>
                <div onclick="checkSex(this,'女')" ><i class="iconfont icon-weixuanzhong"></i>女</div>
            </div>
            <input value="男" type="hidden" id="sex">
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">手机号<em>*</em>：</div>
            <div class="value" flex-box="1">
                <input class="text" id="phone" type="text" placeholder="必填">
            </div>
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">身份证号：</div>
            <div class="value" flex-box="1">
                <input class="text" type="text" id="idcard_no" placeholder="必填">
            </div>
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0">备注信息：</div>
            <div class="value" flex-box="1">
                <textarea class="text textarea"></textarea>
            </div>
        </div>
        <div class="item" flex="cross:stretch">
            <div class="key" flex-box="0"></div>
            <div class="value" flex-box="1">
                <div class="post-btn" onclick="signup()">确认报名</div>
            </div>
        </div>
    </div>
    <div class="vertion" flex="main:justify corss:stretch">
        <a href="#">弘金地网球官网</a>
        <a href="#">连锁网球俱乐部</a>
    </div>
</body>

</html>