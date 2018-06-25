package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ICycleDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.pojo.Coach;
import com.project.pojo.Cycle;
import com.project.pojo.Space_time_price;
import com.project.service.ICycleService;

@Service
public class ICycleServiceImpl implements ICycleService {

	@Resource
	private ICycleDao cycleDao;
	
	@Override
	public Cycle saveCycle(Cycle c) throws Exception{
		return cycleDao.saveObject(c);
	}

	@Override
	public Cycle updateCycle(Cycle c)throws Exception{
		return cycleDao.merge(c);
	}

	@Override
	public boolean getAll(Space_time_price s) {
		List<Cycle> clist=cycleDao.getAll();
		
		
		for(Cycle c:clist){
			if(c.getSpaceId()!=null){
				if(c.getTime().equals(s.getDate()) && c.getTimepoint().equals(String.valueOf(s.getTime())) && c.getSpaceId()==s.getSpace_id().getId()){
					return true;
				}
			}else{
				if(c.getTime().equals(s.getDate()) && c.getTimepoint().equals(String.valueOf(s.getTime())) ){
					return true;
				}
			}
			
		}
		
		return false;
	}

	@Override
	public void deleteByOrderMainNo(Cycle c) {
		try {
			cycleDao.remove(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cycle> getOrderMainNoCycle(String orderMainNo) {
		CriteriaAdapter criteriaAdapter = cycleDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("orderMainNo", orderMainNo));
        List<Cycle> list = criteria.list();
        cycleDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

}
