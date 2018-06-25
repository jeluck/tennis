<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3" charset="UTF-8"></script>
</head>
<body onload="initMap(114.060938,22.548248)">
	<div style="width: 700px;height: 700px" id="map_content" onclick="showInfo()"></div>
	<script type="text/javascript">
	/* 初始化地图 */
	function initMap(x,y){
		map = new BMap.Map("map_content");
		map.enableScrollWheelZoom();///允许鼠标放大缩小
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());
		map.addControl(new BMap.OverviewMapControl());
		var pp = newPoint(x,y); // 创建点坐标
		map.centerAndZoom(pp, 15);
		// 百度地图API功能
		map.centerAndZoom(new BMap.Point(114.060938,22.548248), 11);
		
		map.addEventListener("click", showInfo);
	}
	function showInfo(e){
		alert(e.point.lng + ", " + e.point.lat);
	}
	
	/* 清空地图 */
	function clearMap(){
		map.clearOverlays();
	}
	
	/* 创建点 */
	function newPoint(x,y){
		return new BMap.Point(x,y);
	}
	
	
	</script>	
</body>
</html>