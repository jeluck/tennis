package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IRedBagDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Red_bag;

@Repository
public class RedBagDaoImpl extends HibernateEntityDao<Red_bag> implements IRedBagDao{

}
