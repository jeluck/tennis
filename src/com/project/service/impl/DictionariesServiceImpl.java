package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IDictionariesDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Course;
import com.project.pojo.Dictionaries;
import com.project.service.IDictionariesService;

@Service
public class DictionariesServiceImpl implements IDictionariesService {
	
	@Resource
	private IDictionariesDao dictionariesDao;

	@Override
	public List<Dictionaries> getDictionaries(int type) {
		CriteriaAdapter criteriaAdapter = dictionariesDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	if(type>0){
        	criteria.add(Restrictions.eq("type", type));
        }
        List<Dictionaries> list = criteria.list();
        dictionariesDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public Dictionaries saveDictionaries(Dictionaries o) throws Exception {
		return dictionariesDao.saveObject(o);
	}

	@Override
	public int getDictionariesCount(int type) {
		CriteriaAdapter criteriaAdapter = dictionariesDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	if(type>0){
        	criteria.add(Restrictions.eq("type", type));
        }
        List<Dictionaries> list = criteria.list();
        dictionariesDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list.size();
	}


}
