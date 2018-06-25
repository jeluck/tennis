package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IBusiness_timeDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Business_time;

@Repository
public class Business_timeDaoImpl extends HibernateEntityDao<Business_time> implements IBusiness_timeDao {

}
