package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IReturnReasonDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.ReturnReason;


@Repository
public class ReturnReasonDaoImpl extends HibernateEntityDao<ReturnReason> implements IReturnReasonDao {

}
