<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场馆管理者</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>

<link rel="stylesheet" type="text/css" href="/css/font/iconfont.css">
<script type="text/javascript" src="../js/events.js"></script>
<script type="text/javascript">
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#content', {
		themeType : 'simple',
		uploadJson : '../js/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : '../js/kindeditor/jsp/file_manager_json.jsp',
		allowFileManager : true
	});
});
</script>
<script charset="utf-8" src="http://map.qq.com/api/js?v=1"></script>
<script>
var searchService,map,markers = [];
var markersArray = [];
var init = function() {
	alert(1);
    var center = new soso.maps.LatLng('${o.latitude}','${o.longitude}');
    map = new soso.maps.Map(document.getElementById('map_canvas'),{
        center: center,
        zoomLevel: 13
    });
    var infoWin = new soso.maps.InfoWindow({
        map: map
    });
    infoWin.open('<div style="width:200px;padding-top:10px;">'+
//         '<img style="float:left;" src=""/> '+
        '地点</div>', 
        map.getCenter()
    );
    
    searchService = new soso.maps.SearchService();
    soso.maps.Event.addListener(map, 'click', function(event) {
		clearOverlays(markers);
        document.getElementById('latitude').value = event.latLng.getLat();
        document.getElementById('longitude').value = event.latLng.getLng();
    });
    soso.maps.Event.addListener(map,'mousemove',function(event) {
        var latLng = event.latLng,
            lat = latLng.getLat().toFixed(5),
            lng = latLng.getLng().toFixed(5);
        document.getElementById("latLng").innerHTML = lat+','+lng;
    });
//     soso.maps.Event.addDomListener(map, 'click', function(event) {
//         addMarker(event.latLng);
//     }); 
//     soso.maps.Event.addListener(map, 'click', function() {
//         info.setAnimation(soso.maps.Animation.POP);
//         info.open('<div style="width:200px;height:30px;">'
//             + '我是个可爱的孩子！</div>',
//             map
//         );
//     });
    var navControl = new soso.maps.NavigationControl({
        align: soso.maps.ALIGN.TOP_LEFT,
        margin: new soso.maps.Size(5, 15),
        map: map
    });
    var zoomControl = new soso.maps.ZoomHintControl({map:map});
    
}

// function addMarker(location) {
//     if (markersArray.length != 0) {
//     	var marker = new soso.maps.Marker({
//    	        position: null,
//    	        map: null
//    	    });
//         markersArray = [];
//         markersArray.length = 0;
//     }else{
//     	var marker = new soso.maps.Marker({
//    	        position: location,
//    	        map: map
//    	    });
//     	markersArray.push(marker);
//     }
   
// }


function deleteOverlays() {
    if (markersArray.length != 0) {
        markersArray = [];
        markersArray.length = 0;
    }
}

//清除地图上的marker
function clearOverlays(overlays){
    var overlay;
    while(overlay = overlays.pop()){
        overlay.setMap(null);
    }
}
function searchKeyword() {
    var keyword = document.getElementById("keyword").value;
    var region = document.getElementById("region").value;
    clearOverlays(markers);
    var searchRequest = {
        'keyword': keyword,
        'region':region
    };
    var latlngBounds = new soso.maps.LatLngBounds();
    searchService.search(searchRequest, function(results, status) {
        if (status == soso.maps.SearchStatus.OK) {
            var pois = results.pois;
            for(var i = 0,l = pois.length;i < l; i++){
                var poi = pois[i];
                latlngBounds.extend(poi.latLng);  
                var marker = new soso.maps.Marker({
                    map:map,
                    position: poi.latLng
                });

                var decor = new soso.maps.MarkerDecoration({
                    content: i+1,
                    margin: new soso.maps.Size(0, -4),
                    align: soso.maps.ALIGN.CENTER,
                    marker: marker
                });        
                markers.push(marker);
            }
            map.fitBounds(latlngBounds);
        } else {
            alert("检索没有结果，原因: " + status);
        }
    });
}

