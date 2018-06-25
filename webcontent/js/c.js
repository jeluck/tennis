var uid=new Base64().decode(cookie('uid'))
	,udata=cookie('udata')?$.parseJSON(new Base64().decode(cookie('udata'))):0
	,sid=new Base64().decode(cookie('sid'));
$(function(){
    $('#logout').click(function(){
        uid=delcookie('uid');udata=delcookie('udata');login();
    });
	$('body').append('<ul class="menubar b0 bgg f12 l0 lh100p pf ptd10 tc vm w100p z1024" style="border-top:1px solid #cccccc;">\
            <li><a href="index.html" title="home">首页</a></li>\
            <li><a href="shopo.html" title="shop">微店</a></li>\
            <li><a href="my.html" title="my">我的</a></li>\
        </ul>');
	$('head').append('<style>body{padding-bottom:64px;padding-top:52px;}\
			.header{background:#cc0000;border-bottom:1px solid #eeeeee;color:#ffffff;left:0;position:fixed;top:0;width:100%;z-index:1024}\
		</style>');
	$('.menubar li').addClass('fl w33p');
	$('.menubar a').removeClass('cr').addClass('db').each(function(i,o){
		$(o).html('<p><img src="images/mm'+o.title
			+(location.href.indexOf(o.href)<0?'':(function(){$(o).addClass('cr');return 'h';}()))
			+'.png" width="32"/></p>'+o.innerHTML);
	});
});
function Base64() {

    // private property  
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding  
    this.encode = function(input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                    _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                    _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding  
    this.decode = function(input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input===undefined||input===null?'':input.toString().replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    };

    // private method for UTF-8 encoding  
    _utf8_encode = function(s) {
        s=s==null?'':s;
        s = s.toString().replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < s.length; n++) {
            var c = s.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    };

    // private method for UTF-8 decoding  
    _utf8_decode = function(utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
            ;
        }
        ;
        return string;
    };
}

