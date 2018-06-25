package com.project.service.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IRegionDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Activity;
import com.project.pojo.Coach;
import com.project.pojo.Region;
import com.project.service.IRegionService;

import javax.annotation.Resource;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService{

    @Resource
    private IRegionDao regionDAO;

    public PageFinder<Region> getProvince(int pageNumber, int pageSize)
    {
    	CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("parent_id", 1));
		criteria.addOrder(org.hibernate.criterion.Order.asc("region_id"));
		PageFinder<Region> pageFinder = regionDAO.pagedByCriteria(
				criteria, pageNumber, pageSize);
		regionDAO.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
    }

    public PageFinder<Region> getCity(int provinceid,int pageNumber, int pageSize)
    {
    	CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("parent_id", provinceid));
		criteria.addOrder(org.hibernate.criterion.Order.asc("region_id"));
		PageFinder<Region> pageFinder = regionDAO.pagedByCriteria(
				criteria, pageNumber, pageSize);
		regionDAO.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
    }

    public PageFinder<Region> getArea(int cityid,int pageNumber, int pageSize)
    {
    	CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
		Criteria criteria = criteriaAdapter.getCriteria();
		criteria.add(Restrictions.eq("parent_id", cityid));
		criteria.addOrder(org.hibernate.criterion.Order.asc("region_id"));
		PageFinder<Region> pageFinder = regionDAO.pagedByCriteria(
				criteria, pageNumber, pageSize);
		regionDAO.releaseHibernateSession(criteriaAdapter.getSession());
		return pageFinder;
    }

	@Override
	public Region getRegionById(int regionId) {
        return regionDAO.getById(regionId);

	}

	@Override
	public List<Region> getProvince() {
		CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("parent_id", 1));
        List<Region> list = criteria.list();
        regionDAO.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
	}

	public List<Region> getCity(String provinceid)
    {
        CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(provinceid != null && !"".equals(provinceid)) {
            criteria.add(Restrictions.eq("parent_id", Integer.parseInt(provinceid)));
        }
        List<Region> list = criteria.list();
        regionDAO.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
    }

    public List<Region> getArea(String cityid)
    {
        CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        if(cityid != null && !"".equals(cityid)) {
            criteria.add(Restrictions.eq("parent_id", Integer.parseInt(cityid)));
        }
        List<Region> list = criteria.list();
        regionDAO.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
    }

	@Override
	public Region updateRegion(Region r) {
		return regionDAO.merge(r);
	}

	@Override
	public List<Region> getRegionByStatus() {
		CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("status",1));
        List<Region> list = criteria.list();
        regionDAO.releaseHibernateSession(criteriaAdapter.getSession());
        return list;
	}

	@Override
	public Region getRegionByCityName(String cityName) {
		CriteriaAdapter criteriaAdapter = regionDAO.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("region_name", cityName));
        List<Region> list = criteria.list();
        regionDAO.releaseHibernateSession(criteriaAdapter.getSession());
        if(list != null && list.size() > 0){
        	return list.get(0);
        }else{
        	return null;
        }
	}

	@Override
	@Transactional
	public Region saveRegion(Region r) {
		try {
			return regionDAO.saveObject(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
