package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IVisitDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Visit;
import com.project.service.IVisitService;

@Service
public class VisitServiceImpl implements IVisitService {

	@Resource
	private IVisitDao visitDao;
	

	@Override
	public Visit saveVisit(Visit o) throws Exception {
		return visitDao.saveObject(o);
	}

	@Override
	public List<Visit> getVisitListByCoachId(int coachId,int viType) throws Exception {
		CriteriaAdapter criteriaAdapter = visitDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("coachId", coachId));
        if(viType>0){
        	 criteria.add(Restrictions.eq("viType", viType));
        }
        List<Visit> list = criteria.list();
        visitDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

	@Override
	public void deleteVisit(Visit o) throws Exception {
		visitDao.remove(o);
	}

	
}
