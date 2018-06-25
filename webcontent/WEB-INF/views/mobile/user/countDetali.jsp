<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>账户明细</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
    <link rel="stylesheet" href="../../css/alertJumpConfirm.css">
   	<script src="../../js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
    <style>
      /*js需求界面样式 */
        li.current {
            background-color: #22891F;
        }
        #CountContent ul {
            display:none;
        }
        .clear {
            clear:both;
        }
        span.sortPrice-img {
            position: absolute;
            top: 10px;
            right: -21%;
        }

        /* 分类查看弹出样式 begin */
      li.sortLook {
          width: 49%;
          float: left;
          text-align: center;
          border: 1px solid #ebebeb;
          border-left-width: 0;
          border-right-width: 0;
          padding: 10px 0 10px;
          font-size: 15px;
          border-top: 1px;
      }
      p.sortIndex {
          text-align: center;
          line-height: 20px;
          font-size: 15px;
          margin-bottom: 10px;
      }
      div.sortCancel {
          width: 92%;
          text-align: center;
          float: left;
          padding-top: 12px;
          padding-bottom: 10px;
          font-size: 15px;
          color:#F25558;
      }
      /* 分类查看弹出样式 end */
      
      /* 防止其他界面冲突，故优先内联 */
      span.count-orderDetail {
          float: left;
          line-height: 20px;
          margin: 5%;
          margin-top: 7%;
         /* 优先内联 code */
          width: 55%;  
      }
    </style>
    <script src="../../js/jquery.min.js"></script>
    <!--<script src="../../js/jquery-1.8.1.min.js"></script>-->
    <script type="text/javascript">
      $(function(){
          window.onload = function() {
              var $li = $("#CountTab li");
              var $ul = $("#CountContent ul");
              $li.mouseover(function() {
                  var $this = $(this);
                  var $t = $this.index();
                  $li.removeClass('current');
                  $this.addClass("current");
                  $ul.css('display','none');
                  $ul.eq($t).css('display','block');
              })
          }
      });
        // 弹出隐藏窗口
        function ShowDiv(show_div,bg_div) {
        	var temp = 0;//如果设置了账号或者卡号,就不弹出
       	 <c:if test="${not empty w.card_num_show }" var="hava">
       	 temp=1;
       	 </c:if>
	         <c:if test="${not empty w.alipay_account_show }" var="hava">
	         temp=1;
	         </c:if>
	         if(temp==0){
           	document.getElementById(show_div).style.display = "block";
           	document.getElementById(bg_div).style.display = "block";
	         }else{
	        	 window.location.href='getmoney.do';
	         }
        }
        function CloseDiv(show_div,bg_div) {
            document.getElementById(show_div).style.display = "none";
            document.getElementById(bg_div).style.display = "none";
        }
    </script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">账户明细</a>
    </p>
</div>
<div class="countTitle">
    <span>账户余额（元）</span>
    <span class="getCUser-img" onclick="window.location.href='getuserpay.do'"> 提现账户</span><br>
    <span class="money">${w.amount}</span>
</div>
<div class="countList" style="padding: 5%;  background-color: #fff;">
    <ul>
        <li style="float:left;margin-left: 20px;" onclick="check_counting()">
            <span class="gray">入账中（元）</span><br>
            <span id="check_counting" >点击查看</span>
        </li>
        <li style="float:right;margin-bottom: 20px;  margin-right: 20px;border-left: 1px solid #E4E4E4;
  padding-left: 10%;">
            <span class="gray">可提取收入（元）</span><br>
            <span class="redText">${w.amount}</span>
        </li>
    </ul>
    <input type="button" class="CardButton" onclick="ShowDiv('MyDiv','fade')" value="我要提现"/>
</div>
<div style="padding:4%;xdisplay:none;background: #fff;">
     <span>资金明细</span><br>
    <div >
        <ul id="CountTab">
            <li class="current">已入账</li>
            <li>入账中</li>
        </ul>
        <div id="CountContent">
            <!-- 已入账  注意每一个li中都要添加  <div class="clear"></div> 清除浮动 -->
            <ul style="display:block;" id="content_ul">
               <c:if test="${empty at_account.dataList }">
					<div class="nottiao">没有记录</div>
				</c:if>
		        <c:forEach items="${at_account.dataList }" var="o">
                <li class="count-li">
                    <span>
                    <c:if test="${not empty o.acquiescent_picture}" var="hava">
			    		<img class="count-order-list-img" src="${o.acquiescent_picture }"/>
			    	</c:if>
                    <c:if test="${!hava }">
                    	<img class="count-order-list-img" src="/image/nophoto_120X120.jpg"/>
                    </c:if>
                    </span>
                    <span class="count-orderDetail">订单：${o.orderInfoNo}
                    	<br>商品：${o.commodityName}
                    	<br>
                        <a>佣金：${o.totalcommission}</a><br>
                        <a class="countTime">${o.create_time}</a>
                    </span><br>
                    <span class="countMoney">
                        <a style="color:#F25D5F;margin-left: -16px;">已入账</a>
                    </span>
                    <div class="clear"></div>
                </li>
                </c:forEach>
            </ul>

            <!-- 入账中 注意每一个li中都要添加  <div class="clear"></div> 清除浮动 -->
            <ul id="content_ul_ing">
                 <c:if test="${empty ing_account.dataList }">
					<div class="nottiao">没有记录</div>
				</c:if>
		        <c:forEach items="${ing_account.dataList }" var="o">
                <li class="count-li">
					<c:if test="${not empty o.acquiescent_picture}" var="hava">
			    		<img class="count-order-list-img" src="${o.acquiescent_picture }"/>
			    	</c:if>
                    <c:if test="${!hava }">
                    	<img class="count-order-list-img" src="/image/nophoto_120X120.jpg"/>
                    </c:if>
                    <span class="count-orderDetail">订单：${o.orderInfoNo}
                    	<br>商品：${o.commodityName}
                    	<br>
                        <a>佣金：${o.totalcommission}</a><br>
                        <a class="countTime">${o.create_time}</a>
                    </span><br>
                    <span class="countMoney">
                        <a style="color:#F25D5F;margin-left: -16px;">入账中</a>
                    </span>
                    <div class="clear"></div>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>


</div>
<!-- 点击提取账户弹出 -->
<!-- 确定提现弹出窗口界面begin-->
<div id="fade" class="black_overlay"></div>
<div id="MyDiv" class="white_content">
    <!-- <div style="text-align: right; cursor: default; height: 40px;">
         <span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')">关闭</span>
     </div>-->
    <div class="jumpText">
        <p class="index">设置好提现账户后才能提现哦，提现账户支持银行卡和支付宝账户</p><br>
        <div class="cancelConfirm">
            <div class="cancel"><a class="quexiao" onclick="CloseDiv('MyDiv','fade')">取消</a></div>
            <div class="confirm" style="color:#526EEB;"><a class="queding" onclick="window.location.href='getuserpay.do'">添加提现账户</a></div>
        </div>
    </div>
</div>
<!-- 确定弹出窗口界面end -->
</body>
<script src="/js/userFunction.js"></script>
<script src="/js/scrollpagination.js"></script>
<script src="/js/downpullpage_common.js"></script>
<script type="text/javascript">
//调用下拉分页
$(function(){
    page("CountContent",'nextPage_commissions.do?commission_status=2',function(d){
        $('#content_ul').append(d);
        getcommission_nextPage("content_ul_ing",1);		//获取入账中的佣金
        
    });
});
</script>
</html>