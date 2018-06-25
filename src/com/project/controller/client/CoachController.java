package com.project.controller.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Constants.COACHTYPE;
import com.project.common.Constants.OperationResult;
import com.project.common.FinalList;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Apply;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.CoachReserve;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Coaching_experience;
import com.project.pojo.Comment;
import com.project.pojo.Course;
import com.project.pojo.DateAssist;
import com.project.pojo.Friendship;
import com.project.pojo.Invite;
import com.project.pojo.LoveCollection;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.Qualification_certificate;
import com.project.pojo.Region;
import com.project.pojo.Space_time_price;
import com.project.pojo.StationMessage;
import com.project.pojo.Subsidies;
import com.project.pojo.User_count;
import com.project.pojo.User_level;
import com.project.pojo.VerifyCode;
import com.project.pojo.Visit;
import com.project.pojo.Weuser;
import com.project.pojo.vo.Info;
import com.project.pojo.vo.OrderVo;
import com.project.pojo.vo.SubsidiesFrom;
import com.project.service.IApplyService;
import com.project.service.ICertificateService;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICoach_teach_personService;
import com.project.service.ICoaching_experienceService;
import com.project.service.ICommentService;
import com.project.service.ICourseService;
import com.project.service.IDictionariesService;
import com.project.service.IFriendshipService;
import com.project.service.ILoveCollectionService;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IRechargeEventsService;
import com.project.service.IRedBagRecordService;
import com.project.service.IRegionService;
import com.project.service.ISpace_time_priceService;
import com.project.service.IStationMessageService;
import com.project.service.ISubsidiesFromService;
import com.project.service.ISubsidiesService;
import com.project.service.ISubsidiesSettlementService;
import com.project.service.ISystemConfigService;
import com.project.service.ITrade_recodeService;
import com.project.service.IUser_countService;
import com.project.service.IUser_levelService;
import com.project.service.IVisitService;
import com.project.service.IWeuserService;
import com.project.service.InviteService;
import com.project.util.CommonUtil;
import com.project.util.DateUtil;
import com.project.util.ImageDispose;
import com.project.util.LatitudeUtil;
import com.project.util.SecurityUtil;

@Controller("clientcoachController")
@RequestMapping("/")
public class CoachController extends BaseController{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(UserController.class);
	@Resource
	private IStationMessageService stationMessageService;
	@Resource
	private IRegionService regionService;
	@Resource
	private IDictionariesService dictionariesService;
	@Resource
	private ICoachService coachService; 
	@Resource
	private IUser_levelService user_levelService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IUser_countService user_countService;
	@Resource
	private ICoach_teach_personService coach_teach_personService;
	@Resource
	private ICommentService commentService;
	@Resource
	private IWeuserService weuserService;
	@Resource
	private ICourseService courseService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private ICoachReserveService coachReserveService;
	@Resource
	private IOrderMainService orderMainService;
	@Resource
	private ITrade_recodeService trade_recodeService;
	@Resource
	private ISubsidiesSettlementService subsidiesSettlementService;
	@Resource
	private ILoveCollectionService loveCollectionService; 
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private IVisitService visitService;
	@Resource
	private ICertificateService certificateService;
	@Resource
	private ICoaching_experienceService coaching_experienceService; 
	@Resource
	private IRechargeEventsService rechargeEventsService;
	@Resource
	private ISystemConfigService systemConfigService;
	@Resource
	private IFriendshipService friendshipService;
	@Resource
	private ISubsidiesService subsidiesService;
	@Resource
	private InviteService inviteService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IRedBagRecordService redBagRecordService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IApplyService applyService;
	@Resource
	private ISubsidiesFromService subsidiesFromService;
	
