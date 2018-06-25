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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Business_time;
import com.project.pojo.CoachReserve;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Region;
import com.project.pojo.Schedule;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.service.IBusiness_timeService;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.IOrderInfoService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IScheduleService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;

@Controller("clentspaceController")
@RequestMapping("/")
public class SpaceController {
	
	@Resource
	private ISpaceService spaceService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private ICoachService coachService;
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private IBusiness_timeService business_timeService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private ICoachReserveService coachReserveService;
	@Resource
	private IScheduleService scheduleService;
	
	@RequestMapping(value = "getspace")
	@ResponseBody
	public Object getSpace(ModelMap map, HttpServletRequest request,Integer activeId,Integer belong,String date){
		
		//获取到该教练所有的场地数据
		List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(activeId, belong);
		
		Date d=new Date();
		
		for(int i=0;i<spaceList.size();i++){
			
			//根据场地ID获取场地时间价格数据
			List<Space_time_price> list = space_time_priceService.findByDate(spaceList.get(i).getId(), date);
			
			
			
			spaceList.get(i).setStpList(list);
		}
		
        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), spaceList);
	}
	
	@RequestMapping(value = "getCoachspace")
	@ResponseBody
	public Object getCoachSpace(HttpServletRequest request,Integer activeId,String date){
		Map map=new HashMap();
		
		Integer timeType=0;
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
				timeType=2;
			}else{
				timeType=1;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer palygroundId=playgroundService.getByPlaygroundManagerId(playgroundManagerService.getPGM(coachService.getcoachById(activeId).getPhone()).getId()).getId();
		
		//获取到该教练所有的场地数据
		List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(palygroundId, 0);
		
		List<Coach_set_time> cstList = coach_set_timeService.getCoach_set_timeByCoachId(activeId,timeType,0);
		
		
		for(int i=0;i<spaceList.size();i++){
			
			//根据场地ID获取场地时间价格数据
			List<Space_time_price> list = space_time_priceService.findByDate(spaceList.get(i).getId(), date);
			
			//后台进行时间筛选去除掉前台时间筛选判断		edit by lxc	2015-12-31
			for(int j=0;j<list.size();j++){
				for(int m=0;m<cstList.size();m++){
					if(list.get(j).getTime() == cstList.get(m).getTime()){
						//去教练预约表中找到数据
						CoachReserve cr = coachReserveService.getCoachReserve(date, activeId, cstList.get(m).getTime());
						
						
						
						//当数据不为空的时候进入设置
						if(cr!=null){
							//当订单中的场地ID==场地ID的时候则将这块场地设置为已被预约，否则被设置为未预约
							if(orderInfoService.getOrderByorderSubNo(cr.getOrderSubNo()).getSpaceId()==spaceList.get(i).getId()){
								list.get(j).setIs_reserve(1);
								cstList.get(m).setOrderInfoNo(cr.getOrderSubNo());
							}else{
								list.get(j).setIs_reserve(0);
							}
						}else{
							list.get(j).setIs_reserve(0);
						}
						
						Schedule s=scheduleService.getScheduleAll(list.get(j).getTime(), getWeek(calendar.get(Calendar.DAY_OF_WEEK)), activeId, 2);
						if(s!=null){
							list.get(j).setIs_reserve(2);
						}
						
						Schedule s1=scheduleService.getScheduleAll(list.get(j).getTime(), getWeek(calendar.get(Calendar.DAY_OF_WEEK)), list.get(j).getSpace_id().getId(), 1);
						
						if(s1!=null){
							list.get(j).setIs_reserve(2);
						}
					}
				}
			}//E
			
			spaceList.get(i).setStpList(list);
		}
		
		map.put("spaceList", spaceList);
		map.put("cstList", cstList);
		
		Business_time btime=null;
		List<Integer> tList=new ArrayList<Integer>();
		if(timeType==1){
			btime=business_timeService.findByplaygroundId(palygroundId).get(0);
		}else{
			btime=business_timeService.findByplaygroundId(palygroundId).get(1);
		}
		
		for(int i=btime.getStart_time();i<=btime.getEnd_time();i++){
			tList.add(i);
		}
		
		map.put("timepoint", tList);
		
        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), map);
	}
	
	@RequestMapping(value = "getin_space")
	@ResponseBody
	public Object getinSpace( HttpServletRequest request,Integer activeId,String date){
		
		Map map=new HashMap();
		
		Integer timeType=0;
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
				timeType=2;
			}else{
				timeType=1;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer playgroundId=coachService.getcoachById(activeId).getPlayground_id();
		
		//获取到该教练所有的场地数据
		List<Space> spaceList = spaceService.getPlaygroundSpaceBy_PGid(playgroundId, 0);
		
		//教练时间列表
		List<Coach_set_time> cstList = coach_set_timeService.getCoach_set_timeByCoachId(activeId,timeType,0);
		
		
		for(int i=0;i<spaceList.size();i++){
			
			//根据场地ID获取场地时间价格数据
			List<Space_time_price> list = space_time_priceService.findByDate(spaceList.get(i).getId(), date);
			
			//后台进行时间筛选去除掉前台时间筛选判断
			for(int j=0;j<list.size();j++){
				for(int m=0;m<cstList.size();m++){
					if(list.get(j).getTime() == cstList.get(m).getTime()){
						
						//去教练预约表中找到数据
						CoachReserve cr = coachReserveService.getCoachReserve(date, activeId, cstList.get(m).getTime());
						
						//当数据不为空的时候进入设置
						if(cr!=null){
							//当订单中的场地ID==场地ID的时候则将这块场地设置为已被预约，否则被设置为未预约
							if(orderInfoService.getOrderByorderSubNo(cr.getOrderSubNo()).getSpaceId()==spaceList.get(i).getId()){
								list.get(j).setIs_reserve(1);
								cstList.get(m).setOrderInfoNo(cr.getOrderSubNo());
							}else{
								list.get(j).setIs_reserve(0);
							}
						}else{
							if(list.get(j).getIs_reserve()!=2){
								list.get(j).setIs_reserve(0);
							}
						}
						
						if(cstList.get(m).getSpace_id()==0){
							list.get(j).setIs_reserve(2);
						}
						
					}
				}
			}
			
			spaceList.get(i).setStpList(list);
		}
		
		
		
		map.put("spaceList", spaceList);
		map.put("cstList", cstList);
		map.put("playgroundId", playgroundId);
		
		Business_time btime=null;
		List<Integer> tList=new ArrayList<Integer>();
		if(timeType==1){
			btime=business_timeService.findByplaygroundId(playgroundId).get(0);
		}else{
			btime=business_timeService.findByplaygroundId(playgroundId).get(1);
		}
		
		for(int i=btime.getStart_time();i<=btime.getEnd_time();i++){
			tList.add(i);
		}
		
		map.put("timepoint", tList);
		
        return new BusinessResponse(Constants.OperationResult.SUCCESS.getStatus(), Constants.OperationResult.SUCCESS.getMsg(), map);
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
