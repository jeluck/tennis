<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/reserveFooter.jsp -->
<!--立即预定 start-->
  <div class="lz-fixed" id="lz-booknow" style="display: none;">
    <span class="lz-close" onclick="reservation.booknowClose();"><i class="lz-iconfont lz-icon-guanbi"></i></span>
    
    <h4 class="lz-tit-2"><i class="lz-iconfont lz-icon-zan"></i>推荐教练<span id="lz-msgcoach">（可选项）</span></h4>
    <div class="lz-selectcoach" id="lz-selectcoach">
      <ul class="swiper-wrapper" id="lz-selectcoach-ul">
      </ul>
    </div>
    
    <div class="lz-selectsite" id="lz-selectsite">
      <ul class="swiper-wrapper" id="lz-booked"></ul>
    </div>
    <div class="lz-abreast-btn">
      <div class="lz-money" id="lz-money-sums">￥0</div>
      <c:if test="${empty notReserve }">
      <div class="lz-btn" onclick="verifyLogin();reservation.postFormData();" >立即预定</div>
      </c:if>
      <!-- 如果是教练端,需要判断当前教练能否预定场馆 -->
      <c:if test="${not empty notReserve }">
      <div class="lz-btn">不能预定</div>
      </c:if>
    </div>  
  </div>
  <!--立即预定 end-->
  <!--收藏，分享，评论 start-->
  <div style="height:0.48rem;"></div>
  <div class="lz-fixed lz-share">
    <div class="lz-abreast-btn">
      <div class="<c:if test="${not empty love }" var="hava" >lz-collection-true</c:if>" onclick="reservation.collection(this, {userId: '${userId }', activeId: '${activeId }', type: '${type}'})"> 
        <i class="<c:if test="${empty love }" var="hava" >lz-iconfont lz-icon-kongxing</c:if><c:if test="${not empty love }" var="hava" >lz-iconfont lz-icon-shixing</c:if>"></i>
        收藏
      </div>
      <div onclick="reservation.showCommentBox();"> <i class="lz-iconfont lz-icon-pinglun"></i>评论 </div>
      <div onclick="shareIt();"  > <i class="lz-iconfont lz-icon-fenxiang"></i>分享 </div>
    </div>
  </div>
  <!--收藏，分享，评论 end-->

  <!-- 评论框 start -->
  <div class="lz-fixed-re" id="lz-fixed-re" style="display: none;" data-position="fixed">
    <textarea id="lz-comment-content" placeholder="输入回复内容" oninput="lz.textareaPullHeight(this);"></textarea>
    <button onclick="reservation.postComment(this, {userId: '${userId }', activeId: '${activeId}', orderType: '${type}'})"><span>发送</span></button>
  </div>
  <!-- 评论框 end -->
  <script type="text/javascript">
    document.querySelector('main').addEventListener('click', function () {
      document.getElementById('lz-fixed-re').style.display = 'none';
    }, false);
  </script>
   <script type="text/javascript" src="/js/ios/ios_common.js"> </script>
   <script type="text/javascript" src="/js/plugin/PositionFixedBottom.js"> </script>
