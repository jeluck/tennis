<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>账号注册</title>
    <link rel="stylesheet" href="css/c.css"/>
    <link rel="stylesheet" href="css/customer.css"/>
    <script src="./js/jquery.min.js"></script>
    
    <script type="text/javascript">
        function checkForm() {
            var mobile = document.getElementById("uphone").value;
            var re = /^(1[0-9]{10})|(1[0-9]{10})$/;
            if(!re.test(mobile)) {
                alert('手机号码格式不正确');
                $('#uphone').focus();
                return false;
            }
			
			return true;
        }
    </script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">账号注册</a>
    </p>
</div>
<form class="form register-form" action="tonextRegister.do" method="post" onSubmit="return checkForm();">
    <input class="invitedCode" name="invitedCode" id="invitedCode" type="text" maxlength="11" placeholder="邀请id码" required><br>
    <span class="invited-img01"></span>
    <input  class="tel cellphone" type="text" maxlength="11" name="uphone" id="uphone" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="手机号" required><br>
    <span class="tel-img01 cellphone-img01"></span>
   
    <input  class="setPwd" type="password" id="pass" name="pass" maxlength="16" placeholder="设置6~15位登录密码" required><span style="position:relative;right: -96%;top: -56px;"><a class="displayPwd" id="displayPwd" ></a></span><br>
    <span class="setPwd-img01"></span>
  <!--   <input  class="setName" type="text" maxlength="20" name="username" placeholder="设置昵称" ><br>
    <span class="setName-img01"></span> -->
    <input  class="submit" type="submit" id="nextReg" value="下一步">
    <span class="agreement" style="color:#ACACAC;">注册即视为同意 &nbsp;<a href="agreement.html" style="font-size:16px; color:#788DF0">服务协议</a></span>
</form>

</body>
</html>