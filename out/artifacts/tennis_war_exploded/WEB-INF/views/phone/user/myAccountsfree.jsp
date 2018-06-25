<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>我的账户</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/js/common.js"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/recharge.css">
<%@ include file="./common/common_init.jsp" %>
<script src="/js/recharge.js"></script>
<!--本页面单独使用文件 end-->
</head>

<body class="lz-body">
<main class="lz-main">
    <!-- 账户余额 start -->
    <section class="lz-mb">
        <div class="lz-table lz-balance">
            <div class="lz-table-cell">
                <div class="lz-center">
                    <span><i class="lz-iconfont lz-icon-qiandai"></i></span>
                    <span class="lz-box">
                        账户余额（元）
                        <strong>${user.amount }</strong>
                    </span>
                </div>
            </div>
            <div class="lz-table-cell">
                <div class="lz-center" style="width: 95%;">
                    <span><i class="lz-iconfont lz-icon-qiandai"></i></span>
                    <span class="lz-box">红包</span>
                </div>
            </div>
        </div>
    </section>
    <!-- 账户余额 end -->
    <!-- 账户收入统计 start -->
    <section class="lz-mb">
        <h2 class="lz-tit-4">你是自由教练，暂无合伙人补贴，了解<a href="#" style="color: #5B94DF;">《合伙人计划》</a></h2>
        <table class="lz-log-list"  cellspacing="0" cellpadding="0">
            <tr class="lz-tit">
                <th>累计收入（元）</th>
                <th>本月收入（元）</th>
                <th>本周收入（元）</th>
            </tr>
            <tr class="lz-list">
                <td>
                	<c:if test="${allmoney > 0 }">
                		${allmoney}
                	</c:if>
                	<c:if test="${allmoney <= 0 }">
                		-
                	</c:if>
                </td>
                <td>
                	<c:if test="${monthmoney > 0 }">
                		${monthmoney}
                	</c:if>
                	<c:if test="${monthmoney <= 0 }">
                		-
                	</c:if>
                </td>
                <td>
                    <c:if test="${weekmoney > 0 }">
                		${weekmoney}
                	</c:if>
                	<c:if test="${weekmoney <= 0 }">
                		-
                	</c:if>
                </td>
            </tr>
        </table>
        <table class="lz-log-list"  cellspacing="0" cellpadding="0">
            <tr class="lz-tit">
                <th>累计补贴（元）</th>
                <th>本月补贴（元）</th>
                <th>本周补贴（元）</th>
            </tr>
            <tr class="lz-list">
                <td>
                   	<c:if test="${allSubmoney > 0 }">
                		${allSubmoney}
                	</c:if>
                	<c:if test="${allSubmoney <= 0 }">
                		-
                	</c:if>
                </td>
                <td>
                	<c:if test="${montSubhmoney > 0 }">
                		${montSubhmoney}
                	</c:if>
                	<c:if test="${montSubhmoney <= 0 }">
                		-
                	</c:if>
                </td>
                <td>
                	<c:if test="${weekSubmoney > 0 }">
                		${weekSubmoney}
                	</c:if>
                	<c:if test="${weekSubmoney <= 0 }">
                		-
                	</c:if>
                </td>
            </tr>
        </table>
    </section>
    <!-- 账户收入统计 end -->
    <!-- 补贴结算记录 start -->
    <section>
        <h2 class="lz-tit-4">收入补贴结算记录<span style="font-size: 12px;">（注：N=打球日期）</span></h2>
        <ul class="lz-list-2 lz-log-info">
            <li>
                <span class="lz-left" style="width: 100px;">补贴比例</span>
                <span class="lz-right" style="width: 100px;"  onclick="location.href = 'toviewSub.do?coachId=${c.id}';"  >详情查看<i class="lz-iconfont lz-icon-jiantouright"></i></span>
            </li>
            <li>
                <span class="lz-left">收入结算周期</span>
                <span class="lz-right">N+${trade_balance_time.value }</span>
            </li>
            <li>
                <span class="lz-left">补贴结算周期</span>
                <span class="lz-right">N+${subsidies_grant_time.value }</span>
            </li>
        </ul>	
    </section>
    <!-- 补贴结算记录 end -->
    <!-- 交易记录 start -->
    <section class="lz-mb">
        <h2 class="lz-tit-4">注：单位（元）</h2>
        <table class="lz-log-list"  cellspacing="0" cellpadding="0">
            <tr class="lz-tit">
                <th>交易周期</th>
                <th>交易收入</th>
                <th>补贴</th>
                <th></th>
            </tr>
			<c:forEach items="${subAllList }" var="s">
			    <tr class="lz-list" onclick="cycleMoney(${s.zhe_id},${s.year},${s.month})" >
	                <td>${s.year } - ${s.month }</td>
	                <td>${s.jmoney }</td>
	                <td>${s.subsidies_money }</td>
	                <td><i class="lz-iconfont lz-icon-jiantouright "></i></td>
	            </tr>
			</c:forEach>
        </table>
    </section>
    <!-- 交易记录 end -->
</main>
<script type="text/javascript">
	function cycleMoney(coachId,year,month){
		window.location.href="toCycleMoney.do?coachId="+coachId+"&year="+year+"&month="+month+"";
	}
</script>
</body>
</html>
