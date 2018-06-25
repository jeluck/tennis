package com.project.dao.impl;



import com.project.dao.IAdvertiseDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Advertise;

import org.springframework.stereotype.Repository;

@Repository
public class AdvertiseDaoImpl extends HibernateEntityDao<Advertise> implements IAdvertiseDao{
}
