/**
 * @page 公共方法文件
 */

var lz = {};

//window.addEventListener('load', function () {
//  console.log(lz);
//}, false);

/**
 * 删除元素class
 * @param {object} obj 选中的元素
 * @param {string} value 选择要删除的class名称，多个使用空格隔开，如果不传，则删除所有
 */

lz.removeClass = function (obj, value) {
    if (!value) {
        obj.className = '';
        return;
    }

    var aClass = value.replace(/\s+/g, '|').split('|');
    var aRe = [];

    for (var i = 0; i < aClass.length; i++) {
        aRe.push('(^|\\s)' + aClass[i] + '((?=\\s)|$)');
    }

    var re = new RegExp(aRe.join('|'), 'g');

    obj.className = obj.className.replace(re, '');
}

/**
 * 返回当前元素在父级下的索引值
 * @param {object} obj 选中的元素
 * @return {number} 返回obj在同级中的索引
 */

lz.getIndex = function (obj) {
        var aList = obj.parentNode.children;

        for (var i = 0; i < aList.length; i++) {
            if (aList[i] == obj) {
                return i;
            }
        }
    }
    /**
     * 自定义勾选按钮
     * @param {object} obj 被点击的元素
     */
lz.checkbox = function (obj, pay_type) {
        $("#pay_type").val(pay_type);
        var oInput = obj.querySelector('input[type=checkbox]');
        var oDaGou = obj.getElementsByTagName('i')[0];
        if (oInput.checked) {
            oInput.checked = false;
            lz.removeClass(oDaGou, 'lz-icon-dagou');
        } else {
            oInput.checked = true;
            oDaGou.className += ' lz-icon-dagou';
        }

    }
    /**
     * 清空多选的按钮
     * @param {object} obj 被点击的元素
     * @param {string} dom选择器，选择你要清空的范围
     */
lz.clearCheckboxList = function (obj, sQuery) {

    var oBox = document.querySelector(sQuery);
    var aInput = oBox.querySelectorAll('input[type=checkbox]');

    if (!aInput) {
        return '不存在多选框标签';
    }

    for (var i = 0; i < aInput.length; i++) {
        var oItag = aInput[i].parentNode.getElementsByTagName('i')[0];
        lz.removeClass(oItag, 'lz-icon-dagou');
        aInput[i].checked = false;
    }

};
/**
 * 自定义勾选按钮2
 * @param {object} obj 被点击的元素
 */
lz.checkboxTwo = function (obj) {
    var oInput = obj.querySelector('input[type=checkbox]');
    var oDaGou = obj.getElementsByTagName('i')[0];
    if (oInput.checked) {
        oInput.checked = false;
        oDaGou.className = '';
    } else {
        oInput.checked = true;
        oDaGou.className = 'lz-show';
    }
}

/**
 * 清空多选的按钮2
 * @param {object} obj 被点击的元素
 * @param {string} dom选择器，选择你要清空的范围
 */
lz.clearCheckboxListTwo = function (obj, sQuery) {

    var oBox = document.querySelector(sQuery);
    var aInput = oBox.querySelectorAll('input[type=checkbox]');

    if (!aInput) {
        return '不存在多选框标签';
    }

    for (var i = 0; i < aInput.length; i++) {
        var oItag = aInput[i].parentNode.getElementsByTagName('i')[0];
        oItag.className = '';
        aInput[i].checked = false;
    }

};

/**
 * 单选按钮
 * @param {object} obj 被点击的元素
 * @param {string} dom选择器，选择你要设置的id
 * @param {string} v1设置的值1
 * @param {string} v2设置的值2
 */
lz.setCheckedRadio = function (_this, setId, v1, v2) {

    var oIbox = _this.getElementsByTagName('i')[0];
    var oInput = document.querySelectorAll(setId);
    if (_this.dataset.statue) {
        oIbox.className = 'lz-show';
        oInput.value = v1;
        delete _this.dataset.statue
    } else {
        oIbox.className = '';
        oInput.value = v2;
        _this.dataset.statue = true;
    }
};

/**
 * 跳转链接地址
 */
lz.tapJump = function () {

    var arr = document.querySelectorAll('[data-lz-url]');
    for (var i = 0; i < arr.length; i++) {
        arr[i].addEventListener('click', app, false);
        //    arr[i].addEventListener('touchstart', app, false);
        arr[i].lzUrl = arr[i].dataset.lzUrl;
        delete arr[i].dataset.lzUrl;
    }

    function app() {
        window.location.href = this.lzUrl;

    }

};

/**
 * 设置开关按钮
 * @param {object} _this 被点击的元素
 * @param {array} 要设置的值
 */
lz.setSwitch = function (_this, aSet) {

    var oInput = _this.parentNode.getElementsByTagName('input')[0];

    if (_this.className.indexOf('yes') > -1) {
        lz.removeClass(_this, 'yes');
        _this.className += ' no';
        oInput.value = aSet[0];
    } else {
        lz.removeClass(_this, 'no');
        _this.className += ' yes';
        oInput.value = aSet[1];
    }

};
lz.setCookie = function (name, value) { //设置cookie
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + '=' + escape(value) + ';expires=' + exp.toGMTString() + ';path=/';
};
lz.getCookie = function (name) { //获取cookie
    var reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
    var arr = document.cookie.match(reg);

    if (arr) {
        return unescape(arr[2]);
    } else {
        return null;
    }

};
/**
 * tab选项卡
 */
