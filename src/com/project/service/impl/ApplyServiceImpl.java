package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IApplyDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Apply;
import com.project.pojo.Business_time;
import com.project.pojo.Coach;
import com.project.service.IApplyService;
import com.project.util.CommonUtil;

@Service
public class ApplyServiceImpl  implements IApplyService  {

	@Resource
	private IApplyDao applyDao;
	
	@Override
	public List<Apply> getApplyByObj(Apply o) {
		CriteriaAdapter criteriaAdapter = applyDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("coach", "coach",
				CriteriaSpecification.LEFT_JOIN);
		if(o.getPlayground_id()>0){
			criteria.add(Restrictions.eq("playground_id", o.getPlayground_id()));
		}
		if(o.getPlaygroundmanager_id()>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", o.getPlaygroundmanager_id()));
		}
		if(CommonUtil.NotEmptyObject(o.getCoach())){
			if(o.getCoach().getId()>0){
				criteria.add(Restrictions.eq("coach.id", o.getCoach().getId()));
			}
		}
        List<Apply> list = criteria.list();
        applyDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null && list.size()>0){
        	return list;
        }else{
        	return null;
        }
	}

	@Override
	public Apply saveApply(Apply o) throws Exception {
		return applyDao.saveObject(o);
	}

	@Override
	public void deleteApply(Apply o) throws Exception {
		applyDao.remove(o);
	}

	@Override
	public PageFinder<Apply> getPageFindeApply(Apply o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = applyDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("coach", "coach",
				CriteriaSpecification.LEFT_JOIN);
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		if(o.getPlayground_id()>0){
			criteria.add(Restrictions.eq("playground_id", o.getPlayground_id()));
		}
		if(o.getPlaygroundmanager_id()>0){
			criteria.add(Restrictions.eq("playgroundmanager_id", o.getPlaygroundmanager_id()));
		}
		if(o.getStatus()>0){
			criteria.add(Restrictions.eq("status", o.getStatus()));
		}
		if(CommonUtil.NotEmptyObject(o.getCoach())){
			if(CommonUtil.NotEmpty(o.getCoach().getName())){
				criteria.add(Restrictions.like("coach.name",o.getCoach().getName(),MatchMode.ANYWHERE));
			}
			if(CommonUtil.NotEmpty(o.getCoach().getPhone())){
				criteria.add(Restrictions.eq("coach.phone", o.getCoach().getPhone()));
			}
			
		}
		PageFinder<Apply> pageFinder = applyDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		applyDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Apply getApplyById(int id) {
		CriteriaAdapter criteriaAdapter = applyDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("id",id));
        List<Apply> list = criteria.list();
        applyDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null && list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
	}

	@Override
	public Apply updateApply(Apply o) throws Exception {
		return applyDao.merge(o);
	}

}
