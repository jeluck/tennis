/* 预定场地和预定教练使用 */

function Reservation(setting) {

    this.element = { //存储元素
        oWeeks: document.getElementById('lz-weeks'), //选择星期时的列表总box
        aWeeksList: document.querySelectorAll('#lz-weeks>ul>li'), //一周的天数
        oMore: document.getElementById('lz-more'), //日期下面的更多按钮
        oCyle: document.getElementById('lz-cyle'), //周期按钮控制box
        oHourBox: document.getElementById('lz-hour-box'), //场地box
        oHourChild: document.getElementById('lz-hour-child'), //场地子box
        aHourChildList: document.querySelectorAll('#lz-hour-child>*'), //场地子列表
        oBooknow: document.getElementById('lz-booknow'), //立即预定box
        oMoneySums: document.getElementById('lz-money-sums'), //显示价格
        oSelectsite: document.getElementById('lz-selectsite'), //显示已选择场地的总box
        oBooked: document.getElementById('lz-booked'), //显示已选择场地的列表总box
        oSelectcoach: document.getElementById('lz-selectcoach'), //推荐教练box
        oSelectcoachUl: document.getElementById('lz-selectcoach-ul'), //推荐教练子box
        oEndTime: document.getElementById('endTime'), //周期选择，结束时间
        oFixedRe: document.getElementById('lz-fixed-re'), //评论box
        oCommentContent: document.getElementById('lz-comment-content'), //评论内容
        oCommentListBox: document.getElementById('lz-comment-list-box'), //评论列表box
        oTeaching: document.getElementById('lz-teaching'), //教学形式
        aTeachingLi: document.querySelectorAll('#lz-teaching>li'), //教学形式的列表
        oMsgcoach: document.getElementById('lz-msgcoach'), //提示选择教练是否为必选项
        aFieldList: [], //存储用户选择的场地，场馆详情和运营商会使用到
        aTimeList: [] //存储用户选择的时间，自由教练使用到 
    };

    this.number = { //存储数字
        iWeeksIndex: 0 //默认的选择星期
    };

    this.object = {
        oSelectedCoach: null, //被选中的教练
        oTeaching: this.getTeaching() //被选中的教学形式
    };

    this.array = { //存储数组
        aFieldListId: [] //存储场地被选择的id
    };

    this.formAction = setting.formAction; //存储要发给服务器数据的地址
    this.formData = setting.formData; //存储要发送给服务器的数据
    this.getFieldUrl = setting.getFieldUrl; //获取场地url
    this.jspName = setting.jspName; //模板文件的名称， incoachView.jsp 自由教练  coachView.jsp 运营商 venuesView.jsp 场馆详情

    this.init();
}
Reservation.prototype.init = function () {
    //1.非教练详情页面，隐藏推荐教练
    if (this.jspName != 'venuesView.jsp' && this.jspName != 'venuesViewAdmin.jsp') {
        this.element.oMsgcoach.parentNode.style.display = 'none';
        this.element.oSelectcoach.style.display = 'none';
        this.element.oBooknow.style.paddingTop = '0.34rem';
    }
}
Reservation.prototype.getTeaching = function () {
        var oTeaching = this.element.oTeaching;
        var oTeachingLi = this.element.aTeachingLi[0];
        var data = null;

        if (oTeaching && oTeachingLi) {
            return JSON.parse(oTeachingLi.dataset.teaching);
        } else {
            return null; //该页面没有教学形式或默认的教学形式
        }


    }
    /**
     * 选择日期后，加载或显示对应的场地页面
     * @param {object} element 用户点击的元素
     */
Reservation.prototype.selectDate = function (element) {

    this.selectDateShowIndex(lz.getIndex(element)); //显示对应的内容
    this.selectDateGetData();
    this.elementTopZero(this.element.oCyle);
}

/**
 * 用户选择星期几后，显示对应的星期的场地预定信息
 * @param {number} index Description
 */
Reservation.prototype.selectDateShowIndex = function (index) {
    var aWeeksList = this.element.aWeeksList; //一周的天数
    var oHourBox = this.element.oHourBox;
    var aHourChildList = this.element.aHourChildList; //场地一周内的预约列表box
    var oMore = this.element.oMore;

    for (var i = 0; i < aWeeksList.length; i++) {
        aWeeksList[i].className = 'swiper-slide';
        aHourChildList[i].style.display = 'none';
    }

    aWeeksList[index].className = 'swiper-slide cur';
    aHourChildList[index].style.display = 'block';
    oMore.className = 'lz-more lz-show';
    oHourBox.className = 'lz-hour lz-show';

    this.number.iWeeksIndex = index; //存储用户当前选择的星期
}

/**
 * 选择日期下面的更多按钮，操作
 */
Reservation.prototype.moreContent = function () {
    var iWeeksIndex = this.number.iWeeksIndex;
    var oMore = this.element.oMore;
    var aWeeksList = this.element.aWeeksList;
    var oHourBox = this.element.oHourBox;

    if (oMore.className == 'lz-more lz-show') {
        oMore.className = 'lz-more lz-hide';
        oHourBox.className = 'lz-hour lz-hide';
        aWeeksList[iWeeksIndex].className = 'swiper-slide';
    } else {
        oMore.className = 'lz-more lz-show';
        oHourBox.className = 'lz-hour lz-show';
        aWeeksList[iWeeksIndex].className = 'swiper-slide cur';
        this.elementTopZero(this.element.oCyle);
    }
    this.selectDateGetData();
}

