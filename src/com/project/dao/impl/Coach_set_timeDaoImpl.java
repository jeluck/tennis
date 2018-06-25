package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICoach_set_timeDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Coach_set_time;

@Repository
public class Coach_set_timeDaoImpl extends HibernateEntityDao<Coach_set_time> implements
		ICoach_set_timeDao {

}
