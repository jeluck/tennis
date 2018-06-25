package com.project.dao.impl;


import org.springframework.stereotype.Repository;

import com.project.dao.IUser_countDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.User_count;

@Repository
public class User_countDaoImpl extends HibernateEntityDao<User_count> implements
		IUser_countDao {

}
