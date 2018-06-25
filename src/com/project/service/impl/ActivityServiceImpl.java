package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.common.Constants;
import com.project.dao.IActivityDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.service.IActivityService;

@Service
public class ActivityServiceImpl implements IActivityService {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ActivityServiceImpl.class);

	@Resource
	private IActivityDao activityDao;
	
	@Override
	public Activity saveActivity(Activity a) throws Exception {
		return activityDao.saveObject(a);
	}

	@Override
	public PageFinder<Activity> getPageFindeActivity(Activity o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = activityDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		if(o.getPlaygroundmanager_id()>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", o.getPlaygroundmanager_id()));
		}
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Activity> pageFinder = activityDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		activityDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Activity updateActivity(Activity o) throws Exception {
		return activityDao.merge(o);
	}

	@Override
	public Activity getActivityById(int oid) {
		return activityDao.getById(oid);
	}

	@Override
	public Activity getActivityByPdId(int id) {
		CriteriaAdapter criteriaAdapter = activityDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	if(id>0){
    		criteria.add(Restrictions.eq("authod_type", Constants.AUTHOD_TYPE.PLAYGROUNDMANAGER.getStatus()));
        	criteria.add(Restrictions.eq("playground_id", id));
        	criteria.add(Restrictions.eq("stick", 1));
        }
    	criteria.addOrder(Order.desc("id"));
        List<Activity> list = criteria.list();
        activityDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
		
	}
}
