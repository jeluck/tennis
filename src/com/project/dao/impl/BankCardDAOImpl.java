package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IBankCardDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.BankCard;

@Repository
public class BankCardDAOImpl extends HibernateEntityDao<BankCard> implements IBankCardDao{

}
