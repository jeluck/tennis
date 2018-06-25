<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>教学设置</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<script src="/js/jquery-1.8.1.min.js"></script>
<script src="/js/admin_common.js?c=<%=new Date()%>"></script>
<script src="/js/common.js"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/user.css?2015">
<script src="/js/user.js?c=<%=new Date()%>"></script>

<%@ include file="./common/common_init.jsp" %>
<!--本页面单独使用文件 end-->
</head>

<body class="lz-body">
<main class="lz-view">
    <section class="lz-usre">
    	<input type="hidden" id="userId" value="${o.userid.id }">
        <ul class="lz-user-list">
            <li onclick="beijian()">
                <span class="lz-left">个人主页</span>
                <span class="lz-right">设置个人主页背景<i class="lz-iconfont lz-icon-jiantouright"></i></span>
            </li>
        </ul>
        <h2 class="lz-tit-4">教练专长</h2>
        <ul class="lz-user-list">
            <li style="padding-left: 0.1rem;">
                <span class="lz-left"></span>
                <c:if test="${o.coachType != 2 }">
                	<span class="lz-right lz-child-type" style="width: 100%;">
	                    <div class="lz-value <c:if test="${o.professional == '全部(业余)' }">lz-cur </c:if> " onclick="lz.setRadio(this, '全部(业余)');"><em>全部（业余）</em></div>
	                    <div class="lz-value <c:if test="${o.professional == '少儿(业余)' }">lz-cur </c:if> " onclick="lz.setRadio(this, '少儿(业余)');"><em>少儿（业余）</em></div>
	                    <div class="lz-value <c:if test="${o.professional == '成人(业余)' }">lz-cur </c:if> " onclick="lz.setRadio(this, '成人(业余)');"><em>成人（业余）</em></div>
	                    <div class="lz-value <c:if test="${o.professional == '职业' }">lz-cur </c:if> " onclick="lz.setRadio(this, '职业');"><em>职业</em></div>
	                    <input type="hidden" id="professional" value="${o.professional }">
	                </span>
                </c:if>
                <c:if test="${o.coachType == 2 }">
                	<span class="lz-right lz-child-type" style="width: 100%;">
	                    <div class="lz-value <c:if test="${o.professional == '全部(业余)' }">lz-cur </c:if> " ><em>全部（业余）</em></div>
	                    <div class="lz-value <c:if test="${o.professional == '少儿(业余)' }">lz-cur </c:if> " ><em>少儿（业余）</em></div>
	                    <div class="lz-value <c:if test="${o.professional == '成人(业余)' }">lz-cur </c:if> " ><em>成人（业余）</em></div>
	                    <div class="lz-value <c:if test="${o.professional == '职业' }">lz-cur </c:if> " ><em>职业</em></div>
	                    <input type="hidden" id="professional" value="${o.professional }">
	                </span>
                </c:if>
            </li>
        </ul>
        	<h2 class="lz-tit-4">教练地点</h2>
	        <ul class="lz-user-list">
	        	<c:if test="${o.coachType != 1 }">
		            <li>
		                <span class="lz-left">球场场馆</span>
		                <span class="lz-right">${playground.name }</span>
		            </li>
	            </c:if>
	            <c:forEach items="${ds }" var="d" varStatus="v">
	            	<c:if test="${o.coachType == 3 }">
	            		<c:if test="${d.service != '上门服务'}">
	            			<li>
				                <span class="lz-left">${d.service }</span>
				                <span class="lz-right">
				                    <span class="lz-switch 
				                    	<c:if test="${fn:contains(o.services, d.id) == false }">no</c:if>" onclick="lz.setSwitch(this, ['', '${d.id}']); checkTea(this,${d.id});">
				                        <i></i>
				                        <c:if test="${fn:contains(o.services, d.id) == false }">
				                        	<input type="hidden" name="services" />
				                        </c:if>
				                        <c:if test="${fn:contains(o.services, d.id) == true }">
				                        	<input type="hidden" name="services"  value="${d.id}">
				                        </c:if>
				                    </span>
				                </span>
				                <input type="hidden" id="sev">
				                
				                <input type="hidden" id="ching" value="${o.services }">
				            </li>
	            		</c:if>
	            	</c:if>
					<c:if test="${o.coachType == 1 }">
						<c:if test="${d.service != '免费场地' &&  d.service != '驻场服务'}">
							<li>
				                <span class="lz-left">${d.service }</span>
				                <span class="lz-right">
				                    <span class="lz-switch 
				                    	<c:if test="${fn:contains(o.services, d.id) == false }">no</c:if>" onclick="lz.setSwitch(this, ['', '${d.id}']); checkTea(this,${d.id});">
				                        <i></i>
				                        <c:if test="${fn:contains(o.services, d.id) == false }">
				                        	<input type="hidden" name="services" />
				                        </c:if>
				                        <c:if test="${fn:contains(o.services, d.id) == true }">
				                        	<input type="hidden" name="services"  value="${d.id}">
				                        </c:if>
				                    </span>
				                </span>
				                <input type="hidden" id="sev">
				                
				                <input type="hidden" id="ching" value="${o.services }">
				            </li>
			            </c:if> 
					</c:if>
					<c:if test="${o.coachType == 2 }">
						<c:if test="${d.service == '提供器材' || d.service  == '驻场服务'  }">
							<li>
				                <span class="lz-left">${d.service }</span>
				                <span class="lz-right">
				                    <span class="lz-switch 
				                    	<c:if test="${fn:contains(o.services, d.id) == false }">no</c:if>" >
				                        <i></i>
				                        <c:if test="${fn:contains(o.services, d.id) == false }">
				                        	<input type="hidden" name="services" />
				                        </c:if>
				                        <c:if test="${fn:contains(o.services, d.id) == true }">
				                        	<input type="hidden" name="services"  value="${d.id}">
				                        </c:if>
				                    </span>
				                </span>
				                <input type="hidden" id="sev">
				                
				                <input type="hidden" id="ching" value="${o.services }">
				            </li>
				         </c:if>
					</c:if>
				</c:forEach>
	            <span id="teaBlock" <c:if test="${dis == 0 || o.coachType!=1}"> style="display:none;"</c:if> >
	            <li style="padding-left: 0.1rem; border-bottom: none;">
	                <ul class="lz-user-list lz-site-area" id="lz-aboveArea-box">
	                    <li onclick="document.getElementById('lz-aboveArea').style.display = 'block';">
	                        <span class="lz-left">上门区域</span>
	                        <span class="lz-right">多选项<i class="lz-iconfont lz-icon-jiantouright"></i></span>
	                    </li>
	                    <c:if test="${not empty area }">
					  	  	 <c:forEach items="${area }" var="a">
					  	  	 	<c:forEach items="${sarea }" var="sa">
					  	  	 		<c:if test="${a.region_id == sa.zhe_id  }">
					  	  	 			<li>
					                        <span class="lz-left">
					                            <span class="lz-checked-2">
					                            <i class="lz-show"></i></span>${a.region_name }
					                        </span>
					                        <span class="lz-right" onclick="lz.removeArea(this);"><i class="lz-iconfont lz-icon-guanbi"></i></span></span>
					                        <input type="hidden" name="area" value="${a.region_id }">
					                    </li>
					  	  	 		</c:if>
			                    </c:forEach>
						     </c:forEach>
					  	</c:if>
	                </ul>
	            </li>
	            
	            <li style="padding-left: 0.1rem; border-bottom: none;">
	                <ul class="lz-user-list lz-site-area" id="lz-abovePlay-box">
	                    <li onclick="document.getElementById('lz-abovePlay').style.display = 'block';">
	                        <span class="lz-left">上门场馆</span>
	                        <span class="lz-right">多填项<i class="lz-iconfont lz-icon-jiantouright"></i></span>
	                        <c:if test="${not empty play }">
						  	  	 <c:forEach items="${play }" var="p">
						  	  	 	<c:forEach items="${splay }" var="sp">
						  	  	 		<c:if test="${p.id == sp.zhe_id  }">
						  	  	 			<li>
						                        <span class="lz-left">
						                            <span class="lz-checked-2">
						                            <i class="lz-show"></i></span>${p.name }
						                        </span>
						                        <span class="lz-right" onclick="lz.removeArea(this);"><i class="lz-iconfont lz-icon-guanbi"></i></span></span>
						                        <input type="hidden" name="play" value="${p.id }">
						                    </li>
						  	  	 		</c:if>
				                    </c:forEach>
							     </c:forEach>
					  		</c:if>
	                    </li>
	                </ul>
	            </li>
	            </span>
	        </ul>
	        
