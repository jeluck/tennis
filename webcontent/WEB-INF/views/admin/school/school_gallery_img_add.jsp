<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>场馆批量添加图片</title>
<link rel="stylesheet" type="text/css" href="../js/codebase/GooUploader.css"/>
<script type="text/javascript" src="../js/codebase/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/codebase/GooUploader.js"></script>
<script type="text/javascript" src="../js/codebase/swfupload/swfupload.js"></script>
<!-- basic styles -->
<script type="text/javascript">  
var demo;
//构造函数 参数
var property={  
    width:700,  
    height:300,  
    multiple:true,
    file_post_name : "Filedata", 
    use_query_string: true,  
    file_types:"*.jpg;*.gif;*.png", 
  //  file_types_description: "Web Image Files",  
    post_params:{"schoolId":"<%=request.getAttribute("schoolId")%>"}, 
    btn_add_text:"添加",  
    btn_up_text:"上传",   
    btn_cancel_text:"放弃", 
    btn_clean_text:"清空",   
    op_del_text:"单项删除",  
    op_up_text:"单项上传",  
    op_fail_text:"上传失败",  
    op_ok_text:"上传成功",  
    op_no_text:"取消上传",   
    upload_url:"schooladdimg.do",  
    flash_url :"../js/codebase/swfupload.swf"
};  

$(document).ready(function(){ 
  demo=$.createGooUploader($("#demo"),property);
});  
</script>
</head>
<body>
	<form id="QueryForm" name="form1" class="form-inline" action="editSchoolGallery.do"  
		  method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td  width="40%" >
					<table   align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td>
								<div id="demo" ></div> 
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<a class="btn btn-sm btn-success" onclick="location.href = 'school_gallery_list.do';">[ 返回 ]</a></span>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>