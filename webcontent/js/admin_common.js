/**
 * @requires JQuery
 * @namespace util
 * @author daybreak
 * @version 1.0
 */
var util = $.extend({}, util);
/**
 * POST 同步 获取数据
 */
util.POST = function (url,data){
	//var loadi = layer.load('加载中…'); //需关闭加载层时，执行layer.close(loadi)即可
	var info = {} ;
	$.ajax({
		type:"POST",
		async:false,
		url:url,
		data:data,
		dataType:"json",
		success:function(data){
			info = data;
			//layer.close(loadi);
		}
	});
	return info;
};
/**
 * GET 同步 获取数据
 */
util.GET = function (url,data){
	var info = {} ;
/*	//需关闭加载层时，执行layer.close(loadi)即可
	var load_index = layer.load('加载中…');
	$("#xubox_layer"+load_index).show(function(){
		
	}); */
	$.ajax({
		type:"GET",
		url:url,
		async:false,
		data:data,
		dataType:"json",
		success:function(data){
			info = data;
			//layer.close(load_index);
		}
	});
	return info;
};

/**
 * @author 微笑风采
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
util.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
/**
 * 将JSON对象转换为字符串
 */
util.Json2Str = function(jsonObj){
	var i = 0;
	var convStr = "";
	for(var key in jsonObj){
		if(i++==0){
			if (jsonObj[key] == undefined) {
				convStr +=  key + "=" + "";
			} else {
				convStr +=  key + "=" + jsonObj[key];
			}
		} else {
			if (jsonObj[key] == undefined) {
				convStr += "&"  + key + "=" + "";
			} else {
				convStr += "&" + key + "=" + jsonObj[key];
			}
		}
	}
	return convStr;
};
/**
 * 省市级联 - 待美化
 * 
 */
util.cityChoose = function(privinceId,cityId){
	var data = util.GET("choose_city.do",{"provinceid":$(privinceId).val()});
	var optionhtml = "";
	$.each(data,function(i,item){
		optionhtml +='<option value="'+item.cityid+'">'+item.cityname+'</option>';
	});
	$(cityId).html(optionhtml);
};
/**
 * 初始化选中的值
 */
util.initChooseCity =function(cityId,provinceVal,cityVal){
	if(!$.trim(provinceVal)){return;}
	var data = util.GET("choose_city.do",{"provinceid":provinceVal});
	var optionhtml = "";
	$.each(data,function(i,item){
		if(item.cityid==cityVal){
			optionhtml +='<option value="'+item.cityid+'" selected="selected">'+item.cityname+'</option>';
		}else{
			optionhtml +='<option value="'+item.cityid+'">'+item.cityname+'</option>';
		}
		
	});
	$(cityId).html(optionhtml);
};
/**
 * 改变验证码
 * @param obj
 */
function changeimg(obj){
  	obj.src='valicode.do?action=valicode&time='+new Date().getTime();
}
/**
 * 过滤HTML标签
 */
util.filterHTMLTag = function (str) {
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
    return str;
};

//扩展Array方法  ==== 是否包含某个元素
Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i--) {
		if (this[i] === obj) {
			return true;
		}
	}
	return false;
};
var newarr = [];
//扩展Array方法  ==== 根据元素值移除value
Array.prototype.remove = function(value) {
	newarr = [];
	$.each(this,function(index,item){
		if(value==item){
			return true;
		}
		newarr.push(item);
	});
	return newarr;
};
/**
 * 构造分页代码
 * @param _page
 * @returns {String}
 */
util.init_pagination = function (_page){
		var paginhtml = '<ul class="pagination pull-right no-margin">';
		paginhtml += '<li>';
		paginhtml += '<a href="javascript:void(0);">第'+_page.currentPage+'页/共'+_page.pageCount+'页</a>';
		paginhtml += '</li>';
		paginhtml += '<li>';
		//首页构成
		paginhtml += '<a href="javascript:go_page(1);">首页</a>';
		paginhtml += '</li>';
		//上一页组成
		paginhtml += '<li class="prev ';
		if(_page.currentPage==1){
			paginhtml += ' disabled';
		}
		paginhtml +='">';
		if(_page.currentPage-1>0){
			paginhtml += '<a href="javascript:go_page('+(_page.currentPage-1)+');"><<&nbsp;&nbsp;上一页</a>';
		}
		if(_page.currentPage-1==0){
			paginhtml += '<a href="javascript:void(0);"><<&nbsp;&nbsp;上一页</a>';
		}
		paginhtml += '</li>';
		//页码组成
		for(var i=_page.startPage;i<=_page.endPage;i++){
			paginhtml += '<li ';
			if(_page.currentPage==i){
				paginhtml += 'class="active"';
			}else{
				paginhtml += 'class="prev"';
			}
			paginhtml += '><a href="javascript:go_page('+i+');">'+i+'</a></li>';
		}
		//下一页组成
		paginhtml += '<li class="next ';
		if(_page.currentPage==_page.pageCount){
			paginhtml += ' disabled';
		}
		paginhtml +='">';
		if(_page.currentPage+1<=_page.pageCount){
			paginhtml += '<a href="javascript:go_page('+(_page.currentPage+1)+');">下一页&nbsp;&nbsp;>></a>';
		}
		if(_page.currentPage==_page.pageCount){
			paginhtml += '<a href="javascript:void(0);">下一页&nbsp;&nbsp;>></a>';
		}
		paginhtml += '</li>';
		//尾页构成
		paginhtml += '<li><a href="javascript:go_page('+_page.pageCount+');">尾页</a></li>';
		paginhtml += '<li>&nbsp;&nbsp;到第<input type="text" class="easyui-numberbox" value="'+_page.currentPage+'" data-options="min:1,max:'+_page.pageCount+'" id="pagenum" style="width:55px;" />页&nbsp;&nbsp;<input class="btn btn-xs btn-info" type="button" onclick="go_page('+$('#pagenum').val()+');" value="确定"/></li>';
		paginhtml += '</ul>';
		return paginhtml;
};


/**
 * 保留多少位小数点
 * @param v    实际数
 * @param k    保留位数
 * @returns
 */
function reserve(v,k){
     v=parseFloat(v);
     return !isNaN(v)?v.toFixed(k):'0'.toFixed(k);
}

//提交后,按钮状态改变
function hiddenSubmit(element){
	if(element.statusPost){
		$('#'+element.id).attr("disabled","disabled");
		return false;
	}
	if (!element.statusPost) {
		element.innerHTML  = '提交中。。。';
		element.statusPost = true;
	} 
}

//提交后,按钮状态改变
function hiddenButton(element){
	var f = document.forms[0];
	
	f.action = 'edit_space_time_price.do';
	
	if(element.statusPost){
		$('#'+element.id).attr("disabled","disabled");
		return false;
	}
	
	if (!element.statusPost) {
		element.innerHTML  = '提交中。。。';
		element.statusPost = true;
	} 
	
	f.submit();
}