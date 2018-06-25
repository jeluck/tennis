/**
 * 选择预定的日期
 * @param {object} obj 被点击的元素
 */
//场馆预约时间点击
lz.selectDate = function (obj, month, day, activeId, week, belong) {
  var str = '';
  var p = '';
  var timepoint = $("#lz-list");
  //var reservation = $("#lz-hour-child");
  var tpoint = null;
  if (week == '星期六' || week == '星期天') {
    tpoint = util.POST("/timepoint.do", {
      "playgroundId": activeId,
      "status": 2
    });
  } else {
    tpoint = util.POST("/timepoint.do", {
      "playgroundId": activeId,
      "status": 1
    });
  }

  $.each(tpoint.data, function (n, value) {
    p += "<li>" + value + ":00</li>";
  });
  timepoint.html(p);
  if (!obj.post) {
    var info = util.POST("/getspace.do", {
      "activeId": activeId,
      "belong": belong
    });



    str += "<div class='swiper-wrapper lz-type'>";
    $.each(info.data, function (n, value) {
      str += " <div class='swiper-slide lz-list'>" +
        "<h3>" + value.name + "</h3>" +
        "<ul>";
      var tList = util.POST("/getspace_time_price.do", {
        "spaceId": value.id,
        "date": month + "-" + day
      });

      $.each(tList.data, function (n, v) {
        if (v.is_reserve == 0 || v.is_reserve == 2) {
          str += "<li onclick='lz.siteSelection(this,\"" + value.playground_id.id + "\",\"" + value.name + "\",\"" + v.id + "\");' class='lz-bg-false'>￥" + v.price + "</li>"
        } else {
          str += "<li onclick='lz.siteSelection(this,\"" + value.playground_id.id + "\",\"" + value.name + "\",\"" + v.id + "\");'></li>"
        }

      })

      str += "</ul>" +
        "</div>";
    });
    str += "</div>";
    //		alert(str);
  }



  //reservation.html(str);

  //alert(reservation.html());

  var aLi = obj.parentNode.getElementsByTagName('li');
  var oHourBox = document.getElementById('lz-hour-box');
  var aHourBoxChild = document.querySelectorAll('#lz-hour-child>div');
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
  document.getElementById('lz-more').className = 'lz-more lz-show';
  if (!obj.post) {
    aHourBoxChild[index].innerHTML = str;
  }

  if (!aHourBoxChild[index].Swiper) {

    aHourBoxChild[index].id = 'lz-hour-child-' + index;
    aHourBoxChild[index].Swiper = new Swiper('#' + aHourBoxChild[index].id, {
      freeMode: true,
      slidesPerView: 'auto',
    });
  }

  obj.post = true;
}

