package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IUser_levelDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.User_level;
import com.project.service.IUser_levelService;
import com.project.util.CommonUtil;

@Service
public class IUser_levelServiceImpl implements IUser_levelService {
	
	@Resource
	private  IUser_levelDao user_levelDao;

	@Override
	public User_level updateUser_level(User_level o) throws Exception {
		return user_levelDao.merge(o);
	}

	@Override
	public User_level saveUser_level(User_level o) throws Exception {
		return user_levelDao.saveObject(o);
	}

	@Override
	public List<User_level> getUserLevleList() {
		return user_levelDao.getAll();
	}

	@Override
	public void deleteUser_level(int id) throws Exception {
		user_levelDao.removeById(id);
	}

	@Override
	public PageFinder<User_level> getPageFindeUser_level(User_level o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = user_levelDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(CommonUtil.NotEmpty(o.getLevel())){
			if(!o.getLevel().equals("0")){
				criteria.add(Restrictions.eq("level", o.getLevel()));
			}
		}
		criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
		PageFinder<User_level> pageFinder = user_levelDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		user_levelDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public User_level getUserLevelById(int id) {
		return user_levelDao.getById(id);
	}
	
}
