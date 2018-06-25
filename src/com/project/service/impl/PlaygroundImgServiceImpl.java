package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants.O_STATUS;
import com.project.dao.IPlaygroundImgDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.BankCard;
import com.project.pojo.PlaygroundImg;
import com.project.service.IPlaygroundImgService;

@Service
public class PlaygroundImgServiceImpl implements IPlaygroundImgService {
	
	@Resource
	private IPlaygroundImgDao PlaygroundImgDao;

	@Override
	public PlaygroundImg savePlaygroundImg(PlaygroundImg o) throws Exception {
		return PlaygroundImgDao.saveObject(o);
	}

	@Override
	public PlaygroundImg getPlaygroundImgByPdIb(int pdId) {
		CriteriaAdapter criteriaAdapter = PlaygroundImgDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("pdId",pdId));
		criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
		List<PlaygroundImg> list = criteria.list();
		PlaygroundImgDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list.get(0);
	}

	@Override
	public List<PlaygroundImg> getPlaygoundImgListByPdId(int pdId) {
		CriteriaAdapter criteriaAdapter = PlaygroundImgDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("pdId",pdId));
		criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
		List<PlaygroundImg> list = criteria.list();
		PlaygroundImgDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}
}
