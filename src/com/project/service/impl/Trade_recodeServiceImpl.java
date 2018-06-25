package com.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.controller.client.CommonController;
import com.project.dao.ITrade_recodeDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.OrderMain;
import com.project.pojo.Trade_recode;
import com.project.service.ITrade_recodeService;
import com.project.util.CommonUtil;

@Service
public class Trade_recodeServiceImpl implements ITrade_recodeService {

	@Resource
	private ITrade_recodeDao trade_recodedao;
	
	
	
	@Override
	public Trade_recode saveTrade_recode(Trade_recode o) throws Exception {
		return trade_recodedao.saveObject(o);
	}

	@Override
	public void deleteTrade_recode(int id) throws Exception {
		trade_recodedao.removeById(id);
	}

	@Override
	public Trade_recode updateTrade_recode(Trade_recode o) throws Exception {
		return trade_recodedao.merge(o);
	}

	@Override
	public Trade_recode getTrade_recodeById(int id) {
		return trade_recodedao.getById(id);
	}

	@Override
	public Trade_recode getTrade_recodeByOrderMian(String orderMainId) {
		CriteriaAdapter criteriaAdapter = trade_recodedao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("orderMainNo", orderMainId));
		List<Trade_recode> list = criteria.list();
		trade_recodedao.releaseHibernateSession(criteriaAdapter.getSession());
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public List<Trade_recode> getTrade_recodeByUserId(int userId,String type) {
		CriteriaAdapter criteriaAdapter = trade_recodedao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("weuser.id", userId));
		criteria.add(Restrictions.eq("tradetype", 3));
		criteria.add(Restrictions.eq("business_type", 1));
		if(CommonUtil.NotEmpty(type) && type.equals("week")){
			Map<String,String> map = CommonController.getWeekDay();
			criteria.add(Restrictions.between("create_time", map.get("mon"), map.get("sun")));
			
		}
		if(CommonUtil.NotEmpty(type) && type.equals("month")){
			Map<String,String> map = CommonController.getMonthDate();
			criteria.add(Restrictions.between("create_time", map.get("monthF"), map.get("monthL")));
		}
		criteria.addOrder(Order.desc("id"));
		List<Trade_recode> list = criteria.list();
		trade_recodedao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

}
