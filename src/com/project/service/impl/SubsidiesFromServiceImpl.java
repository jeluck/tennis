package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants;
import com.project.common.Constants.DATATYPE;
import com.project.common.Constants.OrderStatus;
import com.project.pojo.Coach;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Playground;
import com.project.pojo.Space;
import com.project.pojo.Subsidies;
import com.project.pojo.vo.OrderVo;
import com.project.pojo.vo.SubsidiesFrom;
import com.project.service.ICoachService;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundService;
import com.project.service.ISpaceService;
import com.project.service.ISubsidiesFromService;
import com.project.service.ISubsidiesService;
import com.project.util.CommonUtil;

@Service
public class SubsidiesFromServiceImpl implements ISubsidiesFromService {

	@Resource
	private IOrderMainService orderMainService;
	@Resource
	private ISubsidiesService subsidiesService;
	@Resource
	private IPlaygroundService playgroundService;
	@Resource
	private ICoachService coachService;
	@Resource
	private ISpaceService spaceService;
	@Resource
	private IOrderInfoService orderInfoService;
	
	@Override
	public List<OrderMain> getOrderMainListByOrderMainVo(OrderVo o) {
		return orderMainService.getOrderMainListByOrderMainVo(o);
	}

	@Override
	public List<SubsidiesFrom> getSubsidiesFrom(OrderVo o) {
		List<SubsidiesFrom> list = new ArrayList<SubsidiesFrom>();
		List<Orderinfo> ois = getOrderinfoListByOrderVo(o);
		SubsidiesFrom  s = null;	//结算表
		Subsidies sub = null;		//补贴记录
		if (ois!=null && ois.size()>0) {
			for (Orderinfo oi : ois) {
				Playground p = null;		//场馆
				String playground = "";			//场馆名称
				Coach c = null;				//教练 
				String coach = "";			//教练名称
				Space space_t = null;				//场地
				String space = "";			//场地名称
				String status_str = "";		//订单状态说明
				s = new SubsidiesFrom();
				if (oi.getOrderType()==DATATYPE.PlaygroundType.getStatus()) {//场馆类型
					sub = subsidiesService.getSubsidiesByPdId(Integer.parseInt(oi.getActiveID()),2);
					p = playgroundService.getPlaygroundById(Integer.parseInt(oi.getActiveID()));
					playground = p.getName();
					s.setOrderType(DATATYPE.PlaygroundType.getMsg());//订单类型
				} else if(oi.getOrderType()==DATATYPE.CoachType.getStatus()){//教练类型
					sub = subsidiesService.getSubsidiesByPdId(Integer.parseInt(oi.getActiveID()),1);
					c = coachService.getcoachById(Integer.parseInt(oi.getActiveID()));
					coach = c.getName();
					s.setOrderType(DATATYPE.CoachType.getMsg());//订单类型
				}
				if(oi.getSpaceId()!=null && oi.getSpaceId()!=0){
					space_t = spaceService.getSpace(oi.getSpaceId());
					space = space_t.getName();
				}
			
				String play_date = oi.getCreate_date();		//打球日期
				String play_time = oi.getToday_time();
				s.setCoach(coach);
				s.setOrder_time(oi.getCreat_order_time());
				s.setOrderMainNo(oi.getOrderMainNo());
				s.setOrderSubNo(oi.getOrderSubNo());
				s.setPlay_date(play_date);
				s.setPlay_time(play_time+"点");
				s.setPlayground(playground);
				s.setSpace(space);
				
				if(OrderStatus.EXECUTION_ING.getStatus()==oi.getStatus()){
					status_str = OrderStatus.EXECUTION_ING.getMsg();
	        	}else if(OrderStatus.BASE_FINISH.getStatus()==oi.getStatus()){
	        		status_str = OrderStatus.BASE_FINISH.getMsg();
	        	}else if(OrderStatus.COMMENTED.getStatus()==oi.getStatus()){
	        		status_str = OrderStatus.COMMENTED.getMsg();
	        	}
				
				s.setStatus(oi.getStatus());							//订单状态
				s.setStatus_str(status_str);
				s.setTotal_amount(oi.getOrderPayTotalAmont());						//订单总金额
				s.setSubsidies_grant_status(oi.getSubsidies_grant_status());	//补贴发放状态
				s.setSubsidies_grant_time(oi.getSubsidies_grant_time());		//补贴发放时间
				if(sub!=null){
					java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
					String subsidies_money = df.format(s.getTotal_amount() * sub.getMoney());
					s.setSubsidies_money(Double.parseDouble(subsidies_money));					//补贴额
					
					String subsidies_proportion = df.format(sub.getMoney());
					s.setSubsidies_proportion(Double.parseDouble(subsidies_proportion));		//补贴比例
				}
				
				s.setTrade_balance_status(oi.getTrade_balance_status());			//交易结算状态
				s.setTrade_balance_time(oi.getTrade_balance_time());				//交易结算时间
				s.setUser(oi.getWeuser());
				
				list.add(s);
			}
		}
		return list;
	}

	@Override
	public List<Orderinfo> getOrderinfoListByOrderVo(OrderVo o) {
		return orderInfoService.getOrderinfoListByOrderVo(o);
	}

	@Override
	@Transactional
	public Orderinfo editTrade_balance_status(String orderSubNo) {
		Orderinfo o = orderInfoService.getOrderByorderSubNo(orderSubNo);
		o.setTrade_balance_status(Constants.OKTODO);
		o.setTrade_balance_time(CommonUtil.getTimeNow());
		try {
			orderInfoService.mergeOrderInfo(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Orderinfo editSubsidies_grant_status(String orderSubNo) {
		Orderinfo o = orderInfoService.getOrderByorderSubNo(orderSubNo);
		o.setSubsidies_grant_status(Constants.OKTODO);
		o.setSubsidies_grant_time(CommonUtil.getTimeNow());
		try {
			orderInfoService.mergeOrderInfo(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
