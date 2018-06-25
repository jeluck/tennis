function upload(element,type) {

    if (!window.FormData) {
      return alert('请换一个支持H5的浏览器');
    }
    var fd = new FormData();
    fd.append('imgName', element.files[0]);

    var xhr = new XMLHttpRequest();
    
    var folder = ""; 
    if(type == 1){
    	folder = "coach";
    }else if(type == 2){
    	folder = "playpround";
    }else if(type == 3){
    	folder = "certificate";
    }else if(type == 4){
    	folder = "events";
    }
    
    xhr.open('post', '/pgm/upload_picture.do?folder='+folder+'&type='+type+'', true);
    xhr.send(fd); //发送文件
    xhr.onload = function(e) {
      if (this.status == 200) {
    	  var obj = $.parseJSON(this.responseText);
    	  element.parentNode.getElementsByTagName('input')[1].value = obj.data;
      }
    };
  }
  
  function add (el,name1,name2,flag){
	  var result = true;
	  if(flag == 'zhuban'){
	    $("input[name='zhubanImg']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请上传主办单位图片");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }
	    $("input[name='zhubanInput']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请输入主办单位名称");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }
	  }else if(flag == 'chengban'){
		$("input[name='chengbanImg']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请上传承办单位图片");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }
	    $("input[name='chengbanInput']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请输入承办单位名称");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    } 
	  }else if(flag == 'xieban'){
		$("input[name='xiebanImg']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请上传协办单位图片");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }
	    $("input[name='xiebanInput']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请输入协办单位名称");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }  
	  }
	  var div = document.createElement('div');
	  
	  div.innerHTML = '\
			<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>\
			<div class="col-sm-4" >\
				<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />\
				<input type="hidden" name="' + name1 + '">\
			</div>\
			<div class="col-sm-6">\
				名称：<input type="text" name="' + name2 + '" >\
				<i class="lz-iconfont lz-icon-guanbi" onclick="removeExper(this);"></i>\
			</div>\
		';
	  div.className = 'row';
	  el.parentNode.appendChild(div);
	  el.parentNode.appendChild(el);
  }
  
  /*
   *删除区域
   */
  function removeExper(_this) {

  var oLi = _this.parentNode.parentNode;
  var oUl = oLi.parentNode;
  oUl.removeChild(oLi);
  }
  
  
  function addBusiness(el,name,flag){
	  var result = true;
	  if(flag == 'hez'){
	    $("input[name='hezImg']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请上传合作媒体图片");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }
	  }else if(flag == 'zanz'){
		$("input[name='zanzImg']").each(function (y) {
	      if ($(this).val() == null || $(this).val() == "") {
	    	alert("请上传赞助商图片");
	        result = false
	        return result;
	      }
	    });
	    if (result == false) {
	      return false;
	    }
	  }
	  var div = document.createElement('div');
	  div.innerHTML = '\
			<div class="col-sm-2" >&nbsp;&nbsp;&nbsp;&nbsp;图片</div>\
			<div class="col-sm-6" >\
				<input type="file" class="input_text"  style="width: 200px;" onchange="upload(this, 4);" />\
				<input type="hidden" name="'+name+'" >\
			</div>\
			<div class="col-sm-2" ><i class="lz-iconfont lz-icon-guanbi" onclick="removeExper(this);"></i></div>\
	  ';
	  div.className = 'row';
	  el.parentNode.appendChild(div);
	  el.parentNode.appendChild(el);
  }

  function checkForm()
  {
	  if($("#times").val()==null || $("#times").val()==""){
		alert("请填写比赛时间")
		return false;
	  }
	  if($("#begin_time").val()==null || $("#begin_time").val()==""){
			alert("请填写开始时间")
			return false;
		}
		if($("#end_time").val()==null || $("#end_time").val()==""){
			alert("请填写结束时间")
			return false;
		}
    	var result = true;
      $("input[name='zhubanImg']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请上传主办单位图片");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }
      $("input[name='zhubanInput']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请输入主办单位名称");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }
  	$("input[name='chengbanImg']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请上传承办单位图片");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }
      $("input[name='chengbanInput']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请输入承办单位名称");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      } 
  	$("input[name='xiebanImg']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请上传协办单位图片");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }
      $("input[name='xiebanInput']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请输入协办单位名称");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }  
      $("input[name='hezImg']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请上传合作媒体图片");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }
  	$("input[name='zanzImg']").each(function (y) {
        if ($(this).val() == null || $(this).val() == "") {
      	alert("请上传赞助商图片");
          result = false
          return result;
        }
      });
      if (result == false) {
        return false;
      }
  	return true;
  }
  
  function clert(_obj){
		if(input.value != null && input.value != "" && input.value > 0){
			_obj.parentNode.getElementsByTagName('span')[0].innerHTML ="";
		}else{
			_obj.parentNode.getElementsByTagName('span')[0].innerHTML ="请输入金额";
		}
	}
	
	
	function clearNoNum(obj){

		obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符

		obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是

		obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的

		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数

	}
