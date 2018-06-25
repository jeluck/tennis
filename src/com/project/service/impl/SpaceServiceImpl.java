package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.project.dao.ISpaceDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Space;
import com.project.service.ISpaceService;
import com.project.util.CommonUtil;

@Service
public class SpaceServiceImpl implements ISpaceService{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SpaceServiceImpl.class);
	
	@Resource
	private ISpaceDao spaceDao;
	
	@Override
	public Space getSpace(int id) {
		CriteriaAdapter criteriaAdapter = spaceDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.createAlias("playground_id", "playground_id",CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.eq("id",id));
        Space o = (Space) criteria.uniqueResult();
        spaceDao.releaseHibernateSession(criteriaAdapter.getSession());
        if(o != null){
        	return o;
        }else{
        	return null;
        }
		
	}

	@Override
	public Space saveSpace(Space c) throws Exception{
		return spaceDao.saveObject(c);
	}

	@Override
	public Space updateSpace(Space o) {
		return spaceDao.merge(o);
	}

	@Override
	public PageFinder<Space> getPageFindeSpace(Space o, int pageNumber,
			int pageSize,int oid) {
		CriteriaAdapter criteriaAdapter = spaceDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
//		if (CommonUtil.NotEmpty(o.getOrderNo())) {
//			criteria.add(Restrictions.like("orderNo", o.getOrderNo(),MatchMode.ANYWHERE));
//		}
		criteria.add(Restrictions.eq("playground_id.id", oid));
		criteria.add(Restrictions.eq("belongto", 0));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Space> pageFinder = spaceDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		spaceDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

	@Override
	public List<Space> getPlaygroundSpaceBy_PGid(int activeId,Integer belongto) {
		CriteriaAdapter criteriaAdapter = spaceDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.createAlias("playground_id", "playground_id",
				CriteriaSpecification.LEFT_JOIN);
		if(belongto==0){
			criteria.add(Restrictions.eq("playground_id.id", activeId));
		}else{
			criteria.add(Restrictions.eq("coach_id.id", activeId));
		}
		
		criteria.add(Restrictions.eq("belongto", belongto));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		List<Space> list = criteria.list();
		spaceDao.releaseHibernateSession(criteriaAdapter.getSession());
		return list;
	}

	@Override
	public List<Space> getAll() {
		return spaceDao.getAll();
	}

	@Override
	public PageFinder<Space> getPageFindeSpaceByCoach(Space o, int pageNumber,
			int pageSize, int oid) {
		CriteriaAdapter criteriaAdapter = spaceDao.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		if (CommonUtil.NotEmpty(o.getName())) {
			criteria.add(Restrictions.like("name", o.getName(),MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("coach_id.id", oid));
		criteria.add(Restrictions.eq("belongto", 1));
		criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
		PageFinder<Space> pageFinder = spaceDao.pagedByCriteria(
				criteria, pageNumber, pageSize);
		spaceDao.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
	}

}
