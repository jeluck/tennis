/**
 * 小功能脚本
 */

function send_type_f(obj){
	var form=document.forms[0];
	var val =obj.value; 
	var edit_id = 0;
	try{
		edit_id = $("#edit_id").val();
	}catch (e) {
		
	}
	
	if(val==1){
		$('#user_data_tr').css('display','');
		$('#user_data_span').css('display','');
		$('#user_data_span').html('号码之间以\",\"整形');
		$('#user_data').val('');
		var j=0;
		 for(var i=0;i<form.elements.length;i++)//循环表单内的元素数组的最大项
		    {
			 
				 if(form.elements[i].type=="file")//如果当前元素的类型是text
			        {
						 if(j==0){
				            form.elements[i].type="text";//那就把他的值赋成 田洪川
				            form.elements[i].name="user_data";
				            j++;
						 }   
			        }
		    }
		if(typeof(edit_id)== "undefined"){
			form.action="addterraceMessage.do";
			form.enctype="";
		}else{
			form.action="edit_terraceMessage.do";
			form.enctype="";
		}
		 
	}else if(val==2){
		$('#user_data_tr').css('display','');
		$('#user_data_span').css('display','');
		$('#user_data_span').html('n以下或者n-m区间(只填数字)');
		$('#user_data').val('');
		
		var j=0;
		 for(var i=0;i<form.elements.length;i++)//循环表单内的元素数组的最大项
		    {
			 
				 if(form.elements[i].type=="file")//如果当前元素的类型是text
			        {
						 if(j==0){
				            form.elements[i].type="text";//那就把他的值赋成 田洪川
				            form.elements[i].name="user_data";
				            j++;
						 }   
			        }
		    }
		 if(typeof(edit_id)== "undefined"){
				form.action="addterraceMessage.do";
				form.enctype="";
			}else{
				form.action="edit_terraceMessage.do";
				form.enctype="";
			}
	}else if(val==6){
		var j=0;
		 for(var i=0;i<form.elements.length;i++)//循环表单内的元素数组的最大项
		    {
			 	if(form.elements[i].type=="file"){
			 		return;
			 	}
			 
				 if(form.elements[i].type=="text")//如果当前元素的类型是text
			        {
						 if(j==0){
				            form.elements[i].type="file";//那就把他的值赋成 田洪川
				            form.elements[i].name="file";
				            j++;
						 }   
			        }
		    }
		
		if(typeof(edit_id)== "undefined"){
			form.action="fileaddterraceMessage.do";
			form.enctype="multipart/form-data";
		}else{
			form.action="dedit_terraceMessage.do";
			form.enctype="multipart/form-data";
		}
		$('#mes_cloud_type').val(2);//如果 是导入电话,则需要把消息改为短信
		$('#user_data_tr').css('display','');
		$('#user_data').val('');
	}else{
		$('#user_data_tr').css('display','none');
		$('#user_data_span').css('display','none');
		$('#user_data_span').html('');
		$('#user_data').val('');
		if(typeof(edit_id)== "undefined"){
			form.action="addterraceMessage.do";
			form.enctype="";
		}else{
			form.action="edit_terraceMessage.do";
			form.enctype="";
		}
	}
}

/**
 * 场馆详情页拨打电话
 * @param telphone
 */
function p_detail(telphone){
	try{
		window.pay.call(telphone);
	}catch (e) {
		cloud.explain(e);
	}
	
}

/**
 * 推荐教练
 * @param id
 */
function pushcoach(id){
	var pushobj_stick=$("#pushobj_stick_"+id).val();
	if(pushobj_stick==0){
		pushobj_stick=1;
	}else{
		pushobj_stick=0;
	}
	var info = util.POST("pushcoach.do",{"id":id,"stick":pushobj_stick});
	if(info.data==1){
		$("#pushobj_"+id).html("取消推荐");
		$("#pushobj_stick_"+id).val(1);
	}else{
		$("#pushobj_"+id).html("推荐");
		$("#pushobj_stick_"+id).val(0);
	}
}

//预定场地
function is_reservecoach(id,obj){
	var info = util.POST("is_reservecoach.do",{"id":id});
	var text = obj.parentNode.getElementsByTagName('i')[2].innerHTML;
	if(text == "不可预定场地"){
		obj.parentNode.getElementsByTagName('i')[2].innerHTML = "可预定场地";
	}else{
		obj.parentNode.getElementsByTagName('i')[2].innerHTML = "不可预定场地";
	}
}

//用户预定我
function reserve_mecoach(id,obj){
	var info = util.POST("reserve_mecoach.do",{"id":id});
	var text = obj.parentNode.getElementsByTagName('i')[3].innerHTML;
	if(text == "不可预约我"){
		obj.parentNode.getElementsByTagName('i')[3].innerHTML = "可预约我";
	}else{
		obj.parentNode.getElementsByTagName('i')[3].innerHTML = "不可预约我";
	}
}

/**
 * 推荐场馆
 * @param id
 */
function pushplayground(id){
	var pushobj_stick=$("#pushobj_stick_"+id).val();
	if(pushobj_stick==0){
		pushobj_stick=1;
	}else{
		pushobj_stick=0;
	}
	var info = util.POST("pushplayground.do",{"id":id,"stick":pushobj_stick});
	if(info.data==1){
		$("#pushobj_"+id).html("取消推荐");
		$("#pushobj_stick_"+id).val(1);
	}else{
		$("#pushobj_"+id).html("推荐");
		$("#pushobj_stick_"+id).val(0);
	}
}