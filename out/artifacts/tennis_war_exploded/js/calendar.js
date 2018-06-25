window.addEventListener('load', function () {


  lz.initDateTab();

  var index = lz.getCookie(sLocalStorage) || 0; //获取索引
  var oTabNav = document.getElementById('lz-tab-nav');
  var aLi = oTabNav.getElementsByTagName('li');


  for (var i = 0; i < aLi.length; i++) {

    if (i != index) {
      (function (index) {
        aLi[index].addEventListener('click', function () {

          if (this.testClick) {
            return;
          }
          lz.setCookie(sLocalStorage, index);
          lz.initDateTab();
          this.testClick = true;
        }, false);

      })(i);
    }

  }

}, false);

lz.initDateTab = function () {

  var index = lz.getCookie (sLocalStorage) || 0; //获取索引

  new Swiper('#lz-date-' + index, {
    freeMode: true,
    slidesPerView: 'auto',
  });

  var oChild = document.getElementById('lz-hour-child-' + index);
  var aDivList = oChild.querySelectorAll('#lz-hour-child-' + index + '>div');
  for (var i = 0; i < aDivList.length; i++) {
    aDivList[i].id = 'lz-hour-child-' + index + '-' + i;
  }

  aDivList[0].style.display = 'block';
  aDivList[0].Swiper = new Swiper('#' + aDivList[0].id, {
    freeMode: true,
    slidesPerView: 'auto',
  });
  
  //预加载
  oChild.parentNode.parentNode.querySelector('.lz-date li').onclick();
}

/**
 * 选择预定的日期
 * @param {object} obj 被点击的元素
 */
lz.selectDate = function (obj) {

  var index = lz.getCookie (sLocalStorage) || 0; //获取索引
  var aLi = obj.parentNode.getElementsByTagName('li');
  var oHourBox = document.getElementById('lz-hour-box-' + index);
  var aHourBoxChild = document.querySelectorAll('#lz-hour-child-' + index + '>div');
  var index = 0;


  for (var i = 0; i < aLi.length; i++) {

    delete aLi[i].index;
    if (aLi[i] == obj) {
      index = i;
      aLi[i].index = i;
    }

    lz.removeClass(aLi[i], 'cur');
    aHourBoxChild[i].style.display = 'none';
  }

  aLi[index].className += ' cur'
  oHourBox.className = 'lz-hour lz-show';
  aHourBoxChild[index].style.display = 'block';


  if (!aHourBoxChild[index].Swiper) {

    aHourBoxChild[index].id = 'lz-hour-child' + index + '-' + index;
    aHourBoxChild[index].Swiper = new Swiper('#' + aHourBoxChild[index].id, {
      freeMode: true,
      slidesPerView: 'auto',
    });
  }

}

lz.siteSelection = function () {
  cloud.explain('弹出来');
}

/**
 * is_time是否是预约时段 
 */
