package com.project.dao.impl;


import com.project.dao.IPlaygroundManagerAdminRoleDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.PlaygroundManagerAdminRole;

import org.springframework.stereotype.Repository;

@Repository
public class PlaygroundManagerAdminRoleDaoImpl extends HibernateEntityDao<PlaygroundManagerAdminRole> implements
		IPlaygroundManagerAdminRoleDao {

}