</script>
</head>
<body onload="init()">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">赛事管理</li>
			<li class="active">
			<c:if test="${empty check }">
				修改赛事
			</c:if>
			<c:if test="${not empty check }">
				查看赛事
			</c:if>
			</li>
		</ul>
	</div>
	<div class="page-header">
		<h1><c:if test="${empty check }">
				修改赛事
			</c:if>
			<c:if test="${not empty check }">
				查看赛事
			</c:if><span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<form id="QueryForm" name="form1" class="form-inline" action="edit_events.do"  
		  method="post" onsubmit="return checkForm()">
		  <input type="hidden" value="${o.id}" name="id" />
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="80%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td  >
								标题：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="title" style="width: 360px;" placeholder="请输入赛事标题"
										   class="col-xs-10 col-sm-5" required value="${o.title } " />
								</div>
							</td>
						</tr>
						<tr >
							<td  >
								宣传语：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="propaganda" style="width: 360px;" placeholder="请输入宣传语"
										   class="col-xs-10 col-sm-5" required value="${o.propaganda } " />
								</div>
							</td>
						</tr>
						<tr >
							<td  >
								举办商：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="conductor" style="width: 360px;" placeholder="请输入举办商"
										   class="col-xs-10 col-sm-5" required value="${o.conductor } " />
								</div>
							</td>
						</tr>
						<tr >
							<td >
								宣传图片：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-9">
									<input  type="file" class="col-xs-10 col-sm-5"  onchange="upload(this, 4);" />
									<input type="hidden" name="propagandaImg" value="${o.propagandaImg } ">
									<div class="col-md-6"><img alt="" src="/${o.propagandaImg }" width="100px" onclick="onshowimg('/${o.propagandaImg }',2)"></div>
								</div>
							</td>
						</tr>
						<tr >
							<td >
								子标题：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="ztitle" style="width: 360px;" placeholder="请输入子标题"
										   class="col-xs-10 col-sm-5" required  value="${o.ztitle } "/>
								</div>
							</td>
						</tr>
						<tr >
							<td >
								比赛地点：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="gameLocation" style="width: 360px;" placeholder="请输入比赛地点"
										   class="col-xs-10 col-sm-5" required  value="${o.gameLocation } "/>
								</div>
							</td>
						</tr>
						<tr >
							<td >
								报名费：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="price" style="width: 360px;" placeholder="请输入报名费"
										   class="col-xs-10 col-sm-5" value="${o.price }" required style="IME-MODE: disabled; width: 110px; height: 25px; line-height:0.38rem;" 
										 onchange="clert(this)" onkeyup="clearNoNum(this)"
                						 maxlength="10" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px" >
									有效期：
							</td>
							<td>
								<div class="col-sm-9" >
									<input type="text"
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" name="sign_time" value="${o.sign_time } " id="begin_time" placeholder="请输入开始比赛时间" />&nbsp;至&nbsp;
									<input
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" type="text" name="end_time" value="${o.end_time } " id="end_time" placeholder="请输入截止比赛时间"  />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px" >
									比赛时间：
							</td>
							<td>
								<div class="col-sm-9" >
									<input type="text"
										onfocus="WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
										style="width: 150px;" name="time" value="${o.time } " id="times" placeholder="请输入比赛时间" />
								</div>
							</td>
						</tr>
						<tr >
							<td >
								比赛时间说明：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="timeExplanation" style="width: 360px;" placeholder="请输入比赛时间说明"
										   class="col-xs-10 col-sm-5" required value="${o.timeExplanation } " />
								</div>
							</td>
						</tr>
						<tr >
							<td >
								报名时间说明：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="signTimeExplanation" style="width: 360px;" placeholder="请输入报名时间说明"
										   class="col-xs-10 col-sm-5" required value="${o.signTimeExplanation } " />
								</div>
							</td>
						</tr>
						
						<tr>
							<td >
								主办单位：
							</td>
							<td>
								<c:forEach items="${eList }" var="e">
									<c:if test="${e.flag == 'zhuban' }">
										<div class="row">
											<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>
											<div class="col-sm-3" >
												<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />
												<input type="hidden" name="zhubanImg" value="${e.img }" >
											</div>
											<div class="col-md-2"><img alt="" src="/${e.img }" width="100px" onclick="onshowimg('/${e.img }',2)"></div>
											<div class="col-sm-5">
												名称：<input type="text" name="zhubanInput" value="${e.context }" >
											</div>
										</div>	
									</c:if>
								</c:forEach>
								<button class="btn btn-sm btn-success"  style="margin-left: 15px;" type="button" onclick="add(this,'zhubanImg','zhubanInput','zhuban')">添加主办单位</button>
							</td>
						</tr>
						<tr>
							<td >
								承办单位：
							</td>
							<td>
								<c:forEach items="${eList }" var="e">
									<c:if test="${e.flag == 'chengban' }">
										<div class="row">
											<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>
											<div class="col-sm-3" >
												<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />
												<input type="hidden" name="chengbanImg"  value="${e.img }" >
											</div>
											<div class="col-md-2"><img alt="" src="/${e.img }" width="100px" onclick="onshowimg('/${e.img }',2)"></div>
											<div class="col-sm-5">
												名称：<input type="text" name="chengbanInput" value="${e.context }" >
											</div>
										</div>
									</c:if>
								</c:forEach>
								<button class="btn btn-sm btn-success"  style="margin-left: 15px;" type="button" onclick="add(this,'chengbanImg','chengbanInput','chengban')">添加承办单位</button>
							</td>
						</tr>
						<tr>
							<td >
								协办单位：
							</td>
							<td>
								<c:forEach items="${eList }" var="e">
									<c:if test="${e.flag == 'xieban' }">
										<div class="row">
											<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>
											<div class="col-sm-3" >
												<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />
												<input type="hidden" name="xiebanImg" value="${e.img }" >
											</div>
											<div class="col-md-2"><img alt="" src="/${e.img }" width="100px" onclick="onshowimg('/${e.img }',2)"></div>
											<div class="col-sm-5">
												名称：<input type="text" name="xiebanInput" value="${e.context }"  >
											</div>
										</div>
									</c:if>
								</c:forEach>
								<button class="btn btn-sm btn-success"  style="margin-left: 15px;" type="button" onclick="add(this,'xiebanImg','xiebanInput','xieban')">添加协办单位</button>
							</td>
						</tr>	
						<tr>
							<td >
								合作媒体：
							</td>
							<td >
								<c:forEach items="${eList }" var="e">
									<c:if test="${e.flag == 'hez' }">
										<div class="row">
											<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>
											<div class="col-sm-6" >
												<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />
												<input type="hidden" name="hezImg" value="${e.img }" >
											</div>
											<div class="col-md-4"><img alt="" src="/${e.img }" width="100px" onclick="onshowimg('/${e.img }',2)"></div>
										</div>
									</c:if>
								</c:forEach>
								<button class="btn btn-sm btn-success"  style="margin-left: 15px;" type="button" onclick="addBusiness(this,'hezImg','hez');">添加合作媒体</button>
							</td>
						</tr>
						<tr>
							<td >
								赞助商：
							</td>
							<td >
								<c:forEach items="${eList }" var="e">
									<c:if test="${e.flag == 'zanz' }">
										<div class="row">
											<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>
											<div class="col-sm-6" >
												<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />
												<input type="hidden" name="zanzImg" value="${e.img }" >
											</div>
											<div class="col-md-4"><img alt="" src="/${e.img }" width="100px" onclick="onshowimg('/${e.img }',2)"></div>
										</div>
									</c:if>
								</c:forEach>	
								<button class="btn btn-sm btn-success"  style="margin-left: 15px;" type="button" onclick="addBusiness(this,'zanzImg','zanz');">添加赞助商</button>
							</td>
						</tr>
						<tr >
							<td >
								地图：
							</td>
							<td >
								<div >
								   <input id="keyword" type="textbox" placeholder="请输入搜索关键词">
								   <input id="region" type="textbox" placeholder="请输入地点">
