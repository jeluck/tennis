package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ICoaching_experienceDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coaching_experience;
import com.project.service.ICoaching_experienceService;

@Service
public class Coaching_experienceServiceImpl implements ICoaching_experienceService {
	
	@Resource
	private ICoaching_experienceDao coaching_experienceDao;

	@Override
	public Coaching_experience saveCoaching_experience(Coaching_experience o)
			throws Exception {
		return coaching_experienceDao.saveObject(o);
	}

	@Override
	public List<Coaching_experience> getCoaching_experienceListByCoachId(
			int coachId) {
		CriteriaAdapter criteriaAdapter = coaching_experienceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("coachId",coachId));
        List<Coaching_experience> list = criteria.list();
        if(list != null && list.size()>0){
        	return list;
        }else{
        	return null;
        }
	}

	@Override
	public Coaching_experience updateCoaching_experience(Coaching_experience o)
			throws Exception {
		return coaching_experienceDao.merge(o);
	}

	@Override
	public void deleteCoaching_experience(Coaching_experience o)
			throws Exception {
		coaching_experienceDao.remove(o);
	}

}
