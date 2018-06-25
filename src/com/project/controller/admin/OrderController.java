package com.project.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Constants.OperationResult;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.CoachReserve;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Cycle;
import com.project.pojo.DateAssist;
import com.project.pojo.Manager;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.pojo.Weuser;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICoach_reserve_timeService;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICoach_teach_personService;
import com.project.service.ICycleService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPayInfo_thirdPay_orderNoService;
import com.project.service.IPlaygroundService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;
import com.project.service.ISystemConfigService;
import com.project.service.ITrade_recodeService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;
import com.project.util.GenNumberTool;
import com.project.util.LatitudeUtil;

@Controller("adminOrderWebController")
@RequestMapping(value = "/pgm")
public class OrderController extends BaseController {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OrderController.class);

	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IPlaygroundService orderDetail4subService;
	@Resource
	private IOrderComponent orderComponent;
	@Resource
	private ICoachService orderMainService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IWeuserService weuserService;
	@Resource
	private IOrderMainService mainOrderService;
	@Resource
	private ITrade_recodeService trade_recodeService;
	@Resource
	private ICycleService cycleService;
	@Resource
	private ICoachService coachService;
	@Resource
	private ICoachReserveService coachreserveService;
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private ICoach_teach_personService coach_teach_personService;
    @Resource
    private IPayInfo_thirdPay_orderNoService payInfo_thirdPay_orderNoService; 
    @Resource
    private ICoach_reserve_timeService coach_reserve_timeService;
    @Resource
    private ISpaceService spaceService;
    @Resource
    private ISystemConfigService systemConfigService;
	

	@UserRightAuth(menuCode = "order_manager")
	@RequestMapping(value = "orderlist")
	public String orderList(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
		int pageNumber;
		int pageSize;
		PlaygroundManager k = getPlaygroundManager(request);
		Manager manager = getManager(request);
		int playgroundManagerId = 0;	//场馆运营者ID
		if(k==null && manager==null){
			return "redirect:login.do";
		}else{
			if(k!=null){
				playgroundManagerId = k.getId();
			}
		}
		try {
			if (request.getParameter("pagenumber") == null
					|| "".equals(request.getParameter("pagenumber"))) {
				pageNumber = 1;
			} else {
				pageNumber = Integer.parseInt(request
						.getParameter("pagenumber"));
			}

			if (request.getParameter("pagesize") == null
					|| "".equals(request.getParameter("pagesize"))) {
				pageSize = 10;
			} else {
				pageSize = Integer.parseInt(request.getParameter("pagesize"));
			}

			String orderid = request.getParameter("orderid");
			if (orderid == null) {
				orderid = "";
			}
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String baseStatus = request.getParameter("basestatus");
			String status = request.getParameter("status");
			String deliveryStatus = request.getParameter("deliverystatus");
			String playgroundId = request.getParameter("playgroundId");

			int intBaseStatus = 0;
			int intPayStatus = 0;
			int intDeliveryStatus = 0;
			int intPlaygroundId=0;

			if (CommonUtil.NotEmpty(baseStatus)) {
				intBaseStatus = Integer.parseInt(baseStatus);
			}

			if (CommonUtil.NotEmpty(status)) {
				intPayStatus = Integer.parseInt(status);
			}

			if (CommonUtil.NotEmpty(deliveryStatus)) {
				intDeliveryStatus = Integer.parseInt(deliveryStatus);
			}
			if (CommonUtil.NotEmpty(playgroundId)) {
				intPlaygroundId = Integer.parseInt(playgroundId);
			}

			PageFinder<Orderinfo> pageFinder = orderInfoService.orderList(
					orderid, startdate, enddate, intBaseStatus, intPayStatus,
					intDeliveryStatus, 0, pageNumber, pageSize,intPlaygroundId,playgroundManagerId,0);
			
			if(pageFinder!=null && pageFinder.getDataList()!=null){
				for(Orderinfo o: pageFinder.getDataList()){
					if(o.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
						o.setPlaygroundName(playgroundService.getPlaygroundById(Integer.valueOf(o.getActiveID())).getName());
					}
					
					if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						o.setCoachName(coachService.getcoachById(Integer.valueOf(o.getActiveID())));
					}
					
					if(o.getSpaceId()!=null && o.getSpaceId()!=0){
						o.setSpaceName(spaceService.getSpace(o.getSpaceId()).getName());
					}
				}
			}
			
			map.put("playground", playgroundService.getAll());
			map.put("data_page", pageFinder);
			map.put("startdate", startdate);
			map.put("enddate", enddate);
			map.put("orderid", orderid);
			map.put("basestatus", baseStatus);
			map.put("status", status);
			map.put("deliverystatus", deliveryStatus);
			map.put("manager", manager);
			logger.info("查询成功");
			return "admin/order/order_list";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}
	
	@UserRightAuth(menuCode = "order_manager")
	@RequestMapping(value = "userorderList")
	public String userorderList(HttpServletRequest request, ModelMap map,Integer userId) {
		CommonUtil.printHTTP(request);
		int pageNumber;
		int pageSize;

		try {
			if (request.getParameter("pagenumber") == null
					|| "".equals(request.getParameter("pagenumber"))) {
				pageNumber = 1;
			} else {
				pageNumber = Integer.parseInt(request
						.getParameter("pagenumber"));
			}

			if (request.getParameter("pagesize") == null
					|| "".equals(request.getParameter("pagesize"))) {
				pageSize = 10;
			} else {
				pageSize = Integer.parseInt(request.getParameter("pagesize"));
			}

			String orderid = request.getParameter("orderid");
			if (orderid == null) {
				orderid = "";
			}
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String baseStatus = request.getParameter("basestatus");
			String payStatus = request.getParameter("paystatus");
			String deliveryStatus = request.getParameter("deliverystatus");
			String playgroundId = request.getParameter("playgroundId");

			int intBaseStatus = 0;
			int intPayStatus = 0;
			int intDeliveryStatus = 0;
			int intPlaygroundId=0;

			if (CommonUtil.NotEmpty(baseStatus)) {
				intBaseStatus = Integer.parseInt(baseStatus);
			}

			if (CommonUtil.NotEmpty(payStatus)) {
				intPayStatus = Integer.parseInt(payStatus);
			}

			if (CommonUtil.NotEmpty(deliveryStatus)) {
				intDeliveryStatus = Integer.parseInt(deliveryStatus);
			}
			if (CommonUtil.NotEmpty(playgroundId)) {
				intPlaygroundId = Integer.parseInt(playgroundId);
			}

			PageFinder<Orderinfo> pageFinder = orderInfoService.orderList(
					orderid, startdate, enddate, intBaseStatus, intPayStatus,
					intDeliveryStatus, userId, pageNumber, pageSize,intPlaygroundId,0,0);
			
			Integer count=0;
			Double priceCount=0.0;
			if(pageFinder.getDataList()!=null){
				for(Orderinfo o: pageFinder.getDataList()){
					if(o.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
						o.setPlaygroundName(playgroundService.getPlaygroundById(Integer.valueOf(o.getActiveID())).getName());
					}
					
					if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						o.setCoachName(coachService.getcoachById(Integer.valueOf(o.getActiveID())));
					}
					
					if(o.getSpaceId()!=null){
						o.setSpaceName(spaceService.getSpace(o.getSpaceId()).getName());
					}
					
					if(o.getStatus()!=Constants.OrderStatus.DELETE.getStatus() || o.getStatus()!=Constants.OrderStatus.PAY_STAY_PAID.getStatus()){
						priceCount+=o.getOrderPayTotalAmont();
					}
					count++;
				}
			}
			
			
			map.put("count", count);
			map.put("priceCount", priceCount);
			map.put("playground", playgroundService.getAll());
			map.put("data_page", pageFinder);
			map.put("startdate", startdate);
			map.put("enddate", enddate);
			map.put("orderid", orderid);
			map.put("basestatus", baseStatus);
			map.put("paystatus", payStatus);
			map.put("deliverystatus", deliveryStatus);
			map.put("userId", userId);

			
			
			logger.info("查询成功");
			return "admin/user/order_list";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}
	
	
	/**
	 * 订单详情
	 * @param request
	 * @param map
	 * @param orderSubNo
	 * @return
	 */
	@RequestMapping(value = "order_dis")
	public String order_dis(HttpServletRequest request, ModelMap map,String orderSubNo) {
		Orderinfo orderinfo = orderInfoService.getOrderByorderSubNo(orderSubNo);
		map.put("order", orderinfo);
		String name = "";
		//1场馆,2教练,3活动,4培训,5赛事
		if(orderinfo.getOrderType() == 1){
			name = playgroundService.getPlaygroundById(Integer.valueOf(orderinfo.getActiveID())).getName();
		}else if(orderinfo.getOrderType() == 2){
			name = coachService.getcoachById(Integer.valueOf(orderinfo.getActiveID())).getName();
		}
		map.put("name", name);
		Space sp = spaceService.getSpace(orderinfo.getSpaceId());
		String space_name ="";
		if(sp != null){
			space_name = sp.getName();
		}
		map.put("space_name", space_name);
		return "admin/order/order_dis";
	}
	
	@UserRightAuth(menuCode = "order_manager")
	@RequestMapping(value = "todayOrder")
	public String todayOrder(HttpServletRequest request, ModelMap map) {
		PlaygroundManager k = getPlaygroundManager(request);
		Manager manager = getManager(request);
		Integer palygroundManagerId=0;
		if(k==null && manager==null){
			return "redirect:login.do";
		}
		if(k!=null){
			palygroundManagerId=k.getId();
		}
		CommonUtil.printHTTP(request);
		int pageNumber;
		int pageSize;

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		String startdate = format.format(new Date());
		
		try {
			if (request.getParameter("pagenumber") == null
					|| "".equals(request.getParameter("pagenumber"))) {
				pageNumber = 1;
			} else {
				pageNumber = Integer.parseInt(request
						.getParameter("pagenumber"));
			}

			if (request.getParameter("pagesize") == null
					|| "".equals(request.getParameter("pagesize"))) {
				pageSize = 10;
			} else {
				pageSize = Integer.parseInt(request.getParameter("pagesize"));
			}

			String orderid = request.getParameter("orderid");
			if (orderid == null) {
				orderid = "";
			}
			String enddate = request.getParameter("enddate");
			String baseStatus = request.getParameter("basestatus");
			String status = request.getParameter("status");
			String deliveryStatus = request.getParameter("deliverystatus");
			String playgroundId = request.getParameter("playgroundId");

			int intBaseStatus = 0;
			int intPayStatus = 0;
			int intDeliveryStatus = 0;
			int intPlaygroundId=0;

			if (CommonUtil.NotEmpty(baseStatus)) {
				intBaseStatus = Integer.parseInt(baseStatus);
			}

			if (CommonUtil.NotEmpty(status)) {
				intPayStatus = Integer.parseInt(status);
			}

			if (CommonUtil.NotEmpty(deliveryStatus)) {
				intDeliveryStatus = Integer.parseInt(deliveryStatus);
			}
			if (CommonUtil.NotEmpty(playgroundId)) {
				intPlaygroundId = Integer.parseInt(playgroundId);
			}

			PageFinder<Orderinfo> pageFinder = orderInfoService.orderList(
					orderid, startdate, enddate, intBaseStatus, intPayStatus,
					intDeliveryStatus, 0, pageNumber, pageSize,intPlaygroundId,palygroundManagerId,0);
			
			List<Orderinfo> list=orderInfoService.getOrderInfoByCreate(startdate,palygroundManagerId);
			
			double moenyCount=0;
			
			for(Orderinfo o:list){
				moenyCount+=o.getOrderPayTotalAmont();
			}
			if(pageFinder.getDataList()!=null){
				for(Orderinfo o: pageFinder.getDataList()){
					if(o.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
						o.setPlaygroundName(playgroundService.getPlaygroundById(Integer.valueOf(o.getActiveID())).getName());
					}
					
					if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						o.setCoachName(coachService.getcoachById(Integer.valueOf(o.getActiveID())));
					}
					
					if(o.getSpaceId()!=null){
						o.setSpaceName(spaceService.getSpace(o.getSpaceId()).getName());
					}
				
				}
			}
			
			
			map.put("moenyCount", moenyCount);
			map.put("count", list.size());
			map.put("playground", playgroundService.getAll());
			map.put("data_page", pageFinder);
			map.put("startdate", startdate);
			map.put("enddate", enddate);
			map.put("orderid", orderid);
			map.put("basestatus", baseStatus);
			map.put("status", status);
			map.put("deliverystatus", deliveryStatus);

			logger.info("查询成功");
			return "admin/order/todayorder_list";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}
	
	@RequestMapping(value = "waitforconfirmlist")
	public String waitForConfirmList(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);

		try {
			map.put("paystatus", Constants.OrderStatus.PAY_PAID.getStatus());// 支付状态
			return "redirect:orderlist.do?paystatus=9&basestatus=1";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}

	@RequestMapping(value = "userhandlepay")
	public String userhandlepay(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);

		try {
			map.put("paystatus", Constants.OrderStatus.PAY_PART.getStatus());// 支付状态
			return "redirect:orderlist.do?paystatus=8&basestatus=1";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}

	@RequestMapping(value = "waitfordeliverylist")
	public String waitForDeliveryList(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);

		try {
			return "redirect:orderlist.do?paystatus=9&basestatus=2&deliverystatus=14";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}

	
	/**
     * 带场地的
     * @param request
     * @param map
     * @param sn
     * @param endTime
     * @return
     */
    @RequestMapping(value="toOrder")
	public String toOrder(HttpServletRequest request, ModelMap map,String spacePriceId,String endTime,Integer userId,Integer person){
    	logger.info("生成带场地的订单.....");	long s_long = System.currentTimeMillis();		//开始计时
    	OrderMain om=new OrderMain();
    	om.setIs_back(1);
    	//获取场馆ID
    	Integer playgroundId=Integer.valueOf(request.getParameter("playgroundId"));
    	
    	//获取教练ID
    	Integer coachId=Integer.valueOf(request.getParameter("coachId"));
    	
    	Integer inCoachId=Integer.valueOf(request.getParameter("inCoachId"));//驻场教练id
    	
    	
    	//拆分场地ID
    	String[] st=spacePriceId.split(",");
    	List<Integer> stp=new ArrayList<Integer>();
    	boolean flag= Boolean.valueOf(request.getParameter("cycle"));
    	
    	for(String n:st){
    		stp	.add(Integer.valueOf(n));
    	}
    	Date initialDate=null;
		//循环拆分的场地ID获取到预订的场地信息
		List<Space_time_price> sList=new ArrayList<Space_time_price>();
		List<DateAssist> list=new ArrayList<DateAssist>();
		SimpleDateFormat formath = new SimpleDateFormat("yyyy-MM-dd HH");
		for(Integer i:stp){
			Space_time_price temp_s= space_time_priceService.findById(i);		//临时对象  场地时间价格
			int space_id = 0;			//临时变量  场地id				需要把最早打球时间的场地放到主订单
				
			
			sList.add(space_time_priceService.findById(i));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				
				Date date1=formath.parse(space_time_priceService.findById(i).getDate()+" "+space_time_priceService.findById(i).getTime());
				//edit by nantian 2015-12-28	计算出最早的时间
				if(initialDate==null){
					initialDate=date1;
					if(temp_s!=null)space_id = temp_s.getSpace_id().getId();			//add by lxc	2016-02-22
				}
				if(date1.before(initialDate)){
					initialDate=date1;
					if(temp_s!=null)space_id = temp_s.getSpace_id().getId();			//add by lxc	2016-02-22
				}
				Date date=format.parse(space_time_priceService.findById(i).getDate());
				DateAssist da=new DateAssist();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
				da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
				if(calendar.get(Calendar.DATE)<10){
					da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
				}else{ 
					da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
				}
				list.add(da);
				om.setInitialDate(formath.format(initialDate));//设置最早打球时间
				om.setSpaceId(space_id);					//把最早打球时间的场地放到主订单
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		//去除重复值
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
		      if  (list.get(j).getMonth().equals(list.get(i).getMonth()) && list.get(j).getDay().equals(list.get(i).getDay()))   { 
		        list.remove(j); 
		      } 
		    } 
		}
		Weuser user=null;
		if(userId!=0){
			user=weuserService.getUserById(userId);
		}
		if(user==null){
			logger.error("用户不存在....");
		}
		map.put("user", user);
		map.put("tList", list); 
		map.put("sList", sList);
		
		Integer hourCount=0;
		
		
		//对主订单信息进行操作
		om.setOrderMainNo(GenNumberTool.creatOrderNo("A"));
		if(userId!=0){
			om.setWeuser(user);
		}else{
			om.setPhone(request.getParameter("phone"));
			om.setPhoneName(request.getParameter("phoneName"));
			om.setIs_member(2);
		}
		
		if(inCoachId!=0){om.setIncoachId(inCoachId);}//驻场教练id,(如果下单时,选择了驻场教练,则保存的是教练ID)		edit by lxc	2016-02-23
		
		//截取前台传递的字符串
		om.setProdTotalNum(st.length);
    	try {
			mainOrderService.saveOrderMain(om);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	double count=0;
    	List<Coach> coachList=new ArrayList<Coach>();
    	Coach coach=null;
    	Playground p = null;
    	Integer playgroundmanager_id = 0;
    	if(playgroundId>0){
    		p=playgroundService.getPlaygroundById(playgroundId);
    		playgroundmanager_id = p.getPlaygroundmanager_id();
    	}
    	if(coachId>0){
    		coach=coachService.getcoachById(coachId);
    		playgroundmanager_id = coach.getPlaygroundmanager_id();
    		coachList.add(coach);
    	}
    	Integer o=1;
    	//生成子订单的信息
		for(Integer i:stp){
			
			Orderinfo order=new Orderinfo();
			Space_time_price s= space_time_priceService.findById(i);
			
			if(userId!=0){
				order.setWeuser(user);
				order.setPhone(user.getUphone());
			}else{
				order.setPhone(request.getParameter("phone"));
				order.setPhoneName(request.getParameter("phoneName"));
				order.setIs_member(2);
			}
			
			order.setIs_back(1);
			order.setActiveID(String.valueOf(playgroundId));
			order.setCreate_date(s.getDate());
			order.setToday_time(String.valueOf(s.getTime()));
			order.setOrderMainNo(om.getOrderMainNo());
			order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
			order.setOrderPayTotalAmont(s.getPrice());
			order.setSpaceId(s.getSpace_id().getId());
			order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
			
			order.setPlaygroundmanager_id(playgroundmanager_id);
			s.setIs_reserve(1);
			
			o++;
			
			try {
				//完成带教练的预订
				count+=coachOrderInfo(inCoachId,om.getOrderMainNo(),order,coach,s,user,coachList,coach_teach_personService.getCoach_teach_personById(person),o);
		    	
				orderInfoService.saveOrderInfoService(order);
				space_time_priceService.updatespace_time_price(s);
				// 生成正常订单日志
				orderComponent.genOrderLog(order.getWeuser(), order);
			} catch (Exception e) {
				e.printStackTrace();
			}
			count+=s.getPrice();
			hourCount++;
		}
		
		
		
		
		int weekcount=1;
		//对周期预订做判断，当为真时实现周期预订的插入
		if(flag){
			boolean f=true;
			int interval=7;
			while(f){
				
				for(Space_time_price i:sList){
					  Cycle cycle=new Cycle();
					  cycle.setUserId(user.getId());
					  cycle.setOrderMainNo(om.getOrderMainNo());
					  DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); 
					  
					  
					  Date end;
					  Calendar calendar = new GregorianCalendar();
					  try {
						  end = dateformat.parse(endTime);
						   
						   calendar.setTime(dateformat.parse(i.getDate()));
						   calendar.add(calendar.DATE,interval);//把日期往后增加一天.整数往后推,负数往前移动
						   
					   //实现时间的判断
					   if(calendar.getTime().after(end)){
						   f=false;
					   }
					   if(calendar.getTime().before(end)){
						   weekcount+=1;
						   Orderinfo order=new Orderinfo();
						   if(userId!=0){
								order.setWeuser(user);
								order.setPhone(user.getUphone());
							}else{
								order.setPhone(request.getParameter("phone"));
								order.setPhoneName(request.getParameter("phoneName"));
								order.setIs_member(2);
							}
						    order.setIs_back(1);
							order.setSpaceId(i.getSpace_id().getId());
							order.setCreate_date(i.getDate());
							order.setToday_time(String.valueOf(i.getTime()));
							order.setOrderMainNo(om.getOrderMainNo());
							order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
							order.setOrderPayTotalAmont(i.getPrice());
							order.setActiveID(String.valueOf(playgroundId));
							order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
							
							order.setPlaygroundmanager_id(playgroundmanager_id);
							o++;
							try {
								
								//完成带教练的预订
								count+=coachOrderInfo(inCoachId,om.getOrderMainNo(),order,coach,i,user,coachList,coach_teach_personService.getCoach_teach_personById(person),o);
								orderInfoService.saveOrderInfoService(order);
								//space_time_priceService.updatespace_time_price(s);
								// 生成正常订单日志
								orderComponent.genOrderLog(order.getWeuser(), order);
							} catch (Exception e) {
								e.printStackTrace();
							}
							count+=i.getPrice();
					   }
					   if(dateformat.format(calendar.getTime()).equals(dateformat.format(end))){
						   weekcount+=1;
						   Orderinfo order=new Orderinfo();
						   if(userId!=0){
								order.setWeuser(user);
								order.setPhone(user.getUphone());
							}else{
								order.setPhone(request.getParameter("phone"));
								order.setPhoneName(request.getParameter("phoneName"));
								order.setIs_member(2);
							}
						   order.setIs_back(1);;
							order.setSpaceId(i.getSpace_id().getId());
							order.setCreate_date(i.getDate());
							order.setToday_time(String.valueOf(i.getTime()));
							order.setOrderMainNo(om.getOrderMainNo());
							order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
							order.setOrderPayTotalAmont(i.getPrice());
							order.setActiveID(String.valueOf(playgroundId));
							order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
							
							order.setPlaygroundmanager_id(playgroundmanager_id);
							o++;
							try {
								
								
								//完成带教练的预订
								count+=coachOrderInfo(inCoachId,om.getOrderMainNo(),order,coach,i,user,coachList,coach_teach_personService.getCoach_teach_personById(person),o);
								orderInfoService.saveOrderInfoService(order);
								//space_time_priceService.updatespace_time_price(s);
								// 生成正常订单日志
								orderComponent.genOrderLog(order.getWeuser(), order);
							} catch (Exception e) {
								e.printStackTrace();
							}
							count+=i.getPrice();
					   }
					   cycle.setTimepoint(String.valueOf(i.getTime()));
					   cycle.setTime(dateformat.format(calendar.getTime()));
					   cycleService.saveCycle(cycle);
					  }catch(ParseException e){
						  e.printStackTrace();
					  }catch(Exception e){
						  e.printStackTrace();
					  }
				}
				
				interval+=7;
				
			}
			
			
		}
		//去除教练重复值
		for  ( int  i  =   0 ; i  <  coachList.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  coachList.size()  -   1 ; j  >  i; j -- )   { 
		      if  (coachList.get(j).getId()==coachList.get(i).getId())   { 
		    	  coachList.remove(j); 
		      } 
		    } 
		}
		
		map.put("coachList", coachList);
		map.put("interval",LatitudeUtil.Distance(22.549392, 113.957541, p.getCoordinateslongitude(), p.getCoordinateslatitude()));
		map.put("playground", p);
		om.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
		om.setActiveID(String.valueOf(playgroundId));
		om.setName(p.getName());
		om.setImg(p.getPdImg());
		om.setWeekCount(weekcount);
		om.setTotalAmount(count);
		
		try {
			mainOrderService.updateOrderMain(om);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(person!=0){
			map.put("coachPrice", coach_teach_personService.getCoach_teach_personById(person).getPrice());
		}
		map.put("weekcount",weekcount);
		map.put("price", om.getTotalAmount());
		map.put("mainId", om.getId());
		map.put("userId", userId);
		map.put("orderMainNo", om.getOrderMainNo());
		map.put("hourCount", hourCount*weekcount);
		return "admin/playground/shoppingCart";
	}
	
    
    public int coachOrderInfo(Integer coachId,String orderMainNo ,Orderinfo order,Coach coach,Space_time_price s,Weuser user,List<Coach> coachList,Coach_teach_person price,Integer o)throws Exception{
		int count=0;
		//当场地教练ID不等于0的时候放入教练，并向教练预约表中插入数据
    	if(coachId!=0){
    		coach=coachService.getcoachById(coachId);
    		CoachReserve cs=new CoachReserve();
    		//生成教练的子订单
    		Orderinfo corder=new Orderinfo();
    		
    		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(sdf.parse(s.getDate()));
			int week=calendar.get(Calendar.DAY_OF_WEEK);
			corder.setDetails("教练对应带人数"+price.getPeople_num());
			corder.setActiveID(String.valueOf(coachId));
			corder.setOrderType(Constants.DATATYPE.CoachType.getStatus());
			corder.setSpaceId(s.getSpace_id().getId());
			corder.setCreate_date(s.getDate());
			corder.setToday_time(String.valueOf(s.getTime()));
			corder.setWeuser(user);
			corder.setPhone(user.getUphone());
			corder.setOrderMainNo(orderMainNo);
			corder.setOrderSubNo(GenNumberTool.getOrderSubNo(o,orderMainNo));
			corder.setOrderPayTotalAmont(Double.valueOf(price.getPrice()));
			count+=price.getPrice();
			corder.setPlaygroundmanager_id(coach.getPlaygroundmanager_id());
			orderInfoService.saveOrderInfoService(corder);
    		
    		cs.setCoachId(coachId);
    		cs.setDate(s.getDate());
    		cs.setTimepoint(s.getTime());
    		coachreserveService.saveCoachReserve(cs);
    		
    		coachList.add(coach);
    		o++;
    	}
		
		return count;
	}
    
    public String getWeek(int week){
		if(week==1){
			return "星期天";
		}else if(week ==2){
			return "星期一";
		}else if(week ==3){
			return "星期二";
		}else if(week ==4){
			return "星期三";
		}else if(week ==5){
			return "星期四";
		}else if(week ==6){
			return "星期五";
		}else{
			return "星期六";
		}
	}
    
    @UserRightAuth(menuCode = "order_manager")
	@RequestMapping(value = "canorderlist")
	public String canorderList(HttpServletRequest request, ModelMap map) {
		CommonUtil.printHTTP(request);
		int pageNumber;
		int pageSize;
		PlaygroundManager k = getPlaygroundManager(request);
		Manager manager = getManager(request);
		int playgroundManagerId = 0;	//场馆运营者ID
		if(k==null && manager==null){
			return "redirect:login.do";
		}else{
			if(k!=null){
				playgroundManagerId = k.getId();
			}
		}
		try {
			if (request.getParameter("pagenumber") == null
					|| "".equals(request.getParameter("pagenumber"))) {
				pageNumber = 1;
			} else {
				pageNumber = Integer.parseInt(request
						.getParameter("pagenumber"));
			}

			if (request.getParameter("pagesize") == null
					|| "".equals(request.getParameter("pagesize"))) {
				pageSize = 10;
			} else {
				pageSize = Integer.parseInt(request.getParameter("pagesize"));
			}

			String orderid = request.getParameter("orderid");
			if (orderid == null) {
				orderid = "";
			}
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String baseStatus = request.getParameter("basestatus");
			String status = request.getParameter("status");
			String deliveryStatus = request.getParameter("deliverystatus");
			String playgroundId = request.getParameter("playgroundId");

			int intBaseStatus = 0;
			int intPayStatus = 0;
			int intDeliveryStatus = 0;
			int intPlaygroundId=0;

			if (CommonUtil.NotEmpty(baseStatus)) {
				intBaseStatus = Integer.parseInt(baseStatus);
			}

			if (CommonUtil.NotEmpty(status)) {
				intPayStatus = Integer.parseInt(status);
			}

			if (CommonUtil.NotEmpty(deliveryStatus)) {
				intDeliveryStatus = Integer.parseInt(deliveryStatus);
			}	
			if (CommonUtil.NotEmpty(playgroundId)) {
				intPlaygroundId = Integer.parseInt(playgroundId);
			}

			PageFinder<Orderinfo> pageFinder = orderInfoService.orderList(
					orderid, startdate, enddate, intBaseStatus, intPayStatus,
					intDeliveryStatus, 0, pageNumber, pageSize,intPlaygroundId,playgroundManagerId,1);
			
			if(pageFinder!=null && pageFinder.getDataList()!=null){
				for(Orderinfo o: pageFinder.getDataList()){
					if(o.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
						o.setPlaygroundName(playgroundService.getPlaygroundById(Integer.valueOf(o.getActiveID())).getName());
					}
					
					if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						o.setCoachName(coachService.getcoachById(Integer.valueOf(o.getActiveID())));
					}
					
					if(o.getSpaceId()!=null){
						o.setSpaceName(spaceService.getSpace(o.getSpaceId()).getName());
					}
				}
			}
			
			map.put("playground", playgroundService.getPlaygroundListByPlaygroundManagerId(playgroundManagerId));
			map.put("data_page", pageFinder);
			map.put("startdate", startdate);
			map.put("enddate", enddate);
			map.put("orderid", orderid);
			map.put("basestatus", baseStatus);
			map.put("status", status);
			map.put("deliverystatus", deliveryStatus);

			logger.info("查询成功");
			return "admin/playground_manager/order_list";
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
			// TODO 跳转到错误页面
			return "";
		}
	}
    
    /**
     * 后台取消订单
     * @param request
     * @param map
     * @param orderMainId
     * @param userId
     * @return
     */
    @RequestMapping(value = "canOrder")
    @ResponseBody
    @Transactional
	public Object canOrder(HttpServletRequest request, ModelMap map,String orderMainId,Integer userId){
    	OrderMain om = mainOrderService.getOrderMainByNO(orderMainId);
    	
    	if(om.getPayStatus()==Constants.OrderStatus.PAY_STAY_PAID.getStatus()){
    		try {
    			mainOrderService.cancelOrder(om.getId(), userId);
        		return new BusinessResponse(
        				Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new BusinessResponse(
        				Constants.OperationResult.SYSTEM_ERROR.getStatus(), "系统错误", "系统错误");
			}
    	}else if(om.getPayStatus()==Constants.OrderStatus.PAY_PAID.getStatus()){
    		try{
	    		mainOrderService.cancelOrderUserMoeny(orderMainId);
	    		return new BusinessResponse(
	    				Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new BusinessResponse(
	    				Constants.OperationResult.SYSTEM_ERROR.getStatus(), "系统错误", "系统错误");
			}
    	}else if(om.getPayStatus()==Constants.OrderStatus.DELETE.getStatus()){
    		return new BusinessResponse(
    				OperationResult.STATUS_EDITED_NO_SUBMIT.getStatus(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg());
    	}else if(om.getPayStatus()==Constants.OrderStatus.PAY_REFUND_ALL.getStatus()){
    		return new BusinessResponse(
    				OperationResult.STATUS_EDITED_NO_SUBMIT.getStatus(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg());
    	}else{
    		return new BusinessResponse(
    				Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(), "失败", "有相关联的订单正在执行中或已完成,不能取消");
    	}
	}
    
    
    /**
     * 后台现场支付订单
     * @param request
     * @param map
     * @param orderMainId
     * @return
     */
    @RequestMapping(value = "livePayOrder")
    @ResponseBody
    @Transactional
	public Object livePayOrder(HttpServletRequest request, ModelMap map,String orderMainId){
    	String orderMainNo = orderMainId;
    	OrderMain om = mainOrderService.getOrderMainByNO(orderMainNo);
    	
    	if(om.getPayStatus()==Constants.OrderStatus.PAY_STAY_PAID.getStatus()){
    		try {
    			mainOrderService.livePayOrder(om);
        		return new BusinessResponse(
        				Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new BusinessResponse(OperationResult.SYSTEM_ERROR.getStatus(), "系统错误", "系统错误");
			}
    	}else if(om.getPayStatus()==Constants.OrderStatus.LIVE_PAY.getStatus()){
    		return new BusinessResponse(
    				OperationResult.STATUS_EDITED_NO_SUBMIT.getStatus(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg());
    	}else{
    		return new BusinessResponse(
    				Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(), "失败", "有相关联的订单正在执行中或已完成,不能取消");
    	}
		
	}
}
