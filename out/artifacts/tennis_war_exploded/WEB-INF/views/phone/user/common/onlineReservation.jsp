
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/onlineReservation.jsp -->
    <!--在线预定 start-->
    <section class="lz-mb">
      <div class="lz-destine">
        <div class="lz-cycle lz-hide" id="lz-cyle">
          <h3 data-info="在线预定的标题">
<div class="lz-left">在线预定</div>
  <div class="lz-right" onclick="reservation.cyleToggle(this);">周期预定&nbsp;&nbsp;<i class="lz-iconfont lz-icon-jiantou"></i></div>
  <span class="lz-right lz-square" onclick="reservation.cyleToggle(this);"><i class="lz-iconfont lz-icon-dagoutwo"></i></span> </h3>
          <div class="lz-popups">
            <ul>
              <li>
                <span class="lz-left">开始时间</span>
                <span class="lz-right"><input type="text" readonly="readonly" value="${stratTime}" ></span>
              </li>
              <li>
                <span class="lz-left">结束时间</span>
                <span class="lz-right"><input id="endTime" data-type="date" data-end-year="2020" type="text" value="" readonly placeholder="选择时间"></span>
              </li>
              <li class="lz-tag"><span class="lz-left">热门选择</span><span class="lz-right">
                  <c:forEach items="${cyMap}" var="mymap" ><strong onclick="reservation.setEndTime(this, '${mymap.value}')">${mymap.key}</strong></c:forEach>
                  </span>
              </li>
            </ul>
            <div class="lz-btn">
              <div class="lz-false" onclick="reservation.cyleToggle(this, false);"> 取消 </div>
              <div class="lz-true" onclick="reservation.cyleToggle(this, true);"> 确定 </div>
            </div>
          </div>
        </div>

        <div class="lz-date" id="lz-weeks">
          <ul class="swiper-wrapper">
            <c:forEach items="${dateList }" var="d">
              <li onClick="reservation.selectDate(this);" class="swiper-slide" data-date="${d.year}-${d.month}-${d.day}">
                <time>${d.month } &nbsp;-&nbsp;${d.day }</time>
                ${d.week }</li>
            </c:forEach>
          </ul>
        </div>
        <div class="lz-more lz-hide" id="lz-more" onclick="reservation.moreContent(this);"><i class="lz-iconfont lz-icon-sanjiaoxing"></i></div>
        <div class="lz-hour lz-hide" id="lz-hour-box">
          <div id="lz-hour-child">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
          </div>
        </div>
      </div>
    </section>
    <script type="text/javascript">
      new Swiper('#lz-weeks', {
        freeMode: true,
        slidesPerView: 'auto',
      });

    </script>
    <!--在线预定 end-->