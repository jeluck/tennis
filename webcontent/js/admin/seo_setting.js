function buildIndexHtml(){
	var params = util.Json2Str(util.serializeObject($("#option_form")));
	var info = util.POST("buildIndexHtml.do",params);
	parent.ShowMsg("操作提示：",info.info);
}