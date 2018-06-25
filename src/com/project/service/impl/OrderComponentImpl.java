package com.project.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Settings;
import com.project.common.OrderEnum.LogType;
import com.project.common.OrderEnum.MethodCode;
import com.project.dao.IOrderLogDao;
import com.project.dao.IPayInfo_thirdPay_orderNoDao;
import com.project.dao.ITennis_cricles_pariseDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.OrderLog;
import com.project.pojo.Orderinfo;
import com.project.pojo.PayInfo_thirdPay_orderNo;
import com.project.pojo.Weuser;
import com.project.service.ICommentService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IPlaygroundService;
import com.project.service.IRegionService;
import com.project.util.CommonUtil;
import com.project.util.OrderUtils;


@Component
public class OrderComponentImpl implements IOrderComponent {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrderComponentImpl.class);
	
	@Resource
	private IOrderLogDao orderLogDao;

	@Resource
	private IPayInfo_thirdPay_orderNoDao payInfo_thirdPay_orderNoDao;
	
	@Resource
	private ITennis_cricles_pariseDao vipCardInfoDao;
	
	@Resource
	private IOrderInfoService orderInfoService;
    @Resource
    private ICommentService commonAddressService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IPlaygroundService orderDetail4subService;
	@Override
	@Transactional
	public Map paySuccessDeal(int payState, String payInfoId,
			String tradeNo, int onlinePayStyleNo, double payAmount)
			throws Exception {
		Orderinfo order = orderInfoService.getOrderByorderSubNo(tradeNo);
		return null;
	}

	@Override
	@Transactional
	public void genOrderLogForSave(String operator, String orderNo,
			MethodCode methodCode,LogType logType, String remark,boolean flag){
		OrderLog ol = OrderUtils.getOrderLog(orderNo, methodCode, logType, operator, remark, "", flag);
		try {
			orderLogDao.save(ol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
    *
    * 方法描述： 生成正常子订单日志与拆分子订单日志
    * @param user
    * @param orderSubVo
    * @throws Exception void
    * @throws
    */
	public void genOrderLog(Weuser w, Orderinfo o) throws Exception {
   	if(o.getWeuser() != null && CommonUtil.NotEmpty(o.getWeuser().getUsername())){
   		genOrderLogForSave(o.getWeuser().getUsername(), o.getOrderSubNo(),
                   MethodCode.GEN_SUB_ORDER,LogType.ORDER_NORMAL_LOG,"提交订单["+o.getOrderSubNo()+"]成功",true);
   	}else{
	        genOrderLogForSave(w.getUphone(), o.getOrderSubNo(), MethodCode.GEN_SUB_ORDER,LogType.ORDER_NORMAL_LOG, "提交订单["+o.getOrderSubNo()+"]成功",true);
   	}
       	genOrderLogForSave(Settings.SYSTEM_HANDLE, o.getOrderSubNo(),MethodCode.GEN_SUB_SPLIT_ORDER,LogType.ORDER_OPERATE_LOG,"正常生成子订单["+o.getOrderSubNo()+"]",true);
   }

	@Override
	public PageFinder<OrderLog> getPageFindeOrderLog(OrderLog o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = orderLogDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (CommonUtil.NotEmpty(o.getOrderNo())) {
			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<OrderLog> pageFinder = orderLogDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		orderLogDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	@Transactional
	public synchronized String  savePayInfo_thirdPay_orderNo(String orderMainNo, double amount,int pay_type) {
		String payOrderNo = String.valueOf(System.currentTimeMillis());	//支付订单号
		logger.info("保存订单支付信息开始...支付订单号:"+payOrderNo);
		PayInfo_thirdPay_orderNo p = new PayInfo_thirdPay_orderNo();
		p.setOrderMainNo(orderMainNo);
		p.setPay_order_amount(amount);
		p.setCreate_time(CommonUtil.getTimeNow());
		p.setPay_type(pay_type);
		p.setPay_orderNo(payOrderNo);
		try {
			payInfo_thirdPay_orderNoDao.save(p);
		} catch (Exception e) {			
			e.printStackTrace();
			logger.error("保存订单支付信息出错");
			return "";
		}
		return payOrderNo;
	}

	@Override
	public PayInfo_thirdPay_orderNo getPayInfo_thirdPay_orderNo(
			String payOrderNo) {
		CriteriaAdapter criteriaAdapter = payInfo_thirdPay_orderNoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("pay_orderNo", payOrderNo));
		PayInfo_thirdPay_orderNo p = (PayInfo_thirdPay_orderNo) criteria.uniqueResult();
		payInfo_thirdPay_orderNoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return p;
	}


}