lz.schedule = function (obj, month, day, activeId, week, belong, coachType, year, is_time) {
  var str = '';
  var p = '';
  var timepoint = $("#lz-list");
  if (is_time == 1) {
    timepoint = $("#lz-list-1");
  }
  //var reservation = $("#lz-hour-child");
  var tpoint = null;
  if (coachType != 2 && coachType != 3) {
    if (week == '星期六' || week == '星期天') {
      tpoint = util.POST("/ctimepointafter.do", {
        "coachId": activeId,
        "status": 2,
        "coachType": coachType
      });
    } else {
      tpoint = util.POST("/ctimepointafter.do", {
        "coachId": activeId,
        "status": 1,
        "coachType": coachType
      });
    }
    console.log(tpoint.data);
    $.each(tpoint.data, function (n, value) {
      p += "<li>" + value.time + ":00</li>";
    });
    timepoint.html(p);
  }

  var rdate = year + "-" + month + "-" + day;

  if (coachType == 3) {
	  var info = util.POST("/getCoachspace.do", {
	    	"activeId":activeId,
	        "date": year+"-"+month + "-" + day
	      });
	      str += "<div class='swiper-wrapper lz-type'>";
	      $.each(info.data.spaceList, function (n, value) {
	        
	          str += " <div class='swiper-slide lz-list'>" +
	            "<h3>" + value.name + "</h3>" +
	            "<ul>";
	          
	          $.each(value.stpList, function (n, v) {
	            $.each(info.data.cstList, function (n, c) {
		              if (c.time == v.time) {
		            	  if(is_time==0){
		            		  //0表示颗预约，1表示已预约，2表示不可预约
		              		if (v.is_reserve == 0) {
		                          str += "<li onclick='lz.changSchedule(this,\"" + v.time + "\",\"" + week + "\",\"" + value.id + "\",\"" + v.id + "\",1); ' class='lz-bg-false'></i></li>"
		                          return false;
		                        } else if (v.is_reserve == 1) {
		                          str += "<li  class='lz-bg-true' onclick='lz.inTheCoach(\"" + c.orderInfoNo + "\")'></i></li>";//edit by lxc	2015-12-31
		                          return false;
		                        } else {
		                          str += "<li onclick='lz.changSchedule(this,\"" + v.time + "\",\"" + week + "\",\"" + value.id + "\",\"" + v.id + "\",1);'></li>";
		                          return false;
		                        }
		              		
		              		
		              	}else{
		              		if (v.is_reserve == 0) {
		                          str += "<li  class='lz-bg-yes'></i></li>";
		                        return false;
		                        } else if (v.is_reserve == 1) {
		                          str += "<li class='lz-bg-false'></i></li>";
		                        	  return false;
		                        } else {
		                          str += "<li></li>";
		                          return false;
		                        }
		              		
		              	}
		              }
	            })

	          })

	          str += "</ul>" +
	            "</div>";
	       
	      });
	      str += "</div>";
	     

	      $.each(info.data.timepoint, function (n, value) {
	        p += "<li>" + value + ":00</li>";
	      });
	      timepoint.html(p);
    
  } else if (coachType == 2) { 
      var info = util.POST("/getin_space.do", {
    	"activeId":activeId,
        "date": year+"-"+month + "-" + day
      });
      str += "<div class='swiper-wrapper lz-type'>";
      $.each(info.data.spaceList, function (n, value) {
        
          str += " <div class='swiper-slide lz-list'>" +
            "<h3>" + value.name + "</h3>" +
            "<ul>";
          
          $.each(value.stpList, function (n, v) {
            $.each(info.data.cstList, function (n, c) {
            	 //0表示颗预约，1表示已预约，2表示不可预约
            	  if (v.is_reserve == 1 && c.time==v.time) {
	                str += "<li class='lz-bg-true' onclick='lz.inTheCoach(\"" + c.orderInfoNo + "\")'></i></li>";
	                return false;
	              }
	              if (v.is_reserve == 0  && c.time==v.time) {
	                str += "<li class='lz-bg-false'></li>";
	                return false;
	              }
	              if (v.is_reserve == 2  && c.time==v.time) {
	                str += "<li></li>";
	                return false;
	              }
            })

          })

          str += "</ul>" +
            "</div>";
       
      });
      str += "</div>";
     

      $.each(info.data.timepoint, function (n, value) {
        p += "<li>" + value + ":00</li>";
      });
      timepoint.html(p);
  } else {
    if (!obj.post) {
	freetpoint = util.POST("/freetimepoint.do", {
        "coachId": activeId,
        "date": year+"-"+month + "-" + day,
        "coachType": coachType,
        "belong":2
      });
      str += "<div class='swiper-wrapper lz-type'>";
      str += " <div class='swiper-slide lz-list'>" +
        "<h3></h3>" +
        "<ul>";
      $.each(freetpoint.data, function (n, value) {
        
        if (is_time == 0) {
          if (value.status==1) {
            str += "<li class='lz-bg-false' onclick='lz.changSchedule(this,\"" + value.time + "\",\"" + week + "\",\"" + value.coach_id.id + "\",0,2);'></li>"
          } else if (value.status==0) {
            str += "<li onclick='lz.changSchedule(this,\"" + value.time + "\",\"" + week + "\",\"" + value.coach_id.id + "\",0,2);'></li>";
          } else {
            str += "<li class='lz-bg-true' onclick='lz.freeCoach(\"" + value.orderInfoNo + "\")'></li>";
          }
        } else {
          if (value.status==1) {
            str += "<li class='lz-bg-false' onclick=''></li>";
          } else if (value.status==0) {
            str += "<li onclick=''></li>";
          } else {
            str += "<li class='lz-bg-true'></li>";
          }
        }
      });
      str += "</ul>" +
        "</div>";
      str += "</div>";
    }
  }
  var tabIndex = lz.getCookie (sLocalStorage) || 0; //获取索引

  var aLi = obj.parentNode.getElementsByTagName('li');
  var oHourBox = document.getElementById('lz-hour-box-' + tabIndex);
  var aHourBoxChild = document.querySelectorAll('#lz-hour-child-' + tabIndex + '>div');


  if (is_time == 1) {
    oHourBox = document.getElementById('lz-hour-box-' + tabIndex);
    aHourBoxChild = document.querySelectorAll('#lz-hour-child-' + tabIndex + '>div');
  }

  oHourBox.dataset.log = '第' + tabIndex + '个是我';
  
  
  for (var i=0;i<aHourBoxChild.length;i++) {
    aHourBoxChild[i].dataset.log = '第' + tabIndex + ':' + i + '个是我';
  };
  
  
  var index = 0;


  for (var i = 0; i < aLi.length; i++) {

    delete aLi[i].index;
    if (aLi[i] == obj) {
      index = i;
      aLi[i].index = i;
    }

    lz.removeClass(aLi[i], 'cur');
    aHourBoxChild[i].style.display = 'none';
  }
  
  
  if (!obj.post) {
    aHourBoxChild[index].innerHTML = str;
  }
  aLi[index].className += ' cur'
  oHourBox.className = 'lz-hour lz-show';
  aHourBoxChild[index].style.display = 'block';
  aHourBoxChild[index].dataset.log = '当前设置的是你';


//  if (!aHourBoxChild[index].Swiper) {

    aHourBoxChild[index].id = 'lz-hour-child-' + tabIndex + '-' + index;

    aHourBoxChild[index].Swiper = new Swiper('#' + aHourBoxChild[index].id, {
      freeMode: true,
      slidesPerView: 'auto',
    });
//  }

  obj.post = true;
}

