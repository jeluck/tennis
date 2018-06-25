<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/reserveFooter.jsp -->
<!--立即预定 start-->
  <div class="lz-fixed" id="lz-booknow" style="display: none;">
    <h4 class="lz-tit-2"><i class="lz-iconfont lz-icon-zan"></i>推荐教练<span id="lz-msgcoach">（可选项）</span><span class="lz-close" onclick="reservation.booknowClose();"><i class="lz-iconfont lz-icon-guanbi"></i></span></h4>
    <div class="lz-selectcoach" id="lz-selectcoach">
      <ul class="swiper-wrapper" id="lz-selectcoach-ul">
      </ul>
    </div>
    <div class="lz-selectsite" id="lz-selectsite">
      <ul class="swiper-wrapper" id="lz-booked"></ul>
    </div>
    <div class="lz-abreast-btn">
      <div class="lz-money" id="lz-money-sums" style="font-size: 40px;color: red;">￥0</div>
      <div class="lz-btn" onclick="aa();">立即预定</div>
    </div>  
  </div>
  
  
  <!--立即预定 end-->