<%-- 	        <c:if test="${o.coachType == 1 }"> --%>
	        <h2 class="lz-tit-4">申请驻场教练</h2>
	        <ul class="lz-user-list">
	                <ul class="lz-user-list lz-site-area" id="lz-aboveCoachPlay-box">
	                    <li onclick="checkPlay(${applyStatus },${o.coachType })">
	                        <span class="lz-left">申请场馆</span>
	                        <span class="lz-right">申请<i class="lz-iconfont lz-icon-jiantouright"></i></span>
	                        <c:if test="${not empty play }">
						  	  	 <c:forEach items="${play }" var="p">
						  	  	 	<c:forEach items="${coachPlay }" var="sp">
						  	  	 		<c:if test="${p.id == sp.playground_id  }">
						  	  	 			<li>
						                        <span class="lz-left">
						                            <span class="lz-checked-2">
						                            <i class="lz-show"></i></span>${p.name }
						                        </span>
						                       
						                        <span class="lz-right"  <c:if test="${sp.status != 3 }"> onclick="lz.removeArea(this);"  </c:if>>
						                        	
						                        <c:if test="${sp.status == 1 }">
						                        	待审核
						                        </c:if>
						                        <c:if test="${sp.status == 2 }">
						                        	已拒绝
						                        </c:if>
						                        <c:if test="${sp.status == 3 }">
						                        	已通过
						                        </c:if>
						                         <c:if test="${sp.status != 3 }"><i class="lz-iconfont lz-icon-guanbi"></i></c:if>
						                        
						                        </span></span>
						                        <input data-status="${sp.status}" type="hidden" name="play" value="${p.id }">
						                    </li>
						  	  	 		</c:if>
				                    </c:forEach>
							     </c:forEach>
					  		</c:if>
	                    </li>
	                </ul>
	            </li>
	            </span>
	        </ul>