/**
 * 用户选择的日期发送ajax请求获取数据
 * @returns {null} 
 */
Reservation.prototype.selectDateGetData = function () {
    var iWeeksIndex = this.number.iWeeksIndex;
    var query = this.element.aWeeksList[iWeeksIndex].dataset;
    var oHourChild = this.element.oHourChild;
    var aHourChildList = this.element.aHourChildList;
    var curBox = aHourChildList[iWeeksIndex]; //数据返回，将数据插入的div
    var curBoxId = oHourChild.id + '-' + iWeeksIndex;
    var url = this.getFieldUrl + '&date=' + query.date + '&' + new Date().getTime();
    if (curBox.swiper) {
        return curBox.dataset.info = '已经加载过数据了';
    }

    curBox.innerHTML = '<div class="ball-beat"><div></div><div></div><div></div></div>'; //显示加载动画

    //发送http请求
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
    xhr.addEventListener('readystatechange', function () {

        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                curBox.id = curBoxId;
                this.fieldTemplate(curBox, xhr.responseText);
            } else {
                curBox.innerHTML = '<div class="lz-errer"><i class="lz-iconfont lz-icon-jinggao"></i><h4>网络出错啦，请点击按钮重新加载</h4><div onclick="reservation.selectDateGetData();" class="lz-btn">重新加载</div></div>';
            }
        }

    }.bind(this), false);
}

/**
 * 场地模板
 * @param {object} element 将模板拼接到的数据添加到哪个元素
 * @param {string} text json格式的字符串
 */
Reservation.prototype.fieldTemplate = function (element, text) {

    element.innerHTML = '';

    var jData = JSON.parse(text);
    var aTimepoint = jData.data.timepoint;
    if (jData.status != 0) {
        return element.innerHTML = '服务器发生错误！';
    } else if (this.jspName == 'incoachView.jsp') { //自由教练的，另外处理
        this.freeCoachTemplate(jData.data.timepoint);
        return;
    }

    //场地模板
    var oType = document.createElement('div');
    var aSpaceList = jData.data.spaceList;

    oType.className = 'swiper-wrapper lz-type lz-type-two';

    if (aSpaceList.length == 0) { //判断如果没有场地，就直接返回
        return element.innerHTML = '<div class="lz-errer"><i class="lz-iconfont lz-icon-shibaibiaoqing"></i><h4>暂无记录</h4></div>';
    }

    for (var i = 0; i < aSpaceList.length; i++) {

        var oDiv = document.createElement('div');
        var oH3 = document.createElement('h3');
        var oUl = document.createElement('ul');

        oDiv.className = 'swiper-slide lz-list';
        oH3.innerHTML = getTitleHtml(aSpaceList[i]); //aSpaceList[i].name;

        var aStpList = aSpaceList[i].stpList;
        for (var j = 0; j < aStpList.length; j++) {
            var oLi = document.createElement('li');
            oLi.dataset.indexI = i;
            oLi.dataset.indexJ = j;
            oLi.indexI = i; //将for循环i，存储到元素中
            oLi.indexJ = j; //将for循环j，存储到元素中
            this.fieldView(oLi, aSpaceList[i], aStpList[j]); //场地内容渲染和事件绑定
            oUl.appendChild(oLi);
        }


        oDiv.appendChild(oH3);
        oDiv.appendChild(oUl);

        oType.appendChild(oDiv);

    }

    //时间模板
    var oTimeBox = document.createElement('div');
    var sTimeHtml = '<ul>';

    for (var i = 0; i < aTimepoint.length; i++) {
        sTimeHtml += '<li>' + aTimepoint[i] + ':' + '00</li>\n\r';
    }

    sTimeHtml += '</ul>'
    oTimeBox.className = 'lz-time lz-time-two';
    oTimeBox.innerHTML = sTimeHtml;
    element.appendChild(oTimeBox);

    //设置场地最小高度
    var height = oTimeBox.querySelector('ul').offsetHeight;
    oTimeBox.parentNode.style.minHeight = height + 'px';

    element.aSpaceList = aSpaceList; //将对象引用保存到元素下面
    element.appendChild(oType);

    //触摸左右滑动
    element.swiper = new Swiper('#' + element.id, {
        freeMode: true,
        slidesPerView: 'auto',
        observer: true
    });

    /**
     * 处理场地标题
     * @param {object} jData 场地的数据对象
     */
    function getTitleHtml(jData) {
        var sHtml = jData.name;

        if (jData.court_type != '普通') {

            sHtml += '(' + jData.court_type + ')';
        }

        sHtml += '<div class="lz-other">' + jData.spacetype + '/' + jData.in_out + '</div>';

        return sHtml;
    }

};
/**
 * 场地内容渲染和事件绑定
 * @param {object} element 要渲染的元素
 * @param {object} oSpaceList 场地场馆
 * @param {object} oStpList 场地场馆对应的数据
 */
Reservation.prototype.fieldView = function (element, oSpaceList, oStpList) {

    switch (oStpList.is_reserve) { //0,没有预定的，1是预定了的，2不能预定的
    case 0:
        element.className = 'lz-bg-false';
        element.innerHTML = '￥' + oStpList.price;
        element.addEventListener('click', this.fieldViewClick.bind(this, element), false)
        break;
    case 1:
        element.style.background = '#ccc';
        element.innerHTML = '';
        break;
    case 2:
        element.style.background = '#ccc';
        element.innerHTML = '';
    }

}

