package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ISubsidiesSettlementDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.SubsidiesSettlement;

@Repository
public class SubsidiesSettlementDaoImpl extends HibernateEntityDao<SubsidiesSettlement> implements ISubsidiesSettlementDao {

}
