$(function(){
	$(window.parent.document).find("#helpeds").height($("body").height()+50);

	$("div.exp_ul p .onclick_p").click(function(){
		if($(this).parent().next("div").css("display")=="none"){
			$(this).parent().next("div").css("display","block");
			$(this).addClass("onclick_p2");
			$(window.parent.document).find("#helpeds").height($("body").height()+50);
		}else{
			$(this).parent().next("div").css("display","none");
			$(this).removeClass("onclick_p2");
			$(window.parent.document).find("#helpeds").height($("body").height()+50);
		}
		
	});
});