package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IEventsCooperationDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.EventsCooperation;


@Repository
public class EventsCooperationDaoImpl extends  HibernateEntityDao<EventsCooperation> implements IEventsCooperationDao {

}
