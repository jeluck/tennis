<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <title>添加商品</title>
    <link rel="stylesheet" href="../../css/c.css"/>
    <link rel="stylesheet" href="../../css/customer.css"/>
    <link rel="stylesheet" href="../../css/sale.css"/>
    <style>
        li.current {
            color: #F03135;
            border-bottom: 2px solid #F03135;
        /*    padding-top: 4px;*/
        }
        #ProdContent > ul  {
            display:none;
        }

    </style>
    <script src="/js/jquery-1.8.1.min.js"></script>
    <script src="/js/admin_common.js"></script>
    <script>
        $(function() {
            window.onload = function() {
                var $li = $("#ProduceTab li");
                var $ul = $("#ProdContent > ul");
                $li.mouseover(function(){
                    var $this = $(this);
                    var $t = $this.index();
                    $li.removeClass("current");
                    $this.addClass("current");
                    $ul.css('display','none');
                    $ul.eq($t).css('display','block');
                })
            }
        })
    </script>
</head>
<body>
<div class="header">
    <p>
        <a  class="back"  href="javascript:history.go(-1)"></a>
        <a class="loginTitle">添加商品</a>
    </p>
</div>
<div style=" margin: 4% 0%;;">
    <input type="button" class="CardButton" value="添加商品" onclick="window.location.href='toaddProduces.do'"/>
</div>
<div class="Produce">
    <ul id="ProduceTab">
        <li class="current">出售中</li>
        <li>已下架</li>
    </ul>
    <div class="clear"></div>
    <div id="ProdContent">
        <!-- 出售中 -->
        <ul style="display:block;">
            <c:if test="${empty data_page }">
				<div class="nottiao">没有商品记录</div>
			</c:if>
	        <c:forEach items="${data_page.dataList }" var="o">
	        <li class="Prod-Content-li" id="Content-li_${o.id}">
                <span>
                	<c:if test="${not empty o.acquiescent_picture}" var="hava">
			     		<img class="count-order-list-img" src="${o.acquiescent_picture}">
			     	</c:if>
			     	<c:if test="${!hava }">
			     		<img class="count-order-list-img" src="/image/nophoto_120X120.jpg"/>
			     	</c:if>
                </span>
                <span class="count-orderDetail">${o.commodity_name }<br>
                        <a>￥${o.defaultPrice }  &nbsp;<em style="display:none;">&nbsp;佣金：￥30</em></a><br>
                        <a class="countTime"><em >销量&nbsp;${o.sales_volume }</em>
						<em class="backSpace">库存&nbsp;${o.commodityStore}</em><em class="backSpace" >${o.date_mm_dd}</em> </a>
                </span><br>
                <div class="clear"></div>
                <div class="prod-ssdown">
                    <ul style=" display: block;">
                        <li class="ssdown"><span class="ssdown-img01"></span>查看</li>
                        <li class="ssdown">
                           <input type="button" class="ssdown-img02" value="" onclick="window.loacation.href='' " />分享
                        </li>
                        <li class="ssdown" onclick="agent_undercommodity('${o.id}');"><span class="ssdown-img03"></span>下架</li>
                    </ul>
                </div>
            </li>
	        </c:forEach>
        </ul>
        <!-- 已下架 -->
        <ul>
        	<c:if test="${empty underlist_page }">
				<div class="nottiao">没有商品记录</div>
			</c:if>
	        <c:forEach items="${underlist_page.dataList }" var="o">
            <li class="Prod-Content-li"  id="under_Content-li_${o.id}">
                <div class="li-one">
                     <span>
							<c:if test="${not empty o.acquiescent_picture}" var="hava">
					     		<img class="count-order-list-img" src="${o.acquiescent_picture}">
					     	</c:if>
					     	<c:if test="${!hava }">
					     		<img class="count-order-list-img" src="/image/nophoto_120X120.jpg"/>
					     	</c:if>
                     </span>
                       <span class="count-orderDetail">${o.commodity_name }<br>
                       <a>￥${o.defaultPrice }  &nbsp;<em style="display:none;">&nbsp;佣金：￥30</em></a><br>
                        <a class="countTime"><em >销量&nbsp;${o.sales_volume }</em>
						<em class="backSpace">库存&nbsp;${o.commodityStore}</em><em class="backSpace" >${o.date_mm_dd}</em> </a>
                     </span><br>
                    <div class="clear"></div>
                </div>

                <div class="prod-ssdown">
                    <ul style=" display: block;">
                        <li class="ssdown" onclick="agent_commodityedit('${o.id}');"><span class="edit-img01"></span>编辑</li>
                        <li class="ssdown" onclick="agent_oncommodity('${o.id}');"><span class="add-img02"></span>上架</li>
                        <li class="ssdown" onclick="agent_deleteCommodity('${o.id}','${o.commodity_name}');"><span class="delete-img03"></span>删除</li>
                    </ul>
                </div>
            </li>
             </c:forEach>
        </ul>
    </div>
</div>
<div class="maxAddProd" style="display:none;">
</div>

</body>
 <script src="/js/agentCompany.js"></script>
</html>