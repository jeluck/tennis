package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants.WithdrawOrderResult;
import com.project.dao.IBusiness_timeDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Business_time;
import com.project.pojo.Coach_set_time;
import com.project.pojo.WithdrawOrder;
import com.project.service.IBusiness_timeService;

@Service
public class Business_timeServiceImpl implements IBusiness_timeService{

	@Resource
	private IBusiness_timeDao business_timeDao;
	
	@Override
	public Business_time saveBusiness_time(Business_time b) throws Exception {
		return business_timeDao.saveObject(b);
	}

	@Override
	public Business_time findByplaygroundId(int playgroundId,int dateType) {
		CriteriaAdapter criteriaAdapter = business_timeDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("playground", "playground",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("playground.id",playgroundId));
        criteria.add(Restrictions.eq("dateType",dateType));
        List<Business_time> list = criteria.list();
        business_timeDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list.get(0);
	}

	@Override
	public Business_time updateBusiness_time(Business_time b) {
		return business_timeDao.merge(b);
	}

	@Override
	public Business_time findById(Integer id) {
		return business_timeDao.getById(id);
	}

	@Override
	public List<Business_time> findByplaygroundId(int playgroundId) {
		return business_timeDao.findBy("playground.id", playgroundId, true);
	}

}
