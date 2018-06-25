package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IClubImgDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.ClubImg;
import com.project.pojo.Course;
import com.project.service.IClubImgService;
import com.project.util.CommonUtil;


@Service
public class ClubImgServiceImpl implements IClubImgService {
	
	@Resource
	private IClubImgDao ClubImgDao;

	@Override
	public ClubImg seveClubImg(ClubImg o) throws Exception {
		return ClubImgDao.saveObject(o);
	}

	@Override
	public PageFinder<ClubImg> getPageFindeClubImg(ClubImg o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = ClubImgDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(o.getClubId()>0){
			criteria.add(Restrictions.eq("clubId", o.getClubId()));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<ClubImg> pageFinder = ClubImgDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		ClubImgDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public ClubImg getClubImgById(int oid) {
		return ClubImgDao.getById(oid);
	}

	@Override
	public ClubImg mergeClubImg(ClubImg o) throws Exception {
		return ClubImgDao.merge(o);
	}

	@Override
	public void deleteClubImgById(int id) throws Exception {
		ClubImgDao.removeById(id);
	}

	@Override
	public ClubImg getClubImgByClubId(int cid) {
		CriteriaAdapter criteriaAdapter = ClubImgDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("clubId", cid));
        List<ClubImg> list = criteria.list();
        ClubImgDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null && list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
	}

	@Override
	public List<ClubImg> getClubImgsByClubId(int cid) {
		CriteriaAdapter criteriaAdapter = ClubImgDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("clubId", cid));
        List<ClubImg> list = criteria.list();
        ClubImgDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null && list.size()>0){
        	return list;
        }else{
        	return null;
        }
	}

}
