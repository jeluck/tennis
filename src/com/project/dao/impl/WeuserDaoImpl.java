package com.project.dao.impl;

import com.project.dao.IWeuserDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Weuser;

import org.springframework.stereotype.Repository;

@Repository
public class WeuserDaoImpl extends HibernateEntityDao<Weuser> implements IWeuserDao{
}
