<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/coachData.jsp -->
<!-- 教练信息 start -->
<script src="/js/jquery-1.8.1.min.js?${getVersion}"></script>
 <link rel="stylesheet" href="/js/plugin/swiper.3.1.7.min.css?${getVersion}">
  <script src="/js/plugin/swiper.3.1.7.min.js?${getVersion}"></script>
<script src="/js/admin_common.js"></script>
 
<script type="text/javascript">
	function follow(userId,friendUserId){
		var follow=document.getElementById("follow");
		
		var followText=follow.innerHTML;
		if(followText.indexOf('取消关注') == -1){
			util.POST("/attention.do", {
		        "userId": userId,
		        "friendUserId": friendUserId
		      });
			
			follow.innerHTML='<i class="lz-iconfont lz-icon-tianjiahaoyou"></i>取消关注';
		}else{
			util.POST("/cancelAttention.do", {
		        "userId": userId,
		        "friendUserId": friendUserId
		      });
			follow.innerHTML='<i class="lz-iconfont lz-icon-tianjiahaoyou"></i>关注';
		}
	}
</script>

<section class="lz-coachuser">
<div class="lz-basic" style="background-image: url('/images/headimgbg.jpg');">
  <div class="headimg" style="background-image:url('${coach.head_portrait }');">
    <i class="lz-iconfont lz-icon-renzheng"></i>
    <c:if test="${coach.sex=='男' }"><i class="lz-sex lz-iconfont lz-icon-nan"></i></c:if>
    <c:if test="${coach.sex=='女' }"><i class="lz-sex lz-iconfont lz-icon-nv"></i></c:if>
    </div>
    <div class="lz-top">${count.beconcernedcount }&nbsp;&nbsp;粉丝&nbsp;&nbsp;|&nbsp;&nbsp;${count.attentecount }&nbsp;&nbsp;关注&nbsp;&nbsp;|&nbsp;&nbsp;${count.join_clubcount }&nbsp;&nbsp;俱乐部</div>
  <div class="lz-bottom">
    ${coach.age }岁&nbsp;&nbsp;${coach.height }cm&nbsp;&nbsp;${coach.weight }kg
    <br> ${coach.personalitySignature }
  </div>
</div>
<div class="lz-details">
  <div class="lz-z">
    <span>请对我评价<br></span>
  </div>
  <div class="lz-r lz-pingfen">
  </div>
</div>
</section>
<!-- 教练信息 end -->
  <!-- 大评分 start -->
    <style type="text/css">
      .lz-pingfen-box {
        overflow: hidden;
        padding: 0.1rem;
        text-align: center;
        background: #fff;
      }
      .lz-pingfen-box p {
        font-size: 0.13rem;
        line-height: 0.38rem;
        color: #ccc;
      }
      .lz-pingfen-box i{
        display: inline-block;
        padding: 0 0.05rem;
        font-size: 0.25rem;
        text-align: center;
        color: #ccc;
      }
      .lz-pingfen-box i.lz-on {
        color: #FE4919;
      }
    </style>
    <div class="lz-pingfen-box" id="lz-pingfen-box">
      <p>请选择评分</p>
      <i onclick="clickPingFan(0);" class="lz-iconfont lz-icon-shixing lz-on"></i>
      <i onclick="clickPingFan(1);" class="lz-iconfont lz-icon-shixing"></i>
      <i onclick="clickPingFan(2);" class="lz-iconfont lz-icon-shixing"></i>
      <i onclick="clickPingFan(3);" class="lz-iconfont lz-icon-shixing"></i>
      <i onclick="clickPingFan(4);" class="lz-iconfont lz-icon-shixing"></i>
    </div>
    <script>
      
      function clickPingFan (iIndex) {
        var aLi = document.querySelectorAll('#lz-pingfen-box i');
        for (var i=0;i<aLi.length;i++) {
          if (i <= iIndex) {
            aLi[i].className = 'lz-iconfont lz-icon-shixing lz-on';
          } else {
            aLi[i].className = 'lz-iconfont lz-icon-shixing';
          }
        }
        
        var d=util.POST("/score.do", {
            "activeId": '${coach.id}',
            "belong": 1,
            "score":(iIndex+1),
            "orderMainNo":"${orderMainNo}"
          });
        
        window.pay.goTOMy();
      } 
      
    </script>
    <!-- 大评分 end -->