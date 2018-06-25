package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IEventRegistrationDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.EventRegistration;
import com.project.pojo.Gallery;
import com.project.service.IEventRegistrationService;

@Service
public class EventRegistrationServiceImpl implements IEventRegistrationService {
	
	@Resource
	private IEventRegistrationDao eventRegistrationDao;

	@Override
	public EventRegistration seveEventRegistration(EventRegistration o)
			throws Exception {
		return eventRegistrationDao.saveObject(o);
	}

	@Override
	public PageFinder<EventRegistration> getPageFindeEventRegistration(
			EventRegistration o, int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = eventRegistrationDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if(o.getType()>0){
//			criteria.add(Restrictions.eq("type", o.getType()));
//		}
//		criteria.add(Restrictions.eq("acId", o.getAcId()));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<EventRegistration> pageFinder = eventRegistrationDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		eventRegistrationDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public EventRegistration getEventRegistrationById(int oid) {
		return eventRegistrationDao.getById(oid);
	}

	@Override
	public EventRegistration mergeEventRegistration(EventRegistration o) throws Exception {
		return eventRegistrationDao.merge(o);
	}

	@Override
	public void deleteEventRegistrationById(int id) throws Exception {
		eventRegistrationDao.removeById(id);
		
	}
	
}
