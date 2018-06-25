<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

/***
 * imgpaths 图片地址
 * auty_code  未知
 */
function onshowimg(imgpaths,auty_code){
	$("#show_imged").css({position:"relative",left:"0px",top:"0px",zIndex:99999999,width:"851px",height:"450px",backgroundColor:"wihte"});
	var imgarr = imgpaths.split(",");
	if(imgarr.length>0){
		$("#big_img").attr("src","../"+imgarr[0]);
		$("#big_img").attr({width:"850px",height:"450px"}).css({left:"0px",top:"0px"});
	}
	var html = "";
	$.each(imgarr,function(index,item){
		if(item){
			html +="<li>";
			html +="<img src='../"+item+"' onclick=\"show_img_path('"+item+"')\" />";
			html +="</li>";	
		}
	});
	var btn_pass = $("#btn_pass");
	btn_pass.attr("href",btn_pass.attr("href")+auty_code);
	var btn_bohui = $("#btn_bohui");
	btn_bohui.attr("href",btn_bohui.attr("href")+auty_code);
	$("#img_ul").html(html);
	index = $.layer({
	    type : 1,
	    shade : [0],
	    offset: ['10px',''],
	    area: ['850px', '620px'],//($(window).height() - 50) +
	    title : "<b>大图</b>",
	    shade: [0.5, '#000'],
	    shadeClose:true,
	    success: function(){
	    	layer.shift('top', 400);
	    },
	    border: [10, 0.7, '#666'],
	    page : {dom : '#showimg'}
	});
}
function show_img_path(path){
	$("#big_img").attr("src","../"+path);
	$("#big_img").attr({width:"850px",height:"450px"});
}
</script>
<div id="showimg" style="display: none;overflow: hidden;height:580px;">
		<div class="show_img" id="show_imged">
			<img src="" height="430px" width="850px" ondblclick="showimg(this);" id="big_img" style="cursor:pointer;" />
			<!-- style='height:450px;width:850px;' -->
			<!-- ondblclick="showimg(this);"  -->
		</div>
		<div>
			<ul id="img_ul" style="z-index: 999;position: relative;top:0px;left:0px;"></ul>
		</div>
		<div style="float: left; padding-top: 8px;">
		</div>
	</div>