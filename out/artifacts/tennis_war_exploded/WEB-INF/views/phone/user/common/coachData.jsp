<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 文件路径地址：/views/phone/user/common/coachData.jsp -->
<!-- 教练信息 start -->
<script src="/js/admin_common.js"></script>
<script type="text/javascript">
	function follow(userId,friendUserId){
		if(userId==""){
			if (window.pay) {
	            return window.pay.goToLogin(); //安卓如果没登录,返回
	        }
			return;
		}
		var follow=document.getElementById("follow");
		var oBeconcernedcount = document.getElementById('beconcernedcount');
    var iConut = parseInt(oBeconcernedcount.innerHTML.match(/\d+/).input);

		var followText=follow.innerHTML;
		if(followText.indexOf('取消关注') == -1){
			util.POST("/attention.do", {
		        "userId": userId,
		        "friendUserId": friendUserId
		      });
			
			follow.innerHTML='<i class="lz-iconfont lz-icon-tianjiahaoyou"></i>取消关注';
      oBeconcernedcount.innerHTML = (iConut +1);
		}else{
			util.POST("/cancelAttention.do", {
		        "userId": userId,
		        "friendUserId": friendUserId
		      });
			follow.innerHTML='<i class="lz-iconfont lz-icon-tianjiahaoyou"></i>关注';
      oBeconcernedcount.innerHTML = (iConut - 1);
		}
	}
</script>

<section class="lz-coachuser">
<c:if test="${not empty coach.backgroundImg}">
<div class="lz-basic"  style="background-image: url('${coach.backgroundImg}');">
</c:if>
<c:if test="${empty coach.backgroundImg}">
<div class="lz-basic"  style="background-image: url('/images/headimgbg.jpg');">
</c:if>
  <div class="headimg" style="background-image:url('<c:if test="${not empty coach.head_portrait }">${coach.head_portrait }</c:if><c:if test="${empty coach.head_portrait }">/images/coachJap.png</c:if>');">
    <i class="lz-iconfont lz-icon-renzheng"></i>
    <c:if test="${coach.sex=='男' }"><i class="lz-sex lz-iconfont lz-icon-nan"></i></c:if>
    <c:if test="${coach.sex=='女' }"><i class="lz-sex lz-iconfont lz-icon-nv"></i></c:if>
    </div>
  <div class="lz-top"><span id="beconcernedcount">${count.beconcernedcount }</span>&nbsp;&nbsp;粉丝&nbsp;&nbsp;|&nbsp;&nbsp;${count.attentecount }&nbsp;&nbsp;关注&nbsp;&nbsp;|&nbsp;&nbsp;${count.join_clubcount }&nbsp;&nbsp;俱乐部</div>
  <div class="lz-bottom">
    ${coach.age }岁&nbsp;&nbsp;${coach.height }cm&nbsp;&nbsp;${coach.weight }kg
    <br> ${coach.personalitySignature }
  </div>	<c:if test="${empty follow }"><span id="follow" onclick="verifyLogin();follow('${userId}',${coach.userid.id })"><i class="lz-iconfont lz-icon-tianjiahaoyou"></i>关注</span></c:if>
  <c:if test="${not empty follow }"><span id="follow" onclick="follow(${userId},${coach.userid.id })"><i class="lz-iconfont lz-icon-tianjiahaoyou"></i>取消关注</span></c:if>
</div>
<div class="lz-details">
  <div class="lz-z">
    <span>${coach.teaching }<br>执教年限</span>
    <span>${count.traineecount }<br>学员</span>
    <span>${count.lessonCount }<br>累计课时</span>
  </div>
  <div class="lz-r lz-pingfen">
     
          <span>
	        <c:forEach items="${score }" var="s">
	        	<c:if test="${s==0 }">
	        	<i class="lz-iconfont lz-icon-kongxing"></i>
	        	</c:if>
	        	<c:if test="${s==1 }">
	        	<i class="lz-iconfont lz-icon-shixing"></i> 
	        	</c:if>
	        </c:forEach>
	    <c:if test="${coach.evaluate_score!= '0' }">
     	   </span> &nbsp;&nbsp;${coach.evaluate_score } 
     	</c:if>
     	<c:if test="${coach.evaluate_score== '0'}">
     		<i class="lz-iconfont" style="color: #7F7F7F;">暂无</i></span>
     	</c:if>
  </div>
</div>
</section>
<!-- 教练信息 end -->