lz.tab = function () {

        var App = function () {
            this.aTab = document.querySelectorAll('[data-lz-tab]');
            this.init();
        };

        App.prototype = {
            init: function () {
                var aTab = this.aTab;
                for (var i = 0; i < aTab.length; i++) {
                    this.getTabList(aTab[i]);
                }

            },
            getTabList: function (obj) {
                this.aSetting = obj.dataset.lzTab.split(','); //获取配置
                this.aNavChild = obj.querySelectorAll(this.aSetting[1] + '>*'); //导航列表
                this.aBox = obj.querySelectorAll(this.aSetting[2] + '>*'); //盒子列表

                //var index = localStorage.getItem(this.aSetting[0]) || 0; //获取索引，安卓不支持改用cookie
                var index = this.getIndex() || lz.getCookie(this.aSetting[0]) || 0; //获取索引

                for (var i = 0; i < this.aNavChild.length; i++) {
                    this.bind(this.aNavChild[i], i);
                    lz.removeClass(this.aNavChild[i], 'lz-on');
                    if (this.aBox[i])
                        this.aBox[i].style.display = 'none';

                }
                this.aNavChild[index].className += ' lz-on';
                if (this.aBox[index])
                    this.aBox[index].style.display = 'block';

            },
            bind: function (obj, index) {

                var _this = this;
                obj.addEventListener('click', reactions, false);
                //obj.addEventListener('touchstart', reactions, false);

                function reactions() {

                    for (var i = 0; i < _this.aNavChild.length; i++) {
                        lz.removeClass(_this.aNavChild[i], 'lz-on');
                        if (_this.aBox[i])
                            _this.aBox[i].style.display = 'none';
                    }
                    _this.aNavChild[index].className += ' lz-on';
                    if (_this.aBox[index])
                        _this.aBox[index].style.display = 'block'
                        //localStorage.setItem(_this.aSetting[0], index); //存储索引
                    lz.setCookie(_this.aSetting[0], index);
                }
            },
            getIndex: function () {
                var setting = this.aSetting[0];
                var re = new RegExp(setting + '=(\\d+)');
                var url = window.location.href;
                var index = null;
                url.replace(re, function ($1, $2) {
                    index = $2;
                });
                return index;
            }
        };
        new App();
    }
    /**
     * 评论框，
     */
lz.textareaPullHeight = function (_this) {

    if (_this.scrollHeight < 200) {
        _this.style.height = (_this.scrollHeight + 1) + 'px';
    }
}

lz.overlays = function (bool) {
        var oOverlays = document.getElementById('overlays');

        if (oOverlays) {

        } else {
            oOverlays = document.createElement('div');
            oOverlays.id = 'overlays';
            document.body.appendChild(oOverlays);
        }

        if (bool) {
            oOverlays.className = 'lz-show';
        } else {
            oOverlays.className = 'lz-hide';
        }

    }
    /**
     * 我的订单页面使用ajax分页
     * @param {string} id 根据此元素来判断页面是否符合加载
     * @param {type} boxId 将返回的数据插入到哪个元素
     * @param {type} url 请求的url地址
     * @param {type} data 发送给服务器的数据
     * @param {type} fn 回调方法
     */
lz.getNext = function (id, boxId, url, data, fn) {
        return new GetNext({
            elementId: id, //绑定的元素id
            url: url, //url地址
            data: data,
            pageName: 'pageNumber', //指定分页的名称，系统会自动对其累加
            startFn: function (_this) {
                _this.element.innerHTML = '加载中...';
            },
            loadFn: function (_this, data) {
                var json = JSON.parse(data);

                if (typeof (json.data) == 'string') {
                    _this.element.innerHTML = '没有了';
                    return false; //加载完成了，返回false则不会再触发ajax
                }

                var aList = json.data.dataList;
                var oBox = document.getElementById(boxId);

                for (var i = 0; i < aList.length; i++) {
                    var oLi = document.createElement('li');
                    oLi.innerHTML = fn(i, aList[i]);
                    oBox.appendChild(oLi);
                }

                _this.element.innerHTML = '加载更多';
            },
            errerFn: function (_this, xhr) {
                _this.element.innerHTML = '网络错误，请稍候重试';
            }
        });
    }
    /**
     * ios方法调用
     * @param {string} iosCallbackName ios执行的方法
     * @param {object} data 发送给ios的数据
     * @param {function} callback ios完成后执行的回调函数
     */
lz.initIos = function (iosCallbackName, data, callback) {
    iosCallbac(function (ios) {
        ios.init(function (message, responseCallback) {
            var data = {
                'Javascript Responds': 'Wee!'
            };
            responseCallback(data);
        });

        window.ios = ios;
        //		app.callHandler(iosCallbackName, data || {} , callback || function () {}); //发送数据给ios

    });
    //初始化IOS上传控件(ios手机端调用JS,运行IOS方法)
    function iosCallbac(callback) {
        if (window.WebViewJavascriptBridge) {
            callback(WebViewJavascriptBridge);
        } else {
            document.addEventListener('WebViewJavascriptBridgeReady', function () {
                callback(WebViewJavascriptBridge);
            }, false);
        }
    }

}

window.addEventListener('load', function () {
    lz.tapJump(); //设置标签跳转
    lz.tab(); //设置选项卡
}, false);