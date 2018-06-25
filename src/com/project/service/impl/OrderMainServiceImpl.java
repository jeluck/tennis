package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.ConfigKey;
import com.project.common.Constants;
import com.project.common.Constants.COACHTYPE;
import com.project.common.Constants.OrderStatus;
import com.project.common.OrderEnum.LogType;
import com.project.common.OrderEnum.MethodCode;
import com.project.controller.client.CommonController;
import com.project.dao.IOrderMainDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.CoachReserve;
import com.project.pojo.Cycle;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Playground;
import com.project.pojo.Space_time_price;
import com.project.pojo.Trade_recode;
import com.project.pojo.User_count;
import com.project.pojo.Weuser;
import com.project.pojo.vo.OrderVo;
import com.project.service.ICoachReserveService;
import com.project.service.ICoachService;
import com.project.service.ICycleService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundService;
import com.project.service.ISpace_time_priceService;
import com.project.service.ISubsidiesSettlementService;
import com.project.service.ISystemConfigService;
import com.project.service.ITrade_recodeService;
import com.project.service.IUser_countService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;
import com.project.util.SMSUtil;

@Service
public class OrderMainServiceImpl implements IOrderMainService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderMainServiceImpl.class);

    @Resource
    private IOrderMainDao orderMainDao;
    @Resource
    private ISystemConfigService systemConfigService;
    @Resource
    private IOrderInfoService orderInfoService;
    @Resource
    private IOrderComponent orderComponent;
    @Resource
    private IWeuserService weuserService;
    @Resource
	private ITrade_recodeService trade_recodeService;
    @Resource
    private IPlaygroundService playgroundService;
    @Resource
    private ICoachService coachService;
    @Resource
    private ISubsidiesSettlementService subsidiesSettlementService;
    @Resource
    private ISpace_time_priceService space_time_priceService;
    @Resource
    private ICoachReserveService coachReserveService;
    @Resource
    private ICycleService cycleService;
    @Resource
    private IUser_countService user_countService;
    
    @Transactional
	@Override
	public OrderMain saveOrderMain(OrderMain o) throws Exception {
		return orderMainDao.saveObject(o);
	}

    @Transactional
	@Override
	public OrderMain updateOrderMain(OrderMain o) throws Exception {
    	o.setUpdate_time(CommonUtil.getTimeNow());
		return orderMainDao.merge(o);
	}


	@Override
	public OrderMain getOrderMainByNO(String orderMainNo) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("orderMainNo", orderMainNo));
		OrderMain o = (OrderMain) criteria.uniqueResult();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}

	@Override
	public PageFinder<OrderMain> getOrderMainByWeuserId(Integer payStatus,int weuserId,int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", weuserId));
		if(payStatus!=null){
			
		}
		criteria.addOrder(Order.desc("id"));
		PageFinder<OrderMain> o = orderMainDao.pagedByCriteria(criteria, pageNumber, pageSize);
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(o.getDataList()!=null)
		for (OrderMain om : o.getDataList()) {
			List<Orderinfo> list = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
			Set set = new HashSet(list);//设置子订单信息
			om.setOrderSubs(set);
		}

		return o;
	}

	@Override
	public Integer getOrderMainByUserId_payStatus_size(int userId, int payStatus) {
		org.hibernate.Query query=null;
		org.hibernate.Session sessionTemp = orderMainDao.getHibernateSession();
		String sql = "SELECT count(*) FROM order_main om WHERE om.weuser_id ="+userId+" and om.PAY_STATUS="+payStatus;
		query= sessionTemp.createSQLQuery(sql);//.setParameter(0, userId).setParameter(1, payStatus);
		Object ob = query.uniqueResult();
		if(ob==null)ob=0;
		Integer count = Integer.parseInt(ob.toString());
		sessionTemp.close();			
		//end
		return count;
	}


	@Override
	public List<OrderMain> getOrderMainListBy_payStatus(int payStatus) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("payStatus", payStatus));
		List<OrderMain> os = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		return os;
	}
	
	@Override
	public List<OrderMain> getOrderMainListByCondition() {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.lt("initialDate", CommonUtil.getFormatYYYY_MM_dd_HH().format(new Date())));
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
		dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
		criteria.add(dis);  
		List<OrderMain> os = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(os!=null && os.size()>0){
			return os;
		}
		return null;
	}

	@Transactional
	@Override
	public synchronized  boolean pay(Integer mainId, Integer userId,Integer playgroundId,Integer coachId,Integer pay_type) {
		try {
			
			Weuser user=weuserService.getUserById(userId);
			OrderMain om=orderMainDao.getById(mainId);
			if(pay_type == Constants.PayType.BALANCE.getStatus()){  //余额支付
				user.setAmount(user.getAmount()-om.getTotalAmount());
				weuserService.updateUser(user);
			}
			
			//记录短信发送的教练ID
			Integer dcoachId=0;
			
			//完成订单时加场馆预订次数
			if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
				Playground p=playgroundService.getPlaygroundById(Integer.valueOf(om.getActiveID()));
				p.setAudCount(p.getAudCount()+1);
				playgroundService.mergePlayground(p);
			}else if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
				//增加预订次数
				User_count uc = user_countService.getUser_countByUserId(coachService.getcoachById(coachId).getUserid().getId());
				uc.setReservecount(uc.getReservecount()+1);
				user_countService.updateUser_count(uc);
				dcoachId=coachId;
			}
			
			om.setPayStatus(Constants.OrderStatus.PAY_PAID.getStatus());
			
			orderMainDao.merge(om);
			List<Orderinfo> olist = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
			
			for(Orderinfo o:olist){
				//当主订单是场馆类型,子订单为教练类型是(说明此订单由教练列表选择驻场教练下的单)		edit  by nantian	2015-12-22
				if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus() && o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
					//增加预订次数
					User_count uc = user_countService.getUser_countByUserId(coachService.getcoachById(Integer.valueOf(o.getActiveID())).getUserid().getId());
		    		uc.setReservecount(uc.getReservecount()+1);
		    		user_countService.updateUser_count(uc);
		    		dcoachId=Integer.valueOf(o.getActiveID());
				}
				//E
				o.setPayTime(CommonUtil.getTimeNow());
				o.setStatus(Constants.OrderStatus.PAY_PAID.getStatus());
				orderInfoService.mergeOrderInfo(o);
				orderComponent.genOrderLogForSave(o.getWeuser().getReal_name(),o.getOrderSubNo(), MethodCode.PAID_ORDER,LogType.ORDER_NORMAL_LOG,"订单" + o.getOrderSubNo() + "支付成功", true);
			}
