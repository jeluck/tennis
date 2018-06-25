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
    <title>意见反馈</title>
    <link rel="stylesheet" href="/css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
    <link rel="stylesheet" href="/css/sale.css">
    <script src="/js/jquery-1.8.1.min.js"></script>
        <script src="/js/admin_common.js"></script>
    <script>
      $(function() {
          var submit = $(".submit");
         // console.log(feedValue);
            submit.click(function(){
            	var feedValue = $("#feedback").val();
                if(feedValue !=="") {
                	var phone = $("#phone").val();
                    var info = util.POST("addsuggestion.do",{"detailcontent":feedValue,"phone":phone});
                    if(info.status==0){
                    	alert("谢谢反馈！");
                    }else{
                   	 	alert(info.msg);
                    }
                    $("#feedback").val("");
                    location.reload();
                }
                else {
                    alert("请输入您的反馈意见");
                }
             });
      });
    </script>
</head>
<body>
<div class="feedbackDiv">
    <form action="" >
        <textarea id="feedback" name="detailcontent" class="feedback" placeholder="请输入意见反馈(最多输入200字）" maxlength="200"></textarea><br>
        <input type="hidden" name="phone" id="phone" value="${phone}">
        <input class="submit" type="button" value="提交" placeholder="提交" style="width: 90%;">
    </form>
</div>

</body>
</html>