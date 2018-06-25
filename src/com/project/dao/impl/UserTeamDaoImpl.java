package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IUserTeamDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.User_team;

@Repository
public class UserTeamDaoImpl extends HibernateEntityDao<User_team> implements IUserTeamDao {

}
