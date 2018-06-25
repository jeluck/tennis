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
    <title>订单详情</title>
    <link rel="stylesheet" href="../../../css/c.css"/>
    <link rel="stylesheet" href="../../../css/customer.css"/>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">订单详情</a>
    </p>
</div>
<div class="content">
    <div class="orderStatus">
        <span>订单状态：已完成</span><br>
        <span>实收款：￥38元（含运费￥10元）</span>
    </div>
    <div class="buyerInform orderD-common">
        <div class="orderD-head">
            <span class="bI-img"></span>
            <span class="bI-text">收货人信息</span>
            <span class="clear"></span>
        </div>
        <div class="clear"></div>
        <div class="bI-content orderD-com-cont">  <span class="buyerName">收货人：钟森 </span><br>
            <span class="buyerTel">联系方式：15818733811</span><br>
            <span clsss="buyerAddr">收货人地址：广东省深圳市南山区科技园科兴科学园B栋602室</span><br>
            <span class="">留言：</span><br>
            <span class="wechat">微信号：</span>
        </div>
    </div>
    <div class="expressInform orderD-common">
        <div class="orderD-head">
            <span class="expI-img"></span>
            <span class="expI-text">物流信息</span><br>
        </div>
        <div class="expI-content orderD-com-cont">
            <span class="buyerName">物流公司：天天快递</span><br>
            <span class="">运单编号：2015081415</span>
            <span ><a  href="expressDetail.html" class="lookExp">查看物流</a></span>
        </div>
    </div>
    <div class="saleInform orderD-common">
        <div class="orderD-head">
            <span class="saleI-img"></span>
            <span class=" ">卖家信息</span><br>
        </div>
        <div class="expI-content orderD-com-cont">
            <span class="buyerName">卖家：小微信Phone客户</span><br>
            <span class="">联系方式：15818733811</span><br>
            <span class="">微信号：xiaoweiSZ</span><br>
            <span class="copyWx">复制微信号</span>
        </div>
    </div>
    <!--此模块代码可以反复使用，我的订单-均使用此段代码 begin-->
    <div class="proDetail orderD-common">
        <div class="orderD-head">
            <span class="proI-img"></span>
            <span class=" ">商品详情</span><br>
        </div>
        <div class="expI-content orderD-com-cont">
            <span><img class="pro-list-img" src="../../../images/products/orderPro-list_03.png"></span><br>
            <span class="pro-list-name">堂食卤货 &nbsp; 招牌老鸭300g/包</span><br>
            <div class="price-num"> <span class="price">￥58</span><br>
                <span class="num">   &nbsp;&nbsp;&nbsp;   x1</span>
            </div>
            <span class="clear"></span>
            <span class="pro-list-weight">300g/包</span>
        </div>
        <div class="clear"></div>
        <div class="total-pro">
            <span>共有<a class="">1</a>件商品</span> &nbsp;
            <span class="expPay">运费：￥<a class="">0</a></span> &nbsp;
            <span class="">实付：<a class="totalPay">￥58</a></span>
        </div>
    </div>
    <!-- end 反复运用代码 结束 -->
    <div class="orderInform orderD-common">
        <div class="orderD-head">
            <span class="orderI-img"></span>
            <span class="expI-text">订单信息</span><br>
        </div>
        <div class="expI-content orderD-com-cont">
            <span class="">宅微店订单号：20150814110125</span><br>
            <span class="">下单时间：20150804110123</span><br>
            <span class="">付款时间：20150804180123</span><br>
            <span class="">发货时间：暂时无发货</span><br>
            <span class="">确认收货时间：暂无确认收货</span>
        </div>
    </div>
</div>

</body>
</html>