package com.project.dao.impl;


import com.project.dao.IPlaygroundDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Playground;

import org.springframework.stereotype.Repository;

@Repository
public class PlaygroundDaoImpl extends HibernateEntityDao<Playground> implements IPlaygroundDao{
}
