package com.project.dao.impl;

import org.springframework.stereotype.Repository;


import com.project.dao.ISpaceDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Space;

@Repository						  
public class SpaceDaoImpl extends HibernateEntityDao<Space> implements ISpaceDao {


		

}
