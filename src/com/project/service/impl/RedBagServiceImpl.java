package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.common.Constants;
import com.project.dao.IRedBagDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Red_bag;
import com.project.service.IRedBagService;
import com.project.util.CommonUtil;

@Service
public class RedBagServiceImpl implements IRedBagService{

	@Resource
	private IRedBagDao redBagDao;
	
	
	@Transactional
	@Override
	public Red_bag saveRedBag(Red_bag red) throws Exception {
		return redBagDao.saveObject(red);
	}

	@Transactional
	@Override
	public Red_bag updateRedBag(Red_bag red) throws Exception {
		return redBagDao.merge(red);
	}


	@Override
	public PageFinder<Red_bag> getPageFindeRedBag(Red_bag red, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = redBagDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (red.getReward_type()!=0) {
			criteria.add(Restrictions.eq("reward_type", red.getReward_type()));
		}
		criteria.addOrder(org.hibernate.criterion.Order.asc("end_time"));
		PageFinder<Red_bag> pageFinder = redBagDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		redBagDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}


	@Override
	public Red_bag getRedBagById(int redId) {
		return redBagDao.getById(redId);
	}

	@Override
	public List<Red_bag> getAllRedBag() {
		CriteriaAdapter criteriaAdapter = redBagDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	criteria.add(Restrictions.eq("status", Constants.NORMAL_FLAG));
    	criteria.add(Restrictions.le("start_time",CommonUtil.getTimeNow()))
				.add(Restrictions.ge("end_time",CommonUtil.getTimeNow()));
        List<Red_bag> list = criteria.list();
        redBagDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

}
