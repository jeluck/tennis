<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>实名认证</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js?${getVersion}"></script>
<script src="/js/admin_common.js?${getVersion}"></script>
<script src="/js/common.js?${getVersion}"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/user.css?${getVersion}">
<script src="/js/user.js?${getVersion}"></script>
<!--本页面单独使用文件 end-->
<%@ include file="./common/mobiscroll.jsp" %>

<%@ include file="./common/common_init.jsp" %>
</head>

<body class="lz-body">
<main class="lz-view">
 
    <section class="lz-usre">
    	<input id="userId" type="hidden" value="${o.userid.id }">
        <!-- 教育背景 start -->
        <h2 class="lz-tit-4">教育背景</h2>
        <ul class="lz-user-list">
            <li onclick="lz.selectLevel(this);">
                <span class="lz-left">学历</span>
                <span class="lz-right"><span id="lz-levelValue">${o.educationalBackground }</span>
                 <input type="hidden" id="xueli" value="${o.educationalBackground }"><i class="lz-iconfont lz-icon-jiantouright"></i>
                </span>
            </li>
<!--             <li onclick="lz.selectLevel(this, 'lz-professional');"> -->
<!--                 <span class="lz-left">专业</span> -->
<%--                 <span class="lz-right"><span id="lz-professionalValue">${o.professional }</span> --%>
<%--                  <input type="hidden" id="professional" value="${o.professional }"><i class="lz-iconfont lz-icon-jiantouright"></i> --%>
<!--                 </span> -->
<!--             </li> -->
            <li>
                <span class="lz-left">毕业院校</span>
                <span class="lz-right"><input type="text" id="school" placeholder="请输入毕业院校" value="${o.userid.school }"></span>
            </li>
			<li>
                <span class="lz-left">专业</span>
                <span class="lz-right"><input type="text" id="professional" placeholder="请输入专业" value="${o.schoolzy }"></span>
            </li>
            
        </ul>
        <!-- 教育背景 end -->
        <!-- 执教经验 start -->
        <h2 class="lz-tit-4">执教经验</h2>
        <ul class="lz-user-list">
            <li>
                <span class="lz-left">执教年龄</span>
                <span class="lz-right">
                
                	<input type="tel" id="teaching" style="IME-MODE: disabled; " 
                						   onkeyup="this.value=this.value.replace(/\D/g,'')"  
                						   onafterpaste="this.value=this.value.replace(/\D/g,'')" 
										   placeholder="输入执教年龄"  class="col-xs-10 col-sm-5" 
										   <c:if test="${o.teaching > 0}"> value="${o.teaching }" </c:if> />	
                </span>
            </li>
            <li class="lz-indenting">
                <span class="lz-left">执教经历</span>
                <span class="lz-right" onclick="lz.addDate(this);"><span id="error" style="color: red;"></span><i class="lz-iconfont lz-icon-jia"></i></span>
            </li>
           	<c:forEach items="${clist }" var="o" >
           	 <li class="lz-addunit">
                <div>
                  <input type="text" name="unitName" placeholder="单位名称" value="${o.unitName }"> 
                </div>
                <div>
                <input data-type="date" type="text" name="begin_time" placeholder="开始时间" value="${o.begin_time }"> 
                </div>
                <div> 
                  <input data-type="date" type="text" name="end_time" placeholder="结束时间" value="${o.end_time }"> 
                </div>
                <div onclick="lz.removeExper(this);" style="width:10%; text-align: right;"><i class="lz-iconfont lz-icon-guanbi"></i></div>       
              </li>     		
           	</c:forEach>
            <li>
                <span class="lz-left">资质认证</span>
                <span class="lz-right lz-authentication" id="lz-authentication" style="width: 100%;">
                    <div class="lz-table">
                    	<c:if test="${empty o.certificate.ph1 }">
                    		<div class="lz-table-cell" id = "ph1" ><span onclick="auth('1')">ITF</span></div>
                    	</c:if>
                        <c:if test="${not empty o.certificate.ph1 }">
                    		<div class="lz-table-cell"><span class="lz-cur" >ITF</span></div>
                    	</c:if>
                        <c:if test="${empty o.certificate.ph2 }">
                    		<div class="lz-table-cell" id = "ph2" ><span  onclick="auth('2')">USPTA</span></div>
                    	</c:if>
                        <c:if test="${not empty o.certificate.ph2 }">
                    		<div class="lz-table-cell"><span class="lz-cur" >USPTA</span></div>
                    	</c:if>
                        <c:if test="${empty o.certificate.ph3 }">
                    		<div class="lz-table-cell" id = "ph3" ><span onclick="auth('3')">PTR</span></div>
                    	</c:if>
                        <c:if test="${not empty o.certificate.ph3 }">
                    		<div class="lz-table-cell"><span class="lz-cur" >PTR</span></div>
                    	</c:if>
                    </div>
                    <div class="lz-table">
                    	<c:if test="${empty o.certificate.ph4 }">
                    		<div class="lz-table-cell" id = "ph4" ><span onclick="auth('4')">RPT</span></div>
                    	</c:if>
                        <c:if test="${not empty o.certificate.ph4 }">
                    		<div class="lz-table-cell"><span class="lz-cur" >RPT</span></div>
                    	</c:if>
                        <c:if test="${empty o.certificate.ph5 }">
                    		<div class="lz-table-cell" id = "ph5" ><span onclick="auth('5')">Equelite</span></div>
                    	</c:if>
                        <c:if test="${not empty o.certificate.ph5 }">
                    		<div class="lz-table-cell" ><span class="lz-cur" >Equelite</span></div>
                    	</c:if>
                        <c:if test="${empty o.certificate.ph6 }">
                    		<div class="lz-table-cell" id = "ph6" ><span onclick="auth('6')">CTA</span></div>
                    	</c:if>
                        <c:if test="${not empty o.certificate.ph6 }">
                    		<div class="lz-table-cell"><span class="lz-cur" >CTA</span></div>
                    	</c:if>                    
                    </div>
                    <input type="hidden" value="ITF">
                </span>
            </li>
            <li>
                <span class="lz-left">擅长技能</span>
                <span class="lz-right"><input type="text" id="goodAt" placeholder="输入技能" value="${o.goodAt }"></span>
            </li>
        </ul>
        <!-- 执教经验 end -->
        <!-- 教练类型 start -->
