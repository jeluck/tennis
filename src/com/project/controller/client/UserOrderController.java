package com.project.controller.client;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.common.Constants.OperationResult;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Weuser;
import com.project.service.ICoachService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundService;
import com.project.service.IWeuserService;

@Controller("userorderController")
@RequestMapping(value="/")
public class UserOrderController extends BaseController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserOrderController.class);

    @Resource
    private IPlaygroundService orderDetail4subService;
    @Resource
    private IOrderInfoService orderInfoService;
	@Resource
	private IOrderComponent orderComponent;
	
	@Resource
	private IOrderMainService orderMainService;
	@Resource
	private IWeuserService weuserService;
    
	@Resource
	private ICoachService coachService;
    
    /**
	 * 跳转教练我的订单
	 * @return
	 */
	@RequestMapping(value = "toCoachMineOrderform")
	public String toMineOrderform(HttpServletRequest request,
								  @RequestParam(defaultValue="1")int wholePageNumber,
								  @RequestParam(defaultValue="1")int weekPageNumber,
								  @RequestParam(defaultValue="1")int toBePerformedPageNumber,
								  @RequestParam(defaultValue="1")int toBeEvaluatedPageNumber,
								  @RequestParam(defaultValue="0")int orderType,
								  int weuserId,ModelMap map){
		Coach c = getCoach(request);
		c =coachService.getLonginByHttpServletRequest_Id(request,c, this.getClass() );
		
		OrderMain o = new OrderMain();
		o.setOrderType(orderType);
		//全部
		PageFinder<OrderMain> whole = orderMainService.getOrderMain(0,o, weuserId, wholePageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("whole", whole);
		
		//本周
		PageFinder<OrderMain> week = orderMainService.getOrderMain(1,o, weuserId, weekPageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("week", week);
		
		//待进行
		PageFinder<OrderMain> toBePerformed = orderMainService.getOrderMain(2,o, weuserId, toBePerformedPageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("toBePerformed", toBePerformed);
		
		//待评价
		PageFinder<OrderMain> toBeEvaluated = orderMainService.getOrderMain(3,o, weuserId, toBeEvaluatedPageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("toBeEvaluated", toBeEvaluated);
		map.put("weuserId", weuserId);
		map.put("orderType", orderType);
		map.put("type", request.getParameter("type"));
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		return "/phone/user/myCoachOrder";
	}
	
	
	/**
	 * 教练订单分页
	 * @param type  类型  0.全部 1.本周 2.待进行 3.待评价  
	 * @param pageNumber	当前页码
	 * @param orderType		订单类型
	 * @return
	 */
	@RequestMapping(value = "getCoachOrder")
	@ResponseBody
	public Object getCoachOrder(HttpServletRequest request,
								@RequestParam(defaultValue="0")int type, int weuserId,
								@RequestParam(defaultValue="1")int pageNumber, 
								@RequestParam(defaultValue="0")int orderType){
		OrderMain o = new OrderMain();
		o.setOrderType(orderType);
//		Weuser w = getUser(request);
//		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
//		if(w==null){
//			return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
//		}
		
		PageFinder<OrderMain> pageList = orderMainService.getOrderMain(type,o, weuserId, pageNumber, Constants.FOREGROUND_PAGESIZE);
		if(pageList != null && pageList.getDataList() != null && pageList.getDataList().size() > 0 ){
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), "成功", pageList);
		}else{
			return new BusinessResponse(Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	
    /**
	 * 用户跳转我的订单
	 * @return
	 */
	@RequestMapping(value = "toUserMineOrderform")
	public String toUserMineOrderform(HttpServletRequest request,
									  @RequestParam(defaultValue="1")int wholePageNumber,
									  @RequestParam(defaultValue="1")int weekPageNumber,
									  @RequestParam(defaultValue="1")int toBePerformedPageNumber,
									  @RequestParam(defaultValue="1")int toBeEvaluatedPageNumber,
									  @RequestParam(defaultValue="0")int orderType,
									  int weuserId,ModelMap map){
		logger.info("用户跳转我的订单。。。。");long s_long = System.currentTimeMillis();		//开始计时
		Weuser w = getUser(request);
		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
		
		OrderMain o = new OrderMain();
		o.setOrderType(orderType);
		//全部
		PageFinder<OrderMain> whole = orderMainService.getPageOrderMainByStatus(0,o, weuserId, wholePageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("whole", whole);
		
		//待付款
		PageFinder<OrderMain> week = orderMainService.getPageOrderMainByStatus(1,o, weuserId, weekPageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("week", week);
		
		//待消费
		PageFinder<OrderMain> toBePerformed = orderMainService.getPageOrderMainByStatus(3,o, weuserId, toBePerformedPageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("toBePerformed", toBePerformed);
		
		//待评价
		PageFinder<OrderMain> toBeEvaluated = orderMainService.getPageOrderMainByStatus(5,o, weuserId, toBeEvaluatedPageNumber, Constants.FOREGROUND_PAGESIZE);
		map.put("toBeEvaluated", toBeEvaluated);
		map.put("weuserId", weuserId);
		map.put("orderType", orderType);
		map.put("type", request.getParameter("type"));
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/myUserOrder";
	}	
	
	/**
	 * 用户订单分页
	 * @param type  类型  0.全部 1.待付款 3.待消费 5.待评价  
	 * @param pageNumber	当前页码
	 * @param orderType		订单类型
	 * @return
	 */
	@RequestMapping(value = "getUserOrder")
	@ResponseBody
	public Object getUserOrder(HttpServletRequest request,
							   @RequestParam(defaultValue="0")int type,int weuserId,
							   @RequestParam(defaultValue="1")int pageNumber, 
							   @RequestParam(defaultValue="0")int orderType){
		OrderMain o = new OrderMain();
		o.setOrderType(orderType);
//		Weuser w = getUser(request);
//		w=weuserService.getLonginByHttpServletRequest_Id(request, w, this.getClass());	//根据前端传的ID,找登录者,并且保存到session
//		if(w==null){
//			return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
//		}
		
		PageFinder<OrderMain> pageList = orderMainService.getPageOrderMainByStatus(type,o, weuserId, pageNumber,
				 																		Constants.FOREGROUND_PAGESIZE);
		if(pageList != null && pageList.getDataList() != null && pageList.getDataList().size() > 0 ){
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), "成功", pageList);
		}else{
			return new BusinessResponse(Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	
	/**
	 * 确认完成订单
	 * @param weuserId
	 * @param orderType
	 * @param orderMainNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "carryOrderform")
	@ResponseBody
	public Object carryOrderform(int userId,int payStatus,String orderMainNo) throws Exception{
		OrderMain o =orderMainService.getOrderMainByNO(orderMainNo);
		o.setPayStatus(payStatus);
		o = orderMainService.updateOrderMain(o);
		List<Orderinfo> list = orderInfoService.getOrderInfoListByOrderMainId(orderMainNo);
		for (Orderinfo orderinfo : list) {
			orderinfo.setStatus(payStatus);
			orderInfoService.mergeOrderInfo(orderinfo);
		}
		if(o!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", o);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
}
