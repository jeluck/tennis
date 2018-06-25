package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IStationMessageDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.StationMessage;

@Repository
public class StationMessageDaoImpl extends HibernateEntityDao<StationMessage> implements IStationMessageDao{

}
