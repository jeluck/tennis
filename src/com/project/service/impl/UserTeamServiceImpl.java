package com.project.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IUserTeamDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.User_team;
import com.project.service.IUserTeamService;

@Service
public class UserTeamServiceImpl implements IUserTeamService {
	
	@Resource
	private IUserTeamDao userTeamDao;

	@Transactional
	@Override
	public User_team saveUserTeam(User_team userTeam) throws Exception {
		return userTeamDao.saveObject(userTeam);
	}

	@Transactional
	@Override
	public void deleteUserTeam(int id) throws Exception {
		userTeamDao.removeById(id);
	}

	@Transactional
	@Override
	public User_team updateUserTeam(User_team userTeam) throws Exception {
		return userTeamDao.merge(userTeam);
	}

	@Override
	public User_team getUserTeamById(int id) {
		return userTeamDao.getById(id);
	}

	@Override
	public PageFinder<User_team> getPageFindeUserTeam(User_team userTeam,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = userTeamDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<User_team> pageFinder = userTeamDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		userTeamDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
