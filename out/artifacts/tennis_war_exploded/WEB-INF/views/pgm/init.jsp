<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- basic styles -->
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<!-- self_defind styles -->
<link rel="stylesheet" href="../assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="../assets/css/google.css" />
<link rel="stylesheet" href="../assets/css/ace.min.css" />
<link rel="stylesheet" type="text/css" href="../js/layer/skin/layer.css" />
<link rel="stylesheet" type="text/css" href="../css/admin.css" />

<!-- basic scripts -->
<script src='../assets/js/jquery-2.0.3.min.js'></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/layer/layer.min.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-table/bootstrap-table.js"></script>
<script src="../js/admin_common.js"></script>

<!-- ace scripts -->
<script src="../assets/js/ace-extra.min.js"></script>
<script src="../assets/js/ace.min.js"></script>

<!-- page_common scripts -->
<script src="../js/admin/page_common.js"></script>

<!-- KindEditor 编辑器 -->
<link rel="stylesheet" href="../js/kindeditor/themes/simple/simple.css" />
<script charset="utf-8" src="../js/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="../js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	if($(document).height()>=$(window).height()){
		$("#List_Iframe").height($(document).height() - 110);
	}else{
		$("#List_Iframe").height($(window).height() - 235);
	}
});
</script>