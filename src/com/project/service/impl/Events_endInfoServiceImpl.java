package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IEvents_endInfoDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Events;
import com.project.pojo.Events_endInfo;
import com.project.service.IEvents_endInfoService;

@Service
public class Events_endInfoServiceImpl implements IEvents_endInfoService {

	@Resource
	private IEvents_endInfoDao events_endInfoDao;
	
	@Override
	public Events_endInfo saveEvents_endInfo(Events_endInfo e) throws Exception {
		return events_endInfoDao.saveObject(e);
	}

	@Override
	public PageFinder<Events_endInfo> getPageFindeEvents_endInfo(
			Events_endInfo o, int pageNumber, int pageSize,int oid) {
		CriteriaAdapter criteriaAdapter = events_endInfoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("events", "events",CriteriaSpecification.LEFT_JOIN);
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		criteria.add(Restrictions.eq("events.id", oid));
		PageFinder<Events_endInfo> pageFinder = events_endInfoDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		events_endInfoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Events_endInfo updateEvents_endInfo(Events_endInfo o)
			throws Exception {
		return events_endInfoDao.merge(o);
	}

	@Override
	public Events_endInfo getEvents_endInfoById(int oid) {
		return events_endInfoDao.getById(oid);
	}

}
