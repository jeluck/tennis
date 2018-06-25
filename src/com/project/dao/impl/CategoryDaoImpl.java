package com.project.dao.impl;


import com.project.dao.ICategoryInfoDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.InformationCategoryInfo;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends HibernateEntityDao<InformationCategoryInfo> implements ICategoryInfoDao{
}
