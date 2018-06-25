package com.project.dao.impl;


import com.project.dao.IAdminRoleDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.AdminRole;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRoleDaoImpl extends HibernateEntityDao<AdminRole> implements
		IAdminRoleDao {

}
