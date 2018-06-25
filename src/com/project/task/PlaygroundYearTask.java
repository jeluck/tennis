package com.project.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.project.common.Constants;
import com.project.pojo.Coach;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.StationMessage;
import com.project.service.ICoachService;
import com.project.service.ICoach_set_timeService;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IPlaygroundService;
import com.project.service.IStationMessageService;

public class PlaygroundYearTask {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygroundYearTask.class);
	
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private IStationMessageService stationMessageService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService;
	@Resource
	private ICoachService coachService;
	@Resource
	private ICoach_set_timeService coach_set_timeService;
	
	
	public void execute(){
		logger.info("进行年审");
		
		List<Playground> playgroundList = playgroundService.getAll();
		
		for(Playground playground:playgroundList){
			if(!"".equals(playground.getEffective_time()) && playground.getEffective_time()!=null){
				int result = 0;
				int dayResult=0;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();

				try {
					c1.setTime(sdf.parse(playground.getEffective_time()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				c2.setTime(new Date());

				result = c1.get(Calendar.MONDAY) - c2.get(Calendar.MONTH);
				
				dayResult=c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
				//月份相差值小于3的时候
				if(result<=3 && result>=0){
					StationMessage stationMessage=new StationMessage();
					
					stationMessage.setContent("您的账号即将到期，请联系U橙客服！");
					stationMessage.setTitle("年审提醒");
					stationMessage.setWeuser_id(playground.getPlaygroundmanager_id());
					stationMessage.setSend_type(2);
					
					try {
						stationMessageService.saveStationMessage(stationMessage);
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("年审出现异常");
					}
				}
				
				if(result==0 && dayResult==0){
					playground.setIs_locked(1);
					playground.setAuditStatus(3);
					playground.setReturn_reason(Constants.RETURN_REASON_TIME_OUT);
					try {
						playgroundService.mergePlayground(playground);
						
						
						PlaygroundManager pm = playgroundManagerService.getPlaygroundManagerById(playground.getPlaygroundmanager_id());
						
						boolean flag=true;
						
						for(Playground p:playgroundService.getPlaygroundListByPlaygroundManagerId(pm.getId())){
							if(p.getIs_locked()==1 && p.getAuditStatus()==3 && Constants.RETURN_REASON_TIME_OUT.equals(p.getReturn_reason())){
								flag=false;
							}
						}
						
						
						if(flag){
							pm.setIs_active(0);
							playgroundManagerService.mergePlaygroundManager(pm);
							
							//当锁定场馆管理者账号后,则教练改为不可用户预约	edit by lxc	2016-01-08
							Coach coach = coachService.getcoachById(pm.getCoachid());
							if(coach!=null){
								coach.setReserve_me(Constants.IS_RESERVE0);
								coachService.mergePlayground(coach);
							}//E
							
							coachL(coachService.getcoachByphone(pm.getMobile()).getId());
							
							for(Coach c:coachService.getCoachByPm(pm.getId())){
								coachL(c.getId());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void coachL(Integer coachId){
		Coach c=coachService.getcoachById(coachId);
		
		c.setCoachType(Constants.COACHTYPE.FREECOACH.getStatus());
		c.setPlayground_id(0);
		c.setPlaygroundmanager_id(0);
		
		try {
			coachService.mergePlayground(c);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if(coach_set_timeService.getCoach_set_timeByCoachId(coachId)==null){
			//工作时间
			for (int i = Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
				Coach_set_time cst = new Coach_set_time();
				cst.setCoach_id(c);
				cst.setTime(i);
				cst.setTime_type(1);
				cst.setFlag(Constants.NORMAL_FLAG);
				try {
					coach_set_timeService.saveCoachSetTime(cst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//节假时间
			for (int i =  Constants.COACH_STARTTEACHTIME; i < Constants.COACH_ENDTEACHTIME; i++) {
				Coach_set_time cst = new Coach_set_time();
				cst.setCoach_id(c);
				cst.setTime(i);
				cst.setTime_type(2);
				cst.setFlag(Constants.NORMAL_FLAG);
				try {
					coach_set_timeService.saveCoachSetTime(cst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
