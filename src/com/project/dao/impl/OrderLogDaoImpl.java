package com.project.dao.impl;



import com.project.dao.IOrderLogDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.OrderLog;

import org.springframework.stereotype.Repository;

@Repository
public class OrderLogDaoImpl extends HibernateEntityDao<OrderLog> implements IOrderLogDao{
}