<!--         <h2 class="lz-tit-4">教练类型</h2> -->
<!--         <ul class="lz-user-list" id="lz-coach-type"> -->
<!--         	onclick="lz.choiceCoachTYpe(this, '3')" -->
<!--             <li> -->
<!--                 <span class="lz-left"> -->
<!--                     <span class="lz-checked-2"> -->
<%--                     <i <c:if test="${o.coachType == 3 }"> class="lz-show" </c:if> ></i></span>球场运营者 --%>
<!--                 </span> -->
<!--                 <span class="lz-right"><em>自由管理和经营球场的个人或俱乐部，需认证审核</em></span> -->
<!--             </li> -->
<!--             <li> -->
<!--                 <span class="lz-left"> -->
<!--                     <span class="lz-checked-2"> -->
<%--                     <i <c:if test="${o.coachType == 2 }"> class="lz-show" </c:if> ></i></span>驻场教练 --%>
<!--                 </span> -->
<!--                 <span class="lz-right"><em>俱乐部在职教练，有固定的培训场地，需场馆指定</em></span> -->
<!--             </li> -->
<!--             <li> -->
<!--                 <span class="lz-left"> -->
<!--                     <span class="lz-checked-2"> -->
<%--                     <i <c:if test="${o.coachType == 1 }"> class="lz-show" </c:if> ></i></span>自由教练  --%>
<!--                 </span> -->
<!--                 <span class="lz-right"><em>无固定场地，提供上门服务</em></span> -->
<!--             </li> -->
            <input type="hidden" value="${o.coachType }" id="coachType">
