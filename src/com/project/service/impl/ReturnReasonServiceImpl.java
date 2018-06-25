package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IReturnReasonDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Module;
import com.project.pojo.ReturnReason;
import com.project.service.IReturnReasonService;

@Service
public class ReturnReasonServiceImpl implements IReturnReasonService{
	
	@Resource
	private IReturnReasonDao  returnReasonDao;

	@Override
	public List<ReturnReason> getReturnReasonlistByObj(ReturnReason o) {
		CriteriaAdapter criteriaAdapter = returnReasonDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(o.getType() > 0){
        	if(o.getS_id()>0){
        		  criteria.add(Restrictions.eq("type", o.getType()));
        		  criteria.add(Restrictions.eq("s_id", o.getS_id()));
            }
    	}
        List<ReturnReason> list = criteria.list();
        returnReasonDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null && list.size() > 0){
        	return list;	
        }else{
        	return null;
        }
	}

	@Override
	public ReturnReason saveReturnReason(ReturnReason o) throws Exception {
		return returnReasonDao.saveObject(o);
	}

}
