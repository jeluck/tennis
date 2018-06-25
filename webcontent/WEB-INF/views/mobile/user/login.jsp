<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>登录界面</title>
    <link rel="stylesheet" href="/css/c.css"/>
    <link rel="stylesheet" href="/css/customer.css"/>
    <script src='/assets/js/jquery-2.0.3.min.js'></script>
    <script src="/js/admin_common.js"></script>

    <script type="text/javascript">
        window.onload = initAll;
        function initAll() {
            document.getElementById("register").onclick = newRegister;
            document.getElementById("tel").onchange = checkForm;
        }
        function newRegister() {
            window.location = "./toregister.do";
            return false;
        }
        function checkForm() {
            var mobile = document.getElementById("tel").value;
            var re = /^1[0-9]{10}$/;
            if(!re.test(mobile)) {
                alert("请输入有效的手机号码");
                document.getElementById("tel").focus();
                return false;
            }
            
            var password = $('#password').val();
            if(password.length<=0){
            	return false;
            }
            var info = util.POST("user_login.do",{"tel":mobile,"password":password});
            
			if(info.status != '0') {
				alert(info.msg);
				return false;
			}else{
				var str ="&phonetologin_id="+info.data.encryptuphone+"&phonetologin_key="+info.data.pass + '&user='+ mobile +'&key=' + password;
				document.form1.action = "touser.do?headId="+info.data.id+str;
        		document.form1.submit();
			}
			
			return true;
            
        }
  </script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">登录/注册</a>
    </p>
</div>
<form class="form" name="form1" action=""  method="post" onsubmit="return checkForm()">
    <input  class="tel" type="text" maxlength="11" name="tel"  id="tel"  onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
             placeholder="手机号" value="" required><br>
    <span class="tel-img01"></span>
    <input  class="password" type="password" maxlength="16" id="password" placeholder="登录密码" required><a href="forgetPwd.do"><span class="forget">忘记密码</span></a></input><br>
    <span class="key-img02"></span>
    <input  class="submit" type="submit"  value="登录"><br>
     <input class="newReg" type="button" id="register" value="新用户注册">
</form>
</body>
</html>