/**
 * 驻场教练查看订单
 */
lz.inTheCoach = function(orderInfoNo){
	window.location.href='/inTheCoachOrder.do?orderInfoNo='+orderInfoNo;
}

/**
 * 自由教练查看订单
 */
lz.freeCoach = function(orderInfoNo){
	window.location.href='freeCoachOrder.do?orderInfoNo='+orderInfoNo;
}

lz.changSchedule = function (obj, timepoint, week, activeId, stpId, belong) {
  var sche = util.POST("/changSche.do", {
    "timepoint": timepoint,
    "week": week,
    "activeId": activeId,
    "stpId": stpId,
    "belong": belong
  });
 
  if (sche.data == '成功') {
    $(obj).removeClass();
    //$(obj).html("");
  } else if (sche.data == "删除") {
    $(obj).addClass("lz-bg-false");
    //$(obj).html("隐藏的");
  } else {
    cloud.explain("更改失败,请联系管理员");
  }
};

/**
 * 保存教练带人数量
 */
lz.saveperson = function (element, coachId) {

  var oUl = element.parentNode.parentNode;
  var aLi = oUl.querySelectorAll('[data-people_num]');
  var iPeopleNum = parseInt(document.getElementById('personNum').value);
  var iPrice = Math.abs(parseFloat(document.getElementById('price').value).toFixed(2));
  for (var i=0;i<aLi.length;i++) {
    if (parseInt(aLi[i].dataset.people_num) == iPeopleNum) {
      return cloud.explain('1对' + iPeopleNum + '已存在');
    }
  }

  
  var saveStatus = util.POST("/savePerson.do", {
    "coachId": coachId,
    "people_num": $("#personNum").val(),
    "price": iPrice
  });
  if (saveStatus.data == '成功') {
    var ctpList = util.POST("/getPersonByCoachId.do", {
      "coachId": coachId
    });
    var str = "<li class='lz-tit'><span class='lz-left'><span class='lz-checked-2' onclick='lz.setCheckedRadio(this, '#lz-setcharge-value', 'yes', 'no');'> <i class='lz-show'></i>" +
      "<input id='lz-setcharge-value' type='hidden' value='yes'>" +
      "</span> 统一收费</span><span class='lz-right' onclick='lz.saveperson(this, " + coachId + ")'>保存</span>" +
      "</li>";
    $.each(ctpList.data, function (n, value) {
      str += "<li data-people_num=\"" + value.people_num + "\"><span class='lz-left' onclick='deletePer(this," + value.id + ")'><i class='lz-iconfont lz-icon-huishouzhan'></i> 1对" + value.people_num + "</span><span class='lz-right'>" + value.price + "/小时</span></li>";
    })
    str += "<li><span class='lz-left'><i class='lz-iconfont lz-icon-huishouzhan lz-transparent'></i> 1对<span class='lz-operations' style='margin-left:0.05rem;'>" +
      "<input type='text' placeholder='' value='' id='personNum'>" +
      "</span> </span><span class='lz-right'>" +
      "<input type='number' placeholder='' value='' id='price'>" +
      "元/小时</span>" +
      "</li>";

    $("#lz-setcharge").html(str);
  } else {
    cloud.explain("请查看请输入的值是否正确！");
  }
};

function deletePer(obj, perId) {
  var delPer = util.POST("/delete_ctp.do", {
    "id": perId
  });
  if (delPer.data = '成功') {
    obj.parentNode.parentNode.removeChild(obj.parentNode);
  } else {
    cloud.explain("请联系管理员");
  }
}
