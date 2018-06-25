package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IVisitDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Visit;


@Repository
public class VisitDaoImpl extends HibernateEntityDao<Visit> implements IVisitDao {

}
