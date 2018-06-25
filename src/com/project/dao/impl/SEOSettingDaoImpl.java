package com.project.dao.impl;



import com.project.dao.ISEOSettingDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.SEOSetting;

import org.springframework.stereotype.Repository;

@Repository
public class SEOSettingDaoImpl extends HibernateEntityDao<SEOSetting> implements ISEOSettingDao{
}
