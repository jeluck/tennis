package com.project.dao.impl;


import org.springframework.stereotype.Repository;

import com.project.dao.IPlaygroundUserDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.PlaygroundUser;

@Repository
public class PlaygroundUserDaoImpl extends HibernateEntityDao<PlaygroundUser> implements IPlaygroundUserDao {
}
