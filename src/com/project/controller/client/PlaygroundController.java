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
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.model.CalculationChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.common.FinalList;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Business_time;
import com.project.pojo.Coach;
import com.project.pojo.DateAssist;
import com.project.pojo.LoveCollection;
import com.project.pojo.Playground;
import com.project.pojo.Region;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.service.IActivityService;
import com.project.service.IBusiness_timeService;
import com.project.service.ICoachService;
import com.project.service.ICommentService;
import com.project.service.ILoveCollectionService;
import com.project.service.IPeripheral_servicesService;
import com.project.service.IPlaygroundImgService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;
import com.project.util.CommonUtil;
import com.project.util.LatitudeUtil;

@Controller("clentplaygroundController")
@RequestMapping("/")
public class PlaygroundController extends BaseController{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygroundController.class); 
	@Resource
	private IRegionService regionService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IPlaygroundImgService playgroundImgService;
	@Resource
	private IPeripheral_servicesService peripheral_servicesService;
	@Resource
	private IBusiness_timeService business_timeService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private ICommentService commentService;
	@Resource
	private IActivityService activityService;
	
	@Resource
	private ILoveCollectionService loveCollectionService; 
	@Resource
	private ISpaceService sapceService;
	@Resource
	private ICoachService coachService;
	
	
	/**
	 * 场馆数据
	 * @param request
	 * @param cityName
	 * @return
	 */
	@RequestMapping("playgroundData")
	@ResponseBody
	public Object playgroundData(HttpServletRequest request,String cityName,
									String areaid,String type,String services,String sort,
									@RequestParam(defaultValue="1")String pageNumber,
									String lat,String lng,String pdName){
		int city_show_id = 0;
		CommonUtil.printHTTP(request);
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
		paraMap.put("type", type);
		paraMap.put("services", services);
		paraMap.put("sort", sort);
		paraMap.put("lat", lat);
		paraMap.put("lng", lng);
		paraMap.put("pdName", pdName);
		PageFinder<Playground> playgroundList = playgroundService.getPlaygroundListByMap(paraMap,Integer.valueOf(pageNumber),Constants.FOREGROUND_PAGESIZE);	//场馆
		List<Playground> dataList = new ArrayList<Playground>();
		if(playgroundList != null && playgroundList.getDataList()!=null && playgroundList.getDataList().size()>0){
			for (Playground po : playgroundList.getDataList()) {
				
				//距离
				if(CommonUtil.NotEmpty(lat) && CommonUtil.NotEmpty(lng)){
					double distance = LatitudeUtil.Distance(Double.valueOf(lng.toString()),Double.valueOf(lat.toString()), po.getCoordinateslongitude(), po.getCoordinateslatitude());
					String s1 = String.valueOf(distance);
					String s2 = s1.substring(0,s1.indexOf("."));
					int i = Integer.valueOf(s2); 
					po.setDistanceMeters(i);
				}else{
					po.setDistanceMeters(0);
				}
				
				//活动
				Activity activity = activityService.getActivityByPdId(po.getId());
				if(activity!=null){
					po.setHuodong(activity.getTitle());
				}else{
					po.setHuodong("");
				}
				dataList.add(po);
			}
		}
		
		if(dataList!=null  && dataList.size()>0 ){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", dataList);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
		
	}
	
	/**
	 * 按城市名称的全部场馆数据
	 * @param cityName
	 * @return
	 */
	@RequestMapping("playgroundCoordinateList")
	@ResponseBody
	public Object playgroundData(String cityName){
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
		Playground o = new Playground();
		o.setCityid(city_show_id);
		o.setAuditStatus(Constants.AUDITSTATUS_THROUGH);
		List<Playground> list = playgroundService.getPlaygroundListByObj(o);
		if(list!=null&&list.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
		
	
	}
	
	
	
	
	/**
	 * 场馆搜索  ：全城,周边服务,球场类型,排序
	 * @param request
	 * @param cityName
	 * @return
	 */
	@RequestMapping("venueSearchData")
	@ResponseBody
	public Object venue_search(HttpServletRequest request,String cityName){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Area", FinalList.getArea(cityName, regionService));
		map.put("CourtType", FinalList.getCourt());
		map.put("Services", FinalList.getPlaygroundServices());
		map.put("Sort", FinalList.getPlaygroundSort());
		if(map.size()>0){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", map);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "没有数据", "");
		}
		
	}
	
	/***
	 * 跳到场馆详情页
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "playgrounddetail")
	public String playgrounddetail(ModelMap map, HttpServletRequest request,Integer playgroundId,Integer userId) {
		long s = System.currentTimeMillis();		//开始计时
		
		Playground p=playgroundService.getPlaygroundById(playgroundId);

		//如果是教练端,需要判断当前教练能否预定场馆		edit by nantian	2016-01-06
		String apptype=request.getParameter("apptype");
		if(userId!=null){
			Coach coach=coachService.getCoachByUserId(userId);
			if("coach".equals(apptype)){
				if(coach!=null){
					if(coach.getIs_reserve() == Constants.IS_RESERVE0){
						map.put("notReserve", "notReserve");
					}
				}
			}//E
		}
		
		
		
		if(p.getAddress()!=null){
			if(p.getAddress().length()>=10){
				p.setAddress(p.getAddress().substring(0, 10)+"...");
			}
		}
		
		
		map.put("playground", p);
		map.put("img", playgroundImgService.getPlaygoundImgListByPdId(playgroundId));
		
	
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
			
			da.setWeek(getWeek(calendar.get(Calendar.DAY_OF_WEEK)));
			da.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));
			if(calendar.get(Calendar.DATE)<10){
				da.setDay("0"+String.valueOf(calendar.get(Calendar.DATE)));
			}else{
				da.setDay(String.valueOf(calendar.get(Calendar.DATE)));
			}
			da.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			list.add(da);
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  
		Calendar c = new GregorianCalendar();
		
		//两个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 2);
		String two=f.format(c.getTime());
		
		//三个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 3);
		String three=f.format(c.getTime());
		
		//六个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 6);
		String six=f.format(c.getTime());
		
		//十二个月的时间
		c.setTime(new Date());
		c.add(Calendar.MONTH, 12);
		String twelve=f.format(c.getTime());
		
		Map<String,String> cyMap=new HashMap<String,String>();
		
		cyMap.put("近2个月", two);
		cyMap.put("3个月", three);
		cyMap.put("6个月", six);
		cyMap.put("1年", twelve);
		
		map.put("type", 1);
		map.put("cyMap", cyMap);
		map.put("userId", userId);
		map.put("dateList", list);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		map.put("stratTime", format.format(new Date()));
		map.put("comment", commentService.getComment(playgroundId, Constants.DATATYPE.PlaygroundType.getStatus()));
		
		int score[]=new int[5];
		if(!"".equals(playgroundService.getPlaygroundById(playgroundId).getEvaluate_score())){
			for(int i=0;i<score.length;i++){
				double sc=Double.valueOf(playgroundService.getPlaygroundById(playgroundId).getEvaluate_score());
				int scr=(int)sc;
				if(i<scr){
					score[i]=1;
				}
			}
		}
		map.put("activeId", p.getId());
		map.put("score", score);
		
		//经纬度计算，出现异常时显示未知
		try {
			String lng = request.getParameter("lng");
			String lat = request.getParameter("lat");
			if(CommonUtil.NotEmpty(lng) && CommonUtil.NotEmpty(lat)){
				double in=LatitudeUtil.Distance(Double.valueOf(lng), Double.valueOf(lat), p.getCoordinateslongitude(), p.getCoordinateslatitude());
				int interval=(int) in;
				if(interval<1000){
					map.put("interval",interval+"m");
				}else{
					map.put("interval",(interval/1000)+"km");
				}
			}else{
				map.put("interval","未知");
				logger.info("经纬度参数错误....");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("interval","未知");
		}
		
		
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		if(userId!=null){
			//查看是否收藏
			LoveCollection l=loveCollectionService.getLcByUserAndActive(userId, playgroundId, Constants.DATATYPE.PlaygroundType.getStatus());
			if(l!=null){
				map.put("love", l);
			}
		}
		
		
		logger.info("跳到场馆详情页_加载页面时长——————"+(System.currentTimeMillis()-s));
		return "/phone/user/venuesView";
	}
	
	/***
	 * 跳到场馆详情页
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "playgrounddetailshare")
	public String playgrounddetailshaer(ModelMap map, HttpServletRequest request,Integer playgroundId) {
		map.put("playground", playgroundService.getPlaygroundById(playgroundId));
		map.put("img", playgroundImgService.getPlaygoundImgListByPdId(playgroundId));
		
	
		
		map.put("comment", commentService.getComment(playgroundId, Constants.DATATYPE.PlaygroundType.getStatus()));
		
		int score[]=new int[5];
		for(int i=0;i<score.length;i++){
			if(i<Integer.valueOf(playgroundService.getPlaygroundById(playgroundId).getEvaluate_score())){
				score[i]=1;
			}
		}
		map.put("activeId", playgroundService.getPlaygroundById(playgroundId).getId());
		map.put("score", score);
		
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		
		
		return "/phone/user/venuesViewShare";
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
	
	//数据整合，同事获取场地时间场地和场地时间价格表的数据
	@RequestMapping("timepoint")
	@ResponseBody
	public Object getTimepoint(HttpServletRequest request,Integer playgroundId,Integer belong,String date){
		try{
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			
			//由于生成场地时间价格表时,数据的月份带有01,02,03.等格式,但这里传进来的参数没有带0,因此格式化一下.	edit by lxc 2015-12-31
			try{
				Date datetemp = format.parse(date);
				date = format.format(datetemp);
			}catch(Exception e){
			}//E
			
			//获取时间段数据
			Business_time btime=null;
			List<Integer> tList=new ArrayList<Integer>();
			
			Integer status=0;
			
			
			
			Calendar calendar = new GregorianCalendar();
			try {
				calendar.setTime(format.parse(date));
				
				if(getWeek(calendar.get(Calendar.DAY_OF_WEEK)).equals("星期天") || getWeek(calendar.get(Calendar.DAY_OF_WEEK)).equals("星期六")){
					status=2;
				}else{
					status=1;
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			btime=business_timeService.findByplaygroundId(playgroundId,status);
			
			for(int i=btime.getStart_time();i<=btime.getEnd_time();i++){
				tList.add(i);
			}
			
			Map map=new HashMap();
			
			//将时间数据放入到map中
			map.put("timepoint", tList);
			
			List<Space> sList= sapceService.getPlaygroundSpaceBy_PGid(playgroundId, 0);
			
			Date d=new Date();
			
			for(int i=0;i<sList.size();i++){
				List<Space_time_price> list = space_time_priceService.findByDate(sList.get(i).getId(), date);
				
				for(int j=0;j<list.size();j++){
					
					if(format.format(d).equals(list.get(j).getDate())){
						//今天的日期
						Calendar today = new GregorianCalendar();
						
						
						today.setTime(d);
						
						
						if(today.get(Calendar.HOUR_OF_DAY)>=list.get(j).getTime()){
							if(list.get(j).getIs_reserve()!=1){
								list.get(j).setIs_reserve(2);
							}
						}
					}
				}
				
				sList.get(i).setStpList(list);
			}
			
			
			//对应的场地放入到map中
			map.put("spaceList", sList);
			
	        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), map);
		}catch(Exception e){
			e.printStackTrace();
			return new BusinessResponse(Constants.OperationResult.SYSTEM_ERROR.getStatus(), Constants.OperationResult.SYSTEM_ERROR.getMsg(), "失败");
		}
	}
	
	@RequestMapping("timepointafter")
	@ResponseBody
	public Object getTimepoint(HttpServletRequest request,Integer playgroundId,Integer status){
		//获取时间段数据
		Business_time btime=null;
		List<Integer> tList=new ArrayList<Integer>();
		if(status==1){
			btime=business_timeService.findByplaygroundId(playgroundId).get(0);
		}else{
			btime=business_timeService.findByplaygroundId(playgroundId).get(1);
		}
		
		for(int i=btime.getStart_time();i<=btime.getEnd_time();i++){
			tList.add(i);
		}
        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), tList);
	}
	
	/**
	 * 根据城市编号查询场馆
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "playgroundByCity")
    @ResponseBody
    public Object getCity(HttpServletRequest request){
		 String cityid = request.getParameter("city");
		 List<Playground> list = playgroundService.getPlaygroundList(Integer.valueOf(cityid), 0,null);
		 return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
    }
}
