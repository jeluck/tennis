package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IActivityDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Activity;

@Repository
public class ActivityDaoImpl extends HibernateEntityDao<Activity> implements IActivityDao{

}