/**
 * 场地被点击时触发的事件
 * @param {object} element 被点击的元素
 */
Reservation.prototype.fieldViewClick = function (element) {


    this.timeHandled(element, true); //助产教练同时段其他选择取消

    if (element.iFieldMoney) { //用户取消选择
        element.className = 'lz-bg-false'; //更改颜色
        for (var i = 0; i < this.element.aFieldList.length; i++) {

            if (this.element.aFieldList[i] == element) {
                this.element.aFieldList.splice(i, 1); //删除对应的场地id
                break;
            }

        }

        delete element.iFieldMoney;

    } else { //用户选择了场地    
        this.element.aFieldList.push(element);
        element.className = 'lz-bg-true'; //更改颜色
        element.iFieldMoney = true;

    }

    this.booknowBoxToggle(); //价格，显示已选择的场地计算

}

/**
 * 获取指定场地对应的数据信息
 * @param {object} element 要获取的场地元素
 * @returns {object} 返回场地元素数据
 */
Reservation.prototype.getFieldViewData = function (element) {

        var curData = element.parentNode.parentNode.parentNode.parentNode.aSpaceList[element.indexI].stpList[element.indexJ];
        return curData;
    }
    /**
     * 显示用户选择的商品box，并且计算价格
     */
Reservation.prototype.booknowBoxToggle = function () {
    var oBooknow = this.element.oBooknow;
    var oSelectsite = this.element.oSelectsite;
    var oBooked = this.element.oBooked;
    var aoBookedLi = oBooked.getElementsByTagName('li');
    var aFieldList = this.element.aFieldList;
    var _this = this;
    var aFieldListId = [];


    for (var i = 0; i < aFieldList.length; i++) {
        var jData = this.getFieldViewData(aFieldList[i]);
        templace(i, jData, aFieldList[i]);
        aFieldListId.push(jData.id);
    }

    this.removerExcessElement(aFieldList.length, aoBookedLi); //删除多余的节点

    //计算完成后显示页面

    this.showCostMoney(); //显示花费的钱
    this.array.aFieldListId = aFieldListId; //存储被选择的场地id  
    if (aFieldList.length > 0) {

        if (this.jspName == 'venuesView.jsp' || this.jspName == 'venuesViewAdmin.jsp') {
            this.recommendedCoach(); //下一步入口：推荐教练处理
        }
        oBooknow.style.display = 'block';
    } else {
        oBooknow.style.display = 'none';
    }

    this.selectsiteSwiper(); //用户选择的场地滑动特效处理

    function templace(index, data, oFieldList) {

        var oLi = document.createElement('li');
        oLi.className = 'swiper-slide lz-selected-li';

        oLi.innerHTML = '<div class="timebox"><time>' + data.time + ':00</time>~<time>' + (data.time + 1) + ':00</time></div><div class="lz-tit">' + data.space_id.name + '</div>';
        oLi.element = oFieldList;

        oLi.addEventListener('click', function () {
            _this.fieldViewClick(this.element);
        }, false);

        if (aoBookedLi[index]) { //节点已存在
            oBooked.replaceChild(oLi, aoBookedLi[index]); //替换节点

        } else { //节点不存在
            oBooked.appendChild(oLi); //插入节点
        }
    }
}

/**
 * 发送ajax请求获取推荐的教练列表
 */
Reservation.prototype.recommendedCoach = function () {
        var oSelectcoach = this.element.oSelectcoach;
        var sPlaygroundId = this.formData.playgroundId; //获取场馆id
        var sFieldListId = this.array.aFieldListId.join(','); //场地id

        if (oSelectcoach.ajaxStart) {
            return ''; //已经正在请求状态，禁止请求
        }
        oSelectcoach.ajaxStart = true;
        //发送http请求

        var url = 'in_coach.do?playgroundId=' + sPlaygroundId + '&space_time_priceId=' + sFieldListId + '&userId=' + this.formData.userId + '&' + new Date().getTime();
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send();
        xhr.addEventListener('readystatechange', function () {

            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    this.recommendedCoachTemplace(xhr.responseText);
                    delete oSelectcoach.ajaxStart;
                    if (sFieldListId != this.array.aFieldListId.join(',')) { //推荐教练请求回来后，用户又选择了其他场地，重新请求
                        this.recommendedCoach();
                    }

                }
            }

        }.bind(this), false);
    }
    /**
     * 推荐教练模板生成
     * @param {string} text json格式的字符串
     */
