/**
 * 显示等级列表
 * @param {object} obj 被点击的元素
 */
lz.selectLevel = function (obj, obj2) {
  var oLevel = obj2 ? document.getElementById(obj2) : document.getElementById('lz-level');
  oLevel.style.display = 'block';
}

/**
 * 返回用户设置的学历
 * @param {object} obj 被点击的元素
 * @param {string} 用户设置的学历
 */
lz.returnxueli = function (obj, value) {
  var oLevel = document.getElementById('lz-level');
  var oLevelValue = document.getElementById('lz-levelValue');
  var oInput = oLevelValue.parentNode.getElementsByTagName('input')[0];

  oInput.value = value;
  oLevelValue.innerHTML = value;

  document.getElementById('xueli').value = value;
  oLevel.style.display = 'none';
};


/**
 * 返回用户设置的等级
 * @param {object} obj 被点击的元素
 * @param {string} 用户设置的等级
 */
lz.returnLevel = function (obj, value) {
  var oLevel = document.getElementById('lz-level');
  var oLevelValue = document.getElementById('lz-levelValue');
  var oInput = oLevelValue.parentNode.getElementsByTagName('input')[0];

  oInput.value = value;
  oLevelValue.innerHTML = value;

  document.getElementById('level').value = value;
  oLevel.style.display = 'none';
};


/**
 * 返回专业
 * @param {object} obj 被点击的元素
 * @param {string} 用户设置的专业
 */
lz.professional = function (obj, value) {
  var oLevel = document.getElementById('lz-professional');
  var oLevelValue = document.getElementById('lz-professionalValue');
  var oInput = oLevelValue.parentNode.getElementsByTagName('input')[0];

  oInput.value = value;
  oLevelValue.innerHTML = value;

  document.getElementById('professional').value = value;
  oLevel.style.display = 'none';
};


/**
 * 返回用户设置的国籍
 * @param {object} obj 被点击的元素
 * @param {string} 用户设置的等级
 */
lz.returngouji = function (obj, value) {
  var oLevel = document.getElementById('lz-gouji');
  var oLevelValue = document.getElementById('lz-goujiValue');
  var oInput = oLevelValue.parentNode.getElementsByTagName('input')[0];

  oInput.value = value;
  oLevelValue.innerHTML = value;

  oLevel.style.display = 'none';
};

/**
 * 给选中的按钮设置value值
 * @param {object} obj 被点击的元素
 * @param {string} value选中的值
 */
lz.checkboxTwoSetValue = function (obj, value) {
    document.getElementById('sex').value = value;
  }
  /**
   * 选择教练类型
   * @param {object} _this 被点击的元素
   * @param {string} value 要设置的值
   */
lz.choiceCoachTYpe = function (_this, value) {
    var oBox = document.getElementById('lz-coach-type');
    var aChecked = oBox.getElementsByClassName('lz-checked-2');
    var oChecked = _this.getElementsByClassName('lz-checked-2')[0];
    var oInput = oBox.getElementsByTagName('input')[0];
    for (var i = 0; i < aChecked.length; i++) {
      aChecked[i].getElementsByTagName('i')[0].className = '';
    }

    oChecked.getElementsByTagName('i')[0].className = 'lz-show';
    oInput.value = value;
    cloud.explain($("#coachType").val());

  }
  /**
   * 设置单选，实名认证-->资质认证，教学设置-手机端-->教学专长应用到
   * @param {object} _this 被点击的元素
   * @param {string} value 要设置的值
   */
lz.setRadio = function (_this, value) {

    var aList = _this.parentNode.getElementsByTagName('div');

    for (var i = 0; i < aList.length; i++) {
      lz.removeClass(aList[i], 'lz-cur');
    }
    _this.className += ' lz-cur';
    _this.parentNode.getElementsByTagName('input')[0].value = value;
  }
  /**
   * 选择性别
   * @param {object} _this 被点击的元素
   * @param {string} value 要设置的值
   */
lz.choiceSex = function (_this, value) {

  var oFuTag = _this.parentNode;
  var aITag = oFuTag.getElementsByTagName('i');

  for (var i = 0; i < aITag.length; i++) {
    aITag[i].className = '';
  }
  _this.getElementsByTagName('i')[0].className = 'lz-show';
  document.getElementById('sex').value = value;

}

