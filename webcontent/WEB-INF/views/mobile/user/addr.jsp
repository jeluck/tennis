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
    <title>地址管理</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js"></script>
</head>
<body>
    <div class="header">
     <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">地址管理</a>
     </p>
    </div>
    <c:forEach items="${list}" var="o">
    <div class="addressEdit">
         <div class="borderRig"> <span class="nameAddr">${o.consignee}</span><span class="telAddr">${o.phone}</span>
           <p>
           		<c:if test="${o.isdefault == 1}">
<!--            		<span class="defaultAddr">[默认地址]</span> -->
                </c:if>
                <span class="defaultAddr"><input onclick="setdefault('${o.id}')" type="radio" value="${o.id}" name="defaultaddress" <c:if test="${o.isdefault == 1}">checked="checked"</c:if>></span>
           		${o.province}${o.city}${o.province}${o.area}
           </p>
        </div>
         <div class="addressChag" onclick="window.location.href='toeditaddr.do?id=${o.id}'">
           <span class="addressEdit-img03"><a class="modify" href="toeditaddr.do?id=${o.id}">修改</a></span>
         </div>
    </div>
    </c:forEach>
      
      <div class="footer-newAddr">
         <a href="tonewaddr.do"> <span class="newAddr-img04"></span> <span class="newAddr-text">添加新地址</span></a>
      </div>
</body>
<script src="/js/userFunction.js"></script>
</html>