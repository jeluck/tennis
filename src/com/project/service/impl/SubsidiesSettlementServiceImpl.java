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
import com.project.dao.ISubsidiesSettlementDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Subsidies;
import com.project.pojo.SubsidiesSettlement;
import com.project.pojo.Trade_recode;
import com.project.service.ISubsidiesService;
import com.project.service.ISubsidiesSettlementService;
import com.project.util.CommonUtil;

@Service
public class SubsidiesSettlementServiceImpl implements ISubsidiesSettlementService {

	@Resource
	private ISubsidiesSettlementDao subsidiesSettlementDao;
	@Resource
	private ISubsidiesService subsidiesService;
	
	
	@Override
	public SubsidiesSettlement saveSubsidiesSettlement(SubsidiesSettlement o) throws Exception {
		return subsidiesSettlementDao.saveObject(o);
	}

	@Override
	public SubsidiesSettlement updateSubsidiesSettlement(SubsidiesSettlement o) throws Exception {
		return subsidiesSettlementDao.merge(o);
	}

	@Override
	public void saveSubsidiesSettlementByPdId(int zhe_id, double money,String orderMainNo,int type)
			throws Exception {
		Subsidies s = subsidiesService.getSubsidiesByPdId(zhe_id,type);
		SubsidiesSettlement o = new SubsidiesSettlement();
		if(s!=null){
			s.getMoney();
			o.setOrderMainNo(orderMainNo);
			o.setZhe_id(zhe_id);
			o.setType(type);
			o.setCreate_time(CommonUtil.getTimeNow());
			o.setSubsidies_proportion(s.getMoney());
			o.setMoney(money);
			o.setSubsidies_money(s.getMoney()*money);
			o = subsidiesSettlementDao.saveObject(o);
		}
	}

	@Override
	public List<SubsidiesSettlement> subsidiesSettlementList(int zhe_id,
			String type) {
		CriteriaAdapter criteriaAdapter = subsidiesSettlementDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("zhe_id", zhe_id));
		criteria.add(Restrictions.eq("type", 1));
		if(CommonUtil.NotEmpty(type) && type.equals("week")){
			Map<String,String> map = CommonController.getWeekDay();
			criteria.add(Restrictions.between("create_time", map.get("mon"), map.get("sun")));
			
		}
		if(CommonUtil.NotEmpty(type) && type.equals("month")){
			Map<String,String> map = CommonController.getMonthDate();
			criteria.add(Restrictions.between("create_time", map.get("monthF"), map.get("monthL")));
		}
		criteria.addOrder(Order.desc("id"));
		List<SubsidiesSettlement> list = criteria.list();
		subsidiesSettlementDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
		
	}

}
