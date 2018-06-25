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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.ClientLoginAuth;
import com.project.common.Constants;
import com.project.common.Constants.COACHTYPE;
import com.project.controller.BaseController;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Business_time;
import com.project.pojo.Coach;
import com.project.pojo.CoachReserve;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Schedule;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.service.IBusiness_timeService;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IScheduleService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;

@Controller("Coach_set_timeWebController")
@RequestMapping(value="/")
public class Coach_set_timeController extends BaseController{

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private ISpaceService spaceService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private IScheduleService scheduleService;
	@Resource
	private ICoachReserveService coachReserveService;
	@Resource
	private ICoachService coachService;
	@Resource
	private IBusiness_timeService business_timeService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IPlaygroundService PlaygroundService;
	
	/**
	 * 保存时间
	 * @param request
	 * @param cst
	 * @return
	 */
	@ClientLoginAuth
	@RequestMapping(value = "save_cst" )
	public Object saveCoach_set_time(HttpServletRequest request,Integer minTime,Integer maxTime,Integer coachId){
		try {
			for(int i=minTime;i<=maxTime;i++){
				coach_set_timeService.saveCoach_set_time(i,coachId);
			}
			logger.info("保存教练预约时间数据.....");
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
	
	@RequestMapping(value = "cst_list" )
	@ResponseBody
	public Object getCoach_set_timeList(HttpServletRequest request,Integer coachId,Integer timeType){
		try {
			if(timeType==null){
				timeType=0;
			}
			List<Coach_set_time> cstList = coach_set_timeService.getCoach_set_timeByCoachId(coachId,timeType,0);
			logger.info("获得教练预约时间数据.....");
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(),cstList);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(Constants.OperationResult.UNKNOWN_MISTAKE.getStatus(),Constants.OperationResult.UNKNOWN_MISTAKE.getMsg(), "");
		}
	}
	
	/**
	 * 修改时间信息
	 * @param request
	 * @param cst
	 * @return
	 */
	@ClientLoginAuth
	@RequestMapping(value = "update_cst" )
	public String updateCoach_set_time(HttpServletRequest request,Integer id){
		try {
//			coach_set_timeService.updateCoach_set_time(cst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除信息，根据ID删除
	 * @param request
	 * @param id
	 * @return
	 */
	@ClientLoginAuth 
	@RequestMapping(value = "delete_cst" )
	public String deleteCoach_set_time(HttpServletRequest request,int id){
		try {
			coach_set_timeService.deleteById(id);
			logger.info("根据ID删除教练预约时间数据.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param coachId	教练ID
	 * @param date		日期
	 * @param coachType 教练类型
	 * @param belong	所属
	 * @return
	 */
	@RequestMapping("ctimepoint")
	@ResponseBody
	public Object getCoachTime(HttpServletRequest request,Integer coachId,String date,Integer coachType,Integer belong){
		try {
			Map map=new HashMap();
			
			Integer status=0;
			
			//根据前台传递过来的日期进行判断是工作日还是节假日
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			
			
			//由于生成场地时间价格表时,数据的月份带有01,02,03.等格式,但这里传进来的参数没有带0,因此格式化一下.	edit by lxc 2015-12-31
			try{
				Date datetemp = format.parse(date);
				date = format.format(datetemp);
			}catch(Exception e){
			}//E

			
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
			
			List<Coach_set_time> c= coach_set_timeService.getCoach_set_timeByCoachId(coachId, status,coachType);
			
			//当教练为运行商的时候进行的数据获取
			if(coachType==COACHTYPE.CARRIEROPERATORCOACH.getStatus()){
				
				Integer playgroundId=PlaygroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(coachId).getPhone()).getId()).getId();
				
				//获取到该教练所有的场地数据	根据教练ID找场馆主在找场馆
				List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(PlaygroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(coachId).getPhone()).getId()).getId(), 0);
				
				Date d=new Date();
				
				Coach coach=coachService.getcoachById(coachId);
				
				String service=coach.getServices();
				
				for(int i=0;i<spaceList.size();i++){
					
					//根据场地ID获取场地时间价格数据
					List<Space_time_price> list = space_time_priceService.findByDate(spaceList.get(i).getId(), date);
					
					for(int j=0;j<list.size();j++){
						
						if(format.format(d).equals(list.get(j).getDate())){
							//今天的日期
							Calendar today = new GregorianCalendar();
							
							
							today.setTime(d);
							
							//给予数据时判断今天的这个点是否还能预订，过来改时间点用户将不能预订
							if(today.get(Calendar.HOUR_OF_DAY)>=list.get(j).getTime()){
								if(list.get(j).getIs_reserve()!=1){
									list.get(j).setIs_reserve(2);
								}
							}
						}
						
						//edit by lxc	2015-12-31	
						//当教练在这个点被预订了，一条全部显示为灰色
						CoachReserve coachReserve=coachReserveService.getCoachReserve(list.get(j).getDate(), coachId, list.get(j).getTime());
						if(coachReserve!=null){
							if(list.get(j).getTime()==coachReserve.getTimepoint()){
								list.get(j).setIs_reserve(2);
							}
						}//E
					}
					
					if(service.contains("1")){
						for(int o=0;o<list.size();o++){
							list.get(o).setPrice(0);
						}
					}
					
					spaceList.get(i).setStpList(list);
				}
				
				
				//对应的场地放入到map中
				map.put("spaceList", spaceList);
				
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
				
				map.put("timepoint", tList);
			}
			if(coachType==COACHTYPE.FREECOACH.getStatus()){
				Date d=new Date();
				
				for(int i=0;i<c.size();i++){
					
					
					c.get(i).setDate(date);
					if(format.format(d).equals(date)){
						//今天的日期
						Calendar today = new GregorianCalendar();
						
						
						today.setTime(d);
						
						if(today.get(Calendar.HOUR_OF_DAY)>=c.get(i).getTime()){
						
							c.get(i).setFlag(0);
						
						}
					}
					
				}
				
				
				for(int i=0;i<c.size();i++){
					Schedule s=scheduleService.getScheduleAll(c.get(i).getTime(), getWeek(calendar.get(Calendar.DAY_OF_WEEK)), coachId, 2);
					CoachReserve cr=coachReserveService.getCoachReserve(date, coachId, c.get(i).getTime());
					
					if(s!=null || cr!=null){
						c.get(i).setFlag(0);
					}
				}
				
				
				map.put("timepoint", c);
			}
			
			List<Coach_set_time> cstList = coach_set_timeService.getCoach_set_timeByCoachId(coachId, status, coachType);
			
			if(coachType==COACHTYPE.INNERCOACH.getStatus()){
				Business_time btime=business_timeService.findByplaygroundId(coachService.getcoachById(coachId).getPlayground_id(),status);
				
				//获取到该教练所有的场地数据
				List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(coachService.getcoachById(coachId).getPlayground_id(), belong);
				
				Date d=new Date();
				
				for(int i=0;i<spaceList.size();i++){
					
					//根据场地ID获取场地时间价格数据
					List<Space_time_price> list = space_time_priceService.findByDate(spaceList.get(i).getId(), date);
					
					for(int j=0;j<list.size();j++){
						
						if(format.format(d).equals(list.get(j).getDate())){
							//今天的日期
							Calendar today = new GregorianCalendar();
							
							
							today.setTime(d);
							
							//给予数据时判断今天的这个点是否还能预订，过来改时间点用户将不能预订
							if(today.get(Calendar.HOUR_OF_DAY)>=list.get(j).getTime()){
								if(list.get(j).getIs_reserve()!=1){
									list.get(j).setIs_reserve(2);
								}
							}
							
						}
						
						
						
						//当驻场教练休息时，也全部显示为灰色,并且修改场地时间点在前台的价格显示，场地价格+教练价格
						for(Coach_set_time cst: cstList){
							if(cst.getSpace_id()==0 && cst.getTime()==list.get(j).getTime()){
								list.get(j).setIs_reserve(2);
							}
							if(cst.getSpace_id()==1 && cst.getTime()==list.get(j).getTime()){
								list.get(j).setPrice(list.get(j).getPrice()+cst.getPrice());
							}
						}
						
						//当驻场教练在这个点被预订了，一条全部显示为灰色
						CoachReserve coachReserve=coachReserveService.getCoachReserve(list.get(j).getDate(), coachId, list.get(j).getTime());
						if(coachReserve!=null){
							if(list.get(j).getTime()==coachReserve.getTimepoint()){
								list.get(j).setIs_reserve(2);
							}
						}
					}
					
					spaceList.get(i).setStpList(list);
				}
				
//				for(int i=0;i<spaceList.size();i++){
//					for(int j=0;j<spaceList.get(i).getStpList().size();j++){
//						if(){
//							for(int m=0;m<spaceList.get(i).getStpList().size();m++){
//								spaceList.get(i).getStpList().get(m).setIs_reserve(2);
//							}
//						}
//					}
//				}
				
				
				//对应的场地放入到map中
				map.put("spaceList", spaceList);
				
				List<Integer> intList=new ArrayList<Integer>();
				
				for(int i=btime.getStart_time();i<=btime.getEnd_time();i++){
					intList.add(i);
				}
				
				map.put("timepoint", intList);
			}
			
			
			
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(Constants.OperationResult.SYSTEM_ERROR.getStatus(), "系统异常", "");
		}
	}
	
	@RequestMapping("freetimepoint")
	@ResponseBody
	public Object getCoachTimeFree(HttpServletRequest request,Integer coachId,String date,Integer coachType,Integer belong){
		try {
			Map map=new HashMap();
		
			Integer status=0;
			
			//根据前台传递过来的日期进行判断是工作日还是节假日
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			
			//由于生成场地时间价格表时,数据的月份带有01,02,03.等格式,但这里传进来的参数没有带0,因此格式化一下.	edit by lxc 2015-12-31
			try{
				Date datetemp = format.parse(date);
				date = format.format(datetemp);
			}catch(Exception e){
			}//E
			
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
			List<Coach_set_time> c= coach_set_timeService.getCoach_set_timeByCoachId(coachId, status,coachType);
			
			
			
			Date d=new Date();
			
			
			for(int i=0;i<c.size();i++){
				Schedule s=scheduleService.getScheduleAll(c.get(i).getTime(), getWeek(calendar.get(Calendar.DAY_OF_WEEK)), coachId, belong);
				CoachReserve cr=coachReserveService.getCoachReserve(date, coachId, c.get(i).getTime());
				
				if(s==null && cr==null){
					c.get(i).setStatus(1);
				}else if(s!=null){
					c.get(i).setStatus(0);
				}else if(cr!=null){
					c.get(i).setStatus(2);
					c.get(i).setOrderInfoNo(cr.getOrderSubNo());
				}
			}
			
			
			
			
			
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), c);
		} catch (Exception e) {
			e.printStackTrace();
			return new BusinessResponse(Constants.OperationResult.SYSTEM_ERROR.getStatus(), "系统异常", "");
		}
	}
	
	
	@RequestMapping("ctimepointafter")
	@ResponseBody
	public Object getCoachTimeAfter(HttpServletRequest request,Integer coachId,Integer status,Integer coachType){
			
			List<Coach_set_time> c= coach_set_timeService.getCoach_set_timeByCoachId(coachId, status,coachType);
			
			return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), c);
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
}
