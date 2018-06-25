package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IApplyDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Apply;
@Repository
public class ApplyDaoImpl  extends HibernateEntityDao<Apply> implements IApplyDao {

}
