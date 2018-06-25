package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IClubDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Club;
import com.project.pojo.Coach;
import com.project.service.IClubService;
import com.project.util.CommonUtil;

@Service
public class ClubServiceImpl implements  IClubService {
	
	@Resource
	private IClubDao clubDao;

	@Override
	public Club addClub(Club o) throws Exception {
		return clubDao.saveObject(o);
	}

	@Override
	public PageFinder<Club> getPageFindeClub(Club o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = clubDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(CommonUtil.NotEmpty(o.getName())){
			criteria.add(Restrictions.like("name", o.getName(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Club> pageFinder = clubDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		clubDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Club getclubById(int oid) {
		return clubDao.getById(oid);
	}

	@Override
	public Club mergeClub(Club o) throws Exception {
		return clubDao.merge(o);
	}

	@Override
	public List<Club> getAllClub() {
		return clubDao.getAll();
	}

}
