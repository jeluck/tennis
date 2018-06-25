package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IWithdrawOrderDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.WithdrawOrder;

@Repository
public class WithdrawOrderDaoImpl extends HibernateEntityDao<WithdrawOrder> implements IWithdrawOrderDao{

}