//教练预约时间点击
lz.selectcDate = function (obj, month, day, activeId, week, belong, coachType, year) {
    var str = '';
    var p = '';
    var timepoint = $("#lz-list");
    //var reservation = $("#lz-hour-child");
    var tpoint = null;
    if (week == '星期六' || week == '星期天') {
      tpoint = util.POST("/ctimepoint.do", {
        "coachId": activeId,
        "status": 2,
        "coachType": coachType
      });
    } else {
      tpoint = util.POST("/ctimepoint.do", {
        "coachId": activeId,
        "status": 1,
        "coachType": coachType
      });
    }

    $.each(tpoint.data, function (n, value) {
      p += "<li>" + value.time + ":00</li>";
    });
    var rdate = year + "-" + month + "-" + day;
    timepoint.html(p);
    if (coachType == 3) {
      if (!obj.post) {
        var info = util.POST("/getspace.do", {
          "activeId": activeId,
          "belong": belong
        });
        str += "<div class='swiper-wrapper lz-type'>";
        $.each(info.data, function (n, value) {
          str += " <div class='swiper-slide lz-list'>" +
            "<h3>" + value.name + "</h3>" +
            "<ul>";
          var tList = util.POST("/getspace_time_price.do", {
            "spaceId": value.id,
            "date": month + "-" + day
          });

          $.each(tList.data, function (n, v) {
            if (v.is_reserve == 1 || v.is_reserve == 2) {
              str += "<li></li>"
            } else {
              str += "<li onclick='lz.siteSelection(this,\"" + value.playground_id.id + "\",\"" + value.name + "\",\"" + v.id + "\");' class='lz-bg-false'>￥" + v.price + "</li>"
            }
          })

          str += "</ul>" +
            "</div>";
        });
        str += "</div>";
        //			alert(str);
      }
    } else {
      str += "<div class='swiper-wrapper lz-type'>";
      str += " <div class='swiper-slide lz-list'>" +
        "<h3></h3>" +
        "<ul>";
      $.each(tpoint.data, function (n, value) {

        var json = util.POST("/coachReserve.do", {
            "date": month + "-" + day,
            "timepoint": value.time,
            "coachId": activeId
        });
        if (!json.data.timepoint) {
            str += "<li onclick='lz.sitecSelection(this,\"" + value.coach_id.name + "\",\"" + value.id + "\",\"" + value.time + "\",\"" + rdate + "\");' class='lz-bg-false'></li>";
        } else {
            str += "<li onclick='lz.sitecSelection(this);'></li>";
        }

        var sche = util.POST("/getSche.do", {
          "timepoint": value.time,
          "week": week,
          "activeId": activeId,
          "belong": 2
        });
        var json = util.POST("/coachReserve.do", {
          "date": month + "-" + day,
          "timepoint": value.time,
          "coachId": activeId
        });
        if (json.data.timepoint || sche.data == "成功") {
          str += "<li onclick='lz.sitecSelection(this);'></li>";
        } else {
          str += "<li onclick='lz.sitecSelection(this,\"" + value.coach_id.name + "\",\"" + value.id + "\",\"" + value.time + "\",\"" + rdate + "\");' class='lz-bg-false'></li>";
        }

      });
      str += "</ul>" +
        "</div>";
      str += "</div>";
    }




    //reservation.html(str);

    //alert(reservation.html());

    var aLi = obj.parentNode.getElementsByTagName('li');
    var oHourBox = document.getElementById('lz-hour-box');
    var aHourBoxChild = document.querySelectorAll('#lz-hour-child>div');
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
    document.getElementById('lz-more').className = 'lz-more lz-show';
    if (!obj.post) {
      aHourBoxChild[index].innerHTML = str;
    }

    if (!aHourBoxChild[index].Swiper) {

      aHourBoxChild[index].id = 'lz-hour-child-' + index;
      aHourBoxChild[index].Swiper = new Swiper('#' + aHourBoxChild[index].id, {
        freeMode: true,
        slidesPerView: 'auto',
      });
    }

    obj.post = true;
  }
  /**
   * 展示或隐藏预定场地的价格表和时间段
   * @param {object} obj 被点击的元素
   */
lz.moreContent = function (obj) {

  var oMore = document.getElementById('lz-more');
  var oHourBox = document.getElementById('lz-hour-box');
  var adateLi = document.querySelectorAll('#lz-date>ul>li');
  var index = 0;


  if (oMore.className == 'lz-more lz-show') {
    oMore.className = 'lz-more lz-hide';
    oHourBox.className = 'lz-hour lz-hide';
  } else {
    oMore.className = 'lz-more lz-show';
    oHourBox.className = 'lz-hour lz-show';

    for (var i = 0; i < adateLi.length; i++) {

      if (adateLi[i].index) {
        index = i;
      }

    }
    lz.selectDate(adateLi[index]);
  }

};
/**
 * 展示或隐藏周期预定更多选择
 * @param {object} obj 被点击的元素
 */

lz.cyleToggle = function (obj, bool) {

  var oCyle = document.getElementById('lz-cyle');
  var ocycleInput = document.getElementById('cycle');
  var oRect = oCyle.getBoundingClientRect();
  var oPopups = oCyle.querySelector('.lz-popups');
  var oH3 = oCyle.querySelector('h3');
  var oSection = oCyle.parentNode.parentNode;
  var iHeight = oPopups.offsetHeight + oH3.offsetHeight; //标题+内容的高度


  if (oCyle.className == 'lz-cycle lz-hide') {

    oCyle.className = 'lz-cycle lz-show';
    oSection.style.height = iHeight + 'px';
    lz.overlays(true); //打开罩层
    oCyle.dataset.status = true;
    ocycleInput.value = true;
    $("#endTime").val($("#time").val());
  } else {
    oCyle.className = 'lz-cycle lz-hide';
    oSection.style.height = 'auto';
    lz.overlays(false); //关闭罩层
    delete oCyle.dataset.status;
    ocycleInput.value = false;
  }

  if (bool) {
    oCyle.className = 'lz-cycle lz-hide';
    oCyle.dataset.status = true;
    ocycleInput.value = true;
    oSection.style.height = 'auto';
  }
}
var space_time_priceId = [];
/**
 * 选择场地购买
 * @param {object} obj 被点击的元素
 * @
 */
