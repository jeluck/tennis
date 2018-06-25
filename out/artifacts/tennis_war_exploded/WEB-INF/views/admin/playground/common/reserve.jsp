<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/reserve.jsp -->
    
<!-- 周期预定公共部分 start -->
<div class="lz-cycle lz-hide" id="lz-cyle" data-info="周期预定">
<h3 data-info="在线预定的标题">
<div class="lz-left">在线预定 &nbsp;&nbsp;&nbsp;&nbsp;
	<select id="user" onchange="changeObj(this)">
		<option value="">请选择</option>
		<c:forEach items="${users }" var="u">
			<option value="${u.id }">${u.username }</option>
		</c:forEach>
	</select>
</div>
  <div class="lz-right" onclick="lz.cyleToggle(this);">周期预定&nbsp;&nbsp;<i class="lz-iconfont lz-icon-jiantou"></i></div>
  <span class="lz-right lz-square" onclick="lz.cyleToggle(this);"><i class="lz-iconfont lz-icon-dagoutwo"></i></span> </h3>
<div class="lz-popups">
  <ul>
    <li>
        <span class="lz-left">开始时间</span>
        <span class="lz-right"><input style="border:none;" type="date" readonly value="2015-11-11"></span></span> 
      </li>
    <li>
        <span class="lz-left">结束时间：</span>
        <span class="lz-right"><input type="date" value="2015-11-11"></span></span> 
    </li>
  </ul>
  <div class="lz-btn">
    <div class="lz-false" onclick="lz.cyleToggle(this, false);"> 取消 </div>
    <div class="lz-true" onclick="lz.cyleToggle(this, true);"> 确定 </div>
  </div>
</div>
</div>
<!-- 周期预定公共部分 end -->