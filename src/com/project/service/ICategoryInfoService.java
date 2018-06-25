package com.project.service;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.InformationCategoryInfo;


public interface ICategoryInfoService {
	
	 /***
     * 获得所有分类
     * @return
     */
    public List<InformationCategoryInfo> getAllCategory();

    /***
     * 添加分类
     * @param categoryInfo
     * @return
     */
    @Transactional
    public boolean addCategory(InformationCategoryInfo categoryInfo);

    /**
     * 获得分类(带分页)
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageFinder<InformationCategoryInfo> getPageCategory(int pageNumber, int pageSize);

    /**
     * 删除分类
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteCategory(int id);

    /**
     * 修改分类
     * @param id
     * @param flag
     * @return
     */
    @Transactional
    public boolean updateCategory(InformationCategoryInfo categoryInfo);
    
    /**
     * 修改分类状态
     * @param id
     * @param flag		1正常使用 	0已删除
     * @return
     */
    @Transactional
    public boolean flagCategory(int id, int flag);
    
    /***
     * 根据ID获得分类(不带级联)
     * @param id
     * @return
     */
    public InformationCategoryInfo getCategoryInfoById(int id);
    
    
}
