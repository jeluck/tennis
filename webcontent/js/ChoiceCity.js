/*
 * info：选择省份和城市的插件，基于JQ
 * date：2015-11-17
 * author：狼族小狈
 */

var ChoiceCity = function (setting) {
    this.provinceUrl = setting.provinceUrl; //省份url获取地址
    this.cityUrl = setting.cityUrl; //城市url获取地址 
    this.areaUrl = setting.areaUrl || null; //街区获取地址 
    this.boxId = setting.boxId || 'choicecity';
    this.callback = null;
    this.provincId = null;
    this.cityId = null;
};

ChoiceCity.prototype.open = function (provincId, cityId, callback) {
    this.callback = callback;
    this.provincId = provincId;
    this.cityId = cityId;
    var oChoiceCity = document.getElementById(this.boxId);
    var _this = this;
    if (oChoiceCity) { //存在就说明已经加载过，无需再重复加载 
        oChoiceCity.className = 'choicecity choicecity-show';
        _this.initIndex();
        return;
    }

    oChoiceCity = document.createElement('div');
    oChoiceCity.id = this.boxId;
    oChoiceCity.className = 'choicecity choicecity-show';

    var sHtml = '<div class="choicecity-header"><i class="back"></i><h3>选择城市</h3></div>\n\
        <ul class="choicecity-box">';
    $.get(this.provinceUrl, function (response) { //加载省份

        var arr = response.data;

        for (var i = 0; i < arr.length; i++) {
            sHtml += '<li class="choicecity-child-hide" data-region_id="' + arr[i].region_id + '">\n\
                <h4 data-province="' + arr[i].region_id + '">' + arr[i].region_name + '<i></i></h4>\n\
                <ul class="choicecity-child-box" data-region_name="' + arr[i].region_name + '" data-region_id="' + arr[i].region_id + '"></ul>\n\
            </li>';
        }

        sHtml += '</ul>';
        oChoiceCity.innerHTML = sHtml;
        document.body.appendChild(oChoiceCity);
        _this.bindClick(oChoiceCity.querySelectorAll('.choicecity-box>li>h4'));
        _this.initIndex();
        //返回，不选城市
        var oBack = oChoiceCity.getElementsByClassName('back')[0];
        oBack.addEventListener('click', function () {
            document.getElementById(_this.boxId).className = 'choicecity-hide';

        }, false);
    });
};

ChoiceCity.prototype.bindClick = function (arr) {
    var _this = this;
    for (var i = 0; i < arr.length; i++) {
        arr[i].index = i;
        arr[i].addEventListener('click', function () {
            this.parentNode.className = 'choicecity-child-show';
            _this.showChild(this.index, arr);
            _this.initIndex(this.parentNode.dataset.region_id, '');
        }, false);
    }

};

/**
 * 获取城市的区
 * @param {object} element 被点击的城市元素
 */
ChoiceCity.prototype.getAreaUrl = function (element) {
    var _this = this;
    
    if (element.ajaxStatus) {
        return;
    }
    
    var app = {
        init: function () {
            $.get('/area.do?city=' + element.dataset.region_id, function (res) {
                var list = res.data;
                var aHtml = [];
                for (var i=0;i<list.length;i++) {
                    aHtml.push(app.getListHtml(list[i]));
                }
                
                element.innerHTML += aHtml.join('');
                
                var aList = element.getElementsByTagName('div');
                
                for (var i=0;i<aList.length;i++) {
                    aList[i].addEventListener('click', function () {
                        var province = this.parentNode.parentNode; //省份
                        var city = this.parentNode; //城市
                        
                        document.getElementById(_this.boxId).className = 'choicecity-hide';
                        _this.callback(province, city , this);
                        
                    }, false);
                }
                element.ajaxStatus = true;
            });  
        },
        getListHtml: function (data) {
            return '<div class="choicecity-child-area" data-region_name="' + data.region_name + '" data-region_id="' + data.region_id + '">' + data.region_name + '</div>'
        }
    };
    
    app.init();
    

    
}

ChoiceCity.prototype.showChild = function (index, arr) {

    for (var i = 0; i < arr.length; i++) {
        arr[i].parentNode.className = 'choicecity-child-hide';
    }
    arr[index].parentNode.className = 'choicecity-child-show';
    if (!arr[index].ajaxSend) { //如果还没有使用ajax请求数据，则先请求数据
        this.getChild(arr[index]);
    }
};

ChoiceCity.prototype.getChild = function (element) {
    var _this = this;
    var province = element.dataset.province;
    var oUl = element.parentNode.getElementsByTagName('ul')[0];
    $.get(this.cityUrl + '?province=' + province, function (response) {
        var arr = response.data;
        var sHtml = '';
        for (var i = 0; i < arr.length; i++) {
            sHtml += '<li data-region_name="' + arr[i].region_name + '" data-region_id="' + arr[i].region_id + '">' + arr[i].region_name + '</li>';
        }
        oUl.innerHTML = sHtml;
        _this.initIndex();
        var aLi = oUl.getElementsByTagName('li');

        for (var i = 0; i < aLi.length; i++) {

            aLi[i].addEventListener('click', function () {
                
                if (_this.areaUrl) {
                    listHide();
                    _this.getAreaUrl(this);
                    this.className = '';
                } else {                    
                    document.getElementById(_this.boxId).className = 'choicecity-hide';
                    _this.callback(oUl, this);
                }
                
            }, false);
        }
        
        function listHide () {
            for (var i=0;i<aLi.length;i++) {
                aLi[i].className = 'hide';
            }
        }

    });

};

ChoiceCity.prototype.initIndex = function () {

    //    var oProvinc = document.querySelector('#choicecity [data-region_id="' + provincId + '"]');
    //    var oCityId = document.querySelector('#choicecity [data-region_id="' + cityId + '"]');
    //    var aStatus = document.querySelector('#choicecity [data-status="cur"]');
    //    console.log(aStatus);
    //    oProvinc.dataset.status = 'cur';
    //    if (oCityId) {
    //        oCityId.dataset.status = 'cur';
    //    }
};