package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ILoveCollectionDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.LoveCollection;

@Repository
public class LoveCollectionDaoImpl extends HibernateEntityDao<LoveCollection> implements ILoveCollectionDao{

}
