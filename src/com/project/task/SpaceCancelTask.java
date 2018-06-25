package com.project.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import com.project.common.Constants;
import com.project.pojo.Coach;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Space;
import com.project.pojo.Space_time_price;
import com.project.pojo.TimeMoeny;
import com.project.service.ICoach_set_timeService;
import com.project.service.ICycleService;
import com.project.service.IScheduleService;
import com.project.service.ISpaceService;
import com.project.service.ISpace_time_priceService;
import com.project.service.ITimeMoenyService;

public class SpaceCancelTask {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SpaceCancelTask.class);
	
	@Resource
	private ISpaceService spaceService;
	@Resource
	private ITimeMoenyService timeMoenyService;
	@Resource
	private ISpace_time_priceService space_time_priceService;
	@Resource
	private ICycleService cycleService;
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	@Resource
	private IScheduleService scheduleService;
	
	public void saveTime(){
		logger.info("生成场地时间...."+Constants.logInfoSer);long s_long = System.currentTimeMillis();
		List<Space> spaceList=spaceService.getAll();
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,7);//把日期往后增加一天.整数往后推,负数往前移动
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(calendar.getTime());
		for(Space space:spaceList){
			if(space.getBelongto()==0){
				if(week==1 || week==7){
					for(TimeMoeny t:timeMoenyService.getTimeMoenyBySpaceId(space.getId())){
						if(t.getTime_type()==2){
							Space_time_price s=new Space_time_price();
							s.setDate(dateStr);
							s.setPrice(t.getPrice());
							s.setTime(t.getHour());
							s.setSpace_id(space);
							s.setMust_coach(t.getHelp_filed());
							s.setDateType(t.getTime_type());
							if(cycleService.getAll(s)){
								s.setIs_reserve(Constants.IS_RESERVE1);
							}
							if(scheduleService.getScheduleAll(t.getHour(), getWeek(week), space.getId(), 1)!=null){
								s.setIs_reserve(2);
							}
							try {
								space_time_priceService.saveSpace_time_price(s);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}else{
					for(TimeMoeny t:timeMoenyService.getTimeMoenyBySpaceId(space.getId())){
						if(t.getTime_type()==1){
							Space_time_price s=new Space_time_price();
							s.setDate(dateStr);
							s.setPrice(t.getPrice());
							s.setTime(t.getHour());
							s.setMust_coach(t.getHelp_filed());
							s.setSpace_id(space);
							s.setDateType(t.getTime_type());
							if(cycleService.getAll(s)){
								s.setIs_reserve(Constants.IS_RESERVE1);
							}
							if(scheduleService.getScheduleAll(t.getHour(), getWeek(week), space.getId(), 1)!=null){
								s.setIs_reserve(2);
							}
							try {
								space_time_priceService.saveSpace_time_price(s);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			if(space.getBelongto()==1){
				if(week==1 || week==7){
					for(Coach_set_time t:coach_set_timeService.getCoach_set_timeByCoachId(space.getCoach_id().getId(),0,0)){
						if(t.getTime_type()==2){
							Space_time_price s=new Space_time_price();
							s.setDate(dateStr);
							s.setPrice(t.getPrice());
							s.setTime(t.getTime());
							s.setSpace_id(space);
							s.setMust_coach(Constants.MUST_COACH1);
							s.setDateType(t.getTime_type());
							if(cycleService.getAll(s)){
								s.setIs_reserve(Constants.IS_RESERVE1);
							}
							if(scheduleService.getScheduleAll(t.getTime(), getWeek(week), space.getId(), 1)!=null){
								s.setIs_reserve(2);
							}
							try {
								space_time_priceService.saveSpace_time_price(s);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}else{
					for(Coach_set_time t:coach_set_timeService.getCoach_set_timeByCoachId(space.getCoach_id().getId(),0,0)){
						if(t.getTime_type()==1){
							Space_time_price s=new Space_time_price();
							s.setDate(dateStr);
							s.setPrice(t.getPrice());
							s.setTime(t.getTime());
							s.setMust_coach(Constants.MUST_COACH1);
							s.setSpace_id(space);
							s.setDateType(t.getTime_type());
							if(cycleService.getAll(s)){
								s.setIs_reserve(Constants.IS_RESERVE1);
							}
							if(scheduleService.getScheduleAll(t.getTime(), getWeek(week), space.getId(), 1)!=null){
								s.setIs_reserve(2);
							}
							try {
								space_time_priceService.saveSpace_time_price(s);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			
		}
		long s = System.currentTimeMillis()-s_long;
		logger.info("生成场地时间..."+Constants.logInfoSer+((System.currentTimeMillis()-s_long)/1000)+"秒");
		
	}
	
	public void deleteTime(){
		int t=2;
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-t);//把日期往后增加一天.整数往后推,负数往前移动
		
//		for(Space_time_price stp:space_time_priceService.findByDate(calendar)){
//			try {
//				space_time_priceService.deleteStp(stp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		
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
