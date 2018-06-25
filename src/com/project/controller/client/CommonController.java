package com.project.controller.client;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Coach;
import com.project.pojo.Comment;
import com.project.pojo.Dictionaries;
import com.project.pojo.Events;
import com.project.pojo.Notice;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Playground;
import com.project.pojo.StationMessage;
import com.project.pojo.Suggestion;
import com.project.pojo.TerraceMessage;
import com.project.service.ICoachService;
import com.project.service.ICommentService;
import com.project.service.IDictionariesService;
import com.project.service.IEventsService;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IPropagandaService;
import com.project.service.IStationMessageService;
import com.project.service.ISuggestionService;
import com.project.service.ISystemConfigService;
import com.project.service.ITerraceMessageService;
import com.project.util.CommonUtil;


@Controller("clientcommonController")
@RequestMapping(value="/")
public class CommonController  extends BaseController {

	
	@Resource
	private ISuggestionService suggestionService;
	@Resource
	private IStationMessageService stationMessageService;
	@Resource
	private ISystemConfigService  systemConfigService;
	@Resource
	private IDictionariesService dictionariesService;
	@Resource
	private IPropagandaService propagandaService;
	@Resource
	private ITerraceMessageService terraceMessageService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IOrderMainService orderMainService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private ICommentService commentService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IEventsService eventsService; 
	/**
	 * 跳转关于金地
	 * @return
	 */
	@RequestMapping(value = "toAboutGolden")
	public String toAboutGolden(HttpServletRequest request,ModelMap map,String version){
		map.put("version", version);
		map.put("mobile", CommonUtil.JudgeIsMoblie(request));
		return "/phone/user/about";
	}
	
	@RequestMapping("tonitice")
	public String tonitice(ModelMap map,HttpServletRequest request,int nid){
		Notice notice = propagandaService.getNoticeById(nid);
		map.put("notice",notice);
		return "/phone/user/notice_detail";
	}
	
	/**
	 * 问题反馈
	 * @param request
	 * @param phone
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "suggestion.do")
	@ResponseBody
	public  Object saveSuggestion(HttpServletRequest request,String phone,String content){
		Suggestion s = null;
		if(CommonUtil.NotEmpty(phone)&&CommonUtil.NotEmpty(content)){
			s = new Suggestion();
			s.setPhone(phone);
			s.setDetailcontent(content);
			s = suggestionService.saveSuggestion(s);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.ERROR_MSG.getType(), "失败,请填写手机号和反馈信息", "");
		}
		if(s!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
	
	/***
	 * 得到本周的周一和周天
	 * @return
	 */
	public static Map getWeekDay() {
		  Map<String,String> map = new HashMap<String,String>();
		  Calendar cal =Calendar.getInstance();
		  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		  cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		  map.put("mon", df1.format(cal.getTime()));
		  //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		  cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		  //增加一个星期，才是我们中国人理解的本周日的日期
		  cal.add(Calendar.WEEK_OF_YEAR, 1);
		  map.put("sun", df.format(cal.getTime()));
		  return map;
	}
	
