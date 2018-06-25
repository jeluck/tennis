package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ISubsidiesDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Subsidies;

@Repository
public class SubsidiesDaoImpl extends HibernateEntityDao<Subsidies> implements ISubsidiesDao{

}
