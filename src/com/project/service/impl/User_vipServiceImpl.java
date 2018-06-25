package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IUser_vipDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.User_vip;
import com.project.service.IUser_vipService;
import com.project.util.CommonUtil;

@Service("user_vipServiceImpl")
public class User_vipServiceImpl implements IUser_vipService{

	@Resource
	private IUser_vipDao user_vipDao;
	
	@Override
	public User_vip saveUser_vip(User_vip o) throws Exception {
		return user_vipDao.saveObject(o);
	}

	@Override
	public User_vip updateUser_vip(User_vip o) throws Exception {
		return user_vipDao.merge(o);
	}


	@Override
	public User_vip getUser_vipById(int id) {
		return user_vipDao.getById(id);
	}

	@Override
	public PageFinder<User_vip> getPageFindeUser_vip(User_vip o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = user_vipDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (CommonUtil.NotEmpty(o.getName())) {
			criteria.add(Restrictions.like("name", o.getName(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<User_vip> pageFinder = user_vipDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		user_vipDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
