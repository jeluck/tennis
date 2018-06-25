function clearNoNum(obj){

	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符

	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是

	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的

	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数

}
/**
 * 添加单位名称
 * @param {object} obj 被点击的元素
 */
function addDate (_this) {
    var result = true;
    $("input[name='unitName']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
    	alert("请输入单位名称");
        result = false
        return result;
      }
    });
    if (result == false) {
      return false;
    }

    $("input[name='begin_time']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
    	alert("请选择开始时间");
        result = false
        return false;
      }
    });
    if (result == false) {	
      return false;
    }

    $("input[name='end_time']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
    	alert("请选择结束时间");
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

    var aLi = oUl.getElementsByTagName('li');

    var oAdd = aLi[0];
    
    if (!oAdd) {
      aLi = oUl.getElementsByTagName('li');
      oAdd = aLi[2];
      oAdd.dataset.info = 'my';
    }
  
    var oLi = document.createElement('li');
    oLi.className = 'lz-addunit';
    oLi.innerHTML = '\
    <div class="row" >\
    <div class="col-md-3"><input type="text" name="unitName" placeholder="单位名称" value=""></div>\
    <div class="col-md-3"><input type="text" placeholder="开始时间" onfocus="WdatePicker({isShowClear:false,dateFmt:\'yyyy-MM-dd\',readOnly:true})"style="width: 150px;" name="begin_time" /></div>\
    <div class="col-md-3"><input type="text" placeholder="结束时间" onfocus="WdatePicker({isShowClear:false,dateFmt:\'yyyy-MM-dd\',readOnly:true})"style="width: 150px;" name="end_time" /></div>\
    <div  class="col-md-3" onclick="removeExper(this);" style="text-align: right;"><i class="lz-iconfont lz-icon-guanbi"></i></div>\
  </div>\
    ';
    oUl.appendChild(oLi);
//    lz.mobiscroll();
  }
/*
 *删除区域
 */
function removeExper(_this) {

var oLi = _this.parentNode.parentNode;
var oUl = oLi.parentNode;
oUl.removeChild(oLi);
}


//自由教练是不绑定场馆的
function belongtopg(o){
	if(o.value==1){
		$("#belongtopg").css("display","none");
		$("#playground_id").val(0);
	}else{
		$("#belongtopg").css("display","");
	}
}

function getCity(id){
	var province = $("#"+id).val();
	var user_city = $("#user_city");
	var info = util.POST("/city.do",{"province":province});
	var str ='<option value="">请选择</option>';
	
	var user_area = $("#user_area");
	user_area.html(str);
	
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_city.html(str);
}
function getArea(id){
	var city = $("#"+id).val();
	var user_area = $("#user_area");
	var info = util.POST("/area.do",{"city":city});
	var str ='<option value="">请选择</option>';
	$.each(info.data,function(n,value) {  
         str+="<option value="+value.region_id+">"+value.region_name+"</option>";
    });  
	user_area.html(str);
}
function jqchk(){  //jquery获取复选框值    
	  var chk_value =[];    
	  $('input[name="service"]:checked').each(function(){    
	   chk_value.push($(this).val());    
	  });
	  if(chk_value.length>0){
		  $("#kk").val(chk_value);    
	  }
}


function checkForm()
{
	jqchk();
    var result = true;
    $("input[name='unitName']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
    	alert("请输入单位名称");
        result = false
        return result;
      }
    });
    if (result == false) {
      return false;
    }

    $("input[name='begin_time']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
        alert("请选择开始时间");
        result = false
        return false;
      }
    });
    if (result == false) {
      return false;
    }

    $("input[name='end_time']").each(function (y) {
      if ($(this).val() == null || $(this).val() == "") {
    	alert("请选择结束时间");
        result = false
        return false;
      }
    });
    if (result == false) {
      return false;
    } else {
      $("#error").html("");
    }

    var price = document.getElementById('price').value;
    
    if(price<=0){
    	alert("价格必须大于0");
    	return false;
    }
    var money = document.getElementById('money').value;
    if(money<=0){
    	alert("最低价必须大于0");
    	return false;
    }
	
	var bankZh = document.getElementById('bankZh').value;
	if(bankZh != null && bankZh != ""){
		if(bankZh.length<14){
			alert("银行卡账号格式不对")
			return false;
		}
	}
	
	if(checkIdcard_no()>0){
		if(checkPhone()>0){
			if($("#coachType").val()==2 || $("#coachType").val()==3){
				if($("#playground_id").val()!=null && $("#playground_id").val()!="" && $("#playground_id").val()>0){
					if($("#head_portrait").val()!=null && $("#head_portrait").val()!="" ){
						return true;
					}else{
//						alert("请选择头像");
						//时间关系  这段代码有待优化  LZY
						return true;
					}
				}else{
					alert("请选择所属场馆");
					return false;
				}
			}else{
				if($("#head_portrait").val()!=null && $("#head_portrait").val()!="" ){
					return true;
				}else{
					alert("请选择头像");
					return false;
				}
			}
		}else{
			return false;
		}
	}else{
		return false;
	}
	
}


