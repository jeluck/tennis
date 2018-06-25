package com.project.dao.impl;


import com.project.dao.IAdminRoleRightDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.AdminRoleRight;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRoleRightDaoImpl extends HibernateEntityDao<AdminRoleRight> implements
		IAdminRoleRightDao {

}
