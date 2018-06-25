package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ICoach_reserve_timeDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coach_reserve_time;
import com.project.service.ICoach_reserve_timeService;

@Service
public class Coach_reserve_timeServiceImpl implements ICoach_reserve_timeService{

	@Resource
	private ICoach_reserve_timeDao coach_reserve_timeDao;
	
	@Override
	public Coach_reserve_time saveCrt(Coach_reserve_time o)throws Exception {
		
		return coach_reserve_timeDao.saveObject(o);
	}

	@Override
	public List<Coach_reserve_time> getByTime(String data) {
		CriteriaAdapter criteriaAdapter = coach_reserve_timeDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("date", data));
        List<Coach_reserve_time> list = criteria.list();
        coach_reserve_timeDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
	}

	@Override
	public void deleteCrt(Integer id)throws Exception {
		coach_reserve_timeDao.removeById(id);
	}

}
