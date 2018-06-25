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
            document.getElementById("tel").onchange = checkForm;
        }
        function checkForm() {
            var mobile = document.getElementById("tel").value;
            var re = /^([\w]{11})$/;           //字母、数字、下划线
            if(!re.test(mobile)) {
                //alert("请输入有效的登录账号");
                document.getElementById("tel").focus();
                //return false;
            }
            var password = document.getElementById("password").value;
            if(password.length==0){
            	 return false;
            }
            var info = util.POST("agent_login.do",{"tel":mobile,"password":password});
            if(info.status==0){
            	var str ="&phonetologin_id="+info.data.encryptusercode+"&phonetologin_key="+info.data.password;
            	document.form1.action = "my.do?managerId="+info.data.id+str;
        		document.form1.submit();
            }else{
            	alert(info.msg);
            	return false;
            }
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
<div class="spacediv"></div>
<form class="form" name="form1" id="form1" action=""  method="post" onsubmit="return checkForm()">
    <input  class="tel" type="text" maxlength="11" name="tel"  id="tel"  onkeyup="value=value.replace(/[^\w]/g,'')" autocomplete="off" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\w]/g,''))"
            placeholder="登录账号" value="" required><br>
    <span class="tel-img01"></span>
    <input  class="password" type="password" id="password" name="password" maxlength="16" autocomplete="off" onfocus="this.type='password'"  placeholder="登录密码" required><a href="findPwd.html"><span class="forget">忘记密码</span></a></input><br>
    <span class="key-img02"></span>
    <input  class="submit" type="submit"  value="商家登录"><br>
</form>

</body>
</html>