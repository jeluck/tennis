/**
 * @requires JQuery 和scrollpagination.js
 * @namespace util
 * @author lxc
 * @version 1.0
 */
// 分页下拉加载刷新
//最外层div的ID
function page(divid,u,f){
    $('#'+divid).css('min-height',$(window).height());
    var x=y=mx=my=0,vf=function(){
        if(!$('.more_p')[0]){
            $('#'+divid).append('<p class="more_p" p=2></p>');
        }
		//$('#ot').html($('#ot').html() + parseInt(window.scrollY) + '<br>');
        if(!$('.more_p').hasClass('dn')){
            $('.more_p').addClass('dn');
            var pageNumber = $('.more_p').attr('p');
            var orderByparam = "";
            try{
            	orderByparam = document.getElementById("orderByparam").value;
            }catch (e) {}
            $.ajax({
        		type:"POST",
        		url:u,
        		data:{"pageNumber":pageNumber,"orderByparam":orderByparam},
        		dataType:"html",
        		success:function(data){
					if($.trim(data) != ''){
					    $('#loading').fadeIn("fast");
						$('.more_p').attr('p',parseInt($('.more_p').attr('p')||1)+1);
						if(typeof f==='function') f(data);
						$('.more_p').removeClass('dn');
						$('#loading').fadeOut("slow");
					}
        		}
        	});
        }
    };

    $(window).scroll(vf);
}