package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ITimeMoenyDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.TimeMoeny;

@Repository
public class TimeMoenyDaoImpl extends HibernateEntityDao<TimeMoeny> implements ITimeMoenyDao{

}
