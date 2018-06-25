package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.IAdvertiseDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Advertise;
import com.project.service.IAdvertiseService;
import com.project.util.CommonUtil;

@Service
public class IAdvertiseServiceImpl implements IAdvertiseService {
	
	@Resource
	private  IAdvertiseDao advertiseDao;
	
	@Override
	public List<Advertise> getAdvertiseByType(int type) {
		CriteriaAdapter criteriaAdapter = advertiseDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("adtype",type));
      	criteria.add(Restrictions.le("start_time",CommonUtil.getTimeNows()))
      			.add(Restrictions.ge("end_time",CommonUtil.getTimeNows()));
      	criteria.addOrder(Order.desc("create_time"));
        List<Advertise> list = criteria.list();
        advertiseDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

}
