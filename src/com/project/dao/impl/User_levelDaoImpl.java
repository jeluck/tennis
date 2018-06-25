package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IUser_levelDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.User_level;

@Repository
public class User_levelDaoImpl extends HibernateEntityDao<User_level> implements IUser_levelDao {
	
}
