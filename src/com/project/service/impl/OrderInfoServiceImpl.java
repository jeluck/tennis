package com.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants;
import com.project.common.Constants.OrderStatus;
import com.project.controller.client.CommonController;
import com.project.dao.IOrderInfoDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.OrderMain;
import com.project.pojo.Orderinfo;
import com.project.pojo.Trade_recode;
import com.project.pojo.Weuser;
import com.project.pojo.vo.OrderVo;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IOrderMainService;
import com.project.service.IPlaygroundService;
import com.project.service.ISpaceManagerService;
import com.project.service.ITrade_recodeService;
import com.project.service.IWeuserService;
import com.project.util.CommonUtil;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OrderInfoServiceImpl.class);

	@Resource
	private IOrderInfoDao orderInfoDao;
    @Resource
    private IOrderComponent orderComponent;
    @Resource
    private ISpaceManagerService produtService;
    @Resource
    private IPlaygroundService orderDetail4subService;
    @Resource
    private IWeuserService weuserService;
    @Resource
    private IOrderMainService orderMainService;
    @Resource
    private ITrade_recodeService trade_recodeService;
    
	@Override
	@Transactional
	public void saveOrderInfoService(Orderinfo o) throws Exception {
		orderInfoDao.save(o);
	}

	@Override
	@Transactional
	public void acceptOrder(int id) throws Exception {
		Orderinfo orderInfo = orderInfoDao.getById(id);
		orderInfo.setExamineTime(CommonUtil.getTimeNow());
		mergeOrderInfo(orderInfo);
	}


	public Orderinfo getOrderById(int oid) {
		return orderInfoDao.getById(oid);
	}

	public PageFinder<Orderinfo> orderList(String tradeno, String startdate,
			String enddate, int baseStatus, int status, int deliveryStatus,
			int userId, int pageNumber, int pageSize,int playgroundId,int playgroundManagerId,int is_back) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
		if (tradeno != null && !"".equals(tradeno)) {
			criteria.add(Restrictions.eq("orderSubNo", tradeno));
		}
		if (startdate != null && !"".equals(startdate) && enddate != null
				&& !"".equals(enddate)) {
			criteria.add(Restrictions.ge("create_date", startdate));
			criteria.add(Restrictions.le("create_date", enddate));
		} else if (startdate != null && !"".equals(startdate)) {
			criteria.add(Restrictions.eq("create_date", startdate));
		} else if (enddate != null && !"".equals(enddate)) {
			criteria.add(Restrictions.eq("create_date", enddate));
		}
		
		if(is_back>0){
			criteria.add(Restrictions.eq("is_back", is_back));
		}

		if(playgroundManagerId>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", playgroundManagerId));
			
			//好像用不到这一段代码,先注释,(加了这代码后,在运营商后台看不到取消的订单)
//			criteria.add(Restrictions.or(Restrictions.eq("status", OrderStatus.BASE_FINISH.getStatus()), 
//					Restrictions.or(Restrictions.eq("status", OrderStatus.EXECUTION_ING.getStatus()),
//					Restrictions.eq("status", OrderStatus.PAY_PAID.getStatus()))));
		}
		if (baseStatus > 0) {
			criteria.add(Restrictions.eq("baseStatus", baseStatus));
		}

		if (status > 0) {
			criteria.add(Restrictions.eq("status", status));
		}

		if (deliveryStatus > 0) {
			criteria.add(Restrictions.eq("deliveryStatus", deliveryStatus));
		}

		if (userId > 0) {
			criteria.add(Restrictions.eq("weuser.id", userId));
		}
		if(playgroundId>0){
			criteria.add(Restrictions.eq("activeID", String.valueOf(playgroundId)));
			criteria.add(Restrictions.eq("orderType", Constants.DATATYPE.PlaygroundType.getStatus()));
		}
		

		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Orderinfo> pageFinder = orderInfoDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());

		return pageFinder;
	}

	@Override
	public Orderinfo mergeOrderInfo(Orderinfo o) throws Exception {
		o.setUpdate_time(CommonUtil.getTimeNow());
		return orderInfoDao.merge(o);
	}

	@Override
	public List<Orderinfo> getOrderInfoListByOrderMainId(String orderMainId) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("orderMainNo", orderMainId));
		List<Orderinfo> o = criteria.list();
		orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(o!=null && o.size()>0){
			return o;
		}
		return null;
	}

	@Override
	public Orderinfo getOrderByorderSubNo(String orderSubNo) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("orderSubNo", orderSubNo));
		Orderinfo o = orderInfoDao.queryObjectByCriteria(criteria);
		orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}

	@Override
	public List<Orderinfo> getOrderInfoByTime(String create_date, String today_time,Integer orderStauts) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("create_date", create_date));
        criteria.add(Restrictions.eq("today_time", today_time));
        criteria.add(Restrictions.eq("status", orderStauts));
        List<Orderinfo> list = criteria.list();
        orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}
	
	@Override
	public List<Orderinfo> getOrderInfoByCreate(String create_date,int palygroundManagerId) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("create_date", create_date));
        
        if(palygroundManagerId>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", palygroundManagerId));
		}
        List<Orderinfo> list = criteria.list();
        orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public Orderinfo cancelOrder(Integer orderInfoId, Integer userId) {
		Orderinfo oi = orderInfoDao.getById(orderInfoId);
		Weuser user = weuserService.getUserById(userId);
		
		oi.setStatus(Constants.OrderStatus.PAY_REFUND_ALL.getStatus());
		user.setAmount(user.getAmount()+oi.getOrderPayTotalAmont());
		
		orderInfoDao.merge(oi);
		weuserService.mergeAndUpdateTime(user);
		
		List<Orderinfo> list=orderInfoDao.findBy("orderMainNo", oi.getOrderMainNo(),false);
		
		//修改主订单
		boolean flag=true;
		for(Orderinfo o:list){
				if(o.getStatus() != Constants.OrderStatus.PAY_REFUND_ALL.getStatus()){
					flag=false;
					break;
				}
		}
		
		OrderMain om= orderMainService.getOrderMainByNO(oi.getOrderMainNo());
		om.setTotalAmount(om.getTotalAmount()-oi.getOrderPayTotalAmont());
		
		Trade_recode tr= trade_recodeService.getTrade_recodeByOrderMian(om.getOrderMainNo());
		
		if(flag){
			om.setPayStatus(Constants.OrderStatus.PAY_REFUND_ALL.getStatus());	
			tr.setTradetype(Constants.REFUND);
		}
		tr.setDesccp(tr.getDesccp()+oi.getOrderSubNo()+"退款");
		tr.setBalance(user.getAmount());
		tr.setMny_amount(tr.getMny_amount()-oi.getOrderPayTotalAmont());
		
		try {
			orderMainService.updateOrderMain(om);
			trade_recodeService.updateTrade_recode(tr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Orderinfo getOrderinfoByUserIdAndActiveID(int userId,
			String activeID, int orderType) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
		 criteria.add(Restrictions.eq("weuser.id", userId));
        criteria.add(Restrictions.eq("activeID", activeID));
        criteria.add(Restrictions.eq("orderType", orderType));
        List<Orderinfo> list = criteria.list();
        orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return  list.get(0);
        }else{
        	return  null;
        }
	}

	@Override
	public List<Orderinfo> getOrderinfoByObj(Orderinfo o,Map<?,?> map) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(CommonUtil.NotEmptyObject(map.get("year")) && CommonUtil.NotEmptyObject(map.get("month"))){
        	Map<String,String> map1 = CommonController.getMonthTimeByYear(Integer.valueOf(map.get("year").toString()), Integer.valueOf(map.get("month").toString()));
		System.err.println(map1.get("firstDay").toString()+"------------"+ map1.get("lastDay").toString());
        	criteria.add(Restrictions.between("create_date", map1.get("firstDay").toString(), map1.get("lastDay").toString()));
		}
        
        //周
  		if(CommonUtil.NotEmptyObject(map.get("week")) ){
  			Map<String,String> map1 = CommonController.getWeekDayTime(Integer.valueOf(map.get("week").toString()));
  			criteria.add(Restrictions.between("create_date",map1.get("mon"), map1.get("sun")));
  		}
  		
  		//月
		if(CommonUtil.NotEmptyObject(map.get("months")) ){
			Map<String,String> map1 = CommonController.getMonthTime(Integer.valueOf(map.get("months").toString()));
			criteria.add(Restrictions.between("create_date",map1.get("firstDay"), map1.get("lastDay")));
		}
        
        if(o.getOrderType()>0){
        	criteria.add(Restrictions.eq("orderType", o.getOrderType()));
        }
        if(CommonUtil.NotEmpty(o.getActiveID())){
        	criteria.add(Restrictions.eq("activeID", o.getActiveID()));
        }
        if(CommonUtil.NotEmptyObject(map.get("status"))){
        	Disjunction dis=Restrictions.disjunction();  
        	if(map.get("status").equals("status")){
        		dis.add(Restrictions.eq("status", Constants.OrderStatus.PAY_PAID.getStatus()));
        	}
    		dis.add(Restrictions.eq("status", Constants.OrderStatus.BASE_FINISH.getStatus()));
    		dis.add(Restrictions.eq("status", Constants.OrderStatus.EXECUTION_ING.getStatus()));
    		criteria.add(dis); 
        }
        List<Orderinfo> list = criteria.list();
        orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return  list;
        }else{
        	return  null;
        }
	}

	@Override
	public List<Orderinfo> getOrderinfoListByOrderVo(OrderVo o) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		if(CommonUtil.NotEmpty(o.getStart_time()) && CommonUtil.NotEmpty(o.getEnd_time())){
			String start=o.getStart_time()+" 00:00:00";
			String end=o.getEnd_time()+" 23:59:59";
			criteria.add(Restrictions.between("creat_order_time",start, end));
		}else{
			String oneday = CommonUtil.getTimeNows();
			String start=oneday+" 00:00:00";
			String end=oneday+" 23:59:59";
			criteria.add(Restrictions.between("creat_order_time",start, end));
		}
		if(o.getOrderType()>0){
			criteria.add(Restrictions.eq("orderType", o.getOrderType()));
		}
		if(CommonUtil.NotEmpty(o.getActiveID())){
			criteria.add(Restrictions.eq("activeID", o.getActiveID()));
		}
		
		
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("status", OrderStatus.COMMENTED.getStatus()));		//已评价
		dis.add(Restrictions.eq("status", OrderStatus.BASE_FINISH.getStatus()));	//已完成
		criteria.add(dis);
		criteria.addOrder(Order.asc("creat_order_time"));
		criteria.addOrder(Order.asc("orderSubNo"));
		List<Orderinfo> os = criteria.list();
		orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return os;
	}

	@Override
	public List<Orderinfo> getOrderinfoByObj(Orderinfo o) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
		if(CommonUtil.NotEmptyObject(o.getWeuser())){
			 criteria.add(Restrictions.eq("weuser.id", o.getWeuser().getId()));
		}
		if(o.getOrderType()>0){
			 criteria.add(Restrictions.eq("orderType", Constants.DATATYPE.CoachType.getStatus()));
		}
		if(CommonUtil.NotEmpty(o.getActiveID())){
			 criteria.add(Restrictions.eq("activeID", o.getActiveID()));
		}
		if(o.getStatus()>0){
			 criteria.add(Restrictions.eq("status", Constants.OrderStatus.BASE_FINISH.getStatus()));
		}
       
        List<Orderinfo> list = criteria.list();
        orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return  list;
        }else{
        	return  null;
        }
	}
	
	@Override
	public List<Orderinfo> getOrderinfoListByToday(int userId) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", userId));
		String oneday = CommonUtil.getTimeNows();
		String start=oneday+" 00:00:00";
		String end=oneday+" 23:59:59";
		criteria.add(Restrictions.eq("create_date", oneday));
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("status", OrderStatus.BASE_FINISH.getStatus()));
		criteria.add(dis);  
		List<Orderinfo> os = criteria.list();
		orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return os;
	}
	
	@Override
	public List<Orderinfo> getOrderinfoListByTodayactiveID( String activeID) {
		CriteriaAdapter criteriaAdapter = orderInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("activeID", activeID));
		String oneday = CommonUtil.getTimeNows();
		String start=oneday+" 00:00:00";
		String end=oneday+" 23:59:59";
		criteria.add(Restrictions.eq("create_date", oneday));
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("status", OrderStatus.BASE_FINISH.getStatus()));
		criteria.add(dis);  
		List<Orderinfo> os = criteria.list();
		orderInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return os;
	}
}
