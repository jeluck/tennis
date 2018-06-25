package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ITerraceMessageDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.TerraceMessage;

@Repository
public class TerraceMessageDaoImpl extends HibernateEntityDao<TerraceMessage> implements ITerraceMessageDao {

}
