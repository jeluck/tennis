package com.project.dao.impl;

import com.project.dao.ITennis_cricles_pariseDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Tennis_cricles_parise;

import org.springframework.stereotype.Repository;

@Repository
public class Tennis_cricles_pariseDaoImpl extends HibernateEntityDao<Tennis_cricles_parise> implements ITennis_cricles_pariseDao{
}
