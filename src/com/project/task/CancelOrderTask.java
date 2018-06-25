package com.project.task;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import com.project.common.Constants;
import com.project.service.IOrderMainService;

public class CancelOrderTask extends TimerTask{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CancelOrderTask.class);
	@Resource
	private IOrderMainService orderMainService;
	
	private String orderMaiNo;
	
	private Timer timer;
	
	public CancelOrderTask(String orderMainNo,Timer timer,IOrderMainService orderMainService){
		this.orderMaiNo=orderMainNo;
		this.timer=timer;
		this.orderMainService=orderMainService;
	}
	
	@Override
	public void run() {
		try {
			
			if(orderMainService.getOrderMainByNO(orderMaiNo).getPayStatus()==Constants.OrderStatus.PAY_STAY_PAID.getStatus()){
				
				orderMainService.cancelOrderMain(orderMaiNo);
				logger.info("订单编号:"+orderMaiNo+"已被作废.....");
				
			}
			timer.cancel();
			logger.info("调用timer.cancel()   .....");
		} catch (Exception e) {
			timer.cancel();
			e.printStackTrace();
		}
	}

}
