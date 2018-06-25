<!--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0">
    <title>弘金地-首页</title>
    <link href="/weixin/css/common.css" rel="stylesheet">
    <link href="/weixin/css/iconfont/iconfont.css" rel="stylesheet">
    <script src="/weixin/js/common.js"></script>
</head>

<body>
    <header class="header" flex="main:justify cross:stretch">
        <a class="icon" flex-box="0" href="#"><i class="iconfont icon-fanhui"></i></a>
        <div class="text" flex-box="0">消息</div>
        <div class="icon"></div>
    </header>
    <ul class="msg-list" id="next-box">
        <!--start-->
        <c:forEach items="${list.dataList }" var="o">
            <li>
                <a href="getWechatMessage.do?id=${o.id }" flex="main:justify cross:stretch">
                    <div class="title" flex-box="1">${o.title }
                        <c:if test="${o.readstatus == 0 }">
                            <em class="weidu"></em>
                        </c:if>
                    </div>
                    <div class="time">${o.create_time }</div>
                </a>
            </li>
        </c:forEach>
        <!--end-->
    </ul>
    <div class="load" id="next-load">上拉加载更多</div>
    <script src="/weixin/js/getNext.js"></script>
    <script>
        Tool.getNext({
            data: {
                pageNumber: 2,
                userId: '${userId}'
            }
        });
    </script>
    <div class="banquan">弘金地网球版权所有&copy;2015</div>
</body>

</html>