Reservation.prototype.recommendedCoachTemplace = function (text) {
    var oSelectcoachUl = this.element.oSelectcoachUl;
    var aSelectcoachUlLi = oSelectcoachUl.getElementsByTagName('li');
    var jData = JSON.parse(text);
    var aList = jData.data;

    this.object.oSelectedCoach = null;
    this.showCostMoney(); //显示花费的钱
    for (var i = 0; i < aList.length; i++) {

        var oLi = document.createElement('li');
        oLi.className = 'swiper-slide';
        oLi.coachData = aList[i];
        oLi.innerHTML = '\
      <div class="lz-headimg" style="background-image:url(\'/' + aList[i].head_portrait + '\');"> \
          <i class="lz-iconfont lz-icon-renzheng"></i> \
          <i class="lz-sex lz-iconfont lz-icon-' + getSex(aList[i].sex) + '"></i>\
      </div>\
      <div class="lz-my">\
        <span class="lz-name">' + aList[i].name + '</span>\
        <span class="lz-money">￥' + aList[i].ctp.price + '</span>\
      </div>';

        oLi.addEventListener('click', this.choiceCoach.bind(this, oLi), false); //事件绑定
        if (aSelectcoachUlLi[i]) { //节点已存在
            oSelectcoachUl.replaceChild(oLi, aSelectcoachUlLi[i]); //替换节点

        } else { //节点不存在
            oSelectcoachUl.appendChild(oLi); //插入节点
        }
    }

    this.removerExcessElement(aList.length, aSelectcoachUlLi); //删除多余的节点

    this.selectcoachSwiper(); //处理用户选择的场地的滑动特效

    function getSex(sex) {
        return sex == '男' ? 'nan' : 'nv';
    }

}
Reservation.prototype.selectsiteSwiper = function () {
        var oSelectsite = this.element.oSelectsite;

        //初始化或重置滑动特效
        if (!oSelectsite.swiper) {
            oSelectsite.swiper = new Swiper('#' + oSelectsite.id, {
                freeMode: true,
                slidesPerView: 'auto'
            });
        } else {
            oSelectsite.swiper.update(true);
        }
    }
    /**
     * 处理推荐教练的滑动特效
     */
Reservation.prototype.selectcoachSwiper = function () {
        var oSelectcoach = this.element.oSelectcoach;
        //处理滑动特效
        if (oSelectcoach.swiper) {
            return oSelectcoach.swiper.update(true);
        }

        oSelectcoach.swiper = new Swiper('#' + oSelectcoach.id, {
            freeMode: true,
            slidesPerView: 'auto',
            observer: true
        });
    }
    /**
     * 选择的教练
     * @param {object} _this 被点击的元素，里面存有教练信息
     */
Reservation.prototype.choiceCoach = function (_this) {
        var oUl = _this.parentNode;
        var oLi = oUl.getElementsByTagName('li');

        for (var i = 0; i < oLi.length; i++) {
            if (oLi[i] != _this) {
                lz.removeClass(oLi[i], 'lz-selected-li');
            }
        }

        if (_this.className.indexOf('lz-selected-li') > -1) {
            lz.removeClass(_this, 'lz-selected-li');
            this.object.oSelectedCoach = null;
        } else {
            _this.className += ' lz-selected-li'; //教练被选择了

            this.object.oSelectedCoach = _this.coachData; //保存被选中的教练

        }
        this.showCostMoney(); //显示花费的钱
    }
    /**
     * 关闭场地选择box
     */
Reservation.prototype.booknowClose = function () {
        this.element.oBooknow.style.display = 'none';
    }
    /**
     * 将周期预订板块移动到顶部top = 0 位置
     */
Reservation.prototype.elementTopZero = function (element) {
    var iTop = element.getBoundingClientRect().top;

    window.scrollTo(window.scrollX, window.scrollY + iTop);

}

/**
 * 显示需要花费的钱
 */
Reservation.prototype.showCostMoney = function () {
        var jspName = this.jspName;
        var oMoneySums = this.element.oMoneySums;
        var oMsgcoach = this.element.oMsgcoach;
        var count = 0;

        if (jspName == 'venuesView.jsp' || jspName == 'venuesViewAdmin.jsp') { //场馆详情价格计算
            count = this.getVenuesViewMoney();
        } else if (jspName == 'incoachView.jsp') { //自由教练价格计算
            count = this.getIncoachViewMoney();
        } else if (jspName == 'coachView.jsp') { //运营商价格价格计算
            count = this.getCoachViewMoney();
        } else if (jspName == 'inThecoachView.jsp') { //驻场教练价格计算
            count = this.getCoachViewMoney();
        }
        if (this.testVenuesViewFormData()) {
            oMsgcoach.innerHTML = '(必选项)';
        } else {
            oMsgcoach.innerHTML = '(可选项)';
        }

        oMoneySums.innerHTML = '￥' + count;
    }
    /**
     * 运营商价格计算公式：
     * 格子的价格 = （（格子的价格 + 教学形式的价格）* 格子被预定的周期）
     * 总价格 = 所有的格子的价格相加，for 循环+
     */
Reservation.prototype.getCoachViewMoney = function () {
        var aFieldList = this.element.aFieldList; //用户选择的场地
        var bool = Boolean(this.element.oCyle.dataset.status); //周期预定， true为选择了周期预定，false为不选择
        var sEndTime = this.element.oEndTime.value; //结束的时间
        var iTeachingPrice = this.object.oTeaching.price; //获取教学形式的价格
        var iConut = 0;

        if (!/^\d+(.)\d+\1\d+$/.test(sEndTime)) { //验证时间是否正确
            bool = false;
        }

        for (var i = 0; i < aFieldList.length; i++) {
            var jData = this.getFieldViewData(aFieldList[i]); //获取格子对应的数据
            var iPrice = jData.price; //格子的价格
            var sStartDate = jData.date; //格子的预定日期
            var iDayPoor = this.getDayPoor(sStartDate, sEndTime);
            var iWeek = 1; //设置为最低一周

            if (bool && (new Date(sEndTime) > new Date(sStartDate)) && iDayPoor >= 7) { //符合条件，周期时间大于周期开始时间 && 相差的天数大于或等于7
                iWeek += parseInt(iDayPoor / 7);
            }

            iConut += (iPrice + iTeachingPrice) * iWeek; //（（格子的价格 + 教学形式的价格）* 格子被预定的周期）
        }

        return iConut;
    }
    /**
     * 自由教练价格计算公式：
     * 格子的价格 = （教学形式 * 格子被预定的周期）
     * 总价格 = 所有的格子的价格相加，for 循环+
     */
