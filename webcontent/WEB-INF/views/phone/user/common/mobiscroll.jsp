<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/mobiscroll.jsp -->
<!-- 日历控件 start -->

  <link rel="stylesheet" href="/js/plugin/mobiscroll/mobiscroll.animation.css">
  <link rel="stylesheet" href="/js/plugin/mobiscroll/mobiscroll.frame.css">
  <link rel="stylesheet" href="/js/plugin/mobiscroll/mobiscroll.frame.android-holo.css">
  <link rel="stylesheet" href="/js/plugin/mobiscroll/mobiscroll.scroller.android-holo.css">
  <link rel="stylesheet" href="/js/plugin/mobiscroll/mobiscroll.scroller.css">
  
  
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.core.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.frame.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.scroller.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.util.datetime.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.datetime.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.datetimebase.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.frame.android-holo.js"></script>
  <script type="text/javascript" src="/js/plugin/mobiscroll/mobiscroll.i18n.zh.js"></script>
  
  <script type="text/javascript">
  
    
  lz.mobiscroll = function () {
    var aInput = document.querySelectorAll('input[data-type]');
    
    for (var i=0;i<aInput.length;i++) {

      var setting = {
          dateFormat: 'yy-mm-dd',
          theme: 'android-holo',   
          mode: 'scroller',       
          display: 'bottom', 
          lang: 'zh',
          endYear: 2020
      }

      for (attr in aInput[i].dataset) {
        if (/\d+/.test(aInput[i].dataset[attr])) {
          setting[attr] = parseInt(aInput[i].dataset[attr]);
        } else {
          setting[attr] = aInput[i].dataset[attr];
        }
        
      }
      $(aInput[i]).mobiscroll()[aInput[i].dataset.type](setting);
      delete aInput[i].dataset.type;
    }
  }
  window.addEventListener('load', lz.mobiscroll, false)
    
  </script>
  
<!-- 日历控件 end -->