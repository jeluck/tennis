package com.project.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.project.common.Constants;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.User_count;
import com.project.pojo.Weuser;
import com.project.service.ICoachService;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Service
public class StartAddDataListener implements ApplicationListener<ContextRefreshedEvent>{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(StartAddDataListener.class);
	
	@Resource
	private IOrderMainService orderMainService;
	@Resource
	private IOrderInfoService orderInfoService;
    @Resource
    private IWeuserService weuserService;
    @Resource
    private IUser_countService user_countService;
    @Resource
    private ICoachService coachService;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
	    {  
			try {
	        	
	        	List<OrderMain> omList = orderMainService.getOrderMainListBy_payStatus(Constants.OrderStatus.PAY_STAY_PAID.getStatus());
	        	
	        	for(OrderMain o:omList){
	        		orderMainService.cancelOrderMain(o.getOrderMainNo());
	        		logger.info("订单编号:"+o.getOrderMainNo()+"已被作废.....");
	        	}
	        	int i = 1;   //i==1主订单改为完成
	        	List<OrderMain> omList1 = orderMainService.getOrderMainListByCondition();
	        	logger.info("主订单:...."+omList1);
	        	if(omList1 != null){
	        		logger.info("主订单:...."+omList1.size());
	        		for(OrderMain o:omList1){
	        			i = 1;
	        			List<Orderinfo> oInfo =  orderInfoService.getOrderInfoListByOrderMainId(o.getOrderMainNo());
	        			logger.info("子订单:...."+oInfo);
	        			if(oInfo != null){
	        				logger.info("子订单:...."+oInfo.size());
	        				for (Orderinfo oi : oInfo) {
								String time = oi.getCreate_time()+" "+oi.getToday_time()+":00:00";
								DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date date1 = format1.parse(time);  	//子订单打球时间
								Date date2 = new Date();			//当前时间
								if (date2.getTime() > date1.getTime()) {  //如果当前时间大于子订单时间那么子订单修改为完成
									oi.setStatus(Constants.OrderStatus.BASE_FINISH.getStatus());
									orderInfoService.mergeOrderInfo(oi);
									logger.info("子订单编号:"+oi.getOrderSubNo()+"已被修改为完成.....");
									
//									if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
//										//教练累计课时++
//										Weuser userCoach = coachService.getcoachById(Integer.valueOf(o.getActiveID())).getUserid();
//										User_count ucCoach=user_countService.getUser_countByUserId(userCoach.getId());
//										ucCoach.setLessonCount(ucCoach.getLessonCount()+1);
//										user_countService.updateUser_count(ucCoach);
//										
//										//用户训练课时++
//										Weuser user = weuserService.getUserById(o.getWeuser().getId());
//										User_count count=user_countService.getUser_countByUserId(user.getId());
//										count.setUserlessonCount(count.getUserlessonCount()+1);
//										user_countService.updateUser_count(count);
//									}
									
								}else{ 
									i = 0;
								}
							}
	        			}
	        			if(i == 1){
	        				o.setPayStatus(Constants.OrderStatus.BASE_FINISH.getStatus());
	        				orderMainService.updateOrderMain(o);
	        				logger.info("主订单编号:"+o.getOrderMainNo()+"已被修改为完成.....");
	        			}
		        	}
	        	}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("取消订单出现错误"+e);
			}
	    }  
	}

}