//			orderComponent.savePayInfo_thirdPay_orderNo(om.getOrderMainNo(), om.getTotalAmount(), pay_type);
			Trade_recode t=new Trade_recode();
			t.setWeuser(new Weuser());
			t.getWeuser().setId(userId);
			t.setTradetype(Constants.PAY_TRADE_TYPE);
			t.setBalance(weuserService.getUserById(userId).getAmount());
			t.setMny_amount(om.getTotalAmount());
			t.setOrderMainNo(om.getOrderMainNo());
			trade_recodeService.saveTrade_recode(t);
			if(coachId!=0){
				Weuser cuser= coachService.getcoachById(coachId).getUserid();//教练的用户对象
				Coach c =coachService.getcoachById(coachId);//教练对象
				if(c.getCoachType()==COACHTYPE.CARRIEROPERATORCOACH.getStatus() || c.getCoachType() == COACHTYPE.FREECOACH.getStatus()){//运营商或者自由教练
					subsidiesSettlementService.saveSubsidiesSettlementByPdId(coachId, om.getTotalAmount(), om.getOrderMainNo(),1);
					cuser.setAmount(cuser.getAmount()+om.getTotalAmount());//给教练加钱
					Trade_recode ct=new Trade_recode();
					ct.setWeuser(cuser);
					ct.setTradetype(Constants.INCOME_TYPE);
					ct.setBalance(cuser.getAmount());
					ct.setMny_amount(om.getTotalAmount());
					ct.setOrderMainNo(om.getOrderMainNo());
					trade_recodeService.saveTrade_recode(ct);
					
					weuserService.updateUser(cuser);
				}
			} 
			
			if(dcoachId!=0){
				SMSUtil.sendSmsMsg(coachService.getcoachById(dcoachId).getPhone(), "有用户预订了您，请您及时登录APP查看信息");
			}
			
			Playground p= playgroundService.getPlaygroundById(playgroundId);
			if(p!=null){
				p.setProfit(p.getProfit()+om.getTotalAmount());
				subsidiesSettlementService.saveSubsidiesSettlementByPdId(playgroundId, om.getTotalAmount(), om.getOrderMainNo(),2);
				playgroundService.mergePlayground(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public OrderMain getById(Integer id) {
		return orderMainDao.getById(id);
	}

	@Override
	public PageFinder<OrderMain> getOrderMain(int type,OrderMain o,int weuserId,int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", weuserId));
		if(type == 1){
			CommonUtil.getTimeNow();
			Map<String,String> map = CommonController.getWeekDay();
			criteria.add(Restrictions.ge("createTime",map.get("mon")))
					.add(Restrictions.le("createTime",map.get("sun")));
		}
		if(type == 2){
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
			criteria.add(dis);  
		}
		if(type == 3){
			criteria.add(Restrictions.eq("payStatus", Constants.OrderStatus.BASE_FINISH.getStatus()));
		}
		if(o.getOrderType()>0){
			criteria.add(Restrictions.eq("orderType", o.getOrderType()));
		}
		criteria.addOrder(Order.desc("id"));
		PageFinder<OrderMain> o1 = orderMainDao.pagedByCriteria(criteria, pageNumber, pageSize);
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o1;
	}
	

	@Override
	public OrderMain cancelOrder(Integer orderMainId, Integer userId) {
		OrderMain om=orderMainDao.getById(orderMainId);
//		Weuser user = weuserService.getUserById(userId);
		
		om.setPayStatus(Constants.OrderStatus.DELETE.getStatus());
//		user.setAmount(user.getAmount()+om.getTotalAmount()+om.getPrePrice());
//		
//		weuserService.mergeAndUpdateTime(user);
		
		List<Orderinfo> list=orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
		List<Coach> mCoach=null;
		if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
			mCoach=coachService.getCoachBygroundAndType(Integer.valueOf(om.getActiveID()),  Constants.COACHTYPE.INNERCOACH.getStatus());
		}
		for(Cycle cycle:cycleService.getOrderMainNoCycle(om.getOrderMainNo())){
			cycleService.deleteByOrderMainNo(cycle);
		}
		
		for(Orderinfo o:list){
			if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
				o.setStatus(Constants.OrderStatus.DELETE.getStatus());
				try {
//					for(int j=0;j<mCoach.size();j++){
//						if(coachReserveService.getCoachReserve(o.getCreate_date(), , Integer.valueOf(o.getToday_time()))!=null){
//							coachReserveService.deleteCoachReserve(coachReserveService.getCoachReserve(o.getCreate_date(), Integer.valueOf(om.getActiveID()), Integer.valueOf(o.getToday_time())));
//							break;
//						}
//					}
					orderInfoService.mergeOrderInfo(o);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Space_time_price s=space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time()));
				
				s.setIs_reserve(0);
				space_time_priceService.updatespace_time_price(s);
			}
			if(om.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
				try {
					Space_time_price s=space_time_priceService.findByDate(o.getSpaceId(), o.getCreate_date(), Integer.valueOf(o.getToday_time()));
					
					s.setIs_reserve(0);
					space_time_priceService.updatespace_time_price(s);
					
					coachReserveService.deleteCoachReserve(coachReserveService.getCoachReserve(o.getCreate_date(), Integer.valueOf(om.getActiveID()), Integer.valueOf(o.getToday_time())));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			orderMainDao.merge(om);
//			if(om.getPayStatus()!=Constants.OrderStatus.PAY_STAY_PAID.getStatus()){
//				Trade_recode tr= trade_recodeService.getTrade_recodeByOrderMian(om.getOrderMainNo());
//				tr.setTradetype(Constants.REFUND);
//				trade_recodeService.updateTrade_recode(tr);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderMain> getOrderMainList(int type,  int weuserId) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", weuserId));
		if(type == 1){
			Map<String,String> map = CommonController.getWeekDay();
			criteria.add(Restrictions.ge("createTime",map.get("mon")))
					.add(Restrictions.le("createTime",map.get("sun")));
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_STAY_PAID.getStatus()));
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.BASE_FINISH.getStatus()));
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
			criteria.add(dis);  
		}
		if(type == 2){
			Disjunction dis=Restrictions.disjunction();  
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
			dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
			criteria.add(dis);  
		}
		if(type == 3){
			criteria.add(Restrictions.eq("payStatus", Constants.OrderStatus.BASE_FINISH.getStatus()));
		}
		criteria.addOrder(Order.desc("id"));
		List<OrderMain> list = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(list != null){
			return list;
		}else{
			return new ArrayList<OrderMain>();
		}
	}

	@Override
	public List<OrderMain> getOrderMainByStatus(int status, int weuserId) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", weuserId));
		if(status > 0 ){
			if(status == 3){
				Disjunction dis=Restrictions.disjunction();  
				dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
				dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
				criteria.add(dis); 
			}else{
				criteria.add(Restrictions.eq("payStatus", status));
			}
		}
		criteria.addOrder(Order.desc("id"));
		List<OrderMain> list = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public List<OrderMain> getOrderMainListByActiveID(int type, int activeId) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("orderType", Constants.DATATYPE.CoachType.getStatus()));
		criteria.add(Restrictions.eq("activeID", String.valueOf(activeId)));
		//排除作废
		criteria.add(Restrictions.ne("payStatus", Constants.OrderStatus.DELETE.getStatus()));
		//本周
		if(type == 1){
			Map<String,String> map = CommonController.getWeekDay();
			criteria.add(Restrictions.ge("createTime",map.get("mon")))
					.add(Restrictions.le("createTime",map.get("sun")));
		}
		//本月
		if(type == 2){
			Map<String,String> map = CommonController.getMonthDate();
			criteria.add(Restrictions.ge("createTime",map.get("monthF")))
					.add(Restrictions.le("createTime",map.get("monthL")));
		}
		criteria.addOrder(Order.desc("id"));
		List<OrderMain> list = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(list != null){
			return list;
		}else{
			return new ArrayList<OrderMain>();
		}
	}

	@Override
	public PageFinder<OrderMain> getPageOrderMainByStatus(int status,OrderMain o,
			int weuserId,int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", weuserId));
		if(status > 0){
			if(status == 3){
				Disjunction dis=Restrictions.disjunction();  
				dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
				dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
				criteria.add(dis); 
			}else{
				criteria.add(Restrictions.eq("payStatus", status));
			}
			
		}
		if(o.getOrderType()>0){
			criteria.add(Restrictions.eq("orderType", o.getOrderType()));
		}
		criteria.addOrder(Order.desc("id"));
		PageFinder<OrderMain> o1 = orderMainDao.pagedByCriteria(criteria, pageNumber, pageSize);
		return o1;
	}

	@Override
	public List<OrderMain> getOrderMainListByCycle(Map<?,?> map) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", Integer.valueOf(map.get("userId").toString())));
		// 日
		if(CommonUtil.NotEmptyObject(map.get("beginDay")) && CommonUtil.NotEmptyObject(map.get("endDay")) ){
			criteria.add(Restrictions.between("createTime",map.get("beginDay").toString(),map.get("endDay").toString()));
		}
		
		//月
		if(CommonUtil.NotEmptyObject(map.get("month")) ){
			Map<String,String> map1 = CommonController.getMonthTime(Integer.valueOf(map.get("month").toString()));
			criteria.add(Restrictions.between("createTime",map1.get("firstDay"), map1.get("lastDay")));
		}
		
		//周
		if(CommonUtil.NotEmptyObject(map.get("week")) ){
			Map<String,String> map1 = CommonController.getWeekDayTime(Integer.valueOf(map.get("week").toString()));
			criteria.add(Restrictions.between("createTime",map1.get("mon"), map1.get("sun")));
		}
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.PAY_PAID.getStatus()));
		dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.EXECUTION_ING.getStatus()));
		dis.add(Restrictions.eq("payStatus", Constants.OrderStatus.BASE_FINISH.getStatus()));
		criteria.add(dis);  
		criteria.addOrder(Order.desc("id"));
		List<OrderMain> list = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(list != null){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional
	public void cancelOrderMain(String orderMainNo) {
		OrderMain om=orderMainDao.findUniqueBy("orderMainNo", orderMainNo, true);
		try {
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar calendar=new GregorianCalendar();
			calendar.setTime(format.parse(om.getCreateTime()));
			calendar.add(Calendar.DAY_OF_WEEK, 7);
			Integer coachId=0;
			
			
			List<Orderinfo> orderInfoList =  orderInfoService.getOrderInfoListByOrderMainId(orderMainNo);
			
			for(Orderinfo oi:orderInfoList){
				if(oi.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
					if(format.parse(oi.getCreate_date()).before(calendar.getTime()) && !format.format(calendar.getTime()).equals(oi.getCreate_date())){
						Space_time_price stp=space_time_priceService.findByDate(oi.getSpaceId(), oi.getCreate_date(), Integer.valueOf(oi.getToday_time()));
						
						stp.setIs_reserve(0);
						
						space_time_priceService.saveSpace_time_price(stp);
						
						
						oi.setStatus(Constants.OrderStatus.DELETE.getStatus());
					}else{
						for(Cycle c:cycleService.getOrderMainNoCycle(om.getOrderMainNo())){
							cycleService.deleteByOrderMainNo(c);
						}
					}
					
					oi.setStatus(Constants.OrderStatus.DELETE.getStatus());
				}
				if(oi.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
					coachId=Integer.valueOf(oi.getActiveID());
					CoachReserve cr=coachReserveService.getCoachReserve(oi.getCreate_date(), Integer.valueOf(oi.getActiveID()), Integer.valueOf(oi.getToday_time()));
					if(cr!=null){
						coachReserveService.deleteCoachReserve(cr);
					}
					try {
					if(format.parse(oi.getCreate_date()).before(calendar.getTime()) && !format.format(calendar.getTime()).equals(oi.getCreate_date())){
						Space_time_price stp=space_time_priceService.findByDate(oi.getSpaceId(), oi.getCreate_date(), Integer.valueOf(oi.getToday_time()));
						
						stp.setIs_reserve(0);
						
						space_time_priceService.saveSpace_time_price(stp);
						
					}else{
						//当时周期预订的时候，要删除周期预订表里面的数据
						for(Cycle c:cycleService.getOrderMainNoCycle(om.getOrderMainNo())){
							cycleService.deleteByOrderMainNo(c);
						
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
					
					
					oi.setStatus(Constants.OrderStatus.DELETE.getStatus());
				}
				
				orderInfoService.mergeOrderInfo(oi);
			}
			om.setPayStatus(Constants.OrderStatus.DELETE.getStatus());
			
			if(coachId!=0){
				SMSUtil.sendSmsMsg(coachService.getcoachById(coachId).getPhone(), "用户取消了对你的预订，请及时登录APP查看信息");
			}
			orderMainDao.merge(om);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public boolean cancelOrderUserMoeny(String orderMainNo) {
		OrderMain om=orderMainDao.findUniqueBy("orderMainNo", orderMainNo, true);
		try {
			String cancel_time = systemConfigService.getConfigValueByKey(ConfigKey.EARLIST_CANCEL_ORDER_TIME,"3");//取系统配置值
			
			//算出两个时间的时间差值
			SimpleDateFormat formatInitial=new SimpleDateFormat("yyyy-MM-dd HH");
			double timePoor=((formatInitial.parse(om.getInitialDate()).getTime()-new Date().getTime())/1000.0/60.0/60.0);
			if(timePoor<=Double.valueOf(cancel_time)){
				return false;
			}
			
			Integer coachId=0;
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=new GregorianCalendar();
			calendar.setTime(format.parse(om.getCreateTime()));
			calendar.add(Calendar.DAY_OF_WEEK, 7);
			

			//通过主订单获取所有的子订单
			List<Orderinfo> orderInfoList =  orderInfoService.getOrderInfoListByOrderMainId(orderMainNo);
			
			for(Orderinfo oi:orderInfoList){
				
				//当订单类型为场馆时处理
				if(oi.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
					
					//时间判断 7天之内的数据处理
					if(format.parse(oi.getCreate_date()).before(calendar.getTime()) && !format.format(calendar.getTime()).equals(oi.getCreate_date())){
						
						//找到场地时间价格表的数据然后处理
						Space_time_price stp=space_time_priceService.findByDate(oi.getSpaceId(), oi.getCreate_date(), Integer.valueOf(oi.getToday_time()));
						
						stp.setIs_reserve(0);
						
						space_time_priceService.saveSpace_time_price(stp);
						
					}else{
						//当时周期预订的时候，要删除周期预订表里面的数据
						for(Cycle c:cycleService.getOrderMainNoCycle(om.getOrderMainNo())){
							cycleService.deleteByOrderMainNo(c);
						}
					}
					
					oi.setStatus(Constants.OrderStatus.PAY_REFUND_ALL.getStatus());
				}
				
				//当教练类型为教练类型
				if(oi.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
					
					//在教练预约表中找到数据，如果找到了就删除数据
					CoachReserve cr=coachReserveService.getCoachReserve(oi.getCreate_date(), Integer.valueOf(oi.getActiveID()), Integer.valueOf(oi.getToday_time()));
					//找到了就删除数据
					if(cr!=null){
						coachReserveService.deleteCoachReserve(cr);
						coachId=Integer.valueOf(oi.getActiveID());
					}
					try {
							if(format.parse(oi.getCreate_date()).before(calendar.getTime()) && !format.format(calendar.getTime()).equals(oi.getCreate_date())){
								Space_time_price stp=space_time_priceService.findByDate(oi.getSpaceId(), oi.getCreate_date(), Integer.valueOf(oi.getToday_time()));
								
								stp.setIs_reserve(0);
								
								space_time_priceService.saveSpace_time_price(stp);
								
							}else{
								//当时周期预订的时候，要删除周期预订表里面的数据
								for(Cycle c:cycleService.getOrderMainNoCycle(om.getOrderMainNo())){
									cycleService.deleteByOrderMainNo(c);
								
								}
							}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					
					
					
					oi.setStatus(Constants.OrderStatus.PAY_REFUND_ALL.getStatus());
				}
				
				orderInfoService.mergeOrderInfo(oi);
			}
			
			Weuser w = weuserService.getUserById(om.getWeuser().getId());
			w.setAmount(om.getWeuser().getAmount()+om.getTotalAmount());
			om.setPayStatus(Constants.OrderStatus.PAY_REFUND_ALL.getStatus());
			
			weuserService.updateUser(w);
			orderMainDao.merge(om);
			if(coachId!=0){
				SMSUtil.sendSmsMsg(coachService.getcoachById(coachId).getPhone(), "用户取消了对您的预订，请及时登录APP查看信息");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<OrderMain> getOrderMainListByOrderMainVo(OrderVo o) {
		CriteriaAdapter criteriaAdapter = orderMainDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		if(CommonUtil.NotEmpty(o.getStart_time()) && CommonUtil.NotEmpty(o.getEnd_time())){
			criteria.add(Restrictions.between("createTime",o.getStart_time(), o.getEnd_time()));
		}else{
			String oneday = CommonUtil.getTimeNows();
			String start=oneday+" 00:00:00";
			String end=oneday+" 23:59:59";
			criteria.add(Restrictions.between("createTime",start, end));
		}
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("payStatus", OrderStatus.COMMENTED.getStatus()));
		dis.add(Restrictions.eq("payStatus", OrderStatus.BASE_FINISH.getStatus()));
		criteria.add(dis);  
		List<OrderMain> os = criteria.list();
		orderMainDao.releaseHibernateSession(criteriaAdapter.getSession());
		return os;
	}

	@Override
	@Transactional
	public OrderMain livePayOrder(OrderMain om) {
		try {
			Weuser user = weuserService.getUserById(om.getWeuser().getId());
			om.setPayStatus(Constants.OrderStatus.LIVE_PAY.getStatus());
			List<Orderinfo> list=orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
			//完成订单时加场馆预订次数
			if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus()){
				Playground p=playgroundService.getPlaygroundById(Integer.valueOf(om.getActiveID()));
				p.setAudCount(p.getAudCount()+1);
				playgroundService.mergePlayground(p);
				
			}
			om.setPayStatus(Constants.OrderStatus.PAY_PAID.getStatus());
			
			orderMainDao.merge(om);
			List<Orderinfo> olist = orderInfoService.getOrderInfoListByOrderMainId(om.getOrderMainNo());
			
			for(Orderinfo o:olist){
				//当主订单是场馆类型,子订单为教练类型是(说明此订单由教练列表选择驻场教练下的单)		edit  by nantian	2015-12-22
				if(om.getOrderType()==Constants.DATATYPE.PlaygroundType.getStatus() && o.getOrderType()==Constants.DATATYPE.CoachType.getStatus()){
					//增加预订次数
					User_count uc = user_countService.getUser_countByUserId(coachService.getcoachById(Integer.valueOf(o.getActiveID())).getUserid().getId());
		    		uc.setReservecount(uc.getReservecount()+1);
		    		user_countService.updateUser_count(uc);
				}
				//E
				o.setPayTime(CommonUtil.getTimeNow());
				o.setStatus(Constants.OrderStatus.PAY_PAID.getStatus());
				orderInfoService.mergeOrderInfo(o);
				orderComponent.genOrderLogForSave(o.getWeuser().getReal_name(),o.getOrderSubNo(), MethodCode.PAID_ORDER,LogType.ORDER_NORMAL_LOG,"订单" + o.getOrderSubNo() + "支付成功", true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
