package com.project.dao.impl;

import org.springframework.stereotype.Repository;


import com.project.dao.ISpace_time_priceDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Space_time_price;

@Repository
public class Space_time_priceDaoImpl extends HibernateEntityDao<Space_time_price> implements ISpace_time_priceDao{

}