lz.siteSelection = function (obj, playgroundId, name, id) {

    var bool = false;
    for (var i = 0; i < space_time_priceId.length; i++) {
      if (space_time_priceId[i] == id) {
        space_time_priceId.splice(i, 1); //删除
        bool = true;
        break;
      }

    }

    if (!bool) {
      space_time_priceId.push(id);
    }
    //alert(space_time_priceId);

    var oBooknow = document.getElementById('lz-booknow');
    var oMoneySums = document.getElementById('lz-money-sums');
    var oHourChild = document.getElementById('lz-hour-child');
    var aLi = oHourChild.getElementsByTagName('li');
    var oPlaygroundId = document.getElementById('playgroundId');
    var indexId = playgroundId + id; //设置索引唯一id标识
    var oPerson = document.getElementById('person');
    var iMoney = 0;

    var oBooked = document.getElementById('booked');
    var aBookedLi = oBooknow.getElementsByTagName('li');

    if (obj.className == 'lz-bg-true') {

      for (var i = 0; i < aBookedLi.length; i++) {

        if (indexId == aBookedLi[i].dataset.indexId) {
          oBooked.removeChild(aBookedLi[i]); //删除元素
          break;
        }
      }

      obj.className = 'lz-bg-false';
    } else if (obj.className == 'lz-bg-false') {
      var time = util.POST("/getspace_time_priceByid.do", {
        "Id": id
      });
      var t = time.data;
      var t1 = t.time + 1;
      var str = '';
      str += "<li class='swiper-slide' data-index-id='" + indexId + "'>" +
        "<div class='lz-timebox'>" +
        "<time>" + t.time + ": 00</time>" + "~" +
        "<time>" + t1 + ": 00</time>" + "</div>" + "<div class='lz-tit'> " + name + "</div>" + "  <input type='hidden' name='timeId' value='" + id + "'/>" + "</li>";

      obj.className = 'lz-bg-true';
      $("#booked").append(str);
    } else {
      //obj.innerHTML = '已售';
    }

    var coach = util.POST("/in_coach.do", {
      "playgroundId": playgroundId,
      "space_time_priceId": space_time_priceId.join(",")
    });
    var in_coach = "";
    $.each(coach.data, function (n, value) {
      var coach_teach_person = util.POST("/getPersonByCoachIdToOne.do", {
        "coachId": value.id
      });
      in_coach += "<li class='swiper-slide' onclick='lz.selectCoach(this, " + value.id + "," + coach_teach_person.data.price + "," + coach_teach_person.data.id + ")'>";
      in_coach += "<div class='lz-headimg' style='background-image:url(\"" + value.head_portrait + "\");'> <i class='lz-iconfont lz-icon-renzheng'></i> ";
      if (value.sex == '男') {
        in_coach += "<i class='lz-sex lz-iconfont lz-icon-nan'></i> </div>";
      } else {
        in_coach += "<i class='lz-sex lz-iconfont lz-icon-nv'></i> </div>";
      }

      in_coach += "<div class='lz-my'> <span class='lz-name'>" + value.name + "</span> <span class='lz-money'>￥" + coach_teach_person.data.price + "</span> </div>";
      in_coach += "</li>";
    });


    $("#swiper-wrapper").html(in_coach);
    var count = 0;
    var countMoney = 0;
    for (var i = 0; i < aLi.length; i++) {

      if (aLi[i].className == 'lz-bg-true') {
        iMoney += parseInt(aLi[i].innerHTML.match(/\d+/));

        count++;
      }
    }

    if (oPlaygroundId.value == '0') { //教练详情
      countMoney = parseInt(oPerson.dataset.money) * count;
    }

    if (iMoney > 0) {
      oBooknow.style.display = 'block';
    } else {
      oBooknow.style.display = 'none';
    }
    oMoneySums.dataset.count = count;
    oMoneySums.dataset.money = iMoney;
    oMoneySums.innerHTML = '￥' + (iMoney + countMoney);
    $("#stp").val(space_time_priceId.join(','));

  }
  /* 场馆详情，选择教练 */
lz.selectCoach = function (_this, coachId, iMoney, person) {

  var aLi = _this.parentNode.getElementsByTagName('li');
  var oSums = document.getElementById('lz-money-sums');


  for (var i = 0; i < aLi.length; i++) {

    if (aLi[i] != _this) {
      lz.removeClass(aLi[i], 'lz-cur');
    }
  }
  if (_this.className.indexOf('lz-cur') > -1) { //判断是否已经选择了教练
    lz.removeClass(_this, 'lz-cur');
    oSums.innerHTML = '￥' + oSums.dataset.money;
  } else {
    _this.className += ' lz-cur';
    oSums.innerHTML = '￥' + (parseInt(oSums.dataset.money) + (iMoney * parseInt(oSums.dataset.count))); //计算价格
  }

  $("#person").val(person);
  $("#inCoachId").val(coachId);
}

