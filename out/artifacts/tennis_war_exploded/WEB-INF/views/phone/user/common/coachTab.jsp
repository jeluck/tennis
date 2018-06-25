<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/coachTab.jsp -->

<!-- 选项卡 start -->
<section data-lz-tab="jiaoLianXuanXiangKa,#lz-tab-nav,#lz-tab-box">
  <ul class="lz-tab-nav" id="lz-tab-nav">
    <li>预约</li>
    <li>教练资料</li>
    <li>他的动态</li>
  </ul>
  <div class="lz-tab-box" id="lz-tab-box">
    <div>
      <div class="lz-yuyue">
        <h2>
                <i class="lz-iconfont lz-icon-lou"></i>教学地点
            </h2>
        <div class="lz-table-1">
          <ul>
            <!-- 当教练值为驻场教练时显示 -->
            <c:if test="${o.coachType ==2 }">
            	<li>
                <i class="lz-iconfont lz-icon-shangpu"></i>驻场场馆&nbsp;&nbsp;<strong>${playground.name }</strong>
             	 </li>
             	 <c:if test="${not empty freeEquipment }">
	            <li>
	              <i class="lz-iconfont lz-icon-xiaogou"></i>免费提供器材</strong>
	            </li>
	            </c:if>
            </c:if>
            
            <!-- 当教练为自由时显示-->
            <c:if test="${o.coachType ==1 }">
	            <li>
	              <i class="lz-iconfont lz-icon-zoulu"></i>上门区域&nbsp;&nbsp;
	              <c:if test="${not empty sarea }" var="hava">
	                <c:forEach items="${area }" var="a">
	                  <c:forEach items="${sarea }" var="sa">
	                    <c:if test="${a.region_id == sa.zhe_id  }">
	                      <strong>${a.region_name }</strong>&nbsp;&nbsp;
	                    </c:if>
	                  </c:forEach>
	                </c:forEach>
	              </c:if>
	              <c:if test="${!hava}">暂无</c:if>
	            </li>
	            
	            <li>
	              <i class="lz-iconfont lz-icon-quan"></i>上门场馆
	              <c:forEach items="${pList }" var="p">
                		</br><strong>${p.name }</strong>
                	</c:forEach>
	            </li>
	            <c:if test="${not empty freeEquipment }">
	            <li>
	              <i class="lz-iconfont lz-icon-xiaogou"></i>免费提供器材</strong>
	            </li>
	            </c:if>
            </c:if>
            
            <!-- 当教练为运营商 -->
            <c:if test="${o.coachType ==3}">
            	<li>
            		<i class="lz-iconfont lz-icon-shangpu"></i>驻场场馆&nbsp;&nbsp;
            		<c:forEach items="${pList }" var="p">
                		&nbsp;&nbsp;<strong>${p.name }</strong>
                	</c:forEach>
             	 </li>
             	 
	            <li>
	              <i class="lz-iconfont lz-icon-<c:if test="${empty freeSpace }">guanbi</c:if><c:if test="${not empty freeSpace }">xiaogou</c:if>"></i>免费场地</strong>
	            </li>
	            <c:if test="${not empty freeEquipment }">
	            <li>
	              <i class="lz-iconfont lz-icon-xiaogou"></i>免费提供器材</strong>
	            </li>
	            </c:if>
            </c:if>
			<li>
              <i class="lz-iconfont lz-icon-quan"></i>教练专长&nbsp;&nbsp;<string>${o.professional }</strong>
            </li>
          </ul>
          <div class="lz-dizhi" onclick="ditu()"><i class="lz-iconfont lz-icon-ditu"></i></div>
        </div>
      </div>
    </div>
    <div>
      <section class="lz-usre">
        <ul class="lz-user-list">
          <c:if test="${not empty clist }">
            <li class="lz-indenting">
              <span class="lz-left">执教经历</span>
            </li>
            <c:forEach items="${clist }" var="o">
              <li>
                <span class="lz-left"> ${o.begin_time}~ ${o.end_time}</span>
                <span class="lz-right"> ${o.unitName}</span>
              </li>
            </c:forEach>
          </c:if>
          <li>
            <span class="lz-left">擅长技能</span>
            <span class="lz-right">${o.goodAt }</span>
          </li>
          <li>
            <span class="lz-left">资质认证</span>
            <span class="lz-right lz-authentication" id="lz-authentication" style="width: 100%; text-align: right;">
                <c:if test="${not empty o.certificate.ph1 }">ITF&nbsp;&nbsp;</c:if>
                <c:if test="${not empty o.certificate.ph2 }">USPTA&nbsp;&nbsp;</c:if>
                <c:if test="${not empty o.certificate.ph3 }">PTR&nbsp;&nbsp;</c:if>
                <c:if test="${not empty o.certificate.ph4 }">RPT&nbsp;&nbsp;</c:if>
                <c:if test="${not empty o.certificate.ph5 }">Equelite&nbsp;&nbsp;</c:if>
                <c:if test="${not empty o.certificate.ph6 }">CTA</c:if>                    
                </span>
          </li>

	        <li >
	            <span class="lz-left">学历</span>
	            <span class="lz-right"><span id="lz-levelValue">${o.educationalBackground }</span>
	            </span>
	        </li>
	
	        <li>
	            <span class="lz-left">毕业院校</span>
	            <span class="lz-right">${o.userid.school }</span>
	        </li>
        </ul>
      </section>
    </div>
    <div>暂无</div>
  </div>
</section>
<!-- 选项卡 end -->
<!-- 教学形式 start -->
<section>
  <div class="lz-yuyue">
    <h2>
                <i class="lz-iconfont lz-icon-lou"></i>教学形式
            </h2>
    <div class="lz-table-2">
      <ul id="lz-teaching">
        <c:forEach items="${ctpList }" var="c" varStatus="status">
          <c:if test="${status.index==0}">
            <li class="lz-cur" onclick="reservation.teachingSelect(this);" data-teaching='{"id": ${c.id }, "price": ${c.price }}'>
              <span class="lz-l">1对${c.people_num }</span>
              <span class="lz-r">${c.price }/小时</span>
            </li>
          </c:if>
          <c:if test="${status.index!=0}">
            <li onclick="reservation.teachingSelect(this);" data-teaching='{"id": ${c.id }, "price": ${c.price }}'>
              <span class="lz-l">1对${c.people_num }</span>
              <span class="lz-r">${c.price }/小时</span>
            </li>
          </c:if>
        </c:forEach>
      </ul>
    </div>
  </div>
</section>
<!-- 教学形式 end -->
<script>
  //安卓和IOS端调用JS,打开地图
  function ditu() {
    venuesViewtomap(1,'${coach.id}');
  }

</script>
