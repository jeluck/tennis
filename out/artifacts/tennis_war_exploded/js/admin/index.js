function ShowMsg(title, message) {
	$("#msg_title").html(title);
	$("#msg_content").html(message);
	$("#show_success_msg").slideDown(350);
	setTimeout(function() {
		$("#show_success_msg").slideUp(350);
	}, 3000);
}
// ifream自适应高度
function init() {
	$("#MainContentDiv").height($(window).height() - $("#navigation_p2p").height() - 4);
	$("#sidebar").height($(window).height() - $("#navigation_p2p").height() - 4);
}
// 窗口大小改变时
window.onresize = function() {
	init();
};

$(function() {
	$(".nav_tab").bind("click", function() {
		$(".nav_tab").removeClass("active");
		$(this).addClass("active");
	});
	init();
});
function changeUrl(Url,Menu_code) {
	$("#MainiFrame").attr("src", Url);
	select_menu_code=Menu_code;
}
function logout() {
	window.location.href = "logout.do";
}
var required = {
	"oldpassword" : "请输入原密码",
	"password" : "请输入新密码",
	"repassword" : "请输入确认密码"
};
function readyEditPWD() {
	var pass = true;
	for ( var key in required) {
		if (!$.trim($("#" + key).val())) {
			ShowMsg("操作提示", required[key]);
			$("#" + key).focus();
			pass = false;
			break;
		}
	}
	if (pass) {
		var password = $("#password");
		if (password.val().length < 6 || password.val().length > 20) {
			ShowMsg("操作提示", "新密码长度必须为6-20 位");
			return;
		}
		if ($.trim($("#password").val()) != $.trim($("#repassword").val())) {
			ShowMsg("操作提示:", "确认密码和新密码输入不一致，请重新输入");
			return;
		}
		$.ajax({
			type : "POST",
			url : "user.do?action=changepswd",
			data : {
				"oldpswd" : $.trim($("#oldpassword").val()),
				"newpswd" : $.trim($("#password").val())
			},
			dataType : "json",
			success : function(data) {
				ShowMsg("操作提示:", data.info);
				if (data.status) {
					$("#modal-table").modal("hide");
				}
			}
		});

	}

}