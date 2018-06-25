package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IModuleDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Module;

@Repository
public class ModuleDaoImpl  extends HibernateEntityDao<Module> implements IModuleDao {

}
