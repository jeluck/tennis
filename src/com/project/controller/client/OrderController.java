package com.project.controller.client;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Constants.DATATYPE;
import com.project.common.Constants.OrderStatus;
import com.project.common.Settings;
import com.project.common.Constants.OperationResult;
import com.project.common.Constants.OrderStatus;
import com.project.controller.BaseController;
import com.project.pojo.*;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICoach_reserve_timeService;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICoach_teach_personService;
import com.project.service.ICommentService;
import com.project.service.ICycleService;
import com.project.service.IEventsService;
import com.project.service.IFriendshipService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPayInfo_thirdPay_orderNoService;
import com.project.service.IPlaygroundImgService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IPlaygroundUserService;
import com.project.service.IRechargeEventsService;
import com.project.service.IRedBagRecordService;
import com.project.service.IRegionService;
import com.project.service.ISpaceManagerService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;
import com.project.service.ISystemConfigService;
import com.project.service.ITennis_circlesService;
import com.project.service.ITrade_recodeService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;
import com.project.service.impl.OrderMainServiceImpl;
import com.project.service.impl.Space_time_priceServiceImpl;
import com.project.task.CancelOrderTask;
import com.project.util.CommonUtil;
import com.project.util.GenNumberTool;
import com.project.util.LatitudeUtil;
import com.project.util.SMSUtil;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;