Reservation.prototype.getIncoachViewMoney = function () {
        var aTimeList = this.element.aTimeList; //用户选择的场地
        var bool = Boolean(this.element.oCyle.dataset.status); //周期预定， true为选择了周期预定，false为不选择
        var sEndTime = this.element.oEndTime.value; //结束的时间
        var iTeachingPrice = this.object.oTeaching.price; //获取教学形式的价格
        var iConut = 0;

        if (!/^\d+(.)\d+\1\d+$/.test(sEndTime)) { //验证时间是否正确
            bool = false;
        }

        for (var i = 0; i < aTimeList.length; i++) {
            var jData = aTimeList[i].timepoint; //取得格子对应的数据
            var sStartDate = jData.date; //格子的预定日期
            var iDayPoor = this.getDayPoor(sStartDate, sEndTime);
            var iWeek = 1; //设置为最低一周
            if (bool && (new Date(sEndTime) > new Date(sStartDate)) && iDayPoor >= 7) { //符合条件，周期时间大于周期开始时间 && 相差的天数大于或等于7
                iWeek += parseInt(iDayPoor / 7);
            }
            iConut += iTeachingPrice * iWeek; //（教学形式 * 格子被预定的周期）
        }

        return iConut;
    }
    /**
     * 计算场地详情花费的钱
     * 场馆详情价格计算公式：
     * 格子的价格 = （（格子的价格 + 教练的价格）*格子被预定的周期）
     * 总价格 = 所有的格子的价格相加，for 循环+
     */
Reservation.prototype.getVenuesViewMoney = function () {
        var aFieldList = this.element.aFieldList; //用户选择的场地
        var bool = Boolean(this.element.oCyle.dataset.status); //周期预定， true为选择了周期预定，false为不选择
        var sEndTime = this.element.oEndTime.value; //结束的时间
        var iCoachMoney = this.object.oSelectedCoach ? this.object.oSelectedCoach.ctp.price : 0; //获取推荐教练的价格
        var iConut = 0;

        if (!/^\d+(.)\d+\1\d+$/.test(sEndTime)) { //验证时间是否正确
            bool = false;
        }
        for (var i = 0; i < aFieldList.length; i++) {
            var jData = this.getFieldViewData(aFieldList[i]); //获取格子对应的数据
            var iPrice = jData.price; //格子的价格
            var sStartDate = jData.date; //格子的预定日期
            var iDayPoor = this.getDayPoor(sStartDate, sEndTime);
            var iWeek = 1; //设置为最低一周

            if (bool && (new Date(sEndTime) > new Date(sStartDate)) && iDayPoor >= 7) { //符合条件，周期时间大于周期开始时间 && 相差的天数大于或等于7
                iWeek += parseInt(iDayPoor / 7);
            }

            iConut += (iPrice + iCoachMoney) * iWeek; //（（格子的价格 + 教练的价格）*格子被预定的周期）
        }

        return iConut;
    }
    /**
     * 计算天数差的函数，通用  
     * @param {string} sDate1 开始的时间
     * @param {string} sDate2 结束的时间
     * @returns {number} 返回相差的天数，为绝对值
     */