<%-- 	        </c:if> --%>
<!--         <h2 class="lz-tit-4">教学器材</h2> -->
<!--         <ul class="lz-user-list"> -->
<!--             <li> -->
<!--                 <span class="lz-left">免费提供器材</span> -->
<%--                 <c:if test="${o.coachType != 2 }"> --%>
<!-- 	                <span class="lz-right"> -->
<%-- 	                    <span class="lz-switch <c:if test="${o.equipment == 2 }">no</c:if>" onclick="lz.setSwitch(this, ['2', '1']);"> --%>
<!-- 	                        <i></i> -->
<%-- 	                        <input type="hidden" id="equipment" value="${o.equipment}"> --%>
<!-- 	                    </span> -->
<!-- 	                </span> -->
<%--                 </c:if> --%>
<%--                 <c:if test="${o.coachType == 2 }"> --%>
<!-- 	                <span class="lz-right"> -->
<%-- 	                    <span class="lz-switch <c:if test="${o.equipment == 2 }">no</c:if>" > --%>
<!-- 	                        <i></i> -->
	                        <input type="hidden" id="equipment" value="${o.equipment}">
<!-- 	                    </span> -->
<!-- 	                </span> -->
<%--                 </c:if> --%>
<!--             </li> -->
<!--         </ul> -->
<%--         <c:if test="${o.coachType != 2 }"> --%>
<!--             <ul class="lz-user-list"> -->
<!-- 	            <li> -->
<%-- 	                <span class="lz-left" onclick="location='carrschedule.do?coachId=${o.id}'">预约时间段与价格设置</span> --%>
<!-- 	                <span class="lz-right"><i class="lz-iconfont lz-icon-jiantouright"></i></span> -->
<!-- 	            </li> -->
<!-- 	        </ul> -->
<%--         </c:if> --%>

        
<!--         <input type="button" value="提交" onclick="saveTeaching()"> -->
    </section> 
</main>
    
<!-- 上面区域选择 start -->
<div id="lz-aboveArea" class="lz-level">
  <ul class="lz-list">
  	  <c:if test="${empty area }">
  	  	 <li><strong>目前还没有数据</strong></li>
  	  </c:if>
  	  <c:if test="${not empty area }">
  	  	  <c:forEach items="${area }" var="a">
	      	 <li onclick="lz.aboveArea(this, ${a.region_id} );"><strong>${a.region_name }</strong></li>
	      </c:forEach>
  	  </c:if>
  </ul>
</div>


<div id="lz-abovePlay" class="lz-level">
  <ul class="lz-list">
  	  <c:if test="${empty play }">
  	  	 <li><strong>目前还没有数据</strong></li>
  	  </c:if>
  	  <c:if test="${not empty play }">
	  	  <c:forEach items="${play }" var="p">
	      	 <li onclick="lz.abovePlay(this, ${p.id} );"><strong>${p.name }</strong></li>
	      </c:forEach>
  	  </c:if>
  </ul>
</div>

<div id="lz-aboveCoachPlay" class="lz-level">
  <ul class="lz-list">
  	  <c:if test="${empty play }">
  	  	 <li><strong>目前还没有数据</strong></li>
  	  </c:if>
  	  <c:if test="${not empty play }">
	  	  <c:forEach items="${play }" var="p">
	      	 <li onclick="lz.aboveCoachPlay(this, ${p.id});"><strong>${p.name }</strong></li>
	      </c:forEach>
  	  </c:if>
  </ul>
</div>
<!-- 上面区域选择 end -->
</body>
</html>
