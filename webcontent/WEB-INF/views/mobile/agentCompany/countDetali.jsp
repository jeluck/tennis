<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        	 <c:if test="${not empty agent.agentCompany.card_num_show }" var="hava">
        	 temp=1;
        	 </c:if>
	         <c:if test="${not empty agent.agentCompany.alipay_account_show }" var="hava">
	         temp=1;
	         </c:if>
	         if(temp==0){
            	document.getElementById(show_div).style.display = "block";
            	document.getElementById(bg_div).style.display = "block";
	         }else{
	        	 window.location.href='getMoney.do';
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
<div class="spacediv"></div>
<div class="countTitle">
    <span>账户余额（元）</span>
    <span class="getCUser-img" onclick="window.location.href='getPay.do'">提现账户</span><br>
    <span class="money">${agent.agentCompany.amount }</span>
</div>
<div class="countList" style="padding: 5%;  background-color: #fff;">
    <ul>
        <li style="float:left;margin-left: 20px;">
            <span class="gray">入账中（元）</span><br>
            <span>100</span>
        </li>
        <li style="float:right;margin-bottom: 20px;  margin-right: 20px;border-left: 1px solid #E4E4E4;
  padding-left: 10%;">
            <span class="gray">可提取收入（元）</span><br>
            <span class="redText">${agent.agentCompany.amount}</span>
        </li>
    </ul>
    <input type="button" class="CardButton" onclick="ShowDiv('MyDiv','fade')" value="我要提现"/>
</div>
<div style="padding:4%; display:none;">
     <span>资金明细</span><br>
    <div>
        <ul id="CountTab">
            <li class="current">全部</li>
            <li>进行中</li>
            <li  style="position: relative;">分类
            <span class="sortPrice-img" onclick="ShowDiv('SortDiv','fade')"></span>
            </li>
        </ul>
        <div id="CountContent">
            <!-- 全部  注意每一个li中都要添加  <div class="clear"></div> 清除浮动 -->
            <ul style="display:block;">
                <li class="count-li">
                    <span><img class="count-order-list-img" src="../../images/products/orderPro-list_03.png"/></span>
                    <span class="count-orderDetail">订单01：北京正宗老字号德祥楼烤鸭免费品尝<br>
                        <a>2014080098822818佣金</a><br>
                        <a class="countTime">2015-08-23 14:12:34</a>
                    </span><br>
                    <span class="countMoney">6.94<br>
                        <a style="color:#F25D5F;margin-left: -16px;">入账中</a>
                    </span>
                    <div class="clear"></div>
                </li>
                <li class="count-li clear">
                    <span><img class="count-order-list-img" src="../../images/products/orderPro-list_03.png"/></span>
                    <span class="count-orderDetail">订单：<br>
                        <a>2014080098822818佣金</a><br>
                        <a class="countTime">2015-08-23 14:12:34</a>
                    </span><br>
                    <span class="countMoney">6.94<br>
                        <a style="color:#F25D5F;margin-left: -16px;">入账中</a>
                    </span>
                    <div class="clear"></div>
                </li>
                <li class="count-li">
                    <span><img class="count-order-list-img" src="../../images/products/orderPro-list_03.png"/></span>
                    <span class="count-orderDetail">订单01：<br>
                        <a>2014080098822818佣金</a><br>
                        <a class="countTime">2015-08-23 14:12:34</a>
                    </span><br>
                    <span class="countMoney">6.94<br>
                        <a style="color:#F25D5F;margin-left: -16px;">入账中</a>
                    </span>
                    <div class="clear"></div>
                </li>
            </ul>

            <!-- 进行中 注意每一个li中都要添加  <div class="clear"></div> 清除浮动 -->
            <ul>
                <li class="count-li">
                    <span><img class="count-order-list-img" src="../../images/products/orderPro-list_03.png"/></span>
                    <span class="count-orderDetail">订单02：<br>
                        <a>2014080098822818佣金</a><br>
                        <a class="countTime">2015-08-23 14:12:34</a>
                    </span><br>
                    <span class="countMoney">6.94<br>
                        <a style="color:#F25D5F;margin-left: -16px;">入账中</a>
                    </span>
                    <div class="clear"></div>
                </li>
            </ul>

            <!-- 分类  注意每一个li中都要添加  <div class="clear"></div> 清除浮动 -->
            <ul>
                <li class="count-li">
                    <span><img class="count-order-list-img" src="../../images/products/orderPro-list_03.png"/></span>
                    <span class="count-orderDetail">订单03：<br>
                        <a>2014080098822818佣金</a><br>
                        <a class="countTime">2015-08-23 14:12:34</a>
                    </span><br>
                    <span class="countMoney">6.94<br>
                        <a style="color:#F25D5F;margin-left: -16px;">入账中</a>
                    </span>
                    <div class="clear"></div>
                </li>
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
            <div class="confirm" style="color:#526EEB;"><a class="queding" onclick="window.location.href='getPay.do'">添加提现账户</a></div>
        </div>
    </div>
</div>
<!-- 确定弹出窗口界面end -->
<!-- 点击分类查看界面 -->
<div id="SortDiv" class="white_content">
    <div class="jumpText">
        <p class="sortIndex">按分类查看</p>
        <div class="cancelConfirm">
            <ul>
                <li class="sortLook" style="border-right: 1px solid #ebebeb;">自营</li>
                <li class="sortLook">佣金（代销）</li>
                <li class="sortLook" style="border-right: 1px solid #ebebeb;">提现</li>
                <li class="sortLook">其他</li>
            </ul>
            <div class="sortCancel"><a class="quexiao" onclick="CloseDiv('SortDiv','fade')">取消</a></div>
          <!--  <div class="confirm" style="color:#526EEB;"><a class="queding" onclick="window.location.href='getPay.html'">添加提现账户</a></div>-->
        </div>
        <!--<p class="index">取消</p>-->
    </div>

</div>
</body>
 <script type="text/javascript">
 window.hideTab.hide();
 </script>
</html>