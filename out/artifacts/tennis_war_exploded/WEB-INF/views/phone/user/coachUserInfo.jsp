<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
  <link rel="stylesheet" type="text/css" href="/css/common.css?${getVersion}">
  <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
  <script src="/js/admin_common.js?${getVersion}"></script>
  <script src="/js/common.js?${getVersion}"></script>
  <!--本页面单独使用文件 start-->
  <link rel="stylesheet" href="/css/user.css?${getVersion}">
  <link rel="stylesheet" href="/css/ChoiceCity.css?c=<%=new Date()%>">
  <script src="/js/user.js?c=<%=new Date()%>"></script>
  <script src="/js/ChoiceCity.js?c=<%=new Date()%>"></script>
  <!--本页面单独使用文件 end-->
  <%@ include file="./common/mobiscroll.jsp" %>
<%@ include file="./common/common_init.jsp" %>
</head>

<body class="lz-body">
  <main class="lz-view">
    <!-- 用户个人信息展示 start -->
    <section class="lz-usre">
      <input type="hidden" id="userId" value="${o.userid.id }">
      <h2 class="lz-tit-4">个人设置-设置信息，方便交友，经营自己的品牌。</h2>
      <ul class="lz-user-list">
        <li>
          <span class="lz-left">头像</span>
              <span class="lz-right" onclick="head_photo(2,this,${o.userid.id })" >
               	<c:if test="${not empty o.head_portrait}">
               		<img class="headimg" id="img" width="60" height="60" src="/${o.head_portrait }" >
               	</c:if>
               	<c:if test="${empty o.head_portrait}">
               		<img class="headimg" id="img" width="60" height="60" src="/image/morentouxiang.png" >
               	</c:if>
               </span>
        </li>
      </ul>
      <ul class="lz-user-list">
        <li>
          <span class="lz-left">昵称</span>
          <span class="lz-right"><input type="text" id="niname" placeholder="输入昵称" value="${o.userid.username}"></span>
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
                            </span> 男&nbsp;&nbsp;&nbsp;&nbsp;
          <span class="lz-checked-2" onclick="lz.choiceSex(this, '女')">
                                <c:if test="${o.sex == '男' }">
                                    <i></i>
                                </c:if>
                                <c:if test="${o.sex == '女' }">
                                    <i class="lz-show"></i>
                                </c:if>
                            </span> 女
          </span>
          <input id="sex" type="hidden" value="${o.sex}">
        </li>
        <li>
          <span class="lz-left">生日</span>
          <span class="lz-right"><input data-type="date" type="text" id="birthday" placeholder="选择生日"  value="${o.userid.birthday }" readonly></span>
        </li>
        <li>
          <span class="lz-left">签名</span>
          <span class="lz-right"><input type="text" id="personalitySignature" placeholder="输入签名" value="${o.personalitySignature }"></span>
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
        </span>
      </ul>