	/***
	 * 得到输入周的周一和周天
	 * 输入1为本周     输入0为 上周  推。。。。。
	 * @return
	 */
	public static Map getWeekDayTime(int s) {
		  Map<String,String> map = new HashMap<String,String>();
	
		  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		  Calendar cal1 =Calendar.getInstance();
		  cal1.add(Calendar.WEEK_OF_YEAR, s-1);
		  cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		  map.put("mon", df1.format(cal1.getTime()));
		  //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		  
		  Calendar cal2 =Calendar.getInstance();
		  cal2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		  //增加一个星期，才是我们中国人理解的本周日的日期
		  cal2.add(Calendar.WEEK_OF_YEAR, s);
		  map.put("sun", df.format(cal2.getTime()));
		  return map;
	}
	
	
	/**
	 * 得到本月的第一天和最后一天
	 * @return
	 */
	public static Map getMonthDate(){
		  Map<String,String> map = new HashMap<String,String>();
		  // 获取Calendar 
		  Calendar calendar = Calendar.getInstance();
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); 
		   DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59"); 
		  // 设置时间,当前时间不用设置 
		  // calendar.setTime(new Date()); 
		   calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		   map.put("monthF", format.format(calendar.getTime()));
		 // 设置日期为本月最大日期 
		   calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
		 // 打印 
		   map.put("monthL", format1.format(calendar.getTime()));
		   return map;
	}
	
	/**
	 * 输入月份 1
	 * 1为当前月   （比如当前月 为12月  那-1为 11月一直推下去）
	 * 得到月份的第一天和最后一天
	 * @param month
	 * @return
	 */
	public static Map getMonthTime(int month){
		Map<String,String> map = new HashMap<String,String>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); 
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59"); 
		//获取前月的第一天
        Calendar  cal_1 = Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, month - 1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(cal_1.getTime());
        map.put("firstDay", firstDay);
        
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();   
        cale.add(Calendar.MONTH, month);
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
        String lastDay = format1.format(cale.getTime());
        map.put("lastDay", lastDay);
        return map;
	}
	
	
	public static Map getMonthTimeByYear(int year,int month){
		Map<String,String> map = new HashMap<String,String>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
		//获取前月的第一天
        Calendar  cal_1 = Calendar.getInstance();//获取当前日期 
        cal_1.set(Calendar.YEAR, year);//设置年
        cal_1.set(Calendar.MONTH, month-1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(cal_1.getTime());
        map.put("firstDay", firstDay);
        
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.YEAR, year);//设置年
        cale.set(Calendar.MONTH, month);
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为0号,则为最后一天
        String lastDay = format1.format(cale.getTime());
        map.put("lastDay", lastDay);
        return map;
	}
	
	/**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }
	
	/**
     * 获取当年的最后一天
     * @param year
     * @return
     */
    public static String getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        return format.format(getYearLast(currentYear));
    }
	
    /**
     * 获取输入日期的前一天 
     * @param specifiedDay
     * @return
     * @throws ParseException
     */
    public static String getSpecifiedDayBefore(String specifiedDay,int i) throws ParseException{  
        Calendar c = Calendar.getInstance();  
        Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);  
        c.setTime(date);  
        int day=c.get(Calendar.DATE);  
        c.set(Calendar.DATE,day-1);  
        String dayBefore="";
        if(i == 1){
        	dayBefore=new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(c.getTime()); 
        }else{
        	dayBefore=new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime()); 
        }
        return dayBefore;  
    }  
      
    /**
     * 获取输入日期的后一天 
     * @param specifiedDay
     * @return
     * @throws ParseException
     */
    public static String getSpecifiedDayAfter(String specifiedDay,int i) throws ParseException{  
        Calendar c = Calendar.getInstance();  
        Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);  
        c.setTime(date);  
        int day=c.get(Calendar.DATE);  
        c.set(Calendar.DATE,day+1);  
        String dayAfter="";
        if(i == 1){
        	dayAfter=new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(c.getTime()); 
        }else{
        	dayAfter=new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime()); 
        }
        return dayAfter;  
    }  
    
    
	/**
	 * 微信注册页面
	 * @return
	 */
	@RequestMapping(value = "towecharReg")
	public String towecharReg(ModelMap map){
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		return "/phone/user/reg";
	}
	
	/**
	 * 微信登录页面
	 * @return
	 */
	@RequestMapping(value = "towecharLogin")
	public String towecharLogin(ModelMap map){
		map.put("getVersion", getVersion());			///防止页面js,css缓存,获取版本号			add by lxc	2015-11-18
		return "/phone/user/login";
	}

	
	/**
	 * 保存推送消息
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "saveMessage")
	@ResponseBody
	public Object saveMessage(HttpServletRequest request,int userId,String title,String content,String summary){
		StationMessage stationMessage=new StationMessage();
		
		stationMessage.setWeuser_id(userId);
		stationMessage.setTitle(title);
		stationMessage.setContent(content);
		stationMessage.setSummary(summary);
		
		TerraceMessage t=new TerraceMessage();
		
		t.setTitle(title);
		t.setContent(content);
		t.setSummary(summary);
		t.setStatus(1);
		
		try {
			stationMessageService.saveStationMessage(stationMessage);
			terraceMessageService.saveTerraceMessage(t);
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "失败", "失败");
		}
	}
	
	/**
	 * 消息
	 * @param request
	 * @param userId
	 *  @param appsyetem		安卓手机:android		苹果手机:ios
	 * @return
	 */
	@RequestMapping(value = "message")
	@ResponseBody
	public Object message(HttpServletRequest request,int userId,@RequestParam(defaultValue="1")int pageNumber,String appsyetem){
		try {
			PageFinder<StationMessage> list = stationMessageService.getPageStationMessageByUserId(userId, pageNumber, 10,1,appsyetem);
			if(list.getDataList()!=null){
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
			}else{
				return new BusinessResponse(
						Constants.OperationResult.NO_DATA_NOW.getStatus(), "暂无数据", 29);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "失败", "失败");
		}
	}
	
	/**
	 * 获得最新一条消息
	 * @param request
	 * @param userId		用户ID
	 * @param appsyetem		安卓手机:android		苹果手机:ios
	 * @return
	 */
	@RequestMapping(value = "getOneMessage")
	@ResponseBody
	public Object getOneMessage(HttpServletRequest request,int userId,String appsyetem){
		try {
			if(stationMessageService.getOneStationMessageByWeuserId(userId,appsyetem)!=null){
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", stationMessageService.getOneStationMessageByWeuserId(userId,appsyetem));
			}else{
				return new BusinessResponse(
						Constants.OperationResult.NO_MORE_DATA.getStatus(), OperationResult.NO_MORE_DATA.getMsg(), "");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), OperationResult.SYSTEM_ERROR.getMsg(), "");
		}
	}
	
	
	/**
	 * 修改消息状态为已读
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_message")
	@ResponseBody
	public Object updateMessage(HttpServletRequest request,int id){
		StationMessage s = stationMessageService.getStationMessageById(id);
		s.setReadstatus(Constants.MessageReadStatus.HAVE_READ.getStatus());
		try {
			stationMessageService.updateStationMessage(s);
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SYSTEM_ERROR.getStatus(), "失败", "失败");
		}
		
	}
	
	/**
	 * 更新版本
	 * @param request
	 * @param version
	 * @param versionNum
	 * @return
	 */
	@RequestMapping(value = "updatedVersion")
	@ResponseBody
	public Object updatedVersion(HttpServletRequest request,String version,String versionNum){
		String value = systemConfigService.getConfigValueByKey(version);
		double verNum = Double.valueOf(versionNum);
		double getVerNum = 0.0;
		if(CommonUtil.NotEmpty(value)){
			getVerNum = Double.valueOf(value);
		}
		if(verNum >= getVerNum){
			return new BusinessResponse(
					Constants.OperationResult.NO_DATA_NOW.getStatus(), "您的已经是最新版本了", "");
		}else{
			if(version.equals(ConfigKey.VIEWVERSIONFORANDROIDDRIVER)){
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", 
						systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDCOACHVERSIONURL));
			}else if(version.equals(ConfigKey.VIEWVERSIONFORANDROIDUSER)){
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", 
						systemConfigService.getConfigValueByKey(ConfigKey.ANDROIDUSERVERSIONURL));
			}else{
				return new BusinessResponse(
						Constants.OperationResult.SUCCESS.getStatus(), "成功", "");
			}
		}
	}
	
	/**
	 * 服务 列表数据
	 * @param request
	 * @param type 1表示教练 
	 * @return
	 */
	@RequestMapping(value = "getService")
	@ResponseBody
	public Object service(HttpServletRequest request,int type){
		List<Dictionaries> list = dictionariesService.getDictionaries(type);
		if(list!=null){
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", list);
		}else{
			return new BusinessResponse(
					Constants.ResponseCode.EMPTY_DATA.getType(), "失败", "");
		}
	}
	
	/**
	 *评价分数设置
	 */
	@RequestMapping(value = "score")
	@ResponseBody
	public Object score(HttpServletRequest request,String orderMainNo){
		try {
			OrderMain om = orderMainService.getOrderMainByNO(orderMainNo);
			
			Playground playground=null;
			Coach coach=null;
			
			if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
				playground=playgroundService.getPlaygroundById(Integer.valueOf(om.getActiveID()));
				
				for(Orderinfo oi:orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo())){
					if(oi.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						coach=coachService.getcoachById(Integer.valueOf(oi.getActiveID()));
						break;
					}
				}
			}
			
			if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
				coach=coachService.getcoachById(Integer.valueOf(om.getActiveID()));
				
				if(coach.getCoachType()==Constants.COACHTYPE.INNERCOACH.getStatus()){
					playground=playgroundService.getPlaygroundById(Integer.valueOf(coach.getPlayground_id()));
				}
				
				if(coach.getCoachType()==Constants.COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
					playground=playgroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(coach.getId()).getPhone()).getId());
					
				}
			}
			
			if(playground!=null){
				Double s=0.0;
				Comment comment=new Comment();
				comment.setActiveID(String.valueOf(playground.getId()));
				comment.setOrderType(Constants.DATATYPE.PlaygroundType.getStatus());
				comment.setWeuser(om.getWeuser());
				comment.setOrderMainNo(om.getOrderMainNo());
				comment.setEvaluate_score(request.getParameter("playgroundScore"));
				comment.setCommentcontent(request.getParameter("playgroundContent"));
				
				commentService.saveComment(comment);
				
				List<Comment> commentList = commentService.getComment(playground.getId(), Constants.DATATYPE.PlaygroundType.getStatus());
				
				for(Comment c:commentList){
					if(!"".equals(c.getEvaluate_score()) && c.getEvaluate_score()!=null){
						s+=Double.valueOf(c.getEvaluate_score());
					}
				}
				
				s=s/(playground.getScoreCount()+1);
				
				DecimalFormat   df   =new   java.text.DecimalFormat("#0.0"); 
				String Manager_money_str = df.format(s);	
				
				playground.setEvaluate_score(Manager_money_str.toString());
				playground.setScoreCount(playground.getScoreCount()+1);
				
				
				playgroundService.mergePlayground(playground);
			}
			
			if(coach!=null){
				Double s=0.0;
				Comment comment=new Comment();
				comment.setActiveID(String.valueOf(coach.getId()));
				comment.setOrderType(Constants.DATATYPE.CoachType.getStatus());
				comment.setWeuser(om.getWeuser());
				comment.setOrderMainNo(om.getOrderMainNo());
				comment.setEvaluate_score(request.getParameter("coachScore"));
				comment.setCommentcontent(request.getParameter("coachContent"));
				
				commentService.saveComment(comment);
				
				List<Comment> commentList = commentService.getComment(coach.getId(), Constants.DATATYPE.CoachType.getStatus());
				
				for(Comment c:commentList){
					if(!"".equals(c.getEvaluate_score()) && c.getEvaluate_score()!=null){
						s+=Double.valueOf(c.getEvaluate_score());
					}
				}
				
				s=s/(coach.getScoreCount()+1);
				
				DecimalFormat   df   =new   java.text.DecimalFormat("#0.0"); 
				String Manager_money_str = df.format(s);	
				coach.setEvaluate_score(Manager_money_str);
				coach.setScoreCount(coach.getScoreCount()+1);
				
				coachService.mergePlayground(coach);
			}
			
			
			om.setPayStatus(Constants.OrderStatus.COMMENTED.getStatus());
			
			for(Orderinfo oi:orderInfoService.getOrderInfoListByOrderMainId(orderMainNo)){
				oi.setStatus(Constants.OrderStatus.COMMENTED.getStatus());
				orderInfoService.mergeOrderInfo(oi);
			}
			orderMainService.updateOrderMain(om);
			
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "成功", "评分成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(
					Constants.OperationResult.SUCCESS.getStatus(), "失败", "评分失败");
		}
	}
	
	/**
	 * 保存崩溃日志
	 * @param multipartRequest
	 * @param apptype		用户端:user			教练端:coach
	 * @param appsyetem		安卓手机:android		苹果手机:ios
	 * @param errordata		文件名参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveErrorData")
	public Object saveErrorData(MultipartHttpServletRequest multipartRequest,String apptype,String appsyetem){
		logger.info("保存崩溃日志....");
		String date = CommonUtil.getTimeNows();
		String photoPath = Settings.IMAGE_SAVE_FILE_NAME + "/errordata/"+apptype+"/"+appsyetem+"/";
		String errerdataName = date+"_"+String.valueOf(System.currentTimeMillis())+".txt";		//文件名
		MultipartFile files = multipartRequest.getFile("errordata");// 获取前台传来的参数
		uploadFile(files, multipartRequest, photoPath+errerdataName, "");
		return new BusinessResponse(
				OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(), "成功");
	}
	
	
	/***
	 * 判断日程表有无设置
	 * @param coachId	教练ID
	 * @return	返回status=0 ,不提示设置日程表,		其他提示
	 */
	@ResponseBody
	@RequestMapping(value = "getCoachpriceisZero")
	public Object getCoachpriceisZero(String coachId){
		logger.info("判断日程表有无设置....");
		Coach c = coachService.getcoachById(Integer.valueOf(coachId));
		if(c!=null && COACHTYPE.FREECOACH.getStatus() == c.getCoachType() && COACHTYPE.CARRIEROPERATORCOACH.getStatus() == c.getCoachType()){
			if(c.getMoney()==0){
				return new BusinessResponse(
						OperationResult.SETDATETABLE.getStatus(), OperationResult.SETDATETABLE.getMsg(), "");
			}
		}
		return new BusinessResponse(
				OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(), "成功");
	}
	
	/**
	 * 去赛事报名页面
	 * @return
	 */
	@RequestMapping(value = "toEventRegistration")
	public String toEventRegistration(HttpServletRequest request,ModelMap map){
		map.put("e", eventsService.getEvents());
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "getEvent")
	public Object getEvent(int eventId){	
		Events e = eventsService.getEventsById(eventId);
		if(e!=null){
			return new BusinessResponse(
					OperationResult.SUCCESS.getStatus(), OperationResult.SUCCESS.getMsg(), e);
		}else{
			return new BusinessResponse(
					1, "赛事不存在", "是吧");
		}
	}
}
