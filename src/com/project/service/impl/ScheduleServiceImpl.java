package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IScheduleDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.OrderMain;
import com.project.pojo.Schedule;
import com.project.service.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService{
	
	@Resource
	private IScheduleDao scheduleDao;

	@Override
	public Schedule saveSchedule(Schedule s) throws Exception{
		return scheduleDao.saveObject(s);
	}

	@Override
	public Schedule getScheduleAll(Integer timepoint, String week, Integer activeId, Integer belong) {
		CriteriaAdapter criteriaAdapter = scheduleDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("timepoint", timepoint));
		criteria.add(Restrictions.eq("week", week));
		criteria.add(Restrictions.eq("activeId", activeId));
		criteria.add(Restrictions.eq("belong", belong));
		Schedule s = (Schedule) criteria.uniqueResult();
		scheduleDao.releaseHibernateSession(criteriaAdapter.getSession());
		return s;
	}

	@Override
	public void deleteSchedule(Schedule s) throws Exception{
		scheduleDao.remove(s);
	}
}