@Controller("orderController")
@RequestMapping(value="/")
public class OrderController extends BaseController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderController.class);

    @Resource
    private IWeuserService weuserService;
    @Resource
    private ICommentService commonAddressService;
    @Resource
    private ITennis_circlesService tennis_circlesService;
    @Resource
    private ISpaceManagerService produtService;
    @Resource
    private IOrderInfoService orderInfoService;
    @Resource
    private ICoachService orderMainService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IPlaygroundService playgroundService;
	@Resource
	private ISpaceService spaceService;
	@Resource
	private IOrderComponent orderComponent;
	@Resource
	private IRedBagRecordService redBagRecordService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
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
    private IUser_countService user_countService;
    @Resource
	private IRechargeEventsService rechargeEventsService; 
	@Resource
	private ISystemConfigService systemConfigService;
	@Resource
	private IPlaygroundImgService playgroundImgService;
	@Resource
	private IFriendshipService friendshipService;
	@Resource
	private IPlaygroundUserService playgroundUserService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IEventsService eventsService;
	
    /***
	 * 完成订单
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
    @ResponseBody
	@RequestMapping("completeOrder")
	public Object completeOrder(HttpServletRequest request, ModelMap map,String orderMainNo) {
		Weuser w = getUser(request);
    	if(w==null){
    		return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
		}
		CommonUtil.printHTTP(request);
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(),"");
	}
    
    /**
	 * 拒绝订单
	 * @param request
	 * @param map
	 * @return
	 */
    @ResponseBody
	@RequestMapping("deliveryRefuse")
	public Object deliveryRefuse(HttpServletRequest request, ModelMap map,String orderMainNo) {
    	Weuser w = getUser(request);
    	if(w==null){
    		return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
		}
		CommonUtil.printHTTP(request);
		String operator = w.getReal_name();
		try {
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(),"");
	}	
    
    
    /**
     * test 
     * @param request
     * @param map
     * @param pid
     * @return
     */
    @RequestMapping("test")
    public String test(HttpServletRequest request, ModelMap map,String pid){
    	List<Weuser> w = weuserService.getAllUser();
    	map.put("w", w);
    	List<Playground> p = playgroundService.getAll();
    	map.put("p", p);
    	int playground_id = 2; 
    	Playground pe = playgroundService.getPlaygroundById(playground_id);
    	map.put("pe", pe);
    	List<Space> s = spaceService.getPlaygroundSpaceBy_PGid(playground_id,0);
    	map.put("s", s);
    	return "test/play";
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
    @Transactional
	public String toOrder(HttpServletRequest request, ModelMap map,String endTime,Integer userId,Integer person){
    	logger.info("生成带场地的订单.....");	long s_long = System.currentTimeMillis();		//开始计时
    	
    	OrderMain om=new OrderMain();
    	//获取场馆ID
    	Integer playgroundId=Integer.valueOf(request.getParameter("playgroundId"));
    	
    	//获取教练ID
    	Integer coachId=Integer.valueOf(request.getParameter("coachId"));
    	
    	Integer inCoachId=Integer.valueOf(request.getParameter("inCoachId"));		//驻场教练id
    	
    	String sn=request.getParameter("spacePriceId");//场地时间价格ID字符串  以“，”隔开
    	
    	//拆分场地时间价格ID
    	String[] st=sn.split(",");
    	List<Integer> stp=new ArrayList<Integer>();
    	boolean flag= Boolean.valueOf(request.getParameter("cycle"));	//周期预订标识
    	
    	for(String n:st){
    		stp	.add(Integer.valueOf(n));
    	}
    	
    	Integer is_free=0;
    	
    	if(inCoachId!=0){
    		if(coachService.getcoachById(inCoachId).getCoachType()==Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
    			String service=coachService.getcoachById(inCoachId).getServices();
        		
        		if(service.contains("1")){
        			is_free=1;
        		}
    		}
    		
    		om.setIncoachId(inCoachId);//驻场教练id,(如果下单时,选择了驻场教练,则保存的是教练ID)		edit by lxc	2016-02-23
    	}

    	//计算出最早的时间
    	Date initialDate=null;
		//循环拆分的场地ID获取到预订的场地信息
		List<Space_time_price> sList=new ArrayList<Space_time_price>();
		List<DateAssist> list=new ArrayList<DateAssist>();
		for(Integer i:stp){
			Space_time_price temp_s= space_time_priceService.findById(i);		//临时对象  场地时间价格
			int space_id = 0;			//临时变量  场地id				需要把最早打球时间的场地放到主订单
			sList.add(space_time_priceService.findById(i));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
			try {
				
				Date date=format.parse(space_time_priceService.findById(i).getDate()+" "+space_time_priceService.findById(i).getTime());
				//edit by nantian 2015-12-28	计算出最早的时间
				if(initialDate==null){
					initialDate=date;
					if(temp_s!=null)space_id = temp_s.getSpace_id().getId();			//add by lxc	2016-02-22
				}
				if(date.before(initialDate)){
					initialDate=date;
					if(temp_s!=null)space_id = temp_s.getSpace_id().getId();			//add by lxc	2016-02-22
				}
				//E
				
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
				om.setInitialDate(format.format(initialDate));//设置最早打球时间
				om.setSpaceId(space_id);					//把最早打球时间的场地放到主订单
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		//放入场地属于谁的值
//		map.put("spaceBelong", sList.get(0).getSpace_id().getBelongto());
		
		//去除重复值
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
		      if  (list.get(j).getMonth().equals(list.get(i).getMonth()) && list.get(j).getDay().equals(list.get(i).getDay()))   { 
		        list.remove(j); 
		      } 
		    } 
		}
		
		Weuser user=weuserService.getUserById(userId);
		if(user==null){
			logger.error("用户不存在....");
		}
		
		
		
		
		
		//对主订单信息进行操作
		om.setOrderMainNo(GenNumberTool.creatOrderNo("A"));
		om.setWeuser(user);
		
		//截取前台传递的字符串
		om.setProdTotalNum(st.length);
    	try {
			mainOrderService.saveOrderMain(om);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	//主订单价格计算
    	double count=0;
    	List<Coach> coachList=new ArrayList<Coach>();
    	Coach coach=null;
    	Playground p = null;
    	Integer playgroundmanager_id = 0;
    	if(playgroundId>0){
    		p=playgroundService.getPlaygroundById(playgroundId);
    		map.put("playground", p );
    		playgroundmanager_id = p.getPlaygroundmanager_id();
    	}
    	if(coachId>0){
    		coach=coachService.getcoachById(coachId);
    		playgroundmanager_id = coach.getPlaygroundmanager_id();
    		coachList.add(coach);
    	}
    	
    	//生成子订单编号的顺序
    	Integer o=1;
    	
    	//记录场次
    	Integer hourCount=0;
    	
    	Playground p2 =null;
    	//生成子订单的信息
    	
    	//判断其是在教练预订驻场教练还是在场馆预订驻场教练
    	Integer innercoach=0;
    	try {
    		String innercoach_str = request.getParameter("innercoach");
    		if(CommonUtil.NotEmpty(innercoach_str)){
    			innercoach=Integer.valueOf(request.getParameter("innercoach"));
    		}
		} catch (Exception e) {
			e.printStackTrace();
			innercoach=0;
		}
    	
		for(Integer i:stp){
			Orderinfo order=new Orderinfo();
			Space_time_price s= space_time_priceService.findById(i);
			
			//add by ziyao	2015-12-11		运营商的订单详情页需要场馆信息
			try {
				if(p2==null){
					p2=spaceService.getSpace(s.getSpace_id().getId()).getPlayground_id();
					map.put("playground", p2 );
				}
			} catch (Exception e) {
			}

    		//当前端点击返回场地被预订时，提前跳转到shoppingCart;
			if(s.getIs_reserve()==1){
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long));
				return "phone/user/shoppingCart";
			}
			
			
			order.setActiveID(String.valueOf(playgroundId));
			order.setCreate_date(s.getDate());
			order.setToday_time(String.valueOf(s.getTime()));
			order.setWeuser(user);
			order.setPhone(user.getUphone());
			order.setOrderMainNo(om.getOrderMainNo());
			order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
			order.setOrderPayTotalAmont(s.getPrice());
			//当时在教练列表预订驻场教练的时候要将场地价格加上驻场教练本身的价格		edit by nantian 2015-12-22
			if(innercoach==2){
				Coach_set_time cst = coach_set_timeService.getCoach_set_timeByTypeAndHour(inCoachId, s.getDateType(), s.getTime());
				order.setOrderPayTotalAmont(s.getPrice()+cst.getPrice());
				
				for(int j=0;j<sList.size();j++){
					if(sList.get(j).getId()==i){
						sList.get(j).setPrice(order.getOrderPayTotalAmont());
					}
				}
			}
			//E
			order.setSpaceId(s.getSpace_id().getId());
			if(playgroundId!=0){
				order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
			}else{
//				if(coach.getCoachType()==2){
//					CoachReserve cs=new CoachReserve();
//					
//					cs.setCoachId(coachId);
//		    		cs.setDate(order.getCreate_date());
//		    		cs.setTimepoint(s.getTime());
//		    		cs.setOrderSubNo(order.getOrderSubNo());
//		    		
//		    		try {
//						coachreserveService.saveCoachReserve(cs);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
				
				order.setActiveID(String.valueOf(coachId));
				order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
				count+=coach_teach_personService.getCoach_teach_personById(person).getPrice();
				
				//当教练被预约时,往预约表插入数据			edit by lxc	2015-12-31
				CoachReserve cs=new CoachReserve();
				cs.setCoachId(coachId);
	    		cs.setDate(order.getCreate_date());
	    		cs.setTimepoint(s.getTime());
	    		cs.setOrderSubNo(order.getOrderSubNo());
	    		try {
					coachreserveService.saveCoachReserve(cs);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		//E
			}
			order.setPlaygroundmanager_id(playgroundmanager_id);
			s.setIs_reserve(1);
			
			o++;
			
			try {
				hourCount++;
				//完成驻场教练的预订
				count+=coachOrderInfo(inCoachId,om.getOrderMainNo(),order,coach,s,user,coachList,coach_teach_personService.getCoach_teach_personById(person),o);//在方法里,o++后无返回(传进去是什么,就传什么出来)
		    	
				orderInfoService.saveOrderInfoService(order);
				
				//如果驻场外教练下单,则需要再对o加1
				if(inCoachId!=0){
					o++;//
				}
				space_time_priceService.updatespace_time_price(s);
				// 生成正常订单日志
				orderComponent.genOrderLog(order.getWeuser(), order);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(is_free==0){
				count+=order.getOrderPayTotalAmont();
			}
			
		}
		
		if(person!=0){
			map.put("coachPrice", coach_teach_personService.getCoach_teach_personById(person).getPrice());
		}
		
		
		int weekcount=1;
		//对周期预订做判断，当为真时实现周期预订的插入
		if(flag && CommonUtil.NotEmpty(endTime)){
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
						
						   Orderinfo order=new Orderinfo();
							order.setSpaceId(i.getSpace_id().getId());
							order.setCreate_date(dateformat.format(calendar.getTime()));
							order.setToday_time(String.valueOf(i.getTime()));
							order.setWeuser(user);
							order.setPhone(user.getUphone());
							order.setOrderMainNo(om.getOrderMainNo());
							order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
							order.setOrderPayTotalAmont(i.getPrice());
							 //当时在教练列表预订驻场教练的时候要将场地价格加上驻场教练本身的价格		edit by nantian 2015-12-22
							if(innercoach==2){
								Coach_set_time cst = coach_set_timeService.getCoach_set_timeByTypeAndHour(inCoachId, i.getDateType(), i.getTime());
								order.setOrderPayTotalAmont(i.getPrice()+cst.getPrice());
							}
							//E
							if(playgroundId!=0){
								order.setActiveID(String.valueOf(playgroundId));
								order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
							}else{
								
								/*if(coach.getCoachType()==2){
									CoachReserve cs=new CoachReserve();
									
									cs.setCoachId(coachId);
						    		cs.setDate(order.getCreate_date());
						    		cs.setTimepoint(i.getTime());
						    		cs.setOrderSubNo(order.getOrderSubNo());
						    		try {
										coachreserveService.saveCoachReserve(cs);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}*/
								
								order.setActiveID(String.valueOf(coachId));
								order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
								count+=coach_teach_personService.getCoach_teach_personById(person).getPrice();
							}
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
								f=false;
								e.printStackTrace();
							}
//							count+=i.getPrice();		
							if(is_free==0){
								count+=order.getOrderPayTotalAmont();
							}
							cycle.setTimepoint(String.valueOf(i.getTime()));
							cycle.setSpaceId(i.getSpace_id().getId());
							cycle.setTime(dateformat.format(calendar.getTime()));
							cycleService.saveCycle(cycle);
					   }
					   if(dateformat.format(calendar.getTime()).equals(dateformat.format(end))){
						   
						   	Orderinfo order=new Orderinfo();
							order.setSpaceId(i.getSpace_id().getId());
							order.setCreate_date(dateformat.format(calendar.getTime()));
							order.setToday_time(String.valueOf(i.getTime()));
							order.setWeuser(user);
							order.setPhone(user.getUphone());
							order.setOrderMainNo(om.getOrderMainNo());
							order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
							order.setOrderPayTotalAmont(i.getPrice());
							 //当时在教练列表预订驻场教练的时候要将场地价格加上驻场教练本身的价格			edit by nantian 2015-12-22
							if(innercoach==2){
								Coach_set_time cst = coach_set_timeService.getCoach_set_timeByTypeAndHour(inCoachId, i.getDateType(), i.getTime());
								order.setOrderPayTotalAmont(i.getPrice()+cst.getPrice());
							}
							//e
							if(playgroundId!=0){
								order.setActiveID(String.valueOf(playgroundId));
								order.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
							}else{
								
								/*if(coach.getCoachType()==2){
									CoachReserve cs=new CoachReserve();
									
									cs.setCoachId(coachId);
						    		cs.setDate(order.getCreate_date());
						    		cs.setTimepoint(i.getTime());
						    		cs.setOrderSubNo(order.getOrderSubNo());
						    		try {
										coachreserveService.saveCoachReserve(cs);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}*/
								
								
								order.setActiveID(String.valueOf(coachId));
								order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
								count+=coach_teach_personService.getCoach_teach_personById(person).getPrice();
							}
							order.setPlaygroundmanager_id(playgroundmanager_id);
							try {
								
								o++;
								//完成带教练的预订
								count+=coachOrderInfo(inCoachId,om.getOrderMainNo(),order,coach,i,user,coachList,coach_teach_personService.getCoach_teach_personById(person),o);
								orderInfoService.saveOrderInfoService(order);
								//space_time_priceService.updatespace_time_price(s);
								// 生成正常订单日志
								orderComponent.genOrderLog(order.getWeuser(), order);
							} catch (Exception e) {
								f=false;
								e.printStackTrace();
							}
//							count+=i.getPrice();		
							if(is_free==0){
								count+=order.getOrderPayTotalAmont();
							}
							
						   cycle.setTimepoint(String.valueOf(i.getTime()));
						   cycle.setSpaceId(i.getSpace_id().getId());
						   cycle.setTime(dateformat.format(calendar.getTime()));
						   cycleService.saveCycle(cycle);
					   }
					   o++;
					  }catch(ParseException e){
						  f=false;
						  e.printStackTrace();
					  }catch(Exception e){
						  f=false;
						  e.printStackTrace();
					  }
				}
				
				interval+=7;
				if(f==true){
					weekcount+=1;
				}
			}
			
		}
		//当场馆ID不等于0的时候放入场馆
		if(playgroundId!=0){
    		
			map.put("interval", request.getParameter("interval"));
    		map.put("playground", p);
    		om.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
    		om.setActiveID(String.valueOf(playgroundId));
    		om.setName(p.getName());
    		om.setImg(p.getPdImg());
    		try {
				playgroundService.mergePlayground(p);
				playgroundUserService.getOrSavePlaygroundUser(user,p);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
		if(coachId!=0){
			om.setActiveID(String.valueOf(coachId));
			om.setOrderType(Constants.DATATYPE.CoachType.getStatus());
			
			om.setName(coach.getName());
			om.setImg(coach.getHead_portrait());
			
			Playground play=playgroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(coachId).getPhone()).getId());
			playgroundUserService.getOrSavePlaygroundUser(user,play);
			
			
			
		}
		
		if(innercoach==2){
			om.setActiveID(String.valueOf(inCoachId));
			om.setOrderType(Constants.DATATYPE.CoachType.getStatus());
			
			om.setImg(coachService.getcoachById(inCoachId).getHead_portrait());
			Playground play=playgroundService.getPlaygroundById(playgroundId);
			playgroundUserService.getOrSavePlaygroundUser(user,play);

		}
		
		
		om.setPerson(person);
		om.setTotalAmount(count);
		om.setWeekCount(weekcount);
		try {
			mainOrderService.updateOrderMain(om);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//去除教练重复值
		for  ( int  i  =   0 ; i  <  coachList.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  coachList.size()  -   1 ; j  >  i; j -- )   { 
		      if  (coachList.get(j).getId()==coachList.get(i).getId())   { 
		    	  coachList.remove(j); 
		      } 
		    } 
		}
		
		map.put("is_coach", request.getParameter("is_coach"));
		map.put("user", user);
		try {
			map.put("red", redBagRecordService.getRed_bag_recordByUserId(user.getId(), Constants.RewardType.CASHCOUPON.getStatus()).size());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("red", 0);
		}
		
		map.put("tList", list); 
		map.put("sList", sList);
		map.put("coachList", coachList);
		map.put("weekcount",weekcount);
		map.put("price", om.getTotalAmount());
		map.put("mainId", om.getId());
		map.put("userId", userId);
		map.put("orderMainNo", om.getOrderMainNo());
		map.put("hourCount", hourCount*om.getWeekCount());
		
		
		//设置定时器，当用户30分钟没有支付就触发
		Timer timer=new Timer();
		
		CancelOrderTask cancelTask=new CancelOrderTask(om.getOrderMainNo(), timer,mainOrderService);
		String DEFAULT_CANCLE_ORDER_TIME = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_CANCLE_ORDER_TIME,"15");//取系统配置值
		//1000*60*30
		timer.scheduleAtFixedRate(cancelTask, 1000*60*Integer.parseInt(DEFAULT_CANCLE_ORDER_TIME),1000*60*5);
		
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long));
		logger.info("取消订单时间——————"+DEFAULT_CANCLE_ORDER_TIME+"分钟,订单编号为:"+om.getOrderMainNo());
		return "phone/user/shoppingCart";
	}
    
    
    
    /**
     * 赛事订单
     * @param request
     * @param map
     * @param sn 
     * @param endTime
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="toEventsOrder")
    @ResponseBody
    @Transactional
	public Object toEventsOrder(HttpServletRequest request, ModelMap map,int eventsId,String phone,String name) throws Exception{
    	
    	try {
    		logger.info("生成赛事的订单.....");	long s_long = System.currentTimeMillis();		//开始计时
        	Events e = eventsService.getEventsById(eventsId);
        	OrderMain om=new OrderMain();
    		//对主订单信息进行操作
    		om.setWeekCount(1);
    		om.setOrderMainNo(GenNumberTool.creatOrderNo("E"));
    		om.setPhone(phone);
    		om.setPhoneName(name);
    		om.setProdTotalNum(1);
    		om.setOrderType(DATATYPE.EventsType.getStatus());
    		om.setPayStatus(Constants.OrderStatus.PAY_STAY_PAID.getStatus());
    		om.setActiveID(String.valueOf(eventsId));
    		om.setName(e.getTitle());
    		om.setImg(e.getPropagandaImg());
			mainOrderService.saveOrderMain(om);
			Orderinfo order=new Orderinfo();
			order.setOrderType(DATATYPE.EventsType.getStatus());
			order.setStatus(Constants.OrderStatus.PAY_STAY_PAID.getStatus());
			order.setActiveID(String.valueOf(eventsId));
			String date = e.getTime().substring(0,10);
			String time = e.getTime().substring(11,13);
			order.setCreate_date(date);
			order.setToday_time(time);
			order.setPhone(phone);
			order.setPhoneName(name);
			order.setOrderMainNo(om.getOrderMainNo());
			order.setOrderSubNo(GenNumberTool.getOrderSubNo(1,om.getOrderMainNo()));
			order.setOrderPayTotalAmont(e.getPrice());
			orderInfoService.saveOrderInfoService(order);
			// 生成正常订单日志
			orderComponent.genOrderLog(order.getWeuser(), order);
			om.setTotalAmount(e.getPrice());
			mainOrderService.updateOrderMain(om);
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), null);
		} catch (Exception e1) {
			return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(), Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), null);
		}
	}
    
    
    
    /**
     * 对自由教练下单
     * @param request
     * @param map
     * @param sn
     * @param ymd
     * @param endTime
     * @param userId
     * @param person
     * @return
     */
    @Transactional
    @RequestMapping(value="toOrderNotSpace")
    public String toOrderNotSpace(HttpServletRequest request, ModelMap map,String sn,String ymd,String endTime,Integer userId,Integer person){
    	logger.info("对自由教练下单.....");	long s_long = System.currentTimeMillis();		//开始计时
    	OrderMain om=new OrderMain();
    	//拆分教练预约时间ID
    	String[] st=sn.split(",");
    	String[] date=ymd.split(",");
    	List<Integer> stp=new ArrayList<Integer>();
    	for(String n:st){
    		stp	.add(Integer.valueOf(n));
    	}
    	
    	//该字段用于区分是否周期预订
    	boolean flag= Boolean.valueOf(request.getParameter("cycle"));
    	
    	Date initialDate=null;
    	
    	List<Coach_set_time> sList=new ArrayList<Coach_set_time>();
		List<DateAssist> list=new ArrayList<DateAssist>();
		Integer coachId=0;
    	for(int i=0;i<stp.size();i++){
			int space_id = 0;			//临时变量  场地id				需要把最早打球时间的场地放到主订单
    		Coach_set_time cst=coach_set_timeService.getCoach_set_timeById(stp.get(i));
    		cst.setDate(date[i]);
    		coachId=cst.getCoach_id().getId();
    		sList.add(cst);
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
			try {
				
				
				Date d=format.parse(date[i]+" "+cst.getTime());
				DateAssist da=new DateAssist();
				
				//edit by nantian 2015-12-28	计算出最早的时间
				if(initialDate==null){
					initialDate=d;
				}
				if(d.before(initialDate)){
					initialDate=d;
				}
				//E
				
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(d);
				da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
				da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
				if(calendar.get(Calendar.DATE)<10){
					da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
				}else{ 
					da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
				}
				list.add(da);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			om.setInitialDate(format.format(initialDate));//设置最早打球时间
			om.setSpaceId(space_id);					//把最早打球时间的场地放到主订单
    	}
    	Coach coach = coachService.getcoachById(coachId);
    	
    	
    	
    	//去除重复值
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
		      if  (list.get(j).getMonth().equals(list.get(i).getMonth()) && list.get(j).getDay().equals(list.get(i).getDay()))   { 
		        list.remove(j); 
		      } 
		    } 
		}
		
		Weuser user=weuserService.getUserById(userId);
		
		
		
		//对主订单信息进行操作
		om.setOrderMainNo(GenNumberTool.creatOrderNo("A"));
		om.setWeuser(user);
		
		//截取前台传递的字符串
		om.setProdTotalNum(st.length);
    	try {
			mainOrderService.saveOrderMain(om);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	double count=0;
    	List<Coach> coachList=new ArrayList<Coach>();
    	//生成子订单的信息
    	int o=1;
    	Integer hourCount=0;
    	Coach_teach_person ctp=coach_teach_personService.getCoach_teach_personById(person);
		for(Coach_set_time i:sList){
			if(coachreserveService.getCoachReserve(i.getDate(), coachId, i.getTime())!=null){
				return "phone/user/coachShoppingCart";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Orderinfo order=new Orderinfo();
			order.setActiveID(String.valueOf(coachId));
			order.setPlaygroundmanager_id(coach.getPlaygroundmanager_id());
			order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
			
			order.setToday_time(String.valueOf(i.getTime()));
			order.setWeuser(user);
			order.setPhone(user.getUphone());
			order.setOrderMainNo(om.getOrderMainNo());
			order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
			order.setOrderPayTotalAmont(Double.valueOf(ctp.getPrice()));
			order.setDetails("1对"+ctp.getPeople_num());
			
			try {
				order.setCreate_date(sdf.format(sdf.parse(i.getDate())));
	    		om.setOrderType(Constants.DATATYPE.CoachType.getStatus());
	    		om.setActiveID(String.valueOf(i.getCoach_id().getId()));
	    		
	    		//在前端显示的时候动态修改教练价格
	    		coach.setPrice(ctp.getPrice());
	    		coachList.add(coach);
	    		om.setName(coach.getName());
	    		om.setImg(coach.getHead_portrait());
	    		CoachReserve cs=new CoachReserve();
	    		
	    		cs.setCoachId(i.getCoach_id().getId());
	    		cs.setDate(i.getDate());	
	    		cs.setTimepoint(i.getTime());
	    		cs.setOrderSubNo(order.getOrderSubNo());
	    		coachreserveService.saveCoachReserve(cs);
		    	
				orderInfoService.saveOrderInfoService(order);
				hourCount++;
				// 生成正常订单日志
				orderComponent.genOrderLog(order.getWeuser(), order);
			} catch (Exception e) {
				e.printStackTrace();
			}
			o++;
			count+=ctp.getPrice();
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
		map.put("coachPrice", ctp.getPrice());
		
		int weekcount=1;
		//对周期预订做判断，当为真时实现周期预订的插入
		if(flag && CommonUtil.NotEmpty(endTime)){
			boolean f=true;
			int interval=7;
			while(f){
				for(Coach_set_time i:sList){
					  
					  DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); 
					  
					  
					  Date end;
					  Orderinfo order=null;
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
						    order=new Orderinfo();
							order.setCreate_date(dateformat.format(calendar.getTime()));
							order.setToday_time(String.valueOf(i.getTime()));
							order.setWeuser(user);
							order.setPhone(user.getUphone());
							order.setOrderMainNo(om.getOrderMainNo());
							order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
							order.setOrderPayTotalAmont(Double.valueOf(ctp.getPrice()));
							order.setActiveID(String.valueOf(coachId));
							order.setPlaygroundmanager_id(coach.getPlaygroundmanager_id());
							order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
							try {
								//加上教练的价格
								if(person!=null){
									count+=ctp.getPrice();
								}
								orderInfoService.saveOrderInfoService(order);
								//space_time_priceService.updatespace_time_price(s);
								// 生成正常订单日志
								orderComponent.genOrderLog(order.getWeuser(), order);
							} catch (Exception e) {
								e.printStackTrace();
							}
					   }
					   if(dateformat.format(calendar.getTime()).equals(dateformat.format(end))){
						   
						    order=new Orderinfo();
							order.setCreate_date(dateformat.format(calendar.getTime()));
							order.setToday_time(String.valueOf(i.getTime()));
							order.setWeuser(user);
							order.setPhone(user.getUphone());
							order.setOrderMainNo(om.getOrderMainNo());
							order.setOrderSubNo(GenNumberTool.getOrderSubNo(o,om.getOrderMainNo()));
							order.setOrderPayTotalAmont(Double.valueOf(ctp.getPrice()));
							order.setActiveID(String.valueOf(coachId));
							order.setPlaygroundmanager_id(coach.getPlaygroundmanager_id());
							order.setOrderType(Constants.DATATYPE.CoachType.getStatus());
							try {
								//加上教练的价格
								if(person!=null){
									count+=ctp.getPrice();
								}
								orderInfoService.saveOrderInfoService(order);
								//space_time_priceService.updatespace_time_price(s);
								// 生成正常订单日志
								orderComponent.genOrderLog(order.getWeuser(), order);
							} catch (Exception e) {
								e.printStackTrace();
							}
					   }
					    CoachReserve cs=new CoachReserve();
			    		o++;
			    		cs.setCoachId(i.getCoach_id().getId());
			    		cs.setDate(dateformat.format(calendar.getTime()));	
			    		cs.setTimepoint(i.getTime());
			    		cs.setOrderSubNo(order.getOrderSubNo());
			    		coachreserveService.saveCoachReserve(cs);
					  }catch(ParseException e){
						  e.printStackTrace();
						  f=false;
					  }catch(Exception e){
						  f=false;
						  e.printStackTrace();
					  }
				}
				
				interval+=7;
				if(f){
					weekcount+=1;
				}
				
			}
	    	
		}
		om.setPerson(person);
		om.setTotalAmount(count);
		om.setWeekCount(weekcount);
		try {
			mainOrderService.updateOrderMain(om);
			
//			//增加预订次数
//			User_count uc = user_countService.getUser_countByUserId(coach.getUserid().getId());
//			uc.setReservecount(uc.getReservecount()+1);
//			user_countService.updateUser_count(uc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		map.put("coach", coach);
		map.put("user", user);
		try {
			map.put("red", redBagRecordService.getRed_bag_recordByUserId(user.getId(), 1).size());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("red", 0);
		}
		
		map.put("hourCount", hourCount*weekcount);
		map.put("tList", list); 
		map.put("sList", sList);
		map.put("weekcount",weekcount);
		map.put("mainId", om.getId());
		map.put("userId", user.getId());
    	map.put("orderMainNo", om.getOrderMainNo());
    	map.put("price", count);
    	
    	//设置定时器，当用户30分钟没有支付就触发
		Timer timer=new Timer();
		
		CancelOrderTask cancelTask=new CancelOrderTask(om.getOrderMainNo(), timer,mainOrderService);
		
		String DEFAULT_CANCLE_ORDER_TIME = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_CANCLE_ORDER_TIME,"15");//取系统配置值
		
		//1000*60*30
		timer.scheduleAtFixedRate(cancelTask, 1000*60*Integer.parseInt(DEFAULT_CANCLE_ORDER_TIME),1000*60*5);
    	
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long));
		logger.info("取消订单时间——————"+DEFAULT_CANCLE_ORDER_TIME+"分钟,订单编号为:"+om.getOrderMainNo());
    	return "phone/user/coachShoppingCart";
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
	
	
	/**
	 * 支付后处理
	 * @param payOrderNo
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "spaceOrder")
	@Transactional
	public synchronized String spaceOrder(String payOrderNo ,HttpServletRequest request, ModelMap map){
		PayInfo_thirdPay_orderNo o = orderComponent.getPayInfo_thirdPay_orderNo(payOrderNo);
		logger.info("页面跳转支付完成后的处理 payOrderNo开始。。。。。。" + payOrderNo);
		PlaygroundManager k = getPlaygroundManager(request);
		map.put("playgroundmanager", k);
		if(o!=null){
			OrderMain om = mainOrderService.getOrderMainByNO(o.getOrderMainNo());
			
			if(om.getOrderType()==Constants.OrderStatus.DELETE.getStatus()){
				map.put("payType", 2);  //1.表示支付宝 2.余额 3.微信
				map.put("flag", 3); //1.成功 2.失败 3.取消
				return "phone/user/pay";
			}
			
			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(om.getOrderMainNo());
			if(t==null){
				if(o.getPay_type() == Constants.PayType.BALANCE.getStatus()){  //余额支付
					if(weuserService.getUserById(om.getWeuser().getId()).getAmount() < om.getTotalAmount()){
						System.err.println("余额不足！"+ payOrderNo);
						map.put("payType", 2);//1.表示支付宝 2.余额 3.微信
						map.put("flag", 2);//1.成功 2.失败 3.取消
						return "phone/user/pay";
					}
				}
				if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
					mainOrderService.pay(om.getId(), om.getWeuser().getId(),0,Integer.valueOf(om.getActiveID()),o.getPay_type());
				}else if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
					mainOrderService.pay(om.getId(), om.getWeuser().getId(),Integer.valueOf(om.getActiveID()),0,o.getPay_type());
				}
			}else{
				logger.info("已处理订单！");
			}
		}else{
			logger.info("支付订单号为空或者不正确！");
			map.put("payType", 2);  //1.表示支付宝 2.余额 3.微信
			map.put("flag", 2); //表示失败
			return "phone/user/pay";
		}
		map.put("payType", 2);
		map.put("flag", 1); //表示成功
		logger.info("页面跳转支付完成后的处理 payOrderNo结束。。。。。。" + payOrderNo);
		return "phone/user/pay";
		
	}
	
//	/**
//	 * 充值完成跳转（同步）
//	 * 现订单支付后也完成跳转(同步) edit by lxc 2015-12-28
//	 * @param request
//	 * @param map
//	 * @param resultStatus			支付状态,9000为成功,其他为失败
//	 * @param out_trade_no			支付订单号
//	 * @param price					支付价格
//	 * @param notifyurl				异步调用URL,根据异步调用URL判断当前的操作是 充值 还是支付
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "rechargeCompletion")
//	public String rechargeCompletion(HttpServletRequest request, ModelMap map,String resultStatus,String out_trade_no,String price,String notifyurl) throws Exception{
//		System.err.println("同步调用----支付后访问....支付订单号:"+out_trade_no+"  ,price:"+price+"   ,notifyurl:"+notifyurl);
//		CommonUtil.printHTTP(request);
//		PayInfo_thirdPay_orderNo o = orderComponent.getPayInfo_thirdPay_orderNo(out_trade_no);
//		int payType =-1;
//		if(o!=null){
//			payType = o.getPay_type();
//		}
//		if(Settings.NOTIFYURL_APLIY_PAY_ORDER.equals(notifyurl)){
//			PayController pc = new PayController();
//			pc.paid_handle(out_trade_no, price, o.getPay_type());
//			if(payType == 0){
//				map.put("payType", 2);   //1.表示支付宝 2.余额 3.微信
//			}
//			if(payType == 1){
//				map.put("payType", 3);   //1.表示支付宝 2.余额 3.微信
//			}
//			if(payType == 2){
//				map.put("payType", 1);   //1.表示支付宝 2.余额 3.微信
//			}
//			map.put("flag", 1); 	 //1.成功 2.失败 3.取消
//			return "phone/user/pay";
//		}else if(Settings.NOTIFYURL_WECHAT_PAY_ORDER.equals(notifyurl)){
//			PayController pc = new PayController();
//			pc.paid_handle(out_trade_no, price, o.getPay_type());
//			if(payType == 0){
//				map.put("payType", 2);   //1.表示支付宝 2.余额 3.微信
//			}
//			if(payType == 1){
//				map.put("payType", 3);   //1.表示支付宝 2.余额 3.微信
//			}
//			if(payType == 2){
//				map.put("payType", 1);   //1.表示支付宝 2.余额 3.微信
//			}
//			map.put("flag", 1); 	 //1.成功 2.失败 3.取消
//			return "phone/user/pay";
//		}else{
//			Trade_recode t = trade_recodeService.getTrade_recodeByOrderMian(out_trade_no);
//			payType = t.getPay_type();
//			System.err.println("支付方式为:"+payType+"-----1.支付宝 , 2.余额 ,3.微信");
//			if("9000".equals(resultStatus)){
//				if(t.getFlag()==Constants.OPERATE_FAIL0){ //状态为失败时再进行用户充值的操作
//					Weuser w  = weuserService.getUserById(t.getWeuser().getId());
//					if(t.getRecharId()>0){ //选择充值活动时
//						RechargeEvents rechargeEvents = rechargeEventsService.getRechargeEventsById(t.getRecharId());
//						w.setAmount(w.getAmount()+t.getMny_amount()+rechargeEvents.getGet_money());
//					}else{
//						w.setAmount(w.getAmount()+t.getMny_amount());
//					}
//					weuserService.mergeAndUpdateTime(w);
//					t.setFlag(Constants.OPERATE_SUCCESS);
//					trade_recodeService.updateTrade_recode(t);
//					map.put("result", "success");	
//				}else{
//					map.put("result", "fail");		
//				}
//				
//				if(payType == 0){
//					map.put("payType", 2);   //1.表示支付宝 2.余额 3.微信
//				}
//				if(payType == 1){
//					map.put("payType", 3);   //1.表示支付宝 2.余额 3.微信
//				}
//				if(payType == 2){
//					map.put("payType", 1);   //1.表示支付宝 2.余额 3.微信
//				}
//				map.put("flag", 1); 	 //1.成功 2.失败 3.取消
//				return "phone/user/pay";
//			}else{
//				if(payType == 0){
//					map.put("payType", 2);   //1.表示支付宝 2.余额 3.微信
//				}
//				if(payType == 1){
//					map.put("payType", 3);   //1.表示支付宝 2.余额 3.微信
//				}
//				if(payType == 2){
//					map.put("payType", 1);   //1.表示支付宝 2.余额 3.微信
//				}
//				map.put("flag", 2);		//1.成功 2.失败 3.取消
//				return "phone/user/pay";
//			}
//		}
//	}
	
	/**
	 * 取消子订单
	 * @param request
	 * @param map
	 * @param orderInfoId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "cancelSpaceOrder")
	public String cancelOrder(HttpServletRequest request, ModelMap map,Integer orderInfoId,Integer userId){
		orderInfoService.cancelOrder(orderInfoId, userId);
		return null;
	}
	
	@RequestMapping(value = "test1")
	public String test1(HttpServletRequest request, ModelMap map,Integer orderMainId,Integer userId){
		new OrderMainServiceImpl().cancelOrder(orderMainId, userId);
		return null;
	}
	
	/**
	 * 
	 * @param coachId		驻场教练ID
	 * @param orderMainNo	主订单编号
	 * @param order			子订单
	 * @param coach			教练对象
	 * @param s				场地时间价格对象
	 * @param user			用户对象
	 * @param coachList		教练集合对象
	 * @param price			教练带人对象
	 * @param o				子订单编号
	 * @return				返回值为价格
	 * @throws Exception
	 */
	public double coachOrderInfo(Integer coachId,String orderMainNo ,Orderinfo order,Coach coach,Space_time_price s,Weuser user,List<Coach> coachList,Coach_teach_person price,Integer o)throws Exception{
		double count=0;
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
			
			corder.setActiveID(String.valueOf(coachId));
			corder.setOrderType(Constants.DATATYPE.CoachType.getStatus());
			corder.setSpaceId(s.getSpace_id().getId());
			corder.setCreate_date(order.getCreate_date());
			corder.setToday_time(String.valueOf(s.getTime()));
			corder.setDetails("1对"+price.getPeople_num());
			corder.setWeuser(user);
			corder.setPhone(user.getUphone());
			corder.setOrderMainNo(orderMainNo);
			corder.setOrderSubNo(GenNumberTool.getOrderSubNo(o,orderMainNo));
			corder.setOrderPayTotalAmont(Double.valueOf(price.getPrice()));
			count+=price.getPrice();
			corder.setPlaygroundmanager_id(coach.getPlaygroundmanager_id());
			orderInfoService.saveOrderInfoService(corder);
    		
    		cs.setCoachId(coachId);
    		cs.setDate(order.getCreate_date());
    		cs.setTimepoint(s.getTime());
    		cs.setOrderSubNo(corder.getOrderSubNo());
    		coachreserveService.saveCoachReserve(cs);
    		
    		//增加预订次数
//    		User_count uc = user_countService.getUser_countByUserId(coach.getUserid().getId());
//    		uc.setReservecount(uc.getReservecount()+1);
//    		user_countService.updateUser_count(uc);
    		
    		
    		coachList.add(coach);
    		//o++;//在方法里,o++后无返回(传进来是什么,就传什么出去，所以注释)		edit by lxc	2015-12-31
    	}
		
		return count;
	}
	
	@RequestMapping(value = "orderdetail")
	public String orderdetail(HttpServletRequest request, ModelMap map,String orderMainNo){
		logger.info("订单详情页。。。。");long s_long = System.currentTimeMillis();		//开始计时
		String check = request.getParameter("check");
		map.put("check", check);
		//获取父订单
		OrderMain om = mainOrderService.getOrderMainByNO(orderMainNo);
		map.put("red", redBagRecordService.getRed_bag_recordByUserId(om.getWeuser().getId(), 1).size());
		if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
			//订单为场馆预订的时候
			Playground playground=playgroundService.getPlaygroundById(Integer.valueOf(om.getActiveID()));
			
			//获取该父订单下的所有子订单
			List<Orderinfo> oi = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
			
			//获取时间列表
			List<DateAssist> tList=new ArrayList<DateAssist>();
			
			//获取场地时间价格
			List<Space_time_price> sList=new ArrayList<Space_time_price>();
			
			//获取驻场教练
			List<Coach> coachList=new ArrayList<Coach>();
			int count=0;
			//生成日期，用户预订在什么日期
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//用于获取7天后的日期
			Calendar c = new GregorianCalendar();
			Integer hourCount=0;
			for(Orderinfo o:oi){
				
				
				try {
					
					//根据主订单的时间生成7天后的日期
					c.setTime(format.parse(om.getCreateTime()));
					c.set(Calendar.DAY_OF_WEEK, 7);
					int days=CommonUtil.daysBetween(format.parse(o.getCreate_date()),c.getTime());
					//判断时间子订单的日期得在主订单创建日期的7天内
					if(days<7){
					
						//去场地时间价格表中找，是否在场地时间价格表中是否存在 
						if(space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time()))!=null){
							
							Date d=format.parse(o.getCreate_date());
							DateAssist da=new DateAssist();
							Calendar calendar = new GregorianCalendar();
							calendar.setTime(d);
							da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
							da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
							if(calendar.get(Calendar.DATE)<10){
								da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
							}else{ 
								da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
							}
							tList.add(da);
							
							
							//如果是场馆则是场地
							if(o.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
								sList.add(space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time())));
								hourCount++;
								count++;
							}else{
								coachList.add(coachService.getcoachById(Integer.valueOf(o.getActiveID())));
							}
							
							
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			
			//去除重复值
			for  ( int  i  =   0 ; i  <  tList.size()  -   1 ; i ++ )   { 
			    for  ( int  j  =  tList.size()  -   1 ; j  >  i; j -- )   { 
			      if  (tList.get(j).getMonth().equals(tList.get(i).getMonth()) && tList.get(j).getDay().equals(tList.get(i).getDay()))   { 
			    	  tList.remove(j); 
			      } 
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
			
			map.put("playground", playground);
			map.put("tList", tList);
			map.put("sList", sList);
			map.put("weekcount", om.getWeekCount());
			map.put("hourCount", hourCount*om.getWeekCount());
			map.put("coachList", coachList);
			map.put("price", om.getTotalAmount());
			map.put("user", om.getWeuser());
			map.put("mainId", om.getId());
			map.put("userId", om.getWeuser().getId());
			map.put("orderMainNo", orderMainNo);
			if(om.getPerson()!=0){
				map.put("coachPrice", coach_teach_personService.getCoach_teach_personById(om.getPerson()).getPrice());
			}
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return "phone/user/shoppingCart";
		}else{
			Coach coach=coachService.getcoachById(Integer.valueOf(om.getActiveID()));
//			if(coach.getCoachType()==2){
//				Playground playground=playgroundService.getPlaygroundById(coach.getPlayground_id());
//				map.put("playground", playground);
//			}
			if(coach.getCoachType()==3 || coach.getCoachType()==2){
				Playground playground=playgroundService.getPlaygroundById(coach.getPlayground_id());
				Integer hourCount=0;
				List<Orderinfo> oi = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
				List<DateAssist> tList=new ArrayList<DateAssist>();
				List<Space_time_price> sList=new ArrayList<Space_time_price>();
				List<Coach> coachList=new ArrayList<Coach>();
				coachList.add(coach);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				for(Orderinfo o:oi){
					
					//用于获取7天后的日期
					Calendar c = new GregorianCalendar();
					try {
						c.setTime(format.parse(om.getCreateTime()));
						c.set(Calendar.DAY_OF_WEEK, 7);
					
						int days=CommonUtil.daysBetween(format.parse(o.getCreate_date()),c.getTime());
						if(days<7){
							if(space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time()))!=null){
								
								Date d=format.parse(o.getCreate_date());
								DateAssist da=new DateAssist();
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(d);
								da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
								da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
								if(calendar.get(Calendar.DATE)<10){
									da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
								}else{ 
									da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
								}
								tList.add(da);
								
								
								//如果是场馆则是场地
								if(o.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
									Space_time_price sTest=space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time()));
									sTest.setPrice(o.getOrderPayTotalAmont());
									sList.add(sTest);
									playground=playgroundService.getPlaygroundById(spaceService.getSpace(o.getSpaceId()).getPlayground_id().getId());
									hourCount++;
								}
							}
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
				}
				
				//去除重复值
				for  ( int  i  =   0 ; i  <  tList.size()  -   1 ; i ++ )   { 
				    for  ( int  j  =  tList.size()  -   1 ; j  >  i; j -- )   { 
				      if  (tList.get(j).getMonth().equals(tList.get(i).getMonth()) && tList.get(j).getDay().equals(tList.get(i).getDay()))   { 
				    	  tList.remove(j); 
				      } 
				    } 
				}
				hourCount = hourCount*om.getWeekCount();
				map.put("hourCount", hourCount);
				map.put("playground", playground);
				map.put("tList", tList);
				map.put("sList", sList);
				map.put("weekcount", om.getWeekCount());
				map.put("coachList", coachList);
				map.put("price", om.getTotalAmount());
				map.put("user", om.getWeuser());
				map.put("mainId", om.getId());
				map.put("userId", om.getWeuser().getId());
				map.put("orderMainNo", orderMainNo);
				map.put("coachPrice", coach_teach_personService.getCoach_teach_personById(om.getPerson()).getPrice());
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				return "phone/user/shoppingCart";
			}else{
				Integer hourCount=0;
				//用于获取7天后的日期
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = new GregorianCalendar();
				List<Orderinfo> oi = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
				List<DateAssist> tList=new ArrayList<DateAssist>();
				List<CoachReserve> crtList=new ArrayList<CoachReserve>();
				List<Coach> coachList=new ArrayList<Coach>();
				coachList.add(coach);
				for(Orderinfo o:oi){
					try {
					c.setTime(format.parse(om.getCreateTime()));
					c.set(Calendar.DAY_OF_WEEK, 7);
				
					int days=CommonUtil.daysBetween(format.parse(o.getCreate_date()),c.getTime());
						if(days<7 && days>=0){
							
								Date d=format.parse(o.getCreate_date());
								DateAssist da=new DateAssist();
								Calendar calendar = new GregorianCalendar();
								calendar.setTime(d);
								da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
								da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
								if(calendar.get(Calendar.DATE)<10){
									da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
								}else{ 
									da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
								}
								tList.add(da);
								hourCount++;
								for(CoachReserve crt:coachreserveService.getCoachReserveBySubNo(o.getOrderSubNo())){
									
									if(crt.getCoachId()==coach.getId()){
										crtList.add(crt);
									}
								}
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				//去除重复值
				for  ( int  i  =   0 ; i  <  tList.size()  -   1 ; i ++ )   { 
				    for  ( int  j  =  tList.size()  -   1 ; j  >  i; j -- )   { 
				      if  (tList.get(j).getMonth().equals(tList.get(i).getMonth()) && tList.get(j).getDay().equals(tList.get(i).getDay()))   { 
				    	  tList.remove(j); 
				      }
				    } 
				}
				
				map.put("hourCount", hourCount*om.getWeekCount());
				map.put("coach", coach);
				map.put("tList", tList);
				map.put("weekcount", om.getWeekCount());
				map.put("orderMainNo", orderMainNo);
				map.put("crtList", crtList);
				map.put("coachList", coachList);
				map.put("price", om.getTotalAmount());
				map.put("user", om.getWeuser());
				map.put("mainId", om.getId());
				map.put("userId", om.getWeuser().getId());
				map.put("coachPrice", coach_teach_personService.getCoach_teach_personById(om.getPerson()).getPrice());
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				return "phone/user/coachShoppingCart";
			}
		}
		
	}
	
	/**
	 * 评论评分
	 * @param request
	 * @param map
	 * @param orderMainNo
	 * @return
	 */
	@RequestMapping(value = "assess")
	public String assess(HttpServletRequest request, ModelMap map,String orderMainNo){
		
		OrderMain om = mainOrderService.getOrderMainByNO(orderMainNo);
		
		if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
			
			Playground p=playgroundService.getPlaygroundById(Integer.valueOf(om.getActiveID()));
			
			map.put("playground", p);
			
			map.put("orderMainNo", orderMainNo);
			
			List<Orderinfo> orderList=orderInfoService.getOrderInfoListByOrderMainId(orderMainNo);
			
			Coach c=null;
			for(Orderinfo oi:orderList){
				if(oi.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
					c=coachService.getcoachById(Integer.valueOf(oi.getActiveID()));
					map.put("coach", c);
					break;
				}
			}
			
			return "phone/user/venuesViewAssess";
		}else if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
			
			Coach c=coachService.getcoachById(Integer.valueOf(om.getActiveID()));
			
			map.put("coach", c);
			map.put("orderMainNo", orderMainNo);
			
			//判断  ，当订单教练类型不为自由时要同时能评价  其教练所在的场馆
			if(c.getCoachType()==Constants.COACHTYPE.INNERCOACH.getStatus()){
				Playground p=playgroundService.getPlaygroundById(Integer.valueOf(c.getPlayground_id()));
				map.put("playground", p);
				
				return "phone/user/venuesViewAssess";
			}
			
			if(c.getCoachType()==Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
				Playground p=playgroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(c.getId()).getPhone()).getId());
				
				map.put("playground", p);
				
				return "phone/user/venuesViewAssess";
			}
			
			
		
			return "phone/user/coachViewAssess";
			
		}
		
		return "";
	}
	
	/**
	 * 检查是否被预定
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkIs_reserve")
	@ResponseBody
	public Object checkIs_reserve(HttpServletRequest request){
		try {
			Integer playground;
			try{
				playground=Integer.valueOf(request.getParameter("playgroundId"));//场馆ID
			}catch(Exception e){
				playground=0;
			}
			
			Integer coachId=0;
			try{
				coachId=Integer.valueOf(request.getParameter("coachId"));	//教练id
			}catch(Exception e){
				coachId=0;
			}
			
			String spacePriceId=request.getParameter("spacePriceId");	//场地选择的id，拼成字符串
			
			if(playground!=0){
				String[] spId=spacePriceId.split(",");
				
				for(String s:spId){
					if(space_time_priceService.findById(Integer.valueOf(s)).getIs_reserve() == 1 || space_time_priceService.findById(Integer.valueOf(s)).getIs_reserve() == 2){
						return new BusinessResponse(
								Constants.OperationResult.SYSTEM_ERROR.getStatus(), "成功", "失败");
					}
				}
			}
			
			if(coachId!=0){
				if(coachService.getcoachById(coachId).getCoachType()==1){
					String ymd=request.getParameter("ymd");
					String sn=request.getParameter("sn");//自由教练详情页  ,时间ID，拼成字符串
					String[] spId=sn.split(",");
					String[] date=ymd.split(",");
					for(int i=0;i<spId.length;i++){
						Coach_set_time cst = coach_set_timeService.getCoach_set_timeById(Integer.valueOf(spId[i]));
						
						if(coachreserveService.getCoachReserve(date[i], coachId, cst.getTime())!=null){
							return new BusinessResponse(
									Constants.OperationResult.SYSTEM_ERROR.getStatus(), "成功", "失败");
						}
					}
				}else{
					String[] spId=spacePriceId.split(",");
					
					for(String s:spId){
						if(space_time_priceService.findById(Integer.valueOf(s)).getIs_reserve() == 1 || space_time_priceService.findById(Integer.valueOf(s)).getIs_reserve() == 2){
							return new BusinessResponse(
									Constants.OperationResult.SYSTEM_ERROR.getStatus(), "成功", "失败");
						}
					}
				}
			}
			
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "成功", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "成功", "失败");
		}
		
	}
	
	@RequestMapping(value = "userCancelOrder")
	@ResponseBody
	public Object userCancelOrder(HttpServletRequest request,String orderMainNo){
		OrderMain om = mainOrderService.getOrderMainByNO(orderMainNo);
		if(om!=null && om.getPayStatus()!=OrderStatus.PAY_REFUND_ALL.getStatus()){
			if(mainOrderService.cancelOrderUserMoeny(orderMainNo)){
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
			}else{
				return new BusinessResponse(
						Constants.OperationResult.SYSTEM_ERROR.getStatus(), "失败", "失败");
			}
		}else{
			return new BusinessResponse(
					Constants.OperationResult.STATUS_EDITED_NO_SUBMIT.getStatus(), OperationResult.STATUS_EDITED_NO_SUBMIT.getMsg(), "失败");
		}
		
	}
	
}
