<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
   <title>${coach.name }</title>
  <script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
  <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
    <link rel="stylesheet" href="/css/user.css?${getVersion}">

  <script src="/js/common.js?${getVersion}"></script>
  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/venuesView.css?${getVersion}">
  <link rel="stylesheet" href="/js/plugin/swiper.3.1.7.min.css?${getVersion}">
  <script src="/js/plugin/swiper.3.1.7.min.js?${getVersion}"></script>
  <script src="/js/reservation.js?${getVersion}"></script>
  <script type="text/javascript">
    window.addEventListener('load', function() {
      window.reservation = new Reservation({
        getFieldUrl: 'ctimepoint.do?coachId=${activeId}&belong=1&coachType=${coachType}',  //教练预约详情获取场地url （playgroundId = 场馆id） （belong = 0） 0为场馆，1为教练
        jspName: 'coachView.jsp', //运营商
        formAction: 'toOrder.do', //数据提交给服务器的地址
        formData: {
          playgroundId: '0', //传入场馆id，运营商为0
          coachId: '${activeId}', //教练id
          spacePriceId: '', //场地选择的id，拼成字符串
          cycle: 'false', //周期预定
          endTime: '', //周期预定结束时间
          userId: '${userId }', //用户id
          person: '0',  //场馆预定，默认一对一，传入教学形式的id
          inCoachId: '0' //助产教练id，运营商助产教练为0
        }
      });
    }, false);
    
    window.addEventListener('load', function () {
      var oShare = document.querySelector('.lz-share');
      var aDiv = oShare.querySelectorAll('.lz-abreast-btn>div');
      
      
      for (var i=0;i<aDiv.length;i++) {
        aDiv[i].addEventListener('click', function () {
          document.getElementById('lz-fixed-re').style.display = 'block';
        }, false);
      }
      
    }, false);
    
  </script>
  <!--本页面单独使用文件 end-->
<%@ include file="./common/common_init.jsp" %>
</head>

  <body class="lz-body">
    <main class="lz-view">
    <input type="hidden"  id="share" value="coachdetailShare.do?coachId=${activeId }&coachType=${coachType}"/>
	<c:if test="${coach.name != '' }">
		<input type="hidden"  id="name" value="${coach.name }"/>
	</c:if>
	<c:if test="${coach.name == '' }">
		<input type="hidden"  id="name" value="没有数据"/>
	</c:if>
	
	<c:if test="${coach.head_portrait != '' }">
	<input type="hidden"  id="img" value="${coach.head_portrait }"/>
	</c:if>
	<c:if test="${coach.head_portrait == '' }">
	<input type="hidden"  id="img" value="没有数据"/>
	</c:if>
	
	
	<c:if test="${coach.personalitySignature != '' }">
	<input type="hidden"  id="desc" value="${coach.personalitySignature }"/>
	</c:if>
	<c:if test="${coach.personalitySignature == '' }">
	<input type="hidden"  id="desc" value="没有数据"/>
	</c:if>
    
      <%@ include file="./common/coachData.jsp" %>
      <%@ include file="./common/coachTab.jsp" %>
      <%@ include file="./common/comment.jsp" %>
  </main>
      <%@ include file="./common/coachFooter.jsp" %>
</body>

</html>
