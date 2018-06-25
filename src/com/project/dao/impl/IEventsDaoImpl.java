package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IEventsDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Events;

@Repository
public class IEventsDaoImpl extends HibernateEntityDao<Events> implements IEventsDao{

}
