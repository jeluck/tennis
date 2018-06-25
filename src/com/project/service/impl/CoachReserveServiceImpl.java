package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ICoachReserveDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coach;
import com.project.pojo.CoachReserve;
import com.project.service.ICoachReserveService;

@Service
public class CoachReserveServiceImpl implements ICoachReserveService {

	@Resource
	private ICoachReserveDao coachreserveDao;
	
	@Override
	public CoachReserve saveCoachReserve(CoachReserve c) throws Exception{
		return coachreserveDao.saveObject(c);
	}

	@Override
	public CoachReserve getCoachReserve(String date, Integer coachId, Integer timepoint) {
		CriteriaAdapter criteriaAdapter = coachreserveDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.like("date", date, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("coachId", coachId));
        criteria.add(Restrictions.eq("timepoint", timepoint));
        List<CoachReserve> list = criteria.list();
        coachreserveDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()==0){
        	return null;
        }
		return list.get(0);
	}

	@Override
	public void deleteCoachReserve(CoachReserve c) throws Exception {
		coachreserveDao.remove(c);
	}

	@Override
	public List<CoachReserve> getCoachReserveBySubNo(String subNo) {
		CriteriaAdapter criteriaAdapter = coachreserveDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("orderSubNo", subNo));
        List<CoachReserve> list = criteria.list();
        coachreserveDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

}