Reservation.prototype.getDayPoor = function (date1, date2) {
        var type1 = typeof date1,
            type2 = typeof date2;
        if (type1 == 'string')
            date1 = stringToTime(date1);
        else if (date1.getTime)
            date1 = date1.getTime();
        if (type2 == 'string')
            date2 = stringToTime(date2);
        else if (date2.getTime)
            date2 = date2.getTime();
        var iDays = parseInt(Math.abs(date1 - date2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数  
        return iDays;
    }
    //字符串转成Time(dateDiff)所需方法 
function stringToTime(string) {
    var f = string.split(' ', 2);
    var d = (f[0] ? f[0] : '').split('-', 3);
    var t = (f[1] ? f[1] : '').split(':', 3);
    return (new Date(
        parseInt(d[0], 10) || null, (parseInt(d[1], 10) || 1) - 1,
        parseInt(d[2], 10) || null,
        parseInt(t[0], 10) || null,
        parseInt(t[1], 10) || null,
        parseInt(t[2], 10) || null
    )).getTime();

}
/**
 * 周期预定弹出选择
 * @param {object} element 被点击的元素
 * @param {boolean} bool true为确定，false为取消
 */

Reservation.prototype.cyleToggle = function (element, bool) {
    var oCyle = this.element.oCyle;
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
    } else {
        oCyle.className = 'lz-cycle lz-hide';
        oSection.style.height = 'auto';
        lz.overlays(false); //关闭罩层
        delete oCyle.dataset.status;
    }

    if (bool) {
        oCyle.className = 'lz-cycle lz-hide';
        oCyle.dataset.status = true;
        oSection.style.height = 'auto';
    }

    this.elementTopZero(this.element.oCyle);
    this.booknowClose();
    this.showCostMoney(); //显示价格
}

/**
 * 表单提交，页面跳转
 */
Reservation.prototype.postFormData = function () {

        var formData = null;
        var _this = this;
        switch (this.jspName) {
        case 'incoachView.jsp':
            formData = this.getIncoachViewFormData(); //获取自由教练提交给服务器的数据
            break;
        case 'venuesView.jsp':
            if (this.testVenuesViewFormData() && !Boolean(this.object.oSelectedCoach)) { //未选择教练，直接返回
                cloud.explain('请选择教练');
                return false;
            }
            formData = this.getVenuesViewFormData(); //后台获取场馆详情提交给服务器的数据
        case 'venuesViewAdmin.jsp':
            if (this.testVenuesViewFormData() && !Boolean(this.object.oSelectedCoach)) { //未选择教练，直接返回
                alert('请选择教练');
                return false;
            }
            formData = this.getVenuesViewFormData(); //获取场馆详情提交给服务器的数据
            break;
        case 'coachView.jsp':
            formData = this.getCoachViewFormData(); //获取运营商页提交给服务器的数据
        case 'inThecoachView.jsp':
            formData = this.getCoachViewFormData(); //获取助产教练提交给服务器的数据
            break;
            break;
        }

        if (this.jspName != 'venuesViewAdmin.jsp') {
            if (!/^\d+$/.test(formData.userId)) {
                if (window.pay) {
                    window.pay.goToLogin(); //没有用户id传来，强制跳转到登录
                }

                return false;
            }
        }

        if (!formData) {
            return; //数据有错误，直接返回
        } else if (this.panduanADminOrder() == false) {

            return;
        }

        function postForm() {
            //创建提交的表单
            var oForm = document.createElement('form');
            oForm.id = 'xiadanForm';
            oForm.action = _this.formAction;
            oForm.method = 'POST';

            for (var attr in formData) {
                var oInput = document.createElement('input');
                oInput.name = attr;
                oInput.value = formData[attr];
                oForm.appendChild(oInput);
            }

            document.body.appendChild(oForm);

            //    var aInput = document.getElementsByTagName('input');
            //    
            //    for (var i=0;i<aInput.length;i++) {
            //      console.log(aInput[i].name + '=' + aInput[i].value);
            //    }

            //    $('#xiadanForm').submit();
            oForm.submit(); //提交表单
        }

        if (_this.postFormStatus) {
            return false; //已经提交，或者提交中，拦截重复提交
        }
        _this.postFormStatus = true;


        $.ajax({
            type: 'post',
            url: '/checkIs_reserve.do',
            data: formData,
            success: function (data) {
                if (data.data == '成功') {
                    postForm();
                } else {
                    //用于判断该场地是否被预订，被预订则直接刷新页面
                    location.reload(true);
                }
            },
            error: function () {
                _this.postFormStatus = false;
            }
        });

    }
    /**
     * 场馆详情验证一下是否需要选择教练， return true 为必须选择教练， false为不需要
     */

Reservation.prototype.testVenuesViewFormData = function () {
        var aFieldList = this.element.aFieldList;

        if (this.jspName != 'venuesView.jsp') {
            return false; //不是场馆详情，直接false不需要
        }

        for (var i = 0; i < aFieldList.length; i++) {
            var data = this.getFieldViewData(aFieldList[i]);

            if (data.must_coach && data.must_coach == 1) {
                return true;
            }

        }

        return false;
    }
    /**
     * 生成运营商页提交给服务器的数据
     */
Reservation.prototype.getCoachViewFormData = function () {
        var jData = {};
        var formData = this.formData;

        for (var attr in formData) {
            jData[attr] = formData[attr];
        }

        jData.cycle = Boolean(this.element.oCyle.dataset.status); //周期预定， true为选择了周期预定，false为不选择
        jData.endTime = this.element.oEndTime.value; //周期预定选择的结束时间
        jData.spacePriceId = this.array.aFieldListId.join(','); //生成场地id
        jData.person = this.object.oTeaching.id; //教学形式一对几

        return jData; //返回结果

    }
    /**
     * 生成场馆详情提交给服务器的数据
     */
Reservation.prototype.getVenuesViewFormData = function () {
        var jData = {};
        var formData = this.formData;
        var oSelectedCoach = this.object.oSelectedCoach;

        for (var attr in formData) {
            jData[attr] = formData[attr];
        }


        if (oSelectedCoach) { //判断是否选择的教练
            jData.person = oSelectedCoach.ctp.id; //教练带人id
            jData.inCoachId = oSelectedCoach.id; //选中的教练id
        }

        jData.cycle = Boolean(this.element.oCyle.dataset.status); //周期预定， true为选择了周期预定，false为不选择
        jData.endTime = this.element.oEndTime.value; //周期预定选择的结束时间
        jData.spacePriceId = this.array.aFieldListId.join(','); //生成场地的id

        return jData; //返回结果

    }
    /**
     * 生成自由教练提交给服务器的数据
     */
Reservation.prototype.getIncoachViewFormData = function () {
        var jData = {};
        var formData = this.formData;
        var aTimeList = this.element.aTimeList;
        var aYmd = [];
        var aSn = [];

        for (attr in formData) {
            jData[attr] = formData[attr]; //copy object
        }

        for (var i = 0; i < aTimeList.length; i++) {

            var oTimepoint = aTimeList[i].timepoint;
            aYmd.push(oTimepoint.date); //年月日拼接
            aSn.push(oTimepoint.id); //时间id拼接

        }

        jData.ymd = aYmd.join(','); //年月日拼接
        jData.sn = aSn.join(','); //时间的id拼接
        jData.cycle = Boolean(this.element.oCyle.dataset.status); //周期预定， true为选择了周期预定，false为不选择
        jData.endTime = this.element.oEndTime.value; //周期预定选择的结束时间
        jData.person = this.object.oTeaching.id; //教学形式

        return jData; //返回结果
    }
    /**
     * 收藏场馆或教练
     * @param {object} element 被点击的元素
     * @param {object} query 要发送给服务的数据
     */
Reservation.prototype.collection = function (element, query) {
    var sClassName = 'lz-collection-true';
    if (element.postStatus) {
        return;
    }
    element.postStatus = true;
    var oI = element.getElementsByTagName('i')[0];
    $.get('/saveCollection.do', query, function (text) {
        if (element.className.indexOf(sClassName) > -1) {
            lz.removeClass(element, sClassName);
            oI.className = 'lz-iconfont lz-icon-kongxing';
        } else {
            element.className += ' ' + sClassName;
            oI.className = 'lz-iconfont lz-icon-shixing';
        }
        delete element.postStatus;
    });
}

/**
 * 显示评论box
 */
Reservation.prototype.showCommentBox = function () {

        var oFixedRe = this.element.oFixedRe;

        oFixedRe.style.display = 'block';

    }
    /**
     * ajax 提交评论内容
     * @param {object} element 被点击的元素
     * @param {object} query 发送给服务器的数据
     */
Reservation.prototype.postComment = function (element, query) {

        if (query.userId == '') {
            if (window.pay) {
                window.pay.goToLogin(); //没有用户id传来，强制跳转到登录
            }
        }

        var oCommentContent = this.element.oCommentContent;
        var oCommentListBox = this.element.oCommentListBox;
        var oFixedRe = this.element.oFixedRe;
        var aLi = oCommentListBox.getElementsByTagName('li');
        var _this = this;

        query.context = oCommentContent.value;

        $.post('/tocomment.do', query, function (jData) {
            var data = jData.data;
            var oLi = document.createElement('li');
            oLi.className = 'lz-default';
            oLi.innerHTML = '\
      <div class="lz-headimg"><i style="background-image:url(\'' + data.weuser.head_photo + '\');"></i></div>\
      <div class="lz-content">\
          <h4><span class="lz-name">' + data.weuser.username + '</span><span><i class="lz-iconfont lz-icon-' + getSex(data.weuser.sex) + '"></i>\
                  </span>\
                  <time>' + data.create_time + '</time>\
          </h4>\
          <p>' + data.commentcontent + '</p>\
      </div>';

            if (aLi) {
                oCommentListBox.insertBefore(oLi, aLi[0]);
            } else {
                oCommentListBox.appendChild(oLi);
            }

            if (document.getElementById('notComment')) {
                document.getElementById('notComment').style.display = 'none';
            }
            oCommentContent.value = ''; //清除输入
            oFixedRe.style.display = 'none';
            _this.elementTopZero(oLi);
            delete element.postStatus;
        });


        function getSex(str) {
            return (str == '男' ? 'nan' : 'nv');
        }

    }
    /**
     * 设置周期预定的结束时间
     * @param {object} element 被点击的元素
     * @param {string} value 要设置的值
     */
Reservation.prototype.setEndTime = function (element, value) {
        var aTag = element.parentNode.getElementsByTagName('strong');
        var oEndTime = this.element.oEndTime;
        oEndTime.value = value;

        for (var i = 0; i < aTag.length; i++) {
            aTag[i].className = '';
        }

        element.className = 'on';
    }
    /**
     * 选择教学形式
     * @param {object} element 被点击的元素
     * @param {data} data 相关的信息
     */
Reservation.prototype.teachingSelect = function (element, data) {

    var aLi = element.parentNode.getElementsByTagName('li');

    for (var i = 0; i < aLi.length; i++) {
        aLi[i].className = '';
    }

    element.className = 'lz-cur';
    this.object.oTeaching = JSON.parse(element.dataset.teaching); //设置当前选中的教学形式
    this.showCostMoney();
}

/***********************************自由教练单独处理的方法***************************************/
/**
 * 自由教练模板拼装
 * @param {array} aData 列表数据
 */
Reservation.prototype.freeCoachTemplate = function (aData) {
    var iWeeksIndex = this.number.iWeeksIndex;
    var aHourChildList = this.element.aHourChildList;
    var curBox = aHourChildList[iWeeksIndex]; //数据返回，将数据插入的div

    //时间模板
    var oTime = document.createElement('div');
    var oTimeUl = document.createElement('ul');

    //类型模板
    var oType = document.createElement('div');
    var oTypeLsit = document.createElement('div');
    var oTypeLsitH3 = document.createElement('h3');
    var oTypeLsitUl = document.createElement('ul');

    for (var i = 0; i < aData.length; i++) {

        //时间模板
        var oTimeUlLi = document.createElement('li');
        oTimeUlLi.innerHTML = '<li>' + aData[i].time + ':' + '00</li>\n\r';
        oTimeUl.appendChild(oTimeUlLi);

        //类型模板
        var oTypeLsitUlLi = document.createElement('li');
        if (aData[i].flag == 0) { //0不可以预约，1是可以预约
            oTypeLsitUlLi.className = '';
        } else {
            oTypeLsitUlLi.className = 'lz-bg-false';
            oTypeLsitUlLi.addEventListener('click', this.choiceTimeClick.bind(this, oTypeLsitUlLi), false);
        }
        oTypeLsitUlLi.timepoint = aData[i];
        oTypeLsitUl.appendChild(oTypeLsitUlLi);

    }

    ///时间模板
    oTime.className = 'lz-time';
    oTime.appendChild(oTimeUl);

    //类型模板
    oType.className = 'swiper-wrapper lz-type';
    oTypeLsit.className = 'swiper-slide lz-list';
    oTypeLsit.appendChild(oTypeLsitH3);
    oTypeLsit.appendChild(oTypeLsitUl);
    oType.appendChild(oTypeLsit);

    //插入总box
    curBox.appendChild(oTime)
    curBox.appendChild(oType);

    //触摸左右滑动
    curBox.swiper = new Swiper('#' + curBox.id, {
        freeMode: true,
        slidesPerView: 'auto',
        observer: true
    });

}

/**
 * 用户选择或取消时间，
 * @param {object} element 被用户点击的元素
 */
Reservation.prototype.choiceTimeClick = function (element) {

        var oData = element.timepoint;

        if (element.testChoice) { //取消
            element.className = 'lz-bg-false';

            for (var i = 0; i < this.element.aTimeList.length; i++) {

                if (this.element.aTimeList[i] == element) {
                    this.element.aTimeList.splice(i, 1); //删除用户取消的时间
                    break;
                }

            }
            delete element.testChoice;
        } else { //选择了
            element.className = 'lz-bg-true';
            element.testChoice = true;
            this.element.aTimeList.push(element); //将选择的信息添加进去
        }

        this.choiceTimeTemplace(); //输出用户选择的
        this.showCostMoney();
    }
    /**
     * 选择时间，模板拼接
     */
Reservation.prototype.choiceTimeTemplace = function () {
        var oBooknow = this.element.oBooknow;
        var oBooked = this.element.oBooked;
        var oBookedLi = oBooked.getElementsByTagName('li');
        var aTimeList = this.element.aTimeList;

        for (var i = 0; i < aTimeList.length; i++) {

            var jData = aTimeList[i].timepoint;
            var oLi = document.createElement('li');
            oLi.relationElement = aTimeList[i]; //关联的元素
            oLi.addEventListener('click', this.choiceTimeCancel.bind(this, oLi), false);
            oLi.className = 'swiper-slide lz-selected-li';
            oLi.innerHTML = '<div class="timebox"><time>' + jData.time + ':00</time>~<time>' + (jData.time + 1) + ':00</time></div>';

            if (oBookedLi[i]) { //节点存在则替换
                oBooked.replaceChild(oLi, oBookedLi[i]);
            } else { //节点不存在，则在最后面插入
                oBooked.appendChild(oLi);
            }

        }

        this.removerExcessElement(aTimeList.length, oBookedLi); //删除多余的节点

        if (aTimeList.length > 0) {
            oBooknow.style.display = 'block';
            this.selectsiteSwiper(); //用户选择的场地滑动特效处理
        } else {
            oBooknow.style.display = 'none';
        }

    }
    /**
     * 选择取消时间
     * @param {object} element 被点击的元素
     */
Reservation.prototype.choiceTimeCancel = function (element) {
        this.choiceTimeClick(element.relationElement);
    }
    /**
     * 删除多余节点
     * @param {number} iValue 从第几个开始删除
     * @param {array} aElement 要删除的列表
     */
Reservation.prototype.removerExcessElement = function (iValue, aElement) {
    //删除多余节点
    for (var i = iValue; i < aElement.length; i++) {
        aElement[i].parentNode.removeChild(aElement[i]);
        i--;
    }
}

/***********************************助产教练单独处理的方法***************************************/
/**
 * 助产教练同时段只能预约一个场地
 * @param {element} element 被点击的元素
 * @returns {null} 没任何意义
 */
Reservation.prototype.timeHandled = function (element) {

    if (this.jspName != 'inThecoachView.jsp' && this.jspName != 'coachView.jsp') {
        return; //不是助产教练页面，则直接退出
    }

    var oType = element.parentNode.parentNode.parentNode;
    var aIndexJ = oType.querySelectorAll('[data-index-j="' + element.dataset.indexJ + '"]');

    for (var i = 0; i < aIndexJ.length; i++) {

        if (aIndexJ[i] != element && aIndexJ[i].iFieldMoney) { //不是当前点击的元素

            aIndexJ[i].className = 'lz-bg-false'; //更改颜色

            for (var j = 0; j < this.element.aFieldList.length; j++) {

                if (this.element.aFieldList[j] == aIndexJ[i]) {
                    this.element.aFieldList.splice(j, 1); //删除对应的场地id
                    break;
                }
            }

            delete aIndexJ[i].iFieldMoney;
        }
    }

}

/**********************后台预订使用的方法***********************/
Reservation.prototype.panduanADminOrder = function () {
    if (this.jspName != 'venuesViewAdmin.jsp') {
        return true;
    }

    if (this.jspName == 'venuesViewAdmin.jsp') {
        if ($("#meber").val() == '1') {
            if ($("#user").val() == '0') {
                alert("请选择用户");
                return false;
            }
        } else {
            if ($("#phone").val() == '' || $("#phonename").val() == '') {
                alert("请输入用户电话和名字");
                return false;
            }
        }
    }


}