/**
 * 添加单位名称
 * @param {object} obj 被点击的元素
 */
lz.addDate = function (_this) {
    var result = true;
    $("input[name='unitName']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
        $("#error").html("请输入单位名称");
        result = false
        return result;
      }
    });
    if (result == false) {
      return false;
    }

    $("input[name='begin_time']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
        $("#error").html("请选择开始时间");
        result = false
        return false;
      }
    });
    if (result == false) {
      return false;
    }

    $("input[name='end_time']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
        $("#error").html("请选择结束时间");
        result = false
        return false;
      }
    });
    if (result == false) {
      return false;
    } else {
      $("#error").html("");
    }

    var oUl = _this.parentNode.parentNode;
    var aLi = oUl.getElementsByClassName('lz-addunit');
    var oAdd = aLi[0];
    
    if (!oAdd) {
      aLi = oUl.getElementsByTagName('li');
      oAdd = aLi[2];
      oAdd.dataset.info = 'my';
    }
  
    var oLi = document.createElement('li');
//    oli.style.paddingLeft = '20px';
    oLi.className = 'lz-addunit';
    oLi.innerHTML = '\
                <div>\
                  <input type="text" name="unitName" placeholder="单位名称" value="">\
                </div>\
                <div>\
                  <input data-type="date" type="text" name="begin_time" placeholder="开始时间" value="">\
                </div>\
                <div>\
                  <input data-type="date" type="text" name="end_time" placeholder="结束时间" value="">\
                </div>\
    			<div onclick="lz.removeExper(this);" style="width:10%; text-align: right;"><i class="lz-iconfont lz-icon-guanbi"></i></div>\
    ';
    oUl.insertBefore(oLi, oAdd);
    lz.mobiscroll();
  }
  /*
   *删除区域
   */
lz.removeArea = function (_this) {

  var oLi = _this.parentNode;
  var oUl = oLi.parentNode;
  oUl.removeChild(oLi);
}

/*
 *删除执教经历
 */
lz.removeExper = function (_this) {

  var oLi = _this.parentNode;
  var oUl = oLi.parentNode;
  oUl.removeChild(oLi);
}

/**
 * 保存教练端教练个人中心信息
 */
function saveCoach(){
	var userId = document.getElementById('userId').value;  
	var niname = document.getElementById('niname').value;
	var sex = document.getElementById('sex').value;
	var birthday = document.getElementById('birthday').value;
	var personalitySignature = document.getElementById('personalitySignature').value;
	var name = document.getElementById('name').value;
	var height = document.getElementById('height').value;
	var weight = document.getElementById('weight').value;
	var gouji = document.getElementById('gouji').value;
	var hometown = document.getElementById('hometown').value;
	var areaid = document.getElementById('area').value;
	var invite_id = document.getElementById('invite_id').value;
	if(hometown == null || hometown == "" || hometown == undefined){
		  hometown = -1;
	}
	if(niname == null || niname == ""){
		document.getElementById('niname').style.border = '1px solid #FF0000';
		if(window.hideTab){
			window.hideTab.toast("请填写昵称");
		    return;
	    }else{
	    	return JSON.stringify({data: '',status:-1,msg: '请填写昵称'});
	    }
	}else{
		document.getElementById('niname').style.border = '0px solid #FF0000';
	}
	
  if (birthday == null || birthday == "") {
	if(window.hideTab){
		window.hideTab.toast("请填写生日");
		document.getElementById('birthday').style.border = '1px solid #FF0000';
	    return;
    }else{
    	document.getElementById('birthday').style.border = '1px solid #FF0000';
    	return JSON.stringify({data: '',status:-1,msg: '请填写生日'});
    }
  }else{
	document.getElementById('birthday').style.border = '0px solid #FF0000';
  }
	
	if(invite_id != null && invite_id != ""){
		var reg = /^[1][3578][0-9]{9}$/;
		if(!reg.test(invite_id)){
			document.getElementById('invite_id').style.border = '1px solid #FF0000';
			if(window.hideTab){
				window.hideTab.toast("手机号码格式不正确");
			    return;
		    }else{
		    	return JSON.stringify({data: '',status:-1,msg: '手机号码格式不正确'});
		    }
		}else{
			if(window.hideTab){
				if(checkfindPhone() == 1){
					return;
				}
			}else{
				var iosTest = checkfindPhone_IoS();
				if(iosTest.status != 0){
					return JSON.stringify(iosTest);
				}
			}
		}
	}
	
	var city = document.getElementById('city').value;
	if(city == null || city == "" || city <= 0){
		$("#ct").html("<strong style='color:red;'>请选择城市</strong>");
		if(window.hideTab){
			window.hideTab.toast("请选择城市");
		    return;
	    }else{
	    	return JSON.stringify({data: '',status:-1,msg: '请选择城市'});
	    }
	}
	if(gouji == null || gouji == ""){
		$("#lz-goujiValue").html("<strong style='color:red;'>请选择国籍</strong>");
		if(window.hideTab){
			window.hideTab.toast("请选择国籍");
		    return;
	    }else{
	    	return JSON.stringify({data: '',status:-1,msg: '请选择国籍'});
	    }
	}
	var address = document.getElementById('address').value;
	var employer = document.getElementById('employer').value;
	var info = util.POST("/saveCoachUserInfo.do",{"userId":userId,"username":name,"sex":sex,"birthday":birthday,
											"address":address,"niname":niname,"employer":employer,"height":height,
											"weight":weight,"personalitySignature":personalitySignature,
											"city":city, "areaid": areaid,"hometown":hometown,"nationality":gouji,"invite_id":invite_id});
	try{
		if(window.hideTab){
			 window.hideTab.saveFinish();
	    }else{
	    	return info;
	    }
	}catch (e) {
		cloud.explain(info.msg);
	}
}

