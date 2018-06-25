
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/onlineReservation.jsp -->
    <!--在线预定 start-->
    <section class="lz-mb">
      <div class="lz-destine">
        <div class="lz-cycle lz-hide" id="lz-cyle">
          <h3 data-info="在线预定的标题">

<script type="text/javascript">
	function changMeber(obj){
		if(obj.value==1){
			$("#u").css("display","inline");
			$("#phone").css("display","none");
			$("#phonename").css("display","none");
			$("#phone").val("");
			$("#phonename").val("");
			$("#search").val("检索");
		}
		if(obj.value==2){
			reservation.formData.userId = 0
			$("#u").children().val("");
			$("#u").css("display","none");
			$("#phone").css("display","inline");
			$("#phonename").css("display","inline");
		}
	}
	
	function aa(){
		if($("#meber").val()==1){
			if($("#user").val() != '0'){
				reservation.postFormData();
			}else{
				alert("请选择用户！");
			}
			
		}else{
			if($("#phone").val()!='' && $("#phonename").val()!=''){
				reservation.postFormData();
			}else{
				alert("请输入该客户的联系方式和姓名！");
			}
		}
		
	}
	
	function searchPhone(){
		var info=util.POST("/pgm/searchPhone.do",{"Phone":$("#searchPhone").val()})
		if(info.data!=null){
			$("#user").val(info.data.id);
			$("#userMoeny").html("该用户余额为"+info.data.amount+"元")
			reservation.formData.userId = info.data.id;
		}else{
			alert("未找到此用户")
			$("#user").val(info.data);
		}
	}
	
	function searchId(element){
		reservation.formData.userId = element.value;
		var info=util.POST("/pgm/searchId.do",{"id":$("#user").val()})
		if(info.data!=0){
			$("#searchPhone").val(info.data.uphone);
			$("#userMoeny").html("该用户余额为"+info.data.amount+"元")
		}else{
			$("#searchPhone").val("");
			$("#userMoeny").html("");
		}
		
	}
</script>
<div class="lz-left">在线预定&nbsp;&nbsp;&nbsp;&nbsp;
<select id="meber" onchange="changMeber(this)">
	<option value="1">会员</option>
	<option value="2">非会员</option>
</select>


<span id="u">
<select id="user" onchange="searchId(this)" >
		<option value="0">请选择</option>	
		<c:forEach items="${users }" var="u">
			<option value="${u.id }">${u.username }</option>
		</c:forEach>
</select>
<input id="searchPhone" name="searchPhone" type="text" placeholder="请选入要检索的手机号码"/>
<input id="search" class="btn btn-sm btn-success"  type="button" value="[ 检索]" onclick="searchPhone()">
<span id="userMoeny"></span>
</span>
<input placeholder="请输入联系人号码" type="text" onchange="reservation.formData.phone = this.value;" style="display: none;" id="phone"/>
<input placeholder="请输入联系人名字" type="text" onchange="reservation.formData.phoneName = this.value;" style="display: none;" id="phonename"/>
</div>
  <div class="lz-right" onclick="reservation.cyleToggle(this);">周期预定&nbsp;&nbsp;<i class="lz-iconfont lz-icon-jiantou"></i></div>
  <span class="lz-right lz-square" onclick="reservation.cyleToggle(this);"><i class="lz-iconfont lz-icon-dagoutwo"></i></span> </h3>
          <div class="lz-popups">
            <ul>
              <li>
                <span class="lz-left">开始时间</span>
                <span class="lz-right"><input type="text" readonly value="${stratTime}" placeholder="选择时间"></span></span>
              </li>
              <li>
                <span class="lz-left">结束时间</span>
                <span class="lz-right"><input id="endTime" data-type="date" data-end-year="2020" type="text" value="" readonly placeholder="选择时间"></span></span>
              </li>
              <li class="lz-tag"><span class="lz-left">热门选择</span><span class="lz-right">
                  <c:forEach items="${cyMap}" var="mymap" ><strong onclick="reservation.setEndTime(this, '${mymap.value}')">${mymap.key}</strong></c:forEach>
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
              <li onClick="reservation.selectDate(this);" class="swiper-slide" data-date="${d.year}-${d.month}-${d.day}" style="height: auto;">
                <time>${d.month } &nbsp;-&nbsp;${d.day }</time>${d.week }</li>
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