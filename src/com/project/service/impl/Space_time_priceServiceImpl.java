package com.project.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ISpace_time_priceDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coach;
import com.project.pojo.Playground;
import com.project.pojo.Space_time_price;
import com.project.service.ISpace_time_priceService;

@Service
public class Space_time_priceServiceImpl implements ISpace_time_priceService{

	@Resource
	private ISpace_time_priceDao space_time_priceDao;
	
	@Override
	public Space_time_price saveSpace_time_price(Space_time_price s)throws Exception {
		return space_time_priceDao.saveObject(s);
	}

	@Override
	public void deleteStp(Space_time_price stp)throws Exception {
		space_time_priceDao.remove(stp);
	}

	@Override
	public List<Space_time_price> findByDate(int spaceId,String date) {
		CriteriaAdapter criteriaAdapter = space_time_priceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("space_id", "space_id",
				CriteriaSpecification.LEFT_JOIN);
        
        criteria.add(Restrictions.like("date",date, MatchMode.ANYWHERE));
        
        criteria.add(Restrictions.eq("space_id.id",spaceId));
        criteria.addOrder(org.hibernate.criterion.Order.asc("time"));
        List<Space_time_price> list = criteria.list();
        space_time_priceDao.releaseHibernateSession(criteriaAdapter.getSession());
        
        for(Space_time_price stp:list){
        	stp.getSpace_id().setPlayground_id(new Playground());
        }
        
        return list;
	}

	@Override
	public Space_time_price findById(Integer Id) {
		CriteriaAdapter criteriaAdapter = space_time_priceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("space_id", "space_id",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("id", Id));
        
        List<Space_time_price> list = criteria.list();
        space_time_priceDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list.get(0);
	}

	@Override
	public Space_time_price updatespace_time_price(Space_time_price s) {
		return space_time_priceDao.merge(s);
	}

	@Override
	public Space_time_price findByDate(int spaceId, String date, Integer timepoint) {
		CriteriaAdapter criteriaAdapter = space_time_priceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("space_id", "space_id",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.like("date",date, MatchMode.ANYWHERE));
        
        criteria.add(Restrictions.eq("space_id.id",spaceId));
        criteria.add(Restrictions.eq("time",timepoint));
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        List<Space_time_price> list = criteria.list();
        space_time_priceDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list.size()==0){
        	return null;
        }
        return list.get(0);
	}
	

	@Override
	public List<Space_time_price> findBySpaceId(int spaceId) {
		CriteriaAdapter criteriaAdapter = space_time_priceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("space_id", "space_id",
				CriteriaSpecification.LEFT_JOIN);
        
        criteria.add(Restrictions.eq("space_id.id",spaceId));
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        List<Space_time_price> list = criteria.list();
        space_time_priceDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

	@Override
	public List<Space_time_price> findByTime(int spaceId, int time,int dataType) {
		CriteriaAdapter criteriaAdapter = space_time_priceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("space_id", "space_id",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("time",time));
        
        criteria.add(Restrictions.eq("space_id.id",spaceId));
        criteria.add(Restrictions.eq("dateType",dataType));
        criteria.addOrder(org.hibernate.criterion.Order.asc("time"));
        List<Space_time_price> list = criteria.list();
        space_time_priceDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}
}
