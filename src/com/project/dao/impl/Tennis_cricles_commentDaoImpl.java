package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ITennis_cricles_commentDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Tennis_cricles_comment;

@Repository
public class Tennis_cricles_commentDaoImpl extends HibernateEntityDao<Tennis_cricles_comment> implements ITennis_cricles_commentDao{

}