/**
 * 保存用户个人中心信息
 */
function saveUser() {
  var id = document.getElementById('id').value;
  var username = document.getElementById('username').value;
  var sex = document.getElementById('sex').value;
  var birthday = document.getElementById('birthday').value;
  var address = document.getElementById('address').value;
  var signature = document.getElementById('signature').value;
  var level = document.getElementById('level').value;
  var school = document.getElementById('school').value;
  var employer = document.getElementById('employer').value;
  var cityid = document.getElementById('city').value;
  var areaid = document.getElementById('area').value;
  var hometown = document.getElementById('hometown').value;
  var invite_id = document.getElementById('invite_id').value;
  if(hometown == null || hometown == "" || hometown == undefined){
	  hometown = -1;
  }
  
  if (username == null || username == "") {
	if(window.hideTab){
		window.hideTab.toast("请填写昵称");
		document.getElementById('username').style.border = '1px solid #FF0000';
	    return;
    }else{
    	document.getElementById('username').style.border = '1px solid #FF0000';
    	return JSON.stringify({data: '',status:-1,msg: '请填写昵称'});
    }
  }else{
	document.getElementById('username').style.border = '0px solid #FF0000';
  }
  
  if (birthday == null || birthday == "") {
	if(window.hideTab){
		window.hideTab.toast("请填写生日");
		document.getElementById('birthday').style.border = '1px solid #FF0000';
	    return;
    }else{
    	document.getElementById('birthday').style.border = '1px solid #FF0000';
    	return JSON.stringify({data: '',status:-1,msg: '请填写生日'});
    }
  }else{
	document.getElementById('birthday').style.border = '0px solid #FF0000';
  }
  
  if(invite_id != null && invite_id != ""){
	var reg = /^[1][358][0-9]{9}$/;
	if(!reg.test(invite_id)){
		if(window.hideTab){
			document.getElementById('invite_id').style.border = '1px solid #FF0000';
			window.hideTab.toast("手机号码格式不正确");
			return;
		}else{
			document.getElementById('invite_id').style.border = '1px solid #FF0000';
	    	return JSON.stringify({data: '',status:-1,msg: '手机号码格式不正确'});
		}
	}else{
		if(window.hideTab){
			if(checkfindPhone() == 1){
				return;
			}
		}else{
			var iosTest = checkfindPhone_IoS();
			if(iosTest.status != 0){
				return JSON.stringify(iosTest);
			}
		}
	}
  }
  
  
  
  if (level == null || level == "") {
	  if(window.hideTab){
		  window.hideTab.toast("请选择级别");
		  $("#lz-levelValue").html("<strong style='color:red;'>请选择级别</strong>");
		  return;
	  }else{
		  $("#lz-levelValue").html("<strong style='color:red;'>请选择级别</strong>");
		  return JSON.stringify({data: '',status:-1,msg: '请选择级别'});
	  }
  }

  if (cityid == null || cityid == "" || cityid <= 0) {
	  if(window.hideTab){
		  window.hideTab.toast("请选择城市");
		  $("#ct").html("<strong style='color:red;'>请选择城市</strong>");
		  return;
	  }else{
		  $("#ct").html("<strong style='color:red;'>请选择城市</strong>");
		  return JSON.stringify({data: '',status:-1,msg: '请选择城市'});
	  }
  }
  var info = util.POST("/saveUserInfo.do", {
    "userId": id,
    "username": username,
    "sex": sex,
    "birthday": birthday,
    "signature": signature,
    "tennis_level": level,
    "cityid": cityid,
    "areaid": areaid,
    "hometown": hometown,
    "school": school,
    "employer": employer,
    "address": address,
    "invite_id":invite_id
  });
  try {
   
    if(window.hideTab){
    	 window.hideTab.saveFinish();
    }else{
    	return info;
    }
  } catch (e) {
    cloud.explain(info.msg);
  }
}

