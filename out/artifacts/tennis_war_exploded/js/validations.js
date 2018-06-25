$(function(){
//验证邮箱是否输入正确
	$("input[type=text]").focus(function(){
		$(this).css("border","1px solid #4EC520");
	}).blur(function(){
		$(this).css("border","1px solid #AFAFAF");
/*		var values = $(this).val();
		if($.trim(values)=="" || $.trim(values)==null ){
			$(this).next("span").css("display","inline-block");
		}else{
			$(this).next("span").css("display","none");
		}*/
	});
	
	$("#mailed").blur(function(){
		var reg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(!reg.test($("#mailed").val())){
			$("#mailedspan").css("display","inline-block");
			$("#mailed").css("border","1px solid red");
		}else{
			$("#mailedspan").css("display","none");
			
		}
	});

	$("#phoneed").blur(function(){
		if(!$.trim($("#phoneed").val())){
			$("#phoneed").css("border","1px solid red");
			$("#phoneedspan").css("display","inline-block");
		}else{
			$("#phoneedspan").css("display","none");
			
		}
	});	
	
	//默认让邮箱的第一步显示
	$.each($(".show_div"),function(index,items){
		$(items).css("display","none");
	});
	$(".show_div").eq(0).css("display","block");
	
});

function finded(e){
	if(e==1){
		$.each($(".show_div"),function(index,items){
			$(items).css("display","none");
		});
		$(".show_div").eq(0).css("display","block");
	}else{
		$.each($(".show_div"),function(index,items){
			$(items).css("display","none");
		});
		$(".show_div").eq(4).css("display","block");
	}
}

//验证第一步
function validation1(){
	var flag = false;
	var reg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(!reg.test($("#mailed").val())){
			$("#mailedspan").css("display","inline-block");
			$("#mailed").css("border","1px solid red");
			return false;
		}else{
			$("#mailedspan").css("display","none");
			$("#mailed").css("border","1px solid #AFAFAF");
			flag = true;
		}
	if(!$.trim($("#phoneed").val())){
		$("#phoneed").css("border","1px solid red");
		$("#phoneedspan").css("display","inline-block");
		return false;
	}else{
		$("#phoneedspan").css("display","none");
		$("#phoneed").css("border","1px solid #AFAFAF");
		flag = true;
	}
	if(flag){
		$.each($(".show_div"),function(index,items){
			$(items).css("display","none");
		});
		$(".show_div").eq(1).css("display","block");
	}
}
function validation2(){
	$.each($(".show_div"),function(index,items){
		$(items).css("display","none");
	});
	$(".show_div").eq(2).css("display","block");
	var validationed = $("#validationed").val();
	if($.trim(validationed)=="" || $.trim(validationed)==null ){
		
	}
}
function validation3(){
	$.each($(".show_div"),function(index,items){
		$(items).css("display","none");
	});
	$(".show_div").eq(3).css("display","block");
}
function validation4(){
	$.each($(".show_div"),function(index,items){
		$(items).css("display","none");
	});
	$(".show_div").eq(3).css("display","block");
}

//手机的下一步

function validation_phone1(){
	var flag = false;
	var reg = /^[0-9]{11}$/;
	var phone = $("#phone");
	alert(1);
	if(!reg.test(phone.val())){
		phone.next('span').html("&nbsp;*&nbsp;手机号格式不正确.");
		phone.css("border","1px solid red");
		return;
	}else{
		$("#phonespan").css("display","none");
		phone.css("border","1px solid #AFAFAF");
		flag = true;
	}	
	if(!$.trim($("#phoneed2").val())){
		$("#phoneed2").css("border","1px solid red");
		$("#phoneedspan2").css("display","inline-block");
	}else{
		info = util.POST("checkunique.do", {"type" : "phone","value" : $phone.val()});
		if (!info) {
			phone.next('span').html("*请输入正确的手机号.");
			return;
		}
		$("#phoneedspan2").css("display","none");
		$("#phoneed2").css("border","1px solid #AFAFAF");
		flag = true;
	}
	if(flag){
		$.each($(".show_div"),function(index,items){
			$(items).css("display","none");
		});
		$(".show_div").eq(5).css("display","block");
	}
}

function validation_phone2(){
	$.each($(".show_div"),function(index,items){
		$(items).css("display","none");
	});
	$(".show_div").eq(2).css("display","block");
}
var check_phone = true;//检测手机号是否通过
//检测手机号
function phone_blur(){
	var reg = /^[1]\d{10}$/;
	var phone = $("#phone");
	if(!reg.test(phone.val())){
		phone.next('span').html("&nbsp;*&nbsp;手机号格式不正确.");
		phone.css("border","1px solid red");
		check_phone = false;
		return;
	}	
	//验证手机号是否存在
	info = util.POST("checkunique.do", {"type" : "phone","value" : $phone.val()});
	if (!info) {
		phone.next('span').html("&nbsp;*&nbsp;请输入正确的手机号.");
		phone.css("border","1px solid red");
		check_phone = false;
		return;
	}
	$("#phoneedspan2").css("display","none");
	$("#phoneed2").css("border","1px solid #AFAFAF");
	
}
