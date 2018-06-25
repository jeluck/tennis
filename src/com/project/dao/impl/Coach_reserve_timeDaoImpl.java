package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICoach_reserve_timeDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Coach_reserve_time;

@Repository
public class Coach_reserve_timeDaoImpl extends HibernateEntityDao<Coach_reserve_time> implements ICoach_reserve_timeDao{

}
