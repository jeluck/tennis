<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>总后台 - 管理员登录</title>
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
									class="white">U橙后台</span>
							</h1>
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 平台管理者登录信息
										</h4>

										<div class="space-6"></div>

										<form action="managelogin.do" method="POST">
<!-- 										<input type="radio" value="1" id="" class="" name="logintype" checked/>总后台&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 										<input type="radio" value="2" id="" class="" name="logintype" />场馆运营者</br></br> -->
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" name="usercode" class="form-control"
														placeholder="登录账号" required /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" name="password" class="form-control"
														placeholder="登录密码" required /> <i class="icon-lock"></i>
												</span>
												</label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right">
													<input type="text" title="请输入验证码" id="valicode" class="login_input input_text" style="width: 100px;" name="valicode" />  
													<span>
													<img style="position: relative; top: -2px; left: 10px; border: 1px solid #ccc; cursor: pointer; height: 30px"
								onclick="this.src='valicode.do?action=valicode&d='+ new Date().getTime();"
								src="valicode.do?action=valicode" id="changeg" /></span> <span
								class="verlification_code">&nbsp;&nbsp;,<a
								href="javascript:void(0);" onclick="ina();"
								style="color: #434F99">换一张</a></span>
													<i class="icon-lock"></i>
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
														<i class="icon-key"></i> 登录 &nbsp;>>
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
<!-- 											<a href="../index.do" target="_blank" -->
<!-- 												class="user-signup-link"> 进入网站首页 <i -->
<!-- 												class="icon-arrow-right"></i> -->
<!-- 											</a> -->
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

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-key"></i> 找回密码
										</h4>

										<div class="space-6"></div>
										<p>输入您的邮箱接收信息</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" placeholder="电子邮箱" /> <i
														class="icon-envelope"></i>
												</span>
												</label>

												<div class="clearfix">
													<button type="button"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="icon-lightbulb"></i> 发送给我&nbsp;>>
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> 返回登录 <i
											class="icon-arrow-right"></i>
										</a>
									</div>
								</div>
								<!-- /widget-body -->
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
			
		</script>
</body>
</html>
