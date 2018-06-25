package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import com.project.dao.IVideo_classificationDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Video_classification;
import com.project.service.IVideo_classificationService;

@Service
public class Video_classificationServiceImpl implements IVideo_classificationService{
	@Resource
	private IVideo_classificationDao video_classificationDao;

	@Override
	public Video_classification saveVideo_classification(Video_classification e)
			throws Exception {
		return video_classificationDao.saveObject(e);
	}

	@Override
	public PageFinder<Video_classification> getPageFindeVideo_classification(
			Video_classification o, int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = video_classificationDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Video_classification> pageFinder = video_classificationDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		video_classificationDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Video_classification updateVideo_classification(
			Video_classification o) throws Exception {
		return video_classificationDao.merge(o);
	}

	@Override
	public Video_classification getVideo_classificationById(int oid) {
		return video_classificationDao.getById(oid);
	}

	@Override
	public List<Video_classification> getAll() {
		return video_classificationDao.getAll();
	}
}
