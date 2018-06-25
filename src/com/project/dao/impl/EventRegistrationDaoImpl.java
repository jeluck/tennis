package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IEventRegistrationDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.EventRegistration;

@Repository
public class EventRegistrationDaoImpl extends HibernateEntityDao<EventRegistration> implements IEventRegistrationDao{

}
