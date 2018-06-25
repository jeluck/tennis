package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ICoach_set_timeDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coach;
import com.project.pojo.Coach_set_time;
import com.project.pojo.Region;
import com.project.pojo.Space_time_price;
import com.project.service.ICoach_set_timeService;

@Service
public class Coach_set_timeServiceImpl implements ICoach_set_timeService{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Coach_set_timeServiceImpl.class);
	
	@Resource
	private ICoach_set_timeDao coach_set_timeDao;
	
	@Override
	public void saveCoach_set_time(Integer hour,Integer coachId) throws Exception {
			Coach_set_time c=new Coach_set_time();
			c.setTime(hour);
			c.setCoach_id(new Coach());
			c.getCoach_id().setId(coachId);
			coach_set_timeDao.saveObject(c);
	}

	@Override
	public Coach_set_time updateCoach_set_time(Coach_set_time o)
			throws Exception {
		return coach_set_timeDao.merge(o);
	}

	@Override
	public List<Coach_set_time> getCoach_set_timeByCoachId(int coachId,int time_type,int coachType) {
		CriteriaAdapter criteriaAdapter = coach_set_timeDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("coach_id", "coach_id",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("coach_id.id",coachId));
        if(coachType>0){
        	criteria.add(Restrictions.eq("coach_id.coachType",coachType));
        }
        if(time_type>0){
        	criteria.add(Restrictions.eq("time_type",time_type));
        }
        criteria.addOrder(Order.asc("time"));
        List<Coach_set_time> list = criteria.list();
        coach_set_timeDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

	@Override
	public void deleteById(int id) throws Exception {
		Coach_set_time o=coach_set_timeDao.getById(id);
		
		coach_set_timeDao.remove(o);
		
	}

	@Override
	public Coach_set_time saveCoachSetTime(Coach_set_time o) throws Exception {
		return coach_set_timeDao.saveObject(o);
	}

	@Override
	public void deleteBycoachId(Coach_set_time o) throws Exception {
		coach_set_timeDao.remove(o);
	}

	@Override
	public Coach_set_time getCoach_set_timeById(int id) {
		return coach_set_timeDao.getById(id);
	}

	@Override
	public Coach_set_time getCoach_set_timeByTypeAndHour(int coachId,int type, int hour) {
		CriteriaAdapter criteriaAdapter = coach_set_timeDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("coach_id.id",coachId));
        criteria.add(Restrictions.eq("time_type", type));
        criteria.add(Restrictions.eq("time", hour));
        List<Coach_set_time> list = criteria.list();
        coach_set_timeDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
		
	}

	@Override
	public List<Coach_set_time> getCoach_set_timeByCoachId(Integer coachId) {
		CriteriaAdapter criteriaAdapter = coach_set_timeDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("coach_id.id",coachId));
        List<Coach_set_time> list = criteria.list();
        coach_set_timeDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

}