<!-- 								   <input type="button" value="" onclick="searchKeyword()"> -->
								   <button class="btn btn-sm btn-success" type="button" onclick="searchKeyword()"> [ 搜索 ]
									</button>
								</div>
								<div style="width:603px;height:300px" id="map_canvas"></div>
								<div style="width:603px;" id="latLng"></div>
								<div id="info" style="margin-top:10px;">
							</td>
						</tr>
						<tr >
							<td width="120px" >
								经纬度：<span style="color: red;">*</span>
							</td>
							<td >
								<div class="col-sm-8">
									<input type="text" id="longitude" name="longitude"  placeholder="点击地图经度" required value="${o.longitude } " />
									<input type="text" id="latitude" name="latitude" placeholder="点击地图纬度" required value="${o.latitude } " />			
								</div> 
							</td>
						</tr>
						<tr >
							<td >
								内容：
							</td>
							<td >
								<textarea name="context" id="content" 
									style="height: 350px; width:360px;" >${o.context } </textarea>
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>

			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">                 
					<c:if test="${empty check }">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
					</c:if>
					<button class="btn btn-sm btn-success" type="button" onclick="location='events_list.do'"> [ 返回 ]
					</button>
					<button class="btn btn-sm btn-success" type="button" onclick="cl()"> [ cc ]
					</button>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
<jsp:include page="../common_image_show.jsp"></jsp:include>


</body>

<script type="text/javascript">
function info(){
// 	var el = document.getElementById('map_canvas');
// // 	var div = el.getElementsByTagName('div')[0];
// // 	var divs = div.getElementsByTagName('div');
// 	console.info(el);
// // 	console.info(divs);
// // 	div.removeChild(divs[107]);
// // 	div.removeChild(divs[107]);
}

$(function(){
})
</script>
</html>
