package com.project.task;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import com.project.common.Constants;
import com.project.pojo.Coach;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.User_count;
import com.project.pojo.Weuser;
import com.project.service.ICoachService;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.ISystemConfigService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;


public class OrderTask {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderTask.class);
	
    @Resource
    private IOrderInfoService orderInfoService;
    @Resource
    private ICoachService orderMainService;
    @Resource
    private ISystemConfigService systemConfigService;
    @Resource
    private IWeuserService weuserService;
    @Resource
    private IUser_countService user_countService;
    @Resource
    private IOrderMainService mainOrderService;
    @Resource
    private ICoachService coachService;
    
    
    /**
     * 已支付的订单的改成进行中状态
     */
	public void execute(){
		logger.info("已支付的订单的改成进行中状态....");
		
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int hour=calendar.get(Calendar.HOUR_OF_DAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(calendar.getTime());
		
		List<Orderinfo> orderInfolist=orderInfoService.getOrderInfoByTime(dateStr, String.valueOf(hour),Constants.OrderStatus.PAY_PAID.getStatus());
		
		List<String> list=new ArrayList<String>();
		
		for(Orderinfo o:orderInfolist){
				o.setStatus(Constants.OrderStatus.EXECUTION_ING.getStatus());
				try {
					orderInfoService.mergeOrderInfo(o);
					
					
					
					list.add(o.getOrderMainNo());
					logger.info("子订单编号为:"+o.getOrderSubNo()+"的状态改为"+Constants.OrderStatus.EXECUTION_ING.getMsg());
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		
		//去除重复值
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
		      if  (list.get(j).equals(list.get(i)) ) { 
		        list.remove(j); 
		      } 
		    } 
		}
		
		boolean flag=false;
		
		for(String orderMainNo:list){
			List<Orderinfo> oiList=orderInfoService.getOrderInfoListByOrderMainId(orderMainNo);
			
			for(Orderinfo orderInfo:oiList){
				//当所有的子订单中有一个执行中的,主订单状态改执行中(包含周期预定)
				if(orderInfo.getStatus() == Constants.OrderStatus.EXECUTION_ING.getStatus()){
					flag=true;
					break;
				}
			}
			
			if(flag){
				OrderMain om= mainOrderService.getOrderMainByNO(orderMainNo);
				om.setPayStatus(Constants.OrderStatus.EXECUTION_ING.getStatus());
				logger.info("主订单编号为:"+om.getOrderMainNo()+"的状态改为"+Constants.OrderStatus.EXECUTION_ING.getMsg());
				try {
					mainOrderService.updateOrderMain(om);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将执行中的订单状态改成已完成
	 */
	public void executeing(){
		logger.info("将执行中的订单状态改成已完成....");
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int hour=calendar.get(Calendar.HOUR_OF_DAY)-1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(calendar.getTime());
		
		List<Orderinfo> orderInfolist=orderInfoService.getOrderInfoByTime(dateStr, String.valueOf(hour),Constants.OrderStatus.EXECUTION_ING.getStatus());
		
		List<String> list=new ArrayList<String>();
		
		for(Orderinfo o:orderInfolist){
				o.setStatus(Constants.OrderStatus.BASE_FINISH.getStatus());
				list.add(o.getOrderMainNo());
				try {
					if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						//教练累计课时++
						Weuser userCoach = coachService.getcoachById(Integer.valueOf(o.getActiveID())).getUserid();
						User_count ucCoach=user_countService.getUser_countByUserId(userCoach.getId());
						ucCoach.setLessonCount(ucCoach.getLessonCount()+1);
						user_countService.updateUser_count(ucCoach);
						
						//用户训练课时++
						Weuser user = weuserService.getUserById(o.getWeuser().getId());
						User_count count=user_countService.getUser_countByUserId(user.getId());
						count.setUserlessonCount(count.getUserlessonCount()+1);
						user_countService.updateUser_count(count);
					}
					
					User_count uc=user_countService.getUser_countByUserId(o.getWeuser().getId());
					uc.setReservecount(uc.getReservecount()+1);
					uc.setPlaytimecount(uc.getPlaytimecount()+1);
					user_countService.updateUser_count(uc);
					
					orderInfoService.mergeOrderInfo(o);
					
					//教练的学员人数++
					List<Orderinfo> olist = orderInfoService.getOrderinfoByObj(o);
					if(olist!=null){
						if(olist.size()==1){
							int coachId = Integer.valueOf(o.getActiveID());   //教练id
							Coach coach = coachService.getcoachById(coachId);
							User_count user_count = user_countService.getUser_countByUserId(coach.getUserid().getId());
							user_count.setTraineecount(user_count.getTraineecount()+1);
							user_countService.updateUser_count(user_count);
						}
					}
					
					
					List<Orderinfo> oneorder = orderInfoService.getOrderinfoListByToday(o.getWeuser().getId());
					//用户训练次数+1
					Weuser user = weuserService.getUserById(o.getWeuser().getId());
					if(oneorder.size()==1){
						logger.info("用户加次数....");
						User_count ucCoach=user_countService.getUser_countByUserId(user.getId());
						ucCoach.setTrainingtimes(ucCoach.getTrainingtimes()+1);
						user_countService.updateUser_count(ucCoach);
					}
					if(o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
						List<Orderinfo> oneorder_coach = orderInfoService.getOrderinfoListByTodayactiveID(o.getActiveID());
						Weuser userCoach = coachService.getcoachById(Integer.valueOf(o.getActiveID())).getUserid();
						if(userCoach!=null){
							if(oneorder_coach.size()==1){
								logger.info("教练加次数....");
								User_count ucCoach=user_countService.getUser_countByUserId(userCoach.getId());
								ucCoach.setSubjectCount(ucCoach.getSubjectCount()+1);
								user_countService.updateUser_count(ucCoach);
							}
						}
					}
					
					logger.info("子订单编号为:"+o.getOrderSubNo()+"的状态改为"+Constants.OrderStatus.BASE_FINISH.getMsg());
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		boolean flag=true;
		
		for(String orderMainNo:list){
			List<Orderinfo> oiList=orderInfoService.getOrderInfoListByOrderMainId(orderMainNo);
			
			for(Orderinfo orderInfo:oiList){
				//当所有的子订单中有一个未完成的,主订单状态不完成(包含周期预定)
				if(orderInfo.getStatus() != Constants.OrderStatus.BASE_FINISH.getStatus()){
					logger.info("子订单编号为:"+orderInfo.getOrderSubNo()+"的状态不等于"+Constants.OrderStatus.BASE_FINISH.getMsg());
					flag=false;
					break;
				}
			}
			
			if(flag){
				OrderMain om= mainOrderService.getOrderMainByNO(orderMainNo);
				om.setPayStatus(Constants.OrderStatus.BASE_FINISH.getStatus());
				
				//s  lzy
				//主订单如果是教练订单类型  则累计课时次数+1
				if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
//					Weuser userCoach = coachService.getcoachById(Integer.valueOf(om.getActiveID())).getUserid();
//					if(userCoach!=null){
//						User_count ucCoach=user_countService.getUser_countByUserId(userCoach.getId());
//						ucCoach.setSubjectCount(ucCoach.getSubjectCount()+1);
//						user_countService.updateUser_count(ucCoach);
//					}
					
//					//用户训练次数+1
//					Weuser user = weuserService.getUserById(om.getWeuser().getId());
//					if(user!=null){
//						User_count ucCoach=user_countService.getUser_countByUserId(user.getId());
//						ucCoach.setTrainingtimes(ucCoach.getTrainingtimes()+1);
//						user_countService.updateUser_count(ucCoach);
//					}
				}
				//e
				
				try {
					mainOrderService.updateOrderMain(om);
					logger.info("主订单编号为:"+om.getOrderMainNo()+"的状态改为"+Constants.OrderStatus.BASE_FINISH.getMsg());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