/**
 * 选择自由教练购买
 */
lz.sitecSelection = function (obj, name, id, time, date) {

  var oBooknow = document.getElementById('lz-booknow');
  var oMoneySums = document.getElementById('lz-money-sums');
  var oHourChild = document.getElementById('lz-hour-child');
  var aLi = oHourChild.getElementsByTagName('li');
  var oBooked = document.getElementById('booked');
  var aBookedLi = oBooknow.getElementsByTagName('li');
  var oPerson = document.getElementById('person');
  var iMoney = 0;

  if (obj.className == 'lz-bg-true') {


    for (var i = 0; i < aBookedLi.length; i++) {
      var iIndexId = aBookedLi[i].dataset.indexId;


      if ((id + '-' + date) == iIndexId) {
        oBooked.removeChild(aBookedLi[i]); //删除元素
        break;
      }
    }

    obj.className = 'lz-bg-false';
  } else if (obj.className == 'lz-bg-false') {

    //创建标签
    var createLiTag = document.createElement('li');
    createLiTag.className = 'swiper-slide';
    createLiTag.dataset.indexId = id + '-' + date; //给元素设置唯一id标识
    createLiTag.innerHTML = '<div class="lz-timebox">\
                <time>' + time + ': 00</time>\
                <time>' + (parseInt(time) + parseInt(1)) + ': 00</time>\
            </div>\
            <div class="lz-tit">' + name + '</div>\
            <input type="hidden" name="timeId" value="' + id + '">\
            <input type="hidden" name="date" value="' + date + '">\
        ';
    //将标签添加进去
    oBooked.appendChild(createLiTag);

    obj.className = 'lz-bg-true'; //将颜色设置为选择状态

  } else {
    //obj.innerHTML = '已售'; 
  }

  //价格计算
  iMoney = parseInt(oPerson.dataset.money) * aBookedLi.length;

  oMoneySums.innerHTML = iMoney;

  if (iMoney > 0) {
    oBooknow.style.display = 'block';
  } else {
    oBooknow.style.display = 'none';
  }
};
/**
 * 显示评论框
 */
lz.showFixedRe = function () {

  var oFixedRe = document.getElementById('lz-fixed-re');
  oFixedRe.style.display = 'block';

  if (oFixedRe.lzScroll) {
    return;
  }

  window.addEventListener('scroll', function () {
    oFixedRe.style.display = 'none'; //页面滚动，隐藏输入框
  }, false);
  oFixedRe.lzScroll = true;

}

function booked(playgroundId) {
  if ($("#playgroundId").val() == 0) {
    if ($("#person").val() == 0) {
      cloud.explain("请选择教学模式");
      return;
    }
  }
 
  //如果为空,则先登录.	add by lxc	2015-11-18
  var userId = $("#userId").val();
  if (userId == "") {
	window.pay.goToLogin();
    return;
  }

  var aInput = $("input[name='timeId']");
  var yInput = $("input[name='date']");
  var yRr = [];
  yInput.each(function (index, obj) {
    yRr.push(obj.value);
  });
  var aRr = [];

  aInput.each(function (index, obj) {
    aRr.push(obj.value);
  });
  $("#ymd").val(yRr);
  $("#stp").val(aRr);
  $("#book").submit();
  //document.getElementById("book").sumbit();
}



/**
 * 教学形式选择
 * @param {object} _this 被点击的元素
 * @param {string} id 要设置value值的元素的id
 * @param {string} value 要设置的value
 * @param {number} iMoney 教学的价格
 */
//场馆预约时间点击
lz.teachingSelect = function (_this, id, value, iMoney) {
  var arr = _this.parentNode.getElementsByTagName('li');
  var oInput = document.getElementById(id);
  var oMoney = document.getElementById('lz-money-sums');
  for (var i = 0; i < arr.length; i++) {
    arr[i].className = '';
  }
  _this.className = 'lz-cur';
  oInput.value = value;
  oInput.dataset.money = iMoney;
  $("#person").val(value);

  if (oMoney.dataset.count) {
    oMoney.innerHTML = '￥' + (parseInt(oMoney.dataset.count) * iMoney + parseInt(oMoney.dataset.money));
  }

};

