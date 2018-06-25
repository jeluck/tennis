package com.project.dao.impl;


import org.springframework.stereotype.Repository;

import com.project.dao.IOrderMainDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.OrderMain;

@Repository
public class OrderMainDaoImpl extends HibernateEntityDao<OrderMain> implements IOrderMainDao{
}