/**
 * 上传头像
 */
function head_photo(flag,_this, userId) {
	
	  if (window.hideTab) {
	    return window.hideTab.showcontacts(); //安卓调用上传
	  }
	  //ios上传	
	  var element = document.createElement('input');
	  element.type = 'file';
	  element.click();
	  element.onchange = function () {
	//	    if (element.files[0] && /image\/\w+/.test(element.files[0].type)) {
	//	      cloud.explain('只能选择图片');
	//	    }
	    var fd = new FormData();
	    if(flag == 1){
	    	img = "/user_header_picture.do";
	    }else{
	    	img = "/coach_user_header_picture.do";
	    }
	   
	    fd.append('imgName', element.files[0]);
	    fd.append('userId', userId);
	    var xhr = new XMLHttpRequest();
	    xhr.open('post', img, false);
	    xhr.setRequestHeader('X-Request-With', 'XMLHttpRequest');
	  
	    xhr.onload = function (e) {
		      if (xhr.status == 200) {
		        var data = JSON.parse(xhr.responseText);
		        if (data && data.data != '') {
			          _this.getElementsByTagName('img')[0].src = data.data;
		        } else {
		          cloud.explain('上传出错');
		        }
		      }
		    };
		    xhr.send(fd); //发送文件
	  }
}

/**
 * 改变头像
 * @param url
 */
function changeheaderforandroid(url) {
  $("#img").attr("src", "/" + url);
}

/**
 * 资质认证
 */
function auth(type) {
  try {
    if(window.hideTab){
    	return	window.hideTab.showView(type);
    }
	  //ios上传	
	  var element = document.createElement('input');
	  element.type = 'file';
	  element.click();
	  element.onchange = function () {
	    var fd = new FormData();
	    var img = "/auth.do";
	    fd.append('imgName', element.files[0]);
	    fd.append('type', type);
	    fd.append('userId', document.getElementById('userId').value);
	    var xhr = new XMLHttpRequest();
	    xhr.open('post', img, false);
	    xhr.setRequestHeader('X-Request-With', 'XMLHttpRequest');
	    xhr.onload = function (e) {
	      if (xhr.status == 200) {
	        var data = JSON.parse(xhr.responseText);
	        if (data && data.data != '') {
	          changezs(type,data.data);
	        } else {
	          cloud.explain('上传出错');
	        }
	      }
	    };
	    xhr.send(fd); //发送文件
	  }
    
  } catch (e) {
    // TODO: handle exception
  }
}

/**
 * 认证完显示证书高亮
 */
function changezs(type,url) {
  if (type == 1) {
    $("#ph1").html('<span class="lz-cur" >ITF</span>');
  } else if (type == 2) {
    $("#ph2").html('<span class="lz-cur" >USPTA</span>');
  } else if (type == 3) {
    $("#ph3").html('<span class="lz-cur" >PTR</span>');
  } else if (type == 4) {
    $("#ph4").html('<span class="lz-cur" >RPT</span>');
  } else if (type == 5) {
    $("#ph5").html('<span class="lz-cur" >Equelite</span>');
  } else if (type == 6) {
    $("#ph6").html('<span class="lz-cur" >CTA</span>');
  } else if (type == 7) {
	$("#img7").attr("src", "/" + url);
  } else if (type == 8) {
	$("#img8").attr("src", "/" + url);
  } else if (type == 9) {
	$("#img9").attr("src", "/" + url);
  }
}

