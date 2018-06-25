package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IGalleryDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.ClubImg;
import com.project.pojo.Gallery;
import com.project.service.IGalleryService;

@Service
public class GalleryServiceImpl implements IGalleryService {

	@Resource
	private IGalleryDao galleryDao;
	
	@Override
	public Gallery seveClubImg(Gallery o) throws Exception {
		return galleryDao.saveObject(o);
	}

	@Override
	public PageFinder<Gallery> getPageFindeClubGallery(Gallery o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = galleryDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(o.getType()>0){
			criteria.add(Restrictions.eq("type", o.getType()));
		}
		criteria.add(Restrictions.eq("acId", o.getAcId()));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Gallery> pageFinder = galleryDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		galleryDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Gallery getGalleryById(int oid) {
		return galleryDao.getById(oid);
	}

	@Override
	public Gallery mergeGallery(Gallery o) throws Exception {
		return galleryDao.merge(o);
	}

	@Override
	public void deleteGalleryById(int id) throws Exception {
		galleryDao.removeById(id);
	}

	@Override
	public List<Gallery> getGalleryByCidAndType(int type, int cid) {
		CriteriaAdapter criteriaAdapter = galleryDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("type", type));
        criteria.add(Restrictions.eq("acId", cid));
        List<Gallery> list = criteria.list();
        galleryDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null && list.size()>0){
        	return list;
        }else{
        	return null;
        }
	}

}
