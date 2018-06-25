package com.project.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.controller.client.CommonController;
import com.project.dao.ISubsidiesDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Subsidies;
import com.project.pojo.SubsidiesSettlement;
import com.project.service.ISubsidiesService;
import com.project.util.CommonUtil;

@Service
public class SubsidiesServiceImpl implements ISubsidiesService {

	@Resource
	private ISubsidiesDao subsidiesDao; 
	
	@Override
	public Subsidies saveSubsidies(Subsidies o) throws Exception {
		return subsidiesDao.saveObject(o);
	}

	@Override
	public Subsidies getSubsidiesByPdId(int id,int zhe_type) {
		CriteriaAdapter criteriaAdapter = subsidiesDao.createCriteriaAdapter();
	    Criteria criteria = criteriaAdapter.getCriteria();
	    criteria.add(Restrictions.eq("zhe_id", id));
	    criteria.add(Restrictions.eq("zhe_type", zhe_type));
//	    criteria.add(Restrictions.ge("end_time", CommonUtil.getTimeNow()));
	    List<Subsidies> list = criteria.list();
	    subsidiesDao.releaseHibernateSession(criteriaAdapter.getSession());
	    if(list!=null&&list.size()>0){
	    	return list.get(0);
	    }else{
	    	return null;
	    }
	}

	@Override
	public Subsidies updateSubsidies(Subsidies o) {
		return subsidiesDao.merge(o);
	}

	@Override
	public List<Subsidies> getSubsidiesByObj(Subsidies o) {
		CriteriaAdapter criteriaAdapter = subsidiesDao.createCriteriaAdapter();
	    Criteria criteria = criteriaAdapter.getCriteria();
	    if(CommonUtil.NotEmpty(o.getYear())){
	    	 criteria.add(Restrictions.eq("year", o.getYear()));
	    }
	    if(CommonUtil.NotEmpty(o.getMonth())){
	    	 criteria.add(Restrictions.eq("month", o.getMonth()));
	    }
	    if(o.getZhe_type()>0){
	    	 criteria.add(Restrictions.eq("zhe_type", o.getZhe_type()));
	    }
	    if(o.getZhe_id()>0){
	    	 criteria.add(Restrictions.eq("zhe_id", o.getZhe_id()));
	    }
	    if(o.getType()>0){
	    	 criteria.add(Restrictions.eq("type", o.getType()));
	    }
	    if(o.getStatus()>=0){
	    	 criteria.add(Restrictions.eq("status", o.getStatus()));
	    }
	    criteria.addOrder(Order.desc("year"));
	    criteria.addOrder(Order.desc("month"));
	    List<Subsidies> list = criteria.list();
	    subsidiesDao.releaseHibernateSession(criteriaAdapter.getSession());
		
	    if(list != null && list.size()>0){
	    	return list;
	    }else{
	    	return null;
	    }
	   
	}

	@Override
	public void deleteSubsidies(Subsidies o) throws Exception {
		subsidiesDao.remove(o);
	}

	@Override
	public PageFinder<Subsidies> getPageFindeSubsidies(Subsidies o,
			int pageNumber, int pageSize) {
		CriteriaAdapter criteriaAdapter = subsidiesDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		Calendar a=Calendar.getInstance();
//		a.get(Calendar.YEAR);
		if(o.getZhe_type()>0){
	    	 criteria.add(Restrictions.eq("zhe_type", o.getZhe_type()));
	    }
		if(o.getZhe_type()>0){
	    	 criteria.add(Restrictions.eq("zhe_id", o.getZhe_id()));
	    }
//		criteria.add(Restrictions.eq("year",String.valueOf(a.get(Calendar.YEAR))));
		criteria.addOrder(Order.asc("year"));
		criteria.addOrder(Order.asc("month"));
		PageFinder<Subsidies> pageFinder = subsidiesDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		subsidiesDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public Subsidies getSubsidiesById(int id) {
		return subsidiesDao.getById(id);
	}

	@Override
	public List<Subsidies> getSubsidiesByMapAndObj(Subsidies o, Map<?, ?> map) {
		CriteriaAdapter criteriaAdapter = subsidiesDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if(o.getZhe_id()>0){
			criteria.add(Restrictions.eq("zhe_id", o.getZhe_id()));
		}
		if(o.getType()>0){
			criteria.add(Restrictions.eq("type", o.getType()));
		}
		if(o.getZhe_type()>0){
			criteria.add(Restrictions.eq("zhe_type", o.getZhe_type()));
		}
//		if(o.getStatus()>=0){
//			criteria.add(Restrictions.eq("status", o.getStatus()));
//		}
//		if(CommonUtil.NotEmptyObject(map.get("type")) && map.get("type").toString().equals("week")){
//			Map<String,String> map1 = CommonController.getWeekDay();
//			criteria.add(Restrictions.between("create_time", map1.get("mon"), map1.get("sun")));
//			
//		}
//		if(CommonUtil.NotEmptyObject(map.get("type")) && map.get("type").toString().equals("month")){
//			Map<String,String> map1 = CommonController.getMonthDate();
//			criteria.add(Restrictions.between("create_time", map1.get("monthF"), map1.get("monthL")));
//		}
		if(CommonUtil.NotEmpty(o.getYear())){
			criteria.add(Restrictions.eq("year", o.getYear()));
		}
		if(CommonUtil.NotEmpty(o.getMonth())){
			criteria.add(Restrictions.eq("month", o.getMonth()));
		}
	
		criteria.addOrder(Order.desc("year"));
		criteria.addOrder(Order.desc("month"));
		List<Subsidies> list = criteria.list();
		subsidiesDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}


}
