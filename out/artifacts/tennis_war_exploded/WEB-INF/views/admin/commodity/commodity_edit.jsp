<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	var request;

	//页面跳转
	function go_page(pageNumber){
		location.href = "commoditypagelist.do?pagenumber="+pageNumber;
	}


	function returnoverview(listing)
	{
// 		document.form1.action = "commoditypagelist.do";
// 		document.form1.submit();
		location.href = "commoditypagelist.do?listing="+listing;
	}

	function createRequest() {
		try {
			request = new XMLHttpRequest();
		} catch (trymicrosoft) {
			try {
				request = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (othermicrosoft) {
				try {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (failed) {
					request = false;
				}
			}
		}

		if (!request)
			alert("Error initializing XMLHttpRequest!");
	}

	function getParentCategoryInfo() {
		createRequest();
		var phone = document.getElementById("phone").value;
		var url = "/cgi-local/lookupCustomer.php?phone=" + escape(phone);
		request.open("GET", url, true);
		request.onreadystatechange = updatePage;
		request.send(null);
	}

	function checkForm()
	{
		var objexpressfee = document.getElementById("express_fee");
		if(isNaN(objexpressfee.value))
		{
			alert("邮费请输入正确的数字！");
			return false;
		}
		else if(objexpressfee.value < 0)
		{
			alert("邮费请输入大于0的数字！");
			return false;
		}

		for(var i=0;i<divcounter;i++)
		{
			var objprice = document.getElementById("price"+i);
			if(objprice != null)
			{
				if(isNaN(objprice.value))
				{
					alert("价格请输入正确的数字！");
					return false;
				}
				else if(objprice.value < 0)
				{
					alert("价格请输入大于0的数字！");
					return false;
				}
			}


			var objstore = document.getElementById("store" + i);
			if(objstore != null)
			{
				if(isNaN(objstore.value))
				{
					alert("库存请输入正确的数字！");
					return false;
				}
				else
				{
					if(!(objstore.value % 1 === 0))
					{
						alert("库存请输入整数！");
						return false;
					}
					else
					{
						if(objstore.value < 0)
						{
							alert("库存请输入大于等于0的整数！");
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	var divcounter = 0;
	var delcounter = 0;
	function addNewType()
	{
		divcounter++;
		var objkinddiv = document.getElementById("kinddiv");
		var ndiv = document.createElement("DIV");
		ndiv.id = "div" + divcounter;
		ndiv.className = "col-sm-9";
		ndiv.innerHTML = "型号：&nbsp;&nbsp;" +
						'<input type="text" name="type' + divcounter + '" style="width: 300px;" placeholder="请输入型号名称" class="col-xs-20 col-sm-25" required  />' +
						'<br/>' +
						'价格：&nbsp;&nbsp;' +
						'<input type="text" name="price' + divcounter + '" id="price' + divcounter + '" style="width: 300px;" placeholder="请输入价格" class="col-xs-20 col-sm-25" required  />' +
						'<br/>' +
						'库存：&nbsp;&nbsp;' +
						'<input type="text" name="store' + divcounter + '" id="store' + divcounter + '" style="width: 300px;" placeholder="请输入库存" class="col-xs-20 col-sm-25" required />' +
						'<input type="button" value="删除" onclick="deleteType(\'' + ndiv.id + '\')"  />' +
						'';

		objkinddiv.appendChild(ndiv);
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
 * 下架
 */
	function undercommodity(id)
	{
		document.form1.action = "undercommodity.do";
		var inputoid = document.getElementById("id");
		inputoid.value = id;
		document.form1.submit();
	}
/**
 * 上架
 */
	function showcommodity(id)
	{
		document.form1.action = "showcommodity.do";
		var inputoid = document.getElementById("id");
		inputoid.value = id;
		document.form1.submit();
	}
	
	/**
	 * 驳回
	 */
	function rejectcommodity(id)
	{
		document.form1.action = "rejectcommodity.do";
		var inputoid = document.getElementById("id");
		inputoid.value = id;
		document.form1.submit();
	}

	function deletePicture(pid, pimgid, pdeid)
	{
		var objpic = document.getElementById(pid);
		objpic.value = "";

		var objpimg = document.getElementById(pimgid);
		if(objpimg != null) {
			objpimg.parentNode.removeChild(objpimg);
		}

		var objpdeid = document.getElementById(pdeid);
		objpdeid.value = "";
	}


</script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try {
				ace.settings.check('breadcrumbs', 'fixed');
			} catch (e) {
			}
		</script>

		<ul class="breadcrumb" style="padding-top:8px;">
			<li><i class="icon-home home-icon"></i>首页</li>
			<li class="active">管理</li>
			<li class="active">修改商品</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>修改商品 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<input type="hidden" value="${saveresult}" id="saveresult" name="saveresult" />
	<input type="hidden" value="${operationresult}" id="operationresult" name="operationresult" />
	<input type="hidden" value="${typelistsize}" id="typelistsize" name="typelistsize" />

	<form id="QueryForm" name="form1" class="form-inline" action="updatecommodity.do" enctype="multipart/form-data"
		  method="post" onsubmit="return checkForm()">
		<input type="hidden" value="${commodity.id}" name="id" />
		<input type="hidden" value="${commodity.listing}" name="listing" />
		<input type="hidden" value="${defaultacquiescentpicture}" name="defaultacquiescentpicture" />
		<input type="hidden" value="${defaultpicture1}" name="defaultpicture1" id="defaultpicture1id" />
		<input type="hidden" value="${defaultpicture2}" name="defaultpicture2" id="defaultpicture2id" />
		<input type="hidden" value="${defaultpicture3}" name="defaultpicture3" />
		<input type="hidden" value="${defaultpicture4}" name="defaultpicture4" />
		<input type="hidden" value="${defaultpicture5}" name="defaultpicture5" />
		<input type="hidden" value="${defaultpicture6}" name="defaultpicture6" />
		<input type="hidden" value="${defaultpicture7}" name="defaultpicture7" />

		<table width="70%" border="0" align="center">
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<table border="1"  align="left" class="table table-striped table-bordered table-hover">
						<tr >
							<td width="140px" >
								商品名称：
							</td>
							<td >
								<div class="col-sm-9">
									<input type="text"
										   name="commodityname" style="width: 200px;" placeholder="请输入商品名称"
										   class="col-xs-10 col-sm-5" required value="${commodity.commodity_name}"  />
								</div>
								&nbsp;&nbsp;&nbsp;
								<c:if test="${commodity.listing == \"0\" }" >
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="undercommodity('${commodity.id}');">
											<i class="icon-edit bigger-120"></i>下架
										</button>
									</div>
								</c:if>
								<c:if test="${commodity.listing == \"1\" }" >
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="showcommodity('${commodity.id}');">
											<i class="icon-edit bigger-120"></i>上架
										</button>
									</div>
									<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
										<button class="btn btn-xs btn-info" onclick="rejectcommodity('${commodity.id}');">
											<i class="icon-edit bigger-120"></i>驳回
										</button>
									</div>
								</c:if>
							</td>
						</tr>

						<tr style="display:none;">
							<td>
								商家：
							</td>
							<td>
								<div class="col-sm-9">
									<select name="company" class="col-xs-5 col-sm-6" >
										<c:forEach items="${companylist }" var="company" varStatus="c">
											<option value="${company.id}" <c:if test="${company.id == commodity.companyInfo.id }" >selected</c:if>>${company.company_name}</option>
										</c:forEach>
										<option value="0">其他</option>
									</select>
								</div>
							</td>
						</tr>

<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								品牌： -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<div class="col-sm-9"> -->
<!-- 									<select name="brand" class="col-xs-5 col-sm-6" > -->
<%-- 										<c:forEach items="${brandlist }" var="brand" varStatus="b"> --%>
<%-- 											<option value="${brand.id}" <c:if test="${brand.id == commodity.brandInfo.id }" >selected</c:if>>${brand.brand_name}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 										<option value="0">其他</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->

						<tr>
							<td>
								分类：
							</td>
							<td>
								<div class="col-sm-9" id="categoryDiv">
									<select name="category" class="col-xs-5 col-sm-6" onchange="getTwocategoryByOneId()">
										<c:forEach items="${categorylist }" var="category" varStatus="c">
											<option value="${category.id}" <c:if test="${category.id == commodity.categoryInfo.id }" >selected</c:if>>${category.category_name}</option>
										</c:forEach>
										<option value="0">其他</option>
									</select>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								商品介绍：
							</td>
							<td>
								<div class="col-sm-9">
									<textarea rows="6" cols="10" xclass="col-xs-10 col-sm-5" name="introduction" style="width: 360px;" placeholder="请输入介绍" required  >${commodity.introduction}</textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								邮费：
							</td>
							<td>
								<div class="col-sm-9">
									<input type="text"
									name="express_fee" style="width: 360px;" placeholder="请输入邮费"
									class="col-xs-10 col-sm-5" maxlength="6" required id="express_fee" value="${commodity.express_fee}" />
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								图片：
							</td>
							<td>
								<div class="col-sm-9">
									<input type="file"
										   name="acquiescentpicture" style="width: 260px;"
										   class="col-xs-10 col-sm-5" maxlength="6"  />
									<img src="${commodity.acquiescent_picture}" width="50px;" height="50px;">
								</div>
								<div class="col-sm-9">
									<input type="file"
										   name="picture1" style="width: 260px;"
										   class="col-xs-10 col-sm-5" maxlength="6" id="picture1id"  />
									<c:if test="${commodity.picture_1 != \"\"}">
										<img src="${commodity.picture_1}" width="50px;" height="50px;" id="picture1imgid">
									</c:if>
									<input type="button" value="删除" onclick="deletePicture('picture1id', 'picture1imgid', 'defaultpicture1id')"  />
								</div>
								<div class="col-sm-9">
									<input type="file"
										   name="picture2" style="width: 260px;"
										   class="col-xs-10 col-sm-5" maxlength="6" id="picture2id"  />
									<c:if test="${commodity.picture_2 != \"\"}">
										<img src="${commodity.picture_2}" width="50px;" height="50px;" id="picture2imgid" >
									</c:if>
									<input type="button" value="删除" onclick="deletePicture('picture2id', 'picture2imgid', 'defaultpicture2id')"  />
								</div>
							</td>
						</tr>

						<tr>
							<td>
								型号 &nbsp;&nbsp;
<!-- 								<input type="button" value="添加" onclick="addNewType()"> -->
							</td>
							<td>
								<div class="col-sm-9" id="kinddiv">
									<c:forEach items="${commoditytypelist }" var="commoditytype" varStatus="v">
										<div id="div${0 - v.count}" class="col-sm-9">
<!-- 											型号：&nbsp;&nbsp; -->
<%-- 											<input type="text" name="type${0 - v.count}" style="width: 300px;" placeholder="请输入型号名称" class="col-xs-20 col-sm-25" required value="${commoditytype.commodityType}"  /> --%>
											长：&nbsp;&nbsp; <input value="${commoditytype.chang}" type="number" style="width: 300px;" class="col-xs-20 col-sm-25"  value="0" placeholder="长" name="chang${0 - v.count}" /><br/>
							           	 宽：&nbsp;&nbsp;  <input value="${commoditytype.kuan}" type="number" style="width: 300px;" class="col-xs-20 col-sm-25"  value="0" placeholder="宽" name="kuan${0 - v.count}"><br/>
							         	   厚：&nbsp;&nbsp; <input value="${commoditytype.hou}" type="number" style="width: 300px;" class="col-xs-20 col-sm-25"  value="0" placeholder="厚" name="hou${0 - v.count}"><br/>
							         	   圈号 ：&nbsp;&nbsp; <input value="${commoditytype.quanhao}" type="text" style="width: 300px;" class="col-xs-20 col-sm-25" placeholder="填写商品圈号" name="quanhao${0 - v.count}"/><br/>
											价格：&nbsp;&nbsp;
											<input <c:if test="${v.index==0 }">onblur="setcommission('price${0 - v.count}',${c1},${c2},${c3})" </c:if> type="text" name="price${0 - v.count}" id="price${0 - v.count}" style="width: 300px;" placeholder="请输入价格" class="col-xs-20 col-sm-25" required value="${commoditytype.commodityPrice}"  />
											<br/>
											库存：&nbsp;&nbsp;
											<input type="text" name="store${0 - v.count}" id="store${0 - v.count}" style="width: 300px;" placeholder="请输入库存" class="col-xs-20 col-sm-25" required value="${commoditytype.commodityStore}" />
											单位：&nbsp;&nbsp;<select class="modelGray border-none"  name="unit">
							                  <option value="手" <c:if test="${commodity.unit == '手' }">selected</c:if> >手</option>
							                  <option value="件" <c:if test="${commodity.unit == '件' }">selected</c:if> >件</option>
							              </select>
											<c:if test="${v.index!=0 }">
											<input type="button" value="删除" onclick="deleteType('div${0 - v.count}')"  />
											</c:if>
											<input type="hidden" name="id${0 - v.count}" id="id${0 - v.count}" value="${commoditytype.id}" />
										</div>


									</c:forEach>
								</div>
							</td>
						</tr>

							<tr>
							<td>
								一级佣金(<font color="red">最少</font>)：
							</td>
							<td>
								<div class="col-sm-9">
									<input type="text"
									name="commission_1" style="width: 360px;" placeholder="请输入佣金"
									class="col-xs-10 col-sm-5" maxlength="6" required id="commission_1" value="${commodity.commission_1}"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								二级佣金(<font color="red">中间</font>)：
							</td>
							<td>
								<div class="col-sm-9">
									<input type="text"
									name="commission_2" style="width: 360px;" placeholder="请输入佣金"
									class="col-xs-10 col-sm-5" maxlength="6" required id="commission_2"  value="${commodity.commission_2}"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								三级佣金(<font color="red">最多</font>)：
							</td>
							<td>
								<div class="col-sm-9">
									<input type="text"
									name="commission_3" style="width: 360px;" placeholder="请输入佣金"
									class="col-xs-10 col-sm-5" maxlength="6" required id="commission_3"  value="${commodity.commission_3}"/>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>

			<tr>
				<td width="5%">&nbsp;</td>
				<td width="40%">
					<button class="btn btn-sm btn-success" type="submit"> [ 提交 ]
					</button>
					&nbsp;
					<a class="btn btn-sm btn-success" onclick="returnoverview('${commodity.listing}');"> [ 返回 ] </a>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
	</form>




</body>
</html>

<script type="text/javascript">
	window.onload = function() {
		var inputreset = document.getElementById("saveresult");
		if(inputreset.value.indexOf("1") == 0)
		{
			alert("修改成功!", "");
		}

		var operation = document.getElementById("operationresult");
		if(operation.value.indexOf("1") == 0)
		{
			alert("操作成功", "");
		}

		var objtypelistsize = document.getElementById("typelistsize");
		divcounter = objtypelistsize.value;
		divcounter--;
	};
</script>
<script src="/js/admin/addcommodity.js"></script>