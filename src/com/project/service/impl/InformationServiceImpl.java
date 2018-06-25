package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IInformationDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Information;
import com.project.service.IInformationService;
import com.project.util.CommonUtil;

@Service
public class InformationServiceImpl implements IInformationService {
	
	@Resource
	private IInformationDao informationDao;
	
	@Transactional
	@Override
	public Information saveInformation(Information o) throws Exception {
		return informationDao.saveObject(o);
	}

	@Transactional
	@Override
	public void deleteInformationById(int id) throws Exception {
		informationDao.removeById(id);
	}

	@Transactional
	@Override
	public Information updateInformation(Information o) throws Exception {
		return informationDao.merge(o);
	}

	@Override
	public Information getInformationById(int id) {
		return informationDao.getById(id);
	}

	@Override
	public PageFinder<Information> getPageFindeInformation(Information o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = informationDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("information_categoryinfo_id",
				"information_categoryinfo_id",CriteriaSpecification.LEFT_JOIN);
		if (o.getInformation_categoryinfo_id()!=null) {
			if (o.getInformation_categoryinfo_id().getId()>0) {
				criteria.add(Restrictions.eq("information_categoryinfo_id.id", o.getInformation_categoryinfo_id().getId()));
			}
		}
		if (CommonUtil.NotEmpty(o.getTitle())) {
			criteria.add(Restrictions.like("title", o.getTitle(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Information> pageFinder = informationDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		informationDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
