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
    <title>详情</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
<%@ include file="./common/common_init.jsp" %>
</head>
<body>
<div class='header'>
    <p>
        <a class="back" href="javascript:history.go(-1)"></a>
        <c:if test="${type == 1 }">
		 	 <a class="loginTitle">关于我们</a>
		</c:if>
    </p>
</div>
<div class="spacediv"></div>
<style>
.notice img{
	width:200px;
}
</style>
<div style="padding: 20px 4%;padding-bottom: 15px;"  >
    <span style="font-size:18px;">${notice.title}</span><br>
    <span class="timed">${notice.create_time } &nbsp;
    </span>
</div>
<div style="padding:4%" class="notice">
	${notice.content}
</div>
</body>
</html>