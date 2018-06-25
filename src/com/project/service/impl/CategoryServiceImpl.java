package com.project.service.impl;

import com.project.common.Constants;
import com.project.dao.ICategoryInfoDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.InformationCategoryInfo;
import com.project.service.ICategoryInfoService;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl  implements ICategoryInfoService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CategoryServiceImpl.class);

    @Resource
    private ICategoryInfoDao categoryDao;
    
    public List<InformationCategoryInfo> getAllCategory()
    {
        CriteriaAdapter criteriaAdapter = categoryDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
        criteria.add(Restrictions.eq("flag", Constants.NORMAL_FLAG));
        criteria.add(Restrictions.gt("id", 0));
        List<InformationCategoryInfo> list = criteria.list();
        categoryDao.releaseHibernateSession(criteriaAdapter.getSession());

        return list;
    }

    @Transactional
    public boolean addCategory(InformationCategoryInfo categoryInfo)
    {
        try {
            categoryDao.save(categoryInfo);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public PageFinder<InformationCategoryInfo> getPageCategory(int pageNumber, int pageSize)
    {
        CriteriaAdapter criteriaAdapter = categoryDao.createCriteriaAdapter();
        Criteria criteria = criteriaAdapter.getCriteria();
//        criteria.createCriteria("categoryInfo", "categoryInfo",  CriteriaSpecification.LEFT_JOIN);
        criteria.add(Restrictions.gt("id", 0));
        criteria.add(Restrictions.eq("flag", Constants.NORMAL_FLAG));
        PageFinder<InformationCategoryInfo> pageFinder = categoryDao.pagedByCriteria(criteria, pageNumber, pageSize);
        categoryDao.releaseHibernateSession(criteriaAdapter.getSession());

        return pageFinder;
    }

    @Transactional
    public boolean deleteCategory(int id)
    {
        try {
            categoryDao.removeById(id);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Transactional
    public boolean updateCategory(InformationCategoryInfo categoryInfo)
    {
        categoryDao.merge(categoryInfo);
        return true;
    }
    
    @Transactional
    public boolean flagCategory(int id, int flag)
    {
        InformationCategoryInfo categoryInfo = getCategoryInfoById(id);
        categoryInfo.setFlag(flag);
        updateCategory(categoryInfo);
        return true;
    }
    
    public InformationCategoryInfo getCategoryInfoById(int id){
    	return categoryDao.getById(id);
    }
    
    
}