<!--       <ul class="lz-user-list"> -->
<!--         <li> -->
<!--           <span class="lz-left">实名认证</span> -->
<%--           <c:if test="${o.verified == 0 }"> --%>
<%--             <span class="lz-right" onclick="window.location.href = 'toverified.do?userId=${o.userid.id}'" style="color: #2E7AD9;">未认证<i class="lz-iconfont lz-icon-jiantouright"></i></span> --%>
<%--           </c:if> --%>
<%--           <c:if test="${o.verified == 1 }"> --%>
<!--             <span class="lz-right">已认证<i class="lz-iconfont lz-icon-jiantouright"></i></span> -->
<%--           </c:if> --%>
<%--           <c:if test="${o.verified == 2 }"> --%>
<!--             <span class="lz-right">认证中<i class="lz-iconfont lz-icon-jiantouright"></i></span> -->
<%--           </c:if> --%>
<!--         </li> -->
<!--       </ul> -->
      <h2 class="lz-tit-4">详细资料-发现球友，找到组织，拓展人脉圈子</h2>
      <ul class="lz-user-list">
        <li>
          <span class="lz-left">姓名</span>
          <span class="lz-right"><input type="text" id="name" placeholder="输入姓名" value="${o.name }"></span>
        </li>
        <li>
          <span class="lz-left">身高</span>
          <span class="lz-right">
						
                        <input type="text" id="height" placeholder="输入身高CM" <c:if test="${o.height > 0 }"> value="${o.height }" </c:if> ></span>
        </li>
        <li>
          <span class="lz-left">体重</span>
          <span class="lz-right"><input type="text" id="weight" placeholder="输入体重KG" <c:if test="${o.weight > 0 }"> value="${o.weight }" </c:if> ></span>
        </li>
        <li onclick="lz.selectLevel(this,'lz-gouji');">
          <span class="lz-left">国籍</span>
          <span class="lz-right">
		                 <input type="hidden" id="gouji" value="${o.nationality }">
		                 <span id="lz-goujiValue">${o.nationality }</span><i class="lz-iconfont lz-icon-jiantouright"></i>
          </span>
        </li>
        <li id="suoZaiChengShi2">
          <span class="lz-left">籍贯</span>
          <span class="lz-right">
			              <input type="hidden" id="hometown"  value="${o.userid.hometown }">
			              	<span id="gj">${jname }</span><i class="lz-iconfont lz-icon-jiantouright"></i></span>
        </li>
        <li id="suoZaiChengShi">
          <span class="lz-left">所在城市</span>
          <span class="lz-right">
			              <input type="hidden" id="city"  value="${o.now_live_city }">
			                <input type="hidden" id="area"  value="${o.areaid }">
			              	<span id="ct">${rname }</span><i class="lz-iconfont lz-icon-jiantouright"></i></span>
        </li>
        <li>
          <span class="lz-left">所在地址</span>
          <span class="lz-right"><input type="text" id="address" placeholder="输入所在地址" value="${o.userid.address }"></span>
        </li>
        <li>
          <span class="lz-left">工作单位</span>
          <span class="lz-right"><input type="text" id="employer" placeholder="输入工作单位" value="${o.userid.employer }"></span>
          <input type="hidden" id="userPhone" value="${o.userid.uphone }">
        </li>

<!--                             <li> -->
<!--                                 <input type="button" onclick="saveCoach()" value="保存"> -->
<!--                             </li> -->

      </ul>
    </section>
    <!-- 用户个人信息展示 end -->
  </main>
  <!-- 国籍选择 start -->
  <div id="lz-gouji" class="lz-level">
    <ul class="lz-list">
      <li onclick="lz.returngouji(this, '中教');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>中教</strong></li>
      <li onclick="lz.returngouji(this, '外教');"><i class="lz-iconfont lz-icon-xunzhang"></i><strong>外教</strong></li>
    </ul>
  </div>
  <!-- 国籍选择 end -->

  <script>
    var choice = new ChoiceCity({
      provinceUrl: '/province.do', //省份url
      cityUrl: '/city.do', //城市url
      areaUrl: 'area.do' //区url
    });
    var choice1 = new ChoiceCity({
        provinceUrl: '/province.do', //省份url
        cityUrl: '/city.do',         //城市url
       	boxId: 'choicecity-1'
    });
    var oSuoZaiChengShi = document.getElementById('suoZaiChengShi'); //所在城市
    oSuoZaiChengShi.addEventListener('click', function() {
      var provincId = 1; //初始化省份id
      var cityId = 2; //初始化城市id
      choice.open(provincId, cityId, function(provinc, city,area) { //第一个参数是省份，第二个参数是城市
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
    oSuoZaiChengShi2.addEventListener('click', function() {
      var provincId = 1; //初始化省份id
      var cityId = 2; //初始化城市id
      choice1.open(provincId, cityId, function(provinc, city) { //第一个参数是省份，第二个参数是城市
        var pname = provinc.dataset.region_name;
        var pid = provinc.dataset.region_id;
        var cname = city.dataset.region_name;
        var cid = city.dataset.region_id;
        $("#hometown").val(cid);
        $("#gj").html(pname + " " + cname);
      });
    }, false);

  </script>
</body>

</html>
