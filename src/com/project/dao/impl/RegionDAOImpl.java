package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IRegionDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Region;

@Repository
public class RegionDAOImpl extends HibernateEntityDao<Region> implements IRegionDao{

}
