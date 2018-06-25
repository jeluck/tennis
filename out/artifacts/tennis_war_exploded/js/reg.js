lz.sendCode = function(obj) {
    /*注册，发送验证码使用*/
	var phone = document.getElementById('account').value;
	if(phone == null || phone == ""){
		  alert('请输入手机号码');
		return false;	
	}
  var info = util.POST("/send_sms_for_register.do",{"phone":phone});
}

lz.passwordCutover = function(obj) {
    /*注册，切换密码是否明文可见*/
    
    var oBox = obj.parentNode;
    var oI = obj.getElementsByTagName('i')[0];
    var oInput = oBox.getElementsByTagName('input')[0];
    
    if (oInput.type == 'password') {
        //密码可见
        oInput.type = 'text';
        oI.className += ' lz-theme-font-color';
    } else {
        //密码不可见
        oInput.type = 'password';
        lz.removeClass(oI, 'lz-theme-font-color');
    }

}

lz.testRegForm = function(obj) {
    /*注册，表单提交前验证*/
    
    var oAccount = document.getElementById('account');
    //账号，手机号码
    var oCode = document.getElementById('code');
    //验证码
    var oName = document.getElementById('name');
    //昵称
    var oPassword = document.getElementById('password');
    //密码
    var oPact = document.getElementById('pact');
    
    var reAcconut = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    //验证手机号码
    
    
    if (!reAcconut.test(oAccount.value)) {
        
        alert('请输入有效手机号码')
        return false;
    
    } else if (oCode.value == '') {
        
        alert('请输入验证码')
        return false;
    
    } else if (oName.value == '') {
        
        alert('请输入昵称')
        return false;
    
    } else if (oPassword.value == '') {
        
        alert('请输入密码')
        return false;
    
    } else if (!oPact.checked) {
        alert('请先同意用户服务协议');
        return false;
    }
    
    var info = util.POST("/add_new_user.do",{"phone_code":oCode.value,"phone":oAccount.value,"password":oPassword.value,"nickname":oName.value});
    if(info.status == 0){
    	$("#userId").val(info.data.id);
		return true;
	}else{
		alert(info.msg);
	    return false;
	}
}

lz.testLoginForm = function (_this) {
	  /* 登录，表单提交前验证 */
	  var oAccount = document.getElementById('account'); //账号
	  var oPassword = document.getElementById('password'); //密码
	  var reAcconut = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; //验证手机号码
	
	  if (!reAcconut.test(oAccount.value)) {
	    alert('请输入有效手机号码');
	    return false;
	  } else if (oPassword.value == '') {
	    alert('密码不能为空');
	    return false;
	  }
	
	  var info = util.POST("/user_login.do",{"tel":oAccount.value,"password":oPassword.value});
	  if(info.status == 0){
		  $("#userId").val(info.data.id);
		  return true;
	  }else{
		  alert(info.msg);
		  return false;
	  }
};
