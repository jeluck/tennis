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
    <title>
    	<c:if test="${type == 1 }">
		 	 关于我们
		</c:if>
	    <c:if test="${type == 2 }">
		 	服务协议
		</c:if>   
		<c:if test="${type == 3 }">
		 	联系我们
		</c:if> 
    </title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
    <%@ include file="./common/common_init.jsp" %>
</head>
<!-- 图片最大宽度 -->
<style type="text/css">
     img {
         max-width: 100%;
     }
 </style>
<body>
<div class='header'>
    <p>
        <a class="back" href="javascript:history.go(-1)"></a>
        <c:if test="${type == 1 }">
		 	 <a class="loginTitle">关于我们</a>
		</c:if>
	    <c:if test="${type == 2 }">
		 	 <a class="loginTitle">服务协议</a>
		</c:if>
		<c:if test="${type == 3 }">
		 	 <a class="loginTitle">联系我们</a>
		</c:if>
    </p>
</div>
<div class="spacediv"></div>
<div style="display: none; padding: 20px 4%;padding-bottom: 15px;">
    <span style="font-size:18px;">
	<c:if test="${type == 1 }">
	 	 关于我们
	</c:if>
    <c:if test="${type == 2 }">
	 	服务协议
	</c:if> 
	 <c:if test="${type == 3 }">
	 	联系我们
	</c:if>    
    </span><br>
    <span class="timed">${company.update_time } &nbsp;
    </span>
</div>
<div style="padding:4%">
	<c:if test="${type == 1 }">
		${company.company_info }
	</c:if>
   	<c:if test="${type == 2 }">
		${company.agreement }
	</c:if>
   <c:if test="${type == 3 }">
		${company.contact_us }
	</c:if>
</div>
</body>
</html>