function cookie(name) {
    var start = document.cookie.indexOf(name + "=");
    var len = start + name.length + 1;
    if ((!start) && (name != document.cookie.substring(0, name.length))){
        return null;
    }
    if (start == -1)
        return null;
    var end = document.cookie.indexOf(';', len);
    if (end == -1)
        end = document.cookie.length;
    return unescape(document.cookie.substring(len, end));
}
function setcookie(name, value, expires, path, domain, secure) {
    path='/';
    var today = new Date();
    today.setTime(today.getTime());
    if (expires) {
        expires = expires * 1000 * 60 * 60 * 24;
    }
    var expires_date = new Date(today.getTime() + (expires));
    document.cookie = name + '=' + escape(value) +
            ((expires) ? ';expires=' + expires_date.toGMTString() : '') + //expires.toGMTString()   
            ((path) ? ';path=' + path : '') +
            ((domain) ? ';domain=' + domain : '') +
            ((secure) ? ';secure' : '');
}
function delcookie(name, path, domain) {
    path='/';
    if (cookie(name))
        document.cookie = name + '=' + ((path) ? ';path=' + path : '') + ((domain) ? ';domain=' + domain : '') + ';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}

function ef(){
	alert('server error 500');
}

function get(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = location.href.substr(location.href.indexOf('?') + 1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

function hce(f){
	$(window).on('hashchange', f);
}


function login() {
    if (!uid) {
        $('body').append('<form class="loginf bgg h100 l0 pf t0 tc w100p z">'
                + '<p class="bgw m10 p10">电话：<input name=userphone /></p>'
                + '<p class="bgw m10 p10">密码：<input name=password type="password"/></p>'
                + '<h3 class="bgr br5 c1 m10 p10 tc"><input class="bgn br0 cp cw f16 w100p" type="submit" value="登 录"/></h3>'
                + '<h3 class="regbt bgw br5 bs cp m10 p10 tc">注 册</h3></form>');
        var checkphonenum = function() {
            if (!/^[0-9]{3,16}$/.test($('.loginf input[name=userphone]').val())) {
                alert('请输入正确电话号码');
                ef2(1);
                return 0;
            }
            return 1;
        }, ef2 = function(s) {
            s === 1 ? 1 : ef();
            $('.loginf h3').show();
        },ccode;
        $('.loginf').submit(function(i) {
            i = $('.loginf [name=userphone]').val();
            $('.loginf h3').hide();
            if (checkphonenum())
                $.ajax({
                    url: 'user_login.do',
                    type: 'post',
                    data: $('.loginf').serialize(),
                    success: function(rs) {
                        if (rs&&rs.data&&rs.data.id) {
                        	uid=rs.data.id;
                        	udata=rs.data;
                            setcookie('uid', new Base64().encode(rs.data.id));
                            setcookie('udata', new Base64().encode(JSON.stringify(udata)));
                            history.go(0);
                        } else
                            alert(rs.msg);
                        ef2(1);
                    }, error: ef2
                });
            return false;
        });
        $('.regbt').click(function() {
            if (!$('.getvcode')[0]) {
                $('.loginf h3').eq(0).before('<p class="bgw m10 p10">验证码<input name=verifica_code /><br/>'
                        + '<input class="getvcode br5 m10 p5" type="button" value="发送验证码到手机"/></p>');
                $('.loginf .getvcode').click(function() {
                    if (checkphonenum()){
                    	$.ajax({
                            url: 'check_user_phone.do?userphone=' + $('.loginf input[name=userphone]').val(),
                            success: function(rs) {
                                rs?parseInt(rs.status)===0?$.ajax({
                                    url: 'send_sms_for_register.do?userphone=' + $('.loginf input[name=userphone]').val(),
                                    success: function(rs) {
                                        rs?alert(rs.msg):0;
                                        rs&&rs.data?ccode=rs.data.verifycode:0;
                                        ef2(1);
                                    }, error: ef2
                                }):alert(rs.msg):ef2();
                            }, error: ef2
                        });
                    }
                });
                return;
            }
            $('.loginf h3').hide();
            if (checkphonenum()&&ccode)
                $.ajax({
                    url: 'add_new_user.do',
                    type: 'post',
                    data: $('.loginf').serialize(),
                    success: function(rs) {
                        if (rs&&rs.data&&rs.data.id) {
                            $('.loginf').submit();
                        } else
                            alert(rs.msg);
                        ef2(1);
                    }, error: ef2
                });
            return false;
        });
        return false;
    }return uid;
}
function page(u,f,e){
    e=$(e);e.css('min-height',$(window).height());
    var vf=function(o,p,my,y){
    	o=e.parent().find('.more');
		p=parseInt(o.attr('p'));
		p=p>0?p:1;
		my=function(){
			return e.height()+(e.offset()?e.offset().top:0)+$('.menubar').height()-$(window).height()-1+20;
		};y=my()-0;
        if(!o[0]||(!o.hasClass('dn')&&o.parent().height()&&(window.scrollY>=y))){
            if(!o[0]){
                e.after('<p class="more" p=2></p>');
            }
            o.addClass('dn');window.scrollTo(0,y-1);
            $.ajax({
                url:u+p,
                success:function(d){
                    d=d&&d.data?d.data:{};
                    if(d&&d[0]){
                        o.attr('p',p+1);
                        if(typeof f==='function')f(d);
                    }
                    o.removeClass('dn');
                    window.scrollTo(0,o[0]?y-1:0);
                },
                error:ef
            });
        }
    };vf();
    $(window).scroll(vf);
}
window.popup={
    alert:function(c,f,t,disokbt){
        var s=$('<div><p class=popupbg></p><div class="popupbox popstyle"></div></div>');
        $('body').append(s);
        s.find('.popupbox').append('<div class=poptbar><h3 class="poptitle" title="'+(t||'')+'">'+(t||'')+'&#12288;</h3></div>'
            +'<a class="popclose">×</a>'
            +'<div class="popcont">'+c+'</div>'
            +(disokbt?'':'<p class="bggreen br5 m10" style="border-radius: 3px;"><input class="cw bgn br0 popokbt w100p" type="button" value="确定"></p>')).css({
                top:($(window).height()-s.find('.popupbox').height())/2+scrollY,
                left:($(window).width()-s.find('.popupbox').width())/2+scrollX
            });
        s.find('.popclose').click(function(){
            s.remove();
        });
        s.find('.popokbt').click(function(){
            if(typeof f==='function'){
                f(s);
            }
            s.remove();
        });
        return s;
    }
};