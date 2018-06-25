<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>注册 </title>
<meta name="keywords" content="网球馆" />
<meta name="description" content="网球馆" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src='../assets/js/jquery-2.0.3.min.js'></script>
<link rel="icon" href="../image/liantiao_favicon.ico"
	type="image/x-icon" />
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="../assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="../assets/css/google.css" />
<link rel="stylesheet" href="../assets/css/ace.min.css" />
<link rel="stylesheet" href="../assets/css/ace-rtl.min.css" />
<script type="text/javascript" src="../js/layer/layer.min.js"></script>
<script src="../js/admin_common.js"></script>
<link rel="stylesheet" type="text/css" href="../js/layer/skin/layer.css" />
<script type="text/javascript">
		//全登陆不允许iframe嵌入 
		if(window.top !== window.self){ window.top.location = window.location;}
		$(function(){
			$("#login_header_row").css({"marginTop":($(window).height()/5)+"px"});
			var msg = '${msg}';
			if(msg){
				layer.msg(msg, 2, 7);
			}
		});
		function ina(){
			$("#changeg").attr("src","valicode.do?action=valicode&d="+ new Date().getTime());
			return false;
		}

		function checkPhone(){
			var reg = /^[1][358][0-9]{9}$/;
			if(!reg.test($("#usercode").val())){
				alert("手机号码格式不正确");
				return 0;
			}
			return 1;
		}
</script>
</head>

<body class="login-layout" style="background:#1d2024!important;">
	<div class="main-container">
		<div class="main-content">
			<div class="row" id="login_header_row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red"></span> <span
									class="white">注册场管运营者</span>
							</h1>
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 请输入您的注册信息
										</h4>

										<div class="space-6"></div>

										<form action="savePGM.do" method="POST" onsubmit="return checkdata();">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" name="usercode" id="usercode" class="form-control"
														placeholder="手机号码" required onchange="checkPhone()"   maxlength="11"/> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" name="password" class="form-control"
														placeholder="设置密码" required /> <i class="icon-lock"></i>
												</span>
												</label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right">
													<input type="text" title="请输入验证码" id="valicode" class="login_input input_text" style="width: 100px;" name="valicode" />  
													<span>
													<input type="button" value="免费获取验证码" onclick="send_sms_for_reg(this);" >
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<!-- <label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label> -->

													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 注册 &nbsp;>>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

									<div class="toolbar clearfix">
										<div></div>
										<div>
											<a href="login.do" xtarget="_blank"
												class="user-signup-link"> 进入登录 <i
												class="icon-arrow-right"></i>
											</a>
											<!-- <a href="#" onclick="show_box('forgot-box'); return false;" class="user-signup-link">
													忘记密码？
													<i class="icon-arrow-right"></i>
												</a> -->
										</div>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->

							</div>
							<!-- /forgot-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->

	<!-- <![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
			window.jQuery || document.write("<script src='../assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

	<!-- <![endif]-->


	<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
			

	function send_sms_for_reg(obj){
		var phone = $('#usercode').val();
		 var reAcconut = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		 if (!reAcconut.test(phone)) {
		        alert('请输入有效手机号码');
		        return false;
		    } 
		
		var info = util.POST("send_sms_for_reg.do",{"phone":phone});
		if(info.status!=0){
			alert(info.msg);
		}else{
			settime(obj);
		}
	}
	
	function checkdata(){
		var phone = $('#usercode').val();
		var valicode = $('#valicode').val();
		var info = util.POST("checkCode.do",{"phone":phone,"phonecode":valicode});
		if(info.status!=0){
			alert(info.msg);
			return false;
		}
		return true;
	}

/**
 * 60秒倒计时
 */
var countdown=60; 
function settime(val) { 
	var oo = setTimeout(function() { 
		settime(val);
	},1000);
	
	if (countdown == 0) { 
		val.removeAttribute("disabled");    
		val.value="免费获取验证码"; 
		countdown = 60; 
		clearTimeout(oo);
	} else { 
		val.setAttribute("disabled", true); 
		val.value="重新发送(" + countdown + ")"; 
		countdown--; 
	} 
	
} 

	</script>
</body>
</html>
