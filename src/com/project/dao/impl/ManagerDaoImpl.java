package com.project.dao.impl;


import com.project.dao.IManagerDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Manager;

import org.springframework.stereotype.Repository;

@Repository
public class ManagerDaoImpl extends HibernateEntityDao<Manager> implements
		IManagerDao {

}
