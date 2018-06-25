package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.ICertificateDao;
import com.project.orm.basedao.CritMap;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Qualification_certificate;
import com.project.service.ICertificateService;

@Service
public class CertificateServiceImpl implements ICertificateService  {

	@Resource
	private ICertificateDao certificateDao;
	
	@Transactional	
	@Override
	public Qualification_certificate saveCertificate(Qualification_certificate o)
			throws Exception {
		return certificateDao.saveObject(o);
	}

	@Override
	public Qualification_certificate getCertificateById(int id) {
		return certificateDao.getById(id);
	}

	@Transactional
	@Override
	public Qualification_certificate updateCertificate(
			Qualification_certificate o) throws Exception {
		return certificateDao.merge(o);
	}

	@Override
	public Qualification_certificate getCertificateByTypeAndZheId(int type,
			int id) {
		CritMap critMap = new CritMap();
		critMap.addEqual("type", type);
		critMap.addEqual("id", id);
		Qualification_certificate qc = certificateDao.getObjectByCritMap(critMap, false);
		return qc;
	}

}