/**
 * 保存实名认证
 */
function saveVerified(){
	var result = true;
	var unitName = "";
	$("input[name='unitName']").each(function(y){
		if($(this).val() == null || $(this).val() == ""){
			$("#error").html("请输入单位名称");
			result = false
			return false;
		}else{
			unitName += $(this).val()+",";
		}
	});
	if(result == false){
		if(window.hideTab){
			window.hideTab.toast("请输入单位名称");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请输入单位名称'});
		}
	}
	var begin_time = "";
	$("input[name='begin_time']").each(function(y){
		if($(this).val() == null || $(this).val() == ""){
			$("#error").html("请选择开始时间");
			result = false
			return false;
		}else{
			begin_time += $(this).val()+",";
		}
	});
	if(result == false){
		if(window.hideTab){
			window.hideTab.toast("请选择开始时间");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请选择开始时间'});
		}
	}
	var end_time = "";
	$("input[name='end_time']").each(function(y){
		if($(this).val() == null || $(this).val() == ""){
			$("#error").html("请选择结束时间");
			result = false
			return false;
		}else{
			end_time += $(this).val()+",";
		}
	});
	if(result == false){
		if(window.hideTab){
			window.hideTab.toast("请选择结束时间");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请选择结束时间'});
		}
	}else{
		$("#error").html("");
	}
	
	var userId = document.getElementById('userId').value; 
	var xueli = document.getElementById('xueli').value;  
	var professional = document.getElementById('professional').value;
	var school = document.getElementById('school').value;
	var teaching = document.getElementById('teaching').value;
	var goodAt = document.getElementById('goodAt').value;
	var coachType = document.getElementById('coachType').value;
	var idcard_no = document.getElementById('idcard_no').value;
	
	var bank = document.getElementById('bank').value;
	var zbank = document.getElementById('zbank').value;
	var bankZh = document.getElementById('bankZh').value;
	var bankName = document.getElementById('bankName').value;
	
	if(xueli == null || xueli == "" ){	//学历
		$("#lz-levelValue").html("<strong style='color:red;'>请选择学历</strong>");
		if(window.hideTab){
			window.hideTab.toast("请选择学历");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请选择学历'});
		}
	}
	
	if(school == null || school == "" ){	//学校
		document.getElementById('school').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写学校");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写学校'});
		}
	}else{
		document.getElementById('school').style.border = '0px solid #FF0000'; 
	}
	
	if(professional == null || professional == "" ){	//专业
		document.getElementById('professional').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写专业");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写专业'});
		}
	}else{
		document.getElementById('professional').style.border = '0px solid #FF0000'; 
	}
	
	if(teaching == null || teaching == "" ){	//教龄
		document.getElementById('teaching').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写教龄");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写教龄'});
		}
	}else{
		document.getElementById('teaching').style.border = '0px solid #FF0000'; 
	}
	
	if(teaching <= 0 ){	//教龄
		document.getElementById('teaching').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("教龄必须大于0");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '教龄必须大于0'});
		}
	}else{
		document.getElementById('teaching').style.border = '0px solid #FF0000'; 
	}
	
	if(goodAt == null || goodAt == "" ){	//技能
		document.getElementById('goodAt').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写技能");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写技能'});
		}
	}else{
		document.getElementById('goodAt').style.border = '0px solid #FF0000'; 
	}
	
	if(idcard_no == null || idcard_no == "" ){	//身份证
		document.getElementById('idcard_no').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写身份证");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写身份证'});
		}
	}else{
		document.getElementById('idcard_no').style.border = '0px solid #FF0000'; 
	}
	
	var reg1 =/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	var reg2 =/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
	if(reg1.test(idcard_no) != true && reg2.test(idcard_no) != true){
		document.getElementById('idcard_no').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("身份证格式不对");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '身份证格式不对'});
		}
	}else{
		document.getElementById('idcard_no').style.border = '0px solid #FF0000'; 
	}
	
	
	if(bank == null || bank == "" ){	//银行
		document.getElementById('bank').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写银行");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写银行'});
		}
	}else{
		document.getElementById('bank').style.border = '0px solid #FF0000'; 
	}
	
	if(zbank == null || zbank == "" ){	//支行
		document.getElementById('zbank').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写支行");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写支行'});
		}
	}else{
		document.getElementById('zbank').style.border = '0px solid #FF0000'; 
	}
	
	if(bankZh == null || bankZh == "" ){	//银行帐号
		document.getElementById('bankZh').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写银行帐号");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写银行帐号'});
		}
	}else{
		document.getElementById('bankZh').style.border = '0px solid #FF0000'; 
	}
	
	if(bankName == null || bankName == "" ){	//户名
		document.getElementById('bankName').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写户名");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写户名'});
		}
	}else{
		document.getElementById('bankName').style.border = '0px solid #FF0000'; 
	}
	
	
	
	var info = util.POST("/saveVerified.do",{"userId":userId,"xueli":xueli,"professional":professional,
													"school":school,"teaching":teaching,"goodAt":goodAt,
													"coachType":coachType,"idcard_no":idcard_no,
													"unitNames":unitName,"begin_times":begin_time,"end_times":end_time,
													"bank":bank,"zbank":zbank,"bankZh":bankZh,"bankName":bankName});
	try{
		if(window.hideTab){
			window.commit.saveFinish();
	    }else{
	    	return info;
	    }
	}catch (e) {
		cloud.explain(info.msg);
	}
}
	
