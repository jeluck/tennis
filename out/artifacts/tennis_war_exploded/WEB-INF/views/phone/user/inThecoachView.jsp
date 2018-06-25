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
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
  <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
    <link rel="stylesheet" href="/css/user.css?${getVersion}">
    <!--本页面单独使用文件 end-->
  <script src="/js/common.js?${getVersion}"></script>
  <script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/venuesView.css?${getVersion}">
<%@ include file="./common/common_init.jsp" %>
  <link rel="stylesheet" href="/js/plugin/swiper.3.1.7.min.css?${getVersion}">
  <script src="/js/plugin/swiper.3.1.7.min.js?${getVersion}"></script>
  <script src="/js/reservation.js?${getVersion}"></script>
  <script type="text/javascript">
    window.addEventListener('load', function() {
      window.reservation = new Reservation({
        getFieldUrl: 'ctimepoint.do?coachId=${activeId}&belong=0&coachType=${coachType}',  //教练预约详情获取场地url （playgroundId = 场馆id） （belong = 0） 0为场馆，1为教练
        jspName: 'inThecoachView.jsp', //自由教练
        formAction: 'toOrder.do', //数据提交给服务器的地址
        formData: { //提交给服务器的数据
	       playgroundId: '${playgroundId}', //传入场馆id，运营商为0
           coachId: '0', //教练id
           spacePriceId: '', //场地选择的id，拼成字符串
           cycle: 'false', //周期预定
           endTime: '', //周期预定结束时间
           userId: '${userId }', //用户id
           person: '0',  //场馆预定，默认一对一，传入教学形式的id
           inCoachId: '${activeId}', //助产教练id，运营商助产教练为0
           innercoach:'2'				//只有在教练列表预订驻场教练的时候才会带的参数
        }
      });
    }, false);

  </script>
  <!--本页面单独使用文件 end-->
</head>

<body class="lz-body">
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
  <main class="lz-view">
    <%@ include file="./common/coachData.jsp" %>
    <%@ include file="./common/coachTab.jsp" %>
    <c:if test="${coach.reserve_me == 1 }">
	    <c:if test="${empty existCoach }">
	     <c:forEach items="${ctpList }" var="c" varStatus="status">
	        <c:if test="${status.index==0}">  
	        	<c:if test="${c.price != 0 }">
	    			<%@ include file="./common/onlineReservation.jsp" %>
	    		</c:if>
	    	</c:if>
	    </c:forEach>
	    </c:if>
    </c:if>
    <%@ include file="./common/comment.jsp" %>
  </main>
  <%@ include file="./common/coachFooter.jsp" %>
  <%@ include file="./common/mobiscroll.jsp" %> 
</body>

</html>
