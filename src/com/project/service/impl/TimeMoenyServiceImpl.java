package com.project.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ITimeMoenyDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.TimeMoeny;
import com.project.service.ITimeMoenyService;

@Service
public class TimeMoenyServiceImpl implements ITimeMoenyService{

	@Resource
	private ITimeMoenyDao timeMoenyDao;
	
	@Override
	public TimeMoeny saveTimeMoeny(TimeMoeny tm)throws Exception {
		return timeMoenyDao.saveObject(tm);
	}

	@Override
	public List<TimeMoeny> getTimeMoenyBySpaceId(int spaceId) {
		return timeMoenyDao.findBy("space.id", spaceId, true);
	}

	@Override
	public TimeMoeny updateTimeMoeny(TimeMoeny o) {
		return timeMoenyDao.merge(o);
	}

	@Override
	public TimeMoeny getTimeMoenyById(int id) {
		CriteriaAdapter criteriaAdapter = timeMoenyDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("id", id));
        criteria.createAlias("space", "space",CriteriaSpecification.LEFT_JOIN);
        List<TimeMoeny> list = criteria.list();
        timeMoenyDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list.get(0);
	}

	@Override
	public void flush() {
		timeMoenyDao.flush();
	}

	@Override
	public TimeMoeny getTimeMoenyByPdId(int pdId) {
		CriteriaAdapter criteriaAdapter = timeMoenyDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.setProjection(
								Projections.projectionList()
									.add(Projections.min("price").as("price"))
									.add(Projections.property("playground_id"))
							  );
		criteria.add(Restrictions.eq("playground_id",pdId));
		List result = criteria.list();
		Iterator iterator = result.iterator();
		TimeMoeny t = new TimeMoeny();
		while(iterator.hasNext()) {
		    Object[] o = (Object[]) iterator.next();
		    t.setPrice(Double.valueOf(o[0].toString()));
		    t.setPlayground_id(Integer.valueOf(o[1].toString()));
		}
		timeMoenyDao.releaseHibernateSession(criteriaAdapter.getSession());
		return t;
	}

	@Override
	public TimeMoeny getTimeMoenyBySpaHourType(int spaceId, int hour, int type) {
		CriteriaAdapter criteriaAdapter = timeMoenyDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("space", "space",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("space.id", spaceId));
        criteria.add(Restrictions.eq("hour", hour));
        criteria.add(Restrictions.eq("time_type", type));
        List<TimeMoeny> list = criteria.list();
        timeMoenyDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list.get(0);
	}

	@Override
	public void deleteTimeMoeny(TimeMoeny tm) throws Exception {
		timeMoenyDao.remove(tm);
	}
	
	
}
