package com.project.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IEventsDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.pojo.Events;
import com.project.pojo.Orderinfo;
import com.project.service.IEventsService;
import com.project.util.CommonUtil;

@Service
public class EventsServiceImpl implements IEventsService {

	@Resource
	private IEventsDao eventsDao;
	
	@Override
	public Events saveEvents(Events e) throws Exception {
		return eventsDao.saveObject(e);
	}

	@Override
	public PageFinder<Events> getPageFindeEvents(Events o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = eventsDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (CommonUtil.NotEmpty(o.getTitle())) {
			criteria.add(Restrictions.like("title", o.getTitle(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Events> pageFinder = eventsDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		eventsDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Events updateEvents(Events o) throws Exception {
		return eventsDao.merge(o);
	}

	@Override
	public Events getEventsById(int oid) {
		return eventsDao.getById(oid);
	}

	@Override
	public List<Events> getEvents() {
		CriteriaAdapter criteriaAdapter = eventsDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.ge("end_time",CommonUtil.getTimeNow()))
				.add(Restrictions.le("sign_time",CommonUtil.getTimeNow()));
		List<Events> o = criteria.list();
		eventsDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(o!=null && o.size()>0){
			return o;
		}
		return null;
	}

	@Override
	public Events getNewEvents() {
		CriteriaAdapter criteriaAdapter = eventsDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		List<Events> o = criteria.list();
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		eventsDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(o!=null && o.size()>0){
			return o.get(0);
		}
		return null;
	}

}
