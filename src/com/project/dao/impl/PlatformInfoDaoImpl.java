package com.project.dao.impl;



import com.project.dao.IPlatformInfoDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.PlatformInfo;

import org.springframework.stereotype.Repository;

@Repository
public class PlatformInfoDaoImpl extends HibernateEntityDao<PlatformInfo> implements IPlatformInfoDao{
}
