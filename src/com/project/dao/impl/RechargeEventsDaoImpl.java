package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IRechargeEventsDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.RechargeEvents;

@Repository
public class RechargeEventsDaoImpl extends HibernateEntityDao<RechargeEvents> implements IRechargeEventsDao  {

}
