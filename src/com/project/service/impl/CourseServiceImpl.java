package com.project.service.impl;

import com.project.common.Constants;
import com.project.dao.ICourseDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Course;
import com.project.service.ICourseService;
import com.project.util.CommonUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CourseServiceImpl.class);

    @Resource
    private ICourseDao brandDao;

    @Override
    public List<Course> getAllCourse(int city_show_id,int stick,String name)
    {
        CriteriaAdapter criteriaAdapter = brandDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	if(city_show_id>0){
        	criteria.add(Restrictions.eq("city_show_id", city_show_id));
        }
    	if(stick>=0){
    		criteria.add(Restrictions.eq("stick",stick));
        }
    	
    	if(CommonUtil.NotEmpty(name)){
    		
         	criteria.add(Restrictions.like("title",name,MatchMode.ANYWHERE));
        }
        List<Course> list = criteria.list();
        brandDao.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
    }

	@Override
	@Transactional
	public Course saveCourse(Course o) {
    	try {
    		o.setCreate_time(CommonUtil.getTimeNow());
    		o.setUpdate_time(CommonUtil.getTimeNow());
			return brandDao.saveObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}

	@Override
	@Transactional
	public Course updateCourse(Course o) {
		o.setUpdate_time(CommonUtil.getTimeNow());
    	return brandDao.merge(o);
	}

	@Override
	public Course getCourseById(int id) {
		return brandDao.getById(id);
	}

	@Override
	public PageFinder<Course> getCourseList(Course o, int pageNumber,
			int pageSize) {
		CriteriaAdapter criteriaAdapter = brandDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (CommonUtil.NotEmpty(o.getTitle())) {
			criteria.add(Restrictions.like("title", o.getTitle(),MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("id"));
		PageFinder<Course> list = brandDao.pagedByCriteria(criteria, pageNumber, pageSize);
		brandDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public Course getCourseByUserId(int userId) {
		CriteriaAdapter criteriaAdapter = brandDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
    	if(userId>0){
        	criteria.add(Restrictions.eq("weuser.id", userId));
        	criteria.add(Restrictions.eq("stick", 1));
        	criteria.add(Restrictions.eq("authod_type", Constants.AUTHOD_TYPE.WEUSER.getStatus()));
        }
    	criteria.addOrder(Order.desc("id"));
        List<Course> list = criteria.list();
        brandDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }else{
        	return null;
        }
		
	}
}