<!--         </ul> -->
        <!-- 教练类型 end -->
        <!-- 身份认证 start -->
        <h2 class="lz-tit-4">身份验证</h2>
        <ul class="lz-user-list">
        	<c:if test="${o.coachType == 1 }">
        	<li>
	          <span class="lz-left">身份证正面</span>
	              <span class="lz-right" onclick="auth('7')" >
	               	<c:if test="${not empty o.certificate.ph7}">
	               		<img class="headimg" id="img7" width="60" height="60" src="/${o.certificate.ph7 }" >
	               	</c:if>
	               	<c:if test="${empty o.certificate.ph7}">
	               		<img class="headimg" id="img7" width="60" height="60" src="/image/morentouxiang.png" >
	               	</c:if>
	               </span>
	        </li>
	        <li>
	          <span class="lz-left">身份证背面</span>
	              <span class="lz-right" onclick="auth('8')" >
	               	<c:if test="${not empty o.certificate.ph8}">
	               		<img class="headimg" id="img8" width="60" height="60" src="/${o.certificate.ph8 }" >
	               	</c:if>
	               	<c:if test="${empty o.certificate.ph8}">
	               		<img class="headimg" id="img8" width="60" height="60" src="/image/morentouxiang.png" >
	               	</c:if>
	               </span>
	        </li>
	        <li>
	          <span class="lz-left">本人手持身份证照片</span>
	              <span class="lz-right" onclick="auth('9')" >
	               	<c:if test="${not empty o.certificate.ph9}">
	               		<img class="headimg" id="img9" width="60" height="60" src="/${o.certificate.ph9 }" >
	               	</c:if>
	               	<c:if test="${empty o.certificate.ph9}">
	               		<img class="headimg" id="img9" width="60" height="60" src="/image/morentouxiang.png" >
	               	</c:if>
	               </span>
	        </li>
	        </c:if>
            <li>
                <span class="lz-left">身份证号码/ID</span>
                <span class="lz-right"><input type="text" maxlength="18" placeholder="输入身份证号码"  id="idcard_no" value="${o.userid.idcard_no }"><span id="noerroe"></span></span>
            </li>
<!--             <li> -->
<!--                 <span class="lz-left">余额充值验证</span> -->
<!--                 <span class="lz-right"><input type="text" placeholder="输入验证码" value=""></span> -->
<!--             </li> -->

			<li>
                <span class="lz-left">开户银行</span>
                <span class="lz-right"><input type="text" placeholder="输入开户银行"  id="bank" value="${o.bank }"></span>
            </li>
			<li>
                <span class="lz-left">支行</span>
                <span class="lz-right"><input type="text" placeholder="输入支行" id="zbank" value="${o.zbank }"></span>
            </li>
            <li>
                <span class="lz-left">银行帐号</span>
                <span class="lz-right">
                	<input type="tel" id="bankZh" style="IME-MODE: disabled; " maxlength="20" 
                							onkeyup="this.value=this.value.replace(/\D/g,'')"  
                							onafterpaste="this.value=this.value.replace(/\D/g,'')"
										    name="bankZh"  placeholder="输入银行帐号"
										    class="col-xs-10 col-sm-5"  value="${o.bankZh }" />
                </span>
            </li>
            <li>
                <span class="lz-left">户名</span>
                <span class="lz-right"><input type="text" placeholder="输入户名" id="bankName" value="${o.bankName }"></span>
            </li>
        </ul>
        <!-- 身份认证 end -->
<!--         <input type="button" value="保存" onclick="saveVerified()"> -->
    </section>
    <!-- 用户个人信息展示 end -->
   	
</main>
    <!-- 学历选择 start -->
    <div id="lz-level" class="lz-level">
      <ul class="lz-list">																				
        <li onclick="lz.returnxueli(this, '大专及以下');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>大专及以下</strong></li>
        <li onclick="lz.returnxueli(this, '本科');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>本科</strong></li>
        <li onclick="lz.returnxueli(this, '研究生及以上');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>研究生及以上</strong></li>
      </ul>
    </div>
    <!-- 学历选择 end -->


	<!-- 专业选择 start -->
<!--     <div id="lz-professional" class="lz-level" > -->
<!--       <ul class="lz-list">																				 -->
<!--         <li onclick="lz.professional(this, '少儿(业余)');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>少儿(业余)</strong></li> -->
<!--         <li onclick="lz.professional(this, '成人(业余)');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>成人(业余)</strong></li> -->
<!--         <li onclick="lz.professional(this, '全部(业余)');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>全部(业余)</strong></li> -->
<!--         <li onclick="lz.professional(this, '职业');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>职业</strong></li> -->
<!--       </ul> -->
<!--     </div> -->
    <!-- 专业选择 end -->
</body>
</html>
