/**
 * 退出商家
 */
function agentlogout(){
	 window.location.href="agentlogout.do"; 
	 try{ window.hideTab.exit(); }catch (e) {}
}

/*
下架商品
 */
function agent_undercommodity(id){
	 var info = util.POST("agent_undercommodity.do",{"cid":id});
     if(info.status==0){
    	 $("#Content-li_"+id).css("display","none");
     }else{
    	 alert(info.msg);
     }
}

/*
上架商品
 */
function agent_oncommodity(id){
	 var info = util.POST("agent_oncommodity.do",{"cid":id});
     if(info.status==0){
    	 $("#under_Content-li_"+id).css("display","none");
     }else if(info.status==26){
    	 alert(info.msg);
     }else{
    	 alert(info.msg);
     }
}

/*
删除商品
 */
function agent_deleteCommodity(id,name){
	if(window.confirm("确认删除"+name+"商品?")){
		var info = util.POST("agent_deleteCommodity.do",{"cid":id});
	     if(info.status==0){
	    	 $("#under_Content-li_"+id).css("display","none");
	     }else{
	    	 alert(info.msg);
	     }
	}
}


var divcounter = 0;
var delcounter = 0;
function addNewType()
{
	divcounter++;
	var objkinddiv = $("#Model");
	var ndiv = document.createElement("DIV");
	ndiv.id = "div" + divcounter;
	ndiv.className = "adddivModel";
	ndiv.innerHTML = "<p class='model-border'> 型号 " +
					'<input type="text" name="type' + divcounter + '" placeholder="颜色、尺码等规格" class="modelGray border-none" required  /></p>' +
					 ''+
					'<p class="model-border"> 价格 ' +
					'<input type="text" name="price' + divcounter + '" id="price' + divcounter + '" placeholder="填写商品价格" class="modelGray border-none" required  /></p>' +
					 ''+
					' <p class="model-border">库存 ' +
					'<input type="text" name="store' + divcounter + '" id="store' + divcounter + '" placeholder="填写商品数量" class="modelGray border-none" required /></p>' +
					'<div  id="CloseModel" class="close-model" onclick="deleteType(\'' + ndiv.id + '\')"></div>' +
					'';

	objkinddiv.after(ndiv);


}

function deleteType(divid)
{
	if(divcounter > delcounter) {
		var ddiv = document.getElementById(divid);
		ddiv.parentNode.removeChild(ddiv);
		delcounter++;
	}
}

/**
 * 商家选择分类
 * @param id
 */
function choosecategoryInfo(id){
	var info = util.POST("choosecategoryInfo.do",{"cid":id});
    if(info.status==0){
    	$("#categoryInfo").val(info.data.category_name);
    	$("#categoryInfoid").val(info.data.id);
    }else{
   	 	alert(info.msg);
    }
}

/**
 * 获得所有一级分类
 * @param request
 * @param oid
 * @return
 */
function getAllOnelevelCategory(){
	var info = util.POST("getAllOnelevelCategory.do");
    var str="";
	if(info.status==0){
    	$.each(info.data,function(n,value) {  
    		str+="<li class='sort-li' onclick='getTwocategoryByOneId("+value.id+")' >";
    		str+=value.category_name;
    		str+="</li>";
       }); 
    	$("#categorydiv").html(str);
    }else{
   	 	alert(info.msg);
    }
}

/**
 * 根据分类一级获得二级分类
 * @param request
 * @param oid
 * @return
 */
function getTwocategoryByOneId(id){
	var info = util.POST("getTwocategoryByOneId.do",{"oid":id});
    var str="";
	if(info.status==0){
    	$.each(info.data,function(n,value) {  
    		str+="<li class='sort-li' onclick='CloseDiv(\"MyDiv\",\"fade\");choosecategoryInfo("+value.id+")' >";
    		str+=value.category_name;
    		str+="</li>";
       }); 
    	$("#categorydiv").html(str);
    }else{
   	 	alert(info.msg);
    }
}

/***
 * 商家去修改商品
 * @param id
 */
function agent_commodityedit(id){
	 window.location.href="agent_commodityedit.do?cid="+id; 
}
/**
 * 获得商品详情
 * @param id
 */
function getCommodityDetail(id){
	 window.location.href="getCommodityDetail.do?cid="+id; 
}


//管理商品页(下拉加载数据,下架商品)
function getId_under_ing_ul_nextPage(pageNumber){
	pageNumber = $('.more_p').attr('p');//获取第前页
	pageNumber--;
	$.ajax({
		type:"POST",
		async:false,
		url:"tomyProducts_under_nextPage.do",
		data:{"pageNumber":pageNumber},
		dataType:"html",
		success:function(data){
			$("#under_ing_ul").append(data);
		}
	});
}


//商家的商品列表页(下拉加载数据,下架商品)
function getId_under_ing_ul_nextPage_list(){
	var pageNumber = $('.more_p').attr('p');//获取第前页
	pageNumber--;
	$.ajax({
		type:"POST",
		async:false,
		url:"agentprolist_nextPage.do",
		data:{"pageNumber":pageNumber,"listing":"1"},
		dataType:"html",
		success:function(data){
			$("#under_ing_ul").append(data);
		}
	});
}