package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IRedBagRecordDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Red_bag_record;
import com.project.pojo.Space_time_price;
import com.project.service.IRedBagRecordService;

@Service
public class RedBagRecordServiceImpl implements IRedBagRecordService{

	@Resource
	private IRedBagRecordDao redBagRecordDao;

	@Transactional
	@Override
	public Red_bag_record saveRedBagRecord(Red_bag_record red) throws Exception {
		return redBagRecordDao.saveObject(red);
	}


	@Override
	public PageFinder<Red_bag_record> getPageFindeRedBagRecord(Red_bag_record red,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = redBagRecordDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
		if (red.getReward_type()!=0) {
			criteria.add(Restrictions.eq("reward_type", red.getReward_type()));
		}
		criteria.addOrder(org.hibernate.criterion.Order.asc("end_time"));
		PageFinder<Red_bag_record> pageFinder = redBagRecordDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		redBagRecordDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Red_bag_record getRedBagRecordById(int redId) {
		return redBagRecordDao.getById(redId);
	}


	@Override
	public List<Red_bag_record> getRed_bag_recordByUserId(Integer userId, Integer type) {
		CriteriaAdapter criteriaAdapter = redBagRecordDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("weuser", "weuser",
				CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("weuser.id",userId));
        criteria.add(Restrictions.eq("reward_type",type));
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        List<Red_bag_record> list = criteria.list();
        redBagRecordDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list==null){
        	new ArrayList<Red_bag_record>();
        }
        return list;
	}
	

	
}
