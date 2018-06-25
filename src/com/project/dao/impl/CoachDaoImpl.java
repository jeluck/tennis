package com.project.dao.impl;


import com.project.dao.ICoachDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Coach;

import org.springframework.stereotype.Repository;

@Repository
public class CoachDaoImpl extends HibernateEntityDao<Coach> implements ICoachDao{
}
