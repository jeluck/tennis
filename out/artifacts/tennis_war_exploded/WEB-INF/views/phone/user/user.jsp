<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="lz-html" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>个人中心</title>
<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css?${getVersion}">
<link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js?${getVersion}"></script>
<script src="/js/admin_common.js?${getVersion}"></script>
<script src="/js/common.js?${getVersion}"></script>
<!--本页面单独使用文件 start-->
<link rel="stylesheet" href="/css/user.css?${getVersion}">
<link rel="stylesheet" href="/css/ChoiceCity.css?${getVersion}">
<script src="/js/user.js?${getVersion}"></script>
<script src="/js/ChoiceCity.js?${getVersion}"></script>
<!--本页面单独使用文件 end-->
<%@ include file="./common/mobiscroll.jsp" %>
<%@ include file="./common/common_init.jsp" %>
</head>

<body class="lz-body">
<main class="lz-view">	
    <!-- 用户个人信息展示 start -->
    <input type="hidden" value="${o.id }" id = "id">
    <section class="lz-usre">
        <h2 class="lz-tit-4">个人设置-设置信息，方便交友，经营自己的品牌。</h2>
        <ul class="lz-user-list">
            <li>
                <span class="lz-left">头像</span>
                <span class="lz-right" onclick="head_photo(1,this,${o.id})">
                	<c:if test="${not empty o.head_photo}">
                   		<img class="headimg" id="img" width="60" height="60" src="/${o.head_photo }" >
                   	</c:if>
                   	<c:if test="${empty o.head_photo}">
                   		<img class="headimg" id="img" width="60" height="60" src="/image/morentouxiang.png" >
                   	</c:if>
                </span>
            </li>
        </ul>
        <ul class="lz-user-list">
            <li>
                <span class="lz-left">昵称</span>
                <span class="lz-right"><input type="text" id="username" value="${o.username}"></span>
            </li>
             <li>
                <span class="lz-left">性别</span>
                <span class="lz-right">
                  <span class="lz-checked-2" onclick="lz.choiceSex(this, '男')">
                       <c:if test="${o.sex == '女' }">
		                  <i></i>
		               </c:if>
		               <c:if test="${o.sex == '男' }">  
		                  <i class="lz-show"></i>
	                   </c:if>
                  </span>
                  男&nbsp;&nbsp;&nbsp;&nbsp;
                  <span class="lz-checked-2" onclick="lz.choiceSex(this, '女')">
                    <i></i>
                    <c:if test="${o.sex == '男' }">
	                    <i></i>
	                 </c:if>
	                 <c:if test="${o.sex == '女' }">   
	                    <i class="lz-show"></i>
                     </c:if>
                  </span>
                  女
                </span>
                <input id="sex" type="hidden" value="${o.sex}">
            </li>
            <li>	
                <span class="lz-left">生日</span>
                <span class="lz-right"><input data-type="date" type="text" id="birthday" placeholder="选择生日"  value="${o.birthday }" readonly /> </span>
            </li>
            <li>
                <span class="lz-left">签名</span>
                <span class="lz-right"><input type="text" id="signature" placeholder="输入签名"  value="${o.signature }" /></span>
            </li>
            
            <li>
                <span class="lz-left">邀请人</span>
                <span class="lz-right">
                	<input type="tel" id="invite_id" maxlength="11" 
                		<c:if test="${not empty invite}"> readonly="readonly" </c:if>  
                			style="IME-MODE: disabled; width: 1.2rem; " onkeyup="this.value=this.value.replace(/\D/g,'')"  
                			onafterpaste="this.value=this.value.replace(/\D/g,'')"
                		placeholder="输入邀请人手机号" onblur="checkfindPhone()"  value="${invite.user_id.uphone }" />
                </span>
            </li>
        </ul>
        <ul class="lz-user-list">
            <li>
                <span class="lz-left">身份认证</span>
                <c:if test="${o.idcard_status == 0}">
	                <span class="lz-right" style="color: #2E7AD9;" onclick="window.location.href='auth_tion.do?userId=${o.id}'">
	                	未认证<i class="lz-iconfont lz-icon-jiantouright"></i>
	                </span>
                </c:if>
                <c:if test="${o.idcard_status == 1}">
               	   	<span class="lz-right">
               	   	 	已认证<i class="lz-iconfont lz-icon-jiantouright"></i>
               	   	</span>
                </c:if>
            </li>
            
        </ul>
        <h2 class="lz-tit-4">网球水平-私人定制专属教程，培训课程，约战</h2>
        <ul class="lz-user-list">
          <li onclick="lz.selectLevel(this);">
            <span class="lz-left">网球级别<em>（NTRP标准）</em></span>
            <span class="lz-right">
            	
            	<span id="lz-levelValue">${o.tennis_level }</span>
            	<input type="hidden" id="level"  value="${o.tennis_level}">
            	<i class="lz-iconfont lz-icon-jiantouright"></i>
            </span>
            
          </li>
        </ul>
        <h2 class="lz-tit-4">详细资料-发现球友，找到组织，拓展人脉圈子</h2>
        <ul class="lz-user-list">
          <li id="suoZaiChengShi">
              <span class="lz-left">所在城市</span>
              <span class="lz-right">
              <input type="hidden" id="city"  value="${o.cityid }">
               <input type="hidden" id="area"  value="${o.areaid }">
              	<span id="ct">${rname }</span><i class="lz-iconfont lz-icon-jiantouright"></i></span>
          </li>
          <li id="suoZaiChengShi2">
              <span class="lz-left">籍贯</span>
              <span class="lz-right">
              <input type="hidden" id="hometown" value="${o.hometown }">
              	<span id="gj">${jname }</span><i class="lz-iconfont lz-icon-jiantouright"></i></span>
          </li>
          <li>
            <span class="lz-left">毕业院校</span>
            <span class="lz-right"><input type="text" id="school" value="${o.school }"></span>
          </li>
          <li>
            <span class="lz-left">工作单位</span>
            <span class="lz-right"><input type="text" id="employer" value="${o.employer }"></span>
          </li>
           <li>
            <span class="lz-left">个人住址</span>
            <span class="lz-right"><input type="text" id="address" value="${o.address }"></span>
            <input type="hidden" id="userPhone" value="${o.uphone }">
          </li>
          
