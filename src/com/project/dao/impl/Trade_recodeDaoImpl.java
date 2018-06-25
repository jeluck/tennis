package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ITrade_recodeDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Trade_recode;

@Repository
public class Trade_recodeDaoImpl extends HibernateEntityDao<Trade_recode> implements ITrade_recodeDao {

}
