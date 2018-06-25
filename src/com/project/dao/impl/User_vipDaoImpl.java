package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IUser_vipDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.User_vip;

@Repository
public class User_vipDaoImpl extends HibernateEntityDao<User_vip> implements IUser_vipDao {

}
