package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IScheduleDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Schedule;

@Repository
public class ScheduleDaoImpl extends HibernateEntityDao<Schedule> implements IScheduleDao{

}
