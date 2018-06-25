package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IInformationDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Information;

@Repository
public class InformationDaoImpl extends HibernateEntityDao<Information> implements IInformationDao {

}