<!--           <li> -->
<!--           	<input type="button" onclick="saveUser()" value="保存"> -->
<!--           </li> -->
        </ul>
    </section>
    <!-- 用户个人信息展示 end -->
    <!-- 网球级别选择 start -->
    <div id="lz-level" class="lz-level">
        <ul class="lz-level-box">
            <c:forEach items="${level }" var="l">
                <li class="lz-level-list">
                    <a class="lz-level-nav" href="#lz-level-box-${l.id}">${l.level}
                        <i class="lz-iconfont lz-icon-jiantou"></i>
                    </a>
                    <div class="lz-level-about" id="lz-level-box-${l.id}">${l.name}
                        <div class="lz-level-btn" onclick="lz.returnLevel(this, '${l.level}');">确定</div>
                    </div>
                </li>
            </c:forEach>
        </ul>
<!--
      <ul class="lz-list">
      	<c:forEach items="${level }" var="l">
      		 <li onclick="lz.returnLevel(this, '${l.level}');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>${l.level}</strong><span>${l.name}</span></li>
      	</c:forEach>
      </ul>
-->
    </div>
    <!-- 网球级别选择 end -->
</main>
		<script>
            
            
            var choice = new ChoiceCity({
                provinceUrl: '/province.do', //省份url
                cityUrl: '/city.do',         //城市url
                areaUrl: 'area.do' //区url
            });
            
            var choice1 = new ChoiceCity({
                provinceUrl: '/province.do', //省份url
                cityUrl: '/city.do',         //城市url
               	boxId: 'choicecity-1'
            });
            var oSuoZaiChengShi = document.getElementById('suoZaiChengShi'); //所在城市
            oSuoZaiChengShi.addEventListener('click', function () {
                var provincId = 1; //初始化省份id
                var cityId = 2;   //初始化城市id
                choice.open(provincId, cityId, function (provinc, city, area) {  //第一个参数是省份，第二个参数是城市，第三个参数是市的id
               		var pname = provinc.dataset.region_name;
               		var pid = provinc.dataset.region_id;
               		var cname = city.dataset.region_name;
               		var cid = city.dataset.region_id;
               		var qid = area.dataset.region_id;
               		$("#city").val(cid);
               		$("#area").val(qid);
               		$("#ct").html(pname+" "+cname + ' ' + area.dataset.region_name);
                });
            }, false);
            
            var oSuoZaiChengShi2 = document.getElementById('suoZaiChengShi2'); //籍贯
            oSuoZaiChengShi2.addEventListener('click', function () {
                var provincId = 1; //初始化省份id
                var cityId = 2;   //初始化城市id
                choice1.open(provincId, cityId, function (provinc, city) {  //第一个参数是省份，第二个参数是城市，第三个参数是市的id
               		var pname = provinc.dataset.region_name;
               		var pid = provinc.dataset.region_id;
               		var cname = city.dataset.region_name;
               		var cid = city.dataset.region_id;
               		$("#hometown").val(cid);
               		$("#gj").html(pname+" "+cname );
                });
            }, false);
        </script>
</body>
</html>
