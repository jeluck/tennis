package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICycleDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Cycle;

@Repository
public class ICycleDaoImpl extends HibernateEntityDao<Cycle> implements ICycleDao{

}