	/**
	 * 教练数据
	 * @param request
	 * @param cityName
	 * @return
	 */
	@RequestMapping("coachData")
	@ResponseBody
	public Object coachData(HttpServletRequest request,String cityName,
							String areaid,String screening,String services,
							String sort,@RequestParam(defaultValue="1")String pageNumber,
							String lat,String lng,String coachName){
		CommonUtil.printHTTP(request);		//打印请求数据
		logger.info("教练数据....");
		long s_long = System.currentTimeMillis();		//开始计时
		int city_show_id = 0;
		if(CommonUtil.NotEmpty(cityName)){
			String cityNameCl = cityName + "市";
			Region region = regionService.getRegionByCityName(cityNameCl);
			if(CommonUtil.NotEmptyObject(region)){
				city_show_id = region.getRegion_id();
			}else{
				city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
			}
		}else{
			city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("cityid", city_show_id);
		paraMap.put("areaid", areaid);
		paraMap.put("screening", screening);
		paraMap.put("services", services);
		paraMap.put("sort", sort);
		paraMap.put("lat", lat);
		paraMap.put("lng", lng);
		paraMap.put("coachName", coachName);
		PageFinder<Coach> coachList = coachService.getPageCoachListByMap(paraMap, Integer.valueOf(pageNumber),Constants.FOREGROUND_PAGESIZE);
		List<Coach> dataList = new ArrayList<Coach>();
		if(coachList != null && coachList.getDataList()!=null && coachList.getDataList().size()>0){
			for (Coach co : coachList.getDataList()) {
				//距离
				if(CommonUtil.NotEmpty(lat) && CommonUtil.NotEmpty(lng)){
					double distance = LatitudeUtil.Distance(Double.valueOf(lng.toString()),Double.valueOf(lat.toString()), 
							co.getCoordinateslongitude(), co.getCoordinateslatitude());
					String s1 = String.valueOf(distance);
					String s2 = s1.substring(0,s1.indexOf("."));
					int i = Integer.valueOf(s2); 
					co.setDistanceMeters(i);
				}else{
					co.setDistanceMeters(0);
				}

				//课程
				Course course = courseService.getCourseByUserId(co.getUserid().getId());
				if(course!=null){
					co.setCourseTitle(course.getTitle());
				}else{
					co.setCourseTitle("");
				}
				
				//用作于判断，当自己教练没有在前台设置价格时不添加到数据显示
				if(co.getCoachType()==1 && coach_teach_personService.getCoach_teach_personByCidAndper(co.getId())!=null){
					dataList.add(co);
				}else if(co.getCoachType()==2){
					dataList.add(co);
				}else if(co.getCoachType()==3 && co.getReserve_me()==1){
					dataList.add(co);
				}
				
			}
		}
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(dataList!=null  && dataList.size()>0 ){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", dataList);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	/**
	 * 按城市名称的全部教练数据
	 * @param cityName
	 * @return
	 */
	@RequestMapping("coachCoordinateList")
	@ResponseBody
	public Object coachList(HttpServletRequest request,String cityName){
		logger.info("按城市名称的全部教练数据....");
		CommonUtil.printHTTP(request);		//打印请求数据
		long s_long = System.currentTimeMillis();		//开始计时
		int city_show_id = 0;
		if(CommonUtil.NotEmpty(cityName)){
			String cityNameCl = cityName + "市";
			Region region = regionService.getRegionByCityName(cityNameCl);
			if(CommonUtil.NotEmptyObject(region)){
				city_show_id = region.getRegion_id();
			}else{
				city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
			}
		}else{
			city_show_id = regionService.getRegionByCityName("深圳市").getRegion_id();
		}
		Coach o = new Coach();
		o.setCoachType(-1);
		o.setNow_live_city(city_show_id);
		o.setVerified(1);
		List<Coach> list = coachService.getCoachByObj(o);
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(list!=null&&list.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
		
	}
	/**
	 * 教练搜索  ：全城,服务,排序
	 * @param request
	 * @param cityName
	 * @return
	 */
	@RequestMapping("coachSearchData")
	@ResponseBody
	public Object coach_search(HttpServletRequest request,String cityName){
		logger.info("教练搜索  ：全城,服务,排序....");
		CommonUtil.printHTTP(request);		//打印请求数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Area", FinalList.getArea(cityName, regionService));
		map.put("Sort", FinalList.getCoachSort());
		map.put("Services", FinalList.getCourseServices(dictionariesService));
		if(map.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	/**
	 * 网球级别
	 * @param request
	 * @return
	 */
	@RequestMapping("tennisLevel")
	@ResponseBody
	public Object tennisLevel(HttpServletRequest request){
		List<User_level> list =  user_levelService.getUserLevleList();
		if(list.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	/**
	 * 进入教练详情页
	 * @param map
	 * @param request
	 * @param coachId
	 * @param coachType
	 * @param userId
	 * @return
	 */
	@RequestMapping("coachdetail")
	public String coachdetail(ModelMap map, HttpServletRequest request,@RequestParam(value="coachId", required = false)Integer coachId,Integer coachType,Integer userId){
		logger.info("教练详情....");
		
		//s lzy add
		Coach o = coachService.getcoachById(coachId);
		List<Coaching_experience> list1 = coaching_experienceService.getCoaching_experienceListByCoachId(coachId);
		map.put("o", o);
		map.put("clist", list1);
		//e
		
		long s_long = System.currentTimeMillis();		//开始计时
		Coach c=coachService.getcoachByIdCacheObject(coachId,coachType);
		Playground p=null;
		if(c.getPlayground_id()!=0){
			p=playgroundService.getPlaygroundById(c.getPlayground_id());
		}
		
		if(c.getCoachType()==Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
			PlaygroundManager pgm = playgroundManagerService.getPGM(c.getPhone());
			List<Playground> pList=playgroundService.getPlaygroundListByPlaygroundManagerId(pgm.getId());
			map.put("pList", pList);
		}
		
		User_count uc=user_countService.getUser_countByUserId(c.getUserid().getId());
		List<Coach_teach_person> ctpList=coach_teach_personService.getCoach_teach_personByCoachId(c.getId());
		
		//获取到7天的日期表格
		List<DateAssist> list=new ArrayList<DateAssist>();
		for(int m=0;m<7;m++){
			DateAssist da=new DateAssist();
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
			if((calendar.get(Calendar.DAY_OF_WEEK)-1)==0){
				da.setWeek("星期天");
			}
			da.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
			da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
			if(calendar.get(Calendar.DATE)<10){
				da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
			}else{
				da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
			}
			list.add(da);
		}
		List<Comment> comment=commentService.getComment(coachId, Constants.DATATYPE.CoachType.getStatus());
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		int score[]=new int[5];
		if(!"".equals(c.getEvaluate_score())){
			double sc=Double.valueOf(c.getEvaluate_score());
			int scr=(int)sc;
			for(int i=0;i<score.length;i++){
				if(i<scr){
					score[i]=1;
				}
			}
		}
		
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  
		Calendar ca = new GregorianCalendar();
		
		//两个月的时间
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, 2);
		String two=f.format(ca.getTime());
		
		//三个月的时间
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, 3);
		String three=f.format(ca.getTime());
		
		//六个月的时间
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, 6);
		String six=f.format(ca.getTime());
		
		//十二个月的时间
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, 12);
		String twelve=f.format(ca.getTime());
		
		Map<String,String> cyMap=new HashMap<String,String>();
		
		//如果前端用户是教练则不显示预订
		if(userId!=null){
			Weuser user=weuserService.getUserById(userId);
			
			//避免用户找不到时报错
			if(user!=null){
				Coach existCoach = coachService.getcoachByphone(user.getUphone());
				
				
				if(existCoach!=null){
					if(existCoach.getId()==coachId){
						map.put("existCoach", "exist");
					}
					
				}
			}
			
		}
		
		
//		if(c.getCoachType()==Constants.COACHTYPE.FREECOACH.getStatus()){
//			try {
//				c.setAge(DateUtil.diffDate(new Date(),f.parse(c.getUserid().getBirthday())));
//			} catch (ParseException e) {
//				e.printStackTrace();
//				c.setAge(0);
//			}
//		}
		
		cyMap.put("近2个月", two);
		cyMap.put("3个月", three);
		cyMap.put("6个月", six);
		cyMap.put("1年", twelve);
		
		map.put("cyMap", cyMap);
		map.put("score", score);
		map.put("stratTime", format.format(new Date()));
		map.put("userId", userId);
		map.put("comment", comment);
		map.put("dateList", list);
		map.put("coach", c);
		map.put("coachType", coachType);
		map.put("playground", p);
		map.put("count", uc);
		map.put("ctpList", ctpList);
		map.put("type", 2);
		map.put("activeId", c.getId());
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		map.put("is_mustgroom", 1);
		
		//教练上门的区域
		try {
			map.put("area", regionService.getArea(String.valueOf(c.getNow_live_city())));		//区域
			map.put("sarea",visitService.getVisitListByCoachId(coachId, 1));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//查看是否收藏
		LoveCollection l=loveCollectionService.getLcByUserAndActive(userId, coachId, Constants.DATATYPE.CoachType.getStatus());
		if(l!=null){
			map.put("love", l);
		}
		
		try {
			//是否被关注
			Friendship fs = friendshipService.getFriendshipByUserIdAndFuserId(Integer.valueOf(userId), Integer.valueOf(c.getUserid().getId()));
			
			if(fs!=null){
				map.put("follow", "follow");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(c.getCoachType()==COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
			
			String[] service = c.getServices().split(",");
			
			for(String s:service){
				if(s!=null && !"".equals(s)){
					if(s.equals("1")){
						map.put("freeSpace", "freeSpace");
					}
					if(s.equals("2")){
						map.put("freeEquipment", "freeEquipment");
					}
				}
			}
			try {
				map.put("playgroundId", playgroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(coachId).getPhone()).getId()).getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "/phone/user/coachView";
		}else if(c.getCoachType()==COACHTYPE.INNERCOACH.getStatus()){
			
			String[] service = c.getServices().split(",");
			
			for(String s:service){
				if(s!=null && !"".equals(s)){
					if(s.equals("2")){
						map.put("freeEquipment", "freeEquipment");
					}
				}
			}
			
			Integer playgroundId=c.getPlayground_id();
			map.put("playgroundId", playgroundId);
			return "/phone/user/inThecoachView";
		}else{
			String[] service = c.getServices().split(",");
			
			for(String s:service){
				if(s!=null && !"".equals(s)){
					if(s.equals("2")){
						map.put("freeEquipment", "freeEquipment");
					}
				}
			}
			
			try {
				List<Playground> pList = new ArrayList<Playground>();
				for(Visit v : visitService.getVisitListByCoachId(c.getId(), 2)){
					pList.add(playgroundService.getPlaygroundById(v.getZhe_id()));
				}
				
				map.put("pList", pList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "/phone/user/incoachView";
		}
		
	}
	
	/**
	 * 教练端跳教练详情
	 * @param map
	 * @param request
	 * @param coachId
	 * @param coachType
	 * @param userId
	 * @return
	 */
	@RequestMapping("coachenddetail")
	public String coachdeendtail(ModelMap map, HttpServletRequest request,@RequestParam(value="coachId", required = false)Integer coachId,Integer coachType,Integer userId){
		logger.info("教练端跳教练详情....");
		long s_long = System.currentTimeMillis();		//开始计时
		//s lzy add
			Coach o = coachService.getcoachById(coachId);
			List<Coaching_experience> list1 = coaching_experienceService.getCoaching_experienceListByCoachId(coachId);
			map.put("o", o);
			map.put("clist", list1);
			//e
			
			Coach c=coachService.getcoachByIdCacheObject(coachId,coachType);
			Playground p=null;
			if(c.getPlayground_id()!=0){
				p=playgroundService.getPlaygroundById(c.getPlayground_id());
			}
			
			if(c.getCoachType()==Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
				PlaygroundManager pgm = playgroundManagerService.getPGM(c.getPhone());
				List<Playground> pList=playgroundService.getPlaygroundListByPlaygroundManagerId(pgm.getId());
				map.put("pList", pList);
			}
			
			User_count uc=user_countService.getUser_countByUserId(c.getUserid().getId());
			List<Coach_teach_person> ctpList=coach_teach_personService.getCoach_teach_personByCoachId(c.getId());
			List<Comment> comment=commentService.getComment(coachId, Constants.DATATYPE.CoachType.getStatus());
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			int score[]=new int[5];
			if(!"".equals(c.getEvaluate_score())){
				double sc=Double.valueOf(c.getEvaluate_score());
				int scr=(int)sc;
				for(int i=0;i<score.length;i++){
					if(i<scr){
						score[i]=1;
					}
				}
			}
			
			
			
			
			
			
			

			map.put("score", score);
			map.put("stratTime", format.format(new Date()));
			map.put("userId", userId);
			map.put("comment", comment);
			map.put("coach", c);
			map.put("coachType", coachType);
			map.put("playground", p);
			map.put("count", uc);
			map.put("ctpList", ctpList);
			map.put("type", 2);
			map.put("activeId", c.getId());
			map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
			map.put("is_mustgroom", 1);
			
			//教练上门的区域
			try {
				map.put("area", regionService.getArea(String.valueOf(c.getNow_live_city())));		//区域
				map.put("sarea",visitService.getVisitListByCoachId(coachId, 1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//查看是否收藏
			LoveCollection l=loveCollectionService.getLcByUserAndActive(userId, coachId, Constants.DATATYPE.CoachType.getStatus());
			if(l!=null){
				map.put("love", l);
			}
			
			try {
				//是否被关注
				Friendship fs = friendshipService.getFriendshipByUserIdAndFuserId(Integer.valueOf(userId), Integer.valueOf(c.getUserid().getId()));
				
				if(fs!=null){
					map.put("follow", "follow");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			if(c.getCoachType()==COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
			
			String[] service = c.getServices().split(",");
			
			for(String s:service){
				if(s!=null && !"".equals(s)){
					if(s.equals("1")){
						map.put("freeSpace", "freeSpace");
					}
					if(s.equals("2")){
						map.put("freeEquipment", "freeEquipment");
					}
				}
			}
			
		}else if(c.getCoachType()==COACHTYPE.INNERCOACH.getStatus()){
			
			String[] service = c.getServices().split(",");
			
			for(String s:service){
				if(s!=null && !"".equals(s)){
					if(s.equals("2")){
						map.put("freeEquipment", "freeEquipment");
					}
				}
			}
			
			Integer playgroundId=c.getPlayground_id();
			map.put("playgroundId", playgroundId);
		}else{
			String[] service = c.getServices().split(",");
			
			for(String s:service){
				if(s!=null && !"".equals(s)){
					if(s.equals("2")){
						map.put("freeEquipment", "freeEquipment");
					}
				}
			}
			
			try {
				List<Playground> pList = new ArrayList<Playground>();
				for(Visit v : visitService.getVisitListByCoachId(c.getId(), 2)){
					pList.add(playgroundService.getPlaygroundById(v.getZhe_id()));
				}
				
				map.put("pList", pList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/coachendView";
	}
	
	/**
	 * 教练详情页分享
	 * @param map
	 * @param request
	 * @param coachId
	 * @param coachType
	 * @return
	 */
	@RequestMapping("coachdetailShare")
	public String coachdetailShare(ModelMap map, HttpServletRequest request,@RequestParam(value="coachId", required = false)Integer coachId,Integer coachType){
		logger.info("教练详情页分享....");
		Coach c=coachService.getcoachByIdCacheObject(coachId,coachType);
		Playground p=null;
		if(c.getPlayground_id()!=0){
			p=playgroundService.getPlaygroundById(c.getPlayground_id());
		}
		
		
		List<Comment> comment=commentService.getComment(coachId, Constants.DATATYPE.CoachType.getStatus());
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		int score[]=new int[5];
		for(int i=0;i<score.length;i++){
			if(i<Integer.valueOf(c.getEvaluate_score())){
				score[i]=1;
			}
		}
		
		
		
		map.put("score", score);
		map.put("stratTime", format.format(new Date()));
		map.put("comment", comment);
		map.put("coach", c);
		map.put("coachType", coachType);
		map.put("playground", p);
		map.put("type", 2);
		map.put("activeId", c.getId());
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		
		
		long s_long = System.currentTimeMillis();		//开始计时
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(c.getCoachType()==3){
			return "/phone/user/coachViewShare";
		}else{
			return "/phone/user/incoachViewShare";
		}
		
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
	 * 添加教练
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add_new_coach")
	@ResponseBody
	public Object addNewUser(HttpServletRequest request) {
		logger.info("教练注册。。。。");long s_long = System.currentTimeMillis();		//开始计时
		String phonecode = request.getParameter("phone_code");
		String uphone = request.getParameter("phone");
		String pass = request.getParameter("password");
		String username = request.getParameter("nickname");
		try {
			//(一些用户先注册成为会员后,还可以注册为教练)
			Coach check_c = coachService.getcoachByphone(uphone);
			if(check_c!=null){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码已被注册",
						uphone);
			}
			
//			if (user != null) {
//				return new BusinessResponse(
//						Constants.ResponseCode.ERROR_MSG.getType(), "手机号码不可用",
//						uphone);
//			}
			
			ServletContext application = request.getSession().getServletContext();
			VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+uphone);
			String phonecode_verfiy =verifyCode.getVerifycode();
			if(!CommonUtil.NotEmptyObject(verifyCode)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "请先获取验证码",
						uphone);
			}
			if(!phonecode.equals(phonecode_verfiy)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码错误",
						uphone);
			}
			
			
			String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
			int time = Integer.valueOf(code_time);
			if(System.currentTimeMillis()-time*60*1000>verifyCode.getCreatetime()){
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码过期",
						uphone);
			}
			
			Coach c=new Coach();
			c.setName(username);
			c.setPhone(uphone);
			Qualification_certificate qc = new Qualification_certificate();
			qc.setType(Constants.CERTIFICATE_COACH);
			certificateService.saveCertificate(qc);
			c.setCertificate(qc);
			
			Weuser weuser = null;
			weuser = weuserService.getUserByPhone(uphone);
			if(weuser == null){
				weuser = new Weuser();
				weuser.setWenumber("");
				weuser.setPass(SecurityUtil.encrypt(pass));
				weuser.setUphone(uphone);
				weuser.setUsername(username);
				weuser.setCreate_time(CommonUtil.getTimeNow());
				weuser.setUpdate_time(CommonUtil.getTimeNow());
				weuser.setLast_login_ip(CommonUtil.getRemoteIpAddr(request));
				weuser.setLast_login_time(CommonUtil.getTimeNow());
				weuser.setFlag(Constants.NORMAL_FLAG);
				weuser.setLogin_count((weuser.getLogin_count() + 1));
				weuser.setIs_coach(Constants.IS_COACH1);
				if (CommonUtil.isEmpty(weuser.getUsername())) {
					weuser.setUsername(weuser.getReal_name());
				}
				weuserService.saveNewUser(weuser);
				//新增用户时---设置用户统计表			edit by 2015-12-17
				User_count uCount=new User_count();
				uCount.setWeuser(weuser);
				user_countService.saveuser_Count(uCount);
			}else{
				weuser.setPass(SecurityUtil.encrypt(pass));
				weuser.setUsername(username);
				weuser.setIs_coach(Constants.IS_COACH1);
				weuserService.mergeAndUpdateTime(weuser);
			}
			c.setUserid(weuser);
			c = coachService.saveCoach(c);
			
			//添加补贴
//			String money = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_PROPORTION,"0.01");//取系统配置值  教练补贴比例
//			String coach_type = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_TYPE,"1");//取系统配置值  教练补贴类型
//			Subsidies subsidies = new Subsidies();
//			subsidies.setMoney(Float.valueOf(money));
//			subsidies.setZhe_id(c.getId());
//			subsidies.setZhe_type(Constants.SUBSIDIES_COACH);
//			subsidies.setType(Integer.valueOf(coach_type));
//			subsidies.setEnd_time(CommonController.getCurrYearLast());
//			subsidiesService.saveSubsidies(subsidies);
			
			
			
			if (weuser.getId() > 0) {
				logger.info("注册成功！");
				
				//新增教练带人		edit by lxc	2015-12-11	不管什么教练,添加,也需要默认新增
//				if(c.getCoachType()== 2){
					String p = String.valueOf(c.getPrice());
					Coach_teach_person ct = new Coach_teach_person();
					ct.setCoach_id(c.getId());
					ct.setPrice(Float.parseFloat(p));
					coach_teach_personService.saveCoach_teach_person(ct);
//				}
			
				//工作时间
				for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
					Coach_set_time cst = new Coach_set_time();
					cst.setCoach_id(c);
					cst.setTime(i);
					cst.setTime_type(1);
					cst.setFlag(Constants.NORMAL_FLAG);
					coach_set_timeService.saveCoachSetTime(cst);
				}
				//节假时间
				for (int i =  Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
					Coach_set_time cst = new Coach_set_time();
					cst.setCoach_id(c);
					cst.setTime(i);
					cst.setTime_type(2);
					cst.setFlag(Constants.NORMAL_FLAG);
					coach_set_timeService.saveCoachSetTime(cst);
				}
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				
				
//				//S	用户刚注册好,需要弹出消息,告诉用户注册步骤		edit by lxc	2016-02-19 
//				StationMessage s = new StationMessage();
//				s.setContent("第一步实名认证;\r\n第二步教学设置;\r\n第三步日程表的预约收费;\r\n请尽快完善,方可被预约.");
//				s.setTitle("被预约步骤");
//				s.setWeuser_id(weuser.getId());
//				s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
//				stationMessageService.saveStationMessage(s);
//				//E
				
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "注册成功",
						weuser);
			} else {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "注册失败", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			logger.error("");

			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
	
	
	/**
	 * 教练登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "coach_login")
	@ResponseBody
	public Object coach_login(HttpServletRequest request) {
		logger.info("教练登录.....");
		long s_long = System.currentTimeMillis();		//开始计时
		String userPhone = request.getParameter("tel");
		String password = request.getParameter("password");
		int wid = 0;
		try {
			
			
			Weuser user = weuserService.getUserByPhone(userPhone);
			if (user == null) {
				return new BusinessResponse(
						Constants.OperationResult.USER_NOT_EXIST.getStatus(), "用户不存在",
						userPhone);
			}
			Coach o = coachService.getCoachByUserId(user.getId());
			if(o==null){
				return new BusinessResponse(
						Constants.OperationResult.USER_NOT_EXIST.getStatus(), "用户不存在",
						userPhone);
			}
			
			String pass = SecurityUtil.encrypt(password);
			if (!pass.equals(user.getPass())) {
				return new BusinessResponse(
						Constants.OperationResult.INVALID_PASSWORD.getStatus(), "密码不正确",
						password);
			}
			
			
			if(o.getStatus()==Constants.O_STATUS.NOPASS_FOR_CHECK.getStatus()){
				o.setStatus(Constants.O_STATUS.WAIT_FOR_CHECK.getStatus());
				coachService.mergePlayground(o);
			}

			if(o.getCoachType() == COACHTYPE.CARRIEROPERATORCOACH.getStatus() ){
				if(o.getReserve_me() == Constants.IS_RESERVE0){
					return new BusinessResponse(
							Constants.OperationResult.USER_NOT_EXIST.getStatus(), "此用户不能登录",
							userPhone);
				}
			}
			if(o.getStatus()==Constants.O_STATUS.NOPASS_FOR_CHECK.getStatus()){
				o.setStatus(Constants.O_STATUS.WAIT_FOR_CHECK.getStatus());
				coachService.mergePlayground(o);
			}
			Info i = new Info();
			i.setCoachId(o.getId());
			i.setCoachType(o.getCoachType());
			i.setEvaluate_score(o.getEvaluate_score());
			i.setHead_portrait(o.getHead_portrait());
			i.setName(o.getName());
			i.setPersonalitySignature(o.getPersonalitySignature());
			i.setVerified(o.getVerified());
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			
			user.setLogin_count((user.getLogin_count() + 1));
			weuserService.updateUser(user);
			i.setUser(user);
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "登录成功", i);
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			logger.error("");

			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}

	
	
	/**
	 * 获取对应时间点的驻场教练
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "in_coach")
	@ResponseBody
	public Object getIncoach(HttpServletRequest request,String space_time_priceId,Integer playgroundId,Integer userId){
		logger.info("获取对应时间点的驻场教练....");
		
		long s_long = System.currentTimeMillis();		//开始计时
		List<Coach> mCoach=coachService.getCoachBygroundAndType(playgroundId, Constants.COACHTYPE.INNERCOACH.getStatus());
		Integer pmId=playgroundService.getPlaygroundById(playgroundId).getPlaygroundmanager_id();
		Coach carrCoach=coachService.getcoachByphone(playgroundManagerService.getPlaygroundManagerById(pmId).getMobile());
		
		//当场馆运营者不为空的时候
		if(carrCoach!=null){
			if(carrCoach.getVerified()==1 && carrCoach.getReserve_me()==1){
				mCoach.add(carrCoach);
			}
		}
		
		
		String[] stpId=space_time_priceId.split(",");
		
		String[] nu=new String[0];
		
		Weuser user=null;
		try {
			user=weuserService.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			
			List<Coach> reCoach=new ArrayList<Coach>();
			
			for(String i:stpId){
				if(!i.equals("")){
					Space_time_price s=space_time_priceService.findById(Integer.valueOf(i));
					for(int j=0;j<mCoach.size();j++){
						if(coachReserveService.getCoachReserve(s.getDate(), mCoach.get(j).getId(), s.getTime())!=null){
							System.err.println(mCoach.get(j).getName());
							reCoach.add(mCoach.get(j));
							continue;
						}
						if(user!=null){
							if(user.getUphone().equals(mCoach.get(j).getPhone())){
								reCoach.add(mCoach.get(j));
								continue;
							}
						}
						//找教练，当这个教练在休息的时候移除他
						if(coach_set_timeService.getCoach_set_timeByTypeAndHour(mCoach.get(j).getId(), s.getDateType(), s.getTime()).getSpace_id()==0){
							reCoach.add(mCoach.get(j));
							continue;
						}
					}
				}
			}
			
			mCoach.removeAll(reCoach);
			
			for(int i=0;i<mCoach.size();i++){
				mCoach.get(i).setCtp(coach_teach_personService.getCoach_teach_personByCidAndper(mCoach.get(i).getId()));
			}
			
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			if("".equals(stpId[0])){
				return new BusinessResponse(
						Constants.OperationResult.NOSTPID.getStatus(), "数据获取失败", nu);
			}
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", mCoach);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "错误", nu);
		}
		
	}
	
	/**
	 * 跳转教练个人中心
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 */
	@RequestMapping(value = "toCoachSaveUser")
	public String toCoachSaveUser(HttpServletRequest request,ModelMap map,int userId){
		logger.info("教练个人中心....");
		long s_long = System.currentTimeMillis();		//开始计时
		Coach o = coachService.getCoachByUserId(userId);
		map.put("o", o);
		if(o != null){
			if(o.getNow_live_city()> 0 ){
				Region r = regionService.getRegionById(o.getNow_live_city());
				map.put("rname",regionService.getRegionById(r.getParent_id()).getRegion_name()+" "+r.getRegion_name()+" "+regionService.getRegionById(o.getAreaid()).getRegion_name());
				if(o!=null && o.getUserid()!=null && o.getUserid().getHometown()!=null && o.getUserid().getHometown()>0){
					Region r1 = regionService.getRegionById(o.getUserid().getHometown());
					map.put("jname", regionService.getRegionById(r1.getParent_id()).getRegion_name()+" "+r1.getRegion_name());
				}else{
				}
			}else{
				map.put("rname","");
				if(o!=null && o.getUserid()!=null && o.getUserid().getHometown()!=null && o.getUserid().getHometown()>0){
					Region r1 = regionService.getRegionById(o.getUserid().getHometown());
					map.put("jname", regionService.getRegionById(r1.getParent_id()).getRegion_name()+" "+r1.getRegion_name());
				}else{
					map.put("jname","");
				}
			}
		}else{
			map.put("rname","");
			map.put("jname","");
		}
		Invite invite = inviteService.getInviteByfriend_user_id(userId);
		map.put("invite", invite);
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/coachUserInfo";
	}
	
	/**
	 * 保存教练个人中心
	 * @param userId 
	 * @param username
	 * @param sex
	 * @param birthday
	 * @param address
	 * @param niname
	 * @param employer
	 * @param height
	 * @param weight
	 * @param personalitySignature
	 * @param gouji
	 * @param jiguan
	 * @param city
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCoachUserInfo")
	@ResponseBody
	public Object saveCoachUserInfo(int userId,String username,String sex,
									String birthday,String address,String niname,String employer,
									Float height,Float weight,String personalitySignature,
									int city,int areaid,int hometown,String nationality,String invite_id) throws Exception{
		logger.info("保存教练个人中心....");
		long s_long = System.currentTimeMillis();		//开始计时
		Coach o = coachService.getCoachByUserId(userId);
		o.setName(username);					//姓名
		o.setHeight(height);					//升高
		o.setWeight(weight);					//体重
		o.setNationality(nationality);			//国籍
		o.setNow_live_city(city);				//所在城市
		o.setAreaid(areaid);
		o.setPersonalitySignature(personalitySignature);	//签名
		o.setSex(sex);							//性别
		
		//修改教练个人信息同时修改用户信息
		Weuser user = weuserService.getUserById(userId);
		user.setUsername(niname);				//昵称
		user.setBirthday(birthday);	//生日
		user.setSignature(personalitySignature);
		user.setCityid(city);
		user.setAreaid(areaid);
		user.setSex(sex);

		//根据生日计算年龄
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); //本年
		String birthdayT = birthday.substring(0, birthday.indexOf("-"));  
		int age = year - Integer.valueOf(birthdayT);
		user.setAge(age);
		o.setAge(age);
		
		user.setEmployer(employer);				//工作单位
		user.setAddress(address);				//地址
		user.setHometown(hometown);				//籍贯

		coachService.mergePlayground(o);
		//如果没有则保存邀请人信息
		Invite invite = inviteService.getInviteByfriend_user_id(userId);
		if(invite == null){
			Weuser user_id = weuserService.getUserByPhone(invite_id);
			Weuser friend_user_id = weuserService.getUserById(userId);
			if(user_id != null){
				//保存邀请信息
				Invite i = new Invite();
				i.setUser_id(user_id);
				i.setFriend_user_id(friend_user_id);
				inviteService.saveInvite(i);
				
				//增加邀请人的邀请数量
				User_count user_count = user_countService.getUser_countByUserId(user_id.getId());
				user_count.setInvitecount(user_count.getInvitecount()+1);
				user_countService.updateUser_count(user_count);
			}
		}
		
		user = weuserService.mergeAndUpdateTime(user);
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(user!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", user);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
	
	/**
	 * 对接手机端,上传头像文件(安卓端使用)
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "coach_user_header_picture.do")
	@ResponseBody
	public Object user_header_picture(HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response, String userId) {
		logger.info("对接手机端,教练上传头像文件....");
		long s_long = System.currentTimeMillis();		//开始计时
		String photoPath = "upload/a_head_photo/";// 目录名
		Coach o  = null;
		try {
			o = coachService.getCoachByUserId(Integer.parseInt(userId));
		} catch (Exception e) {
			return new BusinessResponse(
					OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
		}
		String image_path = "";
		MultipartFile files = multipartRequest.getFile("imgName");// 获取前台传来的参数
		String photo = "";
		String image_name = ImageDispose.imageName(Settings.COACHHEAD_IMAGENAMESTR, Settings.JPG_PICTUREFORMAT);//图片名称
		if (files.isEmpty()) { // 判断上传的文件是否为空
			System.err.println(OperationResult.UNKNOWN_MISTAKE.getStatus()
					+ "空文件");
		} else {
			logger.info("文件存在....");
			try {
				if(files.getSize() > Settings.IMAGESIZE){  
					return new BusinessResponse(
							OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败,您上传的图片过大,上传图片不能超过4m", "");
				}
				image_name = handleImage(files, request, photoPath, image_name,"",Settings.SMALL_IMAGENAMESTR,"x");//图片上传处理
				o.setHead_portrait(image_name);
				
				logger.info("文件已经上传....");
				try{
					Weuser w = weuserService.getUserById(o.getUserid().getId());
					w.setHead_photo(image_name);
					weuserService.updateUser(w);
					//当教练状态为3时,运营商,		需要把场馆管理者的头像也改了		edit by lxc	2015-12-18
					if(o.getCoachType() == Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
						PlaygroundManager pgm = playgroundManagerService.getPGM(o.getPhone());
						pgm.setHeader(o.getHead_portrait());
						playgroundManagerService.mergePlaygroundManager(pgm);
					}
				}catch(Exception e){
					
				}
				System.err.println(image_path + "安卓====================上传头像,图片大小:"+files.getSize()/1024/1024);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				coachService.mergePlayground(o);
			} catch (Exception e) {
				e.getMessage();
				return new BusinessResponse(
						OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
			}
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),
				"上传成功", image_name);
	}
	

	/**
	 * 对接手机端,上传背景图片文件(安卓端使用)
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "coach_user_background_picture.do")
	@ResponseBody
	public Object coach_user_background_picture(HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response, String userId) {
		logger.info("对接手机端,教练个人背景图片文件....");
		long s_long = System.currentTimeMillis();		//开始计时
		String photoPath = "upload/coach/";// 目录名
		Coach o  = null;
		try {
			o = coachService.getCoachByUserId(Integer.parseInt(userId));
		} catch (Exception e) {
			return new BusinessResponse(
					OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
		}
		String image_path = "";
		MultipartFile files = multipartRequest.getFile("imgName");// 获取前台传来的参数
		String backgroundImg = ImageDispose.imageName(Settings.COACHHEAD_IMAGENAMESTR, Settings.JPG_PICTUREFORMAT);//图片名称
		if (files.isEmpty()) { // 判断上传的文件是否为空
			System.err.println(OperationResult.UNKNOWN_MISTAKE.getStatus()
					+ "空文件");
		} else {
			try {
				if(files.getSize() > Settings.IMAGESIZE){  
					return new BusinessResponse(
							OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败,您上传的图片过大,上传图片不能超过4m", "");
				}
				backgroundImg = handleImage(files, request, photoPath, backgroundImg,"","","");//图片上传处理
				o.setBackgroundImg(backgroundImg);
				System.err.println(image_path + "安卓====================上传头像,图片大小:"+files.getSize()/1024/1024);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				coachService.mergePlayground(o);
			} catch (Exception e) {
				e.getMessage();
				return new BusinessResponse(
						OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
			}
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),
				"上传成功", backgroundImg);
	}
	
	
	/**
	 * 教练我的信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "myCoachInfo")
	@ResponseBody
	public Object myInfo(HttpServletRequest request,int userId,String appsyetem){
		logger.info("教练我的信息....");
		long s_long = System.currentTimeMillis();		//开始计时
		Map<String,Object> map = new HashMap<String,Object>();
		
		Coach o = coachService.getCoachByUserId(userId);
		
		if(o!=null){
			Weuser u = weuserService.getUserById(o.getUserid().getId());
			o.setName(u.getUsername());
			if(o.getNow_live_city()>0){
				o.setCity(regionService.getRegionById(o.getNow_live_city()).getRegion_name());
			}
//			o.setArea(regionService.getRegionById(o.getAreaid()).getRegion_name());
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败,您不是教练", "");
		} 
		User_count p = user_countService.getUser_countById(userId);
		int all = orderMainService.getOrderMainList(0, userId).size();//全部
		int zhou = orderMainService.getOrderMainList(1, userId).size();//本周
		int geton = orderMainService.getOrderMainList(2, userId).size();//待进行
		int evaluation = orderMainService.getOrderMainList(3, userId).size();//待评价
		int message = stationMessageService.getStationMessageByWeuserIdAndStatus(userId,appsyetem).size();//消息
		Weuser w = p.getWeuser();
		if(w.getCityid() != null){
			w.setAddress(regionService.getRegionById(w.getCityid()).getRegion_name());
			p.setWeuser(w);
		}
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("all", all);
		map2.put("zhou", zhou);
		map2.put("geton", geton);
		map2.put("message", message);
		map2.put("evaluation", evaluation);
		map.put("coach", o);
		map.put("User_count", p);
		map.put("orderform", map2);
		
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(map!=null && map.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}
	
	/**
	 * 教练日程表
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 */
	@RequestMapping(value="carrschedule")
	public String carrschedule(HttpServletRequest request,ModelMap map,Integer coachId){
		logger.info("教练日程表....");
		long s_long = System.currentTimeMillis();		//开始计时
		//获取到7天的日期表格
		List<DateAssist> list=new ArrayList<DateAssist>();
		for(int m=0;m<7;m++){
			DateAssist da=new DateAssist();
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE,m);//把日期往后增加一天.整数往后推,负数往前移动
			if((calendar.get(Calendar.DAY_OF_WEEK)-1)==0){
				da.setWeek("星期天");
			}
			da.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
			da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
			if(calendar.get(Calendar.DATE)<10){
				da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
			}else{
				da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
			}
			
			list.add(da);
		}
		
		Coach c=coachService.getcoachById(coachId);
		List<Coach_teach_person> ctpList;
		try {
			ctpList=coach_teach_personService.getCoach_teach_personByCoachId(c.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ctpList=null;
		}
		
		
		if(ctpList!=null && ctpList.size()!=0){
			for(int i=0;i<ctpList.size();i++){
				if(ctpList.get(i).getPrice()==0){
					ctpList.remove(i);
				}
			}
		}
		
		
		map.put("coach", c);
		map.put("dateList",list);
		
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		map.put("mSize",orderMainService.getOrderMainListByActiveID(2, c.getId()).size());		//本月课时 
		map.put("wSize", orderMainService.getOrderMainListByActiveID(1, c.getId()).size());		//本周课时 
		map.put("cSize", user_countService.getUser_countByUserId(c.getUserid().getId()).getLessonCount());		//累计课时
		map.put("person", coach_teach_personService.getCoach_teach_personByCoachId(c.getId()));
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(c.getCoachType()==3){
			return "/phone/user/carrSchedule";
		}else if(c.getCoachType()==2){
			return "/phone/user/inTheFiel";
		}else{
			return "/phone/user/freeSchedule";
		}
		
	}
	
	@RequestMapping(value="getCoachById")
	@ResponseBody
	public Object getCoachById(HttpServletRequest request,Integer coachId){
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", coachService.getcoachById(coachId));
	}
	
	
	/**
	 * 实名认证
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "toverified")
	public String toverified(HttpServletRequest request,ModelMap map,int userId){
		Coach o = coachService.getCoachByUserId(userId);
		List<Coaching_experience> list = coaching_experienceService.getCoaching_experienceListByCoachId(o.getId());
		map.put("o", o);
		map.put("clist", list);
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		return "/phone/user/verified";
	}
	
	/**
	 * 保存实名认证
	 * @param userId
	 * @param xueli
	 * @param professional
	 * @param school
	 * @param teaching
	 * @param goodAt
	 * @param coachType
	 * @param idcard_no
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveVerified")
	@ResponseBody
	public Object saveVerified(int userId,String xueli,String professional,
								 String school,int teaching,String goodAt,
								 int coachType,String idcard_no,String unitNames,
								 String begin_times,String end_times,
								 String bank,String zbank,String bankZh,String bankName) throws Exception{
		logger.info("保存实名认证....");
		long s_long = System.currentTimeMillis();		//开始计时
		Coach o = coachService.getCoachByUserId(userId);
		o.setEducationalBackground(xueli);
		o.setSchoolzy(professional);
		o.setTeaching(teaching);
		o.setGoodAt(goodAt);
		o.setBank(bank);
		o.setZbank(zbank);
		o.setBankZh(bankZh);
		o.setBankName(bankName);
		o.setVerified(2);
//		o.setCoachType(coachType);	现在暂时不能改
		o = coachService.mergePlayground(o);
		
		//循环删除原来的执教经历
		List<Coaching_experience> list = coaching_experienceService.getCoaching_experienceListByCoachId(o.getId());
		if(list != null && list.size() >0 ){
			for (Coaching_experience coaching_experience : list) {
				coaching_experienceService.deleteCoaching_experience(coaching_experience);
			}
		}
		//添加执教经历
		String [] begin_time =  begin_times.split(",");
		String [] end_time =  end_times.split(",");
		String [] unitName =  unitNames.split(",");
		for (int i = 0; i < unitName.length; i++) {
			for (int s = 0; s < begin_time.length; s++) {
				for (int y = 0; y < end_time.length; y++) {
					if(i == s && s == y){
						if(CommonUtil.NotEmpty(unitName[i]) && CommonUtil.NotEmpty(begin_time[s]) && CommonUtil.NotEmpty(end_time[y])){
							Coaching_experience c = new Coaching_experience();
							c.setBegin_time(begin_time[s]);
							c.setUnitName(unitName[i]);
							c.setEnd_time(end_time[y]);
							c.setCoachId(o.getId());
							coaching_experienceService.saveCoaching_experience(c);
							break;
						}
					}
				}
			}
		}
		Weuser user = weuserService.getUserById(userId);
		user.setSchool(school);
		user.setIdcard_no(idcard_no);
		user = weuserService.mergeAndUpdateTime(user);
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(o!=null&&user!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
	
	
	/**
	 * 预计收入和预计补贴
	 * @param request
	 * @param userId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "revenueCycle")
	@ResponseBody
	public Object findCycle1(HttpServletRequest request,int userId) throws ParseException{
		logger.info("预计收入和预计补贴....");
		long s_long = System.currentTimeMillis();		//开始计时
		Map<Object,Object> map = new HashMap<Object,Object>();
	
		List<Orderinfo> moneyList = null;
		
		
		
		Coach c = coachService.getCoachByUserId(userId);
		double money7 = 0;
		double money2 = 0;
		double money3 = 0;
		double money6 = 0;
		double money8 = 0.0;
		double money5 = 0;
		double money4 = 0;
		double money9 = 0.0;
		double m = 0.0;
		if(c!=null){
				
			
			Orderinfo  o = new Orderinfo();
			o.setActiveID(String.valueOf(c.getId()));
			o.setOrderType(Constants.DATATYPE.CoachType.getStatus());
			
			//本周收入
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("week", 1);
			map1.put("status", "status");
			moneyList = orderInfoService.getOrderinfoByObj(o, map1);
			if(moneyList != null){
				for (Orderinfo Orderinfo : moneyList) {
					money7 += Orderinfo.getOrderPayTotalAmont();
				}
			}
			
			
			//下周收入
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("week", 2);
			map2.put("status", "status");
			moneyList = orderInfoService.getOrderinfoByObj(o, map2);
		
			if(moneyList != null){
				for (Orderinfo Orderinfo : moneyList) {
					money2 += Orderinfo.getOrderPayTotalAmont();
				}
			}
			
			
			
			//上周收入
			Map<String,Object> map3 = new HashMap<String,Object>();
			map3.put("week", 0);
			map3.put("status", "1");
			moneyList = orderInfoService.getOrderinfoByObj(o, map3);
			
			if(moneyList != null){
				for (Orderinfo Orderinfo : moneyList) {
					money3 += Orderinfo.getOrderPayTotalAmont();
				}
			}
			
			//上上周收入
			Map<String,Object> map4 = new HashMap<String,Object>();
			map4.put("week", -1);
			map4.put("status", "1");
			moneyList = orderInfoService.getOrderinfoByObj(o, map4);
			
			if(moneyList != null){
				for (Orderinfo Orderinfo : moneyList) {
					money4 += Orderinfo.getOrderPayTotalAmont();
				}
			}
			
			//本月收入
			Map<String,Object> map5 = new HashMap<String,Object>();
			map5.put("months", 1);
			map5.put("status", "status");
			moneyList = orderInfoService.getOrderinfoByObj(o, map5);
			
			if(moneyList != null){
				for (Orderinfo Orderinfo : moneyList) {
					money5 += Orderinfo.getOrderPayTotalAmont();
				}
			}
			
			//上月收入
			Map<String,Object> map6 = new HashMap<String,Object>();
			map6.put("months", 0);
			map6.put("status", "1");
			moneyList = orderInfoService.getOrderinfoByObj(o, map6);
			
			if(moneyList != null){
				for (Orderinfo Orderinfo : moneyList) {
					money6 += Orderinfo.getOrderPayTotalAmont();
				}
			}
	
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH)+1; //本月
			int year = cal.get(Calendar.YEAR); //本年
			int xmonth = 0; // 上月
			int xyear = 0; 	// 上年
			if(month == 1){
				xmonth = 12; 
				xyear = year - 1; 
			}else{
				xmonth = month - 1;
				xyear = year;
			}
			
			
			//本月补贴
			Subsidies sub1 = new Subsidies();
			sub1.setYear(String.valueOf(year));
			sub1.setMonth(String.valueOf(month));
			sub1.setStatus(-1);
			sub1.setType(Constants.SUBSIDIES_APRIL);
			sub1.setZhe_id(c.getId());
			sub1.setZhe_type(1);
			List<Subsidies> subList1 = subsidiesService.getSubsidiesByObj(sub1);
			if(subList1 != null && subList1.size()>0){
				m = subList1.get(0).getMoney();
				money8 = subList1.get(0).getMoney() * money5;
			}
			
			
			
			//上月补贴
			Subsidies sub2 = new Subsidies();
			sub2.setYear(String.valueOf(xyear));
			sub2.setMonth(String.valueOf(xmonth));
			sub2.setStatus(-1);
			sub2.setType(Constants.SUBSIDIES_APRIL);
			sub2.setZhe_id(c.getId());
			sub2.setZhe_type(1);
			List<Subsidies> subList2 = subsidiesService.getSubsidiesByObj(sub2);
			if(subList2 != null && subList2.size()>0){
				money9 = subList2.get(0).getMoney() * money6;
			}
		}
		
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
		
		map.put("benyue_b", money8>0?df.format(money8):money8);
		map.put("shangyue_b", money9>0?df.format(money9):money9);
		map.put("shangyue_s", money6>0?df.format(money6):money6);
		map.put("benyue_s", money5>0?df.format(money5):money5);
		
		
		map.put("shangzhou_b", money3*m>0?df.format(money3*m):money3*m);
		map.put("shangszhou_b", money4*m>0?df.format(money4*m):money4*m);
		map.put("benzhou_b", money7*m>0?df.format(money7*m):money7*m);
		map.put("xiazhou_b", money2*m>0?df.format(money2*m):money2*m);
		
		map.put("xiazhou_s", money2>0?df.format(money2):money2);
		map.put("benzhou_s", money7>0?df.format(money7):money7);
		map.put("shangszhou_s", money4>0?df.format(money4):money4);
		map.put("shangzhou_s", money3>0?df.format(money3):money3);
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(map!=null  && map.size()>0 ){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
	}	
	
	/**
	 * 教练我的账户
	 * @param request
	 * @param map
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "myAccount")
	public String myAccount(HttpServletRequest request,ModelMap map,int userId) throws Exception{
		long s_long = System.currentTimeMillis();		//开始计时
		logger.info("教练我的账户....");
		Coach c = coachService.getCoachByUserId(userId);
		Orderinfo  o = new Orderinfo();
		o.setActiveID(String.valueOf(c.getId()));
		o.setOrderType(Constants.DATATYPE.CoachType.getStatus());

		//累计收入
		Map<Object,Object> map3 = new HashMap<Object, Object>();
		map3.put("status", "1");
		List<Orderinfo> allList = orderInfoService.getOrderinfoByObj(o, map3);
		
		//本月收入
		Map<Object,Object> map1 = new HashMap<Object, Object>();
		Calendar a=Calendar.getInstance();
		map1.put("status", "1");
		map1.put("year", String.valueOf(a.get(Calendar.YEAR)));
		map1.put("month", String.valueOf(a.get(Calendar.MONTH)+1));
		List<Orderinfo> monthList = orderInfoService.getOrderinfoByObj(o, map1);
		
		//本周收入
		Map<Object,Object> map2 = new HashMap<Object, Object>();
		map2.put("status", "1");
		map2.put("week", 1);
		List<Orderinfo> weekList = orderInfoService.getOrderinfoByObj(o, map2);
		double allmoney = 0;
		double weekmoney = 0;
		double monthmoney = 0;
		if(allList!=null && allList.size()>0){
			for (Orderinfo od : allList) {
				allmoney += od.getOrderPayTotalAmont();
			}
		}
		if(monthList!=null && monthList.size()>0){
			for (Orderinfo od : monthList) {
				monthmoney += od.getOrderPayTotalAmont();
			}
		}
		
		if(weekList!=null && weekList.size()>0){
			for (Orderinfo od : weekList) {
				weekmoney += od.getOrderPayTotalAmont();
			}
		}
		
		
		//累计补贴
		Subsidies sub1 = new Subsidies();
		sub1.setStatus(0);
		sub1.setType(Constants.SUBSIDIES_APRIL);
		sub1.setZhe_id(c.getId());
		sub1.setZhe_type(1);
		List<Subsidies> subAllList = subsidiesService.getSubsidiesByMapAndObj(sub1, null);
		
		//本月补贴
		Subsidies sub2 = new Subsidies();
		sub2.setStatus(0);
		sub2.setType(Constants.SUBSIDIES_APRIL);
		sub2.setZhe_id(c.getId());	
		sub2.setZhe_type(1);
		sub2.setYear(String.valueOf(a.get(Calendar.YEAR)));
		sub2.setMonth(String.valueOf(a.get(Calendar.MONTH)+1));
		List<Subsidies> subMonthList = subsidiesService.getSubsidiesByMapAndObj(sub2, null);
		double allSubmoney = 0;
		double weekSubmoney = 0;
		double montSubhmoney = 0;
		
		float sube = 0;
		if(subAllList!=null && subAllList.size()>0){
			for (Subsidies s : subAllList) {
				allSubmoney += s.getSubsidies_money();
			}
		}
		if(subMonthList!=null && subMonthList.size()>0){
			for (Subsidies s : subMonthList) {
				montSubhmoney += s.getSubsidies_money();
				sube = s.getMoney();
			}
			//本周补贴 （用本周收入乘本月补贴额）
			weekSubmoney = weekmoney * sube;
		}
		
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"); 
		Weuser user = weuserService.getUserById(userId);
		map.put("allmoney", allmoney>0?Double.valueOf(df.format(allmoney)):allmoney);
		map.put("weekmoney", weekmoney>0?Double.valueOf(df.format(weekmoney)):weekmoney);
		map.put("monthmoney", monthmoney>0?Double.valueOf(df.format(monthmoney)):monthmoney);
		map.put("allSubmoney", allSubmoney>0?Double.valueOf(df.format(allSubmoney)):allSubmoney);
		map.put("weekSubmoney", weekSubmoney>0?Double.valueOf(df.format(weekSubmoney)):weekSubmoney);
		map.put("montSubhmoney", montSubhmoney>0?Double.valueOf(df.format(montSubhmoney)):montSubhmoney);
		map.put("user", user);
		map.put("c", c);
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		
		map.put("subAllList", subAllList);
		map.put("subsidies_grant_time", systemConfigService.getSystemConfigBykey("subsidies_grant_time"));  //补贴发放时间设置(单位:天)
		map.put("trade_balance_time", systemConfigService.getSystemConfigBykey("trade_balance_time"));  //交易结算时间设置(单位:天)
		String  url = "";
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		if(c!=null&&c.getCoachType()==1){	//自由
			url = "/phone/user/myAccountsfree";
		}
		if(c!=null&&c.getCoachType()==2){	//驻场
			url =  "/phone/user/myAccountsResident";
		}
		if(c!=null&&c.getCoachType()==3){	//运营
			url =  "/phone/user/myAccountsOperators";
		}
		return url;
	}
	
	/**
	 * 月周期收入和补贴
	 * @param request
	 * @param map
	 * @param coachId
	 * @param time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "toCycleMoney")
	public String toCycleMoney(HttpServletRequest request,ModelMap map,String coachId,String year,String month) throws Exception{
		OrderVo o = new OrderVo();
		o.setOrderType(Constants.DATATYPE.CoachType.getStatus());
		o.setActiveID(coachId);
		Map map2 = CommonController.getMonthTimeByYear(Integer.valueOf(year), Integer.valueOf(month));
		o.setStart_time(String.valueOf(map2.get("firstDay")));
		o.setEnd_time(String.valueOf(map2.get("lastDay")));
		
		System.err.println(coachId+"::::::::"+map2.get("firstDay")+"---"+map2.get("lastDay"));
		List<SubsidiesFrom> list = subsidiesFromService.getSubsidiesFrom(o);
		map.put("list", list);
		return "/phone/user/cycleMoney";
	}
	
	/**
	 * 补贴比例详情查看
	 * @param request
	 * @param map
	 * @param coachId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "toviewSub")
	public String toviewSub(HttpServletRequest request,ModelMap map,int coachId) throws Exception{
		Subsidies sub2 = new Subsidies();
		sub2.setStatus(-1);
		sub2.setType(Constants.SUBSIDIES_APRIL);
		sub2.setZhe_id(coachId);	
		sub2.setZhe_type(1);
		map.put("list", subsidiesService.getSubsidiesByObj(sub2));
		return "/phone/user/withdrawList";
	}
	
	/**
	 * 我的教学设置
	 * @param request
	 * @param map
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "myTeaching")
	public String myTeaching(HttpServletRequest request,ModelMap map,int userId) throws Exception{
		logger.info("我的教学设置....");
		long s_long = System.currentTimeMillis();		//开始计时
		Coach coach = coachService.getCoachByUserId(userId);
		map.put("o", coach);
		if(coach.getServices().contains("4")){
			map.put("dis", 1);
		}else{
			map.put("dis", 0);
		}
		map.put("ds",dictionariesService.getDictionaries(Constants.SERVICE_COACH));
		map.put("area", regionService.getArea(String.valueOf(coach.getNow_live_city())));				//区域
		map.put("play", playgroundService.getPlaygroundList(coach.getNow_live_city(), -1,null));		//场馆
		
		map.put("sarea",visitService.getVisitListByCoachId(coach.getId(), 1));		//教练上门的区域
		map.put("splay",visitService.getVisitListByCoachId(coach.getId(), 2));		//教练上门的场馆
		
		//申请成为驻场教练数据
		Apply ap = new Apply();
		Coach co = new Coach();
		co.setId(coach.getId());
		ap.setCoach(co);
		List<Apply> applyList = applyService.getApplyByObj(ap);
		map.put("coachPlay",applyList);
		int status = 0;
		if(applyList != null){
			status = applyList.get(0).getStatus();
		}
		map.put("applyStatus", status);
		
		
		if(coach.getCoachType()==COACHTYPE.INNERCOACH.getStatus()){
			map.put("playground",playgroundService.getPlaygroundById(coach.getPlayground_id()));		//教练所属场馆
		}
		if(coach.getCoachType()==COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
			PlaygroundManager m = playgroundManagerService.getPlaygroundManagerByCoachId(coach.getId());
			Playground  playground = playgroundService.getByPlaygroundManagerId(m.getId());
			map.put("playground",playground);		//教练所属场馆
		}
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/myTeaching";
	}
	
	/**
	 * 保存教学设置
	 * @param userId
	 * @param equipment
	 * @param professional
	 * @param services
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTeaching")
	@ResponseBody
	public Object saveVerified(int userId,int equipment,String professional,String services,String area,String play,String coachPlay) throws Exception{
		logger.info("保存教学设置....");
		long s_long = System.currentTimeMillis();		//开始计时
		Coach o = coachService.getCoachByUserId(userId);
		o.setProfessional(professional);
		o.setServices(services);
		o.setEquipment(equipment);
		o = coachService.mergePlayground(o);
		
		Apply ap = new Apply();
		Coach co = new Coach();
		co.setId(o.getId());
		ap.setCoach(co);
		List<Apply> listApply = applyService.getApplyByObj(ap);
		int status = 1;
		String apyTime = CommonUtil.getTimeNow();
		//先删除全部的申请然后在保存
		if(listApply != null){
			for (Apply apply : listApply) {
				status = apply.getStatus();
				apyTime = apply.getApyTime();
				applyService.deleteApply(apply);
			}
		}
		if(CommonUtil.NotEmpty(coachPlay)){
			String[] coachPlays = coachPlay.split(",");
			for (int i = 0; i < coachPlays.length; i++) {
				if(CommonUtil.NotEmpty(coachPlays[i])){
					Apply ap1 = new Apply();
					ap1.setStatus(status);
					ap1.setApyTime(apyTime);
					ap1.setPlayground_id(Integer.valueOf(coachPlays[i]));
					Playground p = playgroundService.getPlaygroundById(Integer.valueOf(coachPlays[i]));
					ap1.setPlaygroundmanager_id(p.getPlaygroundmanager_id());
					ap1.setCoach(o);
					applyService.saveApply(ap1);
				}
			}
		}
		
		if(o!=null){
			//如果包含上门服务
			if(services.contains("4")){	
				
				//先删除全部上门区域然后在保存
				String[] areas = area.split(",");
				List<Visit> list1 = visitService.getVisitListByCoachId(o.getId(), 1);
				if(list1 != null){
					for (Visit visit : list1) {
						visitService.deleteVisit(visit);
					}
				}
				for (int i = 0; i < areas.length; i++) {
					if(CommonUtil.NotEmpty(areas[i])){
						Visit v = new Visit();
						v.setCoachId(o.getId());
						v.setViType(1);
						v.setZhe_id(Integer.valueOf(areas[i]));
						visitService.saveVisit(v);
					}
				}
				
				//先删除全部上门场馆然后在保存
				String[] plays = play.split(",");
				List<Visit> list2 = visitService.getVisitListByCoachId(o.getId(), 2);
				if(list2 != null){
					for (Visit visit : list2) {
						visitService.deleteVisit(visit);
					}
				}
				for (int i = 0; i < plays.length; i++) {
					if(CommonUtil.NotEmpty(plays[i])){
						Visit v = new Visit();
						v.setCoachId(o.getId());
						v.setViType(2);
						v.setZhe_id(Integer.valueOf(plays[i]));
						visitService.saveVisit(v);
					}
				}
			}else{
				//不包含上门服务则删除当前教练全部上门的记录
				List<Visit> list = visitService.getVisitListByCoachId(o.getId(), 0);
				for (Visit visit : list) {
					visitService.deleteVisit(visit);
				}
			}
			logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
	
	
	/**
	 * 账户充值
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "torecharge")
	public String torecharge(HttpServletRequest request,ModelMap map,int userId){
		Weuser o = weuserService.getUserById(userId);
		map.put("o", o);
		map.put("rechargeList", rechargeEventsService.getAllRechargeEventsList());
		map.put("getVersion", getVersion());
		return "/phone/user/recharge";
	}
	
	/**
	 * 教练查看大图片
	 * @param imgurl
	 * @return
	 */
	@RequestMapping(value = "getCoachImgUrl")
	@ResponseBody
	public Object getCoachImgUrl(int coachId){
		String imgurl = coachService.getcoachById(coachId).getHead_portrait();
		int length = imgurl.lastIndexOf("/")+1;
		String imgurl_mulu = imgurl.substring(0, length);
		String imgUrl = imgurl.substring(imgurl.lastIndexOf("/")+1);
		imgUrl = imgUrl.substring(imgUrl.indexOf("_")+1);
		System.err.println(imgurl_mulu+imgUrl);
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", imgurl_mulu+imgUrl);
	}
	
	/**
	 * 修改教练经纬度
	 * @param coachId
	 * @param lat 纬度
	 * @param lng 经度
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upCoachLatAndLng")
	@ResponseBody
	public Object updateCoach(int coachId,String lat,String lng) throws Exception{
		Coach coach = coachService.getcoachById(coachId);
		if(coach.getCoachType()==Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus() || coach.getCoachType() == Constants.COACHTYPE.FREECOACH.getStatus()){
			coach.setCoordinateslatitude(Double.valueOf(lat));
			coach.setCoordinateslongitude(Double.valueOf(lng));
			coach = coachService.mergePlayground(coach);
		}
		if(coach!=null){
			logger.info("修改教练经纬度成功...");
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), "成功", coach);
		}else{
			logger.info("修改教练经纬度失败...");
			return new BusinessResponse(Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	
	}
	
	/**
	 * 教练资格认证上传证书
	 * @param request
	 * @param multipartRequest
	 * @param response
	 * @param userId
	 * @param type 1：ITF 2：USPTA 3：PTR 4：RPT 5：Equelite 6：CTA
	 * @return
	 */
	@RequestMapping(value = "auth.do")
	@ResponseBody
	public Object auth(HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response, String userId,int type) {
		String photoPath = "upload/certificate/";// 目录名
		Coach o  = null;
		try {
			o = coachService.getCoachByUserId(Integer.parseInt(userId));
		} catch (Exception e) {
			return new BusinessResponse(
					OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
		}
		String image_path = "";
		MultipartFile files = multipartRequest.getFile("imgName");// 获取前台传来的参数
		String image_name = ImageDispose.imageName(Settings.CERTIFICATE_IMAGENAMESTR, Settings.JPG_PICTUREFORMAT);//图片名称
		if (files.isEmpty()) { // 判断上传的文件是否为空
			System.err.println(OperationResult.UNKNOWN_MISTAKE.getStatus()
					+ "空文件");
		} else {
			try {
				if(files.getSize() > Settings.IMAGESIZE){  
					return new BusinessResponse(
							OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败,您上传的图片过大,上传图片不能超过4m", "");
				}
				image_name = handleImage(files, request, photoPath, image_name,"",Settings.SMALL_IMAGENAMESTR,"");//图片上传处理
				Qualification_certificate q = certificateService.getCertificateById(o.getCertificate().getId());
				if(type == 1){
					q.setPh1(image_name);
				}else if(type == 2){
					q.setPh2(image_name);
				}else if(type == 3){
					q.setPh3(image_name);
				}else if(type == 4){
					q.setPh4(image_name);
				}else if(type == 5){
					q.setPh5(image_name);
				}else if(type == 6){
					q.setPh6(image_name);
				}else if(type == 7){
					q.setPh7(image_name);
				}else if(type == 8){
					q.setPh8(image_name);
				}else if(type == 9){
					q.setPh9(image_name);
				}
				System.err.println(image_path + "安卓====================上传证书");
				certificateService.updateCertificate(q);
			} catch (Exception e) {
				return new BusinessResponse(
						OperationResult.UNKNOWN_MISTAKE.getStatus(), "上传失败", "");
			}
		}
		return new BusinessResponse(OperationResult.SUCCESS.getStatus(),
				"上传成功", image_name);
	}
	
	/**
	 * 驻场教练查看预订了自己的订单
	 * @param request
	 * @param orderInfoNo
	 * @return
	 */
	@RequestMapping(value = "inTheCoachOrder")
	public String inTheCoachOrder(HttpServletRequest request,ModelMap map,String orderInfoNo){
		logger.info("驻场教练查看自己的订单详情。。。。");long s_long = System.currentTimeMillis();		//开始计时
		String check = request.getParameter("check");
		map.put("check", check);
		
		
		//获取父订单
		OrderMain om = orderMainService.getOrderMainByNO(orderInfoService.getOrderByorderSubNo(orderInfoNo).getOrderMainNo());
		map.put("red", redBagRecordService.getRed_bag_recordByUserId(om.getWeuser().getId(), 1).size());
		
		//订单为场馆预订的时候
		Playground playground=null;
		
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
		String person="";
		
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
							Space_time_price sTest = space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time()));
							sTest.setPrice(o.getOrderPayTotalAmont());
							sList.add(sTest);
							playground = playgroundService.getPlaygroundById(Integer.valueOf(o.getActiveID()));
							hourCount++;
							count++;
						}else{
							coachList.add(coachService.getcoachById(Integer.valueOf(o.getActiveID())));
							if(o.getDetails()!=null){
								person=o.getDetails();
							}
							
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
		
		if(om.getPerson()!=null && om.getPerson()!=0){
			map.put("coachPrice", coach_teach_personService.getCoach_teach_personById(om.getPerson()).getPrice());
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
		map.put("orderMainNo", om.getOrderMainNo());
		map.put("person", person);
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		
		return "/phone/user/intTheCoachOrder";
	}
	
	/**
	 * 自由教练查看预订了自己的订单
	 * @param request
	 * @param orderInfoNo
	 * @return
	 */
	@RequestMapping(value = "freeCoachOrder")
	public String freeCoachOrder(HttpServletRequest request,ModelMap map,String orderInfoNo){
		logger.info("自由教练查看自预订了自己的订单。。。。");long s_long = System.currentTimeMillis();		//开始计时
		
		//获取父订单
		OrderMain om = orderMainService.getOrderMainByNO(orderInfoService.getOrderByorderSubNo(orderInfoNo).getOrderMainNo());
		Coach coach=coachService.getcoachById(Integer.valueOf(om.getActiveID()));
		List<Orderinfo> oi = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
		List<DateAssist> tList=new ArrayList<DateAssist>();
		List<CoachReserve> crtList=new ArrayList<CoachReserve>();
		List<Coach> coachList=new ArrayList<Coach>();
		coachList.add(coach);
		Integer hourCount=0;
		String person="";
		//用于获取7天后的日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		for(Orderinfo o:oi){
		try {
			c.setTime(format.parse(om.getCreateTime()));
			c.set(Calendar.DAY_OF_WEEK, 7);
		
			int days=CommonUtil.daysBetween(format.parse(o.getCreate_date()),c.getTime());
				if(days<7 && days>=0){
					if(o.getDetails()!=null){
						person=o.getDetails();
					}
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
						hourCount++;
						tList.add(da);
						for(CoachReserve crt:coachReserveService.getCoachReserveBySubNo(o.getOrderSubNo())){
							
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
		map.put("coach", coach);
		map.put("tList", tList);
		map.put("weekcount", om.getWeekCount());
		map.put("orderMainNo", om.getOrderMainNo());
		map.put("crtList", crtList);
		map.put("coachList", coachList);
		map.put("price", om.getTotalAmount());
		map.put("user", om.getWeuser());
		map.put("mainId", om.getId());
		map.put("userId", om.getWeuser().getId());
		map.put("person", person);
		map.put("hourCount", hourCount*om.getWeekCount());
		logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
		return "/phone/user/freeCoachOrder";
	}
	
	
	
	/**
	 * 添加教练
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addCoach")
	@ResponseBody
	public Object addCoach(HttpServletRequest request) {
		logger.info("教练注册。。。。");long s_long = System.currentTimeMillis();		//开始计时
		String phonecode = request.getParameter("phone_code");
		String uphone = request.getParameter("phone");
		String pass = request.getParameter("password");
		String username = request.getParameter("nickname");
		String userId = request.getParameter("userId");
		try {
			//(一些用户先注册成为会员后,还可以注册为教练)
			Coach check_c = coachService.getcoachByphone(uphone);
			if(check_c!=null){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "您的手机号码已被注册",
						uphone);
			}
			
//			if (user != null) {
//				return new BusinessResponse(
//						Constants.ResponseCode.ERROR_MSG.getType(), "手机号码不可用",
//						uphone);
//			}
			
			ServletContext application = request.getSession().getServletContext();
			VerifyCode verifyCode = (VerifyCode) application.getAttribute(Settings.PHONECODE_APPLICATION+uphone);

			if(!CommonUtil.NotEmptyObject(verifyCode)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "请先获取验证码",
						uphone);
			}
			String phonecode_verfiy =verifyCode.getVerifycode();
			if(!phonecode.equals(phonecode_verfiy)){
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码错误",
						uphone);
			}
			
			
			String code_time = systemConfigService.getConfigValueByKey(ConfigKey.DEFAULT_MOBILE_VERIFICATION_CODE_TIME,"3");//取系统配置值
			int time = Integer.valueOf(code_time);
			if(System.currentTimeMillis()-time*60*1000>verifyCode.getCreatetime()){
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "验证码过期",
						uphone);
			}
			
			Coach c=new Coach();
			c.setName(username);
			c.setPhone(uphone);
			Qualification_certificate qc = new Qualification_certificate();
			qc.setType(Constants.CERTIFICATE_COACH);
			certificateService.saveCertificate(qc);
			c.setCertificate(qc);
			
			Weuser weuser = null;
			weuser = weuserService.getUserByPhone(uphone);
			if(weuser == null){
				weuser = new Weuser();
				weuser.setWenumber("");
				weuser.setPass(SecurityUtil.encrypt(pass));
				weuser.setUphone(uphone);
				weuser.setUsername(username);
				weuser.setCreate_time(CommonUtil.getTimeNow());
				weuser.setUpdate_time(CommonUtil.getTimeNow());
				weuser.setLast_login_ip(CommonUtil.getRemoteIpAddr(request));
				weuser.setLast_login_time(CommonUtil.getTimeNow());
				weuser.setFlag(Constants.NORMAL_FLAG);
				weuser.setLogin_count((weuser.getLogin_count() + 1));
				weuser.setIs_coach(Constants.IS_COACH1);
				if (CommonUtil.isEmpty(weuser.getUsername())) {
					weuser.setUsername(weuser.getReal_name());
				}
				weuser = weuserService.saveNewUser(weuser);
				
				Weuser user_id = weuserService.getUserById(Integer.valueOf(userId));
				//保存邀请信息
				Invite i = new Invite();
				i.setUser_id(user_id);
				i.setFriend_user_id(weuser);
				inviteService.saveInvite(i);
				
				//增加邀请人的邀请数量
				User_count user_count = user_countService.getUser_countByUserId(user_id.getId());
				user_count.setInvitecount(user_count.getInvitecount()+1);
				user_countService.updateUser_count(user_count);
				
				//新增用户时---设置用户统计表			edit by 2015-12-17
				User_count uCount=new User_count();
				uCount.setWeuser(weuser);
				user_countService.saveuser_Count(uCount);
			}else{
				weuser.setPass(SecurityUtil.encrypt(pass));
				weuser.setUsername(username);
				weuser.setIs_coach(Constants.IS_COACH1);
				weuserService.mergeAndUpdateTime(weuser);
			}
			c.setUserid(weuser);
			c = coachService.saveCoach(c);
			
			//添加补贴
//			String money = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_PROPORTION,"0.01");//取系统配置值  教练补贴比例
//			String coach_type = systemConfigService.getConfigValueByKey(ConfigKey.COACH_SUBSIDY_TYPE,"1");//取系统配置值  教练补贴类型
//			Subsidies subsidies = new Subsidies();
//			subsidies.setMoney(Float.valueOf(money));
//			subsidies.setZhe_id(c.getId());
//			subsidies.setZhe_type(Constants.SUBSIDIES_COACH);
//			subsidies.setType(Integer.valueOf(coach_type));
//			subsidies.setEnd_time(CommonController.getCurrYearLast());
//			subsidiesService.saveSubsidies(subsidies);
			
			
			
			if (weuser.getId() > 0) {
				logger.info("注册成功！");
				
				//新增教练带人		edit by lxc	2015-12-11	不管什么教练,添加,也需要默认新增
//				if(c.getCoachType()== 2){
					String p = String.valueOf(c.getPrice());
					Coach_teach_person ct = new Coach_teach_person();
					ct.setCoach_id(c.getId());
					ct.setPrice(Float.parseFloat(p));
					coach_teach_personService.saveCoach_teach_person(ct);
//				}
			
				//工作时间
				for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
					Coach_set_time cst = new Coach_set_time();
					cst.setCoach_id(c);
					cst.setTime(i);
					cst.setTime_type(1);
					cst.setFlag(Constants.NORMAL_FLAG);
					coach_set_timeService.saveCoachSetTime(cst);
				}
				//节假时间
				for (int i =  Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
					Coach_set_time cst = new Coach_set_time();
					cst.setCoach_id(c);
					cst.setTime(i);
					cst.setTime_type(2);
					cst.setFlag(Constants.NORMAL_FLAG);
					coach_set_timeService.saveCoachSetTime(cst);
				}
				application.removeAttribute(Settings.PHONECODE_APPLICATION+uphone);
				logger.info("加载页面时长"+Constants.logInfoSer+(System.currentTimeMillis()-s_long)+"毫秒");
				
				
				//S	用户刚注册好,需要弹出消息,告诉用户注册步骤		edit by lxc	2016-02-19 
				StationMessage s = new StationMessage();
				s.setContent("第一步实名认证;\r\n第二步教学设置;\r\n第三步日程表的预约收费;\r\n请尽快完善,方可被预约.");
				s.setTitle("被预约步骤");
				s.setWeuser_id(weuser.getId());
				s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
				stationMessageService.saveStationMessage(s);
				//E
				
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "注册成功",
						weuser);
			} else {
				return new BusinessResponse(
						Constants.ResponseCode.ERROR_MSG.getType(), "注册失败", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ste : e.getStackTrace()) {
				logger.error(ste.toString());
			}
			logger.error("");

			return new BusinessResponse(
					Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),
					Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
	
}	









