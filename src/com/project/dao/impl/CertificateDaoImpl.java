package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICertificateDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Qualification_certificate;

@Repository
public class CertificateDaoImpl extends HibernateEntityDao<Qualification_certificate> implements ICertificateDao {

}
