/**
 * @requires JQuery
 * @namespace util
 * @author daybreak
 * @version 1.0
 */
/**
 * 根据分类一级获得二级分类
 * @param request
 * @param oid
 * @return
 */
function getTwocategoryByOneId(id){
	var categoryselect = $("#categoryselect").val();
	var info = util.POST("/getTwocategoryByOneId.do",{"oid":categoryselect});
    var str="<select name='category' class='col-xs-5 col-sm-6' onchange='backOnelevelCategory(this.value);'>";
    var temp = 0; 
	if(info.status==0){
    	$.each(info.data,function(n,value) {
    		temp=1;
    		str+="<option value='"+value.id+"'>"+value.category_name+"</option>";
       }); 
    	str+="<option value='0'>返回上级</option>";
    	str+="</select>";
    	$("#categoryDiv").html(str);
    	if(temp==0){
    		alert("无下级分类,请先添加");
    		getAllOnelevelCategory();
    	}
    }else{
   	 	alert(info.msg);
    }
}

/**
 * 在第二级选择返回上级时调用.
 * @param val
 */
function backOnelevelCategory(val){
	if(val == 0){
		getAllOnelevelCategory();
	}
}

/**
 * 获得所有一级分类
 * @param request
 * @param oid
 * @return
 */
function getAllOnelevelCategory(){
	var info = util.POST("/getAllOnelevelCategory.do");
    var str="<select name='category' class='col-xs-5 col-sm-6' id='categoryselect' onchange='getTwocategoryByOneId()'>";
    str+="<option value='0'>请选择</option>";
	if(info.status==0){
    	$.each(info.data,function(n,value) {  
    		str+="<option value='"+value.id+"'>"+value.category_name+"</option>";
       }); 
    	str+="</select>";
    	$("#categoryDiv").html(str);
    }else{
   	 	alert(info.msg);
    }
}

/***
 * 默认根据第一个价格设置佣金比例
 * @param id
 * @param commission_per1
 * @param commission_per2
 * @param commission_per3
 */
function setcommission(id,commission_per1,commission_per2,commission_per3){
	var price = $("#"+id).val();
	var commission1 = Number(price)*Number(commission_per1);
	var commission2 = Number(price)*Number(commission_per2);
	var commission3 = Number(price)*Number(commission_per3);
	
	$("#commission_1").val(reserve(commission1,2));
	$("#commission_2").val(reserve(commission2,2));
	$("#commission_3").val(reserve(commission3,2));
	
}