package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants.WithdrawOrderResult;
import com.project.dao.IWithdrawOrderDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Manager;
import com.project.pojo.Weuser;
import com.project.pojo.WithdrawOrder;
import com.project.service.IMoneyService;
import com.project.service.ISystemConfigService;
import com.project.service.IWeuserService;
import com.project.util.CalculateUtil;
import com.project.util.CommonUtil;


@Service
public class MoneyServiceImpl  implements IMoneyService {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private IWithdrawOrderDao withdrawOrderDao;
    @Resource
    private IWeuserService weuserService;
    @Resource
    private ISystemConfigService systemConfigService;
	

	@Override
	public PageFinder<WithdrawOrder> getWithdrawByStatus(int pageNumber,
			int pageSize, int status, HttpServletRequest request) {
		String name = request.getParameter("name");
		String real_name = request.getParameter("real_name");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		
		CriteriaAdapter criteriaAdapter = withdrawOrderDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("manager", "manager",  CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("wd_status", status));
		if(CommonUtil.NotEmpty(name)){
			criteria.add(Restrictions.like("username", name, MatchMode.ANYWHERE));
		}
		if(CommonUtil.NotEmpty(real_name)){
			criteria.add(Restrictions.like("account_name", real_name, MatchMode.ANYWHERE));
		}
		if(CommonUtil.NotEmpty(start_time)){
			criteria.add(Restrictions.between("create_time", start_time, end_time));
		}
		criteria.addOrder(Order.desc("create_time"));
		PageFinder<WithdrawOrder> l = withdrawOrderDao.pagedByCriteria(criteria, pageNumber, pageSize);
		withdrawOrderDao.releaseHibernateSession(criteriaAdapter.getSession());
		return l;
	}

	@Override
	@Transactional
	public boolean updatePassWithdwar(int id, int stauts, String remark) throws Exception {
		WithdrawOrder order = getSimpleWithdrawOrderById(id);
		order.setCheck_time(CommonUtil.getTimeNow());
		order.setCheck_remark(remark);
		order.setWd_status(stauts);
		order.setUpdate_time(CommonUtil.getTimeNow());
		if(stauts==WithdrawOrderResult.SUCCESS.getStatus()){//通过
			mergeWithdrawOrder(order);
		}else if(stauts==WithdrawOrderResult.FAIL.getStatus()){//审核不通过
			Weuser w = weuserService.getUserById(Integer.parseInt(order.getWithdrawer()));
			//提现资金回流
			String amount = CalculateUtil.add(String.valueOf(w.getAmount()),CalculateUtil.add(order.getWd_money(),order.getWithdraw_rate()));
			w.setAmount(Double.parseDouble(amount));
			weuserService.mergeAndUpdateTime(w);
		}
		return true;
	}

	@Override
	public WithdrawOrder getSimpleWithdrawOrderById(int id) {
		return withdrawOrderDao.getById(id);
	}

	@Override
	public WithdrawOrder getWithdrawOrderById(int id) {
		CriteriaAdapter criteriaAdapter = withdrawOrderDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("manager", "manager",  CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("id", id));
		WithdrawOrder w = (WithdrawOrder) criteria.uniqueResult();
		withdrawOrderDao.releaseHibernateSession(criteriaAdapter.getSession());
		w.setWeuser(weuserService.getUserById(Integer.parseInt(w.getWithdrawer())));
		if(w.getManager()!=null){
			w.setOperaer(w.getManager().getUsername());
		}
		return w;
	}

	@Override
	public List<WithdrawOrder> getOrdersByREQ_SN(String req_sn) {
		CriteriaAdapter criteriaAdapter = withdrawOrderDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("bankcard", "bankcard",  CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("wd_status", WithdrawOrderResult.SUCCESS.getStatus()));
		criteria.add(Restrictions.eq("req_sn", req_sn));
		List<WithdrawOrder>  w = criteria.list();
		withdrawOrderDao.releaseHibernateSession(criteriaAdapter.getSession());
		return w;
	}
	
	@Override
	public Integer getWithdwarCount(int status) {
		CriteriaAdapter criteriaAdapter = withdrawOrderDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createCriteria("manager", "manager",  CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("wd_status", status));
		List<WithdrawOrder>  w = criteria.list();
		withdrawOrderDao.releaseHibernateSession(criteriaAdapter.getSession());
		return w.size();
	}

	@Override
	@Transactional
	public boolean updateHandMovementWithdwar(int id, int stauts, String remark,Manager manager) throws Exception {
		WithdrawOrder order = getSimpleWithdrawOrderById(id);
		order.setManager(manager);
		order.setCheck_time(CommonUtil.getTimeNow());
		order.setCheck_remark(remark);
		order.setWd_status(stauts);
		order.setUpdate_time(CommonUtil.getTimeNow());
		order.setSubmit_time(CommonUtil.getTimeNow());
		if(stauts==WithdrawOrderResult.SUCCESS.getStatus()){//成功
			mergeWithdrawOrder(order);
		}else if(stauts==WithdrawOrderResult.FAIL.getStatus()){//审核不通过
			Weuser w = weuserService.getUserById(Integer.parseInt(order.getWithdrawer()));
			//提现资金回流
			String amount = CalculateUtil.add(String.valueOf(w.getAmount()),CalculateUtil.add(order.getWd_money(),order.getWithdraw_rate()));
			w.setAmount(Double.parseDouble(amount));
			weuserService.mergeAndUpdateTime(w);
			
		}
		return true;
	}

	@Override
	public String getAlreadyMentionedAmount(int withdrawer_role,String id, int wd_status) {
		String mentioned = "0"; 
		
		CriteriaAdapter criteriaAdapter = withdrawOrderDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("withdrawer_role", withdrawer_role));
		criteria.add(Restrictions.eq("withdrawer", id));
		criteria.add(Restrictions.eq("wd_status", wd_status));
		List<WithdrawOrder>  w = criteria.list();
		withdrawOrderDao.releaseHibernateSession(criteriaAdapter.getSession());
		for (WithdrawOrder wo : w) {
			mentioned+=CalculateUtil.add(wo.getWd_money()+mentioned);
		}
		return mentioned;
	}

	@Override
	@Transactional
	public WithdrawOrder mergeWithdrawOrder(WithdrawOrder w) throws Exception {
		w.setUpdate_time(CommonUtil.getTimeNow());
		return withdrawOrderDao.merge(w);
	}

}