function jqchk() {
  var chk_value = [];
  $('input[name="services"]').each(function () {
    if ($(this).val() != null && $(this).val() != "") {
      chk_value.push($(this).val());
    }
  });
  if (chk_value.length > 0) {
    $("#sev").val(chk_value);
  }
}

/**
 * 保存教学设置
 */
function saveTeaching() {
  jqchk();
  var services = document.getElementById('sev').value;
  var professional = document.getElementById('professional').value;
  var equipment = document.getElementById('equipment').value;
  var userId = document.getElementById('userId').value;

  var oBox1 = document.getElementById('lz-aboveArea-box');
  var oBox2 = document.getElementById('lz-abovePlay-box');
  var oBox3 = document.getElementById('lz-aboveCoachPlay-box');
  var area = oBox1.getElementsByTagName('input');
  var play = oBox2.getElementsByTagName('input');
 
  var chk_value1 = "";
  var chk_value2 = "";
  var chk_value3 = "";
  for (var i = 0; i < area.length; i++) {
    chk_value1 += area[i].value + ",";
  }
  for (var i = 0; i < play.length; i++) {
    chk_value2 += play[i].value + ",";
  }
  if(oBox3 != null || typeof(oBox3) == "undefined" ){
	  var coachPlay = oBox3.getElementsByTagName('input');
	  for (var i = 0; i < coachPlay.length; i++) {
	    chk_value3 += coachPlay[i].value + ",";
	  }
  }

  var info = util.POST("/saveTeaching.do", {
    "userId": userId,
    "equipment": equipment,
    "professional": professional,
    "services": services,
    "area": chk_value1,
    "play": chk_value2,
    "coachPlay": chk_value3
  });
  try {
    if(window.hideTab){
    	 window.commit.saveFinish1();
    }else{
    	return info;
    }
  } catch (e) {
    cloud.explain(info.msg);
  }
}

function checkTea(value, id) {
  if (id == 4) {
    if ($(value).attr("class").indexOf("yes") == -1) {
      $("#teaBlock").css('display', 'none');
    } else {
      $("#teaBlock").css('display', 'block');
    }
  }
}

/* 选择上门区域 */
lz.aboveArea = function (_this, id) {

  var oBox = document.getElementById('lz-aboveArea-box');
  var aInput = oBox.getElementsByTagName('input');
  for (var i = 0; i < aInput.length; i++) {
    if (aInput[i].value == id) {
      document.getElementById('lz-aboveArea').style.display = 'none';
      return cloud.explain('你已经选过了，不能再选！');
    }
  }

  var createLiTag = document.createElement('li');
  createLiTag.innerHTML = '<span class="lz-left">\
    <span class="lz-checked-2">\
      <i class="lz-show"></i>\
      </span>' + _this.innerHTML + '\
      </span>\
      <span class="lz-right" onclick="lz.removeArea(this);"><i class="lz-iconfont lz-icon-guanbi"></i></span></span>\
      <input type="hidden" name="area" value="' + id + '">';
  createLiTag.dataset.id = id;
  oBox.appendChild(createLiTag);
  document.getElementById('lz-aboveArea').style.display = 'none';
}

