package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IRechargeEventsDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.RechargeEvents;
import com.project.pojo.Red_bag_record;
import com.project.service.IRechargeEventsService;
import com.project.util.CommonUtil;


@Service
public class RechargeEventsServiceImpl implements IRechargeEventsService {

	@Resource
	private IRechargeEventsDao rechargeEventsDao; 
	
	@Override
	public List<RechargeEvents> getAllRechargeEventsList() {
		CriteriaAdapter criteriaAdapter = rechargeEventsDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	criteria.add(Restrictions.le("begin_time",CommonUtil.getTimeNow()))
				.add(Restrictions.ge("end_time",CommonUtil.getTimeNow()));
    	criteria.addOrder(org.hibernate.criterion.Order.asc("is_vip"));
        List<RechargeEvents> list = criteria.list();
        rechargeEventsDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public RechargeEvents saveRechargeEvents(RechargeEvents o) throws Exception {
		return rechargeEventsDao.saveObject(o);
	}

	@Override
	public RechargeEvents uppdateRechargeEvents(RechargeEvents o)
			throws Exception {
		return rechargeEventsDao.merge(o);
	}

	@Override
	public void deleteRechargeEvents(RechargeEvents o) throws Exception {
		rechargeEventsDao.remove(o);
	}

	@Override
	public RechargeEvents getRechargeEventsById(int id) {
		return rechargeEventsDao.getById(id);
	}

	@Override
	public PageFinder<RechargeEvents> getPageFindeRechargeEvents(RechargeEvents re,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = rechargeEventsDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(CommonUtil.NotEmpty(re.getBegin_time())){   //开始时间
			criteria.add(Restrictions.lt("begin_time", re.getBegin_time()));
		}
		if(CommonUtil.NotEmpty(re.getEnd_time())){	   //结束时间
			criteria.add(Restrictions.gt("end_time", re.getEnd_time()));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<RechargeEvents> pageFinder = rechargeEventsDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		rechargeEventsDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}


}
