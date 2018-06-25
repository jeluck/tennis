package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ILoveCollectionDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.LoveCollection;
import com.project.pojo.Orderinfo;
import com.project.service.ILoveCollectionService;

@Service
public class LoveCollectionServiceImpl implements ILoveCollectionService{

	@Resource
	private ILoveCollectionDao loveCollectionDao;
	
	@Override
	public LoveCollection saveLoveCollection(LoveCollection l) throws Exception {
		return loveCollectionDao.saveObject(l);
	}

	@Override
	public LoveCollection getLcByUserAndActive(Integer userId, Integer activeId, Integer type) {
		CriteriaAdapter criteriaAdapter = loveCollectionDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("dataID", String.valueOf(activeId)));
		criteria.add(Restrictions.eq("weuser.id", userId));
		criteria.add(Restrictions.eq("collectionType", type));
		LoveCollection o = loveCollectionDao.queryObjectByCriteria(criteria);
		loveCollectionDao.releaseHibernateSession(criteriaAdapter.getSession());
		return o;
	}

	@Override
	public void deleteLoveCollection(LoveCollection l) throws Exception {
		loveCollectionDao.remove(l);
	}

}
