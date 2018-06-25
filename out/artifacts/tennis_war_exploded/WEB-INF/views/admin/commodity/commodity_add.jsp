<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
<jsp:include page="../init.jsp" />
<script type="text/javascript" src="../js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var index;
	var request;

	//页面跳转
	function go_page(pageNumber){
		location.href = "orderlist.do?pagenumber="+pageNumber;
	}


	function returnoverview()
	{
		document.form1.action = "orderlist.do";
		document.form1.submit();
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
//		var costprice = document.getElementById("costprice");
//		if(isNaN(costprice.value))
//		{
//			alert("成本价请输入数字！");
//			return false;
//		}
//		var saleprice = document.getElementById("saleprice");
//		if(isNaN(saleprice.value))
//		{
//			alert("销售价请输入数字！");
//			return false;
//		}
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
//		alert("divcounter->" + divcounter);
//		alert("delcounter->" + delcounter);
//		if(divcounter == delcounters)
//		{
//			return;
//		}
		if(divcounter > delcounter) {
			var ddiv = document.getElementById(divid);
			ddiv.parentNode.removeChild(ddiv);
			delcounter++;
		}
	}

	function deletePicture(pid)
	{
		var objpic = document.getElementById(pid);
		objpic.value = "";
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
			<li class="active">添加商品</li>
		</ul>
	</div>
	<div class="page-header">
		<h1>添加商品 <span id="time" style="font-size:20px;float: right"></span></h1>
	</div>

	<input type="hidden" value="${saveresult}" id="saveresult" name="saveresult" />

	<form id="QueryForm" name="form1" class="form-inline" action="addcommodity.do" enctype="multipart/form-data"
		  method="post" onsubmit="return checkForm()">
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
										   name="commodityname" style="width: 360px;" placeholder="请输入商品名称"
										   class="col-xs-10 col-sm-5" required  />
								</div>
							</td>
						</tr>

						<tr>
							<td>
								商家：
							</td>
							<td>
								<div class="col-sm-9">
									<select name="company" class="col-xs-5 col-sm-6" >
										<c:forEach items="${companylist }" var="company" varStatus="c">
											<option value="${company.id}">${company.company_name}</option>
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
<%-- 											<option value="${brand.id}">${brand.brand_name}</option> --%>
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
									<select name="category" class="col-xs-5 col-sm-6" id="categoryselect" onchange="getTwocategoryByOneId()">
										<option value="0">请选择</option>
										<c:forEach items="${categorylist }" var="category" varStatus="c">
											<option value="${category.id}">${category.category_name}</option>
										</c:forEach>
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
									<textarea rows="6" cols="10" xclass="col-xs-10 col-sm-5" name="introduction" style="width: 360px;" placeholder="请输入介绍" required ></textarea>
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
									class="col-xs-10 col-sm-5" maxlength="6" required id="express_fee" value="0"/>
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
										   class="col-xs-10 col-sm-5" maxlength="6" required id="acquiescentpictureid"/>
									<input type="button" value="删除" onclick="deletePicture('acquiescentpictureid')"  />
								</div>
								<div class="col-sm-9">
									<input type="file"
										   name="picture1" style="width: 260px;"
										   class="col-xs-10 col-sm-5" maxlength="6" id="picture1id"  />
									<input type="button" value="删除" onclick="deletePicture('picture1id')"  />
								</div>
								<div class="col-sm-9">
									<input type="file"
										   name="picture2" style="width: 260px;"
										   class="col-xs-10 col-sm-5" maxlength="6" id="picture2id"  />
									<input type="button" value="删除" onclick="deletePicture('picture2id')"  />
								</div>
								<%--<div class="col-sm-9">--%>
									<%--<input type="file"--%>
										   <%--name="picture3" style="width: 360px;"--%>
										   <%--class="col-xs-10 col-sm-5" maxlength="6"  />--%>
								<%--</div>--%>
								<%--<div class="col-sm-9">--%>
									<%--<input type="file"--%>
										   <%--name="picture4" style="width: 360px;"--%>
										   <%--class="col-xs-10 col-sm-5" maxlength="6"  />--%>
								<%--</div>--%>
								<%--<div class="col-sm-9">--%>
									<%--<input type="file"--%>
										   <%--name="picture5" style="width: 360px;"--%>
										   <%--class="col-xs-10 col-sm-5" maxlength="6"  />--%>
								<%--</div>--%>
								<%--<div class="col-sm-9">--%>
									<%--<input type="file"--%>
										   <%--name="picture6" style="width: 360px;"--%>
										   <%--class="col-xs-10 col-sm-5" maxlength="6"  />--%>
								<%--</div>--%>
								<%--<div class="col-sm-9">--%>
									<%--<input type="file"--%>
										   <%--name="picture7" style="width: 360px;"--%>
										   <%--class="col-xs-10 col-sm-5" maxlength="6"  />--%>
								<%--</div>--%>
							</td>
						</tr>

						<tr>
							<td>
								型号 &nbsp;&nbsp;
<!-- 								<input type="button" value="添加" onclick="addNewType()"> -->
							</td>
							<td>
								<div class="col-sm-9" id="kinddiv">
									<div id="div0" class="col-sm-9">
<!-- 										型号：&nbsp;&nbsp; -->
<!-- 										<input type="text" name="type0" style="width: 300px;" placeholder="请输入型号名称" class="col-xs-20 col-sm-25" required   /> -->
										长：&nbsp;&nbsp; <input type="number" style="width: 300px;" class="col-xs-20 col-sm-25"  value="" placeholder="长" name="chang0"/><br/>
							           	 宽：&nbsp;&nbsp;  <input type="number" style="width: 300px;" class="col-xs-20 col-sm-25"  value="" placeholder="宽" name="kuan0"><br/>
							         	   厚：&nbsp;&nbsp; <input type="number" style="width: 300px;" class="col-xs-20 col-sm-25"  value="" placeholder="厚" name="hou0"><br/>
							         	   圈号 ：&nbsp;&nbsp; <input type="text" style="width: 300px;" class="col-xs-20 col-sm-25" placeholder="填写商品圈号" name="quanhao0"/><br/>
										价格：&nbsp;&nbsp;
										<input type="text" name="price0" id="price0" style="width: 300px;" class="col-xs-20 col-sm-25" placeholder="请输入价格"  required  onblur="setcommission('price0',${c1},${c2},${c3})" />
										<br/>
										库存：&nbsp;&nbsp;
										<input type="text" name="store0" id="store0" style="width: 300px;"  value="1" placeholder="请输入库存" class="col-xs-20 col-sm-25" required  />
										单位：&nbsp;&nbsp;<select class="modelGray border-none" name="unit">
						                  <option value="手">手</option>
						                  <option value="件" selected>件</option>
						              </select>
<!-- 										<input type="button" value="删除" onclick="deleteType('div0')"  /> -->
									</div>
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
									class="col-xs-10 col-sm-5" maxlength="6" required id="commission_1" />
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
									class="col-xs-10 col-sm-5" maxlength="6" required id="commission_2" />
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
									class="col-xs-10 col-sm-5" maxlength="6" required id="commission_3" />
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
			alert("添加成功!", "");
		};
	};
</script>
<script src="/js/admin/addcommodity.js"></script>