package com.project.dao.impl;


import com.project.dao.IInternetInfoDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.InternetInfo;

import org.springframework.stereotype.Repository;

@Repository
public class InternetInfoDaoImpl extends HibernateEntityDao<InternetInfo> implements IInternetInfoDao{
}