/* 选择上门场馆 */
lz.abovePlay = function (_this, id) {

  var oBox = document.getElementById('lz-abovePlay-box');
  var aInput = oBox.getElementsByTagName('input');
  for (var i = 0; i < aInput.length; i++) {
    if (aInput[i].value == id) {
      document.getElementById('lz-abovePlay').style.display = 'none';
      return cloud.explain('你已经选过了，不能再选！');
    }
  }
  var createLiTag = document.createElement('li');
  createLiTag.innerHTML = '<span class="lz-left">\
    <span class="lz-checked-2">\
      <i class="lz-show"></i>\
      </span>' + _this.innerHTML + '\
      </span>\
      <span class="lz-right" onclick="lz.removeArea(this);"><i class="lz-iconfont lz-icon-guanbi"></i></span></span>\
      <input type="hidden" name="play" value="' + id + '">';

  oBox.appendChild(createLiTag);
  document.getElementById('lz-abovePlay').style.display = 'none';
}

function checkPlay(status,type){
	if(type == 3){
		return cloud.explain('不好意思,您是运营商,已经是场馆管理者了');
	}else if(type == 2){
		return cloud.explain('您已经是驻场教练了');
	}else{
		//如果状态是待审核和拒绝就不能选择
		if (status == 1 ) {
			return cloud.explain('您已经申请,请耐心等待');
		}else if(status == 3){
			return cloud.explain('您已经是驻场教练了');
		}
		var oBox = document.getElementById('lz-aboveCoachPlay-box');
		var aInput = oBox.getElementsByTagName('input');
		for (var i = 0; i < aInput.length; i++) {
		   if (aInput[i].dataset.status == 1) {
			return cloud.explain('您已经申请,请耐心等待');
		   } 
		}
		document.getElementById('lz-aboveCoachPlay').style.display = 'block';	
	}
	
}

/* 自由教练选择驻场场馆 */
lz.aboveCoachPlay = function (_this, id) {
	
	
  var oBox = document.getElementById('lz-aboveCoachPlay-box');
  var aInput = oBox.getElementsByTagName('input');
  for (var i = 0; i < aInput.length; i++) {
    if (aInput[i].value == id) {
      document.getElementById('lz-aboveCoachPlay').style.display = 'none';
      return cloud.explain('你已经选过了，不能再选！');
    } 
  }
  
  var createLiTag = document.createElement('li');
  createLiTag.innerHTML = '<span class="lz-left">\
    <span class="lz-checked-2">\
      <i class="lz-show"></i>\
      </span>' + _this.innerHTML + '\
      </span>\
    <span class="lz-right" onclick="lz.removeArea(this);">待审核<i class="lz-iconfont lz-icon-guanbi"></i></span></span>\
   <input type="hidden" data-status="1" name="play" value="' + id + '">';

  oBox.appendChild(createLiTag);
  document.getElementById('lz-aboveCoachPlay').style.display = 'none';
  
  var iBottom = createLiTag.getBoundingClientRect().bottom;

  window.scrollTo(window.scrollX, window.scrollY + iBottom);
}

/**
 * 保存身份认证
 */
function saveAuth_tion(){
	var userId = document.getElementById('userId').value; 
	var idcard_no = document.getElementById('idcard_no').value;
	if($("#img1").attr("src") ==null || $("#img1").attr("src") == "/" || $("#img1").attr("src") =="" ){
		$("#img1erroe").html("<strong style='color:red;'>请上传身份证正面照</strong>");
		if(window.hideTab){
			window.hideTab.toast("请上传身份证正面照");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请上传身份证正面照'});
		}
	}else{
		$("#img1erroe").html("");
	}
	if($("#img2").attr("src") ==null || $("#img2").attr("src") == "/" || $("#img2").attr("src") =="" ){
		$("#img2erroe").html("<strong style='color:red;'>请上传身份证反面照</strong>");
		if(window.hideTab){
			window.hideTab.toast("请上传身份证反面照");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请上传身份证反面照'});
		}
	}else{
		$("#img2erroe").html("");
	}
	
	if(idcard_no == null || idcard_no == "" ){	//身份证
		document.getElementById('idcard_no').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("请填写身份证");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '请填写身份证'});
		}
	}else{
		document.getElementById('idcard_no').style.border = '0px solid #FF0000'; 
	}
	var reg1 =/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	var reg2 =/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
	if(reg1.test(idcard_no) != true && reg2.test(idcard_no) != true){
		document.getElementById('idcard_no').style.border = '1px solid #FF0000'; 
		if(window.hideTab){
			window.hideTab.toast("身份证格式不对");
			return;
		}else{
			return JSON.stringify({data: '',status:-1,msg: '身份证格式不对'});
		}
	}else{
		document.getElementById('idcard_no').style.border = '0px solid #FF0000'; 
	}
	var info = util.POST("/saveAuth_tion.do",{"userId":userId,"idcard_no":idcard_no});
	try {
		if(window.hideTab){
			window.hideTab.saveFinish2();
	    }else{
	    	return info;
	    }
	} catch (e) {
		cloud.explain(info.msg);
	}
}

