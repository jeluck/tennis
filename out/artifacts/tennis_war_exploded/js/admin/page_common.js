/**
 * 分页显示当前页的前5条,后4条.
 * @param obj
 * @param className		样式名字
 * @param url			请求地址
 * @param rows			总条数
 * @param size			每页显示数量
 * @param index			当前页
 * 
 * 调用方法例子
 * paging($('.pagination')[0],'pagination','accountlist.do?pageNumber=',30,5,1);
 */
function paging(obj,className,url,rows, size, index) {
    rows=parseInt(rows);rows=isNaN(rows)?0:rows;if(!rows)return;
    size=parseInt(size);size=isNaN(size)?1:size;
    index=index||1;
    var ps=parseInt(rows/size);
    ps+=ps<parseFloat(rows/size)?1:0;
    if (!obj) {
        obj=document.createElement('ul');
        obj.className=className;
        document.body.appendChild(obj);
    }
    
    obj.innerHTML="<li><a href='javascript:void(0);'>共"+ps+"页</a></li>";
    
    obj.innerHTML+="<li><a href='"+url+"1'>首页</a></li><li><a href='"+url+(index-1<1?1:index-1)+"'>上一页</a></li>";
    var min=index-5<1?1:index-5,
        max=min+9>ps?ps:min+9;
    for(min=max-min<9?max-9<1?1:max-9:min;min<=max;min++){
        obj.innerHTML+="<li"+(min==index?' class=active':'')+"><a href='"+url+min+"'>"+min+"</a></li>";
    }
    obj.innerHTML+="<li><a href='"+url+(index+1>ps?ps:index+1)+"'>下一页</a></li><li><a href='"+url+ps+"'>尾页</a><li>";
    
    obj.innerHTML+="<li>&nbsp;&nbsp;到第";
    obj.innerHTML+='<input type="text" class="easyui-numberbox" value="" id="pagenum" style="width:55px;" />';
    obj.innerHTML+="页&nbsp;&nbsp;<input class='  btn btn-xs btn-info' type='button' onclick='jump_page(\""+url+"\","+ps+");' value='确定'/></li>";
}

/**
 * 跳到指定页
 * @param url		请求地址
 * @param ps		总页数
 */
function jump_page(url,ps){
	var pagenum =$("#pagenum").val();
	if(pagenum>0 && pagenum<=ps){
		location.href = url+pagenum;
	}
	
}
