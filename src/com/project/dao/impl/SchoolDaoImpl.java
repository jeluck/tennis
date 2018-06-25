package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ISchoolDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.School;

@Repository
public class SchoolDaoImpl extends HibernateEntityDao<School> implements ISchoolDao  {
	
}
