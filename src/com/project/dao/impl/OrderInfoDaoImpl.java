package com.project.dao.impl;


import com.project.dao.IOrderInfoDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Orderinfo;

import org.springframework.stereotype.Repository;

@Repository
public class OrderInfoDaoImpl extends HibernateEntityDao<Orderinfo> implements IOrderInfoDao{
}
