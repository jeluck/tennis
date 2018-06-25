package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ICoach_teach_personDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Coach_teach_person;
import com.project.pojo.Module;
import com.project.service.ICoach_teach_personService;

@Service
public class Coach_teach_personServiceImpl implements ICoach_teach_personService{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Coach_teach_personServiceImpl.class);
	
	
	@Resource
	private ICoach_teach_personDao coach_teach_personDao;
	
	@Override
	public Coach_teach_person saveCoach_teach_person(Coach_teach_person o)
			throws Exception {
		return coach_teach_personDao.saveObject(o);
	}

	@Override
	public Coach_teach_person updateCoach_teach_person(Coach_teach_person o)
			throws Exception {
		return coach_teach_personDao.merge(o);
	}

	@Override
	public List<Coach_teach_person> getCoach_teach_personByCoachId(int coachId) {
		List<Coach_teach_person> ctpList=coach_teach_personDao.findBy("coach_id", coachId, true);
		
		return ctpList;
	}

	@Override
	public void deleteById(int id) throws Exception{
		Coach_teach_person o=coach_teach_personDao.getById(id);
		
		coach_teach_personDao.remove(o);
		
	}

	@Override
	public Coach_teach_person getCoach_teach_personById(Integer id) {
		if(id==0){
			return null;
		}
		return coach_teach_personDao.getById(id);
	}

	@Override
	public Coach_teach_person getCoach_teach_personByCidAndper(Integer coachId) {
		CriteriaAdapter criteriaAdapter = coach_teach_personDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("coach_id",coachId));
        criteria.add(Restrictions.eq("people_num",1));
        List<Coach_teach_person> list = criteria.list();
        coach_teach_personDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()==0){
        	return null;
        }
        return list.get(0);
	}

	@Override
	public PageFinder<Coach_teach_person> getPageFindeCoach_teach_person(Coach_teach_person o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = coach_teach_personDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(o != null){
			if(o.getCoach_id()>0){
				criteria.add(Restrictions.eq("coach_id", o.getCoach_id()));
			}
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Coach_teach_person> pageFinder = coach_teach_personDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		coach_teach_personDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
