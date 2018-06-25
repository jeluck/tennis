package com.project.dao.impl;


import org.springframework.stereotype.Repository;

import com.project.dao.IPlaygroundManagerDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.PlaygroundManager;

@Repository
public class PlaygroundManagerDaoImpl extends HibernateEntityDao<PlaygroundManager> implements IPlaygroundManagerDao{
}
