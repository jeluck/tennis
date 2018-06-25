package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IEvents_endInfoDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Events_endInfo;

@Repository
public class Events_endInfoDaoImpl extends HibernateEntityDao<Events_endInfo> implements IEvents_endInfoDao{

}
