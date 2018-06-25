package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IEventsCooperationDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.EventsCooperation;
import com.project.pojo.Orderinfo;
import com.project.service.IEventsCooperationService;

@Service
public class EventsCooperationServiceImpl implements IEventsCooperationService {
	
	@Resource
	private IEventsCooperationDao eventsCooperationDao;

	@Override
	public EventsCooperation saveEventsCooperation(EventsCooperation o)
			throws Exception {
		return eventsCooperationDao.saveObject(o);
	}

	@Override
	public EventsCooperation updateEventsCooperation(EventsCooperation o)
			throws Exception {
		return eventsCooperationDao.merge(o);
	}

	@Override
	public EventsCooperation getEventsCooperationById(int id) {
		return eventsCooperationDao.getById(id);
	}

	@Override
	public List<EventsCooperation> getEventsCooperationByEid(int eid) {
		CriteriaAdapter criteriaAdapter = eventsCooperationDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("eventId", eid));
		List<EventsCooperation> o = criteria.list();
		eventsCooperationDao.releaseHibernateSession(criteriaAdapter.getSession());
		if(o!=null && o.size()>0){
			return o;
		}
		return null;
	}

	@Override
	public void deleteEventsCooperation(EventsCooperation o) throws Exception {
		eventsCooperationDao.remove(o);
	}

}
