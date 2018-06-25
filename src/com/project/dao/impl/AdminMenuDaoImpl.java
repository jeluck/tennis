package com.project.dao.impl;


import com.project.dao.IAdminMenuDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.AdminMenu;

import org.springframework.stereotype.Repository;

@Repository
public class AdminMenuDaoImpl extends HibernateEntityDao<AdminMenu> implements IAdminMenuDao {


}
