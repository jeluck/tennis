package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import com.project.dao.IVideoDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Video;
import com.project.service.IVideoService;

@Service
public class VideoServiceImpl implements IVideoService{
	@Resource
	private IVideoDao videoDao;

	@Override
	public Video saveVideo(Video e) throws Exception {
		return videoDao.saveObject(e);
	}

	@Override
	public PageFinder<Video> getPageFindeVideo(Video o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = videoDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Video> pageFinder = videoDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		videoDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Video updateVideo(Video o) throws Exception {
		return videoDao.merge(o);
	}
	

	@Override
	public Video getVideoById(int oid) {
		return videoDao.getById(oid);
	}
}