/**
 * 认证身份证 正反面
 */
function auth_tion(type,id) {
  try {
	  if(window.hideTab){
	    	return	window.hideTab.showView(type);
	    }
		
		 //ios上传	
		  var element = document.createElement('input');
		  element.type = 'file';
		  element.click();
		  element.onchange = function () {
		    var fd = new FormData();
		    var img = "/auth_tion_img.do";
		    fd.append('imgName', element.files[0]);
		    fd.append('type', type);
		    fd.append('userId', document.getElementById('userId').value);
		    var xhr = new XMLHttpRequest();
		    xhr.open('post', img, false);
		    xhr.setRequestHeader('X-Request-With', 'XMLHttpRequest');
		    xhr.onload = function (e) {
		      if (xhr.status == 200) {
		        var data = JSON.parse(xhr.responseText);
		        if (data && data.data != '') {
		        	document.getElementById(id).src = data.data;
		        } else {
		          cloud.explain('上传出错');
		        }
		      }
		    };
		    xhr.send(fd); //发送文件
		  }
		  
		  
  } catch (e) {
    // TODO: handle exception
  }
}

/**
 * 显示 身份证 正反面
 */
function changeIdCard(type, url) {
  if (type == 1) {
    $("#img1").attr("src", "/" + url);
  } else if (type == 2) {
    $("#img2").attr("src", "/" + url);
  }
}

/**
 * 邀请检查手机
 * @returns {Number}
 */
function checkfindPhone(){
	var info = util.POST("/checkfindPhone.do",{"phone":$("#invite_id").val()});
	if($("#invite_id").val() == $("#userPhone").val()){
		window.hideTab.toast("不能填写自己的手机号码");
		document.getElementById('invite_id').style.border = '1px solid #FF0000';
		return 1;
	}
	
	if(info.status != 0){
		window.hideTab.toast("您填写的手机号码不存在");
		document.getElementById('invite_id').style.border = '1px solid #FF0000';
		return 1;
	}else{
		document.getElementById('invite_id').style.border = '0px solid #FF0000';
		return 0;
	}
	
}


/**
 * 邀请检查手机IOS
 * @returns {Number}
 */
function checkfindPhone_IoS(){
	var info = util.POST("/checkfindPhone.do",{"phone":$("#invite_id").val()});
	if($("#invite_id").val() == $("#userPhone").val()){
		document.getElementById('invite_id').style.border = '1px solid #FF0000';
    	return {data: '',status:-1,msg: '不能填写自己的手机号码'};
	}
	
	if(info.status != 0){
		document.getElementById('invite_id').style.border = '1px solid #FF0000';
		return {data: '',status:-1,msg: '您填写的手机号码不存在'};
	}else{
		document.getElementById('invite_id').style.border = '0px solid #FF0000';
		return {data: '',status:0,msg: ''};
	}
	
}

function beijian(){
	
	
	if(window.hideTab){
		return window.hideTab.show();
	}
	//ios上传	
	  var element = document.createElement('input');
	  element.type = 'file';
	  element.click();
	  element.onchange = function () {
	    var fd = new FormData();
	    var img = "/coach_user_background_picture.do";
	    fd.append('imgName', element.files[0]);
	    fd.append('userId', document.getElementById('userId').value);
	    var xhr = new XMLHttpRequest();
	    xhr.open('post', img, false);
	    xhr.setRequestHeader('X-Request-With', 'XMLHttpRequest');
	    xhr.send(fd); //发送文件
	  }
}
