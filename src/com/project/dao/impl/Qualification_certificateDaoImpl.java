package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IQualification_certificateDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Qualification_certificate;


@Repository
public class Qualification_certificateDaoImpl extends HibernateEntityDao<Qualification_certificate> implements IQualification_certificateDao {

}
